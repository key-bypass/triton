package com.kkkcut.e20j.driver.communication;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import me.rickmark.prolific_serial.PL2303Driver;
import me.rickmark.prolific_serial.PL2303GDriver;


/* loaded from: classes.dex */
public class Pl2303DriveProxy {
    private DriveType driveType;
    private PL2303Driver pl2303;
    private PL2303GDriver pl2303G;
    private int[] pl2302gID = {9123, 9139, 9155, 9171, 9187, 8992, 8993, 8994, 8995, 9008, 9009};
    private int[] pl2302ID = {8963, 8964, 41216};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum DriveType {
        PL2303,
        PL2303G,
        UNKNOWN
    }

    public Pl2303DriveProxy(UsbManager usbManager, Context context, String str) {
        this.driveType = DriveType.UNKNOWN;
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            if (usbDevice.getVendorId() == 1659) {
                if (isPl2303(usbDevice.getProductId())) {
                    this.driveType = DriveType.PL2303;
                    this.pl2303 = new PL2303Driver((UsbManager) context.getSystemService("usb"), context, str);
                    return;
                } else if (isPl2303G(usbDevice.getProductId())) {
                    this.driveType = DriveType.PL2303G;
                    this.pl2303G = new PL2303GDriver((UsbManager) context.getSystemService("usb"), context, str);
                    return;
                }
            }
        }
    }

    private boolean isPl2303G(int i) {
        for (int i2 : this.pl2302gID) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean isPl2303(int i) {
        for (int i2 : this.pl2302ID) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean isPl2303G() {
        return this.driveType == DriveType.PL2303G;
    }

    public boolean PL2303USBFeatureSupported() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.PL2303USBFeatureSupported();
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.PL2303USBFeatureSupported();
    }

    public boolean enumerate() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.enumerate();
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.enumerate();
    }

    public boolean PL2303Device_IsHasPermission() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.PL2303Device_IsHasPermission();
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.PL2303Device_IsHasPermission();
    }

    public boolean PL2303Device_IsSupportChip() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.PL2303Device_IsSupportChip();
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.PL2303Device_IsSupportChip();
    }

    public boolean isConnected() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.isConnected();
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.isConnected();
    }

    public int write(byte[] bArr) {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return 0;
            }
            return pL2303GDriver.write(bArr);
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return 0;
        }
        return pL2303Driver.write(bArr);
    }

    public int read(byte[] bArr) {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return 0;
            }
            return pL2303GDriver.read(bArr);
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return 0;
        }
        return pL2303Driver.read(bArr);
    }

    public boolean InitByBaudRate(int i) {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return false;
            }
            return pL2303GDriver.InitByBaudRate(PL2303GDriver.BaudRate.B115200, i);
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return false;
        }
        return pL2303Driver.InitByBaudRate(PL2303Driver.BaudRate.B115200, i);
    }

    public void end() {
        if (isPl2303G()) {
            PL2303GDriver pL2303GDriver = this.pl2303G;
            if (pL2303GDriver == null) {
                return;
            }
            pL2303GDriver.end();
            return;
        }
        PL2303Driver pL2303Driver = this.pl2303;
        if (pL2303Driver == null) {
            return;
        }
        pL2303Driver.end();
    }
}
