package com.kkkcut.e20j.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.technical.DataManufacturer
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class TechnicalInfoBradAdapter(protected var mContext: Context) :
    RecyclerView.Adapter<com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter.ViewHolder>() {
    private var listener: OnKeySelectItemClickListener? = null
    protected var mDatas: List<DataManufacturer>? = null
    protected var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    /* loaded from: classes.dex */
    interface OnKeySelectItemClickListener {
        fun onItemClick(i: Int)
    }

    fun setDatas(list: List<DataManufacturer>?) {
        this.mDatas = list
        notifyDataSetChanged()
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.item_technical_info, viewGroup, false))
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvContent.text = mDatas!![i].manufacturerName
        viewHolder.itemView.setOnClickListener { view ->
            // from class: com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter$$ExternalSyntheticLambda0
            // android.view.View.OnClickListener
            this@TechnicalInfoBradAdapter.m23xed71b319(i, view)
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-TechnicalInfoBradAdapter */
    /* synthetic */ fun m23xed71b319(i: Int, view: View?) {
        val onKeySelectItemClickListener = this.listener
        onKeySelectItemClickListener?.onItemClick(i)
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun getItemCount(): Int {
        val list = this.mDatas
        if (list != null) {
            return list.size
        }
        return 0
    }

    /* loaded from: classes.dex */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvContent: TextView = view.findViewById<View>(R.id.tv_content) as TextView
    }

    fun setOnKeySelectItemClickListener(onKeySelectItemClickListener: OnKeySelectItemClickListener?) {
        this.listener = onKeySelectItemClickListener
    }
}