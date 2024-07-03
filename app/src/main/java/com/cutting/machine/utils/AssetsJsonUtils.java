package com.cutting.machine.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.MachineType;
import com.cutting.machine.StepBeanTypeAdapter;
import com.cutting.machine.bean.CalibrationBean;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AssetsJsonUtils {
    public static final String TAG = "AssetsJsonUtils";

    public static List<StepBean> getCommonSteps(Context context, String str) {
        return json2Steps(getJsonStringFromAssets(context, str));
    }

    public static List<StepBean> json2Steps(String str) {
        return new GsonBuilder().registerTypeAdapter(StepBean.class, new StepBeanTypeAdapter()).create().fromJson(str, CalibrationBean.class).getStepBeanList();
    }

    public static List<StepBean> getKeyDecodePathSteps(String str, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("Decoder");
            String str2 = "";
            for (String str3 : jSONObject.getString("VariableSpace").split(",")) {
                if (str3.contains(String.valueOf(i))) {
                    str2 = str3;
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                JSONArray jSONArray = jSONObject.getJSONArray(str2);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    StepBean stepBean = new StepBean();
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    stepBean.setS(jSONObject2.getString("S"));
                    stepBean.setT(jSONObject2.getInt("T"));
                    stepBean.setX(Math.round(jSONObject2.getInt("X") / MachineData.dXScale));
                    stepBean.setY(Math.round(jSONObject2.getInt("Y") / MachineData.dYScale));
                    stepBean.setZ(Math.round(jSONObject2.getInt("Z") / MachineData.dZScale));
                    stepBean.setvStr(jSONObject2.getString("V"));
                    stepBean.setRule(jSONObject2.getString("Rule"));
                    stepBean.setDes(jSONObject2.getString("Des"));
                    arrayList.add(stepBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<StepBean> getKeyDecodePathSteps(KeyInfo keyInfo) {
        return getKeyDecodePathSteps(keyInfo, 0);
    }

    public static List<StepBean> getKeyDecodePathSteps(KeyInfo keyInfo, int i) {
        String str;
        String str2;
        String str3;
        Clamp currentClamp;
        int type = keyInfo.getType();
        if (keyInfo.getIcCard() == 1311 || keyInfo.getIcCard() == 1372) {
            type = 5;
        }
        int i2 = (type == 0 && (currentClamp = ClampManager.getInstance().getCurrentClamp()) != null && currentClamp.getClampStr().contains("S2")) ? 1 : type;
        Key.enumMachineType enummachinetype = Key.enumMachineType.beta;
        int i3 = C19981.$SwitchMap$com$liying$core$MachineType[CuttingMachine.getInstance().getMachineType().ordinal()];
        if (i3 == 1) {
            enummachinetype = Key.enumMachineType.alpha;
        } else if (i3 == 2) {
            enummachinetype = Key.enumMachineType.beta;
        } else if (i3 == 3) {
            enummachinetype = Key.enumMachineType.e9_Pro;
        }
        Key.enumMachineType enummachinetype2 = enummachinetype;
        String spaceStr = keyInfo.getSpaceStr();
        String spaceWidthStr = keyInfo.getSpaceWidthStr();
        String depthStr = keyInfo.getDepthStr();
        String row_pos = keyInfo.getRow_pos();
        if (!keyInfo.isAngleDimple()) {
            str = spaceWidthStr;
            str2 = depthStr;
            str3 = row_pos;
        } else if (i == 0) {
            spaceStr = spaceStr.split(";")[0];
            str = spaceWidthStr.split(";")[0];
            str2 = depthStr.split(";")[0];
            str3 = row_pos.split(";")[0];
        } else {
            spaceStr = spaceStr.split(";")[1];
            String str4 = spaceWidthStr.split(";")[1];
            String str5 = depthStr.split(";")[1];
            str3 = row_pos.split(";")[1];
            str = str4;
            str2 = str5;
        }
        String GetKeyDecoderPath = mdKeyDecoderPathClass.GetKeyDecoderPath(enummachinetype2, keyInfo.getIcCard(), i2, keyInfo.getAlign(), keyInfo.getWidth(), keyInfo.getCuts().replace(",", "-"), spaceStr, str, str2, str3, keyInfo.getType_specific_info());
        Log.i(TAG, "getKeyDecodePathSteps: " + GetKeyDecoderPath);
        return getKeyDecodePathSteps(GetKeyDecoderPath, spaceStr.split(";")[0].split(",").length);
    }

    public static List<StepBean> getKeyDecodeLocationSteps(Context context, KeyInfo keyInfo) {
        String keyDecodeLocationJsonPath;
        if (CuttingMachine.getInstance().isE9()) {
            keyDecodeLocationJsonPath = getKeyDecodeLocationE9JsonPath(keyInfo);
        } else {
            keyDecodeLocationJsonPath = getKeyDecodeLocationJsonPath(keyInfo);
        }
        return getCommonSteps(context, keyDecodeLocationJsonPath);
    }

    public static String getKeyDecodeLocationJsonPath(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 1287 || keyInfo.getIcCard() == 601287) {
            return "keyblank/decoder/S1-B(3KS).json";
        }
        if (keyInfo.getIcCard() == 611287) {
            return "keyblank/decoder/S1-B-D(DimpleShoulder).json";
        }
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean.getClampNum().equals("S1")) {
            if (clampBean.getClampSide().equals("A")) {
                return "keyblank/decoder/S1-A(ThreeendsTop).json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B-D(DimpleShoulder).json" : "keyblank/decoder/S1-B-D(DimpleTip).json" : "keyblank/decoder/S1-B-D(ThreeendsTop).json" : keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B(DimpleShoulder).json" : "keyblank/decoder/S1-B(DimpleTip).json" : "keyblank/decoder/S1-B(ThreeendsTop).json";
            }
            if (clampBean.getClampSide().equals("C")) {
                return TextUtils.equals("0", clampBean.getClampSlot()) ? "keyblank/decoder/S1-C.json" : "keyblank/decoder/S1-C(Down).json";
            }
            if (clampBean.getClampSide().equals("D")) {
                return keyInfo.isNewHonda() ? "keyblank/decoder/S1-D(Honda).json" : "keyblank/decoder/S1-D.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S2")) {
            if (clampBean.getClampSide().equals("A")) {
                return "keyblank/decoder/S2-A.json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return keyInfo.isNewHonda() ? "keyblank/decoder/S2-B(Honda).json" : "keyblank/decoder/S2-B.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return "keyblank/decoder/S3.json";
        }
        if (clampBean.getClampNum().equals("S4")) {
            return "keyblank/decoder/S4.json";
        }
        if (clampBean.getClampNum().equals("S6")) {
            return "keyblank/decoder/S6.json";
        }
        if (clampBean.getClampNum().equals("S10")) {
            return "keyblank/decoder/S10.json";
        }
        return null;
    }

    public static String getKeyDecodeLocationE9JsonPath(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean.getClampNum().equals("S1")) {
            if (keyInfo.isNewHonda()) {
                return "keyblank/decoder_e9/S1-H.json";
            }
            if (clampBean.getClampSide().equals("A") || clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder_e9/S1-A-D(DimpleShoulder).json" : "keyblank/decoder_e9/S1-A-D(DimpleTip).json" : "keyblank/decoder_e9/S1-A-D(ThreeendsTop).json" : keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder_e9/S1-A(DimpleShoulder).json" : "keyblank/decoder_e9/S1-A(DimpleTip).json" : "keyblank/decoder_e9/S1-A(ThreeendsTop).json";
            }
            if (clampBean.getClampSide().equals("C") || clampBean.getClampSide().equals("D")) {
                return "keyblank/decoder_e9/S1-C.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S2")) {
            if (keyInfo.isNewHonda()) {
                return "keyblank/decoder_e9/S2-B(Honda).json";
            }
            if (clampBean.getClampSide().equals("A")) {
                return "keyblank/decoder_e9/S2-A.json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return "keyblank/decoder_e9/S2-B.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return "keyblank/decoder_e9/S3.json";
        }
        if (clampBean.getClampNum().equals("S4")) {
            return "keyblank/decoder_e9/S4.json";
        }
        if (clampBean.getClampNum().equals("S6")) {
            return "keyblank/decoder_e9/S6.json";
        }
        return null;
    }

    public static List<StepBean> getKeyCutCutterLocationSteps(Context context, DataParam dataParam) {
        String keyCutCutterHeightJsonPath;
        if (CuttingMachine.getInstance().isE9()) {
            keyCutCutterHeightJsonPath = getKeyCutCutterHeightE9JsonPath(dataParam);
        } else {
            keyCutCutterHeightJsonPath = getKeyCutCutterHeightJsonPath(dataParam);
        }
        return getCommonSteps(context, keyCutCutterHeightJsonPath);
    }

    public static String getKeyCutCutterHeightE9JsonPath(DataParam dataParam) {
        KeyInfo keyInfo = dataParam.getKeyInfo();
        Clamp clamp = dataParam.getClamp();
        int clampMode = dataParam.getClampMode();
        switch (C19981.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return dataParam.isPlasticKey() ? "keyblank/cutter_e9/S1-A(No).json" : clampMode == 1 ? keyInfo.getType() == 6 ? "keyblank/cutter_e9/S1-A-D(Dimple).json" : "keyblank/cutter_e9/S1-A-D(Top).json" : keyInfo.getType() == 6 ? "keyblank/cutter_e9/S1-A(Dimple).json" : "keyblank/cutter_e9/S1-A(Top).json";
            case 2:
                return "keyblank/cutter_e9/S1-C.json";
            case 3:
                return keyInfo.isNewHonda() ? "keyblank/cutter_e9/S2-B(Honda).json" : "keyblank/cutter_e9/S2-A.json";
            case 4:
                return keyInfo.isNewHonda() ? "keyblank/cutter_e9/S2-B(Honda).json" : "keyblank/cutter_e9/S2-B.json";
            case 5:
                return "keyblank/cutter_e9/S1-H.json";
            case 6:
                return "keyblank/cutter_e9/S3.json";
            case 7:
                return "keyblank/cutter_e9/S4.json";
            default:
                return "";
        }
    }

    public static String getKeyCutCutterHeightJsonPath(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean.getClampNum().equals("S1")) {
            if (clampBean.getClampSide().equals("A")) {
                return keyInfo.getType() == 6 ? "keyblank/cutter/S1-B(No).json" : "keyblank/cutter/S1-A(Top).json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return keyInfo.getType() == 6 ? "keyblank/cutter/S1-B(Dimple).json" : clampBean.getClampSlot().equals("1") ? "keyblank/cutter/S1-B-D(Top).json" : keyInfo.isPlasticKey() ? "keyblank/cutter/S1-B(No).json" : "keyblank/cutter/S1-B(Top).json";
            }
            if (clampBean.getClampSide().equals("C")) {
                return "keyblank/cutter/S1-C.json";
            }
            if (clampBean.getClampSide().equals("D")) {
                return keyInfo.isNewHonda() ? "keyblank/cutter/S1-D(Honda).json" : "keyblank/cutter/S1-D.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S2")) {
            if (clampBean.getClampSide().equals("A")) {
                return "keyblank/cutter/S2-A.json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return keyInfo.isNewHonda() ? "keyblank/cutter/S2-B(Honda).json" : "keyblank/cutter/S2-B.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return "keyblank/cutter/S3.json";
        }
        if (clampBean.getClampNum().equals("S4")) {
            return "keyblank/cutter/S4.json";
        }
        if (clampBean.getClampNum().equals("S6")) {
            return "keyblank/cutter/S6.json";
        }
        return null;
    }

    public static String getKeyCutCutterHeightJsonPath(DataParam dataParam) {
        KeyInfo keyInfo = dataParam.getKeyInfo();
        if (keyInfo.getIcCard() == 1287 || keyInfo.getIcCard() == 601287) {
            return "keyblank/cutter/S1-B(No).json";
        }
        if (keyInfo.getIcCard() == 611287) {
            return "keyblank/cutter/S1-B(Dimple).json";
        }
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean.getClampNum().equals("S1")) {
            if (clampBean.getClampSide().equals("A")) {
                return keyInfo.getType() == 6 ? "keyblank/cutter/S1-B(No).json" : "keyblank/cutter/S1-A(Top).json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return keyInfo.getType() == 6 ? "keyblank/cutter/S1-B(Dimple).json" : clampBean.getClampSlot().equals("1") ? "keyblank/cutter/S1-B-D(Top).json" : dataParam.isPlasticKey() ? "keyblank/cutter/S1-B(No).json" : "keyblank/cutter/S1-B(Top).json";
            }
            if (clampBean.getClampSide().equals("C")) {
                return TextUtils.equals("0", clampBean.getClampSlot()) ? "keyblank/cutter/S1-C.json" : "keyblank/cutter/S1-C(Down).json";
            }
            if (clampBean.getClampSide().equals("D")) {
                return keyInfo.isNewHonda() ? "keyblank/cutter/S1-D(Honda).json" : "keyblank/cutter/S1-D.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S2")) {
            if (clampBean.getClampSide().equals("A")) {
                return "keyblank/cutter/S2-A.json";
            }
            if (clampBean.getClampSide().equals("B")) {
                return keyInfo.isNewHonda() ? "keyblank/cutter/S2-B(Honda).json" : "keyblank/cutter/S2-B.json";
            }
            return null;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return "keyblank/cutter/S3.json";
        }
        if (clampBean.getClampNum().equals("S4")) {
            return "keyblank/cutter/S4.json";
        }
        if (clampBean.getClampNum().equals("S6")) {
            return "keyblank/cutter/S6.json";
        }
        if (clampBean.getClampNum().equals("S9")) {
            return "keyblank/cutter/S9-B.json";
        }
        if (clampBean.getClampNum().equals("S10")) {
            return "keyblank/cutter/S10.json";
        }
        return null;
    }

    public static List<StepBean> getKeyCutLocationSteps(Context context, DataParam dataParam) {
        String keyCutLocationJsonPath;
        if (dataParam == null) {
            return null;
        }
        if (CuttingMachine.getInstance().isE9()) {
            keyCutLocationJsonPath = getKeyCutLocationE9JsonPath(dataParam);
        } else {
            keyCutLocationJsonPath = getKeyCutLocationJsonPath(dataParam);
        }
        return getCommonSteps(context, keyCutLocationJsonPath);
    }

    public static String getKeyCutLocationE9JsonPath(DataParam dataParam) {
        KeyInfo keyInfo = dataParam.getKeyInfo();
        Clamp clamp = dataParam.getClamp();
        int clampMode = dataParam.getClampMode();
        switch (C19981.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return dataParam.isPlasticKey() ? "keyblank/decoder_e9/S1-A(No).json" : clampMode == 1 ? keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder_e9/S1-A-D(DimpleShoulder).json" : "keyblank/decoder_e9/S1-A-D(DimpleTip).json" : "keyblank/decoder_e9/S1-A-D(ThreeendsTop).json" : keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder_e9/S1-A(DimpleShoulder).json" : "keyblank/decoder_e9/S1-A(DimpleTip).json" : "keyblank/decoder_e9/S1-A(Threeends).json";
            case 2:
                return "keyblank/decoder_e9/S1-C.json";
            case 3:
                return keyInfo.isNewHonda() ? "keyblank/decoder_e9/S2-B(Honda).json" : "keyblank/decoder_e9/S2-A.json";
            case 4:
                return keyInfo.isNewHonda() ? "keyblank/decoder_e9/S2-B(Honda).json" : "keyblank/decoder_e9/S2-B.json";
            case 5:
                return "keyblank/decoder_e9/S1-H.json";
            case 6:
                return "keyblank/decoder_e9/S3.json";
            case 7:
                return "keyblank/decoder_e9/S4.json";
            default:
                return "";
        }
    }

    public static String getKeyCutLocationJsonPath(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        return clampBean.getClampNum().equals("S1") ? clampBean.getClampSide().equals("A") ? "keyblank/decoder/S1-A(Threeends).json" : clampBean.getClampSide().equals("B") ? clampBean.getClampSlot().equals("1") ? keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B-D(DimpleShoulder).json" : "keyblank/decoder/S1-B-D(DimpleTip).json" : "keyblank/decoder/S1-B-D(ThreeendsTop).json" : keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B(DimpleShoulder).json" : "keyblank/decoder/S1-B(DimpleTip).json" : keyInfo.isPlasticKey() ? "keyblank/decoder/S1-B(No).json" : "keyblank/decoder/S1-B(Threeends).json" : clampBean.getClampSide().equals("C") ? "keyblank/decoder/S1-C.json" : clampBean.getClampSide().equals("D") ? keyInfo.isNewHonda() ? "keyblank/decoder/S1-D(Honda).json" : "keyblank/decoder/S1-D.json" : "" : clampBean.getClampNum().equals("S2") ? clampBean.getClampSide().equals("A") ? "keyblank/decoder/S2-A.json" : clampBean.getClampSide().equals("B") ? keyInfo.isNewHonda() ? "keyblank/decoder/S2-B(Honda).json" : "keyblank/decoder/S2-B.json" : "" : clampBean.getClampNum().equals("S3") ? "keyblank/decoder/S3.json" : clampBean.getClampNum().equals("S4") ? "keyblank/decoder/S4.json" : clampBean.getClampNum().equals("S6") ? "keyblank/decoder/S6.json" : "";
    }

    public static String getKeyCutLocationJsonPath(DataParam dataParam) {
        KeyInfo keyInfo = dataParam.getKeyInfo();
        if (keyInfo.getIcCard() == 1287 || keyInfo.getIcCard() == 601287) {
            return "keyblank/decoder/S1-B(3KS).json";
        }
        if (keyInfo.getIcCard() == 611287) {
            return "keyblank/decoder/S1-B-D(DimpleShoulder).json";
        }
        ClampBean clampBean = keyInfo.getClampBean();
        return clampBean.getClampNum().equals("S1") ? clampBean.getClampSide().equals("A") ? "keyblank/decoder/S1-A(Threeends).json" : clampBean.getClampSide().equals("B") ? clampBean.getClampSlot().equals("1") ? keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B-D(DimpleShoulder).json" : "keyblank/decoder/S1-B-D(DimpleTip).json" : "keyblank/decoder/S1-B-D(ThreeendsTop).json" : keyInfo.getType() == 6 ? keyInfo.getAlign() == 0 ? "keyblank/decoder/S1-B(DimpleShoulder).json" : "keyblank/decoder/S1-B(DimpleTip).json" : dataParam.isPlasticKey() ? "keyblank/decoder/S1-B(No).json" : "keyblank/decoder/S1-B(Threeends).json" : clampBean.getClampSide().equals("C") ? TextUtils.equals("0", clampBean.getClampSlot()) ? "keyblank/decoder/S1-C.json" : "keyblank/decoder/S1-C(Down).json" : clampBean.getClampSide().equals("D") ? keyInfo.isNewHonda() ? "keyblank/decoder/S1-D(Honda).json" : "keyblank/decoder/S1-D.json" : "" : clampBean.getClampNum().equals("S2") ? clampBean.getClampSide().equals("A") ? "keyblank/decoder/S2-A.json" : clampBean.getClampSide().equals("B") ? keyInfo.isNewHonda() ? "keyblank/decoder/S2-B(Honda).json" : "keyblank/decoder/S2-B.json" : "" : clampBean.getClampNum().equals("S3") ? "keyblank/decoder/S3.json" : clampBean.getClampNum().equals("S4") ? "keyblank/decoder/S4.json" : clampBean.getClampNum().equals("S6") ? "keyblank/decoder/S6.json" : clampBean.getClampNum().equals("S9") ? "keyblank/decoder/S9-B.json" : clampBean.getClampNum().equals("S10") ? "keyblank/decoder/S10.json" : "";
    }

    public static List<StepBean> getDuplicateDecodeLocationSteps(Context context, DuplicateDecodeParams duplicateDecodeParams) {
        return getCommonSteps(context, getDuplicateDecodeLocationJsonPath(duplicateDecodeParams));
    }

    private static String getDuplicateDecodeLocationJsonPath(DuplicateDecodeParams duplicateDecodeParams) {
        Clamp clamp = duplicateDecodeParams.getClamp();
        int clampMode = duplicateDecodeParams.getClampMode();
        switch (C19981.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return clampMode == 1 ? "duplicate/decoder_e9/S1-A-D(ThreeendsTop).json" : "duplicate/decoder_e9/S1-A(ThreeendsTop).json";
            case 2:
                return "duplicate/decoder_e9/S1-C.json";
            case 3:
                return "duplicate/decoder_e9/S2-A.json";
            case 4:
                return "duplicate/decoder_e9/S2-B.json";
            case 5:
            case 7:
            default:
                return null;
            case 6:
                return "duplicate/decoder_e9/S3.json";
            case 8:
                return "duplicate/decoder/S1-A(ThreeendsTop).json";
            case 9:
                return clampMode == 1 ? "duplicate/decoder/S1-B-D(ThreeendsTop).json" : "duplicate/decoder/S1-B(ThreeendsTop).json";
            case 10:
                return "duplicate/decoder/S1-C.json";
            case 11:
                return "duplicate/decoder/S1-D.json";
            case 12:
                return "duplicate/decoder/S2-A.json";
            case 13:
                return "duplicate/decoder/S2-B.json";
            case 14:
                return "duplicate/decoder/S3.json";
        }
    }

    public static List<StepBean> getDuplicateCutLocationSteps(Context context, DuplicateCutParams duplicateCutParams) {
        return getCommonSteps(context, getDuplicateCutLocationJsonPath(duplicateCutParams));
    }

    private static String getDuplicateCutLocationJsonPath(DuplicateCutParams duplicateCutParams) {
        Clamp clamp = duplicateCutParams.getClamp();
        int clampMode = duplicateCutParams.getClampMode();
        switch (C19981.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return clampMode == 1 ? "duplicate/decoder_e9/S1-A-D(ThreeendsTop).json" : "duplicate/decoder_e9/S1-A(Threeends).json";
            case 2:
                return "duplicate/decoder_e9/S1-C.json";
            case 3:
                return "duplicate/decoder_e9/S2-A.json";
            case 4:
                return "duplicate/decoder_e9/S2-B.json";
            case 5:
            case 7:
            default:
                return null;
            case 6:
                return "duplicate/decoder_e9/S3.json";
            case 8:
                return "duplicate/decoder/S1-A(Threeends).json";
            case 9:
                return clampMode == 1 ? "duplicate/decoder/S1-B-D(ThreeendsTop).json" : "duplicate/decoder/S1-B(Threeends).json";
            case 10:
                return "duplicate/decoder/S1-C.json";
            case 11:
                return "duplicate/decoder/S1-D.json";
            case 12:
                return "duplicate/decoder/S2-A.json";
            case 13:
                return "duplicate/decoder/S2-B.json";
            case 14:
                return "duplicate/decoder/S3.json";
        }
    }

    public static List<StepBean> getDuplicateCutCutterHeightLocationSteps(Context context, DuplicateCutParams duplicateCutParams) {
        return getCommonSteps(context, getDuplicateCutCutterHeightLocationJsonPath(duplicateCutParams));
    }

    private static String getDuplicateCutCutterHeightLocationJsonPath(DuplicateCutParams duplicateCutParams) {
        Clamp clamp = duplicateCutParams.getClamp();
        int clampMode = duplicateCutParams.getClampMode();
        switch (C19981.$SwitchMap$com$liying$core$clamp$Clamp[clamp.ordinal()]) {
            case 1:
                return clampMode == 1 ? "duplicate/cutter_e9/S1-A-D(Top).json" : "duplicate/cutter_e9/S1-A(Top).json";
            case 2:
                return "duplicate/cutter_e9/S1-C.json";
            case 3:
                return "duplicate/cutter_e9/S2-A.json";
            case 4:
                return "duplicate/cutter_e9/S2-B.json";
            case 5:
            case 7:
            default:
                return null;
            case 6:
                return "duplicate/cutter_e9/S3.json";
            case 8:
                return "duplicate/cutter/S1-A(Top).json";
            case 9:
                return clampMode == 1 ? "duplicate/cutter/S1-B-D(Top).json" : "duplicate/cutter/S1-B(Top).json";
            case 10:
                return "duplicate/cutter/S1-C.json";
            case 11:
                return "duplicate/cutter/S1-D.json";
            case 12:
                return "duplicate/cutter/S2-A.json";
            case 13:
                return "duplicate/cutter/S2-B.json";
            case 14:
                return "duplicate/cutter/S3.json";
        }
    }

    public static String getJsonStringFromAssets(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String replace = str.split("/")[r0.length - 1].replace(".json", "").replace("(", "[(]").replace(")", "[)]");
        try {
            InputStream open = context.getAssets().open(str);
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    return sb.toString().replaceFirst(replace, "stepBeanList");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<StepBean> getCalibrationSteps(Clamp clamp) {
        return json2Steps(getCalibrationJsonPath(clamp));
    }

    public static String getCalibrationJsonPath(Clamp clamp) {
        switch (clamp) {
            case S1_A:
                return "calibration/S1-A.json";
            case S1_B:
                return "calibration/S1-B.json";
            case S1_C:
                return "calibration/S1-C.json";
            case S1_D:
                return "calibration/S1-D.json";
            case S2_A:
                return "calibration/S2-A.json";
            case S2_B:
                return "calibration/S2-B.json";
            case S3:
            default:
                return "calibration/";
            case D_C:
                return "calibration/D-C.json";
            case S5:
                return "calibration/S5.json";
            case S8:
                return "calibration/S8.json";
        }
    }

    /* renamed from: com.cutting.machine.utils.AssetsJsonUtils$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19981 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$MachineType;

        static {
            int[] iArr = new int[Clamp.values().length];
            $SwitchMap$com$liying$core$clamp$Clamp = iArr;
            try {
                iArr[Clamp.E9S1A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1C.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2A.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2B.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9Honda.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S3.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S4.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_A.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_B.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_C.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_D.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_A.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_B.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S3.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.D_C.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S5.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S8.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr2 = new int[MachineType.values().length];
            $SwitchMap$com$liying$core$MachineType = iArr2;
            try {
                iArr2[MachineType.Alpha.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$liying$core$MachineType[MachineType.Beta.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$liying$core$MachineType[MachineType.E9.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }
}
