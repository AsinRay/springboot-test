package extensions.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.ReflectionSupport;


@Target({TYPE, METHOD})
@Retention(RUNTIME)
@ExtendWith(CaptureSystemOutput.Extension.class)
public @interface CaptureSystemOutput {

    class Extension implements BeforeAllCallback, AfterAllCallback, AfterEachCallback, ParameterResolver {

        @Override
        public void beforeAll(ExtensionContext context) {
            getOutputCapture(context).captureOutput();
        }

        public void afterAll(ExtensionContext context) {
            getOutputCapture(context).releaseOutput();
        }

        @Override
        public void afterEach(ExtensionContext context) {
            OutputCapture outputCapture = getOutputCapture(context);
            try {
                if (!outputCapture.matchers.isEmpty()) {
                    String output = outputCapture.toString();
                    assertThat(output, allOf(outputCapture.matchers));
                }
            } finally {
                outputCapture.reset();
            }
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            boolean isTestMethodLevel = extensionContext.getTestMethod().isPresent();
            boolean isOutputCapture = parameterContext.getParameter().getType() == OutputCapture.class;
            return isTestMethodLevel && isOutputCapture;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            return getOutputCapture(extensionContext);
        }

        private OutputCapture getOutputCapture(ExtensionContext context) {
            return getOrComputeIfAbsent(getStore(context), OutputCapture.class);
        }

        private <V> V getOrComputeIfAbsent(Store store, Class<V> type) {
            return store.getOrComputeIfAbsent(type, ReflectionSupport::newInstance, type);
        }

        private Store getStore(ExtensionContext context) {
            return context.getStore(Namespace.create(getClass()));
        }

    }


    static class OutputCapture {

        private final List<Matcher<? super String>> matchers = new ArrayList<>();

        private CaptureOutputStream captureOut;

        private CaptureOutputStream captureErr;

        private ByteArrayOutputStream copy;

        void captureOutput() {
            this.copy = new ByteArrayOutputStream();
            this.captureOut = new CaptureOutputStream(System.out, this.copy);
            this.captureErr = new CaptureOutputStream(System.err, this.copy);
            System.setOut(new PrintStream(this.captureOut));
            System.setErr(new PrintStream(this.captureErr));
        }

        void releaseOutput() {
            System.setOut(this.captureOut.getOriginal());
            System.setErr(this.captureErr.getOriginal());
            this.copy = null;
        }

        private void flush() {
            try {
                this.captureOut.flush();
                this.captureErr.flush();
            } catch (IOException ex) {
                // ignore
            }
        }

        public void expect(Matcher<? super String> matcher) {
            this.matchers.add(matcher);
        }

        /**
         * Return all captured output to {@code System.out} and {@code System.err} as a single string.
         */
        @Override
        public String toString() {
            flush();
            return this.copy.toString();
        }

        void reset() {
            this.matchers.clear();
            this.copy.reset();
        }

        private static class CaptureOutputStream extends OutputStream {

            private final PrintStream original;

            private final OutputStream copy;

            CaptureOutputStream(PrintStream original, OutputStream copy) {
                this.original = original;
                this.copy = copy;
            }

            PrintStream getOriginal() {
                return this.original;
            }

            @Override
            public void write(int b) throws IOException {
                this.copy.write(b);
                this.original.write(b);
                this.original.flush();
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                this.copy.write(b, off, len);
                this.original.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                this.copy.flush();
                this.original.flush();
            }
        }
    }
}

