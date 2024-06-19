package com.jungle.board.auth.presentation.support;

import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class FakeTokenResolver implements TokenResolver {
	private static final int MEMBER_INFO_INDEX = 0;
	private static final int TOKEN_INDEX = 1;

	@Override
	public Long decode(String token) {
		String value = parsePrefix(token);
		return Long.valueOf(parseMemberInfo(value));
	}

	private String parsePrefix(String token) {
		return Arrays.stream(token.split(AuthConstants.PREFIX))
				.map(String::trim)
				.toList()
				.get(TOKEN_INDEX);
	}

	private String parseMemberInfo(String value) {
		return Arrays.stream(value.split(AuthConstants.SEPARATOR))
				.map(String::trim)
				.toList()
				.get(MEMBER_INFO_INDEX);
	}
}
