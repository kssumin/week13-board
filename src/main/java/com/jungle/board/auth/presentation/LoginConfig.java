package com.jungle.board.auth.presentation;

import com.jungle.board.auth.presentation.interceptor.AuthInterceptor;
import com.jungle.board.auth.presentation.support.HeaderTokenExtractor;
import com.jungle.board.auth.presentation.support.MemberArgumentResolver;
import com.jungle.board.auth.presentation.support.TokenResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer {
	private final MemberArgumentResolver memberArgumentResolver;
	private final TokenResolver tokenResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(memberAuthInterceptor())
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/auth/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(memberArgumentResolver);
	}

	@Bean
	public AuthInterceptor memberAuthInterceptor() {
		return AuthInterceptor.builder()
				.tokenExtractor(new HeaderTokenExtractor())
				.tokenResolver(tokenResolver)
				.build();
	}
}
