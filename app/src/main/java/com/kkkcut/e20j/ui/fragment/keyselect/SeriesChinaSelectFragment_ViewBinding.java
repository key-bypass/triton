package com.kkkcut.e20j.ui.fragment.keyselect;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SeriesChinaSelectFragment_ViewBinding implements Unbinder {
    private SeriesChinaSelectFragment target;

    public SeriesChinaSelectFragment_ViewBinding(SeriesChinaSelectFragment seriesChinaSelectFragment, View view) {
        this.target = seriesChinaSelectFragment;
        seriesChinaSelectFragment.rvCategoryList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_category_list, "field 'rvCategoryList'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SeriesChinaSelectFragment seriesChinaSelectFragment = this.target;
        if (seriesChinaSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        seriesChinaSelectFragment.rvCategoryList = null;
    }
}
