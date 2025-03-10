package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface JobService {
    Map<String, Object> createJob(JobDTO jobDTO);
    Map<String, Object> updateJob(Long id, JobDTO jobDTO);
    Optional<JobDTO> getJobById(Long id);
    List<JobDTO> getAllJobs();
    Map<String, Object> deleteJob(Long id);
}