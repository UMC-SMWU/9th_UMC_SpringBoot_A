package com.umc9th.umc9th.domain.review.controller;

import com.umc9th.umc9th.domain.review.entity.Review;
import com.umc9th.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ResourceUrlProvider mvcResourceUrlProvider;
    private ReviewQueryService reviewQueryService;

    public List<Review> searchReview(
            @RequestParam("query") String query, @RequestParam("type") String type
    ) {
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }
}
