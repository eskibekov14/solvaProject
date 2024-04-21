package kz.currencycontrol.project.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.currencycontrol.project.models.Currency;

import java.util.List;

public interface CurrencyService {
    public List<Currency> sendDailyRequest();
}
