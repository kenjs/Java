package com.cy.common.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.dao.RyxzCommonDao;
import com.cy.common.domain.RyxzCommonDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.RyxzCommonService;
import com.cy.framework.util.SysToolsUtil;

@Service
public class RyxzCommonServiceImp extends BaseBusinessServiceImp implements RyxzCommonService {
	
	private static final String INPUT_TYPE="checkbox";
	private static final String INPUT_NAME_QYRY="qyry";
	private static final String INPUT_NAME_FBSRY="fbsry";
	private static final String QYRY_LB="2";
	private static final String FBSRY_LB="6";
	private static final String TOP_JBDM="1000000000";//顶级级别代码

	@Autowired
	private RyxzCommonDao dao;
	
	/**
	 * 用于获取人员的方法
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void queryRyList(RyxzCommonDomain domain, UserDomain userDomain) throws Exception {
		
		//组织机构
		List<RyxzCommonDomain> zzjgList=dao.queryQyjgList(userDomain.getQyZcxh());
		//企业内部人员
		List<RyxzCommonDomain> qyryList=dao.queryQyryList(userDomain.getQyZcxh());
		//企业内部人员及组织机构
		//List<RyxzCommonDomain> qyryAndZzjgList=dao.queryQyryAndZzjgList(userDomain.getQyZcxh());
		
		Map<String, List<RyxzCommonDomain>> map=new HashMap<String, List<RyxzCommonDomain>>();
		this.getMap(qyryList, map);
		domain.setQyryTree(this.creatTreeStr(map,zzjgList, INPUT_TYPE, QYRY_LB, INPUT_NAME_QYRY));
		
		_logger.debug("企业内部人员及组织机构Tree:"+domain.getQyryTree());
		//分包商组织结构
		List<RyxzCommonDomain> fbsZzjgList=dao.queryFbsZzjgList(userDomain.getQyZcxh());
		for (RyxzCommonDomain obj : fbsZzjgList) {
			if("0".equals(obj.getFbsDjxh()))
				continue;
			obj.setSjJbdm(obj.getJbdm());
			obj.setJbdm(obj.getJbdm()+"F"+obj.getFbsDjxh());
		}
		//分包商的人员
		List<RyxzCommonDomain> fbsryList=dao.queryFbsryList(userDomain.getQyZcxh());
		for (RyxzCommonDomain obj : fbsryList) {
			obj.setJbdm(obj.getJbdm()+"F"+obj.getFbsDjxh());
		}
		Map<String, List<RyxzCommonDomain>> fbsMap=new HashMap<String, List<RyxzCommonDomain>>();
		this.getMap(fbsryList, fbsMap);
		domain.setFbsryTree(this.creatTreeStr(fbsMap,fbsZzjgList,INPUT_TYPE,FBSRY_LB,INPUT_NAME_FBSRY));
	}

	public void getMap(List<RyxzCommonDomain> dataList, Map<String, List<RyxzCommonDomain>> map) {
		List<RyxzCommonDomain> list=null;
		for (RyxzCommonDomain domain : dataList) {
			if(map.containsKey(domain.getJbdm())){
				((List<RyxzCommonDomain>)map.get(domain.getJbdm())).add(domain);
			}else{
				list=new ArrayList<RyxzCommonDomain>();
				list.add(domain);
				map.put(domain.getJbdm(), list);
			}
		}
	}
	
	
	public String creatTreeStr(Map<String,  List<RyxzCommonDomain>> map,List<RyxzCommonDomain> jgList,String inputType,String type,String name)throws Exception{
		StringBuffer treeStr = new StringBuffer();
		if(null==jgList ||jgList.isEmpty()){
			return treeStr.toString();
		}
		RyxzCommonDomain jgDomain=jgList.get(0);
		if(SysToolsUtil.isNullOrEmpty(jgDomain.getSjJbdm()))
			jgDomain.setSjJbdm(TOP_JBDM);//置顶级
		this.creatFatherTreeStr(map,jgList, inputType, type, name, treeStr, jgDomain);
		return treeStr.toString();
	}
	
	public void creatFatherTreeStr(Map<String,  List<RyxzCommonDomain>> map,List<RyxzCommonDomain> jgList,String inputType,String type,String name,StringBuffer treeStr,RyxzCommonDomain jgDomain)throws Exception{
		treeStr.append("<li>")
		.append("<div>");
		treeStr.append("<input type=\""+inputType+"\" value=\""+jgDomain.getJbdm()+"\" name=\""+name+"\" onclick=\"onSelect(this.id,this.checked)\"");
		treeStr.append(" id=\""+type+"-Z-"+jgDomain.getSjJbdm()+"-"+jgDomain.getJbdm()+"\"/>");
		treeStr.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder1.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />");
		treeStr.append(jgDomain.getJgjc()).append("</div>");
		treeStr.append("<ul style='display:none'>");
		this.getSonTreeZzjg(inputType,type,name,jgList,jgDomain.getJbdm(), treeStr,map);
		this.getSonTreeRy(inputType,type,name,jgDomain.getJbdm(),treeStr,map);
		treeStr.append("</ul>");
		treeStr.append("</li>");
	}
	
	//获取组织机构节点的信息
	public void  getSonTreeZzjg(String inputType,String type,String name,List<RyxzCommonDomain> jgList,String jgId,StringBuffer treeStr,Map<String, List<RyxzCommonDomain>> ryMap)throws Exception{
		RyxzCommonDomain jgDom = null;		
		for(int i=0; i<jgList.size();i++){			
			jgDom = jgList.get(i); 
			String jbdm=jgDom.getJbdm();
			String lxrjbDm ="";
			boolean isHad = false;
			for(Iterator<String> ite = ryMap.keySet().iterator();ite.hasNext();){
				lxrjbDm = ite.next();
				if(lxrjbDm != null && lxrjbDm.length() >= jbdm.length()){				
					lxrjbDm = lxrjbDm.substring(0,jbdm.length());
					if(jbdm.equals(lxrjbDm)){
						isHad = true;
						break;
					}
				}
			}
			if(!isHad){
				continue;
			}
			if(null==jgDom.getSjJbdm())
				jgDom.setSjJbdm(TOP_JBDM);//置顶级
			
			if(jgDom.getSjJbdm().equals(jgId)){
				treeStr.append("<li>")
				.append("<div>")
				.append("<input type=\""+inputType+"\" value=\""+jgDom.getJbdm()+"\" name=\""+name+"\" onclick=\"onSelect(this.id,this.checked)\"");
				treeStr.append(" id=\""+type+"-Z-"+jgId+"-"+jgDom.getJbdm()+"\"/>");
				treeStr.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder1.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />");
				treeStr.append(jgDom.getJgjc()).append("</div>");		
				treeStr.append("<ul style='display:none'>");
				this.getSonTreeZzjg(inputType,type,name,jgList, jgDom.getJbdm(), treeStr,ryMap);
				this.getSonTreeRy(inputType,type,name,jgDom.getJbdm(),treeStr,ryMap);
				treeStr.append("</ul>");
				treeStr.append("</li>");
			}
		}	
	}
	//获取人员节点的信息
	@SuppressWarnings("unchecked")
	public void getSonTreeRy(String inputType,String type,String name,String zzjgJbdm,StringBuffer treeStr,Map<String, List<RyxzCommonDomain>> lxrMap)throws Exception{
		List<RyxzCommonDomain> lxrList = null;
		if(lxrMap.containsKey(zzjgJbdm)){
			lxrList = (List<RyxzCommonDomain>)lxrMap.get(zzjgJbdm);
			for(RyxzCommonDomain lxrDom : lxrList){
				treeStr.append("<li>")
				.append("<div>")
				.append("<input type=\""+inputType+"\" value=\""+lxrDom.getCzyDjxh()+"\" name=\""+name+"\" onclick=\"onSelect(this.id,this.checked)\"");
				treeStr.append(" id=\""+type+"-J-"+zzjgJbdm+"-"+lxrDom.getCzyDjxh()+"\"/>");
				treeStr.append("<img src=\"/wlglpt/resource/pageframe/images/menufile1.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />");
				treeStr.append(lxrDom.getCzyMc()).append("</div>");	
				treeStr.append("</li>");
			}
		}
	}
	
	
}
