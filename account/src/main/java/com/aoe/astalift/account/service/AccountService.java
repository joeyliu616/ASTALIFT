package com.aoe.astalift.account.service;

import com.aoe.astalift.common.dto.BaseResponse;

/**
 * Created by joey on 16-3-11.
 */
public interface AccountService {
    BaseResponse isUsernamePasswordMatch(String username, String password);
}