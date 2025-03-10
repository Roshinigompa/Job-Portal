package com.jobportal.api;

import com.jobportal.dto.JobDTO;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@Data
@Validated
@RequestMapping("/jobs")
public class JobAPI {
    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createJob(@RequestBody @Valid JobDTO jobDTO) {
        Map<String, Object> response = jobService.createJob(jobDTO);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get job by Id
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("id") Long id) {
        Optional<JobDTO> jobDTO = jobService.getJobById(id);
        return jobDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Get all jobs
    @GetMapping("/getAll")
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
//
//    //Update the job by id
    @PutMapping("/update/{jobId}")
    public ResponseEntity<Map<String, Object>> updateJob(@PathVariable Long jobId, @RequestBody @Valid JobDTO jobDTO) {
        Map<String, Object> response = jobService.updateJob(jobId, jobDTO);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }
//
//    // Delete a Job
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Map<String, Object>> deleteJob(@PathVariable Long jobId) {
        Map<String, Object> response = jobService.deleteJob(jobId);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
  }
