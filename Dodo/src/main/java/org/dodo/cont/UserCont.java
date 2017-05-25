package org.dodo.cont;

import java.security.*;
import java.util.*;

import javax.servlet.http.*;

import org.dodo.svc.*;
import org.dodo.vo.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("uc/") // ������Ʈ�ѷ� 
public class UserCont {
	
	@Autowired
	private UserSVC usvc;
	@Autowired
	private BoardSVC bsvc;
	
	
	// �ε������� �α��� Ŭ����
	@RequestMapping(value = "User/loginform", method = RequestMethod.GET)
	public String setGoLogin(){
		System.out.println("�α��� �� ���ư�");
		return "User/LoginForm";
	}
	
	// �α��������� ȸ������������
	@RequestMapping(value = "User/userjoin", method = RequestMethod.GET)
	public String setUserJoin(){
		return "User/UserJoin";
	}
	
	
	// ����������
	@RequestMapping(value = "Main/main")
	public String setGoMainList(Model model,HttpSession session,Authentication auth) {
		BoardVO vo = bsvc.getgoodList();//�ֽű�6��
		BoardVO bvo = bsvc.getNoticeBoard();
		List<NoticeVO> nList = bvo.getNlist();
		session.setAttribute("glist", vo.getBlist());
		session.setAttribute("flist", bsvc.getGoodfilename(vo)); //�����̸�6��
		session.setAttribute("nList", nList);
		if(auth!=null){
		String id = auth.getName();
		session.setAttribute("USERID",id);
		List<CategoryVO> cList = usvc.getHobbyList(id);
		session.setAttribute("cList", cList);
		return "Main/main";
		}
		return "Main/main";
	}
	
	
	// ���� ȸ������
	@RequestMapping(value = "User/usave")
	@ResponseBody
	public Object setUserSave(UserVO user){
		boolean result = usvc.setUserSave(user);
		usvc.setUserHobby(user);
		JSONObject json = new JSONObject();
		json.put("save", result);
		return json.toJSONString();
	}
	
	// �α׾ƿ�
	@RequestMapping(value = "User/logout")
	public String isUserLogout(HttpSession session) {
		System.out.println("���� �α׾ƿ� ���ư�!");
		String id = (String) session.getAttribute("USERID");
		System.out.println("������ ����"+id);
		session.removeAttribute("USERID");
		return "User/LoginForm";
	}
	
	//id/pwdã�� ������ �̵�
	@RequestMapping(value = "User/infofind")
	public String setUserInfoFind() {
		return "User/UserInfoFind";
	}
	
	
	//IDã��
	@RequestMapping(value="User/idfind" ,method=RequestMethod.POST)
	@ResponseBody
	public Object getUserIdFind(UserVO uvo) {
		String getId = null;
		UserVO reUvo = usvc.getUserIdFind(uvo);
		getId = reUvo.getId();
		JSONObject json = new JSONObject();
		json.put("get", getId);
		return json;
	}
	
	
	//Pwdã��
	@RequestMapping(value="User/pwdfind" ,method=RequestMethod.POST)
	@ResponseBody
	public Object getUserPwdFind(UserVO uvo) throws Exception {
		int result = usvc.getUserPwdFind(uvo);
		JSONObject json = new JSONObject();
		json.put("pwd", result);
		return json;
	}
	
	
	//id �ߺ� üũ
	@RequestMapping(value="User/idcheck" ,method=RequestMethod.POST)
	@ResponseBody
	public Object getIdCheck(@RequestParam String id){
		System.out.println("ID üũ�� "+id);
		UserVO result = usvc.getIdCheck(id);
		JSONObject json = new JSONObject();
		json.put("idc", result.getId());
		return json;
	}
	
		
}
