package com.kkkcut.e20j.customView.searchSpinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.BaseAutolayoutHolder;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class InputPromptAdapter extends BaseQuickAdapter<String, BaseAutolayoutHolder> {
    public InputPromptAdapter() {
        super(R.layout.item_seach_prompt);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseAutolayoutHolder baseAutolayoutHolder, String str) {
        baseAutolayoutHolder.setText(R.id.tv_pro_result, str);
    }
}
