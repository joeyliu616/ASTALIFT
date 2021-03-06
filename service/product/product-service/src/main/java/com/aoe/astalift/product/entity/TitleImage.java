package com.aoe.astalift.product.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by joey on 16-3-16.
 */
@Entity
@Table(name="t_product_title_image")
public class TitleImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String url;
    private Date createTime;
    private Boolean enable = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
