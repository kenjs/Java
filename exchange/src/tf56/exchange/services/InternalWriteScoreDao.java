package tf56.exchange.services;
import java.util.List;
import java.util.Map;
public interface InternalWriteScoreDao {
	  public String insert(Map map); //增
	  public String selectById(Map map);  //查Bean
	  public String selectListScores(Map formMap);


}