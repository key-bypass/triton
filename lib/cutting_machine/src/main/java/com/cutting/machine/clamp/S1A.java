package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* loaded from: classes2.dex */
public class S1A extends ClampF {
    private int high;
    private int shouldDiff;

    /* renamed from: x1 */
    private int f405x1;

    /* renamed from: x2 */
    private int f406x2;

    public S1A(List<SerialResBean> list) {
        setShouldDiff(Math.abs(list.get(1).getY() - list.get(4).getY()) - ToolSizeManager.getDecoderSize2());
        setHigh(Math.abs(list.get(0).getZ() - list.get(6).getZ()));
        setX1(list.get(3).getX() - ToolSizeManager.getDecoderRadius2());
        setX2(list.get(2).getX() - ToolSizeManager.getDecoderRadius2());
    }

    public S1A() {
    }

    public int getShouldDiff() {
        return this.shouldDiff;
    }

    public void setShouldDiff(int i) {
        this.shouldDiff = i;
    }

    public int getX1() {
        return this.f405x1;
    }

    public void setX1(int i) {
        this.f405x1 = i;
    }

    public int getHigh() {
        return this.high;
    }

    public void setHigh(int i) {
        this.high = i;
    }

    public int getX2() {
        return this.f406x2;
    }

    public void setX2(int i) {
        this.f406x2 = i;
    }

    public String toString() {
        return "S1A{shouldDiff=" + this.shouldDiff + ", high=" + this.high + ", x1=" + this.f405x1 + ", x2=" + this.f406x2 + '}';
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(65, getShouldDiff() + ":" + getHigh() + ":" + getX1() + ":" + getX2());
    }
}
