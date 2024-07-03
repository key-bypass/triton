package com.kkkcut.e20j.ui.fragment.setting;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.CutCountHelper;

/* loaded from: classes.dex */
public class SettingFragment extends BaseBackFragment {
    private Fragment currentFragment;

    RadioButton rbCutSetting;

    RadioButton rb_space_Width;

    TextView tvCutCount;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_setting;
    }

    public static SettingFragment newInstance() {
        Bundle bundle = new Bundle();
        SettingFragment settingFragment = new SettingFragment();
        settingFragment.setArguments(bundle);
        return settingFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.setting);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.currentFragment = SpeedSetFragment.newInstance();
        getChildFragmentManager().beginTransaction().add(R.id.container, this.currentFragment, SpeedSetFragment.TAG).commit();
        if (MyApplication.getInstance().isShowRealDepth()) {
            this.rb_space_Width.setVisibility(0);
            this.rbCutSetting.setVisibility(0);
        }
        CutCountHelper.getInstance().getCutCount(new CutCountHelper.ReadCutCountListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment.1
            @Override // com.kkkcut.e20j.utils.CutCountHelper.ReadCutCountListener
            public void onReadFinish(String str) {
                SettingFragment.this.tvCutCount.setText(str);
            }
        });
    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_cut_setting /* 2131362606 */:
                if (z) {
                    switchPage(CutSettingFragment.TAG, CutSettingFragment.class);
                    return;
                }
                return;
            case R.id.rb_other /* 2131362634 */:
                if (z) {
                    switchPage(OtherSettingFragment.TAG, OtherSettingFragment.class);
                    return;
                }
                return;
            case R.id.rb_space_Width /* 2131362654 */:
                if (z) {
                    switchPage(SpaceWidthSetting.TAG, SpaceWidthSetting.class);
                    return;
                }
                return;
            case R.id.rb_speed /* 2131362655 */:
                if (z) {
                    switchPage(SpeedSetFragment.TAG, SpeedSetFragment.class);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void switchPage(String str, Class cls) {
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag == null) {
            Fragment fragment = null;
            try {
                try {
                    fragment = (Fragment) cls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                getChildFragmentManager().beginTransaction().add(R.id.container, fragment, str).hide(this.currentFragment).commit();
                this.currentFragment = fragment;
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        getChildFragmentManager().beginTransaction().hide(this.currentFragment).show(findFragmentByTag).commit();
        this.currentFragment = findFragmentByTag;
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 21) {
            this.rb_space_Width.setVisibility(0);
            this.rbCutSetting.setVisibility(0);
        }
    }

    public void onClick() {
        RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.setRemindMsg(getString(R.string.do_you_want_to_exit_the_program));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment.2
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    UsbSerialManager.getInstance().stop();
                    SettingFragment.this.getActivity().finish();
                    System.exit(0);
                }
            }
        });
        remindDialog.show();
    }
}
