package com.cy.swp.bo;

import java.util.Date;

/**
 * 企业客户资料表
 * Created by nixianjing on 14/12/11.
 */
public class MarketingCompanyInfo extends BaseBo {


    private Integer id;//主键
    private String contactMobiphone;//联系手机
    private Integer companyId;//公司id
    private String name;//公司名称
    private String province;//省
    private String city;//市
    private String county;//县
    private String address;//详细地址
    private String contactName;//联系人
    private String contactTelephone;//固定电话
    private Integer allocateStatus;//分配状态 0 待分配 1 分配中 2 已分配
    private Integer assisterId;//营销专员id(当分配状态为1或2时，此字段不能为空)
    private Integer distributerId;//分配者id(指有权限进行客户分配的营销人员id)
    private Date distributTime;//分配时间
    private Date createTime;//创建时间
    private Date lastModifyTime;//最后修改时间
    private Integer deleteFlag;//0 未删除 1 已删除


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactMobiphone() {
        return contactMobiphone;
    }

    public void setContactMobiphone(String contactMobiphone) {
        this.contactMobiphone = contactMobiphone;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public Integer getAllocateStatus() {
        return allocateStatus;
    }

    public void setAllocateStatus(Integer allocateStatus) {
        this.allocateStatus = allocateStatus;
    }

    public Integer getAssisterId() {
        return assisterId;
    }

    public void setAssisterId(Integer assisterId) {
        this.assisterId = assisterId;
    }

    public Integer getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(Integer distributerId) {
        this.distributerId = distributerId;
    }

    public Date getDistributTime() {
        return distributTime;
    }

    public void setDistributTime(Date distributTime) {
        this.distributTime = distributTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
