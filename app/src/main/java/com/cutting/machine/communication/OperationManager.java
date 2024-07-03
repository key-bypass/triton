package com.cutting.machine.communication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.cutting.machine.Command;
import com.cutting.machine.CoreConstant;
import com.cutting.machine.CuttingMachine;
import com.cutting.machine.DataFixUtil;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.SerialResponse;
import com.cutting.machine.SpindleState;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.EventBean;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.bean.PointXyz;
import com.cutting.machine.bean.SerialResBean;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorCodeBean;
import com.cutting.machine.error.ErrorHandle;
import com.cutting.machine.error.ErrorHelper;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.utils.AssetsJsonUtils;
import com.cutting.machine.utils.ConversionUtils;
import com.cutting.machine.utils.UnitConvertUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

/* loaded from: classes2.dex */
public class OperationManager extends Observable {
    private static final String TAG = "OperationManager";
    private static OperationManager sInstance;
    private final Handler handler = new Handler();
    /* renamed from: go */
    boolean f414go;
    boolean go2;
    boolean go3;
    boolean go5;
    int index;
    boolean multiPoint;
    int stepDiff;
    int tempIndex;
    /* renamed from: DC */
    StringBuilder f413DC = new StringBuilder();
    List<PointXyz> zList = new ArrayList();
    List<Integer> retry = new ArrayList();
    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
    private List<SerialResBean> calibrationPoints;
    private String[] ckpParam;
    private Communication communication;
    private Context context;
    private boolean duplicateHeightDecode;
    private int duplicateKeyIndex;
    private String duplicateRow;
    private ErrorHandle errorHandle;
    private List<PointXyz> groovePosition;
    private PointXyz highest;
    private boolean isDuplicateDecode;
    private KeyInfo keyinfo;
    private SerialResBean lastPosition;
    private List<StepBean> stepBeanList;
    private byte[] tempOrder;
    private OperationParamBase userSetting;
    private OperateType operateType = OperateType.BOOT;
    private KeyAlignInfo keyAlignInfo = new KeyAlignInfo();
    private SpindleState spindleState = SpindleState.OFF;
    private String firmwareVersion = "";
    private final StringBuilder logs = new StringBuilder();
    private final Runnable writeLogRunnable = new Runnable() { // from class: com.cutting.machine.communication.OperationManager.7

        @Override // java.lang.Runnable
        public void run() {
            new Thread() { // from class: com.cutting.machine.communication.OperationManager.7.1

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    File file;
                    FileWriter fileWriter = null;
                    try {
                        try {
                            File[] listFiles = OperationManager.this.context.getExternalFilesDir("").listFiles();
                            if (listFiles != null) {
                                int length = listFiles.length;
                                for (int i = 0; i < length; i++) {
                                    file = listFiles[i];
                                    if (file.getName().startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile()) {
                                        break;
                                    }
                                }
                            }
                            file = null;
                            if (file == null) {
                                file = new File(OperationManager.this.context.getExternalFilesDir(""), "SerialLog_" + new Date().getTime() + ".txt");
                            }
                            FileWriter fileWriter2 = new FileWriter(file, true);
                            try {
                                fileWriter2.write(new String(OperationManager.this.logs));
                                OperationManager.this.logs.setLength(0);
                                fileWriter2.close();
                            } catch (Exception e) {
                                fileWriter = fileWriter2;
                                e = e;
                                System.out.println(e.getMessage());
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Throwable th) {
                                fileWriter = fileWriter2;
                                th = th;
                                if (fileWriter != null) {
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            var e = e3;
                        }
                    } catch (Throwable th2) {
                        var th = th2;
                    }
                }
            }.start();
        }

        /* renamed from: com.cutting.machine.communication.OperationManager$7$1 */
        /* loaded from: classes2.dex */
        class AnonymousClass1 extends Thread {
            AnonymousClass1() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                File file;
                FileWriter fileWriter = null;

                            File[] listFiles = OperationManager.this.context.getExternalFilesDir("").listFiles();
                            if (listFiles != null) {
                                int length = listFiles.length;
                                for (int i = 0; i < length; i++) {
                                    file = listFiles[i];
                                    if (file.getName().startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile()) {
                                        break;
                                    }
                                }
                            }
                            file = null;
                            if (file == null) {
                                file = new File(OperationManager.this.context.getExternalFilesDir(""), "SerialLog_" + new Date().getTime() + ".txt");
                            }
                            FileWriter fileWriter2 = new FileWriter(file, true);
                            try {
                                fileWriter2.write(new String(OperationManager.this.logs));
                                OperationManager.this.logs.setLength(0);
                                fileWriter2.close();
                            } catch (Exception e) {
                                fileWriter = fileWriter2;
                                e = e;
                                System.out.println(e.getMessage());
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Throwable th) {
                                fileWriter = fileWriter2;
                                th = th;
                                if (fileWriter != null) {
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }

            }
        }
    };
    private final WriteCallback writeCallback = new WriteCallback() { // from class: com.cutting.machine.communication.OperationManager.1
        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteSuccess(int i, int i2, byte[] bArr) {
        }


        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteFailure(Exception exc) {
            OperationManager.this.writeLog(0, "发送失败");
        }
    };
    private final Runnable checkMsg = new Runnable() { // from class: com.cutting.machine.communication.OperationManager.3


        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr = new byte[OperationManager.this.byteBuffer.flip().remaining()];
            OperationManager.this.byteBuffer.get(bArr);
            OperationManager.this.byteBuffer.clear();
            OperationManager.this.writeLog(1, "返回指令错误: " + ConversionUtils.byteToHex(bArr));
            ErrorHelper.handleError(ErrorCode.ReceiveDataError);
        }
    };

