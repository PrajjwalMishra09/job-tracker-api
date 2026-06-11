package com.prajjwal.jobtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prajjwal.jobtracker.entity.JobApplication;
import com.prajjwal.jobtracker.repository.JobRepository;

@Controller
public class ViewController {

    private final JobRepository repository;

    public ViewController(JobRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("jobs", repository.findAll());

        return "index";
    }

    @GetMapping("/search")
    public String searchJobs(
            @RequestParam String company,
            Model model) {

        model.addAttribute(
                "jobs",
                repository.findByCompanyNameContainingIgnoreCase(company));

        return "index";
    }

    @PostMapping("/add-job")
    public String addJob(
            @RequestParam String companyName,
            @RequestParam String role,
            @RequestParam String status) {

        JobApplication job = new JobApplication();

        job.setCompanyName(companyName);
        job.setRole(role);
        job.setStatus(status);

        repository.save(job);

        return "redirect:/";
    }

    @PostMapping("/update-status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        JobApplication job =
                repository.findById(id).orElse(null);

        if (job != null) {

            job.setStatus(status);

            repository.save(job);
        }

        return "redirect:/";
    }

    @PostMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/";
    }
}