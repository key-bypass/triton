package com.kkkcut.e20j.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class BrandVisibilityAdapter :
    BaseQuickAdapter<Manufacturer, BaseAutolayoutHolder>(R.layout.item_manufacturer_visibility) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, manufacturer: Manufacturer) {
        val byteToBitmap = byteToBitmap(manufacturer.getManufacturerLogoImage())
        if (byteToBitmap != null) {
            baseAutolayoutHolder.setImageBitmap(R.id.manuLogo, byteToBitmap)
        }
        baseAutolayoutHolder.setText(R.id.tvManufacturer, manufacturer.manufacturerName)
        baseAutolayoutHolder.setOnCheckedChangeListener(
            R.id.checkbox_brand_visibility
        ) { compoundButton, z ->
            // from class: com.kkkcut.e20j.adapter.BrandVisibilityAdapter.1
            // android.widget.CompoundButton.OnCheckedChangeListener
            manufacturer.isChecked = z
        }
        baseAutolayoutHolder.setChecked(R.id.checkbox_brand_visibility, manufacturer.isChecked)
    }

    private fun byteToBitmap(bArr: ByteArray?): Bitmap? {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.size, null)
        }
        return null
    }
}