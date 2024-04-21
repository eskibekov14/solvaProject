package kz.currencycontrol.project.services.impl;

import kz.currencycontrol.project.models.Limit;
import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.repositories.CurrencyRepository;
import kz.currencycontrol.project.repositories.LimitRepository;
import kz.currencycontrol.project.repositories.TransactionRepository;
import kz.currencycontrol.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private  LimitRepository limitRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Override
    public Transaction transaction(Transaction transaction) throws Exception{

        double usd = 0;
        if(transaction.getCurrency().getId() == 1L){
            usd = transaction.getSumTransaction()/currencyRepository.findAllById(1L).getValue();
        } else if (transaction.getCurrency().getId() == 2L) {
            usd = transaction.getSumTransaction()/currencyRepository.findAllById(2L).getValue();
        } else if (transaction.getCurrency().getId() == 3L) {
            usd = transaction.getSumTransaction();
        } else {
            throw new Exception("Currency Not Found");
        }
        transaction.setSumTransaction(usd);

        List<Limit> limits = limitRepository.findAllByAccountNumberEquals(transaction.getAccountFrom());
        for(Limit lim : limits){
            if(lim.getCategory().getId() == transaction.getCategory().getId()){
                double limitSum = lim.getSum() - transaction.getSumTransaction();
                if(limitSum<0.0){
                    transaction.setLimitExceeded(true);
                }
                lim.setSum(limitSum);
                transaction.setCurrentLimit(lim.getCurrentLimit());
                limitRepository.save(lim);
            }
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllExceededTransactions(Long accountNumber) {
        return transactionRepository.findAllByAccountFromEqualsAndLimitExceededTrue(accountNumber);
    }
}
