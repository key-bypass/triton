package com.kkkcut.e20j.customView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class ItemDecorationPowerful extends RecyclerView.ItemDecoration {
    public static final int GRID_DIV = 2;
    public static final int HORIZONTAL_DIV = 0;
    private static final String TAG = "ItemDecorationPowerful";
    public static final int VERTICAL_DIV = 1;
    private int mDividerWidth;
    private int mOrientation;
    private Paint mPaint;

    public ItemDecorationPowerful() {
        this(1);
    }

    public ItemDecorationPowerful(int i) {
        this(i, Color.parseColor("#808080"), 1);
    }

    public ItemDecorationPowerful(int i, int i2, int i3) {
        this.mDividerWidth = 0;
        setOrientation(i);
        this.mDividerWidth = i3;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(i2);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.mOrientation;
        if (i == 0) {
            drawHorizontal(canvas, recyclerView);
            return;
        }
        if (i == 1) {
            drawVertical(canvas, recyclerView);
        } else if (i == 2) {
            drawGrid(canvas, recyclerView);
        } else {
            drawVertical(canvas, recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            int i = this.mOrientation;
            if (i == 0) {
                if (childAdapterPosition != 0) {
                    rect.set(this.mDividerWidth, 0, 0, 0);
                    return;
                }
                return;
            }
            if (i == 1) {
                if (childAdapterPosition != 0) {
                    rect.set(0, this.mDividerWidth, 0, 0);
                    return;
                }
                return;
            }
            if (i != 2) {
                if (childAdapterPosition != itemCount - 1) {
                    rect.set(0, 0, 0, this.mDividerWidth);
                    return;
                }
                return;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                if (childAdapterPosition == 0) {
                    int i2 = this.mDividerWidth;
                    rect.set(i2, i2, i2, i2);
                } else if (childAdapterPosition + 1 <= spanCount) {
                    int i3 = this.mDividerWidth;
                    rect.set(0, i3, i3, i3);
                } else if ((childAdapterPosition + spanCount) % spanCount == 0) {
                    int i4 = this.mDividerWidth;
                    rect.set(i4, 0, i4, i4);
                } else {
                    int i5 = this.mDividerWidth;
                    rect.set(0, 0, i5, i5);
                }
            }
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            drawLeft(canvas, recyclerView.getChildAt(i), recyclerView);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            drawTop(canvas, recyclerView.getChildAt(i), recyclerView);
        }
    }

    private void drawGrid(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                if (i == 0) {
                    drawTop(canvas, childAt, recyclerView);
                    drawLeft(canvas, childAt, recyclerView);
                }
                if (i + 1 <= spanCount) {
                    drawTop(canvas, childAt, recyclerView);
                }
                if ((i + spanCount) % spanCount == 0) {
                    drawLeft(canvas, childAt, recyclerView);
                }
                drawRight(canvas, childAt, recyclerView);
                drawBottom(canvas, childAt, recyclerView);
            }
        }
    }

    private void drawLeft(Canvas canvas, View view, RecyclerView recyclerView) {
        int bottom;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - this.mDividerWidth) - layoutParams.leftMargin;
        int top = view.getTop() - layoutParams.topMargin;
        int left2 = view.getLeft() - layoutParams.leftMargin;
        if (isGridLayoutManager(recyclerView)) {
            bottom = view.getBottom() + layoutParams.bottomMargin;
            i = this.mDividerWidth;
        } else {
            bottom = view.getBottom();
            i = layoutParams.bottomMargin;
        }
        canvas.drawRect(left, top, left2, bottom + i, this.mPaint);
    }

    private void drawTop(Canvas canvas, View view, RecyclerView recyclerView) {
        int left;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - layoutParams.topMargin) - this.mDividerWidth;
        int right = view.getRight() + layoutParams.rightMargin;
        int top2 = view.getTop() - layoutParams.topMargin;
        if (isGridLayoutManager(recyclerView)) {
            left = view.getLeft() - layoutParams.leftMargin;
            i = this.mDividerWidth;
        } else {
            left = view.getLeft();
            i = layoutParams.leftMargin;
        }
        canvas.drawRect(left - i, top, right, top2, this.mPaint);
    }

    private void drawRight(Canvas canvas, View view, RecyclerView recyclerView) {
        int top;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int right = view.getRight() + layoutParams.rightMargin;
        int i2 = this.mDividerWidth + right;
        int bottom = view.getBottom() + layoutParams.bottomMargin;
        if (isGridLayoutManager(recyclerView)) {
            top = view.getTop() - layoutParams.topMargin;
            i = this.mDividerWidth;
        } else {
            top = view.getTop();
            i = layoutParams.topMargin;
        }
        canvas.drawRect(right, top - i, i2, bottom, this.mPaint);
    }

    private void drawBottom(Canvas canvas, View view, RecyclerView recyclerView) {
        int right;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = view.getLeft() - layoutParams.leftMargin;
        int bottom = view.getBottom() + layoutParams.bottomMargin;
        int i2 = this.mDividerWidth + bottom;
        if (isGridLayoutManager(recyclerView)) {
            right = view.getRight() + layoutParams.rightMargin;
            i = this.mDividerWidth;
        } else {
            right = view.getRight();
            i = layoutParams.rightMargin;
        }
        canvas.drawRect(left, bottom, right + i, i2, this.mPaint);
    }

    private boolean isGridLayoutManager(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager() instanceof GridLayoutManager;
    }

    public void setOrientation(int i) {
        int i2 = this.mOrientation;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("ItemDecorationPowerful：分割线类型设置异常");
        }
        this.mOrientation = i;
    }
}
