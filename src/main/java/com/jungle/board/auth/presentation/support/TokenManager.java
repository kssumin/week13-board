package com.jungle.board.auth.presentation.support;

import org.springframework.stereotype.Component;

public interface TokenManager {
	String createToken(String value);
}
