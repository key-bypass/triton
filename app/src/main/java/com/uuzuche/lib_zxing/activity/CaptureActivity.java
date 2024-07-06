package com.uuzuche.lib_zxing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;


/* loaded from: classes2.dex */
public class CaptureActivity extends AppCompatActivity {
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() { // from class: com.uuzuche.lib_zxing.activity.CaptureActivity.2
        @Override // com.uuzuche.lib_zxing.activity.CodeUtils.AnalyzeCallback
        public void onAnalyzeSuccess(Bitmap bitmap, String str) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, 1);
            bundle.putString(CodeUtils.RESULT_STRING, str);
            intent.putExtras(bundle);
            CaptureActivity.this.setResult(-1, intent);
            CaptureActivity.this.finish();
        }

        @Override // com.uuzuche.lib_zxing.activity.CodeUtils.AnalyzeCallback
        public void onAnalyzeFailed() {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, 2);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            intent.putExtras(bundle);
            CaptureActivity.this.setResult(-1, intent);
            CaptureActivity.this.finish();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.uuzuche.lib_zxing.R.layout.camera);
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(this.analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(com.uuzuche.lib_zxing.R.id.fl_zxing_container, captureFragment).commit();
        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() { // from class: com.uuzuche.lib_zxing.activity.CaptureActivity.1
            @Override // com.uuzuche.lib_zxing.activity.CaptureFragment.CameraInitCallBack
            public void callBack(Exception exc) {
                if (exc == null) {
                    return;
                }
                Log.e("TAG", "callBack: ", exc);
            }
        });
    }
}
