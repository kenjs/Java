package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.ContractAppendix;

/**
 * 2013-10-28
 * @author wei.haung
 */
public interface ContractAppendixDao {
   public String insert(ContractAppendix contractAppendix); //增
   /**
    * wei.huang
    * 2013-10-28
    * 删除一条记录
    * @param map contaractappendixid
    * @return
    */
   public String delete(Map map); //删
   public String update(ContractAppendix contractAppendix); //改
   /**
    * wei.huang
    * 2013-10-28
    * 查询一条记录
    * @param map contaractappendixid
    * @return
    */
   public String selectById(Map map);  //查Bean
   /**
    * wei.huang
    * 2013-10-28
    * 查询多条记录
    * @param map：tabelid,tablename
    * @return
    */
   public List selectList(Map map);  //查List
   /**
    * wei.huang
    * 2013-10-28
    * 检验文件是否已经存在
    * @param map：url 文件完整路径
    * @return 文件数量
    */
   public String isExist(Map map);
}
