package com.pgyersdk.p012c;

import androidx.core.view.InputDeviceCompat;
import com.pgyersdk.crash.AbstractC2032g;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: Strings.java */
/* renamed from: com.pgyersdk.c.b */
/* loaded from: classes2.dex */
public class C2023b {

    /* renamed from: a */
    private static final Map<Integer, String> f479a;

    static {
        HashMap hashMap = new HashMap();
        f479a = hashMap;
        boolean equals = "zh".equals(Locale.getDefault().getLanguage());
        Integer valueOf = Integer.valueOf(InputDeviceCompat.SOURCE_DPAD);
        if (equals) {
            hashMap.put(256, "下载失败");
            hashMap.put(257, "下载文件失败，请重试?");
            hashMap.put(258, "取消");
            hashMap.put(259, "重试");
            hashMap.put(260, "正在下载...");
            hashMap.put(valueOf, "更新提醒");
            hashMap.put(514, "无更新说明");
            hashMap.put(515, "取消");
            hashMap.put(516, "下载");
            hashMap.put(517, "请求检查更新失败");
            hashMap.put(518, "目前无网络");
            hashMap.put(1044, "请输入您的反馈...");
            hashMap.put(1045, "请输入您的邮箱（必填）");
            hashMap.put(1063, "请输入您的邮箱");
            hashMap.put(1046, "你输入的邮箱格式不正确");
            hashMap.put(1048, "发送");
            hashMap.put(1049, "取消");
            hashMap.put(1056, "反馈内容将会保留，下次可以继续发送");
            hashMap.put(1057, "你需要添加android.permission.INTERNET权限");
            hashMap.put(1077, "你需要添加android.permission.WRITE_EXTERNAL_STORAGE权限");
            hashMap.put(1058, "谢谢您的反馈");
            hashMap.put(1059, "发送失败，请稍后重试");
            hashMap.put(1060, "不支持摇一摇");
            hashMap.put(1061, "正在发送反馈...");
            hashMap.put(1062, "反馈");
            hashMap.put(1064, "上传屏幕截图");
            hashMap.put(1065, "设备详情：");
            hashMap.put(1072, "按住录音");
            hashMap.put(1073, "松开结束");
            hashMap.put(1074, "你需要添加android.permission.RECORD_AUDIO权限");
            hashMap.put(1075, "录音时间太短");
            hashMap.put(1076, "最大时长两分钟");
            return;
        }
        hashMap.put(256, "Download failed");
        hashMap.put(257, "The update could not be downloaded. Would you like to try again?");
        hashMap.put(258, "Cancel");
        hashMap.put(259, "Retry");
        hashMap.put(260, "Loading...");
        hashMap.put(valueOf, "Update Available");
        hashMap.put(514, "There is no update note");
        hashMap.put(515, "Cancel");
        hashMap.put(516, "Download");
        hashMap.put(517, "check update failed");
        hashMap.put(518, "new work unavailable");
        hashMap.put(1044, "Please enter a feedback text...");
        hashMap.put(1045, "Please enter an email address(Required)");
        hashMap.put(1063, "Please enter an email address");
        hashMap.put(1046, "Please enter a valid email");
        hashMap.put(1048, "Send");
        hashMap.put(1049, "Cancel");
        hashMap.put(1056, "Feedback content will be retained, the next time you can continue to send");
        hashMap.put(1057, "You need to add permission of android.permission.INTERNET");
        hashMap.put(1058, "Feedback successfully");
        hashMap.put(1059, "Send failed, please try again later");
        hashMap.put(1060, "Shake is not supported");
        hashMap.put(1061, "Sending...");
        hashMap.put(1062, "Feedback");
        hashMap.put(1064, "Take screenshot");
        hashMap.put(1065, "Device details: ");
        hashMap.put(1072, "Hold to talk");
        hashMap.put(1073, "Release to stop");
        hashMap.put(1074, "You need to add permission of android.permission.RECORD_AUDIO");
        hashMap.put(1075, "Recording time is too short");
        hashMap.put(1076, "2 minutes at most");
    }

    /* renamed from: a */
    public static String m151a(int i) {
        return m152a(null, i);
    }

    /* renamed from: a */
    public static String m152a(AbstractC2032g abstractC2032g, int i) {
        String m164a = abstractC2032g != null ? abstractC2032g.m164a(i) : null;
        return m164a == null ? f479a.get(Integer.valueOf(i)) : m164a;
    }
}
