package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

import com.cutting.machine.bean.DestPoint;

import java.util.List;

/* loaded from: classes.dex */
public abstract class CopyKey extends View {
    protected int colorRed;
    private int keyLenght;
    private Rect keyRect;
    private int keyWidth;
    private int maxX;
    private int maxY;
    protected Paint paint;
    protected Path path;
    List<DestPoint> pointsA;
    List<DestPoint> pointsB;
    private float ratio;

    /* loaded from: classes.dex */
    protected enum Side {
        A,
        B
    }

    protected abstract void cacularKeySize();

    protected abstract void drawKeyView(Canvas canvas);

    public List<DestPoint> getPointsB() {
        return this.pointsB;
    }

    public Rect getKeyRect() {
        return this.keyRect;
    }

    public float getRatio() {
        return this.ratio;
    }

    public List<DestPoint> getPointsA() {
        return this.pointsA;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public void setPointsA(List<DestPoint> list) {
        this.pointsA = list;
    }

    public void setPointsB(List<DestPoint> list) {
        this.pointsB = list;
    }

    public void setMaxX(int i) {
        this.maxX = i;
    }

    public void setMaxY(int i) {
        this.maxY = i;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public int getKeyWidth() {
        return this.keyWidth;
    }

    public void setKeyWidth(int i) {
        this.keyWidth = i;
    }

    public int getKeyLenght() {
        return this.keyLenght;
    }

    public void setKeyLenght(int i) {
        this.keyLenght = i;
    }

    public CopyKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context);
        this.colorRed = Color.parseColor("#ff205f");
        this.pointsA = list;
        this.pointsB = list2;
        this.maxY = i2;
        if (list != null && list.size() > 1) {
            this.maxX = Math.abs(list.get(0).getSpace() - i);
        } else if (list2 != null && list2.size() > 1) {
            this.maxX = Math.abs(list2.get(0).getSpace() - i);
        }
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setPathEffect(new CornerPathEffect(4.0f));
        this.paint.setAntiAlias(true);
        this.path = new Path();
        cacularKeySize();
    }

    public CopyKey(Context context) {
        super(context);
        this.colorRed = Color.parseColor("#ff205f");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        initViewSize();
    }

    protected void initViewSize() {
        int measuredHeight = (getMeasuredHeight() - getPaddingBottom()) - getPaddingTop();
        int measuredWidth = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
        this.ratio = (measuredHeight * 1.0f) / (getKeyWidth() * 3);
        float f = measuredWidth;
        if (getKeyLenght() * this.ratio < f) {
            this.keyRect = new Rect(((measuredWidth - ((int) (getKeyLenght() * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 2) / 6) + getPaddingTop(), ((measuredWidth + ((int) (getKeyLenght() * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 6) + getPaddingTop());
            return;
        }
        this.ratio = (f * 1.0f) / getKeyLenght();
        int i = measuredHeight / 2;
        this.keyRect = new Rect(getPaddingLeft(), (getPaddingTop() + i) - ((int) ((this.ratio * getKeyWidth()) / 2.0f)), measuredWidth + getPaddingLeft(), getPaddingTop() + i + ((int) ((this.ratio * getKeyWidth()) / 2.0f)));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawKeyView(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getPointX(int i, Side side) {
        float f;
        float abs;
        if (side == Side.A && i < getPointsA().size()) {
            f = this.keyRect.left;
            abs = Math.abs((getPointsA().get(i).getSpace() - getPointsA().get(0).getSpace()) * this.ratio);
        } else {
            if (i >= getPointsB().size()) {
                return 0.0f;
            }
            f = this.keyRect.left;
            abs = Math.abs((getPointsB().get(i).getSpace() - getPointsB().get(0).getSpace()) * this.ratio);
        }
        return f + abs;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getPointY(int i, Side side) {
        if (side == Side.A) {
            return this.keyRect.bottom - (getPointsA().get(i).getDepth() * this.ratio);
        }
        return this.keyRect.top + (getPointsB().get(i).getDepth() * this.ratio);
    }
}
