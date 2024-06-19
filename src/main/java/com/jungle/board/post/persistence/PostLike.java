package com.jungle.board.post.persistence;

import static jakarta.persistence.FetchType.LAZY;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.post.application.PostLikeValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
public class PostLike{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false, updatable = false)
	private Post post;

	@OneToOne private Member member;

	public void like(PostLikeValidator postLikeValidator) {
		postLikeValidator.validateClickLike(post, member);
		post.clickLike();
	}

	public void cancel() {
		post.cancelLike();
	}
}
