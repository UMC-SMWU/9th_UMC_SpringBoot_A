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
		where.and(r.user.id.eq(userId)); // 내 리뷰만 r.user.id == userId

		if (cond != null) {
			if (cond.storeId() != null) { //storeId
				where.and(r.store.id.eq(cond.storeId()));
			}
			if (cond.storeName() != null && !cond.storeName().isBlank()) { //storeNmae이 있으면
				where.and(s.name.containsIgnoreCase(cond.storeName().trim())); //대소문자 무시
			}
			if (cond.ratingBand() != null) { //별점 구간 필터 적용
				int b = cond.ratingBand();
				if (b == 5) {
					where.and(r.star.eq(5.0f));
				} else {
					float lo = b; //하한 (>=)
					float hi = b + 1; //상한 (<)
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
			.join(r.store, s) //가게명 필터/표시용 조인
			.where(where)
			.orderBy(r.createdAt.desc(), r.id.desc()) //최신순
			.offset(pageable.getOffset()) //페이지네이션
			.limit(pageable.getPageSize())
			.fetch();

		List<Long> reviewIds = rows.stream().map(Row::reviewId).toList();



		// DTO 매핑
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

