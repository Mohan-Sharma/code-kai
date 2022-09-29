package com.code.kai.command.pattern.services.impl;

import com.code.kai.command.pattern.rest.commands.ReqResGetUserCommand;
import com.code.kai.command.pattern.rest.commands.ReqResSaveUserCommand;
import com.code.kai.command.pattern.rest.commands.RestCommand;
import com.code.kai.command.pattern.rest.dto.GetUserRequest;
import com.code.kai.command.pattern.rest.dto.GetUserResponse;
import com.code.kai.command.pattern.rest.dto.SaveUserRequest;
import com.code.kai.command.pattern.rest.dto.SaveUserResponse;
import com.code.kai.command.pattern.services.CommandPatternDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Mohan Sharma
 */
@Service
@Slf4j
public class CommandPatternDemoServiceImpl implements CommandPatternDemoService {

    private final ReqResGetUserCommand reqResGetUserCommand;
    private final ReqResSaveUserCommand reqResSaveUserCommand;

    public CommandPatternDemoServiceImpl(ReqResGetUserCommand reqResGetUserCommand, ReqResSaveUserCommand reqResSaveUserCommand) {
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


    private  <REQUEST, RESPONSE> RESPONSE invoke(final REQUEST request, final RestCommand<REQUEST, RESPONSE> command) {
        final int MAX_ATTEMPTS = 2;
        int attempt = 1;
        RESPONSE response = null;

        while (attempt < MAX_ATTEMPTS + 1) {
            try {
                response = command.perform(request);
                break;
            } catch (final RestClientResponseException clientError) {
                if (log.isDebugEnabled()) {
                    log.debug(String.format("Attempt #%d/%d failed", attempt, MAX_ATTEMPTS));
                }
                handleError(clientError, attempt >= MAX_ATTEMPTS);
                attempt++;
            }
        }
        return response;
    }

    private void handleError(final RestClientResponseException clientError, boolean shouldThrow) {
        if (shouldThrow) {
            throw clientError;
        }
    }
}
