package course.java.model;

import course.java.exception.ConstraintViolation;
import lombok.Value;

import java.util.List;

@Value
public class ErrorResponseDto {
    private int status;
    private String message;
    private List<ConstraintViolation> violations;
}
