package course.java.config;

import course.java.Main;
import course.java.dao.ArticleRepository;
import course.java.dao.IdGenerator;
import course.java.dao.UserRepository;
import course.java.dao.impl.ArticleRepositoryMemoryImpl;
import course.java.dao.impl.LongIdGenerator;
import course.java.dao.impl.UserRepositoryMemoryImpl;
import course.java.qualifiers.Default;
import course.java.qualifiers.Mock;
import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import course.java.service.impl.ArticleProviderDefaultImpl;
import course.java.service.impl.ArticleProviderMockImpl;
import course.java.service.impl.ConsoleArticlePresenter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

//@Configuration
//@PropertySource("classpath:blog.properties")
//@ComponentScan(basePackageClasses = course.java.Main.class)
public class AppConfig {
    @Mock
    @Order(1)
    @Bean("mockProvider")
    public ArticleProviderMockImpl mockProvider() {
        return new ArticleProviderMockImpl();
    }
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public IdGenerator<Long> longIdGenerator() {
        return new LongIdGenerator();
    }

    @Bean
    public ArticleRepository articleRepository(IdGenerator<Long> idGenerator) {
        return new ArticleRepositoryMemoryImpl(idGenerator);
    }

    @Bean
    @Default
    public ArticleProvider defaultProvider(ArticleRepository articleRepo){
        var provider =  new ArticleProviderDefaultImpl();
//        provider.setArticleRepo(articleRepository(longIdGenerator()));
        provider.setArticleRepo(articleRepo);
        return provider;
    }

    @Bean
    public Presenter articlePresenter(@Mock ArticleProvider provider) {
        return new ConsoleArticlePresenter(provider);
    }
}
