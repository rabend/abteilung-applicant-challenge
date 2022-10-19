package com.rewe.digital.applicantchallenge.controller;

import com.rewe.digital.applicantchallenge.model.CategoryDTO;
import com.rewe.digital.applicantchallenge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        try {
            return ResponseEntity.ok(categoryService.getCategories());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
