package com.spl.key

import java.lang.reflect.Field
import java.text.NumberFormat
import java.util.Arrays.copyOf
import java.util.Collections
import java.util.Date
import kotlin.math.max

/* loaded from: classes.dex */
class mdSpaceClass : Object() {
    var rowNum: Int = 0
    var spaceNum: Int = 0
    var spaceValue: Int = 0

    companion object {
        fun sort(list: List<mdSpaceClass>, z: Boolean, vararg strArr: String) {
            Collections.sort(list) { t, t2 ->

                // from class: com.spl.key.mdSpaceClass.1
                // java.util.Comparator
                var i: Int = 0
                var i2: Int = 0
                while (true) {
                    try {
                        val strArr2: Array<String> = copyOf(strArr, strArr.size)
                        if (i >= strArr2.size || (compareObject(
                                strArr2.get(i),
                                z,
                                t,
                                t2
                            ).also({ i2 = it })) != 0
                        ) {
                            break
                        }
                        i++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                i2
            }
        }

        fun sort(list: List<mdSpaceClass>, strArr: Array<String>, zArr: BooleanArray) {
            if (strArr.size != zArr.size) {
                throw RuntimeException("Bad length of parameters")
            }
            // from class: com.spl.key.mdSpaceClass.2
// java.util.Comparator
            Collections.sort(list) { t, t2 ->
                var i: Int = 0
                var i2: Int = 0
                while (true) {
                    try {
                        val strArr2: Array<String> = strArr
                        if (i >= strArr2.size || (compareObject(
                                strArr2.get(i),
                                zArr.get(i),
                                t,
                                t2
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
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Throws(Exception::class)
        fun compareObject(str: String, z: Boolean, t: mdSpaceClass, t2: mdSpaceClass): Int {
            val forceGetFieldValue: Any = forceGetFieldValue(t, str)
            val forceGetFieldValue2: Any = forceGetFieldValue(t2, str)
            var obj: String = forceGetFieldValue.toString()
            var obj2: String = forceGetFieldValue2.toString()
            if ((forceGetFieldValue is Number) && (forceGetFieldValue2 is Number)) {
                val max: Int = max(obj.length.toDouble(), obj2.length.toDouble())
                    .toInt()
                val addZero2Str: String = addZero2Str(forceGetFieldValue as Number?, max)
                obj2 = addZero2Str(forceGetFieldValue2 as Number?, max)
                obj = addZero2Str
            } else if ((forceGetFieldValue is Date) && (forceGetFieldValue2 is Date)) {
                val time: Long = forceGetFieldValue.getTime()
                val time2: Long = forceGetFieldValue2.getTime()
                val length: Int = max(time.toDouble(), time2.toDouble()).toString().length
                obj = addZero2Str(time, length)
                obj2 = addZero2Str(time2, length)
            }
            if (z) {
                return obj.compareTo(obj2)
            }
            return obj2.compareTo(obj)
        }

        fun addZero2Str(number: Number?, i: Int): String {
            val numberFormat: NumberFormat = NumberFormat.getInstance()
            numberFormat.setGroupingUsed(false)
            numberFormat.setMaximumIntegerDigits(i)
            numberFormat.setMinimumIntegerDigits(i)
            return numberFormat.format(number)
        }

        @Throws(Exception::class)
        fun <T: Object, V> forceGetFieldValue(obj: T, fieldName: String): V {
            val declaredField: Field = obj.javaClass.getDeclaredField(fieldName)
            val isAccessible: Boolean = declaredField.isAccessible()
            if (!isAccessible) {
                declaredField.setAccessible(true)
                val obj2: Any? = declaredField.get(obj)
                declaredField.setAccessible(isAccessible)
                return obj2 as V
            }
            return declaredField.get(obj) as V
        }
    }
}
