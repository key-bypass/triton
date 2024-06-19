package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SpeedSetFragment_ViewBinding implements Unbinder {
    private SpeedSetFragment target;

    public SpeedSetFragment_ViewBinding(SpeedSetFragment speedSetFragment, View view) {
        this.target = speedSetFragment;
        speedSetFragment.seekbarSingleKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_single_key, "field 'seekbarSingleKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarDoubleKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_double_key, "field 'seekbarDoubleKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarSingleInsideKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_single_inside_key, "field 'seekbarSingleInsideKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarSingleOutsideKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_single_outside_key, "field 'seekbarSingleOutsideKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarDoubleOutsideKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_double_outside_key, "field 'seekbarDoubleOutsideKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarDoubleInsideKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_double_inside_key, "field 'seekbarDoubleInsideKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarDimpleKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_dimple_key, "field 'seekbarDimpleKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarTubularKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_tubular_key, "field 'seekbarTubularKey'", BubbleSeekBar.class);
        speedSetFragment.seekbarAngleKey = (BubbleSeekBar) Utils.findRequiredViewAsType(view, R.id.seekbar_angle_key, "field 'seekbarAngleKey'", BubbleSeekBar.class);
        speedSetFragment.llTubular = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_tubular, "field 'llTubular'", LinearLayout.class);
        speedSetFragment.llDimple = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_dimple, "field 'llDimple'", LinearLayout.class);
        speedSetFragment.llTibbe = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_tibbe, "field 'llTibbe'", LinearLayout.class);
        speedSetFragment.llSingleStandard = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_single_standard, "field 'llSingleStandard'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SpeedSetFragment speedSetFragment = this.target;
        if (speedSetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        speedSetFragment.seekbarSingleKey = null;
        speedSetFragment.seekbarDoubleKey = null;
        speedSetFragment.seekbarSingleInsideKey = null;
        speedSetFragment.seekbarSingleOutsideKey = null;
        speedSetFragment.seekbarDoubleOutsideKey = null;
        speedSetFragment.seekbarDoubleInsideKey = null;
        speedSetFragment.seekbarDimpleKey = null;
        speedSetFragment.seekbarTubularKey = null;
        speedSetFragment.seekbarAngleKey = null;
        speedSetFragment.llTubular = null;
        speedSetFragment.llDimple = null;
        speedSetFragment.llTibbe = null;
        speedSetFragment.llSingleStandard = null;
    }
}
