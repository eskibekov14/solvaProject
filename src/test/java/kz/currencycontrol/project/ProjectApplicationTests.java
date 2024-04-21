package kz.currencycontrol.project;

import kz.currencycontrol.project.models.Category;
import kz.currencycontrol.project.models.Currency;
import kz.currencycontrol.project.models.Limit;
import kz.currencycontrol.project.models.Transaction;
import kz.currencycontrol.project.repositories.CategoryRepository;
import kz.currencycontrol.project.repositories.CurrencyRepository;
import kz.currencycontrol.project.repositories.LimitRepository;
import kz.currencycontrol.project.repositories.TransactionRepository;
import kz.currencycontrol.project.services.CurrencyService;
import kz.currencycontrol.project.services.LimitService;
import kz.currencycontrol.project.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private LimitService limitService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private LimitRepository limitRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void updateLimitByClient(){
		Category category = categoryRepository.findAllById(1L);
		Assertions.assertNotNull(category);
		Limit limit = Limit.builder()
				.currentLimit(2100)
				.accountNumber(123L)
				.category(category)
				.build();
		Limit startState = limitService.updateLimit(limit);
		Limit finalState = limitRepository.findAllById(1L);
		Assertions.assertEquals(startState,finalState);
	}
	@Test
	void updateMonthLimit(){
		List<Limit> oldLimits = limitService.updateMonthLimitSum();
		List<Limit> updatedLimits = limitRepository.findAll();
		Assertions.assertEquals(oldLimits.size(),updatedLimits.size());
		for(Limit oldLim : oldLimits){
			for(Limit upLim : updatedLimits){
				if(oldLim.getId() == upLim.getId()){
					Assertions.assertEquals(oldLim.getCurrentLimit(),upLim.getSum());
					break;
				}
			}
		}
	}
	@Test
	void getAllExceededTransactions(){
		List<Transaction> transactions = transactionService.getAllExceededTransactions(123L);
		Assertions.assertNotNull(transactions);
	}
	@Test
	void sendTransaction(){
		Currency currency = currencyRepository.findAllById(3L);
		Category category = categoryRepository.findAllById(2L);
		transactionRepository.deleteAll();
		Transaction transaction = Transaction.builder()
				.accountFrom(123L)
				.accountTo(987L)
				.currency(currency)
				.category(category)
				.sumTransaction(800)
				.build();
		Assertions.assertNotNull(currency);
		Assertions.assertNotNull(category);
		try {
			Transaction startState = transactionService.transaction(transaction);
			Transaction finalState = transactionRepository.findAll().get(0);
			Assertions.assertEquals(startState,finalState);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Test
	void getCurrenciesFromApi(){
		List<Currency> currencyList = currencyService.sendDailyRequest();
		Assertions.assertNotNull(currencyList);
	}
}
