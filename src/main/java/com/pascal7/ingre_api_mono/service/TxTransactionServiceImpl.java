package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.TransactionDto;
import com.pascal7.ingre_api_mono.custom.TransactionStat;
import com.pascal7.ingre_api_mono.entity.Ingredient;
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
    UserService userService;

    @Autowired
    Helper helper;

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        helper.validateIdIsNull(transactionDto.getId());
        validateTotal(transactionDto);
        TxTransaction txTransaction = getTxTransactionWithCheckout(transactionDto, false);
        setTransactionDto(transactionDto, txTransaction);
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
        validateTotal(transactionDto);
        TxTransaction txTransaction = getTxTransactionWithCheckout(transactionDto, true);
        setTransactionDto(transactionDto, txTransaction);
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
        validateTransactionStat(id, TransactionStat.WAITING.getValue());
        return setStatTransaction(id, TransactionStat.ON_DELIVERY.getValue());
    }

    @Override
    public TransactionDto checkTransactionStatusToDone(String id) {
        validateTransactionStat(id, TransactionStat.ON_DELIVERY.getValue());
        return setStatTransaction(id, TransactionStat.DONE.getValue());
    }

    @Override
    public List<TransactionDto> getUserTransaction(String id) {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        txTransactionRepository.findByUser(userService.getById(id)).forEach(
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
    public TransactionDto getTransactionById(String userId, String id) {
        userService.getById(userId);
        return getById(id);
    }

    private void setTransactionDto(TransactionDto transactionDto, TxTransaction txTransaction) {
        transactionDto.setId(txTransaction.getId());
        transactionDto.setRecipeName(txTransaction.getRecipe().getName());
        transactionDto.setName(txTransaction.getUser().getFullName());
    }

    private void validateTransactionStat(String id, String stat) {
        if(!getById(id).getStat().equals(stat)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, BankString.statusIsNotAccepted);
        }
    }

    private void validateTotal(TransactionDto transactionDto) {
        List<Integer> total = new ArrayList<>();
        transactionDto.getIngredient().forEach(
                txTransactionCheckout -> {
                    total.add(ingredientService.getById(txTransactionCheckout.getIdIngredient()).getPrice() * txTransactionCheckout.getQty());
                }
        );
        if((int)total.stream().mapToDouble(value -> value).sum() != transactionDto.getTotal()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, BankString.totalDidNotMatch);
        }
    }

    private TxTransaction getFullTxTransaction(String id) {
        TxTransaction txTransaction = txTransactionRepository.getById(id);
        setTransactionFieldId(txTransaction);
        return txTransaction;
    }

    private void setTransactionFieldId(TxTransaction txTransaction) {
        txTransaction.setIdRecipe(txTransaction.getRecipe().getId());
        txTransaction.setIdUser(txTransaction.getUser().getId());
    }

    private void validateIdIsExist(String id) {
        if(!txTransactionRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    private TxTransaction getTxTransactionWithCheckout(TransactionDto transactionDto, Boolean delete) {
        setTransactionObjectField(transactionDto);
        TxTransaction txTransaction = txTransactionRepository.save(new TxTransaction(transactionDto));
        if(delete){
            deleteCheckoutByTransaction(txTransaction);
        }
        transactionDto.getIngredient().forEach(
                txTransactionCheckout -> {
                    Ingredient ingredient = ingredientService.getById(txTransactionCheckout.getIdIngredient());
                    ingredient.setStock(ingredient.getStock() - txTransactionCheckout.getQty());
                    ingredientService.update(ingredient);
                    txTransactionCheckout.setTransaction(txTransaction);
                    txTransactionCheckout.setIngredient(ingredient);
                    txTransactionCheckoutService.create(txTransactionCheckout);
                }
        );
        return txTransaction;
    }

    private void setTransactionObjectField(TransactionDto transactionDto) {
        transactionDto.setUser(userService.getById(transactionDto.getIdUser()));
        transactionDto.setRecipe(new Recipe(recipeService.getById(transactionDto.getIdRecipe())));
    }

    private void deleteCheckoutByTransaction(TxTransaction txTransaction) {
        txTransactionCheckoutService.getCheckoutByTransaction(txTransaction).forEach(
                txTransactionCheckout -> {
                    txTransactionCheckoutService.delete(txTransactionCheckout.getId());
                }
        );
    }

    private TransactionDto setStatTransaction(String id ,String stat) {
        TxTransaction txTransaction = getFullTxTransaction(id);
        txTransaction.setStat(stat);
        txTransactionRepository.save(txTransaction);
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
