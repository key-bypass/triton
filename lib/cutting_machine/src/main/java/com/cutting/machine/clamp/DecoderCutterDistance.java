package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import java.util.List;

/* loaded from: classes2.dex */
public class DecoderCutterDistance extends ClampF {
    private int s1X;
    private int s1Y;
    private int s2X;
    private int s2Y;
    private int xDistance;
    private int yDistance;

    public DecoderCutterDistance() {
    }

    public DecoderCutterDistance(int i, int i2) {
        this.xDistance = i;
        this.yDistance = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.liying.core.clamp.DecoderCutterDistance$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19731 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$clamp$Clamp;

        static {
            int[] iArr = new int[Clamp.values().length];
            $SwitchMap$com$liying$core$clamp$Clamp = iArr;
            try {
                iArr[Clamp.S1_D.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1C.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_A.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DecoderCutterDistance(List<SerialResBean> list, Clamp clamp) {
        int i = C19731.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()];
        if (i == 1) {
            int x = (list.get(0).getX() + list.get(1).getX()) / 2;
            int y = list.get(2).getY();
            int x2 = (list.get(3).getX() + list.get(4).getX()) / 2;
            int y2 = list.get(5).getY();
            setXDistance(x2 - x);
            setYDistance(y2 - y);
            setS1X(list.get(0).getX() + ToolSizeManager.getDecoderRadius2());
            setS1Y(list.get(2).getY() + ToolSizeManager.getDecoderRadius2());
            setS2X(0);
            setS2Y(0);
            return;
        }
        if (i == 2) {
            int x3 = (list.get(0).getX() + list.get(1).getX()) / 2;
            int y3 = list.get(2).getY();
            int x4 = (list.get(3).getX() + list.get(4).getX()) / 2;
            int y4 = list.get(5).getY();
            setXDistance(x4 - x3);
            setYDistance(y4 - y3);
            return;
        }
        if (i != 3) {
            return;
        }
        setXDistance(list.get(2).getX() - list.get(0).getX());
        setYDistance(list.get(3).getY() - list.get(1).getY());
        setS1X(0);
        setS1Y(0);
        setS2X(list.get(0).getX() - ToolSizeManager.getDecoderRadius2());
        setS2Y(list.get(1).getY() + ToolSizeManager.getDecoderRadius2());
    }

    public int getxDistance() {
        return this.xDistance;
    }

    public void setXDistance(int i) {
        this.xDistance = i;
    }

    public int getyDistance() {
        return this.yDistance;
    }

    public void setYDistance(int i) {
        this.yDistance = i;
    }

    public int getS1X() {
        return this.s1X;
    }

    public void setS1X(int i) {
        this.s1X = i;
    }

    public int getS1Y() {
        return this.s1Y;
    }

    public void setS1Y(int i) {
        this.s1Y = i;
    }

    public int getS2X() {
        return this.s2X;
    }

    public void setS2X(int i) {
        this.s2X = i;
    }

    public int getS2Y() {
        return this.s2Y;
    }

    public void setS2Y(int i) {
        this.s2Y = i;
    }

    public String toString() {
        return "DecoderCutterDistance{xDistance=" + this.xDistance + ", yDistance=" + this.yDistance + ", s1X=" + this.s1X + ", s1Y=" + this.s1Y + ", s2X=" + this.s2X + ", s2Y=" + this.s2Y + '}';
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(64, getxDistance() + ":" + getyDistance() + ":" + getS1X() + ":" + getS1Y() + ":" + getS2X() + ":" + getS2Y());
    }
}
