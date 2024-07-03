package com.cutting.machine.error;

import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;

import com.cutting.machine.Command;
import com.cutting.machine.DialogBtnCallBack;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.communication.OperationManager;

import java.util.Locale;

/* loaded from: classes2.dex */
public class ErrorHelper {
    public static void handleError(int i, StepBean stepBean) {
        ErrorBean errorBean = getErrorBean(i, stepBean);
        ErrorHelper$$ExternalSyntheticLambda0 errorHelper$$ExternalSyntheticLambda0 = new DialogBtnCallBack() { // from class: com.cutting.machine.error.ErrorHelper$$ExternalSyntheticLambda0
            @Override // com.cutting.machine.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                ErrorHelper.lambda$handleError$0(z);
            }
        };
        if (i != 604) {
            errorBean.setDialogBtnCallBack(errorHelper$$ExternalSyntheticLambda0);
        }
        OperationManager.getInstance().sendEventMessage(33, errorBean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleError$0(boolean z) {
        OperationManager.getInstance().sendEventMessage(34);
        OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.RESET);
    }

    public static void handleError(int i) {
        handleError(i, null);
    }

    private static ErrorBean getErrorBean(int i, StepBean stepBean) {
        ErrorBean errorBean = new ErrorBean();
        String str = "";
        if (i < 0) {
            if (i == -4) {
                str = "有切到夹具的风险";
            } else if (i == -2) {
                i = getTouchErrorCode(-2);
                if (i < 0) {
                    str = "该碰到未碰到";
                }
            } else if (i == -1 && (i = getTouchErrorCode(-1)) < 0) {
                str = "不该碰到而碰到";
            }
            if (i < 0) {
                errorBean.setMsg(str);
                errorBean.setCode(i);
                return errorBean;
            }
        }
        ErrorCodeBean errorBean2 = OperationManager.getInstance().getErrorBean(i);
        if (errorBean2 != null) {
            if (MachineInfo.isChineseMachine()) {
                str = errorBean2.getDesc_zh();
            } else if (getSystemLanguage().contains("cs")) {
                str = errorBean2.getDesc_cs();
            } else if (getSystemLanguage().contains("fr")) {
                str = errorBean2.getDesc_fr();
            } else if (getSystemLanguage().contains("de")) {
                str = errorBean2.getDesc_de();
            } else if (getSystemLanguage().contains("it")) {
                str = errorBean2.getDesc_it();
            } else if (getSystemLanguage().contains("es")) {
                str = errorBean2.getDesc_es();
            } else if (getSystemLanguage().contains("ko")) {
                str = errorBean2.getDesc_ko();
            } else if (getSystemLanguage().contains("pt")) {
                str = errorBean2.getDesc_pt();
            } else if (getSystemLanguage().contains("ru")) {
                str = errorBean2.getDesc_ru();
            } else if (getSystemLanguage().contains("tr")) {
                str = errorBean2.getDesc_tr();
            } else if (getSystemLanguage().contains("hb")) {
                str = errorBean2.getDesc_hb();
            } else if (getSystemLanguage().contains("pl")) {
                str = errorBean2.getDesc_pl();
            } else if (getSystemLanguage().contains("vi")) {
                str = errorBean2.getDesc_vi();
            } else {
                str = errorBean2.getDesc_en();
            }
            if (errorBean2.getMessageType() == 2 && stepBean != null) {
                String des = stepBean.getDes();
                if (!TextUtils.isEmpty(des)) {
                    if (des.contains("&")) {
                        if (MachineInfo.isChineseMachine()) {
                            des = des.split("&")[0];
                        } else {
                            des = des.split("&")[1];
                        }
                    }
                    str = str + ":" + des;
                }
            }
            if (i == 605) {
                String des2 = stepBean.getDes();
                if (!TextUtils.isEmpty(des2)) {
                    if (des2.contains("&")) {
                        if (MachineInfo.isChineseMachine()) {
                            des2 = des2.split("&")[0];
                        } else {
                            des2 = des2.split("&")[1];
                        }
                    }
                    str = des2;
                }
            }
        }
        errorBean.setMsg(str);
        errorBean.setCode(i);
        return errorBean;
    }

    public static String getSystemLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale.getLanguage();
    }

    private static int getTouchErrorCode(int i) {
        OperateType operateType = OperationManager.getInstance().getOperateType();
        if (operateType != OperateType.CALIBRAT_CLAMP) {
            if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION || operateType == OperateType.KEY_BLANK_CUT_LOCATION || operateType == OperateType.DUPLICATE_DECODE_LOCATION || operateType == OperateType.DUPLICATE_CUT_LOCATION || operateType == OperateType.MODIFY_KEY_BLANK_LOCATION) {
                return 150;
            }
            if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE || operateType == OperateType.DUPLICATE_DECODE_EXECUTE) {
                return ErrorCode.keyDecodeFailed;
            }
            if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH || operateType == OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH) {
                return 300;
            }
            return ErrorCode.TouchError;
        }
        switch (C19891.$SwitchMap$com$liying$core$clamp$Clamp[ClampManager.getInstance().getCurrentClamp().ordinal()]) {
            case 1:
            case 2:
                return 52;
            case 3:
                return 53;
            case 4:
                return 54;
            case 5:
                return 55;
            case 6:
                return 56;
            case 7:
                return 57;
            case 8:
                return 58;
            case 9:
                return 59;
            case 10:
                return 60;
            case 11:
                return 61;
            case 12:
                return 62;
            case 13:
                return 63;
            case 14:
                return 64;
            default:
                return ErrorCode.TouchError;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.error.ErrorHelper$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19891 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$clamp$Clamp;

        static {
            int[] iArr = new int[Clamp.values().length];
            $SwitchMap$com$liying$core$clamp$Clamp = iArr;
            try {
                iArr[Clamp.D_C.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.D_C_S2_A.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_A.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_B.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_C.ordinal()] = 5;
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
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_B.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S3.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S4.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S5.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S6.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S7.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S8.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }
}
