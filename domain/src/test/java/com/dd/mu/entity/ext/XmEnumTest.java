package com.dd.mu.entity.ext;

import org.junit.jupiter.api.Test;

public class XmEnumTest {
    @Test
    public void getEnumTest(){
        XmEnum stopHere = XmEnum.getEnum("Stop Here");
        System.out.println(stopHere);
    }
}
