package com.cy.common.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.dao.WlglptDropDownCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;
import com.cy.common.service.WlglptDropDownCommonService;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.zygl.dao.QyKhDjxxDao;
import com.cy.zygl.domain.DmXzqhDomain;
import com.cy.zygl.domain.QyKhDjxxDomain;
import com.cy.zygl.domain.QyYlClxxDomain;
/**
 * WlglptDropDownCommonServiceImp
 * @author Administrator
 *
 */

@Service
public class WlglptDropDownCommonServiceImp extends BaseBusinessServiceImp implements WlglptDropDownCommonService {
	@Autowired
	private WlglptDropDownCommonDao dao;
	@Autowired
	private QyKhDjxxDao khDao;
	@Autowired
	private WlglptCommonDao commonDao;

	public String[] queryXzqhList(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception{
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<QyKhDjxxDomain> dataList=khDao.queryXzqhList(null);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (QyKhDjxxDomain domain2 : dataList) {
				
				if(null==domain2.getXzqhDm())
					domain2.setXzqhDm("");
				if(null==domain2.getXzqhJc())
					domain2.setXzqhJc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				//sb.append("\"").append(domain2.getXzqhMc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				//sb.append("\"").append(domain2.getXzqhMc()).append("^").append(domain2.getPyjc()).append("\"");
				//sb.append("\"").append(domain2.getXzqhQc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				sb.append("\"").append(domain2.getXzqhQc()).append("^").append(domain2.getXzqhMc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'xzqhQc':'").append(domain2.getXzqhQc()).append("',")
					.append("'xzqhJc':'").append(domain2.getXzqhJc()).append("',")
					.append("'xzqhDm':'").append(domain2.getXzqhDm()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
	
	public void queryXzqhInputSel(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception {
		StringBuilder sbSheng = new StringBuilder();
		StringBuilder sbShi = new StringBuilder();
		StringBuilder sbXian = new StringBuilder();
		
		List<DmXzqhDomain> list = dao.queryXzqhInputSelList();
		
		String xzdqdm = "";
		String sjxzqhDm;
		
		sbSheng.append("<div class=\"inputseltc\" id=\"con_area_1\"> \n")
				.append("<table class=\"province\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n");
		sbShi.append("<div class=\"inputseltc\" id=\"con_area_2\" style=\"display:none\"> \n");
		sbXian.append("<div class=\"inputseltc\" id=\"con_area_3\" style=\"display:none\"> \n");
		
		for (int i=0; i<list.size(); i++) {
			DmXzqhDomain shengj = list.get(i);
			
			if (!xzdqdm.equals(shengj.getXzdqDm())) {
				xzdqdm = shengj.getXzdqDm();
				if (i > 0) {
					//上一个行政大区结束
					sbSheng.append("</td> \n")
							.append("</tr>\n");
				}
				//下一个行政大区开始
				sbSheng.append("<tr> \n")
					.append("<th width=\"18%\">").append(shengj.getXzdqMc()).append("</th> \n")
					.append("<td width=\"82%\"> \n");
			}
				
			//省级开始
			sbSheng.append("<a href=\"#\" onclick=\"onProvinceClick('"+shengj.getXzqhDm()+"')\">").append(shengj.getXzqhJc()).append("</a>\n");
			
			sbShi.append("<table class=\"city\" width=\"100%\" name=\""+shengj.getXzqhDm()+"\" style=\"display: none;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n")
					.append("<tr> \n")
					.append("<td> \n");
			
			//直辖市的把自身包含到市内
			if ("3".equals(shengj.getXzqhjbFlBm())) {
				sbShi.append("<a href=\"#\" onclick=\"onCountyClick('"+shengj.getXzqhDm()+"')\">").append(shengj.getXzqhJc()).append("</a> \n");
			}
			
			int j;
			for (j=i+1; j<list.size(); j++) {
				DmXzqhDomain shij = list.get(j);
				sjxzqhDm = shij.getXzqhDm();
				
				if (!shengj.getXzqhDm().equals(shij.getSjXzqhDm())) {
					//当前省下的市级结束
					sbShi.append("</td></tr></table> \n");
					break;
				}
				
				int k;
				if (StringUtils.isBlank(shij.getXzqhjbFlBm())) {
					for (k = j+1; k<list.size(); k++) {
						DmXzqhDomain qx = list.get(k);
						if (sjxzqhDm.equals(qx.getSjXzqhDm())) {
							//直辖市下的县级开始
							sbShi.append("<a href=\"#\" onclick=\"onCountyClick('"+qx.getXzqhDm()+"')\">").append(qx.getXzqhJc()).append("</a> \n");
						}else {
							break;
						}
					}
				}else {
					//市级开始
					sbShi.append("<a href=\"#\" onclick=\"onCityClick('"+shij.getXzqhDm()+"')\">").append(shij.getXzqhJc()).append("</a> \n");
					
					for (k = j+1; k<list.size(); k++) {
						DmXzqhDomain qx = list.get(k);
						
						if (sjxzqhDm.equals(qx.getSjXzqhDm())) {
							if (k == j+1) {
								//市级下的县级开始
								sbXian.append("<table class=\"county\" name=\""+sjxzqhDm+"\" style=\"display: none;\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n")
										.append("<tr><td>\n")
										.append("<a href=\"#\" onclick=\"onCountyClick('"+shij.getXzqhDm()+"')\">").append(shij.getXzqhJc()).append("</a> \n");
							}		
							
							//县
							sbXian.append("<a href=\"#\" onclick=\"onCountyClick('"+qx.getXzqhDm()+"')\">").append(qx.getXzqhJc()).append("</a> \n");
						}else {
							//若该市下有县，则table结束
							if (k != j+1) {
								sbXian.append("</td></tr></table> \n");
							}
							break;
						}
					}
					
				}
				
				j = k-1;
				
			}
			
			i = j-1;
		}
		
		//最后一个行政大区结束
		sbSheng.append("</td> \n")
				.append("</tr>\n")
		 		.append("</table>\n")
				.append("</div> \n");
		sbShi.append("</td></tr></table> \n")
				.append("</div> \n");
		sbXian.append("</div> \n");
		
		StringBuilder sb = new StringBuilder();
		sb.append(sbSheng).append(sbShi).append(sbXian);
		
		domain.setXzqhInputSelHtml(sb.toString());
		
	}
	
	public String[] queryHykhList(WlglptDropDownCommonDomain domain, UserDomain userDomain)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		
		List<WlglptDropDownCommonDomain> dataList = null;
		//取页面选择的部门下的客户
		if (StringUtils.isNotBlank(domain.getKhDomain().getDjJgbm())) {
			dataList=dao.queryHykhListByBmbm(domain.getKhDomain().getDjJgbm());
		}else if (StringUtils.isNotBlank(domain.getKhDomain().getSsJgbm())) { //取页面选择的公司下的客户
			dataList=dao.queryHykhListBySsjgbm(domain.getKhDomain().getSsJgbm());
		}else {  //否则默认取当前部门下的客户
			dataList=dao.queryHykhListByBmbm(userDomain.getBmbm());
		}
		
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (WlglptDropDownCommonDomain domain2 : dataList) {
				
				if(null==domain2.getFhrDjxh())
					domain2.setFhrDjxh("");
				if(null==domain2.getKhjc())
					domain2.setKhjc("");
				if(null==domain2.getPyqc())
					domain2.setPyqc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				sb.append("\"").append(domain2.getFhrMc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("^").append(domain2.getKhjc()).append("\"");
				dataSb.append("{'fhrMc':'").append(domain2.getFhrMc()).append("',")
				.append("'khlxDm4js':'").append(domain2.getKhlxDm4js()).append("',")
				.append("'ykjsfsDm4js':'").append(domain2.getYkjsfsDm4js()).append("',")
				.append("'fhrDjxh':'").append(domain2.getFhrDjxh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}


	public String[] queryHyhwList(String khDjxh, UserDomain userDomain)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		String CS20001 = commonDao.getFunXtXtcs("20001", userDomain.getGsbm(), "2");
		List<WlglptDropDownCommonDomain> dataList=dao.queryHyhwList(khDjxh, CS20001);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (WlglptDropDownCommonDomain domain2 : dataList) {
				
				if(null==domain2.getHwDjxh())
					domain2.setHwDjxh("");
				if(null==domain2.getHwmc())
					domain2.setHwmc("");
				if(null==domain2.getPyqc())
					domain2.setPyqc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				sb.append("\"").append(domain2.getHwmc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'hwmc':'").append(domain2.getHwmc()).append("',")
				.append("'hwDjxh':'").append(domain2.getHwDjxh()).append("',")
				.append("'bzJldwDm':'").append(domain2.getBzJldwDm()).append("',")
				.append("'slJldwDm':'").append(domain2.getSlJldwDm()).append("',")
				.append("'zlJldwDm':'").append(domain2.getZlJldwDm()).append("',")
				.append("'tjJldwDm':'").append(domain2.getTjJldwDm()).append("',")
				.append("'jsJldwDm':'").append(domain2.getJsJldwDm()).append("',")
				.append("'hwxhDjxh':'").append(domain2.getHwxhDjxh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}


	public String[] queryZhdzList(String khDjxh, UserDomain userDomain)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<WlglptDropDownCommonDomain> dataList=dao.queryHyShdzList(khDjxh);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (WlglptDropDownCommonDomain domain2 : dataList) {
				
				if(null==domain2.getZhdzDjxh())
					domain2.setZhdzDjxh("");
				if(null==domain2.getFhrDz())
					domain2.setFhrDz("");
				if(null==domain2.getPyqc())
					domain2.setPyqc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				if(null==domain2.getXzqhDm())
					domain2.setXzqhDm("");
				if(null==domain2.getXzqhMc())
					domain2.setXzqhMc("");
				sb.append("\"").append(domain2.getXsdz()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'fhrDz':'").append(domain2.getFhrDz()).append("',")
				.append("'xsdz':'").append(domain2.getXsdz()).append("',")
				.append("'zhdzDjxh':'").append(domain2.getZhdzDjxh()).append("',")
				.append("'xzqhDm':'").append(domain2.getXzqhDm()).append("',")
				.append("'xzqhMc':'").append(domain2.getXzqhMc()).append("',")
				.append("'fhrLxr':'").append(domain2.getFhrLxr()).append("',")
				.append("'fhrLxdh':'").append(domain2.getFhrLxdh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}

	public String[] queryHyShrDzList(String khDjxh, UserDomain userDomain)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<WlglptDropDownCommonDomain> dataList=dao.queryHyShrDzList(khDjxh);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (WlglptDropDownCommonDomain domain2 : dataList) {
				
				if(null==domain2.getShdzDjxh())
					domain2.setShdzDjxh("");
				if(null==domain2.getShrDz())
					domain2.setShrDz("");
				if(null==domain2.getPyqc())
					domain2.setPyqc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				sb.append("\"").append(domain2.getXsdz()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'shrDz':'").append(domain2.getShrDz()).append("',")
				.append("'shdzDjxh':'").append(domain2.getShdzDjxh()).append("',")
				.append("'shrLxr':'").append(domain2.getShrLxr()).append("',")
				.append("'shrLxdh':'").append(domain2.getShrLxdh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}


	public String[] queryHyShdwList(String khDjxh, UserDomain userDomain)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<WlglptDropDownCommonDomain> dataList=dao.queryHyShdwList(khDjxh);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (WlglptDropDownCommonDomain domain2 : dataList) {
				
				if(null==domain2.getShdzDjxh())
					domain2.setShdzDjxh("");
				if(null==domain2.getShrMc())
					domain2.setShrMc("");
				if(null==domain2.getPyqc())
					domain2.setPyqc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				if(null==domain2.getXzqhDm())
					domain2.setXzqhDm("");
				if(null==domain2.getXzqhMc())
					domain2.setXzqhMc("");
				sb.append("\"").append(domain2.getXsdz()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'shrMc':'").append(domain2.getShrMc()).append("',")
				.append("'xsdz':'").append(domain2.getXsdz()).append("',")
				.append("'shrDz':'").append(domain2.getShrDz()).append("',")
				.append("'xzqhDm':'").append(domain2.getXzqhDm()).append("',")
				.append("'xzqhMc':'").append(domain2.getXzqhMc()).append("',")
				.append("'shrLxr':'").append(domain2.getShrLxr()).append("',")
				.append("'shrLxdh':'").append(domain2.getShrLxdh()).append("',")
				.append("'khDjxh':'").append(domain2.getKhDjxh()).append("',")
				.append("'shdzDjxh':'").append(domain2.getShdzDjxh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
	
	public String[] queryQyClxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<QyYlClxxDomain> dataList=dao.queryQyClxxBySsbmbm(userDomain.getBmbm(),domain.getClxxDomain());
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (QyYlClxxDomain domain2 : dataList) {
				
				if(null==domain2.getCzXm())
					domain2.setCzXm("");
				if(null==domain2.getClhm())
					domain2.setClhm("");
				if(null==domain2.getSjXm())
					domain2.setSjXm("");
				if(null==domain2.getSjZjhm())
					domain2.setSjZjhm("");
				if(null==domain2.getSjSjhm())
					domain2.setSjSjhm("");
				if(null==domain2.getSjLxdh())
					domain2.setSjLxdh("");
				if(null==domain2.getCzXm())
					domain2.setCzXm("");
				if(null==domain2.getSjSjhm())
					domain2.setSjSjhm("");
				if(null==domain2.getSjLxdh())
					domain2.setSjLxdh("");
				sb.append("\"").append(domain2.getClhm()).append("（车主：").append(domain2.getCzXm())
								.append(" 司机：").append(domain2.getSjXm()).append("）")
								.append("^").append(domain2.getClhm()).append("\"");
				
				dataSb.append("{")
					.append("'xshm':'").append(domain2.getClhm()).append("（车主：").append(domain2.getCzXm())
										.append(" 司机：").append(domain2.getSjXm()).append("）").append("',")
					.append("'czXm':'").append(domain2.getCzXm()).append("',")
					.append("'clDjxh':'").append(domain2.getClDjxh()).append("',")
					.append("'clhm':'").append(domain2.getClhm()).append("',")
					.append("'sjXm':'").append(domain2.getSjXm()).append("',")
					.append("'sjZjhm':'").append(domain2.getSjZjhm()).append("',")
					.append("'sjSjhm':'").append(domain2.getSjSjhm()).append("',")
					.append("'sjLxdh':'").append(domain2.getSjLxdh()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
	
	public String[] queryQyGcxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<QyYlClxxDomain> dataList=dao.queryQyGcxxBySsbmbm(userDomain.getBmbm(),domain.getClxxDomain());
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (QyYlClxxDomain domain2 : dataList) {
				
				if(null==domain2.getClhm())
					domain2.setClhm("");
				sb.append("\"").append(domain2.getClhm())
								.append("^").append(domain2.getClhm()).append("\"");
				
				if(i<dataList.size()-1){
					sb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
	
	public String[] queryZrbmThShdz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception {
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<QyZrbmThShdzDomain> dataList=dao.queryZrbmThShdz(domain.getThShdzDomain(), userDomain);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (QyZrbmThShdzDomain domain2 : dataList) {
				
				if(null==domain2.getZrbmDz())
					domain2.setZrbmDz("");
				if(null==domain2.getZrbmLxr())
					domain2.setZrbmLxr("");
				if(null==domain2.getZrbmLxdh())
					domain2.setZrbmLxdh("");
				if(null==domain2.getZrbmXzqhDm())
					domain2.setZrbmXzqhDm("");
				if(null==domain2.getZrbmXzqhMc())
					domain2.setZrbmXzqhMc("");
				
				sb.append("\"").append(domain2.getZrbmDz()).append("（").append(domain2.getZrbmLxr())
								.append(", ").append(domain2.getZrbmLxdh()).append("）")
								.append("^").append(domain2.getZrbmDz()).append("（").append(domain2.getZrbmLxr())
								.append(", ").append(domain2.getZrbmLxdh()).append("）").append("\"");
				
				dataSb.append("{")
					.append("'xsDz':'").append(domain2.getZrbmDz()).append("（").append(domain2.getZrbmLxr())
									.append(", ").append(domain2.getZrbmLxdh()).append("）").append("',")
					.append("'zrbmDz':'").append(domain2.getZrbmDz()).append("',")
					.append("'zrbmLxr':'").append(domain2.getZrbmLxr()).append("',")
					.append("'zrbmLxdh':'").append(domain2.getZrbmLxdh()).append("',")
					.append("'zrbmXzqhDm':'").append(domain2.getZrbmXzqhDm()).append("',")
					.append("'zrbmXzqhMc':'").append(domain2.getZrbmXzqhMc()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
	
	public void queryGsDz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception {
		List<QyZrbmThShdzDomain> dataList=dao.queryZrbmThShdz(domain.getThShdzDomain(), userDomain);
		if (dataList != null && dataList.size() > 0) {
			domain.setThShdzDomain(dataList.get(0));
		}
	}
	
}
