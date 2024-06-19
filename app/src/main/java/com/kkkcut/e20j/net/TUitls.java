package com.kkkcut.e20j.net;

import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.utils.DesUtil;

/* loaded from: classes.dex */
public class TUitls {
    public static String getconfig(String str) {
        return DesUtil.encrypt("Type=9&Model=" + str, DesUtil.SERVER);
    }

    public static String register(String str, String str2, String str3) {
        return DesUtil.encrypt("Type=2&DID=" + str + "&TT=2&SN=" + str2 + "&LI=" + str3, DesUtil.SERVER);
    }

    public static String uploadTestData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        return DesUtil.encrypt("Type=test&D1=" + str + "&D2=" + str2 + "&D3=" + str3 + "&D4=" + str4 + "&D5=" + str5 + "&D6=" + str6 + "&D7=" + str7 + "&D8=" + str8 + "&D9=" + str9 + "&D10=" + str10 + "&D11=" + str11 + "&D12=" + str12, DesUtil.SERVER);
    }

    public static String getTestData(String str) {
        return DesUtil.encrypt("Type=testlist&SN=" + str, DesUtil.SERVER);
    }

    public static String lackToothParam(String str, int i, String str2, String str3, String str4) {
        return DesUtil.encrypt("Type=388&PhoneType=1&PhoneID=" + str2 + "&TT=&SN=" + str3 + "&InChinese=1&ISN=" + str4 + "&Bitting=" + str + "&DataID=" + i, DesUtil.SERVER);
    }

    public static String certification(String str, String str2, String str3, String str4) {
        return DesUtil.encrypt("Type=40&PhoneType=1&PhoneID=" + str + "&UT=DAND&TT=&SN=" + str2 + "&RC=" + str3 + "&IP=" + str4 + "&LOT=20.3789&LAT=45.126", DesUtil.SERVER);
    }

    public static String uploadCustomkey(String str, String str2, CustomKey customKey) {
        return DesUtil.encrypt("Type=900&D1=" + str + "&D2=" + str2 + "&D3=" + customKey.getKeyname() + "&D4=&D5=0&D6=0&D7=" + customKey.getIcCard() + "&D8=" + customKey.getType() + "&D9=" + customKey.getAlign() + "&D10=" + customKey.getWidth() + "&D11=" + customKey.getThick() + "&D12=" + customKey.getLength() + "&D13=" + customKey.getRow_count() + "&D14=" + customKey.getFace() + "&D15=" + customKey.getRow_pos() + "&D16=" + customKey.getSpace() + "&D17=" + customKey.getSpace_width() + "&D18=" + customKey.getDepth() + "&D19=" + customKey.getDepth_name() + "&D20=" + customKey.getParameter_info(), DesUtil.SERVER);
    }

    public static String checkResUpdate(String str, long j, String str2, String str3, String str4, String str5, String str6, String str7) {
        return DesUtil.encrypt("Type=100&PhoneType=3&PhoneID=" + str + "&TT=" + j + "&SN=" + str2 + "&AppVer=" + str3 + "&DbVer=" + str4 + "&ResVer=" + str5 + "&ErrCodeVer=" + str6 + "&ImgVer=" + str7 + "&In1=&In2=&In3=&In4=&In5=&In6=&In7=&In8=&In9=&In10=", DesUtil.SERVER);
    }

    public static String codeFindToothParam(String str, int i, String str2, String str3, String str4) {
        return DesUtil.encrypt("Type=37&PhoneType=1&PhoneID=" + str2 + "&TT=&SN=" + str3 + "&Code=" + str + "&DataID=" + i + "&ISN=" + str4, DesUtil.SERVER);
    }
}
