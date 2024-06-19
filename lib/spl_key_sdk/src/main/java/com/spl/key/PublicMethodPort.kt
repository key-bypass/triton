package com.spl.key

import kotlin.math.abs

/* loaded from: classes.dex */
object PublicMethodPort {
    fun GetXYZPointRecalculate(i: Int, i2: Int): Int {
        val abs: Double
        if (i == 1) {
            abs = abs(i2 / Program.dXScale)
        } else if (i == 2) {
            abs = abs(i2 / Program.dYScale)
        } else {
            if (i != 3) {
                return 0
            }
            abs = abs(i2 / Program.dZScale)
        }
        return abs.toInt()
    }

    fun GetXYZXFPointRecalculate(i: Int, i2: Int): Int {
        val abs: Double
        if (i == 1) {
            abs = abs(i2 * Program.dXScale)
        } else if (i == 2) {
            abs = abs(i2 * Program.dYScale)
        } else {
            if (i != 3) {
                return 0
            }
            abs = abs(i2 * Program.dZScale)
        }
        return abs.toInt()
    }

    fun trimend(str: String, str2: String?): String {
        var str: String = str
        while (str.endsWith((str2)!!)) {
            str = str.substring(0, str.length - str2.length)
        }
        return str
    }
}
