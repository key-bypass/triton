package com.kkkcut.e20j.bean.eventbus;

/* loaded from: classes.dex */
public class DuplicateBean {
    private int cutDepthSingleKey;
    private int cutterSize;
    private int layerCut;

    public DuplicateBean(int i, int i2, int i3) {
        this.layerCut = i;
        this.cutterSize = i2;
        this.cutDepthSingleKey = i3;
    }

    public DuplicateBean(int i) {
        this.cutterSize = i;
    }

    public int getLayerCut() {
        return this.layerCut;
    }

    public int getCutterSize() {
        return this.cutterSize;
    }

    public int getCutDepthSingleKey() {
        return this.cutDepthSingleKey;
    }

    public String toString() {
        return "DuplicateBean{layerCut=" + this.layerCut + ", cutterSize=" + this.cutterSize + '}';
    }
}
