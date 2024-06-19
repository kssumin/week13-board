package com.jungle.board.post.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreatePostLikeRequest(@NotEmpty Long postId) {}
