package com.kkkcut.e20j.ui.fragment.keyselect;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeySelectFragment_ViewBinding implements Unbinder {
    private KeySelectFragment target;

    public KeySelectFragment_ViewBinding(KeySelectFragment keySelectFragment, View view) {
        this.target = keySelectFragment;
        keySelectFragment.etSearch = (EditText) Utils.findRequiredViewAsType(view, R.id.et_search, "field 'etSearch'", EditText.class);
        keySelectFragment.rvCategoryList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_category_list, "field 'rvCategoryList'", RecyclerView.class);
        keySelectFragment.indexBar = (IndexBar) Utils.findRequiredViewAsType(view, R.id.indexBar, "field 'indexBar'", IndexBar.class);
        keySelectFragment.tvSideBarHint = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSideBarHint, "field 'tvSideBarHint'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeySelectFragment keySelectFragment = this.target;
        if (keySelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keySelectFragment.etSearch = null;
        keySelectFragment.rvCategoryList = null;
        keySelectFragment.indexBar = null;
        keySelectFragment.tvSideBarHint = null;
    }
}
