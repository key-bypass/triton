package com.kkkcut.e20j.ui.fragment.customkey;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
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
public class KeySpaceSetFragment extends BaseBackFragment {
    private static final String CUSTOMKEY = "CUSTOMKEY";
    private boolean abSame;
    private EditText currentEdit;
    private CustomKey customKey;

    @BindView(R.id.fl_row_tool)
    FrameLayout flRowTool;
    private boolean isDimple;

    @BindView(R.id.iv_space)
    ImageView ivSpace;

    @BindView(R.id.ll_index)
    LinearLayout llIndex;

    @BindView(R.id.ll_side)
    LinearLayout llSide;

    @BindView(R.id.ll_space)
    LinearLayout llSpace;

    @BindView(R.id.ll_space_tool)
    LinearLayout llSpaceTool;
    private MyOnfocusChanged myOnfocusChanged = new MyOnfocusChanged();

    @BindView(R.id.rb_imperial)
    RadioButton rbInch;

    @BindView(R.id.rb_metric)
    RadioButton rbMetric;

    @BindView(R.id.rb_sideA)
    RadioButton rbSideA;

    @BindView(R.id.rb_sideB)
    RadioButton rbSideB;
    private int rowCount;
    private String side;

    @BindView(R.id.tv_row_add)
    ImageView tvRowAdd;

    @BindView(R.id.tv_row_reduce)
    ImageView tvRowReduce;

    @BindView(R.id.tv_rows)
    TextView tvRows;

    @BindView(R.id.tv_side_row)
    TextView tvSideRow;

