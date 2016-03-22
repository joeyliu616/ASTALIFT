package com.aoe.astalift.web.test;

import com.aoe.astalift.web.Main;
import com.aoe.astalift.web.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by joey on 16-3-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
@WebIntegrationTest
public class TestSessionService {

    @Resource
    SessionService sessionService;

    @Test
    public void foo(){
        Integer userId = 120;
        String fakeSessionKey = UUID.randomUUID().toString().replaceAll("-", "");
        Assert.isTrue(null == sessionService.getUserId(fakeSessionKey));
        String sessionKey = sessionService.saveUserSessionKey(userId);
        Assert.notNull(sessionKey);
        Assert.isTrue(sessionService.getUserId(sessionKey).compareTo(userId) == 0);
        Assert.isTrue(sessionService.deleteSessionKey(sessionKey));
        Assert.isNull(sessionService.getUserId(sessionKey));

    }
}
;