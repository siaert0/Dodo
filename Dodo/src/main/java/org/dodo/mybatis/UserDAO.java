package org.dodo.mybatis;

import java.util.*;

import org.dodo.vo.*;

public interface UserDAO {

	public int setUserSave(UserVO uvo); // 회원가입

	public UserVO getUserIdFind(UserVO uvo); // Id 찾기
	
	public int getUserPwdFind(UserVO uvo); // 비밀번호 찾기

	public UserVO getIdCheck(String id);  // ID 중복체크

	public UserVO getUserModify(String id); // 수정을 요청한 회원의 정보

	public int getUserDrawal(HashMap<String,Object> hs); // 회원탈퇴

	public UserVO getUserPwd(UserVO uvo); // encoding한 비밀번호 비교

	public int getUserModf(UserVO uvo); // 회원정보수정

	public int setNewPwdChange(UserVO uvo); // 비밀번호변경

	public UserVO getUserNum(String id); // 회원번호로드

	public List<CategoryVO> getHobbyList(String id); // 회원의 관심분야(카테고리) 로드

	public void setUserHobby(Map<String, String> map); // 회원 관심분야 저장

	

}
