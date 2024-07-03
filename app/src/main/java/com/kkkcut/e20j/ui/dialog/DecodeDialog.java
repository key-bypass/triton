package com.kkkcut.e20j.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.clamp.ClampUtil;
import com.cutting.machine.operation.cut.DataParam;
import com.kkkcut.e20j.us.R;

import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class DecodeDialog extends BottomInDialog {
    public static final String PARAM = "param";
    private static final String TAG = "DecodeDialog";

    TextView btCancle;

    TextView btDecode;

    CheckBox cbSlantCorrection;
    private DataParam decodeParams;
    private int decoderSize;
    boolean dimpleDuplicate;
    boolean isRound;

    ImageView ivClamp;

    ImageView ivDecoder;

    LinearLayout llDecodeSlowly;

    RadioButton rb50;

    RadioGroup rgDecodeSize;

    CheckBox sbRound;
    private int timeValue;

    TextView tvDecodeSlowly;

    TextView tvDecoderSize;

    TextView tvRound;

    TextView tvSlantCorrection;

    TextView tvTimeValue;

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.dialog_decode;
    }

    public DecodeDialog(Activity activity, DataParam dataParam) {
        super(activity);
        this.dimpleDuplicate = false;
        this.decodeParams = dataParam;
    }

    public DecodeDialog(Activity activity, DataParam dataParam, boolean z) {
        this(activity, dataParam);
        this.dimpleDuplicate = z;
    }

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public void initView() {
        initClamp();
        initDecoder();
        initRound();
        initPause();
        initHu64SlantCorrection();
    }

    private void initHu64SlantCorrection() {
        if (getKeyInfo().getKeyType() == KeyType.SINGLE_OUTSIDE_GROOVE_KEY && getKeyInfo().getSide() == 1 && TextUtils.equals(getKeyInfo().getClampBean().getClampNum(), "S1") && TextUtils.equals(getKeyInfo().getClampBean().getClampSide(), "C")) {
            this.tvSlantCorrection.setVisibility(0);
            this.cbSlantCorrection.setVisibility(0);
            if (this.decodeParams.isSlantCorrection()) {
                this.cbSlantCorrection.setChecked(true);
            } else {
                this.cbSlantCorrection.setChecked(false);
            }
        }
    }

    private void initPause() {
        this.timeValue = SPUtils.getInt(SpKeys.DECODE_PAUSE, 0);
        this.tvTimeValue.setText(this.timeValue + "s");
    }

    private void initRound() {
        if (this.dimpleDuplicate) {
            this.isRound = false;
            this.sbRound.setVisibility(8);
            this.tvRound.setVisibility(8);
            this.tvTimeValue.setVisibility(8);
            this.tvDecodeSlowly.setVisibility(8);
            this.llDecodeSlowly.setVisibility(8);
            return;
        }
        String setting_round = getKeyInfo().getSetting_round();
        String readBittingRule = getKeyInfo().getReadBittingRule();
        if (!TextUtils.isEmpty(setting_round)) {
            if ("1".equals(setting_round)) {
                this.tvRound.setVisibility(8);
                this.sbRound.setVisibility(8);
                this.isRound = true;
                return;
            } else if ("2".equals(setting_round)) {
                this.tvRound.setVisibility(8);
                this.sbRound.setVisibility(8);
                this.isRound = false;
                return;
            } else {
                boolean z = SPUtils.getBoolean("round", true);
                this.isRound = z;
                this.sbRound.setChecked(z);
                return;
            }
        }
        if (!TextUtils.isEmpty(readBittingRule) && ("1".equals(readBittingRule) || "11".equals(readBittingRule))) {
            this.tvRound.setVisibility(8);
            this.sbRound.setVisibility(8);
        } else {
            boolean z2 = SPUtils.getBoolean("round", true);
            this.isRound = z2;
            this.sbRound.setChecked(z2);
        }
    }

    private void initDecoder() {
        if (getKeyInfo().getType() == 6) {
            this.ivDecoder.setImageResource(R.drawable.decoder_dimple);
            this.tvDecoderSize.setVisibility(8);
        } else {
            this.ivDecoder.setImageResource(R.drawable.decoder_normal);
        }
        this.decoderSize = this.decodeParams.getDecoderSize();
        this.tvDecoderSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.decoderSize / 100.0f)));
        if (getKeyInfo().getType() == 1) {
            this.rgDecodeSize.setVisibility(0);
            if (this.decoderSize == 50) {
                this.rb50.setChecked(true);
            }
        }
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancle /* 2131361909 */:
                dismiss();
                return;
            case R.id.bt_decode /* 2131361922 */:
                WarningDialog warningDialog = new WarningDialog(getContext());
                warningDialog.show();
                String str = (this.decoderSize / 100.0f) + "mm";
                if (getKeyInfo().getType() == 6) {
                    str = getContext().getString(R.string.dimple);
                }
                warningDialog.setRemind(getContext().getString(R.string.please_use_specify_decoder, new Object[]{str}));
                warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog.1
                    @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            DecodeDialog.this.goBack();
                        }
                    }
                });
                return;
            case R.id.iv_close /* 2131362287 */:
                dismiss();
                return;
            case R.id.iv_time_add /* 2131362353 */:
                this.timeValue++;
                this.tvTimeValue.setText(this.timeValue + "s");
                SPUtils.put(SpKeys.DECODE_PAUSE, this.timeValue);
                return;
            case R.id.iv_time_reduce /* 2131362354 */:
                int i = this.timeValue;
                if (i <= 0) {
                    return;
                }
                this.timeValue = i - 1;
                this.tvTimeValue.setText(this.timeValue + "s");
                SPUtils.put(SpKeys.DECODE_PAUSE, this.timeValue);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goBack() {
        dismiss();
        Bundle bundle = new Bundle();
        this.decodeParams.setRound(this.isRound);
        this.decodeParams.setPauseTime(this.timeValue);
        this.decodeParams.setClamp(ClampUtil.getClamp(getKeyInfo()));
        this.decodeParams.setClampMode(ClampUtil.getClampMode(getKeyInfo()));
        this.decodeParams.setDecoderSize(this.decoderSize);
        this.decodeParams.setSlantCorrection(this.cbSlantCorrection.isChecked());
        bundle.putParcelable("param", this.decodeParams);
        EventBus.getDefault().post(new EventCenter(39, bundle));
    }

    private void initClamp() {
        int clampBigImg = ClampCreator.getClampBigImg(getKeyInfo());
        if (clampBigImg != 0) {
            this.ivClamp.setImageResource(clampBigImg);
        }
    }

    private KeyInfo getKeyInfo() {
        return this.decodeParams.getKeyInfo();
    }

    public void onChecekChange(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.rb_100) {
            if (z) {
                this.decoderSize = 100;
                this.tvDecoderSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.decoderSize / 100.0f)));
                return;
            }
            return;
        }
        if (id == R.id.rb_50) {
            if (z) {
                this.decoderSize = 50;
                this.tvDecoderSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.decoderSize / 100.0f)));
                return;
            }
            return;
        }
        if (id != R.id.sb_round) {
            return;
        }
        this.isRound = z;
        if (z) {
            SPUtils.put("round", true);
        } else {
            SPUtils.put("round", false);
        }
    }
}
