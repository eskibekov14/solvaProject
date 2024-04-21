package kz.currencycontrol.project.repositories;

import jakarta.transaction.Transactional;
import kz.currencycontrol.project.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findAllByNameEqualsIgnoreCase(String name);
    Currency findAllById(Long id);
}
