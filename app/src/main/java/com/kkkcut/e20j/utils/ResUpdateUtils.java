package com.kkkcut.e20j.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.DbBean.KeyImage;
import com.kkkcut.e20j.DbBean.LocalDbVersion;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.GsonHelper;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.bean.gsonBean.ResUpdate;
import com.kkkcut.e20j.dao.ResUpdateDaoManager;
import com.kkkcut.e20j.net.TUitls;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes.dex */
public class ResUpdateUtils {
    public static final String APP = "APP";
    public static final String ERROR_CODE = "ErrorCode";
    public static final String ERROR_CODE_E9 = "ErrorCodeE9";
    public static final String IMAGE_DB = "ImgList";
    public static final String RES = "RESDB";
    private static final String TAG = "ResUpdateUtils";
    private static final String URL_CH = "https://appinterfacecn.kkkcut.cn:4433/SECAppUpgrade/SECAppUpgradeMain.ashx";
    private static final String URL_EN = "https://appinterface.kukai361.com:4433/SECAppUpgrade/SECAppUpgradeMain.ashx";

    /* loaded from: classes.dex */
    public interface DataBaseUpdateListener {
        void error(Throwable th);

        void finish();

        void progress(int i);

        void start();
    }

    public static String getLocalErrorDbName() {
        return ERROR_CODE;
    }

    public static String getLocalMainDbName(String str) {
        return str;
    }

    public static String getLocalResDbName() {
        return RES;
    }

    public static String getLocalMainDbName() {
        return SPUtils.getString(SpKeys.MACHINE_ID);
    }

    public static boolean resDownloadFinished() {
        return localMainDbExist() && localErrorDbExist() && localResDbExist();
    }

    public static boolean localErrorDbExist() {
        return new File(Constant.DATABASE_PATH, getLocalErrorDbName()).exists();
    }

    public static boolean localMainDbExist() {
        File file = new File(Constant.DATABASE_PATH, getLocalMainDbName());
        return file.exists() && file.length() > 3145728;
    }

    public static boolean localResDbExist() {
        return new File(Constant.DATABASE_PATH, getLocalResDbName()).exists();
    }

