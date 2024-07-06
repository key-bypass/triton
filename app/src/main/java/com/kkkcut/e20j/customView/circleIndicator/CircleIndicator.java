package com.kkkcut.e20j.customView.circleIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.core.internal.view.SupportMenu;
import androidx.viewpager.widget.ViewPager;

import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CircleIndicator extends View {
    private final int DEFAULT_INDICATOR_BACKGROUND;
    private final int DEFAULT_INDICATOR_LAYOUT_GRAVITY;
    private final int DEFAULT_INDICATOR_MARGIN;
    private final int DEFAULT_INDICATOR_MODE;
    private final int DEFAULT_INDICATOR_RADIUS;
    private final int DEFAULT_INDICATOR_SELECTED_BACKGROUND;
    private int mCurItemPosition;
    private float mCurItemPositionOffset;
    private int mIndicatorBackground;
    private Gravity mIndicatorLayoutGravity;
    private float mIndicatorMargin;
    private Mode mIndicatorMode;
    private float mIndicatorRadius;
    private int mIndicatorSelectedBackground;
    private ShapeHolder movingItem;
    private List<ShapeHolder> tabItems;
    private ViewPager viewPager;

    /* loaded from: classes.dex */
    public enum Gravity {
        LEFT,
        CENTER,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum Mode {
        INSIDE,
        OUTSIDE,
        SOLO
    }

    public CircleIndicator(Context context) {
        super(context);
        this.DEFAULT_INDICATOR_RADIUS = 10;
        this.DEFAULT_INDICATOR_MARGIN = 40;
        this.DEFAULT_INDICATOR_BACKGROUND = -16776961;
        this.DEFAULT_INDICATOR_SELECTED_BACKGROUND = SupportMenu.CATEGORY_MASK;
        this.DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
        this.DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();
        init(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_INDICATOR_RADIUS = 10;
        this.DEFAULT_INDICATOR_MARGIN = 40;
        this.DEFAULT_INDICATOR_BACKGROUND = -16776961;
        this.DEFAULT_INDICATOR_SELECTED_BACKGROUND = SupportMenu.CATEGORY_MASK;
        this.DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
        this.DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();
        init(context, attributeSet);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DEFAULT_INDICATOR_RADIUS = 10;
        this.DEFAULT_INDICATOR_MARGIN = 40;
        this.DEFAULT_INDICATOR_BACKGROUND = -16776961;
        this.DEFAULT_INDICATOR_SELECTED_BACKGROUND = SupportMenu.CATEGORY_MASK;
        this.DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
        this.DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.tabItems = new ArrayList();
        handleTypedArray(context, attributeSet);
    }

    private void handleTypedArray(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IndexBar);
        this.mIndicatorRadius = obtainStyledAttributes.getDimensionPixelSize(4, 10);
        this.mIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(2, 40);
        this.mIndicatorBackground = obtainStyledAttributes.getColor(0, -16776961);
        this.mIndicatorSelectedBackground = obtainStyledAttributes.getColor(5, SupportMenu.CATEGORY_MASK);
        this.mIndicatorLayoutGravity = Gravity.values()[obtainStyledAttributes.getInt(1, this.DEFAULT_INDICATOR_LAYOUT_GRAVITY)];
        this.mIndicatorMode = Mode.values()[obtainStyledAttributes.getInt(3, this.DEFAULT_INDICATOR_MODE)];
        obtainStyledAttributes.recycle();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        createTabItems();
        createMovingItem();
        setUpListener();
    }

    private void setUpListener() {
        this.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.kkkcut.e20j.customView.circleIndicator.CircleIndicator.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
                if (CircleIndicator.this.mIndicatorMode != Mode.SOLO) {
                    CircleIndicator.this.trigger(i, f);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                if (CircleIndicator.this.mIndicatorMode == Mode.SOLO) {
                    CircleIndicator.this.trigger(i, 0.0f);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trigger(int i, float f) {
        this.mCurItemPosition = i;
        this.mCurItemPositionOffset = f;
        Log.e("CircleIndicator", "onPageScrolled()" + i + ":" + f);
        requestLayout();
        invalidate();
    }

    private void createTabItems() {
        for (int i = 0; i < this.viewPager.getAdapter().getCount(); i++) {
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
            ShapeHolder shapeHolder = new ShapeHolder(shapeDrawable);
            Paint paint = shapeDrawable.getPaint();
            paint.setColor(this.mIndicatorSelectedBackground);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            shapeHolder.setPaint(paint);
            this.tabItems.add(shapeHolder);
        }
    }

    private void createMovingItem() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        this.movingItem = new ShapeHolder(shapeDrawable);
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(this.mIndicatorSelectedBackground);
        paint.setAntiAlias(true);
        int i = AnonymousClass2.$SwitchMap$com$kkkcut$e20j$customView$circleIndicator$CircleIndicator$Mode[this.mIndicatorMode.ordinal()];
        if (i == 1) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        } else if (i == 2) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        } else if (i == 3) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }
        this.movingItem.setPaint(paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.customView.circleIndicator.CircleIndicator$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$customView$circleIndicator$CircleIndicator$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$kkkcut$e20j$customView$circleIndicator$CircleIndicator$Mode = iArr;
            try {
                iArr[Mode.INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$customView$circleIndicator$CircleIndicator$Mode[Mode.OUTSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$customView$circleIndicator$CircleIndicator$Mode[Mode.SOLO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.e("CircleIndicator", "onLayout()");
        super.onLayout(z, i, i2, i3, i4);
        layoutTabItems(getWidth(), getHeight());
        layoutMovingItem(this.mCurItemPosition, this.mCurItemPositionOffset);
    }

    private void layoutTabItems(int i, int i2) {
        if (this.tabItems == null) {
            throw new IllegalStateException("forget to create tabItems?");
        }
        float f = i2 * 0.5f;
        float startDrawPosition = startDrawPosition(i);
        for (int i3 = 0; i3 < this.tabItems.size(); i3++) {
            ShapeHolder shapeHolder = this.tabItems.get(i3);
            float f2 = this.mIndicatorRadius;
            shapeHolder.resizeShape(f2 * 2.0f, f2 * 2.0f);
            shapeHolder.setY(f - this.mIndicatorRadius);
            shapeHolder.setX(((this.mIndicatorMargin + (this.mIndicatorRadius * 2.0f)) * i3) + startDrawPosition);
        }
    }

    private float startDrawPosition(int i) {
        if (this.mIndicatorLayoutGravity == Gravity.LEFT) {
            return 0.0f;
        }
        float size = this.tabItems.size();
        float f = this.mIndicatorRadius * 2.0f;
        float f2 = this.mIndicatorMargin;
        float f3 = (size * (f + f2)) - f2;
        float f4 = i;
        if (f4 < f3) {
            return 0.0f;
        }
        return this.mIndicatorLayoutGravity == Gravity.CENTER ? (f4 - f3) / 2.0f : f4 - f3;
    }

    private void layoutMovingItem(int i, float f) {
        if (this.movingItem == null) {
            throw new IllegalStateException("forget to create movingItem?");
        }
        if (this.tabItems.size() == 0) {
            return;
        }
        ShapeHolder shapeHolder = this.tabItems.get(i);
        this.movingItem.resizeShape(shapeHolder.getWidth(), shapeHolder.getHeight());
        this.movingItem.setX(shapeHolder.getX() + ((this.mIndicatorMargin + (this.mIndicatorRadius * 2.0f)) * f));
        this.movingItem.setY(shapeHolder.getY());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Log.e("CircleIndicator", "onDraw()");
        super.onDraw(canvas);
        int save = canvas.save();
        Iterator<ShapeHolder> it = this.tabItems.iterator();
        while (it.hasNext()) {
            drawItem(canvas, it.next());
        }
        ShapeHolder shapeHolder = this.movingItem;
        if (shapeHolder != null) {
            drawItem(canvas, shapeHolder);
        }
        canvas.restoreToCount(save);
    }

    private void drawItem(Canvas canvas, ShapeHolder shapeHolder) {
        canvas.save();
        canvas.translate(shapeHolder.getX(), shapeHolder.getY());
        shapeHolder.getShape().draw(canvas);
        canvas.restore();
    }

    public void setIndicatorRadius(float f) {
        this.mIndicatorRadius = f;
    }

    public void setIndicatorMargin(float f) {
        this.mIndicatorMargin = f;
    }

    public void setIndicatorBackground(int i) {
        this.mIndicatorBackground = i;
    }

    public void setIndicatorSelectedBackground(int i) {
        this.mIndicatorSelectedBackground = i;
    }

    public void setIndicatorLayoutGravity(Gravity gravity) {
        this.mIndicatorLayoutGravity = gravity;
    }

    public void setIndicatorMode(Mode mode) {
        this.mIndicatorMode = mode;
    }
}
