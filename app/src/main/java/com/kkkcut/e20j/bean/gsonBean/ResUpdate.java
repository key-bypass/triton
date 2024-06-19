package com.kkkcut.e20j.bean.gsonBean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class ResUpdate {
    private String AndroidAPPVer;
    private List<DbUpdatelistBean> DbUpdatelist;
    private String IOSAPPVer;
    private KeyImgUpdateListBean KeyImgUpdateList;
    private String UpdateNO;
    private String UpgradeLog;

    public String getUpdateNO() {
        return this.UpdateNO;
    }

    public void setUpdateNO(String str) {
        this.UpdateNO = str;
    }

    public String getAndroidAPPVer() {
        return this.AndroidAPPVer;
    }

    public void setAndroidAPPVer(String str) {
        this.AndroidAPPVer = str;
    }

    public String getIOSAPPVer() {
        return this.IOSAPPVer;
    }

    public void setIOSAPPVer(String str) {
        this.IOSAPPVer = str;
    }

    public String getUpgradeLog() {
        return this.UpgradeLog;
    }

    public void setUpgradeLog(String str) {
        this.UpgradeLog = str;
    }

    public KeyImgUpdateListBean getKeyImgUpdateList() {
        return this.KeyImgUpdateList;
    }

    public void setKeyImgUpdateList(KeyImgUpdateListBean keyImgUpdateListBean) {
        this.KeyImgUpdateList = keyImgUpdateListBean;
    }

    public List<DbUpdatelistBean> getDbUpdatelist() {
        return this.DbUpdatelist;
    }

    public void setDbUpdatelist(List<DbUpdatelistBean> list) {
        this.DbUpdatelist = list;
    }

    /* loaded from: classes.dex */
    public static class KeyImgUpdateListBean {
        private List<ImgHashListBean> ImgHashList;
        private String Ver;

        @SerializedName("URL")
        private String url;

        public String getVer() {
            return this.Ver;
        }

        public void setVer(String str) {
            this.Ver = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public List<ImgHashListBean> getImgHashList() {
            return this.ImgHashList;
        }

        public void setImgHashList(List<ImgHashListBean> list) {
            this.ImgHashList = list;
        }

        /* loaded from: classes.dex */
        public static class ImgHashListBean {
            private String KeyHash;
            private String KeyID;
            private String KeyImg;

            public String getKeyID() {
                return this.KeyID;
            }

            public void setKeyID(String str) {
                this.KeyID = str;
            }

            public String getKeyImg() {
                return this.KeyImg;
            }

            public void setKeyImg(String str) {
                this.KeyImg = str;
            }

            public String getKeyHash() {
                return this.KeyHash;
            }

            public void setKeyHash(String str) {
                this.KeyHash = str;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class DbUpdatelistBean {
        private String DBHash;
        private String DbName;
        private String DbVer;

        @SerializedName("URL")
        private String url;

        public String getDbName() {
            return this.DbName;
        }

        public void setDbName(String str) {
            this.DbName = str;
        }

        public String getDbVer() {
            return this.DbVer;
        }

        public void setDbVer(String str) {
            this.DbVer = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getDBHash() {
            return this.DBHash;
        }

        public void setDBHash(String str) {
            this.DBHash = str;
        }
    }
}
