package tf56.contract.services.impl;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.SettleStepSet;
import tf56.contract.services.SettleStepSetDao;

public class SettleStepSetDaoImpl extends SqlMapClientDaoSupport implements SettleStepSetDao{
 public List<SettleStepSet> selectList(Map map) { //查询数据到Bean,然后转成map
	 List<SettleStepSet> list = null;
  try{
   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleStepSetList",map);
  }catch(Exception e){
   e.printStackTrace();
  }
  return list;
 }
}