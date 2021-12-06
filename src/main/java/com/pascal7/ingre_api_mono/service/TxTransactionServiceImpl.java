package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.TransactionDto;
import com.pascal7.ingre_api_mono.custom.TransactionStat;
import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.TxTransaction;
import com.pascal7.ingre_api_mono.entity.TxTransactionCheckout;
import com.pascal7.ingre_api_mono.repository.TxTransactionRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TxTransactionServiceImpl implements TxTransactionService {

    @Autowired
    TxTransactionRepository txTransactionRepository;

    @Autowired
    TxTransactionCheckoutService txTransactionCheckoutService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    Helper helper;

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        helper.validateIdIsNull(transactionDto.getId());
        TxTransaction txTransaction = getTxTransactionWithCheckout(transactionDto, false);
        txTransaction.setId(txTransaction.getId());
        txTransaction.setRecipe(new Recipe(recipeService.getById(txTransaction.getIdRecipe())));
        return transactionDto;
    }

    @Override
    public TransactionDto getById(String id) {
        validateIdIsExist(id);
        TxTransaction txTransaction = getFullTxTransaction(id);
        return new TransactionDto(
                txTransaction,
                txTransactionCheckoutService.getCheckoutByTransaction(txTransaction)
        );
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        validateIdIsExist(transactionDto.getId());
        getTxTransactionWithCheckout(transactionDto, true);
        return transactionDto;
    }

    @Override
    public List<TransactionDto> getAll() {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        txTransactionRepository.findAll().forEach(
                txTransaction -> {
                    transactionDtos.add(
                            new TransactionDto(
                                    getFullTxTransaction(txTransaction.getId()),
                                    getFullTxTransactionCheckoutList(txTransaction)
                            )
                    );
                }
        );
        return transactionDtos;
    }

    @Override
    public void delete(String id) {
        TxTransaction txTransaction = new TxTransaction(getById(id));
        deleteCheckoutByTransaction(txTransaction);
        txTransactionRepository.delete(txTransaction);
        throw new ResponseStatusException(HttpStatus.ACCEPTED, BankString.success);
    }

    @Override
    public TransactionDto checkTransactionStatusToOnDelivery(String id) {
        return setStatTransaction(id, TransactionStat.ON_DELIVERY.getValue());
    }

    @Override
    public TransactionDto checkTransactionStatusToDone(String id) {
        return setStatTransaction(id, TransactionStat.DONE.getValue());
    }

    private TxTransaction getFullTxTransaction(String id) {
        TxTransaction txTransaction = txTransactionRepository.getById(id);
        txTransaction.setIdRecipe(txTransaction.getRecipe().getId());
        txTransaction.setIdUser(txTransaction.getUser().getId());
        return txTransaction;
    }

    private void validateIdIsExist(String id) {
        if(!txTransactionRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    private TxTransaction getTxTransactionWithCheckout(TransactionDto transactionDto, Boolean delete) {
        TxTransaction txTransaction = txTransactionRepository.save(new TxTransaction(transactionDto));
        if(delete){
            deleteCheckoutByTransaction(txTransaction);
        }
        transactionDto.getIngredient().forEach(
                txTransactionCheckout -> {
                    txTransactionCheckout.setTransaction(txTransaction);
                    txTransactionCheckout.setIngredient(ingredientService.getById(txTransactionCheckout.getIdIngredient()));
                    txTransactionCheckoutService.create(txTransactionCheckout);
                }
        );
        return txTransaction;
    }

    private void deleteCheckoutByTransaction(TxTransaction txTransaction) {
        txTransactionCheckoutService.getCheckoutByTransaction(txTransaction).forEach(
                txTransactionCheckout -> {
                    txTransactionCheckoutService.delete(txTransactionCheckout.getId());
                }
        );
    }

    private TransactionDto setStatTransaction(String id, String stat) {
        TxTransaction txTransaction = getFullTxTransaction(id);
        txTransactionRepository.save(txTransaction);
        txTransaction.setStat(stat);
        return new TransactionDto(
                txTransaction,
                getFullTxTransactionCheckoutList(txTransaction)
        );
    }

    private List<TxTransactionCheckout> getFullTxTransactionCheckoutList(TxTransaction txTransaction) {
        return txTransactionCheckoutService.getCheckoutByTransaction(txTransaction)
                .stream()
                .peek(
                        txTransactionCheckout -> txTransactionCheckout.setIdIngredient(txTransactionCheckout.getIngredient().getId())
                ).collect(Collectors.toList());
    }
}
