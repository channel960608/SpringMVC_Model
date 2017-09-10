package channel.springmvc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import channel.springmvc.bean.User;
import channel.springmvc.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(user);
	}

	@Override
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return (User) getHibernateTemplate().find("select u from User u where u.user_id = ?",id).get(0);
	}

	
	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find("select u from User u where u.type=0");
		
		return list;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> list = getHibernateTemplate().find("select u from User u");
		return list;
	}

	@Override
	public List<User> findProList() {
		// TODO Auto-generated method stub
		List<User> list = getHibernateTemplate().find("select u from User u where u.type>=1 and u.type<=4");		
		return list;
	}

}
