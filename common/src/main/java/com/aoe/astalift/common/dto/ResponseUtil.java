package com.aoe.astalift.common.dto;

/**
 * Created by joey on 16-3-23.
 */
public class ResponseUtil {
    public static boolean isResponseSuccess(BaseResponse response){
        if(null != response && response.getCode() == 0){
            return true;
        }
        return false;
    }

    public static ErrorTemplate getErrorTemplate(BaseResponse response){
        if(null != response && 0 != response.getCode()){
            return new ErrorTemplate(response.getCode(), response.getMsg());
        }
        return null;
    }
}
