package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class OperatorRemindDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    CheckBox cbNotRemind;
    private CountDownTimer countDownTimer;
    FrameLayout flSkip;
    ImageView ivClose;
    String remind;
    TextView tvRemind;
    TextView tvTimer;
    private int type;

    public OperatorRemindDialog(Context context) {
        super(context);
        this.countDownTimer = new CountDownTimer(6100L, 1000L) { // from class: com.kkkcut.e20j.ui.dialog.OperatorRemindDialog.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                OperatorRemindDialog.this.tvTimer.setText(String.valueOf((int) (j / 1000)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                OperatorRemindDialog.this.dismiss();
            }
        };
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fl_skip) {
            this.countDownTimer.cancel();
            dismiss();
        } else {
            if (id != R.id.iv_close) {
                return;
            }
            this.countDownTimer.cancel();
            dismiss();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_operator);
        this.tvTimer = (TextView) findViewById(R.id.tv_timer);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.flSkip = (FrameLayout) findViewById(R.id.fl_skip);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_not_remind);
        this.cbNotRemind = checkBox;
        checkBox.setOnCheckedChangeListener(this);
        this.flSkip.setOnClickListener(this);
        this.ivClose.setOnClickListener(this);
        if (this.type == 0) {
            this.countDownTimer.start();
        }
        if (TextUtils.isEmpty(this.remind)) {
            return;
        }
        this.tvRemind.setText(this.remind);
    }

    public void setRemindText(String str) {
        this.remind = str;
        TextView textView = this.tvRemind;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setType(int i) {
        this.type = i;
        if (i == 1) {
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            FrameLayout frameLayout = this.flSkip;
            if (frameLayout != null) {
                frameLayout.setVisibility(4);
            }
            CheckBox checkBox = this.cbNotRemind;
            if (checkBox != null) {
                checkBox.setVisibility(4);
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SPUtils.put(SpKeys.TABETS_OPERATION_REMIND, z);
    }
}
