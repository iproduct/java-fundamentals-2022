package course.java.service.impl;

import course.java.service.UserProvider;
import course.java.service.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service("userPresenter")
//@DependsOn("userProvider")
public class ConsoleUserPresenter implements Presenter {
    private UserProvider provider;

    @Autowired
    public ConsoleUserPresenter(UserProvider provider) {
        this.provider = provider;
    }

    @Override
    public void present() {
        provider.getUsers().forEach(System.out::println);
    }
}
