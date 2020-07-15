package com.c0220i1.group.formatter;

import com.c0220i1.group.model.Category;
import com.c0220i1.group.service.products.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<Category> {
    private CategoryService categoryService;
    @Autowired
    public CategoryFormatter(CategoryService categoryService){
        this.categoryService =categoryService;
    }
    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text)).orElse(null);
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[ "+object.getId()+" , "+object.getName()+" ]";
    }
}
