package tf56.contract.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.annotations.POST;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.BankFactoring;
import tf56.contract.domain.WaybillAmount;
import tf56.contract.services.ContractAttributeDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ParseFormToBean;

public class ContractAttributeDaoImpl extends SqlMapClientDaoSupport implements ContractAttributeDao{
	/**
	 * 总包会员修改分包商基础信息或者总包修改发货方基础信息
	 * @author lianggui.zhou
	 * @date 2013-10-21
	 * @param frompartyid总包会员id,topartyid 分包商或发货方会员id,shipperorsubcontractor:总包-->发货方关系or 总包-->分包关系
	 * @return
	 */
	@POST
	public String updateContractAttribute(String partyid,String topartyid,String shipperorsubcontractor,String attributename,String attributevalue,Map map){
		String msg="sorry";
		if(shipperorsubcontractor.equals("0")){//总包--分包商关系
			Map map1=new HashMap();
			map1.put("frompartyid", partyid);
			map1.put("shipperorsubcontractor", "0");
			map1.put("topartyid",topartyid);
			map1.put("attributename", attributename);
			map1.put("attributevalue", attributevalue);
			int i=this.getSqlMapClientTemplate().update("iBatisUpdateContractAttribute", map1);
			if(i>0){
				msg="ok";
			}else{
			    if(StringUtils.isNotBlank(attributevalue)){
			        String result = (String) this.getSqlMapClientTemplate().insert("iBatisInsertContractAttribute",map1);
	                if(!"".equals(result.toString())){
	                    msg="ok";
	                }
			    }else{
			        msg = "ok";
			    }
			    
			}
		}
		if(shipperorsubcontractor.equals("1")){//总包--发货方关系
			Map map1=new HashMap();
			map1.put("frompartyid", partyid);
			map1.put("shipperorsubcontractor", "1");
			map1.put("topartyid", topartyid);
			map1.put("attributename", "业务员");
			map1.put("attributevalue", map.get("saler"));
			int i=this.getSqlMapClientTemplate().update("iBatisUpdateContractAttribute", map1);
			if(i>0){
				msg="ok";
			}
			map1.put("attributename", "客户号");
			map1.put("attributevalue", map.get("clientcode"));
			int j=this.getSqlMapClientTemplate().update("iBatisUpdateContractAttribute", map1);
			if(j>0){
				msg="ok";
			}
	}
		return msg;
	}

	
	/**
     * 根据frompartyid和topartyid查询总包扩展信息
     * @param map
     * @return
     * @author donghui.wang
     * @date 2013-10-23
     */
    @Override
    public List<Map> selectContractAttributeList(Map map) {
        List<Map> result = this.getSqlMapClientTemplate().queryForList("iBatisSelectContractAttributeList",map);
        return result;
    }
    /**
	 * @author wei.huang
	 * @date 2013-12-25
	 * @function 验证客户号是否存在
	 * @param map
	 * @return
	 */
	public String selectClientNumberCount(Map map){
		String count="0";
		try{
			count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectClientNumberCount", map).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 更改银行保理状态
	 * @param map
	 * @return
	 * @author hcm
	 * @date 2014-3-3
	 */
	public String updateStatus(Map map) { //改 实现类
		  String waybillamountid="", msg="ok";
		  try{
		   int i=getSqlMapClientTemplate().update("iBatisUpdateFactoringStatus", map);
		   if(i!=1){
		    msg= "更新失败";
		   }
		  }catch(Exception e){
		   e.printStackTrace();
		   msg=e.getMessage();
		  }
		  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
		  return msgJson;
		 }
	
	/**
	 * 新增银行保理
	 * @param map
	 * @return
	 * @author haoy
	 * @date 2014-3-3
	 */
	public String save(Map map){
		String bankfactoringid="",msg = "ok";
		ParseFormToBean pft = new ParseFormToBean();
		BankFactoring bean = new BankFactoring();
		bean = (BankFactoring) pft.parseToBean(map, bean);
		try {
			bankfactoringid = getSqlMapClientTemplate().insert("iBatisInsertBankFactoring", bean).toString();
			if(!StringUtils.isNotBlank(bankfactoringid)){
				msg = "插入失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}		
		return msg;
	}
	
	/**
	 * 修改银行保理
	 * @param map
	 * @return
	 * @author haoy
	 * @date 2014-3-3
	 */
	public String update(Map map){
		String bankfactoringid="",msg = "ok";
		ParseFormToBean pft = new ParseFormToBean();
		BankFactoring bean = new BankFactoring();
		bean = (BankFactoring) pft.parseToBean(map, bean);
		try {
			int i = getSqlMapClientTemplate().update("iBatisUpdateBankFactoring", bean);
			if(i!=1){
			    msg= "更新失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}		
		return msg;
	}
	
	/**
	 * 保存ContractAttribute
	 *author haoy
	 * @date 2014-3-3
	 *
	 */
	public String saveContractAttribute(Map map){
		String contractattributeid="",msg = "ok";
		try {
			contractattributeid = getSqlMapClient().insert("iBatisInsertContractAttribute", map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson=JsonGenerateUtil.getMsgIdJson(msg, contractattributeid);
		return msgJson;
	}
	
	/**
	 * 修改ContractAttribute
	 * @author haoy
	 * @date 2014-3-3
	 *
	 */
	public String updateContractAttribute(Map map){
		String contractattributeid="",msg = "ok";
		try {
			int i = getSqlMapClient().update("iBatisUpdateFactoringInformation", map);
			if(i!=1){
			    msg= "更新失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson=JsonGenerateUtil.getMsgIdJson(msg, contractattributeid);
		return msgJson;
	}
	
	/**
	 *  检验分包商是否已经加入银行保理
	 * @author haoy
	 * @date 2014-3-4
	 *
	 */
	public String checkExists(Map map){
		String msg = "ok";
		try {
			String count = getSqlMapClient().queryForObject("iBatisCheckExistsBank", map).toString();
			if("0".equals(count)){
				msg = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
		return msgJson;
	}
	/**
	 * @author wei.huang
	 * @date 2014-3-12
	 * @function 查询分包商的银行保理状态
	 * @param map settlebillid
	 * @return
	 */
	public Map selectBankFactoringStatus(Map map){
		Map status=null;
		try {
			status = (Map)this.getSqlMapClient().queryForObject("iBatisSelectBankFactoringStatus", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
