package course.java.service.impl;

import course.java.dao.UserRepository;
import course.java.model.User;
import course.java.model.User;
import course.java.service.UserProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static course.java.model.Role.*;

@Service("userProvider")
public class UserProviderDefaultImpl implements UserProvider {
    public static final List<User> DEFAULT_ARTICLES = List.of(
            new User("Default", "Admin", "admin", "Admin123#", Set.of(ADMIN, AUTHOR, READER)),
            new User("Default", "Author", "author", "Author123#", Set.of(AUTHOR)),
            new User("Default", "Reader", "reader", "Reader123#", Set.of(READER))
    );
    private UserRepository userRepo;

    public UserProviderDefaultImpl() {
    }

    @PostConstruct
    public void init() throws Exception {
        DEFAULT_ARTICLES.forEach(userRepo::create);
    }

//    @Inject
//    @Resource
    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }


}
