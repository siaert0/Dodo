package org.dodo.svc;

import java.io.File;
import java.util.*;


import org.dodo.mybatis.*;
import org.dodo.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserSVC {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	BCryptPasswordEncoder passwordEncoder; // ���� �������Ͽ� ������ ���
	@Autowired
    private MailSVC mailService;          // ���Ϻ�����

	
	// ȸ������
	public boolean setUserSave(UserVO uvo) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		String pwd = uvo.getPwd();
		String encodedPwd = passwordEncoder.encode(pwd);
		uvo.setPwd(encodedPwd);
		int result = dao.setUserSave(uvo);
		if(result == 1){
			return true;
		}
		else{
			return false;
		}
	}

	
	//ID ã�� 
	public UserVO getUserIdFind(UserVO uvo) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		return dao.getUserIdFind(uvo);
	}

	// PWD ã�� 
	public int getUserPwdFind(UserVO uvo) throws Exception {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		char j = (char)((Math.random()*26)+65);
		int i = (int)(Math.random()*100000);
		String s = String.valueOf(i);
		String newPwd = j+s;
		String encodedPwd = passwordEncoder.encode(newPwd);
		uvo.setPwd(encodedPwd);
		int result = dao.getUserPwdFind(uvo);
		if(result == 1){
			mailService.sendMail(uvo,newPwd);
			return result;
		}
		return result;
	}

	
	//ȸ������ ������ ���̵� üũ
	public UserVO getIdCheck(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		UserVO result = dao.getIdCheck(id);
		return result;
	}

	
	// ���ɺо� ��������
	public List<CategoryVO> getHobbyList(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		return dao.getHobbyList(id);
	}
	
	
	// ���ɺо� ����
	public void setUserHobby(UserVO user){
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		String id = user.getId();
		String[] token = user.getHobby().split("\\,");
		for(int i=0; i<token.length; i++){
			Map<String,String> map = new HashMap<String,String>();
			String hobby = token[i];
			map.put("hobby", hobby);
			map.put("id", id);
			dao.setUserHobby(map);
		}
	}

}
