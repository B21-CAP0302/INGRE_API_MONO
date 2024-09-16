package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.TransactionDto;

import java.util.List;

public interface TxTransactionService extends CRUDServiceTemplate<TransactionDto>{
    TransactionDto checkTransactionStatusToOnDelivery(String id);
    TransactionDto checkTransactionStatusToDone(String id);
    TransactionDto cancelTransactionStatusFromOnDelivery(String id);
    TransactionDto cancelTransactionStatusFromDone(String id);
    List<TransactionDto> getUserTransaction(String id);
    TransactionDto getTransactionById(String userId, String id);
}
