package com.code.kai.command.pattern.services;

import com.code.kai.command.pattern.rest.dto.GetUserResponse;
import com.code.kai.command.pattern.rest.dto.SaveUserResponse;

/**
 * @author Mohan Sharma
 */
public interface CommandPatternDemoService {

    GetUserResponse getUserById(final String uuid);

    SaveUserResponse saveUser(final String name, final String job);
}
