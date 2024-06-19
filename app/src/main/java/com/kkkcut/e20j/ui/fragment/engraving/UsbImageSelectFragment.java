package com.kkkcut.e20j.ui.fragment.engraving;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.UsbImgSelectAdapter;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class UsbImageSelectFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemChildClickListener {
    private static final String LOGO_IMAGE = "LogoImage";

    @BindView(R.id.rv_pics)
    RecyclerView rvPics;
    private File selectFile;
    UsbImgSelectAdapter usbImgSelectAdapter;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_image_select;
    }

    public static UsbImageSelectFragment newInstance() {
        Bundle bundle = new Bundle();
        UsbImageSelectFragment usbImageSelectFragment = new UsbImageSelectFragment();
        usbImageSelectFragment.setArguments(bundle);
        return usbImageSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(1);
        this.rvPics.setLayoutManager(gridLayoutManager);
        UsbImgSelectAdapter usbImgSelectAdapter = new UsbImgSelectAdapter();
        this.usbImgSelectAdapter = usbImgSelectAdapter;
        usbImgSelectAdapter.setOnItemChildClickListener(this);
        this.rvPics.setAdapter(this.usbImgSelectAdapter);
        getPicsFromLocal();
    }

    private void getPicsFromLocal() {
        showLoadingDialog(getString(R.string.waitting));
        addDisposable(Observable.fromCallable(new Callable<List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.3
            @Override // java.util.concurrent.Callable
            public List<File> call() throws Exception {
                return UsbImageSelectFragment.getImageFile(new File(Environment.getExternalStorageDirectory(), UsbImageSelectFragment.LOGO_IMAGE));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<File> list) throws Exception {
                UsbImageSelectFragment.this.usbImgSelectAdapter.setNewData(list);
                UsbImageSelectFragment.this.dismissLoadingDialog();
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UsbImageSelectFragment.this.dismissLoadingDialog();
            }
        }));
    }

    private void getPicsFromUsb(final File file) {
        showLoadingDialog(getString(R.string.waitting));
        addDisposable(Observable.fromCallable(new Callable<List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.8
            @Override // java.util.concurrent.Callable
            public List<File> call() throws Exception {
                return UsbImageSelectFragment.getImageFile(file);
            }
        }).subscribeOn(Schedulers.io()).map(new Function<List<File>, List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.7
            @Override // io.reactivex.functions.Function
            public List<File> apply(List<File> list) throws Exception {
                if (list == null || list.size() == 0) {
                    throw new Exception(UsbImageSelectFragment.this.getString(R.string.no_new_pictures_found));
                }
                for (File file2 : list) {
                    File file3 = new File(Environment.getExternalStorageDirectory(), UsbImageSelectFragment.LOGO_IMAGE);
                    if (!file3.exists()) {
                        file3.mkdir();
                    }
                    FileUtil.copyFile(file2, new File(file3, file2.getName()));
                }
                return list;
            }
        }).map(new Function<List<File>, List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.6
            @Override // io.reactivex.functions.Function
            public List<File> apply(List<File> list) throws Exception {
                return UsbImageSelectFragment.getImageFile(new File(Environment.getExternalStorageDirectory(), UsbImageSelectFragment.LOGO_IMAGE));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<File>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<File> list) throws Exception {
                UsbImageSelectFragment.this.dismissLoadingDialog();
                UsbImageSelectFragment.this.usbImgSelectAdapter.setNewData(list);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.UsbImageSelectFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UsbImageSelectFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(th.getMessage());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<File> getImageFile(File file) {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        for (File file2 : listFiles) {
            if (file2.isFile() && isImageFile(file2.getPath())) {
                arrayList.add(file2);
            } else if (file2.isDirectory()) {
                arrayList.addAll(getImageFile(file2));
            }
        }
        return arrayList;
    }

    public static boolean isImageFile(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outWidth != -1;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int id = view.getId();
        if (id == R.id.iv_delete) {
            File file = (File) baseQuickAdapter.getData().get(i);
            this.selectFile = file;
            file.delete();
            this.usbImgSelectAdapter.remove(i);
            return;
        }
        if (id != R.id.ll_container) {
            return;
        }
        File file2 = (File) baseQuickAdapter.getData().get(i);
        this.selectFile = file2;
        this.usbImgSelectAdapter.addFrame(file2);
    }

    @OnClick({R.id.tv_load_pics_from_usb, R.id.bt_cancle, R.id.bt_confirm})
    public void onViewClicked(View view) {
        FrameActivity frameActivity = (FrameActivity) getActivity();
        int id = view.getId();
        if (id == R.id.bt_cancle) {
            frameActivity.onBack();
            return;
        }
        if (id == R.id.bt_confirm) {
            if (this.selectFile != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("imageFile", this.selectFile);
                setFragmentResult(1, bundle);
                frameActivity.onBack();
                return;
            }
            ToastUtil.showToast(R.string.please_select_a_picture);
            return;
        }
        if (id != R.id.tv_load_pics_from_usb) {
            return;
        }
        File usbPath = getUsbPath();
        if (usbPath == null) {
            Toast.makeText(this._mActivity, getString(R.string.u_disk_is_not_detected), 0).show();
            return;
        }
        File file = new File(usbPath, LOGO_IMAGE);
        if (!file.exists()) {
            Toast.makeText(this._mActivity, getString(R.string.directory_not_found), 0).show();
        } else {
            getPicsFromUsb(file);
        }
    }

    private File getUsbPath() {
        for (File file : new File("/storage").listFiles()) {
            if (file.canRead() && !file.getName().equals(Environment.getExternalStorageDirectory().getName())) {
                return file;
            }
        }
        return null;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.picture_selection);
    }
}
