package channel.springmvc.service;

import channel.springmvc.bean.Service;

public interface ServiceService {
	void saveService(Service service);
	void updateService(Service service);
	void deleteService(Service service);
	public Service getService(String id);
}
