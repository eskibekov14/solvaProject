package kz.currencycontrol.project.services;

import kz.currencycontrol.project.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction transaction(Transaction transaction) throws Exception;
    List<Transaction> getAllExceededTransactions(Long accountNumber);
}
