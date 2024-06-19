package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class WarningDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private boolean cancelVisible;
    private String cancleStr;
    CheckBox cbDontRemind;
    private CheckBox cbNeverAsk;
    private boolean checkboxVisible;
    private String confirmStr;
    FrameLayout fl_cancle;
    FrameLayout fl_confirm;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String remind;
    private String spKey;
    TextView tvRemind;
    TextView tv_cancel;
    TextView tv_confirm;

    /* loaded from: classes.dex */
    public interface DialogBtnCallBack {
        void onDialogButClick(boolean z);
    }

    public WarningDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_warning);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        this.cbDontRemind = (CheckBox) findViewById(R.id.cb_dont_remind);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_confirm);
        this.fl_confirm = frameLayout;
        frameLayout.setOnClickListener(this);
        this.tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        this.tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.fl_cancle);
        this.fl_cancle = frameLayout2;
        frameLayout2.setOnClickListener(this);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_dont_remind);
        this.cbNeverAsk = checkBox;
        checkBox.setOnCheckedChangeListener(this);
        if (!TextUtils.isEmpty(this.remind)) {
            this.tvRemind.setText(this.remind);
        }
        if (!TextUtils.isEmpty(this.confirmStr)) {
            this.fl_confirm.setVisibility(0);
            this.tv_confirm.setText(this.confirmStr);
        }
        if (!TextUtils.isEmpty(this.cancleStr)) {
            this.fl_cancle.setVisibility(0);
            this.tv_cancel.setText(this.cancleStr);
        } else {
            this.fl_cancle.setVisibility(this.cancelVisible ? 0 : 8);
        }
        this.cbNeverAsk.setVisibility(this.checkboxVisible ? 0 : 8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_cancle /* 2131362198 */:
                dismiss();
                DialogBtnCallBack dialogBtnCallBack = this.mDialogBtnCallBack;
                if (dialogBtnCallBack != null) {
                    dialogBtnCallBack.onDialogButClick(false);
                    return;
                }
                return;
            case R.id.fl_confirm /* 2131362199 */:
                dismiss();
                DialogBtnCallBack dialogBtnCallBack2 = this.mDialogBtnCallBack;
                if (dialogBtnCallBack2 != null) {
                    dialogBtnCallBack2.onDialogButClick(true);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setRemind(String str) {
        this.remind = str;
        TextView textView = this.tvRemind;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setCancelBtVisible(boolean z) {
        this.cancelVisible = z;
        FrameLayout frameLayout = this.fl_cancle;
        if (frameLayout != null) {
            frameLayout.setVisibility(z ? 0 : 8);
        }
    }

    public void setConfirmText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.confirmStr = str;
        TextView textView = this.tv_confirm;
        if (textView != null) {
            textView.setVisibility(0);
            this.tv_confirm.setText(this.confirmStr);
        }
    }

    public void setCancelText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.cancleStr = str;
        if (this.tv_cancel != null) {
            this.fl_cancle.setVisibility(0);
            this.tv_cancel.setText(this.cancleStr);
        }
    }

    public void setCheckbox(boolean z, String str) {
        this.checkboxVisible = z;
        this.spKey = str;
        CheckBox checkBox = this.cbNeverAsk;
        if (checkBox != null) {
            if (z) {
                checkBox.setVisibility(0);
            } else {
                checkBox.setVisibility(8);
            }
        }
    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallBack) {
        this.mDialogBtnCallBack = dialogBtnCallBack;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (TextUtils.isEmpty(this.spKey)) {
            return;
        }
        SPUtils.put(this.spKey, z);
    }
}
