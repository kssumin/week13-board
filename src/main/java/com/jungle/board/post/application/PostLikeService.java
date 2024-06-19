package com.jungle.board.post.application;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.auth.persistence.MemberRepository;
import com.jungle.board.post.persistence.Post;
import com.jungle.board.post.persistence.PostLike;
import com.jungle.board.post.persistence.PostLikeRepository;
import com.jungle.board.post.persistence.PostRepository;
import com.jungle.board.post.presentation.dto.request.CreatePostLikeRequest;
import com.jungle.board.post.presentation.dto.request.DeletePostLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostLikeService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final PostLikeRepository postLikeRepository;
	private final PostLikeValidator postLikeValidator;

	@Transactional
	public void like(Long memberId, CreatePostLikeRequest command) {
		Post post = postRepository.getById(command.postId());
		Member member = memberRepository.getById(memberId);
		PostLike postLike = toPostLike(post, member);

		postLike.like(postLikeValidator);
		postLikeRepository.save(postLike);
	}

	@Transactional
	public void cancel(Long memberId, DeletePostLikeRequest command) {
		PostLike postLike = postLikeRepository.getByPostAndMember(command.postId(), memberId);
		postLike.cancel();
		postLikeRepository.delete(postLike);
	}

	private PostLike toPostLike(Post post, Member member) {
		return PostLike.builder().post(post).member(member).build();
	}
}
