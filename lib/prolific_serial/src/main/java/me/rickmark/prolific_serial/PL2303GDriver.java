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
public class PL2303GDriver<UsbConfiguration> {

    /* renamed from: A */
    private static final int f1138A = 0;

    /* renamed from: B */
    private static final int f1139B = 1;
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

    /* renamed from: C */
    private static final int f1140C = 2;

    /* renamed from: D */
    private static final int f1141D = 2056;

    /* renamed from: E */
    private static final int f1142E = 2313;

    /* renamed from: H */
    private static final int f1143H = 1;

    /* renamed from: I */
    private static final int f1144I = 2;

    /* renamed from: J */
    private static final int f1145J = 32;
    public static final int PL2303G_CTS_ON = 128;
    public static final int PL2303G_DCD_ON = 1;
    public static final int PL2303G_DSR_ON = 2;
    public static final int PL2303G_RI_ON = 8;
    public static final int PL_MAX_INTERFACE_NUM = 4;
    public static final int READBUF_SIZE = 4096;
    public static Object ReadQueueLock = new Object();

    /* renamed from: S */
    private static final boolean f1146S = false;

    /* renamed from: T */
    private static final boolean f1147T = false;
    public static final int WRITEBUF_SIZE = 4096;

    /* renamed from: a */
    static final int f1148a = 11;

    /* renamed from: aQ */
    private static /* synthetic */ int[] f1149aQ = null;

    /* renamed from: aR */
    private static /* synthetic */ int[] f1150aR = null;

    /* renamed from: aS */
    private static /* synthetic */ int[] f1151aS = null;

    /* renamed from: aT */
    private static /* synthetic */ int[] f1152aT = null;

    /* renamed from: aU */
    private static /* synthetic */ int[] f1153aU = null;

    /* renamed from: b */
    static final int f1154b = 12;

    /* renamed from: f */
    private static final boolean f1155f = false;

    /* renamed from: g */
    private static final boolean f1156g = false;

    /* renamed from: h */
    private static final boolean f1157h = false;

    /* renamed from: i */
    private static String f1158i = "1.0.0.0";

    /* renamed from: k */
    private static final int f1159k = 33;

    /* renamed from: l */
    private static final int f1160l = 32;

    /* renamed from: m */
    private static final int f1161m = 33;

    /* renamed from: n */
    private static final int f1162n = 35;

    /* renamed from: o */
    private static final int f1163o = 0;

    /* renamed from: p */
    private static final int f1164p = 161;

    /* renamed from: q */
    private static final int f1165q = 33;

    /* renamed from: r */
    private static final int f1166r = 64;

    /* renamed from: s */
    private static final int f1167s = 192;
    public static UsbDevice sDevice = null;

    /* renamed from: t */
    private static final int f1168t = 33;

    /* renamed from: u */
    private static final int f1169u = 34;

    /* renamed from: v */
    private static final int f1170v = 128;

    /* renamed from: w */
    private static final int f1171w = 129;

    /* renamed from: x */
    private static final int f1172x = 130;

    /* renamed from: y */
    private static final int f1173y = 130;

    /* renamed from: z */
    private static final int f1174z = 131;

    /* renamed from: F */
    private int f1175F;

    /* renamed from: G */
    private byte f1176G;

    /* renamed from: K */
    private final int f1177K;

    /* renamed from: L */
    private UsbManager f1178L;

    /* renamed from: M */
    private UsbDevice f1179M;

    /* renamed from: N */
    private UsbDeviceConnection f1180N;

    /* renamed from: O */
    private UsbInterface f1181O;

    /* renamed from: P */
    private UsbEndpoint f1182P;
    public final int PLDETACHED_VALUE;
    public final String PLUART_DETACHED;
    public final String PLUART_MESSAGE;

    /* renamed from: Q */
    private UsbEndpoint f1183Q;

    /* renamed from: R */
    private UsbEndpoint f1184R;

    /* renamed from: U */
    private int f1185U;

    /* renamed from: V */
    private int f1186V;

    /* renamed from: W */
    private int f1187W;

    /* renamed from: X */
    private int f1188X;

    /* renamed from: Y */
    private int f1189Y;

    /* renamed from: Z */
    private ArrayBlockingQueue<Integer> f1190Z;

    /* renamed from: aA */
    private final int f1191aA;

    /* renamed from: aB */
    private final int f1192aB;

    /* renamed from: aC */
    private int f1193aC;

    /* renamed from: aD */
    private int f1194aD;

    /* renamed from: aE */
    private int f1195aE;

    /* renamed from: aF */
    private int f1196aF;

    /* renamed from: aG */
    private int f1197aG;

    /* renamed from: aH */
    private int f1198aH;

    /* renamed from: aI */
    private int f1199aI;

    /* renamed from: aJ */
    private int f1200aJ;

    /* renamed from: aK */
    private boolean f1201aK;

    /* renamed from: aL */
    private boolean f1202aL;

    /* renamed from: aM */
    private int f1203aM;

    /* renamed from: aN */
    private int f1204aN;

    /* renamed from: aO */
    private final BroadcastReceiver f1205aO;

    /* renamed from: aP */
    private Runnable f1206aP;

    /* renamed from: aa */
    private C2547a f1207aa;

    /* renamed from: ab */
    private boolean f1208ab;

    /* renamed from: ac */
    private int f1209ac;

    /* renamed from: ad */
    private int f1210ad;

    /* renamed from: ae */
    private boolean f1211ae;

    /* renamed from: af */
    private boolean f1212af;

    /* renamed from: ag */
    private String f1213ag;

    /* renamed from: ah */
    private ArrayList<String> f1214ah;

