package tf56.contract.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.ContractDictionary;
import tf56.contract.services.ContractDictionaryDao;
import tf56.sofa.util.ParseFormToBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class ContractDictionaryDaoImplTest {
 @Autowired
 private ContractDictionaryDao contractDictionaryDao;
 @Test
 public void testGetPageList() {
  Map map = new HashMap();
//  map.put("contractdictionaryid", "100");       //
//  map.put("type", "g");                      //
//  map.put("text", "g");                      //
//  map.put("description", "test");               //
//  map.put("inputdate", "2013-09-22 09:48:03");  //输入时间
//  map.put("updatedate", "2013-09-22 09:48:03"); //
  ContractDictionary contractDictionary = new ContractDictionary();      //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  contractDictionary = (ContractDictionary) pftb.parseToBean(map, contractDictionary);
  //System.out.println("增  加"+contractDictionaryDao.insert(contractDictionary)); //增
//  System.out.println("删  除"+contractDictionaryDao.delete(map));     //删
//  System.out.println("更  新"+contractDictionaryDao.update(contractDictionary)); //改
//  System.out.println("查一条"+contractDictionaryDao.isExist(map)); //查一条
//  map = new HashMap();
  map.put("skipCount", "0");       //
  map.put("pageSize", "10");
  System.out.println("查多条"+contractDictionaryDao.selectList(map)); //查多条
 }
}
