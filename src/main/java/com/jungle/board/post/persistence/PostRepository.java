package com.jungle.board.post.persistence;

import com.jungle.board.post.application.exception.NotFoundPostException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

	default Post getById(Long postId) {
		return findById(postId).orElseThrow(NotFoundPostException::new);
	}

	@Query("SELECT p FROM Post p WHERE  p.coordinate.postX BETWEEN :x AND :y")
	List<Post> findByBetweenX(@Param("x") Float x, @Param("y") Float y);
}
