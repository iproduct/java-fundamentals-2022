package course.java.util;

import course.java.exception.ConstraintViolation;
import course.java.exception.InvalidConstraintException;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public class ErrorHandlingUtil {
    public static void handleValidationErrors(Errors errors) {
        if (errors.hasFieldErrors()) {
            var violations = errors.getFieldErrors().stream().map(fieldErr ->
                            new ConstraintViolation(fieldErr.getObjectName(), fieldErr.getField(),
                                    fieldErr.getRejectedValue(), fieldErr.getDefaultMessage()))
                    .collect(Collectors.toList());
            throw new InvalidConstraintException(
                    String.format("Invalid %s data", errors.getObjectName()),
                    violations
            );
        }
    }

}
