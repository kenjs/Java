package com.cy.dctms.common.domain;

import java.util.List;

/**
*������Ϣ
* wjl
*/
public class OrderCargoInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String cargoName;  // ��������
    private String cargoType;  // ��������
    private String cargoWeight;  // ���������
    private String cargoCubage;  // ��������
    private String requestCarLength;  // ����Ҫ�󣨳�����
    private String requestCarPlateType;  // ��-ƽ�塢�ߵͰ�
    private String requestCarBarType;  // ����Ҫ�󣨳� ����
    private String freight;  // �����˷Ѽ۸�
    private String requestStartTime;  // Ҫ��װ��ʱ��
    private String requestEndTime;  // Ҫ�󵽻�ʱ��
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
    private String deletedFlag;  // ɾ��״̬
    private String deployUserid;  // �����û�ID
    private String modifyUserid;  // �޸��û�ID
    private String companyId;  // ��ҵID
    private String cargoFlag;  // ��Դ״̬
    private String cargoFlagTime;  // ״̬�޸�ʱ��
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    private List<OrderCargoInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCargoName() {
        return cargoName;
    }
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    public String getCargoType() {
        return cargoType;
    }
    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }
    public String getCargoWeight() {
        return cargoWeight;
    }
    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
    public String getCargoCubage() {
        return cargoCubage;
    }
    public void setCargoCubage(String cargoCubage) {
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
    public String getFreight() {
        return freight;
    }
    public void setFreight(String freight) {
        this.freight = freight;
    }
    public String getRequestStartTime() {
        return requestStartTime;
    }
    public void setRequestStartTime(String requestStartTime) {
        this.requestStartTime = requestStartTime;
    }
    public String getRequestEndTime() {
        return requestEndTime;
    }
    public void setRequestEndTime(String requestEndTime) {
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
    public String getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public String getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(String deployUserid) {
        this.deployUserid = deployUserid;
    }
    public String getModifyUserid() {
        return modifyUserid;
    }
    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCargoFlag() {
        return cargoFlag;
    }
    public void setCargoFlag(String cargoFlag) {
        this.cargoFlag = cargoFlag;
    }
    public String getCargoFlagTime() {
        return cargoFlagTime;
    }
    public void setCargoFlagTime(String cargoFlagTime) {
        this.cargoFlagTime = cargoFlagTime;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public List<OrderCargoInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<OrderCargoInfoDomain> dataList) {    	this.dataList = dataList;    }}