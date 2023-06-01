package com.example.ooad.configuration;

import com.example.ooad.controller.admin.action.AdminActionFactory;
import com.example.ooad.controller.user.UserLoginUtil;
import com.example.ooad.jpa.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationManager {
    @Bean
    public UserLoginUtil getUserLoginUtil(UserRepository userRepository) {
        return new UserLoginUtil(userRepository);
    }

    @Bean
    public AdminActionFactory getAdminActionFactory() {
        return new AdminActionFactory();
    }
}
