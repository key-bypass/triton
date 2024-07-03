package com.cutting.machine.operation;

import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;

import java.util.List;

/* loaded from: classes2.dex */
public class OperationStepHandle {
    private Clamp clamp;
    private Position curPosition;
    private int index;
    private List<StepBean> stepLists;

    public OperationStepHandle(Clamp clamp) {
    }

    public void parsRule(StepBean stepBean) {
    }

    public void start(List<StepBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.stepLists = list;
        parsRule(list.get(this.index));
    }

    /* loaded from: classes2.dex */
    private static class Position {

        /* renamed from: x */
        private int f421x;

        /* renamed from: y */
        private int f422y;

        /* renamed from: z */
        private int f423z;

        public Position(int i, int i2, int i3) {
            this.f421x = i;
            this.f422y = i2;
            this.f423z = i3;
        }

        public int getX() {
            return this.f421x;
        }

        public void setX(int i) {
            this.f421x = i;
        }

        public int getY() {
            return this.f422y;
        }

        public void setY(int i) {
            this.f422y = i;
        }

        public int getZ() {
            return this.f423z;
        }

        public void setZ(int i) {
            this.f423z = i;
        }
    }
}
