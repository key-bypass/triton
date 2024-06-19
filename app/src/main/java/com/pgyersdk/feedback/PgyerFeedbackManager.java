package com.pgyersdk.feedback;

import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.p016f.C2047l;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PgyerFeedbackManager {

    /* renamed from: a */
    private static PgyerFeedbackManager f546a;

    /* renamed from: b */
    private C2077k f547b;

    /* loaded from: classes2.dex */
    public static class PgyerFeedbackBuilder {
        private int shakingThreshold = 950;
        private TYPE displayType = TYPE.DIALOG_TYPE;
        private HashMap<String, String> moreParam = new HashMap<>();
        private boolean isShakeInvoke = true;

        public PgyerFeedbackManager builder() {
            PgyerFeedbackManager unused = PgyerFeedbackManager.f546a = new PgyerFeedbackManager(this.shakingThreshold, this.displayType, this.moreParam, this.isShakeInvoke);
            return PgyerFeedbackManager.f546a;
        }

        public PgyerFeedbackBuilder setBarBackgroundColor(String str) {
            if (C2047l.m239b(str)) {
                FeedbackActivity.setBarBackgroundColor(str);
            }
            return this;
        }

        public PgyerFeedbackBuilder setBarButtonPressedColor(String str) {
            if (C2047l.m239b(str)) {
                FeedbackActivity.setBarButtonPressedColor(str);
            }
            return this;
        }

        public PgyerFeedbackBuilder setBarImmersive(boolean z) {
            FeedbackActivity.setBarImmersive(true);
            return this;
        }

        public PgyerFeedbackBuilder setColorDialogTitle(String str) {
            if (C2047l.m239b(str)) {
                AlertDialogBuilderC2062m.m276a(str);
            }
            return this;
        }

        public PgyerFeedbackBuilder setColorPickerBackgroundColor(String str) {
            if (C2047l.m239b(str)) {
                FeedbackActivity.setColorPickerBackgroundColor(str);
            }
            return this;
        }

        public PgyerFeedbackBuilder setColorTitleBg(String str) {
            if (C2047l.m239b(str)) {
                AlertDialogBuilderC2062m.m280b(str);
            }
            return this;
        }

        public PgyerFeedbackBuilder setDisplayType(TYPE type) {
            this.displayType = type;
            return this;
        }

        public PgyerFeedbackBuilder setMoreParam(String str, String str2) {
            this.moreParam.put(str, str2);
            return this;
        }

        public PgyerFeedbackBuilder setShakeInvoke(boolean z) {
            this.isShakeInvoke = z;
            return this;
        }

        public PgyerFeedbackBuilder setShakingThreshold(int i) {
            this.shakingThreshold = i;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public enum TYPE {
        ACTIVITY_TYPE,
        DIALOG_TYPE
    }

    /* synthetic */ PgyerFeedbackManager(int i, TYPE type, Map map, boolean z, C2071e c2071e) {
        this(i, type, map, z);
    }

    public static PgyerFeedbackManager getInstance() {
        PgyerFeedbackManager pgyerFeedbackManager = f546a;
        if (pgyerFeedbackManager != null) {
            return pgyerFeedbackManager;
        }
        throw new IllegalArgumentException("Please getInstance() of PgyerFeedbackManager before use this builder ");
    }

    /* renamed from: b */
    public C2077k m257b() {
        return this.f547b;
    }

    public void invoke() {
        if (C2047l.m236a()) {
            this.f547b.m352e();
        }
    }

    public void register() {
        if (C2047l.m236a()) {
            this.f547b.m353f();
        }
    }

    private PgyerFeedbackManager(int i, TYPE type, Map<String, String> map, boolean z) {
        if (z) {
            this.f547b = new C2077k(i, type, new JSONObject(map).toString());
        } else {
            this.f547b = new C2077k(type, new JSONObject(map).toString());
        }
    }
}
