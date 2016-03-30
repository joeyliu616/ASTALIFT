package com.aoe.astalift.web.advice;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.web.exception.SessionInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by joey on 16-3-21.
 */
@ControllerAdvice(basePackages = { "com.aoe.astalift.web.controller" })
public class ServerErrorAdvice extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ServerErrorAdvice.class);

    @ExceptionHandler(SessionInvalidException.class)
    public final ResponseEntity handleSessionInvalidException (
            SessionInvalidException ex){
        return new ResponseEntity<BaseResponse>(new BaseResponse(new AccountError.SessionInvalid()), HttpStatus.FORBIDDEN);
    }


}
