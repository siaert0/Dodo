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
@RequestMapping("bc/") // 게시판 컨트롤러
public class BoardCont {

	@Autowired
	private BoardSVC bsvc;

	
	// 리스트 출력
	@RequestMapping(value = "main/boardList", method = RequestMethod.GET)
	public String getBoardList(BoardListVO blist, HttpSession session, Model model) {
		List<BoardListVO> list = bsvc.getBoardList(blist);
		List<BoardListVO> cntlist = bsvc.getCommentcnt(); // 댓글수

		session.setAttribute("comment", cntlist);
		session.setAttribute("list", list);
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());

		return "Board/BoardList";
	}

	
	@RequestMapping(value = "main/boardList", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getPage(BoardListVO blist, HttpSession session) {
		List<BoardListVO> list = bsvc.getBoardList(blist);
		List<BoardListVO> cntlist = bsvc.getCommentcnt();
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		JSONObject jsonCist = new JSONObject();
		JSONObject jsonCntlist = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		jsonCist.put("list", list);
		jsonCntlist.put("commend", cntlist);
		jsonArr.add(jsonCist);
		jsonArr.add(jsonCntlist);
		return jsonArr;
	}

	
	// 글 읽기
	@RequestMapping(value = "main/read", method = RequestMethod.GET)
	public String getRead(BoardListVO blist, HttpSession session) {
		System.out.println("조회수: " + blist.getReadcnt());
		bsvc.setReadcnt(blist); // 조회수 저장
		BoardListVO vo = bsvc.getRead(blist); // 글불러오기
		BoardVO bvo = bsvc.getBfile(blist); // 파일불러오기
		BoardVO rvo = bsvc.getRipple(blist); // 댓글불러오기
		List<BoardListVO> clist = bsvc.getCommentcnt(); // 댓글수불러오기
		session.setAttribute("fname", bvo);
		session.setAttribute("read", vo);
		session.setAttribute("ripple", rvo);
		session.setAttribute("comment", clist);
		return "Board/BoardRead";
	}

	
	//검색
	@RequestMapping(value = "main/search", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray search(BoardListVO blist, HttpSession session) {
		System.out.println("검색내용 :" + blist.getSearchI() + "검색카테고리" + blist.getSearchS() + "페이지" + blist.getPage());
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

	
	//글 작성
	@RequestMapping(value = "main/write", method = RequestMethod.GET)
	public String writeview() {
		return "Board/BoardWrite";
	}

	
	//댓글
	@RequestMapping(value = "main/comment", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String comment(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		System.out.println(blist.getAuthor());
		String res = bsvc.setcomment(blist);
		return res;
	}

	
	// 글쓰고 저장
	@RequestMapping(value = "main/write", method = RequestMethod.POST)
	public String write(BoardListVO blist, HttpSession session, BfileVO fvo) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		ArrayList<BfileVO> res = bsvc.write(blist, fvo);
		return "redirect:boardList?page=1&cat="+blist.getCat();
	}

	
	// 글수정페이지로 이동
	@RequestMapping(value = "main/edit", method = RequestMethod.GET)
	public String getEditPage(BoardListVO blist, HttpSession session) {
		BoardListVO bv = bsvc.getRead(blist);
		session.setAttribute("read", bv);
		return "Board/BoardEdit";
	}

	
	// 수정사항 저장
	@RequestMapping(value = "main/edit", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String setEdit(BoardListVO blist, HttpSession session) {
		String res = bsvc.setEdit(blist);
		return res;
	}

	
	// 글 삭제
	@RequestMapping(value = "main/del", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String del(BoardListVO blist) {
		String res = bsvc.setDel(blist);
		return res;
	}

	
	//추천
	@RequestMapping(value = "main/goodcnt", method = RequestMethod.POST)
	@ResponseBody
	public boolean setGoodcnt(GoodVO good, HttpSession session) {
		good.setGoodid((String) session.getAttribute("USERID"));
		boolean tf = bsvc.setGoodcnt(good); // 추천인 검사결과 리턴
		return tf;
	}

	
	//다운로드
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
	
	
	//공지사항 리스트
	@RequestMapping(value = "main/notice", method = RequestMethod.GET)
	public String getNoticeList(BoardListVO blist, HttpSession session) {
		List<NoticeVO> list = bsvc.getNotice(blist).getNlist();
		session.setAttribute("list", list);
		session.setAttribute("total", list.get(0).getTotal());
		session.setAttribute("page", list.get(0).getPage());
		return "Board/NoticeList";
	}
	
	
	//공지사항 페이지
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

	
	// 공지사항 읽기
	@RequestMapping(value = "main/nread", method = RequestMethod.GET)
	public String getNoticeRead(NoticeVO nvo, HttpSession session) {
		NoticeVO vo = bsvc.getNoticeRead(nvo).getNlist().get(0); // 글불러오기
		session.setAttribute("read", vo);
		return "Board/NoticeRead";
	}

	
	//(관심분야)카테고리 추가
	@RequestMapping(value = "main/addcat", method = RequestMethod.GET)
	public String setAddCat(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		List<CategoryVO> list = bsvc.setAddCat(blist); // 카테고리 저장+유저 카테고리리스트
		session.setAttribute("cList", list);
		return "redirect:boardList?page=1&cat=" + blist.getCat();
	}

	
    //(관심분야)카테고리 삭제 
	@RequestMapping(value = "main/delcat", method = RequestMethod.GET)
	public String setDelCat(BoardListVO blist, HttpSession session) {
		blist.setAuthor((String) session.getAttribute("USERID"));
		List<CategoryVO> list = bsvc.setDelCat(blist); // 카테고리 삭제+유저카테고리리스트
		session.setAttribute("cList", list);
		return "redirect:boardList?page=1&cat=" + blist.getCat();
	}

	
	// 사이드바 검색
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
	
	
	// 내가 쓴 글 리스트
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
