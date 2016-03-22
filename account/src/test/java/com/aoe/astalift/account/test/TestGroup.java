package com.aoe.astalift.account.test;

import com.aoe.astalift.account.entity.User;
import com.aoe.astalift.account.entity.UserGroup;
import com.aoe.astalift.account.entity.UserRole;
import com.aoe.astalift.account.repository.UserGroupRepository;
import com.aoe.astalift.account.repository.UserRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * Created by joey on 16-3-19.
 */
public class TestGroup extends TestTemplate {

    @Resource
    UserGroupRepository userGroupRepository;

    @Resource
    UserRoleRepository userRoleRepository;

    @Test
    public void testAddGroup() throws JsonProcessingException {
        UserRole sell = userRoleRepository.findByName("sell");
        UserRole buy = userRoleRepository.findByName("buy");

        UserGroup generalAnent = new UserGroup();
        generalAnent.setName("general-anent");

        HashSet<UserRole> anentRoles = new HashSet<UserRole>();
        anentRoles.add(sell);
        generalAnent.setUserRoles(anentRoles);
        userGroupRepository.save(generalAnent);


        HashSet<UserRole> distributorRoles = new HashSet<UserRole>();
        UserGroup distributor = new UserGroup();
        distributor.setName("distributor");
        distributorRoles.add(buy);
        distributor.setUserRoles(distributorRoles);
        userGroupRepository.save(distributor);

        UserGroup retailer = new UserGroup();
        retailer.setName("retailer");
        HashSet<UserRole> retailerRole = new HashSet<UserRole>();
        retailerRole.add(buy);
        retailer.setUserRoles(retailerRole);
        userGroupRepository.save(retailer);

        Assert.notNull(userGroupRepository.findByName("retailer"));
        Assert.notNull(userGroupRepository.findByName("distributor"));
        Assert.notNull(userGroupRepository.findByName("general-anent"));

        distributor = userGroupRepository.findByName("general-anent");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("----" + objectMapper.writeValueAsString(distributor));

        Assert.isTrue(userGroupRepository.findByName("general-anent").getUserRoles().size() == 1);
        Assert.isTrue(userGroupRepository.findByName("retailer").getUserRoles().size() == 1);



    }
}
