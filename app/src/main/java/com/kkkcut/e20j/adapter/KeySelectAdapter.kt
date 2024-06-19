package com.kkkcut.e20j.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.DbBean.Model
import com.kkkcut.e20j.DbBean.ModelSeries
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class KeySelectAdapter<T : MultiItemEntity?>(list: List<T>?) :
    BaseMultiItemQuickAdapter<T, BaseAutolayoutHolder?>(list) {
    init {
        addItemType(0, R.layout.item_manufacturer)
        addItemType(1, R.layout.item_model)
        addItemType(2, R.layout.item_years)
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    public override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, t: T) {
        if (t!!.itemType == 0) {
            val manufacturer = t as Manufacturer
            baseAutolayoutHolder.setText(R.id.tvManufacturer, manufacturer.manufacturerName)
                .setImageBitmap(
                    R.id.manuLogo, byteToBitmap(manufacturer.getManufacturerLogoImage())
                )
        }
        if (t.itemType == 1) {
            baseAutolayoutHolder.setText(R.id.tv_model, (t as Model).modelName)
        }
        t.itemType
    }

    private fun byteToBitmap(bArr: ByteArray?): Bitmap? {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.size, null)
        }
        return null
    }

    /* loaded from: classes.dex */
    internal inner class MyAdapter(private val modelSeriesList: List<ModelSeries>) : BaseAdapter() {
        // android.widget.Adapter
        override fun getItem(i: Int): Any {
            return this.modelSeriesList[i]
        }

        // android.widget.Adapter
        override fun getItemId(i: Int): Long {
            return 0L
        }

        // android.widget.Adapter
        override fun getCount(): Int {
            val list = this.modelSeriesList
            if (list != null) {
                return list.size
            }
            return 0
        }

        // android.widget.Adapter
        override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
            return view ?: View.inflate(viewGroup.context, R.layout.item_serie, null)
        }
    }

    companion object {
        const val MANUFACTURER: Int = 0
        const val MODEL: Int = 1
        const val MODEL_YEARS: Int = 2
    }
}