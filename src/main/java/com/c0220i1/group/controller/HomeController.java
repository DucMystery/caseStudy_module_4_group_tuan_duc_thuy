package com.c0220i1.group.controller;


import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.Product;
import com.c0220i1.group.service.products.CategoryService;
import com.c0220i1.group.service.products.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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
}
