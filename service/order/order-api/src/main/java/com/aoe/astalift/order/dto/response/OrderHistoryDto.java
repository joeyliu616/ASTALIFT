package com.aoe.astalift.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joey on 16-3-23.
 */
public class OrderHistoryDto implements Serializable{

    @JsonProperty
    private Integer status;
    @JsonProperty
    private String desc;
    @JsonProperty
    private Date createTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
