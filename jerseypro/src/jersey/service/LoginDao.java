package jersey.service;

import java.util.Map;

import jersey.domain.User;

public interface LoginDao {
	public User selectUserById(Map map);
}
