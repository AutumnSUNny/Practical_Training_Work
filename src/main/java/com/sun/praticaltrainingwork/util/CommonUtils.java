package com.sun.praticaltrainingwork.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.Map;

public class CommonUtils {
    public static String camelToUnderline(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
