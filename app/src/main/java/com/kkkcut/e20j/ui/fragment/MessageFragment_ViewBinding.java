package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class MessageFragment_ViewBinding implements Unbinder {
    private MessageFragment target;

    public MessageFragment_ViewBinding(MessageFragment messageFragment, View view) {
        this.target = messageFragment;
        messageFragment.rvMessage = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_message, "field 'rvMessage'", RecyclerView.class);
        messageFragment.tvRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_remind, "field 'tvRemind'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MessageFragment messageFragment = this.target;
        if (messageFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        messageFragment.rvMessage = null;
        messageFragment.tvRemind = null;
    }
}
