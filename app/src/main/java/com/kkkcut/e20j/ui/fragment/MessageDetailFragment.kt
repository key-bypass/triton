package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import com.kkkcut.e20j.DbBean.userDB.JpushMsg
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class MessageDetailFragment() : BaseBackFragment() {
    var tvContent: TextView? = null

    var tvTitle: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_message_detail
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val jpushMsg: JpushMsg? = getArguments()!!.getParcelable<Parcelable>(JPUSH_MSG) as JpushMsg?
        tvTitle!!.setText(jpushMsg!!.getTitle())
        tvContent!!.setText(jpushMsg.getContent())
    }

    companion object {
        private val JPUSH_MSG: String = "jpushMsg"

        fun newInstance(jpushMsg: JpushMsg?): MessageDetailFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(JPUSH_MSG, jpushMsg)
            val messageDetailFragment: MessageDetailFragment = MessageDetailFragment()
            messageDetailFragment.setArguments(bundle)
            return messageDetailFragment
        }
    }
}
