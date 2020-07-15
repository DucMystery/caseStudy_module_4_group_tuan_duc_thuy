package com.c0220i1.group.controller;


import com.c0220i1.group.model.Account;
import com.c0220i1.group.model.RoLe;
import com.c0220i1.group.service.products.AccountService;
import com.c0220i1.group.service.products.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @Autowired
    RoleService roleService;

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public ModelAndView registerPage(){
        Account account = new Account();
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("account", account);
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView PostRegisterPage(@ModelAttribute(value = "account") Account account){
        ModelAndView mv = new ModelAndView("view");
        String password = passwordEncoder.encode(account.getPassword());
        RoLe role = new RoLe();
        role.setName("ROLE_USER");
        roleService.save(role);
        account.setPassword(password);
        account.setRole(role);
        accountService.save(account);
        return mv;
    }

}
