package com.kkkcut.e20j.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.eventbus.InputFinishBean;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.cutting.machine.MachineInfo;
import java.lang.reflect.Method;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class IgnitionDoorSearchFragment extends BaseBackFragment {
    public static final int DOOR_TO_IGNITION = 0;
    public static final int IGNITION_TO_DOOR = 1;
    public static final String LENGTH = "length";
    public int STATUS;
    private EditText currentEdit;

    LinearLayout llCode;

    LinearLayout llIndex;

    LinearLayout llTitle;
    private MyOnfocusChanged myOnfocusChanged = new MyOnfocusChanged();

    RadioButton rbIgnitionToDoor;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ignition_door_search;
    }

    public static IgnitionDoorSearchFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(LENGTH, i);
        IgnitionDoorSearchFragment ignitionDoorSearchFragment = new IgnitionDoorSearchFragment();
        ignitionDoorSearchFragment.setArguments(bundle);
        return ignitionDoorSearchFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.ignition_lock_door_lock_check);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        if (MachineInfo.isChineseMachine()) {
            return;
        }
        showRemind();
    }

    private void showRemind() {
        WarningDialog warningDialog = new WarningDialog(getContext());
        warningDialog.show();
        warningDialog.setRemind("The software will calculate the bittings of Side B & C automatically, but show virtual bittings, please ignore the bittings but continue to cut side by side");
    }

    private void initView() {
        int i = getArguments().getInt(LENGTH);
        this.llCode.removeAllViews();
        this.llIndex.removeAllViews();
        this.llTitle.removeAllViews();
        int i2 = 0;
        while (i2 < i) {
            EditText editText = getEditText(i2, "");
            if (i2 == 0) {
                editText.requestFocus();
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 30);
            layoutParams.setMargins(1, 1, 0, 0);
            this.llCode.addView(editText, layoutParams);
            i2++;
            TextView textView = getTextView(String.valueOf(i2));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(60, 30);
            layoutParams2.setMargins(1, 1, 0, 0);
            this.llIndex.addView(textView, layoutParams2);
        }
        if (i == 8) {
            this.rbIgnitionToDoor.setVisibility(8);
        }
        if (this.STATUS == 0) {
            if (i != 8) {
                TextView textView2 = getTextView(getString(R.string.side_face));
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i == 9 ? 182 : 243, 30);
                layoutParams3.setMargins(1, 0, 0, 0);
                this.llTitle.addView(textView2, layoutParams3);
            }
            TextView textView3 = getTextView(getString(R.string.front));
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(i == 8 ? 487 : 365, 30);
            layoutParams4.setMargins(1, 0, 0, 0);
            this.llTitle.addView(textView3, layoutParams4);
            return;
        }
        TextView textView4 = getTextView(getString(R.string.front));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(i == 9 ? 548 : 609, 30);
        layoutParams5.setMargins(1, 0, 0, 0);
        this.llTitle.addView(textView4, layoutParams5);
    }

    private TextView getTextView(String str) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setTextSize(18.0f);
        return textView;
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_delete) {
            delete();
            return;
        }
        if (id == R.id.btn_cancel) {
            onBack();
            return;
        }
        if (id != R.id.btn_ok) {
            switch (id) {
                case R.id.bt_number_1 /* 2131361950 */:
                case R.id.bt_number_2 /* 2131361951 */:
                case R.id.bt_number_3 /* 2131361952 */:
                case R.id.bt_number_4 /* 2131361953 */:
                case R.id.bt_number_5 /* 2131361954 */:
                    inputNumb(((Button) view).getText().toString().trim());
                    return;
                default:
                    switch (id) {
                        case R.id.bt_number_last /* 2131361959 */:
                            changeLast();
                            return;
                        case R.id.bt_number_next /* 2131361960 */:
                            changeNext();
                            return;
                        default:
                            return;
                    }
            }
        }
        int childCount = this.llCode.getChildCount();
        String str = "";
        for (int i = 0; i < childCount; i++) {
            EditText editText = (EditText) this.llCode.getChildAt(i);
            String trim = editText.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                editText.requestFocus();
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
            str = i == childCount - 1 ? str + trim + ";" : str + trim + ",";
        }
        EventBus.getDefault().post(new EventCenter(2, new InputFinishBean(true, str, this.STATUS == 0)));
        start(KeyOperateFragment.newInstance(), 2);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.rb_door_to_ignition) {
            if (z) {
                this.STATUS = 0;
                initView();
                return;
            }
            return;
        }
        if (id == R.id.rb_ignition_to_door && z) {
            this.STATUS = 1;
            initView();
        }
    }

    private void delete() {
        this.currentEdit.setText("");
        changeLast();
    }

    private EditText getEditText(int i, String str) {
        EditText editText = new EditText(getContext());
        editText.setGravity(17);
        editText.setPadding(0, 0, 0, 0);
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        editText.setTextColor(getContext().getResources().getColor(R.color.color_ff205f));
        editText.setInputType(2);
        editText.setCursorVisible(false);
        editText.setTextSize(18.0f);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment.1
        }});
        if (Build.VERSION.SDK_INT >= 21) {
            editText.setShowSoftInputOnFocus(false);
        } else {
            getActivity().getWindow().setSoftInputMode(3);
            try {
                Method method = EditText.class.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        editText.setOnFocusChangeListener(this.myOnfocusChanged);
        if (TextUtils.isEmpty(str)) {
            editText.setHint("");
        } else {
            editText.setText(str);
        }
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyOnfocusChanged implements View.OnFocusChangeListener {
        private MyOnfocusChanged() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                if (IgnitionDoorSearchFragment.this.currentEdit != null) {
                    IgnitionDoorSearchFragment.this.currentEdit.setTextColor(IgnitionDoorSearchFragment.this.getContext().getResources().getColor(R.color.color_ff205f));
                }
                IgnitionDoorSearchFragment.this.currentEdit = (EditText) view;
                IgnitionDoorSearchFragment.this.currentEdit.setTextColor(-1);
            }
        }
    }

    private void inputNumb(String str) {
        this.currentEdit.setText(str);
        changeNext();
    }

    private void changeLast() {
        var r0 = (LinearLayout) this.currentEdit.getParent();
        EditText editText = (EditText) (r0).getChildAt(r0.indexOfChild(this.currentEdit) - 1);
        if (editText != null) {
            editText.requestFocus();
        }
    }

    private void changeNext() {
        EditText editText;
        LinearLayout linearLayout = (LinearLayout) this.currentEdit.getParent();
        int childCount = linearLayout.getChildCount();
        int indexOfChild = linearLayout.indexOfChild(this.currentEdit);
        if (indexOfChild == childCount - 1) {
            editText = (EditText) linearLayout.getChildAt(0);
        } else {
            editText = (EditText) linearLayout.getChildAt(indexOfChild + 1);
        }
        if (editText != null) {
            editText.requestFocus();
        }
    }
}
