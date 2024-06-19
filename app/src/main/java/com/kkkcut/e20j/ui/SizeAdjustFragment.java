package com.kkkcut.e20j.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.kkkcut.e20j.utils.UnitUtils;
import com.liying.core.bean.KeyInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class SizeAdjustFragment extends BaseBackFragment {
    private static final String KEYINFO = "SizeAdjustFragment";

    @BindView(R.id.cb_all_space)
    CheckBox cbAllSpace;

    @BindView(R.id.cb_all_spaceWidth)
    CheckBox cbAllSpaceWidth;
    KeyInfo keyInfo;
    private TextView lastText;

    @BindView(R.id.size_container)
    LinearLayout llSizeContainer;

    @BindView(R.id.tv_space_value)
    TextView tvSpaceValue;

    @BindView(R.id.tv_spaceWidth_value)
    TextView tvSpaceWidthValue;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_size_adjust;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static SizeAdjustFragment newInstance(KeyInfo keyInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEYINFO, keyInfo);
        SizeAdjustFragment sizeAdjustFragment = new SizeAdjustFragment();
        sizeAdjustFragment.setArguments(bundle);
        return sizeAdjustFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        KeyInfo keyInfo = (KeyInfo) getArguments().getParcelable(KEYINFO);
        this.keyInfo = keyInfo;
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        List<List<Integer>> spaceWidthList = this.keyInfo.getSpaceWidthList();
        initSpaceIndex(spaceList);
        initSpaceAndSpaceWidth(spaceList, spaceWidthList);
        ((LinearLayout) this.llSizeContainer.getChildAt(1)).getChildAt(1).performClick();
    }

    private void initSpaceAndSpaceWidth(List<List<Integer>> list, List<List<Integer>> list2) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list3 = list.get(i);
            List<Integer> list4 = list2.get(i);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            linearLayout2.setOrientation(1);
            TextView textView = new TextView(getContext());
            textView.setText(getString(R.string.spaces));
            textView.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
            textView.setTextSize(16.0f);
            linearLayout.addView(textView);
            TextView textView2 = new TextView(getContext());
            textView2.setText(getString(R.string.width));
            textView2.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
            textView2.setTextSize(16.0f);
            linearLayout2.addView(textView2);
            for (int i2 = 0; i2 < list3.size(); i2++) {
                linearLayout.addView(getSpaceText(String.valueOf(list3.get(i2)), 0), getLayoutParams());
                linearLayout2.addView(getSpaceText(String.valueOf(list4.get(i2)), 1), getLayoutParams());
            }
            if (i == 0) {
                this.llSizeContainer.addView(linearLayout);
            } else {
                this.llSizeContainer.addView(linearLayout, getContainerLayoutParam());
            }
            this.llSizeContainer.addView(linearLayout2, getContainerLayoutParam());
        }
    }

    private LinearLayout.LayoutParams getContainerLayoutParam() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(AutoUtils.getPercentWidthSize(2), 0, 0, 0);
        return layoutParams;
    }

    private LinearLayout.LayoutParams getLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AutoUtils.getPercentWidthSize(60), AutoUtils.getPercentHeightSize(30));
        layoutParams.setMargins(0, AutoUtils.getPercentHeightSize(2), 0, 0);
        return layoutParams;
    }

    private void initSpaceIndex(List<List<Integer>> list) {
        int maxSpaceCount = getMaxSpaceCount(list);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        for (int i = 0; i < maxSpaceCount + 1; i++) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(16.0f);
            textView.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
            if (i == 0) {
                textView.setText("");
                linearLayout.addView(textView);
            } else {
                textView.setText(String.valueOf(i));
                textView.setGravity(17);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AutoUtils.getPercentWidthSize(60), AutoUtils.getPercentHeightSize(30));
                layoutParams.setMargins(0, AutoUtils.getPercentHeightSize(2), 0, 0);
                linearLayout.addView(textView, layoutParams);
            }
        }
        this.llSizeContainer.addView(linearLayout);
    }

    private TextView getSpaceText(String str, int i) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.color_blackLight_blueDark));
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setOnClickListener(new MyClickListener());
        textView.setTag(Integer.valueOf(i));
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            textView.setText(String.valueOf(UnitUtils.mm2Inch(Integer.parseInt(str))));
        } else {
            textView.setText(String.valueOf(str));
        }
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyClickListener implements View.OnClickListener {
        private MyClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SizeAdjustFragment.this.lastText == view) {
                return;
            }
            TextView textView = (TextView) view;
            textView.getText();
            textView.setTextColor(-1);
            textView.setBackgroundResource(R.drawable.bg_ff205f);
            if (SizeAdjustFragment.this.lastText != null) {
                SizeAdjustFragment.this.lastText.setTextColor(-1);
                SizeAdjustFragment.this.lastText.setBackgroundResource(ThemeUtils.getResId(SizeAdjustFragment.this.getContext(), R.attr.color_blackLight_blueDark));
            }
            SizeAdjustFragment.this.lastText = textView;
            if (((Integer) textView.getTag()).intValue() == 0) {
                SizeAdjustFragment.this.tvSpaceValue.setText(textView.getText().toString().trim());
            } else {
                SizeAdjustFragment.this.tvSpaceWidthValue.setText(textView.getText().toString().trim());
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Tag {
        private int type;

        private Tag() {
        }
    }

    private int getMaxSpaceCount(List<List<Integer>> list) {
        Iterator<List<Integer>> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = Math.max(i, it.next().size());
        }
        return i;
    }

    @OnClick({R.id.iv_space_reduce, R.id.iv_space_add, R.id.iv_spaceWidth_add, R.id.iv_spaceWidth_reduce, R.id.iv_left, R.id.iv_right, R.id.iv_down, R.id.iv_up, R.id.bt_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save /* 2131361969 */:
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int childCount = this.llSizeContainer.getChildCount();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                int i = 1;
                int i2 = 1;
                while (i2 < childCount - 1) {
                    ArrayList arrayList3 = new ArrayList();
                    LinearLayout linearLayout = (LinearLayout) this.llSizeContainer.getChildAt(i2);
                    for (int i3 = 1; i3 < linearLayout.getChildCount(); i3++) {
                        String trim = ((TextView) linearLayout.getChildAt(i3)).getText().toString().trim();
                        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                            sb.append(UnitUtils.inch2Mm(Integer.parseInt(trim)));
                        } else {
                            sb.append(trim);
                        }
                        if (i3 == linearLayout.getChildCount() - i) {
                            sb.append(";");
                        } else {
                            sb.append(",");
                        }
                        arrayList3.add(Integer.valueOf(Integer.parseInt(trim)));
                    }
                    LinearLayout linearLayout2 = (LinearLayout) this.llSizeContainer.getChildAt(i2 + 1);
                    ArrayList arrayList4 = new ArrayList();
                    for (int i4 = 1; i4 < linearLayout2.getChildCount(); i4++) {
                        String trim2 = ((TextView) linearLayout2.getChildAt(i4)).getText().toString().trim();
                        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                            sb2.append(UnitUtils.inch2Mm(Integer.parseInt(trim2)));
                        } else {
                            sb2.append(trim2);
                        }
                        if (i4 == linearLayout2.getChildCount() - 1) {
                            sb2.append(";");
                        } else {
                            sb2.append(",");
                        }
                        arrayList4.add(Integer.valueOf(Integer.parseInt(trim2)));
                    }
                    arrayList.add(arrayList3);
                    arrayList2.add(arrayList4);
                    i2 += 2;
                    i = 1;
                }
                for (int i5 = 0; i5 < arrayList.size(); i5++) {
                    List list = (List) arrayList.get(i5);
                    List list2 = (List) arrayList2.get(i5);
                    int i6 = 0;
                    while (i6 < list.size() - 1) {
                        int intValue = ((Integer) list.get(i6)).intValue();
                        int i7 = i6 + 1;
                        int intValue2 = ((Integer) list.get(i7)).intValue();
                        int intValue3 = ((Integer) list2.get(i6)).intValue();
                        int intValue4 = ((Integer) list2.get(i7)).intValue();
                        if (this.keyInfo.getAlign() == 0) {
                            if (intValue2 - intValue <= (intValue3 + intValue4) / 2) {
                                ToastUtil.showToast(getString(R.string.key_data_error));
                                return;
                            }
                        } else if (intValue - intValue2 <= (intValue3 + intValue4) / 2) {
                            ToastUtil.showToast(getString(R.string.key_data_error));
                            return;
                        }
                        i6 = i7;
                    }
                }
                this.keyInfo.setSpaceStr(sb.toString());
                this.keyInfo.setSpaceWidthStr(sb2.toString());
                EventBus.getDefault().post(new EventCenter(57));
                onBack();
                return;
            case R.id.iv_down /* 2131362297 */:
                moveToDown();
                return;
            case R.id.iv_left /* 2131362312 */:
                moveToLeft();
                return;
            case R.id.iv_right /* 2131362329 */:
                moveToRight();
                return;
            case R.id.iv_spaceWidth_add /* 2131362340 */:
                modifySpaceWidth(10);
                return;
            case R.id.iv_spaceWidth_reduce /* 2131362341 */:
                modifySpaceWidth(-10);
                return;
            case R.id.iv_space_add /* 2131362342 */:
                modifySpace(10);
                return;
            case R.id.iv_space_reduce /* 2131362343 */:
                modifySpace(-10);
                return;
            case R.id.iv_up /* 2131362357 */:
                moveToUp();
                return;
            default:
                return;
        }
    }

    private void moveToLeft() {
        TextView textView = this.lastText;
        if (textView == null) {
            moveToDefault();
            return;
        }
        LinearLayout linearLayout = (LinearLayout) textView.getParent();
        int indexOfChild = linearLayout.indexOfChild(this.lastText);
        int indexOfChild2 = this.llSizeContainer.indexOfChild(linearLayout);
        if (indexOfChild2 > 1) {
            LinearLayout linearLayout2 = (LinearLayout) this.llSizeContainer.getChildAt(indexOfChild2 - 1);
            if (linearLayout2.getChildCount() > indexOfChild) {
                linearLayout2.getChildAt(indexOfChild).performClick();
            } else {
                linearLayout2.getChildAt(linearLayout2.getChildCount() - 1).performClick();
            }
        }
    }

    private void moveToRight() {
        TextView textView = this.lastText;
        if (textView == null) {
            moveToDefault();
            return;
        }
        LinearLayout linearLayout = (LinearLayout) textView.getParent();
        int indexOfChild = linearLayout.indexOfChild(this.lastText);
        int indexOfChild2 = this.llSizeContainer.indexOfChild(linearLayout);
        if (indexOfChild2 < this.llSizeContainer.getChildCount() - 1) {
            LinearLayout linearLayout2 = (LinearLayout) this.llSizeContainer.getChildAt(indexOfChild2 + 1);
            if (linearLayout2.getChildCount() > indexOfChild) {
                linearLayout2.getChildAt(indexOfChild).performClick();
            } else {
                linearLayout2.getChildAt(linearLayout2.getChildCount() - 1).performClick();
            }
        }
    }

    private void moveToUp() {
        TextView textView = this.lastText;
        if (textView == null) {
            moveToDefault();
            return;
        }
        LinearLayout linearLayout = (LinearLayout) textView.getParent();
        int indexOfChild = linearLayout.indexOfChild(this.lastText);
        if (indexOfChild > 1) {
            linearLayout.getChildAt(indexOfChild - 1).performClick();
        }
    }

    private void moveToDown() {
        TextView textView = this.lastText;
        if (textView == null) {
            moveToDefault();
            return;
        }
        LinearLayout linearLayout = (LinearLayout) textView.getParent();
        int indexOfChild = linearLayout.indexOfChild(this.lastText);
        if (indexOfChild < linearLayout.getChildCount() - 1) {
            linearLayout.getChildAt(indexOfChild + 1).performClick();
        }
    }

    private void moveToDefault() {
        ((TextView) ((LinearLayout) this.llSizeContainer.getChildAt(1)).getChildAt(1)).performClick();
    }

    private void modifySpace(int i) {
        selectSpaceDefault();
        modifySingleValue(this.tvSpaceValue, i);
        if (this.cbAllSpace.isChecked()) {
            modifyAll(this.lastText, i);
        } else {
            modifySingleValue(this.lastText, i);
        }
    }

    private void modifySpaceWidth(int i) {
        selectSpaceWidthDefault();
        modifySingleValue(this.tvSpaceWidthValue, i);
        if (this.cbAllSpaceWidth.isChecked()) {
            modifyAll(this.lastText, i);
        } else {
            modifySingleValue(this.lastText, i);
        }
    }

    private void modifyAll(TextView textView, int i) {
        LinearLayout linearLayout = (LinearLayout) textView.getParent();
        for (int i2 = 1; i2 < linearLayout.getChildCount(); i2++) {
            modifySingleValue((TextView) linearLayout.getChildAt(i2), i);
        }
    }

    private void modifySingleValue(TextView textView, int i) {
        String trim = textView.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        textView.setText(String.valueOf(Integer.parseInt(trim) + i));
    }

    private void selectSpaceDefault() {
        TextView textView = this.lastText;
        if (textView == null || ((Integer) textView.getTag()).intValue() == 1) {
            TextView textView2 = this.lastText;
            ((TextView) ((LinearLayout) this.llSizeContainer.getChildAt(textView2 != null ? this.llSizeContainer.indexOfChild((View) textView2.getParent()) - 1 : 1)).getChildAt(1)).performClick();
        }
    }

    private void selectSpaceWidthDefault() {
        TextView textView = this.lastText;
        if (textView == null || ((Integer) textView.getTag()).intValue() == 0) {
            TextView textView2 = this.lastText;
            ((TextView) ((LinearLayout) this.llSizeContainer.getChildAt(textView2 != null ? this.llSizeContainer.indexOfChild((View) textView2.getParent()) + 1 : 2)).getChildAt(1)).performClick();
        }
    }
}
