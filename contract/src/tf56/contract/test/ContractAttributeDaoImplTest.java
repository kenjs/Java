package tf56.contract.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.ContractAppendix;
import tf56.contract.services.ContractAppendixDao;
import tf56.contract.services.ContractAttributeDao;
import tf56.sofa.util.ParseFormToBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class ContractAttributeDaoImplTest {
 @Autowired
 private ContractAttributeDao contractAttributeDao;
 @Test
 public void testGetPageList() {
  Map msgMap = new HashMap();
  msgMap.put("frompartyid", "176766");
	msgMap.put("shipperorsubcontractor", "1");
	msgMap.put("topartyid", "176766");
	msgMap.put("attributename", "客户号");       //
  System.out.println("检验："+contractAttributeDao.selectContractAttributeList(msgMap));
//  System.out.println("增  加"+contractAppendixDao.insert(contractAppendix)); //增
//  System.out.println("删  除"+contractAppendixDao.delete(map));     //删
//  System.out.println("更  新"+contractAppendixDao.update(contractAppendix)); //改
//  System.out.println("查一条"+contractAppendixDao.selectById(map)); //查一条
//  System.out.println("查多条"+contractAppendixDao.selectList(map)); //查多条
 }
}
