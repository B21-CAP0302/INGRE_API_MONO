package com.pascal7.ingre_api_mono.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_category")
public class Category {

    @Id
    @GeneratedValue(generator = "mst_category_uuid")
    @GenericGenerator(name = "mst_category_uuid", strategy = "uuid")
    private String id;
    private String category;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
