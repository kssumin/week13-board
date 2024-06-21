package com.jungle.board.post.application;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.auth.persistence.MemberRepository;
import com.jungle.board.common.DatabaseCleanup;
import com.jungle.board.member.MemberFixture;
import com.jungle.board.post.PostFixture;
import com.jungle.board.post.persistence.Post;
import com.jungle.board.post.persistence.PostRepository;
import com.jungle.board.post.presentation.dto.request.CreatePostLikeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootTest
class PostLikeServiceTest {
	private static final int EXPECTED_LIKE_SIZE = 30;
	@Autowired private PostLikeService postLikeService;
	@Autowired private PostRepository postRepository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private DatabaseCleanup databaseCleanup;
	private CreatePostLikeRequest request;

	private List<Member> members;
	private Post post;

	@BeforeEach
	void setup() {
		databaseCleanup.execute();

		Member mando = MemberFixture.toMember("mando@naver.com");
		memberRepository.save(mando);
		post = PostFixture.toPost(mando);
		postRepository.save(post);
		request = new CreatePostLikeRequest(post.getId());

		members = new ArrayList<Member>();
		for (int i = 0; i < EXPECTED_LIKE_SIZE; i++) {
			Member member = MemberFixture.toMember("email" + String.valueOf(i) + "@naver.com");
			members.add(memberRepository.save(member));
		}
	}

	@Test
	@DisplayName("딘일 스레드 환경에서 좋아요 요청 테스트")
	void single_thread_like() {
		// when
		for (Member member : members) {
			postLikeService.like(member.getId(), new CreatePostLikeRequest(post.getId()));
		}

		// then
		int likeCount = postRepository.getById(post.getId()).getLikeCount();
		Assertions.assertEquals(EXPECTED_LIKE_SIZE, likeCount);
	}

	@Test
	@DisplayName("멀티 스레드 환경에서 좋아요 요청 테스트")
	void multi_thread_like() throws InterruptedException {
		// given
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		CountDownLatch latch = new CountDownLatch(EXPECTED_LIKE_SIZE);

		AtomicInteger successCount = new AtomicInteger();
		AtomicInteger failCount = new AtomicInteger();

		// when
		for (Member member : members) {
			executorService.submit(
					() -> {
						try {
							postLikeService.like(member.getId(), request);
							successCount.incrementAndGet();
						} catch (ObjectOptimisticLockingFailureException e) {
							failCount.incrementAndGet();
						} finally {
							latch.countDown();
						}
					});
		}

		latch.await();

		System.out.println("success count : " + successCount);
		System.out.println("fail count : " + failCount);

		// then
		int likeCount = postRepository.getById(post.getId()).getLikeCount();
		Assertions.assertEquals(EXPECTED_LIKE_SIZE, likeCount);
	}
}
