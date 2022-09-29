package com.code.kai.command.pattern.graphql.commands;

import com.code.kai.command.pattern.common.AbstractRestCommand;
import com.code.kai.command.pattern.graphql.data.GetCountryRequest;
import com.code.kai.command.pattern.graphql.data.GetCountryResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Mohan Sharma
 */
@Component
public class CountryByIsoCodeCommand extends AbstractRestCommand<GetCountryRequest, GetCountryResponse> {

    @Value("${countries.trevorblades.base.url}")
    private String endpointUrl;

    @Override
    public GetCountryResponse perform(GetCountryRequest request) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEndpointUrl());
        final URI uri = builder.build().encode().toUri();
        return post(uri, request);
    }

    @Override
    protected HttpHeaders createHeaders(final GetCountryRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public String getEndpointUrl() {
        return endpointUrl;
    }
}
