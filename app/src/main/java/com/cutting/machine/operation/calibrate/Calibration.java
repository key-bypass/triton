package com.cutting.machine.operation.calibrate;

import com.cutting.machine.Command;
import com.cutting.machine.CuttingMachine;
import com.cutting.machine.OperateType;
import com.cutting.machine.bean.EventBean;
import com.cutting.machine.bean.SerialResBean;
import com.cutting.machine.clamp.S5;
import com.cutting.machine.clamp.S8;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.DecoderCutterDistance;
import com.cutting.machine.clamp.E9S1A;
import com.cutting.machine.clamp.E9S1C;
import com.cutting.machine.clamp.E9S2A;
import com.cutting.machine.clamp.E9S2B;
import com.cutting.machine.clamp.E9S5;
import com.cutting.machine.clamp.S1A;
import com.cutting.machine.clamp.S1B;
import com.cutting.machine.clamp.S1C;
import com.cutting.machine.clamp.S1D;
import com.cutting.machine.clamp.S2A;
import com.cutting.machine.clamp.S2B;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.operation.Operation;
import com.cutting.machine.utils.AssetsJsonUtils;
import com.cutting.machine.utils.DecoderPositionUtils;

import java.util.List;
import java.util.Observable;

/* loaded from: classes2.dex */
public class Calibration implements Operation {
    public static final String DC_S1 = "calibration/D-C.json";
    public static final String DC_S2 = "calibration/D-C(S2-A).json";
    public static final String E9S5 = "calibration_e9/S5.json";
    public static final String E9_DC_S1 = "calibration_e9/D-C.json";
    public static final String E9_S1A = "calibration_e9/S1-A.json";
    public static final String E9_S1C = "calibration_e9/S1-C.json";
    public static final String E9_S1D = "calibration_e9/S1-D.json";
    public static final String E9_S2A = "calibration_e9/S2-A.json";
    public static final String E9_S2B = "calibration_e9/S2-B.json";
    public static final String S1A = "calibration/S1-A.json";
    public static final String S1B = "calibration/S1-B.json";
    public static final String S1C = "calibration/S1-C.json";
    public static final String S1D = "calibration/S1-D.json";
    public static final String S2A = "calibration/S2-A.json";
    public static final String S2B = "calibration/S2-B.json";

    /* renamed from: S5 */
    public static final String f424S5 = "calibration/S5.json";

    /* renamed from: S8 */
    public static final String f425S8 = "calibration/S8.json";
    CalibrationParams calibrationParams;

    public Calibration(CalibrationParams calibrationParams) {
        this.calibrationParams = calibrationParams;
    }

