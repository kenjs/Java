package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.ContractDictionary;
public interface ContractDictionaryDao {
   public String insert(ContractDictionary contractDictionary); //增
   public String delete(Map map); //删
   public String update(ContractDictionary contractDictionary); //改
   public String selectById(Map map);  //查Bean
   public List selectList(Map map);  //查List
   public List selectTextList(Map map);
   public String getCount(Map map); //获取条数
   public String isExist(Map map);
   /**
    * @author wei.huang
    * @date 2013-11-19
    * @function 通过字典类型type获取List
    * @param type 字典类型
    * @return List
    */
   public List selectListByType(String type);
}
