package com.pgyersdk.crash;

import com.pgyersdk.p016f.C2041f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PgyerObservable.java */
/* renamed from: com.pgyersdk.crash.e */
/* loaded from: classes2.dex */
class C2030e {

    /* renamed from: a */
    private List<PgyerObserver> f490a = new ArrayList();

    public void attach(PgyerObserver pgyerObserver) {
        if (!this.f490a.contains(pgyerObserver)) {
            this.f490a.add(pgyerObserver);
        } else {
            C2041f.m220d("PgyerSDK", "This observer is already attached.");
        }
    }

    public void detach(PgyerObserver pgyerObserver) {
        if (this.f490a.contains(pgyerObserver)) {
            this.f490a.remove(pgyerObserver);
        }
    }

    public void notifyObservers(Thread thread, Throwable th) {
        Iterator<PgyerObserver> it = this.f490a.iterator();
        while (it.hasNext()) {
            it.next().receivedCrash(thread, th);
        }
    }
}
