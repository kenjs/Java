package tf56.contract.services;

import tf56.contract.domain.SettleBillVerification;

public interface SettleBillVerificationDao {
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @param settleBillVerification
	 * @function 新增核销发票
	 */
	public String insert(SettleBillVerification settleBillVerification);
	/**
	 * @author wei.huang
	 * @date 2013-11-22
	 * @param settlebillid
	 * @function 通过settlebillid查询发票列表
	 */
	public String selectList(String settlebillid);
}
