package com.aoe.astalift.order.test;

import com.aoe.astalift.order.OrderServiceConfigHook;
import com.aoe.astalift.order.entity.OrderStatusHistory;
import com.aoe.astalift.product.ProductServiceConfigHook;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joey on 16-3-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({OrderServiceConfigHook.class, ProductServiceConfigHook.class})
@Transactional
public abstract class TestTemplate {
}
