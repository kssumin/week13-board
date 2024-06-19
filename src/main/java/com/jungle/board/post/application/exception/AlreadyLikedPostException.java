package com.jungle.board.post.application.exception;

import com.jungle.board.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyLikedPostException extends BusinessException {

	public AlreadyLikedPostException() {
		super(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getMessage() {
		return "이미 좋아요를 누른 게시물입니다.";
	}
}
