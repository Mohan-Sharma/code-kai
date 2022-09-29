package com.code.kai.command.pattern.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Mohan Sharma
 */
@Slf4j
public abstract class AbstractRestService {

    protected <REQUEST, RESPONSE> RESPONSE invoke(final REQUEST request, final RestCommand<REQUEST, RESPONSE> command) {
        // This scope is intentional: we don't want this to be modified as a usual business configuration
        final int MAX_ATTEMPTS = 2;
        int attempt = 1;
        RESPONSE response = null;

        // Should we need to retry in other places, we may consider Spring Retry
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

    protected abstract void handleError(final RestClientResponseException clientError, boolean shouldThrow);
}
