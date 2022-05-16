package course.java.web;

import course.java.exception.ConstraintViolation;
import course.java.exception.InvalidConstraintException;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.ErrorResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleNonexistingEntityException(NonexistingEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));

    }

    @ExceptionHandler({InvalidEntityDataException.class, InvalidConstraintException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidEntityDataException(Exception e) {
        Throwable ex = e;
        List<ConstraintViolation> violations = null;
        while (ex != null && !(ex instanceof InvalidConstraintException)) {
            ex = ex.getCause();
        }
        if (ex != null) {
            violations = ((InvalidConstraintException) ex).getFieldViolations();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage(), violations));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleInvalidEntityDataException(DataIntegrityViolationException e) {
        Throwable ex = e;
        String message = e.getMessage();
        while (ex != null && !(ex instanceof ConstraintViolationException)) {
            ex = ex.getCause();
        }
        if (ex != null) {
            message = String.format("Username already exists. Invalid constraint: '%s'",
                            ((ConstraintViolationException) ex).getConstraintName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message, null));
    }

}
