package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.us.R;

import spa.lyh.cn.lib_largeimageview.LargeImageView;

/* loaded from: classes.dex */
public class HelpCenterFragment extends BaseBackFragment {
    private int lastIndex;

    ViewPager vp;
    private int[] pics_en = {R.drawable.help1_en, R.drawable.help2_en};
    private int[] pics_ch = {R.drawable.help1_ch, R.drawable.help2_ch};
    private int[] pics_easy = {R.drawable.help1_easy, R.drawable.help2_easy};
    private int[] pics_us = {R.drawable.help1_us, R.drawable.help2_us};

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_help_center;
    }

    public static HelpCenterFragment newInstance() {
        Bundle bundle = new Bundle();
        HelpCenterFragment helpCenterFragment = new HelpCenterFragment();
        helpCenterFragment.setArguments(bundle);
        return helpCenterFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.help_center);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.vp.setAdapter(new PagerAdapter() { // from class: com.kkkcut.e20j.ui.fragment.HelpCenterFragment.1
            @Override // androidx.viewpager.widget.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                return HelpCenterFragment.this.getPics().length;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                LargeImageView largeImageView = new LargeImageView(HelpCenterFragment.this.getContext());
                largeImageView.setImage(HelpCenterFragment.this.getPics()[i]);
                viewGroup.addView(largeImageView);
                return largeImageView;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
            }
        });
        this.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.HelpCenterFragment.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (HelpCenterFragment.this.lastIndex != i) {
                    View childAt = HelpCenterFragment.this.vp.getChildAt(HelpCenterFragment.this.lastIndex);
                    LargeImageView largeImageView = childAt instanceof LargeImageView ? (LargeImageView) childAt : null;
                    if (largeImageView != null) {
                        largeImageView.setScale(1.0f);
                    }
                    HelpCenterFragment.this.lastIndex = i;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] getPics() {
        if (MachineInfo.isE20Us(getContext())) {
            return this.pics_us;
        }
        if (MachineInfo.isChineseMachine()) {
            return this.pics_ch;
        }
        return this.pics_en;
    }
}
