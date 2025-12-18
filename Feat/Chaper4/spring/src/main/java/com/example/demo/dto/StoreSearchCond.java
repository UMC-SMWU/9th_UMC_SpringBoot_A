package com.example.demo.dto;

public record StoreSearchCond(
	String region,
	String keyword,
	String sort
) {}