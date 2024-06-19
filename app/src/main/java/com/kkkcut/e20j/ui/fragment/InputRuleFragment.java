package com.kkkcut.e20j.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import java.lang.reflect.Method;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class InputRuleFragment extends BaseBackFragment {
    private EditText currentEdit;

    @BindView(R.id.ll_A)
    LinearLayout llA;

    @BindView(R.id.ll_B)
    LinearLayout llB;
    private int textColorDefault = -1;
    private MyOnfocusChanged myOnfocusChanged = new MyOnfocusChanged();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_input_rule;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static InputRuleFragment newInstance(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        InputRuleFragment inputRuleFragment = new InputRuleFragment();
        bundle.putInt("isrule", i);
        bundle.putString("ReadBittingRule", str);
        bundle.putString("toothcode", str2);
        inputRuleFragment.setArguments(bundle);
        return inputRuleFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
    }

    private void initView() {
        int i = getArguments().getInt("isrule", 0);
        "3".equals(getArguments().getString("ReadBittingRule"));
        String replace = getArguments().getString("toothcode").replace(",", "").replace(";", "");
        if (i == 1) {
            String str = String.valueOf(replace.charAt(0)) + String.valueOf(replace.charAt(2)) + String.valueOf(replace.charAt(5)) + String.valueOf(replace.charAt(7));
            String str2 = getStringAt(replace, 6, 1) + getStringAt(replace, 6, 3) + getStringAt(replace, 6, 4) + getStringAt(replace, 6, 6);
            for (int i2 = 0; i2 < 4; i2++) {
                EditText editText = getEditText(String.valueOf(str.charAt(i2)));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
                layoutParams.setMargins(10, 0, 0, 0);
                this.llA.addView(editText, layoutParams);
            }
            for (int i3 = 0; i3 < 4; i3++) {
                EditText editText2 = getEditText(String.valueOf(str2.charAt(i3)));
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(50, 50);
                layoutParams2.setMargins(10, 0, 0, 0);
                this.llB.addView(editText2, layoutParams2);
            }
        } else if (i == 3) {
            String str3 = String.valueOf(replace.charAt(4)) + String.valueOf(replace.charAt(5)) + String.valueOf(replace.charAt(7));
            String str4 = getStringAt(replace, 6, 3) + getStringAt(replace, 6, 6) + getStringAt(replace, 6, 8);
            for (int i4 = 0; i4 < 3; i4++) {
                EditText editText3 = getEditText(String.valueOf(str3.charAt(i4)));
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(50, 50);
                layoutParams3.setMargins(10, 0, 0, 0);
                this.llA.addView(editText3, layoutParams3);
            }
            for (int i5 = 0; i5 < 3; i5++) {
                EditText editText4 = getEditText(String.valueOf(str4.charAt(i5)));
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(50, 50);
                layoutParams4.setMargins(10, 0, 0, 0);
                this.llB.addView(editText4, layoutParams4);
            }
        } else if (i == 4) {
            String str5 = getStringAt(replace, 5, 1) + getStringAt(replace, 5, 2);
            String valueOf = String.valueOf(replace.charAt(0));
            for (int i6 = 0; i6 < 2; i6++) {
                EditText editText5 = getEditText(String.valueOf(str5.charAt(i6)));
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(50, 50);
                layoutParams5.setMargins(10, 0, 0, 0);
                this.llA.addView(editText5, layoutParams5);
            }
            for (int i7 = 0; i7 < 1; i7++) {
                EditText editText6 = getEditText(String.valueOf(valueOf.charAt(i7)));
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(50, 50);
                layoutParams6.setMargins(10, 0, 0, 0);
                this.llB.addView(editText6, layoutParams6);
            }
        } else if (i == 5) {
            String str6 = getStringAt(replace, 6, 4) + getStringAt(replace, 6, 6) + getStringAt(replace, 6, 9);
            String str7 = String.valueOf(replace.charAt(5)) + String.valueOf(replace.charAt(7)) + String.valueOf(replace.charAt(8));
            for (int i8 = 0; i8 < 3; i8++) {
                EditText editText7 = getEditText(String.valueOf(str6.charAt(i8)));
                LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(50, 50);
                layoutParams7.setMargins(10, 0, 0, 0);
                this.llA.addView(editText7, layoutParams7);
            }
            for (int i9 = 0; i9 < 3; i9++) {
                EditText editText8 = getEditText(String.valueOf(str7.charAt(i9)));
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(50, 50);
                layoutParams8.setMargins(10, 0, 0, 0);
                this.llB.addView(editText8, layoutParams8);
            }
        } else if (i == 6) {
            String str8 = String.valueOf(replace.charAt(2)) + String.valueOf(replace.charAt(3));
            String str9 = getStringAt(replace, 5, 0) + getStringAt(replace, 5, 1);
            for (int i10 = 0; i10 < 2; i10++) {
                EditText editText9 = getEditText(String.valueOf(str8.charAt(i10)));
                LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(50, 50);
                layoutParams9.setMargins(10, 0, 0, 0);
                this.llA.addView(editText9, layoutParams9);
            }
            for (int i11 = 0; i11 < 2; i11++) {
                EditText editText10 = getEditText(String.valueOf(str9.charAt(i11)));
                LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(50, 50);
                layoutParams10.setMargins(10, 0, 0, 0);
                this.llB.addView(editText10, layoutParams10);
            }
        }
        this.llA.setTag("A");
        this.llB.setTag("B");
        ((EditText) this.llA.getChildAt(0)).requestFocus();
    }

    private EditText getEditText(String str) {
        EditText editText = new EditText(getContext());
        editText.setGravity(17);
        editText.setPadding(0, 0, 0, 0);
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        editText.setTextColor(-1);
        editText.setInputType(2);
        editText.setCursorVisible(false);
        editText.setTextSize(18.0f);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1) { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment.1
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
        editText.setText(String.valueOf(str));
        return editText;
    }

    private String getStringAt(String str, int i, int i2) {
        return str.charAt(i2) == '?' ? "?" : String.valueOf(i - Integer.parseInt(String.valueOf(str.charAt(i2))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyOnfocusChanged implements View.OnFocusChangeListener {
        private MyOnfocusChanged() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                InputRuleFragment.this.currentEdit = (EditText) view;
                Log.i(InputRuleFragment.TAG, "onFocusChange: " + InputRuleFragment.this.currentEdit);
            }
        }
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tvX, R.id.btn_ok, R.id.btn_cancel})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            onBack();
            return;
        }
        if (id != R.id.btn_ok) {
            if (id != R.id.tvX) {
                switch (id) {
                    case R.id.tv1 /* 2131362868 */:
                    case R.id.tv2 /* 2131362869 */:
                    case R.id.tv3 /* 2131362870 */:
                    case R.id.tv4 /* 2131362871 */:
                    case R.id.tv5 /* 2131362872 */:
                        break;
                    default:
                        return;
                }
            }
            inputNumb(((Button) view).getText().toString());
            return;
        }
        String str = "";
        for (int i = 0; i < this.llA.getChildCount(); i++) {
            String trim = ((EditText) this.llA.getChildAt(i)).getText().toString().trim();
            if (TextUtils.isEmpty(trim) || trim.equals("?")) {
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
        }
        for (int i2 = 0; i2 < this.llB.getChildCount(); i2++) {
            String trim2 = ((EditText) this.llB.getChildAt(i2)).getText().toString().trim();
            if (TextUtils.isEmpty(trim2) || trim2.equals("?")) {
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
        }
        int i3 = getArguments().getInt("isrule", 0);
        if (i3 == 1) {
            str = ((("" + getToothFromContainer(this.llA, 0) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 0)))) + "," + getToothFromContainer(this.llA, 1) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 1)))) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 2))) + "," + getToothFromContainer(this.llA, 2)) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 3))) + "," + getToothFromContainer(this.llA, 3) + ";";
        } else if (i3 == 3) {
            str = (("2,2,2," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 0))) + "," + getToothFromContainer(this.llA, 0)) + "," + getToothFromContainer(this.llA, 1) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 1)))) + "," + getToothFromContainer(this.llA, 2) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llB, 2))) + ";";
        } else if (i3 == 4) {
            str = ("" + getToothFromContainer(this.llB, 0) + "," + (5 - Integer.parseInt(getToothFromContainer(this.llA, 0)))) + "," + (5 - Integer.parseInt(getToothFromContainer(this.llA, 1))) + ",1,1,1,1,1,1;";
        } else if (i3 == 5) {
            str = (("2,2,2,2," + (6 - Integer.parseInt(getToothFromContainer(this.llA, 0))) + "," + getToothFromContainer(this.llB, 0)) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llA, 1))) + "," + getToothFromContainer(this.llB, 1)) + "," + getToothFromContainer(this.llB, 2) + "," + (6 - Integer.parseInt(getToothFromContainer(this.llA, 2))) + ";";
        } else if (i3 == 6) {
            str = ("" + (5 - Integer.parseInt(getToothFromContainer(this.llB, 0))) + "," + (5 - Integer.parseInt(getToothFromContainer(this.llB, 1)))) + "," + getToothFromContainer(this.llA, 0) + "," + getToothFromContainer(this.llA, 1) + ",1,1,1,1,1,1;";
        }
        EventBus.getDefault().post(new EventCenter(15, str));
        onBack();
    }

    private String getToothFromContainer(LinearLayout linearLayout, int i) {
        return ((EditText) linearLayout.getChildAt(i)).getText().toString().trim();
    }

    private void inputNumb(String str) {
        EditText editText = this.currentEdit;
        if (editText != null) {
            editText.setText(str);
            LinearLayout linearLayout = (LinearLayout) this.currentEdit.getParent();
            int indexOfChild = linearLayout.indexOfChild(this.currentEdit);
            if (indexOfChild < linearLayout.getChildCount() - 1) {
                this.currentEdit = (EditText) linearLayout.getChildAt(indexOfChild + 1);
            } else {
                String str2 = (String) linearLayout.getTag();
                if (!TextUtils.isEmpty(str2)) {
                    if (str2.equals("A")) {
                        this.currentEdit = (EditText) this.llB.getChildAt(0);
                    }
                    if (str2.equals("B")) {
                        this.currentEdit = (EditText) this.llA.getChildAt(0);
                    }
                }
            }
            this.currentEdit.requestFocus();
        }
    }
}
