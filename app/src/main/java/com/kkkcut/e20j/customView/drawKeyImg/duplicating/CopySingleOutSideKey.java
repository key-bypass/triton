package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.cutting.machine.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopySingleOutSideKey extends CopyKey {
    public CopySingleOutSideKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context, list, list2, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyLenght(getMaxX());
        setKeyWidth(getMaxY());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        this.paint.setColor(-1);
        Rect keyRect = getKeyRect();
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        int size = getPointsA().size();
        for (int i = 0; i < size; i++) {
            if (!getPointsA().get(i).isInvalid()) {
                if (i == 0) {
                    this.path.moveTo(getPointX(i, CopyKey.Side.A), getPointY(i, CopyKey.Side.A));
                } else {
                    this.path.lineTo(getPointX(i, CopyKey.Side.A), getPointY(i, CopyKey.Side.A));
                }
            }
        }
        int size2 = getPointsB().size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (!getPointsB().get(i2).isInvalid()) {
                if (i2 == 0) {
                    this.path.moveTo(getPointX(i2, CopyKey.Side.B), getPointY(i2, CopyKey.Side.B));
                } else {
                    this.path.lineTo(getPointX(i2, CopyKey.Side.B), getPointY(i2, CopyKey.Side.B));
                }
            }
        }
        this.paint.setColor(this.colorRed);
        canvas.drawPath(this.path, this.paint);
    }
}
