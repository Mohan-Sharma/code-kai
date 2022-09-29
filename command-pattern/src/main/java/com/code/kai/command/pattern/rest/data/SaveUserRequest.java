package com.code.kai.command.pattern.rest.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma
 */
@Getter
@Setter
@AllArgsConstructor
public class SaveUserRequest {

    private String name;
    private String job;

}
