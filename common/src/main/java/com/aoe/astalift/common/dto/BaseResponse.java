package com.aoe.astalift.common.dto;

/**
 * Created by joey on 16-3-11.
 */
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;


    public BaseResponse() {
        this.code = 0;
        this.msg = "";
        this.data = null;
    }

    public BaseResponse(T data){
        this.code = 0;
        this.msg = "";
        this.data = data;
    }

    public BaseResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public BaseResponse(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
