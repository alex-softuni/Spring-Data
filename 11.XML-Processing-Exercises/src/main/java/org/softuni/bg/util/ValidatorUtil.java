package org.softuni.bg.util;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidatorUtil {
    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> getViolations(E entity);
}
