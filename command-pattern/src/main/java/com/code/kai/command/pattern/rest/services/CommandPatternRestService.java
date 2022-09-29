package com.code.kai.command.pattern.rest.services;

import com.code.kai.command.pattern.rest.data.GetUserResponse;
import com.code.kai.command.pattern.rest.data.SaveUserResponse;

/**
 * @author Mohan Sharma
 */
public interface CommandPatternRestService {

    GetUserResponse getUserById(final String uuid);

    SaveUserResponse saveUser(final String name, final String job);
}
