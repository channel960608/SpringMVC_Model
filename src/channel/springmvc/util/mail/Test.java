package channel.springmvc.util.mail;

/**
 * Created by channel on 2017/5/9.
 */
public class Test {
    public static void main(String[] args){
    	MailSenderInfo info=new MailSenderInfo();
		info.setToAddress("997476274@qq.com");
		info.setContent("test");
		info.setSubject("test");
		
		
        
        	if(SimpleMailSender.sendHtmlMail(info)){
        		System.out.print("Success");
        	}
        	else{       			
        		System.out.print("Send failed");
        	}
        	
        
        }

    }

