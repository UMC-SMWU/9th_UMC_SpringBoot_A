package com.example.demo.global.paging;

import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageResponseConverter {

	public static <E, D> PageResponse<D> from(Page<E> page, int requestedPage, Function<E, D> mapper) {
		return PageResponse.<D>builder()
			.page(requestedPage)
			.size(page.getSize())
			.totalElements(page.getTotalElements())
			.totalPages(page.getTotalPages())
			.last(page.isLast())
			.content(page.getContent().stream().map(mapper).toList()) // Stream만 사용
			.build();
	}
}
