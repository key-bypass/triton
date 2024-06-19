package com.liying.core.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import com.liying.core.MachineInfo;
import com.liying.core.bean.DestPoint;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.KeyType;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateDecode.DuplicateKeyData;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class CopyKey extends View {
    protected int colorRed;
    private DuplicateKeyData duplicateKeyData;
    private int keyLenght;
    private Rect keyRect;
    private int keyWidth;
    protected Paint paint;
    protected Path path;
    List<DestPoint> pointsA;
    List<DestPoint> pointsB;
    private float ratio;

    /* loaded from: classes2.dex */
    protected enum Side {
        A,
        B
    }

    protected abstract void cacularKeySize();

    protected abstract void drawKeyView(Canvas canvas);

    public List<DestPoint> getPointsB() {
        return this.pointsB;
    }

    public Rect getKeyRect() {
        return this.keyRect;
    }

    public float getRatio() {
        return this.ratio;
    }

    public List<DestPoint> getPointsA() {
        return this.pointsA;
    }

    public void setPointsA(List<DestPoint> list) {
        this.pointsA = list;
    }

    public void setPointsB(List<DestPoint> list) {
        this.pointsB = list;
    }

    public int getKeyWidth() {
        return this.keyWidth;
    }

    public void setKeyWidth(int i) {
        this.keyWidth = i;
    }

    public int getKeyLenght() {
        return this.keyLenght;
    }

    public void setKeyLenght(int i) {
        this.keyLenght = i;
    }

    public CopyKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context);
        this.colorRed = Color.parseColor("#ff205f");
        this.duplicateKeyData = duplicateKeyData;
        if (duplicateKeyData.getKeyType() == KeyType.SINGLE_SIDE_KEY) {
            this.keyWidth = Math.max(duplicateKeyData.getDecodeWidth(), getMaxDepth());
        } else {
            this.keyWidth = duplicateKeyData.getDecodeWidth();
        }
        this.keyLenght = duplicateKeyData.getLength();
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(getKeyColor());
        this.paint.setStrokeWidth(2.0f);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setPathEffect(new CornerPathEffect(4.0f));
        this.paint.setAntiAlias(true);
        this.path = new Path();
    }

    public int getKeyColor() {
        if (MachineInfo.isE9Standard(getContext())) {
            return Color.parseColor("#0BB4ED");
        }
        return -1;
    }

    public int getMaxDepth() {
        Iterator<SinglePathData> it = getKeyDataList().iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (it2.hasNext()) {
                i = Math.max(it2.next().getDepth(), i);
            }
        }
        return i;
    }

    public CopyKey(Context context) {
        super(context);
        this.colorRed = Color.parseColor("#ff205f");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        initViewSize();
    }

    protected void initViewSize() {
        int measuredHeight = (getMeasuredHeight() - getPaddingBottom()) - getPaddingTop();
        int measuredWidth = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
        this.ratio = (measuredHeight * 1.0f) / (getKeyWidth() * 3);
        float f = measuredWidth;
        if (getKeyLenght() * this.ratio < f) {
            this.keyRect = new Rect(((measuredWidth - ((int) (getKeyLenght() * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 2) / 6) + getPaddingTop(), ((measuredWidth + ((int) (getKeyLenght() * this.ratio))) / 2) + getPaddingLeft(), ((measuredHeight * 4) / 6) + getPaddingTop());
            return;
        }
        this.ratio = (f * 1.0f) / getKeyLenght();
        int i = measuredHeight / 2;
        this.keyRect = new Rect(getPaddingLeft(), (getPaddingTop() + i) - ((int) ((this.ratio * getKeyWidth()) / 2.0f)), measuredWidth + getPaddingLeft(), getPaddingTop() + i + ((int) ((this.ratio * getKeyWidth()) / 2.0f)));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawKeyView(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getPointX(DestPoint destPoint) {
        float f;
        float space;
        float ratio;
        if (getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
            f = getKeyRect().left;
            space = destPoint.getSpace();
            ratio = getRatio();
        } else {
            f = getKeyRect().right;
            space = destPoint.getSpace();
            ratio = getRatio();
        }
        return f + (space * ratio);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getPointY(DestPoint destPoint, Benchmark benchmark) {
        if (benchmark == Benchmark.LEFT) {
            return getKeyRect().top + (destPoint.getDepth() * getRatio());
        }
        return getKeyRect().bottom - (destPoint.getDepth() * getRatio());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getPointYBaseCenter(DestPoint destPoint, Benchmark benchmark) {
        if (benchmark == Benchmark.LEFT) {
            return getKeyRect().centerY() + (destPoint.getDepth() * getRatio());
        }
        return getKeyRect().centerY() - (destPoint.getDepth() * getRatio());
    }

    public List<SinglePathData> getKeyDataList() {
        return this.duplicateKeyData.getDecodeData().getPathDataList();
    }

    public KeyAlign getKeyAlign() {
        return this.duplicateKeyData.getKeyAlign();
    }
}
