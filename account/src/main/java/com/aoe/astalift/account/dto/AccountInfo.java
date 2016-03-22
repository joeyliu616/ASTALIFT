package com.aoe.astalift.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joey on 16-3-17.
 */
public class AccountInfo implements Serializable {

    @JsonProperty
    private Integer userId;

    @JsonProperty
    List<String> roles = new ArrayList<String>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
