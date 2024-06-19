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
public class SingleOutGrooveKey extends Key {
    public static final int SIDE_A = 0;
    private CornerPathEffect cornerPathEffect;
    private List<Rect> decimalRectListA;
    private List<Integer> depth;
    private List<String> depthName;
    private List<Rect> integerRectListA;
    private boolean isCside;
    private KeyInfo keyInfo;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private List<Integer> space;
    private List<Integer> spaceWidth;
    private int toothAmount;
    private String[] toothCode;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    public SingleOutGrooveKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.cornerPathEffect = new CornerPathEffect(2.0f);
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
        int width = keyInfo.getWidth();
        this.keyWidth = width;
        if (width == 0) {
            this.keyWidth = getMaxDepth();
        } else {
            this.keyWidth = Math.max(width, getMaxDepth());
        }
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
            this.rect = new Rect(getPaddingLeft(), ((getPaddingTop() + measuredHeight) / 2) - ((int) ((this.ratio * this.keyWidth) / 2.0f)), measuredWidth + getPaddingLeft(), ((measuredHeight + getPaddingTop()) / 2) + ((int) ((this.ratio * this.keyWidth) / 2.0f)));
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
        if (!TextUtils.isEmpty(this.keyInfo.getReadBittingRule()) && this.keyInfo.getReadBittingRule().equals("3") && !this.isCside) {
            canvas.drawLine(this.rect.left + this.rect.height(), this.rect.top, this.rect.left + this.rect.height(), this.rect.bottom, this.paint);
            canvas.drawLine((this.ratio * 100.0f) + this.rect.left + this.rect.height(), this.rect.top, (this.ratio * 100.0f) + this.rect.left + this.rect.height(), this.rect.bottom, this.paint);
        }
        if (this.keyInfo.getSide() == 0) {
            if (this.keyInfo.getNose() == 0) {
                if (this.keyInfo.getGuide() == 0) {
                    this.path.moveTo(this.rect.right, this.rect.top);
                } else {
                    this.path.moveTo(this.rect.right, this.rect.top + (this.keyInfo.getGuide() * this.ratio));
                }
            } else {
                this.path.moveTo(this.rect.right, this.rect.top);
                this.path.lineTo(this.rect.right - (this.keyInfo.getNose() * this.ratio), this.rect.top);
            }
        } else if (this.keyInfo.getNose() == 0) {
            if (this.keyInfo.getGuide() == 0) {
                this.path.moveTo(this.rect.right, this.rect.bottom);
            } else {
                this.path.moveTo(this.rect.right, this.rect.bottom - (this.keyInfo.getGuide() * this.ratio));
            }
        } else {
            this.path.moveTo(this.rect.right, this.rect.bottom);
            this.path.lineTo(this.rect.right - (this.keyInfo.getNose() * this.ratio), this.rect.bottom);
        }
        if (this.keyInfo.getSide() == 0) {
            for (int size = this.space.size() - 1; size >= 0; size--) {
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCode, size)) || getToothCodeAt(this.toothCode, size).equals("?")) {
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(size)), this.rect.top + (this.depth.get(0).intValue() * this.ratio));
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(size)), this.rect.top + (this.depth.get(0).intValue() * this.ratio));
                } else {
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(size)), this.rect.top + getYInGraph(this.depth, this.depthName, getToothCodeAt(this.toothCode, size)));
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(size)), this.rect.top + getYInGraph(this.depth, this.depthName, getToothCodeAt(this.toothCode, size)));
                }
                if (size == 0) {
                    if (this.keyInfo.getAlign() == 0) {
                        if (this.space.get(0).intValue() < 150) {
                            this.path.lineTo((this.rect.height() * 2.25f) + this.rect.left, this.rect.bottom);
                        } else {
                            this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - (150 * this.ratio), this.rect.bottom);
                        }
                    } else if (getKeyinfo().getIcCard() == 1311) {
                        this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - (150 * this.ratio), this.rect.bottom - (this.ratio * 100.0f));
                        this.path.lineTo(this.rect.right, this.rect.bottom - (this.ratio * 100.0f));
                    } else {
                        this.path.lineTo(getSpaceXInGraph(this.space.get(size).intValue()) - (150 * this.ratio), this.rect.bottom);
                    }
                }
            }
        } else {
            for (int size2 = this.space.size() - 1; size2 >= 0; size2--) {
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCode, size2)) || getToothCodeAt(this.toothCode, size2).equals("?")) {
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(size2)), this.rect.bottom - (this.depth.get(0).intValue() * this.ratio));
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(size2)), this.rect.bottom - (this.depth.get(0).intValue() * this.ratio));
                } else {
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidth.get(size2)), this.rect.bottom - getYInGraph(this.depth, this.depthName, getToothCodeAt(this.toothCode, size2)));
                    this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidth.get(size2)), this.rect.bottom - getYInGraph(this.depth, this.depthName, getToothCodeAt(this.toothCode, size2)));
                }
                if (size2 == 0) {
                    if (this.keyInfo.getAlign() == 0) {
                        if (this.space.get(0).intValue() < 150) {
                            this.path.lineTo((this.rect.height() * 2.25f) + this.rect.left, this.rect.top);
                        } else {
                            this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) - (150 * this.ratio), this.rect.top);
                        }
                    } else if (getKeyinfo().getIcCard() == 1372) {
                        this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) - (150 * this.ratio), this.rect.top + (this.ratio * 100.0f));
                        this.path.lineTo(this.rect.right, this.rect.top + (this.ratio * 100.0f));
                    } else {
                        this.path.lineTo(getSpaceXInGraph(this.space.get(size2).intValue()) - (150 * this.ratio), this.rect.top);
                    }
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i3 = 0; i3 < this.space.size(); i3++) {
                this.paint.setTextSize(this.integerTextSize);
                if (isShowDecimal()) {
                    toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCode, i3));
                } else {
                    toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCode, i3), this.depthName);
                }
                List<Integer> list = this.space;
                int spaceXInGraph = ((int) getSpaceXInGraph(list.get(list.size() - 1).intValue())) - (this.twoRectSpace * ((this.space.size() - 1) - i3));
                if (this.keyInfo.getSide() == 0) {
                    i = this.rect.top - this.marginBig;
                } else {
                    i = this.rect.bottom + this.marginSmall + this.rectHeight;
                }
                Rect rect = new Rect(spaceXInGraph - (this.rectWidth / 2), i - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph, i);
                this.integerRectListA.add(rect);
                if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i3 && !this.flag.isDicimal()) {
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
                canvas.drawText(toothCodeRound, f, i - AutoUtils.getPercentHeightSize(4), this.paint);
                if (isShowDecimal()) {
                    this.paint.setTextSize(this.decimalTextSize);
                    if (this.keyInfo.getSide() == 0) {
                        i2 = this.rect.top - this.marginSmall;
                    } else {
                        i2 = this.rect.bottom + this.marginBig + this.rectHeight;
                    }
                    Rect rect2 = new Rect(spaceXInGraph - (this.rectWidth / 2), i2 - this.rectHeight, spaceXInGraph + (this.rectWidth / 2), i2);
                    this.decimalRectListA.add(rect2);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i3 && this.flag.isDicimal()) {
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
                    canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCode, i3)), f, i2 - AutoUtils.getPercentHeightSize(8), this.paint);
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextAlign(Paint.Align.CENTER);
            for (int i4 = 0; i4 < this.space.size(); i4++) {
                Rect rect3 = new Rect();
                String toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCode, i4), this.depthName);
                this.paint.getTextBounds(toothCodeRound2, 0, toothCodeRound2.length(), rect3);
                int spaceXInGraph2 = (int) getSpaceXInGraph(this.space.get(i4).intValue());
                if (this.keyInfo.getSide() == 0) {
                    percentHeightSize = this.rect.top - AutoUtils.getPercentHeightSize(20);
                } else {
                    percentHeightSize = AutoUtils.getPercentHeightSize(15) + (this.rect.bottom - ((int) this.paint.getFontMetrics().ascent));
                }
                if (isInteger(getToothCodeAt(this.toothCode, i4))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound2, spaceXInGraph2, percentHeightSize, this.paint);
            }
        }
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        for (int i5 = 0; i5 < this.depth.size(); i5++) {
            int intValue = this.depth.get(i5).intValue();
            if (this.keyInfo.getSide() == 0) {
                float spaceXInGraph3 = getSpaceXInGraph(this.space.get(0).intValue());
                float f2 = intValue;
                float f3 = this.rect.top + (this.ratio * f2);
                List<Integer> list2 = this.space;
                canvas.drawLine(spaceXInGraph3, f3, getSpaceXInGraph(list2.get(list2.size() - 1).intValue()), this.rect.top + (f2 * this.ratio), this.paint);
            } else {
                float spaceXInGraph4 = getSpaceXInGraph(this.space.get(0).intValue());
                float f4 = intValue;
                float f5 = this.rect.bottom - (this.ratio * f4);
                List<Integer> list3 = this.space;
                canvas.drawLine(spaceXInGraph4, f5, getSpaceXInGraph(list3.get(list3.size() - 1).intValue()), this.rect.bottom - (f4 * this.ratio), this.paint);
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
        this.toothAmount = i;
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
        this.isCside = true;
        invalidate();
    }
}
