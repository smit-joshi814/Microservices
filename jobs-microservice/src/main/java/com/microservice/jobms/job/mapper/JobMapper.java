package com.microservice.jobms.job.mapper;

import java.util.List;

import com.microservice.jobms.job.Job;
import com.microservice.jobms.job.dto.JobDTO;
import com.microservice.jobms.job.external.Company;
import com.microservice.jobms.job.external.Review;

public class JobMapper {
	public static JobDTO mapToJobDto(Job job, Company company,List<Review> reviews) {
		JobDTO jobDto = new JobDTO();
		jobDto.setId(job.getId());
		jobDto.setTitle(job.getTitle());
		jobDto.setDescription(job.getDescription());
		jobDto.setMinSalary(job.getMinSalary());
		jobDto.setMaxSalary(job.getMaxSalary());
		jobDto.setCompany(company);
		jobDto.setReviews(reviews);
		return jobDto;
	}
}
