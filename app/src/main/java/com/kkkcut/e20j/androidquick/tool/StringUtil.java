package com.kkkcut.e20j.androidquick.tool;

import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
    }

    public static String getTrimedString(String str) {
        return trim(str);
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static boolean equals(String str, String str2) {
        return str == str2 || equalsNotNull(str, str2);
    }

    public static boolean equalsNotNull(String str, String str2) {
        return str != null && str.equals(str2);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getHostName(String str) {
        String str2;
        int indexOf = str.indexOf("://");
        if (indexOf != -1) {
            int i = indexOf + 3;
            str2 = str.substring(0, i);
            str = str.substring(i);
        } else {
            str2 = "";
        }
        int indexOf2 = str.indexOf("/");
        if (indexOf2 != -1) {
            str = str.substring(0, indexOf2 + 1);
        }
        return str2 + str;
    }

    public static String getDataSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        if (j < 1024) {
            return j + "bytes";
        }
        if (j < 1048576) {
            return decimalFormat.format(((float) j) / 1024.0f) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format((((float) j) / 1024.0f) / 1024.0f) + "MB";
        }
        if (j >= 0) {
            return "error";
        }
        return decimalFormat.format(((((float) j) / 1024.0f) / 1024.0f) / 1024.0f) + "GB";
    }
}
