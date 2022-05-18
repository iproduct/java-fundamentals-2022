package course.java.dao;

import course.java.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepositoryDataJPA {//extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String user);
    Collection<User> findByLastNameContaining(String lastNamePart);
}
