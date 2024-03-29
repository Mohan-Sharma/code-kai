package com.code.kai.cashbackengine.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Mohan Sharma Created on 11/07/18.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Price implements Serializable
{
	private BigDecimal value;
	private String currencyISO;
}
