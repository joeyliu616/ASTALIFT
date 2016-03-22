package com.aoe.astalift.account.dto.util;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.entity.UserProfile;

/**
 * Created by joey on 16-3-22.
 */
public class ProfileUtil {

    public static ProfileInfo createProfileInfo(UserProfile userProfile){
        if(null == userProfile){
            return null;
        }

        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setUserId(userProfile.getId());
        profileInfo.setAddress(userProfile.getAddress());
        profileInfo.setEmail(userProfile.getEmail());
        profileInfo.setMobile(userProfile.getMobile());
        profileInfo.setQq(userProfile.getQq());
        profileInfo.setWechatId(userProfile.getWechatId());
        profileInfo.setRealName(userProfile.getRealName());
        return profileInfo;
    }

    public static UserProfile createUserProfileEntity(ProfileInfo profileInfo){
        if(null == profileInfo){
            return null;
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setWechatId(profileInfo.getWechatId());
        userProfile.setQq(profileInfo.getQq());
        userProfile.setMobile(profileInfo.getMobile());
        userProfile.setEmail(profileInfo.getEmail());
        userProfile.setAddress(profileInfo.getAddress());
        userProfile.setRealName(profileInfo.getRealName());
        return userProfile;
    }
}
