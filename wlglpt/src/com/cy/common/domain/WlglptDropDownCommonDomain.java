package com.cy.common.domain;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.zygl.domain.QyKhDjxxDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

public class WlglptDropDownCommonDomain extends BaseBusinessDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QyKhDjxxDomain khDomain;
	private QyYlClxxDomain clxxDomain;
	private QyZrbmThShdzDomain thShdzDomain;
	private String khDjxh;                           // 
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String khmc;                             // 名称
	private String khjc;                             // 简称
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称
	private String xzqhDm;                           // 行政区划代码
	private String xzqhMc;                           // 行政区划
                      
	private String khlxDm;							 //客户类型代码
	private String ykjsfsDm;                         //余款结算方式代码
	private String khlxMc;                            //客户类型名称
	private String ykjsfsMc;						//余款结算方式名称

	private String fhrMc;
	private String fhrDjxh;
	
	private String hwDjxh;                           // 
	private String hwmc;                             // 名称
	private String hwjc;                             // 简称
	private String cdJldwDm;                         // 
	private String bzJldwDm;                         // 地址
	private Double bzJsHsbl;                         // 
	private Double bzCbHsbl;                         // 
	private String jsJldwDm;                         // 地址
	private String cbJldwDm;                         // 地址
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String slJldwDm;						//数量
	private String zlJldwDm;  						//重量		
	private String tjJldwDm;                        //体积
	private String sl;
	private String zl;
	private String tj;
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String zhdzDjxh;
	private String fhrDz;
	private String fhrLxr;
	private String fhrLxdh;
	private String xsdz;
	
	private String shdzDjxh;
	private String shrDz;
	private String shrLxr;
	private String shrLxdh;
	private String hwxhDjxh;
	
	private String shrMc;
	private String khlxDm4js;
	private String ykjsfsDm4js;
	
	private String xzqhInputSelHtml;
	
	public WlglptDropDownCommonDomain() {
		super();
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public QyKhDjxxDomain getKhDomain() {
		if (khDomain == null) {
			khDomain = new QyKhDjxxDomain();
		}
		return khDomain;
	}

	public void setKhDomain(QyKhDjxxDomain khDomain) {
		this.khDomain = khDomain;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKhjc() {
		return khjc;
	}

	public void setKhjc(String khjc) {
		this.khjc = khjc;
	}

	public String getPyqc() {
		return pyqc;
	}

	public void setPyqc(String pyqc) {
		this.pyqc = pyqc;
	}

	public String getPyjc() {
		return pyjc;
	}

	public void setPyjc(String pyjc) {
		this.pyjc = pyjc;
	}

	public String getXzqhDm() {
		return xzqhDm;
	}

	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm = xzqhDm;
	}

	public String getXzqhMc() {
		return xzqhMc;
	}

	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc = xzqhMc;
	}

	public String getKhlxDm() {
		return khlxDm;
	}

	public void setKhlxDm(String khlxDm) {
		this.khlxDm = khlxDm;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public String getKhlxMc() {
		return khlxMc;
	}

	public void setKhlxMc(String khlxMc) {
		this.khlxMc = khlxMc;
	}

	public String getYkjsfsMc() {
		return ykjsfsMc;
	}

	public void setYkjsfsMc(String ykjsfsMc) {
		this.ykjsfsMc = ykjsfsMc;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getHwDjxh() {
		return hwDjxh;
	}

	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh = hwDjxh;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getHwjc() {
		return hwjc;
	}

	public void setHwjc(String hwjc) {
		this.hwjc = hwjc;
	}

	public String getCdJldwDm() {
		return cdJldwDm;
	}

	public void setCdJldwDm(String cdJldwDm) {
		this.cdJldwDm = cdJldwDm;
	}

	public String getBzJldwDm() {
		return bzJldwDm;
	}

	public void setBzJldwDm(String bzJldwDm) {
		this.bzJldwDm = bzJldwDm;
	}

	public Double getBzJsHsbl() {
		return bzJsHsbl;
	}

	public void setBzJsHsbl(Double bzJsHsbl) {
		this.bzJsHsbl = bzJsHsbl;
	}

	public Double getBzCbHsbl() {
		return bzCbHsbl;
	}

	public void setBzCbHsbl(Double bzCbHsbl) {
		this.bzCbHsbl = bzCbHsbl;
	}

	public String getJsJldwDm() {
		return jsJldwDm;
	}

	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm = jsJldwDm;
	}

	public String getCbJldwDm() {
		return cbJldwDm;
	}

	public void setCbJldwDm(String cbJldwDm) {
		this.cbJldwDm = cbJldwDm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getSlJldwDm() {
		return slJldwDm;
	}

	public void setSlJldwDm(String slJldwDm) {
		this.slJldwDm = slJldwDm;
	}

	public String getZlJldwDm() {
		return zlJldwDm;
	}

	public void setZlJldwDm(String zlJldwDm) {
		this.zlJldwDm = zlJldwDm;
	}

	public String getTjJldwDm() {
		return tjJldwDm;
	}

	public void setTjJldwDm(String tjJldwDm) {
		this.tjJldwDm = tjJldwDm;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getZhdzDjxh() {
		return zhdzDjxh;
	}

	public void setZhdzDjxh(String zhdzDjxh) {
		this.zhdzDjxh = zhdzDjxh;
	}

	public String getFhrDz() {
		return fhrDz;
	}

	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getFhrLxdh() {
		return fhrLxdh;
	}

	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh = fhrLxdh;
	}

	public String getXsdz() {
		return xsdz;
	}

	public void setXsdz(String xsdz) {
		this.xsdz = xsdz;
	}

	public String getShdzDjxh() {
		return shdzDjxh;
	}

	public void setShdzDjxh(String shdzDjxh) {
		this.shdzDjxh = shdzDjxh;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getHwxhDjxh() {
		return hwxhDjxh;
	}

	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh = hwxhDjxh;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getKhlxDm4js() {
		return khlxDm4js;
	}

	public void setKhlxDm4js(String khlxDm4js) {
		this.khlxDm4js = khlxDm4js;
	}

	public String getYkjsfsDm4js() {
		return ykjsfsDm4js;
	}

	public void setYkjsfsDm4js(String ykjsfsDm4js) {
		this.ykjsfsDm4js = ykjsfsDm4js;
	}

	public String getXzqhInputSelHtml() {
		return xzqhInputSelHtml;
	}

	public void setXzqhInputSelHtml(String xzqhInputSelHtml) {
		this.xzqhInputSelHtml = xzqhInputSelHtml;
	}

	public QyYlClxxDomain getClxxDomain() {
		if (clxxDomain == null) {
			clxxDomain = new QyYlClxxDomain();
		}
		return clxxDomain;
	}

	public void setClxxDomain(QyYlClxxDomain clxxDomain) {
		this.clxxDomain = clxxDomain;
	}

	public QyZrbmThShdzDomain getThShdzDomain() {
		if (thShdzDomain == null) {
			thShdzDomain = new QyZrbmThShdzDomain();
		}
		return thShdzDomain;
	}

	public void setThShdzDomain(QyZrbmThShdzDomain thShdzDomain) {
		this.thShdzDomain = thShdzDomain;
	}

}
