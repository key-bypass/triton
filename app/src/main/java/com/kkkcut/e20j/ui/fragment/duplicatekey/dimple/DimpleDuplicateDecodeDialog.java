package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.kkkcut.e20j.us.R;
import com.liying.core.bean.KeyInfo;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class DimpleDuplicateDecodeDialog extends BottomInDialog {
    public static final String PARAM = "param";
    private static final String TAG = "DecodeDialog";

    @BindView(R.id.bt_cancle)
    TextView btCancle;

    @BindView(R.id.bt_decode)
    TextView btDecode;
    boolean isRound;

    @BindView(R.id.iv_clamp)
    ImageView ivClamp;

    @BindView(R.id.iv_decoder)
    ImageView ivDecoder;
    private KeyInfo keyInfo;
    boolean roundBtVisible;

    @BindView(R.id.sb_round)
    CheckBox sbRound;
    private int timeValue;

    @BindView(R.id.tv_cutter_size)
    TextView tvDecoderSize;

    @BindView(R.id.tv_round)
    TextView tvRound;

    @BindView(R.id.tv_time_value)
    TextView tvTimeValue;

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.dialog_decode;
    }

    public DimpleDuplicateDecodeDialog(Activity activity, KeyInfo keyInfo) {
        super(activity);
        this.roundBtVisible = true;
        this.keyInfo = keyInfo;
    }

    public DimpleDuplicateDecodeDialog(Activity activity, KeyInfo keyInfo, boolean z) {
        this(activity, keyInfo);
        this.roundBtVisible = z;
    }

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public void initView() {
        initClamp();
        initDecoder();
        initRound();
    }

    private void initRound() {
        if (!this.roundBtVisible) {
            this.isRound = false;
            this.sbRound.setVisibility(8);
            this.tvRound.setVisibility(8);
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
        String decoder = getKeyInfo().getDecoder();
        if (!TextUtils.isEmpty(decoder)) {
            if (decoder.contains(",")) {
                String[] split = decoder.split(",");
                this.tvDecoderSize.setText(split[1] + "mm");
                return;
            }
            return;
        }
        this.tvDecoderSize.setText("1.0mm");
    }

    @OnClick({R.id.bt_cancle, R.id.bt_decode, R.id.iv_close, R.id.iv_time_reduce, R.id.iv_time_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancle /* 2131361909 */:
                dismiss();
                return;
            case R.id.bt_decode /* 2131361922 */:
                dismiss();
                EventBus.getDefault().post(new EventCenter(39, Boolean.valueOf(this.isRound)));
                return;
            case R.id.iv_close /* 2131362287 */:
                dismiss();
                return;
            case R.id.iv_time_add /* 2131362353 */:
                this.timeValue++;
                this.tvTimeValue.setText(this.timeValue + "s");
                return;
            case R.id.iv_time_reduce /* 2131362354 */:
                int i = this.timeValue;
                if (i > 0) {
                    this.timeValue = i - 1;
                }
                this.tvTimeValue.setText(this.timeValue + "s");
                return;
            default:
                return;
        }
    }

    private void initClamp() {
        int clampBigImg = ClampCreator.getClampBigImg(getKeyInfo());
        if (clampBigImg != 0) {
            this.ivClamp.setImageResource(clampBigImg);
        }
    }

    private KeyInfo getKeyInfo() {
        return this.keyInfo;
    }

    @OnCheckedChanged({R.id.sb_round})
    public void onChecekChange(CompoundButton compoundButton, boolean z) {
        this.isRound = z;
        if (z) {
            SPUtils.put("round", true);
        } else {
            SPUtils.put("round", false);
        }
    }
}
