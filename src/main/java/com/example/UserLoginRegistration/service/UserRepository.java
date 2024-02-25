package com.example.UserLoginRegistration.service;

import com.example.UserLoginRegistration.model.User;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Id> {
    List<User> findAllById(int id);
      List<User> findAllByEmailAndPassword(String email,String password);
    Optional<User> findAllByFirstName(String firstName);


}




