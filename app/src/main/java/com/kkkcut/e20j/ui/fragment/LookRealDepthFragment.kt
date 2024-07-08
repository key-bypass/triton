package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.internal.view.SupportMenu
import androidx.core.view.InputDeviceCompat
import androidx.core.view.ViewCompat
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.bean.Bitt
import com.kkkcut.e20j.bean.gsonBean.UploadTestData
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentLookRealDepthBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.UUID
import kotlin.math.abs

/* loaded from: classes.dex */
class LookRealDepthFragment : BaseBackFragment() {
    var binding: FragmentLookRealDepthBinding? = null

    private var realDepthName: String = ""
    private var realDepth: String = ""
    private var uuid: String = ""
    private val bittA = ArrayList<Bitt>()
    private val bittB = ArrayList<Bitt>()

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentLookRealDepthBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_look_real_depth
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val split: Array<String> = arguments!!.getString("depth")!!.split(";".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()
        binding!!.tvDepth.text = "标准齿深：" + split[0]
        val string: String? = arguments!!.getString(DEPTH_NAME)
        binding!!.tvDepthName.text = "标准齿深代号：" + string!!.split(";".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[0]
        binding!!.tvTitle.text = arguments!!.getString("title")
        val parcelableArrayList: ArrayList<*>? =
            arguments!!.getParcelableArrayList<Parcelable>("Bitts")
        if (parcelableArrayList == null || parcelableArrayList.size == 0) {
            return
        }
        val arrayList = ArrayList<Float>()
        val it: Iterator<*> = parcelableArrayList.iterator()
        while (it.hasNext()) {
            val bitt: Bitt = it.next() as Bitt
            binding!!.row.addView(devide, LinearLayout.LayoutParams(-1, 1))
            binding!!.row.addView(getText(bitt.row), LinearLayout.LayoutParams(-1, 30))
            binding!!.colum.addView(devide, LinearLayout.LayoutParams(-1, 1))
            binding!!.colum.addView(getText(bitt.coloum), LinearLayout.LayoutParams(-1, 30))
            binding!!.depthName.addView(devide, LinearLayout.LayoutParams(-1, 1))
            binding!!.depthName.addView(
                getText(bitt.depthName),
                LinearLayout.LayoutParams(-1, 30)
            )
            binding!!.realDepth.addView(devide, LinearLayout.LayoutParams(-1, 1))
            binding!!.realDepth.addView(
                getText(
                    (Math.round(
                        bitt.realDepth.toFloat() * 10.0f
                    ) / 10.0f).toString()
                ), LinearLayout.LayoutParams(-1, 30)
            )
            val split2: Array<String> = split[bitt.row.toInt() - 1].split(",".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()
            var f: Float = 2.1474836E9f
            var i: Int = 0
            val r12: Int = split2.size
            for (i2 in 0 until r12) {
                val round: Float =
                    Math.round(abs((r12 - split2.get(i2).toInt()).toDouble()) * 10.0f) / 10.0f
                if (round < f) {
                    f = round
                    i = i2
                }
            }
            arrayList.add(f)
            binding!!.standardDepth.addView(devide, LinearLayout.LayoutParams(-1, 1))
            binding!!.standardDepth.addView(
                getText(split2[i]),
                LinearLayout.LayoutParams(-1, 30)
            )
            binding!!.difference.addView(devide, LinearLayout.LayoutParams(-1, 1))
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(-1, 30)
            val text: TextView = getText(f.toString())
            if (abs(f.toDouble()) > 5.0f && abs(f.toDouble()) < 8.0f) {
                text.setBackgroundColor(InputDeviceCompat.SOURCE_ANY)
            }
            if (abs(f.toDouble()) >= 8.0f) {
                text.setBackgroundColor(SupportMenu.CATEGORY_MASK)
            }
            binding!!.difference.addView(text, layoutParams)
            if (("1" == bitt.row)) {
                bittA.add(bitt)
            } else {
                bittB.add(bitt)
            }
        }
        arrayList.sort()
        val floatValue: Float = arrayList.get(arrayList.size - 1)
        binding!!.tvMax.text = floatValue.toString()
        val floatValue2: Float = arrayList.get(0)
        binding!!.tvMin.text = floatValue2.toString()
        binding!!.tvDiff.text = (floatValue - floatValue2).toString()
        if (bittA.size != 0) {
            addDisposable(Observable.fromIterable(this.bittA).sorted { bitt2, bitt3 ->
                bitt2.coloum.toInt() - bitt3.coloum.toInt()
            }.subscribe(
            { bitt2 ->
                Log.i(TAG, "bittA: $bitt2")
                var depthName: String = bitt2.depthName
                if (depthName.length > 4) {
                    depthName = depthName.substring(0, 4)
                }
                if (!string.contains("0") || string.contains("10")) {
                    depthName = depthName.replace("?", "0")
                }
                realDepthName(this@LookRealDepthFragment, "$depthName,")
                realDepthName(this@LookRealDepthFragment, bitt2.realDepth + ",")
            }, { dismissLoadingDialog() }))
        }
        if (!TextUtils.isEmpty(this.realDepthName)) {
            val str: String = this.realDepthName
            this.realDepthName = str.substring(0, str.length - 1)
            this.realDepthName += ";"
        }
        if (!TextUtils.isEmpty(this.realDepth)) {
            val str2: String = this.realDepth
            this.realDepth = str2.substring(0, str2.length - 1)
            this.realDepth += ";"
        }
        if (bittB.size != 0) {
            addDisposable(
                Observable.fromIterable(this.bittB).sorted { bitt2, bitt3 ->
                    (bitt2.coloum.toInt() - bitt3.coloum.toInt())
                }.subscribe(
                { bitt2 ->
                    Log.i(TAG, "bittB: $bitt2")
                    var depthName: String = bitt2.depthName
                    if (depthName.length > 4) {
                        depthName = depthName.substring(0, 4)
                    }
                    if (!string.contains("0") || string.contains("10")) {
                        depthName = depthName.replace("?", "0")
                    }
                    realDepth(this@LookRealDepthFragment, "$depthName,")
                   realDepth(this@LookRealDepthFragment, bitt2.realDepth + ",")
                }, { dismissLoadingDialog() })
            )
        }
        if (!TextUtils.isEmpty(this.realDepthName)) {
            val str3: String = this.realDepthName
            this.realDepthName = str3.substring(0, str3.length - 1)
            this.realDepthName += ";"
        }
        if (!TextUtils.isEmpty(this.realDepth)) {
            val str4: String = this.realDepth
            this.realDepth = str4.substring(0, str4.length - 1)
            this.realDepth += ";"
        }
        this.uuid = UUID.randomUUID().toString().replace("-".toRegex(), "")
    }

    private fun getText(str: String): TextView {
        val textView: TextView = TextView(context)
        textView.textSize = 20.0f
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK)
        textView.setGravity(17)
        textView.text = str
        return textView
    }

    private val devide: View
        get() {
            val textView = TextView(context)
            textView.setBackgroundColor(SupportMenu.CATEGORY_MASK)
            return textView
        }

    fun onclick() {
        if (TextUtils.isEmpty(this.realDepthName) || TextUtils.isEmpty(
                this.realDepth
            )
        ) {
            return
        }
        val operatorName: String = MyApplication.getInstance().operatorName
        if (!TextUtils.isEmpty(operatorName)) {
            uploadData(operatorName)
            return
        }
        val editDialog = EditDialog(context)
        editDialog.setTip("操作员：")
        editDialog.setDialogBtnCallback { str ->
            MyApplication.getInstance().operatorName = str
            this@LookRealDepthFragment.uploadData(str)
        }
        editDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun uploadData(str: String?) {
        val string: String? = arguments!!.getString("title")
        addDisposable(
            (RetrofitManager.getInstance().createApi(Apis::class.java) as Apis).postTestData(
                TUitls.uploadTestData(
                    this.uuid,
                    arguments!!.getInt("keyid").toString(),
                    string,
                    arguments!!.getString("bitNum"),
                    this.realDepthName,
                    this.realDepth,
                    str,
                    SPUtils.getString("series", "test_sn"),
                    AppUtil.getVersionCode(context),
                    SPUtils.getString(SpKeys.FIRMWARE, "test_fm"),
                    arguments!!.getString("depth"),
                    arguments!!.getString(
                        DEPTH_NAME
                    )
                )
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ uploadTestData ->
                        if (("0" == uploadTestData.code)) {
                            ToastUtil.showToast("上传成功")
                            return@subscribe
                        }
                        ToastUtil.showToast("上传失败：" + uploadTestData.msg)
                }, {
                    ToastUtil.showToast(R.string.network_unavailable)
                }, { dismissLoadingDialog() })
        )
    }

    companion object {
        private val DEPTH_NAME: String = "depthName"

        fun realDepthName(lookRealDepthFragment: LookRealDepthFragment, obj: Any): String {
            val str: String = lookRealDepthFragment.realDepthName + obj
            lookRealDepthFragment.realDepthName = str
            return str
        }

        fun realDepth(lookRealDepthFragment: LookRealDepthFragment, obj: Any): String {
            val str: String = lookRealDepthFragment.realDepth + obj
            lookRealDepthFragment.realDepth = str
            return str
        }

        fun newInstance(
            i: Int,
            arrayList: ArrayList<Bitt>,
            str: String?,
            str2: String?,
            str3: String?,
            str4: String?
        ): LookRealDepthFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList("Bitts", arrayList)
            bundle.putInt("keyid", i)
            bundle.putString("depth", str2)
            bundle.putString("title", str)
            bundle.putString(DEPTH_NAME, str3)
            bundle.putString("bitNum", str4)
            val lookRealDepthFragment = LookRealDepthFragment()
            lookRealDepthFragment.setArguments(bundle)
            return lookRealDepthFragment
        }
    }
}
