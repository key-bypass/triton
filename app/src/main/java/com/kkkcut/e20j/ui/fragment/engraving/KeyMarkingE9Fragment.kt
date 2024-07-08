package com.kkkcut.e20j.ui.fragment.engraving

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.text.TextPaint
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.internal.view.SupportMenu
import androidx.core.view.ViewCompat
import com.cutting.machine.Command
import com.cutting.machine.CuttingMachine
import com.cutting.machine.OperateType
import com.cutting.machine.bean.StepBean
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.operation.cut.DataParam
import com.cutting.machine.utils.AssetsJsonUtils
import com.cutting.machine.utils.DecoderPositionUtils
import com.cutting.machine.utils.UnitConvertUtil
import com.kkkcut.e20j.AlignTextView
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChild
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.customView.KeyMarkingE9Layout
import com.kkkcut.e20j.customView.OnDragTouchListener
import com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.BitmapUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.Date
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/* loaded from: classes.dex */
class KeyMarkingE9Fragment() : BaseBackFragment() {
    var cbAutoCenter: CheckBox? = null
    private var currentView: View? = null
    private var decode = false
    private var engraveingBitmap: Bitmap? = null

    var flContainer: FrameLayout? = null
    private var fontFace: Typeface? = null

    var kmfl: KeyMarkingE9Layout? = null

    var rbHighSpeed: RadioButton? = null

    var rbLowSpeed: RadioButton? = null

    var rbMiddleSpeed: RadioButton? = null
    private var rightSpace = 0

    var tvDepthValue: TextView? = null
    private var cutDepth = 10
    private val origin = Point()
    var dataParam: DataParam = DataParam()
    private val onDraggableClickListener: OnDraggableClickListener =
        object : OnDraggableClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.1
            // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
            override fun onDragged(view: View, i: Int, i2: Int) {
            }

            // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
            override fun onClick(view: View) {
                this@KeyMarkingE9Fragment.changeCurrentView(view)
            }
        }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_marking_e9
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun changeCurrentView(view: View?) {
        if (view is TextView) {
            changeCurrentText(view)
        } else {
            changeCurrentImage(view as ImageView?)
        }
    }

    private fun changeCurrentText(textView: TextView) {
        val view = this.currentView
        if (view != null && (view is TextView)) {
            view.setTextColor(ViewCompat.MEASURED_STATE_MASK)
        }
        this.currentView = textView
        textView.setTextColor(SupportMenu.CATEGORY_MASK)
    }

    private fun changeCurrentImage(imageView: ImageView?) {
        val view = this.currentView
        if (view != null && (view is TextView)) {
            view.setTextColor(ViewCompat.MEASURED_STATE_MASK)
        }
        this.currentView = imageView
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.key_marking)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.fontFace = ResourcesCompat.getFont((context)!!, R.font.thin)
        val i = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
        if (i == 1) {
            rbLowSpeed!!.isChecked = true
        } else if (i == 15) {
            rbMiddleSpeed!!.isChecked = true
        } else if (i == 25) {
            rbHighSpeed!!.isChecked = true
        }
        initParam()
    }

    private fun initParam() {
        dataParam.clamp = Clamp.E9S5
        dataParam.decoderSize = 100
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_cut -> {
                if (ClampManager.getInstance()
                        .checkHasCalibrated(Clamp.E9S5) && flContainer!!.childCount != 0
                ) {
                    dataParam.cutSpeed = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
                    dataParam.cutDepth = this.cutDepth
                    checkDecoderStateOrLocation()
                    return
                }
                return
            }

            R.id.bt_decode -> {
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.E9S5)) {
                    this.decode = true
                    checkDecoderStateOrLocation()
                    return
                }
                return
            }

            R.id.font_size_add -> {
                val view2 = this.currentView
                if (view2 != null) {
                    if (view2 is TextView) {
                        val textView = view2
                        textView.setTextSize(0, textView.textSize + 1.0f)
                        return
                    }
                    val imageView = view2 as ImageView
                    val width = imageView.width
                    val height = imageView.height
                    val layoutParams = imageView.layoutParams as FrameLayout.LayoutParams
                    val i = layoutParams.leftMargin + (width / 2)
                    val i2 = layoutParams.topMargin + (height / 2)
                    val i3 = (width * 1.1).toInt()
                    layoutParams.width = i3
                    val i4 = (height * 1.1).toInt()
                    layoutParams.height = i4
                    if (i3 > 300 || i4 > 300) {
                        return
                    }
                    var i5 = i - (layoutParams.width / 2)
                    if (i5 < 0) {
                        i5 = 0
                    }
                    val i6 = i2 - (layoutParams.height / 2)
                    layoutParams.setMargins(
                        i5,
                        if (i6 >= 0) i6 else 0,
                        i + (layoutParams.width / 2),
                        i2 + (layoutParams.height / 2)
                    )
                    imageView.layoutParams = layoutParams
                    return
                }
                return
            }

            R.id.font_size_reduce -> {
                val view3 = this.currentView
                if (view3 != null) {
                    if (view3 is TextView) {
                        val textView2 = view3
                        val textSize = textView2.textSize
                        if (textSize > 24.0f) {
                            textView2.setTextSize(0, textSize - 1.0f)
                            return
                        }
                        return
                    }
                    val imageView2 = view3 as ImageView
                    val width2 = imageView2.width
                    val height2 = imageView2.height
                    val layoutParams2 = imageView2.layoutParams as FrameLayout.LayoutParams
                    val i7 = layoutParams2.leftMargin + (width2 / 2)
                    val i8 = layoutParams2.topMargin + (height2 / 2)
                    layoutParams2.width = (width2 * 0.9).toInt()
                    layoutParams2.height = (height2 * 0.9).toInt()
                    layoutParams2.setMargins(
                        i7 - (layoutParams2.width / 2),
                        i8 - (layoutParams2.height / 2),
                        i7 + (layoutParams2.width / 2),
                        i8 + (layoutParams2.height / 2)
                    )
                    imageView2.layoutParams = layoutParams2
                    return
                }
                return
            }

            R.id.iv_depth_add -> {
                val i9 = this.cutDepth
                if (i9 < 15) {
                    this.cutDepth = i9 + 1
                    tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_depth_reduce -> {
                val i10 = this.cutDepth
                if (i10 > 1) {
                    this.cutDepth = i10 - 1
                    tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_down -> {
                val view4 = this.currentView
                if (view4 != null) {
                    val bottom = view4.bottom + 5
                    Log.i(TAG, "bottom: $bottom")
                    if (bottom > flContainer!!.measuredHeight - flContainer!!.paddingBottom) {
                        return
                    }
                    Log.i(
                        TAG,
                        "getWidth: " + currentView!!.width + "-getHeight: " + currentView!!.height
                    )
                    val view5 = this.currentView
                    view5!!.layout(
                        view5.left,
                        currentView!!.top + 5,
                        currentView!!.right, bottom
                    )
                    val marginLayoutParams = currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.layoutParams = marginLayoutParams
                    return
                }
                if (flContainer!!.childCount > 0) {
                    changeCurrentView(flContainer!!.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_left -> {
                val view6 = this.currentView
                if (view6 != null) {
                    val left = view6.left - 5
                    if (left < 0) {
                        return
                    }
                    val view7 = this.currentView
                    view7!!.layout(
                        left, view7.top,
                        currentView!!.right - 5,
                        currentView!!.bottom
                    )
                    val marginLayoutParams2 = currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams2.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.layoutParams = marginLayoutParams2
                    return
                }
                if (flContainer!!.childCount > 0) {
                    changeCurrentView(flContainer!!.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_right -> {
                val view8 = this.currentView
                if (view8 != null) {
                    val right = view8.right + 5
                    if (right > (flContainer!!.measuredWidth - flContainer!!.paddingRight) - 0) {
                        return
                    }
                    val view9 = this.currentView
                    view9!!.layout(
                        view9.left + 5,
                        currentView!!.top, right,
                        currentView!!.bottom
                    )
                    val marginLayoutParams3 = currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams3.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.layoutParams = marginLayoutParams3
                    return
                }
                if (flContainer!!.childCount > 0) {
                    changeCurrentView(flContainer!!.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_up -> {
                val view10 = this.currentView
                if (view10 != null) {
                    val top = view10.top - 5
                    if (top < 0) {
                        return
                    }
                    val view11 = this.currentView
                    view11!!.layout(
                        view11.left, top,
                        currentView!!.right,
                        currentView!!.bottom - 5
                    )
                    val marginLayoutParams4 = currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams4.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.layoutParams = marginLayoutParams4
                    return
                }
                if (flContainer!!.childCount > 0) {
                    changeCurrentView(flContainer!!.getChildAt(0))
                    return
                }
                return
            }

            R.id.rb_high_speed -> {
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 25)
                return
            }

            R.id.rb_low_speed -> {
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 1)
                return
            }

            R.id.rb_middle_speed -> {
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 15)
                return
            }

            R.id.tv_add_pic -> {
                startForResult(UsbImageSelectFragment.Companion.newInstance(), 1)
                return
            }

            R.id.tv_add_text -> {
                showAddTextDialog()
                return
            }

            R.id.tv_delete -> {
                val view12 = this.currentView
                if (view12 != null) {
                    flContainer!!.removeView(view12)
                    if (flContainer!!.childCount > 0) {
                        changeCurrentView(flContainer!!.getChildAt(0))
                        return
                    } else {
                        this.currentView = null
                        return
                    }
                }
                return
            }

            R.id.tv_edit_text -> {
                if (this.currentView != null) {
                    showEditDialog()
                    return
                }
                return
            }

            R.id.tv_save -> {
                if (flContainer!!.childCount == 0) {
                    return
                }
                showSaveTemplateDialog()
                return
            }

            R.id.tv_select_template -> {
                startForResult(TemplateSelectFragment.Companion.newInstance(), 2)
                return
            }

            else -> return
        }
    }

    private fun checkDecoderStateOrLocation() {
        if (SPUtils.getBoolean(SpKeys.Not_detect_decoder_position)) {
            decoderLocation()
        } else {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION)
        }
    }

    private fun decoderLocation() {
        showLoadingDialog(getString(R.string.waitting), true)
        OperationManager.getInstance().start(
            this.dataParam, AssetsJsonUtils.getCommonSteps(
                context, "keyblank/decoder_e9/S5.json"
            ), OperateType.ENGRAVE_CUT_LOCATION
        )
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun checkDecoderState(operateType: OperateType?) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType)
    }

    private fun showSaveTemplateDialog() {
        val editDialog = EditDialog(context)
        editDialog.setEditTextContent("")
        editDialog.setTip(getString(R.string.enter_remarks))
        editDialog.setDialogBtnCallback(DialogInputFinishCallBack { str ->
            // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment$$ExternalSyntheticLambda1
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            this@KeyMarkingE9Fragment.m53xae3c4e4d(str)
        })
        editDialog.setContentNullable(true)
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: saveTemplate, reason: merged with bridge method [inline-methods] */
    fun m53xae3c4e4d(str: String?) {
        val keyMarkingTemplate = KeyMarkingTemplate()
        keyMarkingTemplate.width = flContainer!!.width
        keyMarkingTemplate.height = flContainer!!.height
        keyMarkingTemplate.marginLeft = flContainer!!.left - 45
        keyMarkingTemplate.marginTop = flContainer!!.top - 45
        keyMarkingTemplate.description = str
        keyMarkingTemplate.time = Date().time
        UserDataDaoManager.getInstance(context).saveKeyMarkingTemplate(keyMarkingTemplate)
        for (i in 0 until flContainer!!.childCount) {
            val childAt = flContainer!!.getChildAt(i)
            val keyMarkingChild = KeyMarkingChild()
            if (childAt is TextView) {
                val textView = childAt
                val trim = textView.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(trim)) {
                    val textSize = textView.textSize
                    keyMarkingChild.type = 1
                    keyMarkingChild.fontSize = textSize
                    keyMarkingChild.text = trim
                    keyMarkingChild.marginLeft = textView.left
                    keyMarkingChild.marginTop = textView.top
                    keyMarkingChild.parentId = keyMarkingTemplate.id
                    UserDataDaoManager.getInstance(context)
                        .saveKeyMarkingTemplateChild(keyMarkingChild)
                }
            } else {
                val imageView = childAt as ImageView
                keyMarkingChild.type = 2
                keyMarkingChild.imageByte =
                    BitmapUtil.bitmapToByte((imageView.drawable as BitmapDrawable).bitmap)
                keyMarkingChild.width = imageView.width
                keyMarkingChild.height = imageView.height
                keyMarkingChild.marginLeft = imageView.left
                keyMarkingChild.marginTop = imageView.top
                keyMarkingChild.parentId = keyMarkingTemplate.id
                UserDataDaoManager.getInstance(context).saveKeyMarkingTemplateChild(keyMarkingChild)
            }
        }
        ToastUtil.showToast(R.string.saved_successfully)
    }

    private fun getImageViewFromFile(file: File): ImageView {
        val imageView = ImageView(context)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.path, options)
        val i = options.outWidth
        val i2 = options.outHeight
        val f = i.toFloat()
        val f2 = (f * 1.0f) / 300.0f
        val f3 = i2.toFloat()
        val f4 = (f3 * 1.0f) / 300.0f
        if (f2 <= 1.0f && f4 <= 1.0f) {
            options.inJustDecodeBounds = false
            val decodeFile = BitmapFactory.decodeFile(file.path, options)
            val layoutParams = FrameLayout.LayoutParams(i, i2)
            layoutParams.setMargins((300 - i) / 2, (300 - i2) / 2, (i + 300) / 2, (i2 + 300) / 2)
            imageView.layoutParams = layoutParams
            imageView.setImageBitmap(decodeFile)
        } else {
            options.inJustDecodeBounds = false
            val max = max(f2.toDouble(), f4.toDouble()).toFloat()
            options.inSampleSize = max.toInt()
            val scaleBitmap =
                BitmapUtil.scaleBitmap(BitmapFactory.decodeFile(file.path, options), max)
            val i3 = (f / max).toInt()
            val i4 = (f3 / max).toInt()
            val layoutParams2 = FrameLayout.LayoutParams(i3, i4)
            layoutParams2.setMargins((300 - i3) / 2, (300 - i4) / 2, (i3 + 300) / 2, (i4 + 300) / 2)
            imageView.layoutParams = layoutParams2
            imageView.setImageBitmap(scaleBitmap)
        }
        return imageView
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun detectCutterHigh() {
        addDisposable(
            Observable.fromCallable<Rect> {
                this.markingRectRealScreen!!
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map { rect ->
                    this.getScreenBitmap(rect)
                }.observeOn(Schedulers.io()).map(
                ({bitmap ->
                    val engraveParam = this@KeyMarkingE9Fragment.engraveParam
                    this@KeyMarkingE9Fragment.engraveingBitmap = bitmap
                    EngraveE9PathGen.detectCutterHigh(bitmap, engraveParam)
                })
            ).subscribe({ list ->
                OperationManager.getInstance().start(
                    this@KeyMarkingE9Fragment.dataParam,
                    list,
                    OperateType.ENGRAVE_CUT_CUTTER_HIGH
                )
            }, { dismissLoadingDialog() })
        )
    }

    private fun startEngraving() {
        if (this.engraveingBitmap == null) {
            ToastUtil.showToast("no bitmap")
            dismissLoadingDialog()
        } else {
            addDisposable(
                Observable.fromCallable<List<StepBean>> {
                    EngraveE9PathGen.bitmapToPath(
                        this@KeyMarkingE9Fragment.engraveingBitmap,
                        engraveParam
                    )
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).observeOn(
                    Schedulers.io()
                ).subscribe({ list ->
                    OperationManager.getInstance().start(
                        this@KeyMarkingE9Fragment.dataParam,
                        list,
                        OperateType.ENGRAVE_CUT_EXECUTE
                    )
                }, { dismissLoadingDialog() })
            )
        }
    }

    val engraveParam: EngraveE9PathGen.EngraveParam
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            val keyAlignInfo = OperationManager.getInstance().keyAlignInfo
            val tip = keyAlignInfo.tip
            val centerY = ClampManager.getInstance().e9S5.centerY - (UnitConvertUtil.cmm2StepY(
                flContainer!!.width * 10
            ) / 2)
            val i = origin.x * 10
            val i2 = origin.y * 10
            val i3 = ClampManager.getInstance().dc.getxDistance()
            val i4 = ClampManager.getInstance().dc.getyDistance()
            val keyFace = keyAlignInfo.keyFace
            val trim = tvDepthValue!!.text.toString().trim { it <= ' ' }
            return EngraveE9PathGen.EngraveParam(
                tip,
                centerY,
                i,
                i2,
                i3,
                i4,
                keyFace,
                UnitConvertUtil.cmm2StepZ(
                    if (!TextUtils.isEmpty(trim)) Math.round(
                        trim.replace(
                            "mm",
                            ""
                        ).toFloat() * 100.0f
                    ) else 5
                ),
                SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
            )
        }

    /* JADX INFO: Access modifiers changed from: private */
    fun getScreenBitmap(rect: Rect): Bitmap {
        kmfl!!.setMarkLineVisible(false)
        val decorView = activity!!.window.decorView
        decorView.isDrawingCacheEnabled = true
        decorView.buildDrawingCache()
        val createBitmap = Bitmap.createBitmap(
            decorView.drawingCache,
            rect.left,
            rect.top,
            rect.width(),
            rect.height()
        )
        decorView.isDrawingCacheEnabled = false
        kmfl!!.setMarkLineVisible(true)
        return createBitmap
    }

    val markingRectRealScreen: Rect?
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            val rect = Rect(Int.MAX_VALUE, Int.MAX_VALUE, 0, 0)
            val childCount = flContainer!!.childCount
            if (childCount == 0) {
                return null
            }
            for (i in 0 until childCount) {
                val childAt = flContainer!!.getChildAt(i)
                val iArr = IntArray(2)
                childAt.getLocationOnScreen(iArr)
                rect.left = min(rect.left.toDouble(), iArr[0].toDouble()).toInt()
                rect.top = min(rect.top.toDouble(), iArr[1].toDouble()).toInt()
                rect.right = max(rect.right.toDouble(), (iArr[0] + childAt.width).toDouble())
                    .toInt()
                rect.bottom = max(rect.bottom.toDouble(), (iArr[1] + childAt.height).toDouble())
                    .toInt()
            }
            val iArr2 = IntArray(2)
            flContainer!!.getLocationInWindow(iArr2)
            origin.x = abs((rect.left - iArr2[0]).toDouble()).toInt()
            origin.y = abs((rect.top - iArr2[1]).toDouble()).toInt()
            return rect
        }

    private fun showEditDialog() {
        val view = this.currentView
        if (view == null || view !is TextView) {
            return
        }
        val editDialog = EditDialog(context)
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.8
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                (currentView as TextView?)!!.text = str
            }
        })
        editDialog.setTip(getString(R.string.edit_text))
        editDialog.setEditTextContent(
            (currentView as TextView?)!!.text.toString().trim { it <= ' ' })
        if (editDialog.isShowing) {
            return
        }
        editDialog.show()
    }

    private fun showAddTextDialog() {
        val editDialog = EditDialog(context)
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment$$ExternalSyntheticLambda0
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                this@KeyMarkingE9Fragment.m52x7d5ccc69(str)
            }
        })
        editDialog.setEditTextContent("")
        editDialog.setTip(getString(R.string.add_text))
        if (editDialog.isShowing) {
            return
        }
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: addText, reason: merged with bridge method [inline-methods] */
    fun m52x7d5ccc69(str: String) {
        val alignTextView = AlignTextView(context)
        alignTextView.elevation = 1.0f
        alignTextView.includeFontPadding = false
        alignTextView.setTextColor(SupportMenu.CATEGORY_MASK)
        alignTextView.setTextSize(0, 30.0f)
        alignTextView.text = str
        alignTextView.letterSpacing = letterSpace
        alignTextView.includeFontPadding = false
        val width = flContainer!!.width
        val height = flContainer!!.height
        val onDragTouchListener = OnDragTouchListener(width, height)
        onDragTouchListener.onDraggableClickListener = onDraggableClickListener
        alignTextView.setOnTouchListener(onDragTouchListener)
        val layoutParams = FrameLayout.LayoutParams(-2, -2)
        val textPaint = TextPaint()
        textPaint.textSize = 30.0f
        textPaint.letterSpacing = letterSpace
        val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var str2 = ""
        for (i in split.indices) {
            if (split[i].length > str2.length) {
                str2 = split[i]
            }
        }
        val measureText = textPaint.measureText(str2).toInt()
        val fontMetrics = textPaint.fontMetrics
        val f = fontMetrics.descent - fontMetrics.ascent
        var i2 = 0
        for (c: Char in str.toCharArray()) {
            if (c == '\n') {
                i2++
            }
        }
        if (measureText < width) {
            val i3 = (width / 2) - (measureText / 2)
            val f2 = (i2 + 1) * f
            if (f2 > height) {
                ToastUtil.showToast("Exceeded number of rows")
                return
            }
            layoutParams.setMargins(i3, ((height / 2) - (f2 / 2.0f)).toInt(), 0, 0)
        } else {
            val ceil = f * ((ceil(((measureText * 1.0f) / width).toDouble())
                .toInt()) + i2)
            if (ceil > height) {
                ToastUtil.showToast("Exceeded number of rows")
                return
            } else {
                layoutParams.setMargins(0, ((height / 2) - (ceil / 2.0f)).toInt(), 0, 0)
                layoutParams.width = width
            }
        }
        flContainer!!.addView(alignTextView, layoutParams)
        changeCurrentText(alignTextView)
    }

    // com.kkkcut.e20j.base.BaseFFragment, me.yokeyword.fragmentation.ISupportFragment
    override fun onFragmentResult(i: Int, i2: Int, bundle: Bundle?) {
        var file: File? = null
        if (i == 1) {
            if (bundle == null || ((bundle.getSerializable("imageFile") as File?).also {
                    file = (it)!!
                }) == null) {
                return
            }
            val imageViewFromFile = getImageViewFromFile(file!!)
            flContainer!!.addView(imageViewFromFile)
            val onDragTouchListener = OnDragTouchListener(
                flContainer!!.width,
                flContainer!!.height
            )
            onDragTouchListener.onDraggableClickListener = onDraggableClickListener
            imageViewFromFile.setOnTouchListener(onDragTouchListener)
            this.currentView = imageViewFromFile
            return
        }
        if (i != 2 || bundle == null) {
            return
        }
        val keyMarkingTemplate = bundle.getSerializable("template") as KeyMarkingTemplate?
        flContainer!!.removeAllViews()
        val layoutParams = FrameLayout.LayoutParams(keyMarkingTemplate!!.width, -1)
        layoutParams.setMargins(
            keyMarkingTemplate.marginLeft + 45,
            keyMarkingTemplate.marginTop + 45,
            0,
            10
        )
        flContainer!!.layoutParams = layoutParams
        for (keyMarkingChild: KeyMarkingChild in keyMarkingTemplate.childrenBeanList) {
            if (keyMarkingChild.type == 1) {
                val alignTextView = AlignTextView(context)
                alignTextView.elevation = 1.0f
                alignTextView.setTextSize(0, keyMarkingChild.fontSize)
                alignTextView.text = keyMarkingChild.text
                alignTextView.includeFontPadding = false
                alignTextView.letterSpacing = letterSpace
                alignTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK)
                val layoutParams2 = FrameLayout.LayoutParams(-2, -2)
                layoutParams2.setMargins(
                    keyMarkingChild.marginLeft,
                    keyMarkingChild.marginTop,
                    0,
                    0
                )
                flContainer!!.addView(alignTextView, layoutParams2)
                val onDragTouchListener2 = OnDragTouchListener(
                    flContainer!!.width,
                    flContainer!!.height
                )
                onDragTouchListener2.onDraggableClickListener = onDraggableClickListener
                alignTextView.setOnTouchListener(onDragTouchListener2)
            } else {
                val imageView = ImageView(context)
                imageView.setImageBitmap(BitmapUtil.bytes2Bimap(keyMarkingChild.imageByte))
                val layoutParams3 =
                    FrameLayout.LayoutParams(keyMarkingChild.width, keyMarkingChild.height)
                layoutParams3.setMargins(
                    keyMarkingChild.marginLeft,
                    keyMarkingChild.marginTop,
                    0,
                    0
                )
                flContainer!!.addView(imageView, layoutParams3)
                val onDragTouchListener3 = OnDragTouchListener(
                    flContainer!!.width,
                    flContainer!!.height
                )
                onDragTouchListener3.onDraggableClickListener = onDraggableClickListener
                imageView.setOnTouchListener(onDragTouchListener3)
            }
        }
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible) {
            val eventCode = eventCenter.eventCode
            if (eventCode == 47) {
                showLoadingDialog((eventCenter.data as Int).toString() + "%", true)
                return
            }
            if (eventCode != 51) {
                when (eventCode) {
                    32 -> {
                        handleOperationFinish(eventCenter)
                        return
                    }

                    33 -> {
                        this.decode = false
                        showError(eventCenter)
                        return
                    }

                    34 -> {
                        showLoadingDialog(getString(R.string.waitting))
                        return
                    }

                    else -> return
                }
            }
            if (DecoderPositionUtils.isDownPosition(((eventCenter.data as Int)))) {
                decoderLocation()
            } else {
                putDownDecoderRemindDialog()
            }
        }
    }

    private fun showError(eventCenter: EventCenter<*>) {
        dismissLoadingDialog()
        showErrorDialog(context, eventCenter.data as ErrorBean)
    }

    private fun handleOperationFinish(eventCenter: EventCenter<*>) {
        val operateType = eventCenter.data as OperateType
        if (operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            ClampManager.getInstance().e9S5.centerY
            var right = OperationManager.getInstance().keyAlignInfo.right
            if (right > UnitConvertUtil.cmm2StepY(MachineData.getyAxisMax())) {
                right = UnitConvertUtil.cmm2StepY(MachineData.getyAxisMax())
            }
            var left = OperationManager.getInstance().keyAlignInfo.left
            if (left < 0) {
                left = 0
            }
            val step2CmmKeyY = UnitConvertUtil.step2CmmKeyY(
                abs((right - left).toDouble()).toInt()
            )
            this.rightSpace = step2CmmKeyY
            val i = step2CmmKeyY / 10
            val layoutParams = FrameLayout.LayoutParams(i, -1)
            layoutParams.setMargins(195 - (i / 2), 45, 0, 10)
            flContainer!!.layoutParams = layoutParams
            kmfl!!.showBorder(this.rightSpace / 10)
            if (this.decode) {
                center(i)
                dismissLoadingDialog()
                this.decode = false
                return
            } else {
                if (cbAutoCenter!!.isChecked) {
                    center(i)
                }
                Handler().postDelayed(object : Runnable {
                    // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.9
                    // java.lang.Runnable
                    override fun run() {
                        this@KeyMarkingE9Fragment.detectCutterHigh()
                    }
                }, 100L)
                return
            }
        }
        if (operateType == OperateType.ENGRAVE_CUT_CUTTER_HIGH) {
            startEngraving()
        } else if (operateType == OperateType.ENGRAVE_CUT_EXECUTE) {
            addDisposable(
                Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                    .observeOn(
                        AndroidSchedulers.mainThread()
                    ).subscribe({
                        this@KeyMarkingE9Fragment.dismissLoadingDialog()
                    }, { dismissLoadingDialog() })
            )
            showLoadingDialog("100%", true)
        }
    }

    fun putDownDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog = RemindDialog(context)
            remindDialog.setRemindImg(R.drawable.pull_decoder_down)
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down))
            remindDialog.setDialogBtnCallback { z ->
                if (z) {
                    this@KeyMarkingE9Fragment.checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION)
                } else {
                    this@KeyMarkingE9Fragment.dismissLoadingDialog()
                }
            }
            remindDialog.show()
        }
    }

    private fun center(i: Int) {
        val childCount = flContainer!!.childCount
        var i2 = Int.MAX_VALUE
        var i3 = 0
        for (i4 in 0 until childCount) {
            val childAt = flContainer!!.getChildAt(i4)
            i2 = min(i2.toDouble(), childAt.left.toDouble()).toInt()
            i3 = max(i3.toDouble(), (childAt.left + childAt.width).toDouble())
                .toInt()
        }
        if (i3 - i2 > i) {
            for (i5 in 0 until childCount) {
                val childAt2 = flContainer!!.getChildAt(i5)
                if (childAt2.width > i) {
                    val marginLayoutParams = childAt2.layoutParams as MarginLayoutParams
                    marginLayoutParams.width = i
                    marginLayoutParams.setMargins(0, childAt2.top, 0, 0)
                    childAt2.layoutParams = marginLayoutParams
                } else {
                    val left = childAt2.left + childAt2.width
                    if (left > i) {
                        val i6 = left - i
                        childAt2.layout(
                            childAt2.left - i6,
                            childAt2.top,
                            childAt2.right - i6,
                            childAt2.bottom
                        )
                        val marginLayoutParams2 = childAt2.layoutParams as MarginLayoutParams
                        marginLayoutParams2.setMargins(childAt2.left, childAt2.top, 0, 0)
                        childAt2.layoutParams = marginLayoutParams2
                    }
                }
                val onDragTouchListener = OnDragTouchListener(
                    i,
                    flContainer!!.height
                )
                onDragTouchListener.onDraggableClickListener = onDraggableClickListener
                childAt2.setOnTouchListener(onDragTouchListener)
            }
            return
        }
        val i7 = i / 2
        Log.i(TAG, "centerKey: $i7")
        val i8 = (i2 + i3) / 2
        Log.i(TAG, "centerContent: $i8")
        var i9 = i7 - i8
        for (i10 in 0 until childCount) {
            val childAt3 = flContainer!!.getChildAt(i10)
            if (childAt3.left + i9 < 0) {
                i9 = -childAt3.left
            }
            childAt3.layout(childAt3.left + i9, childAt3.top, childAt3.right + i9, childAt3.bottom)
            val marginLayoutParams3 = childAt3.layoutParams as MarginLayoutParams
            marginLayoutParams3.setMargins(childAt3.left, childAt3.top, 0, 0)
            childAt3.layoutParams = marginLayoutParams3
            val onDragTouchListener2 = OnDragTouchListener(
                i,
                flContainer!!.height
            )
            onDragTouchListener2.onDraggableClickListener = onDraggableClickListener
            childAt3.setOnTouchListener(onDragTouchListener2)
        }
    }

    companion object {
        private val FRAME_HEITHT = 300
        private val FRAME_WIDTH = 300
        private val LEFT_SPACE = 0
        private val SELECT_IMAGE = 1
        private val SELECT_TEMPLATE = 2
        private val letterSpace = 0.05f

        fun newInstance(): KeyMarkingE9Fragment {
            val bundle = Bundle()
            val keyMarkingE9Fragment = KeyMarkingE9Fragment()
            keyMarkingE9Fragment.arguments = bundle
            return keyMarkingE9Fragment
        }

        fun substring(str: String, i: Int, i2: Int): String? {
            if (i > str.length) {
                return null
            }
            if (i2 > str.length) {
                return str.substring(i, str.length)
            }
            return str.substring(i, i2)
        }
    }
}
