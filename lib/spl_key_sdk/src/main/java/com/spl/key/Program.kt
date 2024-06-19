package com.spl.key

import com.spl.key.Key.enumMachineType

/* loaded from: classes.dex */
object Program {
    var _JawS1C_LocationLineToShoulderlength: Int = 150
    var _JawS1D_LocationLineToShoulderlength: Int = 150
    var _JawS2A_GrooveDepth: Int = 0
    var _JawS2A_TipToShoulderlength: Int = 0
    var _JawS2A_Tiplength: Int = 550
    var _JawS2B_GrooveDepth: Int = 0
    var _JawS2B_TipToShoulderlength: Int = 0
    var _JawS2B_Tiplength: Int = 550
    var _JawS4_LocationLineToTip: Int = 340
    var _JawS6_TipGrooveDepth: Int = 150
    var _KeyShoulderLocation_JawS1A: Int = 0
    var _KeyShoulderLocation_JawS1B: Int = 0
    var _KeyShoulderLocation_JawS1B_2: Int = 0
    var _KeyShoulderLocation_JawS1C: Int = 0
    var _KeyShoulderLocation_JawS1C_DownAhoulderlength: Int = 0
    var _KeyShoulderLocation_JawS1C_UpAhoulderlength: Int = 0
    var _KeyShoulderLocation_JawS1D: Int = 0
    var _KeyShoulderLocation_JawS1D_DownAhoulderlength: Int = 0
    var _KeyShoulderLocation_JawS1D_UpAhoulderlength: Int = 0
    var _Speed_CuttingDimpleIn: String = "600,600,80"
    var _Speed_CuttingDimpleRoundIn: String = "100,100,100"
    var _Speed_CuttingIn: String = "600,600,400"
    var _Speed_CuttingOut: String = "600,600,400"
    var _Speed_CuttingSharpen: String = "800,800,800"
    var _Speed_CuttingTurn: String = "500,500,400"
    var _Speed_Origin: String = "12000,12000,4000"
    var _Speed_SpindleTurnOff_Move: String = "10000,10000,3000"
    var _Speed_SpindleTurnOff_ZDown: String = "3000,3000,2000"
    var _Speed_SpindleTurnOff_ZUp: String = "3000,3000,2000"
    var _Speed_SpindleTurnOn_Move: String = "3000,3000,3000"
    var _Speed_SpindleTurnOn_ZDown: String = "500,500,500"
    var _Speed_SpindleTurnOn_ZUp: String = "1000,1000,1000"
    var __JawStepDC: Array<Array<String>>? = null
    var __PJawStepS1A: Array<Array<String>>? = null
    var __PJawStepS1B: Array<Array<String>>? = null
    var __PJawStepS1C: Array<Array<String>>? = null
    var __PJawStepS1D: Array<Array<String>>? = null
    var __PJawStepS2A: Array<Array<String>>? = null
    var __PJawStepS2B: Array<Array<String>>? = null
    var __PJawStepS3: Array<Array<String>>? = null
    var __PJawStepS4: Array<Array<String>>? = null
    var __PJawStepS5: Array<Array<String>>? = null
    var __PJawStepS6: Array<Array<String>>? = null
    var __PJawStepS7: Array<Array<String>>? = null
    var __PJawStepS8: Array<Array<String>>? = null
    var _nXMaxRoute: Int = 0
    var _nXMaxRoute_Alpha: Int = 6850
    var _nXMaxRoute_Beta: Int = 6800
    var _nXMaxRoute_E9Pro: Int = 11500
    var _nYMaxRoute: Int = 0
    var _nYMaxRoute_Alpha: Int = 4710
    var _nYMaxRoute_Beta: Int = 5800
    var _nYMaxRoute_E9Pro: Int = 2200
    var _nZMaxRoute: Int = 0
    var _nZMaxRoute_Alpha: Int = 2400
    var _nZMaxRoute_Beta: Int = 2400
    var _nZMaxRoute_E9Pro: Int = 4500
    var dXScale: Double = 0.46875
    var dXScale_Alpha: Double = 0.25
    var dXScale_Beta: Double = 0.46875
    var dXScale_E9Pro: Double = 0.5
    var dYScale: Double = 0.46875
    var dYScale_Alpha: Double = 0.25
    var dYScale_Beta: Double = 0.46875
    var dYScale_E9Pro: Double = 0.5
    var dZScale: Double = 0.25
    var dZScale_Alpha: Double = 0.25
    var dZScale_Beta: Double = 0.25
    var dZScale_E9Pro: Double = 0.25
    var nConfigDecoderMove: Int = 50
    var nConfigDecoderMove_TrackInternal: Int = 15
    var strMoveXYZSpeed: String = "7000,7000,3000"
    var strMoveXYZSpeed2: String = "5000,5000,3000"
    var strTouchXYZSpeed: String = "1200,4000,3000"

