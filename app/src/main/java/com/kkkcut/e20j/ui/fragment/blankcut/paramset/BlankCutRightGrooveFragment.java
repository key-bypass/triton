package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import butterknife.BindView;
import com.example.spl_key_sdklibrary.mdKeyBlankClass;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;
import com.liying.core.clamp.ClampF;
import com.liying.core.clamp.MachineData;
import com.liying.core.clamp.S1B;
import com.liying.core.clamp.S8;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;

/* loaded from: classes.dex */
public class BlankCutRightGrooveFragment extends BaseBlankCutParamSetFragment {

    @BindView(R.id.et_groove_length)
    EditText etGrooveLength;

    @BindView(R.id.et_remaining_thickness)
    EditText etRemainingThickness;

    @BindView(R.id.et_remaining_width)
    EditText etRemainingWidth;
    private int grooveLength;
    private int remainingThickness;
    private int remainingWidth;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_right_groove;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    public static BlankCutRightGrooveFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutRightGrooveFragment blankCutRightGrooveFragment = new BlankCutRightGrooveFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutRightGrooveFragment.setArguments(bundle);
        return blankCutRightGrooveFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
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

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setRepairKeyBlakType(2);
        mdkeyblankclass.setCutFaceSettingType(0);
        mdkeyblankclass.setKeyBlankThick(this.remainingThickness);
        mdkeyblankclass.setKeyBlankGrooveThick(this.remainingWidth);
        mdkeyblankclass.setKeyBlankGrooveLength(this.grooveLength);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etGrooveLength.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        String trim2 = this.etRemainingThickness.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            return false;
        }
        String trim3 = this.etRemainingWidth.getText().toString().trim();
        if (TextUtils.isEmpty(trim3)) {
            return false;
        }
        try {
            this.grooveLength = Integer.parseInt(trim);
            this.remainingThickness = Integer.parseInt(trim2);
            this.remainingWidth = Integer.parseInt(trim3);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return MachineInfo.isChineseMachine() ? "duplicate/decoder/S1-B-D(ThreeendsTop).json" : "keyblank/decoder/S8-3.json";
    }
}
