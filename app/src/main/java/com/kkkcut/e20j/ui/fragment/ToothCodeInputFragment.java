package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.adapter.ToothKeyboardRvAdapter;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.eventbus.InputFinishBean;
import com.kkkcut.e20j.customView.drawKeyImg.AngleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DimpleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleInsideGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleOutsideKey;
import com.kkkcut.e20j.customView.drawKeyImg.Key;
import com.kkkcut.e20j.customView.drawKeyImg.Side3KsKey;
import com.kkkcut.e20j.customView.drawKeyImg.SideToothKey;
import com.kkkcut.e20j.customView.drawKeyImg.SigleInsideGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.SingleKey;
import com.kkkcut.e20j.customView.drawKeyImg.SingleOutGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.TubularKey;
import com.kkkcut.e20j.us.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ToothCodeInputFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {

    Button btIgnitionDoor;

    Button btRounding;

    Switch cbDecimal;
    private boolean doorIgnition;
    private boolean doorToIgnition;

    FrameLayout flKey;
    Key key = null;
    private KeyInfo keyinfo;

    LinearLayout llInputRule;

    RecyclerView rvKeyboard;
    private ToothKeyboardRvAdapter toothKeyboardRvAdapter;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tooth_input;
    }

    public static ToothCodeInputFragment newInstance(KeyInfo keyInfo) {
        ToothCodeInputFragment toothCodeInputFragment = new ToothCodeInputFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KeyInfoFragment.KEY_INFO, keyInfo);
        toothCodeInputFragment.setArguments(bundle);
        return toothCodeInputFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        KeyInfo keyInfo = (KeyInfo) getArguments().get(KeyInfoFragment.KEY_INFO);
        this.keyinfo = keyInfo;
        int type = keyInfo.getType();
        if (type != 92) {
            switch (type) {
                case 0:
                    this.key = new DoubleKey(getContext(), this.keyinfo);
                    break;
                case 1:
                    this.key = new SingleKey(getContext(), this.keyinfo);
                    break;
                case 2:
                    this.key = new DoubleInsideGrooveKey(getContext(), this.keyinfo);
                    break;
                case 3:
                    this.key = new SingleOutGrooveKey(getContext(), this.keyinfo);
                    break;
                case 4:
                    this.key = new DoubleOutsideKey(getContext(), this.keyinfo);
                    break;
                case 5:
                    this.key = new SigleInsideGrooveKey(getContext(), this.keyinfo);
                    break;
                case 6:
                    this.key = new DimpleKey(getContext(), this.keyinfo);
                    break;
                case 7:
                    this.key = new AngleKey(getContext(), this.keyinfo);
                    this.cbDecimal.setVisibility(8);
                    break;
                case 8:
                    this.key = new TubularKey(getContext(), this.keyinfo);
                    break;
                case 9:
                    this.key = new SideToothKey(getContext(), this.keyinfo);
                    break;
            }
        } else {
            this.key = new Side3KsKey(getContext(), this.keyinfo);
        }
        Key key = this.key;
        if (key != null) {
            key.setInputModel(true);
            this.flKey.addView(this.key);
        }
        String setting_round = this.keyinfo.getSetting_round();
        String readBittingRule = this.keyinfo.getReadBittingRule();
        if (!TextUtils.isEmpty(setting_round)) {
            if ("1".equals(setting_round)) {
                this.cbDecimal.setChecked(false);
                this.btRounding.setVisibility(8);
            } else {
                this.cbDecimal.setChecked(true);
            }
        } else if (!TextUtils.isEmpty(readBittingRule)) {
            if ("1".equals(readBittingRule)) {
                this.cbDecimal.setChecked(true);
            }
            if ("11".equals(readBittingRule)) {
                this.cbDecimal.setChecked(false);
                this.btRounding.setVisibility(8);
            }
        } else {
            boolean z = SPUtils.getBoolean("round", true);
            if (this.keyinfo.getType() != 7) {
                this.cbDecimal.setChecked(!z);
            }
            if (z || this.keyinfo.getType() == 7) {
                this.btRounding.setVisibility(8);
            }
        }
        String keyToothCode = this.keyinfo.getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            this.key.setToothCodeAndInvalidate(keyToothCode);
        }
        this.key.setOnKeyboardChangedListener(new Key.OnKeyboardChangedListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.customView.drawKeyImg.Key.OnKeyboardChangedListener
            public final void keyBoardChanged(List list) {
                ToothCodeInputFragment.this.m46x3dc6d62e(list);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setOrientation(1);
        this.rvKeyboard.setLayoutManager(gridLayoutManager);
        ToothKeyboardRvAdapter toothKeyboardRvAdapter = new ToothKeyboardRvAdapter();
        this.toothKeyboardRvAdapter = toothKeyboardRvAdapter;
        this.rvKeyboard.setAdapter(toothKeyboardRvAdapter);
        this.toothKeyboardRvAdapter.setNewData(fillData(this.key.getAllDepthNames().get(0)));
        this.toothKeyboardRvAdapter.setOnItemClickListener(this);
        if (this.keyinfo.getIsrule() > 0) {
            this.llInputRule.setVisibility(0);
            if (TextUtils.isEmpty(this.keyinfo.getReadBittingRule()) || !"3".equals(this.keyinfo.getReadBittingRule())) {
                return;
            }
            this.btIgnitionDoor.setVisibility(8);
            return;
        }
        this.llInputRule.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initViewsAndEvents$0$com-kkkcut-e20j-ui-fragment-ToothCodeInputFragment, reason: not valid java name */
    public /* synthetic */ void m46x3dc6d62e(List list) {
        this.toothKeyboardRvAdapter.replaceData(fillData(list));
    }

    private List<String> fillData(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add("?");
        arrayList.add(getString(R.string.clear));
        return arrayList;
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancle /* 2131361909 */:
                onBack();
                return;
            case R.id.bt_ignition_door /* 2131361937 */:
                start(IgnitionDoorSearchFragment.newInstance(this.keyinfo.getSpaceStr().split(";")[0].split(",").length));
                return;
            case R.id.bt_input_rule /* 2131361940 */:
                start(InputRuleFragment.newInstance(this.keyinfo.getIsrule(), this.keyinfo.getReadBittingRule(), toothListToStr()));
                return;
            case R.id.bt_rounding /* 2131361968 */:
                round();
                return;
            case R.id.iv_down /* 2131362297 */:
                this.key.moveToDown();
                return;
            case R.id.iv_left /* 2131362312 */:
                this.key.moveToLeft();
                return;
            case R.id.iv_right /* 2131362329 */:
                this.key.moveToRight();
                return;
            case R.id.iv_up /* 2131362357 */:
                this.key.moveToUp();
                return;
            case R.id.ll_confirm /* 2131362407 */:
                EventBus.getDefault().post(new EventCenter(2, new InputFinishBean(this.doorIgnition, toothListToStr(), this.doorToIgnition)));
                onBack();
                return;
            default:
                return;
        }
    }

    private void round() {
        List<String> list;
        int indexOf;
        ArrayList<String[]> toothCode = this.key.getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                String str2 = toothCode.get(i)[i2];
                if (str2.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
                    String str3 = "0." + str2.split("\\.")[1];
                    str2 = str2.split("\\.")[0];
                    if (Float.parseFloat(str3) >= 0.5f && (indexOf = (list = this.key.getAllDepthNames().get(i)).indexOf(str2)) != list.size() - 1) {
                        str2 = list.get(indexOf + 1);
                    }
                }
                str = i2 == toothCode.get(i).length - 1 ? str + str2 + ";" : str + str2 + ",";
            }
        }
        this.key.setToothCodeAndInvalidate(str);
    }

    private String toothListToStr() {
        ArrayList<String[]> toothCode = this.key.getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                str = i2 == toothCode.get(i).length - 1 ? str + toothCode.get(i)[i2] + ";" : str + toothCode.get(i)[i2] + ",";
            }
        }
        Log.i(TAG, "toothListToStr: " + str);
        return str;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String str = (String) baseQuickAdapter.getData().get(i);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.key.changeSingleTooth(str);
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.input);
    }

    public void onCheckedChange(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.cb_invert) {
            invert();
            return;
        }
        if (id != R.id.switch_decimal) {
            return;
        }
        Key key = this.key;
        if (key != null) {
            key.setShowDecimal(z);
        }
        if (z) {
            this.btRounding.setVisibility(0);
        } else {
            this.btRounding.setVisibility(8);
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter<?> eventCenter) {
        if (eventCenter.getEventCode() == 15) {
            this.key.setToothCodeAndInvalidate((String) eventCenter.getData());
        } else if (eventCenter.getEventCode() == 22) {
            InputFinishBean inputFinishBean = (InputFinishBean) eventCenter.getData();
            this.doorIgnition = true;
            this.doorToIgnition = inputFinishBean.isDoorToIgnition();
            this.key.setToothCodeAndInvalidate(inputFinishBean.getToothCode());
        }
    }

    private void invert() {
        int indexOf;
        ArrayList<String[]> toothCode = this.key.getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                List<String> list = this.key.getAllDepthNames().get(i);
                String str2 = toothCode.get(i)[i2];
                if (str2.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
                    String str3 = "0." + str2.split("\\.")[1];
                    str2 = str2.split("\\.")[0];
                    if (Float.parseFloat(str3) >= 0.5f && (indexOf = list.indexOf(str2)) != list.size() - 1) {
                        str2 = list.get(indexOf + 1);
                    }
                }
                int indexOf2 = list.indexOf(str2);
                String str4 = indexOf2 != -1 ? list.get((list.size() - 1) - indexOf2) : "?";
                str = i2 == toothCode.get(i).length - 1 ? str + str4 + ";" : str + str4 + ",";
            }
        }
        this.key.setToothCodeAndInvalidate(str);
    }
}
