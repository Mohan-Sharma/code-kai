package com.code.kai.cashbackengine.presentation.data;

import com.code.kai.cashbackengine.domain.model.Stage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohan Sharma Created on 13/07/18.
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CashBackClaimData
{
	private String claimId;
	private int stagePosition;
	private List<Stage> stages;
	private String status;
	private String customerPk;
	private String cashbackId;
}
