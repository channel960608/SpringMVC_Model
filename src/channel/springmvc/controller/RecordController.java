package channel.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


import channel.springmvc.bean.Record;
import channel.springmvc.bean.Service;
import channel.springmvc.bean.User;
import channel.springmvc.service.RecordService;
import channel.springmvc.service.ServiceService;
import channel.springmvc.service.UserService;
import channel.springmvc.util.mail.MailSenderInfo;
import channel.springmvc.util.mail.SimpleMailSender;

public class RecordController extends AbstractController{

	private ServiceService serviceService;
	private UserService userService;
	private RecordService recordService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
												 HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		String action = arg0.getParameter("action");
		if(action.equals("register")){
			return register(arg0, arg1);
		}else if(action.equals("login")){
			return login(arg0, arg1);
		}else if(action.equals("record")){
			return record(arg0,arg1);
		}else if(action.equals("removeuser")){
			return removeuser(arg0,arg1);
		}else if(action.equals("updateuser")){
			return updateuser(arg0,arg1);
		}else if(action.equals("getuser")){
			return getuser(arg0,arg1);
		}else if(action.equals("getrecordofuser")){
			return getrecordofuser(arg0,arg1);
		}else if(action.equals("addservice")){
			return addservice(arg0,arg1);
		}else if(action.equals("updateservice")){
			return updateservice(arg0,arg1);
		}else if(action.equals("findservice")){
			return findservice(arg0,arg1);
		}else if(action.equals("removeservice")){
			return findservice(arg0,arg1);
		}else if(action.equals("removerecord")){
			return removerecord(arg0,arg1);
		}else if(action.equals("getuserlist")){
			return getuserlist(arg0,arg1);
		}else if(action.equals("getprolist")){
			return getprolist(arg0,arg1);
		}else if(action.equals("getadmlist")){
			return getadmlist(arg0,arg1);
		}else if(action.equals("sendmail")){
			return sendmail(arg0,arg1);
		}else if(action.equals("senduserlist")){
			return senduserlist(arg0,arg1);
		}else if(action.equals("sendprolist")){
			return sendprolist(arg0,arg1);
		}


