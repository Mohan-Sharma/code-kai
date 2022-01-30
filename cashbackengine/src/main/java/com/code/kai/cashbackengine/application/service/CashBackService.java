package com.code.kai.cashbackengine.application.service;

import com.code.kai.cashbackengine.domain.model.CashBackOffer;
import com.code.kai.cashbackengine.presentation.data.CashBackOfferData;
import com.code.kai.cashbackengine.presentation.data.CustomerData;
import com.code.kai.cashbackengine.presentation.data.OrdersData;
import com.code.kai.cashbackengine.presentation.data.PriceData;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface CashBackService
{

	void persistOrderDetails(final OrdersData ordersData);

	List<OrdersData> fetchAllOrdersForCustomer(final String customerPk, final Integer durationInDays);

	List<OrdersData> fetchAllOrdersForCustomerGreaterThanThresholdPrice(final String customerPk, final Integer durationInDays, final BigDecimal minOrderValue, String currencyISO);

	Set<String> getAllCategoryForCustomer(String customerPK, int durationInDays);

	List<CashBackOffer> getAllActiveCashBackOffersNotClaimedByUser(String customerPk);

	void persistCashbackOffer(final CashBackOfferData cashBackOfferData);

	CustomerData getClaimsAndOffersForCustomer(String customerPK);

	PriceData getClaimAmountFromCustomer(String claimId, String customerPk);
}

