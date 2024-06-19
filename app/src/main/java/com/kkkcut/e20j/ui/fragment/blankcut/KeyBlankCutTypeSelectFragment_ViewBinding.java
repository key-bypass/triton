package com.kkkcut.e20j.ui.fragment.blankcut;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyBlankCutTypeSelectFragment_ViewBinding implements Unbinder {
    private KeyBlankCutTypeSelectFragment target;
    private View view7f0a00aa;
    private View view7f0a00ac;

    public KeyBlankCutTypeSelectFragment_ViewBinding(final KeyBlankCutTypeSelectFragment keyBlankCutTypeSelectFragment, View view) {
        this.target = keyBlankCutTypeSelectFragment;
        keyBlankCutTypeSelectFragment.ivShow = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_show, "field 'ivShow'", ImageView.class);
        keyBlankCutTypeSelectFragment.rvBlankCut = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_blank_cut, "field 'rvBlankCut'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_ok, "field 'btOk' and method 'onclick'");
        keyBlankCutTypeSelectFragment.btOk = (Button) Utils.castView(findRequiredView, R.id.bt_ok, "field 'btOk'", Button.class);
        this.view7f0a00aa = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyBlankCutTypeSelectFragment.onclick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_preset, "field 'btPreset' and method 'onclick'");
        keyBlankCutTypeSelectFragment.btPreset = (Button) Utils.castView(findRequiredView2, R.id.bt_preset, "field 'btPreset'", Button.class);
        this.view7f0a00ac = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyBlankCutTypeSelectFragment.onclick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyBlankCutTypeSelectFragment keyBlankCutTypeSelectFragment = this.target;
        if (keyBlankCutTypeSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyBlankCutTypeSelectFragment.ivShow = null;
        keyBlankCutTypeSelectFragment.rvBlankCut = null;
        keyBlankCutTypeSelectFragment.btOk = null;
        keyBlankCutTypeSelectFragment.btPreset = null;
        this.view7f0a00aa.setOnClickListener(null);
        this.view7f0a00aa = null;
        this.view7f0a00ac.setOnClickListener(null);
        this.view7f0a00ac = null;
    }
}
