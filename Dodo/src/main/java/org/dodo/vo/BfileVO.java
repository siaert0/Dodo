package org.dodo.vo;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//�Խ��� ����
public class BfileVO {
	private int num;// ��ȣ
	private String fname1; // ���� ���ϸ�
	private String fname2; // �ٲ� ���ϸ�
	private long fsize; // ���� ũ��
	private String fdate; // �ø� ��¥
	private List<MultipartFile> files; //��Ƽ��Ʈ����
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFname1() {
		return fname1;
	}
	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}
	public String getFname2() {
		return fname2;
	}
	public void setFname2(String fname2) {
		this.fname2 = fname2;
	}
	public long getFsize() {
		return fsize;
	}
	public void setFsize(long fsize) {
		this.fsize = fsize;
	}
	
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
}
