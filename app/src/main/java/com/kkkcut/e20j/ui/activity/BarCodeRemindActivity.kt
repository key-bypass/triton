package com.kkkcut.e20j.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.base.HideStatusActivity
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityBarCodeRemindBinding
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class BarCodeRemindActivity : HideStatusActivity() {
    var binding: ActivityBarCodeRemindBinding? = null

    private var type: Type? = null

    /* loaded from: classes.dex */
    private enum class Type {
        HU66,
        TOY48,
        HU101,
        HU100
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        this.binding = ActivityBarCodeRemindBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_bar_code_remind
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun initViewsAndEvents() {
        val intExtra = intent.getIntExtra(ID, 0)
        if (intExtra == 909 || intExtra == 1309) {
            binding!!.ll3.visibility = 8
            binding!!.iv1.setImageResource(R.drawable.bar_code_909)
            binding!!.ll1.tag = 909
            binding!!.iv2.setImageResource(R.drawable.bar_code_1309)
            binding!!.ll2.tag = 1309
            binding!!.tv1.text = "Copy by this option if the original key without extra cutting"
            binding!!.tv2.text = "Copy by this option if the original key with extra cutting"
            this.type = Type.HU66
        }
        if (intExtra == 872 || intExtra == 1510) {
            binding!!.iv1.setImageResource(R.drawable.bar_code_872)
            binding!!.ll1.tag = 872
            binding!!.iv2.setImageResource(R.drawable.bar_code_1510)
            binding!!.ll2.tag = 1510
            binding!!.iv3.setImageResource(R.drawable.bar_code_1510_1)
            binding!!.ll3.tag = 1510
            binding!!.tv1.text = "Copy by this option if both sides work"
            binding!!.tv2.text = "Copy by this option if only one side works"
            binding!!.tv3.text = "Copy by this option if only one side works"
            this.type = Type.TOY48
        }
        if (intExtra == 1097 || intExtra == 1373) {
            binding!!.ll3.visibility = 8
            binding!!.iv1.setImageResource(R.drawable.bar_code_1097)
            binding!!.ll1.tag = 1097
            binding!!.iv2.setImageResource(R.drawable.bar_code_1373)
            binding!!.ll2.tag = 1373
            this.type = Type.HU100
        }
        if (intExtra == 1370 || intExtra == 1407 || intExtra == 998) {
            binding!!.iv1.setImageResource(R.drawable.bar_code_998)
            binding!!.ll1.tag = 998
            binding!!.iv2.setImageResource(R.drawable.bar_code_1370)
            binding!!.ll2.tag = 1370
            binding!!.iv3.setImageResource(R.drawable.bar_code_1407)
            binding!!.ll3.tag = 1407
            this.type = Type.HU101
        }
    }

    // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun getOverridePendingTransitionMode(): TransitionMode {
        return TransitionMode.FADE
    }

    fun onclick(view: View) {
        val id = view.id
        if (id == R.id.iv_back) {
            finish()
            return
        }
        when (id) {
            R.id.ll_1, R.id.ll_2, R.id.ll_3 -> {
                if (this.type == null) {
                    return
                }
                goBack((view.tag as Int))
                return
            }

            else -> return
        }
    }

    private fun goBack(i: Int) {
        val bundle = Bundle()
        bundle.putInt(ID, i)
        bundle.putString("bar_code", intent.getStringExtra("bar_code"))
        EventBus.getDefault().post(EventCenter<Any?>(55, bundle))
        finish()
    }

    companion object {
        const val BAR_CODE: String = "bar_code"
        const val ID: String = "ID"

        private val hu66Arr = intArrayOf(909, 1309)
        private val toy48Arr = intArrayOf(872, 1309)
        private val hu100Arr = intArrayOf(1097, 1373)
        private val hu101Arr = intArrayOf(1370, 1407)


        @JvmStatic
        fun start(activity: Activity, i: Int, str: String?) {
            val intent = Intent(activity, BarCodeRemindActivity::class.java as Class<*>)
            intent.putExtra(ID, i)
            intent.putExtra("bar_code", str)
            activity.startActivityForResult(intent, 0)
        }
    }
}
