package com.jobportal.api;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Data
@RestController //Marks this class as a Spring REST Controller, meaning it handles HTTP requests and responses.
//Internally, it combines @Controller and @ResponseBody, eliminating the need to annotate each method with @ResponseBody
@CrossOrigin(origins = "http://localhost:5173") //Cross-origin resource sharing.allowing frontend applications (e.g., Angular, React) running on different domains to communicate with this backend API.
@Validated
@RequestMapping("/users")
public class  UserAPI {
   // No need t create an object whenever it is needed it will automatically create. Automatically injects an instance of UserService into this class.
   @Autowired
    private UserService userService; //Declares a field of type UserService to call business logic methods


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = userService.registerUser(userDTO);

        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
        // Return complete user details (fixing response)
        // Ensure userDTO.getEmail() exists
        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserDTO userDTO) {
            Map<String, Object> response = userService.loginUser(userDTO);

            if (response.containsKey("error")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }



    @GetMapping("/findByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findUserByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}


//ResponseEntity<UserDTO> → Wraps the response with user data and an HTTP status code.
//registerUser(UserDTO userDTO) → Receives user details from the request.
//        userService.registerUser(userDTO); → Calls the service layer to save the user details.
//return new ResponseEntity<>(userDTO, HttpStatus.CREATED); →
//Returns the saved user data.
//        HttpStatus.CREATED (201) means the user was successfully registered.
//Overall Flow of the Code
//A user sends a POST request to /users/register with their details.
//The request is received by UserAPI (Controller).
//The controller calls UserService to handle the business logic.
//UserService saves user details and returns the data.
//The controller sends the response back to the user with a 201 Created status.