package com.jungle.board.auth.persistence;

import static lombok.AccessLevel.PROTECTED;

import com.jungle.board.auth.application.exception.NotMatchMemberException;
import com.jungle.board.auth.infrastructure.Encoder;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Embeddable
public class Password {

	private String encryptedPassword;

	public void validatePassword(String rawPassword, Encoder encoder) {
		if (!encoder.match(rawPassword, encryptedPassword)) {
			throw new NotMatchMemberException();
		}
	}
}
