package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

/**
 * 类CityDistance.java的实现描述：城区距离
 * 
 * @author donghui.wang 2013-11-5 下午01:32:24
 */
public class CityDistance {

    private String citydistanceid;
    private String frompartyid;
    private String partyid;
    private String topartyid;
    private String fromaddress;
    private String toaddress;
    private String distance;
    private String inputdate;
    private String inputman;
    private String updatedate;
    private String updateman;
    private String rownum;
    private String organization;

    public String getCitydistanceid() {
        if (citydistanceid != null) {
            if (new SysUtil().isInt(citydistanceid) == false) citydistanceid = null;
        }
        return citydistanceid;
    }

    public void setCitydistanceid(String citydistanceid) {
        if (citydistanceid != null) {
            if (new SysUtil().isInt(citydistanceid) == false) citydistanceid = null;
        }
        this.citydistanceid = citydistanceid;
    }

    public String getFrompartyid() {
        if (frompartyid != null) {
            if (new SysUtil().isInt(frompartyid) == false) frompartyid = null;
        }
        return frompartyid;
    }

    public void setFrompartyid(String frompartyid) {
        if (frompartyid != null) {
            if (new SysUtil().isInt(frompartyid) == false) frompartyid = null;
        }
        this.frompartyid = frompartyid;
    }

    public String getPartyid() {
        if (partyid != null) {
            if (new SysUtil().isInt(partyid) == false) partyid = null;
        }
        return partyid;
    }

    public void setPartyid(String partyid) {
        if (partyid != null) {
            if (new SysUtil().isInt(partyid) == false) partyid = null;
        }
        this.partyid = partyid;
    }

    public String getTopartyid() {
        if (topartyid != null) {
            if (new SysUtil().isInt(topartyid) == false) topartyid = null;
        }
        return topartyid;
    }

    public void setTopartyid(String topartyid) {
        if (topartyid != null) {
            if (new SysUtil().isInt(topartyid) == false) topartyid = null;
        }
        this.topartyid = topartyid;
    }

    public String getFromaddress() {
        if (fromaddress != null) {
            if ("".equals(fromaddress)) fromaddress = null;
        }
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        if (fromaddress != null) {
            if ("".equals(fromaddress)) fromaddress = null;
        }
        this.fromaddress = fromaddress;
    }

    public String getToaddress() {
        if (toaddress != null) {
            if ("".equals(toaddress)) toaddress = null;
        }
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        if (toaddress != null) {
            if ("".equals(toaddress)) toaddress = null;
        }
        this.toaddress = toaddress;
    }

    public String getDistance() {
        if (distance != null) {
            if ("".equals(distance)) distance = null;
        }
        return distance;
    }

    public void setDistance(String distance) {
        if (distance != null) {
            if ("".equals(distance)) distance = null;
        }
        this.distance = distance;
    }

    public String getInputdate() {
        if (inputdate != null) {
            if ("".equals(inputdate)) inputdate = null;
        }
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        if (inputdate != null) {
            if ("".equals(inputdate)) inputdate = null;
        }
        this.inputdate = inputdate;
    }

    public String getInputman() {
        if (inputman != null) {
            if ("".equals(inputman)) inputman = null;
        }
        return inputman;
    }

    public void setInputman(String inputman) {
        if (inputman != null) {
            if ("".equals(inputman)) inputman = null;
        }
        this.inputman = inputman;
    }

    public String getUpdatedate() {
        if (updatedate != null) {
            if ("".equals(updatedate)) updatedate = null;
        }
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        if (updatedate != null) {
            if ("".equals(updatedate)) updatedate = null;
        }
        this.updatedate = updatedate;
    }

    public String getUpdateman() {
        if (updateman != null) {
            if ("".equals(updateman)) updateman = null;
        }
        return updateman;
    }

    public void setUpdateman(String updateman) {
        if (updateman != null) {
            if ("".equals(updateman)) updateman = null;
        }
        this.updateman = updateman;
    }

    public String getRownum() {
        if (rownum != null) {
            if ("".equals(rownum)) rownum = null;
        }
        return rownum;
    }

    public void setRownum(String rownum) {
        if (rownum != null) {
            if ("".equals(rownum)) rownum = null;
        }
        this.rownum = rownum;
    }

    public String getOrganization() {
        if (organization != null) {
            if ("".equals(organization)) organization = null;
        }
        return organization;
    }

    public void setOrganization(String organization) {
        if (organization != null) {
            if ("".equals(organization)) organization = null;
        }
        this.organization = organization;
    }

}
