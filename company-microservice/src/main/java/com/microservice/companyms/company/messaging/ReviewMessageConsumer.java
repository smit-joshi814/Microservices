package com.microservice.companyms.company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.companyms.company.CompanyService;
import com.microservice.companyms.company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {

	@Autowired
	private CompanyService companyService;
	
	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyRating(reviewMessage);
	}
	
}
