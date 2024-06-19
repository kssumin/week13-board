package com.jungle.board.post.persistence;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.post.application.exception.NotFoundPostLikeException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	boolean existsByPostAndMember(Post post, Member member);

	default PostLike getByPostAndMember(Long postId, Long memberId) {
		return findByPostAndMember(postId, memberId).orElseThrow(NotFoundPostLikeException::new);
	}

	@Query(
			"""
						SELECT pl FROM PostLike pl
						WHERE pl.post.id = :postId
						AND pl.member.id = :memberId
						""")
	Optional<PostLike> findByPostAndMember(
			@Param("postId") Long postId, @Param("memberId") Long memberId);
}
