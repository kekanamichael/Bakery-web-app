package com.techgiants.topiefor.model;

import com.techgiants.topiefor.exception.ArgumentException;
import java.util.Objects;

public class Category {

    private String name;
    private int categoryId;
    private String description;
    private boolean isActive;

    public Category() {
    }
    public Category(String name, int categoryId, String description, boolean isActive) throws ArgumentException {
        setName(name);
        this.categoryId = categoryId;
        setDescription(description);
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ArgumentException {
        if(name==null || name.isEmpty())
            throw new ArgumentException("Category name cannot be null or empty");
        
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws ArgumentException {
        if(description==null || description.isEmpty())
            throw new ArgumentException("Description of the Category cannot be null");
        
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", categoryId=" + categoryId + ", description=" + description + ", isActive=" + isActive + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + this.categoryId;
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + (this.isActive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        return true;
    }
    

}
