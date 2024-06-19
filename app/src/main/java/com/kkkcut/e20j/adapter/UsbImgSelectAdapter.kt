package com.kkkcut.e20j.adapter

import android.graphics.Bitmap
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.us.R
import java.io.File

/* loaded from: classes.dex */
class UsbImgSelectAdapter() :
    BaseQuickAdapter<File, BaseAutolayoutHolder>(R.layout.item_usb_img) {
    private var selectFile: File? = null

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, file: File) {
        baseAutolayoutHolder.setText(R.id.tv_name, file.getName())
            .setImageBitmap(R.id.iv_img, readBitmapFromFile(file.getPath(), 150, 100))
        baseAutolayoutHolder.addOnClickListener(R.id.ll_container)
        baseAutolayoutHolder.addOnClickListener(R.id.iv_delete)
        val file2: File? = this.selectFile
        if (file2 != null && file2 === file) {
            baseAutolayoutHolder.setBackgroundRes(
                R.id.ll_container,
                R.drawable.frame_image_selected
            )
        } else {
            baseAutolayoutHolder.setBackgroundRes(R.id.ll_container, 0)
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */ /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    fun readBitmapFromFile(r6: String?, r7: Int, r8: Int): Bitmap {
        /*
            r5 = this;
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            android.graphics.BitmapFactory.decodeFile(r6, r0)
            int r2 = r0.outWidth
            float r2 = (float) r2
            int r3 = r0.outHeight
            float r3 = (float) r3
            float r8 = (float) r8
            int r4 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r4 <= 0) goto L1e
            float r7 = (float) r7
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 <= 0) goto L1e
            float r2 = r2 / r7
            int r7 = (int) r2
            goto L2a
        L1e:
            int r7 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r7 >= 0) goto L29
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 <= 0) goto L29
            float r3 = r3 / r8
            int r7 = (int) r3
            goto L2a
        L29:
            r7 = 1
        L2a:
            if (r7 > 0) goto L2d
            goto L2e
        L2d:
            r1 = r7
        L2e:
            r7 = 0
            r0.inJustDecodeBounds = r7
            r0.inSampleSize = r1
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r0)
            return r6
        */
        throw UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.adapter.UsbImgSelectAdapter.readBitmapFromFile(java.lang.String, int, int):android.graphics.Bitmap")
    }

    fun addFrame(file: File?) {
        this.selectFile = file
        notifyDataSetChanged()
    }
}
