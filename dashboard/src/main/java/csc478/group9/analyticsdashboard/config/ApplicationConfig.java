package csc478.group9.analyticsdashboard.config;

import csc478.group9.analyticsdashboard.mapper.UserMapper;
import csc478.group9.analyticsdashboard.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserMapper modelMapper() {
        UserMapper UserMapper = new UserMapper() {
            @Override
            public User getUser(String username) {
                return null;
            }

            @Override
            public int insert(User user) {
                return 0;
            }
        };
        return UserMapper;
    }
}
