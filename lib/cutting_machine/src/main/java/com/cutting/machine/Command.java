package com.liying.core;

import android.util.Log;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.MachineData;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.utils.ConversionUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class Command {
    public static final String Stop = "4b4800930a";
    private static final String TAG = "Command";
    public static String _KB = "KB";
    public static String _KC = "KC";
    public static String _KD = "KD";
    public static String _KE = "KE";
    public static String _KF = "KF";
    public static String _KG = "KG";
    public static String _KH = "KH";
    public static String _KM = "KM";
    public static String _KN = "KN";
    public static String _KR = "KR";
    public static String _KS = "KS";
    public static String _KT = "KT";
    public static String _KU = "KU";
    public static String _KV = "KV";
    public static String _KW = "KW";
    public static byte _b9 = 9;
    public static byte _bB = 66;
    public static byte _bC = 67;
    public static byte _bD = 68;
    public static byte _bE = 69;
    public static byte _bEnd = 10;
    public static byte _bF = 70;
    public static byte _bG = 71;
    public static byte _bH = 72;
    public static byte _bK = 75;
    public static byte _bL = 76;
    public static byte _bM = 77;
    public static byte _bN = 78;
    public static byte _bO = 79;
    public static byte _bR = 82;
    public static byte _bS = 83;
    public static byte _bT = 84;
    public static byte _bU = 85;
    public static byte _bV = 86;
    public static byte _bW = 87;

    public static byte[] BuzzerOperation(int i, int i2) {
        byte[] bArr = {_bK, _bB, 2, (byte) i, (byte) i2, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] DecoderOperation(int i, int i2, int i3, int i4, int i5, String str) {
        int i6;
        int i7;
        Log.d(TAG, "DecoderOperation() called with: nStepCount = [" + i + "], nType = [" + i2 + "], nX = [" + i3 + "], nY = [" + i4 + "], nZ = [" + i5 + "], vStr = [" + str + "]");
        String[] split = str.split(",");
        int i8 = 5000;
        if (split.length > 2) {
            i8 = Integer.parseInt(split[0]);
            i7 = Integer.parseInt(split[1]);
            i6 = Integer.parseInt(split[2]);
        } else {
            i6 = 3000;
            i7 = 5000;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 > MachineData.xAxisMax / MachineData.dXScale) {
            ErrorHelper.handleError(600);
            return null;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (CuttingMachine.getInstance().isE9()) {
            if (i4 > MachineData.yAxisMax / MachineData.dYScale) {
                i4 = (int) (MachineData.yAxisMax / MachineData.dYScale);
            }
        } else if (i4 > MachineData.yAxisMax / MachineData.dYScale) {
            ErrorHelper.handleError(ErrorCode.yOvertravel);
            return null;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        if (i5 > MachineData.zAxisMax / MachineData.dZScale) {
            i5 = (int) (MachineData.zAxisMax / MachineData.dZScale);
        }
        byte[] bArr = {_bK, _bT, ConversionUtils.intConvertHex(16)[1], ConversionUtils.intConvertHex(16)[0], (byte) i, (byte) i2, 0, 1, ConversionUtils.intConvertHex(i3)[1], ConversionUtils.intConvertHex(i3)[0], ConversionUtils.intConvertHex(i4)[1], ConversionUtils.intConvertHex(i4)[0], ConversionUtils.intConvertHex(i5)[1], ConversionUtils.intConvertHex(i5)[0], ConversionUtils.intConvertHex(i8)[1], ConversionUtils.intConvertHex(i8)[0], ConversionUtils.intConvertHex(i7)[1], ConversionUtils.intConvertHex(i7)[0], ConversionUtils.intConvertHex(i6)[1], ConversionUtils.intConvertHex(i6)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] moveU(int i, int i2, String str) {
        int i3;
        int i4;
        String[] split = str.split(",");
        int i5 = 5000;
        if (split.length > 2) {
            i5 = Integer.parseInt(split[0]);
            i4 = Integer.parseInt(split[1]);
            i3 = Integer.parseInt(split[2]);
        } else {
            i3 = 3000;
            i4 = 5000;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > MachineData.xAxisMax / MachineData.dXScale) {
            ErrorHelper.handleError(600);
            return null;
        }
        byte[] bArr = {_bK, _bT, ConversionUtils.intConvertHex(16)[1], ConversionUtils.intConvertHex(16)[0], 1, (byte) i, 0, 1, ConversionUtils.intConvertHex(i2)[1], ConversionUtils.intConvertHex(i2)[0], ConversionUtils.intConvertHex(65535)[1], ConversionUtils.intConvertHex(65535)[0], ConversionUtils.intConvertHex(65535)[1], ConversionUtils.intConvertHex(65535)[0], ConversionUtils.intConvertHex(i5)[1], ConversionUtils.intConvertHex(i5)[0], ConversionUtils.intConvertHex(i4)[1], ConversionUtils.intConvertHex(i4)[0], ConversionUtils.intConvertHex(i3)[1], ConversionUtils.intConvertHex(i3)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] CutOperation(int i, int i2, int i3, int i4, String str, int i5) {
        int i6;
        int i7;
        Log.d(TAG, "CutOperation() called with: nStepCount = [" + i + "], nX = [" + i2 + "], nY = [" + i3 + "], nZ = [" + i4 + "], vStr = [" + str + "], progress = [" + i5 + "]");
        String[] split = str.split(",");
        int i8 = 300;
        if (split.length > 2) {
            i8 = Integer.parseInt(split[0]);
            i7 = Integer.parseInt(split[1]);
            i6 = Integer.parseInt(split[2]);
        } else {
            i6 = 3000;
            i7 = 300;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > MachineData.xAxisMax / MachineData.dXScale) {
            ErrorHelper.handleError(600);
            return null;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (CuttingMachine.getInstance().isE9()) {
            if (i3 > MachineData.yAxisMax / MachineData.dYScale) {
                i3 = (int) (MachineData.yAxisMax / MachineData.dYScale);
            }
        } else if (i3 > MachineData.yAxisMax / MachineData.dYScale) {
            ErrorHelper.handleError(ErrorCode.yOvertravel);
            return null;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (i4 > MachineData.zAxisMax / MachineData.dZScale) {
            i4 = (int) (MachineData.zAxisMax / MachineData.dZScale);
        }
        byte[] bArr = {_bK, _bC, ConversionUtils.intConvertHex(14)[1], ConversionUtils.intConvertHex(14)[0], (byte) i, (byte) i5, ConversionUtils.intConvertHex(i2)[1], ConversionUtils.intConvertHex(i2)[0], ConversionUtils.intConvertHex(i3)[1], ConversionUtils.intConvertHex(i3)[0], ConversionUtils.intConvertHex(i4)[1], ConversionUtils.intConvertHex(i4)[0], ConversionUtils.intConvertHex(i8)[1], ConversionUtils.intConvertHex(i8)[0], ConversionUtils.intConvertHex(i7)[1], ConversionUtils.intConvertHex(i7)[0], ConversionUtils.intConvertHex(i6)[1], ConversionUtils.intConvertHex(i6)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] CutOperation(List<StepBean> list, int i) {
        int i2;
        int i3;
        int size = (list.size() * 12) + 8;
        byte[] bArr = new byte[size];
        bArr[0] = _bK;
        bArr[1] = _bC;
        int size2 = (list.size() * 12) + 2;
        bArr[2] = ConversionUtils.intConvertHex(size2)[1];
        bArr[3] = ConversionUtils.intConvertHex(size2)[0];
        bArr[4] = (byte) list.size();
        bArr[5] = (byte) i;
        for (int i4 = 0; i4 < list.size(); i4++) {
            StepBean stepBean = list.get(i4);
            int x = stepBean.getX();
            int y = stepBean.getY();
            int z = stepBean.getZ();
            if (x < 0) {
                x = 0;
            }
            if (x > MachineData.xAxisMax / MachineData.dXScale) {
                ErrorHelper.handleError(600);
                return null;
            }
            if (y < 0) {
                y = 0;
            }
            if (CuttingMachine.getInstance().isE9()) {
                if (y > MachineData.yAxisMax / MachineData.dYScale) {
                    y = (int) (MachineData.yAxisMax / MachineData.dYScale);
                }
            } else if (y > MachineData.yAxisMax / MachineData.dYScale) {
                ErrorHelper.handleError(ErrorCode.yOvertravel);
                return null;
            }
            if (z < 0) {
                z = 0;
            }
            if (z > MachineData.zAxisMax / MachineData.dZScale) {
                z = (int) (MachineData.zAxisMax / MachineData.dZScale);
            }
            String[] split = stepBean.getvStr().split(",");
            int length = split.length;
            int i5 = ErrorCode.keyDecodeFailed;
            if (length > 2) {
                i5 = Integer.parseInt(split[0]);
                i3 = Integer.parseInt(split[1]);
                i2 = Integer.parseInt(split[2]);
            } else {
                i2 = ErrorCode.keyDecodeFailed;
                i3 = ErrorCode.keyDecodeFailed;
            }
            Log.d(TAG, "CutOperation:" + stepBean.getS() + ">>nX = [" + x + "], nY = [" + y + "], nZ = [" + z + "], vStr = [" + stepBean.getvStr() + "]");
            int i6 = i4 * 12;
            bArr[i6 + 6] = ConversionUtils.intConvertHex(x)[1];
            bArr[i6 + 7] = ConversionUtils.intConvertHex(x)[0];
            bArr[i6 + 8] = ConversionUtils.intConvertHex(y)[1];
            bArr[i6 + 9] = ConversionUtils.intConvertHex(y)[0];
            bArr[i6 + 10] = ConversionUtils.intConvertHex(z)[1];
            bArr[i6 + 11] = ConversionUtils.intConvertHex(z)[0];
            bArr[i6 + 12] = ConversionUtils.intConvertHex(i5)[1];
            bArr[i6 + 13] = ConversionUtils.intConvertHex(i5)[0];
            bArr[i6 + 14] = ConversionUtils.intConvertHex(i3)[1];
            bArr[i6 + 15] = ConversionUtils.intConvertHex(i3)[0];
            bArr[i6 + 16] = ConversionUtils.intConvertHex(i2)[1];
            bArr[i6 + 17] = ConversionUtils.intConvertHex(i2)[0];
        }
        bArr[size - 2] = ConversionUtils.CheckSum(bArr);
        bArr[size - 1] = 10;
        return bArr;
    }

    public static byte[] QueryChipID() {
        byte[] bArr = {_bK, _bD, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] IssueRrrorCode(int i) {
        byte[] bArr = {_bK, _bE, 2, ConversionUtils.intConvertHex(i)[1], ConversionUtils.intConvertHex(i)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] queryDecoderPosition() {
        byte[] bArr = {_bK, _bG, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] TurnOffSpindle() {
        byte[] bArr = {_bK, _bH, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] takePhoto() {
        byte[] bArr = {_bK, _bL, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] SpindleOperation(int i) {
        byte[] bArr = {_bK, _bM, 2, ConversionUtils.intConvertHex(i)[1], ConversionUtils.intConvertHex(i)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] QuerySpindleSpeed() {
        byte[] bArr = {_bK, _bN, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] controlLight(int i) {
        byte[] bArr = new byte[5];
        bArr[0] = _bK;
        bArr[1] = _bO;
        bArr[2] = 1;
        bArr[3] = (byte) i;
        bArr[4] = ConversionUtils.CheckSum(bArr);
        bArr[5] = 10;
        return bArr;
    }

    public static byte[] ReadSpecifyLocationData(int i) {
        byte[] bArr = {_bK, _bR, 2, (byte) i, Ptg.CLASS_ARRAY, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] OpenDoorCuttingSettings(int i) {
        byte[] bArr = {_bK, _bS, 1, (byte) i, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] readyUpgradeFirmware() {
        byte[] bArr = {_bK, _bU, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] upgradeFirmware(List<byte[]> list, int i) {
        if (list == null) {
            return null;
        }
        byte[] bArr = list.get(i);
        byte[] bArr2 = new byte[bArr.length + 7];
        bArr2[0] = _bK;
        bArr2[1] = _bU;
        bArr2[2] = ConversionUtils.intConvertHex(bArr.length + 1)[1];
        bArr2[3] = ConversionUtils.intConvertHex(bArr.length + 1)[0];
        bArr2[4] = (byte) i;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2 + 5] = bArr[i2];
        }
        bArr2[bArr.length + 5] = ConversionUtils.CheckSum(bArr2);
        bArr2[bArr.length + 6] = 10;
        return bArr2;
    }

    public static byte[] QueryFirmwareVersion() {
        byte[] bArr = {_bK, _bV, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] WriteSpecifyLocationData(int i, String str) {
        byte[] contentBytes = getContentBytes(i, str);
        byte[] bArr = new byte[contentBytes.length + 7];
        bArr[0] = _bK;
        bArr[1] = _bW;
        bArr[2] = (byte) (contentBytes.length + 2);
        bArr[3] = (byte) i;
        bArr[4] = (byte) contentBytes.length;
        for (int i2 = 0; i2 < contentBytes.length; i2++) {
            bArr[i2 + 5] = contentBytes[i2];
        }
        int length = contentBytes.length + 5;
        bArr[length] = ConversionUtils.CheckSum(bArr);
        bArr[length + 1] = 10;
        return bArr;
    }

    public static byte[] getContentBytes(int i, String str) {
        byte[] bytes = str.getBytes();
        byte length = (byte) bytes.length;
        int i2 = length + 5;
        byte[] bArr = new byte[i2];
        bArr[0] = RefErrorPtg.sid;
        bArr[1] = (byte) i;
        bArr[2] = length;
        for (int i3 = 0; i3 < bytes.length; i3++) {
            bArr[i3 + 3] = bytes[i3];
        }
        bArr[i2 - 2] = ConversionUtils.CheckSum(bArr);
        bArr[i2 - 1] = NumberPtg.sid;
        return bArr;
    }

    public static byte[] duplicateDecode(int i, int i2, int i3, int i4, int i5) {
        Log.i(TAG, "duplicateDistance: " + i4);
        byte[] bArr = {_bK, _bF, 12, (byte) i5, (byte) i, ConversionUtils.intConvertHex(i4)[1], ConversionUtils.intConvertHex(i4)[0], (byte) i3, (byte) i2, ConversionUtils.intConvertHex(1000)[1], ConversionUtils.intConvertHex(1000)[0], ConversionUtils.intConvertHex(1000)[1], ConversionUtils.intConvertHex(1000)[0], ConversionUtils.intConvertHex(1000)[1], ConversionUtils.intConvertHex(1000)[0], ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }

    public static byte[] OnductiveTestOperation() {
        byte[] bArr = {_bK, _b9, 0, ConversionUtils.CheckSum(bArr), 10};
        return bArr;
    }
}
