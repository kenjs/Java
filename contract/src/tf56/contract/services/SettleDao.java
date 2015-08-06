package tf56.contract.services;

import java.util.List;
import java.util.Map;

import tf56.contract.domain.SettleSet;
import tf56.contract.domain.SettleStepSet;

public interface SettleDao {

	String settleSetList(Map map);//获取结算设置列表

	String isExist(Map params);
	
	String isExist_jt(Map params);

	String delete(Map formMap);//删除记录

	String insert(SettleSet settleSet);

	String insert(SettleStepSet settleStepSet);
	
	String update(SettleSet settleSet);

	String update(SettleStepSet settleStepSet);

	String settleSetListByTopartyidAndType(Map map);
	
	List settleSetListByTopartyidAndType_jt(Map map);

	String deleteByTopartyidAndType(Map formMap);

	List<Map> selectList(Map map);

	List<SettleSet> selectList(String frompartyid, String topartyid, String type);
	//结算设置（阶梯报价）详情查询
	List<SettleSet> selectJtList(String frompartyid, String topartyid, String type);

	Integer countSettleSetList(String frompartyid, String topartyid, String type);

	String selectListBySettleSetId(Map params);

	void deleteStep(Map formMap);

	String delete_jt(Map formMap);

	String selectJtCount(Map formMap);

	List settleStepSetList(Map params);

	String settleSetListByTopartyidAndType_jtCount(Map param);

	String settleStepSetListCount(Map params);
	
	String save_delete(Map params);

}
