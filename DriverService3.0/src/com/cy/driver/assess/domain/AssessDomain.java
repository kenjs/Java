package com.cy.driver.assess.domain;

import com.cy.common.domain.BaseDomain;

public class AssessDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276991409172718373L;
	
	private String score;
	private String level;
	public AssessDomain() {
		super();
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	

}
