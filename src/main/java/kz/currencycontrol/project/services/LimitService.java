package kz.currencycontrol.project.services;

import kz.currencycontrol.project.models.Limit;

import java.util.List;

public interface LimitService {
    Limit updateLimit(Limit limit);
    List<Limit> updateMonthLimitSum();
}
