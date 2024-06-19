package com.kkkcut.e20j.view;

import com.kkkcut.e20j.bean.gsonBean.Configuration;
import java.util.List;

/* loaded from: classes.dex */
public interface HomePageView {
    void showBottomView(List<Configuration.BottomLayoutBean> list);

    void showCenterView(List<Configuration.CenterLayoutBean> list);

    void showLogo(Configuration.TitleLayoutBean.LogoBean logoBean);
}
