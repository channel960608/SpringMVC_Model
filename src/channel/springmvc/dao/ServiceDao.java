package channel.springmvc.dao;

import channel.springmvc.bean.Service;

public interface ServiceDao {

	public void saveService(Service service);
	public void updateService(Service service);
	public void deleteService(Service service);
	public Service getService(String id);
}
