package com.kkkcut.e20j.androidquick.ui.manager;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class QuickAppManager {
    private static final String TAG = "QuickAppManager";
    private static QuickAppManager instance;
    private static List<Activity> mActivities = new LinkedList();

    private QuickAppManager() {
    }

    public static QuickAppManager getInstance() {
        if (instance == null) {
            synchronized (QuickAppManager.class) {
                if (instance == null) {
                    instance = new QuickAppManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        for (int size = mActivities.size() - 1; size > -1; size = mActivities.size() - 1) {
            Activity activity = mActivities.get(size);
            removeActivity(activity);
            activity.finish();
        }
    }

    public synchronized void clearToTop() {
        for (int size = mActivities.size() - 2; size > -1; size = (mActivities.size() - 1) - 1) {
            Activity activity = mActivities.get(size);
            removeActivity(activity);
            activity.finish();
        }
    }
}
