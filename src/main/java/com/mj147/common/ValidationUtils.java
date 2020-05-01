package com.mj147.common;

public class ValidationUtils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isZero(Integer value) {
        return value == 0;
    }

}
