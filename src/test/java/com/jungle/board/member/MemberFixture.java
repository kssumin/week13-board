package com.jungle.board.member;

import com.jungle.board.auth.persistence.Member;
import com.jungle.board.auth.persistence.Password;

public class MemberFixture {
	public static Member toMember(String email) {
		return Member.builder()
				.email(email)
				.password(Password.builder().encryptedPassword("password").build())
				.nickname("mando")
				.build();
	}
}
