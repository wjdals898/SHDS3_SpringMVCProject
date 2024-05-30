package com.shinhan.myapp.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.emp.EmpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.board.BoardDTO;
import com.shinhan.myapp.board.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	//@Qualifier("bService2")
	BoardService bService;
	
	@RequestMapping("/selectAll.do")
	public String test1(Model model, HttpServletRequest request) {
		System.out.println("/board/selectAll.do요청");
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String message = "";
		if(flashMap != null) {
			message = (String)flashMap.get("resultMessage");
		}
		
		List<BoardDTO> blist = bService.selectAll();
		model.addAttribute("blist", blist);
		model.addAttribute("resultMessage", message);
		return "board/boardList"; // forward됨 => 접두사 + board/boardList + 접미사
	}
	
	@GetMapping("/boardInsert.do")
	public void test2() {
		// forward : 접두사 + board/boardInsert + 접미사
	}
	
	@PostMapping("/boardInsert.do")	
	public String test3(MultipartHttpServletRequest multipart, HttpSession session) throws UnsupportedEncodingException {
		BoardDTO board = new BoardDTO();
		HttpServletRequest request = (HttpServletRequest) multipart;
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		EmpDTO emp = (EmpDTO)session.getAttribute("emp");
		String writer = null;
		if(emp == null) {
			writer = "손님";
		} else {
			writer = emp.getFirst_name()+" "+emp.getLast_name();
		}
		board.setWriter(writer);
		
		List<MultipartFile> fileList = multipart.getFiles("pic");
		String path = session.getServletContext().getRealPath("./resources/uploads");
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		long time = System.currentTimeMillis();
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); //
			String saveFileName = String.format("%d_%s", time, originFileName);
			board.setPic(saveFileName);
			try {
				// upload하기
				mf.transferTo(new File(path, saveFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("board:" + board);
		bService.insertBoard(board);
		// forward:요청을 위임
		// redirect:재요청
		return "redirect:selectAll.do";
	}
	
	//@PostMapping("/boardInsert.do")
	//public String test3(String title, String content, String pic) {
	/*
	 * public String test3(BoardDTO board, RedirectAttributes attr, HttpSession
	 * session) { // BoardDTO board = new BoardDTO(); //
	 * board.settitle(request.getParameter("title")); System.out.println(board);
	 * EmpDTO emp = (EmpDTO)session.getAttribute("emp"); String writer =
	 * emp.getFirst_name()+" "+emp.getLast_name(); board.setWriter(writer);
	 * //BoardDTO board = new BoardDTO(0,title, content, "홍길동", pic, null); int
	 * result = bService.insertBoard(board); String message; if(result>0) { message
	 * = "insert success"; } else { message = "insert fail"; }
	 * attr.addFlashAttribute("resultMessage", message);
	 * 
	 * // forward: 요청을 위임 // redirect: 재요청 return "redirect:selectAll.do"; //
	 * response.sendRedirect("selectAll.do"); }
	 */
	
	@GetMapping("/boardDetail.do")
	public void detail(Integer bno, Model model) {
		BoardDTO board = bService.selectById(bno);
		model.addAttribute("board", board);
		//return "board/boardDetail";
	}
	
	@PostMapping("/boardDetail.do")
	public String update(BoardDTO board, RedirectAttributes attr) {
		int result = bService.updateBoard(board);
		String message;
		if(result>0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:selectAll.do";
	}
	
	//@RequestMapping(value="/boardDelete.do", method=RequestMethod.GET)
	@GetMapping("/boardDelete.do")
	public String delete(Integer bno, RedirectAttributes attr) {
		System.out.println("/board/boardDelete.do요청");
		int result = bService.deleteBoard(bno);
		String message;
		if(result>0) {
			message = "delete success";
		} else {
			message = "delete fail";
		}
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:selectAll.do";
	}
	
	@GetMapping("/selectDelete.do")
	public String selectDelete(Integer[] checkBno) {
		System.out.println(Arrays.toString(checkBno));
		
		int result = bService.deleteBoardArray(checkBno);
		return "redirect:selectAll.do";
	}
}




