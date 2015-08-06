package tf56.exchange.services;

import java.util.List;
import java.util.Map;


public interface BillService {
    /**
     * @author tlp
     * @param map
     * @Date 2013-3-04-25
     */
	public String insertAwaitBill(Map map);
	
	/**
	 * @author wjj
	 * @funciton 根据身份证新增收单记录
	 * @date 2014-04-02
	 */
	public String insertBillByCertificate(Map map);

}
