package com.jungle.board.auth.presentation.support;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

public interface TokenExtractor {
	String extract(HttpServletRequest request);
}