		return null;
	}

	private ModelAndView senduserlist(HttpServletRequest arg0,
									  HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		try{
			User user=userService.findUser(user_id);
			String mail=user.getMail();
			MailSenderInfo info = new MailSenderInfo();
			info.setMailServerHost("smtp.ym.163.com");
			info.setMailServerPort("25");
			info.setValidate(true);
			info.setUserName("pinckney@pinckney.win");
			info.setPassword("xjx66601602");//您的邮箱密码
			info.setFromAddress("pinckney@pinckney.win");
			info.setToAddress(mail);

			info.setSubject("会员一周报表");


			String content="<table><thead><tr><th>提供者姓名</th><th>服务名称</th><th>服务时间</th></tr></thead><tbody>";
			List<Record> list=recordService.findRecordByUserId(user_id);
			float sumPay=0;
			for(Record record:list){
				User m_user=userService.findUser(record.getPro_id());
				content+="<tr><td>"+m_user.getName()+"</td>";
				Service m_service=serviceService.getService(record.getSer_id());
				content+="<td>"+m_service.getName()+"</td>";
				content+="<td>"+record.getTime()+"</td></tr>";
				sumPay+=record.getPay();
			}
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(sumPay);
			content+="<tr><td>本周服务费用总额：</td><td>"+p+"</td></tr>";
			content+="</tbody></table>";
			info.setContent(content);

			SimpleMailSender sms = new SimpleMailSender();

			if(SimpleMailSender.sendHtmlMail(info)){
				printWriter.print("Success");
			}
			else{
				printWriter.print("Send failed");
			}
		}catch(Exception e){}

		printWriter.close();
		return null;
	}

	private ModelAndView sendprolist(HttpServletRequest arg0,
									 HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		try{
			User user=userService.findUser(user_id);
			String mail=user.getMail();
			MailSenderInfo info = new MailSenderInfo();
			info.setMailServerHost("smtp.ym.163.com");
			info.setMailServerPort("25");
			info.setValidate(true);
			info.setUserName("pinckney@pinckney.win");
			info.setPassword("xjx66601602");//您的邮箱密码
			info.setFromAddress("pinckney@pinckney.win");
			info.setToAddress(mail);

			info.setSubject("会员一周报表");

			String content="<table><thead><tr><th>用户ID</th><th>用户名称</th><th>服务ID</th>" +
					"<th>费用</th><th>提出时间</th><th>服务时间</th></tr></thead>" +
					"<tbody>";

			List<Record> list=recordService.findRecordByProId(user_id);
			float sumPay=0;
			for(Record record:list){
				User m_user=userService.findUser(record.getUser_id());
				content+="<tr><td>"+record.getUser_id()+"</td>";
				content+="<td>"+m_user.getName()+"</td>";
				content+="<td>"+record.getSer_id()+"</td>";
				content+="<td>"+record.getPay()+"</td>";
				content+="<td>"+record.getTime_input()+"</td>";
				content+="<td>"+record.getTime()+"</td></tr>";

				sumPay+=record.getPay();
			}
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(sumPay);

			content+="<tr><td>本周服务费用总额：</td><td>"+p+"</td></tr>";

			content+="</tbody></table>";

			info.setContent(content);

			SimpleMailSender sms = new SimpleMailSender();

			if(SimpleMailSender.sendHtmlMail(info)){
				printWriter.print("Success");
			}
			else{
				printWriter.print("Send failed");
			}






		}catch(Exception e){}

		printWriter.close();
		return null;
	}

	private ModelAndView sendmail(HttpServletRequest arg0,
								  HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter=arg1.getWriter();

		String subject=arg0.getParameter("subject");
		String content=arg0.getParameter("content");
		String address=arg0.getParameter("address");
		String html=null;
		try{
			html=arg0.getParameter("html");
		}catch(Exception e){}


		MailSenderInfo info = new MailSenderInfo();
		info.setMailServerHost("smtp.ym.163.com");
		info.setMailServerPort("25");
		info.setValidate(true);
		info.setUserName("pinckney@pinckney.win");
		info.setPassword("xjx66601602");//您的邮箱密码
		info.setFromAddress("pinckney@pinckney.win");

		info.setToAddress(address);
		info.setContent(content);
		info.setSubject(subject);

		SimpleMailSender sms = new SimpleMailSender();
		if(html.equals("true")){
			if(SimpleMailSender.sendHtmlMail(info)){
				printWriter.print("Success");
			}
			else{
				printWriter.print("Send failed");
			}

		}else{
			if(sms.sendTextMail(info)){
				printWriter.print("Success");
			}
			else{
				printWriter.print("Send failed");
			}
		}

		printWriter.close();
		return null;
	}

	private ModelAndView getadmlist(HttpServletRequest arg0,
									HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter=arg1.getWriter();

		//List<Record> list=recordService.getRecord();

		Map<String,Object> map=new HashMap<String,Object>();

		List<User> users=userService.getUsers(1);
		List<User> pros=userService.getUsers(2);

		List<Map> prolist=new ArrayList<Map>();
		for(User pro:pros){
			Map<String,Object> m_map=new HashMap<String,Object>();
			List<Record> reclist=recordService.findRecordByProId(pro.getUser_id());
			int count=reclist.size();
			float sumPay=0;
			for(Record rec:reclist){
				sumPay+=rec.getPay();
			}
			m_map.put("name", pro.getName());
			m_map.put("user_id", pro.getUser_id());
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(sumPay);
			m_map.put("sum of pay", p);
			m_map.put("count", count);
			prolist.add(m_map);
		}

		List<Map> userlist=new ArrayList<Map>();
		for(User user:users){
			Map<String,Object> m_map=new HashMap<String,Object>();
			List<Record> reclist=recordService.findRecordByUserId(user.getUser_id());
			int count=reclist.size();
			float sumPay=0;
			for(Record rec:reclist){
				sumPay+=rec.getPay();
			}
			m_map.put("name", user.getName());
			m_map.put("user_id", user.getUser_id());
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(sumPay);
			m_map.put("sum of pay", p);
			m_map.put("count", count);
			userlist.add(m_map);
		}

		Map<String,Object> ans=new HashMap<String,Object>();
		ans.put("users", userlist);
		ans.put("pros", prolist);

		printWriter.print(JSONObject.fromObject(ans).toString());
		printWriter.close();

		return null;
	}

	private ModelAndView getprolist(HttpServletRequest arg0,
									HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub

		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter=arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}

		if(!(user.getType()<=4&&user.getType()>=1)){
			printWriter.print("Not a Provider");
			printWriter.close();
			return null;
		}

		Map<String,String> map=new HashMap<String,String>();
		map.put("name", user.getName());
		map.put("user_id", user.getUser_id());
		map.put("street", user.getStreet());
		map.put("city", user.getCity());
		map.put("country", user.getCountry());

		List<Record> list=recordService.findRecordByProId(user_id);
		List<Map<String,String>> recordData=new ArrayList<Map<String,String>>();

		float sumPay=0;
		int count=0;
		for(Record record:list){
			Map<String,String> m_map=new HashMap<String,String>();
			User m_user=userService.findUser(record.getUser_id());
			Service ser=serviceService.getService(record.getSer_id());
			m_map.put("user_name", m_user.getName());
			m_map.put("user_id", m_user.getUser_id());
			m_map.put("ser_id", record.getSer_id());
			m_map.put("pay", String.valueOf(record.getPay()));
			m_map.put("time",record.getTime().toString());
			m_map.put("time_input",record.getTime_input().toString());
			float m_pay=record.getPay();
			sumPay+=m_pay;
			recordData.add(m_map);
			count++;
		}
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format(sumPay);
		map.put("sum of pay", p);
		map.put("sum of record", String.valueOf(count));

		Map<String,Object> ans=new HashMap<String,Object>();
		ans.put("info", map);
		ans.put("service", recordData);
		printWriter.print(JSONObject.fromObject(ans).toString());
		printWriter.close();
		return null;
	}


	private ModelAndView getuserlist(HttpServletRequest arg0,
									 HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter=arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}

		if(user.getType()!=0){
			printWriter.print("Not a User");
			printWriter.close();
			return null;
		}

		Map<String,String> map=new HashMap<String,String>();
		map.put("name", user.getName());
		map.put("user_id", user.getUser_id());
		map.put("street", user.getStreet());
		map.put("city", user.getCity());
		map.put("country", user.getCountry());

		List<Record> list=recordService.findRecordByUserId(user_id);
		map.put("count",String.valueOf(list.size()));
		List<Map<String,String>> recordData=new ArrayList<Map<String,String>>();

		float sumPay=0;
		for(Record record:list){
			Map<String,String> m_map=new HashMap<String,String>();
			User pro=userService.findUser(record.getPro_id());
			Service ser=serviceService.getService(record.getSer_id());
			m_map.put("pro_name", pro.getName());
			m_map.put("ser_name", ser.getName());
			m_map.put("time",record.getTime().toString());
			float m_pay=record.getPay();
			sumPay+=m_pay;
			recordData.add(m_map);
		}
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format(sumPay);
		map.put("need to pay", p);

		Map<String,Object> ans=new HashMap<String,Object>();
		ans.put("info", map);
		ans.put("service", recordData);
		printWriter.print(JSONObject.fromObject(ans).toString());
		printWriter.close();
		return null;
	}

	private ModelAndView removerecord(HttpServletRequest arg0,
									  HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String id=arg0.getParameter("id");

		Record record;

		try{
			record=recordService.findRecordById(Integer.valueOf(id));
		}catch(Exception e){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}


		try{
			recordService.deleteRecord(record);
		}catch(Exception e){
			printWriter.print("Error");
			printWriter.close();
			return null;
		}

		printWriter.print("Success");
		printWriter.close();


		return null;
	}

	@SuppressWarnings("unused")
	private ModelAndView removeservice(HttpServletRequest arg0,
									   HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String ser_id=arg0.getParameter("ser_id");

		Service service;

		try{
			service=serviceService.getService(ser_id);
		}catch(Exception e){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}


		try{
			serviceService.deleteService(service);
		}catch(Exception e){
			printWriter.print("Error");
			printWriter.close();
			return null;
		}

		printWriter.print("Success");
		printWriter.close();


		return null;
	}

	private ModelAndView findservice(HttpServletRequest arg0,
									 HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String ser_id=arg0.getParameter("ser_id");
		arg1.setContentType("text/html;charset=UTF-8");

		Service service;

		try{
			service=serviceService.getService(ser_id);
		}catch(Exception e){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}

		Map<String,String> map=new HashMap<String,String>();
		map.put("ser_id",service.getSer_id());
		map.put("name", service.getName());
		map.put("pay", String.valueOf(service.getPay()));

		printWriter.print(JSONObject.fromObject(map).toString());
		printWriter.close();


		return null;
	}


	@SuppressWarnings("unused")
	private ModelAndView updateservice(HttpServletRequest arg0,
									   HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=arg1.getWriter();
		String ser_id=arg0.getParameter("ser_id");


		Service service;

		try{
			service=serviceService.getService(ser_id);
		}catch(Exception e){
			printWriter.print("Not Exist");
			printWriter.close();
			return null;
		}


		try{
			String name=arg0.getParameter("name");
			String inputer = new String( name.getBytes("ISO-8859-1") , "UTF-8");
			System.out.println(inputer);
			if(inputer!=null)
				service.setName(name);
			String pay=arg0.getParameter("pay");
			System.out.println(pay);
			if(pay!=null)
				service.setPay(Float.valueOf(pay));
			serviceService.updateService(service);
		}catch(Exception e){
			printWriter.print("Input Error");
			printWriter.close();
			return null;
		}
		printWriter.print("Success");
		printWriter.close();
		return null;
	}

	private ModelAndView addservice(HttpServletRequest arg0,
									HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub


		PrintWriter printWriter=arg1.getWriter();
		String ser_id=arg0.getParameter("ser_id");
		String name=arg0.getParameter("name");
		String pay=arg0.getParameter("pay");

		Service service=new Service();
		String inputer = new String( name.getBytes("ISO-8859-1") , "UTF-8");
		service.setName(inputer);
		service.setPay(Float.valueOf(pay));
		service.setSer_id(ser_id);

		try{
			serviceService.saveService(service);
			printWriter.print("Success");
		}catch(Exception e){
			printWriter.print("Error");
		}


		printWriter.close();
		return null;
	}


	private ModelAndView getrecordofuser(HttpServletRequest arg0,
										 HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			return null;
		}

		int type=user.getType();
		@SuppressWarnings("unused")
		List<Record> data;

		if(type==0){
			data=recordService.findRecordByUserId(user_id);
		}else if(type==1||type==2||type==3||type==4){
			data=recordService.findRecordByProId(user_id);
		}else{
			printWriter.print("Type Error");
			return null;
		}

		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(Record record:data){
			Map<String, String> map=new HashMap<String, String>();
			map.put("pro_id", record.getPro_id());
			map.put("user_id", record.getUser_id());
			map.put("ser_id", record.getSer_id());
			map.put("comment", record.getComment());
			map.put("pay", String.valueOf(record.getPay()));
			map.put("time",String.valueOf(record.getTime()));
			map.put("time_input",String.valueOf(record.getTime_input()));
			Service service=serviceService.getService(record.getSer_id());
			map.put("service_name", service.getName());

			User provider=userService.findUser(record.getPro_id());
			map.put("pro_name", provider.getName());
			map.put("pro_street", provider.getStreet());
			map.put("pro_city", provider.getCity());
			map.put("pro_country", provider.getCountry());
			list.add(map);
		}



		printWriter.print(JSONArray.fromObject(list).toString());
		printWriter.close();


		return null;
	}


	private ModelAndView getuser(HttpServletRequest arg0,
								 HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			return null;
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("type", String.valueOf(user.getType()));
		map.put("name",user.getName());
		map.put("mail", user.getMail());
		map.put("street", user.getStreet());
		map.put("country", user.getCountry());
		map.put("city", user.getCity());

		printWriter.print(JSONObject.fromObject(map).toString());
		printWriter.close();


		return null;
	}

	private ModelAndView updateuser(HttpServletRequest arg0,
									HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			return null;
		}

		try{
			String passwd=arg0.getParameter("passwd");
			if(passwd!=null)
				user.setPasswd(passwd);
		}catch(Exception e){
			printWriter.print("Passwd Error");
		}

		try{
			String type=arg0.getParameter("type");
			if(type!=null)
				user.setType(Integer.valueOf(type));
		}catch(Exception e){
			printWriter.print("Type Error");
		}

		try{
			String name=arg0.getParameter("name");
			String inputer = new String( name.getBytes("ISO-8859-1") , "UTF-8");
			if(inputer!=null)
				user.setName(inputer);
		}catch(Exception e){
			printWriter.print("Name Error");
		}

		try{
			String mail=arg0.getParameter("mail");
			if(mail!=null)
				user.setMail(mail);
		}catch(Exception e){
			printWriter.print("Mail Error");
		}

		try{
			String street=arg0.getParameter("street");
			String inputer = new String( street.getBytes("ISO-8859-1") , "UTF-8");
			if(inputer!=null)
				user.setStreet(inputer);
		}catch(Exception e){
			printWriter.print("Street Error");
		}

		try{
			String country=arg0.getParameter("country");
			String inputer = new String( country.getBytes("ISO-8859-1") , "UTF-8");
			if(inputer!=null)
				user.setCountry(inputer);
		}catch(Exception e){
			printWriter.print("Country Error");
		}

		try{
			String city=arg0.getParameter("city");
			String inputer = new String( city.getBytes("ISO-8859-1") , "UTF-8");
			if(inputer!=null)
				user.setCity(inputer);
		}catch(Exception e){
			printWriter.print("City Error");
		}

		try{
			userService.updateUser(user);
			printWriter.print("Success");
		}catch(Exception e){}

		printWriter.close();

		return null;
	}

	private ModelAndView removeuser(HttpServletRequest arg0,
									HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			return null;
		}

		userService.deleteUser(user);
		printWriter.print("Success");
		printWriter.close();

		return null;
	}

	private ModelAndView record(HttpServletRequest arg0,
								HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding("utf-8");
		arg1.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		String pro_id=arg0.getParameter("pro_id");
		String ser_id=arg0.getParameter("ser_id");
		String time_input=arg0.getParameter("time_input");
		String pay=arg0.getParameter("pay");



		Record record=new Record();
		record.setUser_id(user_id);

		try{
			User m_user=userService.findUser(user_id);
			if(m_user.getType()!=0){
				printWriter.print("输入的user id必须是会员的id");
				printWriter.close();
				return null;
			}
		}catch(Exception e){
			printWriter.print("会员id不存在");
			printWriter.close();
			return null;
		}
		try{
			User n_user=userService.findUser(pro_id);
			if(n_user.getType()!=1&&n_user.getType()!=2&&n_user.getType()!=3&&n_user.getType()!=4){
				printWriter.print("输入的pro id必须是提供者的id");
				printWriter.close();
				return null;
			}
		}catch(Exception e){
			printWriter.print("提供者id不存在");
			printWriter.close();
			return null;
		}




		record.setPro_id(pro_id);
		record.setSer_id(ser_id);
		record.setPay(Float.valueOf(pay));

		Timestamp d = new Timestamp(System.currentTimeMillis());
		record.setTime(d);


		try{
			String[] t=time_input.split("-");
			int t1=Integer.valueOf(t[0]);
			int t2=Integer.valueOf(t[1]);
			int t3=Integer.valueOf(t[2]);
			java.util.Date date = new Date(t1-1900,t2-1,t3);
			System.out.println(date.toString());
			Timestamp d2 = new Timestamp(date.getTime());
			record.setTime_input(d2);

		}catch(Exception e){
			printWriter.print("Wrong time");
			return null;
		}




		try{


			String comment=arg0.getParameter("comment");
			String inputer   = new String( comment.getBytes("ISO-8859-1") , "UTF-8");
			record.setComment(inputer);
		}catch(Exception e){}



		try{
			recordService.saveRecord(record);
			printWriter.print("Success");
		}catch(Exception e){
			printWriter.print("Error");
		}finally{
			printWriter.close();
		}



		return null;
	}

	private ModelAndView login(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		String passwd=arg0.getParameter("passwd");

		User user=userService.findUser(user_id);
		if(user==null){
			printWriter.print("Not Exist");
			return null;
		}

		if(user.getPasswd().equals(passwd)){
			printWriter.print("Success");
		}
		else{
			printWriter.print("Error");
		}
		printWriter.close();
		return null;
	}

	private ModelAndView register(HttpServletRequest arg0,
								  HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = arg1.getWriter();
		String user_id=arg0.getParameter("user_id");
		String passwd=arg0.getParameter("passwd");
		String type=arg0.getParameter("type");
		String mail=arg0.getParameter("mail");
		String name=arg0.getParameter("name");



		if(user_id.length()!=9){
			printWriter.print("The length of user id must be 9");
			printWriter.close();
			return null;
		}

		try{
			User user=userService.findUser(user_id);
			if(user!=null){
				printWriter.print("User id exists");
				printWriter.close();
				return null;
			}
		}catch(Exception e){}

		User user=new User();
		user.setUser_id(user_id);
		user.setPasswd(passwd);
		user.setType(Integer.valueOf(type));
		user.setMail(mail);

		String inputer2 = new String( name.getBytes("ISO-8859-1") , "UTF-8");
		user.setName(inputer2);


		user.setBalance(0);

		try{
			String street=arg0.getParameter("street");
			String inputer = new String( street.getBytes("ISO-8859-1") , "UTF-8");
			user.setStreet(inputer);
		}catch(Exception e){}
		try{
			String country=arg0.getParameter("country");
			String inputer = new String( country.getBytes("ISO-8859-1") , "UTF-8");
			user.setCountry(inputer);
		}catch(Exception e){}
		try{
			String city=arg0.getParameter("city");
			String inputer = new String( city.getBytes("ISO-8859-1") , "UTF-8");
			user.setCity(inputer);
		}catch(Exception e){}





		try{
			userService.saveUser(user);
			printWriter.print("Success");
		}catch(Exception e){
			printWriter.print("Error");
		}finally{
			printWriter.close();
		}



		return null;
	}


	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}



}
