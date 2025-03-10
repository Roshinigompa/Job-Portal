package com.jobportal.entity;

import com.jobportal.dto.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor  // Default constructor
  // Constructor with all fields
public class Applicants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;  // Primary key for Applicants

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;  // Timestamp of application

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;  // Status of the application

    @ManyToOne  // Many applicants can apply to one job
    @JoinColumn(name = "job_id")  // Foreign key column in Applicants linking to Job
    private Job job;  // The job the applicant applied for

    // Constructor to match DTO
    public Applicants(Long applicantId, String firstName, String lastName,
                      String email, String phoneNumber, String website, String resume,
                      String coverLetter, LocalDateTime timestamp, ApplicationStatus status, Job job) {
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
}
