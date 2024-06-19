package com.kkkcut.e20j.ui.fragment.setting;

import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.base.BaseFFragment;
import com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;

/* loaded from: classes.dex */
public class SpeedSetFragment extends BaseFFragment {
    public static final String TAG = "SpeedSetFragment";

    @BindView(R.id.ll_dimple)
    LinearLayout llDimple;

    @BindView(R.id.ll_single_standard)
    LinearLayout llSingleStandard;

    @BindView(R.id.ll_tibbe)
    LinearLayout llTibbe;

    @BindView(R.id.ll_tubular)
    LinearLayout llTubular;

    @BindView(R.id.seekbar_angle_key)
    BubbleSeekBar seekbarAngleKey;

    @BindView(R.id.seekbar_dimple_key)
    BubbleSeekBar seekbarDimpleKey;

    @BindView(R.id.seekbar_double_inside_key)
    BubbleSeekBar seekbarDoubleInsideKey;

    @BindView(R.id.seekbar_double_key)
    BubbleSeekBar seekbarDoubleKey;

    @BindView(R.id.seekbar_double_outside_key)
    BubbleSeekBar seekbarDoubleOutsideKey;

    @BindView(R.id.seekbar_single_inside_key)
    BubbleSeekBar seekbarSingleInsideKey;

    @BindView(R.id.seekbar_single_key)
    BubbleSeekBar seekbarSingleKey;

    @BindView(R.id.seekbar_single_outside_key)
    BubbleSeekBar seekbarSingleOutsideKey;

    @BindView(R.id.seekbar_tubular_key)
    BubbleSeekBar seekbarTubularKey;
    private int defaultSpeed = 15;
    private int defaultDimpleSpeed = 3;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_speed_set;
    }

    public static SpeedSetFragment newInstance() {
        Bundle bundle = new Bundle();
        SpeedSetFragment speedSetFragment = new SpeedSetFragment();
        speedSetFragment.setArguments(bundle);
        return speedSetFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.seekbarSingleKey.setProgress(SPUtils.getInt("speed1", this.defaultSpeed));
        this.seekbarSingleKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(1));
        this.seekbarDoubleKey.setProgress(SPUtils.getInt("speed0", this.defaultSpeed));
        this.seekbarDoubleKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(0));
        int i = SPUtils.getInt("speed5", this.defaultSpeed);
        this.seekbarSingleInsideKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(5));
        this.seekbarSingleInsideKey.setProgress(i);
        this.seekbarSingleOutsideKey.setProgress(SPUtils.getInt("speed3", this.defaultSpeed));
        this.seekbarSingleOutsideKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(3));
        this.seekbarDoubleInsideKey.setProgress(SPUtils.getInt("speed2", this.defaultSpeed));
        this.seekbarDoubleInsideKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(2));
        this.seekbarDoubleOutsideKey.setProgress(SPUtils.getInt("speed4", this.defaultSpeed));
        this.seekbarDoubleOutsideKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(4));
        this.seekbarAngleKey.setProgress(SPUtils.getInt("speed7", this.defaultSpeed));
        this.seekbarAngleKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(7));
        this.seekbarDimpleKey.setProgress(SPUtils.getInt("speed6", this.defaultDimpleSpeed));
        this.seekbarDimpleKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(6));
        this.seekbarTubularKey.setProgress(SPUtils.getInt("speed8", this.defaultSpeed));
        this.seekbarTubularKey.setOnProgressChangedListener(new MyOnSeekBarChangeListener(8));
        if (MachineInfo.isChineseMachine()) {
            this.llSingleStandard.setVisibility(8);
            this.llDimple.setVisibility(8);
            this.llTubular.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    private static class MyOnSeekBarChangeListener implements BubbleSeekBar.OnProgressChangedListener {
        private int keyType;

        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }

        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }

        public MyOnSeekBarChangeListener(int i) {
            this.keyType = i;
        }

        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
            SPUtils.put(SpKeys.SPEED + this.keyType, i);
        }
    }
}
