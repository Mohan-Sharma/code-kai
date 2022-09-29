package com.code.kai.command.pattern.rest.commands;

import com.code.kai.command.pattern.common.AbstractRestCommand;
import com.code.kai.command.pattern.rest.data.GetUserRequest;
import com.code.kai.command.pattern.rest.data.GetUserResponse;
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
public class ReqResGetUserCommand extends AbstractRestCommand<GetUserRequest, GetUserResponse> {

    @Value("${req.res.get.user.url}")
    private String endpointUrl;

    @Override
    public GetUserResponse perform(GetUserRequest request) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEndpointUrl());
        if (StringUtils.isNotBlank(request.getUuid())) {
            builder.path("/{uuid}");
        } else {
            throw new IllegalStateException("'company id' parameter is required");
        }
        final URI uri = builder.build().expand(request.getUuid()).encode().toUri();
        return get(uri, request);
    }

    @Override
    protected HttpHeaders createHeaders(final GetUserRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public String getEndpointUrl() {
        return endpointUrl;
    }
}
