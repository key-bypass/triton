package com.kkkcut.e20j.androidquick.ui.viewstatus;

import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
public interface IVaryViewHelper {
    Context getContext();

    View getCurrentLayout();

    View getView();

    View inflate(int i);

    void restoreView();

    void showLayout(View view);
}
