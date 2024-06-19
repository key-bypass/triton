package com.kkkcut.e20j.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.androidquick.ui.base.QuickActivity;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.base.HideStatusActivity;
import com.kkkcut.e20j.us.R;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class BarCodeRemindActivity extends HideStatusActivity {
    public static final String BAR_CODE = "bar_code";
    public static final String ID = "ID";

    @BindView(R.id.iv_1)
    ImageView iv1;

    @BindView(R.id.iv_2)
    ImageView iv2;

    @BindView(R.id.iv_3)
    ImageView iv3;

    @BindView(R.id.ll_1)
    LinearLayout ll1;

    @BindView(R.id.ll_2)
    LinearLayout ll2;

    @BindView(R.id.ll_3)
    LinearLayout ll3;

    @BindView(R.id.tv_1)
    TextView tv1;

    @BindView(R.id.tv_2)
    TextView tv2;

    @BindView(R.id.tv_3)
    TextView tv3;
    private Type type;
    private static final int[] hu66Arr = {909, 1309};
    private static final int[] toy48Arr = {872, 1309};
    private static final int[] hu100Arr = {1097, 1373};
    private static final int[] hu101Arr = {1370, 1407};

    /* loaded from: classes.dex */
    private enum Type {
        HU66,
        TOY48,
        HU101,
        HU100
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_bar_code_remind;
    }

    public static void start(Activity activity, int i, String str) {
        Intent intent = new Intent(activity, (Class<?>) BarCodeRemindActivity.class);
        intent.putExtra(ID, i);
        intent.putExtra("bar_code", str);
        activity.startActivityForResult(intent, 0);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        int intExtra = getIntent().getIntExtra(ID, 0);
        if (intExtra == 909 || intExtra == 1309) {
            this.ll3.setVisibility(8);
            this.iv1.setImageResource(R.drawable.bar_code_909);
            this.ll1.setTag(909);
            this.iv2.setImageResource(R.drawable.bar_code_1309);
            this.ll2.setTag(1309);
            this.tv1.setText("Copy by this option if the original key without extra cutting");
            this.tv2.setText("Copy by this option if the original key with extra cutting");
            this.type = Type.HU66;
        }
        if (intExtra == 872 || intExtra == 1510) {
            this.iv1.setImageResource(R.drawable.bar_code_872);
            this.ll1.setTag(872);
            this.iv2.setImageResource(R.drawable.bar_code_1510);
            this.ll2.setTag(1510);
            this.iv3.setImageResource(R.drawable.bar_code_1510_1);
            this.ll3.setTag(1510);
            this.tv1.setText("Copy by this option if both sides work");
            this.tv2.setText("Copy by this option if only one side works");
            this.tv3.setText("Copy by this option if only one side works");
            this.type = Type.TOY48;
        }
        if (intExtra == 1097 || intExtra == 1373) {
            this.ll3.setVisibility(8);
            this.iv1.setImageResource(R.drawable.bar_code_1097);
            this.ll1.setTag(1097);
            this.iv2.setImageResource(R.drawable.bar_code_1373);
            this.ll2.setTag(1373);
            this.type = Type.HU100;
        }
        if (intExtra == 1370 || intExtra == 1407 || intExtra == 998) {
            this.iv1.setImageResource(R.drawable.bar_code_998);
            this.ll1.setTag(998);
            this.iv2.setImageResource(R.drawable.bar_code_1370);
            this.ll2.setTag(1370);
            this.iv3.setImageResource(R.drawable.bar_code_1407);
            this.ll3.setTag(1407);
            this.type = Type.HU101;
        }
    }

    @Override // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected QuickActivity.TransitionMode getOverridePendingTransitionMode() {
        return QuickActivity.TransitionMode.FADE;
    }

    @OnClick({R.id.iv_back, R.id.ll_1, R.id.ll_2, R.id.ll_3})
    public void onclick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
            return;
        }
        switch (id) {
            case R.id.ll_1 /* 2131362386 */:
            case R.id.ll_2 /* 2131362387 */:
            case R.id.ll_3 /* 2131362388 */:
                if (this.type == null) {
                    return;
                }
                goBack(((Integer) view.getTag()).intValue());
                return;
            default:
                return;
        }
    }

    private void goBack(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, i);
        bundle.putString("bar_code", getIntent().getStringExtra("bar_code"));
        EventBus.getDefault().post(new EventCenter(55, bundle));
        finish();
    }
}
