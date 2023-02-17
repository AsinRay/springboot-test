package com.dd.mu.entity.ext;

import java.util.Arrays;
import java.util.List;

public enum DemoEnum {
    R("Start There"),
    G("Start Here");
    String value;
    DemoEnum(String s) {
        value = s.toUpperCase();
    }

    public static DemoEnum getEnum(String value) {
        List<DemoEnum> list = Arrays.asList(DemoEnum.values());
        return list.stream().filter(m -> m.value.equals(value)).findAny().orElse(null);
    }

    public static DemoEnum getEnum2(String value) {
        List<DemoEnum> list = Arrays.asList(DemoEnum.values());
        return list.stream().filter(m -> m.value.equals(value))
                .findAny().orElseThrow(RuntimeException::new);
    }

    public static DemoEnum getEnum3(String value) {
        for (DemoEnum re : DemoEnum.values()) {
            if (re.value.compareTo(value) == 0) {
                return re;
            }
        }
        throw new IllegalArgumentException("Invalid RandomEnum value:" + value);
    }


    /*public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name){

    }*/

}