package org.dodo.cont;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import org.dodo.svc.*;
import org.dodo.vo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("bc/") // �Խ��� ��Ʈ�ѷ�
public class BoardCont {

	@Autowired
	private BoardSVC bsvc;

	
	// ����Ʈ ���
	@RequestMapping(value = "main/boardList", method = RequestMethod.GET)
	public String getBoardList(
			BoardListVO blist, HttpSession session, Model model) {
		List<BoardListVO> hobby_good_List = bsvc.getHobbyGoodList(blist.getCat());
		List<BoardListVO> list = bsvc.getBoardList(blist);
		List<BoardListVO> cntlist = bsvc.getCommentcnt(); 
		session.setAttribute("comment", cntlist);
		session.setAttribute("list", list);
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		model.addAttribute("bestList", hobby_good_List);
		model.addAttribute("icon",bsvc.getHobbyIcon(blist));
		return "Board/BoardList";
	}

	
	@RequestMapping(value = "main/boardList", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getPage(BoardListVO blist, HttpSession session,@RequestParam String cat,
			@RequestParam int page,Model model) {
		List<BoardListVO> hobby_good_List = bsvc.getHobbyGoodList(blist.getCat());
		List<BoardListVO> list = bsvc.getBoardList(blist);
		List<BoardListVO> cntlist = bsvc.getCommentcnt();
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		model.addAttribute("bestList", hobby_good_List);
		JSONObject jsonCist = new JSONObject();
		JSONObject jsonCntlist = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		jsonCist.put("list", list);
		jsonCntlist.put("commend", cntlist);
		jsonArr.add(jsonCist);
		jsonArr.add(jsonCntlist);
		return jsonArr;
	}

	
	// �� �б�
	@RequestMapping(value = "main/read", method = RequestMethod.GET)
	public String getRead(BoardListVO blist, HttpSession session) {
		System.out.println("��ȸ��: " + blist.getReadcnt());
		bsvc.setReadcnt(blist); // ��ȸ�� ����
		BoardListVO vo = bsvc.getRead(blist); // �ۺҷ�����
		BoardVO bvo = bsvc.getBfile(blist); // ���Ϻҷ�����
		BoardVO rvo = bsvc.getRipple(blist); // ��ۺҷ�����
		List<BoardListVO> clist = bsvc.getCommentcnt(); // ��ۼ��ҷ�����
		session.setAttribute("fname", bvo);
		session.setAttribute("read", vo);
		session.setAttribute("ripple", rvo);
		session.setAttribute("comment", clist);
		return "Board/BoardRead";
	}

	
	//�˻�
	@RequestMapping(value = "main/search", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray search(BoardListVO blist, HttpSession session) {
		System.out.println("�˻����� :" + blist.getSearchI() + "�˻�ī�װ�" + blist.getSearchS() + "������" + blist.getPage());
		BoardVO vo = bsvc.getSearch(blist);
		List<BoardListVO> list = vo.getBlist();
		List<BoardListVO> cntlist = bsvc.getCommentcnt();
		JSONObject jsonCist = new JSONObject();
		JSONObject jsonCntlist = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		jsonCist.put("list", list);
		jsonCntlist.put("commend", cntlist);
		jsonArr.add(jsonCist);
		jsonArr.add(jsonCntlist);
		return jsonArr;
	}

	
	//�� �ۼ�
	@RequestMapping(value = "main/write", method = RequestMethod.GET)
	public String writeview() {
		return "Board/BoardWrite";
	}

	
	//���
	@RequestMapping(value = "main/comment", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String comment(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		System.out.println(blist.getAuthor());
		String res = bsvc.setcomment(blist);
		return res;
	}

	
	// �۾��� ����
	@RequestMapping(value = "main/write", method = RequestMethod.POST)
	public String write(BoardListVO blist, HttpSession session, BfileVO fvo) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		ArrayList<BfileVO> res = bsvc.write(blist, fvo);
		return "redirect:boardList?page=1&cat="+blist.getCat();
	}

	
	// �ۼ����������� �̵�
	@RequestMapping(value = "main/edit", method = RequestMethod.GET)
	public String getEditPage(BoardListVO blist, HttpSession session) {
		BoardListVO bv = bsvc.getRead(blist);
		session.setAttribute("read", bv);
		return "Board/BoardEdit";
	}

	
	// �������� ����
	@RequestMapping(value = "main/edit", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String setEdit(BoardListVO blist, HttpSession session) {
		String res = bsvc.setEdit(blist);
		return res;
	}

	
	// �� ����
	@RequestMapping(value = "main/del", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String del(BoardListVO blist) {
		String res = bsvc.setDel(blist);
		return res;
	}

	
	//��õ
	@RequestMapping(value = "main/goodcnt", method = RequestMethod.POST)
	@ResponseBody
	public boolean setGoodcnt(GoodVO good, HttpSession session) {
		good.setGoodid((String) session.getAttribute("USERID"));
		boolean tf = bsvc.setGoodcnt(good); // ��õ�� �˻��� ����
		return tf;
	}

	
	//�ٿ�ε�
	@RequestMapping("main/download")
	@ResponseBody
	public byte[] getImage(HttpServletResponse response, @RequestParam String filename) throws IOException {
		File file = new File("c:/Users/EKHB-PC/Desktop/test/" + filename);
		byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);
		String fn = new String(file.getName().getBytes(), "iso_8859_1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
		response.setContentLength(bytes.length);
		response.setContentType("image/jpeg");

		return bytes;
	}
	
	
	//�������� ����Ʈ
	@RequestMapping(value = "main/notice", method = RequestMethod.GET)
	public String getNoticeList(BoardListVO blist, HttpSession session) {
		List<NoticeVO> list = bsvc.getNotice(blist).getNlist();
		session.setAttribute("list", list);
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		return "Board/NoticeList";
	}
	
	
	//�������� ������
	@RequestMapping(value = "main/notice", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getNoticepage(BoardListVO blist, HttpSession session) {
		List<NoticeVO> list = bsvc.getNotice(blist).getNlist();
		JSONObject jlist = new JSONObject();
		jlist.put("list", list);
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		return jlist;
	}

	
	// �������� �б�
	@RequestMapping(value = "main/nread", method = RequestMethod.GET)
	public String getNoticeRead(NoticeVO nvo, HttpSession session) {
		NoticeVO vo = bsvc.getNoticeRead(nvo).getNlist().get(0); // �ۺҷ�����
		session.setAttribute("read", vo);
		return "Board/NoticeRead";
	}

	
	//(���ɺо�)ī�װ� �߰�
	@RequestMapping(value = "main/addcat", method = RequestMethod.GET)
	public String setAddCat(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		List<CategoryVO> list = bsvc.setAddCat(blist); // ī�װ� ����+���� ī�װ�����Ʈ
		session.setAttribute("cList", list);
		return "redirect:boardList?page=1&cat=" + blist.getCat();
	}

	
    //(���ɺо�)ī�װ� ���� 
	@RequestMapping(value = "main/delcat", method = RequestMethod.GET)
	public String setDelCat(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		List<CategoryVO> list = bsvc.setDelCat(blist); // ī�װ� ����+����ī�װ�����Ʈ
		session.setAttribute("cList", list);
		return "redirect:boardList?page=1&cat=" + blist.getCat();
	}

	
	// ���̵�� �˻�
	@RequestMapping(value = "main/ssearch", method = RequestMethod.POST)
    public String sideSearch(BoardListVO blist,HttpSession session){
        String url = "Board/BoardList";
        BoardVO vo =bsvc.getSearch(blist);
        if(vo == null){return url="Board/ListSearchNull";}
        else{
        session.setAttribute("list", vo.getBlist());
        session.setAttribute("commend", bsvc.getCommentcnt());}
         return url;
     }
	
	
	// ���� �� �� ����Ʈ
	@RequestMapping(value="main/writeList", method = RequestMethod.GET)
	public ModelAndView getMyWriteList(@RequestParam String id){
		BoardVO vo = bsvc.getMyWriteList(id);
		ModelAndView mav = new ModelAndView();
		System.out.println(vo.getBlist().get(0).getAuthor());
		mav.addObject("wList", vo.getBlist());
		
		mav.setViewName("Board/MyWriteList");
		return mav;
	}
}
