package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.TxTransactionCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TxTransactionCheckoutRepository extends JpaRepository<TxTransactionCheckout, String> {
    List<TxTransactionCheckout> findByTransaction(TxTransaction txTransaction);
}
