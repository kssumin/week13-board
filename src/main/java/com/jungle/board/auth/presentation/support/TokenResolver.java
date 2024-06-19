package com.jungle.board.auth.presentation.support;

public interface TokenResolver {
	Long decode(String token);
}
