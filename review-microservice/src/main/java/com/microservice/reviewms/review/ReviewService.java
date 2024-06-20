package com.microservice.reviewms.review;

import java.util.List;

public interface ReviewService {

	List<Review> findAllReviews();
	
	List<Review> findAllReviews(Long companyId);
	
	Review getReviewById(Long reviewId);
	
	Boolean createReview(Review review,Long companyId);
	
	void updateReview(Review review,Long reviewId);
	
	Boolean deleteReview(Long reviewId);
	
	Double getAverageReview(Long companyId);
	
}
