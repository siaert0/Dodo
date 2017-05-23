package org.dodo.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dodo.vo.*;

public interface BoardDAO {

	public BoardVO getBoardList(BoardListVO blist); //����Ʈ+������
	
	public BoardListVO getRead(BoardListVO blist); //���б�
	
	public int getMaxnum(); //�ֽű۹�ȣ
	
	public BoardVO getBfile(BoardListVO blist); //�����̸�
	
	public BoardVO getcommentcnt(); //��ۼ�
	
	public BoardVO getGoodlist(GoodVO good); //��õ�� ����Ʈ
	
	public BoardVO getRipple(BoardListVO blist);  //��۸���Ʈ
	
	public BoardVO getSearch(BoardListVO blist); //�˻�
	
	public BoardVO getgoodList(); //�ֽű� 6��(=���ο�)

	public int setWrite(BoardListVO blist); //������+���
	
	public void setFsave(BfileVO fvo);  //��������

	public int setEdit(BoardListVO blist);  //�ۼ���

	public int setDel(BoardListVO blist);  //�ۻ���

	public void setGoodcnt(GoodVO good);  //����õ

	public void setGoodid(GoodVO good); //��õ������

	public void setReadcnt(BoardListVO blist);

	public BoardVO getNoticeBoard(); 

	public BoardVO getNotice(BoardListVO blist); //�������׸���Ʈ ��ü

	public BoardVO getNoticeRead(NoticeVO nvo); //���������б�

	public void setAddCat(BoardListVO blist); //���ɺо� �߰�

	public void setDelCat(BoardListVO blist); // ���ɺо� ����

	public BoardVO getMyWriteList(String id); // ����ڰ� �� �� ����Ʈ

}
