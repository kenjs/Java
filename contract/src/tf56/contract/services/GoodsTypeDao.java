package tf56.contract.services;
import java.util.List;
import java.util.Map;

import net.sf.serfj.annotations.POST;
import tf56.contract.domain.GoodsType;

/**
 * @author wei.huang
 * @date 2013-10-15
 */
public interface GoodsTypeDao {
   public String insert(GoodsType goodsType); //增
   public String delete(Map map); //删
   public String update(GoodsType goodsType); //改
   public String selectById(Map map);  //查Bean
   public List selectList(Map map);  //查List
   public String getCount(Map map); //获取记录的条数
   /**
    * wei.huang
    * 2013-10-16
    * 修改默认货物
    * @return
    */
   public String updateChecked(Map map);
}
