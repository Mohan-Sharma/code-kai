package com.code.kai.cashbackengine.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "claimId")
@AllArgsConstructor
@Document
@Builder(toBuilder = true)
public class ClaimCashBack implements Serializable
{
	@Id
	private String claimId;
	private int stagePosition;
	private List<Stage> stages;
	private String status;
	private String customerPk;
	private String cashbackId;
}
