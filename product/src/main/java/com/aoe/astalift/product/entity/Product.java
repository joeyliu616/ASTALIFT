package com.aoe.astalift.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String productTitle;

    //保湿水， 眼霜等
    private Integer type;

    //计量单位. 如眼霜==>毫升. 面膜==>片
    private Integer measure;

    //总量 59ml 眼霜
    private Integer amount;

    //产品详情. 可以是大段文字
    private String productDetails;

    //产品首页图. 通常为缩略图的第一个
    private String titleImageUrl;

    //产品缩略图
    private List<String> thumbnailUrls;

    //产品详情图片.
    private List<String> detailsImageUrls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMeasure() {
        return measure;
    }

    public void setMeasure(Integer measure) {
        this.measure = measure;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public List<String> getThumbnailUrls() {
        return thumbnailUrls;
    }

    public void setThumbnailUrls(List<String> thumbnailUrls) {
        this.thumbnailUrls = thumbnailUrls;
    }

    public List<String> getDetailsImageUrls() {
        return detailsImageUrls;
    }

    public void setDetailsImageUrls(List<String> detailsImageUrls) {
        this.detailsImageUrls = detailsImageUrls;
    }
}
