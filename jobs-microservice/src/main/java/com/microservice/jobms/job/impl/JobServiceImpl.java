package com.microservice.jobms.job.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.jobms.job.Job;
import com.microservice.jobms.job.JobRepository;
import com.microservice.jobms.job.JobService;
import com.microservice.jobms.job.clients.CompanyClient;
import com.microservice.jobms.job.clients.ReviewClient;
import com.microservice.jobms.job.dto.JobDTO;
import com.microservice.jobms.job.external.Company;
import com.microservice.jobms.job.external.Review;
import com.microservice.jobms.job.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private CompanyClient companyClient;

	@Autowired
	private ReviewClient reviewClient;
	
//	int attempts=0;

	@Override
	@CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
//	@Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//	@RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//	@RateLimiter(name = "companyBreaker")
	public List<JobDTO> findAll() {
//		System.out.println("Attempts : "+attempts++);
		List<Job> jobs = jobRepository.findAll();
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<String> companyBreakerFallback(Exception e) {
		return List.of("This is Circuit breaker's fallback method in Action.");
	}

	private JobDTO convertToDto(Job job) {
		Company company = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		JobDTO jobDto = JobMapper.mapToJobDto(job, company, reviews);
		return jobDto;
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public JobDTO getJobById(Long jobId) {
		return jobRepository.findById(jobId).map(this::convertToDto).orElse(null);
	}

	@Override
	public Boolean deleteJob(Long jobId) {
		try {
			jobRepository.deleteById(jobId);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public void updateJob(Job newJob, Long jobId) {
		jobRepository.findById(jobId).ifPresent(job -> {
			if (Objects.nonNull(newJob.getTitle()) && !newJob.getTitle().equalsIgnoreCase("")) {
				job.setTitle(newJob.getTitle());
			}
			if (Objects.nonNull(newJob.getDescription()) && !newJob.getDescription().equalsIgnoreCase("")) {
				job.setDescription(newJob.getDescription());
			}
			if (Objects.nonNull(newJob.getMinSalary()) && !newJob.getMinSalary().equalsIgnoreCase("")) {
				job.setMinSalary(newJob.getMinSalary());
			}
			if (Objects.nonNull(newJob.getMaxSalary()) && !newJob.getMaxSalary().equalsIgnoreCase("")) {
				job.setMaxSalary(newJob.getMaxSalary());
			}
			if (Objects.nonNull(newJob.getCompanyId())) {
				job.setCompanyId(newJob.getCompanyId());
			}
			jobRepository.save(job);
		});
	}

}
