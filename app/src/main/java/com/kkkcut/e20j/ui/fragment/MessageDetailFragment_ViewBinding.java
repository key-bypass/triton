package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class MessageDetailFragment_ViewBinding implements Unbinder {
    private MessageDetailFragment target;

    public MessageDetailFragment_ViewBinding(MessageDetailFragment messageDetailFragment, View view) {
        this.target = messageDetailFragment;
        messageDetailFragment.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        messageDetailFragment.tvContent = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'tvContent'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MessageDetailFragment messageDetailFragment = this.target;
        if (messageDetailFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        messageDetailFragment.tvTitle = null;
        messageDetailFragment.tvContent = null;
    }
}
