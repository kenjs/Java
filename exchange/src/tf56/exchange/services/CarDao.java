package tf56.exchange.services;

import java.util.List;
import java.util.Map;


public interface CarDao {
	  /**
	   * @author tlp
	   * @param map
	   * @Date 2013-3-04-25
	   */
	public String updateCarLocation(Map map);

	public String selectparkcarList(Map map);
}
