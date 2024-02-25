package com.example.UserLoginRegistration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER_TABLE")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;
}
