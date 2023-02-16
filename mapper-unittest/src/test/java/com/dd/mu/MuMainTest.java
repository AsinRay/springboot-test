package com.dd.mu;

import extensions.annotation.CaptureSystemOutput;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@CaptureSystemOutput
public class MuMainTest {
    @Test
    void test(CaptureSystemOutput.OutputCapture outputCapture) {
        MuMain.main(new String[] {});
        String output = outputCapture.toString();
        assertThat(output).contains("Sys");
        assertThat(output).contains("2002");
        assertThat(output).contains("help");
        assertThat(output).contains("2022-06-06T12:12:12");
        //assertThat(output).contains("1,Sys,2002,help,2020-06-06 12:12:12");
    }
}
