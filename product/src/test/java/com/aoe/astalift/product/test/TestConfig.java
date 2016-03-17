package com.aoe.astalift.product.test;

import com.aoe.astalift.product.ProductServiceConfigHook;
import com.aoe.astalift.product.entity.TitleImage;
import com.aoe.astalift.product.repository.DetailImageRepository;
import com.aoe.astalift.product.repository.ProductRepository;
import com.aoe.astalift.product.repository.ThumbnailRepository;
import com.aoe.astalift.product.repository.TitleImageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductServiceConfigHook.class)
@Rollback
@Transactional
public class TestConfig {

    @Resource
    ThumbnailRepository thumbnailRepository;

    @Resource
    TitleImageRepository titleImageRepository;

    @Resource
    DetailImageRepository detailImageRepository;

    @Resource
    ProductRepository productRepository;

    @Test
    @Rollback
    public void foo() throws JsonProcessingException {
        Assert.noNullElements(new Object[]{thumbnailRepository, titleImageRepository, detailImageRepository, productRepository});

        TitleImage titleImage = new TitleImage();
        titleImage.setCreateTime(new Date());
        titleImage.setUrl("http://p1.ol-img.com/product/400x400/3/285/51a59ae2bb0b8.jpg");
        titleImageRepository.save(titleImage);

        List<TitleImage> all = titleImageRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(all));
    }
}
