package com.code.kai.command.pattern.rest.commands;

import com.google.common.reflect.TypeToken;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mohan Sharma
 */
public abstract class AbstractConcurRestCommand<REQUEST, RESPONSE> implements RestCommand<REQUEST, RESPONSE> {

    private String endpointUrl;

    @Autowired
    private RestTemplate restTemplate;

    protected RESPONSE get(final URI uri, final REQUEST request) {
        return executeHttpVerb(uri, request, HttpMethod.GET);
    }

    protected RESPONSE post(final URI uri, final REQUEST request) {
        return executeHttpVerb(uri, request, HttpMethod.POST);
    }

    private RESPONSE executeHttpVerb(final URI uri, final REQUEST request, final HttpMethod method) {
        final HttpHeaders headers = createHeaders(request);
        final HttpEntity<REQUEST> entity = new HttpEntity<>(request, headers);
        final TypeToken<RESPONSE> type = new TypeToken<RESPONSE>(getClass()) {};
        final ResponseEntity<RESPONSE> response = getRestTemplate().exchange(uri, method, entity, (Class<RESPONSE>) type.getRawType());
        return response.getBody();
    }

    protected abstract HttpHeaders createHeaders(final REQUEST request);

    public abstract String getEndpointUrl();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
