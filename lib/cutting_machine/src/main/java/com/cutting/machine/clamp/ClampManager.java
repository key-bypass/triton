package com.liying.core.clamp;

import android.text.TextUtils;
import android.util.Log;
import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.KeyAlignInfo;
import com.liying.core.OperateType;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.ClampBean;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.StepBean;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.utils.FileUtil;
import com.liying.core.utils.UnitConvertUtil;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class ClampManager {
    private static final String TAG = "CalibrationManager";
    private static ClampManager sInstance;
    private Clamp clamp;
    ClampF clampF;
    ClampFix clampFix;

    /* renamed from: dc */
    DecoderCutterDistance f401dc;
    E9S1A e9S1A;
    E9S1C e9S1C;
    E9S2A e9S2A;
    E9S2B e9S2B;
    E9S5 e9s5;
    private File roothPath;
    S1A s1A;
    S1B s1B;
    S1C s1C;
    S1D s1D;
    S2A s2A;
    S2B s2B;

    /* renamed from: s5 */
    C1976S5 f402s5;

    /* renamed from: s8 */
    private C1979S8 f403s8;
    private String S1A_json = "S1-A.json";
    private String E9_S1A_json = "E9_S1-A.json";
    private String S1B_json = "S1-B.json";
    private String S1C_json = "S1-C.json";
    private String E9_S1C_json = "E9_S1-C.json";
    private String S1D_json = "S1-D.json";
    private String S2A_json = "S2-A.json";
    private String E9_S2A_json = "E9_S2-A.json";
    private String S2B_json = "S2-B.json";
    private String E9_S2B_json = "E9_S2-B.json";
    private String S3_json = "S3.json";
    private String S4_json = "S4.json";
    private String S5_json = "S5.json";
    private String S8_json = "S8.json";
    private String dc_json = "D-C.json";
    private String clampFixFile = "clampFix";
    int dataNumber = -1;

    private ClampManager() {
    }

    public static ClampManager getInstance() {
        if (sInstance == null) {
            sInstance = new ClampManager();
        }
        return sInstance;
    }

    public ClampFix getClampFix() {
        return this.clampFix;
    }

    public void setClampFix(ClampFix clampFix) {
        this.clampFix = clampFix;
    }

    public S1A getS1A() {
        if (this.s1A == null) {
            this.s1A = string2S1A(FileUtil.getFileContent(getSaveFilePath(this.S1A_json)));
        }
        return this.s1A;
    }

    public E9S1A getE9S1A() {
        if (this.e9S1A == null) {
            this.e9S1A = String2E9S1A(FileUtil.getFileContent(getSaveFilePath(this.E9_S1A_json)));
        }
        return this.e9S1A;
    }

    public E9S1C getE9S1C() {
        if (this.e9S1C == null) {
            this.e9S1C = String2E9S1C(FileUtil.getFileContent(getSaveFilePath(this.E9_S1C_json)));
        }
        return this.e9S1C;
    }

    public E9S2A getE9S2A() {
        if (this.e9S2A == null) {
            this.e9S2A = String2E9S2A(FileUtil.getFileContent(getSaveFilePath(this.E9_S2A_json)));
        }
        return this.e9S2A;
    }

    public E9S2B getE9S2B() {
        if (this.e9S2B == null) {
            this.e9S2B = String2E9S2B(FileUtil.getFileContent(getSaveFilePath(this.E9_S2B_json)));
        }
        return this.e9S2B;
    }

    public S1B getS1B() {
        if (this.s1B == null) {
            this.s1B = string2S1B(FileUtil.getFileContent(getSaveFilePath(this.S1B_json)));
        }
        return this.s1B;
    }

    public S1C getS1C() {
        if (this.s1C == null) {
            this.s1C = string2S1C(FileUtil.getFileContent(getSaveFilePath(this.S1C_json)));
        }
        return this.s1C;
    }

    public S1D getS1D() {
        if (this.s1D == null) {
            this.s1D = string2S1D(FileUtil.getFileContent(getSaveFilePath(this.S1D_json)));
        }
        return this.s1D;
    }

    public S2A getS2A() {
        if (this.s2A == null) {
            this.s2A = String2S2A(FileUtil.getFileContent(getSaveFilePath(this.S2A_json)));
        }
        return this.s2A;
    }

    public void setS2A(S2A s2a) {
        this.s2A = s2a;
    }

    public void setE9S2A(E9S2A e9s2a) {
        if (e9s2a == null) {
            return;
        }
        this.e9S2A = e9s2a;
        setE9S2B(new E9S2B(e9s2a));
    }

    public void setE9S1A(E9S1A e9s1a) {
        this.e9S1A = e9s1a;
    }

    public void setE9S1C(E9S1C e9s1c) {
        this.e9S1C = e9s1c;
    }

    public S2B getS2B() {
        return this.s2B;
    }

    public void setE9S2B(E9S2B e9s2b) {
        this.e9S2B = e9s2b;
    }

    public void setS2B(S2B s2b) {
        this.s2B = s2b;
    }

    public C1976S5 getS5() {
        if (this.f402s5 == null) {
            this.f402s5 = string2S5(FileUtil.getFileContent(getSaveFilePath(this.S5_json)));
        }
        return this.f402s5;
    }

    public E9S5 getE9S5() {
        if (this.e9s5 == null) {
            this.e9s5 = string2E9S5(FileUtil.getFileContent(getSaveFilePath(this.S5_json)));
        }
        return this.e9s5;
    }

    private E9S5 string2E9S5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 1) {
            return null;
        }
        E9S5 e9s5 = new E9S5();
        e9s5.setCenterY(Integer.parseInt(split[0]));
        e9s5.setBlockDistance(Integer.parseInt(split[1]));
        return e9s5;
    }

    public void setS5(C1976S5 c1976s5) {
        this.f402s5 = c1976s5;
    }

    public void setE9S5(E9S5 e9s5) {
        this.e9s5 = e9s5;
    }

    private C1976S5 string2S5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 2) {
            return null;
        }
        C1976S5 c1976s5 = new C1976S5();
        c1976s5.setX(Integer.parseInt(split[0]));
        c1976s5.setY(Integer.parseInt(split[1]));
        c1976s5.setDeep(Integer.parseInt(split[2]));
        return c1976s5;
    }

    public DecoderCutterDistance getDC() {
        if (this.f401dc == null) {
            this.f401dc = String2Dc(FileUtil.getFileContent(getSaveFilePath(this.dc_json)));
        }
        return this.f401dc;
    }

    public void setRootPathDir(File file) {
        if (!file.exists()) {
            file.mkdir();
        }
        this.roothPath = file;
    }

    private File getRootPath() {
        return this.roothPath;
    }

    private File getSaveFilePath(String str) {
        return new File(getRootPath(), str);
    }

    public void setCurrentClamp(String str) {
        if (CuttingMachine.getInstance().isAlpha() || CuttingMachine.getInstance().isBeta()) {
            if (str.contains(Clamp.S1_A.getClampStr())) {
                this.clamp = Clamp.S1_A;
                return;
            }
            if (str.contains(Clamp.S1_B_D.getClampStr())) {
                this.clamp = Clamp.S1_B;
                return;
            }
            if (str.contains(Clamp.S1_B.getClampStr())) {
                this.clamp = Clamp.S1_B;
                return;
            }
            if (str.contains(Clamp.S1_C.getClampStr())) {
                this.clamp = Clamp.S1_C;
                return;
            }
            if (str.contains(Clamp.S1_D.getClampStr())) {
                this.clamp = Clamp.S1_D;
                return;
            }
            if (str.contains(Clamp.D_C_S2_A.getClampStr())) {
                this.clamp = Clamp.D_C_S2_A;
                return;
            }
            if (str.contains(Clamp.D_C.getClampStr())) {
                this.clamp = Clamp.D_C;
                return;
            }
            if (str.contains(Clamp.S2_A.getClampStr())) {
                this.clamp = Clamp.S2_A;
                return;
            }
            if (str.contains(Clamp.S2_B.getClampStr())) {
                this.clamp = Clamp.S2_B;
                return;
            }
            if (str.contains(Clamp.S3.getClampStr())) {
                this.clamp = Clamp.S3;
                return;
            }
            if (str.contains(Clamp.S4.getClampStr())) {
                this.clamp = Clamp.S4;
                return;
            }
            if (str.contains(Clamp.S5.getClampStr())) {
                this.clamp = Clamp.S5;
                return;
            }
            if (str.contains(Clamp.S6.getClampStr())) {
                this.clamp = Clamp.S6;
                return;
            }
            if (str.contains(Clamp.S8.getClampStr())) {
                this.clamp = Clamp.S8;
                return;
            }
            if (str.contains(Clamp.S9_A.getClampStr())) {
                this.clamp = Clamp.S9_A;
                return;
            }
            if (str.contains(Clamp.S9_B.getClampStr())) {
                this.clamp = Clamp.S9_B;
                return;
            }
            if (str.contains(Clamp.S9_C.getClampStr())) {
                this.clamp = Clamp.S9_C;
                return;
            } else if (str.contains(Clamp.S9_D.getClampStr())) {
                this.clamp = Clamp.S9_D;
                return;
            } else {
                if (str.contains(Clamp.S10.getClampStr())) {
                    this.clamp = Clamp.S10;
                    return;
                }
                return;
            }
        }
        if (str.contains(Clamp.S1_A.getClampStr())) {
            this.clamp = Clamp.E9S1A;
            return;
        }
        if (str.contains(Clamp.S1_C.getClampStr())) {
            this.clamp = Clamp.E9S1C;
            return;
        }
        if (str.contains(Clamp.S2_A.getClampStr())) {
            this.clamp = Clamp.E9S2A;
            return;
        }
        if (str.contains(Clamp.S2_B.getClampStr())) {
            this.clamp = Clamp.E9S2B;
            return;
        }
        if (str.contains(Clamp.S3.getClampStr())) {
            this.clamp = Clamp.E9S3;
        } else if (str.contains(Clamp.S4.getClampStr())) {
            this.clamp = Clamp.E9S4;
        } else if (str.contains(Clamp.S5.getClampStr())) {
            this.clamp = Clamp.E9S5;
        }
    }

    public Clamp getClampFromFileName(String str) {
        if (str.contains(Clamp.S1_A.getClampStr())) {
            return Clamp.S1_A;
        }
        if (str.contains(Clamp.S1_B.getClampStr())) {
            return Clamp.S1_B;
        }
        if (str.contains(Clamp.S1_C.getClampStr())) {
            return Clamp.S1_C;
        }
        if (str.contains(Clamp.S1_D.getClampStr())) {
            return Clamp.S1_D;
        }
        if (str.contains(Clamp.D_C.getClampStr())) {
            return Clamp.D_C;
        }
        if (str.contains(Clamp.S2_A.getClampStr())) {
            return Clamp.S2_A;
        }
        if (str.contains(Clamp.S2_B.getClampStr())) {
            return Clamp.S2_B;
        }
        if (str.contains(Clamp.S3.getClampStr())) {
            return Clamp.S3;
        }
        if (str.contains(Clamp.S4.getClampStr())) {
            return Clamp.S4;
        }
        if (str.contains(Clamp.S5.getClampStr())) {
            return Clamp.S5;
        }
        if (str.contains(Clamp.S8.getClampStr())) {
            return Clamp.S8;
        }
        return null;
    }

    public Clamp getCurrentClamp() {
        return this.clamp;
    }

    public byte[] getCalibrationDataSaveOrder() {
        byte[] bArr = new byte[0];
        switch (C19721.$SwitchMap$com$liying$core$clamp$Clamp[this.clamp.ordinal()]) {
            case 1:
            case 2:
                return Command.WriteSpecifyLocationData(64, getDC().getxDistance() + ":" + getDC().getyDistance() + ":" + getDC().getS1X() + ":" + getDC().getS1Y() + ":" + getDC().getS2X() + ":" + getDC().getS2Y());
            case 3:
                if (Math.abs((getS1A().getHigh() * MachineData.dZScale) - 65.0f) > 20.0f) {
                    ErrorHelper.handleError(78);
                    return null;
                }
                return Command.WriteSpecifyLocationData(65, this.s1A.getShouldDiff() + ":" + this.s1A.getHigh() + ":" + this.s1A.getX1() + ":" + this.s1A.getX2());
            case 4:
                if (Math.abs((getS1B().getHigh1() * MachineData.dZScale) - 165.0f) > 20.0f) {
                    ErrorHelper.handleError(79);
                    return null;
                }
                return Command.WriteSpecifyLocationData(66, this.s1B.getShoulderDiffUp() + ":" + this.s1B.getShoulderDiffDown() + ":" + this.s1B.getX1Up() + ":" + this.s1B.getX2Up() + ":" + this.s1B.getX1Down() + ":" + this.s1B.getX2Down() + ":" + this.s1B.getHigh1() + ":" + this.s1B.getHigh2());
            case 5:
                return Command.WriteSpecifyLocationData(67, this.s1C.getShoulderDiff() + ":" + this.s1C.getRightDiff() + ":" + this.s1C.getLeftDiff());
            case 6:
                return Command.WriteSpecifyLocationData(68, this.s1D.getShoulderDiff() + ":" + this.s1D.getRightDiff() + ":" + this.s1D.getLeftDiff());
            case 7:
                return Command.WriteSpecifyLocationData(1, this.s2A.getShoulderDiff() + ":" + this.s2A.getDeep() + ":" + this.s2A.getX1() + ":" + this.s2A.getX2());
            case 8:
                return Command.WriteSpecifyLocationData(2, this.s2B.getShoulderDiff() + ":" + this.s2B.getDeep() + ":" + this.s2B.getX1() + ":" + this.s2B.getX2());
            case 9:
            case 10:
            default:
                return bArr;
            case 11:
                return Command.WriteSpecifyLocationData(69, this.f402s5.getX() + ":" + this.f402s5.getY() + ":" + this.f402s5.getDeep());
            case 12:
                return Command.WriteSpecifyLocationData(70, this.f403s8.getLength() + ":" + this.f403s8.getHeight1() + ":" + this.f403s8.getHeight2());
            case 13:
                return Command.WriteSpecifyLocationData(65, this.e9S1A.getShoulderDiff() + ":" + this.e9S1A.getHigh1() + ":" + this.e9S1A.getHigh2() + ":" + this.e9S1A.getTipX() + ":" + this.e9S1A.getStartX() + ":" + this.e9S1A.getCenterY());
            case 14:
                return Command.WriteSpecifyLocationData(67, this.e9S1C.getShoulderDiff() + ":" + this.e9S1C.getLeftThick() + ":" + this.e9S1C.getRightThick() + ":" + this.e9S1C.getTipX() + ":" + this.e9S1C.getK());
            case 15:
                return Command.WriteSpecifyLocationData(1, this.e9S2A.getShoulderDiff() + ":" + this.e9S2A.getDeep() + ":" + this.e9S2A.getTipX() + ":" + this.e9S2A.getLeftY());
            case 16:
                return Command.WriteSpecifyLocationData(2, this.e9S2B.getShoulderDiff() + ":" + this.e9S2B.getDeep() + ":" + this.e9S2B.getTipX() + ":" + this.e9S2B.getLeftY());
        }
    }

    private void saveJson(String str, File file) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.d(TAG, "saveJson() called with: json = [" + str + "], filePath = [" + file.getPath() + "]");
        FileUtil.saveStrToFile(str, file.getPath());
    }

    public void setDc(DecoderCutterDistance decoderCutterDistance) {
        this.f401dc = decoderCutterDistance;
    }

    public void setS1A(S1A s1a) {
        this.s1A = s1a;
    }

    public void setS1B(S1B s1b) {
        this.s1B = s1b;
    }

    public void setS1C(S1C s1c) {
        this.s1C = s1c;
    }

    public void setS1D(S1D s1d) {
        this.s1D = s1d;
    }

    public void initClamp(String str, boolean z) {
        if (z) {
            this.dataNumber = 0;
            OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(0));
            return;
        }
        int i = this.dataNumber;
        if (i == 0) {
            try {
                setClampFix(String2ClampFix(str));
                saveJson(str, getSaveFilePath(this.clampFixFile));
            } catch (Exception unused) {
            }
            this.dataNumber = 1;
            OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
            return;
        }
        if (i == 1) {
            try {
                if (CuttingMachine.getInstance().isE9()) {
                    setE9S2A(String2E9S2A(str));
                    saveJson(str, getSaveFilePath(this.E9_S2A_json));
                } else {
                    setS2A(String2S2A(str));
                    saveJson(str, getSaveFilePath(this.S2A_json));
                }
            } catch (Exception unused2) {
            }
            this.dataNumber = 2;
            OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
            return;
        }
        if (i == 2) {
            try {
                if (!CuttingMachine.getInstance().isE9()) {
                    setS2B(String2S2B(str));
                    saveJson(str, getSaveFilePath(this.S2B_json));
                }
            } catch (Exception unused3) {
            }
            this.dataNumber = 64;
            OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
            return;
        }
        switch (i) {
            case 64:
                try {
                    setDc(String2Dc(str));
                    saveJson(str, getSaveFilePath(this.dc_json));
                } catch (Exception unused4) {
                }
                this.dataNumber = 65;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 65:
                try {
                    if (CuttingMachine.getInstance().isE9()) {
                        setE9S1A(String2E9S1A(str));
                        saveJson(str, getSaveFilePath(this.E9_S1A_json));
                    } else {
                        setS1A(string2S1A(str));
                        saveJson(str, getSaveFilePath(this.S1A_json));
                    }
                } catch (Exception unused5) {
                }
                this.dataNumber = 66;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 66:
                try {
                    setS1B(string2S1B(str));
                    saveJson(str, getSaveFilePath(this.S1B_json));
                } catch (Exception unused6) {
                }
                this.dataNumber = 67;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 67:
                try {
                    if (CuttingMachine.getInstance().isE9()) {
                        setE9S1C(String2E9S1C(str));
                        saveJson(str, getSaveFilePath(this.E9_S1C_json));
                    } else {
                        setS1C(string2S1C(str));
                        saveJson(str, getSaveFilePath(this.S1C_json));
                    }
                } catch (Exception unused7) {
                }
                this.dataNumber = 68;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 68:
                try {
                    setS1D(string2S1D(str));
                    saveJson(str, getSaveFilePath(this.S1D_json));
                } catch (Exception unused8) {
                }
                this.dataNumber = 69;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 69:
                try {
                    if (CuttingMachine.getInstance().isAlpha()) {
                        setS5(string2S5(str));
                        saveJson(str, getSaveFilePath(this.S5_json));
                    } else {
                        setE9S5(string2E9S5(str));
                        saveJson(str, getSaveFilePath(this.S5_json));
                    }
                    this.dataNumber = 70;
                } catch (Exception unused9) {
                }
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 70:
                try {
                    setS8(string2S8(str));
                    saveJson(str, getSaveFilePath(this.S8_json));
                } catch (Exception unused10) {
                }
                this.dataNumber = 71;
                OperationManager.getInstance().sendOrder(Command.ReadSpecifyLocationData(this.dataNumber));
                return;
            case 71:
                this.dataNumber = -1;
                OperationManager.getInstance().sendOrder(Command.BuzzerOperation(1, 3), OperateType.RING);
                return;
            default:
                return;
        }
    }

    public C1979S8 getS8() {
        return this.f403s8;
    }

    public void setS8(C1979S8 c1979s8) {
        this.f403s8 = c1979s8;
    }

    private C1979S8 string2S8(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 2) {
            return null;
        }
        C1979S8 c1979s8 = new C1979S8();
        c1979s8.setLength(Integer.parseInt(split[0]));
        c1979s8.setHeight1(Integer.parseInt(split[1]));
        c1979s8.setHeight2(Integer.parseInt(split[2]));
        return c1979s8;
    }

    private S2B String2S2B(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 3) {
            return null;
        }
        S2B s2b = new S2B();
        s2b.setShoulderDiff(Integer.parseInt(split[0]));
        s2b.setDeep(Integer.parseInt(split[1]));
        s2b.setX1(Integer.parseInt(split[2]));
        s2b.setX2(Integer.parseInt(split[3]));
        return s2b;
    }

    private S2A String2S2A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 3) {
            return null;
        }
        S2A s2a = new S2A();
        s2a.setShoulderDiff(Integer.parseInt(split[0]));
        s2a.setDeep(Integer.parseInt(split[1]));
        s2a.setX1(Integer.parseInt(split[2]));
        s2a.setX2(Integer.parseInt(split[3]));
        return s2a;
    }

    private E9S1A String2E9S1A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 6) {
            return null;
        }
        E9S1A e9s1a = new E9S1A();
        e9s1a.setShoulderDiff(Integer.parseInt(split[0]));
        e9s1a.setHigh1(Integer.parseInt(split[1]));
        e9s1a.setHigh2(Integer.parseInt(split[2]));
        e9s1a.setTipX(Integer.parseInt(split[3]));
        e9s1a.setStartX(Integer.parseInt(split[4]));
        e9s1a.setCenterY(Integer.parseInt(split[5]));
        e9s1a.setRightY(Integer.parseInt(split[6]));
        return e9s1a;
    }

    private E9S1C String2E9S1C(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 3) {
            return null;
        }
        E9S1C e9s1c = new E9S1C();
        e9s1c.setShoulderDiff(Integer.parseInt(split[0]));
        e9s1c.setLeftThick(Integer.parseInt(split[1]));
        e9s1c.setRightThick(Integer.parseInt(split[2]));
        e9s1c.setTipX(Integer.parseInt(split[3]));
        e9s1c.setK(Float.parseFloat(split[4]));
        return e9s1c;
    }

    private E9S2A String2E9S2A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 3) {
            return null;
        }
        E9S2A e9s2a = new E9S2A();
        e9s2a.setShoulderDiff(Integer.parseInt(split[0]));
        e9s2a.setTipX(Integer.parseInt(split[2]));
        e9s2a.setLeftY(Integer.parseInt(split[3]));
        return e9s2a;
    }

    private E9S2B String2E9S2B(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 3) {
            return null;
        }
        E9S2B e9s2b = new E9S2B();
        e9s2b.setShoulderDiff(Integer.parseInt(split[0]));
        e9s2b.setTipX(Integer.parseInt(split[2]));
        e9s2b.setLeftY(Integer.parseInt(split[3]));
        return e9s2b;
    }

    private S1D string2S1D(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        S1D s1d = new S1D();
        s1d.setShoulderDiff(Integer.parseInt(split[0]));
        s1d.setRightDiff(Integer.parseInt(split[1]));
        s1d.setLeftDiff(Integer.parseInt(split[2]));
        return s1d;
    }

    private S1C string2S1C(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        S1C s1c = new S1C();
        s1c.setShoulderDiff(Integer.parseInt(split[0]));
        s1c.setRightDiff(Integer.parseInt(split[1]));
        s1c.setLeftDiff(Integer.parseInt(split[2]));
        return s1c;
    }

    private S1B string2S1B(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        S1B s1b = new S1B();
        s1b.setShoulderDiffUp(Integer.parseInt(split[0]));
        s1b.setShoulderDiffDown(Integer.parseInt(split[1]));
        s1b.setX1Up(Integer.parseInt(split[2]));
        s1b.setX2Up(Integer.parseInt(split[3]));
        s1b.setX1Down(Integer.parseInt(split[4]));
        s1b.setX2Down(Integer.parseInt(split[5]));
        s1b.setHigh1(Integer.parseInt(split[6]));
        s1b.setHigh2(Integer.parseInt(split[7]));
        return s1b;
    }

    private S1A string2S1A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        S1A s1a = new S1A();
        s1a.setShouldDiff(Integer.parseInt(split[0]));
        s1a.setHigh(Integer.parseInt(split[1]));
        s1a.setX1(Integer.parseInt(split[2]));
        s1a.setX2(Integer.parseInt(split[3]));
        return s1a;
    }

    private DecoderCutterDistance String2Dc(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        DecoderCutterDistance decoderCutterDistance = new DecoderCutterDistance();
        decoderCutterDistance.setXDistance(Integer.parseInt(split[0]));
        decoderCutterDistance.setYDistance(Integer.parseInt(split[1]));
        if (split.length >= 6) {
            decoderCutterDistance.setS1X(Integer.parseInt(split[2]));
            decoderCutterDistance.setS1Y(Integer.parseInt(split[3]));
            decoderCutterDistance.setS2X(Integer.parseInt(split[4]));
            decoderCutterDistance.setS2Y(Integer.parseInt(split[5]));
        }
        return decoderCutterDistance;
    }

    private ClampFix String2ClampFix(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length <= 2) {
            return null;
        }
        ClampFix clampFix = new ClampFix();
        clampFix.setLeftFix(Integer.parseInt(split[0]));
        clampFix.setRightFix(Integer.parseInt(split[1]));
        clampFix.setTipFix(Integer.parseInt(split[2]));
        return clampFix;
    }

    public boolean checkHaveRiskCutClamp(List<StepBean> list, int i) {
        if (list != null) {
            boolean z = false;
            for (StepBean stepBean : list) {
                if (stepBean.getRule().contains("SUP:1")) {
                    z = true;
                }
                if (stepBean.getRule().contains("SUP:0")) {
                    z = false;
                }
                if (z && checkHaveRiskCutClamp(OperationManager.getInstance().getKeyAlignInfo(), stepBean.getX(), stepBean.getY(), stepBean.getZ(), i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean checkHaveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3, int i4) {
        int clampFace = keyAlignInfo.getClampFace();
        int shoulderCutter = keyAlignInfo.getShoulderCutter();
        int leftCutter = keyAlignInfo.getLeftCutter();
        int rightCutter = keyAlignInfo.getRightCutter();
        int cmm2StepX = UnitConvertUtil.cmm2StepX(i4 / 2);
        switch (this.clamp) {
            case S1_A:
                int clampFace2 = keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
                if (i3 >= this.s1A.getHigh() + clampFace2) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1a);
                    return true;
                }
                if (i2 < ((shoulderCutter - UnitConvertUtil.cmm2StepY(10)) - this.s1A.getShouldDiff()) - cmm2StepX) {
                    return false;
                }
                if (i3 >= clampFace2) {
                    if (i > (leftCutter - cmm2StepX) - UnitConvertUtil.cmm2StepX(10)) {
                        ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                        return true;
                    }
                    if (i < rightCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                        ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                        return true;
                    }
                }
                int shoulder1 = keyAlignInfo.getShoulder1();
                if (shoulder1 == 0 || i2 < (shoulder1 - UnitConvertUtil.cmm2StepY(10)) - cmm2StepX) {
                    return false;
                }
                if (i > (leftCutter - cmm2StepX) - UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                    return true;
                }
                if (i < rightCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                    return true;
                }
                return false;
            case S1_B:
            case S1_B_D:
                int clampFace3 = keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
                if (i3 >= this.s1B.getHigh1() + clampFace3) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1a);
                    return true;
                }
                if (i3 < clampFace3 || i2 < ((shoulderCutter - UnitConvertUtil.cmm2StepY(10)) - this.s1B.getShoulderDiffUp()) - cmm2StepX) {
                    return false;
                }
                if (i > (leftCutter - cmm2StepX) - UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
                    return true;
                }
                if (i >= rightCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                    return false;
                }
                ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
                return true;
            case S1_C:
                if (i3 > clampFace - UnitConvertUtil.cmm2StepZ(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1cShouldAlign);
                    return true;
                }
                if (i2 < ((shoulderCutter - UnitConvertUtil.cmm2StepY(10)) - this.s1C.getShoulderDiff()) - cmm2StepX) {
                    return false;
                }
                if (i < rightCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10) && i3 > clampFace - (175.0f / MachineData.dZScale)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1cShouldAlign);
                    return true;
                }
                if (i2 > shoulderCutter) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1cShouldAlign);
                    return true;
                }
                return false;
            case S1_D:
                if (i3 > clampFace - (175.0f / MachineData.dZScale)) {
                    ErrorHelper.handleError(-4);
                    return true;
                }
                if (i2 > shoulderCutter) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1dShouldAlign);
                    return true;
                }
                return false;
            case S2_A:
                if (i < leftCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampS2a);
                    return true;
                }
                return false;
            case S2_B:
                if (i < leftCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampS2b);
                    return true;
                }
                return false;
            case S3:
                new C1974S3().haveRiskCutClamp(keyAlignInfo, i, i2, i3);
                return false;
            case S4:
            default:
                return false;
            case S5:
                boolean z = ((float) (i - getDC().getxDistance())) > ((float) getS5().getX()) - (300.0f / MachineData.dXScale);
                boolean z2 = ((float) i) < ((float) (keyAlignInfo.getRight() + getInstance().getDC().getxDistance())) - (700.0f / MachineData.dXScale);
                if (z || z2) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS5);
                    return true;
                }
                if (i2 - getDC().getyDistance() < getS5().getY() + (300.0f / MachineData.dXScale)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS5);
                    return true;
                }
                if (i3 > (getS5().getDeep() + keyAlignInfo.getClampFace()) - (20.0f / MachineData.dZScale)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS5);
                    return true;
                }
                return false;
            case S8:
                int shoulder = keyAlignInfo.getShoulder() + getDC().getyDistance();
                int length = shoulder - this.f403s8.getLength();
                if (i2 > shoulder + cmm2StepX || i2 < length - cmm2StepX) {
                    return false;
                }
                float f = i3;
                if (f >= (keyAlignInfo.getClampFace() + this.f403s8.getHeight1()) - (10.0f / MachineData.dZScale)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
                    return true;
                }
                if (f <= keyAlignInfo.getClampFace() - (10.0f / MachineData.dZScale)) {
                    return false;
                }
                if (i > (leftCutter - cmm2StepX) - UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
                    return true;
                }
                if (i < rightCutter + cmm2StepX + UnitConvertUtil.cmm2StepX(10)) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
                    return true;
                }
                return false;
            case E9S1A:
                return this.e9S1A.haveRiskCutClamp(keyAlignInfo, i, i2, i3);
            case E9S1C:
                return this.e9S1C.haveRiskCutClamp(keyAlignInfo, i, i2, i3);
            case E9S2A:
                return this.e9S2A.haveRiskCutClamp(keyAlignInfo, i, i2, i3);
            case E9S2B:
                return this.e9S2B.haveRiskCutClamp(keyAlignInfo, i, i2, i3);
            case E9S3:
                return new E9S3().haveRiskCutClamp(keyAlignInfo, i, i2, i3);
            case E9Honda:
                return e9HaveRiskCutHondaClamp(keyAlignInfo, i, i2, i3, i4);
            case E9S4:
                return e9HaveRiskCutE9S4Clamp(keyAlignInfo, i, i2, i3, i4);
        }
    }

    private boolean e9HaveRiskCutE9S4Clamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3, int i4) {
        int i5 = i4 / 2;
        if (i < keyAlignInfo.getTipCutter() + UnitConvertUtil.cmm2StepX(i5 + 360)) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS4);
            return true;
        }
        if (i <= keyAlignInfo.getTipCutter() + UnitConvertUtil.cmm2StepX(2240 - i5)) {
            return false;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS4);
        return true;
    }

    private boolean e9HaveRiskCutHondaClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3, int i4) {
        if (i2 <= keyAlignInfo.getLeftCutter() - UnitConvertUtil.cmm2StepY((i4 / 2) + 10)) {
            return false;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampS2a);
        return true;
    }

    public boolean checkHasCalibrated(KeyInfo keyInfo) {
        if (!checkDcCalibrated()) {
            return false;
        }
        if (CuttingMachine.getInstance().isE9()) {
            return checkE9ClampCalibrated(keyInfo);
        }
        return checkAlphaClampCalibrated(keyInfo);
    }

    private boolean checkAlphaClampCalibrated(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean == null) {
            return true;
        }
        if ("S1".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                return checkS1aCalibrated();
            }
            if ("B".equals(clampBean.getClampSide())) {
                return checkS1bCalibrated();
            }
            if ("C".equals(clampBean.getClampSide())) {
                return checkS1cCalibrated();
            }
            if ("D".equals(clampBean.getClampSide())) {
                return checkS1dCalibrated();
            }
            return true;
        }
        if (!"S2".equals(clampBean.getClampNum())) {
            return true;
        }
        if ("A".equals(clampBean.getClampSide())) {
            return checkS2aCalibrated();
        }
        if ("B".equals(clampBean.getClampSide())) {
            return checkS2bCalibrated();
        }
        return true;
    }

    private boolean checkE9ClampCalibrated(KeyInfo keyInfo) {
        ClampBean clampBean;
        if (!keyInfo.isNewHonda() && (clampBean = keyInfo.getClampBean()) != null) {
            if ("S1".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide()) || "B".equals(clampBean.getClampSide())) {
                    return checkE9S1aCalibrated();
                }
                if ("C".equals(clampBean.getClampSide()) || "D".equals(clampBean.getClampSide())) {
                    return checkE9S1cCalibrated() && checkE9S1aCalibrated();
                }
            } else if ("S2".equals(clampBean.getClampNum()) && ("A".equals(clampBean.getClampSide()) || "B".equals(clampBean.getClampSide()))) {
                return checkE9S2aCalibrated();
            }
        }
        return true;
    }

    private boolean checkS2bCalibrated() {
        if (getS2B() != null) {
            return true;
        }
        ErrorHelper.handleError(71);
        return false;
    }

    private boolean checkS5Calibrated() {
        if (getS5() != null) {
            return true;
        }
        ErrorHelper.handleError(74);
        return false;
    }

    private boolean checkS8Calibrated() {
        if (getS8() != null) {
            return true;
        }
        ErrorHelper.handleError(77);
        return false;
    }

    private boolean checkS2aCalibrated() {
        if (getS2A() != null) {
            return true;
        }
        ErrorHelper.handleError(70);
        return false;
    }

    private boolean checkS1dCalibrated() {
        if (getS1D() != null) {
            return true;
        }
        ErrorHelper.handleError(69);
        return false;
    }

    private boolean checkS1cCalibrated() {
        if (getS1C() != null) {
            return true;
        }
        ErrorHelper.handleError(68);
        return false;
    }

    private boolean checkS1bCalibrated() {
        if (getS1B() != null) {
            return true;
        }
        ErrorHelper.handleError(67);
        return false;
    }

    private boolean checkS1aCalibrated() {
        if (getS1A() != null) {
            return true;
        }
        ErrorHelper.handleError(66);
        return false;
    }

    private boolean checkE9S1aCalibrated() {
        if (getE9S1A() != null) {
            return true;
        }
        ErrorHelper.handleError(66);
        return false;
    }

    private boolean checkE9S1cCalibrated() {
        if (getE9S1C() != null) {
            return true;
        }
        ErrorHelper.handleError(68);
        return false;
    }

    private boolean checkE9S2aCalibrated() {
        if (getE9S2A() != null) {
            return true;
        }
        ErrorHelper.handleError(70);
        return false;
    }

    private boolean checkE9S2bCalibrated() {
        if (getE9S2B() != null) {
            return true;
        }
        ErrorHelper.handleError(71);
        return false;
    }

    private boolean checkE9S5Calibrated() {
        if (getE9S5() != null) {
            return true;
        }
        ErrorHelper.handleError(74);
        return false;
    }

    private boolean checkDcCalibrated() {
        if (getDC() != null) {
            return true;
        }
        ErrorHelper.handleError(50);
        return false;
    }

    public boolean checkHasCalibrated(String str) {
        Clamp clampFromFileName;
        if (TextUtils.isEmpty(str) || !checkDcCalibrated() || (clampFromFileName = getClampFromFileName(str)) == null) {
            return false;
        }
        switch (clampFromFileName) {
            case S1_A:
                return checkS1aCalibrated();
            case S1_B:
                return checkS1bCalibrated();
            case S1_C:
                return checkS1cCalibrated();
            case S1_D:
                return checkS1dCalibrated();
            case S2_A:
                return checkS2aCalibrated();
            case S2_B:
                return checkS2bCalibrated();
            case S3:
            case S4:
            default:
                return true;
            case S5:
                return false;
            case S8:
                return checkS8Calibrated();
        }
    }

    public void clear() {
        this.s1A = null;
        this.s1B = null;
        this.s1C = null;
        this.s1D = null;
        this.f401dc = null;
        this.s2A = null;
        this.s2B = null;
        this.clampFix = null;
    }

    public void setCurrentClamp(Clamp clamp) {
        this.clamp = clamp;
    }

    public boolean checkHasCalibrated(Clamp clamp) {
        if (clamp == null) {
            return false;
        }
        int i = C19721.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()];
        if (i != 21) {
            switch (i) {
                case 3:
                    return checkS1aCalibrated();
                case 4:
                    return checkS1bCalibrated();
                case 5:
                    return checkS1cCalibrated();
                case 6:
                    return checkS1dCalibrated();
                case 7:
                    return checkS2aCalibrated();
                case 8:
                    return checkS2bCalibrated();
                default:
                    switch (i) {
                        case 11:
                            return checkS5Calibrated();
                        case 12:
                            return checkS8Calibrated();
                        case 13:
                            return checkE9S1aCalibrated();
                        case 14:
                            return checkE9S1cCalibrated();
                        case 15:
                            return checkE9S2aCalibrated();
                        case 16:
                            return checkE9S2bCalibrated();
                        default:
                            return true;
                    }
            }
        }
        return checkE9S5Calibrated();
    }

    public boolean checkHaveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        return checkHaveRiskCutClamp(keyAlignInfo, i, i2, i3, ToolSizeManager.getCutterSize());
    }
}
