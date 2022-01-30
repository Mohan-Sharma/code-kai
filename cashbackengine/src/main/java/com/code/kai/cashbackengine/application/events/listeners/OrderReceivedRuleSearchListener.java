package com.code.kai.cashbackengine.application.events.listeners;

import com.code.kai.cashbackengine.application.events.OrderReceivedEvent;
import com.code.kai.cashbackengine.application.service.CashBackService;
import com.code.kai.cashbackengine.cashbackexecutors.CashBackExecutor;
import com.code.kai.cashbackengine.domain.model.CashBackOffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class OrderReceivedRuleSearchListener  {


	@Resource
	CashBackService cashBackService;

	@Autowired
	private Map<String, CashBackExecutor> executors;

	@EventListener
	public void handEvent(OrderReceivedEvent event) {

		List<CashBackOffer> allCashBackOffers = cashBackService.getAllActiveCashBackOffersNotClaimedByUser(event.getCustomerPk());
		Optional<CashBackOffer> findFirst = allCashBackOffers.stream().findFirst();
		if(findFirst.isPresent()) {
			String ruleFileName = findFirst.get().getRuleFileName();
			CashBackExecutor executor = executors.get(ruleFileName);
			executor.execute(findFirst.get().getCashbackId(), event.getCustomerPk());
		}

		System.out.println("recieve events for customer"+event.getCustomerPk());
	}

}
