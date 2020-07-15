package com.c0220i1.group.controller;

import com.c0220i1.group.model.Product;
import com.c0220i1.group.service.products.CategoryService;
import com.c0220i1.group.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    public String viewPage(@RequestParam("s")Optional<String>s, @PageableDefault(size = 9) Pageable pageable, Model model){
        Page<Product> products;
        if (s.isPresent()){
            products =productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products =productService.findAll(pageable);
        }
        model.addAttribute("products",products);
        return "viewAdmin";
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("categories",categoryService.findAll());
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView addProduct(@ModelAttribute("product")Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("categories",categoryService.findAll());
        modelAndView.addObject("message","Thêm Thành Công !");
        return modelAndView;
    }
}
