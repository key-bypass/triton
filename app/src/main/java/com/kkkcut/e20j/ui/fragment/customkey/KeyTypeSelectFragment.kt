package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyTypeSelectFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";
    private CustomKey customKey;

    RadioButton rbDimpleKey;

    RadioButton rbDoubleInsideKey;

    RadioButton rbDoubleKey;

    RadioButton rbDoubleOutsideKey;

    RadioButton rbSingleInsideKey;

    RadioButton rbSingleKey;

    RadioButton rbSingleOutsideKey;

    RadioButton rbTubularKey;

    RadioGroup rg1;

    RadioGroup rg2;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_type_select;
    }

    public static KeyTypeSelectFragment newInstance(CustomKey customKey) {
        KeyTypeSelectFragment keyTypeSelectFragment = new KeyTypeSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        keyTypeSelectFragment.setArguments(bundle);
        return keyTypeSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_dimple_key /* 2131362607 */:
                        if (KeyTypeSelectFragment.this.rbDimpleKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg2.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(6);
                            return;
                        }
                        return;
                    case R.id.rb_double_inside_key /* 2131362613 */:
                        if (KeyTypeSelectFragment.this.rbDoubleInsideKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg2.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(2);
                            return;
                        }
                        return;
                    case R.id.rb_double_key /* 2131362614 */:
                        if (KeyTypeSelectFragment.this.rbDoubleKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg2.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(0);
                            return;
                        }
                        return;
                    case R.id.rb_inside_key /* 2131362624 */:
                        if (KeyTypeSelectFragment.this.rbSingleInsideKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg2.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(5);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_double_outside_key /* 2131362615 */:
                        if (KeyTypeSelectFragment.this.rbDoubleOutsideKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg1.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(4);
                            return;
                        }
                        return;
                    case R.id.rb_single_key /* 2131362651 */:
                        if (KeyTypeSelectFragment.this.rbSingleKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg1.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(1);
                            return;
                        }
                        return;
                    case R.id.rb_single_outside_key /* 2131362652 */:
                        if (KeyTypeSelectFragment.this.rbSingleOutsideKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg1.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(3);
                            return;
                        }
                        return;
                    case R.id.rb_tubular_key /* 2131362664 */:
                        if (KeyTypeSelectFragment.this.rbTubularKey.isChecked()) {
                            KeyTypeSelectFragment.this.rg1.clearCheck();
                            KeyTypeSelectFragment.this.customKey.setType(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        if (customKey != null) {
            switch (customKey.getType()) {
                case 0:
                    this.rbDoubleKey.setChecked(true);
                    break;
                case 1:
                    this.rbSingleKey.setChecked(true);
                    break;
                case 2:
                    this.rbDoubleInsideKey.setChecked(true);
                    break;
                case 3:
                    this.rbSingleOutsideKey.setChecked(true);
                    break;
                case 4:
                    this.rbDoubleOutsideKey.setChecked(true);
                    break;
                case 5:
                    this.rbSingleInsideKey.setChecked(true);
                    break;
                case 6:
                    this.rbDimpleKey.setChecked(true);
                    break;
                case 8:
                    this.rbTubularKey.setChecked(true);
                    break;
            }
        }
        if (MachineInfo.isChineseMachine()) {
            this.rbDimpleKey.setVisibility(8);
            this.rbSingleKey.setVisibility(8);
            this.rbTubularKey.setVisibility(8);
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.select_key_type);
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_last) {
            onBack();
        } else {
            if (id != R.id.bt_next) {
                return;
            }
            if (this.customKey.getType() == 8) {
                start(KeySpaceSetFragment.newInstance(this.customKey));
            } else {
                start(KeyAlignSelectFragment.newInstance(this.customKey));
            }
        }
    }
}
