package com.liying.core.utils;

/* loaded from: classes2.dex */
public class ConversionUtils {
    public static byte[] intConvertHex(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    public static String byteToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().trim();
    }

    public static int bytesToInt(byte[] bArr) {
        int i;
        byte b;
        if (bArr.length == 1) {
            return bArr[0] & 255;
        }
        if (bArr.length == 2) {
            i = (bArr[0] & 255) * 256;
            b = bArr[1];
        } else if (bArr.length == 3) {
            i = ((bArr[0] & 255) * 256 * 256) + ((bArr[1] & 255) * 256);
            b = bArr[2];
        } else {
            if (bArr.length != 4) {
                return 0;
            }
            i = ((bArr[0] & 255) * 256 * 256 * 256) + ((bArr[1] & 255) * 256 * 256) + ((bArr[2] & 255) * 256);
            b = bArr[3];
        }
        return i + (b & 255);
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static byte CheckSum(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += b & 255;
        }
        return hexStringToBytes(Integer.toHexString(i))[r4.length - 1];
    }
}
