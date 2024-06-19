package com.jungle.board.auth.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SignUpRequest(
		@NotEmpty @Email(message = "이메일 형식이 맞지 않습니다") String email,
		@NotEmpty(message = "비밀번호는 필수 입력입니다.") String password,
		@NotEmpty(message = "닉네임은 필수 입력입니다.") String nickname) {}
