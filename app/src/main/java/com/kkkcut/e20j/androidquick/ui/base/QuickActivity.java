package com.kkkcut.e20j.androidquick.ui.base;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.kkkcut.e20j.androidquick.autolayout.AutoLayoutActivity;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.androidquick.ui.manager.QuickAppManager;
import com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions;
import com.kkkcut.e20j.androidquick.ui.viewstatus.VaryViewHelperController;
import com.kkkcut.e20j.us.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public abstract class QuickActivity extends AutoLayoutActivity implements EasyPermissions.PermissionWithDialogCallbacks {
    protected static String TAG = "QuickActivity";
    private LinearLayout contentView;
    private boolean flag;
    private Unbinder mUnbinder;
    private View mainView;
    protected Toolbar toolbar;
    protected FrameLayout toolbarLayout;
    protected TextView tvRight;
    protected TextView tvTitle;
    protected Context mContext = null;
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;
    private VaryViewHelperController mVaryViewHelperController = null;
    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
    private Map<Integer, PermissionCallback> mPermissonCallbacks = null;
    private Map<Integer, String[]> mPermissions = null;

    /* loaded from: classes.dex */
    public interface PermissionCallback {
        void hasPermission(List<String> list);

        void noPermission(List<String> list, List<String> list2, Boolean bool);

        void showDialog(int i, EasyPermissions.DialogCallback dialogCallback);
    }

    /* loaded from: classes.dex */
    public enum TransitionMode {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        SCALE,
        FADE
    }

    protected abstract void getBundleExtras(Bundle bundle);

    protected View getContentView(int i, LinearLayout linearLayout) {
        return null;
    }

    protected abstract int getContentViewLayoutID();

    protected abstract View getLoadingTargetView();

    protected abstract TransitionMode getOverridePendingTransitionMode();

    protected void handleSavedInstanceState(Bundle bundle) {
    }

    protected abstract void initViewsAndEvents();

    protected abstract boolean isApplySystemBarTint();

    protected abstract boolean isLoadDefaultTitleBar();

    protected abstract void onEventComing(EventCenter eventCenter);

    protected abstract boolean toggleOverridePendingTransition();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            handleSavedInstanceState(bundle);
        }
        setRequestedOrientation(6);
        isApplySystemBarTint();
        this.mContext = this;
        QuickAppManager.getInstance().addActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (AnonymousClass3.$SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[getOverridePendingTransitionMode().ordinal()]) {
                case 1:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case 2:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case 3:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case 4:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case 5:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case 6:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getBundleExtras(extras);
        }
        EventBus.getDefault().register(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenDensity = displayMetrics.density;
        this.mScreenHeight = displayMetrics.heightPixels;
        this.mScreenWidth = displayMetrics.widthPixels;
        int contentViewLayoutID = getContentViewLayoutID();
        if (contentViewLayoutID != 0) {
            setContentView(contentViewLayoutID);
            initViewsAndEvents();
            if (Build.VERSION.SDK_INT >= 21) {
                setEnterSharedElementCallback(new SharedElementCallback() { // from class: com.kkkcut.e20j.androidquick.ui.base.QuickActivity.1
                    @Override // android.app.SharedElementCallback
                    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
                        super.onSharedElementStart(list, list2, list3);
                        Log.d(QuickActivity.TAG, "onSharedElementStart0() called with: sharedElementNames = [" + list + "], sharedElements = [" + list2 + "], sharedElementSnapshots = [" + list3 + "]");
                    }

                    @Override // android.app.SharedElementCallback
                    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
                        super.onSharedElementEnd(list, list2, list3);
                        Log.d(QuickActivity.TAG, "onSharedElementEnd0() called with: sharedElementNames = [" + list + "], sharedElements = [" + list2 + "], sharedElementSnapshots = [" + list3 + "]");
                    }
                });
            }
            if (Build.VERSION.SDK_INT >= 21) {
                setExitSharedElementCallback(new SharedElementCallback() { // from class: com.kkkcut.e20j.androidquick.ui.base.QuickActivity.2
                    @Override // android.app.SharedElementCallback
                    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
                        super.onSharedElementStart(list, list2, list3);
                        Log.d(QuickActivity.TAG, "onSharedElementStart1() called with: sharedElementNames = [" + list + "], sharedElements = [" + list2 + "], sharedElementSnapshots = [" + list3 + "]");
                    }

                    @Override // android.app.SharedElementCallback
                    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
                        super.onSharedElementEnd(list, list2, list3);
                        list2.clear();
                        Log.d(QuickActivity.TAG, "onSharedElementEnd1() called with: sharedElementNames = [" + list + "], sharedElements = [" + list2 + "], sharedElementSnapshots = [" + list3 + "]");
                    }
                });
                return;
            }
            return;
        }
        throw new IllegalArgumentException("You must return a right contentView layout resource Id");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.androidquick.ui.base.QuickActivity$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode;

        static {
            int[] iArr = new int[TransitionMode.values().length];
            $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode = iArr;
            try {
                iArr[TransitionMode.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[TransitionMode.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[TransitionMode.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[TransitionMode.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[TransitionMode.SCALE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[TransitionMode.FADE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        if (isLoadDefaultTitleBar()) {
            initContentView();
            this.contentView.addView(this.toolbarLayout, new ViewGroup.LayoutParams(-1, -2));
            View contentView = getContentView(i, this.contentView);
            if (contentView == null) {
                LayoutInflater.from(this).inflate(i, (ViewGroup) this.contentView, true);
                super.setContentView(this.contentView, new ViewGroup.LayoutParams(-1, -1));
            } else {
                super.setContentView(contentView, new ViewGroup.LayoutParams(-1, -1));
            }
        } else {
            super.setContentView(i);
        }
        this.mUnbinder = ButterKnife.bind(this);
        if (getLoadingTargetView() != null) {
            this.mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }

    protected void initContentView() {
        LinearLayout linearLayout = new LinearLayout(this);
        this.contentView = linearLayout;
        linearLayout.setOrientation(1);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (toggleOverridePendingTransition()) {
            switch (AnonymousClass3.$SwitchMap$com$kkkcut$e20j$androidquick$ui$base$QuickActivity$TransitionMode[getOverridePendingTransitionMode().ordinal()]) {
                case 1:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    return;
                case 2:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    return;
                case 3:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    return;
                case 4:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    return;
                case 5:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    return;
                case 6:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        QuickAppManager.getInstance().removeActivity(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent getGoIntent(Class<?> cls) {
        return new Intent(this, cls);
    }

    protected void readyGo(Class<?> cls) {
        startActivity(getGoIntent(cls));
    }

    protected void readyGo(Class<?> cls, int i) {
        Intent goIntent = getGoIntent(cls);
        goIntent.setFlags(i);
        startActivity(goIntent);
    }

    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent goIntent = getGoIntent(cls);
        if (bundle != null) {
            goIntent.putExtras(bundle);
        }
        startActivity(goIntent);
    }

    protected void readyGo(Class<?> cls, Bundle bundle, int i) {
        Intent goIntent = getGoIntent(cls);
        if (bundle != null) {
            goIntent.putExtras(bundle);
        }
        goIntent.setFlags(i);
        startActivity(goIntent);
    }

    protected void readyGoThenKill(Class<?> cls) {
        startActivity(getGoIntent(cls));
        finish();
    }

    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        Intent goIntent = getGoIntent(cls);
        if (bundle != null) {
            goIntent.putExtras(bundle);
        }
        startActivity(goIntent);
        finish();
    }

    protected void readyGoForResult(Class<?> cls, int i) {
        startActivityForResult(getGoIntent(cls), i);
    }

    protected void readyGoForResult(Class<?> cls, int i, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, i);
    }

    protected void toggleShowLoading(boolean z, String str) {
        VaryViewHelperController varyViewHelperController = this.mVaryViewHelperController;
        if (varyViewHelperController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (z) {
            varyViewHelperController.showLoading(str);
        } else {
            varyViewHelperController.restore();
        }
    }

    protected void toggleShowEmpty(boolean z, String str, View.OnClickListener onClickListener) {
        VaryViewHelperController varyViewHelperController = this.mVaryViewHelperController;
        if (varyViewHelperController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (z) {
            varyViewHelperController.showEmpty(str, onClickListener);
        } else {
            varyViewHelperController.restore();
        }
    }

    protected void toggleShowError(boolean z, String str, View.OnClickListener onClickListener) {
        VaryViewHelperController varyViewHelperController = this.mVaryViewHelperController;
        if (varyViewHelperController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (z) {
            varyViewHelperController.showError(str, onClickListener);
        } else {
            varyViewHelperController.restore();
        }
    }

    protected void toggleNetworkError(boolean z, View.OnClickListener onClickListener) {
        VaryViewHelperController varyViewHelperController = this.mVaryViewHelperController;
        if (varyViewHelperController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (z) {
            varyViewHelperController.showNetworkError(onClickListener);
        } else {
            varyViewHelperController.restore();
        }
    }

    protected void toggleRestore() {
        VaryViewHelperController varyViewHelperController = this.mVaryViewHelperController;
        if (varyViewHelperController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        varyViewHelperController.restore();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventCenter eventCenter) {
        if (eventCenter != null) {
            onEventComing(eventCenter);
        }
    }

    public void performCodeWithPermission(int i, int i2, String[] strArr, PermissionCallback permissionCallback) {
        String[] findNoPermission = EasyPermissions.findNoPermission(this, strArr);
        if (findNoPermission.length == 0) {
            permissionCallback.hasPermission(Arrays.asList(findNoPermission));
            return;
        }
        if (this.mPermissonCallbacks == null) {
            this.mPermissonCallbacks = new HashMap();
        }
        this.mPermissonCallbacks.put(Integer.valueOf(i2), permissionCallback);
        if (this.mPermissions == null) {
            this.mPermissions = new HashMap();
        }
        this.mPermissions.put(Integer.valueOf(i2), findNoPermission);
        EasyPermissions.requestPermissions(this, i, i2, findNoPermission);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(int i, List<String> list) {
        Map<Integer, String[]> map;
        Map<Integer, PermissionCallback> map2 = this.mPermissonCallbacks;
        if (map2 != null && map2.containsKey(Integer.valueOf(i)) && (map = this.mPermissions) != null && map.containsKey(Integer.valueOf(i)) && this.mPermissions.get(Integer.valueOf(i)).length == list.size()) {
            this.mPermissonCallbacks.get(Integer.valueOf(i)).hasPermission(Arrays.asList(this.mPermissions.get(Integer.valueOf(i))));
        }
    }

    @Override // com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(int i, List<String> list) {
        Map<Integer, String[]> map;
        Map<Integer, PermissionCallback> map2 = this.mPermissonCallbacks;
        if (map2 == null || !map2.containsKey(Integer.valueOf(i)) || (map = this.mPermissions) == null || !map.containsKey(Integer.valueOf(i))) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.mPermissions.get(Integer.valueOf(i))) {
            if (!list.contains(str)) {
                arrayList.add(str);
            }
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            this.mPermissonCallbacks.get(Integer.valueOf(i)).noPermission(list, arrayList, true);
        } else {
            this.mPermissonCallbacks.get(Integer.valueOf(i)).noPermission(list, arrayList, false);
        }
    }

    @Override // com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.PermissionWithDialogCallbacks
    public void onDialog(int i, int i2, EasyPermissions.DialogCallback dialogCallback) {
        Map<Integer, PermissionCallback> map = this.mPermissonCallbacks;
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            return;
        }
        this.mPermissonCallbacks.get(Integer.valueOf(i)).showDialog(i2, dialogCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    protected void clear() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }
}
