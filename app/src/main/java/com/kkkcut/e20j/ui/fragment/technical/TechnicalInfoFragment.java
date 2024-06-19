package com.kkkcut.e20j.ui.fragment.technical;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.kkkcut.e20j.DbBean.technical.DataList;
import com.kkkcut.e20j.DbBean.technical.DataListBean;
import com.kkkcut.e20j.adapter.TechnicalInfoDataAdapter;
import com.kkkcut.e20j.customView.ItemDecorationPowerful;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TechnicalInfoFragment extends BaseBackFragment {
    private String[] names = {"遥控拷贝", "遥控类型", "芯片拷贝", "是否拆读", "钥匙匹配", "OBD位置", "码片类型", "钥匙坯号", "锁片差位", "匹配设备", "防盗类型", "开锁工具", "匹配密码", "芯片型号", "遥控生成", "遥控匹配", "芯片生成", "注意事项", "密码获取", "零件位置", "开锁方向"};

    @BindView(R.id.rv_data_list)
    RecyclerView rvDataList;
    private TechnicalInfoDataAdapter technicalInfoDataAdapter;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tehnical_info;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.technical_information);
    }

    public static TechnicalInfoFragment newInstance(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("yearID", i);
        bundle.putString("title", str);
        TechnicalInfoFragment technicalInfoFragment = new TechnicalInfoFragment();
        technicalInfoFragment.setArguments(bundle);
        return technicalInfoFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.tvInfo.setText(getArguments().getString("title"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setOrientation(1);
        this.rvDataList.setLayoutManager(gridLayoutManager);
        TechnicalInfoDataAdapter technicalInfoDataAdapter = new TechnicalInfoDataAdapter();
        this.technicalInfoDataAdapter = technicalInfoDataAdapter;
        this.rvDataList.setAdapter(technicalInfoDataAdapter);
        this.rvDataList.addItemDecoration(new ItemDecorationPowerful(2));
        getDatas();
    }

    private void getDatas() {
        addDisposable(Observable.fromCallable(new Callable<DataList>() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public DataList call() throws Exception {
                return KeyInfoDaoManager.getInstance().getTechnicalDataList(TechnicalInfoFragment.this.getArguments().getInt("yearID"));
            }
        }).map(new Function<DataList, List<DataListBean>>() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.3
            @Override // io.reactivex.functions.Function
            public List<DataListBean> apply(DataList dataList) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < TechnicalInfoFragment.this.names.length; i++) {
                    arrayList.add(new DataListBean(TechnicalInfoFragment.this.names[i], dataList.getColoumn(i)));
                }
                return arrayList;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<DataListBean>>() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<DataListBean> list) throws Exception {
                if (TechnicalInfoFragment.this.technicalInfoDataAdapter != null) {
                    TechnicalInfoFragment.this.technicalInfoDataAdapter.setNewData(list);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        }));
    }
}
