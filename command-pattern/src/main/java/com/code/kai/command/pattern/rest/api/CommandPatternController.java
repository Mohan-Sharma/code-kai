package com.code.kai.command.pattern.rest.api;

import com.code.kai.command.pattern.rest.dto.GetUserResponse;
import com.code.kai.command.pattern.rest.dto.SaveUserResponse;
import com.code.kai.command.pattern.rest.dto.UserForm;
import com.code.kai.command.pattern.services.CommandPatternDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohan Sharma
 */
@RestController
@RequestMapping(value = "/command-pattern")
@Slf4j
public class CommandPatternController {

    private final CommandPatternDemoService commandPatternDemoService;

    public CommandPatternController(CommandPatternDemoService commandPatternDemoService) {
        this.commandPatternDemoService = commandPatternDemoService;
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable(value = "uuid") final String uuid) {
        return ResponseEntity.ok().body(commandPatternDemoService.getUserById(uuid));
    }

    @PostMapping("/user")
    public ResponseEntity<SaveUserResponse> saveUser(@RequestBody final UserForm userForm) {
        return ResponseEntity.ok().body(commandPatternDemoService.saveUser(userForm.getName(), userForm.getJob()));
    }
}
