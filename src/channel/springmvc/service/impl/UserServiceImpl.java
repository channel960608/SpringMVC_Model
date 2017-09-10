package channel.springmvc.service.impl;

import java.util.List;

import channel.springmvc.bean.User;
import channel.springmvc.dao.UserDao;
import channel.springmvc.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao dao;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		dao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		dao.deleteUser(user);
	}

	@Override
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return dao.findUser(id);
	}

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	

	@Override
	public List<User> getUsers(int i) {
		// TODO Auto-generated method stub
		switch(i){
		case 0:
			return dao.findAllUser();
		case 1:
			return dao.findUserList();
		case 2:
			return dao.findProList();
		}
		
		return null;
	}

	

}
