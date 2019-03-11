package com.example.validation.app.domain.patterns;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2019-03-07.
 */
public class WordValidator implements ConstraintValidator<Word, String> {

    private static final Predicate<String> PATTERN = Pattern.compile("[\\w]+").asMatchPredicate();

    @Override
    public void initialize(Word word) {
    }

    @Override
    public boolean isValid(String word,
                           ConstraintValidatorContext cxt) {
        return nonNull(word) && PATTERN.test(word);
    }

}
