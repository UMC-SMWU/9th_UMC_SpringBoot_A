package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String filter,
            @RequestParam String type
    ) throws Exception {
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(filter, type);
        return result;
    }

    @PostMapping("/create")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @RequestBody ReviewReqDTO.CreateReviewDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewService.createReview(dto));
    }
    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName,page));
    }
    // 내가 쓴 리뷰 조회
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getMyReviews(memberId, page)
        );
    }
}