    fun GetXYZScaleMaxRoute(enummachinetype: enumMachineType, i: Int) {
        val i2: Int =
            AnonymousClass1.`$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`.get(
                enummachinetype.ordinal
            )
        if (i2 == 1) {
            dXScale = dXScale_Beta
            dYScale = dYScale_Beta
            dZScale = dZScale_Beta
            _nXMaxRoute = PublicMethodPort.GetXYZPointRecalculate(1, _nXMaxRoute_Beta)
            _nYMaxRoute = PublicMethodPort.GetXYZPointRecalculate(2, _nYMaxRoute_Beta)
            _nZMaxRoute = PublicMethodPort.GetXYZPointRecalculate(3, _nZMaxRoute_Beta)
        } else if (i2 == 2) {
            dXScale = dXScale_Alpha
            dYScale = dYScale_Alpha
            dZScale = dZScale_Alpha
            _nXMaxRoute = PublicMethodPort.GetXYZPointRecalculate(1, _nXMaxRoute_Alpha)
            _nYMaxRoute = PublicMethodPort.GetXYZPointRecalculate(2, _nYMaxRoute_Alpha)
            _nZMaxRoute = PublicMethodPort.GetXYZPointRecalculate(3, _nZMaxRoute_Alpha)
        } else if (i2 == 3) {
            dXScale = dXScale_E9Pro
            dYScale = dYScale_E9Pro
            dZScale = dZScale_E9Pro
            _nXMaxRoute = PublicMethodPort.GetXYZPointRecalculate(1, _nXMaxRoute_E9Pro)
            _nYMaxRoute = PublicMethodPort.GetXYZPointRecalculate(2, _nYMaxRoute_E9Pro)
            _nZMaxRoute = PublicMethodPort.GetXYZPointRecalculate(3, _nZMaxRoute_E9Pro)
        }
        if (i != -1) {
            SetXYZSpeed(enummachinetype, i)
        }
    }

    fun GetRegistrationCode(str: String): String {
        val replace: String =
            str.replace("I", "").replace("1", "").replace("O", "").replace("0", "")
        return if (replace.length <= 8) replace else replace.substring(replace.length - 8, 8)
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */ /* JADX WARN: Removed duplicated region for block: B:11:0x0056  */ /* JADX WARN: Removed duplicated region for block: B:12:0x005a  */ /* JADX WARN: Removed duplicated region for block: B:13:0x005e  */ /* JADX WARN: Removed duplicated region for block: B:14:0x0062  */ /* JADX WARN: Removed duplicated region for block: B:6:0x003f  */ /* JADX WARN: Removed duplicated region for block: B:7:0x0043  */ /* JADX WARN: Removed duplicated region for block: B:8:0x004a  */ /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */ /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    fun SetXYZSpeed(r22: enumMachineType?, r23: Int) {
        /*
            Method dump skipped, instructions count: 688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw UnsupportedOperationException("Method not decompiled: com.spl.key.Program.SetXYZSpeed(com.spl.key.Key\$enumMachineType, int):void")
    }

    fun CheckXYZSpped(enummachinetype: enumMachineType, i: Int, i2: Int): Int {
        if (i == 1) {
            val i3: Int =
                AnonymousClass1.`$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`.get(
                    enummachinetype.ordinal
                )
        } else if (i == 2) {
            val i4: Int =
                AnonymousClass1.`$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`.get(
                    enummachinetype.ordinal
                )
        } else if (i == 3) {
            val i5: Int =
                AnonymousClass1.`$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`.get(
                    enummachinetype.ordinal
                )
        }
        return i2
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.spl.key.Program$1, reason: invalid class name */ /* loaded from: classes.dex */
    object AnonymousClass1 {
        /* synthetic */val `$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`: IntArray

        init {
            val iArr = IntArray(enumMachineType.entries.size)
            `$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType` = iArr
            try {
                iArr[enumMachineType.beta.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`[enumMachineType.alpha.ordinal] =
                    2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$example$spl_key_sdklibrary$Key$enumMachineType`[enumMachineType.e9_Pro.ordinal] =
                    3
            } catch (unused3: NoSuchFieldError) {
            }
        }
    }
}
