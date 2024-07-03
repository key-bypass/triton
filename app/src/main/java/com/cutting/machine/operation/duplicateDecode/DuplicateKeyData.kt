package com.cutting.machine.operation.duplicateDecode

import com.cutting.machine.bean.KeyAlign
import com.cutting.machine.bean.KeyType
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.communication.KeyInfoBase
import com.cutting.machine.duplicate.DecodeData

/* loaded from: classes2.dex */
class DuplicateKeyData: KeyInfoBase {


    override var extDoublekeyDepthSteps: Int = 0
    override var keyAlign: KeyAlign? = null
    override lateinit var keyType: KeyType
    override var keyWidthSteps: Int = 0
    override var lengthSteps: Int = 0
    override var shoulderBlock: Int = 0
    override var thickSteps: Int = 0
    override var isNewHonda: Boolean = false

    var align: KeyAlign? = null

    var clamp: Clamp? = null

    var clampMode = 0

    var cutDepth = 0

    var decodeData: DecodeData? = null

    var decodeLength = 0

    var decodeWidth = 0

    override var side = 0

    var type: KeyType? = null

    val length : Int
    get() = this.lengthSteps
}
