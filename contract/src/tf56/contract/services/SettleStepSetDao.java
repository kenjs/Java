package tf56.contract.services;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.SettleStepSet;
public interface SettleStepSetDao {
   public List<SettleStepSet> selectList(Map map);  //查List
}