package com.jungle.board.post.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.jungle.board.common.exception.BusinessException;

public class NotFoundPostException extends BusinessException {

	public NotFoundPostException() {
		super(NOT_FOUND);
	}

	@Override
	public String getMessage() {
		return "존재하지 않는 게시글입니다.";
	}
}
