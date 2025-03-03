package com.jobportal.service;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService  {
    public Map<String, Object> registerUser(UserDTO userDTO);

    User findUserByEmail(String email);
    Map<String, Object> loginUser(UserDTO userDTO);
}


//An interface in Java defines a contract. It declares the methods that a class implementing this interface must provide.
//The UserService interface defines the method registerUser(), which will be implemented by the UserServiceImpl class.
//The method will take a UserDTO (Data Transfer Object) as input and return a UserDTO as output.