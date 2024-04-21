package kz.currencycontrol.project.controllers;

import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.services.CurrencyService;
import kz.currencycontrol.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public Transaction transaction(@RequestBody Transaction trans) throws Exception{
        return transactionService.transaction(trans);
    }
}
