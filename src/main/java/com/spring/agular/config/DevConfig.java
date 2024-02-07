//package com.spring.agular.config;
//
//
//import com.spring.agular.service.DBService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//
//@Configuration
//@Profile("dev")
//public class DevConfig {
//
//    @Autowired
//    private DBService dbService;
//
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String strategy;
//
//    @Bean
//    public Boolean instanciaBaseDados(){
//        if(strategy.equals("create")){
//            this.instanciaBaseDados();
//        }
//
//        return false;
//    }
//}
