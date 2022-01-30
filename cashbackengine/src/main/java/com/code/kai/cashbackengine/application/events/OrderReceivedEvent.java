package com.code.kai.cashbackengine.application.events;

import lombok.Getter;
@Getter
public class OrderReceivedEvent {

	private String customerPk;
	
	public OrderReceivedEvent(String customerPk) {
		
		this.customerPk = customerPk;
	}

}
