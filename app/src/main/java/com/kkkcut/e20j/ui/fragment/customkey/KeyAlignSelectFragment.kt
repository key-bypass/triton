package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.androidquick.autolayout.widget.AutoRadioGroup;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;

/* loaded from: classes.dex */
public class KeyAlignSelectFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";
    CustomKey customKey;

    RadioButton rbShoulder;

    RadioButton rbTip;

    AutoRadioGroup rg1;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_align_select;
    }

    public static KeyAlignSelectFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeyAlignSelectFragment keyAlignSelectFragment = new KeyAlignSelectFragment();
        keyAlignSelectFragment.setArguments(bundle);
        return keyAlignSelectFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.please_choose_key_align_method);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        switch (customKey.getType()) {
            case 0:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doublekey_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doublekey_tip_custom));
                break;
            case 1:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singlekey_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singlekey_tip_custom));
                break;
            case 2:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doubleinside_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doubleinside_tip_custom));
                break;
            case 3:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singleoutside_down_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singleoutside_down_tip_custom));
                break;
            case 4:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doubleoutside_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.doubleoutside_tip_custom));
                break;
            case 5:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singleinside_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.singleinside_tip_custom));
                break;
            case 6:
                this.rbShoulder.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.dimple_shoulder_custom));
                this.rbTip.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.dimple_tip_custom));
                break;
        }
        if (this.customKey.getAlign() == 1) {
            this.rbTip.setChecked(true);
        } else {
            this.rbShoulder.setChecked(true);
        }
        this.rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyAlignSelectFragment.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_shoulder) {
                    KeyAlignSelectFragment.this.customKey.setAlign(0);
                } else {
                    if (i != R.id.rb_tip) {
                        return;
                    }
                    KeyAlignSelectFragment.this.customKey.setAlign(1);
                }
            }
        });
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_last) {
            onBack();
        } else {
            if (id != R.id.bt_next) {
                return;
            }
            start(KeySpaceSetFragment.newInstance(this.customKey));
        }
    }
}
