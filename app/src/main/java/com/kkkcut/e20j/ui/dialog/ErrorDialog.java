package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.cutting.machine.DialogBtnCallBack;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class ErrorDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "LoadingDialog";
    private String confirmStr;
    private FrameLayout flImg;
    private LinearLayout frameError;
    private int imgSrc;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String mTip;
    private TextView tvConfirm;
    private TextView tvRemind;

    public ErrorDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_error);
        this.flImg = (FrameLayout) findViewById(R.id.fl_img);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.frame);
        this.frameError = linearLayout;
        linearLayout.setOnClickListener(this);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        }
        if (!TextUtils.isEmpty(this.confirmStr)) {
            this.tvConfirm.setText(this.confirmStr);
        }
        this.flImg.setBackgroundResource(this.imgSrc);
        setCancelable(false);
    }

    public ErrorDialog setTip(String str) {
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

    public ErrorDialog setConfirmStr(String str) {
        this.confirmStr = str;
        if (this.tvRemind != null && !TextUtils.isEmpty(str)) {
            this.tvConfirm.setText(this.confirmStr);
        }
        return this;
    }

    public ErrorDialog setRemindImg(int i) {
        this.imgSrc = i;
        FrameLayout frameLayout = this.flImg;
        if (frameLayout != null) {
            frameLayout.setBackgroundResource(i);
        }
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        DialogBtnCallBack dialogBtnCallBack;
        dismiss();
        if (view.getId() == R.id.frame && (dialogBtnCallBack = this.mDialogBtnCallBack) != null) {
            dialogBtnCallBack.onDialogButClick(true);
        }
    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallBack) {
        this.mDialogBtnCallBack = dialogBtnCallBack;
    }
}
