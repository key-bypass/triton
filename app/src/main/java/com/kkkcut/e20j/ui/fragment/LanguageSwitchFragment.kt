package com.kkkcut.e20j.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.adapter.LanguageListAdapter
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.lan.LanguageConstants
import com.kkkcut.e20j.utils.lan.LocalManageUtil
import java.util.Arrays

/* loaded from: classes.dex */
class LanguageSwitchFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    var adapter: LanguageListAdapter? = null
    var languageList: List<String>? = null
    var languageMap: Map<String, String>? = null

    var rvLanguage: RecyclerView? = null
    private var shortName: String? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_language
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(1)
        rvLanguage!!.setLayoutManager(linearLayoutManager)
        rvLanguage!!.addItemDecoration(DividerItemDecoration(getContext(), 1))
        var string: String? = getArguments()!!.getString("languageStr")
        if (TextUtils.isEmpty(string)) {
            string = "en"
        }
        this.languageList =
            Arrays.asList(*string!!.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
            )
        this.languageMap = this.languageConsMaps
        val selectLanguage: String = LocalManageUtil.getSelectLanguage(getContext())
        this.shortName = selectLanguage
        if (TextUtils.isEmpty(selectLanguage)) {
            this.shortName = "en"
        }
        val languageListAdapter = LanguageListAdapter(
            this.languageList as MutableList<String>,
            languageMap!!, this.shortName
        )
        this.adapter = languageListAdapter
        rvLanguage!!.setAdapter(languageListAdapter)
        adapter!!.setOnItemClickListener(this)
    }

    private val languageConsMaps: Map<String, String>
        get() {
            val hashMap = HashMap<String, String>()
            hashMap.put("en", "English")
            hashMap.put("zh", "简体中文(Chinese-Simplified)")
            hashMap.put("tr", "Türkçe(Turkish)")
            hashMap.put("fr", "Français(French)")
            hashMap.put("cs", "Česky(Czech)")
            hashMap.put("de", "Deutsch(German)")
            hashMap.put("it", "lingua italiana(Italian)")
            hashMap.put("es", "Español(Spanish)")
            hashMap.put("ko", "한국어(Korean)")
            hashMap.put("pt", "Português(Portuguese)")
            hashMap.put("ru", "русский(Russian)")
            hashMap.put("pl", "Polski(Polish)")
            hashMap.put("hb", "HEBREW(עברי)")
            hashMap.put("fa", "(Persian)فارسی")
            hashMap.put("vi", "Tiếng Việt(Vietnamese)")
            hashMap.put(LanguageConstants.THAI, "Thai(ภาษาไทย)")
            return hashMap
        }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.language)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>?, view: View, i: Int) {
        LocalManageUtil.saveSelectLanguage(getContext(), languageList!!.get(i))
        UsbSerialManager.getInstance().stop()
        restartApplication()
    }

    private fun restartApplication() {
        val launchIntentForPackage: Intent? =
            getContext()!!.getPackageManager().getLaunchIntentForPackage(
                getContext()!!.getPackageName()
            )
        launchIntentForPackage!!.addFlags(67108864)
        startActivity((launchIntentForPackage))
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroy() {
        super.onDestroy()
        this.languageList = null
        this.languageMap = null
        this.adapter = null
    }

    companion object {
        fun newInstance(str: String?): LanguageSwitchFragment {
            val languageSwitchFragment: LanguageSwitchFragment = LanguageSwitchFragment()
            val bundle: Bundle = Bundle()
            bundle.putString("languageStr", str)
            languageSwitchFragment.setArguments(bundle)
            return languageSwitchFragment
        }
    }
}
