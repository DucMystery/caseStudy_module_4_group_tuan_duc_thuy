package com.c0220i1.group.controller;


import com.c0220i1.group.model.*;
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
import java.util.HashMap;
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
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String viewPage(@RequestParam("s")Optional<String>s, @PageableDefault(size = 9) Pageable pageable, Model model, Principal principal){
        Page<Product> products;
        if(principal!= null){
            Account account = accountService.findByName(principal.getName());
            model.addAttribute("account", account);
        }else {
            Account account = new Account();
            model.addAttribute("account",account);
        }
        if (s.isPresent()){
            products =productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products =productService.findAll(pageable);
        }
//        Thuy them so san pham trong gio hang
        HashMap<Long, CartLine> carts= (HashMap<Long, CartLine>) httpSession.getAttribute("mycart");
        if(carts==null){
            model.addAttribute("products",products);
        } else {
            int total= carts.size();
            model.addAttribute("total",total);
            model.addAttribute("products",products);
        }

//        Thuy da them 3 dong code tren vao code cua Duc

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
                                         BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("customerinfo");
        } else {
            String username = principal.getName();
            Account account = accountService.findByName(username);
            customerinfo.setAccount(account);
            customerInfoService.save(customerinfo);
            ModelAndView modelAndView = new ModelAndView("redirect:/");
//            modelAndView.addObject("registersuccess", "You are registration infomation success ");
            return modelAndView;
        }
    }

}
