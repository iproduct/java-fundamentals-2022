package course.java.config;

import course.java.Main;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:blog.properties")
@ComponentScan(basePackageClasses = course.java.Main.class)
public class AppConfig {
}
