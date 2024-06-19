package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class TestFragment_ViewBinding implements Unbinder {
    private TestFragment target;
    private View view7f0a0119;

    public TestFragment_ViewBinding(final TestFragment testFragment, View view) {
        this.target = testFragment;
        testFragment.paramDes = (EditText) Utils.findRequiredViewAsType(view, R.id.param, "field 'paramDes'", EditText.class);
        testFragment.responseDec = (TextView) Utils.findRequiredViewAsType(view, R.id.response, "field 'responseDec'", TextView.class);
        testFragment.clear = (Button) Utils.findRequiredViewAsType(view, R.id.clear, "field 'clear'", Button.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.commit, "method 'onViewClicked'");
        this.view7f0a0119 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.TestFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                testFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TestFragment testFragment = this.target;
        if (testFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        testFragment.paramDes = null;
        testFragment.responseDec = null;
        testFragment.clear = null;
        this.view7f0a0119.setOnClickListener(null);
        this.view7f0a0119 = null;
    }
}
