package org.dodo.vo;

import java.sql.Date;
import java.util.List;

public class UserVO {

	private int num;
	private String id;  // ���̵�
	private String pwd;  //��й�ȣ
	private String name; //�̸�
	private String email; //�̸���
	private String que; //����
	private String ans; // ���� ��
	private String hobby; //���ɺо�
	private String phone; //����ó
	private String myself; // �ڱ�Ұ�
	private String regdate; //������
	private String authority; // ����
	private int enabled;
	private List<CategoryVO> cList;  //ī�װ� ����Ʈ
	private List<UserBoardVO> ubList; // ���� ����Ʈ
	private List<UListVO> uList;
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQue() {
		return que;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMyself() {
		return myself;
	}
	public void setMyself(String myself) {
		this.myself = myself;
	}
	
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public List<CategoryVO> getcList() {
		return cList;
	}
	public void setcList(List<CategoryVO> cList) {
		this.cList = cList;
	}

	public List<UserBoardVO> getUbList() {
		return ubList;
	}
	public void setUbList(List<UserBoardVO> ubList) {
		this.ubList = ubList;
	}
	public List<UListVO> getuList() {
		return uList;
	}
	public void setuList(List<UListVO> uList) {
		this.uList = uList;
	}
	
	
	
}
