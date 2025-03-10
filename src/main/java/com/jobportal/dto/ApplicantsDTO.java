package com.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobportal.entity.Applicants;
import com.jobportal.entity.Job;  // Import the Job entity
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApplicantsDTO {

    private Long applicantId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private JobDTO job;  // JobDTO to include job information in applicant's details

    // Constructor for mapping JSON to DTO
    @JsonCreator
    public ApplicantsDTO(
            @JsonProperty("applicantId") Long applicantId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("website") String website,
            @JsonProperty("resume") String resume,
            @JsonProperty("coverLetter") String coverLetter,
            @JsonProperty("timestamp") LocalDateTime timestamp,
            @JsonProperty("status") ApplicationStatus status,
            @JsonProperty("job") JobDTO job) {

        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.timestamp = timestamp;
        this.status = status;
        this.job = job;
    }

    // Convert ApplicantsDTO to Applicants entity
    public Applicants toEntity() {
        Job jobEntity = null;
        if (this.job != null) {
            jobEntity = this.job.toEntity();  // Convert JobDTO to Job entity
        }

        return new Applicants(
                this.applicantId,
                this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber,
                this.website,
                this.resume,
                this.coverLetter,
                this.timestamp,
                this.status,
                jobEntity  // Pass the Job entity
        );
    }

}
