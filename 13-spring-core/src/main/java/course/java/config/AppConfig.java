package course.java.config;

import course.java.Main;
import course.java.qualifiers.Mock;
import course.java.service.impl.ArticleProviderMockImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

@Configuration
@PropertySource("classpath:blog.properties")
@ComponentScan(basePackageClasses = course.java.Main.class)
public class AppConfig {
    @Mock
    @Order(1)
    @Bean("mockProvider")
    public ArticleProviderMockImpl mockProvider() {
        return new ArticleProviderMockImpl();
    }
}
