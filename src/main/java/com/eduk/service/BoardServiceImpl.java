package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Board;
import com.eduk.exception.EdukException;
import com.eduk.repository.BoardRepository;

@Service 
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public void insert(Board board) {
		boardRepository.save(board);
	}

	@Override
	public List<Board> selectAll() {
		return boardRepository.findAll();
	}

	@Override
	public void delete(Long boardId) {
		boardRepository.deleteById(boardId);
	}

}
