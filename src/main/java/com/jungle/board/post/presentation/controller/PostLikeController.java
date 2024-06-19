package com.jungle.board.post.presentation.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.jungle.board.auth.presentation.support.Member;
import com.jungle.board.post.application.PostLikeService;
import com.jungle.board.post.presentation.dto.request.CreatePostLikeRequest;
import com.jungle.board.post.presentation.dto.request.DeletePostLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/posts/likes")
@RestController
public class PostLikeController {

	private final PostLikeService postLikeService;

	@PostMapping
	public ResponseEntity<Void> click(
			@Member Long memberId, @RequestBody CreatePostLikeRequest request) {
		postLikeService.like(memberId, request);
		return ResponseEntity.status(CREATED).build();
	}

	@DeleteMapping
	public ResponseEntity<Void> cancel(
			@Member Long memberId, @RequestBody DeletePostLikeRequest request) {
		postLikeService.cancel(memberId, request);
		return ResponseEntity.noContent().build();
	}
}
