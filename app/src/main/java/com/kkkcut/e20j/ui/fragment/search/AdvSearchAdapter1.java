package com.kkkcut.e20j.ui.fragment.search;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ResUpdateUtils;

/* loaded from: classes.dex */
public class AdvSearchAdapter1 extends BaseQuickAdapter<AdvSearchResult, BaseViewHolder> {
    public AdvSearchAdapter1() {
        super(R.layout.item_advance_result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder baseViewHolder, AdvSearchResult advSearchResult) {
        int fK_KeyID = advSearchResult.getFK_KeyID();
        ResUpdateUtils.showKeyImage(baseViewHolder.itemView.getContext(), fK_KeyID, (ImageView) baseViewHolder.itemView.findViewById(R.id.iv_thumb));
        baseViewHolder.setText(R.id.tv_kid, String.valueOf(fK_KeyID)).setText(R.id.tv_cuts, advSearchResult.getCuts());
        RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.rv_child);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(baseViewHolder.itemView.getContext());
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdvSearchAdapter2(advSearchResult.getChildList()));
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvSearchAdapter1$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean onTouchEvent;
                onTouchEvent = BaseViewHolder.this.itemView.onTouchEvent(motionEvent);
                return onTouchEvent;
            }
        });
    }
}
