package com.code.kai.cashbackengine.domain.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
@Setter
public class Stage implements Serializable
{
	private String status;
	private String identifier;
}
