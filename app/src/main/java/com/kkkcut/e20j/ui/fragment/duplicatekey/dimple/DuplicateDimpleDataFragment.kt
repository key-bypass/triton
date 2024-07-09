package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.cutting.machine.Command
import com.cutting.machine.KeyAlignInfo
import com.cutting.machine.KeyBlankCutStepsGenerateUtil
import com.cutting.machine.OperateType
import com.cutting.machine.StepsGenerateUtil
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo
import com.cutting.machine.bean.StepBean
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.clamp.ClampUtil
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.error.ErrorCode
import com.cutting.machine.operation.cut.DataParam
import com.cutting.machine.utils.AssetsJsonUtils
import com.jakewharton.rxbinding4.view.clicks
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.LogUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog
import com.kkkcut.e20j.ui.dialog.CutDialog
import com.kkkcut.e20j.ui.dialog.DecodeDialog
import com.kkkcut.e20j.ui.dialog.DimpleSpaceSelectDialog
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator.getClearClampImg
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.CutCountHelper
import org.greenrobot.eventbus.EventBus
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.max

/* loaded from: classes.dex */
class DuplicateDimpleDataFragment : BaseBackFragment() {
    private var aligned: Boolean = false

    var btDecode: Button? = null

    var btFindSpace: Button? = null

    var btLocation: Button? = null

    var btSave: Button? = null
    private var dimpleCutIndex: Int = 0

    var gridLayout: GridLayout? = null

    var ivXAdd: ImageView? = null

    var ivXReduce: ImageView? = null

    var ivYAdd: ImageView? = null

    var ivYReduce: ImageView? = null

    var ivZAdd: ImageView? = null

    var ivZReduce: ImageView? = null
    private var keyInfo: KeyInfo? = null
    private var lastRb: RadioButton? = null

    var rbRow: RadioButton? = null

    var rbStepX: RadioButton? = null

    var rbStepY: RadioButton? = null

    var rbStepZ: RadioButton? = null

    var rgRowIndex: RadioGroup? = null
    private var rowIndex: Int = 0
    private var rowList: MutableList<Int>? = null
    private var spaceList: MutableList<MutableList<Int>>? = null

    var tvSleep: TextView? = null

