package channel.springmvc.service.impl;

import java.util.List;

import channel.springmvc.bean.Record;
import channel.springmvc.dao.RecordDao;
import channel.springmvc.service.RecordService;

public class RecordServiceImpl implements RecordService{

	private RecordDao dao;
	
	public RecordDao getDao() {
		return dao;
	}

	public void setDao(RecordDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveRecord(Record record) {
		// TODO Auto-generated method stub
		dao.saveRecord(record);
		
	}

	@Override
	public void updateRecord(Record record) {
		// TODO Auto-generated method stub
		dao.updateRecord(record);
	}

	@Override
	public void deleteRecord(Record record) {
		// TODO Auto-generated method stub
		dao.deleteRecord(record);
	}

	public List<Record> findRecordByUserId(String id) {
		// TODO Auto-generated method stub
		return dao.findRecordByUserId(id);
	}

	public List<Record> findRecordByProId(String id) {
		// TODO Auto-generated method stub
		return dao.findRecordByProId(id);
	}

	public List<Record> findRecordBySerId(String id) {
		// TODO Auto-generated method stub
		return dao.findRecordBySerId(id);
	}

	@Override
	public Record findRecordById(int id) {
		// TODO Auto-generated method stub
		return dao.findRecordById(id);
	}

	@Override
	public List<Record> getRecord() {
		// TODO Auto-generated method stub
		return dao.getRecord();
	}

	
}
