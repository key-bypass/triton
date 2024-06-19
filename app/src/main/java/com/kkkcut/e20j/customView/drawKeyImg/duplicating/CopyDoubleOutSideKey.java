package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.liying.core.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopyDoubleOutSideKey extends CopyKey {
    public CopyDoubleOutSideKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context, list, list2, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyLenght(getMaxX());
        setKeyWidth(getMaxY());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        Rect keyRect = getKeyRect();
        this.paint.setColor(-1);
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top + (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right, keyRect.bottom - (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setColor(this.colorRed);
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
        for (int size2 = getPointsB().size() - 1; size2 >= 0; size2--) {
            this.path.lineTo(getPointX(size2, CopyKey.Side.B), getPointY(size2, CopyKey.Side.B));
        }
        canvas.drawPath(this.path, this.paint);
    }
}
