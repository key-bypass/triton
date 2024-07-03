package com.cutting.machine.bean;

import com.cutting.machine.clamp.MachineData;

/* loaded from: classes2.dex */
public class DestPoint implements Cloneable {
    private int depth;
    private boolean doNotSplitDepth;
    private boolean gapPoint;
    private boolean gapPointMoreCut;
    private int groove;
    private boolean invalid;
    private boolean isTooth;
    private int keyIndex;
    private int rowPos;
    private boolean sharpenPoint;
    private int space;

    public DestPoint(int i, int i2) {
        this.isTooth = true;
        this.space = i;
        this.depth = i2;
    }

    public DestPoint(int i, int i2, int i3) {
        this.isTooth = true;
        this.space = i;
        this.depth = i2;
        this.rowPos = i3;
    }

    public DestPoint(int i, int i2, boolean z) {
        this.isTooth = true;
        if (z) {
            this.space = (int) (i / MachineData.dYScale);
            this.depth = (int) (i2 / MachineData.dXScale);
        } else {
            this.space = i;
            this.depth = i2;
        }
    }

    public DestPoint(int i, int i2, boolean z, boolean z2) {
        this.isTooth = true;
        if (z) {
            this.space = (int) (i / MachineData.dYScale);
            this.depth = (int) (i2 / MachineData.dXScale);
        } else {
            this.space = i;
            this.depth = i2;
        }
        this.isTooth = z2;
    }

    public boolean isSharpenPoint() {
        return this.sharpenPoint;
    }

    public void setSharpenPoint(boolean z) {
        this.sharpenPoint = z;
    }

    public int getGroove() {
        return this.groove;
    }

    public void setGroove(int i) {
        this.groove = i;
    }

    public boolean isInvalid() {
        return this.invalid;
    }

    public void setInvalid(boolean z) {
        this.invalid = z;
    }

    public int getKeyIndex() {
        return this.keyIndex;
    }

    public void setKeyIndex(int i) {
        this.keyIndex = i;
    }

    public boolean isGapPointMoreCut() {
        return this.gapPointMoreCut;
    }

    public void setGapPointMoreCut(boolean z) {
        this.gapPointMoreCut = z;
    }

    public boolean isGapPoint() {
        return this.gapPoint;
    }

    public void setGapPoint(boolean z) {
        this.gapPoint = z;
    }

    public int getRowPos() {
        return this.rowPos;
    }

    public void setRowPos(int i) {
        this.rowPos = i;
    }

    public int getSpace() {
        return this.space;
    }

    public void setSpace(int i) {
        this.space = i;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int i) {
        this.depth = i;
    }

    public boolean isTooth() {
        return this.isTooth;
    }

    public void setTooth(boolean z) {
        this.isTooth = z;
    }

    public boolean isDoNotSplitDepth() {
        return this.doNotSplitDepth;
    }

    public void setDoNotSplitDepth(boolean z) {
        this.doNotSplitDepth = z;
    }

    public String toString() {
        return "DestPoint{space=" + this.space + ", depth=" + this.depth + ", invalid=" + this.invalid + ", groove=" + this.groove + ", sharpenPoint=" + this.sharpenPoint + '}';
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DestPoint m600clone() {
        try {
            return (DestPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
