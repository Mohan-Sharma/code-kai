package com.code.kai.command.pattern.graphql.api;

import com.code.kai.command.pattern.graphql.data.GetCountryResponse;
import com.code.kai.command.pattern.graphql.services.CommandPatternGraphqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohan Sharma
 */
@RestController
@RequestMapping(value = "/command-pattern/graphql")
@Slf4j
public class CommandPatternGraphqlController {

    private final CommandPatternGraphqlService commandPatternGraphqlService;

    public CommandPatternGraphqlController(CommandPatternGraphqlService commandPatternGraphqlService) {
        this.commandPatternGraphqlService = commandPatternGraphqlService;
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<GetCountryResponse> getCountryByIsoCode(@PathVariable(value = "code") final String code) {
        return ResponseEntity.ok().body(commandPatternGraphqlService.getCountryByIsoCode(code));
    }

}
