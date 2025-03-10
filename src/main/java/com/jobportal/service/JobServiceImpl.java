package com.jobportal.service.impl;

import com.jobportal.dto.ApplicantsDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Applicants;
import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "JobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    // Convert Applicant entity to DTO
    private JobDTO convertJobToDTO(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompany(),
                job.getLocation(),
                job.getJobType(),
                job.getSkillsRequired(),
                job.getPostTime(),
                job.getPackageOffered(),
                job.getJobStatus(),
                new ArrayList<>()
        );
    }

    private ApplicantsDTO convertApplicantToDTO(Applicants applicant) {
        JobDTO jobDTO = convertJobToDTO(applicant.getJob());

        return new ApplicantsDTO(
                applicant.getApplicantId(),
                applicant.getFirstName(),
                applicant.getLastName(),
                applicant.getEmail(),
                applicant.getPhoneNumber(),
                applicant.getWebsite(),
                applicant.getResume(),
                applicant.getCoverLetter(),
                applicant.getTimestamp(),
                applicant.getStatus(),
                jobDTO
        );
    }



    @Override
    public Map<String, Object> createJob(JobDTO jobDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Convert DTO to entity and save
            Job job = jobDTO.toEntity();
            job = jobRepository.save(job);

            response.put("success", true);
            response.put("message", "Job created successfully!");
            response.put("jobId", job.getId());
        } catch (Exception e) {
            response.put("error", true);
            response.put("message", "An error occurred while creating the job: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> updateJob(Long id, JobDTO jobDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<Job> existingJob = jobRepository.findById(id);

        if (existingJob.isPresent()) {
            try {
                // Convert JobDTO to Job entity
                Job job = jobDTO.toEntity();

                // Ensure the Job ID is preserved (important when updating an existing Job)
                job.setId(id);  // Ensure the Job ID is not overwritten

                // Save the updated Job entity
                jobRepository.save(job);

                response.put("success", true);
                response.put("message", "Job updated successfully!");
            } catch (Exception e) {
                response.put("error", true);
                response.put("message", "An error occurred while updating the job: " + e.getMessage());
            }
        } else {
            response.put("error", true);
            response.put("message", "Job with id " + id + " not found.");
        }
        return response;
    }

    @Override
    public Optional<JobDTO> getJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        return jobOptional.map(job -> {
            List<ApplicantsDTO> applicantsDTOList = job.getApplicants().stream()
                    .map(this::convertApplicantToDTO) // Use the conversion helper method
                    .collect(Collectors.toList());

            return new JobDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getCompany(),
                    job.getLocation(),
                    job.getJobType(),
                    job.getSkillsRequired(),
                    job.getPostTime(),
                    job.getPackageOffered(),
                    job.getJobStatus(),
                    applicantsDTOList  // Return the List<ApplicantsDTO> here
            );
        });
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(job -> {
            List<ApplicantsDTO> applicantsDTOList = job.getApplicants().stream()
                    .map(this::convertApplicantToDTO) // Use the conversion helper method
                    .collect(Collectors.toList());

            return new JobDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getCompany(),
                    job.getLocation(),
                    job.getJobType(),
                    job.getSkillsRequired(),
                    job.getPostTime(),
                    job.getPackageOffered(),
                    job.getJobStatus(),
                    applicantsDTOList  // Return the List<ApplicantsDTO> here
            );
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> deleteJob(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            try {
                // Delete the job
                jobRepository.deleteById(id);
                response.put("success", true);
                response.put("message", "Job deleted successfully!");
            } catch (Exception e) {
                response.put("error", true);
                response.put("message", "An error occurred while deleting the job: " + e.getMessage());
            }
        } else {
            response.put("error", true);
            response.put("message", "Job with id " + id + " not found.");
        }
        return response;
    }
}
