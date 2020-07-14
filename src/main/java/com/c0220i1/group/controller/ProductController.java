package com.c0220i1.group.controller;

import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.Product;
import com.c0220i1.group.service.products.CategoryService;
import com.c0220i1.group.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ModelAndView showHome(@RequestParam("s")Optional<String> s, @PageableDefault(size = 12)Pageable pageable){
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products =productService.findAll(pageable);
        }
        ModelAndView  modelAndView = new ModelAndView("view");
        modelAndView.addObject("products",products);
        return modelAndView;

    }
}
