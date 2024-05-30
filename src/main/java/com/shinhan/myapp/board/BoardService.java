package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// Service : ë¹„ì¦ˆ?‹ˆ?Š¤ ë¡œì§ ?‘?„±
@Service("bService2")	// @Service = @Component + ?„œë¹„ìŠ¤?—­?• 
public class BoardService {
	
	//BoardDAO boardDao = new BoardDAO();
	// Autowired
	// ****2.Àû¿ëÇÏ±â : @Autowired
	@Autowired
	// »ı·«°¡´É @Qualifier("bDAO")
	BoardDAO boardDao;
	
	public int deleteBoard(int bno) {
		return boardDao.deleteBoard(bno);
	}
	
	public int updateBoard(BoardDTO board) {
		return boardDao.updateBoard(board);
	}
	
	public int insertBoard(BoardDTO board) {
		return boardDao.insertBoard(board);
	}
	
	public BoardDTO selectById(int bno) {
		return boardDao.selectById(bno);
	}
	
	public List<BoardDTO> selectAll() {
		return boardDao.selectAll();
	}

	public int deleteBoardArray(Integer[] checkBno) {
		return boardDao.deleteBoardArray(checkBno); 
		
	}
}
