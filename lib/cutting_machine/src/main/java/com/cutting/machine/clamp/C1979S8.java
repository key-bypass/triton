package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* renamed from: com.liying.core.clamp.S8 */
/* loaded from: classes2.dex */
public class C1979S8 extends ClampF {
    private int grooveWidth;
    private int height1;
    private int height2;
    private int length;

    public C1979S8() {
    }

    public C1979S8(List<SerialResBean> list) {
        setLength(Math.abs(list.get(1).getY() - list.get(6).getY()) - ToolSizeManager.getDecoderSize2());
        setHeight1(Math.abs(list.get(0).getZ() - list.get(3).getZ()));
        setHeight2(Math.abs(list.get(0).getZ() - list.get(2).getZ()));
        setGrooveWidth(Math.abs(list.get(4).getY() - list.get(5).getY()) + ToolSizeManager.getDecoderSize2());
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public int getHeight1() {
        return this.height1;
    }

    public void setHeight1(int i) {
        this.height1 = i;
    }

    public int getHeight2() {
        return this.height2;
    }

    public void setHeight2(int i) {
        this.height2 = i;
    }

    public int getGrooveWidth() {
        return this.grooveWidth;
    }

    public void setGrooveWidth(int i) {
        this.grooveWidth = i;
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(70, getLength() + ":" + getHeight1() + ":" + getHeight2());
    }
}
