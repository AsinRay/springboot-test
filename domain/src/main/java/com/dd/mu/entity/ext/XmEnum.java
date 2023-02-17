package com.dd.mu.entity.ext;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public enum XmEnum {

    StartHere("Start Here"),
    StopHere("Stop Here");

    private final String strVal;
    private XmEnum(String strVal) {
        this.strVal = strVal;
    }

    public static XmEnum getEnum(String strVal) {
        if(!strValMap.containsKey(strVal)) {
            throw new IllegalArgumentException("Unknown XmEnum Value:" + strVal);
        }
        return strValMap.get(strVal);
    }

    private static final Map<String, XmEnum> strValMap;
    static {
        final Map<String, XmEnum> tmpMap = Maps.newHashMap();
        for(final XmEnum en : XmEnum.values()) {
            tmpMap.put(en.strVal, en);
        }
        strValMap = ImmutableMap.copyOf(tmpMap);
    }

    @Override
    public String toString() {
        return strVal;
    }
}