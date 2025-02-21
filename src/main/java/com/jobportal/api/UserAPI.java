package com.jobportal.api;

import com.jobportal.dto.UserDTO;
import com.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //this is created to handle the rest api calls
@CrossOrigin  //Cross origin resource sharing
@RequestMapping("/users")
public class UserAPI {
    @Autowired // No need t create an object whenever it is needed it will automatically creats
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        userDTO=userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

}
