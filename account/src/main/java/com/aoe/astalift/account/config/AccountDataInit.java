package com.aoe.astalift.account.config;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.entity.*;
import com.aoe.astalift.account.repository.SignInRepository;
import com.aoe.astalift.account.repository.UserGroupRepository;
import com.aoe.astalift.account.repository.UserRepository;
import com.aoe.astalift.account.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;

/**
 * Created by joey on 16-3-19.
 */
@Component
@Profile("data-init")
public class AccountDataInit {

    private static Logger logger = LoggerFactory.getLogger(AccountDataInit.class);

    @Resource
    SignInRepository signInRepository;

    @Resource
    UserGroupRepository userGroupRepository;

    @Resource
    UserRoleRepository userRoleRepository;

    @Resource
    UserRepository userRepository;


    String agentUserName = "jc0918";
    String agentMobile = "18001691011";
    String agentEmail = "jc0918@163.com";
    String agentPassword = "123456";
    String agentAddress = "上海杨浦区四平路1239号同济大学内(近彰武路)";

    String distributorUserName = "joeyliu616";
    String distributorEmail = "joeyliu616@live.cn";
    String distributorMobile = "18566231281";
    String distributorPassword = "123456";
    String distributorAddress = "新疆昌吉北京南路";

    String retailerUserName = "gph203";
    String retailerEmail = "gph203@163.com";
    String retailerMobile = "18666665875";
    String retailerPassword = "123456";
    String retailerAddress = "湖北麻城黄金桥";

    @PostConstruct
    public void init(){
        addUserRole();
        addGroup();
        addUser();
    }


    private void addUserRole(){
        logger.info("add Role start");

        UserRole sell = new UserRole();
        sell.setName("sell");
        userRoleRepository.save(sell);

        UserRole buy = new UserRole();
        buy.setName("buy");
        userRoleRepository.save(buy);

        logger.info("add Role success");

    }


    private void addGroup(){

        logger.info("add group start");

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
        distributorRoles.add(sell);
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

        UserGroup agent = userGroupRepository.findByName("general-anent");
        Assert.isTrue(userGroupRepository.findByName("general-anent").getUserRoles().size() == 1);
        Assert.isTrue(userGroupRepository.findByName("retailer").getUserRoles().size() == 1);
        Assert.isTrue(userGroupRepository.findByName("distributor").getUserRoles().size() == 2 );

        logger.info("add group success");
    }

    private void addUser(){

        User agent = new User();
        UserGroup agentGroup = userGroupRepository.findByName("general-anent");
        agent.setUserGroup(agentGroup);
        UserProfile agentProfile = new UserProfile();
        agentProfile.setAddress(agentAddress);
        agentProfile.setMobile(agentMobile);
        agentProfile.setEmail(agentEmail);
        agent.setUserProfile(agentProfile);
        agent = userRepository.save(agent);

        SignIn agentSignIn = new SignIn();
        agentSignIn.setUserName(agentUserName);
        agentSignIn.setEmail(agentEmail);
        agentSignIn.setMobile(agentMobile);
        agentSignIn.setPassword(agentPassword);
        agentSignIn.setUser(agent);
        signInRepository.save(agentSignIn);
        logger.info("add agent user: {}", agentUserName);


        User distributor = new User();
        UserGroup distributorGroup = userGroupRepository.findByName("distributor");
        distributor.setUserGroup(distributorGroup);
        UserProfile distributorProfile = new UserProfile();
        distributorProfile.setEmail(distributorEmail);
        distributorProfile.setAddress(distributorAddress);
        distributorProfile.setMobile(distributorMobile);
        distributor.setUserProfile(distributorProfile);
        distributor = userRepository.save(distributor);

        SignIn distributorSignIn = new SignIn();
        distributorSignIn.setUserName(distributorUserName);
        distributorSignIn.setEmail(distributorEmail);
        distributorSignIn.setMobile(distributorMobile);
        distributorSignIn.setPassword(distributorPassword);
        distributorSignIn.setUser(distributor);
        signInRepository.save(distributorSignIn);
        logger.info("add distributor user: {}", distributorUserName);


        User retailer = new User();
        UserGroup retailerGroup = userGroupRepository.findByName("retailer");
        retailer.setUserGroup(retailerGroup);
        UserProfile retailerProfile = new UserProfile();
        retailerProfile.setMobile(retailerMobile);
        retailerProfile.setEmail(retailerEmail);
        retailerProfile.setAddress(retailerAddress);
        retailer.setUserProfile(retailerProfile);
        retailer = userRepository.save(retailer);

        SignIn retailerSignIn = new SignIn();
        retailerSignIn.setUserName(retailerUserName);
        retailerSignIn.setEmail(retailerEmail);
        retailerSignIn.setMobile(retailerMobile);
        retailerSignIn.setPassword(retailerPassword);
        retailerSignIn.setUser(retailer);
        signInRepository.save(retailerSignIn);
        logger.info("add retailer user: {}", retailerUserName);

        logger.info("add signIn success");

    }


}
