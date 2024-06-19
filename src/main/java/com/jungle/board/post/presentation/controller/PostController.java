package com.jungle.board.post.presentation.controller;

import com.jungle.board.auth.presentation.support.Member;
import com.jungle.board.post.application.PostService;
import com.jungle.board.post.presentation.dto.request.CreatePostRequest;
import com.jungle.board.post.presentation.dto.response.PostDetailResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<Long> create(
			@Member Long memberId, @RequestBody @NotNull CreatePostRequest request) {
		return ResponseEntity.ok(postService.create(memberId, request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDetailResponse> getById(
			@Member Long memberId, @PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(postService.getByOne(memberId, id));
	}

	@GetMapping
	public ResponseEntity<List<PostDetailResponse>> getByCenter(
			@Member Long memberId, @RequestParam("center") String center) {
		return ResponseEntity.ok(postService.getPosts(memberId, center));
	}
}
