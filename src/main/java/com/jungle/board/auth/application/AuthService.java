package com.jungle.board.auth.application;

import com.jungle.board.auth.application.exception.NotDuplicateEmailException;
import com.jungle.board.auth.application.exception.NotMatchMemberException;
import com.jungle.board.auth.persistence.Member;
import com.jungle.board.auth.persistence.MemberRepository;
import com.jungle.board.auth.persistence.Password;
import com.jungle.board.auth.presentation.dto.SignInRequest;
import com.jungle.board.auth.presentation.dto.SignUpRequest;
import com.jungle.board.auth.infrastructure.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final MemberRepository memberRepository;
	private final Encoder encoder;
	@Transactional
	public void signup(SignUpRequest command) {
		validateDuplicateEmail(command.email());

		String encode = encoder.encode(command.password());
		Member member = toMember(command, encode);
		memberRepository.save(member);
	}

	public Long login(SignInRequest command) {
		Member member =
				memberRepository.getByEmail(command.email());

		member.validatePassword(command.password(), encoder);
		return member.getId();
	}

	private Member toMember(SignUpRequest command, String encode) {
		return Member.builder()
				.email(command.email())
				.password(Password.builder()
						.encryptedPassword(encode)
						.build())
				.nickname(command.nickname())
				.build();
	}

	private void validateDuplicateEmail(String email) {
		if(memberRepository.existsByEmail(email)){
			throw new NotDuplicateEmailException();
		}
	}
}
