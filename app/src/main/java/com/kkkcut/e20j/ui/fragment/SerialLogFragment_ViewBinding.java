package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SerialLogFragment_ViewBinding implements Unbinder {
    private SerialLogFragment target;

    public SerialLogFragment_ViewBinding(SerialLogFragment serialLogFragment, View view) {
        this.target = serialLogFragment;
        serialLogFragment.tvLog = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_log, "field 'tvLog'", TextView.class);
        serialLogFragment.btClear = (Button) Utils.findRequiredViewAsType(view, R.id.bt_clear, "field 'btClear'", Button.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SerialLogFragment serialLogFragment = this.target;
        if (serialLogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        serialLogFragment.tvLog = null;
        serialLogFragment.btClear = null;
    }
}
