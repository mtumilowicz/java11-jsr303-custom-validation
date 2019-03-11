package com.example.validation.app.domain;

import com.example.validation.app.domain.patterns.Word;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * Created by mtumilowicz on 2019-03-05.
 */
@Value
@Builder
public class User {

    @NotBlank
    @Word
    String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public User(@JsonProperty("name") String name) {
        this.name = name;
    }
}
