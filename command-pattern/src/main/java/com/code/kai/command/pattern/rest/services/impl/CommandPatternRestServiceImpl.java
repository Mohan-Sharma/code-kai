package com.code.kai.command.pattern.rest.services.impl;

import com.code.kai.command.pattern.common.AbstractRestService;
import com.code.kai.command.pattern.rest.commands.ReqResGetUserCommand;
import com.code.kai.command.pattern.rest.commands.ReqResSaveUserCommand;
import com.code.kai.command.pattern.rest.data.GetUserRequest;
import com.code.kai.command.pattern.rest.data.GetUserResponse;
import com.code.kai.command.pattern.rest.data.SaveUserRequest;
import com.code.kai.command.pattern.rest.data.SaveUserResponse;
import com.code.kai.command.pattern.rest.services.CommandPatternRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Mohan Sharma
 */
@Service
@Slf4j
public class CommandPatternRestServiceImpl extends AbstractRestService implements CommandPatternRestService {

    private final ReqResGetUserCommand reqResGetUserCommand;
    private final ReqResSaveUserCommand reqResSaveUserCommand;

    public CommandPatternRestServiceImpl(ReqResGetUserCommand reqResGetUserCommand, ReqResSaveUserCommand reqResSaveUserCommand) {
        this.reqResGetUserCommand = reqResGetUserCommand;
        this.reqResSaveUserCommand = reqResSaveUserCommand;
    }

    @Override
    public GetUserResponse getUserById(String uuid) {
        final GetUserRequest request = new GetUserRequest();
        request.setUuid(uuid);
        return invoke(request, reqResGetUserCommand);
    }

    @Override
    public SaveUserResponse saveUser(final String name, final String job) {
        final SaveUserRequest request = new SaveUserRequest(name, job);
        return invoke(request, reqResSaveUserCommand);
    }

    @Override
    protected void handleError(final RestClientResponseException clientError, boolean shouldThrow) {
        if (shouldThrow) {
            throw clientError;
        }
    }
}
