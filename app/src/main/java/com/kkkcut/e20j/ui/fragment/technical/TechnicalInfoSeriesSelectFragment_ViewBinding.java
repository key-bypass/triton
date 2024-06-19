package com.kkkcut.e20j.ui.fragment.technical;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class TechnicalInfoSeriesSelectFragment_ViewBinding implements Unbinder {
    private com.kkkcut.e20j.p005ui.fragment.technical.TechnicalInfoSeriesSelectFragment target;

    public TechnicalInfoSeriesSelectFragment_ViewBinding(com.kkkcut.e20j.p005ui.fragment.technical.TechnicalInfoSeriesSelectFragment technicalInfoSeriesSelectFragment, View view) {
        this.target = technicalInfoSeriesSelectFragment;
        technicalInfoSeriesSelectFragment.rvCategoryList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_category_list, "field 'rvCategoryList'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        com.kkkcut.e20j.p005ui.fragment.technical.TechnicalInfoSeriesSelectFragment technicalInfoSeriesSelectFragment = this.target;
        if (technicalInfoSeriesSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        technicalInfoSeriesSelectFragment.rvCategoryList = null;
    }
}
