package com.kkkcut.e20j.ui.fragment.search;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SearchResultActivity_ViewBinding implements Unbinder {
    private SearchResultActivity target;
    private View view7f0a0447;

    public SearchResultActivity_ViewBinding(SearchResultActivity searchResultActivity) {
        this(searchResultActivity, searchResultActivity.getWindow().getDecorView());
    }

    public SearchResultActivity_ViewBinding(final SearchResultActivity searchResultActivity, View view) {
        this.target = searchResultActivity;
        searchResultActivity.tvTile = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTile'", TextView.class);
        searchResultActivity.rvSearchResult1 = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_search_result_1, "field 'rvSearchResult1'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_back, "method 'onClick'");
        this.view7f0a0447 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchResultActivity.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchResultActivity searchResultActivity = this.target;
        if (searchResultActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchResultActivity.tvTile = null;
        searchResultActivity.rvSearchResult1 = null;
        this.view7f0a0447.setOnClickListener(null);
        this.view7f0a0447 = null;
    }
}
