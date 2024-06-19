package com.kkkcut.e20j.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public class Des {
    public static final String ALGORITHM_DES = "DES/CBC/PKCS7Padding";

    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            byte[] bytes = str.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bytes));
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(1, generateSecret, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) throws Exception {
        byte[] bytes = str.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
        SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bytes));
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(2, generateSecret, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static String parseByte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] parseHexStr2Byte(String str) {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }
}
