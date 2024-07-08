package com.kkkcut.e20j.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.LanguageListAdapter;
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.lan.LanguageConstants;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LanguageSwitchFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    LanguageListAdapter adapter;
    List<String> languageList;
    Map<String, String> languageMap;

    RecyclerView rvLanguage;
    private String shortName;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_language;
    }

    public static LanguageSwitchFragment newInstance(String str) {
        LanguageSwitchFragment languageSwitchFragment = new LanguageSwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("languageStr", str);
        languageSwitchFragment.setArguments(bundle);
        return languageSwitchFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvLanguage.setLayoutManager(linearLayoutManager);
        this.rvLanguage.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        String string = getArguments().getString("languageStr");
        if (TextUtils.isEmpty(string)) {
            string = "en";
        }
        this.languageList = Arrays.asList(string.split(","));
        this.languageMap = getLanguageConsMaps();
        String selectLanguage = LocalManageUtil.getSelectLanguage(getContext());
        this.shortName = selectLanguage;
        if (TextUtils.isEmpty(selectLanguage)) {
            this.shortName = "en";
        }
        LanguageListAdapter languageListAdapter = new LanguageListAdapter(this.languageList, this.languageMap, this.shortName);
        this.adapter = languageListAdapter;
        this.rvLanguage.setAdapter(languageListAdapter);
        this.adapter.setOnItemClickListener(this);
    }

    private Map<String, String> getLanguageConsMaps() {
        HashMap hashMap = new HashMap();
        hashMap.put("en", "English");
        hashMap.put("zh", "简体中文(Chinese-Simplified)");
        hashMap.put("tr", "Türkçe(Turkish)");
        hashMap.put("fr", "Français(French)");
        hashMap.put("cs", "Česky(Czech)");
        hashMap.put("de", "Deutsch(German)");
        hashMap.put("it", "lingua italiana(Italian)");
        hashMap.put("es", "Español(Spanish)");
        hashMap.put("ko", "한국어(Korean)");
        hashMap.put("pt", "Português(Portuguese)");
        hashMap.put("ru", "русский(Russian)");
        hashMap.put("pl", "Polski(Polish)");
        hashMap.put("hb", "HEBREW(עברי)");
        hashMap.put("fa", "(Persian)فارسی");
        hashMap.put("vi", "Tiếng Việt(Vietnamese)");
        hashMap.put(LanguageConstants.THAI, "Thai(ภาษาไทย)");
        return hashMap;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.language);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LocalManageUtil.saveSelectLanguage(getContext(), this.languageList.get(i));
        UsbSerialManager.getInstance().stop();
        restartApplication();
    }

    private void restartApplication() {
        Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
        launchIntentForPackage.addFlags(67108864);
        startActivity(launchIntentForPackage);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.languageList = null;
        this.languageMap = null;
        this.adapter = null;
    }
}
