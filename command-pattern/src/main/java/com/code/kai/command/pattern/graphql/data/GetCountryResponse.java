package com.code.kai.command.pattern.graphql.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohan Sharma
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCountryResponse {

    private Data data;

    @Setter
    @Getter
    @NoArgsConstructor
    class Data {

        private Country country;

        @Setter
        @Getter
        @NoArgsConstructor
        class Country {
            private String name;
            private String capital;
            private String currency;
        }
    }
}
