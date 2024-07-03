package com.kkkcut.e20j.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyMarkingTemplateAdapter extends BaseQuickAdapter<KeyMarkingTemplate, BaseAutolayoutHolder> {
    public KeyMarkingTemplateAdapter() {
        super(R.layout.item_keymarking_template);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseAutolayoutHolder baseAutolayoutHolder, KeyMarkingTemplate keyMarkingTemplate) {
        baseAutolayoutHolder.setText(R.id.tv_remark, keyMarkingTemplate.getDescription()).addOnClickListener(R.id.iv_delete);
    }
}