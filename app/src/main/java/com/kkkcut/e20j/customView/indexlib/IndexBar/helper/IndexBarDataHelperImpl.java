package com.kkkcut.e20j.customView.indexlib.IndexBar.helper;

import android.text.TextUtils;
import com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class IndexBarDataHelperImpl implements IIndexBarDataHelper {
    private static final String TAG = "IndexBarDataHelperImpl";

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IIndexBarDataHelper
    public IIndexBarDataHelper convert(List<? extends BaseIndexPinyinBean> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                BaseIndexPinyinBean baseIndexPinyinBean = list.get(i);
                StringBuilder sb = new StringBuilder();

                sb.append(baseIndexPinyinBean.getBaseIndexPinyin());
            }
        }
        return this;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IIndexBarDataHelper
    public IIndexBarDataHelper fillInexTag(List<? extends BaseIndexPinyinBean> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                BaseIndexPinyinBean baseIndexPinyinBean = list.get(i);
                if (baseIndexPinyinBean.isNeedToPinyin()) {
                    String str = baseIndexPinyinBean.getBaseIndexPinyin().toString();
                    if (TextUtils.isEmpty(str)) {
                        baseIndexPinyinBean.setBaseIndexTag("#");
                    } else {
                        String substring = str.substring(0, 1);
                        if (substring.matches("[A-Z]")) {
                            baseIndexPinyinBean.setBaseIndexTag(substring);
                        } else {
                            baseIndexPinyinBean.setBaseIndexTag("#");
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IIndexBarDataHelper
    public IIndexBarDataHelper sortSourceDatas(List<? extends BaseIndexPinyinBean> list) {
        if (list != null && !list.isEmpty()) {
            convert(list);
            fillInexTag(list);
            Collections.sort(list, new Comparator<BaseIndexPinyinBean>() { // from class: com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IndexBarDataHelperImpl.1
                @Override // java.util.Comparator
                public int compare(BaseIndexPinyinBean baseIndexPinyinBean, BaseIndexPinyinBean baseIndexPinyinBean2) {
                    if (!baseIndexPinyinBean.isNeedToPinyin() || !baseIndexPinyinBean2.isNeedToPinyin()) {
                        return 0;
                    }
                    if (TextUtils.equals(baseIndexPinyinBean.getBaseIndexTag(), "#") && TextUtils.equals(baseIndexPinyinBean2.getBaseIndexTag(), "#")) {
                        return 0;
                    }
                    if (TextUtils.equals(baseIndexPinyinBean.getBaseIndexTag(), "#")) {
                        return 1;
                    }
                    if (TextUtils.equals(baseIndexPinyinBean2.getBaseIndexTag(), "#")) {
                        return -1;
                    }
                    return baseIndexPinyinBean.getBaseIndexPinyin().compareTo(baseIndexPinyinBean2.getBaseIndexPinyin());
                }
            });
        }
        return this;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IIndexBarDataHelper
    public IIndexBarDataHelper getSortedIndexDatas(List<? extends BaseIndexPinyinBean> list, List<String> list2) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String baseIndexTag = list.get(i).getBaseIndexTag();
                if (!list2.contains(baseIndexTag)) {
                    list2.add(baseIndexTag);
                }
            }
        }
        return this;
    }
}
