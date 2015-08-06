package jersey.service.serviceimpl;

import java.util.Map;

import jersey.domain.User;
import jersey.service.LoginDao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class LoginDaoImpl extends SqlMapClientDaoSupport implements LoginDao {

	@Override
	public User selectUserById(Map map) {
		User user = (User) this.getSqlMapClientTemplate().queryForObject("myBatisSelectUserByid", map);
		return user;
	}

}
