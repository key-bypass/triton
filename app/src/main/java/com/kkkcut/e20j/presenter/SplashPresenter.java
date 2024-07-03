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
import com.cutting.machine.CoreConstant;
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

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        this.subscribe = Observable.zip(getAssetsVersionObservable(), getLocalVersionObservable(), new BiFunction<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer num, Integer num2) throws Exception {
                Boolean valueOf;
                valueOf = Boolean.valueOf(num.intValue() > num2.intValue());
                return valueOf;
            }
        }).subscribeOn(Schedulers.io()).map(update -> {
            Log.i(TAG, "updateDb: " + update);
            if (update) {
                return Boolean.valueOf(FileUtil.copyAssetToSDCard(this.context.getAssets(), Constant.ZIP_NAME_MAIN_DB, Constant.DATABASE_ZIP_PATH));
            }
            return false;
        }).map(b -> {
            if (b.booleanValue()) {
                return ZipUtils.unzipFile(Constant.DATABASE_ZIP_PATH, Constant.DATABASE_PATH);
            }
            return null;
        }).map(list -> {
            if (list != null && list.size() > 0) {
                return Boolean.valueOf(FileUtil.copyAssetToSDCard(this.context.getAssets(), Constant.JSON_MAIN_DB_UPDATE, Constant.DATABASE_ZIP_PATH));
            }
            return false;
        }).doFinally(() -> {
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
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(bool -> {
                if (bool.booleanValue() && file2.exists()) {
                    file2.delete();
                }
            }).subscribe();
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> gomain(currentThreadTimeMillis), th -> SplashPresenter.this.gomain(currentThreadTimeMillis));
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

    private Observable<Integer> getAssetsVersionObservable() {
        return Observable.fromCallable(() -> {
            return Integer.valueOf(AssetVersionUtil.getAssetsDbVersion(SplashPresenter.this.context.getAssets(), Constant.JSON_MAIN_DB_UPDATE));
        });
    }

    private Observable<Integer> getLocalVersionObservable() {
        return Observable.fromCallable(() -> {
            return Integer.valueOf(AssetVersionUtil.getLocalDbVersion(new File(SplashPresenter.this.context.getFilesDir(), Constant.JSON_MAIN_DB_UPDATE)));
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
