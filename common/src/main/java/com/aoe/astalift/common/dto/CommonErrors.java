package com.aoe.astalift.common.dto;

/**
 * Created by joey on 16-3-20.
 */
public interface CommonErrors {

    class NoNullError extends ErrorTemplate {

        public NoNullError() {
            super(10000, "数据不能为空");
        }

    }

    class UnknownError extends ErrorTemplate{

        public UnknownError() {
            super(10001, "系统未知错误");
        }

    }
}
