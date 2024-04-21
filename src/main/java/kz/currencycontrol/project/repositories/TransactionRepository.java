package kz.currencycontrol.project.repositories;

import jakarta.transaction.Transactional;
import kz.currencycontrol.project.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountFromEqualsAndLimitExceededTrue(Long accountNumber);
    Transaction findAllById(Long id);
}
