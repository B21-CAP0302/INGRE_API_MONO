package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.TxTransactionCheckout;
import com.pascal7.ingre_api_mono.repository.TxTransactionCheckoutRepository;
import com.pascal7.ingre_api_mono.repository.TxTransactionRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TxTransactionCheckoutServiceImpl implements TxTransactionCheckoutService {

    @Autowired
    TxTransactionCheckoutRepository txTransactionCheckoutRepository;

    @Autowired
    Helper helper;

    @Override
    public TxTransactionCheckout create(TxTransactionCheckout txTransactionCheckout) {
        helper.validateIdIsNull(txTransactionCheckout.getId());
        return txTransactionCheckoutRepository.save(txTransactionCheckout);
    }

    @Override
    public TxTransactionCheckout getById(String id) {
        validateIdIsExist(id);
        return txTransactionCheckoutRepository.getById(id);
    }

    private void validateIdIsExist(String id) {
        if(!txTransactionCheckoutRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    @Override
    public TxTransactionCheckout update(TxTransactionCheckout txTransactionCheckout) {
        validateIdIsExist(txTransactionCheckout.getId());
        return txTransactionCheckoutRepository.save(txTransactionCheckout);
    }

    @Override
    public List<TxTransactionCheckout> getAll() {
        return txTransactionCheckoutRepository.findAll();
    }

    @Override
    public void delete(String id) {
        validateIdIsExist(id);
        txTransactionCheckoutRepository.delete(getById(id));
    }

    @Override
    public List<TxTransactionCheckout> getCheckoutByTransaction(TxTransaction txTransaction) {
        return txTransactionCheckoutRepository.findByTransaction(txTransaction);
    }
}
