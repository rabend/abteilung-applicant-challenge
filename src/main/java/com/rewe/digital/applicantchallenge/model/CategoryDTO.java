package com.rewe.digital.applicantchallenge.model;

import java.util.List;

public class CategoryDTO {
    private String id;
    private String name;
    private String slug;

    private List<CategoryDTO> children;

    public CategoryDTO() {
    }

    public CategoryDTO(String id, String name, String slug, List<CategoryDTO> children) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }
}
