package org.dodo.svc;

import java.util.*;

import org.dodo.mybatis.*;
import org.dodo.vo.*;
import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

@Service
public class MainSVC {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	BCryptPasswordEncoder passwordEncoder; // ���� �������Ͽ� ������ ���
	
	// ������ ����� ���� �ε�
	public UserVO getUserModify(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		return dao.getUserModify(id);
	}


	// ȸ��Ż�� 
	public boolean getUserDrawal(UserVO uvo) {
		HashMap<String,Object> hs = new HashMap<String,Object>();
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		boolean newPwd = passwordEncoder.matches(uvo.getPwd(),getUserPwd(uvo));
		hs.put("id", uvo.getId());
		hs.put("pwd", getUserPwd(uvo));
		dao.getUserDrawal(hs);
		return newPwd;
	}
	

	// ��й�ȣ �񱳸� ���� �޼ҵ�
	public String getUserPwd(UserVO uvo) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		UserVO reUvo = dao.getUserPwd(uvo);
		if(reUvo == null){
			throw new NullPointerException("NULL");
		}
		return reUvo.getPwd();
	}

	
	// ȸ����������
	public int getUserModf(UserVO uvo,String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		uvo.setId(id);
		return dao.getUserModf(uvo);
	}


	// ��й�ȣ���� 
	public int setNewPwdChange(String id, String npwd1) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		String newPwd = passwordEncoder.encode(npwd1);
		System.out.println(newPwd);
		UserVO uvo = new UserVO();
		uvo.setPwd(newPwd);
		uvo.setId(id);
		return dao.setNewPwdChange(uvo);
	}

	
    // ȸ���� ��ȣ �ε�
	public UserVO getUserNum(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		UserVO uvo = dao.getUserNum(id);
		return uvo;
	}
	
	

}
