package com.jungle.board.auth.persistence;

import com.jungle.board.auth.application.exception.NotMatchMemberException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	default Member getById(Long memberId) {
		return findById(memberId).orElseThrow(NotMatchMemberException::new);
	}

	default Member getByEmail(String email) {
		return findByEmail(email).orElseThrow(NotMatchMemberException::new);
	}

	Optional<Member> findByEmail(String email);

	boolean existsByEmail(String email);
}
