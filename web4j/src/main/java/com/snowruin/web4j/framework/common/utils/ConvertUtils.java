package com.snowruin.web4j.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName JsonUtils
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/17 10:23
 * @Version 1.0
 **/
public final class ConvertUtils {

    /**
     * 转为 String 型
     */
    public static String convertString(Object obj) {
        return ConvertUtils.convertString(obj, "");
    }

    /**
     * 转为 String 型（提供默认值）
     */
    public static String convertString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为 double 型
     */
    public static double convertDouble(Object obj) {
        return ConvertUtils.convertDouble(obj, 0);
    }

    /**
     * 转为 double 型（提供默认值）
     */
    public static double convertDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = convertString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为 long 型
     */
    public static long convertLong(Object obj) {
        return ConvertUtils.convertLong(obj, 0);
    }

    /**
     * 转为 long 型（提供默认值）
     */
    public static long convertLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = convertString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为 int 型
     */
    public static int convertInt(Object obj) {
        return ConvertUtils.convertInt(obj, 0);
    }

    /**
     * 转为 int 型（提供默认值）
     */
    public static int convertInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = convertString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为 boolean 型
     */
    public static boolean convertBoolean(Object obj) {
        return ConvertUtils.convertBoolean(obj, false);
    }

    /**
     * 转为 boolean 型（提供默认值）
     */
    public static boolean convertBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(convertString(obj));
        }
        return booleanValue;
    }
}