package com.rewe.digital.applicantchallenge.model;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CategoryEntity {
    @Id
    private String id;
    private String name;
    private int version;
    private String slug;
    private String parentId;
    private boolean deleted;

    public CategoryEntity(){}

    public CategoryEntity(String id, String name, int version, String slug, String parentId, boolean deleted) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.slug = slug;
        this.parentId = parentId;
        this.deleted = deleted;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
