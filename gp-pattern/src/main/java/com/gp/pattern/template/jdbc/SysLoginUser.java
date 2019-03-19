package com.gp.pattern.template.jdbc;


import java.io.Serializable;

public class SysLoginUser implements Serializable {
    private static final long serialVersionUID = 8972885560961329488L;
    /**
    * 用户名
    */
    private String sluId;
    /**
    * 姓名
    */
    private String sluName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSluId() {
        return sluId;
    }

    public void setSluId(String sluId) {
        this.sluId = sluId;
    }

    public String getSluName() {
        return sluName;
    }

    public void setSluName(String sluName) {
        this.sluName = sluName;
    }
}
