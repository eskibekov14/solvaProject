package kz.currencycontrol.project.services.impl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.currencycontrol.project.models.Limit;
import kz.currencycontrol.project.repositories.LimitRepository;
import kz.currencycontrol.project.services.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LimitServiceImpl implements LimitService {
    @Autowired
    private LimitRepository limitRepository;
    @Override
    public Limit updateLimit(Limit oldLimit) {
        Limit updateLimit = limitRepository.findLimit(oldLimit.getAccountNumber(), oldLimit.getCategory().getId());
        double sum = oldLimit.getCurrentLimit() - updateLimit.getCurrentLimit();
        sum = sum + updateLimit.getSum();
        updateLimit.setSum(sum);
        updateLimit.setCurrentLimit(oldLimit.getCurrentLimit());
        return limitRepository.save(updateLimit);
    }

//    Ежемесячное обновление лимитов
    @Scheduled(cron = "0 0 0 1 * ?")
    @Transactional
    @Override
    public List<Limit> updateMonthLimitSum() {
        List<Limit> limits = limitRepository.findAll();
        for(Limit lim : limits){
            lim.setSum(lim.getCurrentLimit());
            limitRepository.save(lim);
        }
        return limits;
    }
}
