package course.java.web;

import course.java.exception.ConstraintViolation;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.ErrorResponseDto;
import course.java.model.User;
import course.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        var created = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleNonexistingEntityException(NonexistingEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleInvalidEntityDataException(InvalidEntityDataException e) {
        Throwable ex = e;
        List<ConstraintViolation> violations = null;
//        while(ex != null && !(ex instanceof ConstraintViolationException)) {
//            ex = ex.getCause();
//        }
//        if(ex != null) {
//            violations = ((ConstraintViolationException) ex).getFieldViolations().stream()
//                    .collect(Collectors.toList());
//        }
        while (ex != null && !(ex instanceof ConstraintViolationException)) {
            ex = ex.getCause();
        }
        if (ex != null) {
            violations = ((ConstraintViolationException) ex).getConstraintViolations().stream()
                    .map(cv -> new ConstraintViolation(
                            cv.getRootBeanClass().getSimpleName(),
                            cv.getPropertyPath().toString(),
                            cv.getInvalidValue(),
                            cv.getMessage()
                    )).collect(Collectors.toList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), violations));
    }
}