    public static Disposable checkResUpdate(Context context, final String str, Consumer<UpdateBean> consumer, Consumer<Throwable> consumer2) {
        String str2;
        if (MachineInfo.isE20Us(context)) {
            str2 = "E221108001";
        } else if (MachineInfo.isE20Neutral(context)) {
            str2 = "E221109001";
        } else if (MachineInfo.isE9Standard(context)) {
            str2 = MachineInfo.isChineseMachine() ? "EG23022001" : "EG23021001";
        } else {
            str2 = MachineInfo.isChineseMachine() ? "E221107001" : "E221106001";
        }
        final String versionName = AppUtil.getVersionName(context);
        final String mainDbVersion = ResUpdateDaoManager.getInstance().getMainDbVersion(str);
        final String resDbVersion = ResUpdateDaoManager.getInstance().getResDbVersion();
        final String imgListDbVersion = ResUpdateDaoManager.getInstance().getImgListDbVersion();
        final String errorDbVersion = ResUpdateDaoManager.getInstance().getErrorDbVersion();
        return getUpdateObservable(TUitls.checkResUpdate(GetUUID.getUUID(), new Date().getTime(), SPUtils.getString("series", str2), versionName, mainDbVersion, resDbVersion, errorDbVersion, imgListDbVersion)).doOnNext(new Consumer<ResUpdate>() { // from class: com.kkkcut.e20j.utils.ResUpdateUtils.2
            @Override // io.reactivex.functions.Consumer
            public void accept(ResUpdate resUpdate) throws Exception {
                ResUpdateDaoManager.getInstance().updateLocalResDb(resUpdate, str);
            }
        }).map(new Function<ResUpdate, UpdateBean>() { // from class: com.kkkcut.e20j.utils.ResUpdateUtils.1
            @Override // io.reactivex.functions.Function
            public UpdateBean apply(ResUpdate resUpdate) throws Exception {
                String upgradeLog = resUpdate.getUpgradeLog();
                if (TextUtils.isEmpty(resUpdate.getAndroidAPPVer())) {
                    throw new Exception("No upgrade data available");
                }
                if (Float.parseFloat(resUpdate.getAndroidAPPVer()) > Float.parseFloat(versionName)) {
                    return new UpdateBean(true, upgradeLog);
                }
                List<ResUpdate.DbUpdatelistBean> dbUpdatelist = resUpdate.getDbUpdatelist();
                if (dbUpdatelist == null) {
                    throw new Exception("No upgrade data available");
                }
                for (ResUpdate.DbUpdatelistBean dbUpdatelistBean : dbUpdatelist) {
                    if (TextUtils.equals(dbUpdatelistBean.getDbName(), str) && Float.parseFloat(dbUpdatelistBean.getDbVer()) > Float.parseFloat(mainDbVersion)) {
                        return new UpdateBean(true, upgradeLog);
                    }
                    if (TextUtils.equals(dbUpdatelistBean.getDbName(), ResUpdateUtils.RES) && Float.parseFloat(dbUpdatelistBean.getDbVer()) > Float.parseFloat(resDbVersion)) {
                        return new UpdateBean(true, upgradeLog);
                    }
                    if (TextUtils.equals(dbUpdatelistBean.getDbName(), ResUpdateUtils.ERROR_CODE) && Float.parseFloat(dbUpdatelistBean.getDbVer()) > Float.parseFloat(errorDbVersion)) {
                        return new UpdateBean(true, upgradeLog);
                    }
                }
                if (Float.parseFloat(resUpdate.getKeyImgUpdateList().getVer()) > Float.parseFloat(imgListDbVersion)) {
                    return new UpdateBean(true, upgradeLog);
                }
                return new UpdateBean(false);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResUpdate getResUpdate(String str) throws Exception {
        String decrypt = DesUtil.decrypt(new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(getUrl()).post(new FormBody.Builder().add("T", str).build()).build()).execute().body().string(), DesUtil.SERVER);
        Log.i("TAG", "postSync:" + decrypt);
        return (ResUpdate) GsonHelper.fromJson(decrypt, ResUpdate.class);
    }

    private static String getUrl() {
        MyApplication myApplication = MyApplication.getInstance();
        return (MachineInfo.isE20Us(myApplication) || MachineInfo.isE20Neutral(myApplication) || !MachineInfo.isChineseMachine()) ? URL_EN : URL_CH;
    }

    private static Observable<ResUpdate> getUpdateObservable(final String str) {
        return Observable.fromCallable(new Callable<ResUpdate>() { // from class: com.kkkcut.e20j.utils.ResUpdateUtils.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public ResUpdate call() throws Exception {
                return ResUpdateUtils.getResUpdate(str);
            }
        });
    }

    public static void download(Context context, LocalDbVersion localDbVersion, DataBaseUpdateListener dataBaseUpdateListener, boolean z) {
        if (z) {
            downloadImgList(context, dataBaseUpdateListener);
        } else {
            downloadDb(context, localDbVersion, dataBaseUpdateListener);
        }
    }

    private static void downloadDb(Context context, final LocalDbVersion localDbVersion, final DataBaseUpdateListener dataBaseUpdateListener) {
        String url = localDbVersion.getUrl();
        String[] split = url.split("/");
        String str = split[split.length - 1];
        final String svHash = localDbVersion.getSvHash();
        FileDownloader.setup(context);
        FileDownloader.getImpl().create(url).setPath(Constant.DATABASE_PATH + str).setForceReDownload(true).setListener(new FileDownloadListener() { // from class: com.kkkcut.e20j.utils.ResUpdateUtils.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
                Log.d(ResUpdateUtils.TAG, "pending() called with: task = [" + baseDownloadTask + "], soFarBytes = [" + i + "], totalBytes = [" + i2 + "]");
                DataBaseUpdateListener dataBaseUpdateListener2 = DataBaseUpdateListener.this;
                if (dataBaseUpdateListener2 != null) {
                    dataBaseUpdateListener2.start();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
                Log.d(ResUpdateUtils.TAG, "progress() called with: task = [" + baseDownloadTask + "], soFarBytes = [" + i + "], totalBytes = [" + i2 + "]");
                DataBaseUpdateListener dataBaseUpdateListener2 = DataBaseUpdateListener.this;
                if (dataBaseUpdateListener2 != null) {
                    dataBaseUpdateListener2.progress((i * 100) / i2);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void blockComplete(BaseDownloadTask baseDownloadTask) throws IOException {
                Log.d(ResUpdateUtils.TAG, "blockComplete() called with: task = [" + baseDownloadTask + "]");
                String targetFilePath = baseDownloadTask.getTargetFilePath();
                if (ResUpdateUtils.checkHash(FileSHA256Calculator.getFileSHA256(targetFilePath), svHash)) {
                    new ZipFile(targetFilePath, "Axsw123GtyKK".toCharArray()).extractAll(Constant.DATABASE_PATH);
                    try {
                        Thread.sleep(100L);
                        String mainDbVersion = TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.getLocalMainDbName()) ? ResUpdateDaoManager.getInstance().getMainDbVersion(ResUpdateUtils.getLocalMainDbName()) : null;
                        if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.RES)) {
                            mainDbVersion = ResUpdateDaoManager.getInstance().getResDbVersion();
                        }
                        if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.ERROR_CODE)) {
                            mainDbVersion = ResUpdateDaoManager.getInstance().getErrorDbVersion();
                        }
                        if (TextUtils.isEmpty(mainDbVersion) || "0".equals(mainDbVersion)) {
                            return;
                        }
                        LocalDbVersion localDbVersion2 = localDbVersion;
                        localDbVersion2.setLocResVer(localDbVersion2.getSvResVer());
                        localDbVersion.setLastUpdateTime(new Date().getTime());
                        ResUpdateDaoManager.getInstance().updateSingleLocalDb(localDbVersion);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void completed(BaseDownloadTask baseDownloadTask) {
                DataBaseUpdateListener dataBaseUpdateListener2 = DataBaseUpdateListener.this;
                if (dataBaseUpdateListener2 != null) {
                    dataBaseUpdateListener2.finish();
                }
                Log.d(ResUpdateUtils.TAG, "completed() called with: task = [" + baseDownloadTask + "]");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
                Log.d(ResUpdateUtils.TAG, "paused() called with: task = [" + baseDownloadTask + "], soFarBytes = [" + i + "], totalBytes = [" + i2 + "]");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
                DataBaseUpdateListener dataBaseUpdateListener2 = DataBaseUpdateListener.this;
                if (dataBaseUpdateListener2 != null) {
                    dataBaseUpdateListener2.error(th);
                }
                Log.d(ResUpdateUtils.TAG, "error() called with: task = [" + baseDownloadTask + "], e = [" + th + "]");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            public void warn(BaseDownloadTask baseDownloadTask) {
                Log.d(ResUpdateUtils.TAG, "warn() called with: task = [" + baseDownloadTask + "]");
            }
        }).start();
    }

    public static void downloadImgList(Context context, DataBaseUpdateListener dataBaseUpdateListener) {
        ImageListDownloadListener imageListDownloadListener = new ImageListDownloadListener(context, dataBaseUpdateListener);
        FileDownloader.setup(context);
        FileDownloadQueueSet fileDownloadQueueSet = new FileDownloadQueueSet(imageListDownloadListener);
        ArrayList arrayList = new ArrayList();
        List<KeyImage> imgList = ResUpdateDaoManager.getInstance().getImgList();
        int i = 0;
        for (int i2 = 0; i2 < imgList.size(); i2++) {
            KeyImage keyImage = imgList.get(i2);
            String[] split = keyImage.getUrl().split(";");
            String[] split2 = keyImage.getHashServer().split(";");
            String[] split3 = keyImage.getHashLocal().split(";");
            for (int i3 = 0; i3 < split.length; i3++) {
                if (split3.length < i3 || !TextUtils.equals(split3[i3], split2[i3])) {
                    arrayList.add(FileDownloader.getImpl().create(split[i3]).setTag(new ImgDownloadBean(i, split2[i3], keyImage)));
                    i++;
                }
            }
        }
        imageListDownloadListener.setTaskCount(i);
        fileDownloadQueueSet.disableCallbackProgressTimes();
        fileDownloadQueueSet.setAutoRetryTimes(3);
        fileDownloadQueueSet.downloadSequentially(arrayList);
        fileDownloadQueueSet.setDirectory(new File(context.getFilesDir().getPath(), "keyImg").getPath());
        fileDownloadQueueSet.start();
    }

    public static boolean checkHash(String str, String str2) {
        return TextUtils.equals(str, str2);
    }

    /* loaded from: classes.dex */
    public static class UpdateBean {
        boolean isUpdate;
        String updateLog;

        public UpdateBean(boolean z) {
            this.isUpdate = z;
        }

        public UpdateBean(boolean z, String str) {
            this.isUpdate = z;
            this.updateLog = str;
        }

        public boolean isUpdate() {
            return this.isUpdate;
        }

        public String getUpdateLog() {
            return this.updateLog;
        }
    }

    /* loaded from: classes.dex */
    public static class ImageListDownloadListener extends FileDownloadListener {
        private Context context;
        private DataBaseUpdateListener listener;
        private int taskCount;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void warn(BaseDownloadTask baseDownloadTask) {
        }

        public ImageListDownloadListener(Context context, DataBaseUpdateListener dataBaseUpdateListener) {
            this.listener = dataBaseUpdateListener;
            this.context = context;
        }

        public void setTaskCount(int i) {
            this.taskCount = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
            DataBaseUpdateListener dataBaseUpdateListener;
            if (((ImgDownloadBean) baseDownloadTask.getTag()).getIndex() != 0 || (dataBaseUpdateListener = this.listener) == null) {
                return;
            }
            dataBaseUpdateListener.start();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void completed(BaseDownloadTask baseDownloadTask) {
            ImgDownloadBean imgDownloadBean = (ImgDownloadBean) baseDownloadTask.getTag();
            if (this.listener != null) {
                if (imgDownloadBean.getIndex() == this.taskCount - 1) {
                    this.listener.finish();
                } else {
                    this.listener.progress(((imgDownloadBean.getIndex() + 1) * 100) / this.taskCount);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
            ImgDownloadBean imgDownloadBean = (ImgDownloadBean) baseDownloadTask.getTag();
            String svsHash = imgDownloadBean.getSvsHash();
            String fileSHA256 = FileSHA256Calculator.getFileSHA256(baseDownloadTask.getTargetFilePath());
            if (ResUpdateUtils.checkHash(fileSHA256, svsHash)) {
                KeyImage keyImage = imgDownloadBean.getKeyImage();
                if (TextUtils.equals(keyImage.getHashServer().split(";")[r2.length - 1], svsHash)) {
                    keyImage.setHashLocal(keyImage.getHashServer());
                    ResUpdateDaoManager.getInstance().updateSingleImg(keyImage);
                }
            } else {
                Log.i(ResUpdateUtils.TAG, "svsHash: " + svsHash + "    fileSHA256:" + fileSHA256);
            }
            if (imgDownloadBean.getIndex() == this.taskCount - 1) {
                ResUpdateDaoManager.getInstance().updateImgDbLocal();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadListener
        public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
            DataBaseUpdateListener dataBaseUpdateListener;
            Log.d(ResUpdateUtils.TAG, "error() called with: baseDownloadTask = [" + baseDownloadTask.getUrl() + "], throwable = [" + th.getMessage() + "]");
            if (((ImgDownloadBean) baseDownloadTask.getTag()).getIndex() != this.taskCount - 1 || (dataBaseUpdateListener = this.listener) == null) {
                return;
            }
            dataBaseUpdateListener.error(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ImgDownloadBean {
        private int index;
        KeyImage keyImage;
        private String svsHash;

        public ImgDownloadBean(int i, String str, KeyImage keyImage) {
            this.index = i;
            this.svsHash = str;
            this.keyImage = keyImage;
        }

        public int getIndex() {
            return this.index;
        }

        public String getSvsHash() {
            return this.svsHash;
        }

        public KeyImage getKeyImage() {
            return this.keyImage;
        }
    }

    public static void showKeyImage(final Context context, final int i, ImageView imageView) {
        File file = new File(context.getFilesDir(), "keyImg/" + i + ".png");
        if (!file.exists()) {
            file = null;
        }
        File file2 = new File(context.getFilesDir(), "keyImg/" + i + ".jpg");
        if (file2.exists()) {
            file = file2;
        }
        final KeyImage imgDb = ResUpdateDaoManager.getInstance().getImgDb(i);
        if (file != null && TextUtils.equals(FileSHA256Calculator.getFileSHA256(file.getPath()), imgDb.getHashServer())) {
            Glide.with(context).load(file).into(imageView);
            return;
        }
        String url = imgDb.getUrl();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(context).load(url.split(";")[0]).addListener(new RequestListener<Drawable>() { // from class: com.kkkcut.e20j.utils.ResUpdateUtils.5
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str;
                Bitmap.CompressFormat compressFormat;
                File file3 = new File(context.getFilesDir(), "keyImg/");
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                if (imgDb.getUrl().toLowerCase().contains(".png")) {
                    str = file3.getPath() + "/" + i + ".png";
                    compressFormat = Bitmap.CompressFormat.PNG;
                } else {
                    str = file3.getPath() + "/" + i + ".jpg";
                    compressFormat = Bitmap.CompressFormat.JPEG;
                }
                ResUpdateUtils.drawableToFile(drawable, str, compressFormat);
                KeyImage keyImage = imgDb;
                keyImage.setHashLocal(keyImage.getHashServer());
                ResUpdateDaoManager.getInstance().updateSingleImg(imgDb);
                return false;
            }
        }).into(imageView);
    }

    public static void drawableToFile(Drawable drawable, String str, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ((BitmapDrawable) drawable).getBitmap().compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
