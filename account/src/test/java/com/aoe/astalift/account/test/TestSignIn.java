package com.aoe.astalift.account.test;

import com.aoe.astalift.account.entity.SignIn;
import com.aoe.astalift.account.repository.SignInRepository;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-19.
 */
@Rollback(false)
public class TestSignIn extends TestTemplate {

    @Resource
    SignInRepository signInRepository;

    @Test
    public void addSignIn(){
        String userName = "joeyliu616";
        String mobile = "18566231281";
        String password = "123456";
        String email = "joeyliu616@gmail.com";

        Assert.notNull(signInRepository);

        SignIn signIn = new SignIn();
        signIn.setEmail(email);
        signIn.setMobile(mobile);
        signIn.setUserName(userName);
        signIn.setPassword(password);

        SignIn save = signInRepository.save(signIn);

        Assert.notNull(save);
        Assert.isTrue(save.getPassword().equals(signIn.getPassword()));

        SignIn byMobile = signInRepository.findByMobile(mobile);
        Assert.notNull(byMobile);
        Assert.noNullElements(new Object[]{byMobile.getCreateTime(), byMobile.getUpdateTime()});


    }
}
