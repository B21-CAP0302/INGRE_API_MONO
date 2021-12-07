package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TxTransactionRepository extends JpaRepository<TxTransaction, String> {
    List<TxTransaction> findByUser(User user);
}
