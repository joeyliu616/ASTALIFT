package com.aoe.astalift.account.entity;

import javax.persistence.*;

/**
 * Created by joey on 16-3-7.
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String UserName;
    private String password;
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
