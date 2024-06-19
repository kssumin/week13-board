package com.jungle.board.post;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.post.persistence.Coordinate;
import com.jungle.board.post.persistence.Post;
import com.jungle.board.post.presentation.dto.request.CreatePostRequest;

public class PostFixture {
	public static Post toPost(Member member) {
		return Post.builder()
				.content("content")
				.color("color")
				.coordinate(Coordinate.builder().postX(3.4f).postY(3.4f).build())
				.member(member)
				.build();
	}

	public static CreatePostRequest toRequest() {
		return new CreatePostRequest("content", "color", 3.4f, 3.4f);
	}
}
