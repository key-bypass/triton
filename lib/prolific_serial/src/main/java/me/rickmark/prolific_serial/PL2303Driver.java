package me.rickmark.prolific_serial;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class PL2303Driver<UsbConfiguration> {

    /* renamed from: A */
    private static final int f1026A = 2056;

    /* renamed from: B */
    private static final int f1027B = 2313;
    public static final int BAUD0 = 0;
    public static final int BAUD115200 = 115200;
    public static final int BAUD1200 = 1200;
    public static final int BAUD12000000 = 12000000;
    public static final int BAUD1228800 = 1228800;
    public static final int BAUD14400 = 14400;
    public static final int BAUD150 = 150;
    public static final int BAUD1800 = 1800;
    public static final int BAUD19200 = 19200;
    public static final int BAUD230400 = 230400;
    public static final int BAUD2400 = 2400;
    public static final int BAUD2457600 = 2457600;
    public static final int BAUD300 = 300;
    public static final int BAUD3000000 = 3000000;
    public static final int BAUD38400 = 38400;
    public static final int BAUD460800 = 460800;
    public static final int BAUD4800 = 4800;
    public static final int BAUD57600 = 57600;
    public static final int BAUD600 = 600;
    public static final int BAUD6000000 = 6000000;
    public static final int BAUD614400 = 614400;
    public static final int BAUD75 = 75;
    public static final int BAUD921600 = 921600;
    public static final int BAUD9600 = 9600;

    /* renamed from: E */
    private static final int f1028E = 1;

    /* renamed from: F */
    private static final int f1029F = 2;

    /* renamed from: G */
    private static final int f1030G = 32;

    /* renamed from: P */
    private static final boolean f1031P = false;
    public static final int PL2303HXD_CTS_ON = 128;
    public static final int PL2303HXD_DCD_ON = 1;
    public static final int PL2303HXD_DSR_ON = 2;
    public static final int PL2303HXD_RI_ON = 8;
    public static final int PL_MAX_INTERFACE_NUM = 4;

    /* renamed from: Q */
    private static final boolean f1032Q = false;
    public static final int READBUF_SIZE = 4096;
    public static Object ReadQueueLock = new Object();
    public static final int WRITEBUF_SIZE = 4096;

    /* renamed from: a */
    static final int f1033a = 11;

    /* renamed from: aJ */
    private static /* synthetic */ int[] f1034aJ = null;

    /* renamed from: aK */
    private static /* synthetic */ int[] f1035aK = null;

    /* renamed from: aL */
    private static /* synthetic */ int[] f1036aL = null;

    /* renamed from: aM */
    private static /* synthetic */ int[] f1037aM = null;

    /* renamed from: aN */
    private static /* synthetic */ int[] f1038aN = null;

    /* renamed from: b */
    static final int f1039b = 12;

    /* renamed from: f */
    private static final boolean f1040f = false;

    /* renamed from: g */
    private static final boolean f1041g = false;

    /* renamed from: h */
    private static final boolean f1042h = false;

    /* renamed from: i */
    private static String f1043i = "2.0.13.35";

    /* renamed from: k */
    private static final int f1044k = 33;

    /* renamed from: l */
    private static final int f1045l = 32;

    /* renamed from: m */
    private static final int f1046m = 33;

    /* renamed from: n */
    private static final int f1047n = 35;

    /* renamed from: o */
    private static final int f1048o = 0;

    /* renamed from: p */
    private static final int f1049p = 161;

    /* renamed from: q */
    private static final int f1050q = 33;

    /* renamed from: r */
    private static final int f1051r = 64;

    /* renamed from: s */
    private static final int f1052s = 1;
    public static UsbDevice sDevice = null;

    /* renamed from: t */
    private static final int f1053t = 192;

    /* renamed from: u */
    private static final int f1054u = 1;

    /* renamed from: v */
    private static final int f1055v = 33;

    /* renamed from: w */
    private static final int f1056w = 34;

    /* renamed from: x */
    private static final int f1057x = 0;

    /* renamed from: y */
    private static final int f1058y = 1;

    /* renamed from: z */
    private static final int f1059z = 2;

    /* renamed from: C */
    private int f1060C;

    /* renamed from: D */
    private byte f1061D;

    /* renamed from: H */
    private final int f1062H;

    /* renamed from: I */
    private UsbManager f1063I;

    /* renamed from: J */
    private UsbDevice f1064J;

    /* renamed from: K */
    private UsbDeviceConnection f1065K;

    /* renamed from: L */
    private UsbInterface f1066L;

    /* renamed from: M */
    private UsbEndpoint f1067M;

    /* renamed from: N */
    private UsbEndpoint f1068N;

    /* renamed from: O */
    private UsbEndpoint f1069O;
    public final int PLDETACHED_VALUE;
    public final String PLUART_DETACHED;
    public final String PLUART_MESSAGE;

    /* renamed from: R */
    private int f1070R;

    /* renamed from: S */
    private int f1071S;

    /* renamed from: T */
    private int f1072T;

    /* renamed from: U */
    private int f1073U;

    /* renamed from: V */
    private int f1074V;

    /* renamed from: W */
    private ArrayBlockingQueue<Integer> f1075W;

    /* renamed from: X */
    private C2544a f1076X;

    /* renamed from: Y */
    private boolean f1077Y;

    /* renamed from: Z */
    private int f1078Z;

    /* renamed from: aA */
    private int f1079aA;

    /* renamed from: aB */
    private int f1080aB;

    /* renamed from: aC */
    private int f1081aC;

    /* renamed from: aD */
    private int f1082aD;

    /* renamed from: aE */
    private int f1083aE;

    /* renamed from: aF */
    private int f1084aF;

    /* renamed from: aG */
    private boolean f1085aG;

    /* renamed from: aH */
    private final BroadcastReceiver f1086aH;

    /* renamed from: aI */
    private Runnable f1087aI;

    /* renamed from: aa */
    private int f1088aa;

    /* renamed from: ab */
    private boolean f1089ab;

    /* renamed from: ac */
    private boolean f1090ac;

    /* renamed from: ad */
    private String f1091ad;

    /* renamed from: ae */
    private ArrayList<String> f1092ae;

    /* renamed from: af */
    private int f1093af;

    /* renamed from: ag */
    private int f1094ag;

    /* renamed from: ah */
    private final int f1095ah;

    /* renamed from: ai */
    private final int f1096ai;

    /* renamed from: aj */
    private final int f1097aj;

    /* renamed from: ak */
    private final int f1098ak;

    /* renamed from: al */
    private final int f1099al;

    /* renamed from: am */
    private final int f1100am;

    /* renamed from: an */
    private final int f1101an;

    /* renamed from: ao */
    private final int f1102ao;

    /* renamed from: ap */
    private boolean f1103ap;

    /* renamed from: aq */
    private boolean f1104aq;

    /* renamed from: ar */
    private boolean f1105ar;

    /* renamed from: as */
    private FlowControl f1106as;

    /* renamed from: at */
    private boolean f1107at;

    /* renamed from: au */
    private final boolean f1108au;

    /* renamed from: av */
    private final boolean f1109av;

    /* renamed from: aw */
    private final int f1110aw;

    /* renamed from: ax */
    private final int f1111ax;

    /* renamed from: ay */
    private int f1112ay;

    /* renamed from: az */
    private int f1113az;

    /* renamed from: c */
    byte[] f1114c;

    /* renamed from: d */
    Context f1115d;

    /* renamed from: e */
    private boolean f1116e;

    /* renamed from: j */
    private byte[] f1117j;

    /* loaded from: classes2.dex */
    public enum BaudRate {
        B0,
        B75,
        B150,
        B300,
        B600,
        B1200,
        B1800,
        B2400,
        B4800,
        B9600,
        B14400,
        B19200,
        B38400,
        B57600,
        B115200,
        B230400,
        B460800,
        B614400,
        B921600,
        B1228800,
        B2457600,
        B3000000,
        B6000000,
        B12000000;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static BaudRate[] valuesCustom() {
            BaudRate[] valuesCustom = values();
            int length = valuesCustom.length;
            BaudRate[] baudRateArr = new BaudRate[length];
            System.arraycopy(valuesCustom, 0, baudRateArr, 0, length);
            return baudRateArr;
        }
    }

    /* loaded from: classes2.dex */
    public enum DataBits {
        D5,
        D6,
        D7,
        D8;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static DataBits[] valuesCustom() {
            DataBits[] valuesCustom = values();
            int length = valuesCustom.length;
            DataBits[] dataBitsArr = new DataBits[length];
            System.arraycopy(valuesCustom, 0, dataBitsArr, 0, length);
            return dataBitsArr;
        }
    }

    /* loaded from: classes2.dex */
    public enum FlowControl {
        OFF,
        RTSCTS,
        RFRCTS,
        DTRDSR,
        RTSCTSDTRDSR,
        XONXOFF;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static FlowControl[] valuesCustom() {
            FlowControl[] valuesCustom = values();
            int length = valuesCustom.length;
            FlowControl[] flowControlArr = new FlowControl[length];
            System.arraycopy(valuesCustom, 0, flowControlArr, 0, length);
            return flowControlArr;
        }
    }

    /* loaded from: classes2.dex */
    public enum Parity {
        NONE,
        ODD,
        EVEN;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static Parity[] valuesCustom() {
            Parity[] valuesCustom = values();
            int length = valuesCustom.length;
            Parity[] parityArr = new Parity[length];
            System.arraycopy(valuesCustom, 0, parityArr, 0, length);
            return parityArr;
        }
    }

    /* loaded from: classes2.dex */
    public enum StopBits {
        S1,
        S2;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static StopBits[] valuesCustom() {
            StopBits[] valuesCustom = values();
            int length = valuesCustom.length;
            StopBits[] stopBitsArr = new StopBits[length];
            System.arraycopy(valuesCustom, 0, stopBitsArr, 0, length);
            return stopBitsArr;
        }
    }

    /* renamed from: a */
    private static void m488a(Object obj) {
    }

    public boolean PL2303Device_GetCommTimeouts(int i) {
        return true;
    }

    /* renamed from: a */
    static /* synthetic */ int[] m493a() {
        int[] iArr = f1034aJ;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[BaudRate.valuesCustom().length];
        try {
            iArr2[BaudRate.B0.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[BaudRate.B115200.ordinal()] = 15;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[BaudRate.B1200.ordinal()] = 6;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[BaudRate.B12000000.ordinal()] = 24;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[BaudRate.B1228800.ordinal()] = 20;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[BaudRate.B14400.ordinal()] = 11;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[BaudRate.B150.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[BaudRate.B1800.ordinal()] = 7;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr2[BaudRate.B19200.ordinal()] = 12;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr2[BaudRate.B230400.ordinal()] = 16;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr2[BaudRate.B2400.ordinal()] = 8;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr2[BaudRate.B2457600.ordinal()] = 21;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr2[BaudRate.B300.ordinal()] = 4;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            iArr2[BaudRate.B3000000.ordinal()] = 22;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            iArr2[BaudRate.B38400.ordinal()] = 13;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            iArr2[BaudRate.B460800.ordinal()] = 17;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            iArr2[BaudRate.B4800.ordinal()] = 9;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            iArr2[BaudRate.B57600.ordinal()] = 14;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            iArr2[BaudRate.B600.ordinal()] = 5;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            iArr2[BaudRate.B6000000.ordinal()] = 23;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            iArr2[BaudRate.B614400.ordinal()] = 18;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            iArr2[BaudRate.B75.ordinal()] = 2;
        } catch (NoSuchFieldError unused22) {
        }
        try {
            iArr2[BaudRate.B921600.ordinal()] = 19;
        } catch (NoSuchFieldError unused23) {
        }
        try {
            iArr2[BaudRate.B9600.ordinal()] = 10;
        } catch (NoSuchFieldError unused24) {
        }
        f1034aJ = iArr2;
        return iArr2;
    }

    /* renamed from: b */
    static /* synthetic */ int[] m500b() {
        int[] iArr = f1035aK;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[StopBits.valuesCustom().length];
        try {
            iArr2[StopBits.S1.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[StopBits.S2.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f1035aK = iArr2;
        return iArr2;
    }

    /* renamed from: c */
    static /* synthetic */ int[] m503c() {
        int[] iArr = f1036aL;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Parity.valuesCustom().length];
        try {
            iArr2[Parity.EVEN.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Parity.NONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Parity.ODD.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        f1036aL = iArr2;
        return iArr2;
    }

    /* renamed from: d */
    static /* synthetic */ int[] m507d() {
        int[] iArr = f1037aM;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[DataBits.valuesCustom().length];
        try {
            iArr2[DataBits.D5.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[DataBits.D6.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[DataBits.D7.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[DataBits.D8.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        f1037aM = iArr2;
        return iArr2;
    }

    /* renamed from: e */
    static /* synthetic */ int[] m509e() {
        int[] iArr = f1038aN;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[FlowControl.valuesCustom().length];
        try {
            iArr2[FlowControl.DTRDSR.ordinal()] = 4;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[FlowControl.OFF.ordinal()] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[FlowControl.RFRCTS.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[FlowControl.RTSCTS.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[FlowControl.RTSCTSDTRDSR.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[FlowControl.XONXOFF.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        f1038aN = iArr2;
        return iArr2;
    }

    /* renamed from: a */
    private void m487a(UsbManager usbManager, Context context, String str, boolean z) {
        this.f1063I = usbManager;
        this.f1064J = null;
        this.f1067M = null;
        this.f1068N = null;
        this.f1069O = null;
        this.f1070R = 0;
        this.f1071S = 0;
        this.f1103ap = false;
        this.f1077Y = false;
        this.f1104aq = false;
        this.f1115d = context;
        this.f1105ar = z;
        this.f1091ad = str;
        this.f1107at = true;
        this.f1106as = FlowControl.OFF;
        m499b("067B:2303");
        m499b("067B:2304");
        m499b("067B:2551");
        m499b("067B:2503");
        m499b("067B:A100");
        m499b("067B:AAA5");
        m499b("05AD:0FBA");
        this.f1093af = this.f1092ae.size();
        this.f1112ay = 0;
        this.f1113az = 15;
        this.f1079aA = 3;
        this.f1080aB = 0;
        this.f1081aC = 0;
        this.f1082aD = 0;
        this.f1083aE = 0;
        this.f1084aF = 0;
        this.f1072T = 100;
        this.f1073U = 100;
        this.f1074V = 100;
    }

    public PL2303Driver(UsbManager usbManager, Context context, String str) {
        this.f1116e = false;
        this.f1117j = new byte[7];
        this.f1060C = 0;
        this.f1061D = (byte) 0;
        this.f1062H = 64;
        this.f1114c = new byte[4096];
        this.f1075W = new ArrayBlockingQueue<>(4096, true);
        this.f1078Z = 0;
        this.f1088aa = 0;
        this.f1089ab = false;
        this.f1090ac = false;
        this.f1092ae = new ArrayList<>();
        this.f1094ag = 0;
        this.f1095ah = 2;
        this.f1096ai = 3;
        this.f1097aj = 4;
        this.f1098ak = 5;
        this.f1099al = 6;
        this.f1100am = 7;
        this.f1101an = 8;
        this.f1102ao = 9;
        this.f1108au = true;
        this.f1109av = false;
        this.f1110aw = 17;
        this.f1111ax = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.f1085aG = false;
        this.f1086aH = new BroadcastReceiver() { // from class: tw.com.prolific.driver.pl2303.PL2303Driver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    return;
                }
                if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if (action.equals(PL2303Driver.this.f1091ad)) {
                        synchronized (this) {
                            if (intent.getBooleanExtra("permission", false) && usbDevice != null) {
                                for (int i = 0; i < PL2303Driver.this.f1093af; i++) {
                                    if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(PL2303Driver.this.f1092ae.get(i))) {
                                        PL2303Driver.this.m496b(usbDevice);
                                        return;
                                    }
                                }
                            }
                            return;
                        }
                    }
                    return;
                }
                String deviceName = usbDevice.getDeviceName();
                if (PL2303Driver.this.f1064J == null || !PL2303Driver.this.f1064J.equals(deviceName)) {
                    return;
                }
                Intent intent2 = new Intent("tw.PL2303USBMessage");
                intent2.putExtra("USB.Detached", String.valueOf(255));
                PL2303Driver.this.f1115d.sendBroadcast(intent2);
                PL2303Driver.this.end();
            }
        };
        this.f1087aI = new Runnable() { // from class: tw.com.prolific.driver.pl2303.PL2303Driver.2
            @Override // java.lang.Runnable
            public void run() {
                UsbDevice usbDevice = PL2303Driver.sDevice;
                if (PL2303Driver.this.isConnected()) {
                    return;
                }
                PL2303Driver.this.m486a(usbDevice);
                PL2303Driver.this.f1103ap = true;
            }
        };
        m487a(usbManager, context, str, true);
    }

    public PL2303Driver(UsbManager usbManager, Context context, String str, boolean z) {
        this.f1116e = false;
        this.f1117j = new byte[7];
        this.f1060C = 0;
        this.f1061D = (byte) 0;
        this.f1062H = 64;
        this.f1114c = new byte[4096];
        this.f1075W = new ArrayBlockingQueue<>(4096, true);
        this.f1078Z = 0;
        this.f1088aa = 0;
        this.f1089ab = false;
        this.f1090ac = false;
        this.f1092ae = new ArrayList<>();
        this.f1094ag = 0;
        this.f1095ah = 2;
        this.f1096ai = 3;
        this.f1097aj = 4;
        this.f1098ak = 5;
        this.f1099al = 6;
        this.f1100am = 7;
        this.f1101an = 8;
        this.f1102ao = 9;
        this.f1108au = true;
        this.f1109av = false;
        this.f1110aw = 17;
        this.f1111ax = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.f1085aG = false;
        this.f1086aH = new BroadcastReceiver() { // from class: tw.com.prolific.driver.pl2303.PL2303Driver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    return;
                }
                if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if (action.equals(PL2303Driver.this.f1091ad)) {
                        synchronized (this) {
                            if (intent.getBooleanExtra("permission", false) && usbDevice != null) {
                                for (int i = 0; i < PL2303Driver.this.f1093af; i++) {
                                    if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(PL2303Driver.this.f1092ae.get(i))) {
                                        PL2303Driver.this.m496b(usbDevice);
                                        return;
                                    }
                                }
                            }
                            return;
                        }
                    }
                    return;
                }
                String deviceName = usbDevice.getDeviceName();
                if (PL2303Driver.this.f1064J == null || !PL2303Driver.this.f1064J.equals(deviceName)) {
                    return;
                }
                Intent intent2 = new Intent("tw.PL2303USBMessage");
                intent2.putExtra("USB.Detached", String.valueOf(255));
                PL2303Driver.this.f1115d.sendBroadcast(intent2);
                PL2303Driver.this.end();
            }
        };
        this.f1087aI = new Runnable() { // from class: tw.com.prolific.driver.pl2303.PL2303Driver.2
            @Override // java.lang.Runnable
            public void run() {
                UsbDevice usbDevice = PL2303Driver.sDevice;
                if (PL2303Driver.this.isConnected()) {
                    return;
                }
                PL2303Driver.this.m486a(usbDevice);
                PL2303Driver.this.f1103ap = true;
            }
        };
        m487a(usbManager, context, str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m486a(UsbDevice usbDevice) {
        UsbDeviceConnection openDevice;
        UsbDeviceConnection usbDeviceConnection = this.f1065K;
        if (usbDeviceConnection != null) {
            UsbInterface usbInterface = this.f1066L;
            if (usbInterface != null) {
                usbDeviceConnection.releaseInterface(usbInterface);
                this.f1066L = null;
            }
            this.f1065K.close();
            this.f1064J = null;
            this.f1065K = null;
        }
        if (usbDevice == null) {
            return;
        }
        int interfaceCount = usbDevice.getInterfaceCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < interfaceCount) {
                UsbInterface usbInterface2 = usbDevice.getInterface(i);
                if (255 == usbInterface2.getInterfaceClass() && usbInterface2.getInterfaceProtocol() == 0 && usbInterface2.getInterfaceSubclass() == 0 && 3 == usbInterface2.getEndpointCount()) {
                    z = true;
                    break;
                }
                i++;
            } else {
                i = 0;
                break;
            }
        }
        if (!z) {
            Intent intent = new Intent("tw.PL2303USBMessage");
            intent.putExtra("USB.Detached", String.valueOf(255));
            this.f1115d.sendBroadcast(intent);
            end();
            return;
        }
        UsbInterface usbInterface3 = usbDevice.getInterface(i);
        if (usbDevice == null || usbInterface3 == null || (openDevice = this.f1063I.openDevice(usbDevice)) == null) {
            return;
        }
        if (openDevice.claimInterface(usbInterface3, true)) {
            this.f1064J = usbDevice;
            this.f1065K = openDevice;
            this.f1066L = usbInterface3;
            m492a(usbInterface3);
            return;
        }
        openDevice.close();
    }

    /* renamed from: a */
    boolean m520a(String str) {
        String str2 = null;
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("toolbox ls " + str).getInputStream()));
            str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str2 = String.valueOf(str2) + readLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.compareTo(str2) == 0;
    }

    /* renamed from: b */
    private boolean m499b(String str) {
        this.f1092ae.add(str);
        this.f1093af = this.f1092ae.size();
        return true;
    }

    public boolean enumerate() {
        UsbManager usbManager = (UsbManager) this.f1115d.getSystemService("usb");
        this.f1063I = usbManager;
        PendingIntent broadcast = PendingIntent.getBroadcast(this.f1115d, 0, new Intent(this.f1091ad), 0);
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            for (int i = 0; i < this.f1093af; i++) {
                if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(this.f1092ae.get(i)) && m520a(usbDevice.getDeviceName())) {
                    IntentFilter intentFilter = new IntentFilter(this.f1091ad);
                    intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
                    this.f1115d.registerReceiver(this.f1086aH, intentFilter);
                    if (!this.f1063I.hasPermission(usbDevice)) {
                        this.f1063I.requestPermission(usbDevice, broadcast);
                    } else {
                        m496b(usbDevice);
                        if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:2551") || String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:2503") || String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:A100")) {
                            this.f1104aq = true;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m496b(UsbDevice usbDevice) {
        sDevice = usbDevice;
        new Thread(this.f1087aI).start();
    }

    /* renamed from: f */
    private boolean m511f() {
        if (!this.f1103ap || this.f1064J == null || m481a(this.f1065K) < 0) {
            return false;
        }
        int i = this.f1094ag;
        if (i != 4 && i != 6) {
            return false;
        }
        if (this.f1105ar) {
            this.f1076X = new C2544a();
            return true;
        }
        this.f1076X = null;
        return true;
    }

    public boolean InitByDefualtValue() {
        if (!m511f()) {
            return false;
        }
        if (!this.f1105ar) {
            return true;
        }
        m512g();
        return true;
    }

    public boolean InitByBaudRate(BaudRate baudRate) {
        int i;
        if (!m511f()) {
            return false;
        }
        try {
            i = setup(baudRate, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
            i = 0;
        }
        if (i < 0) {
            return false;
        }
        if (!this.f1105ar) {
            return true;
        }
        m512g();
        return true;
    }

    public boolean InitByBaudRate(BaudRate baudRate, int i) {
        int i2;
        if (!m511f()) {
            return false;
        }
        try {
            i2 = setup(baudRate, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
            i2 = 0;
        }
        if (i2 < 0 || !PL2303Device_SetCommTimeouts(i)) {
            return false;
        }
        if (!this.f1105ar) {
            return true;
        }
        m512g();
        return true;
    }

    public boolean InitByPortSetting(BaudRate baudRate, DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl) {
        int i;
        if (!m511f()) {
            return false;
        }
        try {
            i = setup(baudRate, dataBits, stopBits, parity, flowControl);
        } catch (IOException e) {
            e.printStackTrace();
            i = 0;
        }
        if (i < 0) {
            return false;
        }
        if (!this.f1105ar) {
            return true;
        }
        m512g();
        return true;
    }

    public void end() {
        if (this.f1064J != null) {
            if (this.f1105ar) {
                m513h();
            }
            this.f1104aq = false;
            this.f1115d.unregisterReceiver(this.f1086aH);
            m486a((UsbDevice) null);
        }
    }

    public boolean isConnected() {
        return (this.f1064J == null || this.f1067M == null || this.f1068N == null) ? false : true;
    }

    /* renamed from: a */
    private boolean m492a(UsbInterface usbInterface) {
        if (usbInterface == null) {
            return false;
        }
        for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
            if (usbInterface.getEndpoint(i).getType() == 2) {
                if (usbInterface.getEndpoint(i).getDirection() == 128) {
                    this.f1067M = usbInterface.getEndpoint(i);
                } else {
                    this.f1068N = usbInterface.getEndpoint(i);
                }
            } else if (usbInterface.getEndpoint(i).getType() == 3 && usbInterface.getEndpoint(i).getDirection() == 128) {
                this.f1069O = usbInterface.getEndpoint(i);
            }
        }
        return true;
    }

    /* renamed from: g */
    private void m512g() {
        if (this.f1077Y) {
            return;
        }
        this.f1076X.start();
        this.f1076X.setPriority(10);
        this.f1077Y = this.f1076X.isAlive();
    }

    /* renamed from: h */
    private void m513h() {
        C2544a c2544a;
        if (!this.f1077Y || (c2544a = this.f1076X) == null) {
            return;
        }
        c2544a.m524b();
        this.f1077Y = this.f1076X.isAlive();
        this.f1076X = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: tw.com.prolific.driver.pl2303.PL2303Driver$a */
    /* loaded from: classes2.dex */
    public class C2544a extends Thread {

        /* renamed from: b */
        private int f1133b;

        /* renamed from: c */
        private int f1134c;

        /* renamed from: d */
        private boolean f1135d = true;

        /* renamed from: e */
        private boolean f1136e = false;

        /* renamed from: f */
        private AtomicInteger f1137f = new AtomicInteger(500);

        C2544a() {
        }

        /* renamed from: a */
        public void m522a() {
            this.f1134c = 0;
            this.f1133b = 0;
            PL2303Driver.this.f1075W.clear();
        }

        /* renamed from: a */
        public void m523a(int i) {
            m522a();
            m525b(i);
        }

        /* renamed from: b */
        public void m525b(int i) {
            this.f1137f.set(i);
        }

        /* renamed from: b */
        public void m524b() {
            this.f1136e = true;
            do {
            } while (isAlive());
            PL2303Driver.this.f1075W.clear();
        }

        /* renamed from: c */
        private void m521c(int i) {
            long currentTimeMillis;
            if (i == 0) {
                return;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            do {
                currentTimeMillis = System.currentTimeMillis();
                Thread.yield();
            } while (currentTimeMillis - currentTimeMillis2 <= i);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                byte[] bArr = new byte[4096];
                while (!this.f1136e) {
                    int m483a = PL2303Driver.this.m483a(bArr, 4096);
                    this.f1133b = m483a;
                    if (m483a > 0) {
                        synchronized (PL2303Driver.ReadQueueLock) {
                            int size = PL2303Driver.this.f1075W.size();
                            this.f1134c = size;
                            if (4096 != size) {
                                for (int i = 0; i < this.f1133b && this.f1134c < 4096; i++) {
                                    int intValue = Integer.valueOf(bArr[i]).intValue();
                                    if (FlowControl.XONXOFF == PL2303Driver.this.f1106as) {
                                        if (19 == intValue) {
                                            PL2303Driver.this.f1107at = false;
                                        } else if (17 == intValue) {
                                            PL2303Driver.this.f1107at = true;
                                        }
                                    }
                                    boolean offer = PL2303Driver.this.f1075W.offer(Integer.valueOf(intValue));
                                    this.f1135d = offer;
                                    if (!offer) {
                                        break;
                                    }
                                    this.f1134c = PL2303Driver.this.f1075W.size();
                                }
                            }
                        }
                    }
                    m521c(this.f1137f.get());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m489a(BaudRate baudRate) {
        int i;
        int[] iArr = {3, 5, 10, 25, 100, 200};
        int i2 = iArr[3];
        switch (m493a()[baudRate.ordinal()]) {
            case 1:
                i = 10000;
                break;
            case 2:
            case 3:
                i = iArr[5];
                break;
            case 4:
            case 5:
                i = iArr[4];
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                i = iArr[3];
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                i = iArr[2];
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                i = iArr[1];
                break;
            case 20:
            case 21:
            case 22:
            case 23:
                i = iArr[0];
                break;
            default:
                return;
        }
        C2544a c2544a = this.f1076X;
        if (c2544a != null) {
            c2544a.m525b(i);
        }
    }

    public int read(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return 0;
        }
        if (length > 4096) {
            bArr = new byte[4096];
        }
        if (this.f1105ar) {
            synchronized (ReadQueueLock) {
                int size = this.f1075W.size();
                if (size > 0) {
                    if (length >= size) {
                        length = size;
                    }
                    for (int i = 0; i < length; i++) {
                        Integer poll = this.f1075W.poll();
                        if (poll == null) {
                            break;
                        }
                        bArr[i] = (byte) (poll.intValue() & 255);
                    }
                } else {
                    length = 0;
                }
            }
            return length;
        }
        int m483a = m483a(bArr, length);
        if (m483a > 0) {
            return length >= m483a ? m483a : length;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m483a(byte[] bArr, int i) {
        if (bArr.length == 0 || i == 0) {
            return 0;
        }
        if (this.f1065K == null) {
            this.f1076X.m524b();
            return -1;
        }
        int i2 = this.f1071S;
        if (i2 > 0 && i <= i2) {
            if (!this.f1116e) {
                System.arraycopy(this.f1114c, this.f1070R, bArr, 0, i);
            } else {
                for (int i3 = 0; i3 < i; i3++) {
                    byte[] bArr2 = this.f1114c;
                    int i4 = this.f1070R;
                    this.f1070R = i4 + 1;
                    bArr[i3] = bArr2[i4];
                    this.f1078Z++;
                    while (true) {
                        int i5 = (this.f1078Z - 1) % 10;
                        byte b = bArr[i3];
                        Byte.valueOf(b);
                        if (i5 == b - 48) {
                            break;
                        }
                        this.f1078Z++;
                    }
                }
                this.f1088aa += i;
                this.f1089ab = true;
            }
            this.f1071S -= i;
            return i;
        }
        if (i2 > 0) {
            i -= i2;
            System.arraycopy(this.f1114c, this.f1070R, bArr, 0, i2);
        }
        UsbDeviceConnection usbDeviceConnection = this.f1065K;
        UsbEndpoint usbEndpoint = this.f1067M;
        byte[] bArr3 = this.f1114c;
        int bulkTransfer = usbDeviceConnection.bulkTransfer(usbEndpoint, bArr3, bArr3.length, this.f1072T);
        if (bulkTransfer < 0) {
            return bulkTransfer;
        }
        if (bulkTransfer == 0) {
            return 0;
        }
        int i6 = bulkTransfer / 64;
        if (bulkTransfer % 64 > 0) {
            i6++;
        }
        this.f1071S = bulkTransfer;
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            int i9 = i8 * 64;
            int i10 = 0;
            while (i10 < 64) {
                byte[] bArr4 = this.f1114c;
                bArr4[i7] = bArr4[i9 + i10];
                i10++;
                i7++;
            }
        }
        this.f1070R = 0;
        int i11 = 0;
        while (this.f1071S > 0 && i > 0) {
            int i12 = i11 + 1;
            byte[] bArr5 = this.f1114c;
            int i13 = this.f1070R;
            this.f1070R = i13 + 1;
            bArr[i11] = bArr5[i13];
            if (this.f1116e) {
                this.f1078Z++;
                while (true) {
                    int i14 = (this.f1078Z - 1) % 10;
                    byte b2 = bArr[i12 - 1];
                    Byte.valueOf(b2);
                    if (i14 == b2 - 48) {
                        break;
                    }
                    this.f1078Z++;
                }
            }
            this.f1071S--;
            i--;
            i11 = i12;
        }
        if (this.f1116e) {
            if (i11 > 0) {
                this.f1088aa += i11;
                this.f1089ab = true;
            }
            if (this.f1089ab) {
                this.f1089ab = false;
            }
        }
        return i11;
    }

    public int write(byte[] bArr) {
        return write(bArr, bArr.length);
    }

    public int write(byte[] bArr, int i) {
        byte[] bArr2 = new byte[4096];
        if (this.f1065K == null) {
            return -1;
        }
        if (FlowControl.XONXOFF == this.f1106as && !this.f1107at) {
            return 0;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 4096 > i ? i - i2 : 4096;
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            int bulkTransfer = this.f1065K.bulkTransfer(this.f1068N, bArr2, i3, this.f1073U);
            if (bulkTransfer < 0) {
                return -1;
            }
            i2 += bulkTransfer;
        }
        return i2;
    }

    public int setup(BaudRate baudRate, DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl) throws IOException {
        int i;
        if (this.f1065K == null) {
            return -1;
        }
        if (FlowControl.XONXOFF == this.f1106as && !this.f1107at) {
            return 0;
        }
        int controlTransfer = this.f1065K.controlTransfer(161, 33, 0, 0, this.f1117j, 7, this.f1074V);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        switch (m493a()[baudRate.ordinal()]) {
            case 1:
                i = 0;
                break;
            case 2:
                i = 75;
                break;
            case 3:
                i = 150;
                break;
            case 4:
                i = 300;
                break;
            case 5:
                i = 600;
                break;
            case 6:
                i = 1200;
                break;
            case 7:
                i = 1800;
                break;
            case 8:
                i = 2400;
                break;
            case 9:
                i = 4800;
                break;
            case 10:
                i = 9600;
                break;
            case 11:
                i = 14400;
                break;
            case 12:
                i = 19200;
                break;
            case 13:
                i = 38400;
                break;
            case 14:
                i = 57600;
                break;
            case 15:
                i = 115200;
                break;
            case 16:
                i = 230400;
                break;
            case 17:
                i = 460800;
                break;
            case 18:
                i = 614400;
                break;
            case 19:
                i = 921600;
                break;
            case 20:
                i = 1228800;
                break;
            case 21:
                i = 2457600;
                break;
            case 22:
                i = 3000000;
                break;
            case 23:
                i = 6000000;
                break;
            case 24:
                i = 12000000;
                break;
            default:
                return -2;
        }
        if (i > 1228800 && this.f1094ag == 0) {
            return -2;
        }
        if (this.f1076X != null) {
            m489a(baudRate);
        }
        byte[] bArr = this.f1117j;
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        bArr[2] = (byte) ((i >> 16) & 255);
        bArr[3] = (byte) ((i >> 24) & 255);
        int i2 = m500b()[stopBits.ordinal()];
        if (i2 == 1) {
            this.f1117j[4] = 0;
        } else {
            if (i2 != 2) {
                return -3;
            }
            this.f1117j[4] = 2;
        }
        int i3 = m503c()[parity.ordinal()];
        if (i3 == 1) {
            this.f1117j[5] = 0;
        } else if (i3 == 2) {
            this.f1117j[5] = 1;
        } else {
            if (i3 != 3) {
                return -4;
            }
            this.f1117j[5] = 2;
        }
        int i4 = m507d()[dataBits.ordinal()];
        if (i4 == 1) {
            this.f1117j[6] = 5;
        } else if (i4 == 2) {
            this.f1117j[6] = 6;
        } else if (i4 == 3) {
            this.f1117j[6] = 7;
        } else {
            if (i4 != 4) {
                return -5;
            }
            this.f1117j[6] = 8;
        }
        int controlTransfer2 = this.f1065K.controlTransfer(33, 32, 0, 0, this.f1117j, 7, this.f1074V);
        if (controlTransfer2 < 0) {
            return controlTransfer2;
        }
        int controlTransfer3 = this.f1065K.controlTransfer(33, 35, 0, 0, null, 0, this.f1074V);
        if (controlTransfer3 < 0) {
            return controlTransfer3;
        }
        switch (m509e()[flowControl.ordinal()]) {
            case 1:
                int controlTransfer4 = this.f1065K.controlTransfer(64, 1, 0, 0, null, 0, this.f1074V);
                if (controlTransfer4 < 0) {
                    return controlTransfer4;
                }
                int controlTransfer5 = this.f1065K.controlTransfer(64, 1, 1, 0, null, 0, this.f1074V);
                if (controlTransfer5 < 0) {
                    return controlTransfer5;
                }
                int controlTransfer6 = this.f1065K.controlTransfer(64, 1, 2, 68, null, 0, this.f1074V);
                if (controlTransfer6 < 0) {
                    return controlTransfer6;
                }
                break;
            case 2:
                int controlTransfer7 = this.f1065K.controlTransfer(64, 1, 0, 97, null, 0, this.f1074V);
                if (controlTransfer7 < 0) {
                    return controlTransfer7;
                }
                int controlTransfer8 = this.f1065K.controlTransfer(64, 1, 1, 0, null, 0, this.f1074V);
                if (controlTransfer8 < 0) {
                    return controlTransfer8;
                }
                int controlTransfer9 = this.f1065K.controlTransfer(64, 1, 2, 68, null, 0, this.f1074V);
                if (controlTransfer9 < 0) {
                    return controlTransfer9;
                }
                break;
            case 3:
                break;
            case 4:
                if (this.f1094ag == 4) {
                    int controlTransfer10 = this.f1065K.controlTransfer(64, 1, 0, 73, null, 0, this.f1074V);
                    if (controlTransfer10 < 0) {
                        return controlTransfer10;
                    }
                    int controlTransfer11 = this.f1065K.controlTransfer(64, 1, 1, 5, null, 0, this.f1074V);
                    if (controlTransfer11 < 0) {
                        return controlTransfer11;
                    }
                    int controlTransfer12 = this.f1065K.controlTransfer(64, 1, 2, 68, null, 0, this.f1074V);
                    if (controlTransfer12 < 0) {
                        return controlTransfer12;
                    }
                }
                break;
            case 5:
                if (this.f1094ag == 4) {
                    int controlTransfer13 = this.f1065K.controlTransfer(64, 1, 0, 105, null, 0, this.f1074V);
                    if (controlTransfer13 < 0) {
                        return controlTransfer13;
                    }
                    int controlTransfer14 = this.f1065K.controlTransfer(64, 1, 1, 7, null, 0, this.f1074V);
                    if (controlTransfer14 < 0) {
                        return controlTransfer14;
                    }
                    int controlTransfer15 = this.f1065K.controlTransfer(64, 1, 2, 68, null, 0, this.f1074V);
                    if (controlTransfer15 < 0) {
                        return controlTransfer15;
                    }
                }
                break;
            case 6:
                int controlTransfer16 = this.f1065K.controlTransfer(64, 1, 0, 193, null, 0, this.f1074V);
                if (controlTransfer16 < 0) {
                    return controlTransfer16;
                }
                int controlTransfer17 = this.f1065K.controlTransfer(64, 1, 1, 0, null, 0, this.f1074V);
                if (controlTransfer17 < 0) {
                    return controlTransfer17;
                }
                int controlTransfer18 = this.f1065K.controlTransfer(64, 1, 2, 68, null, 0, this.f1074V);
                if (controlTransfer18 < 0) {
                    return controlTransfer18;
                }
                break;
            default:
                return -6;
        }
        this.f1106as = flowControl;
        if (this.f1090ac) {
            int m480a = m480a(0, 49);
            if (m480a < 0) {
                return m480a;
            }
            int m480a2 = m480a(1, 8);
            if (m480a2 < 0) {
                return m480a2;
            }
        }
        return 0;
    }

    public int setDTR(boolean z) {
        if (z) {
            int i = this.f1060C;
            if ((i & 1) != 1) {
                this.f1060C = i + 1;
            }
        }
        if (!z) {
            int i2 = this.f1060C;
            if ((i2 & 1) == 1) {
                this.f1060C = i2 - 1;
            }
        }
        int controlTransfer = this.f1065K.controlTransfer(33, 34, this.f1060C, 0, null, 0, this.f1074V);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    public int setRTS(boolean z) {
        if (z) {
            int i = this.f1060C;
            if ((i & 2) != 2) {
                this.f1060C = i + 2;
            }
        }
        if (!z) {
            int i2 = this.f1060C;
            if ((i2 & 2) == 2) {
                this.f1060C = i2 - 2;
            }
        }
        int controlTransfer = this.f1065K.controlTransfer(33, 34, this.f1060C, 0, null, 0, this.f1074V);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    /* renamed from: a */
    private int m481a(UsbDeviceConnection usbDeviceConnection) {
        int m515j;
        int m518m;
        int[] iArr = {0};
        if (this.f1104aq) {
            this.f1094ag = 4;
            m515j = 0;
        } else {
            if (!m517l() && (m518m = m518m()) < 0) {
                return m518m;
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == 4) {
                this.f1094ag = 4;
            }
            m515j = m515j();
            if (m515j < 0) {
                return m515j;
            }
            if (this.f1085aG && (m515j = m516k()) < 0) {
                return m515j;
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == 5 && (m515j = m514i()) < 0) {
                return m515j;
            }
        }
        int i = this.f1094ag;
        if (i != 4 && i != 6) {
            return -1;
        }
        for (int i2 = 128; i2 <= 130; i2++) {
            iArr = m501b(i2);
            if (iArr[0] < 0) {
                return iArr[0];
            }
        }
        try {
            m515j = setup(BaudRate.B9600, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (iArr[0] < 0) {
            return m515j;
        }
        return 0;
    }

    /* renamed from: i */
    private int m514i() {
        int[] m501b = m501b(148);
        if (m501b[0] < 0) {
            return m501b[0];
        }
        if ((m501b[1] & 148) == 148) {
            this.f1094ag = 6;
        } else {
            this.f1094ag = 2;
        }
        return 0;
    }

    /* renamed from: j */
    private int m515j() {
        int[] iArr = new int[2];
        int[] m501b = m501b(129);
        if (m501b[0] < 0) {
            return m501b[0];
        }
        int i = m501b[1];
        m501b[0] = m480a(1, 255);
        if (m501b[0] < 0) {
            return m501b[0];
        }
        int[] m501b2 = m501b(129);
        if (m501b2[0] < 0) {
            return m501b2[0];
        }
        if ((m501b2[1] & 15) == 15) {
            this.f1094ag = 4;
            int[] m504c = m504c(250);
            if (m504c[0] < 0) {
                return m504c[0];
            }
            iArr[0] = m504c[1];
            int[] m504c2 = m504c(251);
            if (m504c2[0] < 0) {
                return m504c2[0];
            }
            iArr[1] = m504c2[1];
            if ((iArr[0] != 1 || iArr[1] != 4) && ((iArr[0] != 2 || iArr[1] != 4) && ((iArr[0] != 3 || iArr[1] != 4) && iArr[0] == 1 && iArr[1] == 3))) {
                this.f1094ag = 2;
            }
            m501b2 = m504c2;
        } else {
            this.f1094ag = 2;
        }
        m501b2[0] = m480a(1, i);
        if (m501b2[0] < 0) {
            return m501b2[0];
        }
        return 0;
    }

    /* renamed from: a */
    private String m485a(int i) {
        return new String(new char[]{Character.forDigit((i >> 4) & 15, 16), Character.forDigit(i & 15, 16)});
    }

    /* renamed from: b */
    private static String m495b(byte[] bArr, int i) throws NoSuchAlgorithmException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] digest = null;
        StringBuffer stringBuffer2 = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            digest = messageDigest.digest(bArr);
            stringBuffer = new StringBuffer();
        } catch (NoSuchAlgorithmException e) {
           throw e;
        }

            for (byte b : digest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
            }

        return stringBuffer.toString();
    }

    /* renamed from: k */
    private int m516k() {
        int[] m504c = m504c(9);
        if (m504c[0] < 0) {
            return m504c[0];
        }
        if ((m504c[1] & 8) == 8) {
            m504c[0] = m480a(0, 49);
            if (m504c[0] < 0) {
                return m504c[0];
            }
            m504c[0] = m480a(1, 8);
            if (m504c[0] < 0) {
                return m504c[0];
            }
            this.f1090ac = true;
        }
        return m504c[0];
    }

    public int PL2303TB_Set_PWM(int i, byte b, byte b2) {
        int i2;
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            return -1;
        }
        int i3 = ((b2 & 255) << 8) + b;
        if (i == 0) {
            iArr[0] = m480a(2, 0);
            if (iArr[0] < 0) {
                return iArr[0];
            }
            i2 = 16;
        } else if (i == 1) {
            i2 = 17;
        } else if (i == 2) {
            i2 = 18;
        } else {
            if (i != 3) {
                return -1;
            }
            i2 = 19;
        }
        iArr[0] = m480a(i2, i3);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303TB_Enable_GPIO(int i, boolean z) {
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            return -1;
        }
        if (i == 6 || i == 7 || i == 9) {
            iArr[0] = m480a(2, 0);
            if (iArr[0] < 0) {
                return iArr[0];
            }
        }
        switch (i) {
            case 0:
                if (z) {
                    this.f1083aE |= 1;
                    break;
                } else {
                    this.f1083aE &= -2;
                    break;
                }
            case 1:
                if (z) {
                    this.f1083aE |= 2;
                    break;
                } else {
                    this.f1083aE &= -3;
                    break;
                }
            case 2:
                if (z) {
                    this.f1083aE |= 4;
                    break;
                } else {
                    this.f1083aE &= -5;
                    break;
                }
            case 3:
                if (z) {
                    this.f1083aE |= 8;
                    break;
                } else {
                    this.f1083aE &= -9;
                    break;
                }
            case 4:
                if (z) {
                    this.f1083aE |= 16;
                    break;
                } else {
                    this.f1083aE &= -17;
                    break;
                }
            case 5:
                if (z) {
                    this.f1083aE |= 32;
                    break;
                } else {
                    this.f1083aE &= -33;
                    break;
                }
            case 6:
                if (z) {
                    this.f1083aE |= 64;
                    break;
                } else {
                    this.f1083aE &= -65;
                    break;
                }
            case 7:
                if (z) {
                    this.f1083aE |= 128;
                    break;
                } else {
                    this.f1083aE &= -129;
                    break;
                }
            case 8:
                if (z) {
                    this.f1083aE |= 256;
                    break;
                } else {
                    this.f1083aE &= -257;
                    break;
                }
            case 9:
                if (z) {
                    this.f1083aE |= 512;
                    break;
                } else {
                    this.f1083aE &= -513;
                    break;
                }
            case 10:
                if (z) {
                    this.f1083aE |= 1024;
                    break;
                } else {
                    this.f1083aE &= -1025;
                    break;
                }
            case 11:
                if (z) {
                    this.f1083aE |= 2048;
                    break;
                } else {
                    this.f1083aE &= -2049;
                    break;
                }
            default:
                return -1;
        }
        iArr[0] = m480a(14, this.f1083aE);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303HXD_Enable_GPIO(int i, boolean z) {
        int i2;
        int i3;
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            return -1;
        }
        switch (i) {
            case 0:
                int[] m501b = m501b(129);
                if (m501b[0] < 0) {
                    return m501b[0];
                }
                if (z) {
                    i2 = m501b[1] | 16;
                    m501b[1] = i2;
                } else {
                    i2 = m501b[1] & (-17);
                    m501b[1] = i2;
                }
                m501b[0] = m480a(1, i2);
                if (m501b[0] < 0) {
                    return m501b[0];
                }
                return 0;
            case 1:
                int[] m501b2 = m501b(129);
                if (m501b2[0] < 0) {
                    return m501b2[0];
                }
                if (z) {
                    i3 = m501b2[1] | 32;
                    m501b2[1] = i3;
                } else {
                    i3 = m501b2[1] & (-33);
                    m501b2[1] = i3;
                }
                m501b2[0] = m480a(1, i3);
                if (m501b2[0] < 0) {
                    return m501b2[0];
                }
                return 0;
            case 2:
                if (z) {
                    this.f1113az |= 3;
                } else {
                    this.f1113az &= -4;
                }
                iArr[0] = m480a(12, this.f1113az);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            case 3:
                if (z) {
                    this.f1113az |= 12;
                } else {
                    this.f1113az &= -13;
                }
                iArr[0] = m480a(12, this.f1113az);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            case 4:
                if (z) {
                    this.f1080aB |= 3;
                } else {
                    this.f1080aB &= -4;
                }
                iArr[0] = m480a(6, this.f1080aB);
                return 0;
            case 5:
                if (z) {
                    this.f1080aB |= 12;
                } else {
                    this.f1080aB &= -13;
                }
                iArr[0] = m480a(6, this.f1080aB);
                return 0;
            case 6:
                if (z) {
                    this.f1080aB |= 48;
                } else {
                    this.f1080aB &= -49;
                }
                iArr[0] = m480a(6, this.f1080aB);
                return 0;
            case 7:
                if (z) {
                    this.f1080aB |= 192;
                } else {
                    this.f1080aB &= -193;
                }
                iArr[0] = m480a(6, this.f1080aB);
                return 0;
            default:
                return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0042, code lost:
    
        if (r7 == 1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0045, code lost:
    
        if (r7 == 1) goto L37;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0060 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int PL2303TB_Set_GPIO_Value(int r6, int r7) {
        /*
            r5 = this;
            android.hardware.usb.UsbDeviceConnection r0 = r5.f1065K
            r1 = -1
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 143(0x8f, float:2.0E-43)
            int[] r0 = r5.m501b(r0)
            r2 = 0
            r3 = r0[r2]
            if (r3 >= 0) goto L14
            r6 = r0[r2]
            return r6
        L14:
            r3 = 4
            r4 = 1
            switch(r6) {
                case 0: goto L4c;
                case 1: goto L48;
                case 2: goto L45;
                case 3: goto L42;
                case 4: goto L3d;
                case 5: goto L38;
                case 6: goto L33;
                case 7: goto L2e;
                case 8: goto L29;
                case 9: goto L24;
                case 10: goto L1f;
                case 11: goto L1a;
                default: goto L19;
            }
        L19:
            return r1
        L1a:
            if (r7 != r4) goto L50
            r3 = 2048(0x800, float:2.87E-42)
            goto L51
        L1f:
            if (r7 != r4) goto L50
            r3 = 1024(0x400, float:1.435E-42)
            goto L51
        L24:
            if (r7 != r4) goto L50
            r3 = 512(0x200, float:7.17E-43)
            goto L51
        L29:
            if (r7 != r4) goto L50
            r3 = 256(0x100, float:3.59E-43)
            goto L51
        L2e:
            if (r7 != r4) goto L50
            r3 = 128(0x80, float:1.8E-43)
            goto L51
        L33:
            if (r7 != r4) goto L50
            r3 = 64
            goto L51
        L38:
            if (r7 != r4) goto L50
            r3 = 32
            goto L51
        L3d:
            if (r7 != r4) goto L50
            r3 = 16
            goto L51
        L42:
            if (r7 != r4) goto L50
            goto L51
        L45:
            if (r7 != r4) goto L50
            goto L51
        L48:
            if (r7 != r4) goto L50
            r3 = 2
            goto L51
        L4c:
            if (r7 != r4) goto L50
            r3 = 1
            goto L51
        L50:
            r3 = 0
        L51:
            r6 = 15
            int r6 = r5.m480a(r6, r3)
            r0[r2] = r6
            r6 = r0[r2]
            if (r6 >= 0) goto L60
            r6 = r0[r2]
            return r6
        L60:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: tw.com.prolific.driver.pl2303.PL2303Driver.PL2303TB_Set_GPIO_Value(int, int):int");
    }

    public int PL2303HXD_Set_GPIO_Value(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            return -1;
        }
        switch (i) {
            case 0:
                int[] m501b = m501b(129);
                if (m501b[0] < 0) {
                    return m501b[0];
                }
                if (i2 == 1) {
                    i3 = m501b[1] | 64;
                    m501b[1] = i3;
                } else {
                    i3 = m501b[1] & (-65);
                    m501b[1] = i3;
                }
                m501b[0] = m480a(1, i3);
                if (m501b[0] < 0) {
                    return m501b[0];
                }
                return 0;
            case 1:
                int[] m501b2 = m501b(129);
                if (m501b2[0] < 0) {
                    return m501b2[0];
                }
                if (i2 == 1) {
                    i4 = m501b2[1] | 128;
                    m501b2[1] = i4;
                } else {
                    i4 = m501b2[1] & (-129);
                    m501b2[1] = i4;
                }
                m501b2[0] = m480a(1, i4);
                if (m501b2[0] < 0) {
                    return m501b2[0];
                }
                return 0;
            case 2:
                int[] m501b3 = m501b(141);
                if (m501b3[0] < 0) {
                    return m501b3[0];
                }
                if (i2 == 1) {
                    i5 = m501b3[1] | 1;
                    m501b3[1] = i5;
                } else {
                    i5 = m501b3[1] & (-2);
                    m501b3[1] = i5;
                }
                m501b3[0] = m480a(13, i5);
                if (m501b3[0] < 0) {
                    return m501b3[0];
                }
                return 0;
            case 3:
                int[] m501b4 = m501b(141);
                if (m501b4[0] < 0) {
                    return m501b4[0];
                }
                if (i2 == 1) {
                    i6 = m501b4[1] | 2;
                    m501b4[1] = i6;
                } else {
                    i6 = m501b4[1] & (-3);
                    m501b4[1] = i6;
                }
                m501b4[0] = m480a(13, i6);
                if (m501b4[0] < 0) {
                    return m501b4[0];
                }
                return 0;
            case 4:
                int i7 = this.f1082aD;
                if (i7 == 0) {
                    this.f1081aC = 0;
                } else {
                    this.f1081aC = i7;
                }
                if (i2 == 1) {
                    this.f1081aC |= 1;
                } else {
                    this.f1081aC &= -2;
                }
                int i8 = this.f1081aC;
                this.f1082aD = i8;
                iArr[0] = m480a(7, i8);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            case 5:
                int i9 = this.f1082aD;
                if (i9 == 0) {
                    this.f1081aC = 0;
                } else {
                    this.f1081aC = i9;
                }
                if (i2 == 1) {
                    this.f1081aC |= 2;
                } else {
                    this.f1081aC &= -3;
                }
                int i10 = this.f1081aC;
                this.f1082aD = i10;
                iArr[0] = m480a(7, i10);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            case 6:
                int i11 = this.f1082aD;
                if (i11 == 0) {
                    this.f1081aC = 0;
                } else {
                    this.f1081aC = i11;
                }
                if (i2 == 1) {
                    this.f1081aC |= 4;
                } else {
                    this.f1081aC &= -5;
                }
                int i12 = this.f1081aC;
                this.f1082aD = i12;
                iArr[0] = m480a(7, i12);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            case 7:
                int i13 = this.f1082aD;
                if (i13 == 0) {
                    this.f1081aC = 0;
                } else {
                    this.f1081aC = i13;
                }
                if (i2 == 1) {
                    this.f1081aC |= 8;
                } else {
                    this.f1081aC &= -9;
                }
                int i14 = this.f1081aC;
                this.f1082aD = i14;
                iArr[0] = m480a(7, i14);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                return 0;
            default:
                return -1;
        }
    }

    public int[] PL2303TB_Get_GPIO_Value(int i) {
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            iArr[0] = -1;
            return iArr;
        }
        int[] m501b = m501b(143);
        if (m501b[0] < 0) {
            return m501b;
        }
        switch (i) {
            case 0:
                if ((m501b[1] & 1) == 1) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 1:
                if ((m501b[1] & 2) == 2) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 2:
                if ((m501b[1] & 4) == 4) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 3:
                if ((m501b[1] & 8) == 8) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 4:
                if ((m501b[1] & 16) == 16) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 5:
                if ((m501b[1] & 32) == 32) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 6:
                if ((m501b[1] & 64) == 64) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 7:
                if ((m501b[1] & 128) == 128) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 8:
                if ((m501b[1] & 256) == 256) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 9:
                if ((m501b[1] & 512) == 512) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 10:
                if ((m501b[1] & 1024) == 1024) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 11:
                if ((m501b[1] & 2048) == 2048) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            default:
                m501b[0] = -1;
                return m501b;
        }
    }

    public int[] PL2303HXD_Get_GPIO_Value(int i) {
        int[] m501b;
        int[] iArr = new int[2];
        if (this.f1065K == null) {
            iArr[0] = -1;
            return iArr;
        }
        switch (i) {
            case 0:
                m501b = m501b(129);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 64) == 64) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 1:
                m501b = m501b(129);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 128) == 128) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 2:
                m501b = m501b(141);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 1) == 1) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 3:
                m501b = m501b(141);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 2) == 2) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 4:
                m501b = m501b(135);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 1) == 1) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 5:
                m501b = m501b(135);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 2) == 2) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 6:
                m501b = m501b(135);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 4) == 4) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            case 7:
                m501b = m501b(135);
                if (m501b[0] < 0) {
                    return m501b;
                }
                if ((m501b[1] & 8) == 8) {
                    m501b[1] = 1;
                } else {
                    m501b[1] = 0;
                }
                return m501b;
            default:
                iArr[0] = -1;
                return iArr;
        }
    }

    /* renamed from: a */
    private int m480a(int i, int i2) {
        UsbDeviceConnection usbDeviceConnection = this.f1065K;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(64, 1, i, i2, null, 0, this.f1074V);
    }

    /* renamed from: b */
    private int[] m501b(int i) {
        byte[] bArr = new byte[1];
        int[] iArr = {0};
        UsbDeviceConnection usbDeviceConnection = this.f1065K;
        if (usbDeviceConnection == null) {
            iArr[0] = -1;
            return iArr;
        }
        iArr[0] = usbDeviceConnection.controlTransfer(192, 1, i, 0, bArr, 1, this.f1074V);
        if (iArr[0] < 0) {
            return iArr;
        }
        iArr[1] = bArr[0];
        return iArr;
    }

    /* renamed from: c */
    private int[] m504c(int i) {
        int[] m501b = m501b(132);
        if (m501b[0] < 0) {
            return m501b;
        }
        m501b[0] = m480a(4, i);
        if (m501b[0] < 0) {
            return m501b;
        }
        int[] m501b2 = m501b(132);
        if (m501b2[0] < 0) {
            return m501b2;
        }
        int[] m501b3 = m501b(131);
        int i2 = m501b3[0];
        return m501b3;
    }

    /* renamed from: l */
    private boolean m517l() {
        int[] iArr = new int[2];
        for (int i = 0; i < 2; i++) {
            int[] m504c = m504c(i);
            if (m504c[0] < 0) {
                return this.f1085aG;
            }
            iArr[i] = m504c[1];
        }
        if (iArr[0] == 123 && iArr[1] == 6) {
            this.f1085aG = true;
        }
        return this.f1085aG;
    }

    /* renamed from: m */
    private int m518m() {
        int[] iArr = {1, 0, 68};
        int[] iArr2 = {m480a(f1026A, 0)};
        if (iArr2[0] < 0) {
            return iArr2[0];
        }
        iArr2[0] = m480a(f1027B, 0);
        if (iArr2[0] < 0) {
            return iArr2[0];
        }
        for (int i = 0; i <= 2; i++) {
            iArr2[0] = m480a(i, iArr[i]);
            if (iArr2[0] < 0) {
                return iArr2[0];
            }
        }
        for (int i2 = 128; i2 <= 130; i2++) {
            iArr2 = m501b(i2);
            if (iArr2[0] < 0) {
                return iArr2[0];
            }
        }
        return iArr2[0];
    }

    /* renamed from: d */
    private int m505d(int i) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        UsbDeviceConnection usbDeviceConnection = this.f1065K;
        if (usbDeviceConnection == null) {
            return -1;
        }
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        int controlTransfer = usbDeviceConnection.controlTransfer(161, 32, 0, 0, bArr2, 2, this.f1073U);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.f1065K.bulkTransfer(this.f1069O, bArr, 2, this.f1072T) < 0) {
            return 0;
        }
        return (bArr[1] << 8) | bArr[0];
    }

    /* renamed from: n */
    private int[] m519n() {
        int[] m501b = m501b(135);
        int i = m501b[0];
        return m501b;
    }

    public int[] PL2303HXD_GetCommModemStatus() {
        int[] m519n = m519n();
        if (m519n[0] < 0) {
            return m519n;
        }
        int i = (m519n[1] & 1) != 1 ? 8 : 0;
        int i2 = (m519n[1] & 2) == 2 ? i & (-2) : i | 1;
        int i3 = (m519n[1] & 4) == 4 ? i2 & (-3) : i2 | 2;
        m519n[1] = (m519n[1] & 8) == 8 ? i3 & (-129) : i3 | 128;
        return m519n;
    }

    public void PL2303LibGetVersion(byte[] bArr) {
        int length;
        if (bArr.length < f1043i.length()) {
            length = bArr.length;
        } else {
            length = f1043i.length();
        }
        char[] charArray = f1043i.toCharArray();
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) charArray[i];
        }
    }

    public boolean PL2303USBFeatureSupported() {
        return this.f1115d.getPackageManager().hasSystemFeature("android.hardware.usb.host");
    }

    public String PL2303Device_GetSerialNumber() {
        if (isConnected()) {
            return this.f1065K.getSerial();
        }
        return null;
    }

    public boolean PL2303Device_IsHasPermission() {
        return this.f1103ap;
    }

    public boolean PL2303Device_IsSupportChip() {
        return this.f1094ag == 4;
    }

    public boolean PL2303Device_SetCommTimeouts(int i) {
        if (i < 0) {
            return false;
        }
        this.f1072T = i;
        this.f1073U = i;
        return true;
    }
}
