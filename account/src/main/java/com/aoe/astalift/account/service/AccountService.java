package com.aoe.astalift.account.service;

import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.common.dto.BaseResponse;

/**
 * Created by joey on 16-3-11.
 */
public interface AccountService {
    BaseResponse<AccountInfo> signIn(String signInString, String password);
    BaseResponse<AccountInfo> getAccountInfo(Integer userId);
}