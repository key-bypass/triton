package com.kkkcut.e20j.ui.fragment.technical;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class TechnicalInfoFragment_ViewBinding implements Unbinder {
    private TechnicalInfoFragment target;

    public TechnicalInfoFragment_ViewBinding(TechnicalInfoFragment technicalInfoFragment, View view) {
        this.target = technicalInfoFragment;
        technicalInfoFragment.tvInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_info, "field 'tvInfo'", TextView.class);
        technicalInfoFragment.rvDataList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_data_list, "field 'rvDataList'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TechnicalInfoFragment technicalInfoFragment = this.target;
        if (technicalInfoFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        technicalInfoFragment.tvInfo = null;
        technicalInfoFragment.rvDataList = null;
    }
}
