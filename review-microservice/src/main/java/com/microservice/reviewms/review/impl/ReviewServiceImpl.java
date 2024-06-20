package com.microservice.reviewms.review.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.reviewms.review.Review;
import com.microservice.reviewms.review.ReviewRepository;
import com.microservice.reviewms.review.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	

	@Override
	public List<Review> findAllReviews() {
		return reviewRepository.findAll();
	}

	public List<Review> findAllReviews(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public Review getReviewById(Long reviewId) {
		return reviewRepository.findById(reviewId).orElse(null);
	}
	

	@Override
	public Boolean createReview(Review review,Long compnyId) {
		if(compnyId!=null) {
			review.setCompanyId(compnyId);
			reviewRepository.save(review);
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}

	@Override
	public void updateReview(Review newReview, Long reviewId) {
		reviewRepository.findById(reviewId).ifPresent(review -> {
			if (Objects.nonNull(newReview.getTitle()) && !newReview.getTitle().equalsIgnoreCase("")) {
				review.setTitle(newReview.getTitle());
			}
			if (Objects.nonNull(newReview.getDescription()) && !newReview.getDescription().equalsIgnoreCase("")) {
				review.setDescription(newReview.getDescription());
			}
			if (Objects.nonNull(newReview.getRating())) {
				review.setRating(newReview.getRating());
			}
			if (Objects.nonNull(newReview.getCompanyId())) {
				review.setCompanyId(newReview.getCompanyId());
			}
			reviewRepository.save(review);
		});

	}

	@Override
	public Boolean deleteReview(Long reviewId) {
		try {
			reviewRepository.deleteById(reviewId);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Double getAverageReview(Long companyId) {
		return reviewRepository.findAverageRatingByCompanyId(companyId);
	}

	

}
