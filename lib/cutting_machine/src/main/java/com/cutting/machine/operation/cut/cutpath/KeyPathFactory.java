package com.liying.core.operation.cut.cutpath;

import com.liying.core.KeyAlignInfo;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.KeyType;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.blankCutPath.AY1AngleKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.AbusAngleKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.AngleKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.DimpleKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.DoubleInsideGrooveKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.DoubleOutsideGrooveKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.DoubleSideKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.HY18KeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.PeugeotKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.SideTooth3KsCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.SingleInsideGrooveKeyCutNewPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.SingleInsideGrooveKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.SingleOutsideGrooveKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.SingleSideKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.TubularKeyCutPath;
import com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import java.util.List;

/* loaded from: classes2.dex */
public class KeyPathFactory {
    public static List<StepBean> getCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        KeyBlankCutPath hY18KeyCutPath;
        KeyInfo keyInfo = dataParam.getKeyInfo();
        KeyData keyData = new KeyData(keyInfo);
        if (keyInfo.getIcCard() == 1311 || keyInfo.getIcCard() == 1372) {
            hY18KeyCutPath = new HY18KeyCutPath(keyAlignInfo, dataParam);
        } else {
            switch (C19921.$SwitchMap$com$liying$core$bean$KeyType[keyData.getKeyType().ordinal()]) {
                case 1:
                    hY18KeyCutPath = new DoubleSideKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 2:
                    hY18KeyCutPath = new DoubleOutsideGrooveKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 3:
                    hY18KeyCutPath = new SingleOutsideGrooveKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 4:
                    if (keyInfo.getIcCard() == 1287 || keyInfo.getIcCard() == 601287) {
                        hY18KeyCutPath = new SingleInsideGrooveKeyCutNewPath(keyAlignInfo, dataParam);
                        break;
                    } else {
                        hY18KeyCutPath = new SingleInsideGrooveKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    }
                    break;
                case 5:
                    if (keyData.PeugeoKey()) {
                        hY18KeyCutPath = new PeugeotKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    } else {
                        hY18KeyCutPath = new DoubleInsideGrooveKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    }
                case 6:
                    hY18KeyCutPath = new DimpleKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 7:
                    hY18KeyCutPath = new TubularKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 8:
                    hY18KeyCutPath = new SingleSideKeyCutPath(keyAlignInfo, dataParam);
                    break;
                case 9:
                    if (dataParam.getClamp() == Clamp.S9_A || dataParam.getClamp() == Clamp.S9_B) {
                        hY18KeyCutPath = new AbusAngleKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    } else if (dataParam.getClamp() == Clamp.S9_C || dataParam.getClamp() == Clamp.S9_D) {
                        hY18KeyCutPath = new AY1AngleKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    } else {
                        hY18KeyCutPath = new AngleKeyCutPath(keyAlignInfo, dataParam);
                        break;
                    }
                    break;
                case 10:
                    hY18KeyCutPath = new SideTooth3KsCutPath(keyAlignInfo, dataParam);
                    break;
                default:
                    hY18KeyCutPath = null;
                    break;
            }
        }
        if (hY18KeyCutPath == null) {
            return null;
        }
        List<StepBean> cutPathSteps = hY18KeyCutPath.getCutPathSteps();
        if (ClampManager.getInstance().checkHaveRiskCutClamp(cutPathSteps, dataParam.getCutterSize())) {
            return null;
        }
        return cutPathSteps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.liying.core.operation.cut.cutpath.KeyPathFactory$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19921 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_INSIDE_GROOVE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DIMPLE_KEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TUBULAR_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_SIDE_KEY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.ANGLE_KEY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_TOOTH_3KS_KEY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
