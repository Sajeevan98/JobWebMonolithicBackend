package com.sajee.myJobWeb.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewByCompanyId(@PathVariable Long companyId){
        List<Review> reviewList =  reviewService.findReviewByCompanyId(companyId);
        if (!reviewList.isEmpty())
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        else{
            return new ResponseEntity<>(reviewList, HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping
    public ResponseEntity<String> createReviewForCompany(@PathVariable Long companyId, @RequestBody Review review){
        boolean response = reviewService.addReviewForCompany(companyId, review);
        if(response)
            return new ResponseEntity<>("review successfully added to company existing with id: "+companyId, HttpStatus.OK);
        else
            return new ResponseEntity<>("No company found existing with id: "+companyId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewByReviewId(@PathVariable Long companyId, @PathVariable Long id){
        if (reviewService.findReviewByReviewId(companyId, id) != null)
            return new ResponseEntity<>( reviewService.findReviewByReviewId(companyId, id), HttpStatus.OK );
        else
            return new ResponseEntity<>( reviewService.findReviewByReviewId(companyId, id), HttpStatus.NOT_FOUND );
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review updateReview){

        boolean isUpdated = reviewService.updateReview(companyId, reviewId, updateReview);
        if (isUpdated)
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed! Check again...", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted)
            return new ResponseEntity<>("Review with id: "+reviewId+" is deleted successfully to company existing with " +
                    "id: "+ companyId, HttpStatus.OK);
        else
            return new ResponseEntity<>("Something went wrong! check again...", HttpStatus.NOT_FOUND);
    }

}
