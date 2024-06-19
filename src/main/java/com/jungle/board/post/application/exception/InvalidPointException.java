package com.jungle.board.post.application.exception;

import com.jungle.board.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidPointException extends BusinessException {
	public InvalidPointException() {
		super(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getMessage() {
		return "유효하지 않는 좌표입니다";
	}
}
