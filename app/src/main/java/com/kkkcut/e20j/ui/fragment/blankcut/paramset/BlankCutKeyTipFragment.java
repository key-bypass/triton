package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.clamp.ClampF;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.kkkcut.e20j.us.R;
import com.spl.key.mdKeyBlankClass;

/* loaded from: classes.dex */
public class BlankCutKeyTipFragment extends BaseBlankCutParamSetFragment {

    EditText etRemainingThickness;

    EditText etTipWidth;
    private int remainingThickness;
    private int tipWidth;

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        return false;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_key_tip;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-2.json";
    }

    public static BlankCutKeyTipFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutKeyTipFragment blankCutKeyTipFragment = new BlankCutKeyTipFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutKeyTipFragment.setArguments(bundle);
        return blankCutKeyTipFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setRepairKeyBlakType(1);
        mdkeyblankclass.setCutFaceSettingType(0);
        KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
        mdkeyblankclass.setKeyBlankThick((((int) (((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS8().getHeight1()) - keyAlignInfo.getKeyFace()) * MachineData.dZScale)) + this.remainingThickness) / 2);
        mdkeyblankclass.setKeyBlankTipWidth(this.tipWidth);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etRemainingThickness.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        String trim2 = this.etTipWidth.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            return false;
        }
        try {
            this.remainingThickness = Integer.parseInt(trim);
            this.tipWidth = Integer.parseInt(trim2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
