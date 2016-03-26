package com.aoe.astalift.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by joey on 16-3-16.
 */
@SpringBootApplication
public class ProductServiceConfigHook {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceConfigHook.class,args);
    }
}
