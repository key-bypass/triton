package com.liying.core.duplicate;

import com.liying.core.bean.DestPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SinglePathData implements Cloneable {
    private Benchmark benchmark;
    List<DestPoint> destPointList = new ArrayList();

    public SinglePathData() {
    }

    public SinglePathData(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public Benchmark getBenchmark() {
        return this.benchmark;
    }

    public void setBenchmark(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public List<DestPoint> getDestPointList() {
        return this.destPointList;
    }

    public void setDestPointList(List<DestPoint> list) {
        this.destPointList = list;
    }

    public void addDestPoint(DestPoint destPoint) {
        this.destPointList.add(destPoint);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SinglePathData m602clone() {
        SinglePathData singlePathData;
        CloneNotSupportedException e;
        try {
            singlePathData = (SinglePathData) super.clone();
        } catch (CloneNotSupportedException e2) {
            singlePathData = null;
            e = e2;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<DestPoint> it = singlePathData.getDestPointList().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().m600clone());
            }
            singlePathData.setDestPointList(arrayList);
        } catch (CloneNotSupportedException e3) {
            e = e3;
            e.printStackTrace();
            return singlePathData;
        }
        return singlePathData;
    }
}
