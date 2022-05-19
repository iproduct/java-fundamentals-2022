package course.java.util;

import course.java.exception.InvalidConstraintException;

public interface EntityValidator<E> {
    void validate(E entity) throws InvalidConstraintException;
}
