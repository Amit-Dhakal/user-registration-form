package com.example.UserLoginRegistration.mapper;

import com.example.UserLoginRegistration.DTO.UserDTO;
import com.example.UserLoginRegistration.model.User;

public class MapperService {

    public UserDTO entityToDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }


    public User dtoToEntity(UserDTO userDTO){
       User user=new User();
user.setFirstName(userDTO.getFirstName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
return user;

    }



}
