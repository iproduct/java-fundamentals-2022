package course.java.dao;

import java.util.Properties;

public interface RepoFactory {
    UserRepository createUserRepository(Properties options);
    BookRepository createBookRepository(Properties options);
    OrderRepository createOrderRepository(Properties options);
}
