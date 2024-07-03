package com.kkkcut.e20j.androidquick.autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.kkkcut.e20j.androidquick.autolayout.AutoLayoutInfo;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoLayoutHelper;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class MetroLayout extends ViewGroup {
    private List<MetroBlock> mAvailablePos;
    private int mDivider;
    private final AutoLayoutHelper mHelper;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MetroBlock {
        int left;
        int top;
        int width;

        private MetroBlock() {
        }
    }

    public MetroLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHelper = new AutoLayoutHelper(this);
        this.mAvailablePos = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MetroLayout);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        this.mDivider = dimensionPixelOffset;
        this.mDivider = AutoUtils.getPercentWidthSizeBigger(dimensionPixelOffset);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        randomColor();
        if (!isInEditMode()) {
            this.mHelper.adjustChildren();
        }
        measureChildren(i, i2);
        super.onMeasure(i, i2);
    }

    private void randomColor() {
        Random random = new Random(255L);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setBackgroundColor(Color.argb(100, random.nextInt(), random.nextInt(), random.nextInt()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        initAvailablePosition();
        int i5 = this.mDivider;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != View.GONE) {
                MetroBlock findAvailablePos = findAvailablePos(childAt);
                int i7 = findAvailablePos.left;
                int i8 = findAvailablePos.top;
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight() + i8;
                childAt.layout(i7, i8, i7 + measuredWidth, measuredHeight);
                int i9 = measuredWidth + i5;
                if (i9 < findAvailablePos.width) {
                    findAvailablePos.left += i9;
                    findAvailablePos.width -= i9;
                } else {
                    this.mAvailablePos.remove(findAvailablePos);
                }
                MetroBlock metroBlock = new MetroBlock();
                metroBlock.left = i7;
                metroBlock.top = measuredHeight + i5;
                metroBlock.width = measuredWidth;
                this.mAvailablePos.add(metroBlock);
                mergeAvailablePosition();
            }
        }
    }

    private void mergeAvailablePosition() {
        MetroBlock metroBlock;
        if (this.mAvailablePos.size() <= 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        MetroBlock metroBlock2 = this.mAvailablePos.get(0);
        MetroBlock metroBlock3 = this.mAvailablePos.get(1);
        int size = this.mAvailablePos.size();
        for (int i = 1; i < size - 1; i++) {
            if (metroBlock2.top == metroBlock3.top) {
                metroBlock2.width += metroBlock3.width;
                arrayList.add(metroBlock2);
                metroBlock3.left = metroBlock2.left;
                metroBlock = this.mAvailablePos.get(i + 1);
            } else {
                metroBlock2 = this.mAvailablePos.get(i);
                metroBlock = this.mAvailablePos.get(i + 1);
            }
            metroBlock3 = metroBlock;
        }
        this.mAvailablePos.removeAll(arrayList);
    }

    private void initAvailablePosition() {
        this.mAvailablePos.clear();
        MetroBlock metroBlock = new MetroBlock();
        metroBlock.left = getPaddingLeft();
        metroBlock.top = getPaddingTop();
        metroBlock.width = getMeasuredWidth();
        this.mAvailablePos.add(metroBlock);
    }

    private MetroBlock findAvailablePos(View view) {
        MetroBlock metroBlock = new MetroBlock();
        if (this.mAvailablePos.size() == 0) {
            metroBlock.left = getPaddingLeft();
            metroBlock.top = getPaddingTop();
            metroBlock.width = getMeasuredWidth();
            return metroBlock;
        }
        int i = this.mAvailablePos.get(0).top;
        MetroBlock metroBlock2 = this.mAvailablePos.get(0);
        for (MetroBlock metroBlock3 : this.mAvailablePos) {
            if (metroBlock3.top < i) {
                i = metroBlock3.top;
                metroBlock2 = metroBlock3;
            }
        }
        return metroBlock2;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            this((ViewGroup.LayoutParams) layoutParams);
            this.mAutoLayoutInfo = layoutParams.mAutoLayoutInfo;
        }

        @Override // com.kkkcut.e20j.androidquick.autolayout.utils.AutoLayoutHelper.AutoLayoutParams
        public AutoLayoutInfo getAutoLayoutInfo() {
            return this.mAutoLayoutInfo;
        }
    }
}
