package com.kkkcut.e20j.ui.fragment.blankcut

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutCreateGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutDrillingFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutKeyTipFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLXP90To80KFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLeftGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLengthFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutRightGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutSideGrooveFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentModifyKeyBlankTypeBinding

/* loaded from: classes.dex */
class SpecialBlankCutTypeSelectFragment : BaseBackFragment(),
    BaseQuickAdapter.OnItemChildClickListener {
    private var blankCutAdapter: BlankCutAdapter? = null
    private val blankCutType: BlankCutType? = null

    var binding: FragmentModifyKeyBlankTypeBinding? = null

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        this.binding = FragmentModifyKeyBlankTypeBinding.bind(view)
        this.binding!!.btOk.setOnClickListener { v: View? -> onclick(v!!) }
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_modify_key_blank_type
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        binding!!.btPreset!!.visibility = 8
        val arrayList = ArrayList<BlankCutBean>()
        if (MachineInfo.isChineseMachine()) {
            s1Clamp(arrayList)
        } else {
            s8Clamp(arrayList)
        }
        val blankCutAdapter = BlankCutAdapter(arrayList)
        this.blankCutAdapter = blankCutAdapter
        blankCutAdapter.setOnItemChildClickListener(this)
        binding!!.rvBlankCut.layoutManager = GridLayoutManager(
            context, 2
        )
        binding!!.rvBlankCut.adapter = this.blankCutAdapter
        binding!!.ivShow.setImageResource(R.drawable.blank_cut_width_big)
    }

    private fun s8Clamp(list: MutableList<BlankCutBean>) {
        val blankCutBean = BlankCutBean(
            BlankCutType.Toyota80K,
            "Repair Width Of 80K-Series Keys",
            R.drawable.blank_cut_width_big
        )
        blankCutBean.isChecked = true
        list.add(blankCutBean)
        list.add(
            BlankCutBean(
                BlankCutType.k9ToLxp90,
                "Create K9 From LXP90",
                R.drawable.blank_cut_width_big
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.K4080K,
                getString(R.string.key_40k_80k),
                R.drawable.blank_cut_k40k80
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.HY18,
                "Create HY18 From KK12",
                R.drawable.blank_cut_hy18
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.HY18R,
                "Create HY18R From KK12",
                R.drawable.blank_cut_hy18
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.KW16ToKW15,
                "Create KW15 From KW16",
                R.drawable.blank_cut_kw16_kw15
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.KW14ToKW15,
                "Create KW14 From KW15",
                R.drawable.blank_cut_kw16_kw15
            )
        )
    }

    private fun s1Clamp(list: MutableList<BlankCutBean>) {
        val blankCutBean = BlankCutBean(
            BlankCutType.THICKNESS,
            getString(R.string.create_thickness),
            R.drawable.blank_cut_thickness_big
        )
        blankCutBean.isChecked = true
        list.add(blankCutBean)
        list.add(
            BlankCutBean(
                BlankCutType.WIDTH,
                getString(R.string.create_width),
                R.drawable.blank_cut_width_big
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.LEFT_GROOVE,
                getString(R.string.left_groove),
                R.drawable.blank_cut_left_groove
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.RIGHT_GROOVE,
                getString(R.string.right_groove),
                R.drawable.blank_cut_right_groove
            )
        )
    }

    fun onclick(view: View) {
        if (view.id == R.id.bt_ok) {
            for (blankCutBean in blankCutAdapter!!.data) {
                if (blankCutBean!!.isChecked) {
                    when (blankCutBean.blankCutType) {
                        BlankCutType.KEY_HEAD -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        BlankCutType.THICKNESS -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        BlankCutType.WIDTH, BlankCutType.DRILLING, BlankCutType.LEFT_GROOVE -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        BlankCutType.RIGHT_GROOVE -> start(BlankCutDrillingFragment.newInstance(blankCutBean))
                        BlankCutType.KEY_TIP -> start(BlankCutLeftGrooveFragment.newInstance(blankCutBean))
                        BlankCutType.CREATE_GROOVE -> start(BlankCutRightGrooveFragment.newInstance(blankCutBean))
                        BlankCutType.SIDE_GROOVE -> start(BlankCutKeyTipFragment.newInstance(blankCutBean))
                        BlankCutType.HY18, BlankCutType.K4080K, BlankCutType.Toyota80K, BlankCutType.k9ToLxp90 -> start(
                            BlankCutCreateGrooveFragment.newInstance(
                                blankCutBean
                            )
                        )
                        BlankCutType.HY18R -> start(BlankCutSideGrooveFragment.newInstance(blankCutBean))
                        BlankCutType.KW16ToKW15, BlankCutType.LXP90To80K -> start(BlankCutLengthFragment.newInstance(blankCutBean))
                        BlankCutType.KW14ToKW15 -> start(BlankCutLXP90To80KFragment.newInstance(blankCutBean))
                        null -> return
                    }
                }
            }
        }
    }

    override fun setTitleStr(): String? {
        return getString(R.string.blank_cut)
    }

    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val data = this.blankCutAdapter!!.data
        val it: Iterator<*> = data.iterator()
        while (it.hasNext()) {
            (it.next() as BlankCutBean).isChecked = false
        }
        val blankCutBean = data[i] as BlankCutBean
        blankCutBean.isChecked = true
        this.blankCutAdapter!!.setNewData(data)
        binding!!.ivShow!!.setImageResource(blankCutBean.drawRes)
    }

    companion object {
        @JvmStatic
        fun newInstance(): SpecialBlankCutTypeSelectFragment {
            val bundle = Bundle()
            val specialBlankCutTypeSelectFragment = SpecialBlankCutTypeSelectFragment()
            specialBlankCutTypeSelectFragment.arguments = bundle
            return specialBlankCutTypeSelectFragment
        }
    }
}
