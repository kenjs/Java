package com.cy.dctms.common.bo;

import java.util.Date;
public class OrderCargoInfo {
    private Long id;  // ��������������ʼֵ1��
    private String cargoName;  // ��������
    private Long cargoType;  // ��������
    private Double cargoWeight;  // ���������
    private Double cargoCubage;  // ��������
    private String requestCarLength;  // ����Ҫ�󣨳�����
    private String requestCarPlateType;  // ��-ƽ�塢�ߵͰ�
    private String requestCarBarType;  // ����Ҫ�󣨳� ����
    private Double freight;  // �����˷Ѽ۸�
    private Date requestStartTime;  // Ҫ��װ��ʱ��
    private Date requestEndTime;  // Ҫ�󵽻�ʱ��
    private String startProvince;  // װ����-ʡ
    private String startCity;  // װ����-��
    private String startCounty;  // װ����-��
    private String startTown;  // װ����-�Զ����ַ
    private String endProvince;  // ж����-ʡ
    private String endCity;  // ж����-��
    private String endCounty;  // ж����-��
    private String endTown;  // ж����-�Զ����ַ
    private String contactName;  // ��ϵ��
    private String contactMobilephone;  // �ֻ���
    private String contactTelephone;  // �̶��绰
    private String remark;  // ��ע
    private Long deletedFlag;  // ɾ��״̬
    private Long deployUserid;  // �����û�ID
    private Long modifyUserid;  // �޸��û�ID
    private Long companyId;  // ��ҵID
    private Long cargoFlag;  // ��Դ״̬
    private Date cargoFlagTime;  // ״̬�޸�ʱ��
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCargoName() {
        return cargoName;
    }
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    public Long getCargoType() {
        return cargoType;
    }
    public void setCargoType(Long cargoType) {
        this.cargoType = cargoType;
    }
    public Double getCargoWeight() {
        return cargoWeight;
    }
    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
    public Double getCargoCubage() {
        return cargoCubage;
    }
    public void setCargoCubage(Double cargoCubage) {
        this.cargoCubage = cargoCubage;
    }
    public String getRequestCarLength() {
        return requestCarLength;
    }
    public void setRequestCarLength(String requestCarLength) {
        this.requestCarLength = requestCarLength;
    }
    public String getRequestCarPlateType() {
        return requestCarPlateType;
    }
    public void setRequestCarPlateType(String requestCarPlateType) {
        this.requestCarPlateType = requestCarPlateType;
    }
    public String getRequestCarBarType() {
        return requestCarBarType;
    }
    public void setRequestCarBarType(String requestCarBarType) {
        this.requestCarBarType = requestCarBarType;
    }
    public Double getFreight() {
        return freight;
    }
    public void setFreight(Double freight) {
        this.freight = freight;
    }
    public Date getRequestStartTime() {
        return requestStartTime;
    }
    public void setRequestStartTime(Date requestStartTime) {
        this.requestStartTime = requestStartTime;
    }
    public Date getRequestEndTime() {
        return requestEndTime;
    }
    public void setRequestEndTime(Date requestEndTime) {
        this.requestEndTime = requestEndTime;
    }
    public String getStartProvince() {
        return startProvince;
    }
    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }
    public String getStartCity() {
        return startCity;
    }
    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }
    public String getStartCounty() {
        return startCounty;
    }
    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }
    public String getStartTown() {
        return startTown;
    }
    public void setStartTown(String startTown) {
        this.startTown = startTown;
    }
    public String getEndProvince() {
        return endProvince;
    }
    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }
    public String getEndCity() {
        return endCity;
    }
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }
    public String getEndCounty() {
        return endCounty;
    }
    public void setEndCounty(String endCounty) {
        this.endCounty = endCounty;
    }
    public String getEndTown() {
        return endTown;
    }
    public void setEndTown(String endTown) {
        this.endTown = endTown;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactMobilephone() {
        return contactMobilephone;
    }
    public void setContactMobilephone(String contactMobilephone) {
        this.contactMobilephone = contactMobilephone;
    }
    public String getContactTelephone() {
        return contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Long deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public Long getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(Long deployUserid) {
        this.deployUserid = deployUserid;
    }
    public Long getModifyUserid() {
        return modifyUserid;
    }
    public void setModifyUserid(Long modifyUserid) {
        this.modifyUserid = modifyUserid;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getCargoFlag() {
        return cargoFlag;
    }
    public void setCargoFlag(Long cargoFlag) {
        this.cargoFlag = cargoFlag;
    }
    public Date getCargoFlagTime() {
        return cargoFlagTime;
    }
    public void setCargoFlagTime(Date cargoFlagTime) {
        this.cargoFlagTime = cargoFlagTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}