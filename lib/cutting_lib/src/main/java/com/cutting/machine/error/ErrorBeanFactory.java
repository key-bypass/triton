package com.cutting.machine.error;

import com.cutting.machine.Command;
import com.cutting.machine.DialogBtnCallBack;
import com.cutting.machine.OperateType;
import com.cutting.machine.communication.OperationManager;

/* loaded from: classes2.dex */
public class ErrorBeanFactory {
    public static ErrorBean getErrorBean(int i, String str) {
        ErrorBean errorBean = new ErrorBean(i, str);
        errorBean.setDialogBtnCallBack(new DialogBtnCallBack() { // from class: com.cutting.machine.error.ErrorBeanFactory.1
            @Override // com.cutting.machine.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.RESET);
            }
        });
        return errorBean;
    }
}
