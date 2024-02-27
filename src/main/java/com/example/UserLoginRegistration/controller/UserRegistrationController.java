package com.example.UserLoginRegistration.controller;
import com.example.UserLoginRegistration.DTO.UserDTO;
import com.example.UserLoginRegistration.mapper.MapperService;
import com.example.UserLoginRegistration.model.User;
import com.example.UserLoginRegistration.service.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
public class UserRegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("userDTO",new UserDTO());
    return "register";
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('USERS')")
    public String redirectRegister(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "User is not saved");
            return "redirect:/error";
        }
        try {
            model.addAttribute("userDTO",userDTO);
            MapperService mapperService = new MapperService();
            User user = mapperService.dtoToEntity(userDTO);
            userRepository.save(user);
        } catch (Exception ex) {
           redirectAttributes.addFlashAttribute("error", "User is not saved");
            return "redirect:/error";
        }
        redirectAttributes.addFlashAttribute("success", "User saved successfully");
        return "redirect:/login-page";
    }

    @GetMapping("/login-page")
    public String loginUser(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "login-page";
    }

   @PostMapping("/login-page")
    public String login(@ModelAttribute("userDTO") UserDTO userDTO,Model model){
       model.addAttribute("userDTO",userDTO);
       MapperService mapperService =new MapperService();
      List<User> listUser=userRepository.findAllByEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
       Optional<List<User>> optionalUser=Optional.ofNullable(listUser);
       if(optionalUser.isPresent() && !optionalUser.get().isEmpty()){
           model.addAttribute("viewAllUser",listUser);
           return "view";
       }
       else {
           model.addAttribute("loginFailure", true);
           return "redirect:/login-page";
       }

}
}



//
//after login success
//view all registered user data
//view page create
//fetch data
//
//error handling
//button colour chane to green when validated
//red when not validated

//tasks for today
//exception handling
//button colour change after validation
//redirect to view page after login success
//thymeleaf fragmentaion navbar
//validation ,global custom exception handling



//todo
//navigation fragments
//button colour
//