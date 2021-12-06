package com.pascal7.ingre_api_mono.entity;

import com.pascal7.ingre_api_mono.custom.TransactionDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tx_transaction")
public class TxTransaction {

    @Id
    @GeneratedValue(generator = "tx_transaction_uuid")
    @GenericGenerator(name = "tx_transaction_uuid", strategy = "uuid")
    private String id;

    @Transient
    private String idUser;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Transient
    private String idRecipe;

    @ManyToOne
    @JoinColumn(name = "idRecipe")
    private Recipe recipe;

    private String recipeStat;
    private Integer total;
    private String prove;
    private String stat;
    private Timestamp dateCreated;

    public TxTransaction() {
    }

    public TxTransaction(TransactionDto transactionDto) {
        this.id = transactionDto.getId();
        this.idUser = transactionDto.getIdUser();
        this.user = transactionDto.getUser();
        this.idRecipe = transactionDto.getIdRecipe();
        this.recipe = transactionDto.getRecipe();
        this.recipeStat = transactionDto.getRecipeStat();
        this.total = transactionDto.getTotal();
        this.prove = transactionDto.getProve();
        this.stat = transactionDto.getStat();
        this.dateCreated = transactionDto.getDateCreated();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdRecipe(String idRecipe) {
        this.idRecipe = idRecipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setRecipeStat(String recipeStat) {
        this.recipeStat = recipeStat;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public User getUser() {
        return user;
    }

    public String getIdRecipe() {
        return idRecipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getRecipeStat() {
        return recipeStat;
    }

    public Integer getTotal() {
        return total;
    }

    public String getStat() {
        return stat;
    }

    public String getProve() {
        return prove;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }
}
