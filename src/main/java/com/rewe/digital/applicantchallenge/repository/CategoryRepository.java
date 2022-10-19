package com.rewe.digital.applicantchallenge.repository;

import com.rewe.digital.applicantchallenge.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}
