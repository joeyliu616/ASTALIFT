package com.aoe.astalift.account.service;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.common.dto.BaseResponse;

/**
 * Created by joey on 16-3-22.
 */
public interface ProfileService {
    BaseResponse<ProfileInfo> getUserProfile(Integer userId);
    BaseResponse<ProfileInfo> saveUserProfile(ProfileInfo profileInfo);
}
