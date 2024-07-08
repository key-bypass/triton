package com.kkkcut.e20j.ui.fragment.setting

import android.os.Bundle
import android.widget.LinearLayout
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.base.BaseFFragment
import com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar
import com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class SpeedSetFragment() : BaseFFragment() {
    var llDimple: LinearLayout? = null

    var llSingleStandard: LinearLayout? = null

    var llTibbe: LinearLayout? = null

    var llTubular: LinearLayout? = null

    var seekbarAngleKey: BubbleSeekBar? = null

    var seekbarDimpleKey: BubbleSeekBar? = null

    var seekbarDoubleInsideKey: BubbleSeekBar? = null

    var seekbarDoubleKey: BubbleSeekBar? = null

    var seekbarDoubleOutsideKey: BubbleSeekBar? = null

    var seekbarSingleInsideKey: BubbleSeekBar? = null

    var seekbarSingleKey: BubbleSeekBar? = null

    var seekbarSingleOutsideKey: BubbleSeekBar? = null

    var seekbarTubularKey: BubbleSeekBar? = null
    private val defaultSpeed: Int = 15
    private val defaultDimpleSpeed: Int = 3

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_speed_set
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        seekbarSingleKey!!.setProgress(SPUtils.getInt("speed1", this.defaultSpeed).toFloat())
        seekbarSingleKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(1))
        seekbarDoubleKey!!.setProgress(SPUtils.getInt("speed0", this.defaultSpeed).toFloat())
        seekbarDoubleKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(0))
        val i: Int = SPUtils.getInt("speed5", this.defaultSpeed)
        seekbarSingleInsideKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(5))
        seekbarSingleInsideKey!!.setProgress(i.toFloat())
        seekbarSingleOutsideKey!!.setProgress(SPUtils.getInt("speed3", this.defaultSpeed).toFloat())
        seekbarSingleOutsideKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(3))
        seekbarDoubleInsideKey!!.setProgress(SPUtils.getInt("speed2", this.defaultSpeed).toFloat())
        seekbarDoubleInsideKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(2))
        seekbarDoubleOutsideKey!!.setProgress(SPUtils.getInt("speed4", this.defaultSpeed).toFloat())
        seekbarDoubleOutsideKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(4))
        seekbarAngleKey!!.setProgress(SPUtils.getInt("speed7", this.defaultSpeed).toFloat())
        seekbarAngleKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(7))
        seekbarDimpleKey!!.setProgress(SPUtils.getInt("speed6", this.defaultDimpleSpeed).toFloat())
        seekbarDimpleKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(6))
        seekbarTubularKey!!.setProgress(SPUtils.getInt("speed8", this.defaultSpeed).toFloat())
        seekbarTubularKey!!.setOnProgressChangedListener(MyOnSeekBarChangeListener(8))
        if (MachineInfo.isChineseMachine()) {
            llSingleStandard!!.setVisibility(8)
            llDimple!!.setVisibility(8)
            llTubular!!.setVisibility(8)
        }
    }

    /* loaded from: classes.dex */
    private class MyOnSeekBarChangeListener(private val keyType: Int) : OnProgressChangedListener {
        // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        override fun getProgressOnFinally(
            bubbleSeekBar: BubbleSeekBar,
            i: Int,
            f: Float,
            z: Boolean
        ) {
        }

        // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        override fun onProgressChanged(bubbleSeekBar: BubbleSeekBar, i: Int, f: Float, z: Boolean) {
        }

        // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        override fun getProgressOnActionUp(bubbleSeekBar: BubbleSeekBar, i: Int, f: Float) {
            SPUtils.put(SpKeys.SPEED + this.keyType, i)
        }
    }

    companion object {
        val TAG: String = "SpeedSetFragment"

        fun newInstance(): SpeedSetFragment {
            val bundle: Bundle = Bundle()
            val speedSetFragment: SpeedSetFragment = SpeedSetFragment()
            speedSetFragment.setArguments(bundle)
            return speedSetFragment
        }
    }
}
