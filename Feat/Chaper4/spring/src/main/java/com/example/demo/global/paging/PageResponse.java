package com.example.demo.global.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageResponse<T> {
	private int page;
	private int size;          // 10
	private long totalElements;
	private int totalPages;
	private boolean last;
	private List<T> content;
}
