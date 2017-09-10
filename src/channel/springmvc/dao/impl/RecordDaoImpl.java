package channel.springmvc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import channel.springmvc.bean.Record;
import channel.springmvc.dao.RecordDao;

public class RecordDaoImpl extends HibernateDaoSupport implements RecordDao{

	@Override
	public void saveRecord(Record record) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(record);
	}

	@Override
	public void updateRecord(Record record) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(record);
	}

	@Override
	public void deleteRecord(Record record) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> findRecordByUserId(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select r from Record r where r.user_id = ?",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> findRecordByProId(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select r from Record r where r.pro_id = ?",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> findRecordBySerId(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select r from Record r where r.ser_id = ?",id);
	}

	@Override
	public Record findRecordById(int id) {
		// TODO Auto-generated method stub
		return (Record)getHibernateTemplate().find("select r from Record r where r.id = ?",id).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getRecord() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select r from Record r");
	}

	
	
	
	

}
