package com.aoe.astalift.account.test;

import com.aoe.astalift.account.AccountServiceConfigHook;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joey on 16-3-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({AccountServiceConfigHook.class})
@Transactional
public abstract class TestTemplate {
}
