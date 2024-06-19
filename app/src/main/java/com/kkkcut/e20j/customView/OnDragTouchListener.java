package com.kkkcut.e20j.customView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class OnDragTouchListener implements View.OnTouchListener {
    private static final int LEFT_SPACE = 0;
    private int bottom;
    private boolean hasAutoPullToBorder;
    private int left;
    private float mDistanceX;
    private float mDistanceY;
    private OnDraggableClickListener mListener;
    private float mOriginalX;
    private float mOriginalY;
    private boolean moved;
    private int parentHeight;
    private int parentWidth;
    private int right;
    private int top;

    /* loaded from: classes.dex */
    public interface OnDraggableClickListener {
        void onClick(View view);

        void onDragged(View view, int i, int i2);
    }

    public OnDragTouchListener(int i, int i2, boolean z) {
        this.hasAutoPullToBorder = z;
        this.parentWidth = i;
        this.parentHeight = i2;
    }

    public OnDragTouchListener(int i, int i2) {
        this.parentWidth = i;
        this.parentHeight = i2;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            OnDraggableClickListener onDraggableClickListener = this.mListener;
            if (onDraggableClickListener != null) {
                onDraggableClickListener.onClick(view);
            }
            this.mOriginalX = motionEvent.getRawX();
            this.mOriginalY = motionEvent.getRawY();
            this.mDistanceX = motionEvent.getRawX() - view.getLeft();
            this.mDistanceY = motionEvent.getRawY() - view.getTop();
        } else if (action == 1) {
            this.moved = false;
            view.performClick();
        } else if (action == 2) {
            this.moved = true;
            this.left = (int) (motionEvent.getRawX() - this.mDistanceX);
            this.top = (int) (motionEvent.getRawY() - this.mDistanceY);
            this.right = this.left + view.getWidth();
            this.bottom = this.top + view.getHeight();
            if (this.left < 0) {
                this.left = 0;
                this.right = view.getWidth() + 0;
            }
            if (this.top < 0) {
                this.top = 0;
                this.bottom = view.getHeight() + 0;
            }
            int i = this.right;
            int i2 = this.parentWidth;
            if (i > i2 + 0) {
                int i3 = i2 - 0;
                this.right = i3;
                this.left = i3 - view.getWidth();
            }
            int i4 = this.bottom;
            int i5 = this.parentHeight;
            if (i4 > i5 - 2) {
                int i6 = i5 - 2;
                this.bottom = i6;
                this.top = i6 - view.getHeight();
            }
            view.layout(this.left, this.top, this.right, this.bottom);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = this.left;
            layoutParams.topMargin = this.top;
            view.setLayoutParams(layoutParams);
        } else if (action == 3) {
            this.moved = false;
        }
        return true;
    }

    public OnDraggableClickListener getOnDraggableClickListener() {
        return this.mListener;
    }

    public void setOnDraggableClickListener(OnDraggableClickListener onDraggableClickListener) {
        this.mListener = onDraggableClickListener;
    }

    public boolean isHasAutoPullToBorder() {
        return this.hasAutoPullToBorder;
    }

    public void setHasAutoPullToBorder(boolean z) {
        this.hasAutoPullToBorder = z;
    }

    private void startAutoPull(final View view, final ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (!this.hasAutoPullToBorder) {
            if (this.moved) {
                view.layout(this.left, this.top, this.right, this.bottom);
                marginLayoutParams.setMargins(this.left, this.top, 0, 0);
                view.setLayoutParams(marginLayoutParams);
                OnDraggableClickListener onDraggableClickListener = this.mListener;
                if (onDraggableClickListener != null) {
                    onDraggableClickListener.onDragged(view, this.left, this.top);
                    return;
                }
                return;
            }
            return;
        }
        final float width = this.left + (view.getWidth() / 2) >= this.parentWidth / 2 ? r3 - view.getWidth() : 0.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.left, width);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kkkcut.e20j.customView.OnDragTouchListener.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int floatValue = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                view.layout(floatValue, OnDragTouchListener.this.top, OnDragTouchListener.this.right, OnDragTouchListener.this.bottom);
                marginLayoutParams.setMargins(floatValue, OnDragTouchListener.this.top, 0, 0);
                view.setLayoutParams(marginLayoutParams);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.kkkcut.e20j.customView.OnDragTouchListener.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (OnDragTouchListener.this.mListener != null) {
                    OnDragTouchListener.this.mListener.onDragged(view, (int) width, OnDragTouchListener.this.top);
                }
            }
        });
        ofFloat.setDuration(400L);
        ofFloat.start();
    }
}
