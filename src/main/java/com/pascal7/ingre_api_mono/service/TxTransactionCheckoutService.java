package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.TxTransactionCheckout;

import java.util.List;

public interface TxTransactionCheckoutService extends CRUDServiceTemplate<TxTransactionCheckout> {
    List<TxTransactionCheckout> getCheckoutByTransaction(TxTransaction txTransaction);
}
