package com.example.demo.domain.review.repository;


import com.example.demo.dto.MyReviewItemDto;
import com.example.demo.dto.MyReviewSearchCond;
import com.example.demo.domain.review.entity.QReview;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

	private final JPAQueryFactory query;

	@Override
	public Page<MyReviewItemDto> findMyReviews(Long userId, MyReviewSearchCond cond, Pageable pageable) {
		QReview r = QReview.review;
		QStore s = QStore.store;

		// 조건 빌드
		BooleanBuilder where = new BooleanBuilder();
		where.and(r.user.id.eq(userId));

		if (cond != null) {
			if (cond.storeId() != null) {
				where.and(r.store.id.eq(cond.storeId()));
			}
			if (cond.storeName() != null && !cond.storeName().isBlank()) {
				where.and(s.name.containsIgnoreCase(cond.storeName().trim()));
			}
			if (cond.ratingBand() != null) {
				int b = cond.ratingBand();
				if (b == 5) {
					where.and(r.star.eq(5.0f));
				} else {
					float lo = b;
					float hi = b + 1;
					where.and(r.star.goe(lo).and(r.star.lt(hi)));
				}
			}
		}


		record Row(Long reviewId, Long storeId, String storeName, Float star, String content, java.time.LocalDateTime createdAt) {}
		List<Row> rows = query
			.select(
				com.querydsl.core.types.Projections.constructor(Row.class,
					r.id, s.id, s.name, r.star, r.content, r.createdAt
				)
			)
			.from(r)
			.join(r.store, s)
			.where(where)
			.orderBy(r.createdAt.desc(), r.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Long> reviewIds = rows.stream().map(Row::reviewId).toList();



		// DTO 조립
		List<MyReviewItemDto> content = rows.stream()
			.map(row -> new MyReviewItemDto(
				row.reviewId(),
				row.storeId(),
				row.storeName(),
				row.star(),
				row.content(),
				row.createdAt(),
				List.of() // 사진 생략: 빈 리스트
			)).collect(Collectors.toList());

		// total count
		long total = query
			.select(r.count())
			.from(r)
			.join(r.store, s)
			.where(where)
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}
}

