package com.pascal7.ingre_api_mono.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.TxTransactionCheckout;
import com.pascal7.ingre_api_mono.entity.User;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

public class TransactionDto {

    private String id;

    @NotBlank
    private String idUser;

    @JsonIgnore
    private User user;

    @NotBlank
    private String idRecipe;

    @JsonIgnore
    private Recipe recipe;

    @JsonIgnore
    private String recipeStat;
    @NotBlank
    private Integer total;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    @JsonIgnore
    private String prove;
    private String stat;
    @JsonIgnore
    private Timestamp dateCreated;
    @NotBlank
    private List<TxTransactionCheckout> ingredient;

    public TransactionDto() {
    }

    public TransactionDto(TxTransaction txTransaction, List<TxTransactionCheckout> ingredient) {
        this.id = txTransaction.getId();
        this.idUser = txTransaction.getIdUser();;
        this.user = txTransaction.getUser();
        this.idRecipe = txTransaction.getIdRecipe();
        this.recipe = txTransaction.getRecipe();
        this.recipeStat = txTransaction.getRecipeStat();
        this.total = txTransaction.getTotal();
        this.prove = txTransaction.getProve();
        this.phoneNumber = txTransaction.getPhoneNumber();
        this.address = txTransaction.getAddress();
        this.stat = txTransaction.getStat();
        this.dateCreated = txTransaction.getDateCreated();
        this.ingredient = ingredient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(String idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeStat() {
        return recipeStat;
    }

    public void setRecipeStat(String recipeStat) {
        this.recipeStat = recipeStat;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProve() {
        return prove;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    public List<TxTransactionCheckout> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<TxTransactionCheckout> ingredient) {
        this.ingredient = ingredient;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
