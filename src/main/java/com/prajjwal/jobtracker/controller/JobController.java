package com.prajjwal.jobtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prajjwal.jobtracker.entity.JobApplication;
import com.prajjwal.jobtracker.repository.JobRepository;

@RestController
public class JobController {

    private final JobRepository repository;

    public JobController(JobRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jobs")
    public List<JobApplication> getJobs() {
        return repository.findAll();
    }

    @GetMapping("/jobs/{id}")
    public JobApplication getJobById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/jobs")
    public JobApplication addJob(@RequestBody JobApplication job) {
        return repository.save(job);
    }

    @PutMapping("/jobs/{id}")
    public JobApplication updateJob(
            @PathVariable Long id,
            @RequestBody JobApplication updatedJob) {

        JobApplication job =
                repository.findById(id).orElse(null);

        if (job == null) {
            return null;
        }

        job.setCompanyName(updatedJob.getCompanyName());
        job.setRole(updatedJob.getRole());
        job.setStatus(updatedJob.getStatus());

        return repository.save(job);
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {

        repository.deleteById(id);

        return "Job deleted successfully";
    }
}