package com.kkkcut.e20j.bean.gsonBean;

import java.util.List;

/* loaded from: classes.dex */
public class UpdateLog {
    private UpdateLogBean updateLog;

    public UpdateLogBean getUpdateLog() {
        return this.updateLog;
    }

    public void setUpdateLog(UpdateLogBean updateLogBean) {
        this.updateLog = updateLogBean;
    }

    /* loaded from: classes.dex */
    public static class UpdateLogBean {
        private List<String> e9;
        private List<String> e9z;

        public List<String> getE9() {
            return this.e9;
        }

        public void setE9(List<String> list) {
            this.e9 = list;
        }

        public List<String> getE9z() {
            return this.e9z;
        }

        public void setE9z(List<String> list) {
            this.e9z = list;
        }
    }
}
