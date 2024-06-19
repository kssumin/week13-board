package com.jungle.board.auth.infrastructure;

public interface Encoder {

	String encode(String rawPassword);

	boolean match(String rawPassword, String encrypted);
}
