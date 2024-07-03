package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.cutting.machine.MachineInfo;
import com.cutting.machine.clamp.S8;
import com.cutting.machine.clamp.ClampF;
import com.cutting.machine.clamp.S1B;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorHelper;
import com.cutting.machine.utils.UnitConvertUtil;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutType;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.spl.key.mdKeyBlankClass;


/* loaded from: classes.dex */
public class BlankCutThicknessFragment extends BaseBlankCutParamSetFragment {
    CheckBox cbCutMoreThick;
    private int diameter;

    EditText etThickness;
    private boolean isSecondSide;

    ImageView ivWidth;
    private int thickness;

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_thickness;
    }

    public static BlankCutDrillingFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutDrillingFragment blankCutDrillingFragment = new BlankCutDrillingFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutDrillingFragment.setArguments(bundle);
        return blankCutDrillingFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    public void initViewsAndEvents() {
        if (getBlankCutType() == BlankCutType.WIDTH) {
            this.ivWidth.setImageResource(R.drawable.blank_cut_width);
        } else if (getBlankCutType() == BlankCutType.WIDTH) {
            this.ivWidth.setImageResource(R.drawable.blank_cut_width);
        }
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        int high1;
        boolean z = clampF instanceof S8;
        if (z) {
            if (getBlankCutType() == BlankCutType.WIDTH) {
                high1 = ((S8) clampF).getHeight2();
            } else {
                high1 = ((S8) clampF).getHeight1();
            }
        } else if (getBlankCutType() == BlankCutType.WIDTH) {
            S1B s1b = (S1B) clampF;
            high1 = s1b.getHigh2() + s1b.getHigh1();
        } else {
            high1 = ((S1B) clampF).getHigh1();
        }
        if (UnitConvertUtil.cmm2StepZ(this.thickness) >= high1 + 10) {
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
        mdkeyblankclass.setKeyBlankThick(this.thickness);
        if (getBlankCutType() == BlankCutType.THICKNESS) {
            mdkeyblankclass.setRepairKeyBlakType(1);
        } else {
            mdkeyblankclass.setRepairKeyBlakType(2);
        }
        if (this.isSecondSide) {
            mdkeyblankclass.setCutFaceSettingType(0);
        } else {
            mdkeyblankclass.setCutFaceSettingType(1);
        }
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
        if (!this.isSecondSide) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.show();
            if (getBlankCutType() == BlankCutType.WIDTH) {
                remindDialog.setRemindImg(R.drawable.clamp_remind_blank_cut_width);
            } else {
                remindDialog.setRemindImg(R.drawable.clamp_remind_blank_cut_thickness);
            }
            remindDialog.setRemindMsg(getString(R.string.turn_over_second_side));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutThicknessFragment.1
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        BlankCutThicknessFragment.this.isSecondSide = true;
                        BlankCutThicknessFragment.this.startCut();
                    }
                }
            });
            return;
        }
        this.isSecondSide = false;
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etThickness.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        try {
            this.thickness = Integer.parseInt(trim);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return MachineInfo.isChineseMachine() ? "duplicate/decoder/S1-B(ThreeendsTop).json" : "keyblank/decoder/S8-1.json";
    }
}