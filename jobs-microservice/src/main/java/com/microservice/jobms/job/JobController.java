package com.microservice.jobms.job;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.jobms.job.dto.JobDTO;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<JobDTO>> findAll() {
		return ResponseEntity.ok(jobService.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
		JobDTO jobDto = jobService.getJobById(id);
		if(Objects.nonNull(jobDto)) {
			return ResponseEntity.ok(jobDto);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return ResponseEntity.ok("Job added Successfully");
	}

	@PutMapping("{jobId}")
	public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable Long jobId) {
		jobService.updateJob(job,jobId);
		return ResponseEntity.ok("Job Updated Successfully");
	}

	@DeleteMapping("{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
		Boolean isRemoved = jobService.deleteJob(jobId);
		if (isRemoved)
			return ResponseEntity.ok("Job Deleted Successfully");
		return ResponseEntity.notFound().build();
	}

}