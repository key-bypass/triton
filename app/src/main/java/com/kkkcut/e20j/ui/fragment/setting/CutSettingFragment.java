package com.kkkcut.e20j.ui.fragment.setting;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CutSettingFragment extends BaseBackFragment {
    public static final String CANCLE_REDUCE_LAST_CUT_FEED = "cancle_reduce_last_feed";
    public static final String CUT_MORE_THAN_ONCE = "cut_more_than_once";
    public static final String REDUCE_CUT_FEED = "reduce_feed";
    public static final String STRENGTHEN_ROUTE_PLAN = "strengthen_route_plan";
    public static final String TAG = "CutSettingFragment";

    CheckBox cbAngle1;

    CheckBox cbAngle2;

    CheckBox cbAngle3;

    CheckBox cbAngle4;

    CheckBox cbDimple1;

    CheckBox cbDimple2;

    CheckBox cbDimple3;

    CheckBox cbDimple4;

    CheckBox cbDoubleExternal1;

    CheckBox cbDoubleExternal2;

    CheckBox cbDoubleExternal3;

    CheckBox cbDoubleExternal4;

    CheckBox cbDoubleInternal1;

    CheckBox cbDoubleInternal2;

    CheckBox cbDoubleInternal3;

    CheckBox cbDoubleInternal4;

    CheckBox cbDoublekey1;

    CheckBox cbDoublekey2;

    CheckBox cbDoublekey3;

    CheckBox cbDoublekey4;

    CheckBox cbSingleExternal1;

    CheckBox cbSingleExternal2;

    CheckBox cbSingleExternal3;

    CheckBox cbSingleExternal4;

    CheckBox cbSingleInternal1;

    CheckBox cbSingleInternal2;

    CheckBox cbSingleInternal3;

    CheckBox cbSingleInternal4;

    CheckBox cbSinglekey1;

    CheckBox cbSinglekey2;

    CheckBox cbSinglekey3;

    CheckBox cbSinglekey4;

    CheckBox cbTubular1;

    CheckBox cbTubular2;

    CheckBox cbTubular3;

    CheckBox cbTubular4;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_cut_setting;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.cbSinglekey1.setChecked(SPUtils.getBoolean("cut_more_than_once1", false));
        this.cbDoublekey1.setChecked(SPUtils.getBoolean("cut_more_than_once0", true));
        this.cbSingleInternal1.setChecked(SPUtils.getBoolean("cut_more_than_once5", false));
        this.cbSingleExternal1.setChecked(SPUtils.getBoolean("cut_more_than_once3", false));
        this.cbDoubleInternal1.setChecked(SPUtils.getBoolean("cut_more_than_once2", false));
        this.cbDoubleExternal1.setChecked(SPUtils.getBoolean("cut_more_than_once4", false));
        this.cbDimple1.setChecked(SPUtils.getBoolean("cut_more_than_once6", false));
        this.cbTubular1.setChecked(SPUtils.getBoolean("cut_more_than_once8", false));
        this.cbAngle1.setChecked(SPUtils.getBoolean("cut_more_than_once7", false));
        this.cbSinglekey2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed1", false));
        this.cbDoublekey2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed0", false));
        this.cbSingleInternal2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed5", false));
        this.cbSingleExternal2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed3", false));
        this.cbDoubleInternal2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed2", false));
        this.cbDoubleExternal2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed4", false));
        this.cbDimple2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed6", false));
        this.cbTubular2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed8", false));
        this.cbAngle2.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed7", false));
        this.cbSinglekey3.setChecked(SPUtils.getBoolean("reduce_feed1", false));
        this.cbDoublekey3.setChecked(SPUtils.getBoolean("reduce_feed0", false));
        this.cbSingleInternal3.setChecked(SPUtils.getBoolean("reduce_feed5", false));
        this.cbSingleExternal3.setChecked(SPUtils.getBoolean("reduce_feed3", false));
        this.cbDoubleInternal3.setChecked(SPUtils.getBoolean("reduce_feed2", false));
        this.cbDoubleExternal3.setChecked(SPUtils.getBoolean("reduce_feed4", false));
        this.cbDimple3.setChecked(SPUtils.getBoolean("reduce_feed6", false));
        this.cbTubular3.setChecked(SPUtils.getBoolean("reduce_feed8", false));
        this.cbAngle3.setChecked(SPUtils.getBoolean("reduce_feed7", false));
        this.cbSinglekey4.setChecked(SPUtils.getBoolean("strengthen_route_plan1", false));
        this.cbDoublekey4.setChecked(SPUtils.getBoolean("strengthen_route_plan0", false));
        this.cbSingleInternal4.setChecked(SPUtils.getBoolean("strengthen_route_plan5", false));
        this.cbSingleExternal4.setChecked(SPUtils.getBoolean("strengthen_route_plan3", true));
        this.cbDoubleInternal4.setChecked(SPUtils.getBoolean("strengthen_route_plan2", false));
        this.cbDoubleExternal4.setChecked(SPUtils.getBoolean("strengthen_route_plan4", false));
        this.cbDimple4.setChecked(SPUtils.getBoolean("strengthen_route_plan6", false));
        this.cbTubular4.setChecked(SPUtils.getBoolean("strengthen_route_plan8", false));
        this.cbAngle4.setChecked(SPUtils.getBoolean("strengthen_route_plan7", false));
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        switch (id) {
            case R.id.cb_angle_1 /* 2131361995 */:
                SPUtils.put("cut_more_than_once7", z);
                return;
            case R.id.cb_angle_2 /* 2131361996 */:
                SPUtils.put("cancle_reduce_last_feed7", z);
                return;
            case R.id.cb_angle_3 /* 2131361997 */:
                SPUtils.put("reduce_feed7", z);
                return;
            case R.id.cb_angle_4 /* 2131361998 */:
                SPUtils.put("strengthen_route_plan7", z);
                return;
            default:
                switch (id) {
                    case R.id.cb_dimple_1 /* 2131362006 */:
                        SPUtils.put("cut_more_than_once6", z);
                        return;
                    case R.id.cb_dimple_2 /* 2131362007 */:
                        SPUtils.put("cancle_reduce_last_feed6", z);
                        return;
                    case R.id.cb_dimple_3 /* 2131362008 */:
                        SPUtils.put("reduce_feed6", z);
                        return;
                    case R.id.cb_dimple_4 /* 2131362009 */:
                        SPUtils.put("strengthen_route_plan6", z);
                        return;
                    default:
                        switch (id) {
                            case R.id.cb_double_external_1 /* 2131362011 */:
                                SPUtils.put("cut_more_than_once4", z);
                                return;
                            case R.id.cb_double_external_2 /* 2131362012 */:
                                SPUtils.put("cancle_reduce_last_feed4", z);
                                return;
                            case R.id.cb_double_external_3 /* 2131362013 */:
                                SPUtils.put("reduce_feed4", z);
                                return;
                            case R.id.cb_double_external_4 /* 2131362014 */:
                                SPUtils.put("strengthen_route_plan4", z);
                                return;
                            case R.id.cb_double_internal_1 /* 2131362015 */:
                                SPUtils.put("cut_more_than_once2", z);
                                return;
                            case R.id.cb_double_internal_2 /* 2131362016 */:
                                SPUtils.put("cancle_reduce_last_feed2", z);
                                return;
                            case R.id.cb_double_internal_3 /* 2131362017 */:
                                SPUtils.put("reduce_feed2", z);
                                return;
                            case R.id.cb_double_internal_4 /* 2131362018 */:
                                SPUtils.put("strengthen_route_plan2", z);
                                return;
                            case R.id.cb_doublekey_1 /* 2131362019 */:
                                SPUtils.put("cut_more_than_once0", z);
                                return;
                            case R.id.cb_doublekey_2 /* 2131362020 */:
                                SPUtils.put("cancle_reduce_last_feed0", z);
                                return;
                            case R.id.cb_doublekey_3 /* 2131362021 */:
                                SPUtils.put("reduce_feed0", z);
                                return;
                            case R.id.cb_doublekey_4 /* 2131362022 */:
                                SPUtils.put("strengthen_route_plan0", z);
                                return;
                            default:
                                switch (id) {
                                    case R.id.cb_single_external_1 /* 2131362033 */:
                                        SPUtils.put("cut_more_than_once3", z);
                                        return;
                                    case R.id.cb_single_external_2 /* 2131362034 */:
                                        SPUtils.put("cancle_reduce_last_feed3", z);
                                        return;
                                    case R.id.cb_single_external_3 /* 2131362035 */:
                                        SPUtils.put("reduce_feed3", z);
                                        return;
                                    case R.id.cb_single_external_4 /* 2131362036 */:
                                        SPUtils.put("strengthen_route_plan3", z);
                                        return;
                                    case R.id.cb_single_internal_1 /* 2131362037 */:
                                        SPUtils.put("cut_more_than_once5", z);
                                        return;
                                    case R.id.cb_single_internal_2 /* 2131362038 */:
                                        SPUtils.put("cancle_reduce_last_feed5", z);
                                        return;
                                    case R.id.cb_single_internal_3 /* 2131362039 */:
                                        SPUtils.put("reduce_feed5", z);
                                        return;
                                    case R.id.cb_single_internal_4 /* 2131362040 */:
                                        SPUtils.put("strengthen_route_plan5", z);
                                        return;
                                    case R.id.cb_singlekey_1 /* 2131362041 */:
                                        SPUtils.put("cut_more_than_once1", z);
                                        return;
                                    case R.id.cb_singlekey_2 /* 2131362042 */:
                                        SPUtils.put("cancle_reduce_last_feed1", z);
                                        return;
                                    case R.id.cb_singlekey_3 /* 2131362043 */:
                                        SPUtils.put("reduce_feed1", z);
                                        return;
                                    case R.id.cb_singlekey_4 /* 2131362044 */:
                                        SPUtils.put("strengthen_route_plan1", z);
                                        return;
                                    default:
                                        switch (id) {
                                            case R.id.cb_tubular_1 /* 2131362046 */:
                                                SPUtils.put("cut_more_than_once8", z);
                                                return;
                                            case R.id.cb_tubular_2 /* 2131362047 */:
                                                SPUtils.put("cancle_reduce_last_feed8", z);
                                                return;
                                            case R.id.cb_tubular_3 /* 2131362048 */:
                                                SPUtils.put("reduce_feed8", z);
                                                return;
                                            case R.id.cb_tubular_4 /* 2131362049 */:
                                                SPUtils.put("strengthen_route_plan8", z);
                                                return;
                                            default:
                                                return;
                                        }
                                }
                        }
                }
        }
    }
}
