package com.jungle.board.auth.presentation.support;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class FakeTokenManager implements TokenManager {
	@Override
	public String createToken(String value) {
		return AuthConstants.PREFIX + value + AuthConstants.SEPARATOR + getRandomNumber();
	}

	private String getRandomNumber() {
		return UUID.randomUUID().toString();
	}
}
