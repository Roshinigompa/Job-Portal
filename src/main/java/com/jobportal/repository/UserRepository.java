package com.jobportal.repository;

import com.jobportal.dto.AccountType;
import com.jobportal.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom Query to insert dummy data
    //@Query(value = "SELECT u FROM User u where u.email= :email")
    User findByEmail(String email);



    //@Transactional
    // @Modifying
   // @Query("INSERT INTO User (name, email, password, accountType) VALUES (:name, :email, :password, :accountType)")
    //void insertUser(String name, String email, String password, AccountType accountType);


    // You can also define a method to add users
//    default void addDummyUsers() {
//        // Manually call insertUser with values
//        insertUser("John Doe", "john.doe@example.com", "password123", AccountType.APPLICANT);
//        insertUser("Jane Smith", "jane.smith@example.com", "password456", AccountType.RECRUITER);
//    }

}

//UserRepository is a repository interface that allows you to interact with the User entity
//        and provides built-in methods to perform common database operations.
//By extending JpaRepository<User, Long>, you get access to many useful methods like save(),
//findById(),
//delete(), etc., without needing to write SQL queries.