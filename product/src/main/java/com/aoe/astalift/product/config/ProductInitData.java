package com.aoe.astalift.product.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by joey on 16-3-17.
 */
@Component
@PropertySource("classpath:produt_images.properties")
public class ProductInitData {

    @Value("${images.titleUrls}")
    private String titles;

    @Value("${images.thumbnailUrls}")
    private String thumbnails;


    @Bean
    List<String> titleUrls() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(titles, new TypeReference<List<String>>(){});
    }


    @Bean
    List<List<String>> thumbnailUrls() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(thumbnails,new TypeReference< List<List<String>>>(){});
    }
}
