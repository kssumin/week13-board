package com.jungle.board.auth.presentation;

import static org.springframework.http.HttpStatus.OK;

import com.jungle.board.auth.application.AuthService;
import com.jungle.board.auth.presentation.dto.SignInRequest;
import com.jungle.board.auth.presentation.dto.SignUpRequest;
import com.jungle.board.auth.presentation.support.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	private final TokenManager tokenManager;

	@PostMapping("/signup")
	ResponseEntity<Void> signup(@RequestBody SignUpRequest request) {
		authService.signup(request);
		return ResponseEntity.status(OK).build();
	}

	@PostMapping("/login")
	ResponseEntity<Void> login(@RequestBody SignInRequest request) {
		Long memberId = authService.login(request);
		String token = tokenManager.createToken(String.valueOf(memberId));

		return ResponseEntity.status(OK).header(HttpHeaders.AUTHORIZATION, token).build();
	}
}
