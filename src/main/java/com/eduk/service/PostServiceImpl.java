package com.eduk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eduk.domain.Post;
import com.eduk.repository.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Post insert(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Page<Post> selectAllById(Pageable pageable, Long boardId) {
		Page<Post> postList = postRepository.selectByBoardId(pageable, boardId);
		
		return postList;
	}

	@Override
	public Post update(Post post, Long postId) {
		Post dbPost = postRepository.findById(postId).orElse(null);
		if(dbPost == null) throw new RuntimeException("게시물 번호 오류로 수정할 수 없습니다.");
		
		dbPost.setPostTitle(post.getPostTitle());
		dbPost.setPostContent(post.getPostContent());
		
		return dbPost;
	}

	@Override
	public void delete(Long postId) {
		Post dbPost = postRepository.findById(postId).orElse(null);
		if(dbPost == null) throw new RuntimeException("게시물 번호 오류로 삭제할 수 없습니다.");
		
		postRepository.deleteById(postId);
	}

	@Override
	public Post selectByPostId(Long postId, boolean state) {
		if(state) {
			if(postRepository.readnumUpdate(postId) == 0 ) {
				throw new RuntimeException("게시물 번호 오류로 조회수 증가에 실패하였습니다.");
			}
		}
		
		Post dbPost = postRepository.findById(postId).orElse(null);
		if(dbPost == null) throw new RuntimeException("게시물 번호 오류입니다.");
		
		return dbPost;
	}

}
