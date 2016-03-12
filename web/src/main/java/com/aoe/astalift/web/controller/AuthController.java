package com.aoe.astalift.web.controller;

import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.Error;
import com.aoe.astalift.web.dto.request.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-7.
 */
@Controller
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    ObjectMapper objectMapper;

    @Resource(name="accountService")
    AccountService accountService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestBody LoginDto loginDto) {

        if(null == loginDto || null == loginDto.getMobile() || null == loginDto.getPassword()){
            return new BaseResponse(Error.CommonError.NotNullCode,Error.CommonError.NotNullMsg);
        }
        String username = loginDto.getMobile();
        String password = loginDto.getPassword();
        return accountService.isUsernamePasswordMatch(username,password);
    }
}
