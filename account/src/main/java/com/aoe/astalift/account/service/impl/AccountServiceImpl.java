package com.aoe.astalift.account.service.impl;

import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.common.dto.BaseResponse;
import org.springframework.stereotype.Service;

/**
 * Created by joey on 16-3-11.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    public BaseResponse isUsernamePasswordMatch(String username, String password) {
        return new BaseResponse();
    }
}