    /* renamed from: ai */
    private int f1215ai;

    /* renamed from: aj */
    private int f1216aj;

    /* renamed from: ak */
    private final int f1217ak;

    /* renamed from: al */
    private final int f1218al;

    /* renamed from: am */
    private final int f1219am;

    /* renamed from: an */
    private final int f1220an;

    /* renamed from: ao */
    private final int f1221ao;

    /* renamed from: ap */
    private final int f1222ap;

    /* renamed from: aq */
    private final int f1223aq;

    /* renamed from: ar */
    private final int f1224ar;

    /* renamed from: as */
    private final int f1225as;

    /* renamed from: at */
    private boolean f1226at;

    /* renamed from: au */
    private boolean f1227au;

    /* renamed from: av */
    private boolean f1228av;

    /* renamed from: aw */
    private FlowControl f1229aw;

    /* renamed from: ax */
    private boolean f1230ax;

    /* renamed from: ay */
    private final boolean f1231ay;

    /* renamed from: az */
    private final boolean f1232az;

    /* renamed from: c */
    byte[] f1233c;

    /* renamed from: d */
    Context f1234d;

    /* renamed from: e */
    private boolean f1235e;

    /* renamed from: j */
    private byte[] f1236j;

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
    private static void m534a(Object obj) {
    }

    public boolean PL2303Device_GetCommTimeouts(int i) {
        return true;
    }

    /* renamed from: a */
    static /* synthetic */ int[] m539a() {
        int[] iArr = f1149aQ;
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
        f1149aQ = iArr2;
        return iArr2;
    }

    /* renamed from: b */
    static /* synthetic */ int[] m547b() {
        int[] iArr = f1150aR;
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
        f1150aR = iArr2;
        return iArr2;
    }

    /* renamed from: c */
    static /* synthetic */ int[] m551c() {
        int[] iArr = f1151aS;
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
        f1151aS = iArr2;
        return iArr2;
    }

    /* renamed from: d */
    static /* synthetic */ int[] m555d() {
        int[] iArr = f1152aT;
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
        f1152aT = iArr2;
        return iArr2;
    }

    /* renamed from: e */
    static /* synthetic */ int[] m557e() {
        int[] iArr = f1153aU;
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
        f1153aU = iArr2;
        return iArr2;
    }

    /* renamed from: a */
    private void m533a(UsbManager usbManager, Context context, String str, boolean z) {
        this.f1178L = usbManager;
        this.f1179M = null;
        this.f1182P = null;
        this.f1183Q = null;
        this.f1184R = null;
        this.f1185U = 0;
        this.f1186V = 0;
        this.f1226at = false;
        this.f1208ab = false;
        this.f1227au = false;
        this.f1234d = context;
        this.f1228av = z;
        this.f1213ag = str;
        this.f1230ax = true;
        this.f1229aw = FlowControl.OFF;
        m546b("067B:23A3");
        m546b("067B:23B3");
        m546b("067B:23C3");
        m546b("067B:23D3");
        m546b("067B:23E3");
        m546b("067B:2320");
        m546b("067B:2321");
        m546b("067B:2322");
        m546b("067B:2323");
        this.f1215ai = this.f1214ah.size();
        this.f1193aC = 0;
        this.f1194aD = 15;
        this.f1195aE = 3;
        this.f1196aF = 0;
        this.f1197aG = 0;
        this.f1198aH = 0;
        this.f1199aI = 0;
        this.f1200aJ = 0;
        this.f1187W = 100;
        this.f1188X = 100;
        this.f1189Y = 100;
    }

