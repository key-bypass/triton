package com.cutting.machine.communication

import com.cutting.machine.bean.KeyAlign
import com.cutting.machine.bean.KeyType

/* loaded from: classes2.dex */
interface KeyInfoBase {
    val extDoublekeyDepthSteps: Int

    val keyAlign: KeyAlign?

    val keyType: KeyType?

    val keyWidthSteps: Int

    val lengthSteps: Int

    val shoulderBlock: Int

    val side: Int

    val thickSteps: Int

    val isNewHonda: Boolean
}
