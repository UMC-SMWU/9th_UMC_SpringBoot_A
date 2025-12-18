package com.example.demo.global.paging;

import com.example.demo.global.exception.InvalidPageException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.*;

@Component
public class PageQueryArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(PageQuery.class)
			&& (parameter.getParameterType().equals(int.class)
					|| parameter.getParameterType().equals(Integer.class));
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) {
		PageQuery ann = parameter.getParameterAnnotation(PageQuery.class);
		String key = ann.value();

		String raw = webRequest.getParameter(key);
		if (raw == null) throw new InvalidPageException();

		final int page;
		try {
			page = Integer.parseInt(raw);
		} catch (NumberFormatException e) {
			throw new InvalidPageException();
		}

		if (page <= 0) throw new InvalidPageException();
		return page;
	}
}
