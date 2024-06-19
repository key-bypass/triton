package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* loaded from: classes2.dex */
public class S2B extends ClampF {
    private int deep;
    private int shoulderDiff;

    /* renamed from: x1 */
    private int f409x1;

    /* renamed from: x2 */
    private int f410x2;

    public S2B() {
    }

    public S2B(List<SerialResBean> list) {
        setShoulderDiff(Math.abs(list.get(0).getY() - list.get(4).getY()) - ToolSizeManager.getDecoderSize2());
        setDeep((Math.abs(list.get(1).getX() - list.get(2).getX()) + Math.abs(list.get(1).getX() - list.get(3).getX())) / 2);
        setX1(list.get(2).getX() - ToolSizeManager.getDecoderRadius2());
        setX2(list.get(3).getX() - ToolSizeManager.getDecoderRadius2());
    }

    public int getDeep() {
        return this.deep;
    }

    public void setDeep(int i) {
        this.deep = i;
    }

    public int getShoulderDiff() {
        return this.shoulderDiff;
    }

    public void setShoulderDiff(int i) {
        this.shoulderDiff = i;
    }

    public int getX1() {
        return this.f409x1;
    }

    public void setX1(int i) {
        this.f409x1 = i;
    }

    public int getX2() {
        return this.f410x2;
    }

    public void setX2(int i) {
        this.f410x2 = i;
    }

    public int getX() {
        return (this.f410x2 + this.f409x1) / 2;
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(2, getShoulderDiff() + ":" + getDeep() + ":" + getX1() + ":" + getX2());
    }
}
