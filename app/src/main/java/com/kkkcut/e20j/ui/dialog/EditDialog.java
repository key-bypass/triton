package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class EditDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "LoadingDialog";
    private String content;
    private EditText edit;
    private DialogInputFinishCallBack inputFinish;
    private Button mCancelBtn;
    private Button mConfirmBtn;
    private String mTip;
    private boolean nullable;
    private TextView tvRemind;

    /* loaded from: classes.dex */
    public interface DialogInputFinishCallBack {
        void onDialogButClick(String str);
    }

    public EditDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_edit);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        this.mConfirmBtn = (Button) findViewById(R.id.ll_confirm);
        this.mCancelBtn = (Button) findViewById(R.id.bt_cancle);
        this.edit = (EditText) findViewById(R.id.et_input);
        this.mConfirmBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        }
        if (!StringUtil.isEmpty(this.content)) {
            this.edit.setText(this.content);
            this.edit.setSelection(this.content.length());
        }
        setCancelable(true);
    }

    public EditDialog setTip(String str) {
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

    public EditDialog setContentNullable(boolean z) {
        this.nullable = z;
        return this;
    }

    public EditDialog setEditTextContent(String str) {
        this.content = str;
        EditText editText = this.edit;
        if (editText != null) {
            editText.setText(str);
            this.edit.setSelection(str.length());
        }
        return this;
    }

    public void onDismiss() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        onDismiss();
        int id = view.getId();
        if (id != R.id.bt_cancle) {
            if (id == R.id.ll_confirm && this.inputFinish != null) {
                String trim = this.edit.getText().toString().trim();
                if (TextUtils.isEmpty(trim) && !this.nullable) {
                    this.edit.setError(getContext().getString(R.string.not_null));
                    return;
                } else {
                    this.inputFinish.onDialogButClick(trim);
                    return;
                }
            }
            return;
        }
        this.edit.clearFocus();
    }

    public void setDialogBtnCallback(DialogInputFinishCallBack dialogInputFinishCallBack) {
        this.inputFinish = dialogInputFinishCallBack;
    }
}
