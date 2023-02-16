package com.dd.mu;

import org.junit.jupiter.api.*;

@DisplayName("JUnit5 测试用例")
public class JUnit5Demo {
    @BeforeAll
    public static void init() {
        System.out.println(" ::初始化数据,在所有方法执行前执行，只执行一次");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("::清理数据,在最后一个方法执行后执行，只执行一次");
    }

    @BeforeEach
    public void tearUp() {
        System.out.println("[[ 在每一个测试方法开始之前执行,执行次数与测试的方法数相同");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("在每一个测试方法结束后执行,执行次数与测试的方法数相同 ]]");
    }

    @DisplayName("第一个测试")
    @Test
    void theArenaTest() {
        System.out.println("first test");
    }

    @DisplayName("third")
    @Test
    void theThirdTest() {
        System.out.println("third test");
    }

    @DisplayName("second")
    @Test
    void theSecondTest() {
        System.out.println("second test");
    }
}