    public PL2303GDriver(UsbManager usbManager, Context context, String str) {
        this.f1235e = false;
        this.f1236j = new byte[7];
        this.f1175F = 0;
        this.f1176G = (byte) 0;
        this.f1177K = 64;
        this.f1233c = new byte[4096];
        this.f1190Z = new ArrayBlockingQueue<>(4096, true);
        this.f1209ac = 0;
        this.f1210ad = 0;
        this.f1211ae = false;
        this.f1212af = false;
        this.f1214ah = new ArrayList<>();
        this.f1216aj = 0;
        this.f1217ak = 2;
        this.f1218al = 3;
        this.f1219am = 4;
        this.f1220an = 5;
        this.f1221ao = 6;
        this.f1222ap = 7;
        this.f1223aq = 8;
        this.f1224ar = 9;
        this.f1225as = 10;
        this.f1231ay = true;
        this.f1232az = false;
        this.f1191aA = 17;
        this.f1192aB = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.f1201aK = false;
        this.f1202aL = false;
        this.f1203aM = 0;
        this.f1204aN = 0;
        this.f1205aO = new BroadcastReceiver() { // from class: tw.com.prolific.driver.pl2303g.PL2303GDriver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    return;
                }
                if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if (action.equals(PL2303GDriver.this.f1213ag)) {
                        synchronized (this) {
                            if (intent.getBooleanExtra("permission", false) && usbDevice != null) {
                                for (int i = 0; i < PL2303GDriver.this.f1215ai; i++) {
                                    if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(PL2303GDriver.this.f1214ah.get(i))) {
                                        PL2303GDriver.this.m543b(usbDevice);
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
                if (PL2303GDriver.this.f1179M == null || !PL2303GDriver.this.f1179M.equals(deviceName)) {
                    return;
                }
                Intent intent2 = new Intent("tw.PL2303USBMessage");
                intent2.putExtra("USB.Detached", String.valueOf(255));
                PL2303GDriver.this.f1234d.sendBroadcast(intent2);
                PL2303GDriver.this.end();
            }
        };
        this.f1206aP = new Runnable() { // from class: tw.com.prolific.driver.pl2303g.PL2303GDriver.2
            @Override // java.lang.Runnable
            public void run() {
                UsbDevice usbDevice = PL2303GDriver.sDevice;
                if (PL2303GDriver.this.isConnected()) {
                    return;
                }
                PL2303GDriver.this.m532a(usbDevice);
                PL2303GDriver.this.f1226at = true;
            }
        };
        m533a(usbManager, context, str, true);
    }

    public PL2303GDriver(UsbManager usbManager, Context context, String str, boolean z) {
        this.f1235e = false;
        this.f1236j = new byte[7];
        this.f1175F = 0;
        this.f1176G = (byte) 0;
        this.f1177K = 64;
        this.f1233c = new byte[4096];
        this.f1190Z = new ArrayBlockingQueue<>(4096, true);
        this.f1209ac = 0;
        this.f1210ad = 0;
        this.f1211ae = false;
        this.f1212af = false;
        this.f1214ah = new ArrayList<>();
        this.f1216aj = 0;
        this.f1217ak = 2;
        this.f1218al = 3;
        this.f1219am = 4;
        this.f1220an = 5;
        this.f1221ao = 6;
        this.f1222ap = 7;
        this.f1223aq = 8;
        this.f1224ar = 9;
        this.f1225as = 10;
        this.f1231ay = true;
        this.f1232az = false;
        this.f1191aA = 17;
        this.f1192aB = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.f1201aK = false;
        this.f1202aL = false;
        this.f1203aM = 0;
        this.f1204aN = 0;
        this.f1205aO = new BroadcastReceiver() { // from class: tw.com.prolific.driver.pl2303g.PL2303GDriver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    return;
                }
                if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if (action.equals(PL2303GDriver.this.f1213ag)) {
                        synchronized (this) {
                            if (intent.getBooleanExtra("permission", false) && usbDevice != null) {
                                for (int i = 0; i < PL2303GDriver.this.f1215ai; i++) {
                                    if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(PL2303GDriver.this.f1214ah.get(i))) {
                                        PL2303GDriver.this.m543b(usbDevice);
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
                if (PL2303GDriver.this.f1179M == null || !PL2303GDriver.this.f1179M.equals(deviceName)) {
                    return;
                }
                Intent intent2 = new Intent("tw.PL2303USBMessage");
                intent2.putExtra("USB.Detached", String.valueOf(255));
                PL2303GDriver.this.f1234d.sendBroadcast(intent2);
                PL2303GDriver.this.end();
            }
        };
        this.f1206aP = new Runnable() { // from class: tw.com.prolific.driver.pl2303g.PL2303GDriver.2
            @Override // java.lang.Runnable
            public void run() {
                UsbDevice usbDevice = PL2303GDriver.sDevice;
                if (PL2303GDriver.this.isConnected()) {
                    return;
                }
                PL2303GDriver.this.m532a(usbDevice);
                PL2303GDriver.this.f1226at = true;
            }
        };
        m533a(usbManager, context, str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m532a(UsbDevice usbDevice) {
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection != null) {
            UsbInterface usbInterface = this.f1181O;
            if (usbInterface != null) {
                usbDeviceConnection.releaseInterface(usbInterface);
                this.f1181O = null;
            }
            this.f1180N.close();
            this.f1179M = null;
            this.f1180N = null;
        }
        if (usbDevice == null) {
            return;
        }
        int interfaceCount = usbDevice.getInterfaceCount();
        UsbDeviceConnection openDevice = this.f1178L.openDevice(usbDevice);
        boolean z = false;
        if (1 == interfaceCount) {
            UsbInterface usbInterface2 = usbDevice.getInterface(interfaceCount - 1);
            if (255 == usbInterface2.getInterfaceClass() && 3 == usbInterface2.getEndpointCount()) {
                if (usbInterface2 != null && openDevice != null) {
                    if (openDevice.claimInterface(usbInterface2, true)) {
                        this.f1179M = usbDevice;
                        this.f1180N = openDevice;
                        this.f1181O = usbInterface2;
                        if (m538a(usbInterface2)) {
                            return;
                        }
                    } else {
                        openDevice.close();
                    }
                }
                z = true;
            }
        } else {
            int i = 0;
            for (int i2 = 0; i2 < 2; i2++) {
                UsbInterface usbInterface3 = usbDevice.getInterface(i2);
                if (openDevice.claimInterface(usbInterface3, true)) {
                    i += usbInterface3.getEndpointCount();
                    m538a(usbInterface3);
                    if (3 == i) {
                        this.f1179M = usbDevice;
                        this.f1180N = openDevice;
                        this.f1181O = usbInterface3;
                        if (usbDevice.getDeviceClass() == 239 && this.f1179M.getDeviceSubclass() == 2 && this.f1179M.getDeviceProtocol() == 1) {
                            this.f1202aL = true;
                            return;
                        }
                        return;
                    }
                } else {
                    openDevice.close();
                }
            }
        }
        if (z) {
            return;
        }
        Intent intent = new Intent("tw.PL2303USBMessage");
        intent.putExtra("USB.Detached", String.valueOf(255));
        this.f1234d.sendBroadcast(intent);
        end();
    }

    /* renamed from: a */
    boolean m571a(String str) {
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
    private boolean m546b(String str) {
        this.f1214ah.add(str);
        this.f1215ai = this.f1214ah.size();
        return true;
    }

    public boolean enumerate() {
        UsbManager usbManager = (UsbManager) this.f1234d.getSystemService("usb");
        this.f1178L = usbManager;
        PendingIntent broadcast = PendingIntent.getBroadcast(this.f1234d, 0, new Intent(this.f1213ag), 0);
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            for (int i = 0; i < this.f1215ai; i++) {
                if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals(this.f1214ah.get(i)) && m571a(usbDevice.getDeviceName())) {
                    IntentFilter intentFilter = new IntentFilter(this.f1213ag);
                    intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
                    this.f1234d.registerReceiver(this.f1205aO, intentFilter);
                    if (!this.f1178L.hasPermission(usbDevice)) {
                        this.f1178L.requestPermission(usbDevice, broadcast);
                    } else {
                        m543b(usbDevice);
                        if (String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:2551") || String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:2503") || String.format("%04X:%04X", Integer.valueOf(usbDevice.getVendorId()), Integer.valueOf(usbDevice.getProductId())).equals("067B:A100")) {
                            this.f1227au = true;
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
    public void m543b(UsbDevice usbDevice) {
        sDevice = usbDevice;
        new Thread(this.f1206aP).start();
    }

    /* renamed from: f */
    private boolean m560f() {
        if (!this.f1226at || this.f1179M == null) {
            return false;
        }
        this.f1216aj = 10;
        if (m540b(8, 254) < 0) {
            return false;
        }
        if (this.f1228av) {
            this.f1207aa = new C2547a();
            return true;
        }
        this.f1207aa = null;
        return true;
    }

    public boolean InitByDefualtValue() {
        if (!m560f()) {
            return false;
        }
        if (!this.f1228av) {
            return true;
        }
        m562g();
        return true;
    }

    public boolean InitByBaudRate(BaudRate baudRate) {
        int i;
        if (!m560f()) {
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
        if (!this.f1228av) {
            return true;
        }
        m562g();
        return true;
    }

    public boolean InitByBaudRate(BaudRate baudRate, int i) {
        int i2;
        if (!m560f()) {
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
        if (!this.f1228av) {
            return true;
        }
        m562g();
        return true;
    }

    public boolean InitByPortSetting(BaudRate baudRate, DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl) {
        int i;
        if (!m560f()) {
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
        if (!this.f1228av) {
            return true;
        }
        m562g();
        return true;
    }

    public void end() {
        if (this.f1179M != null) {
            if (this.f1228av) {
                m563h();
            }
            this.f1227au = false;
            this.f1234d.unregisterReceiver(this.f1205aO);
            m532a((UsbDevice) null);
        }
    }

    public boolean isConnected() {
        return (this.f1179M == null || this.f1182P == null || this.f1183Q == null) ? false : true;
    }

    /* renamed from: a */
    private boolean m538a(UsbInterface usbInterface) {
        if (usbInterface == null) {
            return false;
        }
        for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
            if (usbInterface.getEndpoint(i).getType() == 2) {
                if (usbInterface.getEndpoint(i).getDirection() == 128) {
                    this.f1182P = usbInterface.getEndpoint(i);
                } else {
                    this.f1183Q = usbInterface.getEndpoint(i);
                }
            } else if (usbInterface.getEndpoint(i).getType() == 3 && usbInterface.getEndpoint(i).getDirection() == 128) {
                this.f1184R = usbInterface.getEndpoint(i);
            }
        }
        return true;
    }

    /* renamed from: g */
    private void m562g() {
        if (this.f1208ab) {
            return;
        }
        this.f1207aa.start();
        this.f1207aa.setPriority(10);
        this.f1208ab = this.f1207aa.isAlive();
    }

    /* renamed from: h */
    private void m563h() {
        C2547a c2547a;
        if (!this.f1208ab || (c2547a = this.f1207aa) == null) {
            return;
        }
        c2547a.m575b();
        this.f1208ab = this.f1207aa.isAlive();
        this.f1207aa = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: tw.com.prolific.driver.pl2303g.PL2303GDriver$a */
    /* loaded from: classes2.dex */
    public class C2547a extends Thread {

        /* renamed from: b */
        private int f1252b;

        /* renamed from: c */
        private int f1253c;

        /* renamed from: d */
        private boolean f1254d = true;

        /* renamed from: e */
        private boolean f1255e = false;

        /* renamed from: f */
        private AtomicInteger f1256f = new AtomicInteger(500);

        C2547a() {
        }

        /* renamed from: a */
        public void m573a() {
            this.f1253c = 0;
            this.f1252b = 0;
            PL2303GDriver.this.f1190Z.clear();
        }

        /* renamed from: a */
        public void m574a(int i) {
            m573a();
            m576b(i);
        }

        /* renamed from: b */
        public void m576b(int i) {
            this.f1256f.set(i);
        }

        /* renamed from: b */
        public void m575b() {
            this.f1255e = true;
            do {
            } while (isAlive());
            PL2303GDriver.this.f1190Z.clear();
        }

        /* renamed from: c */
        private void m572c(int i) {
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
                while (!this.f1255e) {
                    int m529a = PL2303GDriver.this.m529a(bArr, 4096);
                    this.f1252b = m529a;
                    if (m529a > 0) {
                        synchronized (PL2303GDriver.ReadQueueLock) {
                            int size = PL2303GDriver.this.f1190Z.size();
                            this.f1253c = size;
                            if (4096 != size) {
                                for (int i = 0; i < this.f1252b && this.f1253c < 4096; i++) {
                                    int intValue = Integer.valueOf(bArr[i]).intValue();
                                    if (FlowControl.XONXOFF == PL2303GDriver.this.f1229aw) {
                                        if (19 == intValue) {
                                            PL2303GDriver.this.f1230ax = false;
                                        } else if (17 == intValue) {
                                            PL2303GDriver.this.f1230ax = true;
                                        }
                                    }
                                    boolean offer = PL2303GDriver.this.f1190Z.offer(Integer.valueOf(intValue));
                                    this.f1254d = offer;
                                    if (!offer) {
                                        break;
                                    }
                                    this.f1253c = PL2303GDriver.this.f1190Z.size();
                                }
                            }
                        }
                    }
                    m572c(this.f1256f.get());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m535a(BaudRate baudRate) {
        int i;
        int[] iArr = {3, 5, 10, 25, 100, 200};
        int i2 = iArr[3];
        switch (m539a()[baudRate.ordinal()]) {
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
        C2547a c2547a = this.f1207aa;
        if (c2547a != null) {
            c2547a.m576b(i);
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
        if (this.f1228av) {
            synchronized (ReadQueueLock) {
                int size = this.f1190Z.size();
                if (size > 0) {
                    if (length >= size) {
                        length = size;
                    }
                    for (int i = 0; i < length; i++) {
                        Integer poll = this.f1190Z.poll();
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
        int m529a = m529a(bArr, length);
        if (m529a > 0) {
            return length >= m529a ? m529a : length;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m529a(byte[] bArr, int i) {
        if (bArr.length == 0 || i == 0) {
            return 0;
        }
        if (this.f1180N == null) {
            this.f1207aa.m575b();
            return -1;
        }
        int i2 = this.f1186V;
        if (i2 > 0 && i <= i2) {
            if (!this.f1235e) {
                System.arraycopy(this.f1233c, this.f1185U, bArr, 0, i);
            } else {
                for (int i3 = 0; i3 < i; i3++) {
                    byte[] bArr2 = this.f1233c;
                    int i4 = this.f1185U;
                    this.f1185U = i4 + 1;
                    bArr[i3] = bArr2[i4];
                    this.f1209ac++;
                    while (true) {
                        int i5 = (this.f1209ac - 1) % 10;
                        byte b = bArr[i3];
                        Byte.valueOf(b);
                        if (i5 == b - 48) {
                            break;
                        }
                        this.f1209ac++;
                    }
                }
                this.f1210ad += i;
                this.f1211ae = true;
            }
            this.f1186V -= i;
            return i;
        }
        if (i2 > 0) {
            i -= i2;
            System.arraycopy(this.f1233c, this.f1185U, bArr, 0, i2);
        }
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        UsbEndpoint usbEndpoint = this.f1182P;
        byte[] bArr3 = this.f1233c;
        int bulkTransfer = usbDeviceConnection.bulkTransfer(usbEndpoint, bArr3, bArr3.length, this.f1187W);
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
        this.f1186V = bulkTransfer;
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            int i9 = i8 * 64;
            int i10 = 0;
            while (i10 < 64) {
                byte[] bArr4 = this.f1233c;
                bArr4[i7] = bArr4[i9 + i10];
                i10++;
                i7++;
            }
        }
        this.f1185U = 0;
        int i11 = 0;
        while (this.f1186V > 0 && i > 0) {
            int i12 = i11 + 1;
            byte[] bArr5 = this.f1233c;
            int i13 = this.f1185U;
            this.f1185U = i13 + 1;
            bArr[i11] = bArr5[i13];
            if (this.f1235e) {
                this.f1209ac++;
                while (true) {
                    int i14 = (this.f1209ac - 1) % 10;
                    byte b2 = bArr[i12 - 1];
                    Byte.valueOf(b2);
                    if (i14 == b2 - 48) {
                        break;
                    }
                    this.f1209ac++;
                }
            }
            this.f1186V--;
            i--;
            i11 = i12;
        }
        if (this.f1235e) {
            if (i11 > 0) {
                this.f1210ad += i11;
                this.f1211ae = true;
            }
            if (this.f1211ae) {
                this.f1211ae = false;
            }
        }
        return i11;
    }

    public int write(byte[] bArr) {
        return write(bArr, bArr.length);
    }

    public int write(byte[] bArr, int i) {
        byte[] bArr2 = new byte[4096];
        if (this.f1180N == null) {
            return -1;
        }
        if (FlowControl.XONXOFF == this.f1229aw && !this.f1230ax) {
            return 0;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 4096 > i ? i - i2 : 4096;
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            int bulkTransfer = this.f1180N.bulkTransfer(this.f1183Q, bArr2, i3, this.f1188X);
            if (bulkTransfer < 0) {
                return -1;
            }
            i2 += bulkTransfer;
        }
        return i2;
    }

    public int setup(BaudRate baudRate, DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl) throws IOException {
        int i;
        if (this.f1180N == null) {
            return -1;
        }
        if (FlowControl.XONXOFF == this.f1229aw && !this.f1230ax) {
            return 0;
        }
        int controlTransfer = this.f1180N.controlTransfer(161, 33, 0, 0, this.f1236j, 7, this.f1189Y);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        switch (m539a()[baudRate.ordinal()]) {
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
        if (i > 1228800 && this.f1216aj == 0) {
            return -2;
        }
        if (this.f1207aa != null) {
            m535a(baudRate);
        }
        byte[] bArr = this.f1236j;
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        bArr[2] = (byte) ((i >> 16) & 255);
        bArr[3] = (byte) ((i >> 24) & 255);
        int i2 = m547b()[stopBits.ordinal()];
        if (i2 == 1) {
            this.f1236j[4] = 0;
        } else {
            if (i2 != 2) {
                return -3;
            }
            this.f1236j[4] = 2;
        }
        int i3 = m551c()[parity.ordinal()];
        if (i3 == 1) {
            this.f1236j[5] = 0;
        } else if (i3 == 2) {
            this.f1236j[5] = 1;
        } else {
            if (i3 != 3) {
                return -4;
            }
            this.f1236j[5] = 2;
        }
        int i4 = m555d()[dataBits.ordinal()];
        if (i4 == 1) {
            this.f1236j[6] = 5;
        } else if (i4 == 2) {
            this.f1236j[6] = 6;
        } else if (i4 == 3) {
            this.f1236j[6] = 7;
        } else {
            if (i4 != 4) {
                return -5;
            }
            this.f1236j[6] = 8;
        }
        int controlTransfer2 = this.f1180N.controlTransfer(33, 32, 0, 0, this.f1236j, 7, this.f1189Y);
        if (controlTransfer2 < 0) {
            return controlTransfer2;
        }
        int controlTransfer3 = this.f1180N.controlTransfer(33, 35, 0, 0, null, 0, this.f1189Y);
        if (controlTransfer3 < 0) {
            return controlTransfer3;
        }
        this.f1216aj = 10;
        int[] m558e = m558e(8);
        if (m558e[0] < 0) {
            return m558e[0];
        }
        switch (m557e()[flowControl.ordinal()]) {
            case 1:
                m558e[1] = m558e[1] | 12;
                int m540b = m540b(8, m558e[1]);
                if (m540b < 0) {
                    return m540b;
                }
                break;
            case 2:
                m558e[1] = m558e[1] & (-13);
                m558e[1] = m558e[1] | 8;
                int m540b2 = m540b(8, m558e[1]);
                if (m540b2 < 0) {
                    return m540b2;
                }
                break;
            case 3:
                break;
            case 4:
                m558e[1] = m558e[1] & (-13);
                m558e[1] = 4 | m558e[1];
                int m540b3 = m540b(8, m558e[1]);
                if (m540b3 < 0) {
                    return m540b3;
                }
                break;
            case 5:
                m558e[1] = m558e[1] & (-13);
                int m540b4 = m540b(8, m558e[1]);
                if (m540b4 < 0) {
                    return m540b4;
                }
                break;
            case 6:
                m558e[1] = m558e[1] & (-17);
                int m540b5 = m540b(8, m558e[1]);
                if (m540b5 < 0) {
                    return m540b5;
                }
                break;
            default:
                return -6;
        }
        this.f1229aw = flowControl;
        if (this.f1212af) {
            int m526a = m526a(0, 49);
            if (m526a < 0) {
                return m526a;
            }
            int m526a2 = m526a(1, 8);
            if (m526a2 < 0) {
                return m526a2;
            }
        }
        return 0;
    }

    public int setDTR(boolean z) {
        if (z) {
            int i = this.f1175F;
            if ((i & 1) != 1) {
                this.f1175F = i + 1;
            }
        }
        if (!z) {
            int i2 = this.f1175F;
            if ((i2 & 1) == 1) {
                this.f1175F = i2 - 1;
            }
        }
        int controlTransfer = this.f1180N.controlTransfer(33, 34, this.f1175F, 0, null, 0, this.f1189Y);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    public int setRTS(boolean z) {
        if (z) {
            int i = this.f1175F;
            if ((i & 2) != 2) {
                this.f1175F = i + 2;
            }
        }
        if (!z) {
            int i2 = this.f1175F;
            if ((i2 & 2) == 2) {
                this.f1175F = i2 - 2;
            }
        }
        int controlTransfer = this.f1180N.controlTransfer(33, 34, this.f1175F, 0, null, 0, this.f1189Y);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    /* renamed from: a */
    private int m527a(UsbDeviceConnection usbDeviceConnection) {
        int m565j;
        int m568m;
        int[] iArr = {0};
        if (this.f1227au) {
            this.f1216aj = 4;
            m565j = 0;
        } else {
            if (!m567l() && (m568m = m568m()) < 0) {
                return m568m;
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == 4) {
                this.f1216aj = 4;
            }
            m565j = m565j();
            if (m565j < 0) {
                return m565j;
            }
            if (this.f1201aK && (m565j = m566k()) < 0) {
                return m565j;
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == 5 && (m565j = m564i()) < 0) {
                return m565j;
            }
        }
        int i = this.f1216aj;
        if (i != 4 && i != 6) {
            return -1;
        }
        for (int i2 = 128; i2 <= 130; i2++) {
            iArr = m548b(i2);
            if (iArr[0] < 0) {
                return iArr[0];
            }
        }
        try {
            m565j = setup(BaudRate.B9600, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (iArr[0] < 0) {
            return m565j;
        }
        return 0;
    }

    /* renamed from: i */
    private int m564i() {
        int[] m548b = m548b(148);
        if (m548b[0] < 0) {
            return m548b[0];
        }
        if ((m548b[1] & 148) == 148) {
            this.f1216aj = 6;
        } else {
            this.f1216aj = 2;
        }
        return 0;
    }

    /* renamed from: j */
    private int m565j() {
        int[] iArr = new int[2];
        int[] m548b = m548b(129);
        if (m548b[0] < 0) {
            return m548b[0];
        }
        int i = m548b[1];
        m548b[0] = m526a(1, 255);
        if (m548b[0] < 0) {
            return m548b[0];
        }
        int[] m548b2 = m548b(129);
        if (m548b2[0] < 0) {
            return m548b2[0];
        }
        if ((m548b2[1] & 15) == 15) {
            this.f1216aj = 4;
            int[] m552c = m552c(250);
            if (m552c[0] < 0) {
                return m552c[0];
            }
            iArr[0] = m552c[1];
            int[] m552c2 = m552c(251);
            if (m552c2[0] < 0) {
                return m552c2[0];
            }
            iArr[1] = m552c2[1];
            if ((iArr[0] != 1 || iArr[1] != 4) && ((iArr[0] != 2 || iArr[1] != 4) && ((iArr[0] != 3 || iArr[1] != 4) && iArr[0] == 1 && iArr[1] == 3))) {
                this.f1216aj = 2;
            }
            m548b2 = m552c2;
        } else {
            this.f1216aj = 2;
        }
        m548b2[0] = m526a(1, i);
        if (m548b2[0] < 0) {
            return m548b2[0];
        }
        return 0;
    }

    /* renamed from: a */
    private String m531a(int i) {
        return new String(new char[]{Character.forDigit((i >> 4) & 15, 16), Character.forDigit(i & 15, 16)});
    }

    /* renamed from: b */
    private static String m542b(byte[] bArr, int i) {
        StringBuffer stringBuffer;
        byte[] digest;
        StringBuffer stringBuffer2 = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            digest = messageDigest.digest(bArr);
            stringBuffer = new StringBuffer();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
            for (byte b : digest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
            }

        return stringBuffer.toString();
    }

    /* renamed from: k */
    private int m566k() {
        int[] m552c = m552c(9);
        if (m552c[0] < 0) {
            return m552c[0];
        }
        if ((m552c[1] & 8) == 8) {
            m552c[0] = m526a(0, 49);
            if (m552c[0] < 0) {
                return m552c[0];
            }
            m552c[0] = m526a(1, 8);
            if (m552c[0] < 0) {
                return m552c[0];
            }
            this.f1212af = true;
        }
        return m552c[0];
    }

    public int PL2303G_GPIO_B_CNT_ENABLE(boolean z) {
        int[] iArr = new int[2];
        int i = z ? 255 : 0;
        if (this.f1180N == null) {
            return -1;
        }
        iArr[0] = m526a(5, i);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303G_Enable_GPIO_B(int i, boolean z) {
        int[] iArr = new int[2];
        if (this.f1180N == null) {
            return -1;
        }
        switch (i) {
            case 0:
                if (z) {
                    this.f1203aM |= 1;
                    break;
                } else {
                    this.f1203aM &= -2;
                    break;
                }
            case 1:
                if (z) {
                    this.f1203aM |= 2;
                    break;
                } else {
                    this.f1203aM &= -3;
                    break;
                }
            case 2:
                if (z) {
                    this.f1203aM |= 4;
                    break;
                } else {
                    this.f1203aM &= -5;
                    break;
                }
            case 3:
                if (z) {
                    this.f1203aM |= 8;
                    break;
                } else {
                    this.f1203aM &= -9;
                    break;
                }
            case 4:
                if (z) {
                    this.f1203aM |= 16;
                    break;
                } else {
                    this.f1203aM &= -17;
                    break;
                }
            case 5:
                if (z) {
                    this.f1203aM |= 32;
                    break;
                } else {
                    this.f1203aM &= -33;
                    break;
                }
            case 6:
                if (z) {
                    this.f1203aM |= 64;
                    break;
                } else {
                    this.f1203aM &= -65;
                    break;
                }
            case 7:
                if (z) {
                    this.f1203aM |= 128;
                    break;
                } else {
                    this.f1203aM &= -129;
                    break;
                }
            default:
                return -1;
        }
        iArr[0] = m526a(3, this.f1203aM);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303G_Set_GPIO_Value_B(int i, int i2) {
        int[] iArr = new int[2];
        if (this.f1180N == null) {
            return -1;
        }
        switch (i) {
            case 0:
                if (i2 == 1) {
                    this.f1204aN |= 1;
                    break;
                } else {
                    this.f1204aN &= -2;
                    break;
                }
            case 1:
                if (i2 == 1) {
                    this.f1204aN |= 2;
                    break;
                } else {
                    this.f1204aN &= -3;
                    break;
                }
            case 2:
                if (i2 == 1) {
                    this.f1204aN |= 4;
                    break;
                } else {
                    this.f1204aN &= -5;
                    break;
                }
            case 3:
                if (i2 == 1) {
                    this.f1204aN |= 8;
                    break;
                } else {
                    this.f1204aN &= -9;
                    break;
                }
            case 4:
                if (i2 == 1) {
                    this.f1204aN |= 16;
                    break;
                } else {
                    this.f1204aN &= -17;
                    break;
                }
            case 5:
                if (i2 == 1) {
                    this.f1204aN |= 32;
                    break;
                } else {
                    this.f1204aN &= -33;
                    break;
                }
            case 6:
                if (i2 == 1) {
                    this.f1204aN |= 64;
                    break;
                } else {
                    this.f1204aN &= -65;
                    break;
                }
            case 7:
                if (i2 == 1) {
                    this.f1204aN |= 128;
                    break;
                } else {
                    this.f1204aN &= -129;
                    break;
                }
            default:
                return -1;
        }
        iArr[0] = m526a(1, this.f1204aN);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int[] PL2303G_Get_GPIO_Value_B(int i) {
        int[] iArr = new int[2];
        if (this.f1180N == null) {
            iArr[0] = -1;
            return iArr;
        }
        int[] m548b = m548b(1);
        if (m548b[0] < 0) {
            return m548b;
        }
        switch (i) {
            case 0:
                if ((m548b[1] & 1) == 1) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 1:
                if ((m548b[1] & 2) == 2) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 2:
                if ((m548b[1] & 4) == 4) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 3:
                if ((m548b[1] & 8) == 8) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 4:
                if ((m548b[1] & 16) == 16) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 5:
                if ((m548b[1] & 32) == 32) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 6:
                if ((m548b[1] & 64) == 64) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            case 7:
                if ((m548b[1] & 128) == 128) {
                    m548b[1] = 1;
                } else {
                    m548b[1] = 0;
                }
                return m548b;
            default:
                m548b[0] = -1;
                return m548b;
        }
    }

    /* renamed from: a */
    private int m526a(int i, int i2) {
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(64, 128, i, i2, null, 0, this.f1189Y);
    }

    /* renamed from: b */
    private int[] m548b(int i) {
        byte[] bArr = new byte[1];
        int[] iArr = {0};
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            iArr[0] = -1;
            return iArr;
        }
        iArr[0] = usbDeviceConnection.controlTransfer(192, 129, i, 0, bArr, 1, this.f1189Y);
        if (iArr[0] < 0) {
            return iArr;
        }
        iArr[1] = bArr[0];
        return iArr;
    }

    /* renamed from: c */
    private int[] m552c(int i) {
        int[] m548b = m548b(132);
        if (m548b[0] < 0) {
            return m548b;
        }
        m548b[0] = m526a(4, i);
        if (m548b[0] < 0) {
            return m548b;
        }
        int[] m548b2 = m548b(132);
        if (m548b2[0] < 0) {
            return m548b2;
        }
        int[] m548b3 = m548b(131);
        int i2 = m548b3[0];
        return m548b3;
    }

    /* renamed from: l */
    private boolean m567l() {
        int[] iArr = new int[2];
        for (int i = 0; i < 2; i++) {
            int[] m552c = m552c(i);
            if (m552c[0] < 0) {
                return this.f1201aK;
            }
            iArr[i] = m552c[1];
        }
        if (iArr[0] == 123 && iArr[1] == 6) {
            this.f1201aK = true;
        }
        return this.f1201aK;
    }

    /* renamed from: m */
    private int m568m() {
        this.f1216aj = 10;
        return m540b(8, 254);
    }

    /* renamed from: d */
    private int m553d(int i) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            return -1;
        }
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        int controlTransfer = usbDeviceConnection.controlTransfer(161, 32, 0, 0, bArr2, 2, this.f1188X);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.f1180N.bulkTransfer(this.f1184R, bArr, 2, this.f1187W) < 0) {
            return 0;
        }
        return (bArr[1] << 8) | bArr[0];
    }

    /* renamed from: n */
    private int[] m569n() {
        int[] m548b = m548b(135);
        int i = m548b[0];
        return m548b;
    }

    public int[] PL2303G_GetCommModemStatus() {
        int[] m569n = m569n();
        if (m569n[0] < 0) {
            return m569n;
        }
        int i = (m569n[1] & 1) != 1 ? 8 : 0;
        int i2 = (m569n[1] & 2) == 2 ? i & (-2) : i | 1;
        int i3 = (m569n[1] & 4) == 4 ? i2 & (-3) : i2 | 2;
        m569n[1] = (m569n[1] & 8) == 8 ? i3 & (-129) : i3 | 128;
        return m569n;
    }

    public void PL2303LibGetVersion(byte[] bArr) {
        int length;
        if (bArr.length < f1158i.length()) {
            length = bArr.length;
        } else {
            length = f1158i.length();
        }
        char[] charArray = f1158i.toCharArray();
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) charArray[i];
        }
    }

    public boolean PL2303USBFeatureSupported() {
        return this.f1234d.getPackageManager().hasSystemFeature("android.hardware.usb.host");
    }

    public String PL2303Device_GetSerialNumber() {
        if (isConnected()) {
            return this.f1180N.getSerial();
        }
        return null;
    }

    public boolean PL2303Device_IsHasPermission() {
        return this.f1226at;
    }

    public boolean PL2303Device_IsSupportChip() {
        return this.f1216aj == 10;
    }

    public boolean PL2303Device_SetCommTimeouts(int i) {
        if (i < 0) {
            return false;
        }
        this.f1187W = i;
        this.f1188X = i;
        return true;
    }

    /* renamed from: b */
    private int m540b(int i, int i2) {
        if (this.f1202aL) {
            return 0;
        }
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(64, 128, i, i2, null, 0, this.f1189Y);
    }

    /* renamed from: e */
    private int[] m558e(int i) {
        byte[] bArr = new byte[1];
        int[] iArr = {0};
        if (this.f1202aL) {
            return iArr;
        }
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            iArr[0] = -1;
            return iArr;
        }
        iArr[0] = usbDeviceConnection.controlTransfer(192, 129, i, 0, bArr, 1, this.f1189Y);
        if (iArr[0] < 0) {
            return iArr;
        }
        iArr[1] = bArr[0];
        return iArr;
    }

    /* renamed from: c */
    private int m549c(int i, int i2) {
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(64, 130, i, i2, null, 0, this.f1189Y);
    }

    /* renamed from: f */
    private int[] m561f(int i) {
        byte[] bArr = new byte[1];
        int[] iArr = {0};
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            iArr[0] = -1;
            return iArr;
        }
        iArr[0] = usbDeviceConnection.controlTransfer(192, 131, i, 0, bArr, 1, this.f1189Y);
        if (iArr[0] < 0) {
            return iArr;
        }
        iArr[1] = bArr[0];
        return iArr;
    }

    /* renamed from: o */
    private int[] m570o() {
        byte[] bArr = new byte[1];
        int[] iArr = {0};
        UsbDeviceConnection usbDeviceConnection = this.f1180N;
        if (usbDeviceConnection == null) {
            iArr[0] = -1;
            return iArr;
        }
        iArr[0] = usbDeviceConnection.controlTransfer(192, 130, 0, 0, bArr, 1, this.f1189Y);
        if (iArr[0] < 0) {
            return iArr;
        }
        iArr[1] = bArr[0];
        return iArr;
    }
}
