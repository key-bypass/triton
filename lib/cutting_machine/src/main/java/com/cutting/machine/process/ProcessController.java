package com.liying.core.process;

import com.liying.core.OperateType;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.communication.OperationManager;
import com.liying.core.utils.AssetsJsonUtils;

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
