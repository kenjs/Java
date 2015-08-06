package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_RY_JS is created by tools.
 * @author HJH
 */

public class QyRyJs  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	private String jsDjxh;                           // ��ɫ�Ǽ����(SEQ_JS_DJXH)

	public QyRyJs() {
	}

	//��ȡ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ��ɫ�Ǽ����(SEQ_JS_DJXH)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//���ý�ɫ�Ǽ����(SEQ_JS_DJXH)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}
}