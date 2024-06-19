package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import com.liying.core.error.ErrorHelper;
import java.util.List;

/* loaded from: classes2.dex */
public class S1B extends ClampF {
    private int high1;
    private int high2;
    private int shoulderDiffDown;
    private int shoulderDiffUp;
    private int x1Down;
    private int x1Up;
    private int x2Down;
    private int x2Up;

    public S1B() {
    }

    public S1B(List<SerialResBean> list) {
        setHigh1(Math.abs(list.get(12).getZ() - list.get(10).getZ()));
        setHigh2(Math.abs(list.get(0).getZ() - list.get(3).getZ()));
        setShoulderDiffUp(Math.abs(list.get(1).getY() - list.get(6).getY()) - ToolSizeManager.getDecoderSize2());
        setShoulderDiffDown(Math.abs(list.get(1).getY() - list.get(5).getY()) - ToolSizeManager.getDecoderSize2());
        setX1Up(list.get(8).getX() - ToolSizeManager.getDecoderRadius2());
        setX2Up(list.get(9).getX() - ToolSizeManager.getDecoderRadius2());
        setX1Down(list.get(4).getX() - ToolSizeManager.getDecoderRadius2());
        setX2Down(list.get(2).getX() - ToolSizeManager.getDecoderRadius2());
    }

    public int getShoulderDiffUp() {
        return this.shoulderDiffUp;
    }

    public void setShoulderDiffUp(int i) {
        this.shoulderDiffUp = i;
    }

    public int getShoulderDiffDown() {
        return this.shoulderDiffDown;
    }

    public void setShoulderDiffDown(int i) {
        this.shoulderDiffDown = i;
    }

    public int getX1Up() {
        return this.x1Up;
    }

    public void setX1Up(int i) {
        this.x1Up = i;
    }

    public int getX2Up() {
        return this.x2Up;
    }

    public void setX2Up(int i) {
        this.x2Up = i;
    }

    public int getX1Down() {
        return this.x1Down;
    }

    public void setX1Down(int i) {
        this.x1Down = i;
    }

    public int getX2Down() {
        return this.x2Down;
    }

    public void setX2Down(int i) {
        this.x2Down = i;
    }

    public int getHigh1() {
        return this.high1;
    }

    public void setHigh1(int i) {
        this.high1 = i;
    }

    public int getHigh2() {
        return this.high2;
    }

    public void setHigh2(int i) {
        this.high2 = i;
    }

    public String toString() {
        return "S1B{shoulderDiffUp=" + this.shoulderDiffUp + ", shoulderDiffDown=" + this.shoulderDiffDown + ", x1Up=" + this.x1Up + ", x2Up=" + this.x2Up + ", x1Down=" + this.x1Down + ", x2Down=" + this.x2Down + ", high1=" + this.high1 + ", high2=" + this.high2 + '}';
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        if (Math.abs((getHigh1() * MachineData.dZScale) - 165.0f) > 20.0f) {
            ErrorHelper.handleError(79);
            return null;
        }
        return Command.WriteSpecifyLocationData(66, getShoulderDiffUp() + ":" + getShoulderDiffDown() + ":" + getX1Up() + ":" + getX2Up() + ":" + getX1Down() + ":" + getX2Down() + ":" + getHigh1() + ":" + getHigh2());
    }
}
