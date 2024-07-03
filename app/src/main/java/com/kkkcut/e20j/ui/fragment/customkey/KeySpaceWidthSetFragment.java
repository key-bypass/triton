package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.kkkcut.e20j.utils.ThemeUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class KeySpaceWidthSetFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";
    private EditText currentEdit;
    private CustomKey customKey;

    FrameLayout flRowTool;
    boolean isDimple;

    ImageView ivSpaceWidth;

    LinearLayout llIndex;

    LinearLayout llSpace;

    LinearLayout llSpaceTool;
    private MyOnfocusChanged myOnfocusChanged = new MyOnfocusChanged();

    TextView tvSideRow;

    TextView tvUnit;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_spacewidth_set;
    }

    public static KeySpaceWidthSetFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeySpaceWidthSetFragment keySpaceWidthSetFragment = new KeySpaceWidthSetFragment();
        keySpaceWidthSetFragment.setArguments(bundle);
        return keySpaceWidthSetFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.space_width);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        EditText editText;
        TextView text;
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        switch (customKey.getType()) {
            case 0:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.doublekey_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.doublekey_tip_space_width);
                    break;
                }
            case 1:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.singlekey_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.singlekey_tip_space_width);
                    break;
                }
            case 2:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.double_internal_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.double_internal_tip_space_width);
                    break;
                }
            case 3:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.single_external_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.single_external_tip_space_width);
                    break;
                }
            case 4:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.double_external_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.double_external_tip_space_width);
                    break;
                }
            case 5:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpaceWidth.setImageResource(R.drawable.single_internal_shoulder_space_width);
                    break;
                } else {
                    this.ivSpaceWidth.setImageResource(R.drawable.single_internal_tip_space_width);
                    break;
                }
            case 6:
                this.tvSideRow.setText("Rows");
                this.isDimple = true;
                break;
            case 7:
                this.ivSpaceWidth.setImageResource(R.drawable.tibbe_space_width);
                break;
        }
        String param = SpecificParamUtils.getParam(this.customKey.getParameter_info(), SpecificParamUtils.SIDE);
        String space_width = this.customKey.getSpace_width();
        String[] split = !TextUtils.isEmpty(space_width) ? space_width.split(";") : null;
        String[] split2 = this.customKey.getSpace().split(";");
        int length = this.customKey.isAbSame() ? 1 : split2.length;
        int i = 0;
        while (i < length) {
            if (!this.isDimple) {
                if ((this.customKey.getType() == 0 || this.customKey.getType() == 2 || this.customKey.getType() == 4) && this.customKey.isAbSame()) {
                    text = getText("AB", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                } else if (i == 0 && (TextUtils.isEmpty(param) || param.equals("0") || param.equals("3"))) {
                    text = getText("A", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                } else {
                    text = getText("B", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(140, 30);
                layoutParams.setMargins(0, 1, 0, 0);
                this.llIndex.addView(text, layoutParams);
            } else {
                this.llIndex.addView(getText(String.valueOf(i + 1), ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)), new LinearLayout.LayoutParams(140, 30));
            }
            String[] split3 = (split == null || i >= split.length) ? null : split[i].split(",");
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            int i2 = 0;
            while (i2 < split2[i].split(",").length) {
                if (split3 == null) {
                    editText = getEditText(i2, "");
                } else {
                    editText = getEditText(i2, i2 < split3.length ? split3[i2] : "");
                }
                if (this.customKey.getType() != 6 && i == 0 && i2 == 0) {
                    editText.requestFocus();
                }
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(60, 30);
                layoutParams2.setMargins(1, 1, 0, 0);
                linearLayout.addView(editText, layoutParams2);
                i2++;
            }
            this.llSpace.addView(linearLayout);
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            TextView text2 = getText(getString(R.string.auto), R.drawable.bg_auto);
            text2.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
            text2.setTag("auto");
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(90, 30);
            layoutParams3.setMargins(16, 1, 0, 0);
            text2.setOnClickListener(new SpaceToolClickListener(linearLayout));
            linearLayout2.addView(text2, layoutParams3);
            this.llSpaceTool.addView(linearLayout2);
            i++;
        }
        if (this.customKey.isInch()) {
            this.tvUnit.setText(R.string._1inch_1000);
        }
    }

    private EditText getEditText(int i, String str) {
        EditText editText = new EditText(getContext());
        editText.setGravity(17);
        editText.setPadding(0, 0, 0, 0);
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        editText.setTextColor(-1);
        editText.setInputType(2);
        editText.setCursorVisible(false);
        editText.setTextSize(18.0f);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceWidthSetFragment.1
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
            editText.setHint(String.valueOf(i + 1));
        } else if (this.customKey.isInch()) {
            editText.setText(String.valueOf(Math.round(Integer.parseInt(str) / 2.54d)));
        } else {
            editText.setText(str);
        }
        return editText;
    }

    private TextView getText(String str, int i) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setBackgroundResource(i);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setTextSize(0, 18.0f);
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyOnfocusChanged implements View.OnFocusChangeListener {
        private MyOnfocusChanged() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                KeySpaceWidthSetFragment.this.currentEdit = (EditText) view;
                Log.i(KeySpaceWidthSetFragment.TAG, "onFocusChange: " + KeySpaceWidthSetFragment.this.currentEdit);
            }
        }
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_delete) {
            delete();
            return;
        }
        if (id == R.id.bt_last) {
            onBack();
            return;
        }
        if (id != R.id.bt_next) {
            switch (id) {
                case R.id.bt_number_0 /* 2131361949 */:
                case R.id.bt_number_1 /* 2131361950 */:
                case R.id.bt_number_2 /* 2131361951 */:
                case R.id.bt_number_3 /* 2131361952 */:
                case R.id.bt_number_4 /* 2131361953 */:
                case R.id.bt_number_5 /* 2131361954 */:
                case R.id.bt_number_6 /* 2131361955 */:
                case R.id.bt_number_7 /* 2131361956 */:
                case R.id.bt_number_8 /* 2131361957 */:
                case R.id.bt_number_9 /* 2131361958 */:
                    inputNumb(((Button) view).getText().toString().trim());
                    return;
                case R.id.bt_number_last /* 2131361959 */:
                    var r12 = (LinearLayout) this.currentEdit.getParent();
                    EditText editText = (EditText) r12.getChildAt(r12.indexOfChild(this.currentEdit) - 1);
                    if (editText != null) {
                        editText.requestFocus();
                        return;
                    }
                    return;
                case R.id.bt_number_next /* 2131361960 */:
                    Object tag = this.currentEdit.getTag();
                    if (tag != null) {
                        ((EditText) ((LinearLayout) this.llSpace.getChildAt(((Integer) tag).intValue())).getChildAt(0)).requestFocus();
                        return;
                    }
                    LinearLayout linearLayout = (LinearLayout) this.currentEdit.getParent();
                    EditText editText2 = (EditText) linearLayout.getChildAt(linearLayout.indexOfChild(this.currentEdit) + 1);
                    if (editText2 != null) {
                        editText2.requestFocus();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        String str = "";
        if (this.customKey.isAbSame() && this.customKey.getType() != 0) {
            LinearLayout linearLayout2 = (LinearLayout) this.llSpace.getChildAt(0);
            for (int i = 0; i < linearLayout2.getChildCount(); i++) {
                EditText editText3 = (EditText) linearLayout2.getChildAt(i);
                Editable text = editText3.getText();
                if (TextUtils.isEmpty(text)) {
                    editText3.requestFocus();
                    ToastUtil.showToast(R.string.please_complete_the_data);
                    return;
                }
                int parseInt = Integer.parseInt(text.toString());
                if (this.customKey.isInch()) {
                    parseInt = Math.round(parseInt * 2.54f);
                }
                str = i == linearLayout2.getChildCount() - 1 ? str + parseInt + ";" : str + parseInt + ",";
            }
            str = str + str;
        } else {
            for (int i2 = 0; i2 < this.llSpace.getChildCount(); i2++) {
                LinearLayout linearLayout3 = (LinearLayout) this.llSpace.getChildAt(i2);
                for (int i3 = 0; i3 < linearLayout3.getChildCount(); i3++) {
                    EditText editText4 = (EditText) linearLayout3.getChildAt(i3);
                    Editable text2 = editText4.getText();
                    if (TextUtils.isEmpty(text2)) {
                        editText4.requestFocus();
                        ToastUtil.showToast(R.string.please_complete_the_data);
                        return;
                    }
                    int parseInt2 = Integer.parseInt(text2.toString());
                    if (this.customKey.isInch()) {
                        parseInt2 = Math.round(parseInt2 * 2.54f);
                    }
                    str = i3 == linearLayout3.getChildCount() - 1 ? str + parseInt2 + ";" : str + parseInt2 + ",";
                }
            }
        }
        Log.i(TAG, "spaceWidth: " + str);
        this.customKey.setSpace_width(str);
        start(KeyDepthSetFragment.newInstance(this.customKey));
    }

    private void delete() {
        String trim = this.currentEdit.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        this.currentEdit.setText(trim.substring(0, trim.length() - 1));
    }

    private void inputNumb(String str) {
        this.currentEdit.append(str);
    }

    /* loaded from: classes.dex */
    private class SpaceToolClickListener implements View.OnClickListener {
        private LinearLayout parent;

        public SpaceToolClickListener(LinearLayout linearLayout) {
            this.parent = linearLayout;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = (String) view.getTag();
            Log.i(KeySpaceWidthSetFragment.TAG, "onClick: " + str);
            if (TextUtils.isEmpty(str) || !str.equals("auto")) {
                return;
            }
            KeySpaceWidthSetFragment.this.auto(this.parent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auto(LinearLayout linearLayout) {
        String str = "";
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            str = ((EditText) linearLayout.getChildAt(i)).getText().toString().trim();
            if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
                break;
            }
        }
        if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
            for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
                ((EditText) linearLayout.getChildAt(i2)).setText(str);
            }
            return;
        }
        ToastUtil.showToast(R.string.please_enter_a_data_first);
    }
}
