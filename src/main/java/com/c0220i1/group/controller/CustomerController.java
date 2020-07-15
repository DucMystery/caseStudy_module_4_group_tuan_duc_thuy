package com.c0220i1.group.controller;


import com.c0220i1.group.model.Account;
import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.CustomerInfo;
import com.c0220i1.group.model.Product;
import com.c0220i1.group.service.products.AccountService;
import com.c0220i1.group.service.products.CategoryService;
import com.c0220i1.group.service.products.CustomerInfoService;
import com.c0220i1.group.service.products.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class CustomerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String viewPage(@RequestParam("s")Optional<String>s, @PageableDefault(size = 9) Pageable pageable, Model model){
        Page<Product> products;
        if (s.isPresent()){
            products =productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products =productService.findAll(pageable);
        }
        model.addAttribute("products",products);
        return "view";
    }

    @GetMapping("/category/{id}")
    public ModelAndView showProductsByCate(@PathVariable String id,@PageableDefault(size = 9)Pageable pageable){
        Long cateId = Long.parseLong(id);
        Category category = categoryService.findById(cateId).orElse(null);
        Page<Product> products =productService.findAllByCategorySetEquals(category,pageable);
        ModelAndView modelAndView = new ModelAndView("category");
        modelAndView.addObject("products",products);
        modelAndView.addObject("id",id);

        return modelAndView;
    }

    @GetMapping("/customerform")
    public ModelAndView customerInfoForm(Principal principal){
        ModelAndView modelAndView = new ModelAndView("customerinfo");
        modelAndView.addObject("customerinfo",new CustomerInfo());
        return modelAndView;
    }
    @PostMapping("/save_customer_info")
    public ModelAndView saveCustomerInfo(@Validated @ModelAttribute("customerinfo") CustomerInfo customerinfo, Principal principal,
                                         BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("customerinfo");
        } else {
            String username = principal.getName();
            Account account = accountService.findByName(username);
            customerinfo.setAccount(account);
            customerInfoService.save(customerinfo);
            ModelAndView modelAndView= new ModelAndView("customerinfo");
            modelAndView.addObject("registersuccess","You are registration infomation success ");
            return modelAndView;
        }



    }
}