    @BindView(R.id.tv_unit)
    TextView tvUnit;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyspace_set;
    }

    public static KeySpaceSetFragment newInstance(CustomKey customKey) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CUSTOMKEY, customKey);
        KeySpaceSetFragment keySpaceSetFragment = new KeySpaceSetFragment();
        keySpaceSetFragment.setArguments(bundle);
        return keySpaceSetFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        EditText editText;
        String[] strArr;
        EditText editText2;
        String[] split;
        TextView text;
        CustomKey customKey = (CustomKey) getArguments().getParcelable(CUSTOMKEY);
        this.customKey = customKey;
        String space = customKey.getSpace();
        Object[] split2 = !TextUtils.isEmpty(space) ? space.split(";") : null;
        String row_pos = this.customKey.getRow_pos();
        String[] split3 = !TextUtils.isEmpty(row_pos) ? row_pos.split(";") : null;
        switch (this.customKey.getType()) {
            case 0:
                if (split2 != null && split2.length == 1) {
                    this.abSame = true;
                }
                this.rowCount = 2;
                this.llSide.setVisibility(0);
                this.rbSideA.setText(getString(R.string.ab_diff));
                this.rbSideA.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doublekey_abdiff), (Drawable) null, (Drawable) null);
                this.rbSideB.setText(getString(R.string.ab_same));
                this.rbSideB.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doublekey_absame), (Drawable) null, (Drawable) null);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.doublekey_shoulder_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.doublekey_tip_space);
                    break;
                }
                break;
            case 1:
                this.rowCount = 1;
                this.llSide.setVisibility(8);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.singlekey_shoulder_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.singlekey_tip_space);
                    break;
                }
            case 2:
                if (split2 != null && split2.length == 2 && split2[0].equals(split2[1])) {
                    this.abSame = true;
                }
                this.rowCount = 2;
                this.llSide.setVisibility(0);
                this.rbSideA.setText(getString(R.string.ab_diff));
                this.rbSideA.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doubleinside_abdiff), (Drawable) null, (Drawable) null);
                this.rbSideB.setText(getString(R.string.ab_same));
                this.rbSideB.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doubleinside_absame), (Drawable) null, (Drawable) null);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.double_internal_shoulder_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.double_internal_tip_space);
                    break;
                }
                break;
            case 3:
                this.rowCount = 1;
                this.llSide.setVisibility(0);
                this.rbSideA.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.singleoutside_down), (Drawable) null, (Drawable) null);
                this.rbSideB.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.singleoutside_up), (Drawable) null, (Drawable) null);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.single_external_down_shoulder_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.single_external_down_tip_space);
                    break;
                }
            case 4:
                if (split2 != null && split2.length == 2 && split2[0].equals(split2[1])) {
                    this.abSame = true;
                }
                this.rowCount = 2;
                this.llSide.setVisibility(0);
                this.rbSideA.setText(getString(R.string.ab_diff));
                this.rbSideA.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doubleoutside_abdiff), (Drawable) null, (Drawable) null);
                this.rbSideB.setText(getString(R.string.ab_same));
                this.rbSideB.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.doubleoutside_absame), (Drawable) null, (Drawable) null);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.double_external_shoulder_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.double_external_tip_space);
                    break;
                }
                break;
            case 5:
                this.rowCount = 1;
                this.llSide.setVisibility(0);
                this.rbSideA.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.singleinside_a), (Drawable) null, (Drawable) null);
                this.rbSideB.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.singleinside_b), (Drawable) null, (Drawable) null);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.single_internal_shoulder_a_space);
                    break;
                } else {
                    this.ivSpace.setImageResource(R.drawable.single_internal_tip_a_space);
                    break;
                }
            case 6:
                this.llSide.setVisibility(8);
                if (this.customKey.getAlign() == 0) {
                    this.ivSpace.setImageResource(R.drawable.dimple_shoulder_space);
                } else {
                    this.ivSpace.setImageResource(R.drawable.dimple_tip_space);
                }
                this.tvSideRow.setText("Row Position");
                if (split3 == null) {
                    this.rowCount = 2;
                } else {
                    this.rowCount = split2.length;
                }
                this.isDimple = true;
                this.flRowTool.setVisibility(0);
                this.tvRows.setText(String.valueOf(this.rowCount));
                break;
            case 7:
                this.llSide.setVisibility(8);
                this.rowCount = 2;
                this.ivSpace.setImageResource(R.drawable.tibbe_space);
                break;
            case 8:
                this.rowCount = 1;
                this.llSide.setVisibility(8);
                this.ivSpace.setImageResource(R.drawable.tubular_space);
                this.tvUnit.setText(getString(R.string._1o_100));
                break;
        }
        for (int i = 0; i < this.rowCount; i++) {
            if (!this.isDimple) {
                if (i == 0) {
                    String param = SpecificParamUtils.getParam(this.customKey.getParameter_info(), SpecificParamUtils.SIDE);
                    if (!TextUtils.isEmpty(param) && param.equals("1")) {
                        text = getText("B", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                        this.rbSideB.setChecked(true);
                    } else {
                        text = getText("A", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                    }
                } else {
                    text = getText("B", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 30);
                layoutParams.setMargins(0, 1, 0, 0);
                this.llIndex.addView(text, layoutParams);
            } else {
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(70, 30);
                layoutParams2.setMargins(0, 1, 0, 0);
                if (split3 == null) {
                    editText = getEditText(i, "");
                } else {
                    editText = getEditText(i, split3[i]);
                }
                editText.setTag(Integer.valueOf(i));
                if (i == 0) {
                    editText.requestFocus();
                }
                this.llIndex.addView(editText, layoutParams2);
            }
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            int i2 = 5;
            if (split2 != null) {
                if (this.abSame) {
                    split = split2[0].split(",");
                } else {
                    split = split2[i].split(",");
                }
                strArr = split;
                i2 = split.length;
            } else {
                strArr = null;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (strArr == null) {
                    editText2 = getEditText(i3, "");
                } else {
                    editText2 = getEditText(i3, strArr[i3]);
                }
                if (this.customKey.getType() != 6 && i == 0 && i3 == 0) {
                    editText2.requestFocus();
                }
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(60, 30);
                layoutParams3.setMargins(1, 1, 0, 0);
                linearLayout.addView(editText2, layoutParams3);
            }
            this.llSpace.addView(linearLayout);
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
            text2.setTag("auto");
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(90, 30);
            layoutParams6.setMargins(16, 1, 0, 0);
            text2.setOnClickListener(new SpaceToolClickListener(linearLayout));
            linearLayout2.addView(text2, layoutParams6);
            this.llSpaceTool.addView(linearLayout2);
        }
        if (this.abSame) {
            this.rbSideB.setChecked(true);
        }
        this.customKey.setInch(false);
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
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment.1
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
            editText.setText(String.valueOf(str));
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

    private ImageView getImageView(int i) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(i);
        return imageView;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.space_loacation);
    }

    @OnClick({R.id.bt_number_1, R.id.bt_number_2, R.id.bt_number_3, R.id.bt_delete, R.id.bt_number_4, R.id.bt_number_5, R.id.bt_number_6, R.id.bt_number_next, R.id.bt_number_7, R.id.bt_number_8, R.id.bt_number_9, R.id.bt_number_last, R.id.bt_number_0, R.id.bt_next, R.id.bt_last, R.id.tv_row_reduce, R.id.tv_row_add, R.id.iv_add, R.id.iv_reduce, R.id.tv_auto})
    public void onViewClicked(View view) {
        String str;
        String str2;
        String str3;
        int id = view.getId();
        switch (id) {
            case R.id.bt_delete /* 2131361923 */:
                delete();
                return;
            case R.id.bt_last /* 2131361941 */:
                onBack();
                return;
            case R.id.bt_next /* 2131361947 */:
                int type = this.customKey.getType();
                int i = R.string.please_complete_the_data;
                if (type == 6) {
                    String str4 = "";
                    for (int i2 = 0; i2 < this.llIndex.getChildCount(); i2++) {
                        EditText editText = (EditText) this.llIndex.getChildAt(i2);
                        String trim = editText.getText().toString().trim();
                        if (TextUtils.isEmpty(trim)) {
                            editText.requestFocus();
                            ToastUtil.showToast(R.string.please_complete_the_data);
                            hideSoftInput();
                            return;
                        } else {
                            if (trim.contains("-")) {
                                editText.requestFocus();
                                ToastUtil.showToast(R.string.cannot_be_negative);
                                return;
                            }
                            int parseInt = Integer.parseInt(trim);
                            if (this.customKey.isInch()) {
                                parseInt = Math.round(parseInt * 2.54f);
                            }
                            str4 = str4 + parseInt + ";";
                        }
                    }
                    this.customKey.setRow_pos(str4);
                    Log.i(TAG, "row_position: " + str4);
                }
                if (this.abSame) {
                    LinearLayout linearLayout = (LinearLayout) this.llSpace.getChildAt(0);
                    str = "";
                    for (int i3 = 0; i3 < linearLayout.getChildCount(); i3++) {
                        EditText editText2 = (EditText) linearLayout.getChildAt(i3);
                        Editable text = editText2.getText();
                        if (TextUtils.isEmpty(text)) {
                            editText2.requestFocus();
                            ToastUtil.showToast(R.string.please_complete_the_data);
                            return;
                        }
                        int parseInt2 = Integer.parseInt(text.toString());
                        if (this.customKey.isInch()) {
                            parseInt2 = Math.round(parseInt2 * 2.54f);
                        }
                        str = i3 == linearLayout.getChildCount() - 1 ? str + parseInt2 + ";" : str + parseInt2 + ",";
                    }
                    if (this.customKey.getType() == 4 || this.customKey.getType() == 2) {
                        str = str + str;
                    }
                } else {
                    str = "";
                    int i4 = 0;
                    while (i4 < this.llSpace.getChildCount()) {
                        LinearLayout linearLayout2 = (LinearLayout) this.llSpace.getChildAt(i4);
                        int i5 = 0;
                        while (i5 < linearLayout2.getChildCount()) {
                            EditText editText3 = (EditText) linearLayout2.getChildAt(i5);
                            Editable text2 = editText3.getText();
                            if (TextUtils.isEmpty(text2)) {
                                editText3.requestFocus();
                                ToastUtil.showToast(i);
                                return;
                            }
                            int parseInt3 = Integer.parseInt(text2.toString());
                            if (this.customKey.isInch()) {
                                parseInt3 = Math.round(parseInt3 * 2.54f);
                            }
                            str = i5 == linearLayout2.getChildCount() - 1 ? str + parseInt3 + ";" : str + parseInt3 + ",";
                            i5++;
                            i = R.string.please_complete_the_data;
                        }
                        i4++;
                        i = R.string.please_complete_the_data;
                    }
                }
                Log.i(TAG, "space: " + str);
                this.customKey.setSpace(str);
                if (this.customKey.getType() == 6) {
                    String[] split = this.customKey.getRow_pos().split(";");
                    String[] split2 = str.split(";");
                    String str5 = "";
                    int i6 = 0;
                    while (i6 < split2.length) {
                        if (i6 != split2.length - 1) {
                            int i7 = i6 + 1;
                            if (split2[i6].equals(split2[i7]) && split[i6].equals(split[i7])) {
                                int length = split2[i6].split(",").length;
                                String str6 = "";
                                String str7 = str6;
                                for (int i8 = 0; i8 < length; i8++) {
                                    if (i8 == length - 1) {
                                        str2 = str6 + "-200;";
                                        str3 = str7 + "0;";
                                    } else {
                                        str2 = str6 + "-200,";
                                        str3 = str7 + "0,";
                                    }
                                    str7 = str3;
                                    str6 = str2;
                                }
                                str5 = (str5 + str6) + str7;
                                this.customKey.setSpace_width(str5);
                                i6 = i7;
                            }
                        }
                        i6++;
                    }
                }
                String parameter_info = this.customKey.getParameter_info();
                if (this.customKey.getType() == 0) {
                    if (!this.abSame) {
                        parameter_info = SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.SIDE, "3");
                    } else {
                        parameter_info = SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.SIDE, "");
                    }
                }
                if (this.customKey.getType() == 5 || this.customKey.getType() == 3) {
                    parameter_info = SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.SIDE, this.side);
                }
                Log.i(TAG, "parameter_info: " + parameter_info);
                this.customKey.setParameter_info(parameter_info);
                this.customKey.setAbSame(this.abSame);
                if (this.customKey.getType() == 8) {
                    start(KeyDepthSetFragment.newInstance(this.customKey));
                    return;
                }
                if (this.customKey.getType() == 6) {
                    String space_width = this.customKey.getSpace_width();
                    if (TextUtils.isEmpty(space_width) || !space_width.contains("-")) {
                        start(KeySpaceWidthSetFragment.newInstance(this.customKey));
                        return;
                    } else {
                        start(KeyDepthSetFragment.newInstance(this.customKey));
                        return;
                    }
                }
                start(KeySpaceWidthSetFragment.newInstance(this.customKey));
                return;
            case R.id.iv_add /* 2131362277 */:
                EditText editText4 = this.currentEdit;
                if (editText4 != null) {
                    addSpace((LinearLayout) editText4.getParent());
                    return;
                }
                return;
            case R.id.iv_reduce /* 2131362326 */:
                EditText editText5 = this.currentEdit;
                if (editText5 != null) {
                    reduceSpace((LinearLayout) editText5.getParent());
                    return;
                }
                return;
            case R.id.tv_auto /* 2131362886 */:
                EditText editText6 = this.currentEdit;
                if (editText6 != null) {
                    autoSpace((LinearLayout) editText6.getParent());
                    return;
                }
                return;
            case R.id.tv_row_add /* 2131362985 */:
                int childCount = this.llIndex.getChildCount();
                if (childCount > 3) {
                    return;
                }
                this.tvRows.setText(String.valueOf(childCount + 1));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 30);
                layoutParams.setMargins(0, 1, 0, 0);
                EditText editText7 = getEditText(childCount, "");
                editText7.setTag(Integer.valueOf(childCount));
                this.llIndex.addView(editText7, layoutParams);
                LinearLayout linearLayout3 = new LinearLayout(getContext());
                linearLayout3.setOrientation(0);
                for (int i9 = 0; i9 < 5; i9++) {
                    EditText editText8 = getEditText(i9, "");
                    if (this.customKey.getType() == 6 && childCount == 0 && i9 == 0) {
                        editText8.requestFocus();
                    }
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(60, 30);
                    layoutParams2.setMargins(1, 1, 0, 0);
                    linearLayout3.addView(editText8, layoutParams2);
                }
                this.llSpace.addView(linearLayout3);
                LinearLayout linearLayout4 = new LinearLayout(getContext());
                ImageView imageView = getImageView(R.drawable.space_reduce);
                imageView.setTag("-");
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(36, 30);
                layoutParams3.setMargins(10, 1, 0, 0);
                imageView.setOnClickListener(new SpaceToolClickListener(linearLayout3));
                linearLayout4.addView(imageView, layoutParams3);
                ImageView imageView2 = getImageView(R.drawable.space_add);
                imageView2.setTag("+");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(36, 30);
                layoutParams4.setMargins(10, 1, 0, 0);
                imageView2.setOnClickListener(new SpaceToolClickListener(linearLayout3));
                linearLayout4.addView(imageView2, layoutParams4);
                TextView text3 = getText(getString(R.string.auto), R.drawable.bg_auto);
                text3.setTag("auto");
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(90, 30);
                layoutParams5.setMargins(16, 1, 0, 0);
                text3.setOnClickListener(new SpaceToolClickListener(linearLayout3));
                linearLayout4.addView(text3, layoutParams5);
                this.llSpaceTool.addView(linearLayout4);
                return;
            case R.id.tv_row_reduce /* 2131362987 */:
                int childCount2 = this.llIndex.getChildCount();
                if (childCount2 > 1) {
                    int i10 = childCount2 - 1;
                    this.tvRows.setText(String.valueOf(i10));
                    this.llIndex.removeViewAt(i10);
                    this.llSpace.removeViewAt(i10);
                    this.llSpaceTool.removeViewAt(i10);
                    return;
                }
                return;
            default:
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
                        LinearLayout linearLayout5 = (LinearLayout) this.currentEdit.getParent();
                        EditText editText9 = (EditText) linearLayout5.getChildAt(linearLayout5.indexOfChild(this.currentEdit) - 1);
                        if (editText9 != null) {
                            editText9.requestFocus();
                            return;
                        }
                        return;
                    case R.id.bt_number_next /* 2131361960 */:
                        Object tag = this.currentEdit.getTag();
                        if (tag != null) {
                            ((EditText) ((LinearLayout) this.llSpace.getChildAt(((Integer) tag).intValue())).getChildAt(0)).requestFocus();
                            return;
                        }
                        LinearLayout linearLayout6 = (LinearLayout) this.currentEdit.getParent();
                        EditText editText10 = (EditText) linearLayout6.getChildAt(linearLayout6.indexOfChild(this.currentEdit) + 1);
                        if (editText10 != null) {
                            editText10.requestFocus();
                            return;
                        }
                        return;
                    default:
                        return;
                }
        }
    }

    private void autoSpace(LinearLayout linearLayout) {
        ArrayList arrayList = new ArrayList();
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            String trim = ((EditText) linearLayout.getChildAt(i)).getText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(new BitIndex(i, Integer.parseInt(trim)));
                if (arrayList.size() == 2) {
                    int space = (((BitIndex) arrayList.get(0)).getSpace() - ((BitIndex) arrayList.get(1)).getSpace()) / (((BitIndex) arrayList.get(0)).getIndex() - ((BitIndex) arrayList.get(1)).getIndex());
                    if (this.customKey.getAlign() != 0 || space >= 0) {
                        if (this.customKey.getAlign() != 1 || space <= 0) {
                            for (int i2 = 0; i2 < childCount; i2++) {
                                ((EditText) linearLayout.getChildAt(i2)).setText(String.valueOf(((i2 - ((BitIndex) arrayList.get(0)).getIndex()) * space) + ((BitIndex) arrayList.get(0)).getSpace()));
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void addSpace(LinearLayout linearLayout) {
        int childCount;
        if (linearLayout == this.llIndex || (childCount = linearLayout.getChildCount()) == 13) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 30);
        layoutParams.setMargins(1, 1, 0, 0);
        linearLayout.addView(getEditText(childCount, ""), layoutParams);
    }

    private void reduceSpace(LinearLayout linearLayout) {
        int childCount;
        if (linearLayout != this.llIndex && (childCount = linearLayout.getChildCount()) >= 2) {
            if (linearLayout.getChildCount() - 1 == linearLayout.indexOfChild(this.currentEdit)) {
                linearLayout.getChildAt(0).requestFocus();
            }
            linearLayout.removeViewAt(childCount - 1);
        }
    }

    @OnCheckedChanged({R.id.rb_sideB, R.id.rb_sideA, R.id.rb_metric, R.id.rb_imperial})
    public void oncheckChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_imperial /* 2131362622 */:
                if (z) {
                    if (this.customKey.getType() != 8) {
                        this.tvUnit.setText(R.string._1inch_1000);
                        mm2inch();
                    }
                    this.customKey.setInch(true);
                    return;
                }
                return;
            case R.id.rb_metric /* 2131362630 */:
                if (z) {
                    if (this.customKey.getType() != 8) {
                        this.tvUnit.setText(R.string._1mm_100);
                        inch2mm();
                    }
                    this.customKey.setInch(false);
                    return;
                }
                return;
            case R.id.rb_sideA /* 2131362646 */:
                if (z) {
                    int type = this.customKey.getType();
                    if (type != 0 && type != 2) {
                        if (type == 3) {
                            this.side = "0";
                            ((TextView) this.llIndex.getChildAt(0)).setText("A");
                            if (this.customKey.getAlign() == 0) {
                                this.ivSpace.setImageResource(R.drawable.single_external_down_shoulder_space);
                                return;
                            } else {
                                this.ivSpace.setImageResource(R.drawable.single_external_down_tip_space);
                                return;
                            }
                        }
                        if (type != 4) {
                            if (type != 5) {
                                return;
                            }
                            this.side = "0";
                            ((TextView) this.llIndex.getChildAt(0)).setText("A");
                            if (this.customKey.getAlign() == 0) {
                                this.ivSpace.setImageResource(R.drawable.single_internal_shoulder_a_space);
                                return;
                            } else {
                                this.ivSpace.setImageResource(R.drawable.single_internal_tip_a_space);
                                return;
                            }
                        }
                    }
                    this.abSame = false;
                    TextView textView = (TextView) this.llIndex.getChildAt(0);
                    if (textView != null) {
                        textView.setText("A");
                    }
                    this.llIndex.getChildAt(1).setVisibility(0);
                    this.llSpace.getChildAt(1).setVisibility(0);
                    this.llSpaceTool.getChildAt(1).setVisibility(0);
                    return;
                }
                return;
            case R.id.rb_sideB /* 2131362647 */:
                if (z) {
                    int type2 = this.customKey.getType();
                    if (type2 != 0 && type2 != 2) {
                        if (type2 == 3) {
                            this.side = "1";
                            TextView textView2 = (TextView) this.llIndex.getChildAt(0);
                            if (textView2 != null) {
                                textView2.setText("B");
                            }
                            if (this.customKey.getAlign() == 0) {
                                this.ivSpace.setImageResource(R.drawable.single_external_up_shoulder_space);
                                return;
                            } else {
                                this.ivSpace.setImageResource(R.drawable.single_external_up_tip_space);
                                return;
                            }
                        }
                        if (type2 != 4) {
                            if (type2 != 5) {
                                return;
                            }
                            this.side = "1";
                            TextView textView3 = (TextView) this.llIndex.getChildAt(0);
                            if (textView3 != null) {
                                textView3.setText("B");
                            }
                            if (this.customKey.getAlign() == 0) {
                                this.ivSpace.setImageResource(R.drawable.single_internal_shoulder_b_space);
                                return;
                            } else {
                                this.ivSpace.setImageResource(R.drawable.single_internal_tip_b_space);
                                return;
                            }
                        }
                    }
                    this.abSame = true;
                    TextView textView4 = (TextView) this.llIndex.getChildAt(0);
                    if (textView4 != null) {
                        textView4.setText("AB");
                    }
                    this.llIndex.getChildAt(1).setVisibility(8);
                    this.llSpace.getChildAt(1).setVisibility(8);
                    this.llSpaceTool.getChildAt(1).setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void inch2mm() {
        for (int i = 0; i < this.llSpace.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) this.llSpace.getChildAt(i);
            for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
                EditText editText = (EditText) linearLayout.getChildAt(i2);
                if (!TextUtils.isEmpty(editText.getText())) {
                    try {
                        editText.setText(String.valueOf(Math.round(Integer.parseInt(r7.toString()) * 2.54d)));
                    } catch (Exception unused) {
                    }
                }
            }
        }
        if (this.customKey.getType() == 6) {
            for (int i3 = 0; i3 < this.llIndex.getChildCount(); i3++) {
                EditText editText2 = (EditText) this.llIndex.getChildAt(i3);
                if (!TextUtils.isEmpty(editText2.getText().toString().trim())) {
                    try {
                        editText2.setText(String.valueOf(Math.round(Integer.parseInt(r2) * 2.54d)));
                    } catch (Exception unused2) {
                    }
                }
            }
        }
    }

    private void mm2inch() {
        for (int i = 0; i < this.llSpace.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) this.llSpace.getChildAt(i);
            for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
                EditText editText = (EditText) linearLayout.getChildAt(i2);
                if (!TextUtils.isEmpty(editText.getText())) {
                    try {
                        editText.setText(String.valueOf(Math.round(Integer.parseInt(r7.toString()) / 2.54d)));
                    } catch (Exception unused) {
                    }
                }
            }
        }
        if (this.customKey.getType() == 6) {
            for (int i3 = 0; i3 < this.llIndex.getChildCount(); i3++) {
                EditText editText2 = (EditText) this.llIndex.getChildAt(i3);
                if (!TextUtils.isEmpty(editText2.getText().toString().trim())) {
                    try {
                        editText2.setText(String.valueOf(Math.round(Integer.parseInt(r2) / 2.54d)));
                    } catch (Exception unused2) {
                    }
                }
            }
        }
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
                KeySpaceSetFragment.this.currentEdit = (EditText) view;
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
            Log.i(KeySpaceSetFragment.TAG, "onClick: " + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.equals("-")) {
                KeySpaceSetFragment.this.reduce(this.parent);
            } else if (str.equals("+")) {
                KeySpaceSetFragment.this.add(this.parent);
            } else if (str.equals("auto")) {
                KeySpaceSetFragment.this.auto(this.parent);
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
                    if (this.customKey.getAlign() == 0 && space < 0) {
                        Log.i(TAG, "auto: 由小到大");
                        return;
                    }
                    if (this.customKey.getAlign() == 1 && space > 0) {
                        Log.i(TAG, "auto: 由大到小");
                        return;
                    }
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
