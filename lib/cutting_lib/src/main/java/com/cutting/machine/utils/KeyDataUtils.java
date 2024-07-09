package com.cutting.machine.utils;

import android.text.TextUtils;

import com.cutting.machine.bean.KeyInfo;
import com.spl.key.SpecificParamUtils;

import java.util.List;

/* loaded from: classes2.dex */
public class KeyDataUtils {
    public static String fillSpaceWidth(int i, String str, String str2) {
        String[] split = str.split(";");
        String str3 = i == 8 ? "300" : "80";
        String str4 = "";
        if (TextUtils.isEmpty(str2)) {
            for (String str5 : split) {
                String[] split2 = str5.split(",");
                for (int i2 = 0; i2 < split2.length; i2++) {
                    str4 = i2 == split2.length - 1 ? str4 + str3 + ";" : str4 + str3 + ",";
                }
            }
            return str4;
        }
        String[] split3 = str2.split(";");
        if (split.length <= split3.length) {
            return str2;
        }
        if (split3.length > 0) {
            String[] split4 = split3[0].split(",");
            if (split4.length > 0) {
                str3 = split4[0];
            }
        }
        for (String str6 : split) {
            String[] split5 = str6.split(",");
            for (int i3 = 0; i3 < split5.length; i3++) {
                str4 = i3 == split5.length - 1 ? str4 + str3 + ";" : str4 + str3 + ",";
            }
        }
        return str4;
    }

    public static String fillDepth(String str, String str2) {
        String[] split = str.split(";");
        String[] split2 = str2.split(";");
        String str3 = str2.endsWith(";") ? str2 : str2 + ";";
        if (split.length > split2.length) {
            for (int i = 0; i < split.length - split2.length; i++) {
                str3 = str3 + str2;
            }
        }
        return str3;
    }

