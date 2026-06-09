package com.prajjwal.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prajjwal.jobtracker.entity.JobApplication;

public interface JobRepository
        extends JpaRepository<JobApplication, Long> {
}