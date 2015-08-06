package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.ConsigneeConsignorAddress;
public interface ConsigneeConsignorAddressDao {
   public String insert(ConsigneeConsignorAddress consigneeConsignorAddress); //增
   public String delete(Map map); //删
   public String update(ConsigneeConsignorAddress consigneeConsignorAddress); //改
   public String updateChecked(Map map);
   public String selectById(Map map);  //查Bean
   public List selectList(Map map);  //查List
   public String selectCount(Map map);
   /**
    * 查bean为订单
    * @param map
    * @return
    */
   public ConsigneeConsignorAddress selectForOutWaybill(Map map);
}
