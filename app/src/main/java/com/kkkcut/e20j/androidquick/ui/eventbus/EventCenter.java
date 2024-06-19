package com.kkkcut.e20j.androidquick.ui.eventbus;

/* loaded from: classes.dex */
public class EventCenter<T> {
    private T data;
    private int eventCode;

    public EventCenter(int i) {
        this(i, null);
    }

    public EventCenter(int i, T t) {
        this.eventCode = i;
        this.data = t;
    }

    public int getEventCode() {
        return this.eventCode;
    }

    public T getData() {
        return this.data;
    }
}
