package com.jobportal.dto;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobportal.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Getter
@NoArgsConstructor

public class UserDTO {

    private Long id;
    @NotBlank(message ="Name is null or blank")
    private String name;
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is null or blank")
    private String email;
     @NotBlank(message = "Password is null or Blank")
     @Size(min = 8, message = "Password must be at least 8 characters long")
     @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Invalid password: must contain at least one uppercase letter, one number, and one special character"
    )
    private String password;
    // The password here could be a plain password or a hashed password
    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonCreator
    public UserDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("account_type") AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public UserDTO(
                   String name,
                   String email,
                   String password,
                   @JsonProperty("account_type") AccountType accountType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public UserDTO(
            String email,
            String password){
        this.email = email;
        this.password = password;
    }
    // Convert DTO to Entity

    // Assign default value of id to null when it's not present
    public User toEntity() {
        return new User(this.name, this.email,this.password, this.accountType);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
