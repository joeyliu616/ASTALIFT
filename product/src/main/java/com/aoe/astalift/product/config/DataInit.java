package com.aoe.astalift.product.config;

import com.aoe.astalift.product.entity.DetailImage;
import com.aoe.astalift.product.entity.Product;
import com.aoe.astalift.product.entity.Thumbnail;
import com.aoe.astalift.product.entity.TitleImage;
import com.aoe.astalift.product.repository.DetailImageRepository;
import com.aoe.astalift.product.repository.ProductRepository;
import com.aoe.astalift.product.repository.ThumbnailRepository;
import com.aoe.astalift.product.repository.TitleImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by joey on 16-3-17.
 */
@Profile("data-init")
@Configuration
public class DataInit {

    private static Logger logger = LoggerFactory.getLogger(DataInit.class);

    @Resource
    private TitleImageRepository titleImageRepository;

    @Resource
    private ThumbnailRepository thumbnailRepository;

    @Resource
    private DetailImageRepository detailImageRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource(name="titleUrls")
    private List<String> titleUrls;

    @Resource(name="thumbnailUrls")
    private List<List<String>> thumbnailUrls;

    @Resource(name="productTitles")
    private List<String> productTitles;

    @Resource(name="productTypes")
    private List<String> productTypes;

    @Resource(name="prices")
    private List<Float> prices;

    @Resource
    ObjectMapper objectMapper;

    @PostConstruct
    public void init(){

        importImages();
        int size = titleUrls.size();
        Assert.isTrue(titleUrls.size() != 0);
        Assert.isTrue(titleUrls.size() == thumbnailUrls.size());
        Assert.isTrue(titleUrls.size() == productTitles.size());
        Assert.isTrue(titleUrls.size() == productTypes.size());
        Assert.isTrue(titleUrls.size() == prices.size());

        for (int i = 0; i < size; i++) {

            TitleImage titleImage = new TitleImage();
            Set<Thumbnail> thumbnails = new HashSet<Thumbnail>();
            Set<DetailImage> detailImages = new HashSet<DetailImage>();
            Product product = new Product();

            String titleUrl = titleUrls.get(i);
            String title = productTitles.get(i);
            String type = productTypes.get(i);
            float price = prices.get(i);

            titleImage.setUrl(titleUrl);
            List<String> thumbnailUrl = thumbnailUrls.get(i);

            for (String s : thumbnailUrl) {
                Thumbnail thumbnail = new Thumbnail();
                thumbnail.setUrl(s);
                thumbnails.add(thumbnail);
            }
            product.setAmount(1);
            product.setMeasure(1);
            product.setTitle(title);
            product.setType(type);
            product.setPrice(price);
            product.setTitleImage(titleImage);
            product.setThumbnails(thumbnails);
            //保存失败直接退出
            Assert.notNull(productRepository.save(product));
        }
    }

    public void importImages(){
        TitleImage titleImage = new TitleImage();
        List<Thumbnail> thumbnails = new LinkedList<Thumbnail>();
        titleImage.setUrl("http://p2.ol-img.com/product/100x100/3/292/524401b4410c4.jpg");

        String[] thumbnailsUrls = {
                "http://p2.ol-img.com/product/raw/3/292/524401c7afd06.jpg",
                "http://p2.ol-img.com/product/raw/3/292/524401c6af95f.jpg",
                "http://p2.ol-img.com/product/raw/3/292/524401c53d10d.jpg",
                "http://p2.ol-img.com/product/raw/3/292/524401c3ef85d.jpg",
                "http://p2.ol-img.com/product/raw/3/292/524401c30cba8.jpg",
                "http://p2.ol-img.com/product/raw/3/292/524401b4410c4.jpg"
        };

        return;
    }


}