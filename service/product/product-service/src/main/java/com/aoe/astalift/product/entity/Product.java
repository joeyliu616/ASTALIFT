package com.aoe.astalift.product.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by joey on 16-3-16.
 */
@Entity
@Table
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    //保湿水， 眼霜等
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float price;

    //计量单位. 如眼霜==>毫升. 面膜==>片
    private Integer measure;

    //总量 59ml 眼霜
    private Integer amount;

    //产品详情. 可以是大段文字
    private String detail;

    //产品首页图. 通常为缩略图的第一个
    @OneToOne(cascade=CascadeType.ALL)
    private TitleImage titleImage;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Thumbnail> thumbnails;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<DetailImage> detailImages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public TitleImage getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(TitleImage titleImage) {
        this.titleImage = titleImage;
    }

    public Set<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Set<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Set<DetailImage> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(Set<DetailImage> detailImages) {
        this.detailImages = detailImages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
