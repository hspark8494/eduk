package com.eduk.app;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.eduk.domain.Post;
import com.eduk.repository.PostRepository;

@SpringBootTest
@Transactional
@Commit
public class PostTest {
	
	@Autowired
	private PostRepository postRepository;
	
	/**
	 * 등록
	 */
	@Test
	public void insert() {
		for (int i = 0; i <= 5; i++) {
			//postRepository.save(new Post(null, "도와주세요~! "+i, "어려웡ㅇ", null, null, i, null, null));
		}
	}
}
