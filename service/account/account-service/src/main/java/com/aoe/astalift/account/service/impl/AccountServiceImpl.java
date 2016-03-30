package com.aoe.astalift.account.service.impl;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.account.entity.SignIn;
import com.aoe.astalift.account.entity.User;
import com.aoe.astalift.account.entity.UserRole;
import com.aoe.astalift.account.repository.SignInRepository;
import com.aoe.astalift.account.repository.UserRepository;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.common.dto.BaseResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by joey on 16-3-11.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    SignInRepository signInRepository;

    @Resource
    UserRepository userRepository;

    public BaseResponse<AccountInfo> signIn(String signInString, String password) {
        AccountInfo accountInfo = new AccountInfo();
        Integer userId = null;

        SignIn signIn = null;
        SignIn byMobile = signInRepository.findByMobile(signInString);
        if(null!= byMobile){
           signIn = byMobile;
        }else{
            SignIn byEmail = signInRepository.findByEmail(signInString);
            if(null!= byEmail){
                signIn = byEmail;
            }else{
                SignIn byUserName = signInRepository.findByUserName(signInString);
                if(null != byUserName){
                    signIn = byUserName;
                }
            }
        }

        if(null != signIn){
            //add Log in
            if(signIn.getPassword().equals(password)){
                userId = signIn.getUser().getId();
                accountInfo.setUserId(userId);
                Set<UserRole> userRoles = signIn.getUser().getUserGroup().getUserRoles();
                for (UserRole userRole : userRoles) {
                    accountInfo.getRoles().add(userRole.getName());
                }
                accountInfo.setUserName(signIn.getUser().getUserProfile().getRealName());
                return new BaseResponse<AccountInfo>(accountInfo);
            }
        }

        return new BaseResponse<AccountInfo>(new AccountError.PWDNotMatch());
    }

    public BaseResponse<AccountInfo> getAccountInfo(Integer userId) {
        AccountInfo accountInfo = new AccountInfo();
        User user = userRepository.findOne(userId);
        if(null == user){
            return new BaseResponse<AccountInfo>(new AccountError.UserNotExist());
        }

        Set<UserRole> userRoles = user.getUserGroup().getUserRoles();
        for (UserRole userRole : userRoles) {
            accountInfo.getRoles().add(userRole.getName());
        }
        accountInfo.setUserId(userId);
        accountInfo.setUserName(user.getUserProfile().getRealName());
        return new BaseResponse<AccountInfo>(accountInfo);
    }
}
