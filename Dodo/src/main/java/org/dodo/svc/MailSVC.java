package org.dodo.svc;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.dodo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;



@Service("mailService")
public class MailSVC {

     
    @Autowired
    protected JavaMailSender mailSender;

    
    public boolean sendMail(UserVO uvo,String newPwd) throws Exception {
        try{
	        MimeMessage msg = mailSender.createMimeMessage();
	        InternetAddress addr = new InternetAddress("siaert00@gmail.com");
	        msg.setFrom(addr); // �۽��ڸ� �����ص� �ҿ������ ������ ������ �߻��Ѵ�

	        //msg.setFrom("someone@paran.com");
	        msg.setSubject("Dodo �ӽ� ��й�ȣ �Դϴ�.");



	        // �Ϲ� �ؽ�Ʈ�� �����Ϸ��� ���
	        msg.setText("�ӽú�й�ȣ�� "+newPwd+" �Դϴ�. ��� �� �ݵ�� ��й�ȣ�� ������ �ּ���." );  



	        //HTML �������� �����Ϸ���. http://micropilot.tistory.com/category/Java%20Mail/HTML%20Contents
	       /* msg.setContent("<a href='http://192.168.0.112:8081/SpringWeb/int/login'>�α��� ����</a>", "text/html;charset=utf-8");*/
	        msg.setRecipient(RecipientType.TO , new InternetAddress(uvo.getEmail()));

	         
	        mailSender.send(msg);
	        return true;

        }catch(Exception ex) {
        	ex.printStackTrace();
        }
        return false;
    }
}



