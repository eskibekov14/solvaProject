package kz.currencycontrol.project.controllers;

import kz.currencycontrol.project.models.Limit;
import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.services.LimitService;
import kz.currencycontrol.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientRequestController {
    @Autowired
    private LimitService limitService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllExceededTrans(@RequestParam(name = "accNumber") Long accNumber){
        return transactionService.getAllExceededTransactions(accNumber);
    }
    @PostMapping
    public Limit updateLimit(@RequestBody Limit limit){
        return limitService.updateLimit(limit);
    }
}
