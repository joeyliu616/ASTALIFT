package com.aoe.astalift.account.service.impl;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.dto.util.ProfileUtil;
import com.aoe.astalift.account.entity.User;
import com.aoe.astalift.account.entity.UserProfile;
import com.aoe.astalift.account.repository.UserRepository;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-22.
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Resource
    UserRepository userRepository;

    public BaseResponse<ProfileInfo> getUserProfile(Integer userId) {
        User user = userRepository.findOne(userId);
        if(null == user){
            return new BaseResponse<ProfileInfo>(new AccountError.UserNotExist());
        }
        ProfileInfo profileInfo = ProfileUtil.createProfileInfo(user.getUserProfile());
        return new BaseResponse<ProfileInfo>(profileInfo);
    }

    public BaseResponse<ProfileInfo> saveUserProfile(ProfileInfo profileInfo) {
        if(null == profileInfo || null == profileInfo.getUserId()){
            return new BaseResponse<ProfileInfo>(new CommonErrors.NoNullError());
        }
        Integer userId = profileInfo.getUserId();
        User user = userRepository.findOne(userId);
        if(null == user){
            return new BaseResponse<ProfileInfo>(new AccountError.UserNotExist());
        }

        UserProfile userProfileEntity = ProfileUtil.createUserProfileEntity(profileInfo);
        user.setUserProfile(userProfileEntity);

        User save = userRepository.save(user);
        if(null == save){
            return new BaseResponse<ProfileInfo>(new CommonErrors.DataBaseError());
        }
        return new BaseResponse<ProfileInfo>(profileInfo);
    }
}
