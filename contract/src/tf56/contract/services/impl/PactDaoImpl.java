package tf56.contract.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.Pact;
import tf56.contract.services.PactDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ParseFormToBean;

public class PactDaoImpl extends SqlMapClientDaoSupport implements PactDao{
	
	private static final long MIL_COUNT = 2592000000L;//30天的毫秒数
	/**合同管理列表 
	 * @author lianggui.zhou
	 * @date 2013-09-16
	 * ***/
	public String selectPactsList(Map map){
		List<Map> list=new ArrayList<Map>();
		map.put("limitdate", getMonthAfter(map));
		list=this.getSqlMapClientTemplate().queryForList("iBatisSelectPactsList", map);
		String count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectPactsListCount", map).toString();
		String msg=JsonGenerateUtil.getPageListJson(list, count);
		return msg;
	}
	
	//获取当前日期后的第30天日期
	private String getMonthAfter(Map map) {
		Date date = new Date(new Date().getTime()+MIL_COUNT);
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	/**
	 * 增加合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public String transactionAddPact(Map map){
		String pactid="";
		String msg="sorry";
		ParseFormToBean pftb=new ParseFormToBean();
		Pact pact=new Pact();
		pact=(Pact)pftb.parseToBean(map, pact);
		pactid=this.getSqlMapClientTemplate().insert("iBatisAddPact", pact).toString();
		if(Integer.parseInt(pactid)>0){
			msg="ok";
		}
		/*List appendixs=new ArrayList();
		String[] appendixsAry=map.get("appendixid").toString().split("-");int count=0;
		try {
			*//***
			 * 此处建议使用事务去处理
			 *//*
			try {
				this.getSqlMapClient().startTransaction();
				pactid=this.getSqlMapClientTemplate().insert("iBatisAddPact", pact).toString();
				map.clear();
				map.put("tableid", pactid);
				for(int i=0;i<=appendixsAry.length-1;i++){
					map.put("contractappendixid", appendixsAry[i]);
					count=this.getSqlMapClientTemplate().update("iBatisUpdateAppendixTableId",map);
					count++;
				}
				this.getSqlMapClient().commitTransaction();
				if(count>0){
					msg="ok";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.printStackTrace();
				throw new DataAccessException("") {
				};
			}
		} finally{
			try {
				this.getSqlMapClient().endTransaction();//此处一定要结束事务，不管成功与否
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		return JsonGenerateUtil.getMsgIdJson(msg,pactid);
	}
	/**
	 * 修改合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public String editPact(Map map){
		String msg="sorry";
		int i=this.getSqlMapClientTemplate().update("iBatisEditPact", map);
		if(i>0){
			msg="ok";
		}
		return JsonGenerateUtil.getMsgJson(msg);
	}
	/**
	 * 根据pactid查询pact记录详情
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public Map queryPactDetailById(Map map){
		Map result=new HashMap();
		try {
			result=(Map)this.getSqlMapClientTemplate().queryForObject("iBatisQueryPactDetailById", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	/****
	 * 合同删除，把并删除合同附件
	 * @author lianggui.zhou
	 * @param pactid 合同id
	 * @date 2013-10-22
	 */
	public String pactDel(Map map){
		String msg="sorry";
		try {
			int i=this.getSqlMapClientTemplate().delete("iBatisPactDel", map);
			map.put("tablename", "pact");
			map.put("tableid", map.get("pactid").toString());
			int j=this.getSqlMapClientTemplate().delete("iBatisDelContractAppendix", map);
			if(i>0&&j>=0){
				msg="ok";
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
}
