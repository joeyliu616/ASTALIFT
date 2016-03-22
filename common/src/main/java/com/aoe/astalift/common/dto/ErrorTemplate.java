package com.aoe.astalift.common.dto;

/**
 * Created by joey on 16-3-11.
 */
public class ErrorTemplate {

    /*
    *   public Integer code = 10000;
        public String msg = "数据不能为空";
          Integer code = 10001;
                String msg = "未知错误,请联系管理员";
    * */

    public Integer code = null;
    public String msg = null;

    protected ErrorTemplate(Integer code, String msg) {
        this.code =  code;
        this.msg = msg;
    }

}
