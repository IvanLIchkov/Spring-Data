package com.example.wokshop.controller;

import com.example.wokshop.models.dto.RegistrationDto;
import com.example.wokshop.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/register")
    public ModelAndView registerView(RegistrationDto registrationDto){

        ModelAndView modelAndView = new ModelAndView("user/register");
        modelAndView.addObject("registrationDto", registrationDto);
        return modelAndView;
    }

    @PostMapping(value = "users/register")
    public String doRegister(@Valid RegistrationDto dto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/register";
        }

        this.userService.register(dto);

        return "user/login";
    }
}
