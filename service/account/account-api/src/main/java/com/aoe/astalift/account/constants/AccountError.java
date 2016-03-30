package com.aoe.astalift.account.constants;


import com.aoe.astalift.common.dto.ErrorTemplate;

/**
 * Created by joey on 16-3-19.
 */
public interface AccountError {

    class PWDNotMatch extends ErrorTemplate {

        public PWDNotMatch() {
            super(20001,"账号密码不匹配");
        }
    }

    class SessionInvalid extends ErrorTemplate{
        public SessionInvalid() {
            super(20002,"非法的鉴权信息");
        }
    }

    class UserNotExist extends ErrorTemplate {
        public UserNotExist() {
            super(20003, "用户不存在");
        }
    }

    class InvalidUserRole extends ErrorTemplate {
        public InvalidUserRole() {
            super(20004, "用户角色不存在");
        }
    }

    class PermissionDenied extends ErrorTemplate {
        public PermissionDenied() {
            super(20004, "没有权限访问该服务");
        }
    }
}
