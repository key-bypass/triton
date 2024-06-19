package com.kkkcut.e20j.utils;

import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class DesUtil {
    public static final String DATABASE = "88KK8SPL";
    public static final String SERVER = "12KK5SPL";
    public static Charset charset = Charset.forName("gb2312");

    public static String encrypt(String str, String str2) {
        return Des.parseByte2HexStr(Des.encrypt(str.getBytes(charset), str2));
    }

    public static String decrypt(String str, String str2) throws Exception {
        return new String(Des.decrypt(Des.parseHexStr2Byte(str), str2), charset);
    }
}
