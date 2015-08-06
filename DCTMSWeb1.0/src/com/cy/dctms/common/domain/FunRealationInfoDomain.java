package com.cy.dctms.common.domain;

import java.util.List;

/**
*赋权信息
* wjl
*/
public class FunRealationInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ID
    private String managerId;
    private String functionId;
    private String number;  // 编号
    private String sunCount;
    private String name;  // 功能名字
    private String parentName;  // 父类名称
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    private String deleteFlag;  // 修改标志
    private List<String> funIdList;//功能ID列表
    private List<FunRealationInfoDomain> dataList;
    public String getFunctionId() {
		return functionId;
	}
	public List<String> getFunIdList() {
		return funIdList;
	}
	public void setFunIdList(List<String> funIdList) {
		this.funIdList = funIdList;
	}
	public String getSunCount() {
		return sunCount;
	}
	public void setSunCount(String sunCount) {
		this.sunCount = sunCount;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public List<FunRealationInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<FunRealationInfoDomain> dataList) {    	this.dataList = dataList;    }}