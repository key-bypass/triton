package com.kkkcut.e20j.utils;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class SpecificParamUtils {
    public static final String CUTTER_SIZE = "cutter";
    public static final String CUT_DEPTH = "cut_depth";
    public static final String DECODER_SIZE = "cutter";
    public static final String EXTRA_CUT = "extra_cut";
    public static final String GROOVE = "groove";
    public static final String GUIDE = "guide";
    public static final String INNER_CUT_ANGLE = "inner_cut_angle";
    public static final String LAST_BITTING = "last_bitting";
    public static final String LOCKED = "locked";
    public static final String NOSE = "nose";
    public static final String SIDE = "side";
    public static final String SINGLE_SIDE_ANGLE = "spacewidthangles";
    public static final String VARIABLE_SPACE = "variable_space";

    public static String getParam(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str.split(";");
        for (int i = 0; i < split.length; i++) {
            if (split[i].split(":")[0].equals(str2)) {
                return split[i].split(":")[1];
            }
        }
        return null;
    }

    public static String putParam(String str, String str2, String str3) {
        String str4 = "";
        if (str == null) {
            str = "";
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (!str.contains(str2)) {
            if (TextUtils.isEmpty(str3)) {
                return str;
            }
            return str + str2 + ":" + str3 + ";";
        }
        String[] split = str.split(";");
        for (int i = 0; i < split.length; i++) {
            if (!split[i].split(":")[0].equals(str2)) {
                str4 = str4 + split[i] + ";";
            } else if (!TextUtils.isEmpty(str3)) {
                str4 = str4 + str2 + ":" + str3 + ";";
            }
        }
        return str4;
    }
}
