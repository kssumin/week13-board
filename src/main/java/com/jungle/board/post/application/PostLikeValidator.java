package com.jungle.board.post.application;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.post.application.exception.AlreadyLikedPostException;
import com.jungle.board.post.persistence.Post;
import com.jungle.board.post.persistence.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostLikeValidator {

	private final PostLikeRepository postLikeRepository;

	public void validateClickLike(Post post, Member member) {
		if (postLikeRepository.existsByPostAndMember(post, member)) {
			throw new AlreadyLikedPostException();
		}
	}
}
