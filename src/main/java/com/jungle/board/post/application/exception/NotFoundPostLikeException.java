package com.jungle.board.post.application.exception;

import com.jungle.board.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundPostLikeException extends BusinessException {

	public NotFoundPostLikeException() {
		super(HttpStatus.BAD_REQUEST);
	}
}
