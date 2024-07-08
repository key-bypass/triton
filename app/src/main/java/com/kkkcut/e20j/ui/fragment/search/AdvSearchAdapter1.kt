package com.kkkcut.e20j.ui.fragment.search

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ResUpdateUtils

/* loaded from: classes.dex */
class AdvSearchAdapter1 :
    BaseQuickAdapter<AdvSearchResult, BaseViewHolder>(R.layout.item_advance_result) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseViewHolder: BaseViewHolder, advSearchResult: AdvSearchResult) {
        val fK_KeyID = advSearchResult.fK_KeyID
        ResUpdateUtils.showKeyImage(
            baseViewHolder.itemView.context, fK_KeyID, baseViewHolder.itemView.findViewById<View>(
                R.id.iv_thumb
            ) as ImageView
        )
        baseViewHolder.setText(R.id.tv_kid, fK_KeyID.toString())
            .setText(R.id.tv_cuts, advSearchResult.cuts)
        val recyclerView = baseViewHolder.getView<View>(R.id.rv_child) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(baseViewHolder.itemView.context)
        linearLayoutManager.orientation = 1
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = AdvSearchAdapter2(advSearchResult.childList!!)
        recyclerView.setOnTouchListener { view, motionEvent ->
            val onTouchEvent = view.onTouchEvent(motionEvent)
            onTouchEvent
        }
    }
}
