package com.microservice.reviewms.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.reviewms.review.messaging.ReviewMessageProducer;

@RestController
@RequestMapping("reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewMessageProducer reviewMessageProducer;

	@GetMapping
	public ResponseEntity<List<Review>> findAllReviews(@RequestParam Long companyId) {
		return ResponseEntity.ok(reviewService.findAllReviews(companyId));
	}

	@GetMapping("{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
		return ResponseEntity.ok(reviewService.getReviewById(id));
	}
	
	@GetMapping("/average-rating")
	public ResponseEntity<Double> getAverageReview(@RequestParam Long companyId) {
		return ResponseEntity.ok(reviewService.getAverageReview(companyId));
	}

	@PostMapping
	public ResponseEntity<String> createReview(@RequestBody Review review,@RequestParam Long companyId) {
		if(reviewService.createReview(review,companyId)) {
			reviewMessageProducer.sendMessage(review);
			return ResponseEntity.ok("review created successfully");
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable Long id) {
		reviewService.updateReview(review, id);
		return ResponseEntity.ok("review updated successfully");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id) {
		if (reviewService.deleteReview(id)) {
			return ResponseEntity.ok("review deleted successfully");
		}
		return ResponseEntity.notFound().build();
	}

}
