package com.jobportal.service;

import ch.qos.logback.core.boolex.Matcher;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service(value="UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");



    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Map<String, Object> registerUser(UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();

        if (!EMAIL_PATTERN.matcher(userDTO.getEmail()).matches()) {
            response.put("error", "Invalid email format");
            return response;
        }

        // Encode the password using passwordEncoder
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = userDTO.toEntity();
        user.setPassword(encodedPassword); // Ensure password is encoded


        try {
            user = userRepository.save(user);
            response.put("success", true);
            response.put("message", "User created successfully!");
        }  catch (Exception e) {
            response.put("error", true);
            if (e.getMessage().contains("duplicate key value violates unique constraint")) {
                response.put("error", "Duplicate entry. email already exists! - " +  userDTO.getEmail());
                return response;
            }
            response.put("message", "An internal error occurred: " + e.getMessage());
        }

        return response;
    }
    @Override
    public Map<String, Object> loginUser(UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();

        User user = findUserByEmail(userDTO.getEmail());

        if (user == null) {
            response.put("error", "Invalid email or password");
            return response;
        }

        //User user = userOptional.get();

        // Compare entered password with hashed password

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            response.put("error", "Invalid email or password");
            return response;
        }

        response.put("message", "Login successful");
        response.put("userId", user.getId());
        response.put("email", user.getEmail());
        response.put("accountType", user.getAccountType()); // Optionally send role

        return response;
    }

    @Override

    public User findUserByEmail(String email) {
       User resp =  userRepository.findByEmail(email); // Directly return the Optional from the repository
        return resp;
    }


//    @Override
//    public User loadUserByUsername(String name) throws UsernameNotFoundException{
//        Optional<User> userOptional = userRepository.findByUsername(name);
//        if(userOptional.isPresent()) {
//            var user = userOptional.get();
//            return User.builder()    }
//    }



}
