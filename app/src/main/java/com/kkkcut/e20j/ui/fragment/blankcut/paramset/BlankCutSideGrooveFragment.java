package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.clamp.S8;
import com.cutting.machine.clamp.ClampF;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.clamp.S1B;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorHelper;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.spl.key.mdKeyBlankClass;

/* loaded from: classes.dex */
public class BlankCutSideGrooveFragment extends BaseBlankCutParamSetFragment {
    EditText etGrooveLength;

    EditText etGrooveWidth;

    EditText etRemainingThickness;
    private int grooveLength;
    private int grooveWidth;
    private int remainingThickness;

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_side_groove;
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-3.json";
    }

    public static BlankCutSideGrooveFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutSideGrooveFragment blankCutSideGrooveFragment = new BlankCutSideGrooveFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutSideGrooveFragment.setArguments(bundle);
        return blankCutSideGrooveFragment;
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        int high2;
        boolean z = clampF instanceof S8;
        if (z) {
            high2 = ((S8) clampF).getHeight2();
        } else {
            S1B s1b = (S1B) clampF;
            high2 = s1b.getHigh2() + s1b.getHigh1();
        }
        if (this.remainingThickness / MachineData.dZScale >= high2 + 10) {
            return false;
        }
        if (z) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
            return true;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
        return true;
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setRepairKeyBlakType(2);
        mdkeyblankclass.setCutFaceSettingType(0);
        KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
        mdkeyblankclass.setKeyBlankThick((((int) (((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS8().getHeight2()) - keyAlignInfo.getKeyFace()) * MachineData.dZScale)) + this.remainingThickness) / 2);
        mdkeyblankclass.setKeyBlankGrooveWidth(this.grooveWidth);
        mdkeyblankclass.setKeyBlankGrooveLength(this.grooveLength);
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etGrooveLength.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        String trim2 = this.etRemainingThickness.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            return false;
        }
        String trim3 = this.etGrooveWidth.getText().toString().trim();
        if (TextUtils.isEmpty(trim3)) {
            return false;
        }
        try {
            this.grooveLength = Integer.parseInt(trim);
            this.remainingThickness = Integer.parseInt(trim2);
            this.grooveWidth = Integer.parseInt(trim3);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}