package com.kkkcut.e20j.customView.indexlib.suspension;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import java.util.List;

/* loaded from: classes.dex */
public class SuspensionDecoration extends RecyclerView.ItemDecoration {
    private static int COLOR_TITLE_BG = Color.parseColor("#1b1a28");
    private static int COLOR_TITLE_FONT = Color.parseColor("#ffffff");
    private static int mTitleFontSize;
    private List<? extends ISuspensionInterface> mDatas;
    private LayoutInflater mInflater;
    private int mTitleHeight;
    private int mHeaderViewCount = 0;
    private Paint mPaint = new Paint();
    private Rect mBounds = new Rect();

    public SuspensionDecoration(Context context) {
        this.mTitleHeight = (int) TypedValue.applyDimension(1, 40.0f, context.getResources().getDisplayMetrics());
        int applyDimension = (int) TypedValue.applyDimension(2, 20.0f, context.getResources().getDisplayMetrics());
        mTitleFontSize = applyDimension;
        this.mPaint.setTextSize(applyDimension);
        this.mPaint.setAntiAlias(true);
        this.mInflater = LayoutInflater.from(context);
        COLOR_TITLE_BG = ThemeUtils.getColor(context, R.attr.color_blackDark_gray);
        COLOR_TITLE_FONT = ThemeUtils.getColor(context, R.attr.textColor_ffffff_333333);
    }

    public SuspensionDecoration setmTitleHeight(int i) {
        this.mTitleHeight = i;
        return this;
    }

    public SuspensionDecoration setColorTitleBg(int i) {
        COLOR_TITLE_BG = i;
        return this;
    }

    public SuspensionDecoration setColorTitleFont(int i) {
        COLOR_TITLE_FONT = i;
        return this;
    }

    public SuspensionDecoration setTitleFontSize(int i) {
        this.mPaint.setTextSize(i);
        return this;
    }

    public SuspensionDecoration setmDatas(List<? extends ISuspensionInterface> list) {
        this.mDatas = list;
        return this;
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewCount;
    }

    public SuspensionDecoration setHeaderViewCount(int i) {
        this.mHeaderViewCount = i;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition() - getHeaderViewCount();
            List<? extends ISuspensionInterface> list = this.mDatas;
            if (list != null && !list.isEmpty() && viewLayoutPosition <= this.mDatas.size() - 1 && viewLayoutPosition >= 0 && this.mDatas.get(viewLayoutPosition).isShowSuspension() && viewLayoutPosition > -1) {
                if (viewLayoutPosition == 0) {
                    drawTitleArea(canvas, paddingLeft, width, childAt, layoutParams, viewLayoutPosition);
                } else if (this.mDatas.get(viewLayoutPosition).getSuspensionTag() != null && !this.mDatas.get(viewLayoutPosition).getSuspensionTag().equals(this.mDatas.get(viewLayoutPosition - 1).getSuspensionTag())) {
                    drawTitleArea(canvas, paddingLeft, width, childAt, layoutParams, viewLayoutPosition);
                }
            }
        }
    }

    private void drawTitleArea(Canvas canvas, int i, int i2, View view, RecyclerView.LayoutParams layoutParams, int i3) {
        this.mPaint.setColor(COLOR_TITLE_BG);
        canvas.drawRect(i, (view.getTop() - layoutParams.topMargin) - this.mTitleHeight, i2, view.getTop() - layoutParams.topMargin, this.mPaint);
        this.mPaint.setColor(COLOR_TITLE_FONT);
        this.mPaint.getTextBounds(this.mDatas.get(i3).getSuspensionTag(), 0, this.mDatas.get(i3).getSuspensionTag().length(), this.mBounds);
        canvas.drawText(this.mDatas.get(i3).getSuspensionTag(), 40.0f, (view.getTop() - layoutParams.topMargin) - ((this.mTitleHeight / 2) - (this.mBounds.height() / 2)), this.mPaint);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() - getHeaderViewCount();
        List<? extends ISuspensionInterface> list = this.mDatas;
        if (list == null || list.isEmpty()) {
            return;
        }
        boolean z = true;
        if (findFirstVisibleItemPosition > this.mDatas.size() - 1 || findFirstVisibleItemPosition < 0 || !this.mDatas.get(findFirstVisibleItemPosition).isShowSuspension()) {
            return;
        }
        String suspensionTag = this.mDatas.get(findFirstVisibleItemPosition).getSuspensionTag();
        View view = recyclerView.findViewHolderForLayoutPosition(getHeaderViewCount() + findFirstVisibleItemPosition).itemView;
        int i = findFirstVisibleItemPosition + 1;
        if (i >= this.mDatas.size() || suspensionTag == null || suspensionTag.equals(this.mDatas.get(i).getSuspensionTag()) || view.getHeight() + view.getTop() >= this.mTitleHeight) {
            z = false;
        } else {
            canvas.save();
            canvas.translate(0.0f, (view.getHeight() + view.getTop()) - this.mTitleHeight);
        }
        this.mPaint.setColor(COLOR_TITLE_BG);
        canvas.drawRect(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getRight() - recyclerView.getPaddingRight(), recyclerView.getPaddingTop() + this.mTitleHeight, this.mPaint);
        this.mPaint.setColor(COLOR_TITLE_FONT);
        this.mPaint.getTextBounds(suspensionTag, 0, suspensionTag.length(), this.mBounds);
        int paddingTop = recyclerView.getPaddingTop();
        int i2 = this.mTitleHeight;
        canvas.drawText(suspensionTag, 40.0f, (paddingTop + i2) - ((i2 / 2) - (this.mBounds.height() / 2)), this.mPaint);
        if (z) {
            canvas.restore();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition() - getHeaderViewCount();
        List<? extends ISuspensionInterface> list = this.mDatas;
        if (list == null || list.isEmpty() || viewLayoutPosition > this.mDatas.size() - 1 || viewLayoutPosition <= -1) {
            return;
        }
        ISuspensionInterface iSuspensionInterface = this.mDatas.get(viewLayoutPosition);
        if (iSuspensionInterface.isShowSuspension()) {
            if (viewLayoutPosition == 0) {
                rect.set(0, this.mTitleHeight, 0, 0);
            } else {
                if (iSuspensionInterface.getSuspensionTag() == null || iSuspensionInterface.getSuspensionTag().equals(this.mDatas.get(viewLayoutPosition - 1).getSuspensionTag())) {
                    return;
                }
                rect.set(0, this.mTitleHeight, 0, 0);
            }
        }
    }
}
