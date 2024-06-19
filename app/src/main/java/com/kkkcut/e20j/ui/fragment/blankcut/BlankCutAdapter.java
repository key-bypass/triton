package com.kkkcut.e20j.ui.fragment.blankcut;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.BaseAutolayoutHolder;
import com.kkkcut.e20j.us.R;
import java.util.List;

/* loaded from: classes.dex */
public class BlankCutAdapter extends BaseQuickAdapter<BlankCutBean, BaseAutolayoutHolder> {
    public BlankCutAdapter(List<BlankCutBean> list) {
        super(R.layout.item_blank_cut, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseAutolayoutHolder baseAutolayoutHolder, BlankCutBean blankCutBean) {
        baseAutolayoutHolder.setText(R.id.cb_black_cut, blankCutBean.getName());
        baseAutolayoutHolder.setChecked(R.id.cb_black_cut, blankCutBean.isChecked());
        baseAutolayoutHolder.addOnClickListener(R.id.cb_black_cut);
    }
}
