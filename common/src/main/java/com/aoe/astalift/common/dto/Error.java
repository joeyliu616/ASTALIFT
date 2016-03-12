package com.aoe.astalift.common.dto;

/**
 * Created by joey on 16-3-11.
 */
public interface Error {


    interface CommonError{
        Integer NotNullCode = 10000;
        String NotNullMsg = "数据不能为空";

        Integer UnknownErrorCode = 10001;
        String UnknownErrorMsg = "未知错误,请联系管理员";

    }

    interface AccountError {
        Integer UsernamePwdNotMatchCode = 20000;
        String UsernamePwdNotMatchMsg = "账号或者密码不正确";
    }
}
