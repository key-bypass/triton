package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class AnglekeyTurningDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "LoadingDialog";
    private FrameLayout fSkip;
    private int imgSrc;
    private ImageView ivRemind;
    private FrameLayout mCancelBtn;
    private FrameLayout mConfirmBtn;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String mTip;
    private TextView tvRemind;

    /* loaded from: classes.dex */
    public interface DialogBtnCallBack {
        void onDialogButClick(int i);
    }

    public AnglekeyTurningDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_anglekey_turning);
        this.ivRemind = (ImageView) findViewById(R.id.iv_remind);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        this.mConfirmBtn = (FrameLayout) findViewById(R.id.fl_confirm);
        this.mCancelBtn = (FrameLayout) findViewById(R.id.fl_cancle);
        this.fSkip = (FrameLayout) findViewById(R.id.fl_skip);
        this.mConfirmBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        this.fSkip.setOnClickListener(this);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        }
        if (getDrawableById(this.imgSrc) != null) {
            this.ivRemind.setVisibility(0);
            this.ivRemind.setImageResource(this.imgSrc);
        }
        setCancelable(false);
    }

    public AnglekeyTurningDialog setRemindMsg(String str) {
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

    public AnglekeyTurningDialog setRemindImg(int i) {
        this.imgSrc = i;
        if (this.ivRemind != null && getDrawableById(i) != null) {
            this.ivRemind.setImageResource(i);
        }
        return this;
    }

    private Drawable getDrawableById(int i) {
        try {
            return getContext().getResources().getDrawable(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onDismiss() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_cancle /* 2131362198 */:
                DialogBtnCallBack dialogBtnCallBack = this.mDialogBtnCallBack;
                if (dialogBtnCallBack != null) {
                    dialogBtnCallBack.onDialogButClick(0);
                    break;
                }
                break;
            case R.id.fl_confirm /* 2131362199 */:
                DialogBtnCallBack dialogBtnCallBack2 = this.mDialogBtnCallBack;
                if (dialogBtnCallBack2 != null) {
                    dialogBtnCallBack2.onDialogButClick(2);
                    break;
                }
                break;
            case R.id.fl_skip /* 2131362209 */:
                DialogBtnCallBack dialogBtnCallBack3 = this.mDialogBtnCallBack;
                if (dialogBtnCallBack3 != null) {
                    dialogBtnCallBack3.onDialogButClick(1);
                    break;
                }
                break;
        }
        onDismiss();
    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallBack) {
        this.mDialogBtnCallBack = dialogBtnCallBack;
    }
}
