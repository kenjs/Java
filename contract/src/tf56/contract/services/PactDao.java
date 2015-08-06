package tf56.contract.services;

import java.util.List;
import java.util.Map;

import tf56.contract.domain.Pact;

/**PactDao.java 合同管理类 @author lianggui.zhou @date 2013-09-16****/
public interface PactDao {
	/**合同管理列表 
	 * @author lianggui.zhou
	 * @date 2013-09-16
	 * ***/
	public  String selectPactsList(Map map);
	/**
	 * 增加合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public String transactionAddPact(Map map);
	/**
	 * 修改合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public String editPact(Map map);
	/**
	 * 根据pactid查询pact记录详情
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public Map queryPactDetailById(Map map);
	/**
	 * 根据pactid删除pact记录
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	public String pactDel(Map map);
}
