package com.code.kai.command.pattern.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma
 */
@Getter
@Setter
public abstract class AbstractOAuthRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String oauthToken;
}
