package com.spl.key

/* loaded from: classes.dex */
object DuplicatePathClass {
    private fun GetSetupValue(
        str: String,
        str2: String,
        i: Int,
        i2: Int,
        i3: Int,
        str3: String?,
        str4: String
    ): String {
        return "$str&$str2&$i&$i2&$i3&$str3&$str4"
    }

    /* JADX WARN: Multi-variable type inference failed */
    fun GetDimpleSingleStep(i: Int, i2: Int, i3: Int, i4: Int): Array<Array<String>> {
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add(
            GetSetupValue(
                "步骤1：探测" + i4 + "齿位",
                "1",
                i,
                i2,
                i3,
                Program.strMoveXYZSpeed,
                "DCDS:1,$i4"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向上",
                "0",
                0,
                0,
                -400,
                Program.strMoveXYZSpeed,
                "U:Z"
            )
        )
        var steps: List<Array<String>> = arrayList.map { str ->
            val split = str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            arrayOf(
                split[0],
                split[1],
                split[2],
                split[3],
                split[4],
                split[5],
                if (split.size == 7) split[6] else "",
                "",
                "0",
                "0",
                "0"
            )
        }
        return steps.toTypedArray()
    }
}