package com.kkkcut.e20j.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.cutting.machine.Command
import com.cutting.machine.CuttingMachine
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.error.ErrorCode
import com.cutting.machine.operation.cut.DataParam
import com.cutting.machine.utils.KeyDataUtils
import com.kkkcut.e20j.DbBean.BittingCode
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.KeyBasicData
import com.kkkcut.e20j.DbBean.KeyBasicDataItem
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.LogUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.AngleKeyStep
import com.kkkcut.e20j.bean.Bitt
import com.kkkcut.e20j.bean.CodeAndTooth
import com.kkkcut.e20j.bean.eventbus.InputFinishBean
import com.kkkcut.e20j.customView.MarqueeTextView
import com.kkkcut.e20j.customView.drawKeyImg.AngleKey
import com.kkkcut.e20j.customView.drawKeyImg.DimpleKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleInsideGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleOutsideKey
import com.kkkcut.e20j.customView.drawKeyImg.Key
import com.kkkcut.e20j.customView.drawKeyImg.Side3KsKey
import com.kkkcut.e20j.customView.drawKeyImg.SideToothKey
import com.kkkcut.e20j.customView.drawKeyImg.SigleInsideGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.SingleKey
import com.kkkcut.e20j.customView.drawKeyImg.SingleOutGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.TubularKey
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.dao.ToothCodeDaoManager
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.SizeAdjustFragment
import com.kkkcut.e20j.ui.activity.LookPicActivity.Companion.start
import com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog
import com.kkkcut.e20j.ui.dialog.ChooseBittingNumbersDialog
import com.kkkcut.e20j.ui.dialog.CutDialog
import com.kkkcut.e20j.ui.dialog.DecodeDialog
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampSwitchPagerAdapter
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentCutKeyBinding
import com.kkkcut.e20j.utils.CutCountHelper
import com.kkkcut.e20j.utils.DatabaseFileUtils
import com.kkkcut.e20j.utils.ResUpdateUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import java.util.Locale
import java.util.Random
import java.util.TreeMap
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.cos

/* loaded from: classes.dex */
class KeyOperateFragment : BaseBackFragment() {


    companion object {
        private val ANGLE_KEY_INIT: String = "ANGLE_KEY_INIT"
        private val ANGLE_KEY_TURN_OVER: String = "ANGLE_KEY_TURN_OVER"
        private val KEYINFO: String = "keyinfo"
        val KEY_DATA: String = "keyData"
        val TAG: String = "KeyOperateFragment"
        // 1308

        fun incrementAngleCut(keyOperateFragment: KeyOperateFragment): Int {
            val i: Int = keyOperateFragment.angleKeyCutIndex
            keyOperateFragment.angleKeyCutIndex = i + 1
            return i
        }

        // 1310
        fun decrementAngleCut(keyOperateFragment: KeyOperateFragment): Int {
            val i: Int = keyOperateFragment.angleKeyCutIndex
            keyOperateFragment.angleKeyCutIndex = i - 1
            return i
        }

        // 608
        fun incrementDimpleCut(keyOperateFragment: KeyOperateFragment): Int {
            val i: Int = keyOperateFragment.dimpleCutIndex
            keyOperateFragment.dimpleCutIndex = i + 1
            return i
        }

        @JvmStatic
        fun newInstance(goOperatBean: GoOperatBean?): KeyOperateFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(KEY_DATA, goOperatBean)
            val keyOperateFragment: KeyOperateFragment = KeyOperateFragment()
            keyOperateFragment.setArguments(bundle)
            return keyOperateFragment
        }

