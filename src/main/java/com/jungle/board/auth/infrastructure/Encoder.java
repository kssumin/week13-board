package com.jungle.board.auth.infrastructure;

import org.springframework.stereotype.Component;

public interface Encoder {

	String encode(String rawPassword);

	boolean match(String rawPassword, String encrypted);
}
