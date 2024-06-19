package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* loaded from: classes2.dex */
public class E9S5 extends ClampF {
    private int blockDistance;
    private int centerY;

    public E9S5() {
    }

    public E9S5(List<SerialResBean> list) {
        setCenterY((list.get(3).getY() + list.get(4).getY()) / 2);
        setBlockDistance(Math.abs(list.get(1).getY() - list.get(4).getY()));
    }

    public int getCenterY() {
        return this.centerY;
    }

    public void setCenterY(int i) {
        this.centerY = i;
    }

    public int getBlockDistance() {
        return this.blockDistance;
    }

    public void setBlockDistance(int i) {
        this.blockDistance = i;
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(69, getCenterY() + ":" + getBlockDistance());
    }
}
