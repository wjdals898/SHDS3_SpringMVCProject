package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// Service : 비즈?��?�� 로직 ?��?��
@Service("bService2")	// @Service = @Component + ?��비스?��?��
public class BoardService {
	
	//BoardDAO boardDao = new BoardDAO();
	// Autowired
	// ****2.�����ϱ� : @Autowired
	@Autowired
	// �������� @Qualifier("bDAO")
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
