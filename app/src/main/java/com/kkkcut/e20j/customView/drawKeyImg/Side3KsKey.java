package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
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
public class Side3KsKey extends Key {
    public static final int SIDE_A = 0;
    private CornerPathEffect cornerPathEffect;
    private DashPathEffect dashPathEffect;
    private List<Rect> decimalRectListA;
    private List<Integer> depth;
    private List<String> depthName;
    private List<Rect> integerRectListA;
    private KeyInfo keyInfo;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> space;
    private List<Integer> spaceWidth;
    private String[] toothCode;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    public Side3KsKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.cornerPathEffect = new CornerPathEffect(2.0f);
        this.dashPathEffect = new DashPathEffect(new float[]{3.0f, 3.0f}, 0.0f);
        this.keyInfo = keyInfo;
        this.path = new Path();
        this.paint = new Paint();
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        this.depth = getAllDepths().get(0);
        this.space = getAllSpaces().get(0);
        this.depthName = getAllDepthNames().get(0);
        this.spaceWidth = getAllSpaceWidths().get(0);
        this.keyWidth = keyInfo.getWidth();
        if (keyInfo.getAlign() == 0) {
            if (keyInfo.getLength() == 0) {
                this.maxLenght = getMaxSpace() + (this.keyWidth * 2.25f) + 400.0f;
            } else {
                this.maxLenght = keyInfo.getLength() + (this.keyWidth * 2.25f);
            }
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
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth - getPaddingRight(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        int percentHeightSize;
        String toothCodeRound;
        int i;
        int i2;
        this.paint.reset();
        this.path.reset();
        this.paint.setColor(this.keyColor);
        int i3 = 1;
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
        this.path.lineTo(this.rect.right, this.rect.top);
        this.path.lineTo(this.rect.right, this.rect.bottom);
        if (this.keyInfo.getAlign() == 0) {
            this.path.lineTo(height, this.rect.bottom);
            this.path.lineTo(height, this.rect.bottom + (this.rect.height() * 0.2f));
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom + (this.rect.height() * 0.2f));
        } else {
            this.path.lineTo(this.rect.left + (this.rect.height() * 0.75f), this.rect.bottom);
        }
        canvas.drawPath(this.path, this.paint);
        String[] split = getKeyinfo().getKeyangle().split(",");
        this.path.reset();
        this.paint.setStyle(Paint.Style.FILL);
        for (int length = this.toothCode.length - 1; length >= 0; length--) {
            int height2 = (int) ((this.rect.height() / Math.tan(Math.toRadians(Integer.parseInt(split[length]) / 100))) / 2.0d);
            String toothCodeRound2 = getToothCodeRound(this.toothCode[length], this.depthName);
            if (!TextUtils.isEmpty(this.toothCode[length]) && toothCodeRound2.equals("1")) {
                float f = height2;
                this.path.moveTo((getSpaceXInGraph(this.space.get(length).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(length))) - f, this.rect.top);
                this.path.lineTo((getSpaceXInGraph(this.space.get(length).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(length))) + f, this.rect.bottom);
                this.path.lineTo(getSpaceXInGraph(this.space.get(length).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(length)) + f, this.rect.bottom);
                this.path.lineTo((getSpaceXInGraph(this.space.get(length).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(length))) - f, this.rect.top);
            }
        }
        canvas.drawPath(this.path, this.paint);
        int i4 = 0;
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            while (i4 < this.toothCode.length) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCode, i4));
                } else {
                    toothCodeRound = getToothCodeRound(this.toothCode[i4], this.depthName);
                }
                List<Integer> list = this.space;
                int spaceXInGraph = ((int) getSpaceXInGraph(list.get(list.size() - i3).intValue())) - (this.twoRectSpace * ((this.space.size() - i3) - i4));
                if (this.keyInfo.getSide() == 0) {
                    i = this.rect.top - this.marginBig;
                } else {
                    i = this.rect.bottom + this.marginSmall + this.rectHeight;
                }
                Rect rect = new Rect(spaceXInGraph - (this.rectWidth / 2), i - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph, i);
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
                float f2 = spaceXInGraph;
                canvas.drawText(toothCodeRound, f2, i - AutoUtils.getPercentHeightSize(8), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    if (this.keyInfo.getSide() == 0) {
                        i2 = this.rect.top - this.marginSmall;
                    } else {
                        i2 = this.rect.bottom + this.marginBig + this.rectHeight;
                    }
                    Rect rect2 = new Rect(spaceXInGraph - (this.rectWidth / 2), i2 - this.rectHeight, spaceXInGraph + (this.rectWidth / 2), i2);
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCode, i4)), f2, i2 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
                i4++;
                i3 = 1;
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i5 = 0; i5 < this.toothCode.length; i5++) {
                Rect rect3 = new Rect();
                String toothCodeRound3 = getToothCodeRound(this.toothCode[i5], this.depthName);
                this.paint.getTextBounds(toothCodeRound3, 0, toothCodeRound3.length(), rect3);
                int spaceXInGraph2 = (int) getSpaceXInGraph(this.space.get(i5).intValue());
                if (this.keyInfo.getSide() == 0) {
                    percentHeightSize = this.rect.top - AutoUtils.getPercentHeightSize(20);
                } else {
                    percentHeightSize = AutoUtils.getPercentHeightSize(15) + (this.rect.bottom - ((int) this.paint.getFontMetrics().ascent));
                }
                if (isInteger(this.toothCode[i5])) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound3, spaceXInGraph2, percentHeightSize, this.paint);
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
        canvas.rotate(90.0f, this.rect.left + (this.rect.height() * 1.5f), this.rect.centerY());
        canvas.drawText(valueOf, this.rect.left + (this.rect.height() * 1.5f), this.rect.centerY(), this.paint);
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
        this.toothCode = str.split(";")[0].split(",");
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
        this.toothCode = (String[]) Arrays.copyOf(this.toothCode, i);
        invalidate();
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
        int i = 0;
        while (true) {
            String[] strArr = this.toothCode;
            if (i >= strArr.length) {
                return;
            }
            strArr[i] = "?";
            i++;
        }
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public float getRatio() {
        return this.ratio;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onDecimalRectClick(int i, int i2) {
        this.flag.setRowPosition(0);
        this.flag.setColumn(i2);
        this.flag.setDicimal(true);
        showDecimalKeyboard();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    protected void onIntegerRectClick(int i, int i2) {
        this.flag.setRowPosition(0);
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

    public void switchCSide() {
        invalidate();
    }
}
