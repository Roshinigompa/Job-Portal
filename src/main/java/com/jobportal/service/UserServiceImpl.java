package com.jobportal.service;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Encrypt password before saving
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Convert DTO to Entity with hashed password
        User user = userDTO.toEntity(hashedPassword);

        // Save user entity
        userRepository.save(user);

        // Return converted DTO (without password exposure)
        return user.toDTO();
    }
}
