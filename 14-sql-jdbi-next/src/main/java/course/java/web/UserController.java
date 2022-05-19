package course.java.web;

import course.java.exception.InvalidEntityDataException;
import course.java.model.User;
import course.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

import static course.java.util.ErrorHandlingUtil.handleValidationErrors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable("id") Long id) {
//        return userService.getUserById(id);
//    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, Errors errors) {
        handleValidationErrors(errors);
        var created = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

//    @PutMapping("/{id}")
//    public User updateUser(@Valid  @RequestBody User user, Errors errors, @PathVariable("id") Long id) {
//        if(!id.equals(user.getId())) {
//            throw new InvalidEntityDataException(
//                    String.format("User ID in URL = '%d' does not match ID in message body = '%d'",
//                            id, user.getId()));
//        }
//        handleValidationErrors(errors);
//        return userService.updateUser(user);
//    }

//    @DeleteMapping("/{id}")
//    public User deleteUser(@PathVariable("id") Long id) {
//        return userService.deleteUserById(id);
//    }

}
