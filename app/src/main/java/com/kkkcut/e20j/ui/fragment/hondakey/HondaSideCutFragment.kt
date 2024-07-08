package com.kkkcut.e20j.ui.fragment.hondakey;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.jakewharton.rxbinding3.view.RxView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.cutting.machine.CuttingMachine;
import com.cutting.machine.OperateType;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.error.ErrorBean;
import com.cutting.machine.operation.cut.DataParam;
import com.kkkcut.e20j.us.R;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class HondaSideCutFragment extends BaseBackFragment {
    public static final int SIDE_A = 0;
    public static final int SIDE_B = 1;

    TextView btCut;
    private int cutter_size = 200;
    private DataParam dataParam = new DataParam();


    RadioButton rbHonda2020;

    RadioButton rbHonda2021;

    RadioGroup rgYear;
    private int side;

    TextView tvCutterSize;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_honda_cut;
    }

    public static HondaSideCutFragment newInstance() {
        Bundle bundle = new Bundle();
        HondaSideCutFragment hondaSideCutFragment = new HondaSideCutFragment();
        hondaSideCutFragment.setArguments(bundle);
        return hondaSideCutFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
        this.btCut.setVisibility(0);
        addDisposable((Disposable) RxView.clicks(this.btCut).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> {
            HondaSideCutFragment.this.startCut();
        }));
        if (getArguments() == null) {
            return;
        }
        this.rbHonda2020.setText(getString(R.string.honda) + " 2020");
        this.rbHonda2021.setText(getString(R.string.honda) + " 2021");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCut() {
        KeyInfo createHondaSideKey = HondaKeyFactory.createHondaSideKey(this.rgYear.getCheckedRadioButtonId() == R.id.rb_honda_2020 ? 2020 : 2021, this.side);
        ToolSizeManager.setCutterSize(this.cutter_size);
        this.dataParam.setKeyInfo(createHondaSideKey);
        this.dataParam.setCutSpeed(SPUtils.getInt(SpKeys.SPEED + createHondaSideKey.getType(), 15));
        this.dataParam.setDecoderSize(100);
        this.dataParam.setCutterSize(this.cutter_size);
        this.dataParam.setQuickCut(true);
        CuttingMachine.getInstance().cut(this.dataParam);
        showLoadingDialog(getString(R.string.waitting), true);
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (isVisible()) {
            int eventCode = eventCenter.getEventCode();
            if (eventCode != 47) {
                switch (eventCode) {
                    case 32:
                        handleOperationFinish(eventCenter);
                        return;
                    case 33:
                        showError(eventCenter);
                        return;
                    case 34:
                        showLoadingDialog(getString(R.string.waitting));
                        return;
                    default:
                        return;
                }
            }
            showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
        }
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_size_add /* 2131362335 */:
                int i = this.cutter_size;
                if (i < 250) {
                    this.cutter_size = i + 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_reduce /* 2131362336 */:
                int i2 = this.cutter_size;
                if (i2 > 100) {
                    this.cutter_size = i2 - 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCheckChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_honda_a /* 2131362619 */:
                if (z) {
                    this.side = 0;
                    return;
                }
                return;
            case R.id.rb_honda_b /* 2131362620 */:
                if (z) {
                    this.side = 1;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void handleOperationFinish(EventCenter eventCenter) {
        if (((OperateType) eventCenter.getData()) == OperateType.KEY_BLANK_CUT_EXECUTE) {
            addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    HondaSideCutFragment.this.dismissLoadingDialog();
                }
            }));
            showLoadingDialog("100%", true);
        }
    }

    private void showError(EventCenter eventCenter) {
        dismissLoadingDialog();
        showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.honda);
    }
}
