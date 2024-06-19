package com.kkkcut.e20j.ui.fragment.clampswitch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ClampSwitchPagerAdapter extends PagerAdapter {
    List<ImageView> imageViewList = new ArrayList();
    List<ClampDisplayBean> imgRes;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ClampSwitchPagerAdapter(List<ClampDisplayBean> list, Context context) {
        this.imgRes = list;
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(list.get(i).getDrawable());
            imageView.setPadding(5, 5, 5, 5);
            this.imageViewList.add(imageView);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<ClampDisplayBean> list = this.imgRes;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public float getPageWidth(int i) {
        return super.getPageWidth(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ImageView imageView = this.imageViewList.get(i);
        viewGroup.addView(imageView);
        return imageView;
    }

    public View getViewByPosition(int i) {
        return this.imageViewList.get(i);
    }
}
