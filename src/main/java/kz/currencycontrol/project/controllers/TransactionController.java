package kz.currencycontrol.project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.services.CurrencyService;
import kz.currencycontrol.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Transaction Controller",description = "Контроллер для обработки запросов банковского сервера")
@RestController
@RequestMapping(value = "/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @Operation(summary = "Получение информации о транзакциях с банковского сервера",
            description = "Валюты: { id : 1, value : Rub }, { id : 2, value : Kzt }, { id : 3, value : Usd } | Категории: { id : 1, name : product}, {id : 2, name : service}")

    @PostMapping
    public Transaction transaction(@RequestBody Transaction trans) throws Exception{
        return transactionService.transaction(trans);
    }
}
