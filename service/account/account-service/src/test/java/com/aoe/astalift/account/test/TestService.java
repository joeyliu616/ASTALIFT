package com.aoe.astalift.account.test;

import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.common.dto.BaseResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-19.
 */
public class TestService extends TestTemplate {
    @Resource
    AccountService accountService;

    @Test
    @Ignore
    public void foo(){
        Assert.notNull(accountService);

        BaseResponse<AccountInfo> accountInfoBaseResponse = accountService.signIn("18566231281", "123456");
        Assert.isTrue(accountInfoBaseResponse.getCode() == 0);
        Assert.isTrue(accountInfoBaseResponse.getData().getRoles().size() == 2);


        BaseResponse<AccountInfo> joeyliu616 = accountService.signIn("joeyliu616", "12345");
        Assert.isTrue(joeyliu616.getCode() != 0);

        BaseResponse<AccountInfo> accountInfoBaseResponse1 = accountService.signIn("joeyliu616@live.xx", "123456");
        Assert.isTrue(accountInfoBaseResponse1.getCode() != 0);

    }
}
