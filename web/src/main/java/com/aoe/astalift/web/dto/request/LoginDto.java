package com.aoe.astalift.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-11.
 */
public class LoginDto {
    @JsonProperty
    private String mobile;
    @JsonProperty
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
