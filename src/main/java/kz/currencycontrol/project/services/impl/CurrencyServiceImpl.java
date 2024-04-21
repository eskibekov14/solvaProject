package kz.currencycontrol.project.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.currencycontrol.project.models.Currency;
import kz.currencycontrol.project.repositories.CurrencyRepository;
import kz.currencycontrol.project.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    private final String api_url =
            "https://api.twelvedata.com/time_series?symbol=USD/KZT,USD/RUB&interval=1day&apikey=";
    private final String api_key = "17bfb12ecb484cc4b265a5081ae1b734";
    private final RestTemplate restTemplate = new RestTemplate();



    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public List<Currency> sendDailyRequest(){
        List<Currency> currencyList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(
                    restTemplate.getForObject(api_url+api_key, String.class)
            );
//          KZT/USD отсутсвует в twelvedata.com поэтому использовал USD/KZT and USD/RUB
            JsonNode UsdToKztValues = rootNode.path("USD/KZT").path("values");
            double closeKztValue = UsdToKztValues.get(0).path("close").asDouble();
            Currency usdToKzt = currencyRepository.findAllByNameEqualsIgnoreCase("usdToKzt");
            usdToKzt.setValue(closeKztValue);
            Currency kzt = currencyRepository.save(usdToKzt);

            JsonNode UsdToRubValues = rootNode.path("USD/RUB").path("values");
            double closeRubValue = UsdToRubValues.get(0).path("close").asDouble();
            Currency usdToRub = currencyRepository.findAllByNameEqualsIgnoreCase("usdToRub");
            usdToRub.setValue(closeRubValue);
            Currency rub = currencyRepository.save(usdToRub);
            currencyList.add(kzt);
            currencyList.add(rub);
            return currencyList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
