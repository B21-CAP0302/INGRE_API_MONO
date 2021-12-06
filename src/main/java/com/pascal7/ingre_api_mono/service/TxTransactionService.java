package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.TransactionDto;

public interface TxTransactionService extends CRUDServiceTemplate<TransactionDto>{
    TransactionDto checkTransactionStatusToOnDelivery(String id);
    TransactionDto checkTransactionStatusToDone(String id);
}
