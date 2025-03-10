package com.jobportal.entity;

import com.jobportal.dto.Experience;

import java.security.cert.Certificate;
import java.util.List;

public class Profile {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String companyName;
    private String companyAddress;
    private String about;
    private List<String>skills;
    private List<Experience>experiences;
    private List<Certificate>certificates;
}
