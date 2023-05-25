package com.example.wokshop.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {

    @Bean(name = "default")
    @Primary //ako nqma okazan vinagi vrashta tozi
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "altModelMapper") //Taka se implmentirat dva modela s razlichni vazmojnosti
    public ModelMapper getAltModelMapper(){
        return new ModelMapper();
    }
}
