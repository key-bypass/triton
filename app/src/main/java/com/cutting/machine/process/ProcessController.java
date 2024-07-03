package com.cutting.machine.process;

import com.cutting.machine.OperateType;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.utils.AssetsJsonUtils;

/* loaded from: classes2.dex */
public class ProcessController {
    private static ProcessController sInstance;

    private ProcessController() {
    }

    public static ProcessController getInstance() {
        if (sInstance == null) {
            sInstance = new ProcessController();
        }
        return sInstance;
    }

    public void startCalibrating(Clamp clamp) {
        ClampManager.getInstance().setCurrentClamp(clamp);
        OperationManager.getInstance().startExecution(AssetsJsonUtils.getCalibrationSteps(clamp), OperateType.CALIBRAT_CLAMP);
    }
}
