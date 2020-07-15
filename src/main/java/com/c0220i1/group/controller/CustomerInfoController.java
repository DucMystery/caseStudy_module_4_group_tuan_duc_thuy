//package com.c0220i1.group.controller;
//
//import com.c0220i1.group.model.Account;
//import com.c0220i1.group.model.CustomerInfo;
//import com.c0220i1.group.service.products.AccountService;
//import com.c0220i1.group.service.products.CustomerInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import java.util.Random;
//
//@Controller
//  public class CustomerInfoController {
//    @Autowired
//    private CustomerInfoService customerInfoService;
//    @Autowired
//    private AccountService accountService;
//    @Autowired
//    private HttpSession  httpSession;
//    @GetMapping("/customerform")
//    public ModelAndView customerInfoForm(){
//        ModelAndView modelAndView = new ModelAndView("customerinfo");
//        modelAndView.addObject("customerinfo",new CustomerInfo());
//        //Gia dinh luu duoc username
//        httpSession.setAttribute("username","user");
//        return modelAndView;
//    }
//    @PostMapping("/save_customer_info")
//    public ModelAndView saveCustomerInfo(@Validated @ModelAttribute("customerinfo") CustomerInfo customerinfo,
//                                         BindingResult bindingResult){
//        if(bindingResult.hasFieldErrors()){
//            return new ModelAndView("customerinfo");
//        } else {
//            String username = (String) httpSession.getAttribute("username");
//            Account account = accountService.findByName(username);
//            customerinfo.setAccount(account);
//            customerInfoService.save(customerinfo);
//           ModelAndView modelAndView= new ModelAndView("customerinfo");
//            modelAndView.addObject("registersuccess","You are registration infomation success ");
//            return modelAndView;
//        }
//
//
//        //khi tao ra oder thi se chuyen huong sang order
//
//    }
//}