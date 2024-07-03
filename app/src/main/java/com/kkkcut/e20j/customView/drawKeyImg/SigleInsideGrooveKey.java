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
import com.cutting.machine.bean.KeyInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class SigleInsideGrooveKey extends Key {
    private static final int SIDE_A = 0;
    private static final int SIDE_AB_IDFF = 3;
    private static final int SIDE_AB_IDFF_2_ROW = 6;
    private static final int SIDE_A_VARIABLE = 5;
    private static final int SIDE_B = 1;
    private CornerPathEffect cornerPathEffect;
    private List<Rect> decimalRectListA;
    private List<Rect> decimalRectListB;
    private List<Integer> depthA;
    private List<Integer> depthB;
    private List<String> depthNameA;
    private List<String> depthNameB;
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

    public SigleInsideGrooveKey(Context context, KeyInfo keyInfo) {
        super(context, keyInfo);
        this.integerRectListA = new ArrayList();
        this.decimalRectListA = new ArrayList();
        this.integerRectListB = new ArrayList();
        this.decimalRectListB = new ArrayList();
        this.cornerPathEffect = new CornerPathEffect(2.0f);
        this.path = new Path();
        this.paint = new Paint();
        this.keyInfo = keyInfo;
        initData(keyInfo);
    }

    public void initData(KeyInfo keyInfo) {
        if (getAllSpaces().size() > 1) {
            this.spaceA = getAllSpaces().get(0);
            this.spaceB = getAllSpaces().get(1);
        } else {
            this.spaceA = getAllSpaces().get(0);
            this.spaceB = getAllSpaces().get(0);
        }
        if (getAllDepths().size() > 1) {
            this.depthA = getAllDepths().get(0);
            this.depthB = getAllDepths().get(1);
        } else {
            this.depthA = getAllDepths().get(0);
            this.depthB = getAllDepths().get(0);
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
            this.spaceWidthA = getAllSpaceWidths().get(0);
            this.spaceWidthB = getAllSpaceWidths().get(0);
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
        this.toothAmount = this.spaceA.size();
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
        String str2;
        this.paint.reset();
        this.path.reset();
        this.paint.setColor(this.keyColor);
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
        if (this.keyInfo.getSide() == 1) {
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
        } else {
            this.path.moveTo(this.rect.right, this.rect.top);
        }
        int groove = this.keyInfo.getGroove() == 0 ? 300 : this.keyInfo.getGroove();
        int i = 0;
        if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5) {
            for (int size = this.spaceA.size() - 1; size >= 0; size--) {
                float spaceXInGraph = getSpaceXInGraph(this.spaceA.get(size).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size));
                float spaceXInGraph2 = getSpaceXInGraph(this.spaceA.get(size).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size));
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, size)) || getToothCodeAt(this.toothCodeA, size).equals("?")) {
                    this.path.lineTo(spaceXInGraph, this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                    this.path.lineTo(spaceXInGraph2, this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                } else {
                    this.path.lineTo(spaceXInGraph, this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size)));
                    this.path.lineTo(spaceXInGraph2, this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size)));
                }
            }
            for (int i2 = 0; i2 < this.spaceA.size(); i2++) {
                if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, i2)) || getToothCodeAt(this.toothCodeA, i2).equals("?")) {
                    if (i2 == 0) {
                        if (this.keyInfo.getExtraCut() == 1) {
                            Path path = this.path;
                            float f2 = groove;
                            float spaceXInGraph3 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - (this.ratio * f2);
                            float f3 = this.rect.top;
                            float intValue = this.depthA.get(0).intValue();
                            float f4 = this.ratio;
                            float f5 = (f3 + (intValue * f4)) - (f4 * f2);
                            float spaceXInGraph4 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((1.7f * f2) * this.ratio);
                            float f6 = this.rect.top;
                            float intValue2 = this.depthA.get(0).intValue();
                            float f7 = this.ratio;
                            path.cubicTo(spaceXInGraph3, f5, spaceXInGraph4, (f6 + (intValue2 * f7)) - ((f2 * 0.3f) * f7), getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0)), this.rect.top + ((this.depthA.get(0).intValue() + groove) * this.ratio));
                        } else {
                            float f8 = groove;
                            float spaceXInGraph5 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f8) / 2.0f);
                            float intValue3 = this.rect.top + (this.depthA.get(0).intValue() * this.ratio);
                            float spaceXInGraph6 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((this.ratio * f8) / 2.0f);
                            float f9 = this.rect.top;
                            float intValue4 = this.depthA.get(0).intValue();
                            float f10 = this.ratio;
                            this.path.arcTo(new RectF(spaceXInGraph5, intValue3, spaceXInGraph6, f9 + (intValue4 * f10) + (f8 * f10)), -90.0f, -180.0f);
                        }
                    }
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i2)), this.rect.top + ((this.depthA.get(0).intValue() + groove) * this.ratio));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i2)), this.rect.top + ((this.depthA.get(0).intValue() + groove) * this.ratio));
                } else {
                    if (i2 == 0) {
                        if (this.keyInfo.getExtraCut() == 1) {
                            float f11 = groove;
                            this.path.cubicTo((getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - (this.ratio * f11), (this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i2))) - (this.ratio * f11), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((1.7f * f11) * this.ratio), (this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i2))) - ((0.3f * f11) * this.ratio), getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i2)) + (f11 * this.ratio));
                        } else {
                            float f12 = groove;
                            this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f12) / 2.0f), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[0]), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((this.ratio * f12) / 2.0f), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[0]) + (f12 * this.ratio)), -90.0f, -180.0f);
                        }
                    }
                    float f13 = groove;
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i2)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i2)) + (this.ratio * f13));
                    this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i2)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i2)) + (f13 * this.ratio));
                }
            }
        } else {
            if (this.keyInfo.getSide() == 1) {
                for (int size2 = this.spaceB.size() - 1; size2 >= 0; size2--) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, size2)) || getToothCodeAt(this.toothCodeB, size2).equals("?")) {
                        Path path2 = this.path;
                        float spaceXInGraph7 = getSpaceXInGraph(this.spaceA.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size2));
                        float f14 = this.rect.bottom;
                        float intValue5 = this.depthA.get(0).intValue();
                        float f15 = this.ratio;
                        float f16 = f14 - (intValue5 * f15);
                        float f17 = groove;
                        path2.lineTo(spaceXInGraph7, f16 - (f15 * f17));
                        Path path3 = this.path;
                        float spaceXInGraph8 = getSpaceXInGraph(this.spaceA.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size2));
                        float f18 = this.rect.bottom;
                        float intValue6 = this.depthA.get(0).intValue();
                        float f19 = this.ratio;
                        path3.lineTo(spaceXInGraph8, (f18 - (intValue6 * f19)) - (f17 * f19));
                    } else {
                        float f20 = groove;
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size2)), (this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, size2))) - (this.ratio * f20));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size2).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size2)), (this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, size2))) - (f20 * this.ratio));
                    }
                }
                int i3 = 0;
                while (i3 < this.spaceB.size()) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, i3)) || getToothCodeAt(this.toothCodeB, i3).equals("?")) {
                        if (i3 == 0) {
                            float f21 = groove;
                            float spaceXInGraph9 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f21) / 2.0f);
                            float f22 = this.rect.bottom;
                            float intValue7 = this.depthA.get(0).intValue();
                            float f23 = this.ratio;
                            this.path.arcTo(new RectF(spaceXInGraph9, (f22 - (intValue7 * f23)) - (f23 * f21), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((f21 * this.ratio) / 2.0f), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio)), -90.0f, -180.0f);
                        }
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.bottom - (this.depthA.get(0).intValue() * this.ratio));
                    } else {
                        if (i3 == 0) {
                            float f24 = groove;
                            this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f24) / f), (this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, i3))) - (this.ratio * f24), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((f24 * this.ratio) / f), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, i3))), -90.0f, -180.0f);
                        }
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, i3)));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i3).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i3)), this.rect.bottom - getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeB, i3)));
                    }
                    i3++;
                    f = 2.0f;
                }
            }
            if (this.keyInfo.getSide() == 3) {
                for (int size3 = (this.spaceA.size() + this.spaceB.size()) - 1; size3 >= 0; size3--) {
                    int i4 = size3 % 2;
                    if (i4 == 0) {
                        str2 = this.toothCodeB[size3 / 2];
                    } else {
                        str2 = this.toothCodeA[size3 / 2];
                    }
                    if (TextUtils.isEmpty(str2) || str2.equals("?")) {
                        if (i4 == 0) {
                            Path path4 = this.path;
                            int i5 = size3 / 2;
                            float spaceXInGraph10 = getSpaceXInGraph(this.spaceB.get(i5).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i5));
                            float f25 = this.rect.bottom;
                            float intValue8 = this.depthB.get(0).intValue();
                            float f26 = this.ratio;
                            float f27 = f25 - (intValue8 * f26);
                            float f28 = groove;
                            path4.lineTo(spaceXInGraph10, f27 - (f26 * f28));
                            Path path5 = this.path;
                            float spaceXInGraph11 = getSpaceXInGraph(this.spaceB.get(i5).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i5));
                            float f29 = this.rect.bottom;
                            float intValue9 = this.depthB.get(0).intValue();
                            float f30 = this.ratio;
                            path5.lineTo(spaceXInGraph11, (f29 - (intValue9 * f30)) - (f28 * f30));
                        } else {
                            int i6 = size3 / 2;
                            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i6).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i6)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                            this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i6).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i6)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                        }
                    } else if (i4 == 0) {
                        int i7 = size3 / 2;
                        float f31 = groove;
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i7).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i7)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i7])) - (this.ratio * f31));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i7).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i7)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i7])) - (f31 * this.ratio));
                    } else {
                        int i8 = size3 / 2;
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i8).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i8)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i8]));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i8).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i8)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i8]));
                    }
                }
                int i9 = 0;
                while (i9 < this.spaceA.size() + this.spaceB.size()) {
                    int i10 = i9 % 2;
                    if (i10 == 0) {
                        str = this.toothCodeB[i9 / 2];
                    } else {
                        str = this.toothCodeA[i9 / 2];
                    }
                    if (TextUtils.isEmpty(str) || str.equals("?")) {
                        if (i10 == 0) {
                            if (i9 == 0) {
                                float f32 = groove;
                                float spaceXInGraph12 = (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f32) / 2.0f);
                                float f33 = this.rect.bottom;
                                float intValue10 = this.depthB.get(0).intValue();
                                float f34 = this.ratio;
                                this.path.arcTo(new RectF(spaceXInGraph12, (f33 - (intValue10 * f34)) - (f34 * f32), (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((f32 * this.ratio) / 2.0f), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio)), -90.0f, -180.0f);
                            }
                            int i11 = i9 / 2;
                            this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i11).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i11)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                            this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i11).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i11)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                        } else {
                            Path path6 = this.path;
                            int i12 = i9 / 2;
                            float spaceXInGraph13 = getSpaceXInGraph(this.spaceA.get(i12).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i12));
                            float f35 = this.rect.top;
                            float intValue11 = this.depthA.get(0).intValue();
                            float f36 = this.ratio;
                            float f37 = f35 + (intValue11 * f36);
                            float f38 = groove;
                            path6.lineTo(spaceXInGraph13, f37 + (f36 * f38));
                            Path path7 = this.path;
                            float spaceXInGraph14 = getSpaceXInGraph(this.spaceA.get(i12).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i12));
                            float f39 = this.rect.top;
                            float intValue12 = this.depthA.get(0).intValue();
                            float f40 = this.ratio;
                            path7.lineTo(spaceXInGraph14, f39 + (intValue12 * f40) + (f38 * f40));
                        }
                    } else if (i10 == 0) {
                        if (i9 == 0) {
                            float f41 = groove;
                            int i13 = i9 / 2;
                            this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceB.get(i).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i))) - ((this.ratio * f41) / 2.0f), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i13])) - (this.ratio * f41), (getSpaceXInGraph(this.spaceB.get(i).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i))) + ((f41 * this.ratio) / 2.0f), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i13])), -90.0f, -180.0f);
                        }
                        int i14 = i9 / 2;
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i14).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i14)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i14]));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i14).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i14)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[i14]));
                    } else {
                        int i15 = i9 / 2;
                        float f42 = groove;
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i15).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i15)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i15]) + (this.ratio * f42));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i15).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i15)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[i15]) + (f42 * this.ratio));
                    }
                    i9++;
                    i = 0;
                }
            }
            if (this.keyInfo.getSide() == 6) {
                for (int size4 = this.spaceA.size() - 1; size4 >= 0; size4--) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, size4)) || getToothCodeAt(this.toothCodeA, size4).equals("?")) {
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size4).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size4)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size4).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size4)), this.rect.top + (this.depthA.get(0).intValue() * this.ratio));
                    } else {
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size4).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(size4)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size4)));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(size4).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(size4)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, size4)));
                    }
                }
                for (int i16 = 0; i16 < this.spaceA.size(); i16++) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeA, i16)) || getToothCodeAt(this.toothCodeA, i16).equals("?")) {
                        if (i16 == 0) {
                            float f43 = groove;
                            float spaceXInGraph15 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f43) / 2.0f);
                            float intValue13 = this.rect.top + (this.depthA.get(0).intValue() * this.ratio);
                            float spaceXInGraph16 = (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((this.ratio * f43) / 2.0f);
                            float f44 = this.rect.top;
                            float intValue14 = this.depthA.get(0).intValue();
                            float f45 = this.ratio;
                            this.path.arcTo(new RectF(spaceXInGraph15, intValue13, spaceXInGraph16, f44 + (intValue14 * f45) + (f43 * f45)), -90.0f, -180.0f);
                        }
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i16).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i16)), this.rect.top + ((this.depthA.get(0).intValue() + groove) * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i16).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i16)), this.rect.top + ((this.depthA.get(0).intValue() + groove) * this.ratio));
                        if (i16 == this.spaceA.size() - 1) {
                            this.path.lineTo(this.rect.right, this.rect.bottom - (this.keyInfo.getGuide() * this.ratio));
                        }
                    } else {
                        if (i16 == 0) {
                            float f46 = groove;
                            this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) - ((this.ratio * f46) / 2.0f), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[0]), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(0))) + ((this.ratio * f46) / 2.0f), this.rect.top + getYInGraph(this.depthA, this.depthNameA, this.toothCodeA[0]) + (f46 * this.ratio)), -90.0f, -180.0f);
                        }
                        float f47 = groove;
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i16).intValue()) - getHalfSpaceWidth(this.spaceWidthA.get(i16)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i16)) + (this.ratio * f47));
                        this.path.lineTo(getSpaceXInGraph(this.spaceA.get(i16).intValue()) + getHalfSpaceWidth(this.spaceWidthA.get(i16)), this.rect.top + getYInGraph(this.depthA, this.depthNameA, getToothCodeAt(this.toothCodeA, i16)) + (f47 * this.ratio));
                        if (i16 == this.spaceA.size() - 1) {
                            this.path.lineTo(this.rect.right, this.rect.bottom - (this.keyInfo.getGuide() * this.ratio));
                        }
                    }
                }
                for (int size5 = this.spaceB.size() - 1; size5 >= 0; size5--) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, size5)) || getToothCodeAt(this.toothCodeB, size5).equals("?")) {
                        if (size5 == this.spaceB.size() - 1) {
                            this.path.lineTo(this.rect.right, this.rect.top + (this.keyInfo.getGuide() * this.ratio));
                        }
                        Path path8 = this.path;
                        float spaceXInGraph17 = getSpaceXInGraph(this.spaceB.get(size5).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(size5));
                        float f48 = this.rect.bottom;
                        float intValue15 = this.depthB.get(0).intValue();
                        float f49 = this.ratio;
                        float f50 = f48 - (intValue15 * f49);
                        float f51 = groove;
                        path8.lineTo(spaceXInGraph17, f50 - (f49 * f51));
                        Path path9 = this.path;
                        float spaceXInGraph18 = getSpaceXInGraph(this.spaceB.get(size5).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(size5));
                        float f52 = this.rect.bottom;
                        float intValue16 = this.depthB.get(0).intValue();
                        float f53 = this.ratio;
                        path9.lineTo(spaceXInGraph18, (f52 - (intValue16 * f53)) - (f51 * f53));
                    } else {
                        if (size5 == this.spaceB.size() - 1) {
                            this.path.lineTo(this.rect.right, this.rect.top + (this.keyInfo.getGuide() * this.ratio));
                        }
                        float f54 = groove;
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size5).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(size5)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, size5))) - (this.ratio * f54));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(size5).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(size5)), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, size5))) - (f54 * this.ratio));
                    }
                }
                for (int i17 = 0; i17 < this.spaceB.size(); i17++) {
                    if (TextUtils.isEmpty(getToothCodeAt(this.toothCodeB, i17)) || getToothCodeAt(this.toothCodeB, i17).equals("?")) {
                        if (i17 == 0) {
                            float f55 = groove;
                            float spaceXInGraph19 = (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(0))) - ((this.ratio * f55) / 2.0f);
                            float f56 = this.rect.bottom;
                            float intValue17 = this.depthB.get(0).intValue();
                            float f57 = this.ratio;
                            this.path.arcTo(new RectF(spaceXInGraph19, (f56 - (intValue17 * f57)) - (f57 * f55), (getSpaceXInGraph(this.spaceA.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(0))) + ((f55 * this.ratio) / 2.0f), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio)), -90.0f, -180.0f);
                        }
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i17).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(i17)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i17).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(i17)), this.rect.bottom - (this.depthB.get(0).intValue() * this.ratio));
                    } else {
                        if (i17 == 0) {
                            float f58 = groove;
                            this.path.arcTo(new RectF((getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(0))) - ((this.ratio * f58) / 2.0f), (this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[0])) - (this.ratio * f58), (getSpaceXInGraph(this.spaceB.get(0).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(0))) + ((f58 * this.ratio) / 2.0f), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, this.toothCodeB[0])), -90.0f, -180.0f);
                        }
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i17).intValue()) - getHalfSpaceWidth(this.spaceWidthB.get(i17)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, i17)));
                        this.path.lineTo(getSpaceXInGraph(this.spaceB.get(i17).intValue()) + getHalfSpaceWidth(this.spaceWidthB.get(i17)), this.rect.bottom - getYInGraph(this.depthB, this.depthNameB, getToothCodeAt(this.toothCodeB, i17)));
                    }
                }
            }
        }
        if (this.keyInfo.getSide() == 0) {
            if (this.keyInfo.getNose() == 0) {
                if (this.keyInfo.getGuide() == 0) {
                    this.path.lineTo(this.rect.right, this.rect.bottom);
                } else {
                    this.path.lineTo(this.rect.right, this.rect.bottom - (this.keyInfo.getGuide() * this.ratio));
                }
            } else {
                this.path.lineTo(this.rect.right - (this.keyInfo.getNose() * this.ratio), this.rect.bottom);
                this.path.lineTo(this.rect.right, this.rect.bottom);
            }
        } else {
            this.path.lineTo(this.rect.right, this.rect.bottom);
        }
        canvas.drawPath(this.path, this.paint);
        if (isInputModel()) {
            this.paint.reset();
            this.paint.setAntiAlias(true);
            this.paint.setTextAlign(Paint.Align.CENTER);
            if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
                this.integerRectListA.clear();
                this.decimalRectListA.clear();
                for (int i18 = 0; i18 < this.spaceA.size(); i18++) {
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound = getToothCodeInt(getToothCodeAt(this.toothCodeA, i18));
                    } else {
                        toothCodeRound = getToothCodeRound(getToothCodeAt(this.toothCodeA, i18), this.depthNameA);
                    }
                    List<Integer> list = this.spaceA;
                    int spaceXInGraph20 = ((int) getSpaceXInGraph(list.get(list.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i18));
                    int i19 = this.rect.top - this.marginBig;
                    Rect rect = new Rect(spaceXInGraph20 - (this.rectWidth / 2), i19 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph20, i19);
                    this.integerRectListA.add(rect);
                    if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i18 && !this.flag.isDicimal()) {
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
                    float f59 = spaceXInGraph20;
                    canvas.drawText(toothCodeRound, f59, i19 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i20 = this.rect.top - this.marginSmall;
                        Rect rect2 = new Rect(spaceXInGraph20 - (this.rectWidth / 2), i20 - this.rectHeight, spaceXInGraph20 + (this.rectWidth / 2), i20);
                        this.decimalRectListA.add(rect2);
                        if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i18 && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeA, i18)), f59, i20 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
            }
            if (this.keyInfo.getSide() == 1 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
                this.integerRectListB.clear();
                this.decimalRectListB.clear();
                for (int i21 = 0; i21 < this.spaceB.size(); i21++) {
                    this.paint.setTextSize(this.integerTextSize);
                    if (isShowDecimal()) {
                        toothCodeRound2 = getToothCodeInt(getToothCodeAt(this.toothCodeB, i21));
                    } else {
                        toothCodeRound2 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i21), this.depthNameB);
                    }
                    List<Integer> list2 = this.spaceA;
                    int spaceXInGraph21 = ((int) getSpaceXInGraph(list2.get(list2.size() - 1).intValue())) - (this.twoRectSpace * ((this.spaceA.size() - 1) - i21));
                    int i22 = this.rect.bottom + this.marginSmall + this.rectHeight;
                    Rect rect3 = new Rect(spaceXInGraph21 - (this.rectWidth / 2), i22 - this.rectHeight, (this.rectWidth / 2) + spaceXInGraph21, i22);
                    this.integerRectListB.add(rect3);
                    if (this.keyInfo.getSide() == 1) {
                        if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i21 && !this.flag.isDicimal()) {
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
                    } else if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i21 && !this.flag.isDicimal()) {
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
                    float f60 = spaceXInGraph21;
                    canvas.drawText(toothCodeRound2, f60, i22 - AutoUtils.getPercentHeightSize(4), this.paint);
                    if (isShowDecimal()) {
                        this.paint.setTextSize(this.decimalTextSize);
                        int i23 = this.rect.bottom + this.marginBig + this.rectHeight;
                        Rect rect4 = new Rect(spaceXInGraph21 - (this.rectWidth / 2), i23 - this.rectHeight, spaceXInGraph21 + (this.rectWidth / 2), i23);
                        this.decimalRectListB.add(rect4);
                        if (this.keyInfo.getSide() == 1) {
                            if (this.flag.getRowPosition() == 0 && this.flag.getColumn() == i21 && this.flag.isDicimal()) {
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
                        } else if (this.flag.getRowPosition() == 1 && this.flag.getColumn() == i21 && this.flag.isDicimal()) {
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
                        canvas.drawText(getToothCodeDec(getToothCodeAt(this.toothCodeB, i21)), f60, i23 - AutoUtils.getPercentHeightSize(8), this.paint);
                    }
                }
            }
        } else {
            this.paint.setPathEffect(null);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextSize(this.integerTextSize);
            this.paint.setStrokeWidth(1.0f);
            this.paint.setTextAlign(Paint.Align.CENTER);
            if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
                for (int i24 = 0; i24 < this.spaceA.size(); i24++) {
                    Rect rect5 = new Rect();
                    String toothCodeRound3 = getToothCodeRound(getToothCodeAt(this.toothCodeA, i24), this.depthNameA);
                    this.paint.getTextBounds(toothCodeRound3, 0, toothCodeRound3.length(), rect5);
                    int spaceXInGraph22 = (int) getSpaceXInGraph(this.spaceA.get(i24).intValue());
                    int percentHeightSize = this.rect.top - AutoUtils.getPercentHeightSize(20);
                    if (isInteger(getToothCodeAt(this.toothCodeA, i24))) {
                        this.paint.setColor(this.textColorNoDecimal);
                    } else {
                        this.paint.setColor(this.textColorExistDecimal);
                    }
                    canvas.drawText(toothCodeRound3, spaceXInGraph22, percentHeightSize, this.paint);
                }
            }
            if (this.keyInfo.getSide() == 1 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
                for (int i25 = 0; i25 < this.spaceB.size(); i25++) {
                    String toothCodeRound4 = getToothCodeRound(getToothCodeAt(this.toothCodeB, i25), this.depthNameA);
                    this.paint.getTextBounds(toothCodeRound4, 0, toothCodeRound4.length(), new Rect());
                    float f61 = this.paint.getFontMetrics().ascent;
                    int spaceXInGraph23 = (int) getSpaceXInGraph(this.spaceB.get(i25).intValue());
                    int percentHeightSize2 = (this.rect.bottom - ((int) f61)) + AutoUtils.getPercentHeightSize(15);
                    if (isInteger(getToothCodeAt(this.toothCodeB, i25))) {
                        this.paint.setColor(this.textColorNoDecimal);
                    } else {
                        this.paint.setColor(this.textColorExistDecimal);
                    }
                    canvas.drawText(toothCodeRound4, spaceXInGraph23, percentHeightSize2, this.paint);
                }
            }
        }
        this.paint.setColor(this.markColor);
        this.paint.setPathEffect(this.dashPathEffect);
        if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            for (int i26 = 0; i26 < this.depthA.size(); i26++) {
                int intValue18 = this.depthA.get(i26).intValue();
                float spaceXInGraph24 = getSpaceXInGraph(this.spaceA.get(0).intValue());
                float f62 = intValue18;
                float f63 = this.rect.top + (this.ratio * f62);
                List<Integer> list3 = this.spaceA;
                canvas.drawLine(spaceXInGraph24, f63, getSpaceXInGraph(list3.get(list3.size() - 1).intValue()), this.rect.top + (f62 * this.ratio), this.paint);
            }
        }
        if (this.keyInfo.getSide() == 1 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            for (int i27 = 0; i27 < this.depthB.size(); i27++) {
                int intValue19 = this.depthB.get(i27).intValue();
                float spaceXInGraph25 = getSpaceXInGraph(this.spaceB.get(0).intValue());
                float f64 = intValue19;
                float f65 = this.rect.bottom - (this.ratio * f64);
                List<Integer> list4 = this.spaceB;
                canvas.drawLine(spaceXInGraph25, f65, getSpaceXInGraph(list4.get(list4.size() - 1).intValue()), this.rect.bottom - (f64 * this.ratio), this.paint);
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
        String[] split = str.split(";");
        if (this.keyInfo.getSide() == 1) {
            this.toothCodeB = split[0].split(",");
        } else if (this.keyInfo.getSide() == 6) {
            this.toothCodeA = split[0].split(",");
            this.toothCodeB = split[1].split(",");
        } else {
            this.toothCodeA = split[0].split(",");
        }
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
        if (this.keyInfo.getSide() == 6) {
            arrayList.add(this.toothCodeA);
            arrayList.add(this.toothCodeB);
        } else if (this.keyInfo.getSide() == 3) {
            arrayList.add(this.toothCodeA);
            arrayList.add(this.toothCodeB);
        } else if (this.keyInfo.getSide() == 1) {
            arrayList.add(this.toothCodeB);
        } else {
            arrayList.add(this.toothCodeA);
        }
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
    public void moveToLeft() {
        if (this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            int column = this.flag.getColumn();
            int rowPosition = this.flag.getRowPosition();
            if (column != 0) {
                this.flag.setColumn(column - 1);
            } else if (rowPosition == 0) {
                if (this.flag.isDicimal()) {
                    this.flag.setColumn(getAllSpaces().get(rowPosition).size() - 1);
                    this.flag.setDicimal(false);
                    showIntegerKeyboard(getAllDepthNames().get(0));
                }
            } else if (this.flag.isDicimal()) {
                this.flag.setColumn(getAllSpaces().get(rowPosition).size() - 1);
                this.flag.setDicimal(false);
                showIntegerKeyboard(getAllDepthNames().get(0));
            } else {
                if (isShowDecimal()) {
                    this.flag.setDicimal(true);
                    showDecimalKeyboard();
                } else {
                    showIntegerKeyboard(getAllDepthNames().get(0));
                }
                int i = rowPosition - 1;
                this.flag.setRowPosition(i);
                this.flag.setColumn(getAllSpaces().get(i).size() - 1);
            }
            invalidate();
            return;
        }
        super.moveToLeft();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToUp() {
        if (this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            int rowPosition = this.flag.getRowPosition();
            int column = this.flag.getColumn();
            if (this.flag.isDicimal()) {
                this.flag.setDicimal(false);
                showIntegerKeyboard(getAllDepthNames().get(0));
            } else {
                int size = rowPosition == 0 ? getAllSpaces().size() - 1 : rowPosition - 1;
                if (isShowDecimal()) {
                    this.flag.setDicimal(true);
                    showDecimalKeyboard();
                } else {
                    showIntegerKeyboard(getAllDepthNames().get(0));
                }
                this.flag.setRowPosition(size);
                if (getAllSpaces().get(size).size() - 1 >= column) {
                    this.flag.setColumn(column);
                } else {
                    this.flag.setColumn(getAllSpaces().get(size).size() - 1);
                }
            }
            invalidate();
            return;
        }
        super.moveToUp();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToRight() {
        if (this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            int column = this.flag.getColumn();
            int rowPosition = this.flag.getRowPosition();
            if (column == getAllSpaces().get(rowPosition).size() - 1) {
                this.flag.setColumn(0);
                if (this.flag.isDicimal()) {
                    if (rowPosition == getAllSpaces().size() - 1) {
                        this.flag.setRowPosition(0);
                        showIntegerKeyboard(getAllDepthNames().get(0));
                    } else {
                        this.flag.setRowPosition(rowPosition + 1);
                        showIntegerKeyboard(getAllDepthNames().get(0));
                    }
                    this.flag.setDicimal(false);
                } else if (isShowDecimal()) {
                    this.flag.setDicimal(true);
                    showDecimalKeyboard();
                } else if (rowPosition == getAllSpaces().size() - 1) {
                    this.flag.setRowPosition(0);
                    showIntegerKeyboard(getAllDepthNames().get(0));
                } else {
                    this.flag.setRowPosition(rowPosition + 1);
                    showIntegerKeyboard(getAllDepthNames().get(0));
                }
            } else {
                this.flag.setColumn(column + 1);
            }
            invalidate();
            return;
        }
        super.moveToRight();
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    public void moveToDown() {
        if (this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            int rowPosition = this.flag.getRowPosition();
            int column = this.flag.getColumn();
            if (this.flag.isDicimal() || !isShowDecimal()) {
                int i = rowPosition == getAllDepthNames().size() - 1 ? 0 : rowPosition + 1;
                showIntegerKeyboard(getAllDepthNames().get(0));
                this.flag.setRowPosition(i);
                this.flag.setDicimal(false);
                if (getAllDepthNames().get(i).size() - 1 >= column) {
                    this.flag.setColumn(column);
                } else {
                    this.flag.setColumn(getAllDepthNames().get(i).size() - 1);
                }
            } else if (isShowDecimal()) {
                this.flag.setDicimal(true);
                showDecimalKeyboard();
            }
            invalidate();
            return;
        }
        super.moveToDown();
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
        if (this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
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
        if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            arrayList.add(this.integerRectListA);
        }
        if (this.keyInfo.getSide() == 1 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            arrayList.add(this.integerRectListB);
        }
        return arrayList;
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.Key
    List<List<Rect>> getDecimalRectContainer() {
        ArrayList arrayList = new ArrayList();
        if (this.keyInfo.getSide() == 0 || this.keyInfo.getSide() == 5 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            arrayList.add(this.decimalRectListA);
        }
        if (this.keyInfo.getSide() == 1 || this.keyInfo.getSide() == 3 || this.keyInfo.getSide() == 6) {
            arrayList.add(this.decimalRectListB);
        }
        return arrayList;
    }
}
