package tf56.contract.services;

import java.util.List;
import java.util.Map;

public interface ContractAttributeDao {
	/**
	 * 总包会员修改分包商基础信息或者总包修改发货方基础信息
	 * @author lianggui.zhou
	 * @date 2013-10-21
	 * @param frompartyid总包会员id,topartyid 分包商或发货方会员id,shipperorsubcontractor:总包-->发货方关系or 总包-->分包关系
	 * @return
	 */
	public String updateContractAttribute(String partyid,String topartyid,String shipperorsubcontractor,String attributename,String attributevalue,Map map);
	
	/**
	 * 根据frompartyid和topartyid查询总包扩展信息
	 * @param map
	 * @return
	 * @author donghui.wang
	 * @date 2013-10-23
	 */
	public List<Map> selectContractAttributeList(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-25
	 * @function 验证客户号是否存在
	 * @param map
	 * @return
	 */
	public String selectClientNumberCount(Map map);
	/**
	 * 更改银行保理状态
	 * @param map
	 * @return
	 * @author hcm
	 * @date 2014-3-3
	 */
	public String updateStatus(Map map);
	
	/**
	 * 新增银行保理
	 * @param map
	 * @return
	 * @author haoy
	 * @date 2014-3-3
	 */
	public String save(Map map);
	
	/**
	 * 修改银行保理
	 * @param map
	 * @return
	 * @author haoy
	 * @date 2014-3-3
	 */
	public String update(Map map);
	
	/**
	 * 保存ContractAttribute
	 *author haoy
	 * @date 2014-3-3
	 *
	 */
	public String saveContractAttribute(Map map);
	
	/**
	 * 修改ContractAttribute
	 *author haoy
	 * @date 2014-3-3
	 *
	 */
	public String updateContractAttribute(Map map);
	
	/**
	 *  检验分包商是否已经加入银行保理
	 * @author haoy
	 * @date 2014-3-4
	 *
	 */
	public String checkExists(Map map);
	/**
	 * @author wei.huang
	 * @date 2014-3-12
	 * @function 查询分包商的银行保理状态
	 * @param map settlebillid
	 * @return
	 */
	public Map selectBankFactoringStatus(Map map);
}