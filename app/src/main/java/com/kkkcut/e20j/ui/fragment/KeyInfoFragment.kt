package com.kkkcut.e20j.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.LinearLayout
import android.widget.TextView
import com.cutting.machine.MachineInfo
import com.cutting.machine.bean.KeyInfo
import com.cutting.machine.error.ErrorCode
import com.kkkcut.e20j.DbBean.KeyResource
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.dao.ResourceDaoManager
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentKeyinfoBinding
import com.kkkcut.e20j.utils.ResUpdateUtils
import com.kkkcut.e20j.utils.ThemeUtils
import com.kkkcut.e20j.utils.UnitUtils
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlin.math.max

/* loaded from: classes.dex */
class KeyInfoFragment() : BaseBackFragment() {
    var binding: FragmentKeyinfoBinding? = null

    private var keyResource: KeyResource? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentKeyinfoBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyinfo
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val title: String = keyinfo!!.getTitle()
        binding!!.tvDetail.setText(title + "{" + keyinfo!!.getCuts() + "}")
        val keyChinaNum: String = keyinfo!!.getKeyChinaNum()
        if (TextUtils.isEmpty(keyChinaNum)) {
            binding!!.tvKeyNumbering.setVisibility(8)
        } else {
            binding!!.tvKeyNumbering.setText(getString(R.string.china_key_number) + ":" + keyChinaNum)
        }
        if (keyinfo!!.getAlign() == 0) {
            binding!!.tvAlign.setText(R.string.shoulder)
        } else {
            binding!!.tvAlign.setText(R.string.tip)
        }
        var width: Int = keyinfo!!.getWidth()
        if (width < 0) {
            width = 0
        }
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            width = UnitUtils.mm2Inch(width)
        }
        binding!!.tvKeyWidth.setText(width.toString())
        var thick: Int = keyinfo!!.getThick()
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            thick = UnitUtils.mm2Inch(thick)
        }
        if (thick == 0) {
            binding!!.tvTitleThickness.setVisibility(8)
            binding!!.tvKeyThickness.setVisibility(8)
        } else {
            binding!!.tvTitleThickness.setVisibility(0)
            binding!!.tvKeyThickness.setVisibility(0)
            binding!!.tvKeyThickness.setText(thick.toString())
        }
        if (!keyinfo!!.isCustomKey()) {
            ResUpdateUtils.showKeyImage(
                getContext(), keyinfo!!.getIcCard(),
                binding!!.ivRealKey
            )
        }
        val seriesID: Int = keyinfo!!.getSeriesID()
        if (seriesID != 0) {
            val keyResource: KeyResource? =
                ResourceDaoManager.getInstance().getKeyResource(seriesID)
            this.keyResource = keyResource
            if (keyResource != null && ((keyResource.getFK_LanguageID() == 2 && MachineInfo.isChineseMachine()) || (this.keyResource!!.getFK_LanguageID() == 1 && !MachineInfo.isChineseMachine()))) {
                val videoPath: String = this.keyResource!!.getVideoPath()
                if (!TextUtils.isEmpty(videoPath)) {
                    binding!!.ivPlayVideo.setVisibility(View.VISIBLE)
                    binding!!.tvQrcode.setVisibility(View.VISIBLE)
                    binding!!.ivQrcode.setVisibility(View.VISIBLE)
                    binding!!.ivQrcode.setImageBitmap(
                        CodeUtils.createImage(
                            videoPath,
                            ErrorCode.keyDecodeFailed,
                            ErrorCode.keyDecodeFailed,
                            null
                        )
                    )
                }
                val explainHtml: String = this.keyResource!!.getExplainHtml()
                if (!TextUtils.isEmpty(explainHtml)) {
                    val settings: WebSettings = binding!!.webviewDescription.getSettings()
                    settings.setJavaScriptEnabled(true)
                    settings.setDefaultTextEncodingName("UTF-8")
                    binding!!.webviewDescription.loadData(
                        explainHtml,
                        "text/html; charset=UTF-8",
                        null
                    )
                }
            }
        }
        binding!!.ivClamp.setImageResource(ClampCreator.getDrawableRes(keyinfo!!))
        val codeSeries: String = keyinfo!!.getCodeSeries()
        if (!TextUtils.isEmpty(codeSeries)) {
            binding!!.tvKeySeries.setText(codeSeries)
        }
        val keyBlankMap: HashMap<String, String>? = keyinfo!!.getKeyBlankMap()
        if (keyBlankMap != null) {
            for (str: String in keyBlankMap.keys) {
                val textView: TextView = TextView(getContext())
                textView.setTextSize(AutoUtils.getPercentHeightSize(24).toFloat())
                textView.setTextColor(ThemeUtils.getColor(getContext(), R.attr.color_red_blueDark))
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(-2, -2)
                layoutParams.setMargins(0, 10, 0, 0)
                textView.setText(str)
                binding!!.llKeyBlanks.addView(textView, layoutParams)
                val textView2: TextView = TextView(getContext())
                val str2: String? = keyBlankMap.get(str)
                textView2.setText(str2!!.substring(0, str2.length - 1))
                textView2.setTextColor(
                    ThemeUtils.getColor(
                        getContext(),
                        R.attr.textColor_ffffff_333333
                    )
                )
                textView2.setTextSize(AutoUtils.getPercentHeightSize(20).toFloat())
                LinearLayout.LayoutParams(-2, -2).setMargins(0, 10, 0, 0)
                binding!!.llKeyBlanks.addView(textView2)
            }
        }
        if (keyinfo!!.getLocked() == 0) {
            initSpaceData()
            initDepth()
        }
    }

    private fun initDepth() {
        binding!!.tvTitleDepth.setVisibility(0)
        val split: Array<String> =
            keyinfo!!.getDepthStr().split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val split2: Array<String> =
            keyinfo!!.getDepthName().split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        var i: Int = 0
        for (str: String in split) {
            i = max(
                i.toDouble(),
                str.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size.toDouble()
            )
                .toInt()
        }
        for (i2 in 0 until (split.size * 2)) {
            val linearLayout: LinearLayout = LinearLayout(getContext())
            linearLayout.setOrientation(0)
            for (i3 in 0 until i) {
                var textView: TextView? = null
                val i4: Int = i2 / 2
                val split3: Array<String> =
                    split.get(i4).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                val split4: Array<String> =
                    split2.get(i4).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                if (i3 < split3.size) {
                    if (i2 % 2 == 0) {
                        textView = getText(split4.get(i3), true)
                    } else {
                        textView = getText(split3.get(i3))
                    }
                }
                if (textView == null) {
                    break
                }
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(45, 20)
                layoutParams.setMargins(1, 1, 0, 0)
                linearLayout.addView(textView, layoutParams)
            }
            binding!!.llDepth.addView(linearLayout)
        }
    }

    private fun initSpaceData() {
        binding!!.tvTitleSpace.setVisibility(0)
        var spaceStr: String = keyinfo!!.getSpaceStr()
        val spacenew: String = keyinfo!!.getSpacenew()
        if (!TextUtils.isEmpty(spacenew)) {
            spaceStr = spacenew
        }
        val split: Array<String> =
            spaceStr.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        var i: Int = 0
        for (str: String in split) {
            i = max(
                i.toDouble(),
                str.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size.toDouble()
            )
                .toInt()
        }
        var i2: Int = 0
        while (i2 < split.size + 1) {
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(30, 20)
            layoutParams.setMargins(0, 1, 0, 0)
            binding!!.llIndex.addView(
                getText(if (i2 == 0) "" else i2.toString(), true),
                layoutParams
            )
            val linearLayout: LinearLayout = LinearLayout(getContext())
            linearLayout.setOrientation(0)
            for (i3 in 0 until i) {
                var textView: TextView? = null
                if (i2 == 0) {
                    textView = getText((i3 + 1).toString(), true)
                } else {
                    val split2: Array<String> =
                        split.get(i2 - 1).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray()
                    if (i3 < split2.size) {
                        textView = getText(split2.get(i3))
                    }
                }
                if (textView == null) {
                    break
                }
                val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(45, 20)
                layoutParams2.setMargins(1, 1, 0, 0)
                linearLayout.addView(textView, layoutParams2)
            }
            binding!!.llSpace.addView(linearLayout)
            i2++
        }
    }

    private fun getText(str: String, z: Boolean): TextView {
        var str: String = str
        val textView: TextView = TextView(getContext())
        textView.setGravity(17)
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        if (z) {
            textView.setTextColor(getContext()!!.getResources().getColor(R.color.color_ff205f))
        } else {
            if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                str = UnitUtils.mm2Inch(str.toInt()).toString()
            }
            textView.setTextColor(-1)
        }
        textView.setText(str)
        textView.setTextSize(0, 15.0f)
        return textView
    }

    private fun getText(str: String): TextView {
        return getText(str, false)
    }

    private val keyinfo: KeyInfo?
        get() {
            return getArguments()!!.getParcelable<Parcelable>(KEY_INFO) as KeyInfo?
        }

    fun onViewClicked() {
        val keyResource: KeyResource? = this.keyResource
        if (keyResource != null) {
            val videoPath: String = keyResource.getVideoPath()
            if (TextUtils.isEmpty(videoPath)) {
                return
            }
            startActivity(Intent("android.intent.action.VIEW", Uri.parse(videoPath)))
        }
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.key_information)
    }

    companion object {
        val KEY_INFO: String = "keyinfo"

        fun newInstance(keyInfo: KeyInfo?): KeyInfoFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(KEY_INFO, keyInfo)
            val keyInfoFragment: KeyInfoFragment = KeyInfoFragment()
            keyInfoFragment.setArguments(bundle)
            return keyInfoFragment
        }
    }
}
