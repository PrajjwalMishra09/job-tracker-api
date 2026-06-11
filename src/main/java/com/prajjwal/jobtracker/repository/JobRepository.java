package com.prajjwal.jobtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prajjwal.jobtracker.entity.JobApplication;

public interface JobRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByCompanyNameContainingIgnoreCase(
            String companyName);

    long countByStatus(String status);
}