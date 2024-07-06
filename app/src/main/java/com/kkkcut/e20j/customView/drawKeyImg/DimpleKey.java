package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.core.internal.view.SupportMenu;

import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DimpleKey extends Key {
    private static final String TAG = "DimpleKey";
    private List<List<Integer>> allSpace;
    int baseY;
    int beyondY;
    private boolean canScroll;
    private CornerPathEffect cornerPathEffect;
    private List<List<Rect>> decimalRectListContainer;
    private List<List<String>> depthNames;
    private List<List<Integer>> depths;
    private boolean existSide;
    private GestureDetector gestureDetector;
    private List<List<Rect>> integerRectListContainer;
    private KeyInfo keyInfo;
    private int keyWidth;
    private int maxColumCount;
    private float maxLenght;
    private int maxRowCount;
    private int maxSpace;
    private GestureDetector.OnGestureListener onGestureListener;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> rowPosition;
    private final Paint textPaint;
    private int thick;
    private ArrayList<String[]> toothCodes;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
    }

    public DimpleKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.allSpace = new ArrayList();
        this.depths = new ArrayList();
        this.depthNames = new ArrayList();
        this.toothCodes = new ArrayList<>();
        this.rowPosition = new ArrayList();
        this.integerRectListContainer = new ArrayList();
        this.decimalRectListContainer = new ArrayList();
        this.cornerPathEffect = new CornerPathEffect(10.0f);
        this.onGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.kkkcut.e20j.customView.drawKeyImg.DimpleKey.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                DimpleKey.super.onTouchEvent(motionEvent);
                return super.onSingleTapUp(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!DimpleKey.this.canScroll) {
                    return true;
                }
                DimpleKey.this.baseY = (int) (DimpleKey.this.baseY - f2);
                if (DimpleKey.this.baseY > AutoUtils.getPercentHeightSize(10)) {
                    DimpleKey.this.baseY = AutoUtils.getPercentHeightSize(10);
                }
                if (DimpleKey.this.baseY < DimpleKey.this.beyondY) {
                    DimpleKey dimpleKey = DimpleKey.this;
                    dimpleKey.baseY = dimpleKey.beyondY;
                }
                DimpleKey.this.invalidate();
                return true;
            }
        };
        this.path = new Path();
        this.paint = new Paint();
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(this.integerColorDefault);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        if (keyInfo.isDimpleMotherSonKey()) {
            paint.setTextSize(20.0f);
        } else {
            paint.setTextSize(15.0f);
        }
        this.keyInfo = keyInfo;
        initData(keyInfo);
        this.gestureDetector = new GestureDetector(getContext(), this.onGestureListener);
    }

    public void initData(KeyInfo keyInfo) {
        String[] split = keyInfo.getSpaceStr().split(";");
        for (List<Integer> list : getAllSpaces()) {
            this.toothCodes.add(new String[list.size()]);
            this.maxColumCount = Math.max(this.maxColumCount, list.size());
        }
        this.maxRowCount = getAllSpaces().size();
        this.allSpace = getAllSpaces();
        this.depths = getAllDepths();
        this.depthNames = getAllDepthNames();
        String row_pos = keyInfo.getRow_pos();
        String[] split2 = row_pos.split(";");
        for (int i = 0; i < split2.length; i++) {
            if (TextUtils.isEmpty(split2[i])) {
                this.rowPosition.add(0);
            } else {
                this.rowPosition.add(Integer.valueOf(Integer.parseInt(split2[i])));
            }
        }
        if (row_pos.contains("-")) {
            this.existSide = true;
        }
        int thick = keyInfo.getThick();
        this.thick = thick;
        if (thick == 0) {
            for (int i2 = 0; i2 < this.depths.size(); i2++) {
                if (this.rowPosition.get(i2).intValue() > 0) {
                    for (int i3 = 0; i3 < this.depths.get(i2).size(); i3++) {
                        this.thick = Math.max(this.thick, this.depths.get(i2).get(i3).intValue());
                    }
                }
            }
        }
        for (String str : split) {
            String[] split3 = str.split(",");
            if (keyInfo.getAlign() == 0) {
                this.maxSpace = Math.max(Integer.parseInt(split3[split3.length - 1]), this.maxSpace);
            } else {
                this.maxSpace = Math.max(Integer.parseInt(split3[0]), this.maxSpace);
            }
        }
        this.keyWidth = keyInfo.getWidth();
        if (keyInfo.getAlign() == 0) {
            if (keyInfo.getLength() == 0) {
                this.maxLenght = this.maxSpace + (this.keyWidth * 1.3f) + 400.0f;
            } else {
                this.maxLenght = keyInfo.getLength() + (this.keyWidth * 1.3f);
            }
        } else {
            this.maxLenght = this.maxSpace + (this.keyWidth * 1.3f);
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
        float f = measuredHeight;
        int i = this.keyWidth;
        float f2 = (f * 1.0f) / (i * 3);
        this.ratio = f2;
        float f3 = this.maxLenght;
        float f4 = measuredWidth;
        if (f2 * f3 < f4) {
            if (this.existSide) {
                this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 1) / 6) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), getPaddingTop() + (measuredHeight / 2));
            } else {
                this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 2) / 6) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 6) + getPaddingTop());
            }
        } else {
            float f5 = (f4 * 1.0f) / f3;
            this.ratio = f5;
            if (this.existSide) {
                int i2 = (int) (f / (i * f5));
                this.rect = new Rect(((measuredWidth - ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), (((i2 - 2) * measuredHeight) / (i2 * 2)) + getPaddingTop(), ((measuredWidth + ((int) (this.maxLenght * this.ratio))) / 2) + getPaddingLeft(), getPaddingTop() + (measuredHeight / 2));
            } else {
                this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth + getPaddingLeft(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
            }
        }
        if (isShowDecimal()) {
            this.baseY = ((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - ((this.maxRowCount * 2) * AutoUtils.getPercentHeightSize(57))) / 2) + getPaddingTop();
        } else {
            this.baseY = ((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - (this.maxRowCount * AutoUtils.getPercentHeightSize(57))) / 2) + getPaddingTop();
        }
        if (this.baseY < AutoUtils.getPercentHeightSize(10)) {
            this.canScroll = true;
            this.baseY = AutoUtils.getPercentHeightSize(10);
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setShowDecimal(boolean z) {
        if (z) {
            this.baseY = ((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - ((this.maxRowCount * 2) * AutoUtils.getPercentHeightSize(57))) / 2) + getPaddingTop();
            this.beyondY = ((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - ((this.maxRowCount * 2) * AutoUtils.getPercentHeightSize(57));
        } else {
            this.baseY = ((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - (this.maxRowCount * AutoUtils.getPercentHeightSize(57))) / 2) + getPaddingTop();
            this.beyondY = ((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - (this.maxRowCount * AutoUtils.getPercentHeightSize(57));
        }
        if (this.baseY < AutoUtils.getPercentHeightSize(10)) {
            this.baseY = AutoUtils.getPercentHeightSize(10);
            this.canScroll = true;
        }
        super.setShowDecimal(z);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        float f;
        float height;
        int i;
        int percentHeightSize;
        String toothCodeRound;
        int i2 = 4;
        int i3 = 1;
        if (isInputModel()) {
            this.paint.reset();
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.paint.setAntiAlias(true);
            int measuredWidth = ((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - (this.maxColumCount * this.twoRectSpace)) / 2) + getPaddingLeft();
            this.integerRectListContainer.clear();
            this.decimalRectListContainer.clear();
            int i4 = 0;
            while (i4 < this.allSpace.size()) {
                List<Integer> list = this.allSpace.get(i4);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (isShowDecimal()) {
                    i = this.baseY;
                    percentHeightSize = AutoUtils.getPercentHeightSize(45) * 2;
                } else {
                    i = this.baseY;
                    percentHeightSize = AutoUtils.getPercentHeightSize(45);
                }
                int i5 = i + (percentHeightSize * i4);
                this.paint.setColor(this.integerColorDefault);
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setTextSize(this.integerTextSize);
                StringBuilder sb = new StringBuilder();
                int i6 = i4 + 1;
                sb.append(i6);
                sb.append(":");
                canvas.drawText(sb.toString(), measuredWidth - this.twoRectSpace, (i5 - AutoUtils.getPercentHeightSize(i2)) + this.rectHeight, this.paint);
                int i7 = 0;
                while (i7 < list.size()) {
                    String str = this.toothCodes.get(i4)[i7];
                    if (isShowDecimal()) {
                        toothCodeRound = getToothCodeInt(str);
                    } else {
                        toothCodeRound = getToothCodeRound(str, getAllDepthNames().get(i4));
                    }
                    int i8 = (this.twoRectSpace * i7) + measuredWidth;
                    int i9 = measuredWidth;
                    Rect rect = new Rect(i8 - (this.rectWidth / 2), i5, (this.rectWidth / 2) + i8, this.rectHeight + i5);
                    arrayList.add(rect);
                    if (this.flag.getRowPosition() == i4 && this.flag.getColumn() == i7 && !this.flag.isDicimal()) {
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
                    this.paint.setTextSize(this.integerTextSize);
                    float f2 = i8;
                    canvas.drawText(toothCodeRound, f2, (i5 - AutoUtils.getPercentHeightSize(4)) + this.rectHeight, this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int percentHeightSize2 = AutoUtils.getPercentHeightSize(45) + i5;
                        Rect rect2 = new Rect(i8 - (this.rectWidth / 2), percentHeightSize2, i8 + (this.rectWidth / 2), this.rectHeight + percentHeightSize2);
                        arrayList2.add(rect2);
                        if (this.flag.getRowPosition() == i4 && this.flag.getColumn() == i7 && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(str), f2, (percentHeightSize2 - AutoUtils.getPercentHeightSize(8)) + this.rectHeight, this.paint);
                    }
                    i7++;
                    measuredWidth = i9;
                }
                this.integerRectListContainer.add(arrayList);
                this.decimalRectListContainer.add(arrayList2);
                i4 = i6;
                measuredWidth = measuredWidth;
                i2 = 4;
            }
        } else {
            this.path.reset();
            this.paint.reset();
            this.paint.setColor(this.keyColor);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(2.0f);
            this.paint.setTextSize((this.keyWidth * this.ratio) / 3.0f);
            this.paint.setAntiAlias(true);
            this.path.moveTo(this.rect.left, this.rect.top);
            if (this.keyInfo.getAlign() == 0) {
                this.path.moveTo(this.rect.left, this.rect.top - (this.rect.height() * 0.25f));
                this.path.lineTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.top - (this.rect.height() * 0.25f));
                this.path.lineTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.bottom + (this.rect.height() * 0.25f));
                this.path.lineTo(this.rect.left, this.rect.bottom + (this.rect.height() * 0.25f));
                canvas.drawPath(this.path, this.paint);
                this.path.reset();
                this.path.moveTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.top);
            }
            this.paint.setPathEffect(this.cornerPathEffect);
            this.path.lineTo(this.rect.right, this.rect.top);
            this.path.lineTo(this.rect.right, this.rect.bottom);
            if (this.keyInfo.getAlign() == 0) {
                this.path.lineTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.bottom);
            } else {
                this.path.lineTo(this.rect.left, this.rect.bottom);
            }
            canvas.drawPath(this.path, this.paint);
            this.path.reset();
            if (this.existSide) {
                if (this.keyInfo.getAlign() == 0) {
                    this.path.moveTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.bottom + ((this.rect.height() - (this.thick * this.ratio)) / 2.0f));
                    this.path.lineTo(this.rect.left + (this.rect.height() * 1.3f), this.rect.bottom + ((this.rect.height() + (this.thick * this.ratio)) / 2.0f));
                }
                this.path.moveTo(this.rect.left, this.rect.bottom + ((this.rect.height() - (this.thick * this.ratio)) / 2.0f));
                this.path.lineTo(this.rect.right, this.rect.bottom + ((this.rect.height() - (this.thick * this.ratio)) / 2.0f));
                this.path.lineTo(this.rect.right, this.rect.bottom + ((this.rect.height() + (this.thick * this.ratio)) / 2.0f));
                this.path.lineTo(this.rect.left, this.rect.bottom + ((this.rect.height() + (this.thick * this.ratio)) / 2.0f));
                canvas.drawPath(this.path, this.paint);
            }
            this.paint.setPathEffect(null);
            int i10 = 0;
            while (i10 < this.allSpace.size()) {
                List<Integer> list2 = this.allSpace.get(i10);
                int intValue = this.rowPosition.size() > i10 ? this.rowPosition.get(i10).intValue() : 0;
                if (intValue < 0) {
                    float f3 = this.rect.bottom;
                    float height2 = this.rect.height();
                    float f4 = this.thick;
                    float f5 = this.ratio;
                    f = (f3 + ((height2 - (f4 * f5)) / 2.0f)) - (intValue * f5);
                } else {
                    f = this.rect.top + (intValue * this.ratio);
                }
                int i11 = 0;
                while (i11 < list2.size()) {
                    float spaceXInGraph = getSpaceXInGraph(list2.get(i11));
                    String toothCodeRound2 = getToothCodeRound(this.toothCodes.get(i10)[i11], this.depthNames.get(i10));
                    Rect rect3 = new Rect();
                    this.textPaint.getTextBounds(toothCodeRound2, 0, i3, rect3);
                    float f6 = (this.keyWidth * this.ratio) / 8.0f;
                    if (f6 < 7.0f) {
                        f6 = 7.0f;
                    }
                    canvas.drawCircle(spaceXInGraph, f, f6, this.paint);
                    if (this.keyInfo.isDimpleMotherSonKey()) {
                        String[] split = this.keyInfo.getSpaceWidthStr().split(";");
                        if (split[i10].contains("-")) {
                            height = (this.rect.top - 10) - ((rect3.height() + 10) * (((split.length - i3) - i10) / 2));
                            if (i11 == 0) {
                                this.textPaint.setColor(this.textRectSelect);
                                this.textPaint.getTextBounds("Outside:", 0, 8, rect3);
                                canvas.drawText("Outside:", (spaceXInGraph - (rect3.width() / 2)) - 20.0f, height, this.textPaint);
                            }
                        } else {
                            height = this.rect.bottom + ((rect3.height() + 10) * ((i10 / 2) + i3));
                            if (i11 == 0) {
                                this.textPaint.setColor(this.textRectSelect);
                                this.textPaint.getTextBounds("Inside:", 0, 7, rect3);
                                canvas.drawText("Inside:", (spaceXInGraph - (rect3.width() / 2)) - 20.0f, height, this.textPaint);
                            }
                        }
                    } else {
                        height = f + (rect3.height() / 2);
                    }
                    if (isInteger(this.toothCodes.get(i10)[i11])) {
                        this.textPaint.setColor(this.textColorNoDecimal);
                    } else {
                        this.textPaint.setColor(this.textColorExistDecimal);
                    }
                    canvas.drawText(toothCodeRound2, spaceXInGraph, height, this.textPaint);
                    i11++;
                    i3 = 1;
                }
                i10++;
                i3 = 1;
            }
        }
        if (isInputModel()) {
            return;
        }
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setPathEffect(null);
        this.path.reset();
        if (getKeyinfo().getAlign() == 0) {
            this.path.moveTo((((this.rect.height() * 1.3f) + this.rect.left) - 10.0f) + 4.0f, this.rect.top - this.rect.height());
            this.path.lineTo((this.rect.height() * 1.3f) + this.rect.left + 10.0f + 4.0f, this.rect.top - this.rect.height());
            this.path.lineTo((this.rect.height() * 1.3f) + this.rect.left + 4.0f, (this.rect.top - this.rect.height()) + 20);
        } else {
            this.path.moveTo((this.rect.right - 10) + 4, this.rect.top - this.rect.height());
            this.path.lineTo(this.rect.right + 10 + 4, this.rect.top - this.rect.height());
            this.path.lineTo(this.rect.right + 4, (this.rect.top - this.rect.height()) + 20);
        }
        this.path.close();
        canvas.drawPath(this.path, this.paint);
        this.paint.setStrokeWidth(2.0f);
        if (getKeyinfo().getAlign() == 0) {
            canvas.drawLine((this.rect.height() * 1.3f) + this.rect.left + 4.0f, this.rect.top - this.rect.height(), (this.rect.height() * 1.3f) + this.rect.left + 4.0f, this.rect.bottom + (this.rect.height() / 3), this.paint);
        } else {
            canvas.drawLine(this.rect.right + 4, this.rect.top - this.rect.height(), this.rect.right + 4, this.rect.bottom + (this.rect.height() / 3), this.paint);
        }
    }

    private float getSpaceXInGraph(Integer num) {
        if (this.keyInfo.getAlign() == 0) {
            return (num.intValue() * this.ratio) + (this.rect.height() * 1.3f) + this.rect.left;
        }
        return this.rect.left + ((this.maxLenght - num.intValue()) * this.ratio);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInputModel()) {
            return false;
        }
        this.gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        this.toothCodes.clear();
        for (String str2 : str.split(";")) {
            this.toothCodes.add(str2.split(","));
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public ArrayList<String[]> getToothCode() {
        return this.toothCodes;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCodeDefault() {
        String keyToothCode = this.keyInfo.getKeyToothCode();
        if (!TextUtils.isEmpty(keyToothCode)) {
            setToothCode(keyToothCode);
            return;
        }
        Iterator<String[]> it = this.toothCodes.iterator();
        while (it.hasNext()) {
            String[] next = it.next();
            for (int i = 0; i < next.length; i++) {
                next[i] = "?";
            }
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
        return this.integerRectListContainer;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getDecimalRectContainer() {
        return this.decimalRectListContainer;
    }
}
