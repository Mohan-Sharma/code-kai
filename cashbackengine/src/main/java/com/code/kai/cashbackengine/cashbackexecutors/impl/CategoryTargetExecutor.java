package com.code.kai.cashbackengine.cashbackexecutors.impl;

import com.code.kai.cashbackengine.application.service.CashBackService;
import com.code.kai.cashbackengine.cashbackexecutors.CashBackExecutor;
import com.code.kai.cashbackengine.domain.model.CashBackOffer;
import com.code.kai.cashbackengine.domain.rules.CategoryTargetMileStoneRule;
import com.code.kai.cashbackengine.persistence.CashBackOfferRepository;
import com.google.common.collect.Sets;
import java.util.Set;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryTargetExecutor implements CashBackExecutor {
	
	@Autowired
	private CashBackOfferRepository cashBackOfferRepository;
	@Autowired
	private CashBackService cashBackService;
	@Autowired
	private CategoryTargetMileStoneRule categoryTargetMileStoneRule;

	@Override
	public void execute(final String caskbackId, final String customerPk) {
		
		CashBackOffer cashBackOffer = cashBackOfferRepository.findById(caskbackId).get();
		Facts facts = new Facts();
		cashBackOffer.getCashBackFacts().forEach(fact -> facts.put(fact.getName(), fact.getValue()));
		Integer duration = Integer.parseInt(facts.get("duration").toString());
		Set<String> categories = cashBackService.getAllCategoryForCustomer(customerPk, duration);
		facts.put("usedCategories",categories);
		facts.put("cashbackId", caskbackId);
		facts.put("customerPk", customerPk);
		final String catergoryString = (String) facts.get("categories");
		final String[] categoriesArray = catergoryString.split(",");
		facts.put("categories", Sets.newHashSet(categoriesArray));
		Rules rules = new Rules();
		rules.register(categoryTargetMileStoneRule);
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
		
	}

}
