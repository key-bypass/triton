package com.kkkcut.e20j.ui.fragment.engraving;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class UsbImageSelectFragment_ViewBinding implements Unbinder {
    private UsbImageSelectFragment target;
    private View view7f0a0075;
    private View view7f0a007d;
    private View view7f0a048a;

    public UsbImageSelectFragment_ViewBinding(final UsbImageSelectFragment usbImageSelectFragment, View view) {
        this.target = usbImageSelectFragment;
        usbImageSelectFragment.rvPics = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_pics, "field 'rvPics'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_load_pics_from_usb, "method 'onViewClicked'");
        this.view7f0a048a = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                usbImageSelectFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                usbImageSelectFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_confirm, "method 'onViewClicked'");
        this.view7f0a007d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                usbImageSelectFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UsbImageSelectFragment usbImageSelectFragment = this.target;
        if (usbImageSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        usbImageSelectFragment.rvPics = null;
        this.view7f0a048a.setOnClickListener(null);
        this.view7f0a048a = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a007d.setOnClickListener(null);
        this.view7f0a007d = null;
    }
}
