package com.jungle.board.auth.presentation.interceptor;

import com.jungle.board.auth.presentation.support.TokenExtractor;
import com.jungle.board.auth.presentation.support.TokenResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	private final TokenExtractor tokenExtractor;
	private final TokenResolver tokenResolver;

	public static AuthInterceptorBuilder builder() {
		return new AuthInterceptorBuilder();
	}

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (CorsUtils.isPreFlightRequest(request)) {
			return true;
		}

		String token = tokenExtractor.extract(request);
		tokenResolver.decode(token);
		return true;
	}

	public static class AuthInterceptorBuilder {

		private TokenExtractor tokenExtractor;
		private TokenResolver tokenResolver;

		public AuthInterceptorBuilder tokenExtractor(TokenExtractor tokenExtractor) {
			this.tokenExtractor = tokenExtractor;
			return this;
		}

		public AuthInterceptorBuilder tokenResolver(TokenResolver tokenResolver) {
			this.tokenResolver = tokenResolver;
			return this;
		}

		public AuthInterceptor build() {
			return new AuthInterceptor(tokenExtractor, tokenResolver);
		}
	}
}
