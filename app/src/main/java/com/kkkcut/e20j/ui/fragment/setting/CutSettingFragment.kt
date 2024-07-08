package com.kkkcut.e20j.ui.fragment.setting

import android.widget.CheckBox
import android.widget.CompoundButton
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class CutSettingFragment() : BaseBackFragment() {
    var cbAngle1: CheckBox? = null

    var cbAngle2: CheckBox? = null

    var cbAngle3: CheckBox? = null

    var cbAngle4: CheckBox? = null

    var cbDimple1: CheckBox? = null

    var cbDimple2: CheckBox? = null

    var cbDimple3: CheckBox? = null

    var cbDimple4: CheckBox? = null

    var cbDoubleExternal1: CheckBox? = null

    var cbDoubleExternal2: CheckBox? = null

    var cbDoubleExternal3: CheckBox? = null

    var cbDoubleExternal4: CheckBox? = null

    var cbDoubleInternal1: CheckBox? = null

    var cbDoubleInternal2: CheckBox? = null

    var cbDoubleInternal3: CheckBox? = null

    var cbDoubleInternal4: CheckBox? = null

    var cbDoublekey1: CheckBox? = null

    var cbDoublekey2: CheckBox? = null

    var cbDoublekey3: CheckBox? = null

    var cbDoublekey4: CheckBox? = null

    var cbSingleExternal1: CheckBox? = null

    var cbSingleExternal2: CheckBox? = null

    var cbSingleExternal3: CheckBox? = null

    var cbSingleExternal4: CheckBox? = null

    var cbSingleInternal1: CheckBox? = null

    var cbSingleInternal2: CheckBox? = null

    var cbSingleInternal3: CheckBox? = null

    var cbSingleInternal4: CheckBox? = null

    var cbSinglekey1: CheckBox? = null

    var cbSinglekey2: CheckBox? = null

    var cbSinglekey3: CheckBox? = null

    var cbSinglekey4: CheckBox? = null

    var cbTubular1: CheckBox? = null

    var cbTubular2: CheckBox? = null

    var cbTubular3: CheckBox? = null

    var cbTubular4: CheckBox? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_cut_setting
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        cbSinglekey1!!.setChecked(SPUtils.getBoolean("cut_more_than_once1", false))
        cbDoublekey1!!.setChecked(SPUtils.getBoolean("cut_more_than_once0", true))
        cbSingleInternal1!!.setChecked(SPUtils.getBoolean("cut_more_than_once5", false))
        cbSingleExternal1!!.setChecked(SPUtils.getBoolean("cut_more_than_once3", false))
        cbDoubleInternal1!!.setChecked(SPUtils.getBoolean("cut_more_than_once2", false))
        cbDoubleExternal1!!.setChecked(SPUtils.getBoolean("cut_more_than_once4", false))
        cbDimple1!!.setChecked(SPUtils.getBoolean("cut_more_than_once6", false))
        cbTubular1!!.setChecked(SPUtils.getBoolean("cut_more_than_once8", false))
        cbAngle1!!.setChecked(SPUtils.getBoolean("cut_more_than_once7", false))
        cbSinglekey2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed1", false))
        cbDoublekey2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed0", false))
        cbSingleInternal2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed5", false))
        cbSingleExternal2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed3", false))
        cbDoubleInternal2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed2", false))
        cbDoubleExternal2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed4", false))
        cbDimple2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed6", false))
        cbTubular2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed8", false))
        cbAngle2!!.setChecked(SPUtils.getBoolean("cancle_reduce_last_feed7", false))
        cbSinglekey3!!.setChecked(SPUtils.getBoolean("reduce_feed1", false))
        cbDoublekey3!!.setChecked(SPUtils.getBoolean("reduce_feed0", false))
        cbSingleInternal3!!.setChecked(SPUtils.getBoolean("reduce_feed5", false))
        cbSingleExternal3!!.setChecked(SPUtils.getBoolean("reduce_feed3", false))
        cbDoubleInternal3!!.setChecked(SPUtils.getBoolean("reduce_feed2", false))
        cbDoubleExternal3!!.setChecked(SPUtils.getBoolean("reduce_feed4", false))
        cbDimple3!!.setChecked(SPUtils.getBoolean("reduce_feed6", false))
        cbTubular3!!.setChecked(SPUtils.getBoolean("reduce_feed8", false))
        cbAngle3!!.setChecked(SPUtils.getBoolean("reduce_feed7", false))
        cbSinglekey4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan1", false))
        cbDoublekey4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan0", false))
        cbSingleInternal4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan5", false))
        cbSingleExternal4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan3", true))
        cbDoubleInternal4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan2", false))
        cbDoubleExternal4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan4", false))
        cbDimple4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan6", false))
        cbTubular4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan8", false))
        cbAngle4!!.setChecked(SPUtils.getBoolean("strengthen_route_plan7", false))
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        val id: Int = compoundButton.getId()
        when (id) {
            R.id.cb_angle_1 -> {
                SPUtils.put("cut_more_than_once7", z)
                return
            }

            R.id.cb_angle_2 -> {
                SPUtils.put("cancle_reduce_last_feed7", z)
                return
            }

            R.id.cb_angle_3 -> {
                SPUtils.put("reduce_feed7", z)
                return
            }

            R.id.cb_angle_4 -> {
                SPUtils.put("strengthen_route_plan7", z)
                return
            }

            else -> when (id) {
                R.id.cb_dimple_1 -> {
                    SPUtils.put("cut_more_than_once6", z)
                    return
                }

                R.id.cb_dimple_2 -> {
                    SPUtils.put("cancle_reduce_last_feed6", z)
                    return
                }

                R.id.cb_dimple_3 -> {
                    SPUtils.put("reduce_feed6", z)
                    return
                }

                R.id.cb_dimple_4 -> {
                    SPUtils.put("strengthen_route_plan6", z)
                    return
                }

                else -> when (id) {
                    R.id.cb_double_external_1 -> {
                        SPUtils.put("cut_more_than_once4", z)
                        return
                    }

                    R.id.cb_double_external_2 -> {
                        SPUtils.put("cancle_reduce_last_feed4", z)
                        return
                    }

                    R.id.cb_double_external_3 -> {
                        SPUtils.put("reduce_feed4", z)
                        return
                    }

                    R.id.cb_double_external_4 -> {
                        SPUtils.put("strengthen_route_plan4", z)
                        return
                    }

                    R.id.cb_double_internal_1 -> {
                        SPUtils.put("cut_more_than_once2", z)
                        return
                    }

                    R.id.cb_double_internal_2 -> {
                        SPUtils.put("cancle_reduce_last_feed2", z)
                        return
                    }

                    R.id.cb_double_internal_3 -> {
                        SPUtils.put("reduce_feed2", z)
                        return
                    }

                    R.id.cb_double_internal_4 -> {
                        SPUtils.put("strengthen_route_plan2", z)
                        return
                    }

                    R.id.cb_doublekey_1 -> {
                        SPUtils.put("cut_more_than_once0", z)
                        return
                    }

                    R.id.cb_doublekey_2 -> {
                        SPUtils.put("cancle_reduce_last_feed0", z)
                        return
                    }

                    R.id.cb_doublekey_3 -> {
                        SPUtils.put("reduce_feed0", z)
                        return
                    }

                    R.id.cb_doublekey_4 -> {
                        SPUtils.put("strengthen_route_plan0", z)
                        return
                    }

                    else -> when (id) {
                        R.id.cb_single_external_1 -> {
                            SPUtils.put("cut_more_than_once3", z)
                            return
                        }

                        R.id.cb_single_external_2 -> {
                            SPUtils.put("cancle_reduce_last_feed3", z)
                            return
                        }

                        R.id.cb_single_external_3 -> {
                            SPUtils.put("reduce_feed3", z)
                            return
                        }

                        R.id.cb_single_external_4 -> {
                            SPUtils.put("strengthen_route_plan3", z)
                            return
                        }

                        R.id.cb_single_internal_1 -> {
                            SPUtils.put("cut_more_than_once5", z)
                            return
                        }

                        R.id.cb_single_internal_2 -> {
                            SPUtils.put("cancle_reduce_last_feed5", z)
                            return
                        }

                        R.id.cb_single_internal_3 -> {
                            SPUtils.put("reduce_feed5", z)
                            return
                        }

                        R.id.cb_single_internal_4 -> {
                            SPUtils.put("strengthen_route_plan5", z)
                            return
                        }

                        R.id.cb_singlekey_1 -> {
                            SPUtils.put("cut_more_than_once1", z)
                            return
                        }

                        R.id.cb_singlekey_2 -> {
                            SPUtils.put("cancle_reduce_last_feed1", z)
                            return
                        }

                        R.id.cb_singlekey_3 -> {
                            SPUtils.put("reduce_feed1", z)
                            return
                        }

                        R.id.cb_singlekey_4 -> {
                            SPUtils.put("strengthen_route_plan1", z)
                            return
                        }

                        else -> when (id) {
                            R.id.cb_tubular_1 -> {
                                SPUtils.put("cut_more_than_once8", z)
                                return
                            }

                            R.id.cb_tubular_2 -> {
                                SPUtils.put("cancle_reduce_last_feed8", z)
                                return
                            }

                            R.id.cb_tubular_3 -> {
                                SPUtils.put("reduce_feed8", z)
                                return
                            }

                            R.id.cb_tubular_4 -> {
                                SPUtils.put("strengthen_route_plan8", z)
                                return
                            }

                            else -> return
                        }
                    }
                }
            }
        }
    }

    companion object {
        val CANCLE_REDUCE_LAST_CUT_FEED: String = "cancle_reduce_last_feed"
        val CUT_MORE_THAN_ONCE: String = "cut_more_than_once"
        val REDUCE_CUT_FEED: String = "reduce_feed"
        val STRENGTHEN_ROUTE_PLAN: String = "strengthen_route_plan"
        val TAG: String = "CutSettingFragment"
    }
}
