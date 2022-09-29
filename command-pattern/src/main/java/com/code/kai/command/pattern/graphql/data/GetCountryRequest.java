package com.code.kai.command.pattern.graphql.data;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma
 */
@Getter
@Setter
public class GetCountryRequest {

    private String query;
    private Object variables;

}
