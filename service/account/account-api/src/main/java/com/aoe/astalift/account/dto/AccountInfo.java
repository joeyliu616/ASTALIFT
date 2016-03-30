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

    @JsonProperty
    private String userName;

    @JsonProperty("session_key")
    String sessionKey;

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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
