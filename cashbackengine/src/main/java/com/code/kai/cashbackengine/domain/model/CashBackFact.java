package com.code.kai.cashbackengine.domain.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode(of = "name")
public class CashBackFact implements Serializable
{
	private String name;
	private String value;
	private String operator;
}
