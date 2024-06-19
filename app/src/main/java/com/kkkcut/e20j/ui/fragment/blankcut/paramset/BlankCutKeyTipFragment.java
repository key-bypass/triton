package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import butterknife.BindView;
import com.example.spl_key_sdklibrary.mdKeyBlankClass;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.KeyAlignInfo;
import com.liying.core.clamp.ClampF;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;

/* loaded from: classes.dex */
public class BlankCutKeyTipFragment extends BaseBlankCutParamSetFragment {

    @BindView(R.id.et_remaining_thickness)
    EditText etRemainingThickness;

    @BindView(R.id.et_tip_width)
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
