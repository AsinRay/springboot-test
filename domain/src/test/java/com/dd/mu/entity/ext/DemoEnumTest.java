package com.dd.mu.entity.ext;

import org.junit.jupiter.api.Test;

public class DemoEnumTest {

    @Test
    public void enumTest(){
        System.out.println(DemoEnum.G.value);
        System.out.println(DemoEnum.valueOf("R").value);
        System.out.println(DemoEnum.valueOf("r").value);
    }
}
