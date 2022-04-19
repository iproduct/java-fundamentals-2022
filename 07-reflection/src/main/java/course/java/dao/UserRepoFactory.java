package course.java.dao;

import java.util.Map;

public interface UserRepoFactory {
    UserRepository createUserRepository(Map<String, Object> options);
}
