package org.dodo.vo;

import java.util.Date;

public class BoardListVO {
	private int num; //�۹�ȣ
	private String title; //�� ����
	private String contents; // �� ����
	private String author; // �� �ۼ��� (�ۼ��� ���̵�)
	private String bdate; // �ۼ���¥
	private int goodcnt; // ��õ��
	private int ref; // ref
	private String cat; // ���ɺо� ī�װ�
	private int cnt; //��ۼ�
	private int readcnt; //��ȸ��
	private int fenabled;  //����÷������
	private int total;//��ü������
	private int page;//����������
	private String searchI; //�˻��Է°�
	private String searchS; //�˻�ī�װ���
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getGoodcnt() {
		return goodcnt;
	}
	public void setGoodcnt(int goodcnt) {
		this.goodcnt = goodcnt;
	}
	
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public int getFenabled() {
		return fenabled;
	}
	public void setFenabled(int fenabled) {
		this.fenabled = fenabled;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
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
	public String getSearchI() {
		return searchI;
	}
	public void setSearchI(String searchI) {
		this.searchI = searchI;
	}
	public String getSearchS() {
		return searchS;
	}
	public void setSearchS(String searchS) {
		this.searchS = searchS;
	}
	
	
}
