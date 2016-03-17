package com.aoe.astalift.web;

import com.aoe.astalift.account.AccountServiceConfigHook;
import com.aoe.astalift.order.OrderServiceConfigHook;
import com.aoe.astalift.product.ProductServiceConfigHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by joey on 16-3-7.
 */
@SpringBootApplication
@Import({AccountServiceConfigHook.class, OrderServiceConfigHook.class, ProductServiceConfigHook.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}