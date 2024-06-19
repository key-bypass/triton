package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class ForbidDialog extends Dialog {
    private static final String TAG = "LoadingDialog";
    private int imgSrc;
    private ImageView ivRemind;
    private String mTip;
    private TextView tvRemind;

    public ForbidDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_forbid);
        this.ivRemind = (ImageView) findViewById(R.id.iv_remind);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        if (!StringUtil.isEmpty(this.mTip)) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(this.mTip);
        }
        this.ivRemind.setImageResource(this.imgSrc);
        setCancelable(false);
    }

    public ForbidDialog setTip(String str) {
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

    public ForbidDialog setRemindImg(int i) {
        this.imgSrc = i;
        ImageView imageView = this.ivRemind;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
        return this;
    }
}
