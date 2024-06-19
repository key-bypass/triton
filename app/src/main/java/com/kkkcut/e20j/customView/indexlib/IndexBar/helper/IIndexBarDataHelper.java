package com.kkkcut.e20j.customView.indexlib.IndexBar.helper;

import com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import java.util.List;

/* loaded from: classes.dex */
public interface IIndexBarDataHelper {
    IIndexBarDataHelper convert(List<? extends BaseIndexPinyinBean> list);

    IIndexBarDataHelper fillInexTag(List<? extends BaseIndexPinyinBean> list);

    IIndexBarDataHelper getSortedIndexDatas(List<? extends BaseIndexPinyinBean> list, List<String> list2);

    IIndexBarDataHelper sortSourceDatas(List<? extends BaseIndexPinyinBean> list);
}
