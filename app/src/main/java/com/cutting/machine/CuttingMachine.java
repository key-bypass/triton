package com.cutting.machine;

import android.content.Context;

import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampBase;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.operation.Operation;
import com.cutting.machine.operation.calibrate.Calibration;
import com.cutting.machine.operation.calibrate.CalibrationParams;
import com.cutting.machine.operation.cut.DataCut;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.operation.decode.DataDecode;
import com.cutting.machine.operation.duplicateCut.DuplicateCut;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateDecode;
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams;

import java.util.Observable;
import java.util.Observer;

/* loaded from: classes2.dex */
public class CuttingMachine implements Observer {
    private static CuttingMachine sInstance;
    private Context context;
    private MachineType machineType;
    private boolean notDetectDecoder;
    private Operation operation;

    private CuttingMachine() {
    }

    public static CuttingMachine getInstance() {
        if (sInstance == null) {
            sInstance = new CuttingMachine();
        }
        OperationManager.getInstance().addObserver(sInstance);
        return sInstance;
    }

    private ClampBase getClamp(Clamp clamp) {
        return null;
    }

    public void moveXYZ(int i, int i2, int i3) {
    }

    public void init(Context context, MachineType machineType, boolean z) {
        this.notDetectDecoder = z;
        setContext(context);
        getInstance().setMachineType(machineType);
    }

    public void init(Context context, MachineType machineType) {
        init(context, machineType, false);
    }

    public void init(Context context) {
        MachineType machineType;
        setContext(context);
        if (MachineInfo.isE9Standard(context)) {
            machineType = MachineType.E9;
        } else {
            machineType = MachineType.Alpha;
        }
        getInstance().setMachineType(machineType);
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isE9() {
        return this.machineType == MachineType.E9;
    }

    public boolean isBeta() {
        return this.machineType == MachineType.Beta;
    }

    public boolean isAlpha() {
        return this.machineType == MachineType.Alpha;
    }

    public MachineType getMachineType() {
        return this.machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
        int i = C19641.$SwitchMap$com$liying$core$MachineType[machineType.ordinal()];
        if (i == 1) {
            MachineData.beta();
        } else if (i == 2) {
            MachineData.alpha();
        } else {
            if (i != 3) {
                return;
            }
            MachineData.m93e9();
        }
    }

    public boolean isNotDetectDecoder() {
        return this.notDetectDecoder;
    }

    public void setNotDetectDecoder(boolean z) {
        this.notDetectDecoder = z;
    }

    public void calibrateClamp(CalibrationParams calibrationParams) {
        Calibration calibration = new Calibration(calibrationParams);
        this.operation = calibration;
        calibration.calibrateClamp();
    }

    public void decode(DataParam dataParam) {
        DataDecode dataDecode = new DataDecode();
        this.operation = dataDecode;
        dataDecode.decode(dataParam);
    }

    public void decodeLocation(DataParam dataParam) {
        DataDecode dataDecode = new DataDecode();
        this.operation = dataDecode;
        dataDecode.decodeLocationOnly(dataParam);
    }

    public void decodeNoLocation(DataParam dataParam) {
        DataDecode dataDecode = new DataDecode();
        this.operation = dataDecode;
        dataDecode.decodeNoLocation(dataParam);
    }

    public void cutLocation(DataParam dataParam) {
        DataCut dataCut = new DataCut(dataParam);
        this.operation = dataCut;
        dataCut.locationOnly();
    }

    public void cutLocationCutter(DataParam dataParam) {
        DataCut dataCut = new DataCut(dataParam);
        this.operation = dataCut;
        dataCut.locationCutterHeightOnly();
    }

    public void cut(DataParam dataParam) {
        DataCut dataCut = new DataCut(dataParam);
        this.operation = dataCut;
        dataCut.cut();
    }

    public void cutFromCutterLocation(DataParam dataParam) {
        DataCut dataCut = new DataCut(dataParam);
        this.operation = dataCut;
        dataCut.startCutFromCutterLocation();
    }

    public void cutNoLocation(DataParam dataParam) {
        DataCut dataCut = new DataCut(dataParam);
        this.operation = dataCut;
        dataCut.cutNoLocation();
    }

    public void duplicateDecode(DuplicateDecodeParams duplicateDecodeParams) {
        DuplicateDecode duplicateDecode = new DuplicateDecode();
        this.operation = duplicateDecode;
        duplicateDecode.duplicateDecode(duplicateDecodeParams);
    }

    public void engravingDecode(DuplicateDecodeParams duplicateDecodeParams) {
        DuplicateDecode duplicateDecode = new DuplicateDecode();
        this.operation = duplicateDecode;
        duplicateDecode.duplicateDecode(duplicateDecodeParams);
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        Operation operation = this.operation;
        if (operation != null) {
            operation.handleOperate(observable, obj);
        }
    }

    public void turnOnSpindle() {
        OperationManager.getInstance().sendOrder(Command.SpindleOperation(8000), OperateType.NONE);
    }

    public void turnOffSpindle() {
        OperationManager.getInstance().sendOrder(Command.SpindleOperation(0), OperateType.NONE);
    }

    public void duplicateCut(DuplicateCutParams duplicateCutParams) {
        DuplicateCut duplicateCut = new DuplicateCut(duplicateCutParams);
        this.operation = duplicateCut;
        duplicateCut.duplicateCut();
    }

    public void duplicateCutFromCutterLocation(DuplicateCutParams duplicateCutParams) {
        DuplicateCut duplicateCut = new DuplicateCut(duplicateCutParams);
        this.operation = duplicateCut;
        duplicateCut.duplicateCutFromCutterLocation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.CuttingMachine$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19641 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$MachineType;

        static {
            int[] iArr = new int[MachineType.values().length];
            $SwitchMap$com$liying$core$MachineType = iArr;
            try {
                iArr[MachineType.Beta.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$MachineType[MachineType.Alpha.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$MachineType[MachineType.E9.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
