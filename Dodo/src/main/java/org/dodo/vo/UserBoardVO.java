package org.dodo.vo;

//����
public class UserBoardVO {
	private int num; // �� ��ȣ
	private String masterid; // ���� ���� ���̵� (�α��ε� ���̵�)
	private String author; // �� �ۼ��� = id (�� ���̵� ���� , �� ���Ͽ� ���� ���� ���̵�)
	private String title; // ����
	private String bdate; // �� �ۼ���
	private int uref; // ref
	private int secretnum; // ��б� ��ȣ
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMasterid() {
		return masterid;
	}
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getUref() {
		return uref;
	}
	public void setUref(int uref) {
		this.uref = uref;
	}
	public int getSecretnum() {
		return secretnum;
	}
	public void setSecretnum(int secretnum) {
		this.secretnum = secretnum;
	}
}
