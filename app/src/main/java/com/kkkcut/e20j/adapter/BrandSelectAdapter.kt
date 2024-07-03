package com.kkkcut.e20j.adapter

import com.cutting.machine.MachineInfo
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class BrandSelectAdapter(protected var mContext: Context) : RecyclerView.Adapter<com.kkkcut.e20j.adapter.BrandSelectAdapter.ViewHolder>() {
    private var listener: OnKeySelectItemClickListener? = null
    protected var mDatas: List<Manufacturer> = listOf<Manufacturer>();
    protected var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    /* loaded from: classes.dex */
    interface OnKeySelectItemClickListener {
        fun onItemClick(i: Int)
    }

    fun setDatas(list: List<Manufacturer>) {
        this.mDatas = list
        notifyDataSetChanged()
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.item_manufacturer, viewGroup, false))
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val manufacturer = mDatas!![i]
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(manufacturer.manufacturerNameCN)) {
            viewHolder.tvManuFacturer.text = manufacturer.manufacturerNameCN
        } else {
            viewHolder.tvManuFacturer.text = manufacturer.manufacturerName
        }
        viewHolder.itemView.setOnClickListener { view ->
            // from class: com.kkkcut.e20j.adapter.BrandSelectAdapter$$ExternalSyntheticLambda0
            // android.view.View.OnClickListener
            this@BrandSelectAdapter.m18xfb616ed4(i, view)
        }
        if (MachineInfo.isE20Us(this.mContext)) {
            viewHolder.avatar.visibility = 8
            return
        }
        val byteToBitmap = byteToBitmap(manufacturer.getManufacturerLogoImage())
        if (byteToBitmap != null) {
            viewHolder.avatar.setImageBitmap(byteToBitmap)
        } else {
            viewHolder.avatar.setImageResource(0)
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-BrandSelectAdapter */
    /* synthetic */ fun m18xfb616ed4(i: Int, view: View?) {
        val onKeySelectItemClickListener = this.listener
        onKeySelectItemClickListener?.onItemClick(i)
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun getItemCount(): Int {
    return this.mDatas.size
    }

    /* loaded from: classes.dex */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar: ImageView = view.findViewById<View>(R.id.manuLogo) as ImageView

        var tvManuFacturer: TextView = view.findViewById<View>(R.id.tvManufacturer) as TextView

        init {
            AutoUtils.auto(view)
        }
    }

    fun setOnKeySelectItemClickListener(onKeySelectItemClickListener: OnKeySelectItemClickListener?) {
        this.listener = onKeySelectItemClickListener
    }

    private fun byteToBitmap(bArr: ByteArray?): Bitmap? {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.size, null)
        }
        return null
    }
}