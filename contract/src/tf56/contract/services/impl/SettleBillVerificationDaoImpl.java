package tf56.contract.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.SettleBillVerification;
import tf56.contract.services.SettleBillVerificationDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class SettleBillVerificationDaoImpl extends SqlMapClientDaoSupport implements SettleBillVerificationDao {
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @param settleBillVerification
	 * @function 新增核销发票
	 */
	public String insert(SettleBillVerification settleBillVerification) {
		  String settlebillverificationid="", msg="ok";
		  try{
			  settlebillverificationid=(String) getSqlMapClientTemplate().insert("iBatisInsertSettleBillVerification",settleBillVerification);
		  }catch(Exception e){
			  e.printStackTrace();
			  msg=e.getMessage();
		  }
		  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, settlebillverificationid);
		  return msgJson;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-22
	 * @param settlebillid
	 * @function 通过settlebillid查询发票列表
	 */
	public String selectList(String settlebillid){
		List<Map> list=null;
		try{
			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleBillVerificationById",settlebillid);
		}catch(Exception e){
			e.printStackTrace();
		}
		String msgJson=JsonGenerateUtil.getListJson(list);
		return msgJson;
	}
}
