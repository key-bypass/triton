package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* loaded from: classes2.dex */
public class S1C extends ClampF {
    private int leftDiff;
    private int rightDiff;
    private int shoulderDiff;

    public S1C() {
    }

    public S1C(List<SerialResBean> list) {
        setShoulderDiff(Math.abs(list.get(0).getY() - list.get(3).getY()) - ((int) (150.0f / MachineData.dYScale)));
        setLeftDiff(Math.abs(list.get(5).getX() - list.get(6).getX()));
        setRightDiff(Math.abs(list.get(2).getX() - list.get(4).getX()) - ToolSizeManager.getDecoderSize2());
    }

    public int getShoulderDiff() {
        return this.shoulderDiff;
    }

    public void setShoulderDiff(int i) {
        this.shoulderDiff = i;
    }

    public int getRightDiff() {
        return this.rightDiff;
    }

    public void setRightDiff(int i) {
        this.rightDiff = i;
    }

    public int getLeftDiff() {
        return this.leftDiff;
    }

    public void setLeftDiff(int i) {
        this.leftDiff = i;
    }

    public String toString() {
        return "S1C{shoulderDiff=" + this.shoulderDiff + ", rightDiff=" + this.rightDiff + ", leftDiff=" + this.leftDiff + '}';
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(67, getShoulderDiff() + ":" + getRightDiff() + ":" + getLeftDiff());
    }
}
