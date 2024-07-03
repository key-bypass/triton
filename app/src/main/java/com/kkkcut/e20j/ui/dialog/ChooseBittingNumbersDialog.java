package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.us.R;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class ChooseBittingNumbersDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private int bittings;
    private String desc;
    private RadioGroup rgBittingNumbers;
    private String variable_space;

    public ChooseBittingNumbersDialog(Context context, String str, String str2) {
        super(context);
        this.variable_space = str;
        this.desc = str2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_choosebitting_num);
        initRadioGroup();
        initDesc();
        ((Button) findViewById(R.id.ll_confirm)).setOnClickListener(this);
        setCancelable(false);
    }

    private void initDesc() {
        if (TextUtils.isEmpty(this.desc)) {
            return;
        }
        TextView textView = (TextView) findViewById(R.id.tv_description);
        textView.setVisibility(0);
        textView.setText(this.desc);
    }

    private void initRadioGroup() {
        TextView textView = (TextView) findViewById(R.id.tv_remind);
        this.rgBittingNumbers = (RadioGroup) findViewById(R.id.rg_bitting_numbers);
        if (TextUtils.isEmpty(this.variable_space)) {
            textView.setVisibility(8);
            this.rgBittingNumbers.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        this.rgBittingNumbers.setVisibility(0);
        this.rgBittingNumbers.setOnCheckedChangeListener(this);
        String[] split = this.variable_space.split(",");
        for (int i = 0; i < split.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(split[i]);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            radioButton.setId(i);
            radioButton.setGravity(17);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(-2, -2);
            if (i > 0) {
                layoutParams.setMarginStart(20);
            }
            this.rgBittingNumbers.addView(radioButton, i, layoutParams);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.bittings > 0) {
            EventBus.getDefault().post(new EventCenter(9, Integer.valueOf(this.bittings)));
        }
        dismiss();
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i != -1) {
            this.bittings = Integer.parseInt(this.variable_space.split(",")[i]);
        }
    }
}
