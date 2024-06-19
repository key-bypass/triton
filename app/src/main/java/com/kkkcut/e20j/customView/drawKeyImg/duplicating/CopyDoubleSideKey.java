package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.liying.core.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopyDoubleSideKey extends CopyKey {
    public CopyDoubleSideKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context, list, list2, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyLenght(getMaxX());
        setKeyWidth(getMaxY());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        int size = getPointsA().size();
        if (size <= 0) {
            return;
        }
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
        if (size2 <= 0) {
            return;
        }
        int i2 = size2 - 1;
        this.path.lineTo(getKeyRect().left + (getMaxX() * getRatio()) + (Math.abs(getPointY(size - 1, CopyKey.Side.A) - getPointY(i2, CopyKey.Side.B)) / 2.0f), getKeyRect().centerY());
        while (i2 >= 0) {
            if (!getPointsB().get(i2).isInvalid()) {
                this.path.lineTo(getPointX(i2, CopyKey.Side.B), getPointY(i2, CopyKey.Side.B));
            }
            i2--;
        }
        canvas.drawPath(this.path, this.paint);
    }
}
