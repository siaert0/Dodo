package org.dodo.cont;

import javax.servlet.http.*;
import javax.validation.*;

import org.dodo.svc.*;
import org.dodo.vo.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("mc/") // ������Ʈ�ѷ� 
public class MainCont {

		@Autowired private MainSVC msvc;
		
		
		// �������������� ���� Ŭ���� 
		@RequestMapping(value = "Main/setup")
		public String goSetup(){
			return "Main/MainSetup";
		}
		
		//ȸ��Ż�� �������� �̵� 
		@RequestMapping(value = "Main/withdrawal")
		public ModelAndView getWithdrawal(HttpSession session){
			ModelAndView view = new ModelAndView();
			String id = (String) session.getAttribute("USERID");
			System.out.println("������ �� �ִ� id��:"+id);
			view.addObject("uvo", msvc.getUserNum(id));
			view.setViewName("Main/Withdrawal");
			return view;
		}
		
		//ȸ��Ż��
		@RequestMapping(value = "Main/drawal")
		@ResponseBody
		public Object getUserDrawal(UserVO uvo, BindingResult re){
			boolean result = msvc.getUserDrawal(uvo);
			JSONObject json = new JSONObject();
			System.out.println(result);
			json.put("result", result);
			return json;
			
		}
		
		
		//ȸ���������� �������� �̵�
		@RequestMapping(value = "Main/usermodify")
		public String getUserModPage(HttpSession session,Model model){
			String id = (String) session.getAttribute("USERID");
			System.out.println("���� ���̵�"+id);
			UserVO uvo = msvc.getUserModify(id);
			model.addAttribute("uvo",uvo);
			return "Main/UserModify";
		}
		
		
		//ȸ����������
		@RequestMapping(value = "Main/modf")
		@ResponseBody
		public Object getUserModify(UserVO uvo,HttpSession session){
			String id = (String)session.getAttribute("USERID");
			int result = msvc.getUserModf(uvo,id);
			System.out.println("������� ����Ʈ��"+result);
			JSONObject json = new JSONObject();
			json.put("result", result);
			return json;
		}
		
		
		//��й�ȣ ���� ��������
		@RequestMapping(value = "Main/passwordchange")
		public String getPasswordChange(){
			return "Main/PwdChange";
		}
		
		
		//��й�ȣ ����
		@RequestMapping(value = "Main/pwdchange")
		@ResponseBody
		public Object setNewPwdChange(
				@RequestParam String id,
				@RequestParam String npwd1,
				HttpSession session
				){
			String id1=(String) session.getAttribute("USERID");
			System.out.println(id1+npwd1);
			
			int result = msvc.setNewPwdChange(id1,npwd1);
			JSONObject json = new JSONObject();
			System.out.println(result);
			json.put("result", result);
			return json;
		}
		
	
}

