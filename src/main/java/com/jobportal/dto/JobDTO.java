package com.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobportal.entity.Applicants;
import com.jobportal.entity.Job;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String company;
    private String location;
    private String jobType;
    private List<String> skillsRequired;
    private LocalDateTime postTime;
    private Long packageOffered;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private List<ApplicantsDTO> applicants;  // List of applicants instead of number of applicants

    // Constructor for mapping JSON to DTO
    @JsonCreator
    public JobDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("company") String company,
            @JsonProperty("location") String location,
            @JsonProperty("job_type") String jobType,
            @JsonProperty("skills_required") List<String> skillsRequired,
            @JsonProperty("post_time") LocalDateTime postTime,
            @JsonProperty("package_offered") Long packageOffered,
            @JsonProperty("job_status") JobStatus jobStatus,
            @JsonProperty("applicants") List<ApplicantsDTO> applicants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.postTime = postTime;
        this.packageOffered = packageOffered;
        this.jobStatus = jobStatus;
        this.applicants = applicants != null ? applicants : new ArrayList<>();  // Set applicants list
    }

    // Getter for applicants with message handling
    public String getApplicantsMessage() {
        if (applicants == null || applicants.isEmpty()) {
            return "No applicants";  // Return message if no applicants
        } else {
            return "Applicants: " + applicants.size();  // Show number of applicants if available
        }
    }

    public JobDTO(String title, String description, String company, String location, String jobType, List<String> skillsRequired , LocalDateTime postTime, Long packageOffered, JobStatus jobStatus, List<ApplicantsDTO> applicants) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.postTime = postTime;
        this.packageOffered = packageOffered;
        this.jobStatus = jobStatus;
        this.applicants = applicants != null ? applicants : new ArrayList<>();
    }

    public Job toEntity() {
        // Convert ApplicantsDTO to Applicants using the ApplicantsDTO::toEntity method
        List<Applicants> applicantsList = this.applicants.stream()
                .map(ApplicantsDTO::toEntity)
                .collect(Collectors.toList());

        // Return the Job entity
        return new Job(this.title, this.description, this.company, this.location, this.jobType,
                this.skillsRequired, this.postTime, this.packageOffered, this.jobStatus, applicantsList);
    }

}
