package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Board;
import com.eduk.repository.BoardRepository;

@Service 
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public List<Board> selectAll(Long channelId) {
		List<Board> boardList = boardRepository.selectByChannelId(channelId);
		
		return boardList;
	}

	@Override
	public void delete(Long boardId) {
		Board dbBoard = boardRepository.findById(boardId).orElse(null);
		if(dbBoard == null) throw new RuntimeException("게시판 번호 오류로 삭제할 수 없습니다.");
		
		boardRepository.deleteById(boardId);
	}

	@Override
	public Board selectByBoardId(Long boardId) {
		Board dbBoard = boardRepository.findById(boardId).orElse(null);
		if(dbBoard == null) throw new RuntimeException("게시판 번호 오류입니다.");
		
		return dbBoard;
	}

}
