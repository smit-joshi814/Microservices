package com.microservice.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.reviewms.review.Review;
import com.microservice.reviewms.review.dto.ReviewMessage;

@Service
public class ReviewMessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Review review) {
		ReviewMessage reviewMessage=new ReviewMessage();
		reviewMessage.setId(review.getId());
		reviewMessage.setTitle(review.getTitle());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setRating(review.getRating());
		reviewMessage.setCompanyId(review.getCompanyId());
		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
		
	}
}
