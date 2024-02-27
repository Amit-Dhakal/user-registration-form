package com.example.UserLoginRegistration.ExceptionHandling;

import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, ValidationException.class})
     public ModelAndView handleException(Exception ex){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("error","validation error occurs please check the registration form values");
        modelAndView.setViewName("error");
        return modelAndView;
}


@ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(Exception ex){
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("error",ex.getMessage());
    modelAndView.setViewName("error");
    return modelAndView;
}






}

//Status error code ,error message
//exception catch error and display it on
//300,400,500