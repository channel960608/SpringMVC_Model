package channel.springmvc.service.impl;

import channel.springmvc.bean.Service;
import channel.springmvc.dao.ServiceDao;
import channel.springmvc.service.ServiceService;

public class ServiceServiceImpl implements ServiceService{

	private ServiceDao dao;
	@Override
	public void saveService(Service service) {
		// TODO Auto-generated method stub
		dao.saveService(service);
	}

	@Override
	public void updateService(Service service) {
		// TODO Auto-generated method stub
		dao.updateService(service);
	}

	@Override
	public void deleteService(Service service) {
		// TODO Auto-generated method stub
		dao.deleteService(service);
	}

	public ServiceDao getDao() {
		return dao;
	}

	public void setDao(ServiceDao dao) {
		this.dao = dao;
	}

	
	public Service getService(String id) {
		// TODO Auto-generated method stub
		return dao.getService(id);
	}

}
