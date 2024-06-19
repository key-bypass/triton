package com.kkkcut.e20j.bean.gsonBean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class Configuration implements Serializable {
    private static final long serialVersionUID = -3034634065444641954L;
    private List<BottomLayoutBean> bottom_layout;
    private List<CenterLayoutBean> center_layout;
    private String company;
    private String database;
    private String id;
    private String language;
    private TitleLayoutBean title_layout;
    private String welcome_logo;

    public String getWelcome_logo() {
        return this.welcome_logo;
    }

    public void setWelcome_logo(String str) {
        this.welcome_logo = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public TitleLayoutBean getTitle_layout() {
        return this.title_layout;
    }

    public void setTitle_layout(TitleLayoutBean titleLayoutBean) {
        this.title_layout = titleLayoutBean;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String str) {
        this.database = str;
    }

    public List<CenterLayoutBean> getCenter_layout() {
        return this.center_layout;
    }

    public void setCenter_layout(List<CenterLayoutBean> list) {
        this.center_layout = list;
    }

    public List<BottomLayoutBean> getBottom_layout() {
        return this.bottom_layout;
    }

    public void setBottom_layout(List<BottomLayoutBean> list) {
        this.bottom_layout = list;
    }

    /* loaded from: classes.dex */
    public static class TitleLayoutBean implements Serializable {
        private static final long serialVersionUID = -5449256177759604297L;
        private LogoBean logo;
        private ModelBean model;

        public ModelBean getModel() {
            return this.model;
        }

        public void setModel(ModelBean modelBean) {
            this.model = modelBean;
        }

        public LogoBean getLogo() {
            return this.logo;
        }

        public void setLogo(LogoBean logoBean) {
            this.logo = logoBean;
        }

        /* loaded from: classes.dex */
        public static class ModelBean implements Serializable {
            private static final long serialVersionUID = 7418951652829144796L;
            private String margin_left;
            private String name;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getMargin_left() {
                return this.margin_left;
            }

            public void setMargin_left(String str) {
                this.margin_left = str;
            }
        }

        /* loaded from: classes.dex */
        public static class LogoBean implements Serializable {
            private static final long serialVersionUID = -5588671172308743635L;
            private String img;
            private String margin_left;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }

            public String getMargin_left() {
                return this.margin_left;
            }

            public void setMargin_left(String str) {
                this.margin_left = str;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class CenterLayoutBean implements Serializable {
        private static final long serialVersionUID = 3705273936116423444L;
        private List<KeytypeBean> keytype;

        public List<KeytypeBean> getKeytype() {
            return this.keytype;
        }

        public void setKeytype(List<KeytypeBean> list) {
            this.keytype = list;
        }

        /* loaded from: classes.dex */
        public static class KeytypeBean implements Serializable {
            private static final long serialVersionUID = -4233654561557790374L;
            private String img;
            private String name;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class BottomLayoutBean implements Serializable {
        private static final long serialVersionUID = -8630945884428885927L;
        private String img;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }
    }
}