    @Override // com.cutting.machine.operation.Operation
    public void handleOperate(Observable observable, Object obj) {
        EventBean eventBean = (EventBean) obj;
        OperateType operateType = eventBean.getOperateType();
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION) {
            if (DecoderPositionUtils.isDownPosition(((Integer) eventBean.getData()).intValue())) {
                execute();
                return;
            } else {
                OperationManager.getInstance().sendEventMessage(50, 0);
                return;
            }
        }
        if (operateType == OperateType.CALIBRAT_CLAMP) {
            saveCalibrationData(OperationManager.getInstance().getCalibrationPoints());
            OperationManager.getInstance().sendOrder(getCalibrationDataSaveOrder(), OperateType.SAVE_CALIBRATION_DATA);
        } else if (operateType == OperateType.SAVE_CALIBRATION_DATA) {
            operationFinish();
        }
    }

    private void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    public void calibrateClamp() {
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            execute();
        }
    }

    private boolean isDetectDecoder() {
        return CuttingMachine.getInstance().isE9() && !CuttingMachine.getInstance().isNotDetectDecoder();
    }

    private void execute() {
        String calibrationJsonPath;
        if (this.calibrationParams.isDistanceCalibration()) {
            calibrationJsonPath = getDistanceJsonPath(this.calibrationParams.getClamp());
        } else {
            calibrationJsonPath = getCalibrationJsonPath(this.calibrationParams.getClamp());
        }
        OperationManager.getInstance().start(this.calibrationParams, AssetsJsonUtils.getCommonSteps(CuttingMachine.getInstance().getContext(), calibrationJsonPath), OperateType.CALIBRAT_CLAMP);
    }

    @Override // com.cutting.machine.operation.Operation
    public void operationFinish() {
        OperationManager.getInstance().sendEventMessage(48);
    }

    public String getDistanceJsonPath(Clamp clamp) {
        return clamp == Clamp.S1_D ? DC_S1 : clamp == Clamp.S2_A ? DC_S2 : clamp == Clamp.E9S1C ? E9_DC_S1 : "";
    }

    public String getCalibrationJsonPath(Clamp clamp) {
        switch (C19901.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return S1A;
            case 2:
                return E9_S1A;
            case 3:
                return S1B;
            case 4:
                return S1C;
            case 5:
                return E9_S1C;
            case 6:
                return S1D;
            case 7:
                return S2A;
            case 8:
                return E9_S2A;
            case 9:
                return S2B;
            case 10:
                return E9_S2B;
            case 11:
                return f424S5;
            case 12:
                return E9S5;
            case 13:
                return f425S8;
            default:
                return "";
        }
    }

    public void saveCalibrationData(List<SerialResBean> list) {
        if (this.calibrationParams.isDistanceCalibration()) {
            ClampManager.getInstance().setDc(new DecoderCutterDistance(list, this.calibrationParams.getClamp()));
            return;
        }
        switch (C19901.$SwitchMap$com$liying$core$clamp$Clamp[this.calibrationParams.getClamp().ordinal()]) {
            case 1:
                ClampManager.getInstance().setS1A(new S1A(list));
                return;
            case 2:
                ClampManager.getInstance().setE9S1A(new E9S1A(list));
                return;
            case 3:
                ClampManager.getInstance().setS1B(new S1B(list));
                return;
            case 4:
                ClampManager.getInstance().setS1C(new S1C(list));
                return;
            case 5:
                ClampManager.getInstance().setE9S1C(new E9S1C(list));
                return;
            case 6:
                ClampManager.getInstance().setS1D(new S1D(list));
                return;
            case 7:
                ClampManager.getInstance().setS2A(new S2A(list));
                return;
            case 8:
                ClampManager.getInstance().setE9S2A(new E9S2A(list));
                return;
            case 9:
                ClampManager.getInstance().setS2B(new S2B(list));
                return;
            case 10:
                ClampManager.getInstance().setE9S2B(new E9S2B(list));
                return;
            case 11:
                ClampManager.getInstance().setS5(new S5(list));
                return;
            case 12:
                ClampManager.getInstance().setE9S5(new E9S5(list));
                return;
            case 13:
                ClampManager.getInstance().setS8(new S8(list));
                return;
            default:
        }
    }

    public byte[] getCalibrationDataSaveOrder() {
        byte[] bArr = new byte[0];
        if (this.calibrationParams.isDistanceCalibration()) {
            return ClampManager.getInstance().getDC().getCommand();
        }
        switch (C19901.$SwitchMap$com$liying$core$clamp$Clamp[this.calibrationParams.getClamp().ordinal()]) {
            case 1:
                return ClampManager.getInstance().getS1A().getCommand();
            case 2:
                return ClampManager.getInstance().getE9S1A().getCommand();
            case 3:
                return ClampManager.getInstance().getS1B().getCommand();
            case 4:
                return ClampManager.getInstance().getS1C().getCommand();
            case 5:
                return ClampManager.getInstance().getE9S1C().getCommand();
            case 6:
                return ClampManager.getInstance().getS1D().getCommand();
            case 7:
                return ClampManager.getInstance().getS2A().getCommand();
            case 8:
                return ClampManager.getInstance().getE9S2A().getCommand();
            case 9:
                return ClampManager.getInstance().getS2B().getCommand();
            case 10:
                return ClampManager.getInstance().getE9S2B().getCommand();
            case 11:
                return ClampManager.getInstance().getS5().getCommand();
            case 12:
                return ClampManager.getInstance().getE9S5().getCommand();
            case 13:
                return ClampManager.getInstance().getS8().getCommand();
            default:
                return bArr;
        }
    }

    /* loaded from: classes2.dex */
    private enum Index {
        calibrate,
        saveDate
    }

    /* renamed from: com.cutting.machine.operation.calibrate.Calibration$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19901 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$clamp$Clamp;

        static {
            int[] iArr = new int[Clamp.values().length];
            $SwitchMap$com$liying$core$clamp$Clamp = iArr;
            try {
                iArr[Clamp.S1_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1A.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_B.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_C.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1C.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_D.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_A.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2A.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_B.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2B.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S5.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S5.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S8.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S3.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S4.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }
}
