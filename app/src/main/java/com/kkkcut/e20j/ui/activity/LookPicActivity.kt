package com.kkkcut.e20j.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.kkkcut.e20j.base.HideStatusActivity
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityLookPicBinding
import com.kkkcut.e20j.utils.ResUpdateUtils

/* loaded from: classes.dex */
class LookPicActivity : HideStatusActivity() {
    var binding: ActivityLookPicBinding? = null

    override fun onCreate(bundle: Bundle?, persistableBundle: PersistableBundle?) {
        super.onCreate(bundle, persistableBundle)
        this.binding = ActivityLookPicBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_look_pic
    }

    override fun initViewsAndEvents() {
        if (intent.getBooleanExtra(isKeyImg, false)) {
            ResUpdateUtils.showKeyImage(
                this, intent.getIntExtra(RES_ID, 0),
                binding!!.ivShow
            )
        } else {
            binding!!.ivShow.setImageResource(intent.getIntExtra(RES_ID, 0))
        }
    }

    override fun getOverridePendingTransitionMode(): TransitionMode {
        return TransitionMode.FADE
    }

    companion object {
        private val IMG_TRANSITION: String = "img_transition"
        val RES_ID: String = "ResId"
        val isKeyImg: String = "isKeyImg"

        @JvmStatic
        @JvmOverloads
        fun start(context: Context, i: Int, z: Boolean = false) {
            val intent = Intent(context, LookPicActivity::class.java as Class<*>?)
            intent.putExtra(RES_ID, i)
            intent.putExtra(isKeyImg, z)
            context.startActivity(intent)
        }
    }
}
