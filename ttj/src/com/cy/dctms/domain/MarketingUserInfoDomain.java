package com.cy.dctms.domain;

import java.util.List;

/**
 * Created by nixianjing on 14/12/30.
 */
public class MarketingUserInfoDomain extends BaseDomain {


    private Integer id;//主键id
    private String code;//登录名称
    private String password;//登录密码
    private String name;//姓名
    private Integer sex;//性别
    private String phoneNumber;//手机号码
    private String contactNumber;//联系电话
    private String address;//地址
    private Integer age;//年龄
    private String createTime;//创建时间
    private String modifyTime;//修改时间
    private Integer deleteFlag;//删除状态

    private Integer position;//职务 1 经理 2 主管 3 组长 4 专员
    private Integer joinGroup;//所属组： 0 未分组1 营销一组 2 营销二组 3 营销三组


    private List<MarketingUserInfoDomain> list;

    public List<MarketingUserInfoDomain> getList() {
        return list;
    }

    public void setList(List<MarketingUserInfoDomain> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getJoinGroup() {
        return joinGroup;
    }

    public void setJoinGroup(Integer joinGroup) {
        this.joinGroup = joinGroup;
    }
}
