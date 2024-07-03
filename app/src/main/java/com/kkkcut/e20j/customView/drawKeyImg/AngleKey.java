package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.Log;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.cutting.machine.bean.KeyInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AngleKey extends Key {
    private static final String TAG = "AngleKey";
    private List<Rect> decimalRectListA;
    private List<Rect> decimalRectListB;
    private List<String> depthName;
    private double diff;
    private List<Rect> integerRectListA;
    private List<Rect> integerRectListB;
    private KeyInfo keyInfo;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> space;
    private String[] toothCode;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
    }

    public AngleKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.space = new ArrayList();
        this.depthName = new ArrayList();
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
        this.space = getAllSpaces().get(0);
        this.depthName = getAllDepthNames().get(0);
        int intValue = this.space.get(0).intValue();
        int width = keyInfo.getWidth();
        this.keyWidth = width;
        if (width == 0) {
            this.keyWidth = 600;
        }
        this.maxLenght = intValue + (this.keyWidth * 5.2f);
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
        float f = (measuredHeight * 1.0f) / (this.keyWidth * 5);
        this.ratio = f;
        float f2 = this.maxLenght;
        float f3 = measuredWidth;
        if (f * f2 < f3) {
            this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 10) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 6) / 10) + getPaddingTop());
        } else {
            this.ratio = (f3 * 1.0f) / f2;
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth - getPaddingRight(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
        }
        this.diff = (this.rect.height() * 0.5d) / (this.space.size() - 1);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        String toothCodeRound;
        String toothCodeRound2;
        this.path.reset();
        this.paint.reset();
        canvas.restore();
        this.paint.setColor(this.keyColor);
        this.paint.setStyle(Paint.Style.STROKE);
        int i = 1;
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(4.0f);
        this.path.moveTo(this.rect.left, this.rect.top - (this.rect.height() * 1.3f));
        this.path.addArc(new RectF(this.rect.left - this.rect.height(), this.rect.top - (this.rect.height() * 1.3f), this.rect.left + this.rect.height(), this.rect.bottom + (this.rect.height() * 1.3f)), -90.0f, 180.0f);
        this.path.lineTo(this.rect.left, this.rect.top - (this.rect.height() * 1.3f));
        canvas.clipPath(this.path, Region.Op.DIFFERENCE);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setStrokeWidth(2.0f);
        this.path.moveTo(this.rect.left, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() * 0.1f));
        this.path.lineTo((getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f)) - 8.0f, ((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() * 0.1f));
        this.path.lineTo(getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f), (((this.rect.top + this.rect.bottom) / 2) - (this.rect.height() * 0.1f)) - 8.0f);
        this.path.lineTo(getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f), (this.rect.top - (this.rect.height() * 0.2f)) + 8.0f);
        this.path.lineTo((getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f)) + 8.0f, this.rect.top - (this.rect.height() * 0.2f));
        Path path = this.path;
        List<Integer> list = this.space;
        path.lineTo((getSpaceXInGraph(list.get(list.size() - 1).intValue()) + (this.ratio * 200.0f)) - 5.0f, this.rect.top - (this.rect.height() * 0.2f));
        Path path2 = this.path;
        List<Integer> list2 = this.space;
        path2.lineTo(getSpaceXInGraph(list2.get(list2.size() - 1).intValue()) + (this.ratio * 200.0f), (this.rect.top - (this.rect.height() * 0.2f)) + 5.0f);
        Path path3 = this.path;
        List<Integer> list3 = this.space;
        path3.lineTo(getSpaceXInGraph(list3.get(list3.size() - 1).intValue()) + (this.ratio * 200.0f), this.rect.top);
        Path path4 = this.path;
        List<Integer> list4 = this.space;
        path4.lineTo(((getSpaceXInGraph(list4.get(list4.size() - 1).intValue()) + (this.ratio * 200.0f)) + (this.rect.height() * 1.2f)) - 15.0f, this.rect.top);
        Path path5 = this.path;
        List<Integer> list5 = this.space;
        path5.lineTo(getSpaceXInGraph(list5.get(list5.size() - 1).intValue()) + (this.ratio * 200.0f) + (this.rect.height() * 1.2f), this.rect.top + 15);
        Path path6 = this.path;
        List<Integer> list6 = this.space;
        path6.lineTo(getSpaceXInGraph(list6.get(list6.size() - 1).intValue()) + (this.ratio * 200.0f) + (this.rect.height() * 1.2f), this.rect.bottom - 15);
        Path path7 = this.path;
        List<Integer> list7 = this.space;
        path7.lineTo(((getSpaceXInGraph(list7.get(list7.size() - 1).intValue()) + (this.ratio * 200.0f)) + (this.rect.height() * 1.2f)) - 15.0f, this.rect.bottom);
        Path path8 = this.path;
        List<Integer> list8 = this.space;
        path8.lineTo(getSpaceXInGraph(list8.get(list8.size() - 1).intValue()) + (this.ratio * 200.0f), this.rect.bottom);
        Path path9 = this.path;
        List<Integer> list9 = this.space;
        path9.lineTo(getSpaceXInGraph(list9.get(list9.size() - 1).intValue()) + (this.ratio * 200.0f), (this.rect.bottom + (this.rect.height() * 0.2f)) - 5.0f);
        Path path10 = this.path;
        List<Integer> list10 = this.space;
        path10.lineTo((getSpaceXInGraph(list10.get(list10.size() - 1).intValue()) + (this.ratio * 200.0f)) - 5.0f, this.rect.bottom + (this.rect.height() * 0.2f));
        this.path.lineTo((getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f)) + 8.0f, this.rect.bottom + (this.rect.height() * 0.2f));
        this.path.lineTo(getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f), (this.rect.bottom + (this.rect.height() * 0.2f)) - 8.0f);
        this.path.lineTo(getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f), ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() * 0.1f) + 8.0f);
        this.path.lineTo((getSpaceXInGraph(this.space.get(0).intValue()) - (this.ratio * 200.0f)) - 8.0f, ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() * 0.1f));
        this.path.lineTo(this.rect.left, ((this.rect.top + this.rect.bottom) / 2) + (this.rect.height() * 0.1f));
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        int i2 = 0;
        for (int i3 = 0; i3 < this.space.size(); i3++) {
            if (i3 < this.space.size() - 1) {
                i2 = Math.abs(this.space.get(i3 + 1).intValue() - this.space.get(i3).intValue()) / 2;
                Log.i(TAG, "onDraw: " + i2);
            }
            String toothCodeAt = getToothCodeAt(this.toothCode, i3);
            int indexOf = toothCodeAt.equals("?") ? 0 : this.depthName.indexOf(toothCodeAt);
            if (i3 == 0) {
                this.path.moveTo(getSpaceXInGraph(this.space.get(i3).intValue()) - (i2 * this.ratio), this.rect.top - (this.rect.height() * 0.2f));
            }
            float f = i2;
            double d = indexOf;
            this.path.lineTo(getSpaceXInGraph(this.space.get(i3).intValue()) - (this.ratio * f), (float) (this.rect.top + (this.diff * d)));
            this.path.lineTo(getSpaceXInGraph(this.space.get(i3).intValue()) + (this.ratio * f), (float) (this.rect.top + (this.diff * d)));
            if (i3 == this.space.size() - 1) {
                this.path.lineTo(getSpaceXInGraph(this.space.get(i3).intValue()) + (f * this.ratio), this.rect.top - (this.rect.height() * 0.2f));
            }
        }
        int size = this.space.size() - 1;
        while (size >= 0) {
            String toothCodeRound3 = getToothCodeRound(getToothCodeAt(this.toothCode, size), this.depthName);
            int indexOf2 = toothCodeRound3.equals("?") ? 0 : this.depthName.indexOf(toothCodeRound3);
            if (size == this.space.size() - i) {
                this.path.moveTo(getSpaceXInGraph(this.space.get(size).intValue()) + (i2 * this.ratio), this.rect.bottom + (this.rect.height() * 0.2f));
            }
            float f2 = i2;
            int i4 = i2;
            double d2 = indexOf2;
            this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) + (this.ratio * f2), (float) (this.rect.bottom - (this.diff * d2)));
            this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - (this.ratio * f2), (float) (this.rect.bottom - (this.diff * d2)));
            if (size == 0) {
                this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - (f2 * this.ratio), this.rect.bottom + (this.rect.height() * 0.2f));
            }
            size--;
            i2 = i4;
            i = 1;
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i5 = 0; i5 < this.space.size(); i5++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound2 = getToothCodeInt(getToothCodeAt(this.toothCode, i5));
                } else {
                    toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCode, i5), this.depthName);
                }
                List<Integer> list11 = this.space;
                int spaceXInGraph = ((int) getSpaceXInGraph(list11.get(list11.size() - 1).intValue())) - (this.twoRectSpace * ((this.space.size() - 1) - i5));
                int i6 = this.rect.top - this.marginBig;
                Rect rect = new Rect(spaceXInGraph - (this.rectWidth / 2), i6 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph, i6);
                this.integerRectListA.add(rect);
                if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i5 && !this.flag.isDicimal()) {
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
                canvas.drawText(toothCodeRound2, f3, i6 - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int i7 = this.rect.top - this.marginSmall;
                    Rect rect2 = new Rect(spaceXInGraph - (this.rectWidth / 2), i7 - this.rectHeight, spaceXInGraph + (this.rectWidth / 2), i7);
                    this.decimalRectListA.add(rect2);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i5 && this.flag.isDicimal()) {
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCode, i5)), f3, i7 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
            this.integerRectListB.clear();
            this.decimalRectListB.clear();
            for (int i8 = 0; i8 < this.space.size(); i8++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCode, i8));
                } else {
                    toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCode, i8), this.depthName);
                }
                List<Integer> list12 = this.space;
                int spaceXInGraph2 = ((int) getSpaceXInGraph(list12.get(list12.size() - 1).intValue())) - (this.twoRectSpace * ((this.space.size() - 1) - i8));
                int i9 = this.rect.bottom + this.marginSmall + this.rectHeight;
                Rect rect3 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i9 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph2, i9);
                this.integerRectListB.add(rect3);
                if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i8 && !this.flag.isDicimal()) {
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
                canvas.drawText(toothCodeRound, f4, i9 - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int i10 = this.rect.bottom + this.marginBig + this.rectHeight;
                    Rect rect4 = new Rect(spaceXInGraph2 - (this.rectWidth / 2), i10 - this.rectHeight, spaceXInGraph2 + (this.rectWidth / 2), i10);
                    this.decimalRectListB.add(rect4);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i8 && this.flag.isDicimal()) {
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCode, i8)), f4, i10 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
            return;
        }
        this.paint.setTextSize(this.integerTextSize - 8);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setStrokeWidth(1.0f);
        for (int i11 = 0; i11 < this.space.size(); i11++) {
            Rect rect5 = new Rect();
            String toothCodeRound4 = getToothCodeRound(getToothCodeAt(this.toothCode, i11), this.depthName);
            this.paint.getTextBounds(toothCodeRound4, 0, toothCodeRound4.length(), rect5);
            int spaceXInGraph3 = (int) getSpaceXInGraph(this.space.get(i11).intValue());
            int height = (int) (this.rect.top - (this.rect.height() * 0.3d));
            if (isInteger(getToothCodeAt(this.toothCode, i11))) {
                this.paint.setColor(this.textColorNoDecimal);
            } else {
                this.paint.setColor(this.textColorExistDecimal);
            }
            canvas.drawText(toothCodeRound4, spaceXInGraph3, height, this.paint);
        }
        for (int i12 = 0; i12 < this.space.size(); i12++) {
            Rect rect6 = new Rect();
            String toothCodeRound5 = getToothCodeRound(getToothCodeAt(this.toothCode, i12), this.depthName);
            this.paint.getTextBounds(toothCodeRound5, 0, toothCodeRound5.length(), rect6);
            float f5 = this.paint.getFontMetrics().ascent;
            int spaceXInGraph4 = (int) getSpaceXInGraph(this.space.get(i12).intValue());
            int height2 = (int) ((this.rect.bottom - ((int) f5)) + 15 + (this.rect.height() * 0.3d));
            if (isInteger(getToothCodeAt(this.toothCode, i12))) {
                this.paint.setColor(this.textColorNoDecimal);
            } else {
                this.paint.setColor(this.textColorExistDecimal);
            }
            canvas.drawText(toothCodeRound5, spaceXInGraph4, height2, this.paint);
        }
    }

    private float getSpaceXInGraph(int i) {
        return (this.rect.left + ((this.maxLenght - i) * this.ratio)) - (this.rect.height() * 1.2f);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        this.toothCode = str.split(";")[0].split(",");
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public ArrayList<String[]> getToothCode() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        arrayList.add(this.toothCode);
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCodeDefault() {
        this.toothCode = new String[this.space.size()];
        String keyToothCode = this.keyInfo.getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            setToothCode(keyToothCode);
            return;
        }
        for (int i = 0; i < this.space.size(); i++) {
            this.toothCode[i] = "?";
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
        showIntegerKeyboard(getAllDepthNames().get(0));
        this.flag.setColumn(i2);
        this.flag.setDicimal(false);
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
