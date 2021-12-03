package com.pascal7.ingre_api_mono.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name ="mst_ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "ingredient_uuid")
    @GenericGenerator(name = "ingredient_uuid", strategy = "uuid")
    private String id;

    private String name;
    private Integer stock;
    private Integer price;
    private Timestamp date;

    public Ingredient() {
    }

    public Ingredient(String name, Integer stock, Integer price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.date = getDate();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
