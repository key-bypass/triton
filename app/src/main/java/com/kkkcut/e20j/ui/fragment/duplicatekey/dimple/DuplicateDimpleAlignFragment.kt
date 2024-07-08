package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple

import android.app.AlertDialog
import android.content.DialogInterface
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple
import com.kkkcut.e20j.adapter.DimpleDataAdapter
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class DuplicateDimpleAlignFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener,
    BaseQuickAdapter.OnItemChildClickListener {
    var adapter: DimpleDataAdapter? = null

    var cbSide: CheckBox? = null

    var cbZiMuZhu: CheckBox? = null

    var ivClamp: ImageView? = null
    private var keyInfo: KeyInfo? = null

    var rbShoulder: RadioButton? = null

    var rbTip: RadioButton? = null

    var rvData: RecyclerView? = null
    private var side: Boolean = false

    var tvNumber: TextView? = null

    var tvRowNumber: TextView? = null
    private var ziMuZhu: Boolean = false
    private var bittingCount: Int = 5
    private var rowCount: Int = 1

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_duplicate_dimple_align
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val keyInfo: KeyInfo = KeyInfo()
        this.keyInfo = keyInfo
        keyInfo.setType(6)
        val dimpleDataAdapter: DimpleDataAdapter = DimpleDataAdapter()
        this.adapter = dimpleDataAdapter
        dimpleDataAdapter.setOnItemChildClickListener(this)
        rvData!!.setAdapter(this.adapter)
        adapter!!.setOnItemClickListener(this)
        rvData!!.setLayoutManager(LinearLayoutManager(getContext()))
        initData()
    }

    private fun initData() {
        addDisposable(
            Observable.fromCallable(object : Callable<List<DuplicateDimple>> {
                // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.2
                @Throws(Exception::class)  // java.util.concurrent.Callable
                override fun call(): List<DuplicateDimple> {
                    return UserDataDaoManager.getInstance(this@DuplicateDimpleAlignFragment.getContext())
                        .getDuplicateDimpleKeys()
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                        adapter!!.setNewData(list)
                    }, { dismissLoadingDialog() })
        )
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.getId()) {
            R.id.cb_side -> {
                this.side = z
                if (z) {
                    if (keyInfo!!.getAlign() == 0) {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_shoulder_side)
                        return
                    } else {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip_side)
                        return
                    }
                }
                if (keyInfo!!.getAlign() == 0) {
                    ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_shoulder)
                    return
                } else {
                    ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip)
                    return
                }
            }

            R.id.cb_zimuzhu -> {
                this.ziMuZhu = z
                return
            }

            R.id.rb_shoulder -> {
                if (z) {
                    if (this.side) {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_shoulder_side)
                    } else {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_shoulder)
                    }
                    keyInfo!!.setAlign(0)
                    return
                }
                return
            }

            R.id.rb_tip -> {
                if (z) {
                    if (this.side) {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip_side)
                    } else {
                        ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip)
                    }
                    keyInfo!!.setAlign(1)
                    return
                }
                return
            }

            else -> return
        }
    }

    fun onclick(view: View) {
        when (view.getId()) {
            R.id.bt_ok -> {
                keyInfo!!.setWidth(0)
                keyInfo!!.setThick(0)
                keyInfo!!.setRow_pos("")
                keyInfo!!.setSpaceStr("")
                keyInfo!!.setSpaceWidthStr("")
                keyInfo!!.setDepthStr("")
                keyInfo!!.setDepthName("")
                goDimpleData()
                return
            }

            R.id.tv_number_add -> {
                val i: Int = this.bittingCount
                if (i < 12) {
                    this.bittingCount = i + 1
                }
                tvNumber!!.setText(bittingCount.toString())
                return
            }

            R.id.tv_number_reduce -> {
                val i2: Int = this.bittingCount
                if (i2 > 1) {
                    this.bittingCount = i2 - 1
                }
                tvNumber!!.setText(bittingCount.toString())
                return
            }

            R.id.tv_row_add -> {
                val i3: Int = this.rowCount
                if (i3 < 4) {
                    this.rowCount = i3 + 1
                }
                tvRowNumber!!.setText(rowCount.toString())
                return
            }

            R.id.tv_row_reduce -> {
                val i4: Int = this.rowCount
                if (i4 > 1) {
                    this.rowCount = i4 - 1
                }
                tvRowNumber!!.setText(rowCount.toString())
                return
            }

            else -> return
        }
    }

    private fun goDimpleData() {
        val clampBean: ClampBean = ClampBean()
        clampBean.setClampNum("S1")
        clampBean.setClampSide("B")
        clampBean.setClampSlot(if (this.side) "1" else "0")
        keyInfo!!.setClampKeyBasicData(clampBean)
        keyInfo!!.setKeyToothCode("")
        start(
            DuplicateDimpleDataFragment.Companion.newInstance(
                this.keyInfo,
                this.bittingCount,
                this.ziMuZhu,
                this.rowCount
            )
        )
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>?, view: View, i: Int) {
        val duplicateDimple: DuplicateDimple = adapter!!.getData().get(i)
        val id: Int = view.getId()
        if (id == R.id.iv_delete) {
            AlertDialog.Builder(getContext()).setMessage(R.string.the_key_profile_will_be_deleted)
                .setPositiveButton(
                    R.string.ok, object : DialogInterface.OnClickListener {
                        // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.3
                        // android.content.DialogInterface.OnClickListener
                        override fun onClick(dialogInterface: DialogInterface, i2: Int) {
                            UserDataDaoManager.getInstance(this@DuplicateDimpleAlignFragment.getContext())
                                .deleteDuplicateDimpleKey(duplicateDimple)
                            adapter!!.remove(i)
                        }
                    }).setNegativeButton(R.string.cancel, null as DialogInterface.OnClickListener?)
                .show()
        } else if (id == R.id.iv_edit) {
            showEditDialog(
                duplicateDimple,
                (view.getParent() as View).findViewById<View>(R.id.title) as TextView
            )
        } else {
            if (id != R.id.iv_export) {
                return
            }
            AlertDialog.Builder(getContext())
                .setMessage(R.string.import_this_data_into_the_custom_key).setPositiveButton(
                R.string.ok, object : DialogInterface.OnClickListener {
                    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.4
                    // android.content.DialogInterface.OnClickListener
                    override fun onClick(dialogInterface: DialogInterface, i2: Int) {
                        UserDataDaoManager.getInstance(this@DuplicateDimpleAlignFragment.getContext())
                            .saveCustomKeyFromDimpleDup(duplicateDimple)
                        ToastUtil.showToast(R.string.finish)
                    }
                }).setNegativeButton(R.string.cancel, null as DialogInterface.OnClickListener?)
                .show()
        }
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>?, view: View, i: Int) {
        val duplicateDimple: DuplicateDimple = adapter!!.getData().get(i)
        if (duplicateDimple.getAlign() == 1) {
            rbTip!!.performClick()
        } else {
            rbShoulder!!.performClick()
        }
        val space: String = duplicateDimple.getSpace()
        if (!TextUtils.isEmpty(space)) {
            val length: Int =
                space.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().get(0)
                    .split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().size
            this.bittingCount = length
            tvNumber!!.setText(length.toString())
        }
        keyInfo!!.setSpaceStr(space)
        keyInfo!!.setRow_pos(duplicateDimple.getRow_pos())
        val clampSlot: String = duplicateDimple.getClampSlot()
        if (!TextUtils.isEmpty(clampSlot) && (clampSlot == "1")) {
            cbSide!!.setChecked(true)
        } else {
            cbSide!!.setChecked(false)
        }
        if (duplicateDimple.getSpace_width().contains("-")) {
            cbZiMuZhu!!.setChecked(true)
            var row_count: Int = duplicateDimple.getRow_count()
            if (row_count == 0) {
                row_count = space.split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size / 2
            }
            this.rowCount = row_count
            tvRowNumber!!.setText(row_count.toString())
        } else {
            cbZiMuZhu!!.setChecked(false)
            var row_count2: Int = duplicateDimple.getRow_count()
            if (row_count2 == 0) {
                row_count2 =
                    space.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().size
            }
            this.rowCount = row_count2
            tvRowNumber!!.setText(row_count2.toString())
        }
        goDimpleData()
    }

    private fun showEditDialog(duplicateDimple: DuplicateDimple, textView: TextView) {
        val keyname: String = duplicateDimple.getKeyname()
        val editDialog: EditDialog = EditDialog(getContext())
        editDialog.setTip(getString(R.string.enter_remarks))
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname)
        }
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.5
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                if (TextUtils.isEmpty(str) || (str == keyname)) {
                    return
                }
                textView.setText(str)
                duplicateDimple.setKeyname(str)
                UserDataDaoManager.getInstance(this@DuplicateDimpleAlignFragment.getContext())
                    .saveDuplicateDimpleKey(duplicateDimple)
            }
        })
        editDialog.show()
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (eventCenter.getEventCode() == 46) {
            initData()
        }
    }

    companion object {
        fun newInstance(): DuplicateDimpleAlignFragment {
            return DuplicateDimpleAlignFragment()
        }
    }
}
