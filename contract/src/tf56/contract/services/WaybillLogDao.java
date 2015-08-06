package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.WaybillLog;
public interface WaybillLogDao {
   public String insert(Map map); //增
   public String delete(Map map); //删
   public String update(WaybillLog waybillLog); //改
   public String selectById(Map map);  //查Bean
   public String selectList(Map map);  //查List
   /**
    * 根据运单id查list
    * hcm
    * @param id
    * @return
    */
   public List selectWaybillLogList(String id);
}