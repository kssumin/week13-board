package com.jungle.board.post.application.exception;

import com.jungle.board.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class PostLikeCountNegativeException extends BusinessException {
	public PostLikeCountNegativeException() {
		super(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getMessage() {
		log.info("PostLikeCountNegativeException : 좋아요 음수 발생");
		return "좋아요는 음수가 될 수 없습니다.";
	}
}