    private OperationManager() {
    }

    public static OperationManager getInstance() {
        if (sInstance == null) {
            sInstance = new OperationManager();
        }
        return sInstance;
    }

    public List<SerialResBean> getCalibrationPoints() {
        return this.calibrationPoints;
    }

    public List<PointXyz> getGroovePosition() {
        return this.groovePosition;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public boolean isMultiGrooveKey() {
        List<PointXyz> list = this.groovePosition;
        return list != null && list.size() > 1;
    }

    public void init(Context context, Communication communication) {
        this.context = context;
        this.communication = communication;
        this.errorHandle = (ErrorHandle) communication;
    }

    public void start(String str, OperateType operateType) {
        Log.i(TAG, "start: " + str);
        start(str, null, operateType);
    }

    public void start(String str, KeyInfo keyInfo, OperateType operateType) {
        ClampManager.getInstance().setCurrentClamp(str);
        if (keyInfo != null) {
            this.keyinfo = keyInfo;
        }
        Thread thread = new Thread() {
            public void run() {
                this.startExecution(AssetsJsonUtils.json2Steps(AssetsJsonUtils.getJsonStringFromAssets(OperationManager.this.context, str)), operateType);
            }
        };
        thread.start();
    }

    public void start(OperationParamBase operationParamBase, List<StepBean> list, OperateType operateType) {
        resetStatus();
        if (operationParamBase != null) {
            KeyInfoBase keyInfo = operationParamBase.getKeyInfo();
            if (keyInfo != null) {
                this.keyinfo = keyInfo;
            }
            this.userSetting = operationParamBase;
            Clamp clamp = operationParamBase.getClamp();
            if (clamp != null) {
                ClampManager.getInstance().setCurrentClamp(clamp);
            }
        }
        this.stepBeanList = list;
        this.operateType = operateType;
        if (list == null || list.size() == 0) {
            return;
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION || operateType == OperateType.KEY_BLANK_CUT_LOCATION || operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH || operateType == OperateType.DUPLICATE_DECODE_LOCATION || operateType == OperateType.DUPLICATE_CUT_LOCATION || operateType == OperateType.DUPLICATE_CUT_CUTTER_HIGH || operateType == OperateType.MODIFY_KEY_BLANK_LOCATION || operateType == OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH || operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            StepBean stepBean = list.get(list.size() - 1);
            if (stepBean.getT() == 999) {
                list.remove(stepBean);
            }
        }
        calculateXYZ(null);
    }

    private void resetStatus() {
        this.retry.clear();
        this.index = 0;
        this.f413DC.setLength(0);
        this.duplicateHeightDecode = false;
        this.isDuplicateDecode = false;
        this.go5 = false;
        this.go3 = false;
        this.go2 = false;
        this.f414go = false;
    }

    public void startExecution(List<StepBean> list, OperateType operateType) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION || operateType == OperateType.KEY_BLANK_CUT_LOCATION || operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH || operateType == OperateType.DUPLICATE_DECODE_LOCATION || operateType == OperateType.DUPLICATE_CUT_LOCATION || operateType == OperateType.DUPLICATE_CUT_CUTTER_HIGH || operateType == OperateType.MODIFY_KEY_BLANK_LOCATION || operateType == OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH || operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            StepBean stepBean = list.get(list.size() - 1);
            if (stepBean.getT() == 999) {
                list.remove(stepBean);
            }
        }
        if (operateType == OperateType.ENGRAVE_CUT_EXECUTE || operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            this.spindleState = SpindleState.OFF;
            this.keyinfo = null;
        }
        this.operateType = operateType;
        this.stepBeanList = list;
        resetStatus();
        calculateXYZ(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0465, code lost:

        if (r11[1].equals("Z") != false) goto L1059;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:563:0x14ae. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:130:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0a84  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0f55  */
    /* JADX WARN: Removed duplicated region for block: B:860:0x1eb4  */
    /* JADX WARN: Removed duplicated region for block: B:892:0x1fb2  */
    /* JADX WARN: Removed duplicated region for block: B:901:0x1fea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void calculateXYZ(SerialResBean r36) {
        /*
            Method dump skipped, instructions count: 8306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.communication.OperationManager.calculateXYZ(com.cutting.machine.bean.SerialResBean):void");
    }

    private boolean booleanGo5(SerialResBean serialResBean) {
        String[] strArr;
        return this.go5 && serialResBean != null && (strArr = this.ckpParam) != null && TextUtils.equals("SLU", strArr[0]) && serialResBean.getZ() - this.keyAlignInfo.getKeyFace() > UnitConvertUtil.cmm2StepZ(10);
    }

    private int getExtDoublekeyDepthSteps() {
        if (getKeyInfo() == null) {
            return 0;
        }
        return getKeyInfo().extDoublekeyDepthSteps;
    }

    private boolean isNewHonda() {
        if (getKeyInfo() != null) {
            return getKeyInfo().isNewHonda;
        }
        return false;
    }

    private KeyInfoBase getKeyInfo() {
        return this.keyinfo;
    }

    private boolean isPlasticKey() {
        OperationParamBase operationParamBase = this.userSetting;
        if (operationParamBase == null) {
            return false;
        }
        return operationParamBase.isPlasticKey();
    }

    private boolean isHorizontalClamping() {
        OperationParamBase operationParamBase = this.userSetting;
        return operationParamBase == null || operationParamBase.getClampMode() == 0;
    }

    private boolean isCutterDecoder(OperateType operateType, OperateType operateType2) {
        OperateType operateType3 = this.operateType;
        return operateType3 == operateType || operateType3 == operateType2 || operateType3 == OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH;
    }

    private boolean booleanGo12(SerialResBean serialResBean) {
        if (!this.f414go) {
            return this.go2;
        }
        boolean z = serialResBean.getR() == 1 && this.stepDiff < 0;
        boolean z2 = serialResBean.getR() == 0 && this.stepDiff > 0;
        boolean z3 = z || z2;
        int x = CuttingMachine.getInstance().isE9() ? serialResBean.getX() : serialResBean.getY();
        if (this.stepDiff < 0) {
            if (z) {
                this.retry.add(Integer.valueOf(x));
                if (this.retry.size() > 2) {
                    List<Integer> list = this.retry;
                    int intValue = list.get(list.size() - 3).intValue();
                    List<Integer> list2 = this.retry;
                    if (Math.abs(intValue - list2.get(list2.size() - 1).intValue()) < UnitConvertUtil.xKeyCmm2Steps(3)) {
                        this.retry.clear();
                        this.stepBeanList = null;
                        ErrorHelper.handleError(ErrorCode.keyDecodeError);
                        return false;
                    }
                }
            } else {
                this.retry.clear();
            }
        } else if (z2) {
            this.retry.clear();
        } else {
            this.retry.add(Integer.valueOf(x));
            if (this.retry.size() > 2) {
                List<Integer> list3 = this.retry;
                int intValue2 = list3.get(list3.size() - 3).intValue();
                List<Integer> list4 = this.retry;
                if (Math.abs(intValue2 - list4.get(list4.size() - 1).intValue()) < UnitConvertUtil.xKeyCmm2Steps(3)) {
                    this.retry.clear();
                    this.stepBeanList = null;
                    ErrorHelper.handleError(ErrorCode.keyDecodeError);
                    return false;
                }
            }
        }
        return z3;
    }

    private boolean booleanGo3(SerialResBean serialResBean) {
        if (!this.go3) {
            return false;
        }
        if (serialResBean.getR() == 0) {
            this.retry.clear();
            return true;
        }
        this.retry.add(Integer.valueOf(CuttingMachine.getInstance().isE9() ? serialResBean.getX() : serialResBean.getY()));
        if (this.retry.size() <= 2) {
            return false;
        }
        List<Integer> list = this.retry;
        int intValue = list.get(list.size() - 3).intValue();
        List<Integer> list2 = this.retry;
        if (Math.abs(intValue - list2.get(list2.size() - 1).intValue()) >= UnitConvertUtil.xKeyCmm2Steps(3)) {
            return false;
        }
        this.retry.clear();
        return true;
    }

    public void sendMultiPoint(List<StepBean> list, int i) {
        this.multiPoint = true;
        sendOrder(Command.CutOperation(list, i));
        sendEventMessage(47, Integer.valueOf(i));
    }

    private void handleByteBuffer(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int remaining = byteBuffer.flip().remaining();
        byte[] bArr = new byte[remaining];
        byteBuffer.get(bArr);
        if (remaining < 5) {
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        ConversionUtils.byteToHex(bArr);
        int i3 = -1;
        for (int i4 = 0; i4 < remaining - 4; i4++) {
            if (Command._bK == bArr[i4] && (i2 = (i = (bArr[i4 + 2] & 255) + i4) + 4) < remaining && bArr[i2] == Command._bEnd && (ConversionUtils.CheckSum(Arrays.copyOfRange(bArr, i4, i + 3)) == bArr[i2 - 1] || bArr[i4 + 1] == Command._bU)) {
                handleCompleteResponse(Arrays.copyOfRange(bArr, i4, i + 5));
                i3 = i2 + 1;
                byteBuffer.position(i3);
            }
        }
        if (i3 == -1) {
            byteBuffer.limit(byteBuffer.capacity());
        } else if (i3 == byteBuffer.limit()) {
            byteBuffer.clear();
        } else {
            byteBuffer.compact();
        }
    }

    private void handleCompleteResponse(byte[] bArr) {
        writeLog(1, ConversionUtils.byteToHex(bArr));
        if (bArr[1] == Command._bT) {
            SerialResBean ParsingPortData_Decoder = SerialResponse.ParsingPortData_Decoder(bArr);
            this.lastPosition = ParsingPortData_Decoder;
            if (this.isDuplicateDecode) {
                if (this.duplicateHeightDecode) {
                    List<PointXyz> list = this.zList;
                    list.add(new PointXyz(list.size(), ParsingPortData_Decoder));
                    return;
                }
                String str = this.duplicateRow;
                Log.i(TAG, "decodeRes: " + ParsingPortData_Decoder);
                handleDecodeData(ParsingPortData_Decoder, str, "0");
                return;
            }
            handleMove(ParsingPortData_Decoder);
            return;
        }
        if (bArr[1] == Command._bC) {
            handleMove(SerialResponse.ParsingPortData_Cut(bArr));
            return;
        }
        if (bArr[1] == Command._bM) {
            if (this.spindleState == SpindleState.ON) {
                this.handler.postDelayed(new Runnable() { // from class: com.cutting.machine.communication.OperationManager.4

                    @Override // java.lang.Runnable
                    public void run() {
                        if (OperationManager.this.tempOrder != null) {
                            OperationManager operationManager = OperationManager.this;
                            operationManager.sendOrder(operationManager.tempOrder);
                            OperationManager.this.tempOrder = null;
                        }
                    }
                }, 2000L);
                return;
            }
            byte[] bArr2 = this.tempOrder;
            if (bArr2 != null) {
                sendOrder(bArr2);
                this.tempOrder = null;
                return;
            }
            return;
        }
        if (bArr[1] == Command._bD) {
            return;
        }
        if (bArr[1] == Command._bU) {
            sendEventMessage(16);
            return;
        }
        if (bArr[1] == Command._bB) {
            sendOrder(Command.QueryFirmwareVersion());
            return;
        }
        if (bArr[1] == Command._bV) {
            sendEventMessage(20);
            this.firmwareVersion = SerialResponse.ParsingPortData_QueryFirmwareVersion(bArr);
            Log.i(TAG, "firmwareVersion: " + this.firmwareVersion);
            sendEventMessage(35, this.firmwareVersion);
            return;
        }
        if (bArr[1] == Command._bS) {
            return;
        }
        if (bArr[1] == Command._bG) {
            int decoderPosition = SerialResponse.getDecoderPosition(bArr);
            notifyObs(this.operateType, Integer.valueOf(decoderPosition));
            sendEventMessage(51, Integer.valueOf(decoderPosition));
            return;
        }
        if (bArr[1] == Command._bF) {
            this.isDuplicateDecode = false;
            this.duplicateHeightDecode = false;
            handleMove(SerialResponse.ParsingPortData_Decoder(bArr));
            return;
        }
        if (bArr[1] == Command._bH) {
            reset();
            if (this.operateType == OperateType.STOP) {
                ErrorHelper.handleError(ErrorCode.UserStop);
                return;
            } else {
                if (this.operateType == OperateType.CLAMP_RESET) {
                    return;
                }
                ErrorHelper.handleError(-5);
                return;
            }
        }
        if (bArr[1] == Command._bE) {
            reset();
            ErrorHelper.handleError(SerialResponse.parsingErrorCode(bArr));
            return;
        }
        if (bArr[1] == Command._bW) {
            sendEventMessage(32, OperateType.SAVE_CALIBRATION_DATA);
            notifyObs(OperateType.SAVE_CALIBRATION_DATA);
            return;
        }
        if (bArr[1] == Command._bR) {
            if (bArr[3] != 42) {
                Log.i(TAG, "起始位不正确");
                ClampManager.getInstance().initClamp("", false);
                return;
            }
            String str2 = new String(Arrays.copyOfRange(bArr, 6, bArr[5] + 6));
            Log.i(TAG, "读取成功：" + str2);
            ClampManager.getInstance().initClamp(str2, false);
        }
    }

    private void notifyObs(OperateType operateType) {
        notifyObs(operateType, null);
    }

    private void notifyObs(OperateType operateType, Object obj) {
        setChanged();
        notifyObservers(new EventBean(operateType, obj));
    }

    private void handleMove(SerialResBean serialResBean) {
        Log.i(TAG, "接收: " + serialResBean);
        if (this.f413DC.length() != 0) {
            if (serialResBean.getR() == 1) {
                String[] split = this.f413DC.toString().split(",");
                handleDecodeData(serialResBean, split[0], split[1]);
                this.f413DC.setLength(0);
                try {
                    long pauseTime = getPauseTime() * 1000L;
                    if (pauseTime < 0) {
                        pauseTime = 0;
                    }
                    Thread.sleep(pauseTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (this.stepBeanList.get(this.index - 1).getT() == 1) {
                this.f413DC.setLength(0);
            }
        }
        List<StepBean> list = this.stepBeanList;
        if (list != null && list.size() > this.index) {
            calculateXYZ(serialResBean);
            return;
        }
        if (this.zList.size() > 0) {
            int[] duplicateCutDepth = getDuplicateCutDepth();
            this.zList.clear();
            Log.i(TAG, "cutDepth: " + (duplicateCutDepth[0] * MachineData.dZScale));
            this.keyAlignInfo.setCutDepth(Math.round((float) duplicateCutDepth[0]));
            this.keyAlignInfo.setKeyFace(duplicateCutDepth[1]);
        }
        Log.i(TAG, "keyaligninfo: " + this.keyAlignInfo);
        sendEventMessage(32, this.operateType);
        notifyObs(this.operateType);
    }

    private int getPauseTime() {
        OperationParamBase operationParamBase = this.userSetting;
        if (operationParamBase == null) {
            return 0;
        }
        return operationParamBase.getPauseTime();
    }

    private int[] getDuplicateCutDepth() {
        List<PointXyz> list = this.zList;
        list.remove(list.size() - 1);
        if (getKeyInfo().keyType == KeyType.SINGLE_INSIDE_GROOVE_KEY || getKeyInfo().keyType == KeyType.DOUBLE_INSIDE_GROOVE_KEY || getKeyInfo().keyType == KeyType.TWO_GROOVE || getKeyInfo().keyType == KeyType.THREE_GROOVE || getKeyInfo().keyType == KeyType.LEFT_GROOVE || getKeyInfo().keyType == KeyType.RIGHT_GROOVE) {
            this.groovePosition = new ArrayList();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < this.zList.size(); i2++) {
                if (Math.abs(this.zList.get(i2).getZ() - this.zList.get(i).getZ()) > 30.0f / MachineData.dZScale) {
                    arrayList.add(this.zList.subList(i, i2));
                    i = i2;
                }
                if (i2 == this.zList.size() - 1) {
                    List<PointXyz> list2 = this.zList;
                    arrayList.add(list2.subList(i, list2.size()));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                List list3 = (List) arrayList.get(i3);
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                for (int i7 = 0; i7 < list3.size(); i7++) {
                    PointXyz pointXyz = (PointXyz) list3.get(i7);
                    i4 += pointXyz.getX();
                    i5 += pointXyz.getY();
                    i6 += pointXyz.getZ();
                }
                PointXyz pointXyz2 = new PointXyz();
                pointXyz2.setX(i4 / list3.size());
                pointXyz2.setY(i5 / list3.size());
                pointXyz2.setZ(i6 / list3.size());
                arrayList2.add(pointXyz2);
            }
            this.zList = arrayList2;
            if (arrayList2.size() >= 3) {
                int i8 = 0;
                while (i8 < this.zList.size() - 2) {
                    PointXyz pointXyz3 = this.zList.get(i8);
                    int i9 = i8 + 1;
                    PointXyz pointXyz4 = this.zList.get(i9);
                    PointXyz pointXyz5 = this.zList.get(i8 + 2);
                    if (pointXyz4.getZ() > pointXyz3.getZ() && pointXyz4.getZ() > pointXyz5.getZ()) {
                        this.groovePosition.add(0, pointXyz4);
                    }
                    i8 = i9;
                }
                if (this.zList.get(0).getZ() > this.zList.get(1).getZ()) {
                    this.zList.remove(0);
                }
                List<PointXyz> list4 = this.zList;
                int z = list4.get(list4.size() - 1).getZ();
                List<PointXyz> list5 = this.zList;
                if (z > list5.get(list5.size() - 2).getZ()) {
                    List<PointXyz> list6 = this.zList;
                    list6.remove(list6.size() - 1);
                }
            }
            Iterator<PointXyz> it = this.zList.iterator();
            while (it.hasNext()) {
                Log.i(TAG, "pointXyz0: " + it.next());
            }
        }
        Collections.sort(this.zList, new Comparator<PointXyz>() { // from class: com.cutting.machine.communication.OperationManager.5


            @Override // java.util.Comparator
            public int compare(PointXyz pointXyz6, PointXyz pointXyz7) {
                return pointXyz6.getZ() - pointXyz7.getZ();
            }
        });
        this.highest = this.zList.get(0);
        int z2 = this.zList.get(0).getZ();
        ArrayList arrayList3 = new ArrayList();
        int i10 = 0;
        for (int i11 = 0; i11 < this.zList.size(); i11++) {
            if (Math.abs(this.zList.get(i11).getZ() - this.zList.get(i10).getZ()) > 30.0f / MachineData.dZScale) {
                arrayList3.add(this.zList.subList(i10, i11));
                i10 = i11;
            }
            if (i11 == this.zList.size() - 1) {
                List<PointXyz> list7 = this.zList;
                arrayList3.add(list7.subList(i10, list7.size()));
            }
        }
        if (arrayList3.size() < 2) {
            return new int[]{Math.round(110.0f / MachineData.dZScale), z2};
        }
        Collections.sort(arrayList3, new Comparator<List<PointXyz>>() { // from class: com.cutting.machine.communication.OperationManager.6


            @Override // java.util.Comparator
            public int compare(List<PointXyz> list8, List<PointXyz> list9) {
                return list9.size() - list8.size();
            }
        });
        ArrayList arrayList4 = new ArrayList();
        for (int i12 = 0; i12 < arrayList3.size(); i12++) {
            List list8 = (List) arrayList3.get(i12);
            Iterator it2 = list8.iterator();
            int i13 = 0;
            while (it2.hasNext()) {
                i13 += ((PointXyz) it2.next()).getZ();
            }
            arrayList4.add(Integer.valueOf(i13 / list8.size()));
        }
        int i14 = 0;
        while (i14 < arrayList4.size() - 1) {
            i14++;
            int abs = Math.abs(((Integer) arrayList4.get(0)).intValue() - ((Integer) arrayList4.get(i14)).intValue());
            if (abs > 50.0f / MachineData.dZScale) {
                return new int[]{abs, z2};
            }
        }
        return new int[]{Math.round(50.0f / MachineData.dZScale), z2};
    }

    public void onReceiveData(byte[] bArr) {
        synchronized (this) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bArr.length == 0) {
                return;
            }
            writeLog(1, "data[]:" + ConversionUtils.byteToHex(bArr));
            this.handler.removeCallbacks(this.checkMsg);
            if (this.byteBuffer.remaining() < bArr.length) {
                this.byteBuffer.clear();
            }
            this.byteBuffer.put(bArr);
            handleByteBuffer(this.byteBuffer);
            if (this.byteBuffer.position() > 0) {
                this.handler.postDelayed(this.checkMsg, 2000L);
            }
        }
    }

    public synchronized void sendOrder(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length != 0) {
                if (this.communication != null) {
                    writeLog(0, ConversionUtils.byteToHex(bArr));
                    this.communication.sendData(bArr, this.writeCallback);
                }
            }
        }
    }

    public synchronized void sendOrder(byte[] bArr, OperateType operateType) {
        this.operateType = operateType;
        sendOrder(bArr);
    }

    public String calculateCutSpeed(int i, int i2, int i3, int i4, String str) {
        int i5;
        int i6;
        int i7 = 900;
        int i8 = 300;
        if (CuttingMachine.getInstance().isAlpha()) {
            i5 = 4000;
            i8 = 900;
        } else {
            i5 = MachineData.s1dShoulderDis;
            i7 = 300;
        }
        int i9 = 0;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            i7 = Integer.parseInt(split[0]);
            i8 = Integer.parseInt(split[1]);
            i5 = Integer.parseInt(split[2]);
        }
        float abs = Math.abs(i - i3);
        float abs2 = Math.abs(i2 - i4);
        if (abs == 0.0f || abs2 == 0.0f) {
            if (abs == 0.0f) {
                i7 = 0;
            }
            i6 = i7;
            if (abs2 != 0.0f) {
                i9 = i8;
            }
        } else {
            double d = abs;
            double d2 = abs2;
            double sqrt = Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d));
            i6 = (int) (i7 * (d / sqrt));
            i9 = (int) (i8 * (d2 / sqrt));
        }
        return i6 + "," + i9 + "," + i5;
    }

    public void writeLog(int i, String str) {
        String str2;
        this.handler.removeCallbacks(this.writeLogRunnable);
        String format = new SimpleDateFormat("HH:mm:ss:SSS", Locale.US).format(new Date());
        if (i == 0) {
            Log.i(TAG, "发送: " + str);
            str2 = format + " 发送：" + str + "\n";
        } else {
            Log.i(TAG, "接收: " + str);
            str2 = format + " 接收：" + str + "\n";
        }
        this.logs.append(str2);
        this.handler.postDelayed(this.writeLogRunnable, 2000L);
    }

    public <T> void sendEventMessage(int i, T t) {
        Communication communication = this.communication;
        if (communication != null) {
            communication.sendEventBusMessage(i, t);
        }
    }

    public void sendEventMessage(int i) {
        Communication communication = this.communication;
        if (communication != null) {
            communication.sendEventBusMessage(i, null);
        }
    }

    public OperateType getOperateType() {
        return this.operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public ErrorCodeBean getErrorBean(int i) {
        return this.errorHandle.getErrorBean(i);
    }

    public KeyAlignInfo getKeyAlignInfo() {
        return this.keyAlignInfo;
    }

    public void reset() {
        this.spindleState = SpindleState.OFF;
        this.stepBeanList = null;
        this.f413DC.setLength(0);
    }

    public void move(int i, int i2, int i3, int i4) {
        sendOrder(Command.DecoderOperation(1, i, i2, i3, i4, ""), OperateType.MOVE_XYZ);
    }

    public int getCurrentX() {
        SerialResBean serialResBean = this.lastPosition;
        if (serialResBean != null) {
            return serialResBean.getX();
        }
        return 0;
    }

    public int getCurrentY() {
        SerialResBean serialResBean = this.lastPosition;
        if (serialResBean != null) {
            return serialResBean.getY();
        }
        return 0;
    }

    public int getCurrentZ() {
        SerialResBean serialResBean = this.lastPosition;
        if (serialResBean != null) {
            return serialResBean.getZ();
        }
        return 0;
    }

    private void handleDecodeData(SerialResBean serialResBean, String str, String str2) {
        int x;
        int abs;
        int decoderSize2 = 0;
        int fixZ;
        int abs2;
        int decoderSize22;
        int abs3 = 0;
        int keyWidthSteps = 0;
        int abs4;
        int decoderRadius2;
        int abs5;
        int decoderRadius22;
        int y;
        int xKey2MachineDire;
        Log.d(TAG, "handleDecodeData() called with: serialResBean = [" + serialResBean + "], rowStr = [" + str + "], columnStr = [" + str2 + "]");
        if (CuttingMachine.getInstance().isE9()) {
            x = serialResBean.getY();
        } else {
            x = serialResBean.getX();
        }
        if (getKeyInfo() != null && getKeyInfo().keyType == KeyType.DOUBLE_OUTSIDE_GROOVE_KEY) {
            if (str.equals("1")) {
                abs5 = Math.abs(x - this.keyAlignInfo.getLeft());
                decoderRadius22 = getDecoderSize2();
                abs = abs5 - decoderRadius22;
                str = "2";
            } else {
                abs4 = Math.abs(x - this.keyAlignInfo.getRight());
                decoderRadius2 = getDecoderSize2();
                abs = abs4 - decoderRadius2;
                str = "1";
            }
        } else {
            if (getKeyInfo() != null && (getKeyInfo().keyType == KeyType.DOUBLE_INSIDE_GROOVE_KEY || getKeyInfo().keyType == KeyType.TWO_GROOVE || getKeyInfo().keyType == KeyType.THREE_GROOVE || getKeyInfo().keyType == KeyType.LEFT_GROOVE || getKeyInfo().keyType == KeyType.RIGHT_GROOVE)) {
                if (str.equals("1")) {
                    abs = Math.abs(x - this.keyAlignInfo.getLeft());
                    decoderSize2 = getDecoderSize2();
                } else {
                    abs = Math.abs(x - this.keyAlignInfo.getRight());
                    decoderSize2 = getDecoderSize2();
                }
            } else if (getKeyInfo() != null && getKeyInfo().keyType == KeyType.DOUBLE_SIDE_KEY) {
                if (ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S2")) {
                    abs = Math.abs(x - this.keyAlignInfo.getRight());
                    decoderSize2 = getDecoderSize2();
                } else {
                    if (CuttingMachine.getInstance().isE9() && this.keyAlignInfo.getSlopeX() != 0) {
                        int slopeX = (int) ((this.keyAlignInfo.getSlopeX() - serialResBean.getX()) * ClampManager.getInstance().getE9S1C().getK());
                        Log.i(TAG, "fixY: " + slopeX);
                        x = slopeX + serialResBean.getY();
                    }
                    if (str.equals("1")) {
                        if (this.isDuplicateDecode) {
                            abs5 = Math.abs(x - this.keyAlignInfo.getCenter());
                            decoderRadius22 = getDecoderRadius2();
                            abs = abs5 - decoderRadius22;
                            str = "2";
                        } else {
                            abs3 = Math.abs(x - this.keyAlignInfo.getCenter()) - getDecoderRadius2();
                            keyWidthSteps = getKeyInfo().keyWidthSteps / 2;
                        }
                    } else if (this.isDuplicateDecode) {
                        abs4 = Math.abs(x - this.keyAlignInfo.getCenter());
                        decoderRadius2 = getDecoderRadius2();
                        abs = abs4 - decoderRadius2;
                        str = "1";
                    } else {
                        abs3 = Math.abs(x - this.keyAlignInfo.getCenter()) - getDecoderRadius2();
                        keyWidthSteps = getKeyInfo().keyWidthSteps / 2;
                    }
                    abs = abs3 + keyWidthSteps;
                }
            } else if (getKeyInfo() != null && (getKeyInfo().keyType == KeyType.SINGLE_INSIDE_GROOVE_KEY || getKeyInfo().keyType == KeyType.SINGLE_OUTSIDE_GROOVE_KEY)) {
                if (getKeyInfo().side == 0 || getKeyInfo().side == 5) {
                    abs2 = Math.abs(x - this.keyAlignInfo.getLeft());
                    decoderSize22 = getDecoderSize2();
                } else if (getKeyInfo().side == 6) {
                    if (str.equals("1")) {
                        abs2 = Math.abs(x - this.keyAlignInfo.getLeft());
                        decoderSize22 = getDecoderSize2();
                    } else {
                        abs2 = Math.abs(x - this.keyAlignInfo.getRight());
                        decoderSize22 = getDecoderSize2();
                    }
                } else {
                    abs2 = Math.abs(x - this.keyAlignInfo.getRight());
                    decoderSize22 = getDecoderSize2();
                }
                abs = abs2 - decoderSize22;
                if (!this.isDuplicateDecode && ClampManager.getInstance().getCurrentClamp() == Clamp.S1_C && getKeyInfo().keyType == KeyType.SINGLE_OUTSIDE_GROOVE_KEY) {
                    OperationParamBase operationParamBase = this.userSetting;
                    if ((operationParamBase instanceof DataParam) && ((DataParam) operationParamBase).isSlantCorrection()) {
                        decoderSize2 = (7 - (Integer.parseInt(str2) - 1)) * UnitConvertUtil.cmm2StepY(6);
                    }
                }
            } else if (getKeyInfo() != null && getKeyInfo().keyType == KeyType.SINGLE_SIDE_KEY) {
                abs = Math.abs(x - this.keyAlignInfo.getRight());
                decoderSize2 = getDecoderSize2();
            } else if (getKeyInfo() != null && (getKeyInfo().keyType == KeyType.TUBULAR_KEY || getKeyInfo().keyType == KeyType.SIDE_HOLE)) {
                abs = Math.abs(serialResBean.getZ() - this.keyAlignInfo.getKeyFace());
            } else if (getKeyInfo() != null && (getKeyInfo().keyType == KeyType.DIMPLE_KEY || getKeyInfo().keyType == KeyType.SIDE_TOOTH_3KS_KEY)) {
                int z = serialResBean.getZ();
                int y2 = serialResBean.getY();
                int x2 = serialResBean.getX();
                if (CuttingMachine.getInstance().isE9()) {
                    fixZ = DataFixUtil.getFixZ(this.keyAlignInfo, x2);
                } else {
                    fixZ = DataFixUtil.getFixZ(this.keyAlignInfo, y2);
                }
                int i = z - fixZ;
                if (ClampManager.getInstance().getCurrentClamp() == Clamp.E9S1A) {
                    abs = Math.abs(i - (this.keyAlignInfo.getClampFace() + ClampManager.getInstance().getE9S1A().getHigh1()));
                } else if (ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S1-A")) {
                    abs = Math.abs(i - (this.keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh()));
                } else if (ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S1-B")) {
                    if (isHorizontalClamping()) {
                        abs = Math.abs(i - (this.keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1()));
                    } else {
                        abs = Math.abs(i - ((this.keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1()) + ClampManager.getInstance().getS1B().getHigh2()));
                    }
                } else {
                    abs = ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S10") ? Math.abs(i - (this.keyAlignInfo.getClampFace() + UnitConvertUtil.cmm2StepZ(200))) : 0;
                }
            } else {
                abs = Math.abs(x - this.keyAlignInfo.getLeft());
                decoderSize2 = getDecoderSize2();
            }
            abs -= decoderSize2;
        }
        if (CuttingMachine.getInstance().isE9()) {
            y = serialResBean.getX();
        } else {
            y = serialResBean.getY();
        }
        if (getKeyInfo().keyAlign == KeyAlign.SHOULDERS_ALIGN) {
            xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(y - this.keyAlignInfo.getShoulder());
        } else {
            xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(y - this.keyAlignInfo.getTip());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("row", Integer.parseInt(str));
        bundle.putInt("column", Integer.parseInt(str2));
        bundle.putInt("space", xKey2MachineDire);
        bundle.putInt("depth", abs);
        bundle.putInt("keyIndex", this.duplicateKeyIndex);
        sendEventMessage(0, bundle);
    }

    private int getDecoderRadius() {
        return ToolSizeManager.getDecoderRadius();
    }

    private int getDecoderRadius2() {
        return ToolSizeManager.getDecoderRadius2();
    }

    private int getDecoderSize2() {
        return ToolSizeManager.getDecoderSize2();
    }

    private int getCutterRadiusSize2() {
        return ToolSizeManager.getCutterRadiusSize2();
    }

    private int getCutterSize2() {
        return ToolSizeManager.getCutterSize2();
    }

    public void clearAllState() {
        this.ckpParam = null;
        this.userSetting = null;
        this.calibrationPoints = null;
        this.lastPosition = null;
        this.retry.clear();
        this.multiPoint = false;
        this.zList.clear();
        this.tempIndex = 0;
        this.stepDiff = 0;
        this.go5 = false;
        this.go3 = false;
        this.go2 = false;
        this.f414go = false;
        this.index = 0;
        this.f413DC.setLength(0);
        this.duplicateKeyIndex = 0;
        this.groovePosition = null;
        this.duplicateHeightDecode = false;
        this.highest = null;
        this.spindleState = SpindleState.OFF;
        this.duplicateRow = null;
        this.isDuplicateDecode = false;
        this.keyAlignInfo = new KeyAlignInfo();
        this.keyinfo = null;
        this.tempOrder = null;
        this.stepBeanList = null;
    }

    /* renamed from: com.cutting.machine.communication.OperationManager$8 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19878 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.ANGLE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_SIDE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_INSIDE_GROOVE_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TWO_GROOVE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.THREE_GROOVE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TUBULAR_KEY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.communication.OperationManager$1 */
    /* loaded from: classes2.dex */
    public class C19801 extends WriteCallback {
        C19801() {
        }

        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteSuccess(int i, int i2, byte[] bArr) {
        }

        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteFailure(Exception exc) {
            OperationManager.this.writeLog(0, "发送失败");
        }
    }

    /* renamed from: com.cutting.machine.communication.OperationManager$2 */
    /* loaded from: classes2.dex */
    public class C19812 extends Thread {
        final /* synthetic */ String val$filePath;
        final /* synthetic */ OperateType val$operateType;

        C19812(String str2, OperateType operateType2) {
            val$filePath = str2;
            val$operateType = operateType2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            OperationManager.this.startExecution(AssetsJsonUtils.json2Steps(AssetsJsonUtils.getJsonStringFromAssets(OperationManager.this.context, val$filePath)), val$operateType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.communication.OperationManager$3 */
    /* loaded from: classes2.dex */
    public class RunnableC19823 implements Runnable {
        RunnableC19823() {
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr = new byte[OperationManager.this.byteBuffer.flip().remaining()];
            OperationManager.this.byteBuffer.get(bArr);
            OperationManager.this.byteBuffer.clear();
            OperationManager.this.writeLog(1, "返回指令错误: " + ConversionUtils.byteToHex(bArr));
            ErrorHelper.handleError(ErrorCode.ReceiveDataError);
        }
    }

    /* renamed from: com.cutting.machine.communication.OperationManager$4 */
    /* loaded from: classes2.dex */
    public class RunnableC19834 implements Runnable {
        RunnableC19834() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OperationManager.this.tempOrder != null) {
                OperationManager operationManager = OperationManager.this;
                operationManager.sendOrder(operationManager.tempOrder);
                OperationManager.this.tempOrder = null;
            }
        }
    }

    /* renamed from: com.cutting.machine.communication.OperationManager$5 */
    /* loaded from: classes2.dex */
    public class C19845 implements Comparator<PointXyz> {
        C19845() {
        }

        @Override // java.util.Comparator
        public int compare(PointXyz pointXyz6, PointXyz pointXyz7) {
            return pointXyz6.getZ() - pointXyz7.getZ();
        }
    }

    /* renamed from: com.cutting.machine.communication.OperationManager$6 */
    /* loaded from: classes2.dex */
    public class C19856 implements Comparator<List<PointXyz>> {
        C19856() {
        }

        @Override // java.util.Comparator
        public int compare(List<PointXyz> list8, List<PointXyz> list9) {
            return list9.size() - list8.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.communication.OperationManager$7 */
    /* loaded from: classes2.dex */
    public class RunnableC19867 implements Runnable {
        RunnableC19867() {
        }

        @Override // java.lang.Runnable
        public void run() {
            new Thread() { // from class: com.cutting.machine.communication.OperationManager.7.1


                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    File file;
                    FileWriter fileWriter = null;
                    try {
                        try {
                            try {
                                File[] listFiles = OperationManager.this.context.getExternalFilesDir("").listFiles();
                                if (listFiles != null) {
                                    int length = listFiles.length;
                                    for (int i = 0; i < length; i++) {
                                        file = listFiles[i];
                                        if (file.getName().startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile()) {
                                            break;
                                        }
                                    }
                                }
                                file = null;
                                if (file == null) {
                                    file = new File(OperationManager.this.context.getExternalFilesDir(""), "SerialLog_" + new Date().getTime() + ".txt");
                                }
                                FileWriter fileWriter2 = new FileWriter(file, true);
                                try {
                                    fileWriter2.write(new String(OperationManager.this.logs));
                                    OperationManager.this.logs.setLength(0);
                                    fileWriter2.close();
                                } catch (Exception e) {
                                    fileWriter = fileWriter2;
                                    e = e;
                                    System.out.println(e.getMessage());
                                    if (fileWriter != null) {
                                        fileWriter.close();
                                    }
                                } catch (Throwable th) {
                                    fileWriter = fileWriter2;
                                    th = th;
                                    if (fileWriter != null) {
                                        try {
                                            fileWriter.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception e3) {
                                e = e3;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }.start();
        }

        /* renamed from: com.cutting.machine.communication.OperationManager$7$1 */
        /* loaded from: classes2.dex */
        class AnonymousClass1 extends Thread {
            AnonymousClass1() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                File file;
                FileWriter fileWriter = null;
                try {
                    try {
                        try {
                            File[] listFiles = OperationManager.this.context.getExternalFilesDir("").listFiles();
                            if (listFiles != null) {
                                int length = listFiles.length;
                                for (int i = 0; i < length; i++) {
                                    file = listFiles[i];
                                    if (file.getName().startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile()) {
                                        break;
                                    }
                                }
                            }
                            file = null;
                            if (file == null) {
                                file = new File(OperationManager.this.context.getExternalFilesDir(""), "SerialLog_" + new Date().getTime() + ".txt");
                            }
                            FileWriter fileWriter2 = new FileWriter(file, true);
                            try {
                                fileWriter2.write(new String(OperationManager.this.logs));
                                OperationManager.this.logs.setLength(0);
                                fileWriter2.close();
                            } catch (Exception e) {
                                fileWriter = fileWriter2;
                                e = e;
                                System.out.println(e.getMessage());
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Throwable th) {
                                fileWriter = fileWriter2;
                                th = th;
                                if (fileWriter != null) {
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            e = e3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
}
