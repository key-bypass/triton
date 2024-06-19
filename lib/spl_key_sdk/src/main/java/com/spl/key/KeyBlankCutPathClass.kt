package com.spl.key

import androidx.core.app.NotificationManagerCompat
import com.spl.key.JawClass.JAW_S8
import com.spl.key.Key.enumMachineType
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

/* loaded from: classes.dex */
object KeyBlankCutPathClass {
    fun GetKeyBlankCut_TopPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>> {
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList: ArrayList<String> = ArrayList()
        val GetTopCutPointList = GetTopCutPointList(mdkeylocationpointclass, mdkeyblankclass, i2)
        // Step 1: The milling cutter stops at a point outside the key
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙外的点位",
                "0",
                GetTopCutPointList[0].x,
                GetTopCutPointList[0].y,
                0,
                Program._Speed_SpindleTurnOff_Move,
                " "
            )
        )
        // Step 2: Z-axis down
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                GetTopCutPointList[0].z + 53,
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        // Step 3: Start the Spindle
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i3 = 4
        var z = false
        for (i4 in 0..5) {
            if (!z) {
                var i5 = 0
                while (i5 < GetTopCutPointList.size) {
                    val sb = StringBuilder()
                    // step
                    sb.append("步骤")
                    sb.append(i3)
                    sb.append("：第")
                    val i6 = i4 + 1
                    sb.append(i6)
                    sb.append("层，第")
                    val i7 = i5 + 1
                    sb.append(i7)
                    sb.append("点位")
                    arrayList.add(
                        GetSetupValue(
                            sb.toString(),
                            "2",
                            GetTopCutPointList[i5].x,
                            GetTopCutPointList[i5].y,
                            GetTopCutPointList[i5].z + (i6 * 53),
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    i3++
                    i5 = i7
                }
                z = true
            } else if (z) {
                for (size in GetTopCutPointList.size - 1 downTo -1 + 1) {
                    val sb2 = StringBuilder()
                    sb2.append("步骤")
                    sb2.append(i3)
                    sb2.append("：第")
                    val i8 = i4 + 1
                    sb2.append(i8)
                    sb2.append("层，第")
                    sb2.append(GetTopCutPointList.size - size)
                    sb2.append("点位")
                    arrayList.add(
                        GetSetupValue(
                            sb2.toString(),
                            "2",
                            GetTopCutPointList[size].x,
                            GetTopCutPointList[size].y,
                            GetTopCutPointList[size].z + (i8 * 53),
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    i3++
                }
                z = false
            }
        }
        val i9 = i2 / 2
        arrayList.add(
            GetSetupValue(
                "步骤$i3：铣刀停留在钥匙外的点位",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) + (mdkeyblankclass.keyWidth / 2) + i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i9,
                0,
                Program._Speed_CuttingSharpen,
                "C:5,X;C:5,Y"
            )
        )
        val i10 = i3 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i10：Z轴向下",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) + (mdkeyblankclass.keyWidth / 2) + i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i9,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyThick,
                Program._Speed_SpindleTurnOn_Move,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        var i11 = i10 + 1
        val GetCutThickPointList =
            GetCutThickPointList(mdkeylocationpointclass, mdkeyblankclass, i2, jaw_s8)
        var i12 = 0
        while (i12 < GetCutThickPointList.size) {
            val sb3 = StringBuilder()
            sb3.append("步骤")
            sb3.append(i11)
            sb3.append("：第")
            val i13 = i12 + 1
            sb3.append(i13)
            sb3.append("刀修钥匙头厚度")
            arrayList.add(
                GetSetupValue(
                    sb3.toString(),
                    "2",
                    GetCutThickPointList[i12].x,
                    GetCutThickPointList[i12].y,
                    GetCutThickPointList[i12].z,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            i11++
            i12 = i13
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i11：Z轴向上",
                "2",
                0,
                0,
                -300,
                Program._Speed_SpindleTurnOn_ZUp,
                "U:X;U:Y;U:Z"
            )
        )
        val i14 = i11 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i14：移动到钥匙头左边",
                "2",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) + (mdkeyblankclass.keyWidth / 2) + i2,
                ((PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i9) - mdkeyblankclass.keyCardSlotPosition,
                0,
                Program._Speed_SpindleTurnOn_Move,
                "C:5,X;C:5,Y;U:Z"
            )
        )
        val i15 = i14 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i15：Z轴向下",
                "2",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyCardSlotThick,
                Program._Speed_SpindleTurnOn_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        val i16 = i15 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i16：切到钥头槽右边",
                "2",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) - (mdkeyblankclass.keyWidth / 2)) - i2,
                ((PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i9) - mdkeyblankclass.keyCardSlotPosition,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyCardSlotThick,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        val i17 = i16 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i17：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        val i18 = i17 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i18：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                " "
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i18 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                " "
            )
        )
        val strArr: Array<Array<String?>?> = arrayOfNulls(arrayList.size)
        val result = arrayList.map { str ->
            val split =
                str.toString().split("&".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()

            arrayOf(
                split[0],
                split[1],
                split[2],
                split[3],
                split[4],
                split[5], if (split.size == 7) split[6] else "", "",
                "0",
                "0",
                "0"
            )
        }
        return result.toTypedArray()
    }

    fun GetKeyBlankCut_WidthThickPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val GetCutKeyWidthThickPointList = GetCutKeyWidthThickPointList(
            mdkeylocationpointclass,
            mdkeyblankclass,
            i2,
            jaw_s8
        )
            ?: return null
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙外的点位",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft),
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideTip) - i2,
                0,
                Program._Speed_SpindleTurnOff_Move,
                " "
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                GetCutKeyWidthThickPointList[0].z,
                Program._Speed_SpindleTurnOff_ZUp,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i3 = 0
        var i4 = 4
        while (i3 < GetCutKeyWidthThickPointList.size) {
            val sb = StringBuilder()
            sb.append("步骤")
            sb.append(i4)
            sb.append("：第")
            val i5 = i3 + 1
            sb.append(i5)
            sb.append("点位,")
            sb.append(GetCutKeyWidthThickPointList[i3].des)
            arrayList.add(
                GetSetupValue(
                    sb.toString(),
                    "2",
                    GetCutKeyWidthThickPointList[i3].x,
                    GetCutKeyWidthThickPointList[i3].y,
                    GetCutKeyWidthThickPointList[i3].z,
                    GetCutKeyWidthThickPointList[i3].xYZSpeed,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            i4++
            i3 = i5
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i4：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        val i6 = i4 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i6：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                " "
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i6 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                " "
            )
        )
        val result = arrayList.map { str ->
            val split = str.toString().split("&".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            arrayOf( split[0],
            split[1],
            split[2],
            split[3],
            split[4],
            split[5], if (split.size == 7) split[6] else "",
            "",
            "0",
             "0",
            "0")
        }
        return result.toTypedArray()
    }

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

    private fun GetTopCutPointList(
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i: Int
    ): List<mdXYZClass> {
        val arrayList= ArrayList<mdXYZClass>()
        mdXYZClass()
        val mdxyzclass = mdXYZClass()
        mdxyzclass.x =
            PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft) + 1000
        val i2 = i / 2
        mdxyzclass.y =
            PublicMethodPort.GetXYZXFPointRecalculate(2, mdkeylocationpointclass.sideTip) - 200 - i2
        mdxyzclass.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass)
        val mdxyzclass2 = mdXYZClass()
        mdxyzclass2.x = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideKeyCenter
        ) + (mdkeyblankclass.keyWidth / 2) + i2
        mdxyzclass2.y =
            PublicMethodPort.GetXYZXFPointRecalculate(2, mdkeylocationpointclass.sideTip) - 200 - i2
        mdxyzclass2.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass2)
        val mdxyzclass3 = mdXYZClass()
        mdxyzclass3.x = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideKeyCenter
        ) + (mdkeyblankclass.keyWidth / 2) + i2
        mdxyzclass3.y = (PublicMethodPort.GetXYZXFPointRecalculate(
            2,
            mdkeylocationpointclass.sideTip
        ) - 200) - mdkeyblankclass.keylength - i2
        mdxyzclass3.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass3)
        val mdxyzclass4 = mdXYZClass()
        mdxyzclass4.x = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideKeyCenter
        ) - (mdkeyblankclass.keyWidth / 2) - i2
        mdxyzclass4.y = (PublicMethodPort.GetXYZXFPointRecalculate(
            2,
            mdkeylocationpointclass.sideTip
        ) - 200) - mdkeyblankclass.keylength - i2
        mdxyzclass4.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass4)
        val mdxyzclass5 = mdXYZClass()
        mdxyzclass5.x = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideKeyCenter
        ) - (mdkeyblankclass.keyWidth / 2) - i2
        mdxyzclass5.y =
            PublicMethodPort.GetXYZXFPointRecalculate(2, mdkeylocationpointclass.sideTip) - 200 - i2
        mdxyzclass5.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass5)
        val mdxyzclass6 = mdXYZClass()
        mdxyzclass6.x = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideRight
        ) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED
        mdxyzclass6.y =
            PublicMethodPort.GetXYZXFPointRecalculate(2, mdkeylocationpointclass.sideTip) - 200 - i2
        mdxyzclass6.z =
            PublicMethodPort.GetXYZXFPointRecalculate(
                3,
                mdkeylocationpointclass.sideKeyTopSurface_Cut
            )
        arrayList.add(mdxyzclass6)
        return arrayList
    }

    private fun GetCutThickPointList(
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i: Int,
        jaw_s8: JAW_S8
    ): List<mdXYZClass> {
        val arrayList= ArrayList<mdXYZClass>()
        mdXYZClass()
        val keylength = (mdkeyblankclass.keylength / i) + 1
        var z = false
        for (i2 in 0 until keylength) {
            if (!z) {
                val mdxyzclass = mdXYZClass()
                mdxyzclass.x = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) + (mdkeyblankclass.keyWidth / 2) + i
                val i3 = i / 2
                val i4 = i - 20
                val i5 = i4 * i2
                mdxyzclass.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i3 - i5
                mdxyzclass.z = PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyThick
                arrayList.add(mdxyzclass)
                val mdxyzclass2 = mdXYZClass()
                mdxyzclass2.x = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) - (mdkeyblankclass.keyWidth / 2) - i
                mdxyzclass2.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i3 - i5
                mdxyzclass2.z = PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyThick
                arrayList.add(mdxyzclass2)
                if (i2 < keylength - 1) {
                    val mdxyzclass3 = mdXYZClass()
                    mdxyzclass3.x = PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideKeyCenter
                    ) - (mdkeyblankclass.keyWidth / 2) - i
                    mdxyzclass3.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - 200) - i3 - (i4 * (i2 + 1))
                    mdxyzclass3.z = PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                    ) - mdkeyblankclass.keyThick
                    arrayList.add(mdxyzclass3)
                }
                z = true
            } else if (z) {
                val mdxyzclass4 = mdXYZClass()
                mdxyzclass4.x = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) - (mdkeyblankclass.keyWidth / 2) - i
                val i6 = i / 2
                val i7 = i - 20
                val i8 = i7 * i2
                mdxyzclass4.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i6 - i8
                mdxyzclass4.z = PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyThick
                arrayList.add(mdxyzclass4)
                val mdxyzclass5 = mdXYZClass()
                mdxyzclass5.x = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideKeyCenter
                ) + (mdkeyblankclass.keyWidth / 2) + i
                mdxyzclass5.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - 200) - i6 - i8
                mdxyzclass5.z = PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                ) - mdkeyblankclass.keyThick
                arrayList.add(mdxyzclass5)
                if (i2 < keylength - 1) {
                    val mdxyzclass6 = mdXYZClass()
                    mdxyzclass6.x = PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideKeyCenter
                    ) + (mdkeyblankclass.keyWidth / 2) + i
                    mdxyzclass6.y = (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - 200) - i6 - (i7 * (i2 + 1))
                    mdxyzclass6.z = PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
                    ) - mdkeyblankclass.keyThick
                    arrayList.add(mdxyzclass6)
                }
                z = false
            }
        }
        return arrayList
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x005e  */ /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private fun GetCutKeyWidthThickPointList(
        r19: mdKeyLocationPointClass,
        r20: mdKeyBlankClass,
        r21: Int,
        r22: JAW_S8
    ): List<mdXYZClass> {
        /*
            Method dump skipped, instructions count: 1268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw UnsupportedOperationException("Method not decompiled: com.spl.key.KeyBlankCutPathClass.GetCutKeyWidthThickPointList(com.spl.key.mdKeyLocationPointClass, com.spl.key.mdKeyBlankClass, int, com.spl.key.JawClass\$JAW_S8):java.util.List")
    }

    fun GetKeyBlankCut_KeyHeadDrillingPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int
    ): Array<Array<String>>? {
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val r10 = 0
        val arrayList= ArrayList<String>()
        val ceil = ceil((450 * 1.0) / 50).toInt()
        if (ceil == 0) {
            return null
        }
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙头上方",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft - ((mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight) / 2)
                ),
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - mdkeyblankclass.centerToShoulderDistance,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：Z轴向下",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        var i3 = 4
        for (i4 in 1 until ceil + 1) {
            arrayList.add(
                GetSetupValue(
                    "步骤$i3：Z轴向下切割",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + (50 * i4),
                    Program._Speed_CuttingDimpleIn,
                    "C:5,Z"
                )
            )
            val i5 = i3 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i5：Z轴向上回一点",
                    "0",
                    0,
                    0,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + r10) - 50,
                    Program._Speed_CuttingDimpleIn,
                    "C:5,Z"
                )
            )
            i3 = i5 + 1
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i3：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_CuttingIn,
                "C:5,Z"
            )
        )
        val i6 = i3 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i6：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        val i7 = i6 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i7：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i7 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        var result =  arrayList.map { str ->
            val split = str.toString().split("&".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            arrayOf( split[0],
             split[1],
        split[2],
        split[3],
      split[4],
      split[5],
         if (split.size == 7) split[6] else "",
      "",
      "0",
     "0",
       "0")
        }
        return result.toTypedArray()
    }

    fun GetKeyBlankCut_LeftGrovePath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var GetXYZXFPointRecalculate: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_SecondStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        val GetXYZXFPointRecalculate3 = PublicMethodPort.GetXYZXFPointRecalculate(
            2,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        var i3 = 50
        val ceil =
            ceil(((GetXYZXFPointRecalculate3 * 1.0) - mdkeyblankclass.keyBlankGrooveThick) / 50)
                .toInt()
        if (GetXYZXFPointRecalculate2 < 0 || GetXYZXFPointRecalculate3 < 0 || ceil == 0) {
            return null
        }
        val i4 = i2 / 2
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙肩膀左边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + i2 + 100,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + GetXYZXFPointRecalculate2,
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i5 = 1
        var i6 = 4
        while (i5 < ceil + 1) {
            val i7 = i3 * i5
            GetXYZXFPointRecalculate =
                if (i7 > GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankGrooveThick) {
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) + i4 - (GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankGrooveThick)
                } else {
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) + i4 - i7
                }
            arrayList.add(
                GetSetupValue(
                    "步骤$i6：第【$i5】刀停在钥匙左边",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) + i2 + 100,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                    0,
                    Program._Speed_SpindleTurnOn_Move,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            val i8 = i6 + 1
            val i9 = GetXYZXFPointRecalculate
            arrayList.add(
                GetSetupValue(
                    "步骤$i8：第【$i5】刀从肩膀开始切割",
                    "0",
                    i9,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                    0,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            val i10 = i8 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i10：第【$i5】刀切割到钥匙前端",
                    "0",
                    i9,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2,
                    0,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            i6 = i10 + 1
            i5++
            i3 = 50
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i6：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        val i11 = i6 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i11：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_SpindleTurnOff_Move,
                "SUP:0,1000"
            )
        )
        val i12 = i11 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i12：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i12 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )

        return arrayList.map { str ->

            val split =
                str.toString().split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
             "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_RightGrovePath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var GetXYZXFPointRecalculate: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_SecondStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        val GetXYZXFPointRecalculate3 = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        var i3 = 50
        val ceil =
            ceil(((GetXYZXFPointRecalculate3 * 1.0) - mdkeyblankclass.keyBlankGrooveThick) / 50)
                .toInt()
        if (GetXYZXFPointRecalculate2 < 0 || GetXYZXFPointRecalculate3 < 0 || ceil == 0) {
            return null
        }
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙前端右边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - i2) - 100,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + GetXYZXFPointRecalculate2,
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i4 = 1
        var i5 = 4
        while (i4 < ceil + 1) {
            var i6 = i3 * i4
            if (i6 > GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankGrooveThick) {
                i6 = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - (i2 / 2)
                GetXYZXFPointRecalculate =
                    GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankGrooveThick
            } else {
                GetXYZXFPointRecalculate = PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - (i2 / 2)
            }
            val i7 = i6 + GetXYZXFPointRecalculate
            arrayList.add(
                GetSetupValue(
                    "步骤$i5：第【$i4】刀停在钥匙右边",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideRight
                    ) - i2,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2) - 100,
                    0,
                    Program._Speed_SpindleTurnOn_Move,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            val i8 = i5 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i8：第【$i4】刀停在钥匙右边-移动到对应齿深",
                    "0",
                    i7,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2) - 100,
                    0,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            val i9 = i8 + 1
            val i10 = i2 / 2
            arrayList.add(
                GetSetupValue(
                    "步骤$i9：第【$i4】刀切割到钥匙肩膀",
                    "0",
                    i7,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i10,
                    0,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            val i11 = i9 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i11：第【$i4】刀切割到钥匙肩膀右边",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideRight
                    ) - i2,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i10,
                    0,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;U:Z"
                )
            )
            i5 = i11 + 1
            i4++
            i3 = 50
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i5：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        val i12 = i5 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i12：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_SpindleTurnOff_Move,
                "SUP:0,1000"
            )
        )
        val i13 = i12 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i13：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i13 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )

        return arrayList.map { str ->
            val split =
               str.toString().split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
         "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_TipCutPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var i3: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        var strArr: Array<Array<String>>? = null
        val arrayList = ArrayList<String>()
        val GetXYZXFPointRecalculate = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        val ceil = ceil((mdkeyblankclass.keyBlankTipWidth * 1.0) / 50)
            .toInt()
        if (GetXYZXFPointRecalculate >= 0 && GetXYZXFPointRecalculate2 >= 0 && ceil != 0) {
            var i4 = 1
            for (i5 in 1 until ceil + 1) {
                var i6 = 50 * i5
                if (i6 > mdkeyblankclass.keyBlankTipWidth) {
                    i6 = mdkeyblankclass.keyBlankTipWidth
                }
                if (i5 == 1) {
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i4：铣刀停留在钥匙前端左边",
                            "0",
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideLeft
                            ) + i2 + 100,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + i6) - (i2 / 2),
                            0,
                            Program._Speed_SpindleTurnOff_Move,
                            ""
                        )
                    )
                    val i7 = i4 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i7：Z轴向下",
                            "0",
                            0,
                            0,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) + GetXYZXFPointRecalculate,
                            Program._Speed_SpindleTurnOff_ZDown,
                            "U:X;U:Y;C:5,Z"
                        )
                    )
                    val i8 = i7 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i8：启动主轴",
                            "0",
                            0,
                            0,
                            0,
                            Program._Speed_CuttingIn,
                            "SUP:1,3000"
                        )
                    )
                    i3 = i8 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i3：切割刀钥匙右边",
                            "0",
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideRight
                            ) - i2) - 100,
                            0,
                            0,
                            Program._Speed_CuttingIn,
                            "C:5,X;U:Y;U:Z"
                        )
                    )
                } else if (i5 % 2 == 0) {
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i4：停在钥匙右边Y轴移动",
                            "0",
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideRight
                            ) - i2) - 100,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + i6) - (i2 / 2),
                            0,
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;U:Z"
                        )
                    )
                    i3 = i4 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i3：切割刀钥匙左边",
                            "0",
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideLeft
                            ) + i2 + 100,
                            0,
                            0,
                            Program._Speed_CuttingIn,
                            "C:5,X;U:Y;U:Z"
                        )
                    )
                } else {
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i4：切割刀钥匙左边Y轴移动",
                            "0",
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideLeft
                            ) + i2 + 100,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + i6) - (i2 / 2),
                            0,
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;U:Z"
                        )
                    )
                    i3 = i4 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i3：切割刀钥匙右边",
                            "0",
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                1,
                                mdkeylocationpointclass.sideRight
                            ) - i2) - 100,
                            0,
                            0,
                            Program._Speed_CuttingIn,
                            "C:5,X;U:Y;U:Z"
                        )
                    )
                }
                i4 = i3 + 1
            }
            arrayList.add(
                GetSetupValue(
                    "步骤$i4：Z轴向上",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) - 200,
                    Program._Speed_SpindleTurnOff_Move,
                    "C:5,Z"
                )
            )
            val i9 = i4 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i9：关闭主轴",
                    "0",
                    0,
                    0,
                    0,
                    Program._Speed_SpindleTurnOff_Move,
                    "SUP:0,1000"
                )
            )
            val i10 = i9 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i10：Z轴向上回到原点",
                    "998",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤" + (i10 + 1) + "：X-Y-Z回到原点",
                    "999",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )

            strArr = arrayList.map { str ->

                val split =
                    str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
            "0")
            }.toTypedArray()
        }
        return strArr
    }

    fun GetKeyBlankCut_40KTo80KPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var GetXYZXFPointRecalculate: Int
        var i3: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        var strArr: Array<Array<String>>? = null
        val arrayList = ArrayList<String>()
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        val GetXYZXFPointRecalculate3 = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        val keyBlankLeftWidth =
            (GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankLeftWidth) - mdkeyblankclass.keyBlankRightWidth
        val i4 = i2 - 30
        val ceil = ceil((keyBlankLeftWidth * 1.0) / i4).toInt()
        if (GetXYZXFPointRecalculate2 >= 0 && GetXYZXFPointRecalculate3 >= 0 && ceil != 0) {
            val ceil2 = ceil((GetXYZXFPointRecalculate2 * 1.0) / 65)
                .toInt()
            val i5 = if (ceil2 == 1) GetXYZXFPointRecalculate2 else 65
            val i6 = i2 / 2
            arrayList.add(
                GetSetupValue(
                    "步骤1：铣刀停留在钥匙前端左边",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - i6,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2) - 100,
                    0,
                    Program._Speed_SpindleTurnOff_Move,
                    ""
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤2：Z轴向下",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + i5,
                    Program._Speed_SpindleTurnOff_ZDown,
                    "U:X;U:Y;C:5,Z"
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤3：启动主轴",
                    "0",
                    0,
                    0,
                    0,
                    Program._Speed_CuttingIn,
                    "SUP:1,3000"
                )
            )
            var i7 = 0
            var i8 = 4
            while (i7 < ceil2) {
                val i9 = ceil2 - 1
                val i10 = if (i7 == i9) GetXYZXFPointRecalculate2 else (i7 + 1) * 65
                for (i11 in 0 until ceil) {
                    val i12 = i4 * i11
                    GetXYZXFPointRecalculate = if (i2 + i12 > keyBlankLeftWidth) {
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) + mdkeyblankclass.keyBlankRightWidth + i6
                    } else {
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) - mdkeyblankclass.keyBlankLeftWidth) - i12 - i6
                    }
                    val i13 = GetXYZXFPointRecalculate
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i8：第【$i11】刀停在钥匙前端",
                            "0",
                            i13,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) - i2) - 100,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) + i10,
                            Program._Speed_SpindleTurnOn_Move,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i14 = i8 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i14：第【$i11】刀切割到钥匙肩膀",
                            "0",
                            i13,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) + i10,
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i15 = i14 + 1
                    if (i11 == ceil - 1 && i7 == i9) {
                        arrayList.add(
                            GetSetupValue(
                                "步骤$i15：第【$i11】刀切割到钥匙肩膀-横向往左",
                                "0",
                                (PublicMethodPort.GetXYZXFPointRecalculate(
                                    1,
                                    mdkeylocationpointclass.sideLeft
                                ) - i6) - mdkeyblankclass.keyBlankLeftWidth,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    2,
                                    mdkeylocationpointclass.sideTip
                                ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    3,
                                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                                ) + i10,
                                Program._Speed_CuttingIn,
                                "C:5,X;C:5,Y;C:5,Z"
                            )
                        )
                        i3 = i15 + 1
                        arrayList.add(
                            GetSetupValue(
                                "步骤$i3：第【$i11】刀切割到钥匙肩膀-横向往右",
                                "0",
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    1,
                                    mdkeylocationpointclass.sideRight
                                ) + i6 + mdkeyblankclass.keyBlankRightWidth,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    2,
                                    mdkeylocationpointclass.sideTip
                                ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    3,
                                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                                ) + i10,
                                Program._Speed_CuttingIn,
                                "C:5,X;C:5,Y;C:5,Z"
                            )
                        )
                    } else {
                        val i16 = GetXYZXFPointRecalculate
                        arrayList.add(
                            GetSetupValue(
                                "步骤$i15：第【$i11】Z轴向上",
                                "0",
                                i16,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    2,
                                    mdkeylocationpointclass.sideTip
                                ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    3,
                                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                                ) - 200,
                                Program._Speed_SpindleTurnOn_Move,
                                "C:5,X;C:5,Y;C:5,Z"
                            )
                        )
                        i3 = i15 + 1
                        arrayList.add(
                            GetSetupValue(
                                "步骤$i3：第【$i11】刀移动到钥匙前端",
                                "0",
                                i16,
                                (PublicMethodPort.GetXYZXFPointRecalculate(
                                    2,
                                    mdkeylocationpointclass.sideTip
                                ) - i2) - 100,
                                PublicMethodPort.GetXYZXFPointRecalculate(
                                    3,
                                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                                ) - 200,
                                Program._Speed_SpindleTurnOn_Move,
                                "C:5,X;C:5,Y;C:5,Z"
                            )
                        )
                    }
                    i8 = i3 + 1
                }
                i7++
            }
            arrayList.add(
                GetSetupValue(
                    "步骤$i8：Z轴向上",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) - 200,
                    Program._Speed_SpindleTurnOff_Move,
                    "C:5,Z"
                )
            )
            val i17 = i8 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i17：关闭主轴",
                    "0",
                    0,
                    0,
                    0,
                    Program._Speed_SpindleTurnOff_Move,
                    "SUP:0,1000"
                )
            )
            val i18 = i17 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i18：Z轴向上回到原点",
                    "998",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤" + (i18 + 1) + "：X-Y-Z回到原点",
                    "999",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )
            strArr = arrayList.map { str ->
                val split =
                    str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
              "0")
            }.toTypedArray()
        }
        return strArr
    }

    fun GetKeyBlankCut_InternalGroveCutPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var GetXYZXFPointRecalculate: Int
        var i3: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList= ArrayList<String>()
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_SecondStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        var i4 = 1
        val GetXYZXFPointRecalculate3 = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        val keyBlankGrooveWidth =
            (GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankGrooveWidth) / 2
        mdkeyblankclass.keyBlankLeftWidth = keyBlankGrooveWidth
        mdkeyblankclass.keyBlankRightWidth = keyBlankGrooveWidth
        val keyBlankLeftWidth =
            (GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankLeftWidth) - mdkeyblankclass.keyBlankRightWidth
        val i5 = i2 - 30
        val ceil = ceil((keyBlankLeftWidth * 1.0) / i5).toInt()
        if (GetXYZXFPointRecalculate2 < 0 || GetXYZXFPointRecalculate3 < 0 || ceil == 0) {
            return null
        }
        val i6 = i2 / 2
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙前端左边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft) - i6,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - i2) - 100,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + GetXYZXFPointRecalculate2,
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i7 = 0
        var i8 = 4
        while (i7 < ceil) {
            val i9 = i5 * i7
            GetXYZXFPointRecalculate = if (i2 + i9 > keyBlankLeftWidth) {
                PublicMethodPort.GetXYZXFPointRecalculate(
                    i4,
                    mdkeylocationpointclass.sideRight
                ) + mdkeyblankclass.keyBlankRightWidth + i6
            } else {
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    i4,
                    mdkeylocationpointclass.sideLeft
                ) - mdkeyblankclass.keyBlankLeftWidth) - i9 - i6
            }
            val i10 = GetXYZXFPointRecalculate
            arrayList.add(
                GetSetupValue(
                    "步骤$i8：第【$i7】刀停在钥匙前端",
                    "0",
                    i10,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2) - 100,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + GetXYZXFPointRecalculate2,
                    Program._Speed_SpindleTurnOn_Move,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i11 = i8 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤$i11：第【$i7】刀切割到钥匙肩膀",
                    "0",
                    i10,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + GetXYZXFPointRecalculate2,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i12 = i11 + 1
            if (i7 == ceil - 1) {
                arrayList.add(
                    GetSetupValue(
                        "步骤$i12：第【$i7】刀切割到钥匙肩膀-横向往左",
                        "0",
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) - i6) - mdkeyblankclass.keyBlankLeftWidth,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) + GetXYZXFPointRecalculate2,
                        Program._Speed_CuttingIn,
                        "C:5,X;C:5,Y;C:5,Z"
                    )
                )
                i3 = i12 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i3：第【$i7】刀切割到钥匙肩膀-横向往右",
                        "0",
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) + i6 + mdkeyblankclass.keyBlankRightWidth,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) + GetXYZXFPointRecalculate2,
                        Program._Speed_CuttingIn,
                        "C:5,X;C:5,Y;C:5,Z"
                    )
                )
            } else {
                val i13 = GetXYZXFPointRecalculate
                arrayList.add(
                    GetSetupValue(
                        "步骤$i12：第【$i7】Z轴向上",
                        "0",
                        i13,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + mdkeyblankclass.keyBlankGrooveLength + i6,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) - 200,
                        Program._Speed_SpindleTurnOn_Move,
                        "C:5,X;C:5,Y;C:5,Z"
                    )
                )
                i3 = i12 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i3：第【$i7】刀移动到钥匙前端",
                        "0",
                        i13,
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) - i2) - 100,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) - 200,
                        Program._Speed_SpindleTurnOn_Move,
                        "C:5,X;C:5,Y;C:5,Z"
                    )
                )
            }
            i8 = i3 + 1
            i7++
            i4 = 1
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i8：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        val i14 = i8 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i14：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_SpindleTurnOff_Move,
                "SUP:0,1000"
            )
        )
        val i15 = i14 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i15：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i15 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        return arrayList.map { str ->
            val split =
                str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()

            arrayOf(
            split[0],
             split[1],
             split[2],
           split[3],
           split[4],
           split[5], if (split.size == 7) split[6] else "",
             "",
          "0",
        "0",
          "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_HY18Path(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var GetXYZXFPointRecalculate: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        var strArr: Array<Array<String>>? = null
        var arrayList= ArrayList<String>()
        var GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) - mdkeyblankclass.keyBlankThick
        val GetXYZXFPointRecalculate3 = PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        var keyBlankLeftWidth =
            (GetXYZXFPointRecalculate3 - mdkeyblankclass.keyBlankLeftWidth) - mdkeyblankclass.keyBlankRightWidth
        var i3 = i2 - 30
        var ceil = ceil((keyBlankLeftWidth * 1.0) / i3).toInt()
        if (GetXYZXFPointRecalculate2 >= 0 && GetXYZXFPointRecalculate3 >= 0 && ceil != 0) {
            var ceil2 = ceil((GetXYZXFPointRecalculate2 * 1.0) / 65)
                .toInt()
            val i4 = if (ceil2 == 1) GetXYZXFPointRecalculate2 else 65
            val i5 = i2 / 2
            arrayList.add(
                GetSetupValue(
                    "步骤1：铣刀停留在钥匙前端左边",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - i5,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - i2) - 100,
                    0,
                    Program._Speed_SpindleTurnOff_Move,
                    ""
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤2：Z轴向下",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + i4,
                    Program._Speed_SpindleTurnOff_ZDown,
                    "U:X;U:Y;C:5,Z"
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤3：启动主轴",
                    "0",
                    0,
                    0,
                    0,
                    Program._Speed_CuttingIn,
                    "SUP:1,3000"
                )
            )
            var i6 = 0
            var i7 = 4
            while (i6 < ceil2) {
                val i8 = if (i6 == ceil2 + (-1)) GetXYZXFPointRecalculate2 else (i6 + 1) * 65
                var i9 = 0
                while (i9 < ceil) {
                    val i10 = i3 * i9
                    GetXYZXFPointRecalculate = if (i2 + i10 > keyBlankLeftWidth) {
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) + mdkeyblankclass.keyBlankRightWidth + i5
                    } else {
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) - mdkeyblankclass.keyBlankLeftWidth) - i10 - i5
                    }
                    val i11 = GetXYZXFPointRecalculate
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i7：第【$i9】刀停在钥匙前端",
                            "0",
                            i11,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) - i2) - 100,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) + i8,
                            Program._Speed_SpindleTurnOn_Move,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i12 = i7 + 1
                    arrayList.add(
                        GetSetupValue(
                            "步骤$i12：第【$i9】刀切割到钥匙肩膀",
                            "0",
                            i11,
                            ((PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + mdkeyblankclass.keyBlankGrooveLength) - 450) + i5,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) + i8,
                            Program._Speed_CuttingIn,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i13 = i12 + 1
                    val i14 = GetXYZXFPointRecalculate2
                    val GetXYZXFPointRecalculate4 = PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + i8
                    val i15 = ceil2
                    val GetXYZXFPointRecalculate5 = PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.keyBlankGrooveLength + i5
                    val i16 = keyBlankLeftWidth
                    val abs = abs(
                        (GetXYZXFPointRecalculate4 - (PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) - 100)).toDouble()
                    )
                    val abs2 = abs(
                        ((((PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + mdkeyblankclass.keyBlankGrooveLength) - 450) + i5) - GetXYZXFPointRecalculate5).toDouble()
                    )
                    val arrayList2 = arrayList
                    val i17 = GetXYZXFPointRecalculate
                    val sqrt = sqrt(
                        (abs.pow(2.0) + abs2.pow(2.0)) - (((2.0 * abs) * abs2) * cos(
                            1.5707963267948966
                        ))
                    ) / 600
                    arrayList2.add(
                        GetSetupValue(
                            "步骤$i13：第【$i9】刀斜角切割到钥匙肩膀钥匙坯上表面",
                            "0",
                            i17,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + mdkeyblankclass.keyBlankGrooveLength + i5,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) - 100,
                            "600," + ((abs2 * sqrt).toInt()) + "," + ((abs * sqrt).toInt()),
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i18 = i13 + 1
                    arrayList2.add(
                        GetSetupValue(
                            "步骤$i18：第【$i9】Z轴向上",
                            "0",
                            i17,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) + mdkeyblankclass.keyBlankGrooveLength + i5,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) - 200,
                            Program._Speed_SpindleTurnOn_Move,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    val i19 = i18 + 1
                    arrayList2.add(
                        GetSetupValue(
                            "步骤$i19：第【$i9】刀移动到钥匙前端",
                            "0",
                            i17,
                            (PublicMethodPort.GetXYZXFPointRecalculate(
                                2,
                                mdkeylocationpointclass.sideTip
                            ) - i2) - 100,
                            PublicMethodPort.GetXYZXFPointRecalculate(
                                3,
                                mdkeylocationpointclass.sideKeyTopSurface_Cut
                            ) - 200,
                            Program._Speed_SpindleTurnOn_Move,
                            "C:5,X;C:5,Y;C:5,Z"
                        )
                    )
                    i7 = i19 + 1
                    i9++
                    arrayList = arrayList2
                    GetXYZXFPointRecalculate2 = i14
                    ceil2 = i15
                    keyBlankLeftWidth = i16
                    ceil = ceil
                    i3 = i3
                }
                i6++
                GetXYZXFPointRecalculate2 = GetXYZXFPointRecalculate2
            }
            val arrayList3 = arrayList
            arrayList3.add(
                GetSetupValue(
                    "步骤$i7：Z轴向上",
                    "0",
                    0,
                    0,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) - 200,
                    Program._Speed_SpindleTurnOff_Move,
                    "C:5,Z"
                )
            )
            val i20 = i7 + 1
            arrayList3.add(
                GetSetupValue(
                    "步骤$i20：关闭主轴",
                    "0",
                    0,
                    0,
                    0,
                    Program._Speed_SpindleTurnOff_Move,
                    "SUP:0,1000"
                )
            )
            val i21 = i20 + 1
            arrayList3.add(
                GetSetupValue(
                    "步骤$i21：Z轴向上回到原点",
                    "998",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )
            arrayList3.add(
                GetSetupValue(
                    "步骤" + (i21 + 1) + "：X-Y-Z回到原点",
                    "999",
                    0,
                    0,
                    0,
                    Program._Speed_Origin,
                    ""
                )
            )

            strArr = arrayList.map { str ->

                val split =
                    str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
               "0")
            }.toTypedArray()
        }
        return strArr
    }

    fun GetKeyBlankCut_KW16ToKW15Path(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8?
    ): Array<Array<String>>? {
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        if (PublicMethodPort.GetXYZXFPointRecalculate(
                1,
                mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
            ) < 1
        ) {
            return null
        }
        val i3 = i2 / 2
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙前端左边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft) - i3,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - i2) - 100,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_SpindleTurnOff_ZDown,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤4：切割到肩膀",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft) - i3,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength) - i3,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤5：肩膀横向切到右边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i3,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength) - i3,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤6：切割刀钥匙头",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i3,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - i2) - 100,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤7：Z轴向上",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i3,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) - i2) - 100,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤8：移动到肩膀横向切到右边",
                "0",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - 100) - i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength) - i3,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤9：Z轴向下",
                "0",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - 100) - i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength) - i3,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤10：切割刀钥匙左边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i2,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength) - i3,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ),
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤11：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤12：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_SpindleTurnOff_Move,
                "SUP:0,1000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤13：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤14：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        return arrayList.map { str ->
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
          "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_LXP9080KToPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8
    ): Array<Array<String>>? {
        var i3: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val GetCutKeyWidthThickPointList =
            GetCutKeyWidthThickPointList(mdkeylocationpointclass, mdkeyblankclass, i2, jaw_s8)
                ?: return null
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙外的点位",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft),
                PublicMethodPort.GetXYZXFPointRecalculate(2, mdkeylocationpointclass.sideTip) - i2,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                0,
                0,
                GetCutKeyWidthThickPointList[0].z,
                Program._Speed_SpindleTurnOff_ZUp,
                "U:X;U:Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        var i4 = 0
        var i5 = 4
        while (i4 < GetCutKeyWidthThickPointList.size) {
            val sb = StringBuilder()
            sb.append("步骤")
            sb.append(i5)
            sb.append("：第")
            val i6 = i4 + 1
            sb.append(i6)
            sb.append("点位,")
            sb.append(GetCutKeyWidthThickPointList[i4].des)
            arrayList.add(
                GetSetupValue(
                    sb.toString(),
                    "2",
                    GetCutKeyWidthThickPointList[i4].x,
                    GetCutKeyWidthThickPointList[i4].y,
                    GetCutKeyWidthThickPointList[i4].z,
                    GetCutKeyWidthThickPointList[i4].xYZSpeed,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            i5++
            i4 = i6
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i5：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        var i7 = i5 + 1
        val GetXYZXFPointRecalculate = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            (mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth) - mdkeylocationpointclass.sideKeyTopSurface_Cut
        ) + (-81)
        PublicMethodPort.GetXYZXFPointRecalculate(
            1,
            mdkeylocationpointclass.sideLeft - mdkeylocationpointclass.sideRight
        )
        var i8 = 50
        val ceil = ceil((mdkeyblankclass.keyBlankTipWidth * 1.0) / 50)
            .toInt()
        var i9 = 1
        while (i9 < ceil + 1) {
            var i10 = i8 * i9
            if (i10 > mdkeyblankclass.keyBlankTipWidth) {
                i10 = mdkeyblankclass.keyBlankTipWidth
            }
            if (i9 == 1) {
                arrayList.add(
                    GetSetupValue(
                        "步骤$i7：铣刀停留在钥匙前端左边",
                        "0",
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) + i2 + 100,
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + i10) - (i2 / 2),
                        0,
                        Program._Speed_SpindleTurnOff_Move,
                        ""
                    )
                )
                val i11 = i7 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i11：Z轴向下",
                        "0",
                        0,
                        0,
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            3,
                            mdkeylocationpointclass.sideKeyTopSurface_Cut
                        ) + GetXYZXFPointRecalculate,
                        Program._Speed_SpindleTurnOff_ZDown,
                        "U:X;U:Y;C:5,Z"
                    )
                )
                i3 = i11 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i3：切割刀钥匙右边",
                        "0",
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) - i2) - 100,
                        0,
                        0,
                        Program._Speed_CuttingIn,
                        "C:5,X;U:Y;U:Z"
                    )
                )
            } else if (i9 % 2 == 0) {
                arrayList.add(
                    GetSetupValue(
                        "步骤$i7：停在钥匙右边Y轴移动",
                        "0",
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) - i2) - 100,
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + i10) - (i2 / 2),
                        0,
                        Program._Speed_CuttingIn,
                        "C:5,X;C:5,Y;U:Z"
                    )
                )
                i3 = i7 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i3：切割刀钥匙左边",
                        "0",
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) + i2 + 100,
                        0,
                        0,
                        Program._Speed_CuttingIn,
                        "C:5,X;U:Y;U:Z"
                    )
                )
            } else {
                arrayList.add(
                    GetSetupValue(
                        "步骤$i7：切割刀钥匙左边Y轴移动",
                        "0",
                        PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideLeft
                        ) + i2 + 100,
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            2,
                            mdkeylocationpointclass.sideTip
                        ) + i10) - (i2 / 2),
                        0,
                        Program._Speed_CuttingIn,
                        "C:5,X;C:5,Y;U:Z"
                    )
                )
                i3 = i7 + 1
                arrayList.add(
                    GetSetupValue(
                        "步骤$i3：切割刀钥匙右边",
                        "0",
                        (PublicMethodPort.GetXYZXFPointRecalculate(
                            1,
                            mdkeylocationpointclass.sideRight
                        ) - i2) - 100,
                        0,
                        0,
                        Program._Speed_CuttingIn,
                        "C:5,X;U:Y;U:Z"
                    )
                )
            }
            i7 = i3 + 1
            i9++
            i8 = 50
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i7：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        val i12 = i7 + 1
        var iArr = intArrayOf(266, 172)
        val iArr2 = intArrayOf(225, 232)
        var iArr3 = intArrayOf(532, 172)
        val iArr4 = intArrayOf(564, 226)
        val iArr5 = intArrayOf(318, 346)
        val iArr6 = intArrayOf(456, 355)
        val GetXYZXFPointRecalculate2 = PublicMethodPort.GetXYZXFPointRecalculate(
            3,
            mdkeylocationpointclass.sideZ + jaw_s8.jawS8_FirstStepDepth
        ) - mdkeyblankclass.keyBlankThick
        var ceil2 = ceil((mdkeyblankclass.keyBlankThick * 1.0) / 60)
            .toInt()
        val i13 = i2 / 2
        arrayList.add(
            GetSetupValue(
                "步骤$i12：移动到钥匙前端",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideKeyCenter),
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + iArr[1] + i13,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOn_Move,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        val i14 = i12 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i14：Z轴向下",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideKeyCenter),
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + iArr[1] + i13,
                GetXYZXFPointRecalculate2 + 60,
                Program._Speed_SpindleTurnOn_Move,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        var i15 = i14 + 1
        var i16 = 0
        while (i16 < ceil2) {
            i16++
            var i17 = i16 * 60
            if (i17 > mdkeyblankclass.keyBlankThick) {
                i17 = mdkeyblankclass.keyBlankThick + 30
            }
            val i18 = ceil2
            val iArr7 = iArr3
            val i19 = GetXYZXFPointRecalculate2 + i17
            arrayList.add(
                GetSetupValue(
                    "步骤" + i15 + "：左上角第一个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr[0]) - i13,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr[1] + i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i20 = i15 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤" + i20 + "：左上角第二个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr2[0]) - i13,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr2[1] + i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i21 = i20 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤" + i21 + "：下面第一个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr5[0]) - i13,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr5[1]) - i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i22 = i21 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤" + i22 + "：下面第二个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr6[0]) - i13,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr6[1]) - i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i23 = i22 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤" + i23 + "：右上角第二个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr4[0]) + i13,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr4[1] + i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            val i24 = i23 + 1
            arrayList.add(
                GetSetupValue(
                    "步骤" + i24 + "：右上角第二个点【第" + i16 + "层】",
                    "0",
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - iArr7[0]) + i13,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + iArr7[1] + i13,
                    i19,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            i15 = i24 + 1
            iArr = iArr
            ceil2 = i18
            iArr3 = iArr7
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i15：Z轴向上",
                "0",
                0,
                0,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) - 200,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,Z"
            )
        )
        val i25 = i15 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i25：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        val i26 = i25 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i26：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i26 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )

        return arrayList.map { str ->

            val split =
                str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
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
      "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_Schlage6PINTo5PINPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass?,
        i2: Int,
        jaw_s8: JAW_S8?
    ): Array<Array<String>> {
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val i3 = i2 / 2
        val i4 = 2171 - i3
        arrayList.add(
            GetSetupValue(
                "步骤1：铣刀停留在钥匙坯右边",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i2,
                i4,
                0,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) - i2,
                i4,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 50,
                Program._Speed_SpindleTurnOff_Move,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤4：第一个点(第一层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + i3,
                i4,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 50,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        val i5 = 2679 - i3
        arrayList.add(
            GetSetupValue(
                "步骤5：第二个点(1)(第一层)",
                "0",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 50,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤6：第二个点(2)(第一层)",
                "0",
                ((PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) + 50) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 50,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        val i6 = 2441 - i3
        arrayList.add(
            GetSetupValue(
                "步骤7：第三个点(第一层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i3,
                i6,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 50,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤8：Z轴向下(第二层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i3,
                i6,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 100,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤9：第二个点(2)(第二层)",
                "0",
                ((PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) + 50) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 100,
                Program._Speed_CuttingIn,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤10：第二个点(1)(第一层)",
                "0",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 100,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤11：第一个点(第一层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + i2,
                i4,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 100,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤12：Z轴向下(第三层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + i2,
                i4,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 150,
                Program._Speed_CuttingIn,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤13：第一个点(第三层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + i3,
                i4,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 150,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤14：第二个点(1)(第三层)",
                "0",
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 150,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤15：第二个点(2)(第三层)",
                "0",
                ((PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideRight
                ) + 565) + 50) - i3,
                i5,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 150,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤16：第三个点(第三层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i3,
                i6,
                (PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 658) + 150,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤17：Z轴向下(第三层)",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i3,
                i6,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideZ_Cut
                ) - 800,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤18：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤19：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤20：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        return arrayList.map { str ->

            val split = str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            arrayOf(split[0],
           split[1],
            split[2],
             split[3],
          split[4],
         split[5],
           if (split.size == 7) split[6] else "",
       "",
        "0",
            "0",
       "0")
        }.toTypedArray()
    }

    fun GetKeyBlankCut_SchlageControlToFactoryPath(
        enummachinetype: enumMachineType,
        i: Int,
        mdkeylocationpointclass: mdKeyLocationPointClass,
        mdkeyblankclass: mdKeyBlankClass,
        i2: Int,
        jaw_s8: JAW_S8?
    ): Array<Array<String>> {
        val i3: Int
        Program.GetXYZScaleMaxRoute(enummachinetype, i)
        val arrayList = ArrayList<String>()
        val sb = StringBuilder()
        sb.append("步骤")
        var c = 1.toChar()
        sb.append(1)
        sb.append("：停留在肩膀外侧")
        val i4 = i2 / 2
        arrayList.add(
            GetSetupValue(
                sb.toString(),
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i2,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                0,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤2：Z轴向下",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i2,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + 950,
                Program._Speed_SpindleTurnOff_Move,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤3：启动主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:1,3000"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤4：切割肩膀",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(1, mdkeylocationpointclass.sideLeft) - i4,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + 950,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤5：停留在肩膀外侧",
                "0",
                PublicMethodPort.GetXYZXFPointRecalculate(
                    1,
                    mdkeylocationpointclass.sideLeft
                ) + 100 + i2,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    2,
                    mdkeylocationpointclass.sideTip
                ) + mdkeyblankclass.keyBlankGrooveLength + i4,
                PublicMethodPort.GetXYZXFPointRecalculate(
                    3,
                    mdkeylocationpointclass.sideKeyTopSurface_Cut
                ) + 950,
                Program._Speed_CuttingIn,
                "C:5,X;C:5,Y;C:5,Z"
            )
        )
        if (mdkeyblankclass.standardToControlKeyPoint.size == 3) {
            arrayList.add(
                GetSetupValue(
                    "步骤6：移动到钥匙前端",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) + 100 + i2,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.standardToControlKeyPoint[0] + i4,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + 950,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤7：切到钥匙前端第一个点",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideLeft
                    ) - i4,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.standardToControlKeyPoint[0] + i4,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + 950,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤8：切到钥匙前端第二个点",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideRight
                    ) + mdkeyblankclass.standardToControlKeyPoint[2] + i4,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) + mdkeyblankclass.standardToControlKeyPoint[1] + i4,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + 950,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            arrayList.add(
                GetSetupValue(
                    "步骤9：切到钥匙前端",
                    "0",
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        1,
                        mdkeylocationpointclass.sideRight
                    ) + mdkeyblankclass.standardToControlKeyPoint[2] + i4,
                    (PublicMethodPort.GetXYZXFPointRecalculate(
                        2,
                        mdkeylocationpointclass.sideTip
                    ) - 100) - i2,
                    PublicMethodPort.GetXYZXFPointRecalculate(
                        3,
                        mdkeylocationpointclass.sideKeyTopSurface_Cut
                    ) + 950,
                    Program._Speed_CuttingIn,
                    "C:5,X;C:5,Y;C:5,Z"
                )
            )
            i3 = 10
        } else {
            i3 = 6
        }
        arrayList.add(
            GetSetupValue(
                "步骤$i3：关闭主轴",
                "0",
                0,
                0,
                0,
                Program._Speed_CuttingIn,
                "SUP:0,1000"
            )
        )
        val i5 = i3 + 1
        arrayList.add(
            GetSetupValue(
                "步骤$i5：Z轴向上回到原点",
                "998",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )
        arrayList.add(
            GetSetupValue(
                "步骤" + (i5 + 1) + "：X-Y-Z回到原点",
                "999",
                0,
                0,
                0,
                Program._Speed_Origin,
                ""
            )
        )

        return arrayList.map { str ->

            val split = str.split("&".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            arrayOf(
            split[0],
             split[c.code],
          split[2],
          split[3],
           split[4],
            split[5],
         if (split.size == 7) split[6] else "",
       "",
           "0",
         "0",
             "0")
        }.toTypedArray()
    }
}
