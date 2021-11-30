package com.eduk.app;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.eduk.domain.Board;
import com.eduk.repository.BoardRepository;



@SpringBootTest
@Transactional
@Commit
public class BoardTests {
	
	@Autowired	
	private BoardRepository boardRepository;
	
	@Test
	public void test() {
		System.out.println("Test test---------------------");
	}
	
	/**
	 * 등록하기 
	 * */
	@Test
	public void insert() {
		for (int i = 0; i <= 50; i++) {
			boardRepository.save(new Board(null, i, "게시판 " + i, null));
		}
	}
	
	/**
	 * 전체 검색
	 */
	@Test
	public void selectAll() {
		List<Board> list = boardRepository.findAll();
		System.out.println("개수 : " + list.size());
		
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	/**
	 * 삭제
	 */
	@Test
	public void delete() {
		boardRepository.deleteById((long) 22);
	}
}
