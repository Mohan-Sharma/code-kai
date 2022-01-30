package com.code.kai.cashbackengine.persistence;

import com.code.kai.cashbackengine.domain.model.Orders;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @author Mohan Sharma Created on 11/07/18.
 */
public interface OrdersRepository extends MongoRepository<Orders, Long>
{
	List<Orders> findAllByCustomerPkAndOrderCreationTimeBetween(String customerPK, LocalDate orderCreationDateFrom, LocalDate orderCreationDateTo);

	List<Orders> findAllByOrderCreationTimeBetween(LocalDate orderCreationDateFrom, LocalDate orderCreationDateTo);

	List<Orders> findAllByCustomerPkAndOrderCreationTimeBetweenAndPriceValueAndPriceCurrencyISO(String customerPk, LocalDate pastDate,
			LocalDate currentDate, BigDecimal value, String currencyISO);
}
