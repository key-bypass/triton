package com.kkkcut.e20j.cockroach.compat;

import android.os.Message;

/* loaded from: classes.dex */
public interface IActivityKiller {
    void finishLaunchActivity(Message message);

    void finishPauseActivity(Message message);

    void finishResumeActivity(Message message);

    void finishStopActivity(Message message);
}
