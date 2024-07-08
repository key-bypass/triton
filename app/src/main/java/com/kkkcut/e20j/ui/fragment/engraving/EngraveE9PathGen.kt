package com.kkkcut.e20j.ui.fragment.engraving

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Environment
import androidx.core.view.ViewCompat
import com.cutting.machine.bean.StepBean
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.speed.Speed
import com.kkkcut.e20j.utils.BitmapUtil
import java.io.File

/* loaded from: classes.dex */
object EngraveE9PathGen {
    /* JADX WARN: Multi-variable type inference failed */ /* JADX WARN: Type inference failed for: r5v5 */
    fun bitmapToPath(bitmap: Bitmap?, engraveParam: EngraveParam): List<StepBean> {
        var i: Int
        var i2: Int
        var i3: Int
        val arrayList = ArrayList<StepBean>()
        val binaryBitmap = binaryBitmap(bitmap)
        BitmapUtil.saveBitmap(
            binaryBitmap,
            File(Environment.getExternalStorageDirectory(), "test.bmp").path
        )
        val width = binaryBitmap!!.width
        val height = binaryBitmap.height
        var i4 = 0
        var i5 = 0
        while (true) {
            val i6 = width - 1
            if (i5 >= i6) {
                val stepBean = arrayList[i4]
                arrayList.add(
                    0,
                    StepBean("", 0, stepBean.x, stepBean.y, stepBean.z - 100, "", "", false)
                )
                arrayList.add(1, StepBean(0, 0, 0, 0, "", "U:X;U:Y;U:Z;SUP:1,8000"))
                arrayList.add(StepBean(0, 0, 0, 0, "", "U:X;U:Y;U:Z;SUP:0,0"))
                arrayList.add(StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""))
                arrayList.add(StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""))
                return arrayList
            }
            var i7 = 0
            var z = i4 != 0
            while (true) {
                val i8 = height - 1
                if (i7 < i8) {
                    if (binaryBitmap.getPixel(i5, i7) == -1) {
                        addCutPoint(arrayList, i5, i7, engraveParam, z)
                        addCutPoint(arrayList, i5, i7, engraveParam, true)
                        var i9 = ViewCompat.MEASURED_STATE_MASK
                        binaryBitmap.setPixel(i5, i7, ViewCompat.MEASURED_STATE_MASK)
                        var i10 = i5
                        var i11 = i7
                        while (true) {
                            if (i10 > 0) {
                                i = i10 - 1
                                if (binaryBitmap.getPixel(i, i11) == -1) {
                                    addCutPoint(arrayList, i, i11, engraveParam, true)
                                    binaryBitmap.setPixel(i, i11, i9)
                                    i10 = i
                                }
                            }
                            if (i10 > 0 && i11 < i8) {
                                i = i10 - 1
                                i2 = i11 + 1
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true)
                                    i9 = ViewCompat.MEASURED_STATE_MASK
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK)
                                    i11 = i2
                                    i10 = i
                                } else {
                                    i9 = ViewCompat.MEASURED_STATE_MASK
                                }
                            }
                            if (i11 < i8) {
                                i3 = i11 + 1
                                if (binaryBitmap.getPixel(i10, i3) == -1) {
                                    addCutPoint(arrayList, i10, i3, engraveParam, true)
                                    binaryBitmap.setPixel(i10, i3, i9)
                                    i11 = i3
                                }
                            }
                            if (i10 < i6 && i11 < i8) {
                                i = i10 + 1
                                i2 = i11 + 1
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true)
                                    i9 = ViewCompat.MEASURED_STATE_MASK
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK)
                                    i11 = i2
                                    i10 = i
                                }
                            }
                            i9 = ViewCompat.MEASURED_STATE_MASK
                            if (i10 < i6) {
                                i = i10 + 1
                                if (binaryBitmap.getPixel(i, i11) == -1) {
                                    addCutPoint(arrayList, i, i11, engraveParam, true)
                                    binaryBitmap.setPixel(i, i11, ViewCompat.MEASURED_STATE_MASK)
                                    i10 = i
                                }
                            }
                            if (i10 < i6 && i11 > 0) {
                                i = i10 + 1
                                i2 = i11 - 1
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true)
                                    i9 = ViewCompat.MEASURED_STATE_MASK
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK)
                                    i11 = i2
                                    i10 = i
                                }
                            }
                            i9 = ViewCompat.MEASURED_STATE_MASK
                            if (i11 > 0) {
                                i3 = i11 - 1
                                if (binaryBitmap.getPixel(i10, i3) == -1) {
                                    addCutPoint(arrayList, i10, i3, engraveParam, true)
                                    binaryBitmap.setPixel(i10, i3, ViewCompat.MEASURED_STATE_MASK)
                                    i11 = i3
                                }
                            }
                            if (i10 <= 0 || i11 <= 0) {
                                break
                            }
                            i = i10 - 1
                            i2 = i11 - 1
                            if (binaryBitmap.getPixel(i, i2) != -1) {
                                break
                            }
                            addCutPoint(arrayList, i, i2, engraveParam, true)
                            i9 = ViewCompat.MEASURED_STATE_MASK
                            binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK)
                            i11 = i2
                            i10 = i
                        }
                        z = false
                        addCutPoint(arrayList, i10, i11, engraveParam, false)
                    }
                    i7++
                    z = z
                } else {
                    break
                }
            }
            i5++
            i4 = if (z) 1 else 0
            break
        }
        return arrayList
    }

    fun detectKeyPosition(engraveParam: EngravePathGen.EngraveParam): List<StepBean> {
        val arrayList = ArrayList<StepBean>()
        arrayList.add(
            StepBean(
                "移动到夹具上方",
                0,
                (engraveParam.clampOriginX - (450.0f / MachineData.dYScale)).toInt(),
                (engraveParam.clampOriginY - (360.0f / MachineData.dYScale)).toInt(),
                0,
                "8000,8000,3000",
                "C:5,X;C:5,Y;C:5,Z",
                false
            )
        )
        arrayList.add(StepBean("探测夹具表面", 1, 0, 0, 3500, "4000,4000,3000", "S:1,Z;"))
        arrayList.add(StepBean("离开夹具表面", 0, 0, 0, -50, "8000,8000,3000", "U:Z;"))
        arrayList.add(StepBean("探测右夹块位置", 1, -2500, 0, 0, "4000,4000,3000", "U:X;"))
        arrayList.add(StepBean("离开右夹块", 0, 50, 0, 0, "8000,8000,3000", "S:4,X;C:3,R,X-4"))
        arrayList.add(StepBean("Z轴复位", 998, 0, 0, 0, "8000,8000,3000", ""))
        arrayList.add(StepBean("XY轴复位", 999, 0, 0, 0, "8000,8000,3000", ""))
        return arrayList
    }

    fun detectCutterHigh(bitmap: Bitmap, engraveParam: EngraveParam): List<StepBean> {
        val arrayList= ArrayList<StepBean>()
        val width = (bitmap.width / 2) * 10
        val height = (bitmap.height / 2) * 10
        arrayList.add(
            StepBean(
                "移动到切割区域中点",
                0,
                (engraveParam.clampOriginX + ((height + engraveParam.pictureOriginY) / MachineData.dXScale) + engraveParam.dcX).toInt(),
                (engraveParam.clampOriginY + ((width + engraveParam.pictureOriginX) / MachineData.dYScale) + engraveParam.dcY).toInt(),
                0,
                "8000,8000,3000",
                "C:5,X;C:5,Y;",
                false
            )
        )
        arrayList.add(StepBean("探测钥匙表面", 1, 0, 0, 4500, "4000,4000,3000", "U:Z;"))
        arrayList.add(StepBean("离开钥匙表面", 0, 0, 0, -100, "8000,8000,3000", "U:Z;C:3,K,Z-2"))
        return arrayList
    }

    private fun binaryBitmap(bitmap: Bitmap?): Bitmap? {
        bitmap!!.isPremultiplied = false
        val width = bitmap.width
        val height = bitmap.height
        for (i in 0 until width) {
            for (i2 in 0 until height) {
                val pixel = bitmap.getPixel(i, i2)
                bitmap.setPixel(
                    i,
                    i2,
                    if (((((Color.red(pixel) * 30) + (Color.green(pixel) * 59)) + (Color.blue(pixel) * 11)) + 50) / 100 > 220) ViewCompat.MEASURED_STATE_MASK else -1
                )
            }
        }
        return bitmap
    }

    private fun addCutPoint(
        list: MutableList<StepBean>,
        i: Int,
        i2: Int,
        engraveParam: EngraveParam,
        z: Boolean
    ) {
        val stepBean: StepBean
        val clampOriginX = engraveParam.clampOriginX
        val clampOriginY = engraveParam.clampOriginY
        val dcX = engraveParam.dcX
        val dcY = engraveParam.dcY
        val pictureOriginY =
            (clampOriginX + (((i2 * 10) + engraveParam.pictureOriginY) / MachineData.dXScale) + dcX).toInt()
        val pictureOriginX =
            (clampOriginY + (((i * 10) + engraveParam.pictureOriginX) / MachineData.dYScale) + dcY).toInt()
        val keyFaceZ = engraveParam.keyFaceZ
        stepBean = if (z) {
            StepBean(
                "切割",
                0,
                pictureOriginY,
                pictureOriginX,
                keyFaceZ + engraveParam.cutDepth,
                Speed.get_Speed_Engrave(),
                "C:5,X;C:5,Y;C:5,Z;CUTSM",
                false
            )
        } else {
            StepBean(
                "空走",
                0,
                pictureOriginY,
                pictureOriginX,
                (keyFaceZ - (20.0f / MachineData.dZScale)).toInt(),
                Speed.get_Speed_Engrave(),
                "C:5,X;C:5,Y;C:5,Z;CUTSM",
                false
            )
        }
        list.add(stepBean)
    }

    /* loaded from: classes.dex */
    class EngraveParam {
        var clampOriginX: Int
        var clampOriginY: Int
        var cutDepth: Int = 0
        var cutSpeed: Int = 0
        var dcX: Int
        var dcY: Int
        var keyFaceZ: Int
        var pictureOriginX: Int
        var pictureOriginY: Int

        constructor(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int, i7: Int) {
            this.clampOriginX = i
            this.clampOriginY = i2
            this.pictureOriginX = i3
            this.pictureOriginY = i4
            this.dcX = i5
            this.dcY = i6
            this.keyFaceZ = i7
        }

        constructor(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int, i7: Int, i8: Int) {
            this.clampOriginX = i
            this.clampOriginY = i2
            this.pictureOriginX = i3
            this.pictureOriginY = i4
            this.dcX = i5
            this.dcY = i6
            this.keyFaceZ = i7
            this.cutDepth = i8
        }

        constructor(
            i: Int,
            i2: Int,
            i3: Int,
            i4: Int,
            i5: Int,
            i6: Int,
            i7: Int,
            i8: Int,
            i9: Int
        ) {
            this.clampOriginX = i
            this.clampOriginY = i2
            this.pictureOriginX = i3
            this.pictureOriginY = i4
            this.dcX = i5
            this.dcY = i6
            this.keyFaceZ = i7
            this.cutDepth = i8
            this.cutSpeed = i9
        }
    }
}
