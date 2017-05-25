package org.dodo.svc;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import org.dodo.mybatis.*;
import org.dodo.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardSVC {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	

	//����Ʈ �� ������ 
	public List<BoardListVO> getBoardList(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = dao.getBoardList(blist);
		List<BoardListVO> list = vo.getBlist();
		return list;
	}
	
	
	// �� �б�
	public BoardListVO getRead(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardListVO vo = dao.getRead(blist);
		return vo;
	}//���б�=(num)
	
	
	// �� ���� �� ����
	public ArrayList<BfileVO> write(BoardListVO blist, BfileVO fvo)
	{
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		if(!fvo.getFiles().get(0).getOriginalFilename().equals(""))
		{
			blist.setFenabled(1);
		}
		else{blist.setFenabled(0);}
		int i = dao.setWrite(blist);
		ArrayList<BfileVO> fname = null;
		if(!fvo.getFiles().get(0).getOriginalFilename().equals(""))
		{
			int num = dao.getMaxnum();
			fname = fileprocess(fvo);
			filesave(fname,num);
		}
		return fname;
	}
	
	
	// �� ���
	public String setcomment(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		String res = "";
		int tf = dao.setWrite(blist);
		if(tf==1){
			res ="����Ϸ�";
		}
		else{
			res = "�������";
		}
		return res;
	}
	
	
	// �Խ��ǿ����� �˻�
	public BoardVO getSearch(BoardListVO blist)
	{
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = dao.getSearch(blist);
		return vo;
	}
	
	
	// ��  �������� 
	public String setEdit(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		String res = "";
		int tf = dao.setEdit(blist);
		if(tf==1){
			res ="�����Ϸ�";
		}
		else{
			res = "�������";
		}
		return res;
	}
	
	
	// �� ���� 
	public String setDel(BoardListVO blist)
	{
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		String res="";
		int conf = dao.setDel(blist);
		if(conf == 1){
			res = "�����Ϸ�";
		}
		else{
			res = "������ �� ����";
		}
		
		return res;
	}
	
	
	// �� ��� ��
	public List<BoardListVO> getCommentcnt() {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = dao.getcommentcnt();
		List<BoardListVO>cntlist = vo.getClist();
		return cntlist;
	}
	
	
	// �� ��ȸ�� 
	public void setReadcnt(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		int cnt = blist.getReadcnt();
		blist.setReadcnt(cnt+1);
		dao.setReadcnt(blist);
	}
	
	
	// �� ��õ��
	public boolean setGoodcnt(GoodVO good) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		Boolean tf = getGoodidCheck(good);
		if(tf)
		{
			int cnt = good.getGoodcnt();
			good.setGoodcnt(cnt+1);
			dao.setGoodcnt(good);   //��õ������
			dao.setGoodid(good);    //Good���̺�����
			return true;
		}
		return false;
	}
	
	
	// �� ��õ�� �˻�
	public Boolean getGoodidCheck(GoodVO good)
	{
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		Boolean tf = false;
		BoardVO vo = dao.getGoodlist(good);
		
		if(vo == null){	
			return tf=true;
		}
		List<GoodVO> glist = vo.getGlist();
		for(int i=0;i<glist.size();i++){
			if(good.getNum()==glist.get(i).getNum()){
				return tf;
			}
		}
		return tf=true;
	}
	
	
	// �� ��۷ε�
	public BoardVO getRipple(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = dao.getRipple(blist);
		return vo;
	}
	
	
	// ���������� ��õ�� �ε� 
	public BoardVO getgoodList(){
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = dao.getgoodList();
		return vo;
	}
	
	
	
	//���� DB ���� 
	public void filesave(ArrayList<BfileVO> al,int num){
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		for (int i = 0; i < al.size(); i++) {
			al.get(i).setNum(num);
		}

		for (int i = 0; i < al.size(); i++) {
			dao.setFsave(al.get(i));
		}
	}
		
	
	// ��������
	private ArrayList<BfileVO> fileprocess(BfileVO fvo) {
		String res, desc = "";
		List items = null;

		Iterator<MultipartFile> itr = fvo.getFiles().iterator();
		ArrayList<BfileVO> fname = new ArrayList<BfileVO>();

		while (itr.hasNext()) {
			MultipartFile item = itr.next();
			try {
				String itemName = item.getName();// ���� �ý��� ���� ���ϰ�� �� ���� �̸� ����
				if (itemName == null || itemName.equals("")) {
					continue;
				}
				String fileName = item.getOriginalFilename();// ��ξ��� �����̸��� ������
				// ���۵� ������ ������ �����ϱ� ���� ����
				// String rootPath = getServletContext().getRealPath("/");
				String sfileName = getFname(fileName);
				File savedFile = new File("c:/Users/EKHB-PC/Desktop/test/" + sfileName);
				item.transferTo(savedFile);// ���� ��ο� ������ ������
				BfileVO fv = new BfileVO();
				fv.setFname1(fileName);
				fv.setFname2(sfileName);
				fv.setFsize(getFilesize(fileName));
				fname.add(fv);
			} catch (Exception e) {
				res = "������ ���� ������ ����: ";
			}
		}
		return fname;
	}
	
	
	//���ϰ˻� 
	private static String getFname(String orignFname) {
		File dir = new File("c:/Users/EKHB-PC/Desktop/test");
		String[] fnames = dir.list();
		String newFname = null;

		for (int i = 0; i < fnames.length; i++) {
			if (fnames[i].equals(orignFname)) {
				// ������ ������ �����ÿ� ��ġ�� �Ѵ�.
				String tmp = orignFname.split("\\.")[0];
				String ext = orignFname.split("\\.")[1];
				tmp += new Date().getTime() + "." + ext;
				newFname = tmp;
				return newFname;
			}
		}
		return orignFname;
	}

	
	//���� ������
	private static long getFilesize(String orignFname) {
		File dir = new File("c:/Users/EKHB-PC/Desktop/test/" + orignFname);
		long filesize = dir.length();
		return filesize;
	}

	
	// ���� �̸� 
	public BoardVO getBfile(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO fvo = dao.getBfile(blist);
		return fvo;
	}
	
	
	// ���������� ��õ�� �̹������ϸ� 
	public List<String> getGoodfilename(BoardVO vo) {
		List<String> flist = new ArrayList<String>();
		String fname = "";
		for (int i = 0; i < vo.getBlist().size(); i++) {
			BoardListVO blist = new BoardListVO();
			blist.setNum(vo.getBlist().get(i).getNum());
			String cat = vo.getBlist().get(i).getCat();
			BoardVO bvo = getBfile(blist);
			if (bvo == null) {
				int num = (int) ((Math.random() * 9) + 1);
				if (cat.equalsIgnoreCase("game")) {
					fname = "g/G" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("IT")) {
					fname = "i/I" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("movie")) {
					fname = "c/C" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("sports")) {
					fname = "s/S" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("music")) {
					fname = "m/M" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("fashion")) {
					fname = "f/F" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("travel")) {
					fname = "t/T" + num + ".jpg";
					flist.add(fname);
				} else if (cat.equalsIgnoreCase("love")) {
					fname = "l/L" + num + ".jpg";
					flist.add(fname);
				}
			} else if (bvo != null) {
				fname = bvo.getBflist().get(0).getFname1();
				flist.add(fname);
			}
		}
		for (int i = 0; i < flist.size(); i++) {
			System.out.println("�ֽű����ϸ� " + i + "��°:" + flist.get(i));
		}
		return flist;
	}


	// �������� 
	public BoardVO getNoticeBoard() {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		return dao.getNoticeBoard();
	}


	// �������� ����Ʈ
	public BoardVO getNotice(BoardListVO blist) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		return dao.getNotice(blist);
	}

	
	// �������� �� �б�
	public BoardVO getNoticeRead(NoticeVO nvo) {
		BoardDAO dao = sqlSessionTemplate.getMapper(BoardDAO.class);
		return dao.getNoticeRead(nvo);
	}
	
	
	// ���ɺо�(ī�װ�) �߰�
	public List<CategoryVO> setAddCat(BoardListVO blist) {
		BoardDAO bdao = sqlSessionTemplate.getMapper(BoardDAO.class);
		UserDAO udao = sqlSessionTemplate.getMapper(UserDAO.class);
		bdao.setAddCat(blist);// ���ɺо� �߰�
		List<CategoryVO> list = udao.getHobbyList(blist.getAuthor());
		return list;
	}
	
	
	// ���ɺо�(ī�װ�) ���� 
	public List<CategoryVO> setDelCat(BoardListVO blist) {
		BoardDAO bdao = sqlSessionTemplate.getMapper(BoardDAO.class);
		UserDAO udao = sqlSessionTemplate.getMapper(UserDAO.class);
		bdao.setDelCat(blist);// ���ɺо� ����
		List<CategoryVO> list = udao.getHobbyList(blist.getAuthor());
		return list;
	}


	// ����ڰ� �� �� ���� 20�� ���
	public BoardVO getMyWriteList(String id) {
		BoardDAO bdao = sqlSessionTemplate.getMapper(BoardDAO.class);
		return bdao.getMyWriteList(id);
	}
	
	// ���ɺо� ��õ�� ����Ʈ 
	public List<BoardListVO> getHobbyGoodList(String cat){
		BoardDAO bdao = sqlSessionTemplate.getMapper(BoardDAO.class);
		BoardVO vo = bdao.getHobbyGoodList(cat);
		return vo.getBlist();
	}
	
	public String getHobbyIcon(BoardListVO blist){
		String cat = blist.getCat();
		String iconName = null;
			if (cat.equalsIgnoreCase("Game")) {
				return iconName = "fa fa-gamepad";
			} else if (cat.equalsIgnoreCase("IT")) {
				return iconName = "fa fa-desktop";
			} else if (cat.equalsIgnoreCase("Movie")) {
				return iconName = "fa fa-youtube-play";
			} else if (cat.equalsIgnoreCase("Sports")) {
				return iconName = "fa fa-trophy";
			} else if (cat.equalsIgnoreCase("music")) {
				return iconName = "fa fa-music";
			} else if (cat.equalsIgnoreCase("Fashion")) {
				return iconName = "fa fa-shopping-cart";
			} else if (cat.equalsIgnoreCase("Travel")) {
				return iconName = "fa fa-plane";
			} else if (cat.equalsIgnoreCase("Love")) {
				return iconName = "fa fa-heart";
			}
			return iconName;
	}
}
