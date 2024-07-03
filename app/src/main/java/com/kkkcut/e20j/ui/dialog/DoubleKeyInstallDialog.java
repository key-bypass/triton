package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
public class DoubleKeyInstallDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private int align;
    CheckBox cbDontRemind;
    private int index;
    ImageView ivRemind;
    TextView tvNext;
    TextView tvRemind;

    public DoubleKeyInstallDialog(Context context, int i) {
        super(context);
        this.align = i;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_doublekey_install);
        this.ivRemind = (ImageView) findViewById(R.id.iv_remind);
        this.tvRemind = (TextView) findViewById(R.id.tv_remind);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_dont_remind);
        this.cbDontRemind = checkBox;
        checkBox.setOnCheckedChangeListener(this);
        this.tvNext = (TextView) findViewById(R.id.tv_next);
        ((ImageView) findViewById(R.id.iv_remind)).setOnClickListener(this);
        ((FrameLayout) findViewById(R.id.fl_cancle)).setOnClickListener(this);
        ((FrameLayout) findViewById(R.id.fl_confirm)).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_cancle /* 2131362198 */:
                this.index = 0;
                dismiss();
                return;
            case R.id.fl_confirm /* 2131362199 */:
            case R.id.iv_remind /* 2131362327 */:
                int i = this.index;
                if (i == 0) {
                    if (this.align == 0) {
                        this.ivRemind.setImageResource(R.drawable.doublekey_remind_two_shoulder);
                    } else {
                        this.ivRemind.setImageResource(R.drawable.doublekey_remind_two_tip);
                    }
                    this.tvRemind.setText(getContext().getString(R.string.second_step_please_put_the_key_as_shwn));
                    this.index++;
                    return;
                }
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    this.index = 0;
                    dismiss();
                    return;
                }
                this.tvNext.setText(R.string.ok);
                if (this.align == 0) {
                    this.ivRemind.setImageResource(R.drawable.doublekey_remind_three_shoulder);
                } else {
                    this.ivRemind.setImageResource(R.drawable.doublekey_remind_three_tip);
                }
                this.tvRemind.setText(getContext().getString(R.string.third_step_please_toggle_the_switch_to_release_the_slider));
                this.index++;
                return;
            default:
                return;
        }
    }

    @Override // android.app.Dialog
    public void show() {
        this.index = 0;
        TextView textView = this.tvNext;
        if (textView != null) {
            textView.setText(R.string.next);
        }
        TextView textView2 = this.tvRemind;
        if (textView2 != null) {
            textView2.setText(R.string.first_step_please_push_the_slider_to_the_specified_position);
        }
        ImageView imageView = this.ivRemind;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.doublekey_remind_one);
        }
        super.show();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() != R.id.cb_dont_remind) {
            return;
        }
        SPUtils.put(SpKeys.DOUBLE_KEY_DONT_REMIND, z);
    }
}
