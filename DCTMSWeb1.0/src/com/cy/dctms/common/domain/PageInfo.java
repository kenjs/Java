package com.cy.dctms.common.domain;

import java.io.Serializable;

import com.cy.dctms.common.constants.Constants;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = 7553973735794350843L;

	/**
	 * �ܼ�¼�� Ĭ��Ϊ-1
	 */
	private int totalRecords = -1;
	
	/**
	 * ��ҳ�� 
	 */
	private int totalPages;
	
	/**
	 * ÿҳ��ʾ�ļ�¼��
	 * */
	private int pageSize = Constants.PAGE_SIZE_MAIN; 
	
	/**
	 *  ��ǰ����ҳ�루�ڵڼ�ҳ��
	 * */
	private int curPageNo = 1;
	
	/**
	 * ��ʾ�ڼ�ҳ 
	 */
	private int curPage = 1;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurPageNo() {
		return curPageNo;
	}
	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		
		if(this.totalRecords == 0) {
			this.totalPages = this.totalRecords;
		}else if(this.totalRecords<this.pageSize) {
			this.totalPages = 1;
		}else if(this.totalRecords % this.pageSize == 0) {
			this.totalPages = this.totalRecords / this.pageSize;
		}else {
			this.totalPages = (this.totalRecords / this.pageSize)+1;
		}
	}
	
}
