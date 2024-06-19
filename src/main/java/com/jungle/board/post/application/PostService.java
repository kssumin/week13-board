package com.jungle.board.post.application;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.auth.persistence.MemberRepository;
import com.jungle.board.post.application.exception.InvalidPointException;
import com.jungle.board.post.persistence.Coordinate;
import com.jungle.board.post.persistence.Post;
import com.jungle.board.post.persistence.PostLikeRepository;
import com.jungle.board.post.persistence.PostRepository;
import com.jungle.board.post.presentation.dto.request.CreatePostRequest;
import com.jungle.board.post.presentation.dto.response.PostDetailResponse;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;

	@Transactional
	public Long create(Long memberId, CreatePostRequest command) {
		Member member = memberRepository.getById(memberId);
		Post post = toPost(member, command);
		return postRepository.save(post).getId();
	}

	public PostDetailResponse getByOne(Long memberId, Long postId) {
		Post post = postRepository.getById(postId);
		Member member = memberRepository.getById(memberId);

		boolean isLike = isLike(post, member);
		return toResponse(post, isLike, member);
	}

	public List<PostDetailResponse> getPosts(Long memberId, String center) {
		List<Float> xPoints = toXPoint(center);
		List<Post> posts = fetchPostsByXRange(xPoints);
		Member member = memberRepository.getById(memberId);

		return toResponse(posts, member);
	}

	private Post toPost(Member member, CreatePostRequest command) {
		return Post.builder()
				.content(command.content())
				.color(command.color())
				.coordinate(Coordinate.builder().postX(command.x()).postY(command.y()).build())
				.member(member)
				.build();
	}

	private boolean isLike(Post post, Member member) {
		return postLikeRepository.existsByPostAndMember(post, member);
	}

	private PostDetailResponse toResponse(Post post, boolean isLike, Member member) {
		return PostDetailResponse.from(post, isLike, member.getNickname());
	}

	private List<PostDetailResponse> toResponse(List<Post> posts, Member member) {
		return posts.stream().map(post -> toResponse(post, isLike(post, member), member)).toList();
	}

	private List<Float> toXPoint(String center) {
		return Arrays.stream(center.split(",")).map(String::trim).map(Float::parseFloat).toList();
	}

	/**
	 * 주어진 x 좌표 범위 내의 게시물을 조회
	 *
	 * @param xPoints x 좌표 목록
	 * @return 게시물 목록
	 */
	private List<Post> fetchPostsByXRange(List<Float> xPoints) {
		if (xPoints.size() < 2) {
			throw new InvalidPointException();
		}
		return postRepository.findByBetweenX(xPoints.get(0), xPoints.get(1));
	}
}
