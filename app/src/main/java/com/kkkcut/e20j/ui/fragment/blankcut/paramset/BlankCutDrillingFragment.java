package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.cutting.machine.clamp.ClampF;
import com.kkkcut.e20j.us.R;
import com.spl.key.mdKeyBlankClass;

/* loaded from: classes.dex */
public class BlankCutDrillingFragment extends BaseBlankCutParamSetFragment {
    private int diameter;
    private int distance;

    EditText etDiameter;

    EditText etDistance;

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        return false;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_drilling;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-1.json";
    }

    public static BlankCutDrillingFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutDrillingFragment blankCutDrillingFragment = new BlankCutDrillingFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutDrillingFragment.setArguments(bundle);
        return blankCutDrillingFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setCutFaceSettingType(0);
        mdkeyblankclass.setRepairKeyBlakType(0);
        mdkeyblankclass.setCenterToShoulderDistance(this.distance);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etDistance.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        try {
            this.distance = Integer.parseInt(trim);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
