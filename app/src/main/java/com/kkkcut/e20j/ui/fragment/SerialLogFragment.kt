package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentSerialLogBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class SerialLogFragment : BaseBackFragment() {
    var binding: FragmentSerialLogBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentSerialLogBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_serial_log
    }

    override fun setTitleStr(): String? {
        return "日志"
    }

    override fun initViewsAndEvents() {
        val file = File(context!!.filesDir, "log")
        addDisposable(
            Observable.fromCallable<String>
            {
                FileUtil.readFileContent(file.path)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ str ->
                    binding!!.tvLog.text = str
                }, { dismissLoadingDialog() })
        )
        binding!!.btClear.setOnClickListener {
            file.delete()
            binding!!.tvLog.text = ""
        }
    }

    companion object {
        fun newInstance(): SerialLogFragment {
            val bundle = Bundle()
            val serialLogFragment = SerialLogFragment()
            serialLogFragment.setArguments(bundle)
            return serialLogFragment
        }
    }
}
