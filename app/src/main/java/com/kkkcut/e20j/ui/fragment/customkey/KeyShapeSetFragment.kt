package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.SpecificParamUtils
import org.greenrobot.eventbus.EventBus
import kotlin.math.min

/* loaded from: classes.dex */
class KeyShapeSetFragment() : BaseBackFragment() {
    var cbExtraCut: CheckBox? = null
    private var customKey: CustomKey? = null

    var etAngle: EditText? = null

    var etCutDepth: EditText? = null

    var etGroove: EditText? = null

    var etGuide: EditText? = null

    var etNose: EditText? = null

    var etThickness: EditText? = null

    var etWidth: EditText? = null

    var ivAngle: ImageView? = null

    var ivCutDepth: ImageView? = null

    var ivGroove: ImageView? = null

    var ivGuide: ImageView? = null

    var ivNose: ImageView? = null

    var ivThick: ImageView? = null

    var ivWidth: ImageView? = null

    var llAngle: LinearLayout? = null

    var llCutDepth: LinearLayout? = null

    var llGroove: LinearLayout? = null

    var llGuide: LinearLayout? = null

    var llNose: LinearLayout? = null

    var llThickness: LinearLayout? = null

    var llWidth: LinearLayout? = null

    var tvUnit: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_shape
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val customKey: CustomKey? =
            getArguments()!!.getParcelable<Parcelable>(CUSTOMKEY) as CustomKey?
        this.customKey = customKey
        when (customKey!!.getType()) {
            0 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.doublekey_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.doublekey_width_tip)
                }
                llThickness!!.setVisibility(8)
                llCutDepth!!.setVisibility(8)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }

            1 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.singlekey_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.singlekey_width_tip)
                }
                llThickness!!.setVisibility(8)
                llCutDepth!!.setVisibility(8)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(0)
                ivAngle!!.setImageResource(R.drawable.single_side_cut_angle)
            }

            2 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.double_internal_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.double_internal_width_tip)
                }
                ivThick!!.setImageResource(R.drawable.laser_thick)
                ivCutDepth!!.setImageResource(R.drawable.double_internal_cutdepth)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                ivNose!!.setImageResource(R.drawable.double_internal_nose)
                llAngle!!.setVisibility(8)
            }

            3 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.single_external_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.single_external_width_tip)
                }
                ivThick!!.setImageResource(R.drawable.laser_thick)
                ivCutDepth!!.setImageResource(R.drawable.single_external_cutdepth)
                llGroove!!.setVisibility(8)
                ivGuide!!.setImageResource(R.drawable.single_external_guide)
                ivNose!!.setImageResource(R.drawable.single_external_nose)
                llAngle!!.setVisibility(8)
            }

            4 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.double_external_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.double_external_width_tip)
                }
                ivThick!!.setImageResource(R.drawable.laser_thick)
                ivCutDepth!!.setImageResource(R.drawable.double_external_cutdepth)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }

            5 -> {
                if (this.customKey!!.getAlign() == 0) {
                    ivWidth!!.setImageResource(R.drawable.single_internal_width_shoulder)
                } else {
                    ivWidth!!.setImageResource(R.drawable.single_internal_width_tip)
                }
                ivThick!!.setImageResource(R.drawable.laser_thick)
                ivCutDepth!!.setImageResource(R.drawable.single_internal_cut_depth)
                ivGroove!!.setImageResource(R.drawable.single_internal_grooveh)
                ivGuide!!.setImageResource(R.drawable.single_internal_guide)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }

            6 -> {
                llCutDepth!!.setVisibility(8)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }

            7 -> {
                ivWidth!!.setImageResource(R.drawable.tibbe_width)
                ivThick!!.setImageResource(R.drawable.tibbe_thick)
                llCutDepth!!.setVisibility(8)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }

            8 -> {
                ivWidth!!.setImageResource(R.drawable.tubular_width)
                ivThick!!.setImageResource(R.drawable.tubular_thick)
                ivCutDepth!!.setImageResource(R.drawable.tubular_cutdepth)
                llGroove!!.setVisibility(8)
                llGuide!!.setVisibility(8)
                llNose!!.setVisibility(8)
                llAngle!!.setVisibility(8)
            }
        }
        var width: Int = this.customKey!!.getWidth()
        if (width != 0) {
            if (this.customKey!!.isInch()) {
                width = Math.round(width / 2.54f)
            }
            etWidth!!.setText(width.toString())
        }
        var thick: Int = this.customKey!!.getThick()
        if (thick != 0) {
            if (this.customKey!!.isInch()) {
                thick = Math.round(thick / 2.54f)
            }
            etThickness!!.setText(thick.toString())
        }
        val parameter_info: String = this.customKey!!.getParameter_info()
        val param: String =
            SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.CUT_DEPTH)
        if (!TextUtils.isEmpty(param)) {
            if (this.customKey!!.isInch()) {
                etCutDepth!!.setText(Math.round(param.toInt() / 2.54f).toString())
            } else {
                etCutDepth!!.setText(param)
            }
        }
        val param2: String = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.GROOVE)
        if (!TextUtils.isEmpty(param2)) {
            if (this.customKey!!.isInch()) {
                etGroove!!.setText(Math.round(param2.toInt() / 2.54f).toString())
            } else {
                etGroove!!.setText(param2)
            }
        }
        val param3: String = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.GUIDE)
        if (!TextUtils.isEmpty(param3)) {
            if (this.customKey!!.isInch()) {
                etGuide!!.setText(Math.round(param3.toInt() / 2.54f).toString())
            } else {
                etGuide!!.setText(param3)
            }
        }
        val param4: String = SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.NOSE)
        if (!TextUtils.isEmpty(param4)) {
            if (this.customKey!!.isInch()) {
                etNose!!.setText(Math.round(param4.toInt() / 2.54f).toString())
            } else {
                etNose!!.setText(param4)
            }
        }
        val param5: String =
            SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.EXTRA_CUT)
        if (!TextUtils.isEmpty(param5) && (param5 == "1")) {
            cbExtraCut!!.setChecked(true)
        }
        val param6: String =
            SpecificParamUtils.getParam(parameter_info, SpecificParamUtils.SINGLE_SIDE_ANGLE)
        if (!TextUtils.isEmpty(param6)) {
            if (this.customKey!!.isInch()) {
                etAngle!!.setText(Math.round(param6.toInt() / 2.54f).toString())
            } else {
                etAngle!!.setText(param6)
            }
        }
        if (this.customKey!!.isInch()) {
            tvUnit!!.setText(R.string._1inch_1000)
        }
    }

    fun onFocusChanged(view: View, z: Boolean) {
        val id: Int = view.getId()
        if (id == R.id.et_guide) {
            if (z) {
                etNose!!.setText("")
            }
        } else if (id == R.id.et_nose && z) {
            etGuide!!.setText("")
        }
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.bt_last) {
            onBack()
            return
        }
        if (id != R.id.bt_next) {
            return
        }
        val trim: String = etWidth!!.getText().toString().trim({ it <= ' ' })
        if (!TextUtils.isEmpty(trim)) {
            var parseInt: Int = trim.toInt()
            if (customKey!!.isInch()) {
                parseInt = Math.round(parseInt * 2.54f)
            }
            customKey!!.setWidth(parseInt)
        }
        val trim2: String = etThickness!!.getText().toString().trim({ it <= ' ' })
        if (!TextUtils.isEmpty(trim2)) {
            var parseInt2: Int = trim2.toInt()
            if (customKey!!.isInch()) {
                parseInt2 = Math.round(parseInt2 * 2.54f)
            }
            customKey!!.setThick(parseInt2)
        }
        val parameter_info: String = customKey!!.getParameter_info()
        var trim3: String = etCutDepth!!.getText().toString().trim({ it <= ' ' })
        if (customKey!!.isInch() and (!TextUtils.isEmpty(trim3))) {
            trim3 = Math.round(trim3.toInt() * 2.54f).toString()
        }
        val putParam: String =
            SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.CUT_DEPTH, trim3)
        var trim4: String = etGroove!!.getText().toString().trim({ it <= ' ' })
        if (customKey!!.isInch() and (!TextUtils.isEmpty(trim4))) {
            trim4 = Math.round(trim4.toInt() * 2.54f).toString()
        }
        var putParam2: String? =
            SpecificParamUtils.putParam(putParam, SpecificParamUtils.GROOVE, trim4)
        if (!TextUtils.isEmpty(trim4) && trim4.toInt() < 230) {
            putParam2 = SpecificParamUtils.putParam(putParam2, "cutter", "T60-E15-P,1.5")
        }
        var trim5: String = etGuide!!.getText().toString().trim({ it <= ' ' })
        if (customKey!!.isInch() && !TextUtils.isEmpty(trim5)) {
            trim5 = Math.round(trim5.toInt() * 2.54f).toString()
        }
        val putParam3: String =
            SpecificParamUtils.putParam(putParam2, SpecificParamUtils.GUIDE, trim5)
        var trim6: String = etNose!!.getText().toString().trim({ it <= ' ' })
        if (customKey!!.isInch() && !TextUtils.isEmpty(trim6)) {
            trim6 = Math.round(trim6.toInt() * 2.54f).toString()
        }
        var putParam4: String? =
            SpecificParamUtils.putParam(putParam3, SpecificParamUtils.NOSE, trim6)
        if (cbExtraCut!!.isChecked()) {
            putParam4 = SpecificParamUtils.putParam(putParam4, SpecificParamUtils.EXTRA_CUT, "1")
        }
        if (customKey!!.getType() == 4 && customKey!!.getAlign() == 1) {
            putParam4 = SpecificParamUtils.putParam(putParam4, SpecificParamUtils.LAST_BITTING, "1")
        }
        if (customKey!!.getType() == 2) {
            putParam4 =
                SpecificParamUtils.putParam(putParam4, SpecificParamUtils.INNER_CUT_ANGLE, "1")
        }
        var trim7: String = etAngle!!.getText().toString().trim({ it <= ' ' })
        if (customKey!!.isInch() && !TextUtils.isEmpty(trim7)) {
            trim7 = Math.round(trim7.toInt() * 2.54f).toString()
        }
        customKey!!.setParameter_info(
            SpecificParamUtils.putParam(
                putParam4,
                SpecificParamUtils.SINGLE_SIDE_ANGLE,
                trim7
            )
        )
        when (customKey!!.type) {
            0 -> if (MachineInfo.isE9Standard(getContext())) {
                customKey!!.clampNum = "S1"
                customKey!!.clampSide = "C"
                customKey!!.clampSlot = "0"
            } else {
                start(KeyClampSetFragment.Companion.newInstance(this.customKey))
                return
            }

            1 -> {
                val split: Array<String> =
                    customKey!!.depth.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[0].split(",".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                val min: Int = min(split[0].toInt(), split[split.size - 1].toInt())
                customKey!!.clampNum = "S2"
                customKey!!.clampSlot = "0"
                if (min > 510) {
                    customKey!!.clampSide = "A"
                } else {
                    customKey!!.setClampSide("B")
                }
            }

            2, 3, 4, 5, 6 -> if (MachineInfo.isE9Standard(getContext())) {
                customKey!!.clampNum = "S1"
                customKey!!.clampSide = "A"
                customKey!!.clampSlot = "0"
            } else {
                start(KeyClampSetFragment.newInstance(this.customKey))
                return
            }

            7 -> {
                customKey!!.setClampNum("S4")
                customKey!!.setClampSide("A")
                customKey!!.setClampSlot("0")
            }

            8 -> {
                customKey!!.setClampNum("S3")
                customKey!!.setClampSide("A")
                customKey!!.setClampSlot("0")
            }
        }
        showEditDialog(this.customKey)
    }

    private fun showEditDialog(customKey: CustomKey?) {
        val editDialog = EditDialog(getContext())
        val keyname: String = customKey!!.getKeyname()
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname)
        }
        editDialog.setTip(getString(R.string.please_input_the_key_name))
        editDialog.setDialogBtnCallback { str ->
            customKey.keyname = str
            UserDataDaoManager.getInstance(this@KeyShapeSetFragment.getContext())
                .saveCustomKey(customKey)
            this@KeyShapeSetFragment.start(CustomKeyListFragment.Companion.newInstance(), 2)
            EventBus.getDefault().post(EventCenter<Any?>(11))
        }
        editDialog.show()
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        if (compoundButton.getId() != R.id.cb_extra_cut) {
            return
        }
        if (z) {
            ivGroove!!.setImageResource(R.drawable.single_internal_grooveh_extra)
        } else {
            ivGroove!!.setImageResource(R.drawable.single_internal_grooveh)
        }
    }

    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"

        fun newInstance(customKey: CustomKey?): KeyShapeSetFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            val keyShapeSetFragment: KeyShapeSetFragment = KeyShapeSetFragment()
            keyShapeSetFragment.setArguments(bundle)
            return keyShapeSetFragment
        }
    }
}
