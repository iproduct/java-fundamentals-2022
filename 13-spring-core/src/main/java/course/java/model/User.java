package course.java.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Identifiable<Long> {
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private Set<Role> roles;
    private boolean active = true;
}
