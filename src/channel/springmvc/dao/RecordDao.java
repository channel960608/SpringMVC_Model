package channel.springmvc.dao;

import java.util.List;

import channel.springmvc.bean.Record;


public interface RecordDao {
	public void saveRecord(Record record);
	public void updateRecord(Record record);
	public void deleteRecord(Record record);
	public List<Record> findRecordByUserId(String id);
	public List<Record> findRecordByProId(String id);
	public List<Record> findRecordBySerId(String id);
	public Record findRecordById(int id);
	public List<Record> getRecord();
	
	
}
