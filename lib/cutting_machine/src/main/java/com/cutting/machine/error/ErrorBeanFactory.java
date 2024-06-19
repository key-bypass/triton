package com.liying.core.error;

import com.liying.core.Command;
import com.liying.core.DialogBtnCallBack;
import com.liying.core.OperateType;
import com.liying.core.communication.OperationManager;

/* loaded from: classes2.dex */
public class ErrorBeanFactory {
    public static ErrorBean getErrorBean(int i, String str) {
        ErrorBean errorBean = new ErrorBean(i, str);
        errorBean.setDialogBtnCallBack(new DialogBtnCallBack() { // from class: com.liying.core.error.ErrorBeanFactory.1
            @Override // com.liying.core.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.RESET);
            }
        });
        return errorBean;
    }
}
