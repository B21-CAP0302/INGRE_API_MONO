package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.custom.ResponseStat;
import com.pascal7.ingre_api_mono.custom.TransactionDto;
import com.pascal7.ingre_api_mono.custom.TransactionStat;
import com.pascal7.ingre_api_mono.service.TxTransactionService;
import com.pascal7.ingre_api_mono.utils.BankString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TxTransactionService txTransactionService;

    @PostMapping("/api/user/transaction")
    public TransactionDto userBuyProduct(@RequestBody TransactionDto transactionDto){
        transactionDto.setStat(TransactionStat.WAITING.getValue());
        transactionDto.setRecipeStat("Complete");
        return txTransactionService.create(transactionDto);
    }

    @GetMapping("/api/user/transaction/{id}")
    public List<TransactionDto> getUserTransaction(@PathVariable String id){
        return txTransactionService.getUserTransaction(id);
    }

    @GetMapping("/api/user/{userId}/transaction/{id}")
    public TransactionDto getTransactionById(@PathVariable String userId, @PathVariable String id){
        return txTransactionService.getTransactionById(userId, id);
    }

    @GetMapping("/api/admin/transaction/{id}")
    public List<TransactionDto> getUserTransactionByAdmin(@PathVariable String id){
        return txTransactionService.getUserTransaction(id);
    }

    @GetMapping("/api/admin/transaction")
    public List<TransactionDto> getAllTransaction(){
        return txTransactionService.getAll();
    }

    @PutMapping("/api/admin/transaction/payment/success/{id}")
    public ResponseStat paymentSuccess(@PathVariable String id){
        txTransactionService.checkTransactionStatusToOnDelivery(id);
        return new ResponseStat(id, BankString.success);
    }

    @PutMapping("/api/admin/transaction/delivery/success/{id}")
    public ResponseStat deliverySuccess(@PathVariable String id){
        txTransactionService.checkTransactionStatusToDone(id);
        return new ResponseStat(id, BankString.success);
    }

    @PutMapping("/api/admin/transaction/deliver/cancel/{id}")
    public ResponseStat deliveryCancel(@PathVariable String id){
        txTransactionService.cancelTransactionStatusFromOnDelivery(id);
        return new ResponseStat(id, BankString.success);
    }

    @PutMapping("/api/admin/transaction/done/cancel/{id}")
    public ResponseStat doneCancel(@PathVariable String id){
        txTransactionService.cancelTransactionStatusFromDone(id);
        return new ResponseStat(id, BankString.success);
    }

    @DeleteMapping("/api/admin/transaction/{id}")
    public void deleteTransaction(@PathVariable String id){
        txTransactionService.delete(id);
    }
}
