package com.kkkcut.e20j.ui.fragment.customkey;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.kkkcut.e20j.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class KeyDepthSetFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";
    private EditText currentEdit;
    private CustomKey customKey;
    private boolean isDimple;

    @BindView(R.id.iv_space)
    ImageView ivSpace;

    @BindView(R.id.ll_space)
    LinearLayout llDepth;

    @BindView(R.id.ll_depth_name)
    LinearLayout llDepthName;

    @BindView(R.id.ll_space_tool)
    LinearLayout llDepthTool;

    @BindView(R.id.ll_index)
    LinearLayout llIndex;

    @BindView(R.id.ll_side)
    LinearLayout llSide;
    private MyOnfocusChanged myOnfocusChanged = new MyOnfocusChanged();
    private int rowCount;

    @BindView(R.id.tv_row_add)
    ImageView tvRowAdd;

    @BindView(R.id.tv_row_reduce)
    ImageView tvRowReduce;

    @BindView(R.id.tv_side_row)
    TextView tvSideRow;

    @BindView(R.id.tv_unit)
    TextView tvUnit;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_space_depth_set;
    }

    public static KeyDepthSetFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeyDepthSetFragment keyDepthSetFragment = new KeyDepthSetFragment();
        keyDepthSetFragment.setArguments(bundle);
        return keyDepthSetFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.depth);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        EditText depthNameEditText;
        EditText editText;
        TextView text;
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        int i = 1;
        switch (customKey.getType()) {
            case 0:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.doublekey_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.doublekey_depth_tip);
                    break;
                }
            case 1:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.singlekey_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.singlekey_depth_tip);
                    break;
                }
            case 2:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.double_internal_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.double_internal_depth_tip);
                    break;
                }
            case 3:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.single_external_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.single_external_depth_tip);
                    break;
                }
            case 4:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.double_external_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.double_external_depth_tip);
                    break;
                }
            case 5:
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.single_internal_depth_shoulder);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.single_internal_depth_tip);
                    break;
                }
            case 6:
                this.ivSpace.setImageResource(R.drawable.dimple_depth);
                this.tvSideRow.setText("Rows");
                this.isDimple = true;
                break;
            case 7:
                this.ivSpace.setImageResource(R.drawable.tibbe_depth);
                break;
            case 8:
                this.ivSpace.setImageResource(R.drawable.tubular_depth);
                break;
        }
        String param = SpecificParamUtils.getParam(this.customKey.getParameter_info(), SpecificParamUtils.SIDE);
        String[] split = this.customKey.getSpace().split(";");
        String depth = this.customKey.getDepth();
        String[] split2 = !TextUtils.isEmpty(depth) ? depth.split(";") : null;
        int length = this.customKey.isAbSame() ? 1 : split.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = 60;
            if (i3 < length) {
                if (!this.isDimple) {
                    if ((this.customKey.getType() == 0 || this.customKey.getType() == 2 || this.customKey.getType() == 4) && this.customKey.isAbSame()) {
                        text = getText("AB", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                    } else if (i3 == 0 && (TextUtils.isEmpty(param) || param.equals("0") || param.equals("3"))) {
                        text = getText("A", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                    } else {
                        text = getText("B", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 30);
                    layoutParams.setMargins(i2, i, i2, i2);
                    this.llIndex.addView(text, layoutParams);
                } else {
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(70, 30);
                    layoutParams2.setMargins(i2, i, i2, i2);
                    this.llIndex.addView(getText(String.valueOf(i3 + 1), ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)), layoutParams2);
                }
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(i2);
                String[] split3 = (split2 == null || i3 >= split2.length) ? null : split2[i3].split(",");
                int length2 = split3 != null ? split3.length : 5;
                int i5 = 0;
                while (i5 < length2) {
                    if (split3 == null) {
                        editText = getEditText(i5, "");
                    } else {
                        String str = split3[i5];
                        if (this.customKey.isInch()) {
                            str = String.valueOf(Math.round(Integer.parseInt(split3[i5]) / 2.54f));
                        }
                        editText = getEditText(i5, str);
                    }
                    if (this.customKey.getType() != 6 && i3 == 0 && i5 == 0) {
                        editText.requestFocus();
                    }
                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i4, 30);
                    layoutParams3.setMargins(1, 1, 0, 0);
                    linearLayout.addView(editText, layoutParams3);
                    i5++;
                    i4 = 60;
                }
                this.llDepth.addView(linearLayout);
                LinearLayout linearLayout2 = new LinearLayout(getContext());
                ImageView imageView = getImageView(R.drawable.space_reduce);
                imageView.setTag("-");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(36, 30);
                layoutParams4.setMargins(10, 1, 0, 0);
                imageView.setOnClickListener(new SpaceToolClickListener(linearLayout));
                linearLayout2.addView(imageView, layoutParams4);
                ImageView imageView2 = getImageView(R.drawable.space_add);
                imageView2.setTag("+");
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(36, 30);
                layoutParams5.setMargins(10, 1, 0, 0);
                imageView2.setOnClickListener(new SpaceToolClickListener(linearLayout));
                linearLayout2.addView(imageView2, layoutParams5);
                TextView text2 = getText(getString(R.string.auto), R.drawable.bg_auto);
                text2.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
                text2.setTag("auto");
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(90, 30);
                layoutParams6.setMargins(16, 1, 0, 0);
                text2.setOnClickListener(new SpaceToolClickListener(linearLayout));
                linearLayout2.addView(text2, layoutParams6);
                this.llDepthTool.addView(linearLayout2);
                i3++;
                i = 1;
                i2 = 0;
            } else {
                String depth_name = this.customKey.getDepth_name();
                String[] split4 = !TextUtils.isEmpty(depth_name) ? depth_name.split(";")[0].split(",") : null;
                for (int i6 = 0; i6 < 12; i6++) {
                    if (split4 != null && i6 < split4.length) {
                        depthNameEditText = getDepthNameEditText(i6, split4[i6]);
                    } else {
                        depthNameEditText = getDepthNameEditText(i6, "");
                    }
                    LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(60, 30);
                    layoutParams7.setMargins(1, 0, 0, 0);
                    this.llDepthName.addView(depthNameEditText, layoutParams7);
                }
                this.llDepthName.addView(new Space(getContext()), new LinearLayout.LayoutParams(0, 0, 1.0f));
                TextView text3 = getText(getString(R.string.auto), R.drawable.bg_auto);
                text3.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(90, 30);
                layoutParams8.setMargins(16, 0, 0, 0);
                text3.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        KeyDepthSetFragment.this.autoDepthName();
                    }
                });
                this.llDepthName.addView(text3, layoutParams8);
                if (this.customKey.isInch()) {
                    this.tvUnit.setText(R.string._1inch_1000);
                    return;
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoDepthName() {
        int i = 0;
        String trim = ((EditText) this.llDepthName.getChildAt(0)).getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        char c = trim.toCharArray()[0];
        if (c > '@' && c < '[') {
            int childCount = this.llDepthName.getChildCount();
            while (i < childCount - 2) {
                EditText editText = (EditText) this.llDepthName.getChildAt(i);
                char c2 = (char) (c + i);
                if (c2 > 'Z') {
                    c2 = (char) (c2 - 26);
                }
                editText.setText(String.valueOf(c2));
                i++;
            }
            return;
        }
        if (c > '`' && c < '{') {
            int childCount2 = this.llDepthName.getChildCount();
            while (i < childCount2 - 2) {
                EditText editText2 = (EditText) this.llDepthName.getChildAt(i);
                char c3 = (char) (c + i);
                if (c3 > 'z') {
                    c3 = (char) (c3 - 26);
                }
                editText2.setText(String.valueOf(c3));
                i++;
            }
            return;
        }
        if (c <= '/' || c >= ':') {
            return;
        }
        int childCount3 = this.llDepthName.getChildCount();
        while (i < childCount3 - 2) {
            EditText editText3 = (EditText) this.llDepthName.getChildAt(i);
            char c4 = (char) (c + i);
            if (c4 > '9') {
                c4 = (char) (c4 + 7);
            }
            editText3.setText(String.valueOf(c4));
            i++;
        }
    }

    @OnClick({R.id.bt_number_1, R.id.bt_number_2, R.id.bt_number_3, R.id.bt_delete, R.id.bt_number_4, R.id.bt_number_5, R.id.bt_number_6, R.id.bt_number_next, R.id.bt_number_7, R.id.bt_number_8, R.id.bt_number_9, R.id.bt_number_last, R.id.bt_number_0, R.id.bt_next, R.id.bt_last, R.id.tv_row_reduce, R.id.tv_row_add})
    public void onViewClicked(View view) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
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
                    changeLast();
                    return;
                case R.id.bt_number_next /* 2131361960 */:
                    changeNext();
                    return;
                default:
                    return;
            }
        }
        boolean isAbSame = this.customKey.isAbSame();
        int i = 10;
        int i2 = R.string.cannot_be_negative;
        String str6 = "";
        if (isAbSame && this.customKey.getType() != 0) {
            LinearLayout linearLayout = (LinearLayout) this.llDepth.getChildAt(0);
            String str7 = "";
            int i3 = 0;
            while (i3 < linearLayout.getChildCount()) {
                EditText editText = (EditText) linearLayout.getChildAt(i3);
                String obj = editText.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    editText.requestFocus();
                    ToastUtil.showToast(R.string.please_complete_the_data);
                    return;
                }
                if (obj.contains("-")) {
                    editText.requestFocus();
                    ToastUtil.showToast(R.string.cannot_be_negative);
                    return;
                }
                String trim = ((EditText) this.llDepthName.getChildAt(i3)).getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    int i4 = i3 + 1;
                    if (i4 == i) {
                        trim = "A";
                    } else if (i4 == 11) {
                        trim = "B";
                    } else {
                        trim = i4 == 12 ? "C" : String.valueOf(i4);
                    }
                }
                if (this.customKey.isInch()) {
                    obj = String.valueOf(Math.round(Integer.parseInt(obj) * 2.54f));
                }
                if (i3 == linearLayout.getChildCount() - 1) {
                    str4 = str6 + obj + ";";
                    str5 = str7 + trim + ";";
                } else {
                    str4 = str6 + obj + ",";
                    str5 = str7 + trim + ",";
                }
                str7 = str5;
                str6 = str4;
                i3++;
                i = 10;
            }
            str2 = str6 + str6;
            str = str7 + str7;
        } else {
            String str8 = "";
            int i5 = 0;
            while (i5 < this.llDepth.getChildCount()) {
                LinearLayout linearLayout2 = (LinearLayout) this.llDepth.getChildAt(i5);
                int i6 = 0;
                while (i6 < linearLayout2.getChildCount()) {
                    EditText editText2 = (EditText) linearLayout2.getChildAt(i6);
                    String obj2 = editText2.getText().toString();
                    if (TextUtils.isEmpty(obj2)) {
                        editText2.requestFocus();
                        ToastUtil.showToast(R.string.please_complete_the_data);
                        return;
                    }
                    if (obj2.contains("-")) {
                        editText2.requestFocus();
                        ToastUtil.showToast(i2);
                        return;
                    }
                    String trim2 = ((EditText) this.llDepthName.getChildAt(i6)).getText().toString().trim();
                    if (TextUtils.isEmpty(trim2)) {
                        int i7 = i6 + 1;
                        if (i7 == 10) {
                            trim2 = "A";
                        } else if (i7 == 11) {
                            trim2 = "B";
                        } else {
                            trim2 = i7 == 12 ? "C" : String.valueOf(i7);
                        }
                    }
                    if (this.customKey.isInch()) {
                        obj2 = String.valueOf(Math.round(Integer.parseInt(obj2) * 2.54f));
                    }
                    if (i6 == linearLayout2.getChildCount() - 1) {
                        str3 = str6 + obj2 + ";";
                        str8 = str8 + trim2 + ";";
                    } else {
                        str3 = str6 + obj2 + ",";
                        str8 = str8 + trim2 + ",";
                    }
                    str6 = str3;
                    i6++;
                    i2 = R.string.cannot_be_negative;
                }
                i5++;
                i2 = R.string.cannot_be_negative;
            }
            str = str8;
            str2 = str6;
        }
        Log.i(TAG, "depth: " + str2);
        this.customKey.setDepth(str2);
        Log.i(TAG, "depthName: " + str);
        this.customKey.setDepth_name(str);
        start(KeyShapeSetFragment.newInstance(this.customKey));
    }

    private void changeLast() {
        EditText editText = (EditText) ((LinearLayout) this.currentEdit.getParent()).getChildAt(r0.indexOfChild(this.currentEdit) - 1);
        if (editText != null) {
            editText.requestFocus();
        }
    }

    private void changeNext() {
        Object tag = this.currentEdit.getTag();
        if (tag != null) {
            ((EditText) ((LinearLayout) this.llDepth.getChildAt(((Integer) tag).intValue())).getChildAt(0)).requestFocus();
            return;
        }
        LinearLayout linearLayout = (LinearLayout) this.currentEdit.getParent();
        EditText editText = (EditText) linearLayout.getChildAt(linearLayout.indexOfChild(this.currentEdit) + 1);
        if (editText != null) {
            editText.requestFocus();
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
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.2
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
        } else {
            editText.setText(str);
        }
        return editText;
    }

    private EditText getDepthNameEditText(int i, String str) {
        EditText editText = new EditText(getContext());
        editText.setGravity(17);
        editText.setPadding(0, 0, 0, 0);
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        editText.setTextColor(-1);
        editText.setCursorVisible(true);
        editText.setTextSize(18.0f);
        editText.setInputType(4096);
        editText.setFilters(new InputFilter[]{new AdnNameLengthFilter(editText) { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.3
        }});
        editText.setOnFocusChangeListener(this.myOnfocusChanged);
        if (TextUtils.isEmpty(str)) {
            editText.setHint(String.valueOf(i + 1));
        } else {
            editText.setText(str);
        }
        return editText;
    }

    /* loaded from: classes.dex */
    public static class AdnNameLengthFilter implements InputFilter {
        private final EditText editText;

        public AdnNameLengthFilter(EditText editText) {
            this.editText = editText;
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            Log.d(KeyDepthSetFragment.TAG, "filter() called with: source = [" + ((Object) charSequence) + "], start = [" + i + "], end = [" + i2 + "], dest = [" + ((Object) spanned) + "], dstart = [" + i3 + "], dend = [" + i4 + "]");
            if (spanned.length() == 0) {
                return charSequence;
            }
            this.editText.setText(charSequence);
            return null;
        }
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

    private ImageView getImageView(int i) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(i);
        return imageView;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyOnfocusChanged implements View.OnFocusChangeListener {
        private MyOnfocusChanged() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                KeyDepthSetFragment.this.currentEdit = (EditText) view;
            }
        }
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
            Log.i(KeyDepthSetFragment.TAG, "onClick: " + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.equals("-")) {
                KeyDepthSetFragment.this.reduce(this.parent);
            } else if (str.equals("+")) {
                KeyDepthSetFragment.this.add(this.parent);
            } else if (str.equals("auto")) {
                KeyDepthSetFragment.this.auto(this.parent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reduce(LinearLayout linearLayout) {
        int childCount = linearLayout.getChildCount();
        if (childCount > 0) {
            linearLayout.removeViewAt(childCount - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void add(LinearLayout linearLayout) {
        int childCount = linearLayout.getChildCount();
        if (childCount == 12) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 30);
        layoutParams.setMargins(1, 1, 0, 0);
        linearLayout.addView(getEditText(childCount, ""), layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auto(LinearLayout linearLayout) {
        ArrayList arrayList = new ArrayList();
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            String trim = ((EditText) linearLayout.getChildAt(i)).getText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(new BitIndex(i, Integer.parseInt(trim)));
                if (arrayList.size() == 2) {
                    int space = (((BitIndex) arrayList.get(0)).getSpace() - ((BitIndex) arrayList.get(1)).getSpace()) / (((BitIndex) arrayList.get(0)).getIndex() - ((BitIndex) arrayList.get(1)).getIndex());
                    for (int i2 = 0; i2 < childCount; i2++) {
                        ((EditText) linearLayout.getChildAt(i2)).setText(String.valueOf(((i2 - ((BitIndex) arrayList.get(0)).getIndex()) * space) + ((BitIndex) arrayList.get(0)).getSpace()));
                    }
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BitIndex {
        private int index;
        private int space;

        public BitIndex(int i, int i2) {
            this.index = i;
            this.space = i2;
        }

        public int getIndex() {
            return this.index;
        }

        public int getSpace() {
            return this.space;
        }
    }
}
