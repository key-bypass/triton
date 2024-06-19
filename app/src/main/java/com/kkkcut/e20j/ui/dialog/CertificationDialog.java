package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CertificationDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "LoadingDialog";
    private boolean clickDismiss;
    private String confirmStr;
    private int imgSrc;
    private LinearLayout mConfirmBtn;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String mTip;
    private TextView tvConfirm;
    private TextView tvRemind;

    /* loaded from: classes.dex */
    public interface DialogBtnCallBack {
        void onDialogButClick(boolean z);
    }

    public CertificationDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_certification);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.frame);
        this.mConfirmBtn = linearLayout;
        linearLayout.setOnClickListener(this);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        }
        if (!TextUtils.isEmpty(this.confirmStr)) {
            this.tvConfirm.setText(this.confirmStr);
        }
        setCancelable(false);
    }

    public CertificationDialog setTip(String str) {
        this.mTip = str;
        if (this.tvRemind != null) {
            if (TextUtils.isEmpty(str)) {
                this.tvRemind.setVisibility(8);
            } else {
                this.tvRemind.setText(str);
            }
        }
        return this;
    }

    public CertificationDialog setConfirmStr(String str) {
        this.confirmStr = str;
        if (this.tvRemind != null && !TextUtils.isEmpty(str)) {
            this.tvConfirm.setText(this.confirmStr);
        }
        return this;
    }

    public void setClickDismiss(boolean z) {
        this.clickDismiss = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.frame) {
            return;
        }
        DialogBtnCallBack dialogBtnCallBack = this.mDialogBtnCallBack;
        if (dialogBtnCallBack != null) {
            dialogBtnCallBack.onDialogButClick(true);
        }
        if (this.clickDismiss) {
            dismiss();
        }
    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallBack) {
        this.mDialogBtnCallBack = dialogBtnCallBack;
    }
}
