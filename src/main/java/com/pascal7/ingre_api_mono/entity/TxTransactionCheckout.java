package com.pascal7.ingre_api_mono.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.custom.TransactionDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tx_transaction_checkout")
public class TxTransactionCheckout {

    @Id
    @GeneratedValue(generator = "tx_transaction_checkout_uuid")
    @GenericGenerator(name = "tx_transaction_checkout_uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idTransaction")
    @JsonIgnore
    private TxTransaction transaction;

    @Transient
    private String idIngredient;

    @ManyToOne
    @JoinColumn(name = "idIngredient")
    @JsonIgnore
    private Ingredient ingredient;

    private Integer qty;

    public TxTransactionCheckout() {
    }

    public TxTransactionCheckout(String id, TxTransaction transaction, Ingredient ingredient, Integer qty) {
        this.id = id;
        this.transaction = transaction;
        this.ingredient = ingredient;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TxTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(TxTransaction transaction) {
        this.transaction = transaction;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
