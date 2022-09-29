package com.code.kai.command.pattern.rest.commands;

import com.code.kai.command.pattern.rest.dto.SaveUserRequest;
import com.code.kai.command.pattern.rest.dto.SaveUserResponse;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Mohan Sharma
 */
@Component
public class ReqResSaveUserCommand extends AbstractConcurRestCommand<SaveUserRequest, SaveUserResponse> {

    @Value("${req.res.save.user.url}")
    private String endpointUrl;

    @Override
    public SaveUserResponse perform(SaveUserRequest request) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEndpointUrl());
        if (StringUtils.isBlank(request.getName()) || StringUtils.isBlank(request.getJob())) {
            throw new IllegalStateException("name | job parameter is required");
        }
        final URI uri = builder.build().encode().toUri();
        return post(uri, request);
    }

    @Override
    protected HttpHeaders createHeaders(final SaveUserRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public String getEndpointUrl() {
        return endpointUrl;
    }
}
