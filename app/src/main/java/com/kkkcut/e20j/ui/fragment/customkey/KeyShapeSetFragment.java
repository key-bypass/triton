package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.liying.core.MachineInfo;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class KeyShapeSetFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";

    @BindView(R.id.cb_extra_cut)
    CheckBox cbExtraCut;
    private CustomKey customKey;

    @BindView(R.id.et_angle)
    EditText etAngle;

    @BindView(R.id.et_cut_depth)
    EditText etCutDepth;

    @BindView(R.id.et_groove)
    EditText etGroove;

    @BindView(R.id.et_guide)
    EditText etGuide;

    @BindView(R.id.et_nose)
    EditText etNose;

    @BindView(R.id.et_thickness)
    EditText etThickness;

    @BindView(R.id.et_width)
    EditText etWidth;

    @BindView(R.id.iv_angle)
    ImageView ivAngle;

    @BindView(R.id.iv_cut_depth)
    ImageView ivCutDepth;

    @BindView(R.id.iv_groove)
    ImageView ivGroove;

    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    @BindView(R.id.iv_nose)
    ImageView ivNose;

    @BindView(R.id.iv_thick)
    ImageView ivThick;

    @BindView(R.id.iv_width)
    ImageView ivWidth;

    @BindView(R.id.ll_angle)
    LinearLayout llAngle;

    @BindView(R.id.ll_cut_depth)
    LinearLayout llCutDepth;

    @BindView(R.id.ll_groove)
    LinearLayout llGroove;

    @BindView(R.id.ll_guide)
    LinearLayout llGuide;

    @BindView(R.id.ll_nose)
    LinearLayout llNose;

    @BindView(R.id.ll_thickness)
    LinearLayout llThickness;

    @BindView(R.id.ll_width)
    LinearLayout llWidth;

    @BindView(R.id.tv_unit)
    TextView tvUnit;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_shape;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static KeyShapeSetFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeyShapeSetFragment keyShapeSetFragment = new KeyShapeSetFragment();
        keyShapeSetFragment.setArguments(bundle);
        return keyShapeSetFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        switch (customKey.getType()) {
            case 0:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.doublekey_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.doublekey_width_tip);
                }
                this.llThickness.setVisibility(8);
                this.llCutDepth.setVisibility(8);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
            case 1:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.singlekey_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.singlekey_width_tip);
                }
                this.llThickness.setVisibility(8);
                this.llCutDepth.setVisibility(8);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(0);
                this.ivAngle.setImageResource(R.drawable.single_side_cut_angle);
                break;
            case 2:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.double_internal_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.double_internal_width_tip);
                }
                this.ivThick.setImageResource(R.drawable.laser_thick);
                this.ivCutDepth.setImageResource(R.drawable.double_internal_cutdepth);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.ivNose.setImageResource(R.drawable.double_internal_nose);
                this.llAngle.setVisibility(8);
                break;
            case 3:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.single_external_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.single_external_width_tip);
                }
                this.ivThick.setImageResource(R.drawable.laser_thick);
                this.ivCutDepth.setImageResource(R.drawable.single_external_cutdepth);
                this.llGroove.setVisibility(8);
                this.ivGuide.setImageResource(R.drawable.single_external_guide);
                this.ivNose.setImageResource(R.drawable.single_external_nose);
                this.llAngle.setVisibility(8);
                break;
            case 4:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.double_external_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.double_external_width_tip);
                }
                this.ivThick.setImageResource(R.drawable.laser_thick);
                this.ivCutDepth.setImageResource(R.drawable.double_external_cutdepth);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
            case 5:
                if (this.customKey.getAlign() == 0) {
                    this.ivWidth.setImageResource(R.drawable.single_internal_width_shoulder);
                } else {
                    this.ivWidth.setImageResource(R.drawable.single_internal_width_tip);
                }
                this.ivThick.setImageResource(R.drawable.laser_thick);
                this.ivCutDepth.setImageResource(R.drawable.single_internal_cut_depth);
                this.ivGroove.setImageResource(R.drawable.single_internal_grooveh);
                this.ivGuide.setImageResource(R.drawable.single_internal_guide);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
            case 6:
                this.llCutDepth.setVisibility(8);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
            case 7:
                this.ivWidth.setImageResource(R.drawable.tibbe_width);
                this.ivThick.setImageResource(R.drawable.tibbe_thick);
                this.llCutDepth.setVisibility(8);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
            case 8:
                this.ivWidth.setImageResource(R.drawable.tubular_width);
                this.ivThick.setImageResource(R.drawable.tubular_thick);
                this.ivCutDepth.setImageResource(R.drawable.tubular_cutdepth);
                this.llGroove.setVisibility(8);
                this.llGuide.setVisibility(8);
                this.llNose.setVisibility(8);
                this.llAngle.setVisibility(8);
                break;
        }
        int width = this.customKey.getWidth();
        if (width != 0) {
            if (this.customKey.isInch()) {
                width = Math.round(width / 2.54f);
            }
            this.etWidth.setText(String.valueOf(width));
        }
        int thick = this.customKey.getThick();
        if (thick != 0) {
            if (this.customKey.isInch()) {
                thick = Math.round(thick / 2.54f);
            }
            this.etThickness.setText(String.valueOf(thick));
        }
        String parameter_info = this.customKey.getParameter_info();
        String param = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.CUT_DEPTH);
        if (!TextUtils.isEmpty(param)) {
            if (this.customKey.isInch()) {
                this.etCutDepth.setText(String.valueOf(Math.round(Integer.parseInt(param) / 2.54f)));
            } else {
                this.etCutDepth.setText(param);
            }
        }
        String param2 = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.GROOVE);
        if (!TextUtils.isEmpty(param2)) {
            if (this.customKey.isInch()) {
                this.etGroove.setText(String.valueOf(Math.round(Integer.parseInt(param2) / 2.54f)));
            } else {
                this.etGroove.setText(param2);
            }
        }
        String param3 = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.GUIDE);
        if (!TextUtils.isEmpty(param3)) {
            if (this.customKey.isInch()) {
                this.etGuide.setText(String.valueOf(Math.round(Integer.parseInt(param3) / 2.54f)));
            } else {
                this.etGuide.setText(param3);
            }
        }
        String param4 = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.NOSE);
        if (!TextUtils.isEmpty(param4)) {
            if (this.customKey.isInch()) {
                this.etNose.setText(String.valueOf(Math.round(Integer.parseInt(param4) / 2.54f)));
            } else {
                this.etNose.setText(param4);
            }
        }
        String param5 = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.EXTRA_CUT);
        if (!TextUtils.isEmpty(param5) && param5.equals("1")) {
            this.cbExtraCut.setChecked(true);
        }
        String param6 = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.SINGLE_SIDE_ANGLE);
        if (!TextUtils.isEmpty(param6)) {
            if (this.customKey.isInch()) {
                this.etAngle.setText(String.valueOf(Math.round(Integer.parseInt(param6) / 2.54f)));
            } else {
                this.etAngle.setText(param6);
            }
        }
        if (this.customKey.isInch()) {
            this.tvUnit.setText(R.string._1inch_1000);
        }
    }

    @OnFocusChange({R.id.et_guide, R.id.et_nose})
    public void onFocusChanged(View view, boolean z) {
        int id = view.getId();
        if (id == R.id.et_guide) {
            if (z) {
                this.etNose.setText("");
            }
        } else if (id == R.id.et_nose && z) {
            this.etGuide.setText("");
        }
    }

    @OnClick({R.id.bt_last, R.id.bt_next})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_last) {
            onBack();
            return;
        }
        if (id != R.id.bt_next) {
            return;
        }
        String trim = this.etWidth.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            int parseInt = Integer.parseInt(trim);
            if (this.customKey.isInch()) {
                parseInt = Math.round(parseInt * 2.54f);
            }
            this.customKey.setWidth(parseInt);
        }
        String trim2 = this.etThickness.getText().toString().trim();
        if (!TextUtils.isEmpty(trim2)) {
            int parseInt2 = Integer.parseInt(trim2);
            if (this.customKey.isInch()) {
                parseInt2 = Math.round(parseInt2 * 2.54f);
            }
            this.customKey.setThick(parseInt2);
        }
        String parameter_info = this.customKey.getParameter_info();
        String trim3 = this.etCutDepth.getText().toString().trim();
        if (this.customKey.isInch() & (!TextUtils.isEmpty(trim3))) {
            trim3 = String.valueOf(Math.round(Integer.parseInt(trim3) * 2.54f));
        }
        String putParam = SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.CUT_DEPTH, trim3);
        String trim4 = this.etGroove.getText().toString().trim();
        if (this.customKey.isInch() & (!TextUtils.isEmpty(trim4))) {
            trim4 = String.valueOf(Math.round(Integer.parseInt(trim4) * 2.54f));
        }
        String putParam2 = SpecificParamUtils.putParam(putParam, SpecificParamUtils.GROOVE, trim4);
        if (!TextUtils.isEmpty(trim4) && Integer.parseInt(trim4) < 230) {
            putParam2 = SpecificParamUtils.putParam(putParam2, "cutter", "T60-E15-P,1.5");
        }
        String trim5 = this.etGuide.getText().toString().trim();
        if (this.customKey.isInch() && !TextUtils.isEmpty(trim5)) {
            trim5 = String.valueOf(Math.round(Integer.parseInt(trim5) * 2.54f));
        }
        String putParam3 = SpecificParamUtils.putParam(putParam2, SpecificParamUtils.GUIDE, trim5);
        String trim6 = this.etNose.getText().toString().trim();
        if (this.customKey.isInch() && !TextUtils.isEmpty(trim6)) {
            trim6 = String.valueOf(Math.round(Integer.parseInt(trim6) * 2.54f));
        }
        String putParam4 = SpecificParamUtils.putParam(putParam3, SpecificParamUtils.NOSE, trim6);
        if (this.cbExtraCut.isChecked()) {
            putParam4 = SpecificParamUtils.putParam(putParam4, SpecificParamUtils.EXTRA_CUT, "1");
        }
        if (this.customKey.getType() == 4 && this.customKey.getAlign() == 1) {
            putParam4 = SpecificParamUtils.putParam(putParam4, SpecificParamUtils.LAST_BITTING, "1");
        }
        if (this.customKey.getType() == 2) {
            putParam4 = SpecificParamUtils.putParam(putParam4, SpecificParamUtils.INNER_CUT_ANGLE, "1");
        }
        String trim7 = this.etAngle.getText().toString().trim();
        if (this.customKey.isInch() && !TextUtils.isEmpty(trim7)) {
            trim7 = String.valueOf(Math.round(Integer.parseInt(trim7) * 2.54f));
        }
        this.customKey.setParameter_info(SpecificParamUtils.putParam(putParam4, SpecificParamUtils.SINGLE_SIDE_ANGLE, trim7));
        switch (this.customKey.getType()) {
            case 0:
                if (MachineInfo.isE9Standard(getContext())) {
                    this.customKey.setClampNum("S1");
                    this.customKey.setClampSide("C");
                    this.customKey.setClampSlot("0");
                    break;
                } else {
                    start(KeyClampSetFragment.newInstance(this.customKey));
                    return;
                }
            case 1:
                String[] split = this.customKey.getDepth().split(";")[0].split(",");
                int min = Math.min(Integer.parseInt(split[0]), Integer.parseInt(split[split.length - 1]));
                this.customKey.setClampNum("S2");
                this.customKey.setClampSlot("0");
                if (min > 510) {
                    this.customKey.setClampSide("A");
                    break;
                } else {
                    this.customKey.setClampSide("B");
                    break;
                }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                if (MachineInfo.isE9Standard(getContext())) {
                    this.customKey.setClampNum("S1");
                    this.customKey.setClampSide("A");
                    this.customKey.setClampSlot("0");
                    break;
                } else {
                    start(KeyClampSetFragment.newInstance(this.customKey));
                    return;
                }
            case 7:
                this.customKey.setClampNum("S4");
                this.customKey.setClampSide("A");
                this.customKey.setClampSlot("0");
                break;
            case 8:
                this.customKey.setClampNum("S3");
                this.customKey.setClampSide("A");
                this.customKey.setClampSlot("0");
                break;
        }
        showEditDialog(this.customKey);
    }

    private void showEditDialog(final CustomKey customKey) {
        EditDialog editDialog = new EditDialog(getContext());
        String keyname = customKey.getKeyname();
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname);
        }
        editDialog.setTip(getString(R.string.please_input_the_key_name));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment.1
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                customKey.setKeyname(str);
                UserDataDaoManager.getInstance(KeyShapeSetFragment.this.getContext()).saveCustomKey(customKey);
                KeyShapeSetFragment.this.start(CustomKeyListFragment.newInstance(), 2);
                EventBus.getDefault().post(new EventCenter(11));
            }
        });
        editDialog.show();
    }

    @OnCheckedChanged({R.id.cb_extra_cut})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() != R.id.cb_extra_cut) {
            return;
        }
        if (z) {
            this.ivGroove.setImageResource(R.drawable.single_internal_grooveh_extra);
        } else {
            this.ivGroove.setImageResource(R.drawable.single_internal_grooveh);
        }
    }
}
