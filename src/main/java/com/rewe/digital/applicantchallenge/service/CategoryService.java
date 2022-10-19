package com.rewe.digital.applicantchallenge.service;

import com.rewe.digital.applicantchallenge.model.CategoryDTO;
import com.rewe.digital.applicantchallenge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getCategories() {
        throw new RuntimeException("Not yet implemented"); // TODO
    }
}
