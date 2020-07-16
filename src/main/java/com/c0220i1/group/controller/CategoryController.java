package com.c0220i1.group.controller;

import com.c0220i1.group.model.Account;
import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.Product;
import com.c0220i1.group.service.products.AccountService;
import com.c0220i1.group.service.products.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String viewPage(@RequestParam("s") Optional<String> s, @PageableDefault(size = 20) Pageable pageable, Model model, Principal principal){
        Page<Category> categories;
        if(principal != null){
            Account account = accountService.findByName(principal.getName());
            model.addAttribute("account", account);
        }else {
            Account account = new Account();
            model.addAttribute("account",account);
        }
        if (s.isPresent()){
            categories =categoryService.findAllByNameContaining(s.get(),pageable);
        }else {
            categories =categoryService.findAll(pageable);
        }
        model.addAttribute("categories",categories);
        return "viewCate";
    }

    @GetMapping("/create-category")
    public String showCreateForm(Model model,Principal principal){
        model.addAttribute("category",new Category());
        model.addAttribute("account",accountService.findByName(principal.getName()));
        return "createCate";
    }

    @PostMapping("create-category")
    public ModelAndView addCategory(@ModelAttribute Category category,Principal principal){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("createCate");
        modelAndView.addObject("message","Thêm Mới Loại Sản Phẩm Thành Công !");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
        return modelAndView;
    }

    @GetMapping("edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id,Principal principal){
        Category category = categoryService.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("editCate");
        modelAndView.addObject("category",category);
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
        return modelAndView;
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCate(@ModelAttribute Category category,Principal principal){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("editCate");
        modelAndView.addObject("category",category);
        modelAndView.addObject("message","Sửa Thông Tin Thành Công !");
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView confirmDelete(@PathVariable Long id,Principal principal){
        ModelAndView modelAndView = new ModelAndView("deleteCategory");
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
        modelAndView.addObject("category",categoryService.findById(id).orElse(null));
        return modelAndView;
    }

    @PostMapping("/delete-category")
    public ModelAndView deleteCategory(@ModelAttribute Category category,Principal principal){
        categoryService.remove(category.getId());
        ModelAndView modelAndView = new ModelAndView("alertDeleteSuccess");
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
        modelAndView.addObject("message","Xóa Thành Công !");
        return modelAndView;
    }
}
