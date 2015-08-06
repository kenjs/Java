package com.cy.driver.bo;

import java.io.Serializable;
/**
 * app分享绑定
 * @author       Haoyong
 * @since        2014年12月3日 下午5:17:02
 * @description
 */
public class AppShareInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -211096505742119506L;
	
	private int id;							//主键
	private String recommendedMobileNum;	//被推荐人手机号码（输入框填写的）
	private String recommendMobileNum;		//推荐人手机号码（分享链接带过来的）
	private String createTime;				//创建时间
	public AppShareInfo() {
		super();
	}
	public AppShareInfo(String recommendedMobileNum, String recommendMobileNum) {
		super();
		this.recommendedMobileNum = recommendedMobileNum;
		this.recommendMobileNum = recommendMobileNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecommendedMobileNum() {
		return recommendedMobileNum;
	}
	public void setRecommendedMobileNum(String recommendedMobileNum) {
		this.recommendedMobileNum = recommendedMobileNum;
	}
	public String getRecommendMobileNum() {
		return recommendMobileNum;
	}
	public void setRecommendMobileNum(String recommendMobileNum) {
		this.recommendMobileNum = recommendMobileNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
