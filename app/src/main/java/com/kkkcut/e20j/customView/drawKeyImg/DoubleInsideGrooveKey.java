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
import com.kkkcut.e20j.customView.drawKeyImg.Key;
import com.kkkcut.e20j.utils.UnitUtils;
import com.cutting.machine.bean.KeyInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DoubleInsideGrooveKey extends Key {
    private CornerPathEffect cornerPathEffect;
    private List<Rect> decimalRectListA;
    private List<Rect> decimalRectListB;
    private List<Integer> depthA;
    private List<Integer> depthB;
    private List<String> depthNameA;
    private List<String> depthNameB;
    private List<Rect> integerRectListA;
    private List<Rect> integerRectListB;
    private boolean isPeugeot;
    private KeyInfo keyInfo;
    private int keyWidth;
    private float maxLenght;
    private Paint paint;
    private Path path;
    private float ratio;
    private Rect rect;
    private ArrayList<Key.Index> sortSpace;
    private List<Integer> spaceA;
    private List<Integer> spaceB;
    private List<Integer> spaceWidthA;
    private List<Integer> spaceWidthB;
    private String[] toothCodeA;
    private String[] toothCodeB;

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public int getToothAmount() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void setToothAmount(int i) {
    }

    public DoubleInsideGrooveKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.integerRectListB = new ArrayList();
        this.decimalRectListB = new ArrayList();
        this.cornerPathEffect = new CornerPathEffect(2.0f);
        this.keyInfo = keyInfo;
        this.path = new Path();
        this.paint = new Paint();
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        if (getAllSpaces().size() > 1) {
            this.spaceA = getAllSpaces().get(0);
            this.spaceB = getAllSpaces().get(1);
        } else {
            List<Integer> list = getAllSpaces().get(0);
            this.spaceB = list;
            this.spaceA = list;
        }
        if (getAllDepths().size() > 1) {
            this.depthA = getAllDepths().get(0);
            this.depthB = getAllDepths().get(1);
        } else {
            List<Integer> list2 = getAllDepths().get(0);
            this.depthB = list2;
            this.depthA = list2;
        }
        if (getAllDepthNames().size() > 1) {
            this.depthNameA = getAllDepthNames().get(0);
            this.depthNameB = getAllDepthNames().get(1);
        } else {
            this.depthNameA = getAllDepthNames().get(0);
            this.depthNameB = getAllDepthNames().get(0);
        }
        if (getAllSpaceWidths().size() > 1) {
            this.spaceWidthA = getAllSpaceWidths().get(0);
            this.spaceWidthB = getAllSpaceWidths().get(1);
        } else {
            List<Integer> list3 = getAllSpaceWidths().get(0);
            this.spaceWidthB = list3;
            this.spaceWidthA = list3;
        }
        int width = keyInfo.getWidth();
        this.keyWidth = width;
        if (width == 0) {
            this.keyWidth = getMaxDepth();
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
        String[] split = keyInfo.getSpaceStr().split(";");
        if (split[0].equals(split[1]) || keyInfo.getGroove() <= 0) {
            return;
        }
        this.isPeugeot = true;
        this.sortSpace = getSortSpace();
        this.flag.setRowPosition(this.sortSpace.get(0).getRow());
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
        String str;
        String toothCodeRound3;
        String str2;
        String str3;
        this.paint.reset();
        this.path.reset();
        this.paint.setColor(this.keyColor);
        int i = 1;
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        float f = 2.0f;
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
        if (this.keyInfo.getNose() == 0) {
            if (this.keyInfo.getGuide() == 0) {
                this.path.moveTo(this.rect.right, this.rect.top);
            } else {
                this.path.moveTo(this.rect.right, this.rect.bottom - (this.keyInfo.getGuide() * this.ratio));
            }
        } else {
            this.path.moveTo(this.rect.right, this.rect.top);
            this.path.lineTo(this.rect.right - (this.keyInfo.getNose() * this.ratio), this.rect.top);
        }
        int groove = getKeyinfo().getGroove();
        if (this.isPeugeot) {
            for (int size = (this.spaceA.size() + this.spaceB.size()) - 1; size >= 0; size--) {
                Key.Index index = this.sortSpace.get(size);
                if (index.getRow() == 1) {
                    str3 = this.toothCodeB[index.getColumn()];
                } else {
                    str3 = this.toothCodeA[index.getColumn()];
                }
                if (TextUtils.isEmpty(str3) || str3.equals("?")) {
                    if (index.getRow() == 1) {
                        Path path = this.path;
                        int i2 = size / 2;
                        float spaceXInGraph = getSpaceXInGraph(this.spaceB.get(i2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i2));
                        float f2 = this.rect.bottom;
                        float intValue = this.depthB.get(0).intValue();
                        float f3 = this.ratio;
                        float f4 = f2 - (intValue * f3);
                        float f5 = groove;
                        path.lineTo(spaceXInGraph, f4 - (f3 * f5));
                        Path path2 = this.path;
                        float spaceXInGraph2 = getSpaceXInGraph(this.spaceB.get(i2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i2));
                        float f6 = this.rect.bottom;
                        float intValue2 = this.depthB.get(0).intValue();
                        float f7 = this.ratio;
                        path2.lineTo(spaceXInGraph2, (f6 - (intValue2 * f7)) - (f5 * f7));
                    } else {
                        int i3 = size / 2;
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                    }
                } else if (index.getRow() == 1) {
                    int i4 = size / 2;
                    float f8 = groove;
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i4).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i4)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i4])) - (this.ratio * f8));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i4).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i4)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i4])) - (f8 * this.ratio));
                } else {
                    int i5 = size / 2;
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i5).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i5)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i5]));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i5).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i5)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i5]));
                }
            }
            int i6 = 0;
            while (i6 < this.spaceA.size() + this.spaceB.size()) {
                Key.Index index2 = this.sortSpace.get(i6);
                if (index2.getRow() == i) {
                    str2 = this.toothCodeB[index2.getColumn()];
                } else {
                    str2 = this.toothCodeA[index2.getColumn()];
                }
                if (TextUtils.isEmpty(str2) || str2.equals("?")) {
                    if (index2.getRow() == 1) {
                        if (i6 == 0) {
                            float f9 = groove;
                            float spaceXInGraph3 = (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f9) / 2.0f);
                            float f10 = this.rect.bottom;
                            float intValue3 = this.depthB.get(0).intValue();
                            float f11 = this.ratio;
                            this.path.arcTo(new RectF(spaceXInGraph3, (f10 - (intValue3 * f11)) - (f11 * f9), (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((f9 * this.ratio) / 2.0f), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio)), -90.0f, -180.0f);
                        }
                        int i7 = i6 / 2;
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i7).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i7)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i7).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i7)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                    } else {
                        Path path3 = this.path;
                        int i8 = i6 / 2;
                        float spaceXInGraph4 = getSpaceXInGraph(this.spaceA.get(i8).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i8));
                        float f12 = this.rect.top;
                        float intValue4 = this.depthA.get(0).intValue();
                        float f13 = this.ratio;
                        float f14 = f12 + (intValue4 * f13);
                        float f15 = groove;
                        path3.lineTo(spaceXInGraph4, f14 + (f13 * f15));
                        Path path4 = this.path;
                        float spaceXInGraph5 = getSpaceXInGraph(this.spaceA.get(i8).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i8));
                        float f16 = this.rect.top;
                        float intValue5 = this.depthA.get(0).intValue();
                        float f17 = this.ratio;
                        path4.lineTo(spaceXInGraph5, f16 + (intValue5 * f17) + (f15 * f17));
                    }
                } else if (index2.getRow() == i) {
                    if (i6 == 0) {
                        float f18 = groove;
                        int i9 = i6 / 2;
                        this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f18) / f), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i9])) - (this.ratio * f18), (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((f18 * this.ratio) / f), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i9])), -90.0f, -180.0f);
                    }
                    int i10 = i6 / 2;
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i10).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i10)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i10]));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i10).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i10)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i10]));
                } else {
                    int i11 = i6 / 2;
                    float f19 = groove;
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i11).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i11)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i11]) + (this.ratio * f19));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i11).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i11)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i11]) + (f19 * this.ratio));
                }
                i6++;
                i = 1;
                f = 2.0f;
            }
        } else {
            for (int size2 = this.spaceA.size() - 1; size2 >= 0; size2--) {
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, size2)) || getToothCodeAt(this.toothCodeA, size2).equals("?")) {
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size2)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size2)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                } else {
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size2)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size2)));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size2)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size2)));
                }
            }
            int innerCutLength = this.keyInfo.getInnerCutLength();
            if (innerCutLength == 0) {
                innerCutLength = 300;
            }
            if (this.keyInfo.getAlign() == 0) {
                this.path.lineTo((this.rect.height() * 2.25f) + this.rect.left + ((getMinSpace() - innerCutLength) * this.ratio), (this.rect.top + this.rect.bottom) / 2);
            } else {
                this.path.lineTo(this.rect.left + (((this.maxLenght - getMaxSpace()) - innerCutLength) * this.ratio), (this.rect.top + this.rect.bottom) / 2);
            }
            for (int i12 = 0; i12 < this.spaceB.size(); i12++) {
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, i12)) || getToothCodeAt(this.toothCodeB, i12).equals("?")) {
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i12).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(i12)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i12).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(i12)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                } else {
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i12).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(i12)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, i12)));
                    this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i12).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(i12)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, i12)));
                }
            }
        }
        if (this.keyInfo.getNose() == 0) {
            if (this.keyInfo.getGuide() == 0) {
                this.path.lineTo(this.rect.right, this.rect.bottom);
            } else {
                this.path.lineTo(this.rect.right, this.rect.top + (this.keyInfo.getGuide() * this.ratio));
            }
        } else {
            this.path.lineTo(this.rect.right - (this.keyInfo.getNose() * this.ratio), this.rect.bottom);
            this.path.lineTo(this.rect.right, this.rect.bottom);
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.integerRectListA.clear();
            this.decimalRectListA.clear();
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            if (this.isPeugeot) {
                for (int i13 = 0; i13 < this.sortSpace.size(); i13++) {
                    Key.Index index3 = this.sortSpace.get(i13);
                    if (index3.getRow() == 0) {
                        str = this.toothCodeA[index3.getColumn()];
                    } else {
                        str = this.toothCodeB[index3.getColumn()];
                    }
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound3 = getToothCodeInt(str);
                    } else {
                        toothCodeRound3 = getToothCodeRound(str, this.depthNameA);
                    }
                    ArrayList<Key.Index> arrayList = this.sortSpace;
                    int spaceXInGraph6 = ((int) getSpaceXInGraph(arrayList.get(arrayList.size() - 1).getData())) - (this.twoRectSpace * (((this.spaceA.size() * 2) - 1) - i13));
                    int i14 = this.rect.top - this.marginBig;
                    Rect rect = new Rect(spaceXInGraph6 - (this.rectWidth / 2), i14 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph6, i14);
                    if (index3.getRow() == 0) {
                        this.integerRectListA.add(rect);
                    } else {
                        this.integerRectListB.add(rect);
                    }
                    if (this.flag.getRowPosition() == index3.getRow() && this.flag.getColumn() == index3.getColumn() && !this.flag.isDicimal()) {
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
                    float f20 = spaceXInGraph6;
                    canvas.drawText(toothCodeRound3, f20, i14 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i15 = this.rect.top - this.marginSmall;
                        Rect rect2 = new Rect(spaceXInGraph6 - (this.rectWidth / 2), i15 - this.rectHeight, spaceXInGraph6 + (this.rectWidth / 2), i15);
                        if (index3.getRow() == 0) {
                            this.decimalRectListA.add(rect2);
                        } else {
                            this.decimalRectListB.add(rect2);
                        }
                        if (this.flag.getRowPosition() == index3.getRow() && this.flag.getColumn() == index3.getColumn() && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(str), f20, i15 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
            } else {
                for (int i16 = 0; i16 < this.spaceA.size(); i16++) {
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound2 = getToothCodeInt(getToothCodeAt(this.toothCodeA, i16));
                    } else {
                        toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i16), this.depthNameA);
                    }
                    List<Integer> list = this.spaceA;
                    int spaceXInGraph7 = ((int) getSpaceXInGraph(list.get(list.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i16));
                    int i17 = this.rect.top - this.marginBig;
                    Rect rect3 = new Rect(spaceXInGraph7 - (this.rectWidth / 2), i17 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph7, i17);
                    this.integerRectListA.add(rect3);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i16 && !this.flag.isDicimal()) {
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
                    float f21 = spaceXInGraph7;
                    canvas.drawText(toothCodeRound2, f21, i17 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i18 = this.rect.top - this.marginSmall;
                        Rect rect4 = new Rect(spaceXInGraph7 - (this.rectWidth / 2), i18 - this.rectHeight, spaceXInGraph7 + (this.rectWidth / 2), i18);
                        this.decimalRectListA.add(rect4);
                        if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i16 && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeA, i16)), f21, i18 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
                this.integerRectListB.clear();
                this.decimalRectListB.clear();
                for (int i19 = 0; i19 < this.spaceB.size(); i19++) {
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCodeB, i19));
                    } else {
                        toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCodeB, i19), this.depthNameB);
                    }
                    List<Integer> list2 = this.spaceA;
                    int spaceXInGraph8 = ((int) getSpaceXInGraph(list2.get(list2.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i19));
                    int i20 = this.rect.bottom + this.marginSmall + this.rectHeight;
                    Rect rect5 = new Rect(spaceXInGraph8 - (this.rectWidth / 2), i20 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph8, i20);
                    this.integerRectListB.add(rect5);
                    if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i19 && !this.flag.isDicimal()) {
                        this.paint.setStyle(Paint.Style.FILL);
                        this.paint.setColor(this.textRectSelect);
                        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                        canvas.drawRect(rect5, this.paint);
                        this.paint.setColor(this.textColorSelect);
                    } else {
                        this.paint.setStyle(Paint.Style.STROKE);
                        this.paint.setColor(this.integerRectColor);
                        canvas.drawRect(rect5, this.paint);
                        this.paint.setColor(this.integerColorDefault);
                        this.paint.setStyle(Paint.Style.FILL);
                    }
                    float f22 = spaceXInGraph8;
                    canvas.drawText(toothCodeRound, f22, i20 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i21 = this.rect.bottom + this.marginBig + this.rectHeight;
                        Rect rect6 = new Rect(spaceXInGraph8 - (this.rectWidth / 2), i21 - this.rectHeight, spaceXInGraph8 + (this.rectWidth / 2), i21);
                        this.decimalRectListB.add(rect6);
                        if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i19 && this.flag.isDicimal()) {
                            this.paint.setStyle(Paint.Style.FILL);
                            this.paint.setColor(this.textRectSelect);
                            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                            canvas.drawRect(rect6, this.paint);
                            this.paint.setColor(this.textColorSelect);
                        } else {
                            this.paint.setStyle(Paint.Style.STROKE);
                            this.paint.setColor(this.decimalRectColor);
                            canvas.drawRect(rect6, this.paint);
                            this.paint.setColor(this.decimalColorDefault);
                            this.paint.setStyle(Paint.Style.FILL);
                        }
                        canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeB, i19)), f22, i21 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.paint.setColor(this.integerColorDefault);
            for (int i22 = 0; i22 < this.spaceA.size(); i22++) {
                Rect rect7 = new Rect();
                String toothCodeRound4 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i22), this.depthNameA);
                this.paint.getTextBounds(toothCodeRound4, 0, toothCodeRound4.length(), rect7);
                int spaceXInGraph9 = (int) getSpaceXInGraph(this.spaceA.get(i22).intValue());
                int i23 = this.rect.top - 20;
                if (isInteger(getToothCodeAt(this.toothCodeA, i22))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound4, spaceXInGraph9, i23, this.paint);
            }
            for (int i24 = 0; i24 < this.spaceB.size(); i24++) {
                Rect rect8 = new Rect();
                String toothCodeRound5 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i24), this.depthNameB);
                this.paint.getTextBounds(toothCodeRound5, 0, toothCodeRound5.length(), rect8);
                float f23 = this.paint.getFontMetrics().ascent;
                int spaceXInGraph10 = (int) getSpaceXInGraph(this.spaceB.get(i24).intValue());
                int i25 = (this.rect.bottom - ((int) f23)) + 15;
                if (isInteger(getToothCodeAt(this.toothCodeB, i24))) {
                    this.paint.setColor(this.textColorNoDecimal);
                } else {
                    this.paint.setColor(this.textColorExistDecimal);
                }
                canvas.drawText(toothCodeRound5, spaceXInGraph10, i25, this.paint);
            }
        }
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        for (int i26 = 0; i26 < this.depthA.size(); i26++) {
            int intValue6 = this.depthA.get(i26).intValue();
            float spaceXInGraph11 = getSpaceXInGraph(this.spaceA.get(0).intValue());
            float f24 = intValue6;
            float f25 = this.rect.top + (this.ratio * f24);
            List<Integer> list3 = this.spaceA;
            canvas.drawLine(spaceXInGraph11, f25, getSpaceXInGraph(list3.get(list3.size() - 1).intValue()), this.rect.top + (f24 * this.ratio), this.paint);
        }
        for (int i27 = 0; i27 < this.depthB.size(); i27++) {
            int intValue7 = this.depthB.get(i27).intValue();
            float spaceXInGraph12 = getSpaceXInGraph(this.spaceB.get(0).intValue());
            float f26 = intValue7;
            float f27 = this.rect.bottom - (this.ratio * f26);
            List<Integer> list4 = this.spaceB;
            canvas.drawLine(spaceXInGraph12, f27, getSpaceXInGraph(list4.get(list4.size() - 1).intValue()), this.rect.bottom - (f26 * this.ratio), this.paint);
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
        String[] split = str.split(";");
        this.toothCodeA = split[0].split(",");
        this.toothCodeB = split[1].split(",");
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

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToRight() {
        if (this.isPeugeot) {
            if (this.sortSpace != null) {
                int findCurrentIndex = findCurrentIndex();
                int i = 0;
                if (findCurrentIndex < this.sortSpace.size() - 1) {
                    i = findCurrentIndex + 1;
                } else if (this.flag.isDicimal()) {
                    showIntegerKeyboard(getAllDepthNames().get(0));
                    this.flag.setDicimal(false);
                } else if (isShowDecimal()) {
                    this.flag.setDicimal(true);
                    showDecimalKeyboard();
                }
                Key.Index index = this.sortSpace.get(i);
                this.flag.setColumn(index.getColumn());
                this.flag.setRowPosition(index.getRow());
                invalidate();
                return;
            }
            return;
        }
        super.moveToRight();
    }

    private int findCurrentIndex() {
        for (int i = 0; i < this.sortSpace.size(); i++) {
            Key.Index index = this.sortSpace.get(i);
            if (index.getRow() == this.flag.getRowPosition() && index.getColumn() == this.flag.getColumn()) {
                return i;
            }
        }
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToLeft() {
        if (this.isPeugeot) {
            if (this.sortSpace != null) {
                int findCurrentIndex = findCurrentIndex();
                if (findCurrentIndex > 0) {
                    findCurrentIndex--;
                } else if (this.flag.isDicimal()) {
                    findCurrentIndex = getAllDepthNames().get(0).size() - 1;
                    showIntegerKeyboard(getAllDepthNames().get(0));
                    this.flag.setDicimal(false);
                }
                Key.Index index = this.sortSpace.get(findCurrentIndex);
                this.flag.setColumn(index.getColumn());
                this.flag.setRowPosition(index.getRow());
                invalidate();
                return;
            }
            return;
        }
        super.moveToLeft();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToUp() {
        if (this.isPeugeot) {
            if (this.sortSpace != null) {
                if (this.flag.isDicimal()) {
                    showIntegerKeyboard(getAllDepthNames().get(0));
                    this.flag.setDicimal(false);
                }
                invalidate();
                return;
            }
            return;
        }
        super.moveToUp();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToDown() {
        if (this.isPeugeot) {
            if (this.sortSpace != null) {
                if (this.flag.isDicimal()) {
                    showIntegerKeyboard(getAllDepthNames().get(0));
                    this.flag.setDicimal(false);
                } else if (isShowDecimal()) {
                    this.flag.setDicimal(true);
                    showDecimalKeyboard();
                }
                invalidate();
                return;
            }
            return;
        }
        super.moveToDown();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void goFirst() {
        if (this.isPeugeot) {
            Key.Index index = this.sortSpace.get(0);
            this.flag.setRowPosition(index.getRow());
            this.flag.setColumn(index.getColumn());
            this.flag.setDicimal(false);
            showIntegerKeyboard(getAllDepthNames().get(0));
            invalidate();
            return;
        }
        this.flag.setRowPosition(0);
        this.flag.setColumn(0);
        this.flag.setDicimal(false);
        showIntegerKeyboard(getAllDepthNames().get(0));
        invalidate();
    }
}
