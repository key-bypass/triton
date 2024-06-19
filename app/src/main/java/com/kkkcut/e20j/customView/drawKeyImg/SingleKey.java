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
public class SingleKey extends Key {
    private float angle;
    private CornerPathEffect cornerPathEffect;
    private List<Rect> decimalRectListA;
    private List<Integer> depthA;
    private List<String> depthNameA;
    private int extraBit;
    private List<Rect> integerRectListA;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> spaceA;
    private List<Integer> spaceWidthA;
    private int toothAmount;
    private String[] toothCodeA;

    public SingleKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.angle = 1.5707964f;
        this.cornerPathEffect = new CornerPathEffect(2.0f);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.path = new Path();
        this.paint = new Paint();
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        this.depthA = getAllDepths().get(0);
        this.spaceA = getAllSpaces().get(0);
        this.depthNameA = getAllDepthNames().get(0);
        this.spaceWidthA = getAllSpaceWidths().get(0);
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
        this.toothCodeA = new String[this.spaceA.size()];
        for (int i = 0; i < this.spaceA.size(); i++) {
            this.toothCodeA[i] = "?";
        }
        this.toothAmount = this.spaceA.size();
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
        this.path.reset();
        this.paint.reset();
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
        float height = this.rect.left + (this.rect.height() * 2.25f);
        if (getKeyinfo().getAlign() == 0) {
            this.path.moveTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.top - (this.rect.height() * 0.2f));
            this.path.lineTo(height, this.rect.top - (this.rect.height() * 0.2f));
            this.path.lineTo(height, this.rect.top);
            if (this.spaceA.get(0).intValue() > 150) {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(0)) - (150 * this.ratio), this.rect.top);
            }
        } else {
            this.path.moveTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.top);
            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(0)) - (150 * this.ratio), this.rect.top);
        }
        for (int i = 0; i < this.spaceA.size(); i++) {
            if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, i)) || getToothCodeAt(this.toothCodeA, i).equals("?")) {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i)) - getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.top);
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i)) + getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.top);
            } else {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i)) - getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i)));
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i)) + getHalfSpaceWidth(this.spaceWidthA.get(i)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i)));
            }
        }
        if (getKeyinfo().getAlign() == 0) {
            this.path.lineTo((getMaxSpace() * this.ratio) + (this.rect.height() * 2.25f) + (this.extraBit * this.ratio) + this.rect.left, this.rect.top);
        } else {
            this.path.lineTo(this.rect.right - ((getMinSpace() * this.ratio) / 2.0f), this.rect.top);
        }
        this.path.lineTo(this.rect.right, (this.rect.top + this.rect.bottom) / 2);
        if (getKeyinfo().getAlign() == 0) {
            this.path.lineTo((getMaxSpace() * this.ratio) + (this.rect.height() * 2.25f) + (this.extraBit * this.ratio) + this.rect.left, this.rect.bottom);
        } else {
            this.path.lineTo(this.rect.right - ((getMinSpace() * this.ratio) / 2.0f), this.rect.bottom);
        }
        if (getKeyinfo().getAlign() == 0) {
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom);
        } else {
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom);
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
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCodeA, i2));
                } else {
                    toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCodeA, i2), this.depthNameA);
                }
                List<Integer> list = this.spaceA;
                int spaceXInGraph = ((int) getSpaceXInGraph(list.get(list.size() - 1))) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i2));
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
                float f = spaceXInGraph;
                canvas.drawText(toothCodeRound, f, i3 - AutoUtils.getPercentHeightSize(4), this.paint);
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeA, i2)), f, i4 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.paint.setStyle(Paint.Style.FILL);
            for (int i5 = 0; i5 < this.spaceA.size(); i5++) {
                Rect rect3 = new Rect();
                String toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i5), this.depthNameA);
                this.paint.getTextBounds(toothCodeRound2, 0, toothCodeRound2.length(), rect3);
                int spaceXInGraph2 = (int) getSpaceXInGraph(this.spaceA.get(i5));
                int percentHeightSize = this.rect.top - AutoUtils.getPercentHeightSize(20);
                if (isInteger(getToothCodeAt(this.toothCodeA, i5))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound2, spaceXInGraph2, percentHeightSize, this.paint);
            }
        }
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        for (int i6 = 0; i6 < this.depthA.size(); i6++) {
            int intValue = this.depthA.get(i6).intValue();
            float spaceXInGraph3 = getSpaceXInGraph(this.spaceA.get(0));
            float f2 = intValue;
            float f3 = this.rect.bottom - (this.ratio * f2);
            List<Integer> list2 = this.spaceA;
            canvas.drawLine(spaceXInGraph3, f3, getSpaceXInGraph(list2.get(list2.size() - 1)), this.rect.bottom - (f2 * this.ratio), this.paint);
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
        int width = getKeyinfo().getWidth();
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH) && SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            width = UnitUtils.mm2Inch(width);
        }
        String valueOf = String.valueOf(width);
        this.paint.setTextSize(this.decimalTextSize);
        while (this.paint.measureText(valueOf) > this.rect.height()) {
            this.paint.setTextSize(this.paint.getTextSize() - 2.0f);
        }
        canvas.rotate(90.0f, this.rect.left + (this.rect.height() * 1.875f), this.rect.centerY());
        canvas.drawText(valueOf, this.rect.left + (this.rect.height() * 1.875f), this.rect.centerY(), this.paint);
        canvas.restore();
    }

    private float getSpaceXInGraph(Integer num) {
        if (getKeyinfo().getAlign() == 0) {
            return (num.intValue() * this.ratio) + (this.rect.height() * 2.25f) + this.rect.left;
        }
        return this.rect.left + ((this.maxLenght - num.intValue()) * this.ratio);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        this.toothCodeA = str.split(";")[0].split(",");
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
        this.toothCodeA = (String[]) Arrays.copyOf(this.toothCodeA, i);
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
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCodeDefault() {
        this.toothCodeA = new String[this.spaceA.size()];
        String keyToothCode = getKeyinfo().getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            setToothCode(keyToothCode);
            return;
        }
        for (int i = 0; i < this.spaceA.size(); i++) {
            this.toothCodeA[i] = "?";
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public float getRatio() {
        return this.ratio;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onDecimalRectClick(int i, int i2) {
        this.flag.setColumn(i2);
        this.flag.setDicimal(true);
        showDecimalKeyboard();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onIntegerRectClick(int i, int i2) {
        this.flag.setColumn(i2);
        this.flag.setDicimal(false);
        showIntegerKeyboard(getAllDepthNames().get(0));
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getIntegerRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.integerRectListA);
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getDecimalRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.decimalRectListA);
        return arrayList;
    }
}
