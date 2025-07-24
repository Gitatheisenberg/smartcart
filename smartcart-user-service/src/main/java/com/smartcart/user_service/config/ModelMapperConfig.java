package com.smartcart.user_service.config;

import com.smartcart.user_service.dto.User;
import com.smartcart.user_service.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapperConfig modelMapper() {
        return new ModelMapperConfig();
    }

//    public UserEntity map(UserEntity user, Class<User> userEntityClass) {
//        return null;
}
