package com.spring.agular.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 @Autowired: Precisa de bean não nullos, por isso criar um modelMapper
 */
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
