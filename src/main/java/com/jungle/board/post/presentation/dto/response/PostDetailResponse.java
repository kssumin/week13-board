package com.jungle.board.post.presentation.dto.response;

import com.jungle.board.post.persistence.Coordinate;
import com.jungle.board.post.persistence.Post;
import lombok.Builder;

@Builder
public record PostDetailResponse(
		Long postId,
		String title,
		String content,
		String color,
		CoordinateResponse coordinate,
		int likeCount,
		boolean isLike,
		String writer) {

	public static PostDetailResponse from(Post post, boolean isLike, String writer) {
		return PostDetailResponse.builder()
				.postId(post.getId())
				.content(post.getContent())
				.color(post.getColor())
				.coordinate(CoordinateResponse.from(post.getCoordinate()))
				.likeCount(post.getLikeCount())
				.isLike(isLike)
				.writer(writer)
				.build();
	}

	public record CoordinateResponse(double x, double y) {
		private static CoordinateResponse from(Coordinate coordinate) {
			return new CoordinateResponse(coordinate.getPostX(), coordinate.getPostY());
		}
	}
}
