package com.sajee.myJobWeb.review;

import java.util.List;

public interface ReviewService {

    List<Review> findReviewByCompanyId(Long companyId);
    boolean addReviewForCompany(Long companyId, Review review);
    Review findReviewByReviewId(Long companyId, Long id);
    boolean updateReview(Long companyId, Long reviewId, Review updateReview);
    boolean deleteReview(Long companyId, Long reviewId);
}
