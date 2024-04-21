package kz.currencycontrol.project.repositories;

import jakarta.transaction.Transactional;
import kz.currencycontrol.project.models.Category;
import kz.currencycontrol.project.models.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface LimitRepository extends JpaRepository<Limit,Long> {
    List<Limit> findAllByAccountNumberEquals(Long accountNUmber);
    Limit findAllById(Long id);

    @Query("SELECT l from Limit l where l.accountNumber = :accNumber and l.category.id = :catId")
    Limit findLimit(@Param("accNumber") Long accNumber,@Param("catId") Long catId);
}
