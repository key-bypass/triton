package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.cutting.machine.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopySingleSideKey extends CopyKey {
    public CopySingleSideKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context, list, list2, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyWidth(getMaxY());
        setKeyLenght(getMaxX());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        int size = getPointsA().size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                this.path.moveTo(getPointX(i, CopyKey.Side.A), getPointY(i, CopyKey.Side.A));
            } else {
                this.path.lineTo(getPointX(i, CopyKey.Side.A), getPointY(i, CopyKey.Side.A));
            }
        }
        this.path.lineTo(getPointX(size - 1, CopyKey.Side.A) - (getPointsA().get(size).getDepth() * getRatio()), getKeyRect().bottom);
        this.path.lineTo(getPointX(0, CopyKey.Side.A), getKeyRect().bottom);
        canvas.drawPath(this.path, this.paint);
    }
}
