package com.jungle.board.auth.presentation.support;

import org.springframework.stereotype.Component;

public interface TokenResolver {
	Long decode(String token);
}
