package channel.springmvc.service;

import java.util.List;

import channel.springmvc.bean.User;

public interface UserService {
	
	void saveUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
	User findUser(String id);
	List<User> getUsers(int i);
	
}
