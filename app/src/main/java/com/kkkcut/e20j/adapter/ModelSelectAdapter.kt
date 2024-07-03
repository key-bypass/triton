package com.kkkcut.e20j.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.Model
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class ModelSelectAdapter(protected var mContext: Context) : RecyclerView.Adapter<com.kkkcut.e20j.adapter.ModelSelectAdapter.ViewHolder>() {
    private var listener: OnKeySelectItemClickListener? = null
    protected var mDatas: List<Model> = listOf()
    protected var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    /* loaded from: classes.dex */
    interface OnKeySelectItemClickListener {
        fun onItemClick(i: Int)
    }

    var datas: List<Model>
        get() = this.mDatas
        set(list) {
            this.mDatas = list
            notifyDataSetChanged()
        }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.item_model, viewGroup, false))
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val model = mDatas!![i]
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(model.modelName_CN)) {
            viewHolder.tvModel.text = model.modelName_CN
        } else {
            viewHolder.tvModel.text = model.modelName
        }
        viewHolder.itemView.setOnClickListener { view ->
            // from class: com.kkkcut.e20j.adapter.ModelSelectAdapter$$ExternalSyntheticLambda0
            // android.view.View.OnClickListener
            this@ModelSelectAdapter.m20x695b672(i, view)
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-ModelSelectAdapter */
    /* synthetic */ fun m20x695b672(i: Int, view: View?) {
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
        var tvModel: TextView = view.findViewById<View>(R.id.tv_model) as TextView

        init {
            AutoUtils.auto(view)
        }
    }

    fun setOnKeySelectItemClickListener(onKeySelectItemClickListener: OnKeySelectItemClickListener?) {
        this.listener = onKeySelectItemClickListener
    }

    companion object {
        private const val TAG = "ModelSelectAdapter"
    }
}