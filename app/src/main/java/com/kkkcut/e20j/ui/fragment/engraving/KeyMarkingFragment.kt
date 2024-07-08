package com.kkkcut.e20j.ui.fragment.engraving

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.internal.view.SupportMenu
import androidx.core.view.ViewCompat
import com.cutting.machine.OperateType
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.error.ErrorCode
import com.cutting.machine.operation.cut.DataParam
import com.cutting.machine.utils.AssetsJsonUtils
import com.kkkcut.e20j.AlignTextView
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChild
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.customView.KeyMarkingFramelayout
import com.kkkcut.e20j.customView.OnDragTouchListener
import com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentKeyMarkingBinding
import com.kkkcut.e20j.utils.BitmapUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/* loaded from: classes.dex */
class KeyMarkingFragment : BaseBackFragment() {
    var binding: FragmentKeyMarkingBinding? = null

    private var engraveingBitmap: Bitmap? = null
    private var currentView: View? = null
    private var decode: Boolean = false


    var kmfl: KeyMarkingFramelayout? = null

    private var rightSpace: Int = 0

    private var cutDepth: Int = 10
    private val origin: Point = Point()
    var dataParam: DataParam = DataParam()
    private val onDraggableClickListener: OnDraggableClickListener =
        object : OnDraggableClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.1
            // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
            override fun onDragged(view: View, i: Int, i2: Int) {
            }

            // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
            override fun onClick(view: View) {
                this@KeyMarkingFragment.changeCurrentView(view)
            }
        }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentKeyMarkingBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_marking
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
        val view: View? = this.currentView
        if ((view is TextView)) {
            view.setTextColor(ViewCompat.MEASURED_STATE_MASK)
        }
        this.currentView = textView
        textView.setTextColor(SupportMenu.CATEGORY_MASK)
    }

    private fun changeCurrentImage(imageView: ImageView?) {
        val view: View? = this.currentView
        if ((view is TextView)) {
            view.setTextColor(ViewCompat.MEASURED_STATE_MASK)
        }
        this.currentView = imageView
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String {
        return getString(R.string.key_marking)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val i: Int = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
        if (i == 1) {
            binding!!.rbLowSpeed.setChecked(true)
        } else if (i == 15) {
            binding!!.rbMiddleSpeed.setChecked(true)
        } else if (i == 25) {
            binding!!.rbHighSpeed.setChecked(true)
        }
        initParam()
        showRemind()
    }

    private fun showRemind() {
        val warningDialog = WarningDialog(context)
        warningDialog.show()
        warningDialog.setRemind("First to click MEASURE button to measure the engraving area of metal piece")
    }

    private fun initParam() {
        dataParam.clamp = Clamp.S5
        dataParam.decoderSize = 100
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_cut -> {
                if (ClampManager.getInstance()
                        .checkHasCalibrated(Clamp.S5) && binding!!.flContainer.childCount != 0
                ) {
                    showLoadingDialog(getString(R.string.waitting), true)
                    dataParam.cutDepth = this.cutDepth
                    dataParam.cutSpeed = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
                    OperationManager.getInstance().start(
                        this.dataParam,
                        AssetsJsonUtils.getCommonSteps(context, "keyblank/decoder/S5.json"),
                        OperateType.ENGRAVE_CUT_LOCATION
                    )
                    return
                }
                return
            }

            R.id.bt_decode -> {
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.S5)) {
                    showLoadingDialog(getString(R.string.waitting), true)
                    this.decode = true
                    OperationManager.getInstance().start(
                        this.dataParam,
                        AssetsJsonUtils.getCommonSteps(context, "keyblank/decoder/S5.json"),
                        OperateType.ENGRAVE_CUT_LOCATION
                    )
                    return
                }
                return
            }

            R.id.font_size_add -> {
                val view2: View? = this.currentView
                if (view2 != null) {
                    if (view2 is TextView) {
                        view2.setTextSize(0, view2.textSize + 1.0f)
                        return
                    }
                    val imageView: ImageView = view2 as ImageView
                    val width: Int = imageView.width
                    val height: Int = imageView.height
                    val layoutParams: FrameLayout.LayoutParams =
                        imageView.layoutParams as FrameLayout.LayoutParams
                    val i: Int = layoutParams.leftMargin + (width / 2)
                    val i2: Int = layoutParams.topMargin + (height / 2)
                    val i3: Int = (width * 1.1).toInt()
                    layoutParams.width = i3
                    val i4: Int = (height * 1.1).toInt()
                    layoutParams.height = i4
                    if (i3 > 302 || i4 > 302) {
                        return
                    }
                    var i5: Int = i - (layoutParams.width / 2)
                    if (i5 < 0) {
                        i5 = 0
                    }
                    val i6: Int = i2 - (layoutParams.height / 2)
                    layoutParams.setMargins(
                        i5,
                        max(i6.toDouble(), 0.0).toInt(),
                        i + (layoutParams.width / 2),
                        i2 + (layoutParams.height / 2)
                    )
                    imageView.setLayoutParams(layoutParams)
                    return
                }
                return
            }

            R.id.font_size_reduce -> {
                val view3: View? = this.currentView
                if (view3 != null) {
                    if (view3 is TextView) {
                        val textSize: Float = view3.textSize
                        if (textSize > 24.0f) {
                            view3.setTextSize(0, textSize - 1.0f)
                            return
                        }
                        return
                    }
                    val imageView2: ImageView = view3 as ImageView
                    val width2: Int = imageView2.width
                    val height2: Int = imageView2.height
                    val layoutParams2: FrameLayout.LayoutParams =
                        imageView2.layoutParams as FrameLayout.LayoutParams
                    val i7: Int = layoutParams2.leftMargin + (width2 / 2)
                    val i8: Int = layoutParams2.topMargin + (height2 / 2)
                    layoutParams2.width = (width2 * 0.9).toInt()
                    layoutParams2.height = (height2 * 0.9).toInt()
                    layoutParams2.setMargins(
                        i7 - (layoutParams2.width / 2),
                        i8 - (layoutParams2.height / 2),
                        i7 + (layoutParams2.width / 2),
                        i8 + (layoutParams2.height / 2)
                    )
                    imageView2.setLayoutParams(layoutParams2)
                    return
                }
                return
            }

            R.id.iv_depth_add -> {
                val i9: Int = this.cutDepth
                if (i9 < 15) {
                    this.cutDepth = i9 + 1
                    binding!!.tvDepthValue.text = (this.cutDepth / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_depth_reduce -> {
                val i10: Int = this.cutDepth
                if (i10 > 1) {
                    this.cutDepth = i10 - 1
                    binding!!.tvDepthValue.text = (this.cutDepth / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_down -> {
                val view4: View? = this.currentView
                if (view4 != null) {
                    val bottom: Int = view4.bottom + 5
                    Log.i(TAG, "bottom: " + bottom)
                    if (bottom > binding!!.flContainer.measuredHeight - binding!!.flContainer.paddingBottom) {
                        return
                    }
                    Log.i(
                        TAG,
                        "getWidth: " + currentView!!.width + "-getHeight: " + currentView!!.height
                    )
                    val view5: View? = this.currentView
                    view5!!.layout(
                        view5.left,
                        currentView!!.top + 5,
                        currentView!!.right, bottom
                    )
                    val marginLayoutParams: MarginLayoutParams =
                        currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.setLayoutParams(marginLayoutParams)
                    return
                }
                if (binding!!.flContainer.childCount > 0) {
                    changeCurrentView(binding!!.flContainer.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_left -> {
                val view6: View? = this.currentView
                if (view6 != null) {
                    val left: Int = view6.left - 5
                    if (left < 0) {
                        return
                    }
                    val view7: View? = this.currentView
                    view7!!.layout(
                        left, view7.top,
                        currentView!!.right - 5,
                        currentView!!.bottom
                    )
                    val marginLayoutParams2: MarginLayoutParams =
                        currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams2.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.setLayoutParams(marginLayoutParams2)
                    return
                }
                if (binding!!.flContainer.childCount > 0) {
                    changeCurrentView(binding!!.flContainer.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_right -> {
                val view8: View? = this.currentView
                if (view8 != null) {
                    val right: Int = view8.right + 5
                    if (right > (binding!!.flContainer.measuredWidth - binding!!.flContainer.getPaddingRight())) {
                        return
                    }
                    val view9: View? = this.currentView
                    view9!!.layout(
                        view9.left + 5,
                        currentView!!.top, right,
                        currentView!!.bottom
                    )
                    val marginLayoutParams3: MarginLayoutParams =
                        currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams3.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.setLayoutParams(marginLayoutParams3)
                    return
                }
                if (binding!!.flContainer.childCount > 0) {
                    changeCurrentView(binding!!.flContainer.getChildAt(0))
                    return
                }
                return
            }

            R.id.iv_up -> {
                val view10: View? = this.currentView
                if (view10 != null) {
                    val top: Int = view10.top - 5
                    if (top < 0) {
                        return
                    }
                    val view11: View? = this.currentView
                    view11!!.layout(
                        view11.left, top,
                        currentView!!.right,
                        currentView!!.bottom - 5
                    )
                    val marginLayoutParams4: MarginLayoutParams =
                        currentView!!.layoutParams as MarginLayoutParams
                    marginLayoutParams4.setMargins(
                        currentView!!.left,
                        currentView!!.top, 0, 0
                    )
                    currentView!!.setLayoutParams(marginLayoutParams4)
                    return
                }
                if (binding!!.flContainer.childCount > 0) {
                    changeCurrentView(binding!!.flContainer.getChildAt(0))
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
                startForResult(UsbImageSelectFragment.newInstance(), 1)
                return
            }

            R.id.tv_add_text -> {
                showAddTextDialog()
                return
            }

            R.id.tv_delete -> {
                val view12: View? = this.currentView
                if (view12 != null) {
                    binding!!.flContainer.removeView(view12)
                    if (binding!!.flContainer.childCount > 0) {
                        changeCurrentView(binding!!.flContainer.getChildAt(0))
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
                if (binding!!.flContainer.childCount == 0) {
                    return
                }
                showSaveTemplateDialog()
                return
            }

            R.id.tv_select_template -> {
                startForResult(TemplateSelectFragment.newInstance(), 2)
                return
            }

            else -> {}
        }
    }

    private fun showSaveTemplateDialog() {
        val editDialog = EditDialog(requireContext())
        editDialog.setEditTextContent("")
        editDialog.setTip(getString(R.string.enter_remarks))
        editDialog.setDialogBtnCallback { str ->
            this@KeyMarkingFragment.saveTemplate(
                str
            )
        }
        editDialog.setContentNullable(true)
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: saveTemplate, reason: merged with bridge method [inline-methods] */
    fun saveTemplate(str: String?) {
        val keyMarkingTemplate = KeyMarkingTemplate()
        keyMarkingTemplate.width = ErrorCode.RiskCutClampDownFaceS1a
        keyMarkingTemplate.height = ErrorCode.RiskCutClampDownFaceS1a
        keyMarkingTemplate.description = str
        keyMarkingTemplate.time = Date().time
        UserDataDaoManager.getInstance(requireContext()).saveKeyMarkingTemplate(keyMarkingTemplate)
        for (i in 0 until binding!!.flContainer.childCount) {
            val childAt: View = binding!!.flContainer.getChildAt(i)
            val keyMarkingChild = KeyMarkingChild()
            if (childAt is TextView) {
                val trim: String = childAt.getText().toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(trim)) {
                    val textSize: Float = childAt.textSize
                    keyMarkingChild.type = 1
                    keyMarkingChild.fontSize = textSize
                    keyMarkingChild.text = trim
                    keyMarkingChild.marginLeft = childAt.getLeft()
                    keyMarkingChild.marginTop = childAt.getTop()
                    keyMarkingChild.parentId = keyMarkingTemplate.id
                    UserDataDaoManager.getInstance(requireContext())
                        .saveKeyMarkingTemplateChild(keyMarkingChild)
                }
            } else {
                val imageView: ImageView = childAt as ImageView
                keyMarkingChild.type = 2
                keyMarkingChild.imageByte = BitmapUtil.bitmapToByte((imageView.getDrawable() as BitmapDrawable).bitmap)
                keyMarkingChild.width = imageView.width
                keyMarkingChild.height = imageView.height
                keyMarkingChild.marginLeft = imageView.left
                keyMarkingChild.marginTop = imageView.top
                keyMarkingChild.parentId = keyMarkingTemplate.id
                UserDataDaoManager.getInstance(requireContext())
                    .saveKeyMarkingTemplateChild(keyMarkingChild)
            }
        }
        ToastUtil.showToast(R.string.saved_successfully)
    }

    private fun getImageViewFromFile(file: File): ImageView {
        val imageView = ImageView(requireContext())
        val options: BitmapFactory.Options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.path, options)
        val i: Int = options.outWidth
        val i2: Int = options.outHeight
        val f2: Float = (i.toFloat()) / 302.0f
        val f4: Float = (i2.toFloat()) / 302.0f
        if (f2 <= 1.0f && f4 <= 1.0f) {
            options.inJustDecodeBounds = false
            val decodeFile: Bitmap = BitmapFactory.decodeFile(file.path, options)
            val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(i, i2)
            layoutParams.setMargins(
                (302 - i) / 2,
                (302 - i2) / 2,
                (i + ErrorCode.RiskCutClampDownFaceS1a) / 2,
                (i2 + ErrorCode.RiskCutClampDownFaceS1a) / 2
            )
            imageView.setLayoutParams(layoutParams)
            imageView.setImageBitmap(decodeFile)
        } else {
            options.inJustDecodeBounds = false
            val max: Float = max(f2.toDouble(), f4.toDouble()).toFloat()
            options.inSampleSize = max.toInt()
            val scaleBitmap: Bitmap =
                BitmapUtil.scaleBitmap(BitmapFactory.decodeFile(file.path, options), max)
            val i3: Int = (i.toFloat() / max).toInt()
            val i4: Int = (i2.toFloat() / max).toInt()
            val layoutParams2: FrameLayout.LayoutParams = FrameLayout.LayoutParams(i3, i4)
            layoutParams2.setMargins(
                (302 - i3) / 2,
                (302 - i4) / 2,
                (i3 + ErrorCode.RiskCutClampDownFaceS1a) / 2,
                (i4 + ErrorCode.RiskCutClampDownFaceS1a) / 2
            )
            imageView.setLayoutParams(layoutParams2)
            imageView.setImageBitmap(scaleBitmap)
        }
        return imageView
    }

    private fun detectCutterHigh() {
        addDisposable(Observable.fromCallable({ this.markingRectRealScreen!! })
            .subscribeOn(
                Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread()).map { rect -> this.getScreenBitmap(rect) }.observeOn(Schedulers.io()).map { bitmap ->
                val engraveParam: EngravePathGen.EngraveParam =
                    engraveParam
                this@KeyMarkingFragment.engraveingBitmap = bitmap
                EngravePathGen.detectCutterHigh(bitmap, engraveParam)
            }.subscribe({ list ->
                OperationManager.getInstance().start(
                    this@KeyMarkingFragment.dataParam, list, OperateType.ENGRAVE_CUT_CUTTER_HIGH
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
                Observable.fromCallable({
                    EngravePathGen.bitmapToPath(
                        this.engraveingBitmap,
                        engraveParam
                    )
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).observeOn(
                    Schedulers.io()
                ).subscribe({ list ->
                        if (ClampManager.getInstance().checkHaveRiskCutClamp(list, ToolSizeManager.getCutterSize())) {
                            return@subscribe
                        }
                        OperationManager.getInstance().start(
                            this@KeyMarkingFragment.dataParam,
                            list,
                            OperateType.ENGRAVE_CUT_EXECUTE)
                    }, { dismissLoadingDialog() })

            )
        }
    }

    val engraveParam: EngravePathGen.EngraveParam
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            return EngravePathGen.EngraveParam(
                ClampManager.getInstance().getS5().x,
                ClampManager.getInstance().getS5().y,
                origin.x * 10,
                origin.y * 10,
                ClampManager.getInstance().getDC().getxDistance(),
                ClampManager.getInstance().getDC().getyDistance(),
                OperationManager.getInstance().keyAlignInfo.keyFace,
                ((if (!TextUtils.isEmpty(
                        binding!!.tvDepthValue.getText().toString().trim({ it <= ' ' })
                    )
                ) Math.round(
                    binding!!.tvDepthValue.getText().toString().replace("mm", "").toFloat() * 100.0f
                ) else 5) / MachineData.dZScale).toInt(),
                SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15)
            )
        }

    /* JADX INFO: Access modifiers changed from: private */
    fun getScreenBitmap(rect: Rect?): Bitmap {
        kmfl!!.setMarkLineVisible(false)
        val decorView: View = activity!!.window.decorView
        decorView.setDrawingCacheEnabled(true)
        decorView.buildDrawingCache()
        val createBitmap: Bitmap = Bitmap.createBitmap(
            decorView.drawingCache,
            rect!!.left,
            rect.top,
            rect.width(),
            rect.height()
        )
        decorView.setDrawingCacheEnabled(false)
        kmfl!!.setMarkLineVisible(true)
        return createBitmap
    }

    private val markingRectRealScreen: Rect?
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            val rect = Rect(Int.MAX_VALUE, Int.MAX_VALUE, 0, 0)
            val childCount: Int = binding!!.flContainer.childCount
            if (childCount == 0) {
                return null
            }
            for (i in 0 until childCount) {
                val childAt: View = binding!!.flContainer.getChildAt(i)
                val iArr = IntArray(2)
                childAt.getLocationOnScreen(iArr)
                rect.left = min(rect.left.toDouble(), iArr[0].toDouble()).toInt()
                rect.top = min(rect.top.toDouble(), iArr[1].toDouble()).toInt()
                rect.right =
                    max(rect.right.toDouble(), (iArr[0] + childAt.width).toDouble())
                        .toInt()
                rect.bottom =
                    max(rect.bottom.toDouble(), (iArr[1] + childAt.height).toDouble())
                        .toInt()
            }
            val iArr2 = IntArray(2)
            binding!!.flContainer.getLocationInWindow(iArr2)
            origin.x = (abs((rect.left - iArr2[0]).toDouble()) + 30).toInt()
            origin.y = (abs((rect.top - iArr2[1]).toDouble()) + 30).toInt()
            return rect
        }

    private fun showEditDialog() {
        val view: View? = this.currentView
        if (view !is TextView) {
            return
        }
        val editDialog = EditDialog(requireContext())

        editDialog.setDialogBtnCallback { str ->
            (currentView as TextView?)!!.text = str
        }
        editDialog.setTip(getString(R.string.edit_text))
        editDialog.setEditTextContent(
            (currentView as TextView?)!!.getText().toString().trim { it <= ' ' }
        )
        if (editDialog.isShowing) {
            return
        }
        editDialog.show()
    }

    private fun showAddTextDialog() {
        val editDialog = EditDialog(requireContext())

        editDialog.setDialogBtnCallback { str: String? ->
            this.addText(
                str
            )
        }
        editDialog.setEditTextContent("")
        editDialog.setTip(getString(R.string.add_text))
        if (editDialog.isShowing) {
            return
        }
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: addText, reason: merged with bridge method [inline-methods] */
    fun addText(str: String?) {
        val alignTextView = AlignTextView(requireContext())
        if (Build.VERSION.SDK_INT >= 21) {
            alignTextView.elevation = 1.0f
        }
        alignTextView.includeFontPadding = false
        alignTextView.setTextColor(SupportMenu.CATEGORY_MASK)
        alignTextView.textSize = 30.0f
        alignTextView.text = str
        alignTextView.letterSpacing = 0.05f
        alignTextView.includeFontPadding = false
        val onDragTouchListener = OnDragTouchListener(
            binding!!.flContainer.width,
            binding!!.flContainer.height
        )
        onDragTouchListener.onDraggableClickListener = this.onDraggableClickListener
        alignTextView.setOnTouchListener(onDragTouchListener)
        binding!!.flContainer.addView(alignTextView, FrameLayout.LayoutParams(-2, -2))
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
            val imageViewFromFile: ImageView = getImageViewFromFile(file!!)
            binding!!.flContainer.addView(imageViewFromFile)
            val onDragTouchListener = OnDragTouchListener(
                binding!!.flContainer.width,
                binding!!.flContainer.height
            )
            onDragTouchListener.onDraggableClickListener = this.onDraggableClickListener
            imageViewFromFile.setOnTouchListener(onDragTouchListener)
            this.currentView = imageViewFromFile
            return
        }
        if (i != 2 || bundle == null) {
            return
        }
        val keyMarkingTemplate: KeyMarkingTemplate? =
            bundle.getSerializable("template") as KeyMarkingTemplate?
        binding!!.flContainer.removeAllViews()
        for (keyMarkingChild: KeyMarkingChild in keyMarkingTemplate!!.getChildrenBeanList()) {
            if (keyMarkingChild.type == 1) {
                val alignTextView = AlignTextView(context)
                if (Build.VERSION.SDK_INT >= 21) {
                    alignTextView.elevation = 1.0f
                }
                alignTextView.setTextSize(0, keyMarkingChild.fontSize)
                alignTextView.text = keyMarkingChild.text
                alignTextView.includeFontPadding = false
                alignTextView.letterSpacing = 0.05f
                alignTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK)
                val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(-2, -2)
                layoutParams.setMargins(
                    keyMarkingChild.marginLeft + keyMarkingTemplate.marginLeft,
                    keyMarkingChild.marginTop + keyMarkingTemplate.marginLeft,
                    0,
                    0
                )
                binding!!.flContainer.addView(alignTextView, layoutParams)
                val onDragTouchListener2 = OnDragTouchListener(
                    binding!!.flContainer.width,
                    binding!!.flContainer.height
                )
                onDragTouchListener2.onDraggableClickListener = this.onDraggableClickListener
                alignTextView.setOnTouchListener(onDragTouchListener2)
            } else {
                val imageView = ImageView(context)
                imageView.setImageBitmap(BitmapUtil.bytes2Bimap(keyMarkingChild.imageByte))
                val layoutParams2: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    keyMarkingChild.width,
                    keyMarkingChild.height
                )
                layoutParams2.setMargins(
                    keyMarkingChild.marginLeft,
                    keyMarkingChild.marginTop,
                    0,
                    0
                )
                binding!!.flContainer.addView(imageView, layoutParams2)
                val onDragTouchListener3 = OnDragTouchListener(
                    binding!!.flContainer.width,
                    binding!!.flContainer.height
                )
                onDragTouchListener3.onDraggableClickListener = this.onDraggableClickListener
                imageView.setOnTouchListener(onDragTouchListener3)
            }
        }
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible) {
            val eventCode: Int = eventCenter.eventCode
            if (eventCode != 47) {
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
            showLoadingDialog(eventCenter.data.toString() + "%", true)
        }
    }

    private fun showError(eventCenter: EventCenter<*>) {
        dismissLoadingDialog()
        showErrorDialog(context, eventCenter.data as ErrorBean?)
    }

    private fun handleOperationFinish(eventCenter: EventCenter<*>) {
        val operateType: OperateType = eventCenter.data as OperateType
        if (operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            val abs: Int = (abs(
                ((ClampManager.getInstance().getS5().x - OperationManager.getInstance()
                    .keyAlignInfo.right) * MachineData.dXScale).toDouble()
            )
                .toInt()) + 700
            this.rightSpace = abs
            val i: Int = (abs / 10) - 30
            val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(i, -1)
            layoutParams.setMargins(75, 75, 0, 10)
            binding!!.flContainer.setLayoutParams(layoutParams)
            kmfl!!.showBorder(this.rightSpace / 10)
            if (this.decode) {
                dismissLoadingDialog()
                this.decode = false
                return
            }
            if (binding!!.cbAutoCenter.isChecked) {
                val childCount: Int = binding!!.flContainer.childCount
                var i2: Int = Int.MAX_VALUE
                var i3: Int = 0
                for (i4 in 0 until childCount) {
                    val childAt: View = binding!!.flContainer.getChildAt(i4)
                    i2 = min(i2.toDouble(), childAt.left.toDouble()).toInt()
                    i3 = max(i3.toDouble(), (childAt.left + childAt.width).toDouble())
                        .toInt()
                }
                if (i3 - i2 <= i) {
                    val i5: Int = ((this.rightSpace / 10) + 30) / 2
                    Log.i(TAG, "centerKey: " + i5)
                    val i6: Int = (i2 + i3) / 2
                    Log.i(TAG, "centerContent: " + i6)
                    var i7: Int = i5 - i6
                    for (i8 in 0 until childCount) {
                        val childAt2: View = binding!!.flContainer.getChildAt(i8)
                        if (childAt2.left + i7 < 0) {
                            i7 = 0 - childAt2.left
                        }
                        childAt2.layout(
                            (childAt2.left + i7) - 30,
                            childAt2.top,
                            (childAt2.right + i7) - 30,
                            childAt2.bottom
                        )
                        val marginLayoutParams: MarginLayoutParams =
                            childAt2.layoutParams as MarginLayoutParams
                        marginLayoutParams.setMargins(childAt2.left, childAt2.top, 0, 0)
                        childAt2.setLayoutParams(marginLayoutParams)
                        val onDragTouchListener = OnDragTouchListener(
                            layoutParams.width,
                            binding!!.flContainer.height
                        )
                        onDragTouchListener.onDraggableClickListener = this.onDraggableClickListener
                        childAt2.setOnTouchListener(onDragTouchListener)
                    }
                } else {
                    for (i9 in 0 until childCount) {
                        val childAt3: View = binding!!.flContainer.getChildAt(i9)
                        if (childAt3.width > i) {
                            val marginLayoutParams2: MarginLayoutParams =
                                childAt3.layoutParams as MarginLayoutParams
                            marginLayoutParams2.width = i
                            marginLayoutParams2.setMargins(0, childAt3.top, 0, 0)
                            childAt3.setLayoutParams(marginLayoutParams2)
                        } else {
                            val left: Int = childAt3.left + childAt3.width
                            if (left > i) {
                                val i10: Int = left - i
                                childAt3.layout(
                                    childAt3.left - i10,
                                    childAt3.top,
                                    childAt3.right - i10,
                                    childAt3.bottom
                                )
                                val marginLayoutParams3: MarginLayoutParams =
                                    childAt3.layoutParams as MarginLayoutParams
                                marginLayoutParams3.setMargins(
                                    childAt3.left,
                                    childAt3.top,
                                    0,
                                    0
                                )
                                childAt3.setLayoutParams(marginLayoutParams3)
                            }
                        }
                        val onDragTouchListener2 = OnDragTouchListener(
                            layoutParams.width,
                            binding!!.flContainer.height
                        )
                        onDragTouchListener2.onDraggableClickListener = this.onDraggableClickListener
                        childAt3.setOnTouchListener(onDragTouchListener2)
                    }
                }
            }
            detectCutterHigh()
            return
        }
        if (operateType == OperateType.ENGRAVE_CUT_CUTTER_HIGH) {
            startEngraving()
        } else if (operateType == OperateType.ENGRAVE_CUT_EXECUTE) {
            addDisposable(
                Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                    .observeOn(
                        AndroidSchedulers.mainThread()
                    ).subscribe({ this.dismissLoadingDialog() }, { dismissLoadingDialog() })
            )
            showLoadingDialog("100%", true)
        }
    }

    companion object {
        private val FRAME_HEITHT: Int = 302
        private val FRAME_WIDTH: Int = 302
        private val LEFT_SPACE: Int = 0
        private val SELECT_IMAGE: Int = 1
        private val SELECT_TEMPLATE: Int = 2

        fun newInstance(): KeyMarkingFragment {
            val bundle = Bundle()
            val keyMarkingFragment = KeyMarkingFragment()
            keyMarkingFragment.setArguments(bundle)
            return keyMarkingFragment
        }

        fun substring(str: String, i: Int, i2: Int): String? {
            if (i > str.length) {
                return null
            }
            if (i2 > str.length) {
                return str.substring(i)
            }
            return str.substring(i, i2)
        }
    }
}
