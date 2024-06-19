package com.spl.key

import com.spl.key.Key.enumMachineType
import java.util.Collections
import kotlin.math.abs

/* loaded from: classes.dex */
object mdKeyDecoderPathClass {
    private fun GetAvaKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String
    ): String {
        return ""
    }

    private fun GetTibbeKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String
    ): String {
        return ""
    }

    fun GetKeyDecoderPath(
        enummachinetype: enumMachineType,
        i: Int,
        i2: Int,
        i3: Int,
        i4: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String,
        str5: String,
        str6: String
    ): String {
        val GetStandardDoubleKeyPath_Left: String?
        Program.GetXYZScaleMaxRoute(enummachinetype, -1)
        val GetKeyType: Key.KeyType? = Key.GetKeyType(i2)
        val GetKeySide: String? = Key.GetKeySide(GetKeyType, str6)
        val GetCut_Depth: Int = Key.GetCut_Depth(GetKeyType, str6)
        val GetKeyLength: Int = Key.GetKeyLength(GetKeyType, str6)
        Key.GetVariableSpace(str6)
        val str7: String =
            "{\r\n\"ID\":" + i + ",\r\n\"KeyType\":" + i2 + ",\r\n%Decoder%,%Cutting%}"
        when (AnonymousClass2.`$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`.get(
            GetKeyType!!.ordinal
        )) {
            1 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left =
                    GetStandardDoubleKeyPath_Left_Horizontal(i3, str, str, GetKeySide, str2)
            } else {
                GetStandardDoubleKeyPath_Left =
                    GetStandardDoubleKeyPath_Left(i3, str, str, (GetKeySide)!!, str2)
            }

            2 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left =
                    GetStandardSingleKeyPath_Horizontal(i3, str, str, str2, i4, GetCut_Depth)
            } else {
                GetStandardDoubleKeyPath_Left =
                    GetStandardSingleKeyPath(i3, str, str, str2, i4, GetCut_Depth)
            }

            3 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left = GetTrack4InternalKeyPath_Horizontal(
                    i3,
                    str,
                    str,
                    GetKeySide,
                    str2,
                    str4,
                    i4,
                    GetCut_Depth - 50
                )
            } else {
                GetStandardDoubleKeyPath_Left = GetTrack4InternalKeyPath(
                    i3,
                    str,
                    str,
                    GetKeySide,
                    str2,
                    str4,
                    i4,
                    GetCut_Depth - 50
                )
            }

            4 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left = GetTrack2ExternalKeyPath_Horizontal(
                    i3,
                    str,
                    str,
                    GetKeySide,
                    str2,
                    GetCut_Depth - 50
                )
            } else {
                GetStandardDoubleKeyPath_Left =
                    GetTrack2ExternalKeyPath(i3, str, str, GetKeySide, str2, GetCut_Depth - 50)
            }

            5 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left = GetTrack4ExternalKeyPath2_Horizontal(
                    i3,
                    str,
                    str,
                    GetKeySide,
                    str2,
                    GetCut_Depth - 50
                )
            } else {
                GetStandardDoubleKeyPath_Left =
                    GetTrack4ExternalKeyPath2(i3, str, str, GetKeySide, str2, GetCut_Depth - 50)
            }

            6 -> if (enummachinetype != enumMachineType.e9_Pro) {
                if (GetKeyLength > 0) {
                    GetStandardDoubleKeyPath_Left = GetChannelTrackKeyPath_3KS(
                        i3,
                        str,
                        str,
                        GetKeySide,
                        str2,
                        str4,
                        GetCut_Depth - 30
                    )
                } else if (PublicMethodPort.trimend(str4, ";").split(";".toRegex())
                        .dropLastWhile({ it.isEmpty() }).toTypedArray().size == 2
                ) {
                    GetStandardDoubleKeyPath_Left = GetDoubleChannelTrackKeyPath(
                        i3,
                        str,
                        str,
                        GetKeySide,
                        str2,
                        str4,
                        GetCut_Depth - 30
                    )
                } else {
                    GetStandardDoubleKeyPath_Left = GetChannelTrackKeyPath(
                        i3,
                        str,
                        str,
                        GetKeySide,
                        str2,
                        str4,
                        GetCut_Depth - 50
                    )
                }
            } else {
                GetStandardDoubleKeyPath_Left = GetChannelTrackKeyPath_Horizontal(
                    i3,
                    str,
                    str,
                    GetKeySide,
                    str2,
                    str4,
                    GetCut_Depth - 50
                )
            }

            7 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left =
                    GetDimpleKeyPath_Horizontal(i3, str, str2, str5, str3)
            } else {
                GetStandardDoubleKeyPath_Left = GetDimpleKeyPath(i3, str, str2, str5, str3)
            }

            8 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left = GetTubularKeyPath_Horizontal(str, str2)
            } else {
                GetStandardDoubleKeyPath_Left = GetTubularKeyPath(str, str2)
            }

            9 -> if (enummachinetype == enumMachineType.e9_Pro) {
                GetStandardDoubleKeyPath_Left =
                    GetSingleAngleKey_Horizontal(i3, str, str2, i4, str3)
            } else {
                GetStandardDoubleKeyPath_Left = GetSingleAngleKeyPath(i3, str, str2, i4, str3)
            }

            else -> GetStandardDoubleKeyPath_Left = ""
        }
        return str7.replace(
            "%Decoder%",
            "\"Decoder\": \r\n{\"VariableSpace\":\"" + str + "\",\r\n" + GetStandardDoubleKeyPath_Left + "\r\n}"
        ).replace("%Cutting%", "\"Cutting\": \"\"")
    }

    private fun GetmdSpaceClass(i: Int, str: String): List<mdSpaceClass> {
        val arrayList = ArrayList<mdSpaceClass>()
        mdSpaceClass()
        val split: Array<String> =
            PublicMethodPort.trimend(str, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        for (i2 in split.indices) {
            val split2: Array<String> =
                split.get(i2).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var i3: Int = 0
            while (i3 < split2.size) {
                val mdspace = mdSpaceClass()
                mdspace.spaceValue = split2[i3].toInt()
                mdspace.rowNum = (i2 + 1)
                i3++
                mdspace.spaceNum = i3
                arrayList.add(mdspace)
            }
        }
        if (i == 0) {
            mdSpaceClass.Companion.sort(
                arrayList,
                arrayOf<String>("_SpaceValue", "_RowNum"),
                booleanArrayOf(false, true)
            )
        } else if (i == 1) {
            mdSpaceClass.Companion.sort(
                arrayList,
                arrayOf<String>("_SpaceValue", "_RowNum"),
                booleanArrayOf(true, true)
            )
        }
        return arrayList
    }

    private fun GetmdSpaceClass2(i: Int, str: String): List<mdSpaceClass> {
        val arrayList = ArrayList<mdSpaceClass>()
        val split: Array<String> =
            PublicMethodPort.trimend(str, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        for (i2 in split.indices) {
            val split2: Array<String> =
                split.get(i2).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var i3: Int = 0
            while (i3 < split2.size) {
                val mdspaceclass: mdSpaceClass = mdSpaceClass()
                mdspaceclass.spaceValue = (split2[i3].toInt())
                mdspaceclass.rowNum = (i2 + 1)
                i3++
                mdspaceclass.spaceNum = i3
                arrayList.add(mdspaceclass)
            }
        }
        Collections.sort<mdSpaceClass>(arrayList, object : Comparator<mdSpaceClass> {
            // from class: com.spl.key.mdKeyDecoderPathClass.1
            // java.util.Comparator
            override fun compare(mdspaceclass2: mdSpaceClass, mdspaceclass3: mdSpaceClass): Int {
                if (i == 1) {
                    return mdspaceclass2.spaceValue - mdspaceclass3.spaceValue
                }
                return mdspaceclass3.spaceValue - mdspaceclass2.spaceValue
            }
        })
        return arrayList
    }

    private fun GetmdDepthClass(i: Int, str: String): List<mdDepthClass> {
        val arrayList = ArrayList<mdDepthClass>()
        val split: Array<String> =
            PublicMethodPort.trimend(str, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        for (i2 in split.indices) {
            val split2: Array<String> =
                split.get(i2).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var i3: Int = 0
            while (i3 < split2.size) {
                val depth = mdDepthClass()
                depth.depthValue = (split2.get(i3).toInt() + 70)
                depth.rowNum = (i2 + 1)
                i3++
                depth.spaceNum = i3
                arrayList.add(depth)
            }
        }
        if (i == 0) {
            mdDepthClass.Companion.sort(
                arrayList,
                arrayOf<String>("_DepthValue", "_RowNum"),
                booleanArrayOf(false, true)
            )
        } else if (i == 1) {
            mdDepthClass.Companion.sort(
                arrayList,
                arrayOf<String>("_DepthValue", "_RowNum"),
                booleanArrayOf(true, true)
            )
        }
        return arrayList.toList()
    }

    private fun GetStandardDoubleKeyPath_Left(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String
    ): String? {
        var str5: String? = null
        var str6: String? = null
        var str7: String? = null
        var str8: String?
        var str9: String?
        var str10: String? = null
        var str11: String? = null
        var str12: String?
        var i2: Int = 0
        var str13: String? = null
        var str14: String?
        var str15: String? = null
        var strArr: Array<String>
        var str16: String? = null
        var str17: String? = null
        var str18: String? = null
        var str19: String? = null
        var str20: String? = null
        var str21: String? = null
        var str22: String? = null
        var str23: String? = null
        var str24: String? = null
        var i3: Int = 0
        var str25: String? = null
        var str26: String? = null
        var str27: String?
        var i4: Int
        var str28: String?
        var str29: String?
        var str30: String?
        var str31: String?
        var str32: String?
        var sb: String?
        var i5: Int
        var str33: String?
        var str34: String? = null
        var str35: String? = null
        var str36: String? = null
        var str37: String? = null
        var str38: String? = null
        var str39: String? = null
        var sb2: StringBuilder = StringBuilder()
        var str40: String?
        var str41: String = str3
        var str42: String = str4
        var str43: String? = "SL:R,X"
        var str44: String? = ";"
        var str45: String? = ""
        var str46: String? = ","
        try {
            var split: Array<String> =
                str.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var str47: String = ""
            var i6: Int = 0
            while (i6 < split.size) {
                try {
                    val str48: String = str47 + "\"" + split.get(i6) + "\": [\r\n"
                    if (i == 0) {
                        str5 = "DC:2,%;SL:R,X;SL:S,Y"
                        str6 = "SL:S,Y;SL:R,X"
                        str7 = "DC:1,%;SL:L,X;SL:S,Y"
                        str8 = "SL:S,Y;SL:L,X"
                        str9 = "SL:S,Y"
                    } else {
                        str5 = "DC:2,%;SL:R,X;SL:T,Y"
                        str6 = "SL:T,Y;SL:R,X"
                        str7 = "DC:1,%;SL:L,X;SL:T,Y"
                        str8 = "SL:T,Y;SL:L,X"
                        str9 = "SL:T,Y"
                    }
                    try {
                        val split2: Array<String> = PublicMethodPort.trimend(str42, str44).split(
                            str46!!.toRegex()
                        ).dropLastWhile({ it.isEmpty() }).toTypedArray()
                        val strArr2: Array<String> = split
                        val i7: Int = i6
                        var str49: String? =
                            ":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":200,\r\n\"Y\":"
                        var str50: String? = str48
                        val str51: String? = str45
                        var str52: String? = str43
                        var str53: String = str5
                        var str54: String = str6
                        var str55: String? = str9
                        var str56: String? = "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":"
                        var str57: String? =
                            ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-500,\r\n\"V\":\""
                        var str58: String? = ":移到下一个齿位"
                        var str59: String? = "\",\r\n\"Rule\":\"SL:Z,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                        var str60: String = "SL:L,X"
                        var str61: String? = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
                        var str62: String? = ":离开探测齿位"
                        var str63: String? = "\",\r\n\"T\":1,\r\n\"X\":-500,\r\n\"Y\":"
                        var str64: String = ":探测齿位"
                        try {
                            try {
                                try {
                                    if (!(str41 == "2")) {
                                        try {
                                            if (str3.length >= 1) {
                                                if (!(str41 == "3")) {
                                                    return str51
                                                }
                                                var split3: Array<String> = str42.split(
                                                    str44!!.toRegex()
                                                ).dropLastWhile({ it.isEmpty() }).toTypedArray()
                                                str11 = str44
                                                str12 = str50
                                                i2 = 1
                                                var i8: Int = 0
                                                while (i8 < split3.size) {
                                                    try {
                                                        val split4: Array<String> =
                                                            split3.get(i8).split(
                                                                str46!!.toRegex()
                                                            ).dropLastWhile({ it.isEmpty() })
                                                                .toTypedArray()
                                                        if (i8 == 0) {
                                                            strArr = split3
                                                            try {
                                                                val sb3: StringBuilder =
                                                                    StringBuilder()
                                                                sb3.append(str12)
                                                                sb3.append("{\r\n\"S\":\"步骤")
                                                                sb3.append(i2)
                                                                sb3.append(str49)
                                                                str15 = str46
                                                                sb3.append(split4.get(0))
                                                                sb3.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                sb3.append(Program.strMoveXYZSpeed)
                                                                sb3.append("\",\r\n\"Rule\":\"")
                                                                sb3.append(str8)
                                                                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                val i9: Int = i2 + 1
                                                                var i10: Int = i9 + 1
                                                                var str65: String? =
                                                                    sb3.toString() + "{\r\n\"S\":\"步骤" + i9 + str57 + Program.strMoveXYZSpeed + str59
                                                                var i11: Int = 0
                                                                while (i11 < split4.size) {
                                                                    try {
                                                                        i4 = i11 + 1
                                                                        str28 = str8
                                                                        val replace: String =
                                                                            str7!!.replace(
                                                                                "%",
                                                                                i4.toString()
                                                                            )
                                                                        str29 = str7
                                                                        val sb4: StringBuilder =
                                                                            StringBuilder()
                                                                        sb4.append(str65)
                                                                        sb4.append("{\r\n\"S\":\"步骤")
                                                                        sb4.append(i10)
                                                                        str30 = str49
                                                                        val str66: String = str64
                                                                        sb4.append(str66)
                                                                        sb4.append(i4)
                                                                        str64 = str66
                                                                        str31 = str63
                                                                        sb4.append(str31)
                                                                        sb4.append(split4.get(i11))
                                                                        sb4.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                        sb4.append(Program.strTouchXYZSpeed)
                                                                        sb4.append("\",\r\n\"Rule\":\"")
                                                                        sb4.append(replace)
                                                                        sb4.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                        val sb5: String =
                                                                            sb4.toString()
                                                                        val i12: Int = i10 + 1
                                                                        val sb6: StringBuilder =
                                                                            StringBuilder()
                                                                        sb6.append(sb5)
                                                                        sb6.append("{\r\n\"S\":\"步骤")
                                                                        sb6.append(i12)
                                                                        str32 = str62
                                                                        sb6.append(str32)
                                                                        sb6.append(i4)
                                                                        sb6.append("\",\r\n\"T\":0,\r\n\"X\":")
                                                                        sb6.append(Program.nConfigDecoderMove)
                                                                        val str67: String? = str61
                                                                        sb6.append(str67)
                                                                        str61 = str67
                                                                        sb6.append(Program.strMoveXYZSpeed)
                                                                        sb6.append("\",\r\n\"Rule\":\"")
                                                                        val str68: String = str60
                                                                        sb6.append(str68)
                                                                        sb6.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                        sb = sb6.toString()
                                                                        i5 = i12 + 1
                                                                        str60 = str68
                                                                    } catch (unused: Exception) {
                                                                        str50 = str65
                                                                        str45 = str50
                                                                        return str45
                                                                    }
                                                                    try {
                                                                        if (i11 < split4.size - 1) {
                                                                            val sb7: StringBuilder =
                                                                                StringBuilder()
                                                                            sb7.append(sb)
                                                                            sb7.append("{\r\n\"S\":\"步骤")
                                                                            sb7.append(i5)
                                                                            str63 = str31
                                                                            str33 = str58
                                                                            sb7.append(str33)
                                                                            sb7.append(i11 + 2)
                                                                            val str69: String? =
                                                                                str56
                                                                            sb7.append(str69)
                                                                            str56 = str69
                                                                            sb7.append(split4.get(i4))
                                                                            sb7.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                            sb7.append(Program.strMoveXYZSpeed)
                                                                            sb7.append("\",\r\n\"Rule\":\"")
                                                                            val str70: String? =
                                                                                str55
                                                                            sb7.append(str70)
                                                                            sb7.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                            str65 = sb7.toString()
                                                                            i10 = i5 + 1
                                                                            str55 = str70
                                                                        } else {
                                                                            str63 = str31
                                                                            str33 = str58
                                                                            val sb8: StringBuilder =
                                                                                StringBuilder()
                                                                            sb8.append(sb)
                                                                            sb8.append("{\r\n\"S\":\"步骤")
                                                                            sb8.append(i5)
                                                                            str55 = str55
                                                                            sb8.append(":主轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-1000,\r\n\"V\":\"")
                                                                            sb8.append(Program.strMoveXYZSpeed)
                                                                            sb8.append(str59)
                                                                            i10 = i5 + 1
                                                                            str65 = sb8.toString()
                                                                        }
                                                                        str58 = str33
                                                                        i11 = i4
                                                                        str62 = str32
                                                                        str8 = str28
                                                                        str7 = str29
                                                                        str49 = str30
                                                                    } catch (unused2: Exception) {
                                                                        str45 = sb
                                                                        return str45
                                                                    }
                                                                }
                                                                str14 = str49
                                                                str22 = str65
                                                                str16 = str7
                                                                str17 = str8
                                                                str19 = str58
                                                                str21 = str62
                                                                str23 = str59
                                                                i2 = i10
                                                                str26 = str55
                                                                str18 = str56
                                                                str20 = str61
                                                                str24 = str57
                                                                i3 = i8
                                                                str25 = str52
                                                            } catch (unused3: Exception) {
                                                                str45 = str12
                                                            }
                                                        } else {
                                                            str14 = str49
                                                            str15 = str46
                                                            strArr = split3
                                                            str16 = str7
                                                            str17 = str8
                                                            str18 = str56
                                                            str19 = str58
                                                            str20 = str61
                                                            str21 = str62
                                                            if (i8 == 1) {
                                                                val sb9: StringBuilder =
                                                                    StringBuilder()
                                                                sb9.append(str12)
                                                                sb9.append("{\r\n\"S\":\"步骤")
                                                                sb9.append(i2)
                                                                sb9.append(":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":-200,\r\n\"Y\":")
                                                                i3 = i8
                                                                sb9.append(split4.get(0))
                                                                sb9.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                sb9.append(Program.strMoveXYZSpeed)
                                                                sb9.append("\",\r\n\"Rule\":\"")
                                                                val str71: String = str54
                                                                sb9.append(str71)
                                                                sb9.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                val sb10: String = sb9.toString()
                                                                val i13: Int = i2 + 1
                                                                val sb11: StringBuilder =
                                                                    StringBuilder()
                                                                sb11.append(sb10)
                                                                sb11.append("{\r\n\"S\":\"步骤")
                                                                sb11.append(i13)
                                                                sb11.append(str57)
                                                                sb11.append(Program.strMoveXYZSpeed)
                                                                sb11.append(str59)
                                                                val sb12: String = sb11.toString()
                                                                var i14: Int = i13 + 1
                                                                str54 = str71
                                                                var str72: String? = sb12
                                                                var i15: Int = 0
                                                                while (i15 < split4.size) {
                                                                    val i16: Int = i15 + 1
                                                                    val str73: String? = str59
                                                                    val str74: String? = str57
                                                                    val str75: String = str53
                                                                    val replace2: String =
                                                                        str75.replace(
                                                                            "%",
                                                                            i16.toString()
                                                                        )
                                                                    str53 = str75
                                                                    val sb13: StringBuilder =
                                                                        StringBuilder()
                                                                    sb13.append(str72)
                                                                    sb13.append("{\r\n\"S\":\"步骤")
                                                                    sb13.append(i14)
                                                                    val str76: String = str64
                                                                    sb13.append(str76)
                                                                    sb13.append(i16)
                                                                    str64 = str76
                                                                    sb13.append("\",\r\n\"T\":1,\r\n\"X\":500,\r\n\"Y\":")
                                                                    sb13.append(split4.get(i15))
                                                                    sb13.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                    sb13.append(Program.strTouchXYZSpeed)
                                                                    sb13.append("\",\r\n\"Rule\":\"")
                                                                    sb13.append(replace2)
                                                                    sb13.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                    str72 = sb13.toString()
                                                                    val i17: Int = i14 + 1
                                                                    try {
                                                                        val sb14: StringBuilder =
                                                                            StringBuilder()
                                                                        sb14.append(str72)
                                                                        sb14.append("{\r\n\"S\":\"步骤")
                                                                        sb14.append(i17)
                                                                        sb14.append(str21)
                                                                        sb14.append(i16)
                                                                        sb14.append("\",\r\n\"T\":0,\r\n\"X\":-")
                                                                        sb14.append(Program.nConfigDecoderMove)
                                                                        sb14.append(str20)
                                                                        sb14.append(Program.strMoveXYZSpeed)
                                                                        sb14.append("\",\r\n\"Rule\":\"")
                                                                        val str77: String? = str52
                                                                        sb14.append(str77)
                                                                        sb14.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                        str72 = sb14.toString()
                                                                        i14 = i17 + 1
                                                                        if (i15 < split4.size - 1) {
                                                                            val sb15: StringBuilder =
                                                                                StringBuilder()
                                                                            sb15.append(str72)
                                                                            sb15.append("{\r\n\"S\":\"步骤")
                                                                            sb15.append(i14)
                                                                            sb15.append(str19)
                                                                            sb15.append(i15 + 2)
                                                                            sb15.append(str18)
                                                                            sb15.append(
                                                                                split4.get(
                                                                                    i16
                                                                                )
                                                                            )
                                                                            sb15.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                                                            sb15.append(Program.strMoveXYZSpeed)
                                                                            sb15.append("\",\r\n\"Rule\":\"")
                                                                            str27 = str55
                                                                            sb15.append(str27)
                                                                            sb15.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                                                            str72 = sb15.toString()
                                                                            i14++
                                                                        } else {
                                                                            str27 = str55
                                                                        }
                                                                        str55 = str27
                                                                        str52 = str77
                                                                        i15 = i16
                                                                        str59 = str73
                                                                        str57 = str74
                                                                    } catch (unused4: Exception) {
                                                                        str45 = str72
                                                                        return str45
                                                                    }
                                                                }
                                                                str22 = str72
                                                                str23 = str59
                                                                str24 = str57
                                                                str25 = str52
                                                                str26 = str55
                                                                i2 = i14
                                                            } else {
                                                                str22 = str12
                                                                str23 = str59
                                                                str24 = str57
                                                                i3 = i8
                                                                str25 = str52
                                                                str26 = str55
                                                            }
                                                        }
                                                        i8 = i3 + 1
                                                        str55 = str26
                                                        str52 = str25
                                                        str56 = str18
                                                        str62 = str21
                                                        str12 = str22
                                                        str59 = str23
                                                        str57 = str24
                                                        str46 = str15
                                                        str8 = str17
                                                        str7 = str16
                                                        str58 = str19
                                                        str61 = str20
                                                        split3 = strArr
                                                        str14 = null
                                                        str49 = str14
                                                    } catch (unused5: Exception) {
                                                        str50 = str12
                                                    }
                                                }
                                                str13 = str46
                                                str34 =
                                                    str12 + "{\r\n\"S\":\"步骤" + i2 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n"
                                                str34 =
                                                    str34 + "{\r\n\"S\":\"步骤" + (i2 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n"
                                                str47 = str34 + " ],"
                                                i6 = i7 + 1
                                                str41 = str3
                                                str42 = str4
                                                split = strArr2
                                                str45 = str51
                                                str43 = str52
                                                str44 = str11
                                                str46 = str13
                                            }
                                        } catch (unused6: Exception) {
                                        }
                                    }
                                    str34 =
                                        str34 + "{\r\n\"S\":\"步骤" + (i2 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n"
                                    str47 = str34 + " ],"
                                    i6 = i7 + 1
                                    str41 = str3
                                    str42 = str4
                                    split = strArr2
                                    str45 = str51
                                    str43 = str52
                                    str44 = str11
                                    str46 = str13
                                } catch (unused7: Exception) {
                                    str45 = str34
                                    return str45
                                }
                                sb2.append(str10)
                                sb2.append("{\r\n\"S\":\"步骤")
                                sb2.append(1)
                                sb2.append(":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":200,\r\n\"Y\":")
                                sb2.append(split2.get(0))
                                sb2.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                sb2.append(Program.strMoveXYZSpeed)
                                sb2.append("\",\r\n\"Rule\":\"")
                                sb2.append(str38)
                                sb2.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                var str78: String =
                                    sb2.toString() + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-500,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:Z,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                                var str79: String? = str35
                                var str80: String? = str36
                                str.split(str79!!.toRegex()).dropLastWhile({ it.isEmpty() })
                                    .toTypedArray().get(0).split(
                                    str80!!.toRegex()
                                ).dropLastWhile({ it.isEmpty() }).toTypedArray()
                                var i18: Int = 0
                                var i19: Int = 3
                                while (true) {
                                    try {
                                        str11 = str79
                                        if (i18 >= strArr2.get(i7).toInt()) {
                                            break
                                        }
                                        val i20: Int = i18 + 1
                                        val str81: String? = str39
                                        val str82: String? = str80
                                        val str83: String? = str37
                                        val replace3: String = str83!!.replace("%", i20.toString())
                                        str37 = str83
                                        val sb16: StringBuilder = StringBuilder()
                                        sb16.append(str78)
                                        sb16.append("{\r\n\"S\":\"步骤")
                                        sb16.append(i19)
                                        str40 = str78
                                        val str84: String = str64
                                        try {
                                            sb16.append(str84)
                                            sb16.append(i20)
                                            str64 = str84
                                            val str85: String? = str63
                                            sb16.append(str85)
                                            str63 = str85
                                            sb16.append(split2.get(i18))
                                            sb16.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                                            sb16.append(Program.strTouchXYZSpeed)
                                            sb16.append("\",\r\n\"Rule\":\"")
                                            sb16.append(replace3)
                                            sb16.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                            val sb17: String = sb16.toString()
                                            val i21: Int = i19 + 1
                                            val sb18: StringBuilder = StringBuilder()
                                            sb18.append(sb17)
                                            sb18.append("{\r\n\"S\":\"步骤")
                                            sb18.append(i21)
                                            sb18.append(str62)
                                            sb18.append(i20)
                                            sb18.append("\",\r\n\"T\":0,\r\n\"X\":")
                                            sb18.append(Program.nConfigDecoderMove)
                                            sb18.append(str61)
                                            sb18.append(Program.strMoveXYZSpeed)
                                            sb18.append("\",\r\n\"Rule\":\"")
                                            val str86: String = str60
                                            sb18.append(str86)
                                            sb18.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                                            str78 = sb18.toString()
                                            i19 = i21 + 1
                                            if (i18 < strArr2.get(i7).toInt() - 1) {
                                                str78 =
                                                    str78 + "{\r\n\"S\":\"步骤" + i19 + str58 + (i18 + 2) + str56 + split2.get(
                                                        i20
                                                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str55 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                                                i19++
                                            }
                                            i18 = i20
                                            str60 = str86
                                            str39 = str81
                                            str79 = str11
                                            str80 = str82
                                        } catch (unused8: Exception) {
                                            str45 = str40
                                            return str45
                                        }
                                    } catch (unused9: Exception) {
                                        str40 = str78
                                    }
                                }
                                val str87: String = str78
                                str13 = str80
                                str52 = str39
                                i2 = i19
                                str12 = str87
                                str34 =
                                    str12 + "{\r\n\"S\":\"步骤" + i2 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n"
                            } catch (unused10: Exception) {
                                str45 = str10
                                return str45
                            }
                            sb2 = StringBuilder()
                            str10 = str50
                        } catch (unused11: Exception) {
                            str10 = str50
                        }
                        str35 = str44
                        str36 = str46
                        str37 = str7
                        str38 = str8
                        str39 = str52
                    } catch (unused12: Exception) {
                        str10 = str48
                    }
                } catch (unused13: Exception) {
                    str45 = str47
                }
            }
            return PublicMethodPort.trimend(str47, str46)
        } catch (unused14: Exception) {
            return null
        }
    }

    private fun GetStandardSingleKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        i2: Int,
        i3: Int
    ): String? {
        val i4: Int = i2 - 150
        var split: Array<String> =
            str.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        var c: Char = 0.toChar()
        var str4: String = ""
        var i5: Int = 0
        while (i5 < split.size) {
            val str5: String = str4 + "\"" + split.get(i5) + "\": [\r\n"
            var str6: String = if (i == 0) "SL:S,Y" else "SL:T,Y"
            val i6: Int = if (i3 > 0) i3 / 2 else 900
            val split2: Array<String> =
                PublicMethodPort.trimend(str3, ";").toString().split(",".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            var str7: String =
                (str5 + "{\r\n\"S\":\"步骤1:移动到1齿位上方\",\r\n\"T\":0,\r\n\"X\":" + i4 + ",\r\n\"Y\":" + split2.get(
                    c.code
                ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X;" + str6 + "\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i6 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
            var i7: Int = 3
            var i8: Int = 0
            while (i8 < split.get(i5).toInt()) {
                val sb: StringBuilder = StringBuilder()
                sb.append(str7)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i7)
                sb.append(":移动到")
                val i9: Int = i8 + 1
                sb.append(i9)
                val strArr: Array<String> = split
                sb.append("齿位左边\",\r\n\"T\":0,\r\n\"X\":")
                sb.append(i4)
                sb.append(",\r\n\"Y\":")
                sb.append(split2.get(i8))
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,X;")
                sb.append(str6)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i10: Int = i7 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i10)
                sb3.append(":探测")
                sb3.append(i9)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"SL:R,X;")
                sb3.append("DC:1,%;".replace("%", i9.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i11: Int = i10 + 1
                val str8: String =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i11 + ":离开" + i9 + "齿位\",\r\n\"T\":0,\r\n\"X\":" + i4 + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i7 = i11 + 1
                i8 = i9
                str6 = str6
                str7 = str8
                split = strArr
            }
            val strArr2: Array<String> = split
            str4 =
                ((str7 + "{\r\n\"S\":\"步骤" + i7 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i7 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ],"
            i5++
            split = strArr2
            c = 0.toChar()
        }
        return PublicMethodPort.trimend(str4, ",")
    }

    private fun GetTrack4InternalKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int,
        i3: Int
    ): String {
        var i4: Int
        var i5: Int
        var i6: Int
        var list: List<mdSpaceClass>
        var str6: String
        var str7: String
        var str8: String
        val str9: String = "\"" + str2 + "\": [\r\n"
        var str10: String = if (i == 0) "SL:S,Y" else "SL:T,Y"
        val i7: Int = i2 / 2
        val split: Array<String> =
            PublicMethodPort.trimend(str5, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i8: Int = 100
        var str11: String = ","
        var i9: Int = 0
        var i10: Int = 1
        if (split.size == 2) {
            split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                .toInt()
            split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size - 1
                ).toInt()
            split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                .toInt()
            split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size - 1
                ).toInt()
            i4 = 100
        } else {
            i4 = 0
            i8 = 0
        }
        val sb: StringBuilder = StringBuilder()
        sb.append(str9)
        sb.append("{\r\n\"S\":\"步骤")
        sb.append(1)
        sb.append(":移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":-")
        sb.append(i7)
        var str12: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
        sb.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
        sb.append(Program.strMoveXYZSpeed)
        sb.append("\",\r\n\"Rule\":\"SL:T,Y;SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n")
        var str13: String =
            sb.toString() + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i3 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
        var GetmdSpaceClass2: List<mdSpaceClass> = GetmdSpaceClass2(i, str4)
        var i11: Int = 3
        while (i9 < GetmdSpaceClass2.size) {
            val str14: String =
                str13 + "{\r\n\"S\":\"步骤" + i11 + ":移动到对应齿位【轴:" + GetmdSpaceClass2.get(i9)
                    .rowNum + ",齿位:" + GetmdSpaceClass2.get(i9)
                    .spaceNum + "】\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + GetmdSpaceClass2.get(
                    i9
                )
                    .spaceValue + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str10 + ";GO:D,6\",\r\n\"Des\":\"\"\r\n},\r\n"
            val i12: Int = i11 + 1
            val str15: String =
                str14 + "{\r\n\"S\":\"步骤" + i12 + ":回退一点-Y\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-20,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
            i11 = i12 + i10
            val i13: Int = i4
            val str16: String = str12
            val i14: Int = i8
            val str17: String = str10
            val str18: String = str11
            val list2: List<mdSpaceClass> = GetmdSpaceClass2
            if (GetmdSpaceClass2.get(i9).rowNum == 1) {
                val str19: String =
                    str15 + "{\r\n\"S\":\"步骤" + i11 + ":探测左边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i15: Int = i11 + 1
                val str20: String =
                    str19 + "{\r\n\"S\":\"步骤" + i15 + ":离开左边一点-X\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i16: Int = i15 + 1
                val str21: String =
                    str20 + "{\r\n\"S\":\"步骤" + i16 + ":探测右边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i17: Int = i16 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str21)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i17)
                sb2.append(":移动到左右两边的中间(跳转到第1步)\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                sb2.append("\",\r\n\"Rule\":\"C:2,X,X-")
                sb2.append(i17 - 3)
                sb2.append(",X-")
                sb2.append(i17 - 1)
                sb2.append(";GO2:U,5\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb3: String = sb2.toString()
                val i18: Int = i17 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(sb3)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i18)
                sb4.append(":探测齿位【轴:")
                sb4.append(list2.get(i9).rowNum)
                sb4.append(",齿位:")
                sb4.append(list2.get(i9).spaceNum)
                sb4.append("】\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":")
                sb4.append(list2.get(i9).spaceValue)
                sb4.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb4.append(Program.strTouchXYZSpeed)
                sb4.append("\",\r\n\"Rule\":\"DC:")
                sb4.append(list2.get(i9).rowNum)
                str8 = str18
                sb4.append(str8)
                sb4.append(list2.get(i9).spaceNum)
                sb4.append(";SL:L,X;")
                sb4.append(str17)
                sb4.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i19: Int = i18 + 1
                i11 = i19 + 1
                i5 = i14
                str13 =
                    sb4.toString() + "{\r\n\"S\":\"步骤" + i19 + ":离开探测齿位-X\",\r\n\"T\":0,\r\n\"X\":-" + i14 + str16 + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                str7 = str16
                list = list2
                str6 = str17
                i6 = i13
            } else {
                i5 = i14
                if (list2.get(i9).rowNum == 2) {
                    val str22: String =
                        str15 + "{\r\n\"S\":\"步骤" + i11 + ":探测右边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i20: Int = i11 + 1
                    val str23: String =
                        str22 + "{\r\n\"S\":\"步骤" + i20 + ":离开右边一点-X\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i21: Int = i20 + 1
                    val str24: String =
                        str23 + "{\r\n\"S\":\"步骤" + i21 + ":探测左边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i22: Int = i21 + 1
                    val sb5: StringBuilder = StringBuilder()
                    sb5.append(str24)
                    sb5.append("{\r\n\"S\":\"步骤")
                    sb5.append(i22)
                    sb5.append(":移动到左右两边的中间(跳转到第1步)\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                    sb5.append(Program.strMoveXYZSpeed)
                    sb5.append("\",\r\n\"Rule\":\"C:2,X,X-")
                    sb5.append(i22 - 1)
                    sb5.append(",X-")
                    sb5.append(i22 - 3)
                    sb5.append(";GO2:U,5\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val sb6: String = sb5.toString()
                    val i23: Int = i22 + 1
                    val sb7: StringBuilder = StringBuilder()
                    sb7.append(sb6)
                    sb7.append("{\r\n\"S\":\"步骤")
                    sb7.append(i23)
                    sb7.append(":探测齿位【轴:")
                    list = list2
                    sb7.append(list.get(i9).rowNum)
                    sb7.append(",齿位:")
                    sb7.append(list.get(i9).spaceNum)
                    sb7.append("】\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":")
                    sb7.append(list.get(i9).spaceValue)
                    sb7.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                    sb7.append(Program.strTouchXYZSpeed)
                    sb7.append("\",\r\n\"Rule\":\"DC:")
                    sb7.append(list.get(i9).rowNum)
                    str8 = str18
                    sb7.append(str8)
                    sb7.append(list.get(i9).spaceNum)
                    sb7.append(";SL:R,X;")
                    str6 = str17
                    sb7.append(str6)
                    sb7.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val sb8: String = sb7.toString()
                    val i24: Int = i23 + 1
                    val sb9: StringBuilder = StringBuilder()
                    sb9.append(sb8)
                    sb9.append("{\r\n\"S\":\"步骤")
                    sb9.append(i24)
                    sb9.append(":离开探测齿位-X\",\r\n\"T\":0,\r\n\"X\":")
                    i6 = i13
                    sb9.append(i6)
                    str7 = str16
                    sb9.append(str7)
                    sb9.append(Program.strMoveXYZSpeed)
                    sb9.append("\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n")
                    str13 = sb9.toString()
                    i11 = i24 + 1
                } else {
                    i6 = i13
                    list = list2
                    str6 = str17
                    str7 = str16
                    str8 = str18
                    str13 = str15
                }
            }
            i9++
            GetmdSpaceClass2 = list
            str10 = str6
            i8 = i5
            i10 = 1
            i4 = i6
            str12 = str7
            str11 = str8
        }
        return ((str13 + "{\r\n\"S\":\"步骤" + i11 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i11 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTrack2ExternalKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        i2: Int
    ): String {
        val str5: String
        val str6: String
        val str7: String
        var i3: Int
        var str8: String?
        var str9: String?
        var str10: String?
        var str11: String?
        var str12: String = "\"" + str2 + "\": [\r\n"
        if (i == 0) {
            str5 = "DC:1,%;SL:R,X;SL:S,Y"
            str6 = "DC:1,%;SL:L,X;SL:S,Y"
            str7 = "SL:S,Y"
        } else {
            str5 = "DC:1,%;SL:R,X;SL:T,Y"
            str6 = "DC:1,%;SL:L,X;SL:T,Y"
            str7 = "SL:T,Y"
        }
        val split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val str13: String = str6
        var str14: String? = "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":"
        var str15: String? = ":移到下一个齿位"
        var str16: String = ":离开探测齿位"
        if ((str3 == "0")) {
            str12 =
                (str12 + "{\r\n\"S\":\"步骤1:移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":-200,\r\n\"Y\":" + split.get(
                    0
                ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
            var i4: Int = 0
            i3 = 3
            while (i4 < split.size) {
                val i5: Int = i4 + 1
                val str17: String =
                    str12 + "{\r\n\"S\":\"步骤" + i3 + ":探测齿位" + i5 + "\",\r\n\"T\":1,\r\n\"X\":1000,\r\n\"Y\":" + split.get(
                        i4
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str5.replace(
                        "%",
                        i5.toString()
                    ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i6: Int = i3 + 1
                val sb: StringBuilder = StringBuilder()
                sb.append(str17)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i6)
                val str18: String = str16
                sb.append(str18)
                sb.append(i5)
                sb.append("\",\r\n\"T\":0,\r\n\"X\":-")
                sb.append(Program.nConfigDecoderMove)
                sb.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"")
                sb.append("SL:R,X")
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                i3 = i6 + 1
                if (i4 < split.size - 1) {
                    val sb3: StringBuilder = StringBuilder()
                    sb3.append(sb2)
                    sb3.append("{\r\n\"S\":\"步骤")
                    sb3.append(i3)
                    str11 = str15
                    sb3.append(str11)
                    sb3.append(i4 + 2)
                    str10 = str14
                    sb3.append(str10)
                    sb3.append(split.get(i5))
                    sb3.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                    sb3.append(Program.strMoveXYZSpeed)
                    sb3.append("\",\r\n\"Rule\":\"")
                    sb3.append(str7)
                    sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    str12 = sb3.toString()
                    i3++
                } else {
                    str10 = str14
                    str11 = str15
                    str12 = sb2
                }
                str14 = str10
                i4 = i5
                str15 = str11
                str16 = str18
            }
        } else {
            var str19: String? = str15
            var str20: String = str16
            var str21: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
            if ((str3 == "1")) {
                str12 =
                    (str12 + "{\r\n\"S\":\"步骤1:Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":200,\r\n\"Y\":" + split.get(
                        0
                    ) + ",\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                var i7: Int = 0
                i3 = 3
                while (i7 < split.size) {
                    val i8: Int = i7 + 1
                    val str22: String =
                        str12 + "{\r\n\"S\":\"步骤" + i3 + ":探测齿位" + i8 + "\",\r\n\"T\":1,\r\n\"X\":-1000,\r\n\"Y\":" + split.get(
                            i7
                        ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str13.replace(
                            "%",
                            i8.toString()
                        ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i9: Int = i3 + 1
                    val sb4: StringBuilder = StringBuilder()
                    sb4.append(str22)
                    sb4.append("{\r\n\"S\":\"步骤")
                    sb4.append(i9)
                    val str23: String = str20
                    sb4.append(str23)
                    sb4.append(i8)
                    sb4.append("\",\r\n\"T\":0,\r\n\"X\":")
                    sb4.append(Program.nConfigDecoderMove)
                    val str24: String = str21
                    sb4.append(str24)
                    sb4.append(Program.strMoveXYZSpeed)
                    sb4.append("\",\r\n\"Rule\":\"")
                    sb4.append("SL:L,X")
                    sb4.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    str12 = sb4.toString()
                    i3 = i9 + 1
                    if (i7 < split.size - 1) {
                        val sb5: StringBuilder = StringBuilder()
                        sb5.append(str12)
                        sb5.append("{\r\n\"S\":\"步骤")
                        sb5.append(i3)
                        str9 = str19
                        sb5.append(str9)
                        sb5.append(i7 + 2)
                        str8 = str14
                        sb5.append(str8)
                        sb5.append(split.get(i8))
                        sb5.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                        sb5.append(Program.strMoveXYZSpeed)
                        sb5.append("\",\r\n\"Rule\":\"")
                        sb5.append(str7)
                        sb5.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                        str12 = sb5.toString()
                        i3++
                    } else {
                        str8 = str14
                        str9 = str19
                    }
                    str14 = str8
                    i7 = i8
                    str19 = str9
                    str20 = str23
                    str21 = str24
                }
            } else {
                i3 = 1
            }
        }
        return ((str12 + "{\r\n\"S\":\"步骤" + i3 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i3 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTrack4ExternalKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String,
        i2: Int
    ): String {
        var str5: String
        var str6: String
        val str7: String
        var str8: String
        var i3: Int
        var str9: String
        var str10: String?
        var str11: String?
        var str12: String?
        var str13: String?
        var i4: Int = i2
        var str14: String = "\"" + str2 + "\": [\r\n"
        var split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        if (i == 0) {
            str5 = "DC:1,%;SL:R,X;SL:S,Y"
            str6 = "DC:2,%;SL:L,X;SL:S,Y"
            str7 = "SL:S,Y"
        } else {
            str5 = "DC:1,%;SL:R,X;SL:T,Y"
            str6 = "DC:2,%;SL:L,X;SL:T,Y"
            str7 = "SL:T,Y"
        }
        var i5: Int = 0
        var i6: Int = 1
        while (i5 < split.size) {
            val split2: Array<String> =
                split.get(i5).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            val strArr: Array<String> = split
            var str15: String = str6
            var str16: String? = "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":"
            var str17: String? = ":移到下一个齿位"
            var str18: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
            var str19: String = ":离开探测齿位"
            if (i5 == 0) {
                val i7: Int = i5
                val sb: StringBuilder = StringBuilder()
                sb.append(str14)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i6)
                sb.append(":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":-200,\r\n\"Y\":")
                var str20: String = "\",\r\n\"Des\":\"\"\r\n},\r\n"
                sb.append(split2.get(0))
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"")
                sb.append(str7)
                sb.append(";SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i8: Int = i6 + 1
                i6 = i8 + 1
                var str21: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i8 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i4 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                var i9: Int = 0
                while (i9 < split2.size) {
                    val i10: Int = i9 + 1
                    val replace: String = str5.replace("%", i10.toString())
                    val sb2: StringBuilder = StringBuilder()
                    sb2.append(str21)
                    sb2.append("{\r\n\"S\":\"步骤")
                    sb2.append(i6)
                    sb2.append(":探测齿位")
                    sb2.append(i10)
                    sb2.append("\",\r\n\"T\":1,\r\n\"X\":500,\r\n\"Y\":")
                    sb2.append(split2.get(i9))
                    sb2.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                    sb2.append(Program.strTouchXYZSpeed)
                    sb2.append("\",\r\n\"Rule\":\"")
                    sb2.append(replace)
                    val str22: String = str20
                    sb2.append(str22)
                    val sb3: String = sb2.toString()
                    val i11: Int = i6 + 1
                    val sb4: StringBuilder = StringBuilder()
                    sb4.append(sb3)
                    sb4.append("{\r\n\"S\":\"步骤")
                    sb4.append(i11)
                    val str23: String = str19
                    sb4.append(str23)
                    sb4.append(i10)
                    sb4.append("\",\r\n\"T\":0,\r\n\"X\":-")
                    sb4.append(Program.nConfigDecoderMove)
                    sb4.append(str18)
                    sb4.append(Program.strMoveXYZSpeed)
                    sb4.append("\",\r\n\"Rule\":\"")
                    sb4.append("SL:R,X")
                    sb4.append(str22)
                    str21 = sb4.toString()
                    i6 = i11 + 1
                    val str24: String = str5
                    if (i9 < split2.size - 1) {
                        val sb5: StringBuilder = StringBuilder()
                        sb5.append(str21)
                        sb5.append("{\r\n\"S\":\"步骤")
                        sb5.append(i6)
                        str13 = str17
                        sb5.append(str13)
                        sb5.append(i9 + 2)
                        str12 = str16
                        sb5.append(str12)
                        sb5.append(split2.get(i10))
                        sb5.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                        sb5.append(Program.strMoveXYZSpeed)
                        sb5.append("\",\r\n\"Rule\":\"")
                        sb5.append(str7)
                        sb5.append(str22)
                        str21 = sb5.toString()
                        i6++
                    } else {
                        str12 = str16
                        str13 = str17
                    }
                    str16 = str12
                    i9 = i10
                    str20 = str22
                    str17 = str13
                    str5 = str24
                    str19 = str23
                }
                str8 = str5
                str9 = str15
                str14 = str21
                i3 = i7
            } else {
                var str25: String? = str16
                var str26: String? = str17
                str8 = str5
                i3 = i5
                if (i3 == 1) {
                    val str27: String =
                        str14 + "{\r\n\"S\":\"步骤" + i6 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:Z,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i12: Int = i6 + 1
                    val str28: String =
                        str27 + "{\r\n\"S\":\"步骤" + i12 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":200,\r\n\"Y\":" + split2.get(
                            0
                        ) + ",\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i13: Int = i12 + 1
                    i6 = i13 + 1
                    var str29: String =
                        str28 + "{\r\n\"S\":\"步骤" + i13 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i4 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    var i14: Int = 0
                    while (i14 < split2.size) {
                        val i15: Int = i14 + 1
                        val str30: String = str15
                        val str31: String =
                            str29 + "{\r\n\"S\":\"步骤" + i6 + ":探测齿位" + i15 + "\",\r\n\"T\":1,\r\n\"X\":-500,\r\n\"Y\":" + split2.get(
                                i14
                            ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str30.replace(
                                "%",
                                i15.toString()
                            ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                        i6++
                        val sb6: StringBuilder = StringBuilder()
                        sb6.append(str31)
                        sb6.append("{\r\n\"S\":\"步骤")
                        sb6.append(i6)
                        sb6.append(str19)
                        sb6.append(i15)
                        sb6.append("\",\r\n\"T\":0,\r\n\"X\":")
                        sb6.append(Program.nConfigDecoderMove)
                        val str32: String = str18
                        sb6.append(str32)
                        sb6.append(Program.strMoveXYZSpeed)
                        sb6.append("\",\r\n\"Rule\":\"")
                        sb6.append("SL:L,X")
                        sb6.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                        val sb7: String = sb6.toString()
                        if (i14 < split2.size - 1) {
                            val sb8: StringBuilder = StringBuilder()
                            sb8.append(sb7)
                            sb8.append("{\r\n\"S\":\"步骤")
                            sb8.append(i6)
                            sb8.append(str26)
                            sb8.append(i14 + 2)
                            str10 = str25
                            sb8.append(str10)
                            sb8.append(split2.get(i15))
                            sb8.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                            sb8.append(Program.strMoveXYZSpeed)
                            sb8.append("\",\r\n\"Rule\":\"")
                            sb8.append(str7)
                            sb8.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                            i6++
                            str29 = sb8.toString()
                            str11 = str26
                        } else {
                            str10 = str25
                            str11 = str26
                            str29 = sb7
                        }
                        str25 = str10
                        i14 = i15
                        str15 = str30
                        str26 = str11
                        str18 = str32
                    }
                    str9 = str15
                    str14 = str29
                    i5 = i3 + 1
                    split = strArr
                    i4 = i2
                    str6 = str9
                    str5 = str8
                } else {
                    str9 = str15
                }
            }
            i5 = i3 + 1
            split = strArr
            i4 = i2
            str6 = str9
            str5 = str8
        }
        return ((str14 + "{\r\n\"S\":\"步骤" + i6 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i6 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTrack4ExternalKeyPath2(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        i2: Int
    ): String {
        var str5: String
        var str6: String
        val str7: String
        var str8: String
        var i3: Int
        var str9: String
        var str10: String?
        var str11: String?
        var str12: String
        var i4: Int = i2
        var str13: String = "\"" + str2 + "\": [\r\n"
        var split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        if (i == 0) {
            str5 = "DC:1,%;SL:R,X;SL:S,Y"
            str6 = "DC:2,%;SL:L,X;SL:S,Y"
            str7 = "SL:S,Y"
        } else {
            str5 = "DC:1,%;SL:R,X;SL:T,Y"
            str6 = "DC:2,%;SL:L,X;SL:T,Y"
            str7 = "SL:T,Y"
        }
        var i5: Int = 0
        var i6: Int = 1
        while (i5 < split.size) {
            val split2: Array<String> =
                split.get(i5).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var str14: String = "%"
            val strArr: Array<String> = split
            var str15: String = str5
            var str16: String = ":移到下一个齿位"
            var str17: String = ":离开探测齿位"
            if (i5 == 0) {
                val i7: Int = i5
                val sb: StringBuilder = StringBuilder()
                sb.append(str13)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i6)
                sb.append(":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":200,\r\n\"Y\":")
                var str18: String = "\",\r\n\"Des\":\"\"\r\n},\r\n"
                sb.append(split2.get(0))
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"")
                sb.append(str7)
                sb.append(";SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i8: Int = i6 + 1
                i6 = i8 + 1
                var str19: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i8 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i4 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                var i9: Int = 0
                while (i9 < split2.size) {
                    val i10: Int = i9 + 1
                    val replace: String = str6.replace("%", i10.toString())
                    val sb2: StringBuilder = StringBuilder()
                    sb2.append(str19)
                    sb2.append("{\r\n\"S\":\"步骤")
                    sb2.append(i6)
                    sb2.append(":探测齿位")
                    sb2.append(i10)
                    sb2.append("\",\r\n\"T\":1,\r\n\"X\":-500,\r\n\"Y\":")
                    sb2.append(split2.get(i9))
                    sb2.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                    sb2.append(Program.strTouchXYZSpeed)
                    sb2.append("\",\r\n\"Rule\":\"")
                    sb2.append(replace)
                    val str20: String = str18
                    sb2.append(str20)
                    val sb3: String = sb2.toString()
                    i6++
                    val sb4: StringBuilder = StringBuilder()
                    sb4.append(sb3)
                    sb4.append("{\r\n\"S\":\"步骤")
                    sb4.append(i6)
                    val str21: String = str17
                    sb4.append(str21)
                    sb4.append(i10)
                    sb4.append("\",\r\n\"T\":0,\r\n\"X\":")
                    sb4.append(Program.nConfigDecoderMove)
                    sb4.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                    sb4.append(Program.strMoveXYZSpeed)
                    sb4.append("\",\r\n\"Rule\":\"")
                    sb4.append("SL:L,X")
                    sb4.append(str20)
                    str19 = sb4.toString()
                    if (i9 < split2.size - 1) {
                        val sb5: StringBuilder = StringBuilder()
                        sb5.append(str19)
                        sb5.append("{\r\n\"S\":\"步骤")
                        sb5.append(i6)
                        val str22: String = str16
                        sb5.append(str22)
                        sb5.append(i9 + 2)
                        sb5.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":")
                        sb5.append(split2.get(i10))
                        sb5.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                        sb5.append(Program.strMoveXYZSpeed)
                        sb5.append("\",\r\n\"Rule\":\"")
                        sb5.append(str7)
                        sb5.append(str20)
                        i6++
                        str19 = sb5.toString()
                        str12 = str22
                    } else {
                        str12 = str16
                    }
                    str16 = str12
                    i9 = i10
                    str18 = str20
                    str17 = str21
                }
                str9 = str15
                str13 = str19
                str8 = str6
                i3 = i7
            } else {
                var str23: String? = "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":"
                var str24: String? = str16
                var str25: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
                str8 = str6
                i3 = i5
                if (i3 == 1) {
                    val str26: String =
                        str13 + "{\r\n\"S\":\"步骤" + i6 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:Z,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i11: Int = i6 + 1
                    val str27: String =
                        str26 + "{\r\n\"S\":\"步骤" + i11 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":-200,\r\n\"Y\":" + split2.get(
                            0
                        ) + ",\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i12: Int = i11 + 1
                    i6 = i12 + 1
                    var str28: String =
                        str27 + "{\r\n\"S\":\"步骤" + i12 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i4 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    var i13: Int = 0
                    while (i13 < split2.size) {
                        val i14: Int = i13 + 1
                        val str29: String = str15
                        val str30: String =
                            str28 + "{\r\n\"S\":\"步骤" + i6 + ":探测齿位" + i14 + "\",\r\n\"T\":1,\r\n\"X\":500,\r\n\"Y\":" + split2.get(
                                i13
                            ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str29.replace(
                                str14,
                                i14.toString()
                            ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                        val i15: Int = i6 + 1
                        val sb6: StringBuilder = StringBuilder()
                        sb6.append(str30)
                        sb6.append("{\r\n\"S\":\"步骤")
                        sb6.append(i15)
                        sb6.append(str17)
                        sb6.append(i14)
                        sb6.append("\",\r\n\"T\":0,\r\n\"X\":-")
                        sb6.append(Program.nConfigDecoderMove)
                        val str31: String = str25
                        sb6.append(str31)
                        sb6.append(Program.strMoveXYZSpeed)
                        sb6.append("\",\r\n\"Rule\":\"")
                        sb6.append("SL:R,X")
                        sb6.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                        var sb7: String = sb6.toString()
                        i6 = i15 + 1
                        val str32: String = str14
                        if (i13 < split2.size - 1) {
                            val sb8: StringBuilder = StringBuilder()
                            sb8.append(sb7)
                            sb8.append("{\r\n\"S\":\"步骤")
                            sb8.append(i6)
                            str11 = str24
                            sb8.append(str11)
                            sb8.append(i13 + 2)
                            str10 = str23
                            sb8.append(str10)
                            sb8.append(split2.get(i14))
                            sb8.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                            sb8.append(Program.strMoveXYZSpeed)
                            sb8.append("\",\r\n\"Rule\":\"")
                            sb8.append(str7)
                            sb8.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                            sb7 = sb8.toString()
                            i6++
                        } else {
                            str10 = str23
                            str11 = str24
                        }
                        str28 = sb7
                        str23 = str10
                        i13 = i14
                        str24 = str11
                        str25 = str31
                        str15 = str29
                        str14 = str32
                    }
                    str9 = str15
                    str13 = str28
                    i5 = i3 + 1
                    split = strArr
                    i4 = i2
                    str5 = str9
                    str6 = str8
                } else {
                    str9 = str15
                }
            }
            i5 = i3 + 1
            split = strArr
            i4 = i2
            str5 = str9
            str6 = str8
        }
        return ((str13 + "{\r\n\"S\":\"步骤" + i6 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i6 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetChannelTrackKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int
    ): String {
        val str6: String
        val str7: String
        val str8: String
        val i3: Int
        var i4: Int
        val i5: Int
        var str9: String = "\"" + str2 + "\": [\r\n"
        if (i == 0) {
            str6 = "DC:1,%;SL:L,X;SL:S,Y"
            str7 = "DC:2,%;SL:R,X;SL:S,Y"
            str8 = "SL:S,Y"
        } else {
            str6 = "DC:1,%;SL:L,X;SL:T,Y"
            str7 = "DC:1,%;SL:R,X;SL:T,Y"
            str8 = "SL:T,Y"
        }
        val split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val str10: String = str6
        var str11: String? = "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
        val str12: String = str7
        val str13: String = str8
        if ((str3 == "0") || (str3 == "5")) {
            var str14: String? = str11
            val split2: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split2.size > 0) {
                val parseInt: Int =
                    split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).toInt()
                val parseInt2: Int =
                    split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                        .get(split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().size - 1
                        ).toInt()
                i3 = if (parseInt > parseInt2) parseInt + 50 else parseInt2 + 50
            } else {
                i3 = 0
            }
            var str15: String =
                ((str9 + "{\r\n\"S\":\"步骤1:移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":-" + i3 + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,Y;SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                    split.size - 1
                )
                    .toInt() + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str13 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length: Int = split.size - 1
            i4 = 4
            var i6: Int = -1
            while (length > i6) {
                val i7: Int = length + 1
                val replace: String = str10.replace("%", i7.toString())
                val sb: StringBuilder = StringBuilder()
                sb.append(str15)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i4)
                sb.append(":回退一点,齿位")
                sb.append(i7)
                sb.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-10,\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                val str16: String? = str14
                sb.append(str16)
                val i8: Int = i4 + 1
                val str17: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i8 + ":探测钥匙A面,齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i9: Int = i8 + 1
                val str18: String =
                    str17 + "{\r\n\"S\":\"步骤" + i9 + ":离开钥匙A面,齿位" + i7 + "\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i10: Int = i9 + 1
                val str19: String =
                    str18 + "{\r\n\"S\":\"步骤" + i10 + ":探测钥匙B面,齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i11: Int = i10 + 1
                val str20: String =
                    str19 + "{\r\n\"S\":\"步骤" + i11 + ":离开B面移动到AB两边的中间,齿位" + i7 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,X,X-" + (i11 - 3).toString() + ",X-" + (i11 - 1).toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i12: Int = i11 + 1
                val str21: String =
                    str20 + "{\r\n\"S\":\"步骤" + i12 + ":往" + i7 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str13 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i13: Int = i12 + 1
                val str22: String =
                    str21 + "{\r\n\"S\":\"步骤" + i13 + ":探测齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i14: Int = i13 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str22)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i14)
                sb2.append(":离开探测齿位")
                sb2.append(i7)
                sb2.append("\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                sb2.append("\",\r\n\"Rule\":\"S:")
                sb2.append(i14 - 1)
                sb2.append(",X\",\r\n\"Des\":\"\"\r\n},\r\n")
                str15 = sb2.toString()
                i4 = i14 + 1
                length--
                str14 = str16
                i6 = -1
            }
            str9 = str15
        } else if ((str3 == "1")) {
            val split3: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split3.size > 0) {
                val parseInt3: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).toInt()
                val parseInt4: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                        .get(split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().size - 1
                        ).toInt()
                i5 = if (parseInt3 > parseInt4) parseInt3 + 100 else parseInt4 + 100
            } else {
                i5 = 0
            }
            var str23: String =
                ((str9 + "{\r\n\"S\":\"步骤1:移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":" + i5 + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,Y;SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":0,\r\n\"Y\":" + (split.get(
                    split.size - 1
                )
                    .toInt() + 10) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str13 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length2: Int = split.size - 1
            i4 = 4
            var i15: Int = -1
            while (length2 > i15) {
                val i16: Int = length2 + 1
                val replace2: String = str12.replace("%", i16.toString())
                val sb3: StringBuilder = StringBuilder()
                sb3.append(str23)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i4)
                sb3.append(":回退一点,齿位")
                sb3.append(i16)
                sb3.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-20,\r\n\"Z\":0,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                val str24: String? = str11
                sb3.append(str24)
                val i17: Int = i4 + 1
                val str25: String =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i17 + ":探测钥匙B面,齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i18: Int = i17 + 1
                val str26: String =
                    str25 + "{\r\n\"S\":\"步骤" + i18 + ":离开钥匙B面,齿位" + i16 + "\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i19: Int = i18 + 1
                val str27: String =
                    str26 + "{\r\n\"S\":\"步骤" + i19 + ":探测钥匙A面,齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i20: Int = i19 + 1
                val str28: String =
                    str27 + "{\r\n\"S\":\"步骤" + i20 + ":离开A面移动到AB两边的中间,齿位" + i16 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,X,X-" + (i20 - 1).toString()
                        .toString() + ",X-" + (i20 - 3).toString()
                        .toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i21: Int = i20 + 1
                val str29: String =
                    str28 + "{\r\n\"S\":\"步骤" + i21 + ":往" + i16 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length2
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str13 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i22: Int = i21 + 1
                val str30: String =
                    str29 + "{\r\n\"S\":\"步骤" + i22 + ":探测齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length2
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace2 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i23: Int = i22 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(str30)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i23)
                sb4.append(":离开探测齿位")
                sb4.append(i16)
                sb4.append("\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb4.append(Program.strMoveXYZSpeed)
                sb4.append("\",\r\n\"Rule\":\"S:")
                sb4.append(i23 - 1)
                sb4.append(",X\",\r\n\"Des\":\"\"\r\n},\r\n")
                str23 = sb4.toString()
                i4 = i23 + 1
                length2--
                str11 = str24
                i15 = -1
            }
            str9 = str23
        } else {
            i4 = 1
        }
        return ((str9 + "{\r\n\"S\":\"步骤" + i4 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i4 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetDoubleChannelTrackKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int
    ): String {
        val str6: String
        val str7: String
        val str8: String
        var str9: String
        var str10: String
        var str11: String
        val str12: String
        val i3: Int
        var i4: Int = i2
        val str13: String = "\"" + str2 + "\": [\r\n"
        var str14: String = if (i == 0) "DC:1,%;SL:L,X;SL:S,Y" else "DC:1,%;SL:L,X;SL:T,Y"
        var str15: String = ";"
        var split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray().get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray().get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var str16: String = "\",\r\n\"Rule\":\"SL:CKP,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
        var str17: String = "%"
        var str18: String = ":探测齿位"
        var str19: String = "\",\r\n\"Rule\":\"SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
        var str20: String = ",\r\n\"Z\":"
        var str21: String = ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":"
        if (split.size > 1) {
            var GetmdDepthClass: List<mdDepthClass> = GetmdDepthClass(
                1,
                PublicMethodPort.trimend(str5, ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
            )
            var str22: String = str13
            var length: Int = split.size - 1
            var i5: Int = 1
            var i6: Int = -1
            while (length > i6) {
                val i7: Int = length + 1
                val str23: String = str15
                str14.replace(str17, i7.toString())
                var i8: Int = i5
                val str24: String = str17
                val str25: String = str14
                val str26: String = str16
                var str27: String = str22
                for (i9 in GetmdDepthClass.indices) {
                    val str28: String =
                        str27 + "{\r\n\"S\":\"步骤" + i8 + ":移动到钥匙的上方测试2\",\r\n\"T\":0,\r\n\"X\":-" + GetmdDepthClass.get(
                            i9
                        )
                            .depthValue + ",\r\n\"Y\":" + split.get(length) + ",\r\n\"Z\":-200,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i10: Int = i8 + 1
                    val sb: StringBuilder = StringBuilder()
                    sb.append(str28)
                    sb.append("{\r\n\"S\":\"步骤")
                    sb.append(i10)
                    sb.append(":探测钥匙A面,齿位")
                    sb.append(i7)
                    sb.append(",齿深")
                    sb.append(i9)
                    sb.append("\",\r\n\"T\":1,\r\n\"X\":-")
                    sb.append(GetmdDepthClass.get(i9).depthValue)
                    sb.append(",\r\n\"Y\":")
                    sb.append(split.get(length))
                    sb.append(",\r\n\"Z\":300,\r\n\"V\":\"")
                    sb.append(Program.strMoveXYZSpeed)
                    sb.append("\",\r\n\"Rule\":\"SL:L,X;SL:S,Y;SL:U,Z;CKP:SLU,Z,")
                    sb.append(i4)
                    sb.append(";GO5:D,")
                    sb.append((((GetmdDepthClass.size - i9) * 3) + 2) - 3)
                    sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val i11: Int = i10 + 1
                    str27 =
                        sb.toString() + "{\r\n\"S\":\"步骤" + i11 + ":离开钥匙A面,齿位" + i7 + ",齿深" + i9 + "\",\r\n\"T\":0,\r\n\"X\":-" + GetmdDepthClass.get(
                            i9
                        )
                            .depthValue + ",\r\n\"Y\":" + split.get(length) + ",\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    i8 = i11 + 1
                }
                val str29: String =
                    str27 + "{\r\n\"S\":\"步骤" + i8 + ":Z轴向上\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i12: Int = i8 + 1
                val str30: String =
                    str29 + "{\r\n\"S\":\"步骤" + i12 + ":移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length
                    ) + ",\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + str26
                val i13: Int = i12 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str30)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i13)
                val str31: String = str21
                sb2.append(str31)
                val list: List<mdDepthClass> = GetmdDepthClass
                sb2.append(split.get(length))
                val str32: String = str20
                sb2.append(str32)
                sb2.append(i4)
                val strArr: Array<String> = split
                sb2.append(",\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                val str33: String = str19
                sb2.append(str33)
                val i14: Int = i13 + 1
                str19 = str33
                val str34: String =
                    sb2.toString() + "{\r\n\"S\":\"步骤" + i14 + str18 + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X;DC:1," + i7.toString() + ";\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i15: Int = i14 + 1
                val str35: String =
                    str34 + "{\r\n\"S\":\"步骤" + i15 + ":离开探测齿位" + i7 + "\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i16: Int = i15 + 1
                str22 =
                    str35 + "{\r\n\"S\":\"步骤" + i16 + ":Z轴向上\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                length--
                str16 = str26
                i5 = i16 + 1
                split = strArr
                str15 = str23
                str17 = str24
                i6 = -1
                str20 = str32
                GetmdDepthClass = list
                str21 = str31
                str14 = str25
            }
            str6 = str17
            str7 = str14
            str8 = str15
            str9 = str16
            str10 = str20
            str11 = str21
            i3 = i5
            str12 = str22
        } else {
            str6 = "%"
            str7 = str14
            str8 = ";"
            str9 = "\",\r\n\"Rule\":\"SL:CKP,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
            str10 = str20
            str11 = str21
            str12 = str13
            i3 = 1
        }
        var str36: String =
            str12 + "{\r\n\"S\":\"步骤" + i3 + ":Z轴向上，移动到钥匙上表面\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-200,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
        var i17: Int = i3 + 1
        if (split2.size > 1) {
            val str37: String = str8
            val GetmdDepthClass2: List<mdDepthClass> = GetmdDepthClass(
                1,
                PublicMethodPort.trimend(str5, str37).split(str37.toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(1)
            )
            var i18: Int = i17
            var length2: Int = split2.size - 1
            var str38: String = str36
            while (length2 > -1) {
                val i19: Int = length2 + 1
                val str39: String = str38
                val str40: String = str11
                val str41: String = str10
                str7.replace(str6, i19.toString())
                var i20: Int = i18
                val str42: String = str9
                var str43: String = str39
                for (i21 in GetmdDepthClass2.indices) {
                    val str44: String =
                        str43 + "{\r\n\"S\":\"步骤" + i20 + ":移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":" + GetmdDepthClass2.get(
                            i21
                        )
                            .depthValue + ",\r\n\"Y\":" + split2.get(length2) + ",\r\n\"Z\":-200,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i22: Int = i20 + 1
                    val sb3: StringBuilder = StringBuilder()
                    sb3.append(str44)
                    sb3.append("{\r\n\"S\":\"步骤")
                    sb3.append(i22)
                    sb3.append(":探测钥匙B面,齿位")
                    sb3.append(i19)
                    sb3.append(",齿深")
                    sb3.append(i21)
                    sb3.append("\",\r\n\"T\":1,\r\n\"X\":")
                    sb3.append(GetmdDepthClass2.get(i21).depthValue)
                    sb3.append(",\r\n\"Y\":")
                    sb3.append(split2.get(length2))
                    sb3.append(",\r\n\"Z\":300,\r\n\"V\":\"")
                    sb3.append(Program.strMoveXYZSpeed)
                    sb3.append("\",\r\n\"Rule\":\"SL:R,X;SL:S,Y;SL:U,Z;CKP:SLU,Z,")
                    sb3.append(i4)
                    sb3.append(";GO5:D,")
                    sb3.append((((GetmdDepthClass2.size - i21) * 3) + 2) - 3)
                    sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val i23: Int = i22 + 1
                    str43 =
                        sb3.toString() + "{\r\n\"S\":\"步骤" + i23 + ":离开钥匙B面,齿位" + i19 + ",齿深" + i21 + "\",\r\n\"T\":0,\r\n\"X\":" + GetmdDepthClass2.get(
                            i21
                        )
                            .depthValue + ",\r\n\"Y\":" + split2.get(length2) + ",\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X;SL:S,Y;SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    i20 = i23 + 1
                }
                val str45: String =
                    str43 + "{\r\n\"S\":\"步骤" + i20 + ":Z轴向上\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i24: Int = i20 + 1
                val str46: String =
                    str45 + "{\r\n\"S\":\"步骤" + i24 + ":移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split2.get(
                        length2
                    ) + ",\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + str42
                val i25: Int = i24 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(str46)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i25)
                str11 = str40
                sb4.append(str11)
                sb4.append(split2.get(length2))
                sb4.append(str41)
                sb4.append(i4)
                sb4.append(",\r\n\"V\":\"")
                sb4.append(Program.strMoveXYZSpeed)
                val str47: String = str19
                sb4.append(str47)
                val sb5: String = sb4.toString()
                val i26: Int = i25 + 1
                str19 = str47
                val sb6: StringBuilder = StringBuilder()
                sb6.append(sb5)
                sb6.append("{\r\n\"S\":\"步骤")
                sb6.append(i26)
                val str48: String = str18
                sb6.append(str48)
                sb6.append(i19)
                sb6.append("\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb6.append(Program.strTouchXYZSpeed)
                sb6.append("\",\r\n\"Rule\":\"SL:R,X;DC:2,")
                sb6.append(i19.toString())
                sb6.append(";\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i27: Int = i26 + 1
                val str49: String =
                    sb6.toString() + "{\r\n\"S\":\"步骤" + i27 + ":离开探测齿位" + i19 + "\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i28: Int = i27 + 1
                val i29: Int = i28 + 1
                length2--
                i4 = i2
                str38 =
                    str49 + "{\r\n\"S\":\"步骤" + i28 + ":Z轴向上\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                i18 = i29
                str18 = str48
                str10 = str41
                str9 = str42
            }
            i17 = i18
            str36 = str38
        }
        return ((str36 + "{\r\n\"S\":\"步骤" + i17 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i17 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetChannelTrackKeyPath_3KS(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int
    ): String {
        val str6: String
        val str7: String
        val str8: String
        val i3: Int
        var i4: Int
        val i5: Int
        var str9: String = "\"" + str2 + "\": [\r\n"
        if (i == 0) {
            str6 = "DC:1,%;SL:L,X;SL:S,Y"
            str7 = "DC:2,%;SL:R,X;SL:S,Y"
            str8 = "SL:S,Y"
        } else {
            str6 = "DC:1,%;SL:L,X;SL:T,Y"
            str7 = "DC:1,%;SL:R,X;SL:T,Y"
            str8 = "SL:T,Y"
        }
        val split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val str10: String = str6
        var str11: String? = "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
        var str12: String = ":回退一点,齿位"
        val str13: String = str7
        val str14: String = str8
        if ((str3 == "0") || (str3 == "5")) {
            var str15: String? = str11
            val split2: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split2.size > 0) {
                split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    .get(0).toInt()
                i3 = 1
                split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    .get(split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().size - 1
                    ).toInt()
            } else {
                i3 = 1
            }
            val str16: String =
                ((str9 + "{\r\n\"S\":\"步骤" + i3 + ":移动到钥匙的上方测试\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,Y;SL:C,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                    split.size - 1
                )
                    .toInt() + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str14 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length: Int = split.size - 1
            str9 = str16
            i4 = 4
            while (length > -1) {
                val i6: Int = length + 1
                val replace: String = str10.replace("%", i6.toString())
                val sb: StringBuilder = StringBuilder()
                sb.append(str9)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i4)
                sb.append(str12)
                sb.append(i6)
                sb.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-10,\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                val str17: String? = str15
                sb.append(str17)
                val i7: Int = i4 + 1
                val str18: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i7 + ":探测钥匙A面,齿位" + i6 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i8: Int = i7 + 1
                val str19: String =
                    str18 + "{\r\n\"S\":\"步骤" + i8 + ":离开钥匙A面,齿位" + i6 + "\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i9: Int = i8 + 1
                val str20: String =
                    str19 + "{\r\n\"S\":\"步骤" + i9 + ":探测钥匙B面,齿位" + i6 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i10: Int = i9 + 1
                val str21: String =
                    str20 + "{\r\n\"S\":\"步骤" + i10 + ":离开B面移动到AB两边的中间,齿位" + i6 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,X,X-" + (i10 - 3).toString() + ",X-" + (i10 - 1).toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i11: Int = i10 + 1
                val str22: String =
                    str21 + "{\r\n\"S\":\"步骤" + i11 + ":往" + i6 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str14 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i12: Int = i11 + 1
                val str23: String =
                    str22 + "{\r\n\"S\":\"步骤" + i12 + ":探测齿位" + i6 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i13: Int = i12 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str23)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i13)
                sb2.append(":离开探测齿位")
                sb2.append(i6)
                sb2.append("\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                sb2.append("\",\r\n\"Rule\":\"S:")
                sb2.append(i13 - 1)
                sb2.append(",X\",\r\n\"Des\":\"\"\r\n},\r\n")
                i4 = i13 + 1
                length--
                str9 = sb2.toString()
                str15 = str17
            }
        } else if ((str3 == "1")) {
            val split3: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split3.size > 0) {
                val parseInt: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).toInt()
                val parseInt2: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                        .get(split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().size - 1
                        ).toInt()
                i5 = if (parseInt > parseInt2) parseInt + 100 else parseInt2 + 100
            } else {
                i5 = 0
            }
            val str24: String =
                ((str9 + "{\r\n\"S\":\"步骤1:移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":" + i5 + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,Y;SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":0,\r\n\"Y\":" + (split.get(
                    split.size - 1
                )
                    .toInt() + 10) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str14 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length2: Int = split.size - 1
            str9 = str24
            i4 = 4
            var i14: Int = -1
            while (length2 > i14) {
                val i15: Int = length2 + 1
                val replace2: String = str13.replace("%", i15.toString())
                val sb3: StringBuilder = StringBuilder()
                sb3.append(str9)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i4)
                val str25: String = str12
                sb3.append(str25)
                sb3.append(i15)
                sb3.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-20,\r\n\"Z\":0,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                val str26: String? = str11
                sb3.append(str26)
                val i16: Int = i4 + 1
                val str27: String =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i16 + ":探测钥匙B面,齿位" + i15 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i17: Int = i16 + 1
                val str28: String =
                    str27 + "{\r\n\"S\":\"步骤" + i17 + ":离开钥匙B面,齿位" + i15 + "\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i18: Int = i17 + 1
                val str29: String =
                    str28 + "{\r\n\"S\":\"步骤" + i18 + ":探测钥匙A面,齿位" + i15 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,X\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i19: Int = i18 + 1
                val str30: String =
                    str29 + "{\r\n\"S\":\"步骤" + i19 + ":离开A面移动到AB两边的中间,齿位" + i15 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,X,X-" + (i19 - 1).toString()
                        .toString() + ",X-" + (i19 - 3).toString()
                        .toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i20: Int = i19 + 1
                val str31: String =
                    str30 + "{\r\n\"S\":\"步骤" + i20 + ":往" + i15 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length2
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str14 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i21: Int = i20 + 1
                val str32: String =
                    str31 + "{\r\n\"S\":\"步骤" + i21 + ":探测齿位" + i15 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":" + split.get(
                        length2
                    ) + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace2 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i22: Int = i21 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(str32)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i22)
                sb4.append(":离开探测齿位")
                sb4.append(i15)
                sb4.append("\",\r\n\"T\":0,\r\n\"X\":20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb4.append(Program.strMoveXYZSpeed)
                sb4.append("\",\r\n\"Rule\":\"S:")
                sb4.append(i22 - 1)
                sb4.append(",X\",\r\n\"Des\":\"\"\r\n},\r\n")
                str9 = sb4.toString()
                i4 = i22 + 1
                length2--
                str12 = str25
                str11 = str26
                i14 = -1
            }
        } else {
            i4 = 1
        }
        return ((str9 + "{\r\n\"S\":\"步骤" + i4 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i4 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetDimpleKeyPath(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String
    ): String {
        var parseInt: Int
        var str5: String = "\"" + str + "\": [\r\n"
        var str6: String = if (i == 0) "SL:S,Y" else "SL:T,Y"
        val split: Array<String> =
            PublicMethodPort.trimend(str3, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split3: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i2: Int = 1
        var i3: Int = 0
        var i4: Int = 1
        while (i3 < split2.size) {
            val split4: Array<String> =
                split2.get(i3).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            val split5: Array<String> =
                split3.get(i3).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            var i5: Int = 0
            while (i5 < split4.size) {
                if (split5.get(i5).toInt() < 0) {
                    parseInt = split4.get(i5).toInt() - (abs(split5.get(i5).toInt()) / 2)
                } else {
                    parseInt = split4.get(i5).toInt()
                }
                val abs: Int = if (split.get(i3).length < i2) 100 else abs(split.get(i3).toInt())
                val sb: StringBuilder = StringBuilder()
                sb.append(str5)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i4)
                sb.append(":移动到轴")
                val i6: Int = i3 + 1
                sb.append(i6)
                sb.append(",")
                i5++
                sb.append(i5)
                sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":-")
                sb.append(abs)
                sb.append(",\r\n\"Y\":")
                sb.append(parseInt)
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,X;")
                sb.append(str6)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i7: Int = i4 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i7)
                sb3.append(":探测轴")
                sb3.append(i6)
                sb3.append(",")
                sb3.append(i5)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":5000,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"U:Z;")
                val str7: String = str6
                sb3.append("DC:@,%".replace("@", i6.toString()).replace("%", i5.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i8: Int = i7 + 1
                str5 =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i8 + ":Z轴向上轴" + i6 + "," + i5 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-300,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i4 = i8 + 1
                str6 = str7
                i2 = 1
            }
            i3++
            i2 = 1
        }
        return ((str5 + "{\r\n\"S\":\"步骤" + i4 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i4 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTubularKeyPath(str: String, str2: String): String {
        var str3: String = "\"" + str + "\": [\r\n"
        val split: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i: Int = 0
        var i2: Int = 1
        while (i < split.size) {
            val sb: StringBuilder = StringBuilder()
            sb.append(str3)
            sb.append("{\r\n\"S\":\"步骤")
            sb.append(i2)
            sb.append(":移动到")
            val i3: Int = i + 1
            sb.append(i3)
            sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":")
            sb.append(split.get(i))
            sb.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
            sb.append(Program.strMoveXYZSpeed)
            sb.append("\",\r\n\"Rule\":\"")
            sb.append("SL:TC,X")
            sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
            val i4: Int = i2 + 1
            val str4: String =
                sb.toString() + "{\r\n\"S\":\"步骤" + i4 + ":探测" + i3 + "齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":600,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z;" + "DC:1,%;".replace(
                    "%",
                    i3.toString()
                ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            val i5: Int = i4 + 1
            i2 = i5 + 1
            i = i3
            str3 =
                str4 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上" + i3 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
        }
        return ((str3 + "{\r\n\"S\":\"步骤" + i2 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i2 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetSingleAngleKeyPath(
        i: Int,
        str: String,
        str2: String,
        i2: Int,
        str3: String
    ): String {
        var parseInt: Int
        var str4: String = "\"" + str + "\": [\r\n"
        var str5: String = if (i == 0) "SL:S,Y" else "SL:T,Y"
        var i3: Int = 1
        val split: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            PublicMethodPort.trimend(str3, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val strArr: Array<String> = arrayOf((i2 / 2).toString())
        var i4: Int = 0
        var i5: Int = 1
        while (i4 < split.size) {
            val split3: Array<String> =
                split.get(i4).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            val split4: Array<String> =
                split2.get(i4).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            var i6: Int = 0
            while (i6 < split3.size) {
                if (split4.get(i6).toInt() < 0) {
                    parseInt = split3.get(i6).toInt() - (abs(split4.get(i6).toInt()) / 2)
                } else {
                    parseInt = split3.get(i6).toInt()
                }
                val abs: Int = if (strArr.get(i4).length < i3) 100 else abs(strArr.get(i4).toInt())
                val sb: StringBuilder = StringBuilder()
                sb.append(str4)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i5)
                sb.append(":移动到轴")
                val i7: Int = i4 + 1
                sb.append(i7)
                sb.append(",")
                i6++
                sb.append(i6)
                sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":-")
                sb.append(abs)
                sb.append(",\r\n\"Y\":")
                sb.append(parseInt)
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,X;")
                sb.append(str5)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i8: Int = i5 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i8)
                sb3.append(":探测轴")
                sb3.append(i7)
                sb3.append(",")
                sb3.append(i6)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":5000,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"U:Z;")
                val str6: String = str5
                sb3.append("DC:@,%".replace("@", i7.toString()).replace("%", i6.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i9: Int = i8 + 1
                str4 =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i9 + ":Z轴向上轴" + i7 + "," + i6 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-300,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i5 = i9 + 1
                str5 = str6
                i3 = 1
            }
            i4++
            i3 = 1
        }
        return ((str4 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i5 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(4:(3:61|62|(1:64)(2:65|(10:67|68|(5:72|(8:74|75|76|77|(14:81|82|83|84|85|86|87|88|89|90|(2:92|93)(3:95|96|97)|94|78|79)|100|101|102)(2:109|(14:111|112|113|114|115|116|117|118|119|120|(14:123|124|125|126|127|128|129|130|131|132|133|(3:135|136|137)(2:139|140)|138|121)|145|146|147)(2:153|154))|103|69|70)|155|156|47|48|49|50|51)(2:159|160)))|49|50|51)|16|17|18|19|20|21|22|(3:23|24|(8:26|27|28|29|30|31|(3:33|34|35)(2:37|38)|36)(1:45))|46|47|48) */ /* JADX WARN: Code restructure failed: missing block: B:58:0x05e4, code lost:
    
        r5 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private fun GetStandardDoubleKeyPath_Left_Horizontal(
        r37: Int,
        r38: String,
        r39: String,
        r40: String?,
        r41: String
    ): String {
        /*
            Method dump skipped, instructions count: 1522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw UnsupportedOperationException("Method not decompiled: com.spl.key.mdKeyDecoderPathClass.GetStandardDoubleKeyPath_Left_Horizontal(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String")
    }

    private fun GetStandardSingleKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        i2: Int,
        i3: Int
    ): String? {
        val i4: Int = i2 - 200
        var split: Array<String> =
            str.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        var c: Char = 0.toChar()
        var str4: String = ""
        var i5: Int = 0
        while (i5 < split.size) {
            val str5: String = str4 + "\"" + split.get(i5) + "\": [\r\n"
            var str6: String = if (i == 0) "SL:S,X" else "SL:T,X"
            val i6: Int = if (i3 > 0) i3 / 2 else -280
            val split2: Array<String> =
                PublicMethodPort.trimend(str3, ";").toString().split(",".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            var str7: String =
                (str5 + "{\r\n\"S\":\"步骤1:移动到1齿位上方\",\r\n\"T\":0,\r\n\"X\":" + split2.get(c.code) + ",\r\n\"Y\":-" + i4 + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y;" + str6 + "\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i6 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
            var i7: Int = 3
            var i8: Int = 0
            while (i8 < split.get(i5).toInt()) {
                val sb: StringBuilder = StringBuilder()
                sb.append(str7)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i7)
                sb.append(":移动到")
                val i9: Int = i8 + 1
                sb.append(i9)
                val strArr: Array<String> = split
                sb.append("齿位左边\",\r\n\"T\":0,\r\n\"X\":")
                sb.append(split2.get(i8))
                sb.append(",\r\n\"Y\":-")
                sb.append(i4)
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,Y;")
                sb.append(str6)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i10: Int = i7 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i10)
                sb3.append(":探测")
                sb3.append(i9)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"SL:L,Y;")
                sb3.append("DC:1,%;".replace("%", i9.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i11: Int = i10 + 1
                val str8: String =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i11 + ":离开" + i9 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-" + i4 + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i7 = i11 + 1
                i8 = i9
                str6 = str6
                str7 = str8
                split = strArr
            }
            val strArr2: Array<String> = split
            str4 =
                ((str7 + "{\r\n\"S\":\"步骤" + i7 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i7 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ],"
            i5++
            split = strArr2
            c = 0.toChar()
        }
        return PublicMethodPort.trimend(str4, ",")
    }

    private fun GetTrack4InternalKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int,
        i3: Int
    ): String {
        var i4: Int
        var i5: Int
        var i6: Int
        var list: List<mdSpaceClass>
        var str6: String
        var str7: String
        var str8: String
        val str9: String = "\"" + str2 + "\": [\r\n"
        var str10: String = if (i == 0) "SL:S,X" else "SL:T,X"
        val i7: Int = i2 / 2
        val split: Array<String> =
            PublicMethodPort.trimend(str5, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i8: Int = 100
        var str11: String = ","
        var i9: Int = 0
        var i10: Int = 1
        if (split.size == 2) {
            split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                .toInt()
            split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(split.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size - 1
                ).toInt()
            split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                .toInt()
            split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(split.get(1).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size - 1
                ).toInt()
            i4 = 100
        } else {
            i4 = 0
            i8 = 0
        }
        val sb: StringBuilder = StringBuilder()
        sb.append(str9)
        sb.append("{\r\n\"S\":\"步骤")
        sb.append(1)
        sb.append(":移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":")
        sb.append(i7)
        var str12: String = ",\r\n\"Z\":0,\r\n\"V\":\""
        sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
        sb.append(Program.strMoveXYZSpeed)
        sb.append("\",\r\n\"Rule\":\"SL:T,X;SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n")
        var str13: String =
            sb.toString() + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i3 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
        var GetmdSpaceClass: List<mdSpaceClass> = GetmdSpaceClass(i, str4)
        var i11: Int = 3
        while (i9 < GetmdSpaceClass.size) {
            val str14: String =
                str13 + "{\r\n\"S\":\"步骤" + i11 + ":移动到对应齿位【轴:" + GetmdSpaceClass.get(i9)
                    .rowNum + ",齿位:" + GetmdSpaceClass.get(i9)
                    .spaceNum + "】\",\r\n\"T\":0,\r\n\"X\":" + GetmdSpaceClass.get(i9)
                    .spaceValue + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str10 + ";GO:D,6\",\r\n\"Des\":\"\"\r\n},\r\n"
            val i12: Int = i11 + 1
            val str15: String =
                str14 + "{\r\n\"S\":\"步骤" + i12 + ":回退一点-Y\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
            i11 = i12 + i10
            val i13: Int = i4
            val str16: String = str12
            val i14: Int = i8
            val str17: String = str10
            val str18: String = str11
            val list2: List<mdSpaceClass> = GetmdSpaceClass
            if (GetmdSpaceClass.get(i9).rowNum == 1) {
                val str19: String =
                    str15 + "{\r\n\"S\":\"步骤" + i11 + ":探测左边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i15: Int = i11 + 1
                val str20: String =
                    str19 + "{\r\n\"S\":\"步骤" + i15 + ":离开左边一点-X\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":20,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i16: Int = i15 + 1
                val str21: String =
                    str20 + "{\r\n\"S\":\"步骤" + i16 + ":探测右边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i17: Int = i16 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str21)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i17)
                sb2.append(":移动到左右两边的中间(跳转到第1步)\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                sb2.append("\",\r\n\"Rule\":\"C:2,Y,Y-")
                sb2.append(i17 - 3)
                sb2.append(",Y-")
                sb2.append(i17 - 1)
                sb2.append(";GO2:U,5\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb3: String = sb2.toString()
                val i18: Int = i17 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(sb3)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i18)
                sb4.append(":探测齿位【轴:")
                sb4.append(list2.get(i9).rowNum)
                sb4.append(",齿位:")
                sb4.append(list2.get(i9).spaceNum)
                sb4.append("】\",\r\n\"T\":1,\r\n\"X\":")
                sb4.append(list2.get(i9).spaceValue)
                sb4.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb4.append(Program.strTouchXYZSpeed)
                sb4.append("\",\r\n\"Rule\":\"DC:")
                sb4.append(list2.get(i9).rowNum)
                str8 = str18
                sb4.append(str8)
                sb4.append(list2.get(i9).spaceNum)
                sb4.append(";SL:L,Y;")
                sb4.append(str17)
                sb4.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i19: Int = i18 + 1
                i11 = i19 + 1
                i5 = i14
                str13 =
                    sb4.toString() + "{\r\n\"S\":\"步骤" + i19 + ":离开探测齿位-X\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + i14 + str16 + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                str7 = str16
                list = list2
                str6 = str17
                i6 = i13
            } else {
                i5 = i14
                if (list2.get(i9).rowNum == 2) {
                    val str22: String =
                        str15 + "{\r\n\"S\":\"步骤" + i11 + ":探测右边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i20: Int = i11 + 1
                    val str23: String =
                        str22 + "{\r\n\"S\":\"步骤" + i20 + ":离开右边一点-X\",\r\n\"T\":0,\r\n\"Y\":0,\r\n\"X\":-20,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i21: Int = i20 + 1
                    val str24: String =
                        str23 + "{\r\n\"S\":\"步骤" + i21 + ":探测左边-X\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i22: Int = i21 + 1
                    val sb5: StringBuilder = StringBuilder()
                    sb5.append(str24)
                    sb5.append("{\r\n\"S\":\"步骤")
                    sb5.append(i22)
                    sb5.append(":移动到左右两边的中间(跳转到第1步)\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                    sb5.append(Program.strMoveXYZSpeed)
                    sb5.append("\",\r\n\"Rule\":\"C:2,Y,Y-")
                    sb5.append(i22 - 1)
                    sb5.append(",Y-")
                    sb5.append(i22 - 3)
                    sb5.append(";GO2:U,5\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val sb6: String = sb5.toString()
                    val i23: Int = i22 + 1
                    val sb7: StringBuilder = StringBuilder()
                    sb7.append(sb6)
                    sb7.append("{\r\n\"S\":\"步骤")
                    sb7.append(i23)
                    sb7.append(":探测齿位【轴:")
                    list = list2
                    sb7.append(list.get(i9).rowNum)
                    sb7.append(",齿位:")
                    sb7.append(list.get(i9).spaceNum)
                    sb7.append("】\",\r\n\"T\":1,\r\n\"X\":")
                    sb7.append(list.get(i9).spaceValue)
                    sb7.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                    sb7.append(Program.strTouchXYZSpeed)
                    sb7.append("\",\r\n\"Rule\":\"DC:")
                    sb7.append(list.get(i9).rowNum)
                    str8 = str18
                    sb7.append(str8)
                    sb7.append(list.get(i9).spaceNum)
                    sb7.append(";SL:R,Y;")
                    str6 = str17
                    sb7.append(str6)
                    sb7.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    val sb8: String = sb7.toString()
                    val i24: Int = i23 + 1
                    val sb9: StringBuilder = StringBuilder()
                    sb9.append(sb8)
                    sb9.append("{\r\n\"S\":\"步骤")
                    sb9.append(i24)
                    sb9.append(":离开探测齿位-X\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-")
                    i6 = i13
                    sb9.append(i6)
                    str7 = str16
                    sb9.append(str7)
                    sb9.append(Program.strMoveXYZSpeed)
                    sb9.append("\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n")
                    str13 = sb9.toString()
                    i11 = i24 + 1
                } else {
                    i6 = i13
                    list = list2
                    str6 = str17
                    str7 = str16
                    str8 = str18
                    str13 = str15
                }
            }
            i9++
            GetmdSpaceClass = list
            str10 = str6
            i8 = i5
            i10 = 1
            i4 = i6
            str12 = str7
            str11 = str8
        }
        return ((str13 + "{\r\n\"S\":\"步骤" + i11 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i11 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTrack2ExternalKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        i2: Int
    ): String {
        val str5: String
        val str6: String
        val str7: String
        var i3: Int
        var str8: String
        var str9: String?
        var str10: String
        var str11: String?
        var str12: String = "\"" + str2 + "\": [\r\n"
        if (i == 0) {
            str5 = "DC:1,%;SL:R,Y;SL:S,X"
            str6 = "DC:1,%;SL:L,Y;SL:S,X"
            str7 = "SL:S,X"
        } else {
            str5 = "DC:1,%;SL:R,Y;SL:T,X"
            str6 = "DC:1,%;SL:L,Y;SL:T,X"
            str7 = "SL:T,X"
        }
        val split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var str13: String = str6
        var str14: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
        var str15: String? = ":移到下一个齿位"
        var str16: String = ",\r\n\"Z\":0,\r\n\"V\":\""
        var str17: String = ":离开探测齿位"
        if ((str3 == "0")) {
            str12 =
                (str12 + "{\r\n\"S\":\"步骤1:移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":" + split.get(
                    0
                ) + ",\r\n\"Y\":200,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
            i3 = 3
            var i4: Int = 0
            while (i4 < split.size) {
                val i5: Int = i4 + 1
                val str18: String =
                    str12 + "{\r\n\"S\":\"步骤" + i3 + ":探测齿位" + i5 + "\",\r\n\"T\":1,\r\n\"X\":" + split.get(
                        i4
                    ) + ",\r\n\"Y\":-1000,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str5.replace(
                        "%",
                        i5.toString()
                    ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i6: Int = i3 + 1
                val sb: StringBuilder = StringBuilder()
                sb.append(str18)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i6)
                val str19: String = str17
                sb.append(str19)
                sb.append(i5)
                sb.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":")
                sb.append(Program.nConfigDecoderMove)
                val str20: String = str16
                sb.append(str20)
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"")
                sb.append("SL:R,Y")
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                i3 = i6 + 1
                if (i4 < split.size - 1) {
                    val sb3: StringBuilder = StringBuilder()
                    sb3.append(sb2)
                    sb3.append("{\r\n\"S\":\"步骤")
                    sb3.append(i3)
                    str11 = str15
                    sb3.append(str11)
                    sb3.append(i4 + 2)
                    sb3.append("\",\r\n\"T\":0,\r\n\"X\":")
                    sb3.append(split.get(i5))
                    val str21: String = str14
                    sb3.append(str21)
                    sb3.append(Program.strMoveXYZSpeed)
                    sb3.append("\",\r\n\"Rule\":\"")
                    sb3.append(str7)
                    sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    i3++
                    str12 = sb3.toString()
                    str10 = str21
                } else {
                    str10 = str14
                    str11 = str15
                    str12 = sb2
                }
                str14 = str10
                i4 = i5
                str17 = str19
                str15 = str11
                str16 = str20
            }
        } else {
            var str22: String = str17
            var str23: String = str14
            var str24: String? = str15
            var str25: String = str16
            if ((str3 == "1")) {
                str12 =
                    (str12 + "{\r\n\"S\":\"步骤1:Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":" + split.get(
                        0
                    ) + ",\r\n\"Y\":-200,\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                i3 = 3
                var i7: Int = 0
                while (i7 < split.size) {
                    val i8: Int = i7 + 1
                    val str26: String = str13
                    val str27: String =
                        str12 + "{\r\n\"S\":\"步骤" + i3 + ":探测齿位" + i8 + "\",\r\n\"T\":1,\r\n\"X\":" + split.get(
                            i7
                        ) + ",\r\n\"Y\":1000,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str26.replace(
                            "%",
                            i8.toString()
                        ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i9: Int = i3 + 1
                    val sb4: StringBuilder = StringBuilder()
                    sb4.append(str27)
                    sb4.append("{\r\n\"S\":\"步骤")
                    sb4.append(i9)
                    sb4.append(str22)
                    sb4.append(i8)
                    sb4.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-")
                    sb4.append(Program.nConfigDecoderMove)
                    val str28: String = str25
                    sb4.append(str28)
                    sb4.append(Program.strMoveXYZSpeed)
                    sb4.append("\",\r\n\"Rule\":\"")
                    sb4.append("SL:L,Y")
                    sb4.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                    str12 = sb4.toString()
                    i3 = i9 + 1
                    val str29: String = str22
                    if (i7 < split.size - 1) {
                        val sb5: StringBuilder = StringBuilder()
                        sb5.append(str12)
                        sb5.append("{\r\n\"S\":\"步骤")
                        sb5.append(i3)
                        str9 = str24
                        sb5.append(str9)
                        sb5.append(i7 + 2)
                        sb5.append("\",\r\n\"T\":0,\r\n\"X\":")
                        sb5.append(split.get(i8))
                        val str30: String = str23
                        sb5.append(str30)
                        sb5.append(Program.strMoveXYZSpeed)
                        sb5.append("\",\r\n\"Rule\":\"")
                        sb5.append(str7)
                        sb5.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                        i3++
                        str12 = sb5.toString()
                        str8 = str30
                    } else {
                        str8 = str23
                        str9 = str24
                    }
                    str23 = str8
                    i7 = i8
                    str24 = str9
                    str13 = str26
                    str25 = str28
                    str22 = str29
                }
            } else {
                i3 = 1
            }
        }
        return ((str12 + "{\r\n\"S\":\"步骤" + i3 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i3 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTrack4ExternalKeyPath2_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        i2: Int
    ): String {
        var str5: String
        var str6: String
        val str7: String
        var str8: String
        var str9: String
        var str10: String
        var str11: String?
        var str12: String?
        var str13: String
        var i3: Int = i2
        var str14: String = "\"" + str2 + "\": [\r\n"
        var split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        if (i == 0) {
            str5 = "DC:1,%;SL:R,Y;SL:S,X"
            str6 = "DC:2,%;SL:L,Y;SL:S,X"
            str7 = "SL:S,X"
        } else {
            str5 = "DC:1,%;SL:R,Y;SL:T,X"
            str6 = "DC:2,%;SL:L,Y;SL:T,X"
            str7 = "SL:T,X"
        }
        var i4: Int = 0
        var i5: Int = 1
        while (i4 < split.size) {
            val split2: Array<String> =
                split.get(i4).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            val strArr: Array<String> = split
            var str15: String = ":探测齿位"
            var str16: String = str5
            var str17: String = ":移到下一个齿位"
            var str18: String = ",\r\n\"Z\":0,\r\n\"V\":\""
            var str19: String = ":离开探测齿位"
            if (i4 == 0) {
                val i6: Int = i4
                val sb: StringBuilder = StringBuilder()
                sb.append(str14)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i5)
                sb.append(":移动到对应齿位的上方\",\r\n\"T\":0,\r\n\"X\":")
                var str20: String = "\",\r\n\"Des\":\"\"\r\n},\r\n"
                sb.append(split2.get(0))
                sb.append(",\r\n\"Y\":-200,\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"")
                sb.append(str7)
                sb.append(";SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i7: Int = i5 + 1
                i5 = i7 + 1
                var str21: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i7 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i3 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                var i8: Int = 0
                while (i8 < split2.size) {
                    val i9: Int = i8 + 1
                    val replace: String = str6.replace("%", i9.toString())
                    val sb2: StringBuilder = StringBuilder()
                    sb2.append(str21)
                    sb2.append("{\r\n\"S\":\"步骤")
                    sb2.append(i5)
                    sb2.append(":探测齿位")
                    sb2.append(i9)
                    sb2.append("\",\r\n\"T\":1,\r\n\"X\":")
                    sb2.append(split2.get(i8))
                    sb2.append(",\r\n\"Y\":500,\r\n\"Z\":0,\r\n\"V\":\"")
                    sb2.append(Program.strTouchXYZSpeed)
                    sb2.append("\",\r\n\"Rule\":\"")
                    sb2.append(replace)
                    val str22: String = str20
                    sb2.append(str22)
                    val sb3: String = sb2.toString()
                    i5++
                    val sb4: StringBuilder = StringBuilder()
                    sb4.append(sb3)
                    sb4.append("{\r\n\"S\":\"步骤")
                    sb4.append(i5)
                    val str23: String = str19
                    sb4.append(str23)
                    sb4.append(i9)
                    sb4.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-")
                    sb4.append(Program.nConfigDecoderMove)
                    val str24: String = str18
                    sb4.append(str24)
                    val str25: String = str6
                    sb4.append(Program.strMoveXYZSpeed)
                    sb4.append("\",\r\n\"Rule\":\"")
                    sb4.append("SL:L,Y")
                    sb4.append(str22)
                    var sb5: String = sb4.toString()
                    if (i8 < split2.size - 1) {
                        val sb6: StringBuilder = StringBuilder()
                        sb6.append(sb5)
                        sb6.append("{\r\n\"S\":\"步骤")
                        sb6.append(i5)
                        val str26: String = str17
                        sb6.append(str26)
                        sb6.append(i8 + 2)
                        sb6.append("\",\r\n\"T\":0,\r\n\"X\":")
                        sb6.append(split2.get(i9))
                        sb6.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                        sb6.append(Program.strMoveXYZSpeed)
                        sb6.append("\",\r\n\"Rule\":\"")
                        sb6.append(str7)
                        sb6.append(str22)
                        i5++
                        sb5 = sb6.toString()
                        str13 = str26
                    } else {
                        str13 = str17
                    }
                    str17 = str13
                    i8 = i9
                    str19 = str23
                    str20 = str22
                    str21 = sb5
                    str6 = str25
                    str18 = str24
                }
                str8 = str6
                str9 = str16
                str14 = str21
                i4 = i6
            } else {
                var str27: String = ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\""
                var str28: String? = "\",\r\n\"T\":0,\r\n\"X\":"
                var str29: String? = str17
                var str30: String = str18
                str8 = str6
                if (i4 == 1) {
                    val str31: String =
                        str14 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:Z,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i10: Int = i5 + 1
                    val str32: String =
                        str31 + "{\r\n\"S\":\"步骤" + i10 + ":Z轴向上回到钥匙上方\",\r\n\"T\":0,\r\n\"X\":" + split2.get(
                            0
                        ) + ",\r\n\"Y\":200,\r\n\"Z\":-1000,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str7 + ";SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                    val i11: Int = i10 + 1
                    i5 = i11 + 1
                    var str33: String =
                        str32 + "{\r\n\"S\":\"步骤" + i11 + ":Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i3 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n"
                    var i12: Int = 0
                    while (i12 < split2.size) {
                        val i13: Int = i12 + 1
                        val str34: String = str16
                        val str35: String =
                            str33 + "{\r\n\"S\":\"步骤" + i5 + str15 + i13 + "\",\r\n\"T\":1,\r\n\"X\":" + split2.get(
                                i12
                            ) + ",\r\n\"Y\":-500,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + str34.replace(
                                "%",
                                i13.toString()
                            ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                        val i14: Int = i5 + 1
                        val sb7: StringBuilder = StringBuilder()
                        sb7.append(str35)
                        sb7.append("{\r\n\"S\":\"步骤")
                        sb7.append(i14)
                        sb7.append(str19)
                        sb7.append(i13)
                        sb7.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":")
                        sb7.append(Program.nConfigDecoderMove)
                        val str36: String = str30
                        sb7.append(str36)
                        sb7.append(Program.strMoveXYZSpeed)
                        sb7.append("\",\r\n\"Rule\":\"")
                        sb7.append("SL:R,Y")
                        sb7.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                        var sb8: String = sb7.toString()
                        i5 = i14 + 1
                        val str37: String = str15
                        if (i12 < split2.size - 1) {
                            val sb9: StringBuilder = StringBuilder()
                            sb9.append(sb8)
                            sb9.append("{\r\n\"S\":\"步骤")
                            sb9.append(i5)
                            str12 = str29
                            sb9.append(str12)
                            sb9.append(i12 + 2)
                            str11 = str28
                            sb9.append(str11)
                            sb9.append(split2.get(i13))
                            val str38: String = str27
                            sb9.append(str38)
                            sb9.append(Program.strMoveXYZSpeed)
                            sb9.append("\",\r\n\"Rule\":\"")
                            sb9.append(str7)
                            sb9.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                            sb8 = sb9.toString()
                            i5++
                            str10 = str38
                        } else {
                            str10 = str27
                            str11 = str28
                            str12 = str29
                        }
                        str28 = str11
                        str27 = str10
                        str29 = str12
                        i12 = i13
                        str30 = str36
                        str16 = str34
                        str15 = str37
                        str33 = sb8
                    }
                    str9 = str16
                    str14 = str33
                    i4++
                    split = strArr
                    i3 = i2
                    str5 = str9
                    str6 = str8
                } else {
                    str9 = str16
                }
            }
            i4++
            split = strArr
            i3 = i2
            str5 = str9
            str6 = str8
        }
        return ((str14 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i5 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetChannelTrackKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String?,
        str4: String,
        str5: String,
        i2: Int
    ): String {
        val str6: String
        val str7: String
        val str8: String
        val i3: Int
        var i4: Int
        val i5: Int
        var str9: String = "\"" + str2 + "\": [\r\n"
        if (i == 0) {
            str6 = "DC:1,%;SL:L,Y;SL:S,X"
            str7 = "DC:2,%;SL:R,Y;SL:S,X"
            str8 = "SL:S,X"
        } else {
            str6 = "DC:1,%;SL:L,Y;SL:T,X"
            str7 = "DC:1,%;SL:R,Y;SL:T,X"
            str8 = "SL:T,X"
        }
        val split: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val str10: String = str6
        var str11: String? = "\",\r\n\"Rule\":\"U:X\",\r\n\"Des\":\"\"\r\n},\r\n"
        val str12: String = str7
        val str13: String = str8
        if ((str3 == "0") || (str3 == "5")) {
            var str14: String? = str11
            val split2: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split2.size > 0) {
                val parseInt: Int =
                    split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).toInt()
                val parseInt2: Int =
                    split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                        .get(split2.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().size - 1
                        ).toInt()
                i3 = if (parseInt > parseInt2) parseInt + 50 else parseInt2 + 50
            } else {
                i3 = 0
            }
            var str15: String =
                ((str9 + "{\r\n\"S\":\"步骤1:移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":" + i3 + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,X;SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":" + split.get(
                    split.size - 1
                )
                    .toInt() + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str13 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length: Int = split.size - 1
            i4 = 4
            var i6: Int = -1
            while (length > i6) {
                val i7: Int = length + 1
                val replace: String = str10.replace("%", i7.toString())
                val sb: StringBuilder = StringBuilder()
                sb.append(str15)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i4)
                sb.append(":回退一点,齿位")
                sb.append(i7)
                sb.append("\",\r\n\"T\":0,\r\n\"X\":-10,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                val str16: String? = str14
                sb.append(str16)
                val i8: Int = i4 + 1
                val str17: String =
                    sb.toString() + "{\r\n\"S\":\"步骤" + i8 + ":探测钥匙A面,齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i9: Int = i8 + 1
                val str18: String =
                    str17 + "{\r\n\"S\":\"步骤" + i9 + ":离开钥匙A面,齿位" + i7 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":20,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i10: Int = i9 + 1
                val str19: String =
                    str18 + "{\r\n\"S\":\"步骤" + i10 + ":探测钥匙B面,齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i11: Int = i10 + 1
                val str20: String =
                    str19 + "{\r\n\"S\":\"步骤" + i11 + ":离开B面移动到AB两边的中间,齿位" + i7 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,Y,Y-" + (i11 - 3).toString() + ",Y-" + (i11 - 1).toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i12: Int = i11 + 1
                val str21: String =
                    str20 + "{\r\n\"S\":\"步骤" + i12 + ":往" + i7 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":" + split.get(
                        length
                    ) + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str13 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i13: Int = i12 + 1
                val str22: String =
                    str21 + "{\r\n\"S\":\"步骤" + i13 + ":探测齿位" + i7 + "\",\r\n\"T\":1,\r\n\"X\":" + split.get(
                        length
                    ) + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i14: Int = i13 + 1
                val sb2: StringBuilder = StringBuilder()
                sb2.append(str22)
                sb2.append("{\r\n\"S\":\"步骤")
                sb2.append(i14)
                sb2.append(":离开探测齿位")
                sb2.append(i7)
                sb2.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":20,\r\n\"Z\":0,\r\n\"V\":\"")
                sb2.append(Program.strMoveXYZSpeed)
                sb2.append("\",\r\n\"Rule\":\"S:")
                sb2.append(i14 - 1)
                sb2.append(",Y\",\r\n\"Des\":\"\"\r\n},\r\n")
                str15 = sb2.toString()
                i4 = i14 + 1
                length--
                str14 = str16
                i6 = -1
            }
            str9 = str15
        } else if ((str3 == "1")) {
            val split3: Array<String> =
                (PublicMethodPort.trimend(str5, ";") + ";").split(";".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (split3.size > 0) {
                val parseInt3: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).toInt()
                val parseInt4: Int =
                    split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                        .get(split3.get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().size - 1
                        ).toInt()
                i5 = if (parseInt3 > parseInt4) parseInt3 + 100 else parseInt4 + 100
            } else {
                i5 = 0
            }
            var str23: String =
                ((str9 + "{\r\n\"S\":\"步骤1:移动到钥匙的上方\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-" + i5 + ",\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:T,X;SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤2:Z轴向下\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":" + i2 + ",\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤3:往钥匙柄移动\",\r\n\"T\":-1,\r\n\"X\":" + (split.get(
                    split.size - 1
                )
                    .toInt() + 10) + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed2 + "\",\r\n\"Rule\":\"" + str13 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            var length2: Int = split.size - 1
            i4 = 4
            var i15: Int = -1
            while (length2 > i15) {
                val i16: Int = length2 + 1
                val replace2: String = str12.replace("%", i16.toString())
                val sb3: StringBuilder = StringBuilder()
                sb3.append(str23)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i4)
                sb3.append(":回退一点,齿位")
                sb3.append(i16)
                sb3.append("\",\r\n\"T\":0,\r\n\"X\":-20,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                val str24: String? = str11
                sb3.append(str24)
                val i17: Int = i4 + 1
                val str25: String =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i17 + ":探测钥匙B面,齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:R,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i18: Int = i17 + 1
                val str26: String =
                    str25 + "{\r\n\"S\":\"步骤" + i18 + ":离开钥匙B面,齿位" + i16 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-20,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i19: Int = i18 + 1
                val str27: String =
                    str26 + "{\r\n\"S\":\"步骤" + i19 + ":探测钥匙A面,齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"SL:L,Y\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i20: Int = i19 + 1
                val str28: String =
                    str27 + "{\r\n\"S\":\"步骤" + i20 + ":离开A面移动到AB两边的中间,齿位" + i16 + "\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"C:2,Y,Y-" + (i20 - 1).toString()
                        .toString() + ",Y-" + (i20 - 3).toString()
                        .toString() + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i21: Int = i20 + 1
                val str29: String =
                    str28 + "{\r\n\"S\":\"步骤" + i21 + ":往" + i16 + "齿位方向移动\",\r\n\"T\":0,\r\n\"X\":" + split.get(
                        length2
                    ) + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"" + str13 + ";GO:U,5\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i22: Int = i21 + 1
                val str30: String =
                    str29 + "{\r\n\"S\":\"步骤" + i22 + ":探测齿位" + i16 + "\",\r\n\"T\":1,\r\n\"X\":" + split.get(
                        length2
                    ) + ",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strTouchXYZSpeed + "\",\r\n\"Rule\":\"" + replace2 + "\",\r\n\"Des\":\"\"\r\n},\r\n"
                val i23: Int = i22 + 1
                val sb4: StringBuilder = StringBuilder()
                sb4.append(str30)
                sb4.append("{\r\n\"S\":\"步骤")
                sb4.append(i23)
                sb4.append(":离开探测齿位")
                sb4.append(i16)
                sb4.append("\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":-20,\r\n\"Z\":0,\r\n\"V\":\"")
                sb4.append(Program.strMoveXYZSpeed)
                sb4.append("\",\r\n\"Rule\":\"S:")
                sb4.append(i23 - 1)
                sb4.append(",Y\",\r\n\"Des\":\"\"\r\n},\r\n")
                str23 = sb4.toString()
                i4 = i23 + 1
                length2--
                str11 = str24
                i15 = -1
            }
            str9 = str23
        } else {
            i4 = 1
        }
        return ((str9 + "{\r\n\"S\":\"步骤" + i4 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i4 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetDimpleKeyPath_Horizontal(
        i: Int,
        str: String,
        str2: String,
        str3: String,
        str4: String
    ): String {
        var parseInt: Int
        var str5: String = "\"" + str + "\": [\r\n"
        var str6: String = if (i == 0) "SL:S,X" else "SL:T,X"
        val split: Array<String> =
            PublicMethodPort.trimend(str3, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split3: Array<String> =
            PublicMethodPort.trimend(str4, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i2: Int = 1
        var i3: Int = 0
        var i4: Int = 1
        while (i3 < split2.size) {
            val split4: Array<String> =
                split2.get(i3).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            val split5: Array<String> =
                split3.get(i3).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            var i5: Int = 0
            while (i5 < split4.size) {
                if (split5.get(i5).toInt() < 0) {
                    parseInt = split4.get(i5).toInt() - (abs(split5.get(i5).toInt()) / 2)
                } else {
                    parseInt = split4.get(i5).toInt()
                }
                val abs: Int = if (split.get(i3).length < i2) 100 else abs(split.get(i3).toInt())
                val sb: StringBuilder = StringBuilder()
                sb.append(str5)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i4)
                sb.append(":移动到轴")
                val i6: Int = i3 + 1
                sb.append(i6)
                sb.append(",")
                i5++
                sb.append(i5)
                sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":")
                sb.append(parseInt)
                sb.append(",\r\n\"Y\":")
                sb.append(abs)
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,Y;")
                sb.append(str6)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i7: Int = i4 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i7)
                sb3.append(":探测轴")
                sb3.append(i6)
                sb3.append(",")
                sb3.append(i5)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":5000,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"U:Z;")
                val str7: String = str6
                sb3.append("DC:@,%".replace("@", i6.toString()).replace("%", i5.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i8: Int = i7 + 1
                str5 =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i8 + ":Z轴向上轴" + i6 + "," + i5 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-300,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i4 = i8 + 1
                str6 = str7
                i2 = 1
            }
            i3++
            i2 = 1
        }
        return ((str5 + "{\r\n\"S\":\"步骤" + i4 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i4 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetTubularKeyPath_Horizontal(str: String, str2: String): String {
        var str3: String = "\"" + str + "\": [\r\n"
        val split: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i: Int = 0
        var i2: Int = 1
        while (i < split.size) {
            val sb: StringBuilder = StringBuilder()
            sb.append(str3)
            sb.append("{\r\n\"S\":\"步骤")
            sb.append(i2)
            sb.append(":移动到")
            val i3: Int = i + 1
            sb.append(i3)
            sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":")
            sb.append(split.get(i))
            sb.append(",\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"")
            sb.append(Program.strMoveXYZSpeed)
            sb.append("\",\r\n\"Rule\":\"")
            sb.append("SL:TC,X")
            sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
            val i4: Int = i2 + 1
            val str4: String =
                sb.toString() + "{\r\n\"S\":\"步骤" + i4 + ":探测" + i3 + "齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":600,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z;" + "DC:1,%;".replace(
                    "%",
                    i3.toString()
                ) + "\",\r\n\"Des\":\"\"\r\n},\r\n"
            val i5: Int = i4 + 1
            i2 = i5 + 1
            i = i3
            str3 =
                str4 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上" + i3 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-100,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"SL:U,Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
        }
        return ((str3 + "{\r\n\"S\":\"步骤" + i2 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i2 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    private fun GetSingleAngleKey_Horizontal(
        i: Int,
        str: String,
        str2: String,
        i2: Int,
        str3: String
    ): String {
        var parseInt: Int
        var str4: String = "\"" + str + "\": [\r\n"
        var str5: String = if (i == 0) "SL:S,X" else "SL:T,X"
        var i3: Int = 1
        val split: Array<String> =
            PublicMethodPort.trimend(str2, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            PublicMethodPort.trimend(str3, ";").split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val strArr: Array<String> = arrayOf((i2 / 2).toString())
        var i4: Int = 0
        var i5: Int = 1
        while (i4 < split.size) {
            val split3: Array<String> =
                split.get(i4).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            val split4: Array<String> =
                split2.get(i4).toString().split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray()
            var i6: Int = 0
            while (i6 < split3.size) {
                if (split4.get(i6).toInt() < 0) {
                    parseInt = split3.get(i6).toInt() - (abs(split4.get(i6).toInt()) / 2)
                } else {
                    parseInt = split3.get(i6).toInt()
                }
                val abs: Int = if (strArr.get(i4).length < i3) 100 else abs(strArr.get(i4).toInt())
                val sb: StringBuilder = StringBuilder()
                sb.append(str4)
                sb.append("{\r\n\"S\":\"步骤")
                sb.append(i5)
                sb.append(":移动到轴")
                val i7: Int = i4 + 1
                sb.append(i7)
                sb.append(",")
                i6++
                sb.append(i6)
                sb.append("齿位上方\",\r\n\"T\":0,\r\n\"X\":")
                sb.append(parseInt)
                sb.append(",\r\n\"Y\":")
                sb.append(abs)
                sb.append(",\r\n\"Z\":0,\r\n\"V\":\"")
                sb.append(Program.strMoveXYZSpeed)
                sb.append("\",\r\n\"Rule\":\"SL:L,Y;")
                sb.append(str5)
                sb.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val sb2: String = sb.toString()
                val i8: Int = i5 + 1
                val sb3: StringBuilder = StringBuilder()
                sb3.append(sb2)
                sb3.append("{\r\n\"S\":\"步骤")
                sb3.append(i8)
                sb3.append(":探测轴")
                sb3.append(i7)
                sb3.append(",")
                sb3.append(i6)
                sb3.append("齿位\",\r\n\"T\":1,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":5000,\r\n\"V\":\"")
                sb3.append(Program.strMoveXYZSpeed)
                sb3.append("\",\r\n\"Rule\":\"U:Z;")
                val str6: String = str5
                sb3.append("DC:@,%".replace("@", i7.toString()).replace("%", i6.toString()))
                sb3.append("\",\r\n\"Des\":\"\"\r\n},\r\n")
                val i9: Int = i8 + 1
                str4 =
                    sb3.toString() + "{\r\n\"S\":\"步骤" + i9 + ":Z轴向上轴" + i7 + "," + i6 + "齿位\",\r\n\"T\":0,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":-300,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"U:Z;\",\r\n\"Des\":\"\"\r\n},\r\n"
                i5 = i9 + 1
                str5 = str6
                i3 = 1
            }
            i4++
            i3 = 1
        }
        return ((str4 + "{\r\n\"S\":\"步骤" + i5 + ":Z轴向上回到原点\",\r\n\"T\":998,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n},\r\n") + "{\r\n\"S\":\"步骤" + (i5 + 1) + ":X-Y-Z回到原点\",\r\n\"T\":999,\r\n\"X\":0,\r\n\"Y\":0,\r\n\"Z\":0,\r\n\"V\":\"" + Program.strMoveXYZSpeed + "\",\r\n\"Rule\":\"\",\r\n\"Des\":\"\"\r\n}\r\n") + " ]\r\n"
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.spl.key.mdKeyDecoderPathClass$2, reason: invalid class name */ /* loaded from: classes.dex */
    object AnonymousClass2 {
        /* synthetic */val `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`: IntArray

        init {
            val iArr: IntArray = IntArray(Key.KeyType.entries.size)
            `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType` = iArr
            try {
                iArr[Key.KeyType.StandardDouble.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.StandardSingle.ordinal] =
                    2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.Track4Internal.ordinal] =
                    3
            } catch (unused3: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.Track2External.ordinal] =
                    4
            } catch (unused4: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.Track4External.ordinal] =
                    5
            } catch (unused5: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.ChannelTrack.ordinal] =
                    6
            } catch (unused6: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.Dimple.ordinal] =
                    7
            } catch (unused7: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.Tubular.ordinal] =
                    8
            } catch (unused8: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[Key.KeyType.SingleAngle.ordinal] =
                    9
            } catch (unused9: NoSuchFieldError) {
            }
        }
    }
}
