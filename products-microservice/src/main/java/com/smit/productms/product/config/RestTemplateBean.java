package com.smit.productms.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateBean {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
