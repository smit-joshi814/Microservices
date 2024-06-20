package com.microservice.jobms.job;

import java.util.List;

import com.microservice.jobms.job.dto.JobDTO;

public interface JobService {

	List<JobDTO> findAll();

	void createJob(Job job);

	JobDTO getJobById(Long id);

	Boolean deleteJob(Long jobId);

	void updateJob(Job job, Long jobId);

}
