package com.aoe.astalift.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by joey on 16-3-15.
 */
@SpringBootApplication
public class OrderServiceConfigHook {


    @Bean
    ObjectMapper objectMapper (){
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceConfigHook.class,args);
    }
}
