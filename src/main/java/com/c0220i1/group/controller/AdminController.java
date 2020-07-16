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
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView addProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("categories",categoryService.findAll());
        modelAndView.addObject("message","Thêm Thành Công !");
        return modelAndView;
    }

    @GetMapping("/showList")
    public String showList(@RequestParam("s")Optional<String>s, @PageableDefault(size = 20) Pageable pageable, Model model){
        Page<Product> products;
        if (s.isPresent()){
            products =productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products =productService.findAll(pageable);
        }
        model.addAttribute("products",products);
        return "listProduct";
    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable("id")Long id){
        Product product = productService.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("editProduct");
        modelAndView.addObject("product",product);
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product")Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("editProduct");
        modelAndView.addObject("product",product);
        modelAndView.addObject("message","Thay Đổi Thông Tin Thành Công !");
        return modelAndView;
    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id")Long id){
        Product product = productService.findById(id).orElse(null);
            ModelAndView modelAndView = new ModelAndView("deleteProduct");
            modelAndView.addObject("product",product);
            return modelAndView;

    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product")Product product){
        productService.remove(product.getId());
        return "redirect:/showList";
    }


}
