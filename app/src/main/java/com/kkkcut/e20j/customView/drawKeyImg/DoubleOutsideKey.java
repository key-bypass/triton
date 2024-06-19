package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import androidx.core.internal.view.SupportMenu;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.utils.UnitUtils;
import com.liying.core.bean.KeyInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class DoubleOutsideKey extends Key {
    private float angle;
    private CornerPathEffect cornerPathEffect;
    private List<Rect> decimalRectListA;
    private List<Rect> decimalRectListB;
    private List<Integer> depthA;
    private List<Integer> depthB;
    private List<String> depthNameA;
    private List<String> depthNameB;
    private int extraBit;
    private List<Rect> integerRectListA;
    private List<Rect> integerRectListB;
    private KeyInfo keyInfo;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> spaceA;
    private List<Integer> spaceB;
    private List<Integer> spaceWidthA;
    private List<Integer> spaceWidthB;
    private int toothAmount;
    private String[] toothCodeA;
    private String[] toothCodeB;

    public DoubleOutsideKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.angle = 1.5707964f;
        this.cornerPathEffect = new CornerPathEffect(2.0f);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.integerRectListB = new ArrayList();
        this.decimalRectListB = new ArrayList();
        this.path = new Path();
        this.paint = new Paint();
        this.keyInfo = keyInfo;
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        this.depthA = getAllDepths().get(0);
        this.depthB = getAllDepths().get(1);
        this.spaceA = getAllSpaces().get(0);
        this.spaceB = getAllSpaces().get(1);
        this.depthNameA = getAllDepthNames().get(0);
        this.depthNameB = getAllDepthNames().get(1);
        this.spaceWidthA = getAllSpaceWidths().get(0);
        this.spaceWidthB = getAllSpaceWidths().get(1);
        int width = keyInfo.getWidth();
        this.keyWidth = width;
        if (width == 0) {
            this.keyWidth = getMaxDepth();
        }
        int tan = (int) ((this.keyWidth / 2) / Math.tan(this.angle / 2.0f));
        if (keyInfo.getAlign() == 0) {
            this.extraBit = 200;
            this.maxLenght = getMaxSpace() + this.extraBit + tan + (this.keyWidth * 2.25f);
        } else {
            this.maxLenght = getMaxSpace() + (this.keyWidth * 2.25f);
        }
        setToothCodeDefault();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        initSize();
    }

    public void initSize() {
        int measuredHeight = (getMeasuredHeight() - getPaddingBottom()) - getPaddingTop();
        int measuredWidth = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
        float f = (measuredHeight * 1.0f) / (this.keyWidth * 3);
        this.ratio = f;
        float f2 = this.maxLenght;
        float f3 = measuredWidth;
        if (f * f2 < f3) {
            this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 2) / 6) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 6) + getPaddingTop());
        } else {
            this.ratio = (f3 * 1.0f) / f2;
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth + getPaddingLeft(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        String toothCodeRound;
        String toothCodeRound2;
        this.paint.reset();
        this.path.reset();
        this.paint.setColor(this.keyColor);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(2.0f);
        this.path.moveTo(this.rect.left, this.rect.top - (this.rect.height() * 0.65f));
        this.path.lineTo(this.rect.left + (this.rect.height() * 0.3725f), this.rect.top - (this.rect.height() * 0.65f));
        this.path.addArc(new RectF(this.rect.left, this.rect.top - (this.rect.height() * 0.65f), this.rect.left + (this.rect.height() * 0.75f), this.rect.top), 270.0f, 90.0f);
        this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom + (this.rect.height() * 0.3725f));
        this.path.addArc(new RectF(this.rect.left, this.rect.bottom, this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom + (this.rect.height() * 0.65f)), 0.0f, 90.0f);
        this.path.lineTo(this.rect.left, this.rect.bottom + (this.rect.height() * 0.65f));
        this.path.lineTo(this.rect.left, this.rect.top - (this.rect.height() * 0.65f));
        canvas.drawPath(this.path, this.paint);
        this.paint.setPathEffect(this.cornerPathEffect);
        this.path.reset();
        float height = this.rect.left + (this.rect.height() * 2.25f);
        if (this.keyInfo.getAlign() == 0) {
            this.path.moveTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.top - (this.rect.height() * 0.2f));
            this.path.lineTo(height, this.rect.top - (this.rect.height() * 0.2f));
            this.path.lineTo(height, this.rect.top);
        } else {
            this.path.moveTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.top);
        }
        this.path.lineTo(this.rect.right - (this.rect.height() * 0.2f), this.rect.top);
        this.path.lineTo(this.rect.right, this.rect.top + (this.rect.height() * 0.3f));
        this.path.lineTo(this.rect.right, this.rect.bottom - (this.rect.height() * 0.3f));
        this.path.lineTo(this.rect.right - (this.rect.height() * 0.2f), this.rect.bottom);
        if (this.keyInfo.getAlign() == 0) {
            this.path.lineTo(height, this.rect.bottom);
            this.path.lineTo(height, this.rect.bottom + (this.rect.height() * 0.2f));
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom + (this.rect.height() * 0.2f));
        } else {
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom);
        }
        canvas.drawPath(this.path, this.paint);
        if (this.keyInfo.getLastBitting() != 0) {
            this.path.moveTo((getSpaceXInGraph(this.spaceA.get(0).intValue()) - (300 * this.ratio)) - (this.rect.height() * 0.6f), this.rect.top);
            float f = 150;
            this.path.lineTo((getSpaceXInGraph(this.spaceA.get(0).intValue()) - (this.ratio * f)) - (this.rect.height() * 0.6f), this.rect.top + (this.rect.height() * 0.2f));
            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(0).intValue()) - (f * this.ratio), this.rect.top + (this.rect.height() * 0.2f));
        } else {
            this.path.moveTo(getSpaceXInGraph(this.spaceA.get(0).intValue()) - (150 * this.ratio), this.rect.top);
        }
        for (int i = 0; i < this.spaceA.size(); i++) {
            if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, i)) || getToothCodeAt(this.toothCodeA, i).equals("?")) {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio));
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio));
            } else {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i)));
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i)));
            }
        }
        if (this.keyInfo.getAlign() == 0) {
            this.path.lineTo((getMaxSpace() * this.ratio) + (this.rect.height() * 2.25f) + this.rect.left + (this.extraBit * this.ratio), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio));
        }
        this.path.lineTo(this.rect.right, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() * 0.1f));
        this.path.moveTo(this.rect.right, ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() * 0.1f));
        if (this.keyInfo.getAlign() == 0) {
            this.path.lineTo((getMaxSpace() * this.ratio) + (this.rect.height() * 2.25f) + this.rect.left + (this.extraBit * this.ratio), this.rect.top + (this.depthB.get(0).intValue() * this.ratio));
        }
        for (int size = this.spaceB.size() - 1; size >= 0; size--) {
            if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, size)) || getToothCodeAt(this.toothCodeB, size).equals("?")) {
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(size)), this.rect.top + (this.depthB.get(0).intValue() * this.ratio));
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(size)), this.rect.top + (this.depthB.get(0).intValue() * this.ratio));
            } else {
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(size)), this.rect.top + getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, size)));
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(size)), this.rect.top + getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, size)));
            }
        }
        if (this.keyInfo.getLastBitting() != 0) {
            float f2 = 150;
            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(0).intValue()) - (this.ratio * f2), this.rect.bottom - (this.rect.height() * 0.2f));
            this.path.lineTo((getSpaceXInGraph(this.spaceA.get(0).intValue()) - (f2 * this.ratio)) - (this.rect.height() * 0.6f), this.rect.bottom - (this.rect.height() * 0.2f));
            this.path.lineTo((getSpaceXInGraph(this.spaceA.get(0).intValue()) - (300 * this.ratio)) - (this.rect.height() * 0.6f), this.rect.bottom);
        } else {
            this.path.lineTo(getSpaceXInGraph(this.spaceB.get(0).intValue()) - (150 * this.ratio), this.rect.bottom);
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i2 = 0; i2 < this.spaceA.size(); i2++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound2 = getToothCodeInt(getToothCodeAt(this.toothCodeA, i2));
                } else {
                    toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i2), this.depthNameA);
                }
                List<Integer> list = this.spaceA;
                int spaceXInGraph = ((int) getSpaceXInGraph(list.get(list.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i2));
                int i3 = this.rect.top - this.marginBig;
                Rect rect = new Rect(spaceXInGraph - (this.rectWidth / 2), i3 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph, i3);
                this.integerRectListA.add(rect);
                if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i2 && !this.flag.isDicimal()) {
                    this.paint.setStyle(Paint.Style.FILL);
                    this.paint.setColor(this.textRectSelect);
                    this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    canvas.drawRect(rect, this.paint);
                    this.paint.setColor(this.textColorSelect);
                } else {
                    this.paint.setStyle(Paint.Style.STROKE);
                    this.paint.setColor(this.integerRectColor);
                    canvas.drawRect(rect, this.paint);
                    this.paint.setColor(this.integerColorDefault);
                    this.paint.setStyle(Paint.Style.FILL);
                }
                float f3 = spaceXInGraph;
                canvas.drawText(toothCodeRound2, f3, i3 - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int i4 = this.rect.top - this.marginSmall;
                    Rect rect2 = new Rect(spaceXInGraph - (this.rectWidth / 2), i4 - this.rectHeight, spaceXInGraph + (this.rectWidth / 2), i4);
                    this.decimalRectListA.add(rect2);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i2 && this.flag.isDicimal()) {
                        this.paint.setStyle(Paint.Style.FILL);
                        this.paint.setColor(this.textRectSelect);
                        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                        canvas.drawRect(rect2, this.paint);
                        this.paint.setColor(this.textColorSelect);
                    } else {
                        this.paint.setStyle(Paint.Style.STROKE);
                        this.paint.setColor(this.decimalRectColor);
                        canvas.drawRect(rect2, this.paint);
                        this.paint.setColor(this.decimalColorDefault);
                        this.paint.setStyle(Paint.Style.FILL);
                    }
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeA, i2)), f3, i4 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
            this.integerRectListB.clear();
            this.decimalRectListB.clear();
            for (int i5 = 0; i5 < this.spaceB.size(); i5++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCodeB, i5));
                } else {
                    toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCodeB, i5), this.depthNameB);
                }
                List<Integer> list2 = this.spaceA;
                int spaceXInGraph2 = ((int) getSpaceXInGraph(list2.get(list2.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i5));
                int i6 = this.rect.bottom + this.marginSmall + this.rectHeight;
                Rect rect3 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i6 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph2, i6);
                this.integerRectListB.add(rect3);
                if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i5 && !this.flag.isDicimal()) {
                    this.paint.setStyle(Paint.Style.FILL);
                    this.paint.setColor(this.textRectSelect);
                    this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    canvas.drawRect(rect3, this.paint);
                    this.paint.setColor(this.textColorSelect);
                } else {
                    this.paint.setStyle(Paint.Style.STROKE);
                    this.paint.setColor(this.integerRectColor);
                    canvas.drawRect(rect3, this.paint);
                    this.paint.setColor(this.integerColorDefault);
                    this.paint.setStyle(Paint.Style.FILL);
                }
                float f4 = spaceXInGraph2;
                canvas.drawText(toothCodeRound, f4, i6 - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int i7 = this.rect.bottom + this.marginBig + this.rectHeight;
                    Rect rect4 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i7 - this.rectHeight, spaceXInGraph2 + (this.rectWidth / 2), i7);
                    this.decimalRectListB.add(rect4);
                    if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i5 && this.flag.isDicimal()) {
                        this.paint.setStyle(Paint.Style.FILL);
                        this.paint.setColor(this.textRectSelect);
                        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                        canvas.drawRect(rect4, this.paint);
                        this.paint.setColor(this.textColorSelect);
                    } else {
                        this.paint.setStyle(Paint.Style.STROKE);
                        this.paint.setColor(this.decimalRectColor);
                        canvas.drawRect(rect4, this.paint);
                        this.paint.setColor(this.decimalColorDefault);
                        this.paint.setStyle(Paint.Style.FILL);
                    }
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeB, i5)), f4, i7 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i8 = 0; i8 < this.spaceA.size(); i8++) {
                Rect rect5 = new Rect();
                String toothCodeRound3 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i8), this.depthNameA);
                this.paint.getTextBounds(toothCodeRound3, 0, toothCodeRound3.length(), rect5);
                int spaceXInGraph3 = (int) getSpaceXInGraph(this.spaceA.get(i8).intValue());
                int i9 = this.rect.top - 20;
                if (isInteger(getToothCodeAt(this.toothCodeA, i8))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound3, spaceXInGraph3, i9, this.paint);
            }
            for (int i10 = 0; i10 < this.spaceB.size(); i10++) {
                Rect rect6 = new Rect();
                String toothCodeRound4 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i10), this.depthNameB);
                this.paint.getTextBounds(toothCodeRound4, 0, toothCodeRound4.length(), rect6);
                float f5 = this.paint.getFontMetrics().ascent;
                int spaceXInGraph4 = (int) getSpaceXInGraph(this.spaceB.get(i10).intValue());
                int i11 = (this.rect.bottom - ((int) f5)) + 15;
                if (isInteger(getToothCodeAt(this.toothCodeB, i10))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound4, spaceXInGraph4, i11, this.paint);
            }
        }
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        for (int i12 = 0; i12 < this.depthA.size(); i12++) {
            int intValue = this.depthA.get(i12).intValue();
            float spaceXInGraph5 = getSpaceXInGraph(this.spaceA.get(0).intValue());
            float f6 = intValue;
            float f7 = this.rect.bottom - (this.ratio * f6);
            List<Integer> list3 = this.spaceA;
            canvas.drawLine(spaceXInGraph5, f7, getSpaceXInGraph(list3.get(list3.size() - 1).intValue()), this.rect.bottom - (f6 * this.ratio), this.paint);
        }
        for (int i13 = 0; i13 < this.depthB.size(); i13++) {
            int intValue2 = this.depthB.get(i13).intValue();
            float spaceXInGraph6 = getSpaceXInGraph(this.spaceB.get(0).intValue());
            float f8 = intValue2;
            float f9 = this.rect.top + (this.ratio * f8);
            List<Integer> list4 = this.spaceB;
            canvas.drawLine(spaceXInGraph6, f9, getSpaceXInGraph(list4.get(list4.size() - 1).intValue()), this.rect.top + (f8 * this.ratio), this.paint);
        }
        if (isInputModel()) {
            return;
        }
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setPathEffect(null);
        this.path.reset();
        if (getKeyinfo().getAlign() == 0) {
            this.path.moveTo((((this.rect.height() * 2.25f) + this.rect.left) - 10.0f) + 4.0f, this.rect.top - this.rect.height());
            this.path.lineTo((this.rect.height() * 2.25f) + this.rect.left + 10.0f + 4.0f, this.rect.top - this.rect.height());
            this.path.lineTo((this.rect.height() * 2.25f) + this.rect.left + 4.0f, (this.rect.top - this.rect.height()) + 20);
        } else {
            this.path.moveTo((this.rect.right - 10) + 4, this.rect.top - this.rect.height());
            this.path.lineTo(this.rect.right + 10 + 4, this.rect.top - this.rect.height());
            this.path.lineTo(this.rect.right + 4, (this.rect.top - this.rect.height()) + 20);
        }
        this.path.close();
        canvas.drawPath(this.path, this.paint);
        this.paint.setStrokeWidth(2.0f);
        if (getKeyinfo().getAlign() == 0) {
            canvas.drawLine((this.rect.height() * 2.25f) + this.rect.left + 4.0f, this.rect.top - this.rect.height(), (this.rect.height() * 2.25f) + this.rect.left + 4.0f, this.rect.bottom + (this.rect.height() / 3), this.paint);
        } else {
            canvas.drawLine(this.rect.right + 4, this.rect.top - this.rect.height(), this.rect.right + 4, this.rect.bottom + (this.rect.height() / 3), this.paint);
        }
        int width = this.keyInfo.getWidth();
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH) && SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            width = UnitUtils.mm2Inch(width);
        }
        String valueOf = String.valueOf(width);
        this.paint.setTextSize(this.decimalTextSize);
        while (this.paint.measureText(valueOf) > this.rect.height()) {
            this.paint.setTextSize(this.paint.getTextSize() - 2.0f);
        }
        canvas.rotate(90.0f, this.rect.left + (this.rect.height() * 0.9f), this.rect.centerY());
        canvas.drawText(valueOf, this.rect.left + (this.rect.height() * 0.9f), this.rect.centerY(), this.paint);
        canvas.restore();
    }

    private float getSpaceXInGraph(int i) {
        if (this.keyInfo.getAlign() == 0) {
            return (i * this.ratio) + (this.rect.height() * 2.25f) + this.rect.left;
        }
        return this.rect.left + ((this.maxLenght - i) * this.ratio);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        String[] split = str.split(";");
        this.toothCodeA = split[0].split(",");
        this.toothCodeB = split[1].split(",");
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
        this.toothCodeA = (String[]) Arrays.copyOf(this.toothCodeA, i);
        this.toothCodeB = (String[]) Arrays.copyOf(this.toothCodeB, i);
        this.toothAmount = i;
        invalidate();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return this.toothAmount;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public ArrayList<String[]> getToothCode() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        arrayList.add(this.toothCodeA);
        arrayList.add(this.toothCodeB);
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCodeDefault() {
        this.toothCodeA = new String[this.spaceA.size()];
        this.toothCodeB = new String[this.spaceB.size()];
        String keyToothCode = this.keyInfo.getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            setToothCode(keyToothCode);
            return;
        }
        for (int i = 0; i < this.spaceA.size(); i++) {
            this.toothCodeA[i] = "?";
        }
        for (int i2 = 0; i2 < this.spaceB.size(); i2++) {
            this.toothCodeB[i2] = "?";
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public float getRatio() {
        return this.ratio;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onDecimalRectClick(int i, int i2) {
        this.flag.setRowPosition(i);
        this.flag.setColumn(i2);
        this.flag.setDicimal(true);
        showDecimalKeyboard();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onIntegerRectClick(int i, int i2) {
        this.flag.setRowPosition(i);
        this.flag.setColumn(i2);
        this.flag.setDicimal(false);
        showIntegerKeyboard(getAllDepthNames().get(i));
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getIntegerRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.integerRectListA);
        arrayList.add(this.integerRectListB);
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getDecimalRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.decimalRectListA);
        arrayList.add(this.decimalRectListB);
        return arrayList;
    }
}