        fun newInstance(): KeyOperateFragment {
            return KeyOperateFragment()
        }
    }

    private var angleKeyCutIndex: Int = 0
    private var angleKeyDepthCount: Int = 0
    private var angleKeySteps: List<AngleKeyStep>? = null
    private var bittingCodes: ArrayList<CodeAndTooth?>? = null


    var binding: FragmentCutKeyBinding? = null
    private var clearClampRemind: RemindDialog? = null
    private var cutCount: Int = 0
    private var dimpleCutIndex: Int = 0
    private var doorIgnition: Boolean = false
    private var doorToIgnition: Boolean = false

    private var isBarCodeScan: Boolean = false
    private var isSibling: Boolean = false


    private var ki: KeyInfo? = null
    private var lastClamp: ClampBean? = null


    private var mKey: Key? = null
    private var mainKeyID: Int = 0
    private var mainKeyToothCode: String? = null

    var mtv: MarqueeTextView? = null
    var onPageChangeListener: OnPageChangeListener? = null
    private var rounding: Boolean = false
    private var sideKeyToothCode: String? = null
    private var toothCodeLack: String? = null
    private var toothCount: Int = 0

    private var moveToRight: Boolean = true
    private var dataParam: DataParam? = DataParam()
    private val toothCodeMap = HashMap<Int, String>()
    private val bittList: ArrayList<Bitt> = ArrayList()

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentCutKeyBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_cut_key
    }

    val keyData: GoOperatBean?
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            return arguments!!.getParcelable(Companion.KEY_DATA)
        }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initKey(keyData!!.keyID)
        val codeSeries: String? = keyData!!.codeSeries
        if (!TextUtils.isEmpty(codeSeries)) {
            binding!!.tvSeries.text = codeSeries
        }
        val title: String? = keyData!!.title
        binding!!.tvHint.text = title
        this.isBarCodeScan = title!!.contains("Bar Code")
        checkKeyCollected()
        if (!keyData!!.isCustomkey) {
            ResUpdateUtils.showKeyImage(
                context, keyData!!.keyID,
                binding!!.ivRealKey
            )
            checkCodeDatabaseExist(keyData!!.seriesID, keyData!!.keyID)
        } else {
            binding!!.tvInfo.visibility = View.GONE
            binding!!.tvCodeFindTooth.visibility = View.GONE
            binding!!.tvLackTooth.visibility = View.GONE
        }
    }

    private fun checkCodeDatabaseExist(i: Int, i2: Int) {
        addDisposable(
            Observable.fromCallable {
                if (i > 0) {
                    KeyInfoDaoManager.getInstance().isChineseCar(i)
                }
                else {
                    false
                }
            }.map { DatabaseFileUtils.dataExist(i2.toString()) }
                .subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread()).subscribe({ bool: Boolean ->
                    if (bool) {
                        binding!!.tvLackTooth.visibility = View.VISIBLE
                        binding!!.tvCodeFindTooth.visibility = View.VISIBLE
                    } else {
                        binding!!.tvLackTooth.visibility = View.GONE
                        binding!!.tvCodeFindTooth.visibility = View.GONE
                    }
                }, { dismissLoadingDialog() })
        )
    }

    private fun initKey(i: Int) {
        addDisposable(getKeyInfoDisposable(
            i,
            keyData!!.isCustomkey
        ).subscribeOn(Schedulers.io()).doOnSubscribe {
            showLoadingDialog(getString(R.string.waitting));
        }.doFinally {

            dismissLoadingDialog();

        }.subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { keyInfo ->
                    initKeyinfo(keyInfo);
                    initKeyView(keyInfo);
                    initClamp(keyInfo);
                    initParam(keyInfo);
                    initSiblingKey(keyInfo);
                    initButton(keyInfo);
                    if (keyData!!.isCustomkey) {
                        return@subscribe
                    }
                    this.binding!!.tvBlank.setText(keyInfo.keyBlanks);
                    initRemind(keyInfo);
                }, { obj ->
                    ToastUtil.showToast(obj!!.message)
                })
        )
    }
    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initKey$0$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun `m29lambda$initKey$0$comkkkcute20juifragmentKeyOperateFragment`(
        disposable: Disposable?
    ) {
        showLoadingDialog(getString(R.string.waitting))
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initKey$1$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun `m30lambda$initKey$1$comkkkcute20juifragmentKeyOperateFragment`() {
        dismissLoadingDialog()
    }

   fun keyOperateFragment(
        keyInfo: KeyInfo
    ) {
        initKeyinfo(keyInfo)
        initKeyView(keyInfo)
        initClamp(keyInfo)
        initParam(keyInfo)
        initSiblingKey(keyInfo)
        initButton(keyInfo)
        if (keyData!!.isCustomkey) {
            return
        }
        binding!!.tvBlank.text = keyInfo.keyBlanks
        initRemind(keyInfo)
    }

    private fun getKeyInfoDisposable(i: Int, z: Boolean): Observable<KeyInfo> {
        if (z) {
            return Observable.fromCallable {
                UserDataDaoManager.getInstance(context).getCustomKeyByID(i)
            }
                .map { obj: Any ->
                    val keyInfo: KeyInfo = (obj as CustomKey).toKeyInfo()
                    keyInfo
                }
        }
        return Observable.fromCallable {
            val basicData: KeyBasicData = KeyInfoDaoManager.getInstance().getBasicData(i)
            basicData
        }.map { obj: KeyBasicData ->
            val keyInfo: KeyInfo = obj.toKeyInfo()
            keyInfo
        }
    }

    private fun initRemind(keyInfo: KeyInfo) {
        val desc: String = keyInfo.desc
        if (TextUtils.isEmpty(desc)) {
            return
        }
        mtv!!.text = desc
    }

    private fun initButton(keyInfo: KeyInfo) {
        this.angleKeyDepthCount =
            ki!!.depthName.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().size
        if (keyInfo.type == 7) {
            binding!!.btDecode.visibility = View.GONE
        }
        if (keyInfo.spaceStr.split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray().size > 4
        ) {
            binding!!.btDecode.visibility = View.GONE
            binding!!.btCut.visibility = View.GONE
        }
    }

    private fun initKeySide(i: Int) {
        addDisposable(
            Observable.fromCallable {
                val basicDataSide: KeyBasicDataItem =
                    KeyInfoDaoManager.getInstance().getBasicDataSide(i)
                basicDataSide
            }.map { obj: KeyBasicDataItem ->
                val keyInfo: KeyInfo
                keyInfo = obj.toKeyInfo()
                keyInfo
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
              { obj: KeyInfo ->
                    this@KeyOperateFragment.m32xce3e71f(obj)
                }, { dismissLoadingDialog() }
            )
        )
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initKeySide$10$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun m32xce3e71f(keyInfo: KeyInfo) {
        initKeyinfo(keyInfo)
        initKeyView(keyInfo)
        initSiblingKey(keyInfo)
        initClamp(keyInfo)
        initCutDepth(keyInfo)
    }

    private fun initKeyinfo(keyInfo: KeyInfo) {
        this.ki = keyInfo
        val keyData: GoOperatBean? = keyData
        val isDoorIgnition: Boolean = keyData!!.isDoorIgnition()
        if (isDoorIgnition) {
            this.doorIgnition = isDoorIgnition
            this.doorToIgnition = keyData.isDoorToIgnition()
            this.sideKeyToothCode = keyData.toothCode
        }
        if (TextUtils.isEmpty(ki!!.keyComb)) {
            if (!this.isSibling) {
                if (!TextUtils.isEmpty(this.mainKeyToothCode)) {
                    ki!!.setKeyToothCode(this.mainKeyToothCode)
                } else {
                    ki!!.setKeyToothCode(keyData.toothCode)
                }
            } else {
                if (TextUtils.isEmpty(this.sideKeyToothCode)) {
                    this.sideKeyToothCode = this.keyData!!.toothCodeSide
                }
                ki!!.setKeyToothCode(this.sideKeyToothCode)
            }
        } else if (!TextUtils.isEmpty(
                toothCodeMap.get(
                    ki!!.icCard
                )
            )
        ) {
            val keyInfo2: KeyInfo? = this.ki
            keyInfo2!!.setKeyToothCode(toothCodeMap[keyInfo2.icCard])
        } else {
            val split: Array<String> =
                ki!!.keyComb.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            if (ki!!.icCard == split[0].split("-".toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()[1].toInt()
            ) {
                toothCodeMap[ki!!.icCard] = this.keyData!!.toothCode!!
                ki!!.setKeyToothCode(this.keyData!!.toothCode)
            }
            if (ki!!.icCard == split.get(1).split("-".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(1)
                    .toInt() || ki!!.icCard == split.get(2).split("-".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(1).toInt()
            ) {
                val toothCodeSide: String? = this.keyData!!.toothCodeSide
                if (!TextUtils.isEmpty(toothCodeSide)) {
                    for (str: String in toothCodeSide!!.split("\\|".toRegex())
                        .dropLastWhile { it.isEmpty() }.toTypedArray()) {
                        val split2: Array<String> =
                            str.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if (split2[0].toInt() == ki!!.icCard) {
                            toothCodeMap[split2[0].toInt()] = split2[1]
                            ki!!.setKeyToothCode(split2[1])
                        }
                    }
                }
            }
        }
        ki!!.seriesID = keyData.seriesID
        ki!!.codeSeries = keyData.codeSeries
        ki!!.cuts = keyData.cuts
        ki!!.title = keyData.title
        ki!!.keyChinaNum = keyData.KeyChinaNum
        val variableSpace: String = ki!!.variableSpace
        val desc: String = ki!!.desc
        if (!this.keyData!!.isCustomkey && (!TextUtils.isEmpty(variableSpace) || !TextUtils.isEmpty(
                desc
            ))
        ) {
            ChooseBittingNumbersDialog(context, variableSpace, desc).show()
        }
        dataParam!!.setKeyInfo(keyInfo)
        if (MachineInfo.isE20Us(context) && (keyInfo.icCard == 252 || keyInfo.icCard == 894)) {
            showAngleKeyBlankRemind()
        }
        if ((ki!!.type == 7) || (ki!!.type == 8) || ki!!.isDimpleMotherSonKey) {
            binding!!.tvAdjust.visibility = View.GONE
        }
    }

    fun showAngleKeyBlankRemind() {
        val remindDialog: RemindDialog = RemindDialog(context)
        remindDialog.show()
        remindDialog.setCancleBtnVisibility(false)
        remindDialog.setRemindImg(R.drawable.angle_key_remind)
    }

    private fun initSiblingKey(keyInfo: KeyInfo) {
        if (keyData!!.isCustomkey) {
            binding!!.btChangeSibling.visibility = View.GONE
            return
        }
        if (TextUtils.isEmpty(keyInfo.siblingProfile) && !this.isSibling && (keyInfo.keyitemid == 0) && TextUtils.isEmpty(
                ki!!.keyComb
            )
        ) {
            binding!!.btChangeSibling.visibility = View.GONE
            return
        }
        binding!!.tvSide.visibility = View.VISIBLE
        if (!TextUtils.isEmpty(ki!!.keyComb)) {
            for (str: String in ki!!.keyComb.split(",".toRegex())
                .dropLastWhile({ it.isEmpty() }).toTypedArray()) {
                val split: Array<String> =
                    str.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                if (split.size > 1 && ki!!.icCard == split.get(1).toInt()) {
                    binding!!.tvSide.text = getString(R.string.side) + " " + split.get(0)
                }
            }
        } else if (!TextUtils.isEmpty(keyInfo.siblingProfile) || keyInfo.keyitemid != 0) {
            binding!!.tvSide.setText(R.string.a_side)
        } else {
            binding!!.tvSide.setText(R.string.b_side)
        }
        if (TextUtils.isEmpty(ki!!.readBittingRule) || !("3" == ki!!.readBittingRule)) {
            return
        }
        this.cutCount = 0
    }

    private fun initCutter(keyInfo: KeyInfo) {
        var cutterSize: Int = keyInfo.cutterSize
        if (cutterSize == 0) {
            if (ki!!.type == 5) {
                if (ki!!.getGroove() != 0 && ki!!.getGroove() < 200) {
                    cutterSize = ki!!.getGroove()
                }
                cutterSize = 200
            } else if (ki!!.type == 0) {
                cutterSize = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200)
            } else {
                if (ki!!.type == 6) {
                    cutterSize = ToolSizeManager.DimpleCutterSize
                }
                cutterSize = 200
            }
        }
        dataParam!!.cutterSize = cutterSize
        ToolSizeManager.setCutterSize(cutterSize)
        if (ki!!.type == 6 || ki!!.type == 92) {
            if (MachineInfo.isE20Us(context)) {
                if (ki!!.isDimpleMotherSonKey) {
                    binding!!.tvCutterSize.text = "D&E cutter"
                    return
                } else {
                    binding!!.tvCutterSize.text = "C cutter"
                    return
                }
            }
            binding!!.tvCutterSize.setText(R.string.dimple_cutter)
            return
        }
        binding!!.tvCutterSize.text = String.format(Locale.US, "%.1fmm", cutterSize / 100.0f)
    }

    private fun initDecoder(keyInfo: KeyInfo) {
        var i: Int
        val decoder: String = keyInfo.decoder
        if (!TextUtils.isEmpty(decoder)) {
            if (decoder.contains(",")) {
                val split: Array<String> =
                    decoder.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                binding!!.tvDecoderSize.text = split.get(1) + "mm"
                i = (split.get(1).toFloat() * 100.0f).toInt()
                dataParam!!.decoderSize = i
                ToolSizeManager.setDecoderSize(i)
            }
        } else {
            binding!!.tvDecoderSize.text = "1.0mm"
        }
        i = 100
        dataParam!!.decoderSize = i
        ToolSizeManager.setDecoderSize(i)
    }

    private fun initParam(keyInfo: KeyInfo) {
        initDecoder(keyInfo)
        initCutter(keyInfo)
        initCutDepth(keyInfo)
    }

     fun initCutDepth(keyInfo: KeyInfo) {
        dataParam!!.cutDepth = keyInfo.cutDepth
    }

     fun initClamp(keyInfo: KeyInfo) {
        if (TextUtils.equals(
                keyInfo.clampBean.clampNum,
                "S1"
            ) && MachineInfo.isE20Standard(context)
        ) {
            var str: String? = if (TextUtils.equals(
                    keyInfo.clampBean.clampSide,
                    "A"
                )
            ) "Please make sure the key is fixed on S1-A" else ""
            if (TextUtils.equals(keyInfo.clampBean.clampSide, "C")) {
                str = "Make sure the key is fixed on S1-C"
            }
            if (!TextUtils.isEmpty(str)) {
                AlertDialog.Builder(context).setMessage(str)
                    .setPositiveButton(R.string.ok, null as DialogInterface.OnClickListener?).show()
            }
        }
        val clampBeanList = ClampCreator.getClampBeanList(keyInfo, this.lastClamp)
        ki!!.setClampKeyBasicData(clampBeanList!![0]!!.clampBean!!)
        binding!!.ivSwitchLast.setVisibility(View.GONE)
        if (clampBeanList.size > 1) {
            binding!!.ivSwitchNext.setVisibility(View.VISIBLE)
        } else {
            binding!!.ivSwitchNext.setVisibility(View.GONE)
        }
        binding!!.vpClamp.setAdapter(ClampSwitchPagerAdapter(clampBeanList, context!!))
        val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
            // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.4
            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageScrollStateChanged(i: Int) {
            }

            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageScrolled(i: Int, f: Float, i2: Int) {
            }

            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageSelected(i: Int) {
                ki!!.setClampKeyBasicData(clampBeanList[i]!!.clampBean)
                if (i == 0) {
                    binding!!.ivSwitchLast.setVisibility(View.GONE)
                    binding!!.ivSwitchNext.setVisibility(View.VISIBLE)
                } else if (i == clampBeanList.size - 1) {
                    binding!!.ivSwitchLast.setVisibility(View.VISIBLE)
                    binding!!.ivSwitchNext.setVisibility(View.GONE)
                } else {
                    binding!!.ivSwitchLast.setVisibility(View.VISIBLE)
                    binding!!.ivSwitchNext.setVisibility(View.VISIBLE)
                }
            }
        }
        this.onPageChangeListener = onPageChangeListener
        binding!!.vpClamp.addOnPageChangeListener(onPageChangeListener)
    }

    /* loaded from: classes.dex */
    private inner class MyPagerAdapter(var imgRes: IntArray) : PagerAdapter() {
        var imageViewList: MutableList<ImageView> = ArrayList()

        // androidx.viewpager.widget.PagerAdapter
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        init {
            for (i: Int in imgRes) {
                val imageView: ImageView = ImageView(this@KeyOperateFragment.context)
                imageView.setImageResource(i)
                imageView.setPadding(5, 5, 5, 5)
                imageViewList.add(imageView)
            }
        }

        // androidx.viewpager.widget.PagerAdapter
        override fun getCount(): Int {
            return imgRes.size
        }

        // androidx.viewpager.widget.PagerAdapter
        override fun destroyItem(viewGroup: ViewGroup, i: Int, obj: Any) {
            viewGroup.removeView(obj as View?)
        }

        // androidx.viewpager.widget.PagerAdapter
        override fun getPageWidth(i: Int): Float {
            if (imgRes.size > 1) {
                return 0.8f
            }
            return super.getPageWidth(i)
        }

        // androidx.viewpager.widget.PagerAdapter
        override fun instantiateItem(viewGroup: ViewGroup, i: Int): Any {
            val imageView: ImageView = imageViewList[i]
            viewGroup.addView(imageView)
            return (imageView)!!
        }

        fun getViewByPosition(i: Int): View {
            return imageViewList[i]
        }
    }

    private fun getClampImg(keyInfo: KeyInfo): IntArray? {
        if (keyInfo.icCard == 20131 || keyInfo.icCard == 1915) {
            return intArrayOf(R.drawable.car_clamp_d_tip_20131)
        }
        if (keyInfo.shoulderBlock == 1) {
            return intArrayOf(R.drawable.car_clamp_d_shoulder_6620131)
        }
        val clampBean: ClampBean = ki!!.clampBean
        if (("S1" == clampBean.clampNum)) {
            if (("A" == clampBean.clampSide)) {
                if (("0" == clampBean.clampSlot)) {
                    return if (keyInfo.align == 0) intArrayOf(
                        R.drawable.car_clamp_a_shoulder,
                        R.drawable.car_clamp_b_shoulder
                    ) else intArrayOf(
                        R.drawable.car_clamp_a_tip, R.drawable.car_clamp_b_tip
                    )
                }
                return null
            }
            if (("B" == clampBean.clampSide)) {
                return if (("0" == clampBean.clampSlot)) if (keyInfo.align == 0) intArrayOf(
                    R.drawable.car_clamp_b_shoulder, R.drawable.car_clamp_a_shoulder
                ) else intArrayOf(
                    R.drawable.car_clamp_b_tip, R.drawable.car_clamp_a_tip
                ) else if (keyInfo.align == 0) intArrayOf(
                    R.drawable.car_clamp_b_shoulder_side
                ) else intArrayOf(R.drawable.car_clamp_b_tip_side)
            }
            if (("C" == clampBean.clampSide)) {
                if (("0" == clampBean.clampSlot)) {
                    return if (keyInfo.align == 0) intArrayOf(
                        R.drawable.car_clamp_c_shoulder,
                        R.drawable.car_clamp_d_shoulder
                    ) else if (keyInfo.spaceStr.split(";".toRegex())
                            .dropLastWhile { it.isEmpty() }.toTypedArray().get(0)
                            .split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt() + ErrorCode.keyCuttingError > 3000
                    ) intArrayOf(
                        R.drawable.car_clamp_c_long_tip, R.drawable.car_clamp_d_tip
                    ) else intArrayOf(
                        R.drawable.car_clamp_c_tip, R.drawable.car_clamp_d_tip
                    )
                }
                return null
            }
            if (("D" == clampBean.clampSide) && ("0" == clampBean.clampSlot)) {
                return if (keyInfo.align == 0) intArrayOf(
                    R.drawable.car_clamp_d_shoulder,
                    R.drawable.car_clamp_c_shoulder
                ) else if (ki!!.icCard == 852) intArrayOf(
                    R.drawable.car_clamp_d_tip, R.drawable.sx9_clamp_side_a
                ) else if (ki!!.icCard == 1047) intArrayOf(
                    R.drawable.car_clamp_d_tip, R.drawable.sx9_clamp_side_b
                ) else intArrayOf(R.drawable.car_clamp_d_tip, R.drawable.car_clamp_c_tip)
            }
            return null
        }
        if (("S2" == clampBean.clampNum)) {
            return if (("A" == clampBean.clampSide)) if (keyInfo.align == 0) intArrayOf(
                R.drawable.singlekey_clamp_a_shoulder,
                R.drawable.singlekey_clamp_b_shoulder
            ) else intArrayOf(
                R.drawable.singlekey_clamp_a_tip, R.drawable.singlekey_clamp_b_tip
            ) else if (keyInfo.align == 0) intArrayOf(
                R.drawable.singlekey_clamp_b_shoulder, R.drawable.singlekey_clamp_a_shoulder
            ) else intArrayOf(
                R.drawable.singlekey_clamp_b_tip, R.drawable.singlekey_clamp_a_tip
            )
        }
        if (("S3" == clampBean.clampNum)) {
            if (("A" == clampBean.clampSide)) {
                return intArrayOf(R.drawable.tubular_clamp_s3_s7)
            }
            return null
        }
        if (("S4" == clampBean.clampNum)) {
            if (("A" == clampBean.clampSide)) {
                return intArrayOf(R.drawable.anglekey_clamp)
            }
            return null
        }
        if (("S6" == clampBean.clampNum)) {
            return if (("A" == clampBean.clampSide)) intArrayOf(
                R.drawable.sx9_clamp_side_a,
                R.drawable.car_clamp_d_tip
            ) else intArrayOf(
                R.drawable.sx9_clamp_side_b, R.drawable.car_clamp_d_tip
            )
        }
        return null
    }

    fun checkKeyCollected() {
        if (keyData!!.isCustomkey) {
            binding!!.tvCollect.visibility = View.GONE
        }
    }

    fun initKeyView(keyInfo: KeyInfo?) {
        binding!!.flKeyView.removeAllViews()
        val type: Int = keyInfo!!.type
        if (type != 92) {
            when (type) {
                0 -> {
                    val doubleKey: DoubleKey = DoubleKey(context, keyInfo)
                    this.mKey = doubleKey
                    binding!!.flKeyView.addView(doubleKey)
                }

                1 -> {
                    val singleKey: SingleKey = SingleKey(context, keyInfo)
                    this.mKey = singleKey
                    binding!!.flKeyView.addView(singleKey)
                }

                2 -> {
                    val doubleInsideGrooveKey: DoubleInsideGrooveKey =
                        DoubleInsideGrooveKey(context, keyInfo)
                    this.mKey = doubleInsideGrooveKey
                    binding!!.flKeyView.addView(doubleInsideGrooveKey)
                }

                3 -> {
                    val singleOutGrooveKey: SingleOutGrooveKey =
                        SingleOutGrooveKey(context, keyInfo)
                    this.mKey = singleOutGrooveKey
                    binding!!.flKeyView.addView(singleOutGrooveKey)
                }

                4 -> {
                    val doubleOutsideKey: DoubleOutsideKey = DoubleOutsideKey(context, keyInfo)
                    this.mKey = doubleOutsideKey
                    binding!!.flKeyView.addView(doubleOutsideKey)
                }

                5 -> {
                    val sigleInsideGrooveKey: SigleInsideGrooveKey =
                        SigleInsideGrooveKey(context, keyInfo)
                    this.mKey = sigleInsideGrooveKey
                    binding!!.flKeyView.addView(sigleInsideGrooveKey)
                }

                6 -> {
                    val dimpleKey: DimpleKey = DimpleKey(context, keyInfo)
                    this.mKey = dimpleKey
                    binding!!.flKeyView.addView(dimpleKey)
                }

                7 -> {
                    val angleKey: AngleKey = AngleKey(context, keyInfo)
                    this.mKey = angleKey
                    binding!!.flKeyView.addView(angleKey)
                    binding!!.btDecode.setEnabled(false)
                }

                8 -> {
                    val tubularKey: TubularKey = TubularKey(context, keyInfo)
                    this.mKey = tubularKey
                    binding!!.flKeyView.addView(tubularKey)
                }

                9 -> {
                    val sideToothKey: SideToothKey = SideToothKey(context, keyInfo)
                    this.mKey = sideToothKey
                    binding!!.flKeyView.addView(sideToothKey)
                    binding!!.btDecode.setEnabled(false)
                }
            }
        } else {
            val side3KsKey: Side3KsKey = Side3KsKey(context, keyInfo)
            this.mKey = side3KsKey
            binding!!.flKeyView.addView(side3KsKey)
        }
        val key: Key? = this.mKey
        key?.setPadding(
            AutoUtils.getPercentWidthSize(20),
            AutoUtils.getPercentHeightSize(20),
            AutoUtils.getPercentWidthSize(20),
            AutoUtils.getPercentHeightSize(20)
        )
    }

    fun onViewClicked(view: View) {
        val i: Int
        var str: String
        when (view.id) {
            R.id.bt_change_sibling -> {
                this.lastClamp = ki!!.clampBean
                if (!TextUtils.isEmpty(ki!!.keyComb)) {
                    val split: Array<String> =
                        ki!!.keyComb.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray()
                    var i2: Int = 0
                    while (i2 < split.size) {
                        val split2: Array<String> =
                            split.get(i2).split("-".toRegex()).dropLastWhile({ it.isEmpty() })
                                .toTypedArray()
                        if (split2.size > 1 && ki!!.icCard == split2.get(1).toInt()) {
                            if (i2 < split.size - 1) {
                                str = split.get(i2 + 1).split("-".toRegex())
                                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(1)
                            } else {
                                str = split.get(0).split("-".toRegex())
                                    .dropLastWhile({ it.isEmpty() }).toTypedArray().get(1)
                            }
                            toothCodeMap.put(
                                ki!!.icCard,
                                ki!!.keyToothCode
                            )
                            initKey(str.toInt())
                        }
                        i2++
                    }
                    return
                }
                if (!this.isSibling) {
                    if (!this.doorIgnition && !TextUtils.isEmpty(
                            ki!!.readBittingRule
                        ) && ("2" == ki!!.readBittingRule)
                    ) {
                        val keyToothCode: String = ki!!.keyToothCode
                        if (!TextUtils.isEmpty(keyToothCode)) {
                            val split3: Array<String> =
                                keyToothCode.split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                                    .toTypedArray().get(0).split(",".toRegex())
                                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
                            var str2: String = ""
                            var i3: Int = 0
                            while (i3 < split3.size) {
                                var str3: String = split3.get(i3)
                                var str4: String = "1"
                                if (str3.contains("?")) {
                                    str3 = "1"
                                }
                                if (split3.size != 8 && ((i3 < 3 || split3.size != 9) && (i3 < 4 || split3.size != 10))) {
                                    str4 = str3
                                }
                                str2 =
                                    if (i3 == split3.size - 1) str2 + str4 + ";" else str2 + str4 + ","
                                i3++
                            }
                            this.sideKeyToothCode = str2
                        }
                    }
                    this.isSibling = true
                    this.mainKeyID = ki!!.icCard
                    this.mainKeyToothCode = ki!!.keyToothCode
                    if (!TextUtils.isEmpty(ki!!.siblingProfile)) {
                        val parseInt: Int = ki!!.siblingProfile.toInt()
                        initKey(parseInt)
                        ResUpdateUtils.showKeyImage(
                            context, parseInt,
                            binding!!.ivRealKey
                        )
                    }
                    if (ki!!.keyitemid != 0) {
                        initKeySide(ki!!.keyitemid)
                        return
                    }
                    return
                }
                this.isSibling = false
                if (this.cutCount != 1) {
                    this.sideKeyToothCode = ki!!.keyToothCode
                }
                if (!TextUtils.isEmpty(ki!!.siblingProfile)) {
                    i = ki!!.siblingProfile.toInt()
                } else {
                    i = this.mainKeyID
                }
                initKey(i)
                ResUpdateUtils.showKeyImage(context, i, binding!!.ivRealKey)
                return
            }

            R.id.bt_cut -> {
                if (AppUtil.isApkInDebug(context) || ClampManager.getInstance()
                        .checkHasCalibrated(
                            this.ki
                        )
                ) {
                    CutDialog(activity, this.dataParam).show()
                    return
                }
                return
            }

            R.id.bt_decode -> {
                if (AppUtil.isApkInDebug(context) || ClampManager.getInstance()
                        .checkHasCalibrated(
                            this.ki
                        )
                ) {
                    DecodeDialog(activity, this.dataParam).show()
                    return
                }
                return
            }

            R.id.fl_key_view -> {
                if (MyApplication.getInstance()
                        .isShowRealDepth || AppUtil.isApkInDebug(context)
                ) {
                    val arrayList: ArrayList<Bitt> = this.bittList
                    if (arrayList == null || arrayList.size == 0) {
                        ToastUtil.showToast(getString(R.string.no_decode_data))
                        return
                    } else {
                        start(
                            LookRealDepthFragment.Companion.newInstance(
                                keyData!!.keyID, this.bittList, keyData!!.title,
                                ki!!.depthStr,
                                ki!!.depthName, KeyDataUtils.getCutsBySpace(ki!!.spaceStr)
                            )
                        )
                        return
                    }
                }
                return
            }

            R.id.iv_key_scale -> {
                start((context)!!, ki!!.icCard, true)
                return
            }

            R.id.iv_scale -> {
                start((context)!!, ClampCreator.getClampZoomImg(this.ki!!))
                return
            }

            R.id.iv_switch_last -> {
                val currentItem: Int = binding!!.vpClamp.currentItem
                if (currentItem > 0) {
                    binding!!.vpClamp.setCurrentItem(currentItem - 1)
                    return
                }
                return
            }

            R.id.iv_switch_next -> {
                val currentItem2: Int = binding!!.vpClamp.currentItem
                if (currentItem2 < binding!!.vpClamp.adapter!!.count - 1) {
                    binding!!.vpClamp.setCurrentItem(currentItem2 + 1)
                    return
                }
                return
            }

            R.id.tv_adjust -> {
                start(SizeAdjustFragment.Companion.newInstance(this.ki))
                return
            }

            R.id.tv_code_find_tooth -> {
                start(
                    CodeFindToothFragment.Companion.newInstance(
                        keyData!!.keyID,
                        keyData!!.codeSeries,
                        keyData!!.isn
                    )
                )
                return
            }

            R.id.tv_collect -> {
                showEditDialog()
                return
            }

            R.id.tv_info -> {
                start(KeyInfoFragment.newInstance(this.ki))
                return
            }

            R.id.tv_input -> {
                start(ToothCodeInputFragment.newInstance(this.ki!!))
                return
            }

            R.id.tv_lack_tooth -> {
                start(
                    LackToothFragment.newInstance(
                        keyData!!.keyID,
                        this.ki,
                        this.mainKeyToothCode,
                        this.sideKeyToothCode,
                        keyData!!.codeSeries,
                        this.toothCodeLack,
                        this.bittingCodes
                    )
                )
                return
            }

            R.id.tv_move -> {
                showLoadingDialog(getString(R.string.waitting))
                if (this.moveToRight) {
                    this.moveToRight = false
                    OperationManager.getInstance().sendOrder(
                        Command.DecoderOperation(
                            1,
                            0,
                            (6300.0f / MachineData.dXScale).toInt(),
                            0,
                            0,
                            ""
                        ), OperateType.MOVE_XYZ
                    )
                    return
                } else {
                    this.moveToRight = true
                    OperationManager.getInstance().sendOrder(
                        Command.DecoderOperation(1, 0, 1, 1, 1, ""),
                        OperateType.MOVE_XYZ
                    )
                    return
                }
            }

            else -> return
        }
    }

    private fun showEditDialog() {
        val editDialog = EditDialog(context)
        editDialog.setTip(getString(R.string.enter_remarks))
        editDialog.setDialogBtnCallback { str -> this@KeyOperateFragment.collectKey(str) }
        editDialog.setContentNullable(true)
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun savaCutHistory() {
        val cutHistoryData = CutHistoryData(keyData)
        if (!TextUtils.isEmpty(ki!!.keyComb)) {
            val keySet: Set<Int?> = toothCodeMap.keys
            val sb: StringBuilder = StringBuilder()
            for (num: Int? in keySet) {
                val split: Array<String> =
                    ki!!.keyComb.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                if (split.isNotEmpty() && TextUtils.equals(
                        split.get(0).split("-".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().get(1), num.toString()
                    )
                ) {
                    cutHistoryData.toothCode = if (TextUtils.isEmpty(
                            toothCodeMap.get(num)
                        )
                    ) "" else toothCodeMap.get(num)
                }
                if (split.size > 1 && TextUtils.equals(
                        split.get(1).split("-".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().get(1), num.toString()
                    )
                ) {
                    sb.append(num)
                    sb.append(":")
                    sb.append(
                        if (TextUtils.isEmpty(toothCodeMap.get(num))) "" else toothCodeMap.get(
                            num
                        )
                    )
                    sb.append("|")
                }
                if (split.size > 2 && TextUtils.equals(
                        split.get(2).split("-".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().get(1), num.toString()
                    )
                ) {
                    sb.append(num)
                    sb.append(":")
                    sb.append(
                        if (TextUtils.isEmpty(toothCodeMap.get(num))) "" else toothCodeMap.get(
                            num
                        )
                    )
                    sb.append("|")
                }
                cutHistoryData.toothCodeSide = sb.toString()
            }
        } else if (this.isSibling) {
            if (this.cutCount == 0) {
                cutHistoryData.toothCode = this.mainKeyToothCode
                cutHistoryData.toothCodeSide = ki!!.keyToothCode
            }
        } else {
            cutHistoryData.toothCode = ki!!.keyToothCode
        }
        cutHistoryData.doorIgnition = if (this.doorIgnition) 1 else 0
        cutHistoryData.doorToIgnition = if (this.doorToIgnition) 1 else 0
        addDisposable(
            Observable.fromCallable<Boolean>
            {
                UserDataDaoManager.getInstance(this@KeyOperateFragment.context)
                    .saveCutHistory(cutHistoryData)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<Boolean> {
                    // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.5
                    @Throws(Exception::class)  // io.reactivex.functions.Consumer
                    override fun accept(bool: Boolean) {
                        if (bool) {
                            return
                        }
                        ToastUtil.showToast(R.string.cut_history_save_failed)
                    }
                }, { dismissLoadingDialog() })
        )
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: collectKey, reason: merged with bridge method [inline-methods] */
    fun collectKey(str: String?) {
        var variableSpace: String
        val keyData: GoOperatBean? = keyData
        keyData!!.toothCode = ki!!.keyToothCode
        keyData.remark = str
        val collectionData = CollectionData(keyData)
        if (TextUtils.isEmpty(keyData.cuts)) {
            if (TextUtils.isEmpty(ki!!.variableSpace)) {
                val split: Array<String> =
                    ki!!.spaceStr.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                variableSpace = ""
                for (i in split.indices) {
                    variableSpace = if (i == 0) variableSpace + split.get(i).split(",".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray().size else "$variableSpace-" + split.get(i)
                        .split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size
                }
            } else {
                variableSpace = ki!!.variableSpace
            }
            collectionData.cuts = variableSpace
        }
        addDisposable(
            Observable.fromCallable {
                UserDataDaoManager.getInstance(
                    this@KeyOperateFragment.context
                ).collectKey(collectionData)
            }.subscribeOn(
                Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread()).subscribe({ dismissLoadingDialog() })
        )
    }

    private fun cancleCollect() {
        addDisposable(
            Observable.fromCallable<Boolean> {
                UserDataDaoManager.getInstance(
                    this@KeyOperateFragment.context
                ).cancleCollect(
                    keyData!!.seriesID
                )
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ bool: Boolean ->
                    if (bool) {
                        binding!!.tvCollect.setCompoundDrawablesWithIntrinsicBounds(
                            resources.getDrawable(R.drawable.collect_default),
                            null as Drawable?,
                            null as Drawable?,
                            null as Drawable?
                        )
                        binding!!.tvCollect.setCompoundDrawablePadding(10)
                    }
                                                                                               },
                    { ToastUtil.showToast(R.string.failed_to_remove_favorites) }, { dismissLoadingDialog() }))

    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        val f: Float
        val f2: Float
        val eventCode: Int = eventCenter.eventCode
        if (eventCode == 0) {
            val bundle: Bundle = eventCenter.data as Bundle
            var i: Int = bundle.getInt("row")
            val i2: Int = bundle.getInt("column")
            if (ki!!.type == 6 || ki!!.type == 8) {
                f = bundle.getInt("depth").toFloat()
                f2 = MachineData.dZScale
            } else {
                f = bundle.getInt("depth").toFloat()
                f2 = MachineData.dXScale
            }
            var i3: Int = (f * f2).toInt()
            LogUtil.i(TAG, "depth: $i3")
            if (!AppUtil.isApkInDebug(context) && (ki!!.type != 6) && (ki!!.type != 1) && (ki!!.icCard != 601287)) {
                i3 = fixDepth(i - 1, i3)
            }
            if (ki!!.isAngleDimple) {
                i = this.dimpleCutIndex + 1
                i3 = (i3 * cos(Math.toRadians(14.0))).toInt()
            }
            LogUtil.i(TAG, "depthFix: $i3")
            val i4: Int = i + (-1)
            val f3: Float = i3.toFloat()
            val changeSingleDepth: String =
                mKey!!.changeSingleDepth(
                    i4, i2 + (-1), f3, this.rounding,
                    ki!!.readBittingRule
                )
            ki!!.setKeyToothCode(changeSingleDepth)
            val map: MutableMap<Int, String> = this.toothCodeMap
            map[ki!!.icCard] = changeSingleDepth
            val valueOf: String = i.toString()
            val valueOf2: String = i2.toString()
            val valueOf3: String = i3.toString()
            val key: Key? = this.mKey
            bittList.add(
                Bitt(
                    valueOf, valueOf2, valueOf3, key!!.getCodeByDepth(
                        key.allDepths[i4],
                        mKey!!.allDepthNames[i4], f3, this.rounding
                    )
                )
            )
            return
        }
        if (eventCode == 1) {
            savaCutHistory()
            this.dataParam =
                (eventCenter.data as Bundle).getParcelable<Parcelable>("param") as DataParam?
            if (ki!!.type != 6 && ki!!.type != 92) {
                binding!!.tvCutterSize.text = String.format(
                    Locale.US, "%.1fmm",
                    dataParam!!.cutterSize / 100.0f
                )
            }
            binding!!.tvDecoderSize.text = String.format(
                Locale.US, "%.1fmm",
                dataParam!!.decoderSize / 100.0f
            )
            ToolSizeManager.setCutterSize(dataParam!!.cutterSize)
            ToolSizeManager.setDecoderSize(dataParam!!.decoderSize)
            if (ki!!.type != 7 && dataParam!!.isPlastic) {
                binding!!.btDecode.visibility = View.GONE
            }
            if (dataParam!!.isPlastic && TextUtils.equals(
                    ki!!.clampBean.clampSide, "A"
                )
            ) {
                showErrorDialog(getString(R.string.please_use_s1b_jaw), R.drawable.error_1, null)
                return
            }
            if (ki!!.type == 7) {
                this.angleKeySteps = initAngleKeyCutSteps()
                showTurningDialog()
                return
            }
            if (ki!!.isPlasticKey && ki!!.align == 1) {
                showPlasticLocationDialog()
                return
            }
            if (ki!!.isDimpleMotherSonKey) {
                showDimpleOperationDialog()
                return
            } else if (ki!!.isAngleDimple) {
                showAngleDimpleInitDialog(1)
                return
            } else {
                showClearClampRemind(1)
                return
            }
        }
        if (eventCode == 2) {
            val inputFinishBean: InputFinishBean = eventCenter.data as InputFinishBean
            this.doorIgnition = inputFinishBean.isDoorAndIgnition
            this.doorToIgnition = inputFinishBean.isDoorToIgnition
            var toothCode: String = inputFinishBean.toothCode
            this.toothCodeLack = inputFinishBean.toothCodeLack
            this.bittingCodes = inputFinishBean.bittingCodes
            val spaceStr: String = ki!!.spaceStr
            if (ki!!.isNewHonda) {
                val split: Array<String> =
                    toothCode.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val split2: Array<String> =
                    spaceStr.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (split.size > split2.size) {
                    var str = ""
                    var str2 = ""
                    for (i5 in split.indices) {
                        if (i5 < split2.size) {
                            str += split[i5]
                        } else {
                            str2 += split[i5]
                        }
                    }
                    if (!this.isSibling) {
                        if (!TextUtils.isEmpty(str)) {
                            this.mainKeyToothCode = str
                            toothCode = str
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            this.sideKeyToothCode = str2
                        }
                    } else {
                        if (!TextUtils.isEmpty(str)) {
                            this.mainKeyToothCode = str
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            this.sideKeyToothCode = str2
                            toothCode = str2
                        }
                    }
                }
            } else if ((this.isSibling && this.cutCount != 1) || this.doorIgnition) {
                this.sideKeyToothCode = toothCode
            }
            ki!!.setKeyToothCode(toothCode)
            mKey!!.setToothCodeAndInvalidate(toothCode)
            val map2: MutableMap<Int, String> = this.toothCodeMap
            map2[ki!!.icCard] = toothCode
            return
            return
        }
        if (eventCode == 9) {
            val intValue: Int = (eventCenter.data as Int)
            this.toothCount = intValue
            ki!!.spaceStr = reduceCount(intValue, mKey!!.allSpaces)
            ki!!.spaceWidthStr = reduceCount(
                this.toothCount,
                mKey!!.allSpaceWidths
            )
            initKeyView(this.ki)
            return
        }
        if (eventCode == 14) {
            dismissLoadingDialog()
            return
        }
        if (eventCode == 39) {
            val dataParam: DataParam? =
                (eventCenter.data as Bundle).getParcelable<Parcelable>("param") as DataParam?
            this.dataParam = dataParam
            ToolSizeManager.setDecoderSize(dataParam!!.decoderSize)
            binding!!.tvDecoderSize.text = String.format(
                Locale.US, "%.1fmm",
                this.dataParam!!.decoderSize / 100.0f
            )
            this.rounding = this.dataParam!!.isRound
            showClearClampRemind(39)
            return
        }
        if (eventCode == 47) {
            showLoadingDialog((eventCenter.data as Int).toString() + "%", true)
            return
        }
        if (eventCode == 57) {
            initKeyView(this.ki)
            return
        }
        if (eventCode == 49) {
            putUpDecoderRemindDialog()
            return
        }
        if (eventCode != 50) {
            when (eventCode) {
                32 -> {
                    val operateType: OperateType = eventCenter.data as OperateType
                    if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION) {
                        bittList.clear()
                        if (ki!!.isAngleDimple) {
                            showAngleDimpleTurnDialog(0)
                            return
                        }
                        return
                    }
                    if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE) {
                        if (this.isBarCodeScan && MachineInfo.isE20Us(context)) {
                            binding!!.tvInput.visibility = View.GONE
                            binding!!.tvCodeFindTooth.visibility = View.GONE
                            binding!!.tvLackTooth.visibility = View.GONE
                            binding!!.tvMove.visibility = View.GONE
                        }
                        if (ki!!.icCard == 601287) {
                            fix3KsKeyTooth()
                        }
                        if (ki!!.isAngleDimple) {
                            dimpleCutIndex++
                            showAngleDimpleTurnDialog(0)
                        }
                        savaCutHistory()
                        toothFindCode()
                        dismissLoadingDialog()
                        return
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_LOCATION) {
                        return
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH) {
                        if (ki!!.type == 7) {
                            showTurningDialog()
                            return
                        } else {
                            if (ki!!.isAngleDimple) {
                                showAngleDimpleTurnDialog(1)
                                return
                            }
                            return
                        }
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_EXECUTE) {
                        addDisposable(
                            Observable.timer(500L, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.newThread()).observeOn(
                                AndroidSchedulers.mainThread()
                            ).subscribe({ this@KeyOperateFragment.dismissLoadingDialog() }, { dismissLoadingDialog() })
                        )
                        showLoadingDialog("100%", true)
                        if (ki!!.type == 7) {
                            val list: List<AngleKeyStep>? = this.angleKeySteps
                            if (list != null && this.angleKeyCutIndex < list.size) {
                                showTurningDialog()
                            } else {
                                this.angleKeyCutIndex = 0
                                savaCutHistory()
                                saveCutNumber()
                                OperationManager.getInstance()
                                    .sendOrder(Command.TurnOffSpindle(), OperateType.CLAMP_RESET)
                            }
                        } else if (ki!!.type == 6 && ki!!.spaceWidthStr.contains("-")) {
                            dimpleCutIndex++
                            showDimpleOperationDialog()
                        } else if (ki!!.isAngleDimple) {
                            dimpleCutIndex++
                            showAngleDimpleTurnDialog(1)
                        } else {
                            hu162tChangeSide()
                            savaCutHistory()
                            saveCutNumber()
                        }
                        CutCountHelper.getInstance().addCutCount(this.ki)
                        return
                    }
                    if (operateType == OperateType.MOVE_XYZ) {
                        dismissLoadingDialog()
                        return
                    }
                    return
                }

                33 -> {
                    dismissLoadingDialog()
                    this.angleKeyCutIndex = 0
                    this.dimpleCutIndex = 0
                    showErrorDialog(context, eventCenter.data as ErrorBean?)
                    return
                }

                34 -> {
                    showLoadingDialog(getString(R.string.waitting))
                    return
                }

                else -> return
            }
        }
        putDownDecoderRemindDialog((eventCenter.data as Int))
    }

    private fun toothFindCode() {
        val sb: StringBuilder = StringBuilder()
        for (i in ki!!.getToothCodeArray().indices) {
            val list: List<String> =
                ki!!.getToothCodeArray().get(i)
            for (i2 in list.indices) {
                sb.append(
                    KeyDataUtils.getToothCodeRound(
                        list.get(i2),
                        ki!!.getDepthNameList().get(i)
                    )
                )
            }
            if (i != ki!!.getToothCodeArray().size - 1) {
                sb.append("-")
            }
        }

        addDisposable(
            Observable.fromCallable {
                ToothCodeDaoManager(
                    ki!!.icCard
                ).lackTooth(sb.toString().replace("?", "_"))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list2: List<BittingCode> ->
                    if (list2.size > 0) {
                        val sb2: StringBuilder = StringBuilder()
                        sb2.append(this@KeyOperateFragment.getString(R.string.code))
                        sb2.append(":")
                        for (i3 in list2.indices) {
                            sb2.append(list2.get(i3).code)
                            if (i3 != list2.size - 1) {
                                sb2.append(",")
                            }
                        }
                        binding!!.tvCode.text = sb2.toString()
                    }
                }, { th: Throwable -> Log.i(TAG, "throwable: " + th.message) }, { dismissLoadingDialog() }
            )
        )
    }

    private fun fix3KsKeyTooth() {
        addDisposable(
            Observable.fromCallable(Callable {
                val str: String? = toothCodeMap.get(1287)
                val toothCodeArray: List<MutableList<String>> =
                    ki!!.getToothCodeArray()
                var i: Int = 0
                var list: MutableList<String> = toothCodeArray.get(0)
                val list2: MutableList<String> = toothCodeArray.get(1)
                if (!TextUtils.isEmpty(str)) {
                    val keyInfo: KeyInfo =
                        KeyInfoDaoManager.getInstance().getBasicData(1287).toKeyInfo()
                    var split: Array<String?> =
                        str!!.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                            .get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray()
                    var i2: Int = 0
                    while (i2 < split.size) {
                        val str2: String? = split.get(i2)
                        val str3: String = list.get(i2)
                        val str4: String = list2.get(i2)
                        val depthByCode: Int = ki!!.getDepthByCode(
                            ki!!.getDepthList().get(i),
                            ki!!.getDepthNameList().get(i), str3
                        )
                        val strArr: Array<String?> = split
                        val width: Int = (ki!!.width - ki!!.getGroove()) - ki!!.getDepthByCode(
                            ki!!.getDepthList().get(1),
                            ki!!.getDepthNameList().get(1), str4
                        )
                        i = 0
                        val depthByCode2: Int = keyInfo.getDepthByCode(
                            keyInfo.getDepthList().get(0),
                            keyInfo.getDepthNameList().get(0),
                            str2
                        )
                        if (abs((depthByCode2 - depthByCode).toDouble()) > abs((depthByCode2 - width).toDouble())) {
                            list2.set(i2, list.get(i2))
                        } else {
                            list.set(i2, list2.get(i2))
                        }
                        i2++
                        split = strArr
                    }
                } else {
                    var i3: Int = 0
                    while (i3 < list.size) {
                        val str5: String = list.get(i3)
                        val str6: String = list2.get(i3)
                        val toothCodeDec: String = this@KeyOperateFragment.getToothCodeDec(str5)
                        val toothCodeDec2: String = this@KeyOperateFragment.getToothCodeDec(str6)
                        val abs: Double = abs(toothCodeDec.toFloat() - 0.5)
                        val parseFloat: Float = toothCodeDec2.toFloat()
                        val list3: MutableList<String> = list
                        if (abs > abs(parseFloat - 0.5)) {
                            list2.set(i3, list3.get(i3))
                        } else {
                            list3.set(i3, list2.get(i3))
                        }
                        i3++
                        list = list3
                    }
                }
                val sb: StringBuilder = StringBuilder()
                for (i4 in toothCodeArray.indices) {
                    val list4: List<String> = toothCodeArray.get(i4)
                    for (i5 in list4.indices) {
                        sb.append(list4.get(i5))
                        if (i5 == list4.size - 1) {
                            sb.append(";")
                        } else {
                            sb.append(",")
                        }
                    }
                }
                sb.toString()
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ str: String? ->
                    mKey!!.setToothCodeAndInvalidate(str)
                    ki!!.setKeyToothCode(str)
                    this@KeyOperateFragment.savaCutHistory()
                }, { dismissLoadingDialog() })
            )

    }

    fun getToothCodeDec(str: String): String {
        return if (str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) str.substring(
            str.indexOf(
                FileUtil.FILE_EXTENSION_SEPARATOR
            ), str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 2
        ) else ".0"
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

        anglekeyTurningDialog.setDialogBtnCallback { i: Int ->
            if (i == 0) {
                this@KeyOperateFragment.dimpleCutIndex = 0
                this@KeyOperateFragment.dismissLoadingDialog()
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
            } else if (i == 1) {
                incrementDimpleCut(this@KeyOperateFragment)
                this@KeyOperateFragment.showDimpleOperationDialog()
            } else {
                val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                keyOperateFragment.showLoadingDialog(
                    keyOperateFragment.getString(R.string.cutting),
                    true
                )
                this@KeyOperateFragment.startCut()
            }
        }
        anglekeyTurningDialog.show()
    }

    fun showDimpleKeyTurnOverDialog() {
        val anglekeyTurningDialog: AnglekeyTurningDialog = AnglekeyTurningDialog(context)
        if (this.dimpleCutIndex == 1) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_2))
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_1)
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_4))
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_2)
        }

        anglekeyTurningDialog.setDialogBtnCallback({ i: Int ->
            if (i == 0) {
                this@KeyOperateFragment.dimpleCutIndex = 0
                this@KeyOperateFragment.dismissLoadingDialog()
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
            } else if (i == 1) {
                incrementDimpleCut(this@KeyOperateFragment)
                showDimpleOperationDialog()
            } else {
                val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                keyOperateFragment.showLoadingDialog(
                    keyOperateFragment.getString(R.string.cutting),
                    true
                )
                startCut()
            }
        })
        anglekeyTurningDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCut() {
        val str: String
        if (ki!!.isDimpleMotherSonKey) {
            val i: Int = this.dimpleCutIndex
            if (i == 0) {
                dataParam!!.cutDimpleSonOrMother = 1
            } else if (i == 1) {
                dataParam!!.cutDimpleSonOrMother = 1
            } else if (i == 2) {
                dataParam!!.cutDimpleSonOrMother = 2
            } else if (i == 3) {
                dataParam!!.cutDimpleSonOrMother = 2
            }
        }
        if (this.doorIgnition && !TextUtils.isEmpty(ki!!.readBittingRule)) {
            if (("2" == ki!!.readBittingRule)) {
                str =
                    mKey!!.getDoorIgnitionFrontDepthStr(OperateType.CUT, this.doorToIgnition)
            } else if (this.cutCount == 0) {
                str =
                    mKey!!.getDoorIgnitionSideBDepthStr(OperateType.CUT, this.doorToIgnition)
            } else {
                str =
                    mKey!!.getDoorIgnitionSideCDepthStr(OperateType.CUT, this.doorToIgnition)
            }
        } else if (TextUtils.isEmpty(ki!!.readBittingRule) || !("3" == ki!!.readBittingRule)) {
            str = ""
        } else if (this.cutCount == 0) {
            str = mKey!!.getHu162tSideBDepthStr(OperateType.CUT)
        } else {
            str = mKey!!.getHu162tSideCDepthStr(OperateType.CUT)
        }
        dataParam!!.toothCodeReal = str
        CuttingMachine.getInstance().cut(this.dataParam)
        showLoadingDialog(getString(R.string.cutting), true)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startDecode() {
        showLoadingDialog(getString(R.string.decoding), true)
        CuttingMachine.getInstance().decode(this.dataParam)
    }

    private fun initAngleKeyCutSteps(): List<AngleKeyStep>? {
        val keyToothCode: String = ki!!.keyToothCode
        if (TextUtils.isEmpty(keyToothCode)) {
            return null
        }
        val split: Array<String> =
            keyToothCode.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                .split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val split2: Array<String> =
            ki!!.spaceStr.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                .get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val treeMap: TreeMap<String, String> = TreeMap()
        val findNoCutDepth: String = findNoCutDepth()
        for (i in split.indices) {
            if (!TextUtils.equals(findNoCutDepth, split.get(i))) {
                if (treeMap.containsKey(split.get(i))) {
                    treeMap.put(split.get(i), treeMap.get(split.get(i)) + split2.get(i) + ",")
                } else {
                    treeMap.put(split.get(i), split2.get(i) + ",")
                }
            }
        }
        val arrayList = ArrayList<AngleKeyStep>()
        for (entry in treeMap.entries) {
            arrayList.add(AngleKeyStep(entry.key, entry.value))
        }
        val arrayList2 = ArrayList<AngleKeyStep>()
        arrayList2.add(AngleKeyStep(ANGLE_KEY_INIT, ""))
        arrayList2.addAll(arrayList)
        if (TextUtils.equals(ki!!.clampBean.clampNum, "S9") && (TextUtils.equals(
                ki!!.clampBean.clampSide, "C"
            ) || TextUtils.equals(
                ki!!.clampBean.clampSide, "D"
            ))
        ) {
            return arrayList2
        }
        if (ki!!.getFace() == 4) {
            arrayList2.addAll(arrayList)
        }
        arrayList2.add(AngleKeyStep(ANGLE_KEY_TURN_OVER, ""))
        arrayList2.addAll(arrayList)
        if (ki!!.getFace() == 4) {
            arrayList2.addAll(arrayList)
        }
        return arrayList2
    }

    private fun findNoCutDepth(): String {
        try {
            val indexOf: Int = ki!!.getDepthList()[0].indexOf(0)
            return if (indexOf >= 0) ki!!.getDepthNameList()[0][indexOf] else ""
        } catch (e: Exception) {
            ToastUtil.showToast(e.message)
            return ""
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun detectCutterHigh() {
        CuttingMachine.getInstance().cutLocationCutter(this.dataParam)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showTurningDialog() {
        val betaAnglekeyTurningImg: Int
        val list: List<AngleKeyStep> = this.angleKeySteps ?: return
        if (this.angleKeyCutIndex >= list.size) {
            this.angleKeyCutIndex = 0
            dismissLoadingDialog()
            OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
            return
        }
        val angleKeyStep: AngleKeyStep = angleKeySteps!!.get(this.angleKeyCutIndex)
        if (TextUtils.equals(angleKeyStep.tooth, ANGLE_KEY_INIT)) {
            showAngleKeyInitDialog()
            return
        }
        if (TextUtils.equals(angleKeyStep.tooth, ANGLE_KEY_TURN_OVER)) {
            showTurnOverDialog()
            return
        }
        val anglekeyTurningDialog = AnglekeyTurningDialog(context)
        anglekeyTurningDialog.setRemindMsg(
            getString(
                R.string.turn_key_to_n,
                angleKeyStep.tooth
            )
        )
        if (CuttingMachine.getInstance().isE9) {
            betaAnglekeyTurningImg = e9AnglekeyTurningImg
        } else if (TextUtils.equals("S9", ki!!.clampBean.clampNum)) {
            betaAnglekeyTurningImg = abusAngleKeyTurningImg
        } else {
            betaAnglekeyTurningImg = this.betaAnglekeyTurningImg
        }
        anglekeyTurningDialog.setRemindImg(betaAnglekeyTurningImg)

        anglekeyTurningDialog.setDialogBtnCallback { i: Int ->
            if (i == 0) {
                this@KeyOperateFragment.angleKeyCutIndex = 0
                this@KeyOperateFragment.dismissLoadingDialog()
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
            } else {
                if (i == 1) {
                    incrementAngleCut(this@KeyOperateFragment)
                    this@KeyOperateFragment.showTurningDialog()
                    return@setDialogBtnCallback
                }
                val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                keyOperateFragment.showLoadingDialog(
                    keyOperateFragment.getString(R.string.waitting),
                    true
                )
                incrementAngleCut(this@KeyOperateFragment)
                dataParam!!.angleKeySpace = angleKeyStep.space
                dataParam!!.angleKeyTooth = angleKeyStep.tooth
                CuttingMachine.getInstance().cutNoLocation(this@KeyOperateFragment.dataParam)
            }
        }
        anglekeyTurningDialog.show()
    }

    private val abusAngleKeyTurningImg: Int
        get() {
            val tooth: String =
                angleKeySteps!!.get(this.angleKeyCutIndex).tooth
            if (TextUtils.equals("1", tooth)) {
                return R.drawable.abuskey_1
            }
            if (TextUtils.equals("2", tooth)) {
                return R.drawable.abuskey_2
            }
            if (TextUtils.equals("3", tooth)) {
                return R.drawable.abuskey_3
            }
            if (TextUtils.equals("4", tooth)) {
                return R.drawable.abuskey_4
            }
            if (TextUtils.equals("5", tooth)) {
                return R.drawable.abuskey_5
            }
            if (TextUtils.equals("6", tooth)) {
                return R.drawable.abuskey_6
            }
            return 0
        }

     val betaAnglekeyTurningImg: Int
         get() {
            val tooth: String =
                angleKeySteps!!.get(this.angleKeyCutIndex).tooth
            val size: Int = (angleKeySteps!!.size - 2) / 4
            val i: Int = this.angleKeyCutIndex
            if (i < size + 1 || (i > (size * 2) + 1 && i < (size * 3) + 2)) {
                if (TextUtils.equals("2", tooth)) {
                    val i2: Int = this.angleKeyDepthCount
                    return if (i2 == 4) R.drawable.anglekey_a_4_2_left else if (i2 == 3) R.drawable.anglekey_a_3_2_left else 0
                }
                if (TextUtils.equals("3", tooth)) {
                    val i3: Int = this.angleKeyDepthCount
                    return if (i3 == 4) R.drawable.anglekey_a_4_3_left else if (i3 == 3) R.drawable.anglekey_a_3_3_left else 0
                }
                if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                    return R.drawable.anglekey_a_4_4_left
                }
                return 0
            }
            if (TextUtils.equals("2", tooth)) {
                val i4: Int = this.angleKeyDepthCount
                return if (i4 == 4) R.drawable.anglekey_a_4_2_right else if (i4 == 3) R.drawable.anglekey_a_3_2_right else 0
            }
            if (TextUtils.equals("3", tooth)) {
                val i5: Int = this.angleKeyDepthCount
                return if (i5 == 4) R.drawable.anglekey_a_4_3_right else if (i5 == 3) R.drawable.anglekey_a_3_3_right else 0
            }
            if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                return R.drawable.anglekey_a_4_4_right
            }
            return 0
        }

    val e9AnglekeyTurningImg: Int
        get() {
            val tooth: String =
                angleKeySteps!!.get(this.angleKeyCutIndex).tooth
            val size: Int = (angleKeySteps!!.size - 2) / 4
            val i: Int = this.angleKeyCutIndex
            if (i < size + 1 || (i > (size * 2) + 1 && i < (size * 3) + 2)) {
                if (TextUtils.equals("2", tooth)) {
                    val i2: Int = this.angleKeyDepthCount
                    return if (i2 == 4) R.drawable.tibbe_4_2_u else if (i2 == 3) R.drawable.tibbe_3_2_u else 0
                }
                if (TextUtils.equals("3", tooth)) {
                    val i3: Int = this.angleKeyDepthCount
                    return if (i3 == 4) R.drawable.tibbe_4_3_u else if (i3 == 3) R.drawable.tibbe_3_3_u else 0
                }
                if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                    return R.drawable.tibbe_4_4_u
                }
                return 0
            }
            if (TextUtils.equals("2", tooth)) {
                val i4: Int = this.angleKeyDepthCount
                return if (i4 == 4) R.drawable.tibbe_4_2_d else if (i4 == 3) R.drawable.tibbe_3_2_d else 0
            }
            if (TextUtils.equals("3", tooth)) {
                val i5: Int = this.angleKeyDepthCount
                return if (i5 == 4) R.drawable.tibbe_4_3_d else if (i5 == 3) R.drawable.tibbe_3_3_d else 0
            }
            if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                return R.drawable.tibbe_4_4_d
            }
            return 0
        }

     fun showAngleKeyInitDialog() {
        OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(1))
        val remindDialog: RemindDialog = RemindDialog(context)
        if (CuttingMachine.getInstance().isE9) {
            remindDialog.setRemindImg(if (this.angleKeyDepthCount == 3) R.drawable.tibbe_3_1 else R.drawable.tibbe_4_1)
        } else if (TextUtils.equals("S9", ki!!.clampBean.clampNum)) {
            remindDialog.setRemindImg(R.drawable.abuskey_6)
        } else {
            remindDialog.setRemindImg(if (this.angleKeyDepthCount == 3) R.drawable.anglekey_3_1 else R.drawable.anglekey_4_1)
        }
        remindDialog.setCancleBtnVisibility(false)
        remindDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture))

        remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack({ z: Boolean ->
            if (z) {
                val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                keyOperateFragment.showLoadingDialog(
                    keyOperateFragment.getString(R.string.cutting),
                    true
                )
                incrementAngleCut(this@KeyOperateFragment)
                CuttingMachine.getInstance().cutLocation(this@KeyOperateFragment.dataParam)
            }
        }))
        remindDialog.show()
    }

    private fun showTurnOverDialog() {
        val remindDialog: RemindDialog = RemindDialog(context)
        if (CuttingMachine.getInstance().isE9) {
            remindDialog.setRemindImg(R.drawable.tibbe_rotate)
        } else if (TextUtils.equals("S9", ki!!.clampBean.clampNum)) {
            remindDialog.setRemindImg(R.drawable.anglekey_abus_turnover)
        } else {
            remindDialog.setRemindImg(if (this.angleKeyDepthCount == 3) R.drawable.anglekey_3_1 else R.drawable.anglekey_4_1)
        }
        remindDialog.setCancleBtnVisibility(false)
        remindDialog.setRemindMsg(getString(R.string.turn_the_key_to_the_other_side))

        remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack({ z: Boolean ->
            if (z) {
                incrementAngleCut(this@KeyOperateFragment)
                val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                keyOperateFragment.showLoadingDialog(
                    keyOperateFragment.getString(R.string.cutting),
                    true
                )
                this@KeyOperateFragment.detectCutterHigh()
            }
        }))
        remindDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showAngleDimpleInitDialog(i: Int) {
        this.dimpleCutIndex = 0
        val remindDialog: RemindDialog = RemindDialog(context)
        remindDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_0)
        remindDialog.setCancleBtnVisibility(false)
        remindDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture))

        remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack({ z: Boolean ->
            if (z) {
                if (i == 0) {
                    val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                    keyOperateFragment.showLoadingDialog(
                        keyOperateFragment.getString(R.string.decoding),
                        true
                    )
                    CuttingMachine.getInstance().decodeLocation(this@KeyOperateFragment.dataParam)
                } else {
                    val keyOperateFragment2: KeyOperateFragment = this@KeyOperateFragment
                    keyOperateFragment2.showLoadingDialog(
                        keyOperateFragment2.getString(R.string.cutting),
                        true
                    )
                    CuttingMachine.getInstance().cutLocation(this@KeyOperateFragment.dataParam)
                }
            }
        }))
        remindDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showAngleDimpleTurnDialog(i: Int) {
        val anglekeyTurningDialog: AnglekeyTurningDialog = AnglekeyTurningDialog(context)
        val i2: Int = this.dimpleCutIndex
        if (i2 == 0) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture))
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_1)
        } else {
            if (i2 != 1) {
                return
            }
            anglekeyTurningDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture))
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_2)
        }

        anglekeyTurningDialog.setDialogBtnCallback(AnglekeyTurningDialog.DialogBtnCallBack setDialogBtnCallback@{ i3: Int ->
            if (i3 == 0) {
                this@KeyOperateFragment.dimpleCutIndex = 0
                this@KeyOperateFragment.dismissLoadingDialog()
                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP)
            } else {
                if (i3 != 1) {
                    dataParam!!.angleDimpleIndex = this@KeyOperateFragment.dimpleCutIndex
                    dataParam!!.isAngleDimple = true
                    if (i == 0) {
                        val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                        keyOperateFragment.showLoadingDialog(
                            keyOperateFragment.getString(R.string.decoding),
                            true
                        )
                        CuttingMachine.getInstance()
                            .decodeNoLocation(this@KeyOperateFragment.dataParam)
                        return@setDialogBtnCallback
                    } else {
                        val keyOperateFragment2: KeyOperateFragment = this@KeyOperateFragment
                        keyOperateFragment2.showLoadingDialog(
                            keyOperateFragment2.getString(R.string.cutting),
                            true
                        )
                        CuttingMachine.getInstance()
                            .cutNoLocation(this@KeyOperateFragment.dataParam)
                        return@setDialogBtnCallback
                    }
                }
                incrementDimpleCut(this@KeyOperateFragment)
                this@KeyOperateFragment.showAngleDimpleTurnDialog(i)
            }
        })
        anglekeyTurningDialog.show()
    }

    private fun reduceCount(i: Int, list: List<List<Int>>): String {
        var str: String = ""
        for (i2 in list.indices) {
            for (i3 in list.get(i2).indices) {
                val i4: Int = i - 1
                if (i3 == i4) {
                    str = str + list.get(i2).get(i3) + ";"
                } else if (i3 < i4) {
                    str = str + list.get(i2).get(i3) + ","
                }
            }
        }
        return str
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String {
        return getString(R.string.operation)
    }

    fun showPlasticLocationDialog() {
        val remindDialog: RemindDialog = RemindDialog(context)
        if (CuttingMachine.getInstance().isE9) {
            remindDialog.setRemindImg(R.drawable.a9_laser_stop_4_e9)
        } else if (("B" == ki!!.clampBean.clampSide)) {
            remindDialog.setRemindImg(R.drawable.remind_s1_b_plastic)
        } else {
            remindDialog.setRemindImg(R.drawable.remind_s1_a_plastic)
        }
        remindDialog.setRemindMsg(getString(R.string.clear_clamp_and_install_key))

        remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack({ z: Boolean ->
            if (z) {
                this@KeyOperateFragment.startCut()
            }
        }))
        remindDialog.show()
    }

    fun putDownDecoderRemindDialog(i: Int) {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog: RemindDialog = RemindDialog(context)
            remindDialog.setRemindImg(R.drawable.pull_decoder_down)
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down))

            remindDialog.setDialogBtnCallback({ z: Boolean ->
                if (!z) {
                    if (this@KeyOperateFragment.angleKeyCutIndex > 0) {
                        decrementAngleCut(this@KeyOperateFragment)
                    }
                    this@KeyOperateFragment.dismissLoadingDialog()
                } else if (i == 0) {
                    this@KeyOperateFragment.startDecode()
                } else {
                    this@KeyOperateFragment.startCut()
                }
            })
            remindDialog.show()
        }
    }

    fun putUpDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog: RemindDialog = RemindDialog(context)
            remindDialog.setRemindImg(R.drawable.push_decoder_up)
            remindDialog.setRemindMsg(getString(R.string.push_the_decoder_up))

            remindDialog.setDialogBtnCallback({ z: Boolean ->
                if (z) {
                    CuttingMachine.getInstance()
                        .cutFromCutterLocation(this@KeyOperateFragment.dataParam)
                    val keyOperateFragment: KeyOperateFragment = this@KeyOperateFragment
                    keyOperateFragment.showLoadingDialog(
                        keyOperateFragment.getString(R.string.waitting),
                        true
                    )
                } else {
                    if (this@KeyOperateFragment.angleKeyCutIndex > 0) {
                        decrementAngleCut(this@KeyOperateFragment)
                    }
                    this@KeyOperateFragment.dismissLoadingDialog()
                }
            })
            remindDialog.show()
        }
    }

    private fun showClearClampRemind(i: Int) {
        if (this.clearClampRemind == null) {
            this.clearClampRemind = RemindDialog(context)
        }
        clearClampRemind!!.setCancleBtnVisibility(false)
        clearClampRemind!!.setRemindImg(ClampCreator.getClearClampImg(this.ki!!))
        clearClampRemind!!.setRemindMsg(getString(R.string.clean_the_groove_from_chips))

        clearClampRemind!!.setDialogBtnCallback(RemindDialog.DialogBtnCallBack { z: Boolean ->
            if (z) {
                if (i == 39) {
                    if (ki!!.isAngleDimple) {
                        this@KeyOperateFragment.showAngleDimpleInitDialog(0)
                    } else {
                        this@KeyOperateFragment.startDecode()
                    }
                }
                if (i == 1) {
                    this@KeyOperateFragment.startCut()
                }
            }
        })
        if (clearClampRemind!!.isShowing) {
            return
        }
        clearClampRemind!!.show()
    }

    private fun fixDepth(i: Int, i2: Int): Int {
        val split: Array<String> =
            ki!!.depthStr.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        if (i < split.size) {
            val split2: Array<String> =
                split.get(i).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            var i3: Int = Int.MAX_VALUE
            var i4: Int = 0
            for (i5 in split2.indices) {
                val round: Int = Math.round(((i2 - split2.get(i5).toInt()) * 10).toFloat()) / 10
                if (abs(round.toDouble()) < abs(i3.toDouble())) {
                    i4 = i5
                    i3 = round
                }
            }
            val parseInt: Int = split2.get(i4).toInt()
            val random: Random = Random()
            if (abs(i3.toDouble()) > 30) {
                return i2
            }
            if (abs(i3.toDouble()) > 20) {
                val nextInt: Float = ((random.nextInt(200) * 1.0f) / 100.0f) + 8.0f
                val f: Float = parseInt.toFloat()
                return (if (i3 > 0) f + nextInt else f - nextInt).toInt()
            }
            if (abs(i3.toDouble()) > 15) {
                val nextInt2: Float = ((random.nextInt(200) * 1.0f) / 100.0f) + 6.0f
                val f2: Float = parseInt.toFloat()
                return (if (i3 > 0) f2 + nextInt2 else f2 - nextInt2).toInt()
            }
            if (abs(i3.toDouble()) > 10) {
                val nextInt3: Float = ((random.nextInt(100) * 1.0f) / 100.0f) + 5.0f
                val f3: Float = parseInt.toFloat()
                return (if (i3 > 0) f3 + nextInt3 else f3 - nextInt3).toInt()
            }
            if (abs(i3.toDouble()) > 7) {
                val nextInt4: Float = ((random.nextInt(100) * 1.0f) / 100.0f) + 4.0f
                val f4: Float = parseInt.toFloat()
                return (if (i3 > 0) f4 + nextInt4 else f4 - nextInt4).toInt()
            }
        }
        return i2
    }

    private fun hu162tChangeSide() {
        var valueOf: String?
        if (TextUtils.isEmpty(ki!!.readBittingRule) || !("3" == ki!!.readBittingRule)) {
            return
        }
        if (!this.doorIgnition) {
            if (this.cutCount == 0) {
                this.cutCount = 1
                val split: Array<String> =
                    ki!!.keyToothCode.split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray().get(0).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                var str: String = ""
                for (i in split.indices) {
                    var str2: String = split.get(i)
                    var str3: String = "1"
                    if (str2.contains("?")) {
                        str2 = "1"
                    }
                    if (split.size != 8) {
                        if (i < 3 && split.size == 9) {
                            val parseFloat: Float = str2.toFloat()
                            if (parseFloat <= 4.0f) {
                                if (parseFloat >= 1.0f) {
                                    valueOf = (5.0 - parseFloat).toString()
                                    str3 = valueOf
                                }
                                str3 = "4"
                            }
                        } else if (i >= 4 || split.size != 10) {
                            str3 = "2.5"
                        } else {
                            val parseFloat2: Float = str2.toFloat()
                            if (parseFloat2 <= 4.0f) {
                                if (parseFloat2 >= 1.0f) {
                                    valueOf = (5.0 - parseFloat2).toString()
                                    str3 = valueOf
                                }
                                str3 = "4"
                            }
                        }
                    }
                    str = if (i == split.size - 1) str + str3 + ";" else str + str3 + ","
                }
                ki!!.setKeyToothCode(str)
                mKey!!.setToothCodeAndInvalidate(str)
                val key: Key? = this.mKey
                if (key is SingleOutGrooveKey) {
                    key.switchCSide()
                }
                binding!!.tvSide.setText(R.string.c_side)
                return
            }
            return
        }
        if (this.cutCount == 0) {
            this.cutCount = 1
            val key2: Key? = this.mKey
            if (key2 is SingleOutGrooveKey) {
                key2.switchCSide()
            }
            binding!!.tvSide.setText(R.string.c_side)
        }
    }

    private fun saveCutNumber() {
        val i: Int = SPUtils.getInt(SpKeys.CUT_NUMBER, 200) - 1
        if (i <= 0) {
            EventBus.getDefault().post(EventCenter<Any?>(36))
            SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1)
        }
        SPUtils.put(SpKeys.CUT_NUMBER, i)
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroy() {
        if (ki!!.type == 7) {
            OperationManager.getInstance().sendOrder(
                Command.OpenDoorCuttingSettings(
                    if (SPUtils.getBoolean(
                            SpKeys.COVER,
                            true
                        )
                    ) 1 else 0
                )
            )
        }
        this.ki = null
        super.onDestroy()
    }

}
