package com.kkkcut.e20j.ui.fragment;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kkkcut.e20j.base.BaseFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.DesUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class TestFragment extends BaseFragment {

    Button clear;

    EditText paramDes;

    TextView responseDec;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.paramDes.setShowSoftInputOnFocus(false);
        }
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.clear) {
            this.paramDes.setText("");
            return;
        }
        if (id != R.id.commit) {
            return;
        }
        String encrypt = DesUtil.encrypt(this.paramDes.getText().toString().trim(), DesUtil.SERVER);
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.i(TAG, "param: " + encrypt);
        okHttpClient.newCall(new Request.Builder().url("http://192.168.0.200:8086/MobilePhoneAppService.ashx").post(new FormBody.Builder().add("T", encrypt).build()).build()).enqueue(new Callback() { // from class: com.kkkcut.e20j.ui.fragment.TestFragment.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.d(TestFragment.TAG, "onFailure: " + iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TestFragment.TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TestFragment.TAG, headers.name(i) + ":" + headers.value(i));
                }
                String string = response.body().string();
                Log.i(TestFragment.TAG, "onResponse: " + string);
                try {
                    String decrypt = DesUtil.decrypt(string, DesUtil.SERVER);
                    Log.i(TestFragment.TAG, "返回: " + decrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
