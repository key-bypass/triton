package com.kkkcut.e20j.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.ModelSeries
import com.kkkcut.e20j.DbBean.ModelYear
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class SeriesSelectAdapter(protected var mContext: Context) : RecyclerView.Adapter<com.kkkcut.e20j.adapter.SeriesSelectAdapter.ViewHolder>() {
    private var itemChildClickListener: OnItemChildClickListener? = null
    private var itemClickListener: OnItemClickListener? = null
    protected var mDatas: List<ModelYear>? = null
    protected var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    /* loaded from: classes.dex */
    interface OnItemChildClickListener {
        fun onItemChildClick(str: String?, modelSeries: ModelSeries?)
    }

    /* loaded from: classes.dex */
    interface OnItemClickListener {
        fun onItemClick(view: View?, i: Int, str: String?, i2: Int)
    }

    var datas: List<ModelYear>?
        get() = this.mDatas
        set(list) {
            this.mDatas = list
            notifyDataSetChanged()
        }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.item_years, viewGroup, false))
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val str: String
        val childCount = viewHolder.llContainer.childCount
        if (childCount > 1) {
            viewHolder.llContainer.removeViews(1, childCount - 1)
        }
        val modelYear = mDatas!![i]
        val fromYear = modelYear.fromYear
        val toYear = modelYear.toYear
        str = if (yearIsBlank(fromYear) && !yearIsBlank(toYear)) {
            "Up to $toYear"
        } else if (yearIsBlank(toYear) && !yearIsBlank(fromYear)) {
            "From $fromYear"
        } else if (yearIsBlank(toYear) && yearIsBlank(fromYear)) {
            "no year info"
        } else {
            "$fromYear~$toYear"
        }
        val str2 = str
        viewHolder.tvYearInfo.text = str2
        viewHolder.llYears.setOnClickListener { view ->
            // from class: com.kkkcut.e20j.adapter.SeriesSelectAdapter$$ExternalSyntheticLambda0
            // android.view.View.OnClickListener
            this@SeriesSelectAdapter.m22x48994614(
                viewHolder,
                modelYear,
                str2,
                i,
                view
            )
        }
        if (itemCount == 1) {
            viewHolder.llYears.performClick()
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-SeriesSelectAdapter */
    /* synthetic */ fun m22x48994614(
        viewHolder: ViewHolder,
        modelYear: ModelYear,
        str: String?,
        i: Int,
        view: View?
    ) {
        val onItemClickListener = this.itemClickListener
        onItemClickListener?.onItemClick(viewHolder.itemView, modelYear.modelYearID, str, i)
    }

    private fun yearIsBlank(str: String): Boolean {
        return TextUtils.isEmpty(str) || "0" == str
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
        var arrow: ImageView = view.findViewById<View>(R.id.iv_arrow) as ImageView
        var llContainer: LinearLayout = view.findViewById<View>(R.id.ll_space_a) as LinearLayout

        var llYears: LinearLayout = view.findViewById<View>(R.id.ll_years) as LinearLayout

        var tvYearInfo: TextView = view.findViewById<View>(R.id.tv_year_info) as TextView
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.itemClickListener = onItemClickListener
    }

    fun setOnItemChildClickListener(onItemChildClickListener: OnItemChildClickListener?) {
        this.itemChildClickListener = onItemChildClickListener
    }
}