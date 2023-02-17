package com.dd.mu.entity.ext;

import java.lang.reflect.Field;

public enum MyEnum {
    A("Start There"),
    B("Start Here");

    MyEnum(String name) {
        try {
            Field fieldName = getClass().getSuperclass().getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(this, name);
            fieldName.setAccessible(false);
        } catch (Exception e) {}
    }
}
