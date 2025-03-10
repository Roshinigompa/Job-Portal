package com.jobportal.entity;

import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;
  // Update if you have a DTO for Applicants
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String company;
    private String location;
    private String jobType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> skillsRequired;

    // List of applicants for this job
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Applicants> applicants = new ArrayList<>();

    private LocalDateTime postTime;
    private Long packageOffered;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;


    //Constructor for New job

    public  Job(String title, String description, String company, String location, String jobType, List<String> skillsRequired, LocalDateTime postTime, Long packageOffered, JobStatus jobStatus, List<Applicants> applicants) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.postTime = LocalDateTime.now();
        this.packageOffered = 0L;
        this.jobStatus = jobStatus;
        this.applicants = applicants;

    }

    // Constructor for existing jon


    // Convert Entity to DTO
//    public JobDTO toDTO() {
//        return new JobDTO(
//                this.id,
//                this.title,
//                this.description,
//                this.company,
//                this.location,
//                this.jobType,
//                this.skillsRequired,
//                this.postTime,
//                this.packageOffered,
//                this.jobStatus,
//                // Pass the applicants list to DTO instead of the number of applicants
//                this.applicants != null ? this.applicants.stream().map(Applicants::toDTO).collect(Collectors.toList()) : null
//        );
//    }
}
