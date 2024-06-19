package com.kkkcut.e20j.customView.circleIndicator;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/* loaded from: classes.dex */
public class ShapeHolder {
    private int color;
    private Paint paint;
    private ShapeDrawable shape;
    private float x = 0.0f;
    private float y = 0.0f;
    private float alpha = 1.0f;

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getY() {
        return this.y;
    }

    public void setShape(ShapeDrawable shapeDrawable) {
        this.shape = shapeDrawable;
    }

    public ShapeDrawable getShape() {
        return this.shape;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.shape.getPaint().setColor(i);
        this.color = i;
    }

    public void setAlpha(float f) {
        this.alpha = f;
        this.shape.setAlpha((int) ((f * 255.0f) + 0.5f));
    }

    public float getWidth() {
        return this.shape.getShape().getWidth();
    }

    public void setWidth(float f) {
        Shape shape = this.shape.getShape();
        shape.resize(f, shape.getHeight());
    }

    public float getHeight() {
        return this.shape.getShape().getHeight();
    }

    public void setHeight(float f) {
        Shape shape = this.shape.getShape();
        shape.resize(shape.getWidth(), f);
    }

    public void resizeShape(float f, float f2) {
        this.shape.getShape().resize(f, f2);
    }

    public ShapeHolder(ShapeDrawable shapeDrawable) {
        this.shape = shapeDrawable;
    }
}
