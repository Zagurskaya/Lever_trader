package com.gmail.zagurskaya.web.util;

public class DateUtil {

    public static String randomCode(int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            int oneNumber = (int) (Math.random() * 10);
            code += String.valueOf(oneNumber);
        }
        return code;
    }
}
