package com.jungle.board.auth.application.exception;

import com.jungle.board.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotDuplicateEmailException extends BusinessException {
	public NotDuplicateEmailException() {
		super(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getMessage() {
		return "중복된 이메일 입니다.";
	}
}
