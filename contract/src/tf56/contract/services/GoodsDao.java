package tf56.contract.services;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.Goods;
public interface GoodsDao {
   public String insert(Goods goods); //增
   public String delete(Map map); //删
   public String update(Goods goods); //改
   public String selectById(Map map);  //查Bean
   public String selectList(Map map);  //查List
   /**
    * 根据运单id查货物列表
    * hcm
    * @param map
    * @return
    */
   public List selectGoodsList(String id);
   
   /**
    * 根据运单id查货物总数量
    * yao.xia
    * @param map
    * @return
    */
   public String selectNumSumByWaybillId(Map map);
   /**
    * 结算时修改保存
    * hcm
    * @param goods
    * @throws SQLException
    */
   public void updateWhenSettleBill(Goods goods) throws SQLException;
}