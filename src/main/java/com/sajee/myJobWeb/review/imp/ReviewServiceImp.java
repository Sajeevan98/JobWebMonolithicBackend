package com.sajee.myJobWeb.review.imp;

import com.sajee.myJobWeb.company.Company;
import com.sajee.myJobWeb.company.CompanyService;
import com.sajee.myJobWeb.review.Review;
import com.sajee.myJobWeb.review.ReviewRepository;
import com.sajee.myJobWeb.review.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImp(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findReviewByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReviewForCompany(Long companyId, Review review) {
        Company company = companyService.findByCompanyId(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else
            return false;
    }

    @Override
    public Review findReviewByReviewId(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        // "stream()" used for filtering mechanism...
        return reviews.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        if (companyService.findByCompanyId(companyId)!=null && reviewRepository.existsById(reviewId))
        {
            updateReview.setCompany(companyService.findByCompanyId(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.findByCompanyId(companyId)!=null && reviewRepository.existsById(reviewId)){
            //  get the reviews
            Review review = reviewRepository.findById(reviewId).orElse(null);
            //get the company for review
            Company company = review.getCompany();
            //delete particular review from the particular company
            company.getReviews().remove(review);
            //update the company
            companyService.updateCompanyById(companyId,company);
            // delete review by id
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else
            return false;
    }


}
