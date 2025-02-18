package org.softuni.bg.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> getViolations(E entity) {
        return this.validator.validate(entity);
    }
}
