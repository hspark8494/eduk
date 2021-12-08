package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Reply;
import com.eduk.repository.ReplyRepository;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Override
	public Reply insert(Reply reply) {
		return replyRepository.save(reply);
	}

	@Override
	public Reply update(Reply reply) {
		Reply dbReply = replyRepository.findById(reply.getReplyId()).orElse(null);
		if(dbReply == null) throw new RuntimeException("댓글 번호 오류로 수정할 수 없습니다.");
		
		dbReply.setReplyContent(reply.getReplyContent());
		
		return dbReply;
	}

	@Override
	public void delete(Long replyId) {
		Reply dbReply = replyRepository.findById(replyId).orElse(null);
		if(dbReply == null) throw new RuntimeException("댓글 번호 오류로 삭제할 수 없습니다.");
		
		replyRepository.deleteById(replyId);
	}

	@Override
	public List<Reply> selectAll(Long postId) {
		List<Reply> replyList = replyRepository.selectByPostId(postId);
		
		return replyList;
//		return replyList.stream().filter(reply -> postId == reply.getPost().getPostId()).collect(Collectors.toList());
	}

}
