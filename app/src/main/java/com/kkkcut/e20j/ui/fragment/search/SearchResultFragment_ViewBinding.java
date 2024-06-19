package com.kkkcut.e20j.ui.fragment.search;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SearchResultFragment_ViewBinding implements Unbinder {
    private SearchResultFragment target;

    public SearchResultFragment_ViewBinding(SearchResultFragment searchResultFragment, View view) {
        this.target = searchResultFragment;
        searchResultFragment.tvTile = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTile'", TextView.class);
        searchResultFragment.rvSearchResult1 = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_search_result_1, "field 'rvSearchResult1'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchResultFragment searchResultFragment = this.target;
        if (searchResultFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchResultFragment.tvTile = null;
        searchResultFragment.rvSearchResult1 = null;
    }
}
