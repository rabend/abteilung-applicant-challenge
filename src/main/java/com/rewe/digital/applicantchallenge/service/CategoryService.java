package com.rewe.digital.applicantchallenge.service;

import com.rewe.digital.applicantchallenge.model.CategoryDTO;
import com.rewe.digital.applicantchallenge.model.CategoryEntity;
import com.rewe.digital.applicantchallenge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getCategories() {
        return getChildren(null, categoryRepository.findAll());
    }

    private List<CategoryDTO> getChildren(String parentId, List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .filter(categoryEntity ->
                        (categoryEntity.getParentId() == null && parentId == null)
                                || (parentId != null && parentId.equals(categoryEntity.getParentId()))
                )
                .map(categoryEntity -> new CategoryDTO(
                        categoryEntity.getId(),
                        categoryEntity.getName(),
                        categoryEntity.getSlug(),
                        getChildren(categoryEntity.getId(), categoryEntities)
                ))
                .collect(Collectors.toList());
    }
}
