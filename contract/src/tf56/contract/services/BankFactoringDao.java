package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.BankFactoring;
public interface BankFactoringDao {
   public String insert(BankFactoring bankFactoring); //增
   public String update(BankFactoring bankFactoring); //改
   public String selectList(Map map);  //查List
   /**
	 * @author wei.huang
	 * @date 2014-3-5
	 * @function 获取分包商的保理资格及账期
	 * @param map[settlebillid]
	 * @return
	 */
   public String selectConsigneeBusinessDays(Map map);
}