    var tvSpace: TextView? = null
    private var rbList: MutableList<RadioButton> = ArrayList()
    private var x: Int = 1
    private var y: Int = 1
    private var z: Int = 1500
    private val sleep: Int = 3
    private val dataParam: DataParam = DataParam()

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_duplicate_dimple_data
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var i: Int
        var i2: Int
        this.keyInfo =
            if (arguments != null) arguments!!.getParcelable<Parcelable>(KEY) as KeyInfo? else null
        val i3: Int = arguments!!.getInt(ROW_COUNT)
        if (this.keyInfo != null) {
            val sb: StringBuilder = StringBuilder()
            val sb2: StringBuilder = StringBuilder()
            val i4: Int =
                if (ClampUtil.getClampMode(this.keyInfo) == 0) ErrorCode.keyDecodeFailed else 1000
            for (i5 in 0 until i3) {
                if (arguments!!.getBoolean(ZIMUZHU)) {
                    for (i6 in 0..1) {
                        var i7: Int = 0
                        var i8: Int = i4
                        while (i8 > 0) {
                            if (i8 == 50) {
                                sb.append(i8)
                                sb.append(";")
                                i2 = i7 + 1
                                sb2.append(i7)
                                sb2.append(";")
                            } else {
                                sb.append(i8)
                                sb.append(",")
                                i2 = i7 + 1
                                sb2.append(i7)
                                sb2.append(",")
                            }
                            i7 = i2
                            i8 -= 50
                        }
                    }
                } else {
                    var i9: Int = 0
                    var i10: Int = i4
                    while (i10 > 0) {
                        if (i10 == 50) {
                            sb.append(i10)
                            sb.append(";")
                            i = i9 + 1
                            sb2.append(i9)
                            sb2.append(";")
                        } else {
                            sb.append(i10)
                            sb.append(",")
                            i = i9 + 1
                            sb2.append(i9)
                            sb2.append(",")
                        }
                        i9 = i
                        i10 -= 50
                    }
                }
            }
            keyInfo!!.depthStr = sb.toString()
            keyInfo!!.depthName = sb2.toString()
        }
        val i11: Int = arguments!!.getInt(SPACE_COUNT)
        val spaceList: MutableList<MutableList<Int>>? =
            keyInfo!!.getSpaceList()
        this.spaceList = spaceList
        if (spaceList == null || spaceList.size == 0) {
            this.spaceList = ArrayList<ArrayList<Int>>() as MutableList<MutableList<Int>>
            for (i12 in 0 until i3) {
                val arrayList = ArrayList<Int>()
                for (i13 in 0 until i11) {
                    arrayList.add(0)
                }
                this.spaceList!!.add(arrayList)
            }
        }
        for (i14 in 0 until i3) {
            val childAt: View? = rgRowIndex!!.getChildAt(i14)
            if (childAt != null) {
                childAt.visibility = 0
            }
        }
        val row_pos: String = keyInfo!!.row_pos
        this.rowList = listOf<Int>().toMutableList()
        if (!TextUtils.isEmpty(row_pos)) {
            for (str: String? in row_pos.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()) {
                rowList!!.add(str!!.toInt())
            }
        } else {
            for (i15 in 0 until arguments!!.getInt(ROW_COUNT)) {
                rowList!!.add(0)
            }
        }
        rbList.add(this.rbStepX!!)
        rbStepX!!.setOnClickListener(CustomOnClickListener())
        rbList.add(this.rbStepY!!)
        rbStepY!!.setOnClickListener(CustomOnClickListener())
        rbList.add(this.rbStepZ!!)
        rbStepZ!!.setOnClickListener(CustomOnClickListener())
        rbList.add(this.rbRow!!)
        rbRow!!.setOnClickListener(CustomOnClickListener())
        changeRow(0)
        addDisposable(
            ivXAdd!!.clicks().throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe({ obj: Unit ->
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentX: Int = OperationManager.getInstance().getCurrentX()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.x =
                    currentX + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepX)
                this@DuplicateDimpleDataFragment.y =
                    OperationManager.getInstance().getCurrentY()
                this@DuplicateDimpleDataFragment.z =
                    OperationManager.getInstance().getCurrentZ()
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            ivYAdd!!.clicks().throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe({ obj: Unit ->  // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.2
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentY: Int = OperationManager.getInstance().getCurrentY()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.y =
                    currentY + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepY)
                this@DuplicateDimpleDataFragment.x =
                    OperationManager.getInstance().getCurrentX()
                this@DuplicateDimpleDataFragment.z =
                    OperationManager.getInstance().getCurrentZ()
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            ivZAdd!!.clicks().throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe({ obj: Unit ->  // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.3
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                this@DuplicateDimpleDataFragment.x =
                    OperationManager.getInstance().getCurrentX()
                this@DuplicateDimpleDataFragment.y =
                    OperationManager.getInstance().getCurrentY()
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentZ: Int = OperationManager.getInstance().getCurrentZ()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.z =
                    currentZ + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepZ)
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            ivXReduce!!.clicks().throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe( { obj: Unit ->  // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.4
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentX: Int = OperationManager.getInstance().getCurrentX()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.x =
                    currentX - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepX)
                this@DuplicateDimpleDataFragment.y =
                    OperationManager.getInstance().getCurrentY()
                this@DuplicateDimpleDataFragment.z =
                    OperationManager.getInstance().getCurrentZ()
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            ivYReduce!!.clicks().throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe({    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.5
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentY: Int = OperationManager.getInstance().getCurrentY()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.y =
                    currentY - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepY)
                this@DuplicateDimpleDataFragment.x =
                    OperationManager.getInstance().getCurrentX()
                this@DuplicateDimpleDataFragment.z =
                    OperationManager.getInstance().getCurrentZ()
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            ivZReduce!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.6
                if (!this@DuplicateDimpleDataFragment.aligned) {
                    ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
                    return@subscribe
                }
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                this@DuplicateDimpleDataFragment.x =
                    OperationManager.getInstance().getCurrentX()
                this@DuplicateDimpleDataFragment.y =
                    OperationManager.getInstance().getCurrentY()
                val duplicateDimpleDataFragment2: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                val currentZ: Int = OperationManager.getInstance().getCurrentZ()
                val duplicateDimpleDataFragment3: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment2.z =
                    currentZ - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepZ)
                OperationManager.getInstance().move(
                    0,
                    this@DuplicateDimpleDataFragment.x,
                    this@DuplicateDimpleDataFragment.y,
                    this@DuplicateDimpleDataFragment.z
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            btSave!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.7
                if (this@DuplicateDimpleDataFragment.aligned) {
                    if (!this@DuplicateDimpleDataFragment.dataNotComplete()) {
                        this@DuplicateDimpleDataFragment.showEditDialog()
                        return@subscribe
                    } else {
                        ToastUtil.showToast(R.string.please_complete_the_data)
                        return@subscribe
                    }
                }
                ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            btDecode!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.8
                if (!this@DuplicateDimpleDataFragment.dataNotComplete()) {
                    if (ClampManager.getInstance()
                            .checkHasCalibrated(this@DuplicateDimpleDataFragment.keyInfo)
                    ) {
                        DecodeDialog(
                            this@DuplicateDimpleDataFragment.activity,
                            this@DuplicateDimpleDataFragment.dataParam,
                            true
                        ).show()
                        return@subscribe
                    }
                    return@subscribe
                }
                ToastUtil.showToast(R.string.please_complete_the_data)
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            btLocation!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.9
                val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                    this@DuplicateDimpleDataFragment
                duplicateDimpleDataFragment.showLoadingDialog(
                    duplicateDimpleDataFragment.getString(
                        R.string.waitting
                    ), true
                )
                OperationManager.getInstance().start(
                    this@DuplicateDimpleDataFragment.dataParam, AssetsJsonUtils.getCommonSteps(
                        this@DuplicateDimpleDataFragment.context,
                        AssetsJsonUtils.getKeyDecodeLocationJsonPath(
                            this@DuplicateDimpleDataFragment.keyInfo
                        )
                    ), OperateType.DIMPLE_DUPLICATE_LOCATION
                )
            }, { dismissLoadingDialog() })
        )
        addDisposable(
            btFindSpace!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({
                val indexOf: Int = 0
                if (this@DuplicateDimpleDataFragment.aligned) {
                    val size: Int =
                        (this@DuplicateDimpleDataFragment.spaceList!!.get(this@DuplicateDimpleDataFragment.rowIndex)).size
                    var i16: Int = 0
                    if (this@DuplicateDimpleDataFragment.lastRb != null && rbList.indexOf(this@DuplicateDimpleDataFragment.lastRb) - 4 >= 0) {
                        i16 = indexOf
                    }
                    val dimpleSpaceSelectDialog: DimpleSpaceSelectDialog =
                        DimpleSpaceSelectDialog(
                            this@DuplicateDimpleDataFragment.context,
                            size,
                            i16
                        )
                    dimpleSpaceSelectDialog.show()
                    dimpleSpaceSelectDialog.setOnConfirm { i17 ->
                        rbList[(rbList.size - size) + i17]
                            .performClick()
                        this@DuplicateDimpleDataFragment.showLoadingDialog(
                            this@DuplicateDimpleDataFragment.getString(
                                R.string.waitting
                            ), true
                        )
                        val clampFace: Int =
                            OperationManager.getInstance().keyAlignInfo
                                .clampFace + ClampManager.getInstance().getS1B()
                                .high1
                        val arrayList2 = ArrayList<StepBean>()
                        arrayList2.add(
                            StepBean(
                                0,
                                this@DuplicateDimpleDataFragment.x,
                                this@DuplicateDimpleDataFragment.y,
                                this@DuplicateDimpleDataFragment.z,
                                "",
                                "C:5,X;C:5,Y;C:5,Z",
                                false
                            )
                        )
                        arrayList2.add(
                            StepBean(
                                1,
                                0,
                                0,
                                clampFace,
                                "",
                                "C:5,Z;DCDS:1," + (i17 + 1),
                                false
                            )
                        )
                        arrayList2.add(StepBean(0, 0, 0, -400, "", "U:Z;"))
                        dataParam.pauseTime = this@DuplicateDimpleDataFragment.sleep
                        OperationManager.getInstance().start(
                            this@DuplicateDimpleDataFragment.dataParam,
                            arrayList2,
                            OperateType.DUPLICATE_DIMPLE_DECODE_SINGLE
                        )

                    }
                }
                ToastUtil.showToast(this@DuplicateDimpleDataFragment.getString(R.string.please_locate_first))
            }, { dismissLoadingDialog() })
        )
        tvSleep!!.text = sleep.toString()
        initParam()
    }

    private fun initParam() {
        dataParam.setKeyInfo(this.keyInfo)
        dataParam.decoderSize = 100
        dataParam.clamp = ClampUtil.getClamp(this.keyInfo)
        dataParam.clampMode = ClampUtil.getClampMode(this.keyInfo)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showEditDialog() {
        val editDialog: EditDialog = EditDialog(context)
        editDialog.setTip(getString(R.string.enter_remarks))
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment$$ExternalSyntheticLambda0
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                this@DuplicateDimpleDataFragment.m51x8c6c2cc7(str)
            }
        })
        editDialog.setContentNullable(true)
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: collectKey, reason: merged with bridge method [inline-methods] */
    fun m51x8c6c2cc7(str: String?) {
        keyInfo!!.width = OperationManager.getInstance().keyAlignInfo.decodeWidth
        val sb: StringBuilder = StringBuilder()
        val sb2: StringBuilder = StringBuilder()
        val sb3: StringBuilder = StringBuilder()
        val sb4: StringBuilder = StringBuilder()
        val i: Int = if (arguments != null) arguments!!.getInt(ROW_COUNT) else 1
        for (i2 in 0 until i) {
            val list: List<Int> = spaceList!!.get(i2)
            if (i2 == 0) {
                sb.append(list!!.size)
            } else {
                sb.append("-")
                sb.append(list!!.size)
            }
            if (arguments!!.getBoolean(ZIMUZHU)) {
                for (i3 in 0..1) {
                    for (i4 in list.indices) {
                        sb2.append(list.get(i4))
                        if (i3 == 0) {
                            sb3.append("-200")
                        } else {
                            sb3.append("0")
                        }
                        if (i4 != list.size - 1) {
                            sb2.append(",")
                            sb3.append(",")
                        } else {
                            sb2.append(";")
                            sb3.append(";")
                        }
                    }
                    sb4.append(rowList!!.get(i2))
                    sb4.append(";")
                }
            } else {
                for (i5 in list.indices) {
                    sb2.append(list.get(i5))
                    sb3.append("0")
                    if (i5 != list.size - 1) {
                        sb2.append(",")
                        sb3.append(",")
                    } else {
                        sb2.append(";")
                        sb3.append(";")
                    }
                }
                sb4.append(rowList!!.get(i2))
                sb4.append(";")
            }
        }
        keyInfo!!.cuts = sb.toString()
        keyInfo!!.spaceStr = sb2.toString()
        keyInfo!!.spaceWidthStr = sb3.toString()
        keyInfo!!.row_pos = sb4.toString()
        keyInfo!!.type_specific_info = ""
        val duplicateDimple: DuplicateDimple = DuplicateDimple()
        duplicateDimple.type = keyInfo!!.type
        duplicateDimple.align = keyInfo!!.align
        duplicateDimple.width = keyInfo!!.width
        duplicateDimple.thick = keyInfo!!.thick
        duplicateDimple.row_pos = keyInfo!!.row_pos
        duplicateDimple.row_count = if (arguments != null) arguments!!.getInt(ROW_COUNT) else 1
        duplicateDimple.space = keyInfo!!.spaceStr
        duplicateDimple.space_width = keyInfo!!.spaceWidthStr
        duplicateDimple.depth = keyInfo!!.depthStr
        duplicateDimple.depth_name = keyInfo!!.depthName
        duplicateDimple.parameter_info = ""
        duplicateDimple.keyname = str
        val clampBean: ClampBean = keyInfo!!.clampBean
        duplicateDimple.clampNum = clampBean.clampNum
        duplicateDimple.clampSide = clampBean.clampSide
        duplicateDimple.clampSlot = clampBean.clampSlot
        duplicateDimple.time = Date().time
        UserDataDaoManager.getInstance(context).saveDuplicateDimpleKey(duplicateDimple)
        ToastUtil.showToast(R.string.saved_successfully)
        EventBus.getDefault().post(EventCenter<Any?>(46))
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun dataNotComplete(): Boolean {
        if (TextUtils.isEmpty(rbRow!!.getText().toString().trim { it <= ' ' })) {
            rbFocus(this.rbRow)
            return true
        }
        for (row in spaceList!!) {
            for (item in row) {
                return true
            }
        }
        return false
    }

    fun rbFocus(radioButton: RadioButton?) {
        lastRb!!.setChecked(false)
        radioButton!!.setChecked(true)
        this.lastRb = radioButton
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0169  */ /* JADX WARN: Removed duplicated region for block: B:56:0x017d  */ /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    fun onClick(r6: View?) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.onClick(android.view.View):void")
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        if (z) {
            when (compoundButton.id) {
                R.id.rb_row_1 -> {
                    changeRow(0)
                    return
                }

                R.id.rb_row_2 -> {
                    changeRow(1)
                    return
                }

                R.id.rb_row_3 -> {
                    changeRow(2)
                    return
                }

                R.id.rb_row_4 -> {
                    changeRow(3)
                    return
                }

                else -> return
            }
        }
    }

    private fun changeRow(i: Int) {
        this.rowIndex = i
        var i2: Int = if (arguments != null) arguments!!.getInt(SPACE_COUNT) else 5
        val list2: List<Int>? = if (spaceList != null) spaceList!![i] else null
        if (list2 != null && list2.size > 0) {
            i2 = list2.size
        }
        tvSpace!!.text = i2.toString()
        gridLayout!!.removeAllViews()
        for (i3 in 0 until i2) {
            getLayoutInflater().inflate(R.layout.item_duplicate_dimple_space_input, this.gridLayout)
        }
        for (i4 in 0 until gridLayout!!.childCount) {
            val radioButton: RadioButton = gridLayout!!.getChildAt(i4) as RadioButton
            if (!list2.isNullOrEmpty() && (i4 < i2) && (list2[i4] > 0)) {
                radioButton.text = list2[i4].toString()
            } else {
                radioButton.setHint((i4 + 1).toString())
            }
        }
        val num: Int = rowList!![i]
        if (num == 0) {
            rbRow!!.text = ""
            rbRow!!.setHint("0")
        } else {
            rbRow!!.text = num.toString()
        }
        this.rbList = rbList.subList(0, 4)
        for (i5 in 0 until gridLayout!!.childCount) {
            val radioButton2: RadioButton = gridLayout!!.getChildAt(i5) as RadioButton
            radioButton2.setOnClickListener(CustomOnClickListener())
            rbList.add(radioButton2)
        }
        gridLayout!!.getChildAt(0).performClick()
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        val f: Float
        val f2: Float
        val tip: Float
        val f3: Float
        val eventCode: Int = eventCenter.eventCode
        if (eventCode == 0) {
            val bundle: Bundle = eventCenter.data as Bundle
            val i: Int = bundle.getInt("row")
            val i2: Int = bundle.getInt("column")
            if (keyInfo!!.type == 6 || keyInfo!!.type == 8) {
                f = bundle.getInt("depth").toFloat()
                f2 = MachineData.dZScale
            } else {
                f = bundle.getInt("depth").toFloat()
                f2 = MachineData.dXScale
            }
            val i3: Int = (f * f2).toInt()
            LogUtil.i(TAG, "depth: " + i3)
            val keyInfo: KeyInfo? = this.keyInfo
            this.keyInfo!!.setKeyToothCode(
                keyInfo!!.changeSingleDepth(
                    i + (-1),
                    i2 + (-1),
                    i3.toFloat(),
                    false,
                    keyInfo.readBittingRule
                )
            )
            return
        }
        if (eventCode == 1) {
            this.dimpleCutIndex = 0
            ToolSizeManager.setCutterSize(ToolSizeManager.DimpleCutterSize)
            ToolSizeManager.setDecoderSize(100)
            if (keyInfo!!.type == 6 && keyInfo!!.spaceWidthStr.contains("-")) {
                showDimpleOperationDialog()
                return
            } else {
                showClearClampRemind(1)
                return
            }
        }
        if (eventCode != 32) {
            if (eventCode != 33) {
                if (eventCode != 39) {
                    return
                }
                ToolSizeManager.setDecoderSize(100)
                showClearClampRemind(39)
                return
            }
            this.dimpleCutIndex = 0
            dismissLoadingDialog()
            showErrorDialog(context, eventCenter.data as ErrorBean?)
            return
        }
        val operateType: OperateType = eventCenter.data as OperateType
        if (operateType == OperateType.MOVE_XYZ) {
            dismissLoadingDialog()
            return
        }
        if (operateType == OperateType.DIMPLE_DUPLICATE_LOCATION) {
            dismissLoadingDialog()
            keyInfo!!.width = OperationManager.getInstance().keyAlignInfo.decodeWidth
            this.aligned = true
            return
        }
        if (operateType == OperateType.DUPLICATE_DIMPLE_DECODE_SINGLE) {
            val keyAlignInfo: KeyAlignInfo = OperationManager.getInstance().keyAlignInfo
            val left: Int =
                (((keyAlignInfo.left - ToolSizeManager.getDecoderRadius2()) - this.x) * MachineData.dXScale).toInt()
            rbRow!!.text = abs(left.toDouble()).toString()
            rowList!!.set(this.rowIndex, left)
            if (keyInfo!!.align == 0) {
                tip = (keyAlignInfo.shoulder - this.y).toFloat()
                f3 = MachineData.dYScale
            } else {
                tip = (this.y - keyAlignInfo.tip).toFloat()
                f3 = MachineData.dYScale
            }
            val i4: Int = (tip * f3).toInt()
            val radioButton: RadioButton? = this.lastRb
            if (radioButton != null) {
                radioButton.text = i4.toString()
                val ix: Int = rbList.indexOf(this.lastRb) - 4
                if (this.rowIndex < spaceList!!.size && ix >= 0) {
                    spaceList!![this.rowIndex][ix] =
                            lastRb!!.getText().toString().trim { it <= ' ' }.toInt()

                }
            }
            dismissLoadingDialog()
            return
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION) {
            this.aligned = true
            keyInfo!!.width = OperationManager.getInstance().keyAlignInfo.decodeWidth
            val sb: StringBuilder = StringBuilder()
            val sb2: StringBuilder = StringBuilder()
            val sb3: StringBuilder = StringBuilder()
            val sb4: StringBuilder = StringBuilder()
            val i5: Int = if (arguments != null) arguments!!.getInt(ROW_COUNT) else 1
            for (i6 in 0 until i5) {
                val list: List<Int> = spaceList!![i6]
                if (i6 == 0) {
                    sb.append(list!!.size)
                } else {
                    sb.append("-")
                    sb.append(list!!.size)
                }
                if (arguments!!.getBoolean(ZIMUZHU)) {
                    for (i7 in 0..1) {
                        for (i8 in list.indices) {
                            sb2.append(list[i8])
                            if (i7 == 0) {
                                sb3.append("-200")
                            } else {
                                sb3.append("0")
                            }
                            if (i8 != list.size - 1) {
                                sb2.append(",")
                                sb3.append(",")
                            } else {
                                sb2.append(";")
                                sb3.append(";")
                            }
                        }
                        sb4.append(rowList!![i6])
                        sb4.append(";")
                    }
                } else {
                    for (i9 in list.indices) {
                        sb2.append(list[i9])
                        sb3.append("0")
                        if (i9 != list.size - 1) {
                            sb2.append(",")
                            sb3.append(",")
                        } else {
                            sb2.append(";")
                            sb3.append(";")
                        }
                    }
                    sb4.append(rowList!![i6])
                    sb4.append(";")
                }
            }
            keyInfo!!.cuts = sb.toString()
            keyInfo!!.spaceStr = sb2.toString()
            keyInfo!!.spaceWidthStr = sb3.toString()
            keyInfo!!.row_pos = sb4.toString()
            keyInfo!!.type_specific_info = ""
            dataParam.pauseTime = this.sleep
            OperationManager.getInstance().start(
                this.dataParam, AssetsJsonUtils.getKeyDecodePathSteps(
                    this.keyInfo
                ), OperateType.KEY_BLANK_DECODE_EXECUTE
            )
            return
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE) {
            dismissLoadingDialog()
            CutDialog(activity, this.dataParam, true).show()
            return
        }
        if (operateType == OperateType.KEY_BLANK_CUT_LOCATION) {
            detectCutterHigh()
            return
        }
        if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH) {
            OperationManager.getInstance().startExecution(
                StepsGenerateUtil.getKeyBlanksCutSteps(
                    this.keyInfo,
                    OperationManager.getInstance().keyAlignInfo,
                    KeyBlankCutStepsGenerateUtil.getDestCutPoint(
                        this.keyInfo,
                        OperationManager.getInstance().keyAlignInfo,
                        "",
                        this.dimpleCutIndex,
                        SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1)
                    ),
                    1,
                    SPUtils.getBoolean(SpKeys.DIMPLE_UP_DOWN_CUTTING)
                ), OperateType.KEY_BLANK_CUT_EXECUTE
            )
            return
        }
        if (operateType == OperateType.KEY_BLANK_CUT_EXECUTE) {
            dismissLoadingDialog()
            CutCountHelper.getInstance().addCutCount(this.keyInfo)
            if (keyInfo!!.type == 6 && keyInfo!!.spaceWidthStr.contains("-")) {
                dimpleCutIndex++
                showDimpleOperationDialog()
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showDimpleOperationDialog() {
        val i: Int = this.dimpleCutIndex
        if (i == 0) {
            showDimpleKeyChangeCutterDialog()
            return
        }
        if (i == 1) {
            showDimpleKeyTurnOverDialog()
            return
        }
        if (i == 2) {
            showDimpleKeyChangeCutterDialog()
        } else if (i == 3) {
            showDimpleKeyTurnOverDialog()
        } else {
            this.dimpleCutIndex = 0
        }
    }

    private fun showDimpleKeyChangeCutterDialog() {
        val anglekeyTurningDialog: AnglekeyTurningDialog = AnglekeyTurningDialog(context)
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_1))
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_3))
        }
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_2)
        } else {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_1)
        }
        anglekeyTurningDialog.setDialogBtnCallback(object :
            AnglekeyTurningDialog.DialogBtnCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.11
            // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            override fun onDialogButClick(i: Int) {
                if (i == 0) {
                    this@DuplicateDimpleDataFragment.dimpleCutIndex = 0
                    this@DuplicateDimpleDataFragment.dismissLoadingDialog()
                    OperationManager.getInstance()
                        .sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
                } else if (i == 1) {
                    incrementDimpleCutIndex(this@DuplicateDimpleDataFragment)
                    this@DuplicateDimpleDataFragment.showDimpleOperationDialog()
                } else {
                    val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                        this@DuplicateDimpleDataFragment
                    duplicateDimpleDataFragment.showLoadingDialog(
                        duplicateDimpleDataFragment.getString(
                            R.string.cutting
                        ), true
                    )
                    this@DuplicateDimpleDataFragment.startCut()
                }
            }
        })
        anglekeyTurningDialog.show()
    }

    private fun showDimpleKeyTurnOverDialog() {
        val anglekeyTurningDialog: AnglekeyTurningDialog = AnglekeyTurningDialog(context)
        if (this.dimpleCutIndex == 1) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_2))
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_1)
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_4))
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_2)
        }
        anglekeyTurningDialog.setDialogBtnCallback(object :
            AnglekeyTurningDialog.DialogBtnCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.12
            // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            override fun onDialogButClick(i: Int) {
                if (i == 0) {
                    this@DuplicateDimpleDataFragment.dimpleCutIndex = 0
                    this@DuplicateDimpleDataFragment.dismissLoadingDialog()
                    OperationManager.getInstance()
                        .sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
                } else if (i == 1) {
                    incrementDimpleCutIndex(this@DuplicateDimpleDataFragment)
                    this@DuplicateDimpleDataFragment.showDimpleOperationDialog()
                } else {
                    val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                        this@DuplicateDimpleDataFragment
                    duplicateDimpleDataFragment.showLoadingDialog(
                        duplicateDimpleDataFragment.getString(
                            R.string.cutting
                        ), true
                    )
                    this@DuplicateDimpleDataFragment.startCut()
                }
            }
        })
        anglekeyTurningDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startDecode() {
        showLoadingDialog(getString(R.string.decoding), true)
        OperationManager.getInstance().start(
            this.dataParam, AssetsJsonUtils.getCommonSteps(
                context, AssetsJsonUtils.getKeyDecodeLocationJsonPath(
                    this.keyInfo
                )
            ), OperateType.KEY_BLANK_DECODE_LOCATION
        )
    }

    private fun detectCutterHigh() {
        OperationManager.getInstance().start(
            this.dataParam, AssetsJsonUtils.getCommonSteps(
                context, AssetsJsonUtils.getKeyCutCutterHeightJsonPath(
                    this.keyInfo
                )
            ), OperateType.KEY_BLANK_CUT_CUTTER_HIGH
        )
    }

    private fun showClearClampRemind(i: Int) {
        val remindDialog: RemindDialog = RemindDialog(context)
        remindDialog.setCancleBtnVisibility(false)
        remindDialog.setRemindImg(getClearClampImg((keyInfo)!!))
        remindDialog.setRemindMsg(getString(R.string.clean_the_groove_from_chips))
        remindDialog.setDialogBtnCallback(object : RemindDialog.DialogBtnCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.13
            // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            override fun onDialogButClick(z: Boolean) {
                if (z) {
                    if (i == 39) {
                        this@DuplicateDimpleDataFragment.startDecode()
                    }
                    if (i == 1) {
                        this@DuplicateDimpleDataFragment.startCut()
                    }
                }
            }
        })
        if (remindDialog.isShowing) {
            return
        }
        remindDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCut() {
        val depthList: List<List<Int>> =
            keyInfo!!.getDepthList()
        val depthNameList: List<List<String>> =
            keyInfo!!.getDepthNameList()
        val toothCodeArray: List<List<String>> =
            keyInfo!!.getToothCodeArray()
        var i: Int = 0
        for (i2 in toothCodeArray.indices) {
            val it: Iterator<String> = toothCodeArray.get(i2).iterator()
            while (it.hasNext()) {
                i = max(
                    i.toDouble(),
                    keyInfo!!.getDepthByCode(depthList.get(i2), depthNameList.get(i2), it.next())
                        .toDouble()
                ).toInt()
            }
        }
        keyInfo!!.thick = i + 50
        OperationManager.getInstance().start(
            this.dataParam, AssetsJsonUtils.getCommonSteps(
                context, AssetsJsonUtils.getKeyCutLocationJsonPath(
                    this.keyInfo
                )
            ), OperateType.KEY_BLANK_CUT_LOCATION
        )
        showLoadingDialog(getString(R.string.cutting), true)
    }


    /* JADX INFO: Access modifiers changed from: private */
    fun getStep(radioButton: RadioButton?): Int {
        if (TextUtils.isEmpty(radioButton!!.getText().toString().trim({ it <= ' ' }))) {
            ToastUtil.showToast(R.string.please_complete_the_data)
            return 0
        }
        val indexOf: Int = rbList.indexOf(radioButton)
        val r0: String = radioButton.getText().toString().trim({ it <= ' ' })
        if (indexOf == 0) {
            return (r0.toInt() / MachineData.dXScale).toInt()
        }
        if (indexOf == 1) {
            return (r0.toInt() / MachineData.dYScale).toInt()
        }
        return (r0.toInt() / MachineData.dZScale).toInt()
    }


    private fun auto() {
        val list = spaceList!![this.rowIndex].toMutableList()
        val size: Int = list!!.size
        if (size < 2) {
            return
        }
        var i: Int = -1
        for (size2 in rbList.size - size until rbList.size) {
            val trim: String = rbList[size2].getText().toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(trim)) {
                if (i != -1) {
                    val parseInt: Int =
                        rbList[i].getText().toString().trim { it <= ' ' }.toInt()
                    val parseInt2: Int = (parseInt - trim.toInt()) / (i - size2)
                    for (size3 in rbList.size - size until rbList.size) {
                        val i2: Int = ((size3 - i) * parseInt2) + parseInt
                        rbList[size3].text = i2.toString()
                        list[size3 - 4] = i2
                    }
                    return
                }
                i = size2
            }
        }
    }

    private fun changeLast() {
        var indexOf: Int = 0
        val radioButton: RadioButton? = this.lastRb
        if (radioButton == null || (rbList.indexOf(radioButton).also { indexOf = it }) == 0) {
            return
        }
        lastRb!!.setChecked(false)
        val radioButton2: RadioButton = rbList[indexOf - 1]
        this.lastRb = radioButton2
        radioButton2!!.setChecked(true)
    }

    private fun changeNext() {
        var indexOf: Int = 0
        val radioButton: RadioButton? = this.lastRb
        if (radioButton == null || (rbList.indexOf(radioButton)
                .also { indexOf = it }) == rbList.size - 1
        ) {
            return
        }
        lastRb!!.setChecked(false)
        val radioButton2: RadioButton = rbList[indexOf + 1]
        this.lastRb = radioButton2
        radioButton2!!.setChecked(true)
    }

    private fun delete() {
        val radioButton: RadioButton? = this.lastRb
        if (radioButton != null) {
            val text: CharSequence = radioButton.getText()
            if (text.isNotEmpty()) {
                lastRb!!.text = text.subSequence(0, text.length - 1)
            }
        }
    }

    private fun inputNumb(str: String) {
        val radioButton: RadioButton? = this.lastRb
        if (radioButton != null) {
            radioButton.append(str)
            val indexOf: Int = rbList.indexOf(this.lastRb) - 4
            if (this.rowIndex < spaceList!!.size && indexOf >= 0) {
                spaceList!![this.rowIndex]
                    .set(
                        indexOf,
                        lastRb!!.getText().toString().trim { it <= ' ' }.toInt()
                    )
            }
            if (indexOf == -1) {
                rowList!![this.rowIndex] = lastRb!!.getText().toString().trim { it <= ' ' }.toInt()
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class CustomOnClickListener : View.OnClickListener {
        // android.view.View.OnClickListener
        override fun onClick(view: View) {
            if (this@DuplicateDimpleDataFragment.lastRb != null) {
                lastRb!!.setChecked(false)
            }
            if (view is RadioButton) {
                val radioButton: RadioButton = view
                radioButton.setChecked(true)
                this@DuplicateDimpleDataFragment.lastRb = radioButton
            }
        }
    }

    companion object {
        private val KEY: String = "key"
        private val ROW_COUNT: String = "row_count"
        private val SPACE_COUNT: String = "space_count"
        private val ZIMUZHU: String = "zimuzhu"
        fun incrementDimpleCutIndex(duplicateDimpleDataFragment: DuplicateDimpleDataFragment): Int {
            val i: Int = duplicateDimpleDataFragment.dimpleCutIndex
            duplicateDimpleDataFragment.dimpleCutIndex = i + 1
            return i
        }

        fun newInstance(
            keyInfo: KeyInfo?,
            i: Int,
            z: Boolean,
            i2: Int
        ): DuplicateDimpleDataFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(KEY, keyInfo)
            bundle.putBoolean(ZIMUZHU, z)
            bundle.putInt(SPACE_COUNT, i)
            bundle.putInt(ROW_COUNT, i2)
            val duplicateDimpleDataFragment: DuplicateDimpleDataFragment =
                DuplicateDimpleDataFragment()
            duplicateDimpleDataFragment.setArguments(bundle)
            return duplicateDimpleDataFragment
        }
    }
}
