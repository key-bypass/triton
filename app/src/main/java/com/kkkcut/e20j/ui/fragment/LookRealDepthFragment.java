package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.bean.Bitt;
import com.kkkcut.e20j.bean.gsonBean.UploadTestData;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class LookRealDepthFragment extends BaseBackFragment {
    private static final String DEPTH_NAME = "depthName";

    @BindView(R.id.colum)
    LinearLayout colum;

    @BindView(R.id.difference)
    LinearLayout difference;

    @BindView(R.id.depth_name)
    LinearLayout llDepthName;

    @BindView(R.id.real_depth)
    LinearLayout llRealDepth;

    @BindView(R.id.row)
    LinearLayout row;

    @BindView(R.id.standard_depth)
    LinearLayout standardDepth;

    @BindView(R.id.tv_depth)
    TextView tvDepth;

    @BindView(R.id.tv_depth_name)
    TextView tvDepthName;

    @BindView(R.id.tv_diff)
    TextView tvDiff;

    @BindView(R.id.tv_max)
    TextView tvMax;

    @BindView(R.id.tv_min)
    TextView tvMin;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String realDepthName = "";
    private String realDepth = "";
    private String uuid = "";
    private List<Bitt> bittA = new ArrayList();
    private List<Bitt> bittB = new ArrayList();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_look_real_depth;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    static /* synthetic */ String access$184(LookRealDepthFragment lookRealDepthFragment, Object obj) {
        String str = lookRealDepthFragment.realDepthName + obj;
        lookRealDepthFragment.realDepthName = str;
        return str;
    }

    static /* synthetic */ String access$284(LookRealDepthFragment lookRealDepthFragment, Object obj) {
        String str = lookRealDepthFragment.realDepth + obj;
        lookRealDepthFragment.realDepth = str;
        return str;
    }

    public static LookRealDepthFragment newInstance(int i, ArrayList<Bitt> arrayList, String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Bitts", arrayList);
        bundle.putInt("keyid", i);
        bundle.putString("depth", str2);
        bundle.putString("title", str);
        bundle.putString(DEPTH_NAME, str3);
        bundle.putString("bitNum", str4);
        LookRealDepthFragment lookRealDepthFragment = new LookRealDepthFragment();
        lookRealDepthFragment.setArguments(bundle);
        return lookRealDepthFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        String[] split = getArguments().getString("depth").split(";");
        this.tvDepth.setText("标准齿深：" + split[0]);
        final String string = getArguments().getString(DEPTH_NAME);
        this.tvDepthName.setText("标准齿深代号：" + string.split(";")[0]);
        this.tvTitle.setText(getArguments().getString("title"));
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("Bitts");
        if (parcelableArrayList == null || parcelableArrayList.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            Bitt bitt = (Bitt) it.next();
            this.row.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            this.row.addView(getText(bitt.getRow()), new LinearLayout.LayoutParams(-1, 30));
            this.colum.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            this.colum.addView(getText(bitt.getColoum()), new LinearLayout.LayoutParams(-1, 30));
            this.llDepthName.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            this.llDepthName.addView(getText(bitt.getDepthName()), new LinearLayout.LayoutParams(-1, 30));
            this.llRealDepth.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            this.llRealDepth.addView(getText(String.valueOf(Math.round(Float.parseFloat(bitt.getRealDepth()) * 10.0f) / 10.0f)), new LinearLayout.LayoutParams(-1, 30));
            String[] split2 = split[Integer.parseInt(bitt.getRow()) - 1].split(",");
            float f = 2.1474836E9f;
            int i = 0;
            for (int i2 = 0; i2 < split2.length; i2++) {
                float round = Math.round(Math.abs(r12 - Integer.parseInt(split2[i2])) * 10.0f) / 10.0f;
                if (round < f) {
                    f = round;
                    i = i2;
                }
            }
            arrayList.add(Float.valueOf(f));
            this.standardDepth.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            this.standardDepth.addView(getText(split2[i]), new LinearLayout.LayoutParams(-1, 30));
            this.difference.addView(getDevide(), new LinearLayout.LayoutParams(-1, 1));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 30);
            TextView text = getText(String.valueOf(f));
            if (Math.abs(f) > 5.0f && Math.abs(f) < 8.0f) {
                text.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            }
            if (Math.abs(f) >= 8.0f) {
                text.setBackgroundColor(SupportMenu.CATEGORY_MASK);
            }
            this.difference.addView(text, layoutParams);
            if ("1".equals(bitt.getRow())) {
                this.bittA.add(bitt);
            } else {
                this.bittB.add(bitt);
            }
        }
        Collections.sort(arrayList);
        float floatValue = ((Float) arrayList.get(arrayList.size() - 1)).floatValue();
        this.tvMax.setText(String.valueOf(floatValue));
        float floatValue2 = ((Float) arrayList.get(0)).floatValue();
        this.tvMin.setText(String.valueOf(floatValue2));
        this.tvDiff.setText(String.valueOf(floatValue - floatValue2));
        if (this.bittA.size() != 0) {
            addDisposable(Observable.fromIterable(this.bittA).sorted(new Comparator<Bitt>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.2
                @Override // java.util.Comparator
                public int compare(Bitt bitt2, Bitt bitt3) {
                    return Integer.parseInt(bitt2.getColoum()) - Integer.parseInt(bitt3.getColoum());
                }
            }).subscribe(new Consumer<Bitt>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Bitt bitt2) throws Exception {
                    Log.i(LookRealDepthFragment.TAG, "bittA: " + bitt2.toString());
                    String depthName = bitt2.getDepthName();
                    if (depthName.length() > 4) {
                        depthName = depthName.substring(0, 4);
                    }
                    if (!string.contains("0") || string.contains("10")) {
                        depthName = depthName.replace("?", "0");
                    }
                    LookRealDepthFragment.access$184(LookRealDepthFragment.this, depthName + ",");
                    LookRealDepthFragment.access$284(LookRealDepthFragment.this, bitt2.getRealDepth() + ",");
                }
            }));
        }
        if (!TextUtils.isEmpty(this.realDepthName)) {
            String str = this.realDepthName;
            this.realDepthName = str.substring(0, str.length() - 1);
            this.realDepthName += ";";
        }
        if (!TextUtils.isEmpty(this.realDepth)) {
            String str2 = this.realDepth;
            this.realDepth = str2.substring(0, str2.length() - 1);
            this.realDepth += ";";
        }
        if (this.bittB.size() != 0) {
            addDisposable(Observable.fromIterable(this.bittB).sorted(new Comparator<Bitt>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.4
                @Override // java.util.Comparator
                public int compare(Bitt bitt2, Bitt bitt3) {
                    return Integer.parseInt(bitt2.getColoum()) - Integer.parseInt(bitt3.getColoum());
                }
            }).subscribe(new Consumer<Bitt>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Bitt bitt2) throws Exception {
                    Log.i(LookRealDepthFragment.TAG, "bittB: " + bitt2.toString());
                    String depthName = bitt2.getDepthName();
                    if (depthName.length() > 4) {
                        depthName = depthName.substring(0, 4);
                    }
                    if (!string.contains("0") || string.contains("10")) {
                        depthName = depthName.replace("?", "0");
                    }
                    LookRealDepthFragment.access$184(LookRealDepthFragment.this, depthName + ",");
                    LookRealDepthFragment.access$284(LookRealDepthFragment.this, bitt2.getRealDepth() + ",");
                }
            }));
        }
        if (!TextUtils.isEmpty(this.realDepthName)) {
            String str3 = this.realDepthName;
            this.realDepthName = str3.substring(0, str3.length() - 1);
            this.realDepthName += ";";
        }
        if (!TextUtils.isEmpty(this.realDepth)) {
            String str4 = this.realDepth;
            this.realDepth = str4.substring(0, str4.length() - 1);
            this.realDepth += ";";
        }
        this.uuid = UUID.randomUUID().toString().replaceAll("-", "");
    }

    private TextView getText(String str) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(20.0f);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setGravity(17);
        textView.setText(str);
        return textView;
    }

    private View getDevide() {
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(SupportMenu.CATEGORY_MASK);
        return textView;
    }

    @OnClick({R.id.bt_screenshot})
    public void onclick() {
        if (TextUtils.isEmpty(this.realDepthName) || TextUtils.isEmpty(this.realDepth)) {
            return;
        }
        String operatorName = MyApplication.getInstance().getOperatorName();
        if (!TextUtils.isEmpty(operatorName)) {
            uploadData(operatorName);
            return;
        }
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip("操作员：");
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.5
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                MyApplication.getInstance().setOperatorName(str);
                LookRealDepthFragment.this.uploadData(str);
            }
        });
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadData(String str) {
        String string = getArguments().getString("title");
        addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).postTestData(TUitls.uploadTestData(this.uuid, String.valueOf(getArguments().getInt("keyid")), string, getArguments().getString("bitNum"), this.realDepthName, this.realDepth, str, SPUtils.getString("series", "test_sn"), AppUtil.getVersionCode(getContext()), SPUtils.getString(SpKeys.FIRMWARE, "test_fm"), getArguments().getString("depth"), getArguments().getString(DEPTH_NAME))).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UploadTestData>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.6
            @Override // io.reactivex.functions.Consumer
            public void accept(UploadTestData uploadTestData) throws Exception {
                if ("0".equals(uploadTestData.getCode())) {
                    ToastUtil.showToast("上传成功");
                    return;
                }
                ToastUtil.showToast("上传失败：" + uploadTestData.getMsg());
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(R.string.network_unavailable);
            }
        }));
    }
}
