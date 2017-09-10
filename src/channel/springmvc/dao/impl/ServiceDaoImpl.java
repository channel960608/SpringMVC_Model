package channel.springmvc.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import channel.springmvc.bean.Service;
import channel.springmvc.dao.ServiceDao;


public class ServiceDaoImpl extends HibernateDaoSupport implements ServiceDao{

	@Override
	public void saveService(Service service) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(service);
	}

	@Override
	public void updateService(Service service) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(service);
	}

	@Override
	public void deleteService(Service service) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(service);
	}

	@Override
	public Service getService(String id) {
		// TODO Auto-generated method stub
		return (Service)getHibernateTemplate().find("select s from Service s where s.ser_id = ?",id).get(0);
	}

	
	
	
	

}