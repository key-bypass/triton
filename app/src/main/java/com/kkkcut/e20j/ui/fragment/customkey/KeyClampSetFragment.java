package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class KeyClampSetFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";

    @BindView(R.id.bt_next)
    Button btNext;
    private CustomKey customKey;

    @BindView(R.id.rb_clamp_1)
    RadioButton rbClamp1;

    @BindView(R.id.rb_clamp_2)
    RadioButton rbClamp2;

    @BindView(R.id.rb_clamp_3)
    RadioButton rbClamp3;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_clamp_set;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static KeyClampSetFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeyClampSetFragment keyClampSetFragment = new KeyClampSetFragment();
        keyClampSetFragment.setArguments(bundle);
        return keyClampSetFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        customKey.setClampNum("S1");
        this.rbClamp2.setChecked(true);
        int type = this.customKey.getType();
        if (type == 0) {
            if (this.customKey.getAlign() == 0) {
                this.rbClamp1.setBackgroundResource(R.drawable.custom_clamp_s1_c_shoulder);
                this.rbClamp2.setBackgroundResource(R.drawable.custom_clamp_s1_d_shoulder);
            } else {
                this.rbClamp1.setBackgroundResource(R.drawable.custom_clamp_s1_c_tip);
                this.rbClamp2.setBackgroundResource(R.drawable.custom_clamp_s1_d_tip);
            }
            if (this.customKey.getClampSide().equals("C")) {
                this.rbClamp1.setChecked(true);
            }
            this.rbClamp3.setVisibility(8);
            return;
        }
        if (type == 2 || type == 3 || type == 4 || type == 5 || type == 6) {
            if (this.customKey.getAlign() == 0) {
                this.rbClamp1.setBackgroundResource(R.drawable.custom_clamp_s1_a_shoulder);
                this.rbClamp2.setBackgroundResource(R.drawable.custom_clamp_s1_b_shoulder);
                this.rbClamp3.setBackgroundResource(R.drawable.custom_clamp_s1_b_side_shoulder);
            } else {
                this.rbClamp1.setBackgroundResource(R.drawable.custom_clamp_s1_a_tip);
                this.rbClamp2.setBackgroundResource(R.drawable.custom_clamp_s1_b_tip);
                this.rbClamp3.setBackgroundResource(R.drawable.custom_clamp_s1_b_side_tip);
            }
            if (this.customKey.getClampSide().equals("A")) {
                this.rbClamp1.setChecked(true);
            }
            if (this.customKey.getClampSide().equals("B") && this.customKey.getClampSlot().equals("1")) {
                this.rbClamp3.setChecked(true);
            }
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
        Log.i(TAG, "onViewClicked: " + this.customKey.getClampNum() + "-:" + this.customKey.getClampSide() + "-:" + this.customKey.getClampSlot());
        showEditDialog(this.customKey);
    }

    @OnCheckedChanged({R.id.rb_clamp_1, R.id.rb_clamp_2, R.id.rb_clamp_3})
    public void onCheckedChange(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_clamp_1 /* 2131362602 */:
                if (z) {
                    if (this.customKey.getType() == 0) {
                        this.customKey.setClampSide("C");
                    } else {
                        this.customKey.setClampSide("A");
                    }
                    this.customKey.setClampSlot("0");
                    return;
                }
                return;
            case R.id.rb_clamp_2 /* 2131362603 */:
                if (z) {
                    if (this.customKey.getType() == 0) {
                        this.customKey.setClampSide("D");
                    } else {
                        this.customKey.setClampSide("B");
                    }
                    this.customKey.setClampSlot("0");
                    return;
                }
                return;
            case R.id.rb_clamp_3 /* 2131362604 */:
                if (z) {
                    this.customKey.setClampSide("B");
                    this.customKey.setClampSlot("1");
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void showEditDialog(final CustomKey customKey) {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.please_input_the_key_name));
        String keyname = customKey.getKeyname();
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname);
        }
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment.1
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                customKey.setKeyname(str);
                UserDataDaoManager.getInstance(KeyClampSetFragment.this.getContext()).saveCustomKey(customKey);
                KeyClampSetFragment.this.start(CustomKeyListFragment.newInstance(), 2);
                EventBus.getDefault().post(new EventCenter(11));
            }
        });
        editDialog.show();
    }
}
