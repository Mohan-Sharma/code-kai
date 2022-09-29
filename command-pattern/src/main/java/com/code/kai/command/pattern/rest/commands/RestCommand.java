package com.code.kai.command.pattern.rest.commands;

public interface RestCommand<REQUEST, RESPONSE> {

	RESPONSE perform(REQUEST request);

}