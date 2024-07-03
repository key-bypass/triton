package com.kkkcut.e20j.driver.pl2303;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.cutting.machine.OperateType;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.communication.Communication;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.communication.WriteCallback;
import com.cutting.machine.error.ErrorCodeBean;
import com.cutting.machine.error.ErrorHandle;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.ErrorCodeDaoManager;
import com.kkkcut.e20j.driver.communication.Pl2303DriveProxy;
import com.kkkcut.e20j.us.R;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/* loaded from: classes.dex */
public class UsbSerialManager implements Communication, ErrorHandle {
    public static final String ACTION_PL2303_PERMISSION = "com.kkkcut.android.USB_PERMISSION";
    private static final String TAG = "UsbSerialManager";
    private static final String TEXT_CHARSET = "UTF-8";
    private static UsbSerialManager sInstance;
    private Context context;
    private Pl2303DriveProxy mSerial;
    private OperateType operateType;
    private String order = "";
    private Handler handler = new Handler();
    WriteCallback writeCallback = new WriteCallback() { // from class: com.kkkcut.e20j.driver.pl2303.UsbSerialManager.1
        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteFailure(Exception exc) {
        }

        @Override // com.cutting.machine.communication.WriteCallback
        public void onWriteSuccess(int i, int i2, byte[] bArr) {
        }
    };
    private Runnable openSerial = new Runnable() { // from class: com.kkkcut.e20j.driver.pl2303.UsbSerialManager.2
        @Override // java.lang.Runnable
        public void run() {
            UsbSerialManager.this.openUsbSerial();
        }
    };
    private BroadcastReceiver mUsbPermissionActionReceiver = new BroadcastReceiver() { // from class: com.kkkcut.e20j.driver.pl2303.UsbSerialManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (UsbSerialManager.ACTION_PL2303_PERMISSION.equals(intent.getAction())) {
                synchronized (this) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    if (intent.getBooleanExtra("permission", false)) {
                        Log.i(UsbSerialManager.TAG, "onReceive: " + usbDevice.toString());
                        if (usbDevice != null && (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:2303") || String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:23D3"))) {
                            Log.i(UsbSerialManager.TAG, "权限通过: ");
                            UsbSerialManager.this.handler.postDelayed(UsbSerialManager.this.openSerial, 1000L);
                        }
                    } else {
                        Toast.makeText(context, String.valueOf("Permission denied for device：" + String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId()))), 1).show();
                    }
                }
            }
        }
    };
    byte[] readBuffer = new byte[256];
    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();

    public String getOrder() {
        return this.order;
    }

    public OperateType getOperateType() {
        return this.operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    @Override // com.cutting.machine.communication.Communication
    public void sendData(byte[] bArr, WriteCallback writeCallback) {
        this.writeCallback = writeCallback;
        if (sendOrder(bArr)) {
            this.writeCallback.onWriteSuccess(bArr.length, bArr.length, bArr);
        }
    }

    @Override // com.cutting.machine.communication.Communication
    public <T> void sendEventBusMessage(int i, T t) {
        EventBus.getDefault().post(new EventCenter(i, t));
    }

    @Override // com.cutting.machine.error.ErrorHandle
    public ErrorCodeBean getErrorBean(int i) {
        com.kkkcut.e20j.DbBean.ErrorCodeBean errorCode = ErrorCodeDaoManager.getInstance().getErrorCode(i);
        ErrorCodeBean errorCodeBean = new ErrorCodeBean();
        errorCodeBean.setCode(errorCode.getCode());
        errorCodeBean.setDesc_en(errorCode.getDesc_en());
        errorCodeBean.setDesc_zh(errorCode.getDesc_zh());
        errorCodeBean.setDesc_cs(errorCode.getDesc_cs());
        errorCodeBean.setDesc_fr(errorCode.getDesc_fr());
        errorCodeBean.setDesc_de(errorCode.getDesc_de());
        errorCodeBean.setDesc_it(errorCode.getDesc_it());
        errorCodeBean.setDesc_es(errorCode.getDesc_es());
        errorCodeBean.setDesc_ko(errorCode.getDesc_ko());
        errorCodeBean.setDesc_pt(errorCode.getDesc_pt());
        errorCodeBean.setDesc_ru(errorCode.getDesc_ru());
        errorCodeBean.setDesc_tr(errorCode.getDesc_tr());
        errorCodeBean.setDesc_hb(errorCode.getDesc_hb());
        errorCodeBean.setDesc_pl(errorCode.getDesc_pl());
        errorCodeBean.setDesc_vi(errorCode.getDesc_vi());
        errorCodeBean.setMessageType(errorCode.getMessageType());
        return errorCodeBean;
    }

    private UsbSerialManager() {
    }

    public static UsbSerialManager getInstance() {
        if (sInstance == null) {
            sInstance = new UsbSerialManager();
        }
        return sInstance;
    }

    public void init(Context context) {
        context.registerReceiver(this.mUsbPermissionActionReceiver, new IntentFilter(ACTION_PL2303_PERMISSION));
        this.context = context;
        Pl2303DriveProxy pl2303DriveProxy = new Pl2303DriveProxy((UsbManager) context.getSystemService("usb"), context, ACTION_PL2303_PERMISSION);
        this.mSerial = pl2303DriveProxy;
        if (!pl2303DriveProxy.PL2303USBFeatureSupported()) {
            Toast.makeText(context, "No Support USB host API", 0).show();
            Log.d(TAG, "No Support USB host API");
            this.mSerial = null;
        } else {
            if (!this.mSerial.enumerate()) {
                Toast.makeText(context, "no more devices found", 0).show();
            }
            this.handler.postDelayed(this.openSerial, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openUsbSerial() {
        Log.d(TAG, "Enter  openUsbSerial");
        Pl2303DriveProxy pl2303DriveProxy = this.mSerial;
        if (pl2303DriveProxy == null) {
            Log.d(TAG, "No mSerial");
            return;
        }
        if (pl2303DriveProxy.isConnected()) {
            if (!this.mSerial.InitByBaudRate(700)) {
                if (!this.mSerial.PL2303Device_IsHasPermission()) {
                    Toast.makeText(this.context, "cannot open, maybe no permission", 0).show();
                }
                if (!this.mSerial.PL2303Device_IsHasPermission() || this.mSerial.PL2303Device_IsSupportChip()) {
                    return;
                }
                Toast.makeText(this.context, "cannot open, maybe this chip has no support, please use PL2303G chip.", 0).show();
                Log.d(TAG, "cannot open, maybe this chip has no support, please use PL2303G chip.");
                return;
            }
            ClampManager.getInstance().setRootPathDir(this.context.getFilesDir());
            OperationManager.getInstance().init(MyApplication.getInstance(), this);
            startReceiveThread();
            this.handler.postDelayed(new Runnable() { // from class: com.kkkcut.e20j.driver.pl2303.UsbSerialManager.3
                @Override // java.lang.Runnable
                public void run() {
                    ClampManager.getInstance().initClamp("", true);
                }
            }, 100L);
            Toast.makeText(this.context, R.string.connected, 0).show();
            Log.d(TAG, "connected : OK");
            sendEventBusMessage(20, null);
            return;
        }
        Toast.makeText(this.context, "Connected failed, Please plug in PL2303 cable again!", 0).show();
        Log.d(TAG, "connected failed, Please plug in PL2303 cable again!");
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            inputStreamReader = null;
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer("");
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.kkkcut.e20j.driver.pl2303.UsbSerialManager$5] */
    private void startReceiveThread() {
        new Thread() { // from class: com.kkkcut.e20j.driver.pl2303.UsbSerialManager.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (UsbSerialManager.this.mSerial.isConnected()) {
                    try {
                        int read = UsbSerialManager.this.mSerial.read(UsbSerialManager.this.readBuffer);
                        byte[] copyOf = Arrays.copyOf(UsbSerialManager.this.readBuffer, read);
                        if (read > 0) {
                            OperationManager.getInstance().onReceiveData(copyOf);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static String byteToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().trim();
    }

    public boolean sendOrder(byte[] bArr) {
        Pl2303DriveProxy pl2303DriveProxy = this.mSerial;
        if (pl2303DriveProxy == null) {
            Log.i(TAG, "请先打开串口: ");
            return false;
        }
        int write = pl2303DriveProxy.write(bArr);
        if (write == -1) {
            ToastUtil.showToast(R.string.failed_to_send);
            EventBus.getDefault().post(new EventCenter(14));
            return false;
        }
        if (bArr.length > write) {
            return sendOrder(bArr);
        }
        byteToHex(bArr);
        return true;
    }

    public void stop() {
        this.handler.removeCallbacks(this.openSerial);
        Pl2303DriveProxy pl2303DriveProxy = this.mSerial;
        if (pl2303DriveProxy != null) {
            pl2303DriveProxy.end();
            this.mSerial = null;
            clear();
            this.context.unregisterReceiver(this.mUsbPermissionActionReceiver);
            this.context = null;
        }
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    protected void clear() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }
}
