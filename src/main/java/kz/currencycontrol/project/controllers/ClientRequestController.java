package kz.currencycontrol.project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.currencycontrol.project.models.Limit;
import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.services.LimitService;
import kz.currencycontrol.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Client Controller",description = "Контроллер для обработки запросы клиентов:")
@RestController
@RequestMapping(value = "/client")
public class ClientRequestController {
    @Autowired
    private LimitService limitService;
    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Получение транзакции превысивших лимит")
    @GetMapping
    public List<Transaction> getAllExceededTrans(
           @Parameter(description = "Номер счета(аккаунта)") @RequestParam(name = "accNumber") Long accNumber){
        return transactionService.getAllExceededTransactions(accNumber);
    }

    @Operation(summary = "Обновление существующего лимита",
    description = "Последний установленный лимит остается, а каждый месяц обновляется только остаток лимита! Обязательные поля: currentLimit, accountNumber, category")
    @PostMapping
    public Limit updateLimit(
            @RequestBody Limit limit){
        return limitService.updateLimit(limit);
    }
}
