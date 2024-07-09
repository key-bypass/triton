package com.cutting.machine.duplicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DecodeData implements Cloneable {
    List<SinglePathData> pathDataList = new ArrayList();

    public List<SinglePathData> getPathDataList() {
        return this.pathDataList;
    }

    public void setPathDataList(List<SinglePathData> list) {
        this.pathDataList = list;
    }

    public void addSinglePathData(SinglePathData singlePathData) {
        this.pathDataList.add(singlePathData);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DecodeData m601clone() {
        DecodeData decodeData;
        CloneNotSupportedException e;
        try {
            decodeData = (DecodeData) super.clone();
        } catch (CloneNotSupportedException e2) {
            decodeData = null;
            e = e2;
        }

        ArrayList arrayList = new ArrayList();
        assert decodeData != null;
        Iterator<SinglePathData> it = decodeData.getPathDataList().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().m602clone());
        }
        decodeData.setPathDataList(arrayList);

        return decodeData;
    }
}
