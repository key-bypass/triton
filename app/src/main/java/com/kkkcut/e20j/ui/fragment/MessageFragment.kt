package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.userDB.JpushMsg
import com.kkkcut.e20j.adapter.MessageAdapter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.OperatorRemindDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentMessageBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class MessageFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var messageAdapter: MessageAdapter? = null

    var binding: FragmentMessageBinding? = null

    private val usageNoticeCN: Array<String> = arrayOf(
        "机器使用须知",
        "1.本机只可读，切导电钥匙！（不导电钥匙例如，大众迈腾塑料钥匙，路虎沃尔沃铝制钥匙氧化之后）\n2.请尽量使用原厂探针铣刀，副厂未经验证探针有可能会损坏夹具，机器，断针，读，切不准！\n3.请务必保留原厂泡沫箱！！\n4.本机禁止倒置！倒置可能导致铜屑损坏本机电路！\n5.旋转S1夹具，请先关闭S1-D辅助挡块！"
    )

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentMessageBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_message
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.messageAdapter = MessageAdapter()
        binding!!.rvMessage.setLayoutManager(LinearLayoutManager(context))
        binding!!.rvMessage.addItemDecoration(DividerItemDecoration(context, 1))
        binding!!.rvMessage.setAdapter(this.messageAdapter)
        messageAdapter!!.onItemClickListener = this
        messageFromDb
        if (MachineInfo.isE9Standard(context)) {
            return
        }
        showOperatorRemind(1)
    }

    private fun showOperatorRemind(i: Int) {
        val operatorRemindDialog = OperatorRemindDialog(context)
        operatorRemindDialog.show()
        operatorRemindDialog.setRemindText(getString(R.string.caution))
        operatorRemindDialog.setType(i)
        operatorRemindDialog.window!!
            .setBackgroundDrawableResource(android.R.color.transparent)
    }

    private val messageFromDb: Unit
        get() {
            addDisposable(
                Observable.fromCallable { UserDataDaoManager.getInstance(context).jpushMsgs }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ list ->
                        var list = list
                        if (list == null) {
                            list = ArrayList<JpushMsg>()
                        }
                        if (MachineInfo.isChineseMachine()) {
                            val jpushMsg = JpushMsg()
                            jpushMsg.title = usageNoticeCN.get(0)
                            jpushMsg.content = usageNoticeCN.get(1)
                            list.add(0, jpushMsg)
                            binding!!.tvRemind.visibility = 8
                        } else {
                            binding!!.tvRemind.visibility = 0
                        }
                        messageAdapter!!.setNewData(list)
                    }, { dismissLoadingDialog() })
            )
        }


    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.message)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        start(
            MessageDetailFragment.newInstance(
                baseQuickAdapter.data[i] as JpushMsg?
            )
        )
    }

    companion object {
        fun newInstance(): MessageFragment {
            return MessageFragment()
        }
    }
}
