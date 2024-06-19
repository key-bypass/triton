package com.spl.key

import java.text.NumberFormat
import java.util.Arrays.copyOf
import java.util.Collections
import java.util.Date
import kotlin.math.max

/* loaded from: classes.dex */
class mdDepthClass: Object() {
    var spaceNum: Int = 0
    var depthValue: Int = 0
    var rowNum: Int = 0

    companion object {
        fun sort(list: List<mdDepthClass>, z: Boolean, vararg strArr: String) {
            Collections.sort(list, Comparator { t, t2 ->

                // from class: com.spl.key.mdDepthClass.1
                // java.util.Comparator
                var i = 0
                var i2 = 0
                while (true) {
                    try {
                        val strArr2: Array<String> = copyOf(strArr, strArr.size)
                        if (i >= strArr2.size || (compareObject(
                                strArr2[i], z, t, t2
                            ).also { i2 = it }) != 0
                        ) {
                            break
                        }
                        i++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                i2
            })
        }

        fun sort(list: List<mdDepthClass>, strArr: Array<String>, zArr: BooleanArray) {
            if (strArr.size != zArr.size) {
                throw RuntimeException("属性数组元素个数和升降序数组元素个数不相等")
            }
            Collections.sort(list, object : Comparator<mdDepthClass> {
                // from class: com.spl.key.mdDepthClass.2
                // java.util.Comparator
                override fun compare(t: mdDepthClass, t2: mdDepthClass): Int {
                    var i = 0
                    var i2 = 0
                    while (true) {
                        try {
                            val strArr2 = strArr
                            if (i >= strArr2.size || (compareObject(
                                    strArr2[i], zArr[i], t, t2
                                ).also { i2 = it }) != 0
                            ) {
                                break
                            }
                            i++
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    return i2
                }
            })
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Throws(Exception::class)
        fun compareObject(str: String, z: Boolean, t: mdDepthClass, t2: mdDepthClass): Int {
            val forceGetFieldValue: Object = forceGetFieldValue(t, str)
            val forceGetFieldValue2: Object = forceGetFieldValue(t2, str)
            var obj = forceGetFieldValue.toString()
            var obj2 = forceGetFieldValue2.toString()
            if ((forceGetFieldValue is Number) && (forceGetFieldValue2 is Number)) {
                val max = max(obj.length.toDouble(), obj2.length.toDouble())
                    .toInt()
                val addZero2Str = addZero2Str(forceGetFieldValue, max)
                obj2 = addZero2Str(forceGetFieldValue2, max)
                obj = addZero2Str
            } else if ((forceGetFieldValue is Date) && (forceGetFieldValue2 is Date)) {
                val time = forceGetFieldValue.time
                val time2 = forceGetFieldValue2.time
                val length = max(time.toDouble(), time2.toDouble()).toString().length
                obj = addZero2Str(time, length)
                obj2 = addZero2Str(time2, length)
            }
            if (z) {
                return obj.compareTo(obj2)
            }
            return obj2.compareTo(obj)
        }

        fun addZero2Str(number: Number?, i: Int): String {
            val numberFormat = NumberFormat.getInstance()
            numberFormat.isGroupingUsed = false
            numberFormat.maximumIntegerDigits = i
            numberFormat.minimumIntegerDigits = i
            return numberFormat.format(number)
        }

        @Throws(Exception::class)
        fun <T : Object, V : Object> forceGetFieldValue(obj: T, str: String): V {
            val declaredField = obj.javaClass.getDeclaredField(str)
            val isAccessible = declaredField.isAccessible
            if (!isAccessible) {
                declaredField.isAccessible = true
                val obj2 = declaredField[obj]
                declaredField.isAccessible = isAccessible
                return obj2 as V
            }
            return declaredField[obj] as V
        }
    }
}
