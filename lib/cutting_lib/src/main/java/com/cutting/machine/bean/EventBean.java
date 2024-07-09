package com.cutting.machine.bean;

import com.cutting.machine.OperateType;

/* loaded from: classes2.dex */
public class EventBean {
    private Object data;
    private final OperateType operateType;

    public EventBean(OperateType operateType) {
        this.operateType = operateType;
    }

    public EventBean(OperateType operateType, Object obj) {
        this.operateType = operateType;
        this.data = obj;
    }

    public OperateType getOperateType() {
        return this.operateType;
    }

    public Object getData() {
        return this.data;
    }
}
