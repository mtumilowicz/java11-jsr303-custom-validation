package com.example.validation.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * Created by mtumilowicz on 2019-03-05.
 */
@Value
class User {

    @NotBlank
    @Word
    String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public User(@JsonProperty("name") String name) {
        this.name = name;
    }
}
