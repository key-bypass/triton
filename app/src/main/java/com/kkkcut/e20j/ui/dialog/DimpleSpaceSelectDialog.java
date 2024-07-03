package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;

import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DimpleSpaceSelectDialog extends Dialog {
    private List<CheckBox> cbList;
    private GridLayout glContainer;
    private int index;
    private CheckBox lastRb;
    private OnConfirmListener onConfirmListener;
    private int spaceCount;

    /* loaded from: classes.dex */
    public interface OnConfirmListener {
        void onConfirm(int i);
    }

    public DimpleSpaceSelectDialog(Context context, int i, int i2) {
        super(context);
        this.spaceCount = i;
        this.index = i2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_dimple_space_select);
        initRadioGroup();
        ((Button) findViewById(R.id.ll_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DimpleSpaceSelectDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DimpleSpaceSelectDialog.this.onConfirmListener != null) {
                    DimpleSpaceSelectDialog.this.onConfirmListener.onConfirm(DimpleSpaceSelectDialog.this.lastRb != null ? DimpleSpaceSelectDialog.this.cbList.indexOf(DimpleSpaceSelectDialog.this.lastRb) : 0);
                }
                DimpleSpaceSelectDialog.this.dismiss();
            }
        });
        setCancelable(false);
    }

    private void initRadioGroup() {
        this.glContainer = (GridLayout) findViewById(R.id.rl_container);
        int i = 0;
        for (int i2 = 0; i2 < this.spaceCount; i2++) {
            View.inflate(getContext(), R.layout.mrg_child, this.glContainer);
        }
        this.cbList = new ArrayList();
        while (i < this.spaceCount) {
            CheckBox checkBox = (CheckBox) this.glContainer.getChildAt(i);
            this.cbList.add(checkBox);
            if (i == this.index) {
                checkBox.setChecked(true);
                this.lastRb = checkBox;
            }
            i++;
            checkBox.setText(String.valueOf(i));
            checkBox.setOnClickListener(new CustomOnClickListener());
        }
    }

    public void setOnConfirm(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CustomOnClickListener implements View.OnClickListener {
        private CustomOnClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DimpleSpaceSelectDialog.this.lastRb != null) {
                DimpleSpaceSelectDialog.this.lastRb.setChecked(false);
            }
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                checkBox.setChecked(true);
                DimpleSpaceSelectDialog.this.lastRb = checkBox;
            }
        }
    }
}
