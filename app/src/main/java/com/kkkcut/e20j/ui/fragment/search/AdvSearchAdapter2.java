package com.kkkcut.e20j.ui.fragment.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kkkcut.e20j.DbBean.search.MenuSummary;
import com.kkkcut.e20j.us.R;
import java.util.List;

/* loaded from: classes.dex */
public class AdvSearchAdapter2 extends BaseQuickAdapter<MenuSummary, BaseViewHolder> {
    public AdvSearchAdapter2(List<MenuSummary> list) {
        super(R.layout.item_search_result_series, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, MenuSummary menuSummary) {
        baseViewHolder.setText(R.id.tv_lock_sys, menuSummary.getLockSystem()).setText(R.id.tv_series, menuSummary.getSeries()).setText(R.id.tv_card, String.valueOf(menuSummary.getCard())).setText(R.id.tv_sn, menuSummary.getSN()).setText(R.id.tv_type, menuSummary.getType());
    }
}
