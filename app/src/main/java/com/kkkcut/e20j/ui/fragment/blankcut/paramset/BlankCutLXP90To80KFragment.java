package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.cutting.machine.clamp.ClampF;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutType;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.spl.key.mdKeyBlankClass;

/* loaded from: classes.dex */
public class BlankCutLXP90To80KFragment extends BaseBlankCutParamSetFragment {

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
        return R.layout.fragment_blank_cut_lxp90to80k;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-2.json";
    }

    public static BlankCutLXP90To80KFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutLXP90To80KFragment blankCutLXP90To80KFragment = new BlankCutLXP90To80KFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutLXP90To80KFragment.setArguments(bundle);
        return blankCutLXP90To80KFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void initViewsAndEvents() {
        if (getBlankCutType() == BlankCutType.LXP90To80K) {
            this.etRemainingThickness.setText("175");
            this.etTipWidth.setText("150");
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setRepairKeyBlakType(12);
        mdkeyblankclass.setCutFaceSettingType(0);
        mdkeyblankclass.setKeyBlankThick(this.remainingThickness);
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
