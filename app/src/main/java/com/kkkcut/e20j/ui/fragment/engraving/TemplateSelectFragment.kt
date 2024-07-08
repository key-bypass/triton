package com.kkkcut.e20j.ui.fragment.engraving

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate
import com.kkkcut.e20j.adapter.KeyMarkingTemplateAdapter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentTemplateListBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/* loaded from: classes.dex */
class TemplateSelectFragment : BaseBackFragment(), BaseQuickAdapter.OnItemChildClickListener,
    BaseQuickAdapter.OnItemClickListener {
    var binding: FragmentTemplateListBinding? = null

    private var keyMarkingTemplateAdapter: KeyMarkingTemplateAdapter? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentTemplateListBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_template_list
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String {
        return getString(R.string.template_selection)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.setOrientation(1)
        binding!!.rvTemplateList.setLayoutManager(linearLayoutManager)

        this.keyMarkingTemplateAdapter = KeyMarkingTemplateAdapter()
        keyMarkingTemplateAdapter!!.onItemChildClickListener = this
        keyMarkingTemplateAdapter!!.onItemClickListener = this
        binding!!.rvTemplateList.setAdapter(this.keyMarkingTemplateAdapter)
        templates
    }

    private val templates: Unit
        get() {
            addDisposable(Observable.fromCallable {
                UserDataDaoManager.getInstance(this.requireContext())
                    .keyMarkingTemplates
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list: List<KeyMarkingTemplate> ->
                    keyMarkingTemplateAdapter!!.setNewData(
                        list
                    )
                }, { }, { dismissLoadingDialog() }
            )
            )
        }

    fun onViewClicked() {
        val remindDialog = RemindDialog(requireContext())
        remindDialog.setRemindMsg(getString(R.string.delete_all_recordes))
        remindDialog.setDialogBtnCallback { z: Boolean ->
            if (z) {
                keyMarkingTemplateAdapter!!.setNewData(ArrayList())
                UserDataDaoManager.getInstance(this.requireContext())
                    .deleteAllTemplate()
            }
        }
        remindDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        val subscribe: Disposable = Observable.fromCallable {
            UserDataDaoManager.getInstance(this.requireContext()).fuzzyQueryTemplates(editable.toString())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ list: List<KeyMarkingTemplate> ->
            keyMarkingTemplateAdapter!!.setNewData(list)
        }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View, position: Int) {
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("template", adapter.data[position] as KeyMarkingTemplate?)
        setFragmentResult(0, bundle)
        onBack()
    }

    companion object {
        fun newInstance(): TemplateSelectFragment {
            val bundle = Bundle()
            val templateSelectFragment = TemplateSelectFragment()
            templateSelectFragment.setArguments(bundle)
            return templateSelectFragment
        }
    }
}
