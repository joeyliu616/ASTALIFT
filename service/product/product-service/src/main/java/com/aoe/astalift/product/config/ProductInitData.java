package com.aoe.astalift.product.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by joey on 16-3-17.
 */
@Component
@PropertySource("classpath:produt_init_data.properties")
public class ProductInitData {

    @Value("${tiles}")
    private String tiles;

    @Value("${images.titleUrls}")
    private String titleImages;

    @Value("${images.thumbnailUrls}")
    private String thumbnails;

    @Value("${types}")
    private String types;

    @Value("${prices}}")
    private String pricesString;

    @Resource
    private ObjectMapper objectMapper;

    @Bean
    public List<Float> prices() throws IOException {
        return objectMapper.readValue(pricesString, new TypeReference<List<Float>>(){});
    }

    @Bean
    public List<String> productTypes() throws IOException {
        return objectMapper.readValue(types, new TypeReference<List<String>>(){});
    }

    @Bean
    public List<String> productTitles() throws IOException {
        return objectMapper.readValue(tiles, new TypeReference<List<String>>(){});
    }

    @Bean
    List<String> titleUrls() throws IOException {
        return objectMapper.readValue(titleImages, new TypeReference<List<String>>(){});
    }


    @Bean
    List<List<String>> thumbnailUrls() throws IOException {
        return objectMapper.readValue(thumbnails,new TypeReference< List<List<String>>>(){});
    }
}
