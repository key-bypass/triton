package com.kkkcut.e20j.androidquick.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.cutting.machine.DialogBtnCallBack;
import com.cutting.machine.error.ErrorBean;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.androidquick.ui.viewstatus.VaryViewHelperController;
import com.kkkcut.e20j.ui.dialog.ErrorDialog;
import com.kkkcut.e20j.ui.dialog.LoadingDialog;
import com.kkkcut.e20j.us.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;


/* loaded from: classes.dex */
public abstract class QuickFragment extends Fragment {
    protected static String TAG = "QuickFragment";
    private ErrorDialog errorDialog;
    private boolean isPrepared;
    private LoadingDialog loadingDialog;
    public View mainLayout;
    protected Context mContext = null;
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;
    private boolean isFirstResume = false;
    private boolean isFirstVisible = false;
    private boolean isFirstInvisible = false;
    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
    private VaryViewHelperController mVaryViewHelperController = null;

    private void onFirstUserInvisible() {
    }

    protected abstract int getContentViewLayoutID();

    protected abstract View getLoadingTargetView();

    protected abstract void initViewsAndEvents();

    protected abstract void onEventComing(EventCenter<?> eventCenter);

    protected abstract void onFirstUserVisible();

    protected abstract void onUserInvisible();

    protected abstract void onUserVisible();

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int contentViewLayoutID = getContentViewLayoutID();
        if (contentViewLayoutID != 0) {
            View inflate = layoutInflater.inflate(contentViewLayoutID, viewGroup, false);
            this.mainLayout = inflate;
            inflate.setClickable(true);
            return this.mainLayout;
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getLoadingTargetView() != null) {
            this.mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenDensity = displayMetrics.density;
        this.mScreenHeight = displayMetrics.heightPixels;
        this.mScreenWidth = displayMetrics.widthPixels;
        initViewsAndEvents();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        clearDisposable();
        EventBus.getDefault().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initPrepare();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isFirstResume) {
            this.isFirstResume = false;
            onFirstUserVisible();
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        Log.i(TAG, "setUserVisibleHint: " + z);
        if (z) {
            if (this.isFirstVisible) {
                this.isFirstVisible = false;
                initPrepare();
                return;
            } else {
                onUserVisible();
                return;
            }
        }
        if (this.isFirstInvisible) {
            this.isFirstInvisible = false;
            onFirstUserInvisible();
        } else {
            onUserInvisible();
        }
    }

    private synchronized void initPrepare() {
        if (this.isPrepared) {
            onFirstUserVisible();
        } else {
            this.isPrepared = true;
        }
    }

    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    protected void readyGo(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoForResult(Class<?> cls, int i) {
        startActivityForResult(new Intent(getActivity(), cls), i);
    }

    protected void readyGoForResult(Class<?> cls, int i, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventCenter eventCenter) {
        if (eventCenter != null) {
            onEventComing(eventCenter);
        }
    }

    public void performCodeWithPermission(int i, int i2, String[] strArr, QuickActivity.PermissionCallback permissionCallback) {
        if (getActivity() == null || !(getActivity() instanceof QuickActivity)) {
            return;
        }
        ((QuickActivity) getActivity()).performCodeWithPermission(i, i2, strArr, permissionCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearDisposable() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingDialog(String str, boolean z) {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(getContext());
        }
        this.loadingDialog.showStop(z);
        if (!StringUtil.isEmpty(str)) {
            this.loadingDialog.setTip(str);
        }
        if (this.loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingDialog(String str) {
        showLoadingDialog(str, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dismissLoadingDialog() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }

    public void showErrorDialog(Context context, ErrorBean errorBean) {
        String str;
        int code = errorBean.getCode();
        if (code == -5) {
            return;
        }
        if (code < 0) {
            str = errorBean.getMsg();
        } else {
            str = errorBean.getMsg() + "{" + code + "}";
        }
        if (code == 604) {
            errorBean.setImgRes(R.drawable.error_7);
        } else {
            errorBean.setImgRes(R.drawable.error_1);
        }
        ErrorDialog errorDialog = new ErrorDialog(context);
        errorDialog.show();
        errorDialog.setTip(str);
        errorDialog.setRemindImg(errorBean.getImgRes());
        errorDialog.setDialogBtnCallback(errorBean.getDialogBtnCallBack());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showErrorDialog(String str, int i, DialogBtnCallBack dialogBtnCallBack) {
        if (this.errorDialog == null) {
            this.errorDialog = new ErrorDialog(getContext());
        }
        if (!StringUtil.isEmpty(str)) {
            this.errorDialog.setTip(str);
        }
        this.errorDialog.setDialogBtnCallback(dialogBtnCallBack);
        this.errorDialog.setRemindImg(i);
        if (this.errorDialog.isShowing()) {
            return;
        }
        this.errorDialog.show();
    }
}
