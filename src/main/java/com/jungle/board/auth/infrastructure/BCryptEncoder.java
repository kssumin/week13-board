package com.jungle.board.auth.infrastructure;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder implements Encoder {
	@Override
	public String encode(String rawPassword) {
		return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
	}

	@Override
	public boolean match(String rawPassword, String encrypted) {
		return BCrypt.checkpw(rawPassword, encrypted);
	}
}