    public static String fillNoneDepthNameData(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split(";");
        String[] split2 = str2.split(";");
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (TextUtils.isEmpty(str2) || split2.length == 0) {
                for (int i2 = 0; i2 < split[i].split(",").length; i2++) {
                    sb2.append(i2);
                    sb2.append(",");
                }
                sb = new StringBuilder(sb2.substring(0, sb2.length() - 1));
                sb.append(";");
            } else if (split2.length > i) {
                if (TextUtils.isEmpty(split2[i])) {
                    for (int i3 = 0; i3 < split[i].split(",").length; i3++) {
                        sb2.append(i3);
                        sb2.append(",");
                    }
                    sb2 = new StringBuilder(sb2.substring(0, sb2.length() - 1));
                } else {
                    sb2.append(split2[i]);
                }
                sb2.append(";");
            } else {
                for (int i4 = 0; i4 < split[i].split(",").length; i4++) {
                    sb2.append(i4);
                    sb2.append(",");
                }
                sb = new StringBuilder(sb2.substring(0, sb2.length() - 1));
                sb.append(";");
            }
            sb2 = sb;
        }
        return sb2.toString();
    }

    public static String getCutsBySpace(String str) {
        String[] split = str.split(";");
        String str2 = "{";
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(",");
            str2 = i == 0 ? str2 + split2.length : str2 + "-" + split2.length;
        }
        return str2 + "}";
    }

    public static void parseKeySpecificInfo(KeyInfo keyInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.replace("\t", "").split(";")) {
            String[] split = str2.split(":");
            if (split[0].equals(SpecificParamUtils.SIDE)) {
                keyInfo.setSide(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.GROOVE)) {
                keyInfo.setGroove(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.CUT_DEPTH)) {
                keyInfo.setCutDepth(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.GUIDE)) {
                keyInfo.setGuide(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.EXTRA_CUT)) {
                keyInfo.setExtraCut(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.NOSE)) {
                keyInfo.setNose(Integer.parseInt(split[1]));
            } else if (split[0].equals("inner_cut_length")) {
                keyInfo.setInnerCutLength(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.LAST_BITTING)) {
                keyInfo.setLastBitting(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.VARIABLE_SPACE)) {
                keyInfo.setVariableSpace(split[1]);
            } else if (split[0].equals("sibling_profile")) {
                keyInfo.setSiblingProfile(split[1]);
            } else if (split[0].equals("shape")) {
                keyInfo.setShape(Integer.parseInt(split[1]));
            } else if (split[0].equals("ReadBittingRule")) {
                keyInfo.setReadBittingRule(split[1]);
            } else if (split[0].equals("clamp_info")) {
                keyInfo.setClampInfo(split[1]);
            } else if (split[0].equals("S9clamp_info")) {
                keyInfo.setS9BclampInfo(split[1]);
            } else if (split[0].equals("isrule")) {
                keyInfo.setIsrule(Integer.parseInt(split[1]));
            } else if (split[0].equals("locked")) {
                keyInfo.setLocked(Integer.parseInt(split[1]));
            } else if (split[0].equals("decoder")) {
                keyInfo.setDecoder(split[1]);
            } else if (split[0].equals("cutter")) {
                if (!TextUtils.isEmpty(split[1]) && split[1].contains(",")) {
                    keyInfo.setCutterSize((int) (Float.parseFloat(split[1].split(",")[1]) * 100.0f));
                }
            } else if (split[0].equals("spacenew")) {
                keyInfo.setSpacenew(split[1]);
            } else if (split[0].equals("spacewidthnew")) {
                keyInfo.setSpacewidthnew(split[1]);
            } else if (split[0].equals("keyitemid")) {
                keyInfo.setKeyitemid(Integer.parseInt(split[1]));
            } else if (split[0].equals(SpecificParamUtils.INNER_CUT_ANGLE)) {
                String[] split2 = split[1].split(",");
                keyInfo.setInner_cut_angle(Integer.parseInt(split2[0]));
                if (split2.length > 1) {
                    keyInfo.setInnerCutX(Integer.parseInt(split2[1]));
                    keyInfo.setInnerCutY(Integer.parseInt(split2[2]));
                }
            } else if (split[0].equals("ext_cut")) {
                keyInfo.setExCut(split[1]);
            } else if (split[0].equals("setting_round")) {
                keyInfo.setSetting_round(split[1]);
            } else if (split[0].equals("cut_speed")) {
                keyInfo.setCut_speed(Integer.parseInt(split[1]));
            } else if (split[0].equals("shoulderblock")) {
                keyInfo.setShoulderBlock(Integer.parseInt(split[1]));
            } else if (split[0].equals("cut_sharpentype")) {
                keyInfo.setCutSharpenType(split[1]);
            } else if (split[0].equals("ext_top_cut")) {
                keyInfo.setExtTopCut(split[1]);
            } else if (split[0].equals("ext_jaw")) {
                keyInfo.setExtJaw(split[1]);
            } else if (split[0].equals("remaining_depth")) {
                keyInfo.setRemainingDepth(Integer.parseInt(split[1]));
            } else if (split[0].equals("ext_doublekey_depth")) {
                keyInfo.setExtDoublekeyDepth(Integer.parseInt(split[1]));
            } else if (split[0].equals("decode_locked")) {
                keyInfo.setDecode_locked(Integer.parseInt(split[1]));
            } else if (!split[0].equals("default_bitting")) {
                if (split[0].equals("keyangle")) {
                    keyInfo.setKeyangle(split[1]);
                } else if (split[0].equals("keyanglecod")) {
                    keyInfo.setKeyanglecod(split[1]);
                } else if (split[0].equals("last_position")) {
                    keyInfo.setLastPosition(split[1]);
                } else if (split[0].equals("nosenew")) {
                    String[] split3 = split[1].split(",");
                    if (split3.length == 2) {
                        keyInfo.setNoseUp(Integer.parseInt(split3[0]));
                        keyInfo.setNoseDown(Integer.parseInt(split3[1]));
                    }
                } else if (split[0].equals(SpecificParamUtils.SINGLE_SIDE_ANGLE)) {
                    keyInfo.setSpacewidthangles(Integer.parseInt(split[1]));
                }
            }
        }
    }

    public static String getToothCodeRound(String str, List<String> list) {
        if (!str.contains(".")) {
            return str;
        }
        String str2 = str.split("\\.")[0];
        float parseFloat = Float.parseFloat("0." + str.split("\\.")[1]);
        int indexOf = list.indexOf(str2);
        if (parseFloat < 0.5f) {
            return indexOf == -1 ? "?" : list.get(indexOf);
        }
        if (indexOf == -1) {
            return list.get(0);
        }
        if (indexOf == list.size() - 1) {
            return list.get(list.size() - 1);
        }
        return list.get(indexOf + 1);
    }
}
