package com.microservice.jobms.job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.jobms.job.external.Review;

@FeignClient(name = "REVIEW-MICROSERVICE")
public interface ReviewClient {
	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
