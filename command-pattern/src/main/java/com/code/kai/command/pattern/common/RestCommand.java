package com.code.kai.command.pattern.common;

public interface RestCommand<REQUEST, RESPONSE> {

	RESPONSE perform(REQUEST request);

}