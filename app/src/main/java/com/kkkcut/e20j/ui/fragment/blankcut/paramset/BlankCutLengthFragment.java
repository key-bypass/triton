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
public class BlankCutLengthFragment extends BaseBlankCutParamSetFragment {

    EditText etKeyLength;
    private int length;

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        return false;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_length;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-3.json";
    }

    public static BlankCutLengthFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutLengthFragment blankCutLengthFragment = new BlankCutLengthFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutLengthFragment.setArguments(bundle);
        return blankCutLengthFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void initViewsAndEvents() {
        super.initViewsAndEvents();
        if (getBlankCutType() == BlankCutType.KW16ToKW15) {
            this.etKeyLength.setText("2840");
        } else if (getBlankCutType() == BlankCutType.KW14ToKW15) {
            this.etKeyLength.setText("2840");
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setKeyBlankGrooveLength(this.length);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etKeyLength.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        try {
            this.length = Integer.parseInt(trim);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
