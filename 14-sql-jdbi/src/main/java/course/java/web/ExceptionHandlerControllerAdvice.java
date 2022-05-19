package course.java.web;

import course.java.exception.*;
import course.java.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

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
    public ResponseEntity<ErrorResponseDto> handleInvalidEntityDataException(PersistenceException e) {
        Throwable ex = e;
        var message = new StringJoiner("");
        message.add(e.getMessage() + ": ");
        while (ex != null && !(ex instanceof SQLException)) {
            ex = ex.getCause();
        }
        if (ex != null) {
            var sqlex = (SQLException) ex;
            message.add(" SQL Error Code: " + sqlex.getErrorCode());
            if (sqlex.getSQLState() != null) {
                message.add("[" + sqlex.getSQLState() + "], ");
            }
            message.add(" SQL Errors: {");
            for (var sqlException : sqlex) { // SQL Error is iterable of throwables
                message.add(sqlException.getMessage());
                Throwable t = sqlException.getCause();
                if (t != null) {
                    message.add("[");
                    while (t != null) {
                        message.add(t.getMessage() + " ");
                        t = t.getCause();
                    }
                    message.add("] ");
                }
            }
            message.add("}");
            if (sqlex != null) {
                Throwable t = sqlex;
                message.add(", Cause: [");
                while (t != null) {
                    message.add(t.getMessage() + " ");
                    t = t.getCause();
                }
                message.add("] ");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message.toString(), null));
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> handleInvalidEntityDataException(DataIntegrityViolationException e) {
//        Throwable ex = e;
//        String message = e.getMessage();
//        while (ex != null && !(ex instanceof ConstraintViolationException)) {
//            ex = ex.getCause();
//        }
//        if (ex != null) {
//            message = String.format("Username already exists. Invalid constraint: '%s'",
//                            ((ConstraintViolationException) ex).getConstraintName());
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message, null));
//    }

}
