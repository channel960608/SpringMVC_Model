package channel.springmvc.bean;

import java.sql.Timestamp;

public class Record {
	private int id;
	private String pro_id;
	private String user_id;
	private String ser_id;
	private String comment;
	private float pay;
	private Timestamp time_input;
	private Timestamp time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSer_id() {
		return ser_id;
	}
	public void setSer_id(String ser_id) {
		this.ser_id = ser_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getPay() {
		return pay;
	}
	public void setPay(float pay) {
		this.pay = pay;
	}
	public Timestamp getTime_input() {
		return time_input;
	}
	public void setTime_input(Timestamp time_input) {
		this.time_input = time_input;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
