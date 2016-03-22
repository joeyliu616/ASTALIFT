package com.aoe.astalift.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-11.
 */
public class LoginDto {
    @JsonProperty
    private String singInString;
    @JsonProperty
    private String password;

    public String getSingInString() {
        return singInString;
    }

    public void setSingInString(String singInString) {
        this.singInString = singInString;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
