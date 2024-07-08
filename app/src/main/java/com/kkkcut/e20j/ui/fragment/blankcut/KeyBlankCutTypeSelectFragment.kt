package com.kkkcut.e20j.ui.fragment.blankcut

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutCreateGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutDrillingFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutKeyTipFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLeftGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutRightGrooveFragment
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutSideGrooveFragment
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class KeyBlankCutTypeSelectFragment : BaseBackFragment(),
    BaseQuickAdapter.OnItemChildClickListener {
    var blankCutAdapter: BlankCutAdapter? = null
    private val blankCutType: BlankCutType? = null

    var btOk: Button? = null

    var btPreset: Button? = null

    var ivShow: ImageView? = null

    var rvBlankCut: RecyclerView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_modify_key_blank_type
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val arrayList = ArrayList<BlankCutBean>()
        if (MachineInfo.isChineseMachine()) {
            s1Clamp(arrayList)
            btPreset!!.visibility = 8
        } else {
            s8Clamp(arrayList)
        }
        val blankCutAdapter = BlankCutAdapter(arrayList)
        this.blankCutAdapter = blankCutAdapter
        blankCutAdapter.setOnItemChildClickListener(this)
        rvBlankCut!!.layoutManager = GridLayoutManager(
            context, 2
        )
        rvBlankCut!!.adapter = this.blankCutAdapter
    }

    private fun s8Clamp(list: MutableList<BlankCutBean>) {
        val blankCutBean = BlankCutBean(
            BlankCutType.KEY_HEAD,
            getString(R.string.cut_blank_head),
            R.drawable.blank_cut_head_big
        )
        blankCutBean.isChecked = true
        list.add(blankCutBean)
        list.add(
            BlankCutBean(
                BlankCutType.THICKNESS,
                getString(R.string.create_thickness),
                R.drawable.blank_cut_thickness_big
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.WIDTH,
                getString(R.string.create_width),
                R.drawable.blank_cut_width_big
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.DRILLING,
                getString(R.string.key_head_drilling),
                R.drawable.blank_cut_drilling
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
        list.add(
            BlankCutBean(
                BlankCutType.KEY_TIP,
                getString(R.string.key_tip),
                R.drawable.blank_cut_key_tip
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.CREATE_GROOVE,
                getString(R.string.creat_groove),
                R.drawable.blank_cut_k40k80
            )
        )
        list.add(
            BlankCutBean(
                BlankCutType.SIDE_GROOVE,
                getString(R.string.side_groove),
                R.drawable.blank_cut_side_groove
            )
        )
    }

    private fun s1Clamp(list: MutableList<BlankCutBean>) {
        val blankCutBean = BlankCutBean(
            BlankCutType.THICKNESS,
            getString(R.string.thickness),
            R.drawable.blank_cut_thickness_big
        )
        blankCutBean.isChecked = true
        list.add(blankCutBean)
        list.add(
            BlankCutBean(
                BlankCutType.WIDTH,
                getString(R.string.width),
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
        val id = view.id
        if (id != R.id.bt_ok) {
            if (id != R.id.bt_preset) {
                return
            }
            start(SpecialBlankCutTypeSelectFragment.newInstance())
            return
        }
        val blankCutAdapter = this.blankCutAdapter
        if (blankCutAdapter != null) {
            for (blankCutBean in blankCutAdapter.data) {
                if (blankCutBean!!.isChecked) {
                    when (AnonymousClass1.blankCutType[blankCutBean.blankCutType!!.ordinal]) {
                        1 -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        2 -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        3 -> start(BlankCutHeadFragment.newInstance(blankCutBean))
                        4 -> start(BlankCutDrillingFragment.newInstance(blankCutBean))
                        5 -> start(BlankCutLeftGrooveFragment.newInstance(blankCutBean))
                        6 -> start(BlankCutRightGrooveFragment.newInstance(blankCutBean))
                        7 -> start(BlankCutKeyTipFragment.newInstance(blankCutBean))
                        8 -> start(BlankCutCreateGrooveFragment.newInstance(blankCutBean))
                        9, 10 -> start(BlankCutCreateGrooveFragment.newInstance(blankCutBean))
                        11 -> start(BlankCutSideGrooveFragment.newInstance(blankCutBean))
                    }
                }
            }
        }
    }

    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment$1, reason: invalid class name */ /* loaded from: classes.dex */
    internal object AnonymousClass1 {
        val blankCutType = IntArray(BlankCutType.entries.size)

        init {

            blankCutType[BlankCutType.KEY_HEAD.ordinal] = 1

            blankCutType[BlankCutType.THICKNESS.ordinal] =
                    2
            blankCutType[BlankCutType.WIDTH.ordinal] =
                    3
            blankCutType[BlankCutType.DRILLING.ordinal] =
                    4
            blankCutType[BlankCutType.LEFT_GROOVE.ordinal] =
                    5
            blankCutType[BlankCutType.RIGHT_GROOVE.ordinal] =
                    6
            blankCutType[BlankCutType.KEY_TIP.ordinal] =
                    7
            blankCutType[BlankCutType.CREATE_GROOVE.ordinal] =
                    8
            blankCutType[BlankCutType.HY18.ordinal] =
                    9
            blankCutType[BlankCutType.HY18R.ordinal] =
                    10
            blankCutType[BlankCutType.SIDE_GROOVE.ordinal] =
                    11
        }
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.blank_cut)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val baseQuickAdapter = adapter as BaseQuickAdapter<Any, *>
        val data = baseQuickAdapter.data
        val it: Iterator<*> = data.iterator()
        while (it.hasNext()) {
            (it.next() as BlankCutBean).isChecked = false
        }
        val blankCutBean = data[i] as BlankCutBean
        blankCutBean.isChecked = true
        baseQuickAdapter.setNewData(data)
        ivShow!!.setImageResource(blankCutBean.drawRes)
    }

    companion object {
        fun newInstance(): KeyBlankCutTypeSelectFragment {
            val bundle = Bundle()
            val keyBlankCutTypeSelectFragment = KeyBlankCutTypeSelectFragment()
            keyBlankCutTypeSelectFragment.arguments = bundle
            return keyBlankCutTypeSelectFragment
        }
    }
}
