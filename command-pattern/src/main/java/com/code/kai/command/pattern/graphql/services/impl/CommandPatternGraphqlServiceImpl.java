package com.code.kai.command.pattern.graphql.services.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.code.kai.command.pattern.common.AbstractRestService;
import com.code.kai.command.pattern.graphql.commands.CountryByIsoCodeCommand;
import com.code.kai.command.pattern.graphql.data.CountryByIsoCodeVariables;
import com.code.kai.command.pattern.graphql.data.GetCountryRequest;
import com.code.kai.command.pattern.graphql.data.GetCountryResponse;
import com.code.kai.command.pattern.graphql.services.CommandPatternGraphqlService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Mohan Sharma
 */
@Service
@Slf4j
public class CommandPatternGraphqlServiceImpl extends AbstractRestService implements CommandPatternGraphqlService {

    public static final String COUNTRY_BY_ISOCODE_QUERY = "classpath:graphql/queries/countryByIso.graphql";

    private CountryByIsoCodeCommand countryByIsoCodeCommand;

    public CommandPatternGraphqlServiceImpl(CountryByIsoCodeCommand countryByIsoCodeCommand) {
        this.countryByIsoCodeCommand = countryByIsoCodeCommand;
    }

    @Override
    public GetCountryResponse getCountryByIsoCode(String isoCode) {
        final GetCountryRequest request = new GetCountryRequest();
        final String query = getFileAsString(COUNTRY_BY_ISOCODE_QUERY);
        CountryByIsoCodeVariables variables = new CountryByIsoCodeVariables();
        variables.setCode(isoCode);
        request.setQuery(query);
        request.setVariables(variables);
        return invoke(request, getCountryByIsoCodeCommand());
    }

    private String getFileAsString(final String fileName) {
        try (Reader in = new InputStreamReader(new FileInputStream(ResourceUtils.getFile(fileName)), UTF_8)) {
            return FileCopyUtils.copyToString(in);
        } catch (IOException e) {
            // todo throw custom exception
            throw new RuntimeException(e);
        }
    }

    protected void handleError(final RestClientResponseException clientError, boolean shouldThrow) {
        if (shouldThrow) {
            throw clientError;
        }
    }

    public CountryByIsoCodeCommand getCountryByIsoCodeCommand() {
        return countryByIsoCodeCommand;
    }
}
