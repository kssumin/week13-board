package com.jungle.board.post.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreatePostRequest(
		@NotEmpty String content, @NotEmpty String color, Float x, Float y) {}
