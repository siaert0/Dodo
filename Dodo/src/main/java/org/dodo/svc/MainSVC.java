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
	BCryptPasswordEncoder passwordEncoder; // 서블릿 설정파일에 빈으로 등록
	
	// 수정할 대상의 정보 로드
	public UserVO getUserModify(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		return dao.getUserModify(id);
	}


	// 회원탈퇴 
	public boolean getUserDrawal(UserVO uvo) {
		HashMap<String,Object> hs = new HashMap<String,Object>();
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		boolean newPwd = passwordEncoder.matches(uvo.getPwd(),getUserPwd(uvo));
		hs.put("id", uvo.getId());
		hs.put("pwd", getUserPwd(uvo));
		dao.getUserDrawal(hs);
		return newPwd;
	}
	

	// 비밀번호 비교를 위한 메소드
	public String getUserPwd(UserVO uvo) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		UserVO reUvo = dao.getUserPwd(uvo);
		if(reUvo == null){
			throw new NullPointerException("NULL");
		}
		return reUvo.getPwd();
	}

	
	// 회원정보수정
	public int getUserModf(UserVO uvo,String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		uvo.setId(id);
		return dao.getUserModf(uvo);
	}


	// 비밀번호변경 
	public int setNewPwdChange(String id, String npwd1) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		String newPwd = passwordEncoder.encode(npwd1);
		System.out.println(newPwd);
		UserVO uvo = new UserVO();
		uvo.setPwd(newPwd);
		uvo.setId(id);
		return dao.setNewPwdChange(uvo);
	}

	
    // 회원의 번호 로드
	public UserVO getUserNum(String id) {
		UserDAO dao = sqlSessionTemplate.getMapper(UserDAO.class);
		UserVO uvo = dao.getUserNum(id);
		return uvo;
	}
	
	

}
