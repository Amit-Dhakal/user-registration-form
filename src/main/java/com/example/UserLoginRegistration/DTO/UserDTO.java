package com.example.UserLoginRegistration.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserDTO {
    private int id;
    @NotBlank(message = "First name cannot be blank or null")
    private String firstName;
    @NotBlank(message = "First name cannot be blank or null")
    private String lastName;
    @NotBlank(message = "First name cannot be blank or null")
    private String password;
    @NotBlank(message = "First name cannot be blank or null")
    private String email;

}
