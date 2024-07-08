package com.kkkcut.e20j.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kkkcut.e20j.base.BaseFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentTestBinding
import com.kkkcut.e20j.utils.DesUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.RequestBody
import okhttp3.Response
import org.apache.commons.math3.analysis.FunctionUtils.add
import java.io.IOException


/* loaded from: classes.dex */
class TestFragment : BaseFragment() {
    var binding: FragmentTestBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentTestBinding.inflate(layoutInflater, viewGroup, false)
        this.binding!!.commit.setOnClickListener { onViewClicked(it) }
        this.binding!!.param.setShowSoftInputOnFocus(false)
        return binding!!.getRoot()
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_test
    }

    override fun initViewsAndEvents() {
    }

    fun onViewClicked(view: View) {
        val id = view.id
        if (id == R.id.clear) {
            binding!!.param.setText("")
            return
        }
        if (id != R.id.commit) {
            return
        }
        val encrypt = DesUtil.encrypt(binding!!.param.getText().toString().trim(), DesUtil.SERVER)
        val okHttpClient = OkHttpClient()
        Log.i(TAG, "param: $encrypt")
        val builder = Builder()
        okHttpClient.newCall(
            builder.url("http://192.168.0.200:8086/MobilePhoneAppService.ashx")
                .post(FormBody.Builder().add("T", encrypt).build()).build()
        ).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: " + e.message)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG,
                    (response.protocol.toString() + " " + response.code).toString() + " " + response.message
                )
                val headers = response.headers
                for (i in 0 until headers.size) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i))
                }
                val string = response.body!!.string()
                Log.i(TAG, "onResponse: $string")
                try {
                    val decrypt = DesUtil.decrypt(string, DesUtil.SERVER)
                    Log.i(TAG, "返回: $decrypt")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }



        })
    }

    companion object {
        fun newInstance(): TestFragment {
            return TestFragment()
        }
    }
}
