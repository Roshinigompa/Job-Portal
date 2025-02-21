package com.jobportal.dto;

import com.jobportal.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    // Explicit getter for password in case Lombok doesn't generate it
    @Getter
    private String password; // This should be the hashed password
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public UserDTO(String name, String email, String password, AccountType accountType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }
    // Convert DTO to Entity
    public User toEntity(String hashedPassword) {
        return new User(this.id, this.name, this.email, hashedPassword, this.accountType);
    }
}
