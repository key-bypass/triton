package com.kkkcut.e20j.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.kkkcut.e20j.androidquick.ui.base.QuickActivity;
import com.kkkcut.e20j.base.HideStatusActivity;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ResUpdateUtils;

/* loaded from: classes.dex */
public class LookPicActivity extends HideStatusActivity {
    private static final String IMG_TRANSITION = "img_transition";
    public static final String RES_ID = "ResId";
    public static final String isKeyImg = "isKeyImg";

    ImageView ivPhoto;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_look_pic;
    }

    public static void start(Context context, int i, boolean z) {
        Intent intent = new Intent(context, (Class<?>) LookPicActivity.class);
        intent.putExtra(RES_ID, i);
        intent.putExtra(isKeyImg, z);
        context.startActivity(intent);
    }

    public static void start(Context context, int i) {
        start(context, i, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.HideStatusActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        if (getIntent().getBooleanExtra(isKeyImg, false)) {
            ResUpdateUtils.showKeyImage(this, getIntent().getIntExtra(RES_ID, 0), this.ivPhoto);
        } else {
            this.ivPhoto.setImageResource(getIntent().getIntExtra(RES_ID, 0));
        }
    }

    @Override // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected QuickActivity.TransitionMode getOverridePendingTransitionMode() {
        return QuickActivity.TransitionMode.FADE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
