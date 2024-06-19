package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class RemindDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "LoadingDialog";
    private String cancel;
    private boolean cancleBtnVisible;
    private CheckBox cbNeverAsk;
    private boolean checkboxVisible;
    private String confirm;
    private int imgSrc;
    private ImageView ivRemind;
    private LinearLayout llPicText;
    private FrameLayout mCancelBtn;
    private LinearLayout mConfirmBtn;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String mTip;
    private String spKey;
    private TextView tvCancel;
    private TextView tvConfirm;
    private TextView tvRemind;

    /* loaded from: classes.dex */
    public interface DialogBtnCallBack {
        void onDialogButClick(boolean z);
    }

    public RemindDialog(Context context) {
        super(context);
        this.cancleBtnVisible = true;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_remind);
        this.ivRemind = (ImageView) findViewById(R.id.iv_remind);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancle);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_dont_remind);
        this.cbNeverAsk = checkBox;
        checkBox.setOnCheckedChangeListener(this);
        this.mConfirmBtn = (LinearLayout) findViewById(R.id.ll_confirm);
        this.mCancelBtn = (FrameLayout) findViewById(R.id.fl_cancle);
        this.mConfirmBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        } else {
            this.tvRemind.setVisibility(8);
        }
        if (getDrawableById(this.imgSrc) != null) {
            this.ivRemind.setVisibility(0);
            this.ivRemind.setImageResource(this.imgSrc);
        }
        if (this.cancleBtnVisible) {
            this.mCancelBtn.setVisibility(0);
        } else {
            this.mCancelBtn.setVisibility(8);
        }
        setCancelable(false);
        if (this.checkboxVisible) {
            this.cbNeverAsk.setVisibility(0);
        } else {
            this.cbNeverAsk.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.confirm)) {
            this.tvConfirm.setText(this.confirm);
        }
        if (TextUtils.isEmpty(this.cancel)) {
            return;
        }
        this.tvCancel.setText(this.cancel);
    }

    public RemindDialog setRemindMsg(String str) {
        this.mTip = str;
        if (this.tvRemind != null) {
            if (TextUtils.isEmpty(str)) {
                this.tvRemind.setVisibility(8);
            } else {
                this.tvRemind.setVisibility(0);
                this.tvRemind.setText(str);
            }
        }
        return this;
    }

    public RemindDialog setCheckbox(boolean z, String str) {
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
        return this;
    }

    public RemindDialog setRemindImg(int i) {
        this.imgSrc = i;
        if (this.ivRemind != null && getDrawableById(i) != null) {
            this.ivRemind.setVisibility(0);
            this.ivRemind.setImageResource(i);
        }
        return this;
    }

    public RemindDialog setCancleBtnVisibility(boolean z) {
        this.cancleBtnVisible = z;
        FrameLayout frameLayout = this.mCancelBtn;
        if (frameLayout != null) {
            if (z) {
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
            }
        }
        return this;
    }

    public void setConfirmStr(String str) {
        this.confirm = str;
        TextView textView = this.tvConfirm;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setCancelStr(String str) {
        this.cancel = str;
        TextView textView = this.tvCancel;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private Drawable getDrawableById(int i) {
        try {
            return getContext().getResources().getDrawable(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        DialogBtnCallBack dialogBtnCallBack;
        int id = view.getId();
        if (id == R.id.fl_cancle) {
            DialogBtnCallBack dialogBtnCallBack2 = this.mDialogBtnCallBack;
            if (dialogBtnCallBack2 != null) {
                dialogBtnCallBack2.onDialogButClick(false);
            }
        } else if (id == R.id.ll_confirm && (dialogBtnCallBack = this.mDialogBtnCallBack) != null) {
            dialogBtnCallBack.onDialogButClick(true);
        }
        dismiss();
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
