package com.spl.key

/* loaded from: classes.dex */
object Key {
    fun GetKeyType(i: Int): KeyType? {
        if (i != 92) {
            return when (i) {
                0 -> KeyType.StandardDouble
                1 -> KeyType.StandardSingle
                2 -> KeyType.Track4Internal
                3 -> KeyType.Track2External
                4 -> KeyType.Track4External
                5 -> KeyType.ChannelTrack
                6 -> KeyType.Dimple
                7 -> KeyType.Tibbe
                8 -> KeyType.Tubular
                9 -> KeyType.Ava
                else -> null
            }
        }
        return KeyType.SingleAngle
    }

    fun GetKeySide(keyType: KeyType?, str: String): String {
        val str2: String
        val split =
            PublicMethodPort.trimend(str, ";").split(";".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        var i = 0
        while (true) {
            if (i >= split.size) {
                str2 = ""
                break
            }
            val split2 = split[i].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split2[0].equals(SpecificParamUtils.SIDE, ignoreCase = true)) {
                str2 = split2[1]
                break
            }
            i++
        }
        if (str2.length >= 1) {
            return str2
        }
        return when (AnonymousClass1.`$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[keyType!!.ordinal]) {
            1, 5 -> "2"
            2, 4, 6 -> "0"
            3 -> "3"
            else -> str2
        }
    }

    fun GetCut_Depth(keyType: KeyType?, str: String): Int {
        val i =
            if ((keyType == KeyType.Track4Internal || keyType == KeyType.Track2External || keyType == KeyType.Track4External || keyType == KeyType.ChannelTrack)) 100 else 0
        for (str2 in PublicMethodPort.trimend(str, ";").split(";".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()) {
            val split = str2.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split[0].equals(SpecificParamUtils.CUT_DEPTH, ignoreCase = true)) {
                return split[1].toInt()
            }
        }
        return i
    }

    fun GetVariableSpace(str: String): String? {
        for (str2 in PublicMethodPort.trimend(str, ";").split(";".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()) {
            val split = str2.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split[0].equals(SpecificParamUtils.VARIABLE_SPACE, ignoreCase = true)) {
                return split[1]
            }
        }
        return null
    }

    fun GetKeyLength(keyType: KeyType?, str: String): Int {
        for (str2 in PublicMethodPort.trimend(str, ";").split(";".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()) {
            val split = str2.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split[0].equals("keylength", ignoreCase = true)) {
                return split[1].toInt()
            }
        }
        return 0
    }

    /* loaded from: classes.dex */
    enum class KeyType {
        StandardDouble,
        StandardSingle,
        Track4Internal,
        Track2External,
        Track4External,
        ChannelTrack,
        Dimple,
        Tibbe,
        Tubular,
        Ava,
        SingleAngle
    }

    /* loaded from: classes.dex */
    enum class enumMachineType {
        beta,
        alpha,
        e9_Pro
    }

    /* renamed from: com.spl.key.Key$1, reason: invalid class name */ /* loaded from: classes.dex */
    internal object AnonymousClass1 {
        /* synthetic */val `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`: IntArray

        init {
            val iArr = IntArray(KeyType.entries.size)
            `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType` = iArr
            try {
                iArr[KeyType.StandardDouble.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[KeyType.StandardSingle.ordinal] =
                    2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[KeyType.Track4Internal.ordinal] =
                    3
            } catch (unused3: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[KeyType.Track2External.ordinal] =
                    4
            } catch (unused4: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[KeyType.Track4External.ordinal] =
                    5
            } catch (unused5: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$KeyType`[KeyType.ChannelTrack.ordinal] =
                    6
            } catch (unused6: NoSuchFieldError) {
            }
        }
    }
}
