package com.example.validation.app.domain.patterns;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2019-03-11.
 */
public class WordValidatorTest {
    
    private final WordValidator validator = new WordValidator();

    @Test
    public void isValid() {
        assertTrue(validator.isValid("a", null));
    }

    @Test
    public void isNotValid() {
        assertFalse(validator.isValid("%%%", null));
    }
}