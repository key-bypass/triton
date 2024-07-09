package com.spl.key

import android.text.TextUtils

/* loaded from: classes.dex */
object SpecificParamUtils {
    val CUTTER_SIZE: String = "cutter"
    @JvmField
    val CUT_DEPTH: String = "cut_depth"
    val DECODER_SIZE: String = "cutter"
    @JvmField
    val EXTRA_CUT: String = "extra_cut"
    @JvmField
    val GROOVE: String = "groove"
    @JvmField
    val GUIDE: String = "guide"
    @JvmField
    val INNER_CUT_ANGLE: String = "inner_cut_angle"
    @JvmField
    val LAST_BITTING: String = "last_bitting"
    val LOCKED: String = "locked"
    @JvmField
    val NOSE: String = "nose"
    @JvmField
    val SIDE: String = "side"
    @JvmField
    val SINGLE_SIDE_ANGLE: String = "spacewidthangles"
    @JvmField
    val VARIABLE_SPACE: String = "variable_space"


    fun getParam(str: String, str2: String): String? {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return ""
        }
        val split: Array<String> =
            str.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (i in split.indices) {
            if ((split.get(i).split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    .get(0) == str2)
            ) {
                return split.get(i).split(":".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().get(1)
            }
        }
        return null
    }

    fun putParam(str: String?, str2: String, str3: String): String {
        var str: String? = str
        var str4: String = ""
        if (str == null) {
            str = ""
        }
        if (TextUtils.isEmpty(str2)) {
            return str
        }
        if (!str.contains(str2)) {
            if (TextUtils.isEmpty(str3)) {
                return str
            }
            return str + str2 + ":" + str3 + ";"
        }
        val split: Array<String> =
            str.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (i in split.indices) {
            if (!(split.get(i).split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    .get(0) == str2)
            ) {
                str4 = str4 + split.get(i) + ";"
            } else if (!TextUtils.isEmpty(str3)) {
                str4 = str4 + str2 + ":" + str3 + ";"
            }
        }
        return str4
    }
}