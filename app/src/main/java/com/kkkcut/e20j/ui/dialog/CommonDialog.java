package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CommonDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "CommonDialog";
    private DialogCallback mCallback;
    private boolean mCanConfirmButtonDismiss;
    private Button mCancelBtn;
    private String mCancelText;
    private Button mConfirmBtn;
    private String mConfirmText;
    private Context mContext;
    private DialogBtnCallBack mDialogBtnCallBack;
    private String mInfoText;
    private TextView mInfoView;
    private String mTitle;
    private TextView mTitleView;

    /* loaded from: classes.dex */
    public interface DialogBtnCallBack {
        void onDialogButClick(boolean z);
    }

    /* loaded from: classes.dex */
    public interface DialogCallback {
        void onDialogCallback(boolean z, boolean z2, int i);
    }

    public CommonDialog(Context context) {
        super(context, R.style.dialog_common_style);
        this.mCanConfirmButtonDismiss = true;
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_common);
        this.mTitleView = findViewById(R.id.dialog_title);
        this.mInfoView = findViewById(R.id.dialog_info);
        this.mConfirmBtn = findViewById(R.id.dialog_confirm);
        this.mCancelBtn = findViewById(R.id.dialog_cancel);
        this.mConfirmBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        String str = this.mInfoText;
        if (str != null && !StringUtil.isEmpty(str)) {
            this.mInfoView.setText(this.mInfoText);
        }
        this.mTitleView.setText(this.mTitle);
        String str2 = this.mConfirmText;
        if (str2 != null && !StringUtil.isEmpty(str2)) {
            this.mConfirmBtn.setText(this.mConfirmText);
        }
        String str3 = this.mCancelText;
        if (str3 == null || StringUtil.isEmpty(str3)) {
            return;
        }
        this.mCancelBtn.setText(this.mCancelText);
    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallBack) {
        this.mDialogBtnCallBack = dialogBtnCallBack;
    }

    public void setValue(String str, String str2, String str3, String str4, String str5, DialogCallback dialogCallback) {
        LogUtil.i(TAG, "dialog title:" + str + "  mes:0" + str2 + " positiveBtn:" + str3 + " negativeBtn:" + str4 + " not_tip:" + str5 + " callback" + dialogCallback);
        this.mTitle = str;
        this.mInfoText = str2;
        this.mConfirmText = str3;
        this.mCancelText = str4;
        this.mCallback = dialogCallback;
    }

    public void onDismiss() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.dialog_confirm) {
            if (this.mCanConfirmButtonDismiss) {
                onDismiss();
            }
            DialogBtnCallBack dialogBtnCallBack = this.mDialogBtnCallBack;
            if (dialogBtnCallBack != null) {
                dialogBtnCallBack.onDialogButClick(true);
                return;
            }
            return;
        }
        if (id == R.id.dialog_cancel) {
            onDismiss();
            DialogBtnCallBack dialogBtnCallBack2 = this.mDialogBtnCallBack;
            if (dialogBtnCallBack2 != null) {
                dialogBtnCallBack2.onDialogButClick(false);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String mCancelText;
        private String mConfirmText;
        private Context mContext;
        private DialogBtnCallBack mDialogBtnCallback;
        private DialogCallback mDialogCallback;
        private String mDoNotShowAgain;
        private String mInfoText;
        private String mTitle;
        private boolean mSelected = false;
        private boolean mCancelable = true;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setTitle(int i) {
            return setTitle(this.mContext.getString(i));
        }

        public Builder setMessage(String str) {
            this.mInfoText = str;
            return this;
        }

        public Builder setContectTextSelected(boolean z) {
            this.mSelected = z;
            return this;
        }

        public Builder setMessage(int i) {
            return setMessage(this.mContext.getString(i));
        }

        public Builder setPositiveButton(String str) {
            this.mConfirmText = str;
            return this;
        }

        public Builder setPositiveButton(int i) {
            return setPositiveButton(this.mContext.getString(i));
        }

        public Builder setNegativeButton(String str) {
            this.mCancelText = str;
            return this;
        }

        public Builder setNegativeButton(int i) {
            return setNegativeButton(this.mContext.getString(i));
        }

        public Builder setNotTipText(String str) {
            this.mDoNotShowAgain = str;
            return this;
        }

        public Builder setClickCallBack(DialogCallback dialogCallback) {
            this.mDialogCallback = dialogCallback;
            return this;
        }

        public Builder setBtnClickCallBack(DialogBtnCallBack dialogBtnCallBack) {
            this.mDialogBtnCallback = dialogBtnCallBack;
            return this;
        }

        public CommonDialog create() {
            CommonDialog commonDialog = new CommonDialog(this.mContext);
            commonDialog.setValue(this.mTitle, this.mInfoText, this.mConfirmText, this.mCancelText, this.mDoNotShowAgain, this.mDialogCallback);
            commonDialog.setDialogBtnCallback(this.mDialogBtnCallback);
            return commonDialog;
        }

        public CommonDialog show() {
            LogUtil.i(CommonDialog.TAG, " title " + this.mTitle + " message" + this.mInfoText + "  show()  to be invoked");
            CommonDialog create = create();
            create.show();
            create.setCancelable(this.mCancelable);
            return create;
        }
    }
}
