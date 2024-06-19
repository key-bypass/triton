package com.kkkcut.e20j.presenter;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.ZipUtils;
import com.kkkcut.e20j.view.SplashView;
import com.liying.core.CoreConstant;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SplashPresenter {
    private static final String TAG = "SplashPresenter";
    private Context context;
    private SplashView splashView;
    private Disposable subscribe;

    public SplashPresenter(Context context, SplashView splashView) {
        this.splashView = splashView;
        this.context = context;
        checkDbUpdate();
    }

    private void checkDbUpdate() {
        final long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        this.subscribe = Observable.zip(getAssetsVersionObservable(), getLocalVersionObservable(), new BiFunction() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(r0.intValue() > r1.intValue());
                return valueOf;
            }
        }).subscribeOn(Schedulers.io()).map(new Function() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return SplashPresenter.this.m21lambda$checkDbUpdate$1$comkkkcute20jpresenterSplashPresenter((Boolean) obj);
            }
        }).map(new Function() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return SplashPresenter.lambda$checkDbUpdate$2((Boolean) obj);
            }
        }).map(new Function() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return SplashPresenter.this.m22lambda$checkDbUpdate$3$comkkkcute20jpresenterSplashPresenter((List) obj);
            }
        }).doFinally(new Action() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Action
            public final void run() {
                SplashPresenter.this.m23lambda$checkDbUpdate$4$comkkkcute20jpresenterSplashPresenter();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.presenter.SplashPresenter$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SplashPresenter.this.m24lambda$checkDbUpdate$5$comkkkcute20jpresenterSplashPresenter(currentThreadTimeMillis, (Boolean) obj);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.presenter.SplashPresenter.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                SplashPresenter.this.gomain(currentThreadTimeMillis);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkDbUpdate$1$com-kkkcut-e20j-presenter-SplashPresenter, reason: not valid java name */
    public /* synthetic */ Boolean m21lambda$checkDbUpdate$1$comkkkcute20jpresenterSplashPresenter(Boolean bool) throws Exception {
        Log.i(TAG, "updateDb: " + bool);
        if (bool.booleanValue()) {
            return Boolean.valueOf(FileUtil.copyAssetToSDCard(this.context.getAssets(), Constant.ZIP_NAME_MAIN_DB, Constant.DATABASE_ZIP_PATH));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$checkDbUpdate$2(Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return ZipUtils.unzipFile(Constant.DATABASE_ZIP_PATH, Constant.DATABASE_PATH);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkDbUpdate$3$com-kkkcut-e20j-presenter-SplashPresenter, reason: not valid java name */
    public /* synthetic */ Boolean m22lambda$checkDbUpdate$3$comkkkcute20jpresenterSplashPresenter(List list) throws Exception {
        if (list != null && list.size() > 0) {
            return Boolean.valueOf(FileUtil.copyAssetToSDCard(this.context.getAssets(), Constant.JSON_MAIN_DB_UPDATE, Constant.DATABASE_ZIP_PATH));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkDbUpdate$5$com-kkkcut-e20j-presenter-SplashPresenter, reason: not valid java name */
    public /* synthetic */ void m24lambda$checkDbUpdate$5$comkkkcute20jpresenterSplashPresenter(long j, Boolean bool) throws Exception {
        gomain(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deleteLogAndApkFile, reason: merged with bridge method [inline-methods] */
    public void m23lambda$checkDbUpdate$4$comkkkcute20jpresenterSplashPresenter() {
        File[] listFiles = new File(Environment.getExternalStorageDirectory(), "Android/data/com.estrongs.android.pop/tmp").listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.getName().endsWith(".apk")) {
                    file.delete();
                }
            }
        }
        final File file2 = new File(this.context.getExternalFilesDir(""), CoreConstant.SERIAL_FILE_NAME);
        Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.kkkcut.e20j.presenter.SplashPresenter.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    try {
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        try {
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            try {
                                String readLine = bufferedReader.readLine();
                                if (TextUtils.isEmpty(readLine)) {
                                    observableEmitter.onNext(false);
                                } else if (readLine.length() < 23) {
                                    observableEmitter.onNext(true);
                                } else {
                                    try {
                                        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).parse(readLine.substring(0, 23));
                                        parse.getTime();
                                        if (new Date().getTime() - parse.getTime() > 172800000) {
                                            observableEmitter.onNext(true);
                                        }
                                    } catch (Exception unused) {
                                        observableEmitter.onNext(true);
                                    }
                                }
                                observableEmitter.onComplete();
                                bufferedReader.close();
                                inputStreamReader.close();
                                fileInputStream.close();
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.presenter.SplashPresenter.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue() && file2.exists()) {
                    file2.delete();
                }
            }
        }).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gomain(long j) {
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis() - j;
        if (currentThreadTimeMillis > 2000) {
            this.splashView.goMain();
        } else {
            this.splashView.goMainDelay(2000 - currentThreadTimeMillis);
        }
    }

    private Observable getAssetsVersionObservable() {
        return Observable.fromCallable(new Callable<Integer>() { // from class: com.kkkcut.e20j.presenter.SplashPresenter.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(AssetVersionUtil.getAssetsDbVersion(SplashPresenter.this.context.getAssets(), Constant.JSON_MAIN_DB_UPDATE));
            }
        });
    }

    private Observable getLocalVersionObservable() {
        return Observable.fromCallable(new Callable<Integer>() { // from class: com.kkkcut.e20j.presenter.SplashPresenter.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(AssetVersionUtil.getLocalDbVersion(new File(SplashPresenter.this.context.getFilesDir(), Constant.JSON_MAIN_DB_UPDATE)));
            }
        });
    }

    public void onDetach() {
        Disposable disposable = this.subscribe;
        if (disposable != null && !disposable.isDisposed()) {
            this.subscribe.dispose();
        }
        this.splashView = null;
    }
}
