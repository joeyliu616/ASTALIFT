package com.aoe.astalift.web.controller;

import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.web.annotation.SessionValidIgnore;
import com.aoe.astalift.web.constants.SessionConstants;
import com.aoe.astalift.web.dto.request.LoginDto;
import com.aoe.astalift.web.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by joey on 16-3-7.
 */
@RestController
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    ObjectMapper objectMapper;

    @Resource(name="accountService")
    AccountService accountService;

    @Resource(name="sessionService")
    SessionService sessionService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @SessionValidIgnore
    public BaseResponse<AccountInfo> login(@RequestBody LoginDto loginDto,HttpSession session) {

        if(null == loginDto || null == loginDto.getSingInString() || null == loginDto.getPassword()){
            return new BaseResponse(new CommonErrors.NoNullError());
        }
        BaseResponse<AccountInfo> infoBaseResponse = accountService.signIn(loginDto.getSingInString(), loginDto.getPassword());
        if(infoBaseResponse.getCode() == 0){
            session.setAttribute("user_id", infoBaseResponse.getData().getUserId());
            String sessionKey = sessionService.saveUserSessionKey(infoBaseResponse.getData().getUserId());
            session.setAttribute(SessionConstants.SESSION_KEY, sessionKey);
        }
        return infoBaseResponse;
    }

    @RequestMapping(value = "/auth/logout", method = RequestMethod.DELETE)
    public BaseResponse logout(HttpSession session, @RequestParam(required = true) String sessionKey){
        session.removeAttribute("user_id");
        sessionService.deleteSessionKey(sessionKey);
        return new BaseResponse();
    }

    @RequestMapping(value = "/auth/accountInfo", method = RequestMethod.GET)
    public BaseResponse<AccountInfo> getAccountInfo(HttpSession session){
        Integer userId = (Integer) session.getAttribute("user_id");
        return accountService.getAccountInfo(userId);
    }
}
