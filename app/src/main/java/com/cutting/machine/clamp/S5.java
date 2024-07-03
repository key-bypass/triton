package com.cutting.machine.clamp;

import com.cutting.machine.Command;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.SerialResBean;

import java.util.List;

/* renamed from: com.cutting.machine.clamp.S5 */
/* loaded from: classes2.dex */
public class S5 extends ClampF {
    private int deep;

    /* renamed from: x */
    private int f411x;

    /* renamed from: y */
    private int f412y;

    public S5() {
    }

    public S5(List<SerialResBean> list) {
        setX(list.get(2).getX() + ToolSizeManager.getDecoderRadius2());
        setY(list.get(1).getY() - ToolSizeManager.getDecoderRadius2());
        setDeep(Math.abs(list.get(0).getZ() - list.get(3).getZ()));
    }

    public int getX() {
        return this.f411x;
    }

    public void setX(int i) {
        this.f411x = i;
    }

    public int getY() {
        return this.f412y;
    }

    public void setY(int i) {
        this.f412y = i;
    }

    public int getDeep() {
        return this.deep;
    }

    public void setDeep(int i) {
        this.deep = i;
    }

    @Override // com.cutting.machine.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(69, getX() + ":" + getY() + ":" + getDeep());
    }
}
