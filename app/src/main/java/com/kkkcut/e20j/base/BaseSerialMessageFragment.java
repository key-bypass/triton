package com.kkkcut.e20j.base;

import android.os.Bundle;
import android.util.Log;
import com.kkkcut.e20j.driver.communication.OnSerialMessageComingListener;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;

/* loaded from: classes.dex */
public abstract class BaseSerialMessageFragment extends BaseBackFragment implements OnSerialMessageComingListener {
    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Log.i(TAG, "onDestroy: adfsdf");
        super.onDestroy();
    }
}
