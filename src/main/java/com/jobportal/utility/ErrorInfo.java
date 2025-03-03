package com.jobportal.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorInfo {
    private String errorMessage;
    private Integer errorCode;
    private LocalDateTime timestamp;

    public ErrorInfo() {
    }

    // All-args constructor
    public ErrorInfo(String errorMessage, Integer errorCode, LocalDateTime timestamp) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
