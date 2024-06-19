package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.liying.core.bean.KeyInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SideToothKey extends Key {
    private List<Rect> decimalRectListA;
    private List<Rect> decimalRectListB;
    private List<Integer> depthA;
    private List<Integer> depthB;
    private List<String> depthNameA;
    private List<String> depthNameB;
    private List<Rect> integerRectListA;
    private List<Rect> integerRectListB;
    private boolean isDoubleSide;
    private KeyInfo keyInfo;
    private int keyWidth;
    private int maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> spaceA;
    private List<Integer> spaceB;
    private String[] toothCodeA;
    private String[] toothCodeB;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
    }

    public SideToothKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.integerRectListB = new ArrayList();
        this.decimalRectListB = new ArrayList();
        this.keyInfo = keyInfo;
        this.path = new Path();
        this.paint = new Paint();
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        this.spaceA = getAllSpaces().get(0);
        if (getAllSpaces().size() > 1) {
            this.isDoubleSide = true;
            this.spaceB = getAllSpaces().get(1);
        }
        this.depthNameA = getAllDepthNames().get(0);
        if (getAllDepthNames().size() > 1) {
            this.depthNameB = getAllDepthNames().get(1);
        }
        this.depthA = getAllDepths().get(0);
        if (getAllDepths().size() > 1) {
            this.depthB = getAllDepths().get(1);
        }
        int intValue = this.spaceA.get(0).intValue();
        this.keyWidth = keyInfo.getWidth();
        this.maxLenght = intValue * 2;
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
        float f = (measuredHeight * 1.0f) / (this.keyWidth * 7);
        this.ratio = f;
        int i = this.maxLenght;
        float f2 = i * f;
        float f3 = measuredWidth;
        if (f2 < f3) {
            this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 6) / 14) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 8) / 14) + getPaddingTop());
        } else {
            this.ratio = (f3 * 1.0f) / i;
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth + getPaddingLeft(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        String toothCodeRound;
        String toothCodeRound2;
        this.path.reset();
        this.paint.reset();
        this.paint.setColor(this.keyColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(4.0f);
        this.path.moveTo(this.rect.left, this.rect.top - (this.rect.height() * 1.3f));
        this.path.addArc(new RectF(this.rect.left - this.rect.height(), this.rect.top - (this.rect.height() * 1.3f), this.rect.left + this.rect.height(), this.rect.bottom + (this.rect.height() * 1.3f)), -90.0f, 180.0f);
        this.path.lineTo(this.rect.left, this.rect.top - (this.rect.height() * 1.3f));
        canvas.clipPath(this.path, Region.Op.DIFFERENCE);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setStrokeWidth(2.0f);
        this.path.moveTo(this.rect.left, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4));
        this.path.lineTo(this.rect.right, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4));
        this.path.addArc(new RectF(this.rect.right - (this.rect.height() / 4), ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4), this.rect.right + (this.rect.height() / 4), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4)), -90.0f, 180.0f);
        this.path.lineTo(this.rect.left, ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4));
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        int i = 0;
        for (int i2 = 0; i2 < this.spaceA.size(); i2++) {
            if (i2 < this.spaceA.size() - 1) {
                i = Math.abs(this.spaceA.get(i2 + 1).intValue() - this.spaceA.get(i2).intValue()) / 2;
            }
            if (i2 == 0) {
                this.path.moveTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) - (i * this.ratio), ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4));
            }
            String toothCodeRound3 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i2), this.depthNameA);
            float f = i;
            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) - (this.ratio * f), (((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4)) - getYInGraph(this.depthA, this.depthNameA, toothCodeRound3));
            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + (this.ratio * f), (((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4)) - getYInGraph(this.depthA, this.depthNameA, toothCodeRound3));
            if (i2 == this.spaceA.size() - 1) {
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + (this.ratio * f), ((this.rect.top + this.rect.bottom) / 2) - ((this.rect.height() * 1) / 2));
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + (this.ratio * f) + 10.0f, ((this.rect.top + this.rect.bottom) / 2) - ((this.rect.height() * 1) / 2));
                this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + (f * this.ratio) + 10.0f, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4));
            }
        }
        if (this.isDoubleSide) {
            for (int i3 = 0; i3 < this.spaceB.size(); i3++) {
                if (i3 < this.spaceB.size() - 1) {
                    i = Math.abs(this.spaceB.get(i3 + 1).intValue() - this.spaceB.get(i3).intValue()) / 2;
                }
                if (i3 == 0) {
                    this.path.moveTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) - (i * this.ratio), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4));
                }
                String toothCodeRound4 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i3), this.depthNameB);
                float f2 = i;
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) - (this.ratio * f2), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4) + getYInGraph(this.depthA, this.depthNameA, toothCodeRound4));
                this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) + (this.ratio * f2), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4) + getYInGraph(this.depthA, this.depthNameA, toothCodeRound4));
                if (i3 == this.spaceB.size() - 1) {
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) + (this.ratio * f2), ((this.rect.top + this.rect.bottom) / 2) + ((this.rect.height() * 1) / 2));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) + (this.ratio * f2) + 10.0f, ((this.rect.top + this.rect.bottom) / 2) + ((this.rect.height() * 1) / 2));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i3).intValue()) + (f2 * this.ratio) + 10.0f, ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4));
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            for (int i4 = 0; i4 < this.spaceA.size(); i4++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound2 = getToothCodeInt(getToothCodeAt(this.toothCodeA, i4));
                } else {
                    toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i4), this.depthNameA);
                }
                List<Integer> list = this.spaceA;
                int spaceXInGraph = ((int) getSpaceXInGraph(list.get(list.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i4));
                int i5 = (this.rect.top - this.marginBig) - this.rectHeight;
                Rect rect = new Rect(spaceXInGraph - (this.rectWidth / 2), i5 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph, i5);
                this.integerRectListA.add(rect);
                if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i4 && !this.flag.isDicimal()) {
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
                canvas.drawText(toothCodeRound2, f3, i5 - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int i6 = (this.rect.top - this.marginSmall) - this.rectHeight;
                    Rect rect2 = new Rect(spaceXInGraph - (this.rectWidth / 2), i6 - this.rectHeight, spaceXInGraph + (this.rectWidth / 2), i6);
                    this.decimalRectListA.add(rect2);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i4 && this.flag.isDicimal()) {
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeA, i4)), f3, i6 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
            if (this.isDoubleSide) {
                this.integerRectListB.clear();
                this.decimalRectListB.clear();
                for (int i7 = 0; i7 < this.spaceB.size(); i7++) {
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCodeB, i7));
                    } else {
                        toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCodeB, i7), this.depthNameB);
                    }
                    List<Integer> list2 = this.spaceA;
                    int spaceXInGraph2 = ((int) getSpaceXInGraph(list2.get(list2.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i7));
                    int i8 = this.rect.bottom + this.marginSmall + (this.rectHeight * 2);
                    Rect rect3 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i8 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph2, i8);
                    this.integerRectListB.add(rect3);
                    if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i7 && !this.flag.isDicimal()) {
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
                    canvas.drawText(toothCodeRound, f4, i8 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i9 = this.rect.bottom + this.marginBig + (this.rectHeight * 2);
                        Rect rect4 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i9 - this.rectHeight, spaceXInGraph2 + (this.rectWidth / 2), i9);
                        this.decimalRectListB.add(rect4);
                        if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i7 && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeB, i7)), f4, i9 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i10 = 0; i10 < this.spaceA.size(); i10++) {
                Rect rect5 = new Rect();
                String toothCodeRound5 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i10), this.depthNameA);
                this.paint.getTextBounds(toothCodeRound5, 0, toothCodeRound5.length(), rect5);
                int spaceXInGraph3 = (int) getSpaceXInGraph(this.spaceA.get(i10).intValue());
                int height = (((this.rect.top + this.rect.bottom) / 2) - ((this.rect.height() * 5) / 4)) - 20;
                this.paint.setStyle(Paint.Style.FILL);
                if (isInteger(getToothCodeAt(this.toothCodeA, i10))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound5, spaceXInGraph3, height, this.paint);
            }
            if (this.isDoubleSide) {
                for (int i11 = 0; i11 < this.spaceB.size(); i11++) {
                    Rect rect6 = new Rect();
                    String toothCodeRound6 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i11), this.depthNameB);
                    this.paint.getTextBounds(toothCodeRound6, 0, toothCodeRound6.length(), rect6);
                    float f5 = this.paint.getFontMetrics().ascent;
                    int spaceXInGraph4 = (int) getSpaceXInGraph(this.spaceB.get(i11).intValue());
                    int height2 = ((((this.rect.top + this.rect.bottom) / 2) + ((this.rect.height() * 5) / 4)) - ((int) f5)) + 15;
                    this.paint.setStyle(Paint.Style.FILL);
                    if (isInteger(getToothCodeAt(this.toothCodeB, i11))) {
                        this.paint.setColor(this.textColorNoDecimal);
                    } else {
                        this.paint.setColor(this.textColorExistDecimal);
                    }
                    canvas.drawText(toothCodeRound6, spaceXInGraph4, height2, this.paint);
                }
            }
        }
        this.path.reset();
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        for (int i12 = 0; i12 < this.depthA.size(); i12++) {
            int intValue = this.spaceA.get(0).intValue();
            float spaceXInGraph5 = getSpaceXInGraph(this.spaceA.get(0).intValue());
            float f6 = intValue;
            float height3 = (((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4)) - (this.ratio * f6);
            List<Integer> list3 = this.spaceA;
            canvas.drawLine(spaceXInGraph5, height3, getSpaceXInGraph(list3.get(list3.size() - 1).intValue()), (((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() / 4)) - (f6 * this.ratio), this.paint);
        }
        if (this.isDoubleSide) {
            for (int i13 = 0; i13 < this.depthB.size(); i13++) {
                int intValue2 = this.spaceB.get(0).intValue();
                float spaceXInGraph6 = getSpaceXInGraph(this.spaceB.get(i13).intValue());
                float f7 = intValue2;
                float height4 = ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4) + (this.ratio * f7);
                List<Integer> list4 = this.spaceB;
                canvas.drawLine(spaceXInGraph6, height4, getSpaceXInGraph(list4.get(list4.size() - 1).intValue()), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() / 4) + (f7 * this.ratio), this.paint);
            }
        }
        canvas.drawPath(this.path, this.paint);
    }

    private float getSpaceXInGraph(int i) {
        return this.rect.left + ((this.maxLenght - i) * this.ratio);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        String[] split = str.split(";");
        if (this.isDoubleSide) {
            this.toothCodeA = split[0].split(",");
            this.toothCodeB = split[1].split(",");
        } else {
            this.toothCodeA = split[0].split(",");
        }
        invalidate();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public ArrayList<String[]> getToothCode() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        if (this.isDoubleSide) {
            arrayList.add(this.toothCodeA);
            arrayList.add(this.toothCodeB);
        } else {
            arrayList.add(this.toothCodeA);
        }
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCodeDefault() {
        this.toothCodeA = new String[this.spaceA.size()];
        if (this.isDoubleSide) {
            this.toothCodeB = new String[this.spaceB.size()];
        }
        String keyToothCode = this.keyInfo.getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            setToothCode(keyToothCode);
            return;
        }
        for (int i = 0; i < this.spaceA.size(); i++) {
            this.toothCodeA[i] = "?";
        }
        if (this.isDoubleSide) {
            for (int i2 = 0; i2 < this.spaceB.size(); i2++) {
                this.toothCodeB[i2] = "?";
            }
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public float getRatio() {
        return this.ratio;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onDecimalRectClick(int i, int i2) {
        showDecimalKeyboard();
        this.flag.setRowPosition(i);
        this.flag.setColumn(i2);
        this.flag.setDicimal(true);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onIntegerRectClick(int i, int i2) {
        if (this.isDoubleSide) {
            showIntegerKeyboard(getAllDepthNames().get(0));
        } else {
            showIntegerKeyboard(getAllDepthNames().get(i));
        }
        this.flag.setRowPosition(i);
        this.flag.setColumn(i2);
        this.flag.setDicimal(false);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getIntegerRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.integerRectListA);
        if (this.isDoubleSide) {
            arrayList.add(this.integerRectListB);
        }
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getDecimalRectContainer() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.decimalRectListA);
        if (this.isDoubleSide) {
            arrayList.add(this.decimalRectListB);
        }
        return arrayList;
    }
}
