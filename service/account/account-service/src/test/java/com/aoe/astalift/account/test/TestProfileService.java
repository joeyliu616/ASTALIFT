package com.aoe.astalift.account.test;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.entity.UserProfile;
import com.aoe.astalift.account.repository.UserRepository;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-22.
 */
public class TestProfileService extends TestTemplate {

    @Resource
    ProfileService profileService;

    @Resource
    UserRepository userRepository;

    @Test
    @Ignore
    public void foo(){

        Assert.notNull(userRepository);
        Assert.notNull(profileService);

        BaseResponse<ProfileInfo> u1 = profileService.getUserProfile(1);
        BaseResponse<ProfileInfo> u2 = profileService.getUserProfile(2);
        BaseResponse<ProfileInfo> u3 = profileService.getUserProfile(3);

        Assert.isNull(u1.getData());
        Assert.isNull(u2.getData());
        Assert.isNull(u3.getData());


        ProfileInfo p1 = new ProfileInfo();
        ProfileInfo p2 = new ProfileInfo();
        ProfileInfo p3 = new ProfileInfo();


        p1.setMobile("18001691011");
        p1.setEmail("jc0918@163.com");
        p1.setAddress("上海杨浦区四平路1239号同济大学内(近彰武路)");
        p1.setUserId(1);
        profileService.saveUserProfile(p1);

        Assert.isTrue(profileService.getUserProfile(1).getData() != null);
        Assert.isTrue(profileService.getUserProfile(1).getData().getMobile().equals("18001691011"));

        Assert.isTrue(userRepository.getOne(1).getUserProfile() != null);
        Assert.isTrue(userRepository.getOne(1).getUserProfile().getMobile().equals("18001691011"));

        p1.setEmail("jc0918@163.comx");
        profileService.saveUserProfile(p1);
        Assert.isTrue(userRepository.getOne(1).getUserProfile().getEmail().equals("jc0918@163.comx"));

    }
}
