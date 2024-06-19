package com.jungle.board.post.persistence;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.post.application.exception.PostLikeCountNegativeException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;
	private String color;
	@OneToOne private Member member;

	@Embedded private Coordinate coordinate;

	private int likeCount = 0;
	@Version private Long version;

	public void clickLike() {
		this.likeCount++;
	}

	public void cancelLike() {
		if (likeCount == 0) {
			throw new PostLikeCountNegativeException();
		}
		this.likeCount--;
	}
}
