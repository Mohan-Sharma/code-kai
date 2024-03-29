package com.code.kai.cashbackengine.domain.rules;

import com.code.kai.cashbackengine.domain.model.ClaimCashBack;
import com.code.kai.cashbackengine.domain.model.Stage;
import com.code.kai.cashbackengine.persistence.ClaimRepository;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Rule(name="categoryTargetMileStoneRule", description="make target number of Categories")
@Component
public class CategoryTargetMileStoneRule {


	@Autowired
	private ClaimRepository claimRepository;


	@Condition
	public boolean itChecks(@Fact("categories") Set<String> categories, @Fact("cashbackId") String cashbackId, @Fact("customerPk") String customerPk, @Fact("usedCategories") Set<String> usedCategories)
	{

		Set<String> temporaryUsedCategory = Sets.newHashSet(usedCategories);
		temporaryUsedCategory.retainAll(categories);
		if(temporaryUsedCategory.size() > 0)
		{
			ClaimCashBack claim = claimRepository.findOneByCashbackIdAndCustomerPk(cashbackId, customerPk);
			if(Objects.isNull(claim))
			{
				claim = ClaimCashBack.builder().build();
			}
			final List<Stage> stages = new ArrayList<>();
			temporaryUsedCategory
					.forEach( category ->
					{
						Stage stage = new Stage();
						stage.setIdentifier(category);
						stage.setStatus("completed");
						stages.add(stage);

					});

			categories.removeAll(usedCategories);
			claim = claim
					.toBuilder()
					.cashbackId(cashbackId)
					.customerPk(customerPk)
					.status("IN_PROGRESS")
					.stagePosition(temporaryUsedCategory.size())
					.stages(stages)
					.build();
			claimRepository.save(claim);
		}
		if(categories.size() ==  0)
		{
			return true;
		}else {
			return false;
		}

	}

	@Action
	public void updateClaimStatus(@Fact("cashbackId") String cashbackId, @Fact("customerPk") String customerPk)
	{
		ClaimCashBack claim = claimRepository.findOneByCashbackIdAndCustomerPk(cashbackId, customerPk);
		claim.setStatus("READY_TO_CLAIM");
		claimRepository.save(claim);
	}
}
