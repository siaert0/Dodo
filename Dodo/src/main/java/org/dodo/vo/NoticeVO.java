package org.dodo.vo;

import java.util.Date;

//��������
public class NoticeVO {
	
	private int anum;//�� ��ȣ
	private String atitle; //�� ����
	private String acontents; // �� ����
	private String adate; // �ۼ���
	private String gm; // ��� ���̵� (���� : gm)
	private int total; // ��Ż
	private int page; //������
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public String getAtitle() {
		return atitle;
	}
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}
	public String getAcontents() {
		return acontents;
	}
	public void setAcontents(String acontents) {
		this.acontents = acontents;
	}
	
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public String getGm() {
		return gm;
	}
	public void setGm(String gm) {
		this.gm = gm;
	}
	
	
}
