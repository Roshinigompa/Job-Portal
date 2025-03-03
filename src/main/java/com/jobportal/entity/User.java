package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobportal.dto.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Data

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @ToString.Exclude
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    @JsonProperty("account_type")
    private AccountType accountType;

    public User() {
    }

    // Constructor (remove encoding from here)
    public User(String name, String email, String password, AccountType accountType) {
        this.name = name;
        this.email = email;
        this.setPassword(password); // Use setter to ensure encoding
        this.accountType = accountType;
    }

    // Your existing constructor with ID (for updating user)
    public User(Long id, String name, String email, String password, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    // Ensure password is always encoded
    //public void setPassword(String password) {
        //this.password = new BCryptPasswordEncoder().encode(password);
    //}
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
