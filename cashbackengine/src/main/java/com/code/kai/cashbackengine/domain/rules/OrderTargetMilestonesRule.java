package com.code.kai.cashbackengine.domain.rules;

import com.code.kai.cashbackengine.domain.model.ClaimCashBack;
import com.code.kai.cashbackengine.domain.model.Stage;
import com.code.kai.cashbackengine.persistence.ClaimRepository;
import com.code.kai.cashbackengine.presentation.data.OrdersData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Rule(name="orderTargetMilesstone", description="make target number of orders")
@Component
public class OrderTargetMilestonesRule {

	@Autowired
	private ClaimRepository claimRepository;

	@Condition
	public boolean itChecks(@Fact("orders") List<OrdersData> orders,@Fact("thresholdQuantity") String thresholdQuantity,
			@Fact("minOrderValue") String minOrderValue, @Fact("orderStatus") String orderStatus, @Fact("cashbackId") String cashbackId, @Fact("customerPk") String customerPk) {

		final double minimumOrderTotal = Double.valueOf(minOrderValue);
		Long numberOfOrder = orders
				.stream()
				.filter(order -> order.getOrderStatus().equalsIgnoreCase(orderStatus))
				.filter(order -> order.getPrice().getValue().doubleValue() >= minimumOrderTotal)
				.count();

		if(numberOfOrder>0)
		{
			ClaimCashBack claim = claimRepository.findOneByCashbackIdAndCustomerPk(cashbackId, customerPk);
			if(Objects.isNull(claim))
			{
				claim = new ClaimCashBack();
			}
			List<Stage> stages = new ArrayList<>();
			orders
					.stream()
					.filter(order ->order.getOrderStatus().equalsIgnoreCase(orderStatus))
					.filter(order -> order.getPrice().getValue().doubleValue()>=minimumOrderTotal)
					.forEach( order ->{
						Stage stage = new Stage();
						String orderCode = String.valueOf( order.getOrderCode());
						stage.setIdentifier(orderCode);
						stage.setStatus("completed");
						stages.add(stage);

					});
			claim = claim
					.toBuilder()
					.cashbackId(cashbackId)
					.customerPk(customerPk)
					.status("IN_PROGRESS")
					.stagePosition(numberOfOrder.intValue())
					.stages(stages)
					.build();
			claimRepository.save(claim);
		}
		if(numberOfOrder>=Integer.parseInt(thresholdQuantity)) {

			return true;

		}else {
			return false;
		}

	}

	@Action
	public void updateClaimStatus(@Fact("cashbackId") String cashbackId,@Fact("customerPk") String customerPk)
	{
		ClaimCashBack claim = claimRepository.findOneByCashbackIdAndCustomerPk(cashbackId, customerPk);
		claim.setStatus("READY_TO_CLAIM");
		claimRepository.save(claim);
	}
}
