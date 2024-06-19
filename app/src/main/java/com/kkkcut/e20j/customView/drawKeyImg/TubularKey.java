package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.liying.core.bean.KeyInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TubularKey extends Key {
    private List<Rect> decimalRectListA;
    private List<String> depthName;
    private List<Rect> integerRectListA;
    private KeyInfo keyInfo;
    private int keyThick;
    private int keyWidth;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Float> space;
    private String[] toothCode;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
    }

    public TubularKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.space = new ArrayList();
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.keyInfo = keyInfo;
        this.path = new Path();
        this.paint = new Paint();
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        for (String str : keyInfo.getSpaceStr().split(";")[0].split(",")) {
            this.space.add(Float.valueOf(Integer.parseInt(str) / 100.0f));
        }
        this.depthName = getAllDepthNames().get(0);
        this.keyWidth = keyInfo.getWidth();
        this.toothCode = new String[this.space.size()];
        this.keyThick = keyInfo.getThick();
        setToothCodeDefault();
    }

    public void initSize() {
        int measuredHeight = (getMeasuredHeight() - getPaddingBottom()) - getPaddingTop();
        int measuredWidth = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
        int i = this.keyWidth;
        float f = (measuredHeight * 1.0f) / (i * 3);
        this.ratio = f;
        float f2 = i * f;
        float f3 = measuredWidth;
        if (f2 < f3) {
            this.rect = new Rect(((measuredWidth - ((int) (this.keyWidth * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 2) / 6) + getPaddingTop(), ((measuredWidth + ((int) (this.keyWidth * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 6) + getPaddingTop());
        } else {
            this.ratio = (f3 * 1.0f) / i;
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth - getPaddingRight(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        initSize();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void drawKeyView(Canvas canvas) {
        String toothCodeRound;
        this.paint.setColor(this.keyColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(2.0f);
        float f = (this.rect.top + this.rect.bottom) / 2;
        this.path.moveTo(this.rect.left, f);
        int i = (this.rect.left + this.rect.right) / 2;
        float f2 = i;
        this.path.addCircle(f2, f, (this.rect.height() / 2) - ((this.keyThick * this.ratio) / 2.0f), Path.Direction.CCW);
        this.path.addCircle(f2, f, (this.rect.height() / 2) + ((this.keyThick * this.ratio) / 2.0f), Path.Direction.CCW);
        canvas.drawPath(this.path, this.paint);
        int width = this.rect.width() / 4;
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(-1);
        int i2 = (width * 2) / 3;
        int i3 = width / 3;
        canvas.drawRect(this.rect.left - i2, f - ((this.keyThick * this.ratio) / 2.0f), this.rect.left + i3, f + ((this.keyThick * this.ratio) / 2.0f), this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.keyColor);
        canvas.drawRect(this.rect.left - i2, f - ((this.keyThick * this.ratio) / 2.0f), this.rect.left + i3, f + ((this.keyThick * this.ratio) / 2.0f), this.paint);
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i4 = 0; i4 < this.space.size(); i4++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCode, i4));
                } else {
                    toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCode, i4), this.depthName);
                }
                int measuredWidth = (this.twoRectSpace * i4) + ((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - (this.space.size() * this.twoRectSpace)) / 2);
                int percentHeightSize = this.rect.top - AutoUtils.getPercentHeightSize(64);
                Rect rect = new Rect(measuredWidth - (this.rectWidth / 2), percentHeightSize - this.rectHeight, (this.rectWidth / 2) + measuredWidth, percentHeightSize);
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
                float f3 = measuredWidth;
                canvas.drawText(toothCodeRound, f3, percentHeightSize - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    int percentHeightSize2 = this.rect.top - AutoUtils.getPercentHeightSize(27);
                    Rect rect2 = new Rect(measuredWidth - (this.rectWidth / 2), percentHeightSize2 - this.rectHeight, measuredWidth + (this.rectWidth / 2), percentHeightSize2);
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCode, i4)), f3, percentHeightSize2 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
        }
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setTextSize(20.0f);
        int width2 = (int) ((this.rect.width() / 2) + (this.keyThick * 2 * this.ratio));
        for (int i5 = 0; i5 < this.space.size(); i5++) {
            double d = width2;
            float cos = (float) (i - (Math.cos(Math.toRadians(this.space.get(i5).floatValue())) * d));
            float sin = f - ((float) (d * Math.sin(Math.toRadians(this.space.get(i5).floatValue()))));
            String toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCode, i5), this.depthName);
            this.paint.getTextBounds(toothCodeRound2, 0, toothCodeRound2.length(), new Rect());
            if (isInteger(getToothCodeAt(this.toothCode, i5))) {
                this.paint.setColor(this.textColorNoDecimal);
            } else {
                this.paint.setColor(this.textColorExistDecimal);
            }
            canvas.drawText(toothCodeRound2, cos - (r8.width() / 2), sin + (r8.height() / 2), this.paint);
        }
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

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothCode(String str) {
        this.toothCode = str.split(";")[0].split(",");
    }
}
