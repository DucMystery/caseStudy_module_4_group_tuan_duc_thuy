package com.c0220i1.group.controller;


import com.c0220i1.group.model.Account;
import com.c0220i1.group.service.products.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public ModelAndView registerPage(){
        Account user = new Account();
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/register")
    public String PostRegisterPage(@ModelAttribute(value = "user") Account user){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        accountService.save(user);
        return "UserRegisterSuccess";
    }

}

