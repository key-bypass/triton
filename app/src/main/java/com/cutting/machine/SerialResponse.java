package com.cutting.machine;

import com.cutting.machine.bean.SerialResBean;
import com.cutting.machine.utils.ConversionUtils;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class SerialResponse {
    public static SerialResBean ParsingPortData_Decoder(byte[] bArr) {
        return new SerialResBean(ConversionUtils.bytesToInt(new byte[]{bArr[4], bArr[5]}), ConversionUtils.bytesToInt(new byte[]{bArr[6], bArr[7]}), ConversionUtils.bytesToInt(new byte[]{bArr[8], bArr[9]}), ConversionUtils.bytesToInt(new byte[]{bArr[3]}));
    }

    public static SerialResBean ParsingPortData_Cut(byte[] bArr) {
        return new SerialResBean(ConversionUtils.bytesToInt(new byte[]{bArr[4], bArr[5]}), ConversionUtils.bytesToInt(new byte[]{bArr[6], bArr[7]}), ConversionUtils.bytesToInt(new byte[]{bArr[8], bArr[9]}), ConversionUtils.bytesToInt(new byte[]{bArr[3]}));
    }

    public static int parsingErrorCode(byte[] bArr) {
        return ConversionUtils.bytesToInt(Arrays.copyOfRange(bArr, 3, (bArr[2] & 255) + 3));
    }

    public static int getDecoderPosition(byte[] bArr) {
        return ConversionUtils.bytesToInt(Arrays.copyOfRange(bArr, 3, (bArr[2] & 255) + 3));
    }

    public static String ParsingPortData_QueryChipID(byte[] bArr) {
        int i = bArr[2];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 3, bArr2, 0, i);
        return ConversionUtils.byteToHex(bArr2);
    }

    public static String ParsingPortData_QueryFirmwareVersion(byte[] bArr) {
        int i = bArr[2];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 3, bArr2, 0, i);
        new String(bArr2);
        return String.valueOf(ConversionUtils.bytesToInt(bArr2));
    }
}
