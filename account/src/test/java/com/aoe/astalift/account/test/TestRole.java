package com.aoe.astalift.account.test;

import com.aoe.astalift.account.entity.UserRole;
import com.aoe.astalift.account.repository.UserRoleRepository;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-19.
 */
public class TestRole extends TestTemplate {

    @Resource
    UserRoleRepository userRoleRepository;


    @Test
    public void testSave(){
        UserRole sell = new UserRole();
        sell.setName("sell");
        userRoleRepository.save(sell);

        UserRole buy = new UserRole();
        buy.setName("buy");
        userRoleRepository.save(buy);

    }

}
