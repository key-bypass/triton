package com.kkkcut.e20j.ui.fragment;

import android.widget.LinearLayout;
import butterknife.BindView;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;
import me.yokeyword.fragmentation.ISupportFragment;

/* loaded from: classes.dex */
public class SupportFragment extends BaseBackFragment {
    public static final String TAG = "SupportFragment";

    @BindView(R.id.ll_chinese)
    LinearLayout llChinese;

    @BindView(R.id.ll_foreign)
    LinearLayout llForeign;

    @BindView(R.id.ll_foreign_us)
    LinearLayout llForeignUS;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_support;
    }

    public static ISupportFragment newInstance() {
        return new SupportFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        if (MachineInfo.isChineseMachine()) {
            this.llChinese.setVisibility(0);
            this.llForeign.setVisibility(8);
            this.llForeignUS.setVisibility(8);
        } else if (MachineInfo.isE20Us(getContext())) {
            this.llChinese.setVisibility(8);
            this.llForeign.setVisibility(8);
            this.llForeignUS.setVisibility(0);
        } else {
            this.llChinese.setVisibility(8);
            this.llForeign.setVisibility(0);
            this.llForeignUS.setVisibility(8);
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.support);
    }
}