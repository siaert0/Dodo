package org.dodo.mybatis;

import java.util.*;

import org.dodo.vo.*;

public interface UserDAO {

	public int setUserSave(UserVO uvo); // ȸ������

	public UserVO getUserIdFind(UserVO uvo); // Id ã��
	
	public int getUserPwdFind(UserVO uvo); // ��й�ȣ ã��

	public UserVO getIdCheck(String id);  // ID �ߺ�üũ

	public UserVO getUserModify(String id); // ������ ��û�� ȸ���� ����

	public int getUserDrawal(HashMap<String,Object> hs); // ȸ��Ż��

	public UserVO getUserPwd(UserVO uvo); // encoding�� ��й�ȣ ��

	public int getUserModf(UserVO uvo); // ȸ����������

	public int setNewPwdChange(UserVO uvo); // ��й�ȣ����

	public UserVO getUserNum(String id); // ȸ����ȣ�ε�

	public List<CategoryVO> getHobbyList(String id); // ȸ���� ���ɺо�(ī�װ�) �ε�

	public void setUserHobby(Map<String, String> map); // ȸ�� ���ɺо� ����

	

}
