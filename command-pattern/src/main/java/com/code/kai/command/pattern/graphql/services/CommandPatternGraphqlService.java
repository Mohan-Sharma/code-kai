package com.code.kai.command.pattern.graphql.services;

import com.code.kai.command.pattern.graphql.data.GetCountryResponse;

/**
 * @author Mohan Sharma
 */
public interface CommandPatternGraphqlService {

    GetCountryResponse getCountryByIsoCode(final String isoCode);
}
