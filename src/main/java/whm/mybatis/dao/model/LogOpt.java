package whm.mybatis.dao.model;

public class LogOpt {
    private String ologId;

    private Object ologDatetime;

    private String ologOptcode;

    private String ologStocode;

    private String ologBsncode;

    private String ologReslult;

    private String ologInfo;

    private String ologIp;

    private String ologMac;

    public String getOlogId() {
        return ologId;
    }

    public void setOlogId(String ologId) {
        this.ologId = ologId == null ? null : ologId.trim();
    }

    public Object getOlogDatetime() {
        return ologDatetime;
    }

    public void setOlogDatetime(Object ologDatetime) {
        this.ologDatetime = ologDatetime;
    }

    public String getOlogOptcode() {
        return ologOptcode;
    }

    public void setOlogOptcode(String ologOptcode) {
        this.ologOptcode = ologOptcode == null ? null : ologOptcode.trim();
    }

    public String getOlogStocode() {
        return ologStocode;
    }

    public void setOlogStocode(String ologStocode) {
        this.ologStocode = ologStocode == null ? null : ologStocode.trim();
    }

    public String getOlogBsncode() {
        return ologBsncode;
    }

    public void setOlogBsncode(String ologBsncode) {
        this.ologBsncode = ologBsncode == null ? null : ologBsncode.trim();
    }

    public String getOlogReslult() {
        return ologReslult;
    }

    public void setOlogReslult(String ologReslult) {
        this.ologReslult = ologReslult == null ? null : ologReslult.trim();
    }

    public String getOlogInfo() {
        return ologInfo;
    }

    public void setOlogInfo(String ologInfo) {
        this.ologInfo = ologInfo == null ? null : ologInfo.trim();
    }

    public String getOlogIp() {
        return ologIp;
    }

    public void setOlogIp(String ologIp) {
        this.ologIp = ologIp == null ? null : ologIp.trim();
    }

    public String getOlogMac() {
        return ologMac;
    }

    public void setOlogMac(String ologMac) {
        this.ologMac = ologMac == null ? null : ologMac.trim();
    }
}