package tf56.contract.services;

import java.util.Map;

public interface OperatorService {
	
	public String update(Map map); //更新密码
	public String selectPageByKey(Map map);
	public String delete(Map map);
	public String checkoperator(Map map);
	public String save(Map map);
	public String selectbyid(Map map);
	public String updateoperatormsg(Map map);
	public String updateoldpassword(Map map);//重置密码
	public String selectAdminMsgByPartyid(Map map);
	public String checkMobileNumber(Map map);
	public String updateMobileNumber(Map map);


}
