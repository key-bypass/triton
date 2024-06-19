package com.kkkcut.e20j.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;

/* loaded from: classes.dex */
public class MD5Util {
    protected static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected static MessageDigest messageDigest;

    static {
        messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5String(File file) throws IOException {
        messageDigest.update(new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
        return bufferToHex(messageDigest.digest());
    }

    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    public static String getMD5String(byte[] bArr) {
        messageDigest.update(bArr);
        return bufferToHex(messageDigest.digest());
    }

    private static String bufferToHex(byte[] bArr) {
        return bufferToHex(bArr, 0, bArr.length);
    }

    private static String bufferToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            appendHexPair(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void appendHexPair(byte b, StringBuffer stringBuffer) {
        char[] cArr = hex;
        int i = b & IntersectionPtg.sid;
        char c = cArr[i >> 4];
        char c2 = cArr[i];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }
}
