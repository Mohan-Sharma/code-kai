package com.code.kai.cashbackengine.domain.model;

import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Mohan Sharma Created on 12/07/18.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Document
@Builder(toBuilder = true)
public class CashBackOffer implements Serializable
{
	@Id
	private String cashbackId;
	private Price claimAmount;
	private String ruleFileName;
	private boolean active;
	private Set<CashBackFact> cashBackFacts;
	private int priority;
	private String description;
	private String offerName;
}


