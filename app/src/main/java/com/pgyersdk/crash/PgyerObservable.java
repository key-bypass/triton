package com.pgyersdk.crash;

import com.pgyersdk.utils.LogUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PgyerObservable.java */
/* renamed from: com.pgyersdk.crash.e */
/* loaded from: classes2.dex */
public class PgyerObservable {

    /* renamed from: a */
    private List<PgyerObserver> pgyerObserver = new ArrayList();

    public void attach(PgyerObserver pgyerObserver) {
        if (!this.pgyerObserver.contains(pgyerObserver)) {
            this.pgyerObserver.add(pgyerObserver);
        } else {
            LogUtils.m220d("PgyerSDK", "This observer is already attached.");
        }
    }

    public void detach(PgyerObserver pgyerObserver) {
        if (this.pgyerObserver.contains(pgyerObserver)) {
            this.pgyerObserver.remove(pgyerObserver);
        }
    }

    public void notifyObservers(Thread thread, Throwable th) {
        Iterator<PgyerObserver> it = this.pgyerObserver.iterator();
        while (it.hasNext()) {
            it.next().receivedCrash(thread, th);
        }
    }
}
